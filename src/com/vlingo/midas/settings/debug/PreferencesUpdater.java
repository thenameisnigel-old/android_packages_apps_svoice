package com.vlingo.midas.settings.debug;

import java.util.HashMap;

public class PreferencesUpdater extends HashMap<String, PreferenceUpdateAction>
{
  public void register(String paramString, PreferenceUpdateAction paramPreferenceUpdateAction)
  {
    put(paramString, paramPreferenceUpdateAction);
  }

  public void unregister(String paramString)
  {
    if (containsKey(paramString))
      remove(paramString);
  }

  public void unregister(String paramString, PreferenceUpdateAction paramPreferenceUpdateAction)
  {
    if ((containsKey(paramString)) && (get(paramString) == paramPreferenceUpdateAction))
      remove(paramString);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.PreferencesUpdater
 * JD-Core Version:    0.6.0
 */