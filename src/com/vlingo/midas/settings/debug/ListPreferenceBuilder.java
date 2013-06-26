package com.vlingo.midas.settings.debug;

import android.preference.ListPreference;
import android.preference.PreferenceActivity;

public class ListPreferenceBuilder extends PreferenceBuilder<ListPreference>
{
  private static PreferenceUpdateListener showAsSummaryAction = new ListPreferenceBuilder.1();
  private String[] entryValues;
  private String[] values;

  public ListPreferenceBuilder()
  {
    setShowAsSummary(showAsSummaryAction);
  }

  public ListPreferenceBuilder(String paramString)
  {
    super(paramString);
    setShowAsSummary(showAsSummaryAction);
  }

  public ListPreferenceBuilder defaultTo(String paramString)
  {
    super.defaultTo(paramString);
    return this;
  }

  public ListPreferenceBuilder onSetting(String paramString)
  {
    super.onSetting(paramString);
    return this;
  }

  public ListPreference register(PreferenceActivity paramPreferenceActivity)
  {
    ListPreference localListPreference = (ListPreference)attach(paramPreferenceActivity);
    localListPreference.setEntries(this.values);
    localListPreference.setEntryValues(this.entryValues);
    localListPreference.setValue((String)getValue());
    if (isShowAsSummary())
      localListPreference.setSummary(localListPreference.getValue());
    return localListPreference;
  }

  public ListPreferenceBuilder setEntries(String[] paramArrayOfString)
  {
    this.values = paramArrayOfString;
    return this;
  }

  public ListPreferenceBuilder setEntryValues(String[] paramArrayOfString)
  {
    this.entryValues = paramArrayOfString;
    return this;
  }

  public ListPreferenceBuilder showAsSummary()
  {
    super.showAsSummary();
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.ListPreferenceBuilder
 * JD-Core Version:    0.6.0
 */