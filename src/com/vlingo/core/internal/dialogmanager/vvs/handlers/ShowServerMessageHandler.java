package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;

public class ShowServerMessageHandler extends VVSActionHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    String str1 = VLActionUtil.getParamString(paramVLAction, "Message", false);
    if (StringUtils.isNullOrWhiteSpace(str1));
    while (true)
    {
      return false;
      String str2 = VLActionUtil.getParamString(paramVLAction, "Details", false);
      if (str2 == null)
        str2 = "";
      String str3 = VLActionUtil.getParamString(paramVLAction, "Type", false);
      if ((str3 != null) && (str3.equals("error")))
        UserLoggingEngine.getInstance().errorDisplayed("SVR2", str1 + ": " + str2);
      paramVVSActionHandlerListener.showVlingoTextAndTTS(str1, str1);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.ShowServerMessageHandler
 * JD-Core Version:    0.6.0
 */