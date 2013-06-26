package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.actions.ExecuteIntentAction;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;

public class ChinaNavHandler extends VVSActionHandler
{
  private static final String ACTION_NAVIGATE = "com.autonavi.xmgd.action.NAVIGATE";
  private static final String ACTION_SHOWMAP = "com.autonavi.xmgd.action.SHOWMAP";
  private static final String EXTRA_ADDRESS = "address";
  private static final String EXTRA_AREA = "area";
  private static final String EXTRA_KEYWORD = "keyword";
  private static final String EXTRA_TARGET = "target";
  private static final String NAVIGATION_TYPE = "NAVIGATE";
  private static final String PARAM_CITY = "City";
  private static final String PARAM_KEYWORD = "Keyword";
  private static final String PARAM_NAVTYPE = "Navtype";
  private static final String PARAM_QUERY = "Query";
  private static final String PARAM_STATE = "State";
  private static final String PARAM_STREET = "Street";
  private static final String PARAM_STREET_NUM = "StreetNumber";
  private DMAction launchActivityAction;

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    String str1 = "com.autonavi.xmgd.action.SHOWMAP";
    String str2 = null;
    String str3 = VLActionUtil.getParamString(paramVLAction, "City", false);
    String str4 = VLActionUtil.getParamString(paramVLAction, "State", false);
    String str5 = VLActionUtil.getParamString(paramVLAction, "StreetNumber", false);
    String str6 = VLActionUtil.getParamString(paramVLAction, "Street", false);
    String str7 = VLActionUtil.getParamString(paramVLAction, "Keyword", false);
    String str8 = VLActionUtil.getParamString(paramVLAction, "Navtype", false);
    String str9 = VLActionUtil.getParamString(paramVLAction, "Query", false);
    String str10 = null;
    String str11 = null;
    if ((str3 != null) || (str4 != null))
      str10 = new String(str3 + " " + str4).trim();
    if ((str5 != null) || (str6 != null))
      str11 = new String(str5 + " " + str6).trim();
    if ((str8 != null) && (str8.equals("NAVIGATE")))
      str1 = "com.autonavi.xmgd.action.NAVIGATE";
    if (str10 != null)
    {
      if (!StringUtils.isNullOrWhiteSpace(null))
        str2 = null + ";" + "area" + "," + str10;
    }
    else
    {
      if (str11 != null)
      {
        if (StringUtils.isNullOrWhiteSpace(str2))
          break label462;
        str2 = str2 + ";" + "address" + "," + str11;
      }
      label294: if (str7 != null)
      {
        if (StringUtils.isNullOrWhiteSpace(str2))
          break label487;
        str2 = str2 + ";" + "keyword" + "," + str7;
      }
      label344: if (str9 != null)
        if (StringUtils.isNullOrWhiteSpace(str2))
          break label512;
    }
    label512: for (str2 = str2 + ";" + "target" + "," + str9; ; str2 = "target," + str9)
    {
      UserLoggingEngine.getInstance().landingPageViewed("navigate");
      this.launchActivityAction = ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).name(str1).extra(str2);
      this.launchActivityAction.queue();
      return false;
      str2 = "area," + str10;
      break;
      label462: str2 = "address," + str11;
      break label294;
      label487: str2 = "keyword," + str7;
      break label344;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ChinaNavHandler
 * JD-Core Version:    0.6.0
 */