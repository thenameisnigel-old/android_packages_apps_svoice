package com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven;

import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.SettingChangeInterface;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.sdk.recognition.VLAction;

public class SettingChangeHandler extends VVSActionHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    String str1 = VLActionUtil.getParamString(paramVLAction, "name", true);
    String str2 = VLActionUtil.getParamString(paramVLAction, "state", true);
    String str3 = VLActionUtil.getParamString(paramVLAction, "confirmOn", false);
    String str4 = VLActionUtil.getParamString(paramVLAction, "confirmOff", false);
    String str5 = VLActionUtil.getParamString(paramVLAction, "confirmOnTTS", false);
    String str6 = VLActionUtil.getParamString(paramVLAction, "confirmOffTTS", false);
    String str7 = VLActionUtil.getParamString(paramVLAction, "alreadySet", false);
    UserLoggingEngine.getInstance().landingPageViewed("settings");
    ((SettingChangeInterface)getAction(DMActionType.SETTING_CHANGE, SettingChangeInterface.class)).name(str1).state(str2).confirmOn(str3).confirmOff(str4).confirmOnTTS(str5).confirmOffTTS(str6).alreadySet(str7).VVSlistener(paramVVSActionHandlerListener).queue();
    return false;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.SettingChangeHandler
 * JD-Core Version:    0.6.0
 */