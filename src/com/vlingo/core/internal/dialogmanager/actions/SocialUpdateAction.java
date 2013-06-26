package com.vlingo.core.internal.dialogmanager.actions;

import android.os.Bundle;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.social.api.FacebookAPI;
import com.vlingo.core.internal.social.api.FacebookAPI.FacebookAPICallback;
import com.vlingo.core.internal.social.api.QZoneAPI.QZoneCallBack;
import com.vlingo.core.internal.social.api.SocialAPI;
import com.vlingo.core.internal.social.api.SocialNetworkType;
import com.vlingo.core.internal.social.api.TwitterAPI.TwitterCallback;
import com.vlingo.core.internal.social.api.WeiboAPI.WeiboCallBack;
import com.vlingo.core.internal.util.SocialUtils;
import com.vlingo.core.internal.util.StringUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class SocialUpdateAction extends DMAction
  implements TwitterAPI.TwitterCallback, FacebookAPI.FacebookAPICallback, WeiboAPI.WeiboCallBack, QZoneAPI.QZoneCallBack
{
  private HashMap<Integer, SocialAPI> apis;
  private int apisUpdating = 0;
  private String errorMsg;
  private boolean hasFailed = false;
  private String status;
  private SocialNetworkType type;
  private Queue<SocialAPI> updateQueue = new LinkedList();

  private boolean isNetworkEnabled(SocialAPI paramSocialAPI)
  {
    return paramSocialAPI.isEnabled();
  }

  private void performAllUpdates()
  {
    while (this.updateQueue.size() > 0)
    {
      SocialAPI localSocialAPI = (SocialAPI)this.updateQueue.remove();
      localSocialAPI.setUpdateStatus(1);
      localSocialAPI.updateStatus(this.status);
    }
  }

  private void performUpdate()
  {
    Iterator localIterator = this.apis.values().iterator();
    while (localIterator.hasNext())
    {
      SocialAPI localSocialAPI = (SocialAPI)localIterator.next();
      if (!isNetworkEnabled(localSocialAPI))
        continue;
      this.updateQueue.add(localSocialAPI);
    }
    this.apisUpdating = this.updateQueue.size();
    performAllUpdates();
  }

  private void setNetworkEnabled(SocialAPI paramSocialAPI, boolean paramBoolean)
  {
    if ((paramBoolean) && (paramSocialAPI.isLoggedIn()))
      paramSocialAPI.setEnabled(true);
    while (true)
    {
      return;
      paramSocialAPI.setEnabled(false);
    }
  }

  protected void execute()
  {
    try
    {
      this.apis = SocialUtils.getAPIs(this);
      if (this.type != null)
      {
        if (!StringUtils.isNullOrWhiteSpace(this.status))
        {
          switch (1.$SwitchMap$com$vlingo$core$internal$social$api$SocialNetworkType[this.type.ordinal()])
          {
          default:
          case 1:
          case 2:
          case 3:
          case 4:
          case 5:
          }
          while (true)
          {
            performUpdate();
            break;
            setNetworkEnabled((SocialAPI)this.apis.get(Integer.valueOf(8)), true);
            continue;
            if (this.status.length() <= 140)
            {
              setNetworkEnabled((SocialAPI)this.apis.get(Integer.valueOf(4)), true);
              continue;
            }
            String str5 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_too_long);
            getListener().actionFail(str5);
            continue;
            if (!VlingoAndroidCore.isChineseBuild())
              continue;
            if (this.status.length() <= 140)
            {
              setNetworkEnabled((SocialAPI)this.apis.get(Integer.valueOf(16)), true);
              continue;
            }
            String str4 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_too_long);
            getListener().actionFail(str4);
            continue;
            if ((!VlingoAndroidCore.isChineseBuild()) || (!VlingoAndroidCore.isChineseBuild()))
              continue;
            if (this.status.length() <= 140)
            {
              setNetworkEnabled((SocialAPI)this.apis.get(Integer.valueOf(32)), true);
              continue;
            }
            String str3 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_too_long);
            getListener().actionFail(str3);
            continue;
            if (VlingoAndroidCore.isChineseBuild())
            {
              if (Settings.getBoolean("qzone_account", false))
                setNetworkEnabled((SocialAPI)this.apis.get(Integer.valueOf(16)), true);
              if (!Settings.getBoolean("weibo_account", false))
                continue;
              setNetworkEnabled((SocialAPI)this.apis.get(Integer.valueOf(32)), true);
              continue;
            }
            if (Settings.getBoolean("facebook_account", false))
              setNetworkEnabled((SocialAPI)this.apis.get(Integer.valueOf(8)), true);
            if (!Settings.getBoolean("twitter_account", false))
              continue;
            setNetworkEnabled((SocialAPI)this.apis.get(Integer.valueOf(4)), true);
          }
        }
        String str2 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_no_status);
        getListener().actionFail(str2);
      }
      else
      {
        String str1 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_no_network);
        getListener().actionFail(str1);
      }
      label482: return;
    }
    catch (Exception localException)
    {
      break label482;
    }
  }

  public void onFacebookAPILogin(FacebookAPI paramFacebookAPI, int paramInt, Bundle paramBundle)
  {
  }

  public void onFacebookAPIMethod(FacebookAPI paramFacebookAPI, int paramInt, String paramString, Bundle paramBundle)
  {
    this.apisUpdating = (-1 + this.apisUpdating);
    String str1 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_wcis_social_facebook);
    if (StringUtils.isNullOrWhiteSpace(this.errorMsg))
    {
      this.errorMsg = (str1 + ":  ");
      if (paramInt != 901)
        break label183;
      String str3 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_status_updated);
      this.errorMsg = (this.errorMsg + str3 + "\n");
      if (this.apisUpdating == 0)
      {
        if (!this.hasFailed)
          break label171;
        getListener().actionFail(this.errorMsg);
      }
    }
    while (true)
    {
      return;
      this.errorMsg = (this.errorMsg + str1 + ":  ");
      break;
      label171: getListener().actionSuccess();
      continue;
      label183: this.hasFailed = true;
      int i = paramBundle.getInt("error_id");
      String str2 = paramBundle.getString("error") + "\n";
      if (i == 4)
        str2 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_network_error);
      this.errorMsg += str2;
      if (this.apisUpdating != 0)
        continue;
      getListener().actionFail(this.errorMsg);
    }
  }

  public void onFollowVlingoComplete(int paramInt, String paramString)
  {
  }

  public void onLoginComplete(int paramInt, boolean paramBoolean, String paramString)
  {
  }

  public void onQZoneFail(int paramInt)
  {
    if (paramInt == -106)
      getListener().actionFail(VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_api_qzone_update_error_publish));
    while (true)
    {
      return;
      getListener().actionFail(VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_api_qzone_update_error));
    }
  }

  public void onQZoneSuccess(int paramInt)
  {
    getListener().actionSuccess();
  }

  public void onUpdateComplete(int paramInt, String paramString)
  {
    this.apisUpdating = (-1 + this.apisUpdating);
    String str1 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_wcis_social_twitter);
    if (StringUtils.isNullOrWhiteSpace(this.errorMsg))
    {
      this.errorMsg = (str1 + ":  ");
      if (paramInt != 0)
        break label177;
      String str2 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_status_updated);
      this.errorMsg = (this.errorMsg + str2 + "\n");
      if (this.apisUpdating == 0)
      {
        if (!this.hasFailed)
          break label165;
        getListener().actionFail(this.errorMsg);
      }
    }
    while (true)
    {
      return;
      this.errorMsg = (this.errorMsg + str1 + ":  ");
      break;
      label165: getListener().actionSuccess();
      continue;
      label177: this.hasFailed = true;
      this.errorMsg += paramString;
      if (this.apisUpdating != 0)
        continue;
      getListener().actionFail(this.errorMsg);
    }
  }

  public void onVerifyComplete(int paramInt, String paramString)
  {
  }

  public void onVlingoFriendshipExists(int paramInt, boolean paramBoolean, String paramString)
  {
  }

  public void onWeiboFail(int paramInt)
  {
    getListener().actionFail(VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_api_weibo_update_error));
  }

  public void onWeiboSuccess(int paramInt)
  {
    getListener().actionSuccess();
  }

  public SocialUpdateAction status(String paramString)
  {
    this.status = paramString;
    return this;
  }

  public SocialUpdateAction type(SocialNetworkType paramSocialNetworkType)
  {
    this.type = paramSocialNetworkType;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.SocialUpdateAction
 * JD-Core Version:    0.6.0
 */