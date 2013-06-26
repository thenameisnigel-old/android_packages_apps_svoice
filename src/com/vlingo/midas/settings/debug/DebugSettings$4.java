package com.vlingo.midas.settings.debug;

import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import com.vlingo.core.internal.audio.AudioPlayerProxy;
import com.vlingo.core.internal.audio.TTSRequest;

class DebugSettings$4
  implements Preference.OnPreferenceChangeListener
{
  public boolean onPreferenceChange(Preference paramPreference, Object paramObject)
  {
    String str = (String)paramObject;
    if ((str == null) || (str.isEmpty()));
    for (int i = 0; ; i = 1)
    {
      return i;
      AudioPlayerProxy.play(TTSRequest.getResult(str), null);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.DebugSettings.4
 * JD-Core Version:    0.6.0
 */