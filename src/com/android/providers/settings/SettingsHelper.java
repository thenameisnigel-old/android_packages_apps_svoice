package com.android.providers.settings;

import android.app.ActivityManagerNative;
import android.app.IActivityManager;
import android.app.backup.IBackupManager;
import android.app.backup.IBackupManager.Stub;
import android.content.ContentResolver;
import android.content.Context;
import android.content.IContentService;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.IPowerManager;
import android.os.IPowerManager.Stub;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import java.util.Locale;

public class SettingsHelper
{
  private static final String TAG = "SettingsHelper";
  private AudioManager mAudioManager;
  private IContentService mContentService;
  private Context mContext;
  private IPowerManager mPowerManager;
  private boolean mSilent;
  private boolean mVibrate;

  public SettingsHelper(Context paramContext)
  {
    this.mContext = paramContext;
    this.mAudioManager = ((AudioManager)paramContext.getSystemService("audio"));
    this.mContentService = ContentResolver.getContentService();
    this.mPowerManager = IPowerManager.Stub.asInterface(ServiceManager.getService("power"));
  }

  private void setAutoRestore(boolean paramBoolean)
  {
    try
    {
      IBackupManager localIBackupManager = IBackupManager.Stub.asInterface(ServiceManager.getService("backup"));
      if (localIBackupManager != null)
        localIBackupManager.setAutoRestore(paramBoolean);
      label20: return;
    }
    catch (RemoteException localRemoteException)
    {
      break label20;
    }
  }

  private void setBrightness(int paramInt)
  {
    try
    {
      IPowerManager localIPowerManager = IPowerManager.Stub.asInterface(ServiceManager.getService("power"));
      if (localIPowerManager != null)
        localIPowerManager.setBacklightBrightness(paramInt);
      label20: return;
    }
    catch (RemoteException localRemoteException)
    {
      break label20;
    }
  }

  private void setGpsLocation(String paramString)
  {
    if (("gps".equals(paramString)) || (paramString.startsWith("gps,")) || (paramString.endsWith(",gps")) || (paramString.contains(",gps,")));
    for (boolean bool = true; ; bool = false)
    {
      Settings.Secure.setLocationProviderEnabled(this.mContext.getContentResolver(), "gps", bool);
      return;
    }
  }

  private void setRingerMode()
  {
    int i = 1;
    if (this.mSilent)
    {
      AudioManager localAudioManager2 = this.mAudioManager;
      if (this.mVibrate);
      while (true)
      {
        localAudioManager2.setRingerMode(i);
        return;
        i = 0;
      }
    }
    this.mAudioManager.setRingerMode(2);
    AudioManager localAudioManager1 = this.mAudioManager;
    if (this.mVibrate);
    while (true)
    {
      localAudioManager1.setVibrateSetting(0, i);
      break;
      i = 0;
    }
  }

  private void setSoundEffects(boolean paramBoolean)
  {
    if (paramBoolean)
      this.mAudioManager.loadSoundEffects();
    while (true)
    {
      return;
      this.mAudioManager.unloadSoundEffects();
    }
  }

  void applyAudioSettings()
  {
    new AudioManager(this.mContext).reloadAudioSettings();
  }

  byte[] getLocaleData()
  {
    Locale localLocale = this.mContext.getResources().getConfiguration().locale;
    String str1 = localLocale.getLanguage();
    String str2 = localLocale.getCountry();
    if (!TextUtils.isEmpty(str2))
      str1 = str1 + "_" + str2;
    return str1.getBytes();
  }

  public boolean restoreValue(String paramString1, String paramString2)
  {
    boolean bool = false;
    if ("screen_brightness".equals(paramString1))
      setBrightness(Integer.parseInt(paramString2));
    while (true)
    {
      bool = true;
      while (true)
      {
        return bool;
        if ("sound_effects_enabled".equals(paramString1))
        {
          if (Integer.parseInt(paramString2) == 1)
            bool = true;
          setSoundEffects(bool);
          break;
        }
        if (!"location_providers_allowed".equals(paramString1))
          break label67;
        setGpsLocation(paramString2);
      }
      label67: if (!"backup_auto_restore".equals(paramString1))
        continue;
      if (Integer.parseInt(paramString2) == 1)
        bool = true;
      setAutoRestore(bool);
    }
  }

  void setLocaleData(byte[] paramArrayOfByte)
  {
    Configuration localConfiguration1 = this.mContext.getResources().getConfiguration();
    if (localConfiguration1.userSetLocale)
      return;
    String[] arrayOfString = this.mContext.getAssets().getLocales();
    String str1 = new String(paramArrayOfByte);
    String str2 = new String(paramArrayOfByte, 0, 2);
    String str3;
    label76: Locale localLocale;
    if (paramArrayOfByte.length > 4)
    {
      str3 = new String(paramArrayOfByte, 3, 2);
      localLocale = null;
    }
    label172: for (int i = 0; ; i++)
    {
      while (true)
      {
        if (i < arrayOfString.length)
        {
          if (!arrayOfString[i].equals(str1))
            break label172;
          localLocale = new Locale(str2, str3);
        }
        if (localLocale == null)
          break;
        try
        {
          IActivityManager localIActivityManager = ActivityManagerNative.getDefault();
          Configuration localConfiguration2 = localIActivityManager.getConfiguration();
          localConfiguration2.locale = localLocale;
          localConfiguration2.userSetLocale = true;
          localIActivityManager.updateConfiguration(localConfiguration2);
        }
        catch (RemoteException localRemoteException)
        {
        }
      }
      break;
      str3 = "";
      break label76;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.providers.settings.SettingsHelper
 * JD-Core Version:    0.6.0
 */