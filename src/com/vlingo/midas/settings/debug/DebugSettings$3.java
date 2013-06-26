package com.vlingo.midas.settings.debug;

import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.widget.Toast;

class DebugSettings$3
  implements Preference.OnPreferenceChangeListener
{
  public boolean onPreferenceChange(Preference paramPreference, Object paramObject)
  {
    try
    {
      int i = Integer.parseInt((String)paramObject);
      if (i > 0);
      return false;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
        Toast.makeText(this.this$0, "NumberFormatException", 0).show();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.DebugSettings.3
 * JD-Core Version:    0.6.0
 */