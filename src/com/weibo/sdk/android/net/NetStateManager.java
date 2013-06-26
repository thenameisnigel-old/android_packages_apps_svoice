package com.weibo.sdk.android.net;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import org.apache.http.HttpHost;

public class NetStateManager
{
  public static NetState CUR_NETSTATE = NetState.Mobile;
  private static Context mContext;

  public static HttpHost getAPN()
  {
    HttpHost localHttpHost = null;
    Uri localUri = Uri.parse("content://telephony/carriers/preferapn");
    Cursor localCursor = null;
    if (mContext != null)
      localCursor = mContext.getContentResolver().query(localUri, null, null, null, null);
    if ((localCursor != null) && (localCursor.moveToFirst()))
    {
      String str = localCursor.getString(localCursor.getColumnIndex("proxy"));
      if ((str != null) && (str.trim().length() > 0))
        localHttpHost = new HttpHost(str, 80);
      localCursor.close();
    }
    return localHttpHost;
  }

  public static enum NetState
  {
    static
    {
      NOWAY = new NetState("NOWAY", 2);
      NetState[] arrayOfNetState = new NetState[3];
      arrayOfNetState[0] = Mobile;
      arrayOfNetState[1] = WIFI;
      arrayOfNetState[2] = NOWAY;
      ENUM$VALUES = arrayOfNetState;
    }
  }

  public class NetStateReceive extends BroadcastReceiver
  {
    public NetStateReceive()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      NetStateManager.mContext = paramContext;
      if ("android.net.conn.CONNECTIVITY_CHANGE".equals(paramIntent.getAction()))
      {
        WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
        WifiInfo localWifiInfo = localWifiManager.getConnectionInfo();
        if ((!localWifiManager.isWifiEnabled()) || (-1 == localWifiInfo.getNetworkId()))
          NetStateManager.CUR_NETSTATE = NetStateManager.NetState.Mobile;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.net.NetStateManager
 * JD-Core Version:    0.6.0
 */