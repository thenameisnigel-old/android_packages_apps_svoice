package com.vlingo.midas.settings.debug;

import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;

public abstract class PreferenceUpdateListenerAdapter
  implements PreferenceUpdateListener
{
  public void onPreferenceUpdated(Preference paramPreference)
  {
  }

  protected Object valueOf(Preference paramPreference)
  {
    Object localObject = null;
    if ((paramPreference instanceof EditTextPreference))
      localObject = ((EditTextPreference)paramPreference).getText();
    while (true)
    {
      return localObject;
      if ((paramPreference instanceof CheckBoxPreference))
      {
        localObject = Boolean.valueOf(((CheckBoxPreference)paramPreference).isChecked());
        continue;
      }
      if ((paramPreference instanceof ListPreference))
      {
        localObject = ((ListPreference)paramPreference).getValue();
        continue;
      }
      if (!(paramPreference instanceof ServerPreference))
        continue;
      localObject = ((ServerPreference)paramPreference).getValue();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.PreferenceUpdateListenerAdapter
 * JD-Core Version:    0.6.0
 */