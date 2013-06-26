package com.vlingo.midas.settings.debug;

import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;

public class PreferenceBuilder<T extends Preference>
{
  private static PreferenceUpdateListenerHolder defaultUpdateActions = new PreferenceUpdateListenerHolder();
  private PreferenceClickListenerHolder clickListeners = new PreferenceClickListenerHolder();
  private Object defaultTo;
  private PreferenceChangeListenerHolder listeners = new PreferenceChangeListenerHolder();
  private String setting;
  private boolean showAsSummary;
  private PreferenceUpdateListener showSummaryAsValueAction;
  private Object summary;
  private PreferenceUpdateListenerHolder updateListeners = new PreferenceUpdateListenerHolder(defaultUpdateActions);
  private Object value;

  public PreferenceBuilder()
  {
  }

  public PreferenceBuilder(String paramString)
  {
    onSetting(paramString);
  }

  public static void onAllUpdatedPreferences(PreferenceUpdateListener paramPreferenceUpdateListener)
  {
    defaultUpdateActions.add(paramPreferenceUpdateListener);
  }

  protected T attach(PreferenceActivity paramPreferenceActivity)
  {
    Preference localPreference = paramPreferenceActivity.findPreference(getSetting());
    localPreference.setDefaultValue(getDefault());
    localPreference.setOnPreferenceChangeListener(getListener());
    return localPreference;
  }

  public PreferenceBuilder<T> defaultTo(Object paramObject)
  {
    this.defaultTo = paramObject;
    return this;
  }

  public Preference.OnPreferenceClickListener getClickListener()
  {
    return this.clickListeners;
  }

  public Object getDefault()
  {
    return this.defaultTo;
  }

  public Preference.OnPreferenceChangeListener getListener()
  {
    return this.listeners;
  }

  public String getSetting()
  {
    return this.setting;
  }

  protected PreferenceUpdateListener getShowAsSummary()
  {
    return this.showSummaryAsValueAction;
  }

  public Object getSummary()
  {
    if (isShowAsSummary());
    for (Object localObject = getValue(); ; localObject = this.summary)
      return localObject;
  }

  public PreferenceUpdateListener getUpdateListener()
  {
    return this.updateListeners;
  }

  public Object getValue()
  {
    if (this.value == null);
    for (Object localObject = getDefault(); ; localObject = this.value)
      return localObject;
  }

  protected boolean hasValue()
  {
    if (this.value != null);
    for (int i = 1; ; i = 0)
      return i;
  }

  protected boolean isShowAsSummary()
  {
    return this.showAsSummary;
  }

  public PreferenceBuilder<T> onPreferenceChange(Preference.OnPreferenceChangeListener paramOnPreferenceChangeListener)
  {
    this.listeners.add(paramOnPreferenceChangeListener);
    return this;
  }

  public PreferenceBuilder<T> onPreferenceClick(Preference.OnPreferenceClickListener paramOnPreferenceClickListener)
  {
    this.clickListeners.add(paramOnPreferenceClickListener);
    return this;
  }

  public PreferenceBuilder<T> onPreferenceUpdated(PreferenceUpdateListener paramPreferenceUpdateListener)
  {
    this.updateListeners.add(paramPreferenceUpdateListener);
    return this;
  }

  public PreferenceBuilder<T> onSetting(String paramString)
  {
    this.setting = paramString;
    return this;
  }

  public T register(PreferenceActivity paramPreferenceActivity)
  {
    return attach(paramPreferenceActivity);
  }

  public T register(PreferenceActivity paramPreferenceActivity, PreferencesUpdater paramPreferencesUpdater)
  {
    Preference localPreference = register(paramPreferenceActivity);
    paramPreferencesUpdater.register(getSetting(), this.updateListeners.setPreference(localPreference));
    return localPreference;
  }

  protected void setShowAsSummary(PreferenceUpdateListener paramPreferenceUpdateListener)
  {
    this.showSummaryAsValueAction = paramPreferenceUpdateListener;
  }

  public PreferenceBuilder<T> showAsSummary()
  {
    this.showAsSummary = true;
    onPreferenceUpdated(getShowAsSummary());
    return this;
  }

  public PreferenceBuilder<T> withSummary(Object paramObject)
  {
    this.summary = paramObject;
    if (isShowAsSummary())
    {
      this.updateListeners.remove(getShowAsSummary());
      this.showAsSummary = false;
    }
    return this;
  }

  public PreferenceBuilder<T> withValue(Object paramObject)
  {
    this.value = paramObject;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.PreferenceBuilder
 * JD-Core Version:    0.6.0
 */