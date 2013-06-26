package com.vlingo.core.internal.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.vlingo.core.internal.settings.Settings;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class ADMController
{
  public static String SAFEREADER = "Feature.SAFEREADER";
  protected static ADMController instance;
  protected ApplicationModeBroadcastReceiver appModeBroadcastReceiver = null;
  private HashMap<String, CopyOnWriteArrayList<ADMFeatureListener>> featureListeners = new HashMap();
  private HashMap<String, Boolean> featureStatuses;
  protected boolean onBoot = false;

  protected ADMController()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.HEADSET_PLUG");
    localIntentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
    localIntentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
    localIntentFilter.addAction("com.vlingo.client.app.action.VLINGO_APP_START");
    ApplicationAdapter.getInstance().getApplicationContext().registerReceiver(this.appModeBroadcastReceiver, localIntentFilter);
    this.featureStatuses = new HashMap();
  }

  protected static ADMController getInstance()
  {
    if (instance == null)
      instance = new ADMController();
    return instance;
  }

  public void addListener(ADMFeatureListener paramADMFeatureListener, String[] paramArrayOfString)
  {
    int i = paramArrayOfString.length;
    for (int j = 0; j < i; j++)
    {
      String str = paramArrayOfString[j];
      if (this.featureListeners.get(str) == null)
        this.featureListeners.put(str, new CopyOnWriteArrayList());
      if (this.featureStatuses.get(str) == null)
        this.featureStatuses.put(str, null);
      ((CopyOnWriteArrayList)this.featureListeners.get(str)).addIfAbsent(paramADMFeatureListener);
    }
    refreshFeatureStates();
  }

  protected boolean getFeatureStatus(String paramString)
  {
    int i = 0;
    if ((paramString.equals(SAFEREADER)) && (!this.onBoot) && (ClientSuppliedValues.shouldIncomingMessagesReadout()) && (Settings.getBoolean("tos_accepted", false)) && (Settings.getBoolean("iux_complete", false)) && (CorePackageInfoProvider.hasMessaging()))
      i = 1;
    return i;
  }

  protected void refreshAppModeStates()
  {
  }

  protected void refreshFeatureStates()
  {
    Iterator localIterator1 = this.featureStatuses.keySet().iterator();
    while (localIterator1.hasNext())
    {
      String str = (String)localIterator1.next();
      Boolean localBoolean = Boolean.valueOf(getFeatureStatus(str));
      if ((this.featureStatuses.get(str) != null) && (((Boolean)this.featureStatuses.get(str)).equals(localBoolean)))
        continue;
      this.featureStatuses.put(str, localBoolean);
      Iterator localIterator2 = ((CopyOnWriteArrayList)this.featureListeners.get(str)).iterator();
      while (localIterator2.hasNext())
        ((ADMFeatureListener)localIterator2.next()).onStatusChanged(str, localBoolean.booleanValue());
    }
    if (this.onBoot)
    {
      refreshAppModeStates();
      this.onBoot = false;
    }
  }

  public void removeListener(ADMFeatureListener paramADMFeatureListener)
  {
    Iterator localIterator = this.featureListeners.values().iterator();
    while (localIterator.hasNext())
      ((CopyOnWriteArrayList)localIterator.next()).remove(paramADMFeatureListener);
  }

  private class ApplicationModeBroadcastReceiver extends BroadcastReceiver
  {
    private ApplicationModeBroadcastReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent != null)
      {
        if ("com.vlingo.client.app.action.VLINGO_APP_START".equals(paramIntent.getAction()))
          ADMController.this.onBoot = true;
        ADMController.this.refreshFeatureStates();
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.ADMController
 * JD-Core Version:    0.6.0
 */