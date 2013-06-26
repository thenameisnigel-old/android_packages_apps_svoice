package com.vlingo.core.internal.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil
{
  public static boolean isNetworkConnectionAvailable()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("connectivity");
    NetworkInfo localNetworkInfo;
    if (localConnectivityManager != null)
    {
      localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
      if (localNetworkInfo == null);
    }
    for (boolean bool = localNetworkInfo.isConnected(); ; bool = false)
      return bool;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.NetworkUtil
 * JD-Core Version:    0.6.0
 */