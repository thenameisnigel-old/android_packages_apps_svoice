package com.vlingo.midas.settings.debug;

import android.preference.PreferenceActivity;

public class ServerPreferenceBuilder extends PreferenceBuilder<ServerPreference>
{
  private static PreferenceUpdateListener summaryDisplay = new ServerPreferenceBuilder.1();

  public ServerPreferenceBuilder()
  {
    setShowAsSummary(summaryDisplay);
  }

  public ServerPreferenceBuilder(String paramString)
  {
    super(paramString);
    setShowAsSummary(summaryDisplay);
  }

  public ServerPreference register(PreferenceActivity paramPreferenceActivity)
  {
    ServerPreference localServerPreference = (ServerPreference)attach(paramPreferenceActivity);
    localServerPreference.setValue((String)getValue());
    localServerPreference.setSummary((String)getSummary());
    return localServerPreference;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.ServerPreferenceBuilder
 * JD-Core Version:    0.6.0
 */