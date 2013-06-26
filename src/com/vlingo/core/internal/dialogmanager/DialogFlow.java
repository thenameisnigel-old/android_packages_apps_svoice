package com.vlingo.core.internal.dialogmanager;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.audio.AudioPlayerProxy;
import com.vlingo.core.internal.audio.AudioRequest;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored;
import com.vlingo.core.internal.audio.MicrophoneStream;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.ActionInterface;
import com.vlingo.core.internal.dialogmanager.tasks.ActionTask;
import com.vlingo.core.internal.dialogmanager.tasks.DMServerTask;
import com.vlingo.core.internal.dialogmanager.tasks.PausableTask;
import com.vlingo.core.internal.dialogmanager.tasks.PlayAudioTask;
import com.vlingo.core.internal.dialogmanager.tasks.PlayMediaTask;
import com.vlingo.core.internal.dialogmanager.tasks.PlayTTSTask;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.display.WakeLockManager;
import com.vlingo.core.internal.logging.EventLog;
import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.core.internal.phrasespotter.PhraseSpotter;
import com.vlingo.core.internal.safereader.ISafeReaderAlertHandler;
import com.vlingo.core.internal.safereader.SafeReaderAlert;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.schedule.ScheduleTask;
import com.vlingo.core.internal.util.Alarm;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.PhoneUtil;
import com.vlingo.core.internal.util.PrecisionTimer;
import com.vlingo.core.internal.util.PrecisionTimer.Interval;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.util.TaskQueue;
import com.vlingo.core.internal.util.TaskQueue.Task;
import com.vlingo.core.internal.util.TaskQueue.TaskQueueListener;
import com.vlingo.core.internal.vlservice.VlingoApplicationService;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.recognition.NBestData;
import com.vlingo.sdk.recognition.VLAction;
import com.vlingo.sdk.recognition.VLRecognitionErrors;
import com.vlingo.sdk.recognition.VLRecognitionListener;
import com.vlingo.sdk.recognition.VLRecognitionResult;
import com.vlingo.sdk.recognition.VLRecognitionStates;
import com.vlingo.sdk.recognition.VLRecognitionWarnings;
import com.vlingo.sdk.recognition.VLRecognizer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashMap<Lcom.vlingo.core.internal.dialogmanager.DialogDataType;Ljava.lang.Object;>;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DialogFlow
  implements ISafeReaderAlertHandler
{
  private static DialogFlow instance;
  private Context activityContext;
  private LinkedList<SafeReaderAlert> alerts;
  private DialogTurn currentTurn;
  private HashMap<DialogDataType, Object> dialogData = new HashMap();
  private DialogFlowListener dialogFlowListener = new DefaultDialogFlowListener();
  private TaskQueue dialogQueue;
  private DialogFlowState dialogState = DialogFlowState.IDLE;
  private final DialogTurn.DialogTurnListener dialogTurnListener = new DialogTurnListenerImpl(null);
  private boolean isProcessingActions = false;
  private boolean isSilentMode = false;
  protected IAudioPlaybackService.AudioPlaybackListener mAudioPlaybackListener = new IAudioPlaybackService.AudioPlaybackListener()
  {
    public void onRequestCancelled(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled paramReasonCanceled)
    {
      DialogFlow.this.wakeLockManager.releaseWakeLock();
    }

    public void onRequestDidPlay(AudioRequest paramAudioRequest)
    {
      DialogFlow.this.wakeLockManager.releaseWakeLock();
    }

    public void onRequestIgnored(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored paramReasonIgnored)
    {
      DialogFlow.this.wakeLockManager.releaseWakeLock();
    }

    public void onRequestWillPlay(AudioRequest paramAudioRequest)
    {
      DialogFlow.this.wakeLockManager.acquireWakeLock();
    }
  };
  private DMServerTask pendingDMTransactionTask;
  private DialogTurn pendingSafeReaderTurn;
  private Object preservedState;
  private VLRecognitionStates recoState;
  private final TaskQueue.TaskQueueListener taskQueueListener = new TaskQueueListenerImpl(null);
  private Object taskQueueMutex = new Object();
  private HashMap<DialogFlowTaskRegulator.EventType, Set<DialogFlowTaskRegulator>> taskRegulators = new HashMap();
  private HashMap<String, String> userProperties;
  private final VLRecognitionListener vlRecognitionListener = new VLRecognitionListenerImpl(null);
  private WakeLockManager wakeLockManager = null;
  private WidgetFactory widgetFactory;

  private DialogFlow()
  {
    initQueue();
  }

  private void clearPreservedFieldID()
  {
    this.dialogTurnListener.storeState(DialogDataType.PREVIOUS_FIELD_ID, null);
  }

  private HashMap<DialogDataType, Object> cloneState()
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = this.dialogData.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      DialogDataType localDialogDataType = (DialogDataType)localEntry.getKey();
      Object localObject = localEntry.getValue();
      if (localObject == null)
        continue;
      switch (3.$SwitchMap$com$vlingo$core$internal$dialogmanager$DialogDataType[localDialogDataType.ordinal()])
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      }
      while (true)
      {
        localHashMap.put(localDialogDataType, localObject);
        break;
        try
        {
          VVSActionBase localVVSActionBase = ((Controller)localObject).clone();
          localObject = localVVSActionBase;
        }
        catch (CloneNotSupportedException localCloneNotSupportedException)
        {
          localCloneNotSupportedException.printStackTrace();
        }
        continue;
        localObject = ((ContactMatch)localObject).clone();
        continue;
        localObject = Alarm.clone((List)localObject);
        continue;
        localObject = ScheduleEvent.clone((List)localObject);
        continue;
        localObject = ContactMatch.clone((List)localObject);
        continue;
        localObject = ScheduleTask.clone((List)localObject);
      }
    }
    return (HashMap<DialogDataType, Object>)localHashMap;
  }

  private void createDMTransactionTask()
  {
    if (this.pendingDMTransactionTask == null)
      this.pendingDMTransactionTask = new DMServerTask(this.currentTurn, this.vlRecognitionListener, this.userProperties);
  }

  private void finishActionListProcessing()
  {
    synchronized (this.taskQueueMutex)
    {
      this.isProcessingActions = false;
      if (this.pendingDMTransactionTask != null)
      {
        this.dialogQueue.queueTask(this.pendingDMTransactionTask);
        this.pendingDMTransactionTask = null;
        return;
      }
      handleIdle(false);
    }
  }

  private void finishTurn()
  {
    interruptTurn();
    handleIdle(true);
  }

  private int getAudioMode()
  {
    return ((AudioManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("audio")).getMode();
  }

  public static DialogFlow getInstance()
  {
    if (instance == null)
      instance = new DialogFlow();
    return instance;
  }

  private void handleIdle(boolean paramBoolean)
  {
    if ((isIdle()) || (paramBoolean))
    {
      if (this.pendingSafeReaderTurn == null)
        break label23;
      startSafeReaderFlow();
    }
    while (true)
    {
      return;
      label23: VlingoAndroidCore.onDialogIdle();
      notifyDialogIdle();
    }
  }

  private void initQueue()
  {
    this.dialogQueue = new TaskQueue(new Handler(Looper.getMainLooper()), this.taskQueueListener);
  }

  private boolean listen(MicrophoneStream paramMicrophoneStream)
  {
    int i = 0;
    if (this.dialogFlowListener.onInterceptStartReco());
    while (true)
    {
      return i;
      clearPreservedFieldID();
      preserveState(getFieldID());
      boolean bool = PhoneUtil.phoneInUse(this.activityContext);
      if ((bool) || (getAudioMode() != 0))
      {
        if (bool);
        for (String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_phone_in_use); ; str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_mic_in_use))
        {
          if (this.activityContext == null)
            break label103;
          Toast.makeText(this.activityContext, str, 1).show();
          break;
        }
        label103: if (ApplicationAdapter.getInstance().getApplicationContext() == null)
          continue;
        Toast.makeText(ApplicationAdapter.getInstance().getApplicationContext(), str, 1).show();
        continue;
      }
      synchronized (this.taskQueueMutex)
      {
        createDMTransactionTask();
        DialogFieldID localDialogFieldID = getFieldID();
        this.pendingDMTransactionTask.performReco(localDialogFieldID, paramMicrophoneStream, this.userProperties);
        if (!this.isProcessingActions)
        {
          this.dialogQueue.queueTask(this.pendingDMTransactionTask);
          this.pendingDMTransactionTask = null;
        }
        i = 1;
      }
    }
  }

  private void notifyDialogBusy()
  {
    if (this.dialogState == DialogFlowState.IDLE)
    {
      this.dialogFlowListener.showDialogFlowStateChange(DialogFlowState.BUSY);
      this.dialogState = DialogFlowState.BUSY;
    }
  }

  private void notifyDialogIdle()
  {
    if (this.dialogState == DialogFlowState.BUSY)
    {
      this.dialogFlowListener.showDialogFlowStateChange(DialogFlowState.IDLE);
      this.dialogState = DialogFlowState.IDLE;
    }
  }

  private void preserveState(DialogFieldID paramDialogFieldID)
  {
    HashMap localHashMap = cloneState();
    localHashMap.put(DialogDataType.PREVIOUS_FIELD_ID, paramDialogFieldID);
    this.preservedState = localHashMap;
  }

  private void resetState()
  {
    this.dialogData = new HashMap();
  }

  private void restoreState(Object paramObject)
  {
    if (paramObject != null)
      this.dialogData = ((HashMap)paramObject);
  }

  private boolean sendTextRequest(String paramString)
  {
    int i = 1;
    if (PhoneUtil.phoneInUse(this.activityContext))
    {
      String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_phone_in_use);
      Toast.makeText(this.activityContext, str, i).show();
      i = 0;
    }
    while (true)
    {
      return i;
      synchronized (this.taskQueueMutex)
      {
        createDMTransactionTask();
        DialogFieldID localDialogFieldID = getFieldID();
        clearPreservedFieldID();
        this.pendingDMTransactionTask.sendTextRequest(localDialogFieldID, paramString, this.userProperties);
        if (!this.isProcessingActions)
        {
          this.dialogQueue.queueTask(this.pendingDMTransactionTask);
          this.pendingDMTransactionTask = null;
        }
      }
    }
  }

  public void addUserProperties(String paramString1, String paramString2)
  {
    if (this.userProperties != null)
      this.userProperties.put(paramString1, paramString2);
  }

  public void cancelAudio()
  {
    AudioPlayerProxy.stop();
  }

  public void cancelDialog()
  {
    cancelTurn();
    finishDialog();
  }

  @Deprecated
  public void cancelTTS()
  {
    cancelAudio();
  }

  public void cancelTurn()
  {
    synchronized (this.taskQueueMutex)
    {
      this.isProcessingActions = false;
      this.pendingDMTransactionTask = null;
      if (this.currentTurn != null)
        this.currentTurn.cancel();
      finishTurn();
      return;
    }
  }

  public void clearPendingSafeReaderTurn()
  {
    this.pendingSafeReaderTurn = null;
    if (this.alerts != null)
    {
      this.alerts.clear();
      this.alerts = null;
    }
  }

  public void deleteQueuedAudioTasks()
  {
    this.dialogQueue.deleteQueuedTaskOfType(PlayAudioTask.class);
  }

  @Deprecated
  public void deleteQueuedTtsTasks()
  {
    deleteQueuedAudioTasks();
  }

  public void endpointReco()
  {
    VLSdk.getInstance().getRecognizer().stopRecognition();
  }

  void fakeResults(VLRecognitionResult paramVLRecognitionResult)
  {
    this.vlRecognitionListener.onRecognitionResults(paramVLRecognitionResult);
  }

  public void finishDialog()
  {
    this.currentTurn = null;
    resetState();
  }

  public DialogFieldID getFieldID()
  {
    DialogFieldID localDialogFieldID = (DialogFieldID)this.dialogTurnListener.getState(DialogDataType.PREVIOUS_FIELD_ID);
    if (localDialogFieldID == null)
      if (this.currentTurn != null)
        break label36;
    label36: for (localDialogFieldID = VlingoAndroidCore.getFieldId(FieldIds.DEFAULT); ; localDialogFieldID = this.currentTurn.getFieldId())
      return localDialogFieldID;
  }

  public Object getPreservedState()
  {
    return this.preservedState;
  }

  public Set<DialogFlowTaskRegulator> getTaskRegulators(DialogFlowTaskRegulator.EventType paramEventType)
  {
    return (Set)this.taskRegulators.get(paramEventType);
  }

  WidgetFactory getWidgetFactory()
  {
    return this.widgetFactory;
  }

  // ERROR //
  public void handleAlert(LinkedList<? extends SafeReaderAlert> paramLinkedList)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 80	com/vlingo/core/internal/dialogmanager/DialogFlow:taskQueueMutex	Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 463	com/vlingo/core/internal/dialogmanager/DialogFlow:alerts	Ljava/util/LinkedList;
    //   11: ifnull +68 -> 79
    //   14: aload_0
    //   15: getfield 463	com/vlingo/core/internal/dialogmanager/DialogFlow:alerts	Ljava/util/LinkedList;
    //   18: invokevirtual 532	java/util/LinkedList:isEmpty	()Z
    //   21: ifne +58 -> 79
    //   24: aload_0
    //   25: getfield 463	com/vlingo/core/internal/dialogmanager/DialogFlow:alerts	Ljava/util/LinkedList;
    //   28: invokevirtual 533	java/util/LinkedList:iterator	()Ljava/util/Iterator;
    //   31: astore 4
    //   33: aload 4
    //   35: invokeinterface 239 1 0
    //   40: ifeq +39 -> 79
    //   43: aload 4
    //   45: invokeinterface 243 1 0
    //   50: checkcast 535	com/vlingo/core/internal/safereader/SafeReaderAlert
    //   53: astore 5
    //   55: aload_1
    //   56: aload 5
    //   58: invokevirtual 539	java/util/LinkedList:contains	(Ljava/lang/Object;)Z
    //   61: ifne -28 -> 33
    //   64: aload_1
    //   65: aload 5
    //   67: invokevirtual 542	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   70: pop
    //   71: goto -38 -> 33
    //   74: astore_3
    //   75: aload_2
    //   76: monitorexit
    //   77: aload_3
    //   78: athrow
    //   79: aload_0
    //   80: aload_1
    //   81: putfield 463	com/vlingo/core/internal/dialogmanager/DialogFlow:alerts	Ljava/util/LinkedList;
    //   84: aload_0
    //   85: getfield 463	com/vlingo/core/internal/dialogmanager/DialogFlow:alerts	Ljava/util/LinkedList;
    //   88: ifnull +49 -> 137
    //   91: aload_0
    //   92: getfield 463	com/vlingo/core/internal/dialogmanager/DialogFlow:alerts	Ljava/util/LinkedList;
    //   95: invokevirtual 532	java/util/LinkedList:isEmpty	()Z
    //   98: ifne +39 -> 137
    //   101: aload_0
    //   102: new 457	com/vlingo/core/internal/dialogmanager/DialogTurn
    //   105: dup
    //   106: aload_0
    //   107: getfield 463	com/vlingo/core/internal/dialogmanager/DialogFlow:alerts	Ljava/util/LinkedList;
    //   110: getstatic 512	com/vlingo/core/internal/dialogmanager/FieldIds:DEFAULT	Lcom/vlingo/core/internal/dialogmanager/FieldIds;
    //   113: invokestatic 516	com/vlingo/core/internal/VlingoAndroidCore:getFieldId	(Lcom/vlingo/core/internal/dialogmanager/FieldIds;)Lcom/vlingo/core/internal/dialogmanager/DialogFieldID;
    //   116: aload_0
    //   117: getfield 93	com/vlingo/core/internal/dialogmanager/DialogFlow:dialogTurnListener	Lcom/vlingo/core/internal/dialogmanager/DialogTurn$DialogTurnListener;
    //   120: invokespecial 545	com/vlingo/core/internal/dialogmanager/DialogTurn:<init>	(Ljava/util/LinkedList;Lcom/vlingo/core/internal/dialogmanager/DialogFieldID;Lcom/vlingo/core/internal/dialogmanager/DialogTurn$DialogTurnListener;)V
    //   123: putfield 340	com/vlingo/core/internal/dialogmanager/DialogFlow:pendingSafeReaderTurn	Lcom/vlingo/core/internal/dialogmanager/DialogTurn;
    //   126: new 8	com/vlingo/core/internal/dialogmanager/DialogFlow$1
    //   129: dup
    //   130: aload_0
    //   131: invokespecial 546	com/vlingo/core/internal/dialogmanager/DialogFlow$1:<init>	(Lcom/vlingo/core/internal/dialogmanager/DialogFlow;)V
    //   134: invokestatic 552	com/vlingo/core/internal/util/ActivityUtil:runOnMainThread	(Ljava/lang/Runnable;)V
    //   137: aload_2
    //   138: monitorexit
    //   139: return
    //
    // Exception table:
    //   from	to	target	type
    //   7	77	74	finally
    //   79	139	74	finally
  }

  public void initFlow(Context paramContext, DialogFlowListener paramDialogFlowListener, HashMap<String, String> paramHashMap, WidgetFactory paramWidgetFactory)
  {
    if (paramContext == null)
      throw new IllegalArgumentException("activityContext cannot be null");
    if (paramDialogFlowListener == null)
      throw new IllegalArgumentException("listener cannot be null");
    if ((this.activityContext != paramContext) || (this.dialogFlowListener != paramDialogFlowListener))
    {
      cancelDialog();
      this.activityContext = paramContext;
      this.dialogFlowListener = paramDialogFlowListener;
    }
    this.userProperties = paramHashMap;
    this.widgetFactory = paramWidgetFactory;
  }

  public void interruptTurn()
  {
    synchronized (this.taskQueueMutex)
    {
      this.dialogQueue.cancel();
      return;
    }
  }

  public boolean isIdle()
  {
    int i;
    synchronized (this.taskQueueMutex)
    {
      if ((this.isProcessingActions) || (this.dialogQueue.isRunningTask()) || (this.dialogQueue.hasQueuedTask()))
        i = 0;
      else
        i = 1;
    }
    return i;
  }

  public boolean isQueuedAudioTask()
  {
    return this.dialogQueue.isQueuedTask(PlayAudioTask.class);
  }

  @Deprecated
  public boolean isQueuedTtsTask()
  {
    return this.dialogQueue.isQueuedTask(PlayAudioTask.class);
  }

  public boolean isSilentMode()
  {
    return this.isSilentMode;
  }

  public void playMedia(int paramInt)
  {
    if (paramInt > 0)
    {
      PlayMediaTask localPlayMediaTask = new PlayMediaTask(this.mAudioPlaybackListener, paramInt);
      synchronized (this.taskQueueMutex)
      {
        this.dialogQueue.queueTask(localPlayMediaTask);
      }
    }
  }

  public void queuePauseableTask(PausableTask paramPausableTask)
  {
    paramPausableTask.setVlRecognitionListener(this.vlRecognitionListener);
    synchronized (this.taskQueueMutex)
    {
      this.dialogQueue.queueTask(paramPausableTask);
      return;
    }
  }

  public long readoutDelay()
  {
    return 0L;
  }

  public void registerTaskRegulator(DialogFlowTaskRegulator.EventType paramEventType, DialogFlowTaskRegulator paramDialogFlowTaskRegulator)
  {
    if (!this.taskRegulators.containsKey(paramEventType))
      this.taskRegulators.put(paramEventType, Collections.newSetFromMap(new ConcurrentHashMap()));
    ((Set)this.taskRegulators.get(paramEventType)).add(paramDialogFlowTaskRegulator);
  }

  public void releaseFlow(DialogFlowListener paramDialogFlowListener)
  {
    if (paramDialogFlowListener != this.dialogFlowListener);
    while (true)
    {
      return;
      cancelDialog();
      initQueue();
      this.activityContext = null;
      this.dialogFlowListener = new DefaultDialogFlowListener();
      this.userProperties = null;
      VLSdk.getInstance().getRecognizer().destroy();
    }
  }

  public void removeUserProperties(String paramString)
  {
    if (this.userProperties != null)
      this.userProperties.remove(paramString);
  }

  public void sendPendingEvents()
  {
    if ((this.currentTurn != null) && (!this.currentTurn.getPendingEvents().isEmpty()))
    {
      createDMTransactionTask();
      if (!this.isProcessingActions)
      {
        this.dialogQueue.queueTask(this.pendingDMTransactionTask);
        this.pendingDMTransactionTask = null;
      }
    }
  }

  public void setSilentMode(boolean paramBoolean)
  {
    this.isSilentMode = paramBoolean;
  }

  public void setUserProperties(HashMap<String, String> paramHashMap)
  {
    this.userProperties = paramHashMap;
  }

  public void startSafeReaderFlow()
  {
    if (this.currentTurn != null)
      this.currentTurn.cancel();
    if (this.pendingSafeReaderTurn != null)
    {
      PhraseSpotter.getInstance().stopPhraseSpotting();
      this.dialogFlowListener.showReceivedResults(null);
    }
    synchronized (this.taskQueueMutex)
    {
      this.currentTurn = this.pendingSafeReaderTurn;
      this.pendingSafeReaderTurn = null;
      this.pendingDMTransactionTask = null;
      this.isProcessingActions = true;
      this.currentTurn.processSafeReaderMessage(isSilentMode());
      this.alerts = null;
      return;
    }
  }

  public void startSafeReaderFlow(SMSMMSAlert paramSMSMMSAlert)
  {
    if (this.currentTurn != null)
      this.currentTurn.cancel();
    PhraseSpotter.getInstance().stopPhraseSpotting();
    LinkedList localLinkedList = new LinkedList();
    localLinkedList.add(paramSMSMMSAlert);
    this.pendingSafeReaderTurn = new DialogTurn(localLinkedList, VlingoAndroidCore.getFieldId(FieldIds.DEFAULT), this.dialogTurnListener);
    synchronized (this.taskQueueMutex)
    {
      this.currentTurn = this.pendingSafeReaderTurn;
      this.pendingSafeReaderTurn = null;
      this.pendingDMTransactionTask = null;
      this.isProcessingActions = true;
      this.currentTurn.processSafeReaderMessage(paramSMSMMSAlert, isSilentMode());
      return;
    }
  }

  public boolean startUserFlow(MicrophoneStream paramMicrophoneStream)
  {
    if (this.currentTurn != null)
      this.currentTurn.cancel();
    return listen(paramMicrophoneStream);
  }

  public boolean startUserFlow(String paramString, Object paramObject)
  {
    if (this.currentTurn != null)
      this.currentTurn.cancel();
    if (paramObject != null)
      restoreState(paramObject);
    preserveState(getFieldID());
    return sendTextRequest(paramString);
  }

  public void stealFlow(Context paramContext, DialogFlowListener paramDialogFlowListener, HashMap<String, String> paramHashMap, WidgetFactory paramWidgetFactory)
  {
    if (paramContext == null)
      throw new IllegalArgumentException("activityContext cannot be null");
    if (paramDialogFlowListener == null)
      throw new IllegalArgumentException("listener cannot be null");
    if ((this.activityContext != paramContext) || (this.dialogFlowListener != paramDialogFlowListener))
    {
      this.activityContext = paramContext;
      this.dialogFlowListener = paramDialogFlowListener;
    }
    this.userProperties = paramHashMap;
    this.widgetFactory = paramWidgetFactory;
  }

  public String toString()
  {
    if (this.dialogData == null);
    StringBuilder localStringBuilder;
    for (String str = ""; ; str = localStringBuilder.toString())
    {
      return str;
      localStringBuilder = new StringBuilder();
      Iterator localIterator1 = this.dialogData.entrySet().iterator();
      label34: 
      while (localIterator1.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator1.next();
        if (localStringBuilder.length() == 0)
          localStringBuilder.append("{");
        Object localObject;
        while (true)
        {
          DialogDataType localDialogDataType = (DialogDataType)localEntry.getKey();
          localObject = localEntry.getValue();
          localStringBuilder.append(localDialogDataType.toString() + "=");
          if (localObject == null)
            break label360;
          switch (3.$SwitchMap$com$vlingo$core$internal$dialogmanager$DialogDataType[localDialogDataType.ordinal()])
          {
          default:
            localStringBuilder.append(localObject.toString());
            break label34;
            localStringBuilder.append(",");
          case 3:
          case 4:
          case 5:
          case 6:
          }
        }
        Iterator localIterator5 = ((List)localObject).iterator();
        while (localIterator5.hasNext())
          localStringBuilder.append(((Alarm)localIterator5.next()).toString());
        Iterator localIterator4 = ((List)localObject).iterator();
        while (localIterator4.hasNext())
          localStringBuilder.append(((ScheduleEvent)localIterator4.next()).toString());
        Iterator localIterator3 = ((List)localObject).iterator();
        while (localIterator3.hasNext())
          localStringBuilder.append(((ContactMatch)localIterator3.next()).toString());
        Iterator localIterator2 = ((List)localObject).iterator();
        while (localIterator2.hasNext())
          localStringBuilder.append(((ScheduleTask)localIterator2.next()).toString());
        continue;
        label360: localStringBuilder.append("null");
      }
      if (localStringBuilder.length() == 0)
        continue;
      localStringBuilder.append("}");
    }
  }

  public void tts(String paramString)
  {
    if (!StringUtils.isNullOrWhiteSpace(paramString))
    {
      PlayTTSTask localPlayTTSTask = new PlayTTSTask(this.mAudioPlaybackListener, paramString);
      synchronized (this.taskQueueMutex)
      {
        this.dialogQueue.queueTask(localPlayTTSTask);
      }
    }
  }

  public void ttsAnyway(String paramString)
  {
    if (!StringUtils.isNullOrWhiteSpace(paramString))
    {
      PlayTTSTask localPlayTTSTask = new PlayTTSTask(this.mAudioPlaybackListener, paramString);
      localPlayTTSTask.setTTSAnyway(true);
      synchronized (this.taskQueueMutex)
      {
        this.dialogQueue.queueTask(localPlayTTSTask);
      }
    }
  }

  public void unregisterTaskFlowRegulator(DialogFlowTaskRegulator.EventType paramEventType, DialogFlowTaskRegulator paramDialogFlowTaskRegulator)
  {
    if ((this.taskRegulators.containsKey(paramEventType)) && (paramDialogFlowTaskRegulator != null))
      ((Set)this.taskRegulators.get(paramEventType)).remove(paramDialogFlowTaskRegulator);
  }

  public void userCancel()
  {
    this.dialogFlowListener.userCancel();
  }

  public static enum DialogFlowState
  {
    static
    {
      BUSY = new DialogFlowState("BUSY", 1);
      DialogFlowState[] arrayOfDialogFlowState = new DialogFlowState[2];
      arrayOfDialogFlowState[0] = IDLE;
      arrayOfDialogFlowState[1] = BUSY;
      $VALUES = arrayOfDialogFlowState;
    }
  }

  private class DialogTurnListenerImpl
    implements DialogTurn.DialogTurnListener
  {
    private DialogTurnListenerImpl()
    {
    }

    public void endpointReco()
    {
      DialogFlow.this.endpointReco();
    }

    public void execute(ActionInterface paramActionInterface)
    {
      if (paramActionInterface != null)
      {
        ActionTask localActionTask = new ActionTask(paramActionInterface);
        synchronized (DialogFlow.this.taskQueueMutex)
        {
          DialogFlow.this.dialogQueue.queueTask(localActionTask);
        }
      }
    }

    public void finishDialog()
    {
      DialogFlow.this.finishDialog();
    }

    public void finishTurn()
    {
      DialogFlow.this.finishTurn();
    }

    public Context getActivityContext()
    {
      if (DialogFlow.this.activityContext != null);
      for (Context localContext = DialogFlow.this.activityContext; ; localContext = ApplicationAdapter.getInstance().getApplicationContext())
        return localContext;
    }

    public Object getState(DialogDataType paramDialogDataType)
    {
      if (DialogFlow.this.dialogData != null);
      for (Object localObject = DialogFlow.this.dialogData.get(paramDialogDataType); ; localObject = null)
        return localObject;
    }

    public void interruptTurn()
    {
      DialogFlow.this.interruptTurn();
    }

    public void onAsyncActionStarted()
    {
      synchronized (DialogFlow.this.taskQueueMutex)
      {
        DialogFlow.access$802(DialogFlow.this, true);
        return;
      }
    }

    public void onDoneProcessingActions(DialogTurn paramDialogTurn)
    {
      DialogFlow.this.finishActionListProcessing();
    }

    public void playMedia(int paramInt)
    {
      DialogFlow.this.playMedia(paramInt);
    }

    public void sendEvent(DialogEvent paramDialogEvent)
    {
      synchronized (DialogFlow.this.taskQueueMutex)
      {
        DialogFlow.this.createDMTransactionTask();
        if (!DialogFlow.this.isProcessingActions)
        {
          DialogFlow.this.dialogQueue.queueTask(DialogFlow.this.pendingDMTransactionTask);
          DialogFlow.access$902(DialogFlow.this, null);
        }
        if (paramDialogEvent.isTerminalState())
          DialogFlow.this.resetState();
        return;
      }
    }

    public void sendTextRequest(String paramString)
    {
      DialogFlow.this.sendTextRequest(paramString);
    }

    public void showUserText(String paramString)
    {
      if (!StringUtils.isNullOrWhiteSpace(paramString))
        DialogFlow.this.dialogFlowListener.showUserText(paramString, null);
    }

    public void showUserText(String paramString, NBestData paramNBestData)
    {
      DialogFlow.this.dialogFlowListener.showUserText(paramString, paramNBestData);
    }

    public void showVlingoText(String paramString)
    {
      if (!StringUtils.isNullOrWhiteSpace(paramString))
        DialogFlow.this.dialogFlowListener.showVlingoText(paramString);
    }

    public void showVlingoTextAndTTS(String paramString1, String paramString2)
    {
      if (!StringUtils.isNullOrWhiteSpace(paramString1))
        showVlingoText(paramString1);
      if ((!StringUtils.isNullOrWhiteSpace(paramString2)) && (VlingoApplicationService.isAppInForeground()))
      {
        PlayTTSTask localPlayTTSTask = new PlayTTSTask(DialogFlow.this.mAudioPlaybackListener, paramString2, true);
        synchronized (DialogFlow.this.taskQueueMutex)
        {
          DialogFlow.this.dialogQueue.queueTask(localPlayTTSTask);
        }
      }
    }

    public <T> void showWidget(WidgetUtil.WidgetKey paramWidgetKey, WidgetDecorator paramWidgetDecorator, T paramT, WidgetActionListener paramWidgetActionListener)
    {
      if (DialogFlow.this.widgetFactory != null)
        DialogFlow.this.widgetFactory.showWidget(paramWidgetKey, paramWidgetDecorator, paramT, paramWidgetActionListener);
    }

    public void startReco()
    {
      synchronized (DialogFlow.this.taskQueueMutex)
      {
        DialogFlow.this.listen(null);
        return;
      }
    }

    public void storeState(DialogDataType paramDialogDataType, Object paramObject)
    {
      DialogFlow.this.dialogData.put(paramDialogDataType, paramObject);
    }

    public void tts(String paramString)
    {
      DialogFlow.this.tts(paramString);
    }

    public void ttsAnyway(String paramString)
    {
      DialogFlow.this.ttsAnyway(paramString);
    }

    public void userCancel()
    {
      DialogFlow.this.userCancel();
    }
  }

  private static class LapTimer extends PrecisionTimer
  {
    private LinkedList<PrecisionTimer.Interval> laps = new LinkedList();

    public long elapsed()
    {
      return elapsedInterval().span();
    }

    public PrecisionTimer.Interval elapsedInterval()
    {
      this.laps.add(super.elapsedInterval());
      return (PrecisionTimer.Interval)this.laps.getLast();
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      Iterator localIterator = this.laps.iterator();
      while (localIterator.hasNext())
        localStringBuilder.append(((PrecisionTimer.Interval)localIterator.next()).toString()).append("  ");
      return localStringBuilder.toString();
    }
  }

  private class TaskQueueListenerImpl
    implements TaskQueue.TaskQueueListener
  {
    private TaskQueueListenerImpl()
    {
    }

    private void handleTaskStartingForRegulators(Set<DialogFlowTaskRegulator> paramSet, DialogFlowTaskRegulator.EventType paramEventType, ResumeControl paramResumeControl)
    {
      MultipleResumeControl localMultipleResumeControl = new MultipleResumeControl(paramResumeControl, paramSet.size());
      Iterator localIterator = paramSet.iterator();
      while (localIterator.hasNext())
        ((DialogFlowTaskRegulator)localIterator.next()).onTaskWaitingToStart(paramEventType, localMultipleResumeControl);
    }

    public void onQueueCancelled()
    {
    }

    public void onQueueDone()
    {
      DialogFlow.this.handleIdle(false);
    }

    public void onTaskStarting(TaskQueue.Task paramTask)
    {
      DialogFlow.this.notifyDialogBusy();
      if ((paramTask instanceof DMServerTask))
      {
        Set localSet2 = DialogFlow.this.getTaskRegulators(DialogFlowTaskRegulator.EventType.RECOGNITION_START);
        if ((localSet2 != null) && (!localSet2.isEmpty()))
        {
          DMServerTask localDMServerTask = (DMServerTask)paramTask;
          if ((localDMServerTask.isPausable()) && (localDMServerTask.isPerformingReco()))
          {
            localDMServerTask.pause();
            handleTaskStartingForRegulators(localSet2, DialogFlowTaskRegulator.EventType.RECOGNITION_START, localDMServerTask);
          }
        }
      }
      while (true)
      {
        return;
        if ((paramTask instanceof PlayTTSTask))
        {
          Set localSet1 = DialogFlow.this.getTaskRegulators(DialogFlowTaskRegulator.EventType.SYSTEM_TEXT_TTS);
          if ((localSet1 == null) || (localSet1.isEmpty()))
            continue;
          PlayTTSTask localPlayTTSTask = (PlayTTSTask)paramTask;
          if ((!localPlayTTSTask.isPausable()) || (!localPlayTTSTask.isSystemTts()))
            continue;
          localPlayTTSTask.pause();
          handleTaskStartingForRegulators(localSet1, DialogFlowTaskRegulator.EventType.SYSTEM_TEXT_TTS, localPlayTTSTask);
          continue;
        }
        if (!(paramTask instanceof PlayMediaTask))
          continue;
      }
    }
  }

  private class VLRecognitionListenerImpl
    implements VLRecognitionListener
  {
    private VLRecognitionListenerImpl()
    {
    }

    public void onCancelled()
    {
      DialogFlow.this.dialogFlowListener.onRecoCancelled();
    }

    public void onError(VLRecognitionErrors paramVLRecognitionErrors, String paramString)
    {
      DialogFlow.this.dialogFlowListener.showError(paramVLRecognitionErrors, paramString);
    }

    public long onRecoToneStarting()
    {
      return DialogFlow.this.dialogFlowListener.onRecoToneStarting();
    }

    public void onRecognitionResults(VLRecognitionResult paramVLRecognitionResult)
    {
      List localList = paramVLRecognitionResult.getActions();
      if (localList == null)
        DialogFlow.this.dialogFlowListener.onResultsNoAction();
      while (true)
      {
        return;
        if ((StringUtils.isNullOrWhiteSpace(paramVLRecognitionResult.getResultString())) && (localList.size() == 1) && (((VLAction)localList.get(0)).getName().equals("SetTurnParams")))
        {
          DialogFlow.this.dialogFlowListener.onResultsNoAction();
          continue;
        }
        DialogFlow.this.dialogFlowListener.showReceivedResults(new EventLog(paramVLRecognitionResult));
        if ((localList == null) || (localList.size() <= 0))
          continue;
        synchronized (DialogFlow.this.taskQueueMutex)
        {
          if (DialogFlow.this.currentTurn == null)
          {
            DialogFlow.access$1802(DialogFlow.this, new DialogTurn(paramVLRecognitionResult, VlingoAndroidCore.getFieldId(FieldIds.DEFAULT), DialogFlow.this.dialogTurnListener));
            DialogFlow.access$902(DialogFlow.this, null);
            DialogFlow.access$802(DialogFlow.this, true);
            DialogFlow.this.currentTurn.processActions();
            continue;
          }
          DialogFlow.access$1802(DialogFlow.this, new DialogTurn(DialogFlow.this.currentTurn, paramVLRecognitionResult, DialogFlow.this.dialogTurnListener));
        }
      }
    }

    public void onRmsChanged(int paramInt)
    {
      DialogFlow.this.dialogFlowListener.showRMSChange(paramInt);
    }

    public void onStateChanged(VLRecognitionStates paramVLRecognitionStates)
    {
      DialogFlow.access$1702(DialogFlow.this, paramVLRecognitionStates);
      DialogFlow.this.dialogFlowListener.showRecoStateChange(paramVLRecognitionStates);
    }

    public void onWarning(VLRecognitionWarnings paramVLRecognitionWarnings, String paramString)
    {
      DialogFlow.this.dialogFlowListener.showWarning(paramVLRecognitionWarnings, paramString);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.DialogFlow
 * JD-Core Version:    0.6.0
 */