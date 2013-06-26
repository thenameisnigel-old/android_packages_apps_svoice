package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.Random;

public class ShowSystemTurnHandler extends VVSActionHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    int i = VLActionUtil.getParamInt(paramVLAction, "responseCount", 1, false);
    int j = 1 + new Random().nextInt(i);
    if (i == 1);
    for (String str1 = ""; ; str1 = Integer.toString(j))
    {
      String str2 = VLActionUtil.getParamString(paramVLAction, "action.prompt" + str1, false);
      String str3 = VLActionUtil.getParamString(paramVLAction, "action.prompt.spoken" + str1, false);
      if (StringUtils.isNullOrWhiteSpace(str2))
      {
        str2 = VLActionUtil.getParamString(paramVLAction, "action.prompt", false);
        str3 = VLActionUtil.getParamString(paramVLAction, "action.prompt.spoken", false);
      }
      paramVVSActionHandlerListener.showVlingoTextAndTTS(str2, str3);
      return false;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.ShowSystemTurnHandler
 * JD-Core Version:    0.6.0
 */