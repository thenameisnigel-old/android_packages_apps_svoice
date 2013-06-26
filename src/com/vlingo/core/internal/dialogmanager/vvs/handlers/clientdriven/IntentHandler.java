package com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven;

import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.actions.ExecuteIntentAction;
import com.vlingo.core.internal.dialogmanager.actions.LaunchActivityAction;
import com.vlingo.core.internal.dialogmanager.actions.OpenAppAction;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.OpenAppUtil;
import com.vlingo.core.internal.util.OpenAppUtil.AppInfo;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.internal.util.PackageUtil;
import com.vlingo.sdk.recognition.VLAction;
import java.util.List;

public class IntentHandler extends VVSActionHandler
{
  public static final String ERROR_RUN_CURRENT_VLINGO_APP = "run current vlingo app";
  private boolean isOldVvs;
  private DMAction launchActivityAction;
  private String spokenForm;

  private boolean tryLaunchApp(String paramString)
  {
    int i = 0;
    OpenAppUtil localOpenAppUtil = new OpenAppUtil();
    localOpenAppUtil.buildMatchingAppList(paramString);
    if (localOpenAppUtil.getAppInfoList().size() == 1)
    {
      launchAppAction((OpenAppUtil.AppInfo)localOpenAppUtil.getAppInfoList().get(0));
      i = 1;
    }
    return i;
  }

  public void actionFail(String paramString)
  {
    super.actionFail(paramString);
    if (!paramString.equals("run current vlingo app"))
      if ((!this.isOldVvs) && (!StringUtils.isNullOrWhiteSpace(this.spokenForm)))
        break label52;
    label52: ResourceIdProvider localResourceIdProvider;
    ResourceIdProvider.string localstring;
    Object[] arrayOfObject;
    for (String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_tts_NO_APPMATCH_DEMAND); ; str = localResourceIdProvider.getString(localstring, arrayOfObject))
    {
      unified().showSystemTurn(str);
      return;
      localResourceIdProvider = VlingoAndroidCore.getResourceProvider();
      localstring = ResourceIdProvider.string.core_car_tts_NO_SPOKEN_APPMATCH_DEMAND;
      arrayOfObject = new Object[1];
      arrayOfObject[0] = this.spokenForm;
    }
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    this.isOldVvs = false;
    String str1 = VLActionUtil.getParamString(paramVLAction, "ExecName", false);
    String str2 = VLActionUtil.getParamString(paramVLAction, "ExecPackage", false);
    this.spokenForm = VLActionUtil.getParamString(paramVLAction, "SpokenForm", false);
    String str8;
    if ((!StringUtils.isNullOrWhiteSpace(str1)) && (!StringUtils.isNullOrWhiteSpace(str2)))
    {
      str8 = VLActionUtil.getParamString(paramVLAction, "AppName", false);
      String str9 = VLActionUtil.getParamString(paramVLAction, "Extras", false);
      String str10 = VLActionUtil.getParamString(paramVLAction, "ExecAction", false);
      UserLoggingEngine.getInstance().landingPageViewed("launch " + str8);
      if (PackageUtil.isAppInstalled(str2, 1))
        this.launchActivityAction = ((LaunchActivityAction)getAction(DMActionType.LAUNCH_ACTIVITY, LaunchActivityAction.class)).enclosingPackage(str2).activity(str1).extra(str9).action(str10).app(str8);
    }
    while (true)
    {
      if (this.launchActivityAction != null)
      {
        this.launchActivityAction.queue();
        this.launchActivityAction = null;
        getListener().finishDialog();
      }
      String str3;
      while (true)
      {
        return false;
        if (StringUtils.isNullOrWhiteSpace(this.spokenForm))
          this.spokenForm = str8;
        if (tryLaunchApp(this.spokenForm))
          continue;
        actionFail("Not found");
        continue;
        str3 = VLActionUtil.getParamString(paramVLAction, "IntentName", true);
        String str4 = VLActionUtil.getParamString(paramVLAction, "IntentArgument", false);
        String str5 = VLActionUtil.getParamString(paramVLAction, "Extras", false);
        String str6 = VLActionUtil.getParamString(paramVLAction, "ClassName", false);
        String str7 = VLActionUtil.getParamString(paramVLAction, "Type", false);
        boolean bool1 = VLActionUtil.getParamBool(paramVLAction, "broadcast", false, false);
        boolean bool2 = VLActionUtil.getParamBool(paramVLAction, "service", false, false);
        this.launchActivityAction = ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).name(str3).argument(str4).extra(str5).className(str6).broadcast(bool1).service(bool2).type(str7);
        if ((((ExecuteIntentAction)this.launchActivityAction).isAvailable()) || (str3.equalsIgnoreCase("com.sec.android.app.music.musicservicecommand.pause")))
          break;
        if (StringUtils.isNullOrWhiteSpace(this.spokenForm))
        {
          this.spokenForm = str3;
          this.isOldVvs = true;
        }
        if (tryLaunchApp(this.spokenForm))
          continue;
        actionFail("Not found");
      }
      UserLoggingEngine.getInstance().landingPageViewed("execute " + str3);
    }
  }

  public void launchAppAction(OpenAppUtil.AppInfo paramAppInfo)
  {
    String str = "launch";
    if (paramAppInfo != null)
      str = str + " " + paramAppInfo;
    UserLoggingEngine.getInstance().landingPageViewed(str);
    ((OpenAppAction)getAction(DMActionType.OPEN_APP, OpenAppAction.class)).appInfo(paramAppInfo).queue();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.IntentHandler
 * JD-Core Version:    0.6.0
 */