package com.vlingo.midas.settings.debug;

import android.preference.Preference;
import java.util.HashSet;
import java.util.Iterator;

public class PreferenceUpdateListenerHolder extends HashSet<PreferenceUpdateListener>
  implements PreferenceUpdateListener, PreferenceUpdateAction
{
  private Preference preference;

  public PreferenceUpdateListenerHolder()
  {
  }

  public PreferenceUpdateListenerHolder(PreferenceUpdateListenerHolder paramPreferenceUpdateListenerHolder)
  {
    super(paramPreferenceUpdateListenerHolder);
  }

  public Preference getPreference()
  {
    return this.preference;
  }

  public void onPreferenceUpdated()
  {
    onPreferenceUpdated(getPreference());
  }

  public void onPreferenceUpdated(Preference paramPreference)
  {
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
      ((PreferenceUpdateListener)localIterator.next()).onPreferenceUpdated(getPreference());
  }

  public PreferenceUpdateListenerHolder setPreference(Preference paramPreference)
  {
    this.preference = paramPreference;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.PreferenceUpdateListenerHolder
 * JD-Core Version:    0.6.0
 */