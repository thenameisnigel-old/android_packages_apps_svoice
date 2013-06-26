package com.vlingo.core.internal.social.api;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import com.vlingo.core.internal.settings.Settings;

public abstract class SocialAPI
{
  public static final String SOCIAL_NETWORK_TYPE_STRING_ALL = "all";
  public static final String SOCIAL_NETWORK_TYPE_STRING_FACEBOOK = "facebook";
  public static final String SOCIAL_NETWORK_TYPE_STRING_QZONE = "qzone";
  public static final String SOCIAL_NETWORK_TYPE_STRING_TWITTER = "twitter";
  public static final String SOCIAL_NETWORK_TYPE_STRING_WEIBO = "weibo";
  public static final int TYPE_INTENT = 0;
  public static final int UPDATE_STATUS_DONE = 2;
  public static final int UPDATE_STATUS_ERR = 3;
  public static final int UPDATE_STATUS_NONE = 0;
  public static final int UPDATE_STATUS_WORKING = 1;
  private String acceptedTextTagString;
  private int btnId;
  private int charLimit;
  private boolean enabled;
  private boolean hasLoginDialog;
  private String intentString;
  private int loginDialogId;
  private int loginMessageId;
  private String loginSetting;
  private int logoutDialogId;
  private int logoutMessageId;
  private int offImageResourceId;
  private int onImageResourceId;
  private boolean supportsLogout;
  private int typeId;
  private int updateStatus = 0;
  private String urlId;
  private int wcisId;

  SocialAPI(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, boolean paramBoolean1, boolean paramBoolean2, String paramString4)
  {
    this.urlId = paramString2;
    this.loginSetting = paramString1;
    this.loginMessageId = paramInt1;
    this.loginDialogId = paramInt2;
    this.logoutMessageId = paramInt3;
    this.logoutDialogId = paramInt4;
    this.hasLoginDialog = paramBoolean1;
    this.wcisId = paramInt5;
    this.onImageResourceId = paramInt6;
    this.offImageResourceId = paramInt7;
    this.intentString = paramString3;
    this.typeId = paramInt8;
    this.btnId = paramInt9;
    this.charLimit = paramInt10;
    this.acceptedTextTagString = paramString4;
    this.enabled = false;
    this.supportsLogout = paramBoolean2;
  }

  public void authorizeCallback(int paramInt1, int paramInt2, Intent paramIntent)
  {
  }

  public int btnId()
  {
    return this.btnId;
  }

  public int charLimit()
  {
    return this.charLimit;
  }

  public abstract void clearCredentials();

  public abstract void dismissDialogs();

  public boolean followVlingo()
  {
    return false;
  }

  public String getAcceptedTextTagString()
  {
    return this.acceptedTextTagString;
  }

  public abstract Dialog getDialog();

  public String getLoginUrl()
  {
    return Settings.getString(this.urlId, null);
  }

  public int getUpdateStatus()
  {
    return this.updateStatus;
  }

  public boolean includesLoginDialog()
  {
    return this.hasLoginDialog;
  }

  public String intentType()
  {
    return this.intentString;
  }

  public boolean isEnabled()
  {
    return this.enabled;
  }

  public boolean isLoggedIn()
  {
    return Settings.getBoolean(this.loginSetting, false);
  }

  public boolean login(Activity paramActivity)
  {
    if (!this.hasLoginDialog)
      throw new RuntimeException("Calling wrong login method, call loginUserPW");
    return false;
  }

  public int loginDialogId()
  {
    return this.loginDialogId;
  }

  public int loginMessageId()
  {
    return this.loginMessageId;
  }

  public boolean loginUserPW(String paramString1, String paramString2)
  {
    if (this.hasLoginDialog)
      throw new RuntimeException("Calling wrong login method, call login");
    return false;
  }

  public int logoutDialogId()
  {
    return this.logoutDialogId;
  }

  public int logoutMessageId()
  {
    return this.logoutMessageId;
  }

  public int offImageResourceId()
  {
    return this.offImageResourceId;
  }

  public int onImageResourceId()
  {
    return this.onImageResourceId;
  }

  public abstract void refreshCredentials();

  public void setEnabled(boolean paramBoolean)
  {
    this.enabled = paramBoolean;
  }

  public void setUpdateStatus(int paramInt)
  {
    this.updateStatus = paramInt;
  }

  public abstract void showDialogs();

  public void shutdown()
  {
  }

  public boolean supportsLogout()
  {
    return this.supportsLogout;
  }

  public int typeId()
  {
    return this.typeId;
  }

  public abstract void updateDialogs();

  public abstract void updateStatus(String paramString);

  public int wcisId()
  {
    return this.wcisId;
  }

  public static abstract interface SocialCallback
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.social.api.SocialAPI
 * JD-Core Version:    0.6.0
 */