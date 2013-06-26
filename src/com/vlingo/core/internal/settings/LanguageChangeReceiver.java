package com.vlingo.core.internal.settings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public abstract class LanguageChangeReceiver extends BroadcastReceiver
{
  public static final String EXTRA_LANGUAGE = "com.vlingo.client.settings.LANGUAGE";
  public static final String NOTIFY_LANGUAGE_CHANGED = "com.vlingo.client.settings.LANGUAGE_CHANGED";

  static void notifyLanguageChanged(String paramString, Context paramContext)
  {
    Intent localIntent = new Intent("com.vlingo.client.settings.LANGUAGE_CHANGED");
    localIntent.putExtra("com.vlingo.client.settings.LANGUAGE", paramString);
    paramContext.sendBroadcast(localIntent);
  }

  public static void register(LanguageChangeReceiver paramLanguageChangeReceiver, Context paramContext)
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.vlingo.client.settings.LANGUAGE_CHANGED");
    paramContext.registerReceiver(paramLanguageChangeReceiver, localIntentFilter);
  }

  public static void unregister(LanguageChangeReceiver paramLanguageChangeReceiver, Context paramContext)
  {
    paramContext.unregisterReceiver(paramLanguageChangeReceiver);
  }

  public abstract void onLanguageChanged(String paramString);

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("com.vlingo.client.settings.LANGUAGE_CHANGED".equals(paramIntent.getAction()))
      onLanguageChanged(paramIntent.getStringExtra("com.vlingo.client.settings.LANGUAGE"));
  }

  public void register(Context paramContext)
  {
    register(this, paramContext);
  }

  public void unregister(Context paramContext)
  {
    unregister(this, paramContext);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.settings.LanguageChangeReceiver
 * JD-Core Version:    0.6.0
 */