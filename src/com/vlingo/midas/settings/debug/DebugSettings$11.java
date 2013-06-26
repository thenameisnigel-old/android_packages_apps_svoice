package com.vlingo.midas.settings.debug;

import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import com.vlingo.midas.settings.MidasSettings;

class DebugSettings$11
  implements Preference.OnPreferenceChangeListener
{
  public boolean onPreferenceChange(Preference paramPreference, Object paramObject)
  {
    String str = (String)paramObject;
    if (!str.trim().isEmpty())
      MidasSettings.setInt("lmtt.update.version" + this.val$suffix, Integer.parseInt(str));
    return true;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.DebugSettings.11
 * JD-Core Version:    0.6.0
 */