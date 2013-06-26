package com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven;

import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;

public class EngineWebSearchHandler extends WebSearchHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    setListener(paramVVSActionHandlerListener);
    String str1 = VLActionUtil.getParamString(paramVLAction, "Engine", false);
    String str2 = null;
    if ((StringUtils.isNullOrWhiteSpace(str1)) || ("default".equals(str1)))
      str2 = getWebSearchURL(VLActionUtil.getParamString(paramVLAction, "Search", false));
    while (true)
    {
      executeSearchIntentFromURL(str2);
      return false;
      if (VlingoAndroidCore.isChineseBuild())
        str2 = VLActionUtil.getParamString(paramVLAction, "URL-" + str1 + "-cn", false);
      if (!StringUtils.isNullOrWhiteSpace(str2))
        continue;
      str2 = VLActionUtil.getParamString(paramVLAction, "URL-" + str1, true);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.EngineWebSearchHandler
 * JD-Core Version:    0.6.0
 */