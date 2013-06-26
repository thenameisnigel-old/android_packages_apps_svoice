package com.vlingo.midas.settings.debug;

import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import java.util.HashSet;
import java.util.Iterator;

public class PreferenceClickListenerHolder extends HashSet<Preference.OnPreferenceClickListener>
  implements Preference.OnPreferenceClickListener
{
  public boolean onPreferenceClick(Preference paramPreference)
  {
    boolean bool = true;
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
      bool &= ((Preference.OnPreferenceClickListener)localIterator.next()).onPreferenceClick(paramPreference);
    return bool;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.PreferenceClickListenerHolder
 * JD-Core Version:    0.6.0
 */