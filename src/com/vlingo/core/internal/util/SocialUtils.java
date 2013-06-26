package com.vlingo.core.internal.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import com.vlingo.core.internal.CoreAdapterRegistrar;
import com.vlingo.core.internal.CoreAdapterRegistrar.AdapterList;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.social.api.FacebookAPI;
import com.vlingo.core.internal.social.api.FacebookAPI.FacebookAPICallback;
import com.vlingo.core.internal.social.api.QZoneAPI;
import com.vlingo.core.internal.social.api.QZoneAPI.QZoneCallBack;
import com.vlingo.core.internal.social.api.SocialAPI;
import com.vlingo.core.internal.social.api.SocialAPI.SocialCallback;
import com.vlingo.core.internal.social.api.SocialNetworkType;
import com.vlingo.core.internal.social.api.TwitterAPI;
import com.vlingo.core.internal.social.api.TwitterAPI.TwitterCallback;
import com.vlingo.core.internal.social.api.WeiboAPI;
import com.vlingo.core.internal.social.api.WeiboAPI.WeiboCallBack;
import java.util.ArrayList;
import java.util.HashMap;

public class SocialUtils
{
  public static final String CONTROLLER_LOGIN = "com.vlingo.core.internal.dialogmanager.controllers.socialupdatecontroller";
  public static final String EXTRA_CONTROLLER_LOGIN_RESULT = "extra_controller_login_result";

  public static HashMap<Integer, SocialAPI> getAPIs(SocialAPI.SocialCallback paramSocialCallback)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(Integer.valueOf(8), new FacebookAPI((FacebookAPI.FacebookAPICallback)paramSocialCallback));
    localHashMap.put(Integer.valueOf(4), new TwitterAPI((TwitterAPI.TwitterCallback)paramSocialCallback));
    localHashMap.put(Integer.valueOf(32), new WeiboAPI((WeiboAPI.WeiboCallBack)paramSocialCallback));
    localHashMap.put(Integer.valueOf(16), new QZoneAPI((QZoneAPI.QZoneCallBack)paramSocialCallback));
    return localHashMap;
  }

  public static ArrayList<String> getSocialNetworkNameList()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("all");
    if (VlingoAndroidCore.isChineseBuild())
    {
      localArrayList.add("weibo");
      localArrayList.add("qzone");
    }
    while (true)
    {
      return localArrayList;
      localArrayList.add("twitter");
      localArrayList.add("facebook");
    }
  }

  public static Intent loginIntent(boolean paramBoolean)
  {
    Intent localIntent = new Intent("com.vlingo.core.internal.dialogmanager.controllers.socialupdatecontroller");
    localIntent.putExtra("extra_controller_login_result", paramBoolean);
    return localIntent;
  }

  public static String loginToNetwork(SocialNetworkType paramSocialNetworkType)
  {
    HashMap localHashMap = getAPIs(null);
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    String str;
    switch (1.$SwitchMap$com$vlingo$core$internal$social$api$SocialNetworkType[paramSocialNetworkType.ordinal()])
    {
    default:
      if (VlingoAndroidCore.isChineseBuild())
      {
        if ((!Settings.getBoolean("weibo_account", false)) || (Settings.getBoolean("qzone_account", false)))
          break;
        str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_login_to_qzone_msg);
        SocialNetworkType localSocialNetworkType4 = SocialNetworkType.QZONE;
        ApplicationAdapter.getInstance().getVlingoApp().startSocialLogin(localContext, localSocialNetworkType4, (SocialAPI)localHashMap.get(Integer.valueOf(16)));
      }
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return str;
      Class localClass = CoreAdapterRegistrar.get(CoreAdapterRegistrar.AdapterList.FbStringSelector);
      FacebookStringUtil localFacebookStringUtil = null;
      if (localClass != null);
      try
      {
        localFacebookStringUtil = (FacebookStringUtil)localClass.newInstance();
        if (localFacebookStringUtil != null)
        {
          str = localFacebookStringUtil.getLoginToNetworkErrorMsg();
          ApplicationAdapter.getInstance().getVlingoApp().startSocialLogin(localContext, paramSocialNetworkType, (SocialAPI)localHashMap.get(Integer.valueOf(8)));
        }
      }
      catch (InstantiationException localInstantiationException)
      {
        while (true)
          localInstantiationException.printStackTrace();
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        while (true)
        {
          localIllegalAccessException.printStackTrace();
          continue;
          str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_login_to_facebook_msg);
        }
      }
      str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_login_to_twitter_msg);
      ApplicationAdapter.getInstance().getVlingoApp().startSocialLogin(localContext, paramSocialNetworkType, (SocialAPI)localHashMap.get(Integer.valueOf(4)));
      continue;
      str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_login_to_weibo_msg);
      ApplicationAdapter.getInstance().getVlingoApp().startSocialLogin(localContext, paramSocialNetworkType, (SocialAPI)localHashMap.get(Integer.valueOf(32)));
      continue;
      str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_login_to_qzone_msg);
      ApplicationAdapter.getInstance().getVlingoApp().startSocialLogin(localContext, paramSocialNetworkType, (SocialAPI)localHashMap.get(Integer.valueOf(16)));
      continue;
      if ((Settings.getBoolean("qzone_account", false)) && (!Settings.getBoolean("weibo_account", false)))
      {
        str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_login_to_weibo_msg);
        SocialNetworkType localSocialNetworkType3 = SocialNetworkType.WEIBO;
        ApplicationAdapter.getInstance().getVlingoApp().startSocialLogin(localContext, localSocialNetworkType3, (SocialAPI)localHashMap.get(Integer.valueOf(32)));
        continue;
      }
      str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_login_to_network_msg);
      ApplicationAdapter.getInstance().getVlingoApp().startSocialLogin(localContext, paramSocialNetworkType, (SocialAPI)localHashMap.get(Integer.valueOf(32)));
      continue;
      if ((Settings.getBoolean("facebook_account", false)) && (!Settings.getBoolean("twitter_account", false)))
      {
        str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_login_to_twitter_msg);
        SocialNetworkType localSocialNetworkType2 = SocialNetworkType.TWITTER;
        ApplicationAdapter.getInstance().getVlingoApp().startSocialLogin(localContext, localSocialNetworkType2, (SocialAPI)localHashMap.get(Integer.valueOf(4)));
        continue;
      }
      if ((Settings.getBoolean("twitter_account", false)) && (!Settings.getBoolean("facebook_account", false)))
      {
        str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_login_to_facebook_msg);
        SocialNetworkType localSocialNetworkType1 = SocialNetworkType.FACEBOOK;
        ApplicationAdapter.getInstance().getVlingoApp().startSocialLogin(localContext, localSocialNetworkType1, (SocialAPI)localHashMap.get(Integer.valueOf(8)));
        continue;
      }
      str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_login_to_network_msg);
      ApplicationAdapter.getInstance().getVlingoApp().startSocialLogin(localContext, paramSocialNetworkType, (SocialAPI)localHashMap.get(Integer.valueOf(8)));
    }
  }

  public static void setNetworkPicture(int paramInt, Bitmap paramBitmap)
  {
    switch (paramInt)
    {
    default:
    case 8:
    case 4:
    case 32:
    case 16:
    }
    while (true)
    {
      return;
      Settings.setImage("facebook_picture", paramBitmap);
      continue;
      Settings.setImage("twitter_picture", paramBitmap);
      continue;
      Settings.setImage("weibo_picture", paramBitmap);
      continue;
      Settings.setImage("qzone_picture", paramBitmap);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.SocialUtils
 * JD-Core Version:    0.6.0
 */