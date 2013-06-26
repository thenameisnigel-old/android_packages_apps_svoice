package com.vlingo.midas.settings.debug;

import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;

public class CheckBoxPreferenceBuilder extends PreferenceBuilder<CheckBoxPreference>
{
  private static PreferenceUpdateListener showAsSummaryAction = new CheckBoxPreferenceBuilder.1();

  public CheckBoxPreferenceBuilder()
  {
    setShowAsSummary(showAsSummaryAction);
  }

  public CheckBoxPreferenceBuilder(String paramString)
  {
    super(paramString);
    setShowAsSummary(showAsSummaryAction);
  }

  public CheckBoxPreference register(PreferenceActivity paramPreferenceActivity)
  {
    CheckBoxPreference localCheckBoxPreference = (CheckBoxPreference)super.register(paramPreferenceActivity);
    if ((!localCheckBoxPreference.getSharedPreferences().contains(localCheckBoxPreference.getKey())) && (getDefault() != null))
      localCheckBoxPreference.setChecked(((Boolean)getDefault()).booleanValue());
    return localCheckBoxPreference;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.CheckBoxPreferenceBuilder
 * JD-Core Version:    0.6.0
 */