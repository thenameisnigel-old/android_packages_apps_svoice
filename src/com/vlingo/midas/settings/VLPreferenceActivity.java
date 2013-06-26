package com.vlingo.midas.settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class VLPreferenceActivity extends PreferenceActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    MidasSettings.updateCurrentLocale(getResources());
    super.onCreate(paramBundle);
    setVolumeControlStream(3);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.VLPreferenceActivity
 * JD-Core Version:    0.6.0
 */