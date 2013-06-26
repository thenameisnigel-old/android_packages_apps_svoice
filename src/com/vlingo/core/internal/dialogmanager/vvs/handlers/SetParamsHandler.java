package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;

public class SetParamsHandler extends VVSActionHandler
{
  private void setAutoConfirmTimeout(String paramString)
  {
    if (!StringUtils.isNullOrWhiteSpace(paramString));
    try
    {
      Settings.setInt("DIALOG_MANAGER_AUTO_CONFIRM_TIMEOUT", Integer.parseInt(paramString));
      label16: return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      break label16;
    }
  }

  private void setEndpoint(String paramString)
  {
    if (!StringUtils.isNullOrWhiteSpace(paramString));
    try
    {
      Settings.setInt("DIALOG_MANAGER_ENDPOINT_THRESHOLD", Integer.parseInt(paramString));
      label16: return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      break label16;
    }
  }

  private void setWorkingMessageUpdate(String paramString)
  {
    if (!StringUtils.isNullOrWhiteSpace(paramString));
    try
    {
      Settings.setInt("DIALOG_MANAGER_WORKING_MESSAGE_INTERVAL", Integer.parseInt(paramString));
      label16: return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      break label16;
    }
  }

  private void setWorkingMessages(String paramString)
  {
    if (!StringUtils.isNullOrWhiteSpace(paramString))
      Settings.setString("DIALOG_MANAGER_WORKING_MESSAGES", paramString);
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    setEndpoint(paramVLAction.getParamValue("endpoint.threshold.msec"));
    setWorkingMessages(paramVLAction.getParamValue("working.msgs"));
    setWorkingMessageUpdate(paramVLAction.getParamValue("working.rotate.msec"));
    setAutoConfirmTimeout(paramVLAction.getParamValue("autoconfirm.timeout.msec"));
    return false;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.SetParamsHandler
 * JD-Core Version:    0.6.0
 */