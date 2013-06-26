package com.vlingo.midas.settings.debug;

import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import java.util.HashSet;
import java.util.Iterator;

public class PreferenceChangeListenerHolder extends HashSet<Preference.OnPreferenceChangeListener>
  implements Preference.OnPreferenceChangeListener
{
  public boolean onPreferenceChange(Preference paramPreference, Object paramObject)
  {
    boolean bool = true;
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
      bool &= ((Preference.OnPreferenceChangeListener)localIterator.next()).onPreferenceChange(paramPreference, paramObject);
    return bool;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.PreferenceChangeListenerHolder
 * JD-Core Version:    0.6.0
 */