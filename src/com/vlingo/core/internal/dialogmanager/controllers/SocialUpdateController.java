package com.vlingo.core.internal.dialogmanager.controllers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.StateController;
import com.vlingo.core.internal.dialogmanager.StateController.Rule;
import com.vlingo.core.internal.dialogmanager.actions.SocialUpdateAction;
import com.vlingo.core.internal.dialogmanager.types.SocialType;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.recognition.acceptedtext.SocialAcceptedText;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.social.api.SocialNetworkType;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.OrdinalUtil;
import com.vlingo.core.internal.util.SocialUtils;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.ArrayList;
import java.util.Map;

public class SocialUpdateController extends StateController
  implements WidgetActionListener
{
  private BroadcastReceiver loginEventComplete = new BroadcastReceiver()
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.controllers.socialupdatecontroller"));
      try
      {
        if (paramIntent.hasExtra("extra_controller_login_result"))
        {
          if (!paramIntent.getBooleanExtra("extra_controller_login_result", false))
            break label68;
          SocialUpdateController.this.doSocialUpdateAction();
        }
        while (true)
        {
          return;
          label68: SocialUpdateController.this.reset();
        }
      }
      finally
      {
        ApplicationAdapter.getInstance().getApplicationContext().unregisterReceiver(SocialUpdateController.this.loginEventComplete);
        SocialUpdateController.this.getListener().asyncHandlerDone();
      }
      throw localObject;
    }
  };
  private ArrayList<String> socialNetworks = null;
  private String status;
  Thread thread;
  private SocialNetworkType updateType;

  private boolean doSocialUpdateAction()
  {
    int i = 0;
    if (this.updateType != null)
      getListener().storeState(DialogDataType.SOCIAL_UPDATE, this.updateType.toString());
    if (isLoggedIn(this.updateType))
      if (StringUtils.isNullOrWhiteSpace(this.status))
        promptForStatus();
    while (true)
    {
      return i;
      promptForConfirm();
      continue;
      notLoggedIn();
      i = 1;
    }
  }

  private boolean isLoggedIn(SocialNetworkType paramSocialNetworkType)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (paramSocialNetworkType != null);
    switch (2.$SwitchMap$com$vlingo$core$internal$social$api$SocialNetworkType[paramSocialNetworkType.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
      while (true)
      {
        return bool2;
        bool2 = Settings.getBoolean("facebook_account", false);
        continue;
        bool2 = Settings.getBoolean("twitter_account", false);
        continue;
        if (!VlingoAndroidCore.isChineseBuild())
          continue;
        bool2 = Settings.getBoolean("weibo_account", false);
        continue;
        if (!VlingoAndroidCore.isChineseBuild())
          continue;
        bool2 = Settings.getBoolean("qzone_account", false);
      }
    case 5:
    }
    if (VlingoAndroidCore.isChineseBuild())
    {
      if ((Settings.getBoolean("qzone_account", false)) && (Settings.getBoolean("weibo_account", false)));
      while (true)
      {
        bool2 = bool1;
        break;
        bool1 = false;
      }
    }
    if ((Settings.getBoolean("facebook_account", false)) && (Settings.getBoolean("twitter_account", false)));
    while (true)
    {
      bool2 = bool1;
      break;
      bool1 = false;
    }
  }

  private void notLoggedIn()
  {
    String str = SocialUtils.loginToNetwork(this.updateType);
    ApplicationAdapter.getInstance().getApplicationContext().registerReceiver(this.loginEventComplete, new IntentFilter("com.vlingo.core.internal.dialogmanager.controllers.socialupdatecontroller"));
    getListener().setFieldId(VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_SOCIAL_STATUS));
    UserLoggingEngine.getInstance().landingPageViewed("car-social-home");
    unified().showSystemTurn(str);
  }

  private void promptForConfirm()
  {
    SocialType localSocialType = new SocialType(this.updateType, this.status);
    unified().showSystemTurn(ResourceIdProvider.string.core_car_social_final_prompt, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_SOCIAL_STATUS));
    getListener().showWidget(WidgetUtil.WidgetKey.ComposeSocialStatus, null, localSocialType, this);
    UserLoggingEngine.getInstance().landingPageViewed("car-social-home");
  }

  private void promptForSocialType()
  {
    this.socialNetworks = SocialUtils.getSocialNetworkNameList();
    unified().showSystemTurn(ResourceIdProvider.string.core_car_social_service_prompt, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_SOCIAL_CHOICE));
    getListener().showWidget(WidgetUtil.WidgetKey.SocialNetworkChoice, null, this.socialNetworks, this);
    UserLoggingEngine.getInstance().landingPageViewed("car-social-select");
  }

  private void promptForStatus()
  {
    unified().showSystemTurn(ResourceIdProvider.string.core_car_social_status_prompt, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_SOCIAL_STATUS));
    UserLoggingEngine.getInstance().landingPageViewed("car-social-home");
  }

  private void update(boolean paramBoolean)
  {
    ((SocialUpdateAction)getAction(DMActionType.SOCIAL_UPDATE, SocialUpdateAction.class)).type(this.updateType).status(this.status).queue();
    if (this.updateType != null)
      sendAcceptedText(new SocialAcceptedText(this.updateType.name(), null, this.status));
  }

  public void actionFail(String paramString)
  {
    try
    {
      String str1 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_network_error);
      if (paramString == null)
        paramString = str1;
      String str2 = paramString;
      getListener().showVlingoTextAndTTS(paramString, str2);
      super.actionFail(paramString);
      return;
    }
    finally
    {
      getListener().asyncHandlerDone();
    }
    throw localObject;
  }

  public void actionSuccess()
  {
    try
    {
      unified().showSystemTurn(ResourceIdProvider.string.core_social_status_updated);
      super.actionSuccess();
      return;
    }
    finally
    {
      getListener().asyncHandlerDone();
    }
    throw localObject;
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    boolean bool = false;
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    this.action = paramVLAction;
    if (paramVLAction.getName().equals("LPAction"))
    {
      String str6 = VLActionUtil.getParamString(paramVLAction, "Action", true);
      if ((str6 != null) && ("save".equalsIgnoreCase(str6)))
      {
        if (!StringUtils.isNullOrWhiteSpace(this.status))
          break label71;
        promptForStatus();
      }
    }
    while (true)
    {
      return bool;
      label71: update(true);
      bool = true;
      continue;
      UserLoggingEngine.getInstance().landingPageViewed("social");
      String str1 = VLActionUtil.getParamString(paramVLAction, "Status", false);
      if (!StringUtils.isNullOrWhiteSpace(str1))
        this.status = str1;
      String str2 = VLActionUtil.getParamString(paramVLAction, "Value", false);
      if (!StringUtils.isNullOrWhiteSpace(str2))
        this.status = (this.status + " " + str2);
      Object localObject = VLActionUtil.getParamString(paramVLAction, "Type", false);
      String str3 = VLActionUtil.getParamString(paramVLAction, "Which", false);
      if ((StringUtils.isNullOrWhiteSpace((String)localObject)) && (StringUtils.isNullOrWhiteSpace(str3)))
      {
        String str5 = (String)getListener().getState(DialogDataType.SOCIAL_UPDATE);
        if (str5 != null)
        {
          localObject = str5;
          getListener().storeState(DialogDataType.SOCIAL_UPDATE, null);
        }
      }
      if (!StringUtils.isNullOrWhiteSpace((String)localObject))
        this.updateType = SocialNetworkType.getSocialNetworkType((String)localObject);
      if (!StringUtils.isNullOrWhiteSpace(str3))
      {
        if (!"all".equalsIgnoreCase(str3))
          break label331;
        this.updateType = SocialNetworkType.getSocialNetworkType(str3);
      }
      while (true)
      {
        if ((!VlingoAndroidCore.isChineseBuild()) || ((this.updateType != SocialNetworkType.FACEBOOK) && (this.updateType != SocialNetworkType.TWITTER)))
          break label361;
        unified().showSystemTurn(VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_no_network));
        break;
        label331: String str4 = (String)OrdinalUtil.getElementFromList(SocialUtils.getSocialNetworkNameList(), str3);
        if (str4 == null)
          continue;
        this.updateType = SocialNetworkType.getSocialNetworkType(str4);
      }
      label361: if ((!VlingoAndroidCore.isChineseBuild()) && ((this.updateType == SocialNetworkType.QZONE) || (this.updateType == SocialNetworkType.WEIBO)))
      {
        unified().showSystemTurn(VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_no_network));
        continue;
      }
      if ((this.updateType == null) && (paramVLAction.getName().equalsIgnoreCase("UpdatePage")))
        unified().showSystemTurn(VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_no_network));
      if (this.updateType == null)
      {
        promptForSocialType();
        continue;
      }
      bool = doSocialUpdateAction();
    }
  }

  public Map<String, String> getRuleMappings()
  {
    return null;
  }

  public Map<String, StateController.Rule> getRules()
  {
    return null;
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    if (paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Choice"))
      if (paramIntent.hasExtra("choice"))
      {
        getListener().interruptTurn();
        int i = paramIntent.getExtras().getInt("choice");
        this.updateType = SocialNetworkType.getSocialNetworkType((String)this.socialNetworks.get(i));
        if (this.updateType != null)
          getListener().storeState(DialogDataType.SOCIAL_UPDATE, this.updateType.toString());
        if (!isLoggedIn(this.updateType))
          break label120;
        if (!StringUtils.isNullOrWhiteSpace(this.status))
          break label113;
        promptForStatus();
      }
    while (true)
    {
      return;
      label113: promptForConfirm();
      continue;
      label120: notLoggedIn();
      continue;
      if (paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Default"))
      {
        getListener().interruptTurn();
        if (paramIntent.hasExtra("message"))
          this.status = paramIntent.getExtras().getString("message");
        if (paramIntent.hasExtra("social choice"))
          this.updateType = SocialNetworkType.getSocialNetworkType(paramIntent.getExtras().getString("social choice"));
        update(true);
        continue;
      }
      if (paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Cancel"))
      {
        getListener().interruptTurn();
        unified().showSystemTurn(getString(ResourceIdProvider.string.core_car_tts_TASK_CANCELLED, new Object[0]));
        reset();
        continue;
      }
      if (paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.BodyChange"))
      {
        getListener().finishTurn();
        if (paramIntent.hasExtra("message"))
        {
          this.status = paramIntent.getExtras().getString("message");
          continue;
        }
        this.status = null;
        continue;
      }
      if (!paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.SocialChange"))
        continue;
      getListener().finishTurn();
      if (!paramIntent.hasExtra("social choice"))
        continue;
      this.updateType = SocialNetworkType.getSocialNetworkType(paramIntent.getExtras().getString("social choice"));
      if (this.updateType == null)
        continue;
      getListener().storeState(DialogDataType.SOCIAL_UPDATE, this.updateType.toString());
    }
  }

  public boolean handleLPAction(VLAction paramVLAction)
  {
    return false;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.controllers.SocialUpdateController
 * JD-Core Version:    0.6.0
 */