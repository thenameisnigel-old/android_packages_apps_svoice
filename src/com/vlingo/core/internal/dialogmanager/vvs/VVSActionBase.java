package com.vlingo.core.internal.dialogmanager.vvs;

import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.DMActionFactory;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.DialogTurn;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.ActionInterface;
import com.vlingo.core.internal.recognition.acceptedtext.AcceptedText;
import com.vlingo.core.internal.recognition.acceptedtext.AcceptedText.TextType;
import com.vlingo.core.internal.recognition.acceptedtext.BaseAcceptedText;
import com.vlingo.core.internal.recognition.acceptedtext.VlingoSRStatisticsManager;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.NBestData;
import com.vlingo.sdk.recognition.VLAction;

public class VVSActionBase
  implements DMAction.Listener, Cloneable
{
  private VVSActionHandlerListener listener;
  protected DialogTurn turn;

  protected static String getString(ResourceIdProvider.string paramstring, Object[] paramArrayOfObject)
  {
    return VlingoAndroidCore.getResourceProvider().getString(paramstring, paramArrayOfObject);
  }

  public void actionAbort()
  {
    reset();
  }

  public void actionFail(String paramString)
  {
    reset();
  }

  public void actionSuccess()
  {
    reset();
  }

  public VVSActionBase clone()
    throws CloneNotSupportedException
  {
    return (VVSActionBase)super.clone();
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    setListener(paramVVSActionHandlerListener);
    return false;
  }

  protected <T extends ActionInterface> T getAction(DMActionType paramDMActionType, Class<T> paramClass)
  {
    return getAction(paramDMActionType, paramClass, this);
  }

  protected <T extends ActionInterface> T getAction(DMActionType paramDMActionType, Class<T> paramClass, DMAction.Listener paramListener)
  {
    return DMActionFactory.getInstance(paramDMActionType, this.listener.getActivityContext(), paramListener, this.listener);
  }

  protected VVSActionHandlerListener getListener()
  {
    return this.listener;
  }

  protected NBestData getNBestData()
  {
    return this.turn.getNBestData();
  }

  protected void reset()
  {
    getListener().finishDialog();
  }

  protected void sendAcceptedText(AcceptedText paramAcceptedText)
  {
    if (this.turn != null)
    {
      paramAcceptedText.setGUttId(this.turn.getGUttId());
      paramAcceptedText.setDialogGuid(this.turn.getGUID());
      paramAcceptedText.setDialogTurn(this.turn.getTurnNumber());
      VlingoSRStatisticsManager.getInstance().sendAcceptedText(paramAcceptedText);
    }
  }

  protected void sendAcceptedText(String paramString, AcceptedText.TextType paramTextType)
  {
    if (this.turn != null)
      VlingoSRStatisticsManager.getInstance().sendAcceptedText(new BaseAcceptedText(this.turn.getGUID(), this.turn.getTurnNumber(), this.turn.getGUttId(), paramString, paramTextType));
  }

  protected void sendAcceptedText(String paramString1, String paramString2, String paramString3)
  {
    if (this.turn != null)
      VlingoSRStatisticsManager.getInstance().sendAcceptedText(new BaseAcceptedText(this.turn.getGUID(), this.turn.getTurnNumber(), this.turn.getGUttId(), paramString1, paramString2, paramString3));
  }

  public void setListener(VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    this.listener = paramVVSActionHandlerListener;
  }

  public void setTurn(DialogTurn paramDialogTurn)
  {
    this.turn = paramDialogTurn;
  }

  protected void showSystemTurn(ResourceIdProvider.string paramstring1, ResourceIdProvider.string paramstring2)
  {
    showSystemTurn(getString(paramstring1, new Object[0]), getString(paramstring2, new Object[0]), false, null);
  }

  protected void showSystemTurn(ResourceIdProvider.string paramstring1, ResourceIdProvider.string paramstring2, DialogFieldID paramDialogFieldID)
  {
    showSystemTurn(getString(paramstring1, new Object[0]), getString(paramstring2, new Object[0]), true, paramDialogFieldID);
  }

  protected void showSystemTurn(String paramString1, String paramString2)
  {
    showSystemTurn(paramString1, paramString2, false, null);
  }

  protected void showSystemTurn(String paramString1, String paramString2, DialogFieldID paramDialogFieldID)
  {
    showSystemTurn(paramString1, paramString2, true, paramDialogFieldID);
  }

  protected void showSystemTurn(String paramString1, String paramString2, boolean paramBoolean, DialogFieldID paramDialogFieldID)
  {
    getListener().showVlingoTextAndTTS(paramString1, paramString2);
    if ((paramDialogFieldID != null) && (!StringUtils.isNullOrWhiteSpace(paramDialogFieldID.getFieldId())))
      getListener().setFieldId(paramDialogFieldID);
    if ((!StringUtils.isNullOrWhiteSpace(paramString2)) && (paramBoolean))
      getListener().startReco();
  }

  protected void showUserTurn(String paramString)
  {
    if (!StringUtils.isNullOrWhiteSpace(paramString))
      this.listener.showUserText(paramString);
  }

  protected UnifiedPrompter unified()
  {
    return new UnifiedPrompter();
  }

  protected class UnifiedPrompter
  {
    protected UnifiedPrompter()
    {
    }

    public void showSystemTurn(ResourceIdProvider.string paramstring)
    {
      showSystemTurn(VVSActionBase.getString(paramstring, new Object[0]));
    }

    public void showSystemTurn(ResourceIdProvider.string paramstring, DialogFieldID paramDialogFieldID)
    {
      showSystemTurn(VVSActionBase.getString(paramstring, new Object[0]), paramDialogFieldID);
    }

    public void showSystemTurn(ResourceIdProvider.string paramstring, boolean paramBoolean, DialogFieldID paramDialogFieldID)
    {
      showSystemTurn(VVSActionBase.getString(paramstring, new Object[0]), paramBoolean, paramDialogFieldID);
    }

    public void showSystemTurn(String paramString)
    {
      showSystemTurn(paramString, false, (DialogFieldID)null);
    }

    public void showSystemTurn(String paramString, DialogFieldID paramDialogFieldID)
    {
      VVSActionBase.this.showSystemTurn(paramString, paramString, true, paramDialogFieldID);
    }

    public void showSystemTurn(String paramString1, String paramString2, DialogFieldID paramDialogFieldID)
    {
      VVSActionBase.this.showSystemTurn(paramString1, paramString2, true, paramDialogFieldID);
    }

    public void showSystemTurn(String paramString, boolean paramBoolean, DialogFieldID paramDialogFieldID)
    {
      VVSActionBase.this.showSystemTurn(paramString, paramString, paramBoolean, paramDialogFieldID);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase
 * JD-Core Version:    0.6.0
 */