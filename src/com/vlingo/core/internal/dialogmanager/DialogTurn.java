package com.vlingo.core.internal.dialogmanager;

import android.content.Context;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.ActionInterface;
import com.vlingo.core.internal.dialogmanager.events.QueryEvent;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.VVSDispatcher;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.SafeReaderHandler;
import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.core.internal.safereader.SafeReaderAlert;
import com.vlingo.core.internal.util.ActivityUtil;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.sdk.recognition.NBestData;
import com.vlingo.sdk.recognition.VLRecognitionResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DialogTurn
{
  private VvsActionHandlerListenerProxy actionHandlerProxy = new VvsActionHandlerListenerProxy(null);
  private LinkedList<SafeReaderAlert> alerts;
  private int asyncHandlersFinished;
  private int asyncHandlersStarted;
  private DialogTurnListener dialogTurnListener;
  private boolean doesEventResolveQuery = false;
  private DialogFieldID fieldId;
  private List<DialogEvent> pendingConcatenatedEvents = new ArrayList();
  private Map<String, DialogEvent> pendingUniqueEvents = new HashMap();
  private VLRecognitionResult result;
  private boolean turnWasCancelled = false;

  DialogTurn(DialogTurn paramDialogTurn, VLRecognitionResult paramVLRecognitionResult, DialogTurnListener paramDialogTurnListener)
  {
    if (paramDialogTurn == null)
      throw new IllegalArgumentException("previousTurn is null");
    init(paramVLRecognitionResult, paramDialogTurnListener);
    this.fieldId = paramDialogTurn.fieldId;
    this.pendingUniqueEvents.putAll(paramDialogTurn.pendingUniqueEvents);
    this.pendingConcatenatedEvents.addAll(paramDialogTurn.pendingConcatenatedEvents);
  }

  DialogTurn(VLRecognitionResult paramVLRecognitionResult, DialogFieldID paramDialogFieldID, DialogTurnListener paramDialogTurnListener)
  {
    init(paramVLRecognitionResult, paramDialogTurnListener);
    this.fieldId = paramDialogFieldID;
  }

  DialogTurn(LinkedList<SafeReaderAlert> paramLinkedList, DialogFieldID paramDialogFieldID, DialogTurnListener paramDialogTurnListener)
  {
    if ((paramLinkedList == null) || (paramLinkedList.isEmpty()))
      throw new IllegalArgumentException("alerts is null or empty");
    init(null, paramDialogTurnListener);
    this.alerts = paramLinkedList;
    this.fieldId = paramDialogFieldID;
  }

  private void finishActionListProcessing()
  {
    if (!this.turnWasCancelled)
      this.dialogTurnListener.onDoneProcessingActions(this);
  }

  private void init(VLRecognitionResult paramVLRecognitionResult, DialogTurnListener paramDialogTurnListener)
  {
    if (paramDialogTurnListener == null)
      throw new IllegalArgumentException("dialogTurnListener is null");
    this.result = paramVLRecognitionResult;
    this.dialogTurnListener = paramDialogTurnListener;
  }

  private void setFieldId(DialogFieldID paramDialogFieldID)
  {
    this.fieldId = paramDialogFieldID;
  }

  void addEvent(DialogEvent paramDialogEvent)
  {
    if (paramDialogEvent.isUnique())
      this.pendingUniqueEvents.put(paramDialogEvent.getName(), paramDialogEvent);
    while (true)
    {
      return;
      this.pendingConcatenatedEvents.add(paramDialogEvent);
      if ((this.doesEventResolveQuery) || (!(paramDialogEvent instanceof QueryEvent)))
        continue;
      this.doesEventResolveQuery = ((QueryEvent)paramDialogEvent).isMeaningful();
    }
  }

  public void cancel()
  {
    this.turnWasCancelled = true;
  }

  public void clearEvents()
  {
    this.pendingUniqueEvents.clear();
    this.pendingConcatenatedEvents.clear();
  }

  public boolean doesEventResolveQuery()
  {
    return this.doesEventResolveQuery;
  }

  public DialogFieldID getFieldId()
  {
    return this.fieldId;
  }

  public String getGUID()
  {
    if (this.result != null);
    for (String str = this.result.getDialogGUID(); ; str = null)
      return str;
  }

  public String getGUttId()
  {
    if (this.result != null);
    for (String str = this.result.getGUttId(); ; str = null)
      return str;
  }

  public NBestData getNBestData()
  {
    return this.result.getNBestData();
  }

  public List<DialogEvent> getPendingEvents()
  {
    ArrayList localArrayList = new ArrayList(this.pendingConcatenatedEvents);
    Iterator localIterator = this.pendingUniqueEvents.values().iterator();
    while (localIterator.hasNext())
      localArrayList.add((DialogEvent)localIterator.next());
    return localArrayList;
  }

  public byte[] getServerState()
  {
    if (this.result != null);
    for (byte[] arrayOfByte = this.result.getDialogState(); ; arrayOfByte = null)
      return arrayOfByte;
  }

  public int getTurnNumber()
  {
    if (this.result != null);
    for (int i = this.result.getDialogTurn(); ; i = -1)
      return i;
  }

  public String getUserText()
  {
    if (this.result != null);
    for (String str = this.result.getResultString(); ; str = null)
      return str;
  }

  public boolean isCancelled()
  {
    return this.turnWasCancelled;
  }

  void processActions()
  {
    List localList = this.result.getActions();
    this.asyncHandlersStarted = 0;
    this.asyncHandlersFinished = 0;
    this.asyncHandlersStarted = VVSDispatcher.processActionList(localList, this.actionHandlerProxy, this);
    if (this.asyncHandlersStarted == this.asyncHandlersFinished)
      finishActionListProcessing();
  }

  void processSafeReaderMessage(SMSMMSAlert paramSMSMMSAlert, boolean paramBoolean)
  {
    SafeReaderHandler localSafeReaderHandler = new SafeReaderHandler(paramBoolean);
    localSafeReaderHandler.setListener(this.actionHandlerProxy);
    localSafeReaderHandler.setTurn(this);
    localSafeReaderHandler.init(this.alerts);
    localSafeReaderHandler.init(paramSMSMMSAlert);
    localSafeReaderHandler.executeAction(null, this.actionHandlerProxy);
    finishActionListProcessing();
  }

  void processSafeReaderMessage(boolean paramBoolean)
  {
    VVSActionHandler localVVSActionHandler = ClientSuppliedValues.getSafeReaderHandler(paramBoolean, this.alerts);
    localVVSActionHandler.setListener(this.actionHandlerProxy);
    localVVSActionHandler.setTurn(this);
    localVVSActionHandler.executeAction(null, this.actionHandlerProxy);
    finishActionListProcessing();
  }

  public static abstract interface DialogTurnListener
  {
    public abstract void endpointReco();

    public abstract void execute(ActionInterface paramActionInterface);

    public abstract void finishDialog();

    public abstract void finishTurn();

    public abstract Context getActivityContext();

    public abstract Object getState(DialogDataType paramDialogDataType);

    public abstract void interruptTurn();

    public abstract void onAsyncActionStarted();

    public abstract void onDoneProcessingActions(DialogTurn paramDialogTurn);

    public abstract void playMedia(int paramInt);

    public abstract void sendEvent(DialogEvent paramDialogEvent);

    public abstract void sendTextRequest(String paramString);

    public abstract void showUserText(String paramString);

    public abstract void showUserText(String paramString, NBestData paramNBestData);

    public abstract void showVlingoText(String paramString);

    public abstract void showVlingoTextAndTTS(String paramString1, String paramString2);

    public abstract <T> void showWidget(WidgetUtil.WidgetKey paramWidgetKey, WidgetDecorator paramWidgetDecorator, T paramT, WidgetActionListener paramWidgetActionListener);

    public abstract void startReco();

    public abstract void storeState(DialogDataType paramDialogDataType, Object paramObject);

    public abstract void tts(String paramString);

    public abstract void ttsAnyway(String paramString);

    public abstract void userCancel();
  }

  private class VvsActionHandlerListenerProxy
    implements VVSActionHandlerListener
  {
    private VvsActionHandlerListenerProxy()
    {
    }

    public void asyncHandlerDone()
    {
      if (!DialogTurn.this.turnWasCancelled)
        ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.16(this));
    }

    public void asyncHandlerStarted()
    {
      if (!DialogTurn.this.turnWasCancelled)
        ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.18(this));
    }

    public void endpointReco()
    {
      ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.10(this));
    }

    public void execute(ActionInterface paramActionInterface)
    {
      ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.3(this, paramActionInterface));
    }

    public void finishDialog()
    {
      ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.15(this));
    }

    public void finishTurn()
    {
      ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.12(this));
    }

    public Context getActivityContext()
    {
      if (DialogTurn.this.dialogTurnListener != null);
      for (Context localContext = DialogTurn.this.dialogTurnListener.getActivityContext(); ; localContext = ApplicationAdapter.getInstance().getApplicationContext())
        return localContext;
    }

    public Object getState(DialogDataType paramDialogDataType)
    {
      if (DialogTurn.this.dialogTurnListener != null);
      for (Object localObject = DialogTurn.this.dialogTurnListener.getState(paramDialogDataType); ; localObject = null)
        return localObject;
    }

    public void interruptTurn()
    {
      ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.11(this));
    }

    public void queueEvent(DialogEvent paramDialogEvent, DialogTurn paramDialogTurn)
    {
      ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.13(this, paramDialogTurn, paramDialogEvent));
    }

    public void sendEvent(DialogEvent paramDialogEvent, DialogTurn paramDialogTurn)
    {
      ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.7(this, paramDialogEvent, paramDialogTurn));
    }

    public void setFieldId(DialogFieldID paramDialogFieldID)
    {
      ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.9(this, paramDialogFieldID));
    }

    public void showUserText(String paramString)
    {
      showUserText(paramString, null);
    }

    public void showUserText(String paramString, DialogTurn paramDialogTurn)
    {
      ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.4(this, paramString, paramDialogTurn));
    }

    public void showVlingoText(String paramString)
    {
      ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.5(this, paramString));
    }

    public void showVlingoTextAndTTS(String paramString1, String paramString2)
    {
      ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.6(this, paramString1, paramString2));
    }

    public <T> void showWidget(WidgetUtil.WidgetKey paramWidgetKey, WidgetDecorator paramWidgetDecorator, T paramT, WidgetActionListener paramWidgetActionListener)
    {
      ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.17(this, paramWidgetKey, paramWidgetDecorator, paramT, paramWidgetActionListener));
    }

    public boolean startReco()
    {
      ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.8(this));
      return true;
    }

    public void storeState(DialogDataType paramDialogDataType, Object paramObject)
    {
      ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.14(this, paramDialogDataType, paramObject));
    }

    public void tts(String paramString)
    {
      ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.1(this, paramString));
    }

    public void ttsAnyway(String paramString)
    {
      ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.2(this, paramString));
    }

    public void userCancel()
    {
      ActivityUtil.runOnMainThread(new DialogTurn.VvsActionHandlerListenerProxy.19(this));
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.DialogTurn
 * JD-Core Version:    0.6.0
 */