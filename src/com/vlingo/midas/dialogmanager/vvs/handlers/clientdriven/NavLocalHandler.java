package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;

public class NavLocalHandler extends VVSActionHandler
{
  private static final String PARAM_LOCALE = "Locale";
  private static final String PARAM_LOCATION = "Location";
  private static final String VALUE_KOREAN = "Korean";
  private static final String VALUE_OFFICE = "office";
  private static final String VALUE_RECENT = "recentdestination";

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    String str1 = VLActionUtil.getParamString(paramVLAction, "Location", true);
    String str2 = VLActionUtil.getParamString(paramVLAction, "Locale", false);
    if ((StringUtils.isNullOrWhiteSpace(str2)) || (!str2.equals("Korean")) || (str1.equals("office")));
    while (true)
    {
      return false;
      if (!str1.equals("recentdestination"))
        continue;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.NavLocalHandler
 * JD-Core Version:    0.6.0
 */