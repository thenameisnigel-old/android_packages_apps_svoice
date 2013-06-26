package com.vlingo.core.internal.util;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;

public class SystemServicesUtil
{
  private Context context;
  private BluetoothAdapter mBluetoothAdapter;
  private LocationManager mLocationManger;
  private WifiManager mWifiManger;

  public SystemServicesUtil(Context paramContext)
  {
    this.context = paramContext;
  }

  private BluetoothAdapter getBluetoothAdapter()
  {
    if (this.mBluetoothAdapter == null)
      this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    return this.mBluetoothAdapter;
  }

  private LocationManager getLocationManager()
  {
    if (this.mLocationManger == null)
      this.mLocationManger = ((LocationManager)this.context.getSystemService("location"));
    return this.mLocationManger;
  }

  private WifiManager getWifiManager()
  {
    if (this.mWifiManger == null)
      this.mWifiManger = ((WifiManager)this.context.getSystemService("wifi"));
    return this.mWifiManger;
  }

  private void gpsToggle(Context paramContext)
  {
    if (!Settings.Secure.getString(paramContext.getContentResolver(), "location_providers_allowed").contains("gps"))
    {
      Intent localIntent = new Intent();
      localIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
      localIntent.addCategory("android.intent.category.ALTERNATIVE");
      localIntent.setData(Uri.parse("3"));
      paramContext.sendBroadcast(localIntent);
    }
  }

  public boolean isBluetoothEnabled()
  {
    int i = 0;
    switch (getBluetoothAdapter().getState())
    {
    case 10:
    case 11:
    default:
    case 12:
    }
    while (true)
    {
      return i;
      i = 1;
    }
  }

  public boolean isGpsEnabled()
  {
    if (getLocationManager().isProviderEnabled("gps"));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isWifiEnabled()
  {
    if (getWifiManager().isWifiEnabled());
    for (int i = 1; ; i = 0)
      return i;
  }

  public void setBluetoothEnabled(boolean paramBoolean)
  {
    BluetoothAdapter localBluetoothAdapter = getBluetoothAdapter();
    if (paramBoolean)
      localBluetoothAdapter.enable();
    while (true)
    {
      return;
      localBluetoothAdapter.disable();
    }
  }

  public void setGpsEnabled(boolean paramBoolean)
  {
    Settings.Secure.setLocationProviderEnabled(this.context.getContentResolver(), "gps", paramBoolean);
  }

  public boolean setWifiEnabled(boolean paramBoolean)
  {
    return getWifiManager().setWifiEnabled(paramBoolean);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.SystemServicesUtil
 * JD-Core Version:    0.6.0
 */