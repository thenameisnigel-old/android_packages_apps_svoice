package com.vlingo.core.internal.social.api;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.drawable;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.util.ApplicationAdapter;

public enum SocialNetworkType
{
  private String socialNetworkType;

  static
  {
    FACEBOOK = new SocialNetworkType("FACEBOOK", 2, "facebook");
    WEIBO = new SocialNetworkType("WEIBO", 3, "weibo");
    QZONE = new SocialNetworkType("QZONE", 4, "qzone");
    SocialNetworkType[] arrayOfSocialNetworkType = new SocialNetworkType[5];
    arrayOfSocialNetworkType[0] = ALL;
    arrayOfSocialNetworkType[1] = TWITTER;
    arrayOfSocialNetworkType[2] = FACEBOOK;
    arrayOfSocialNetworkType[3] = WEIBO;
    arrayOfSocialNetworkType[4] = QZONE;
    $VALUES = arrayOfSocialNetworkType;
  }

  private SocialNetworkType(String paramString)
  {
    this.socialNetworkType = paramString;
  }

  public static Drawable getSocialNetworkLogo(String paramString)
  {
    Drawable localDrawable;
    if ("twitter".equalsIgnoreCase(paramString))
      localDrawable = VlingoAndroidCore.getResourceProvider().getDrawable(ResourceIdProvider.drawable.core_twitter_icon);
    while (true)
    {
      return localDrawable;
      if ("facebook".equalsIgnoreCase(paramString))
      {
        localDrawable = VlingoAndroidCore.getResourceProvider().getDrawable(ResourceIdProvider.drawable.core_facebook_icon);
        continue;
      }
      if ("weibo".equalsIgnoreCase(paramString))
      {
        localDrawable = VlingoAndroidCore.getResourceProvider().getDrawable(ResourceIdProvider.drawable.core_weibo_icon);
        continue;
      }
      if ("qzone".equalsIgnoreCase(paramString))
      {
        localDrawable = VlingoAndroidCore.getResourceProvider().getDrawable(ResourceIdProvider.drawable.core_qzone_icon);
        continue;
      }
      localDrawable = VlingoAndroidCore.getResourceProvider().getDrawable(ResourceIdProvider.drawable.core_all_logo);
    }
  }

  public static SocialNetworkType getSocialNetworkType(String paramString)
  {
    SocialNetworkType localSocialNetworkType;
    if ("twitter".equalsIgnoreCase(paramString))
      localSocialNetworkType = TWITTER;
    while (true)
    {
      return localSocialNetworkType;
      if ("facebook".equalsIgnoreCase(paramString))
      {
        localSocialNetworkType = FACEBOOK;
        continue;
      }
      if ("weibo".equalsIgnoreCase(paramString))
      {
        localSocialNetworkType = WEIBO;
        continue;
      }
      if ("qzone".equalsIgnoreCase(paramString))
      {
        localSocialNetworkType = QZONE;
        continue;
      }
      localSocialNetworkType = ALL;
    }
  }

  public static String localizedSocialNetworkTypeString(String paramString)
  {
    String str;
    if ("twitter".equalsIgnoreCase(paramString))
    {
      TwitterAPI localTwitterAPI = new TwitterAPI(null);
      str = ApplicationAdapter.getInstance().getApplicationContext().getString(localTwitterAPI.wcisId());
    }
    while (true)
    {
      return str;
      if ("facebook".equalsIgnoreCase(paramString))
      {
        FacebookAPI localFacebookAPI = new FacebookAPI(null);
        str = ApplicationAdapter.getInstance().getApplicationContext().getString(localFacebookAPI.wcisId());
        continue;
      }
      if ("weibo".equalsIgnoreCase(paramString))
      {
        WeiboAPI localWeiboAPI = new WeiboAPI(null);
        str = ApplicationAdapter.getInstance().getApplicationContext().getString(localWeiboAPI.wcisId());
        continue;
      }
      if ("qzone".equalsIgnoreCase(paramString))
      {
        QZoneAPI localQZoneAPI = new QZoneAPI(null);
        str = ApplicationAdapter.getInstance().getApplicationContext().getString(localQZoneAPI.wcisId());
        continue;
      }
      str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_social_prompt_ex1);
    }
  }

  public static SocialNetworkType of(String paramString)
  {
    SocialNetworkType[] arrayOfSocialNetworkType = values();
    int i = arrayOfSocialNetworkType.length;
    int j = 0;
    SocialNetworkType localSocialNetworkType;
    if (j < i)
    {
      localSocialNetworkType = arrayOfSocialNetworkType[j];
      if (!localSocialNetworkType.toString().equals(paramString));
    }
    while (true)
    {
      return localSocialNetworkType;
      j++;
      break;
      localSocialNetworkType = ALL;
    }
  }

  public String toString()
  {
    return this.socialNetworkType;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.social.api.SocialNetworkType
 * JD-Core Version:    0.6.0
 */