package com.vlingo.sdk.internal.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import com.vlingo.sdk.internal.core.ApplicationAdapter;
import com.vlingo.sdk.internal.http.custom.AndroidVStreamConnection;
import com.vlingo.sdk.internal.http.custom.VConnectionFactory;
import com.vlingo.sdk.internal.util.StringUtils;
import java.io.IOException;

public class ConnectionManager extends ConnectionProvider
{
  public static final int CONNECTION_TYPE_BIS = 1;
  public static final int CONNECTION_TYPE_DIRECT = 0;
  public static final int CONNECTION_TYPE_MDS = 2;
  public static final int DEFAULT_TIMEOUT_FAST_NETWORK = 20000;
  public static final int DEFAULT_TIMEOUT_SLOW_NETWORK = 40000;
  private static final int RETRY_THRESHOLD_TIME_DEFAULT = 25000;
  private static ConnectionManager instance;
  private static final String[] possibleParameters;
  private static boolean sTimedOut = false;
  private int cdmaSignal = 0;
  private int evdoSignal = 0;
  private int gsmSignal = 0;

  static
  {
    instance = null;
    String[] arrayOfString = new String[12];
    arrayOfString[0] = "deviceside=";
    arrayOfString[1] = "apn=";
    arrayOfString[2] = "interface=";
    arrayOfString[3] = "wapgatewayapn=";
    arrayOfString[4] = "wapsourceport=";
    arrayOfString[5] = "wapsourceip=";
    arrayOfString[6] = "wapgatewayport=";
    arrayOfString[7] = "wapgatewayip=";
    arrayOfString[8] = "wapenablewtls=";
    arrayOfString[9] = "tunnelauthusername=";
    arrayOfString[10] = "tunnelauthpassword=";
    arrayOfString[11] = "endtoendrequired";
    possibleParameters = arrayOfString;
  }

  public static String DEBUG_fixLocalIPs(String paramString)
  {
    return paramString;
  }

  public static void destroy()
  {
    instance = null;
  }

  public static String getConnectionTypeName(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    default:
      str = "Unknown";
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      return str;
      str = "DirectTCP";
      continue;
      str = "BIS-B";
      continue;
      str = "MDS";
    }
  }

  public static ConnectionManager getInstance()
  {
    monitorenter;
    try
    {
      if (instance == null)
        instance = new ConnectionManager();
      ConnectionManager localConnectionManager = instance;
      monitorexit;
      return localConnectionManager;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private String getNetworkType(TelephonyManager paramTelephonyManager)
  {
    String str;
    switch (paramTelephonyManager.getNetworkType())
    {
    default:
      str = "unknown";
    case 0:
    case 1:
    case 2:
    case 3:
    case 8:
    case 9:
    case 10:
    case 4:
    case 5:
    case 6:
    case 12:
    case 7:
    case 11:
    }
    while (true)
    {
      return str;
      str = "NETWORK_TYPE_UNKNOWN";
      continue;
      str = "NETWORK_TYPE_GPRS";
      continue;
      str = "NETWORK_TYPE_EDGE";
      continue;
      str = "NETWORK_TYPE_UMTS";
      continue;
      str = "NETWORK_TYPE_HSDPA";
      continue;
      str = "NETWORK_TYPE_HSUPA";
      continue;
      str = "NETWORK_TYPE_HSPA";
      continue;
      str = "NETWORK_TYPE_CDMA";
      continue;
      str = "NETWORK_TYPE_EVDO_0";
      continue;
      str = "NETWORK_TYPE_EVDO_0";
      continue;
      str = "NETWORK_TYPE_EVDO_B";
      continue;
      str = "NETWORK_TYPE_1xRTT";
      continue;
      str = "NETWORK_TYPE_IDEN";
    }
  }

  public static int getOptimalConnectTimeout()
  {
    if (isSlowNetwork());
    for (int i = 40000; ; i = 20000)
      return i;
  }

  public static int getOptimalNetworkTimeout()
  {
    if (isSlowNetwork());
    for (int i = 40000; ; i = 20000)
      return i;
  }

  public static boolean isSlowNetwork()
  {
    int i = 1;
    int j = ((TelephonyManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("phone")).getNetworkType();
    if ((isTimedOut()) || (j == 7) || (j == 2) || (j == i) || (j == 0));
    while (true)
    {
      return i;
      i = 0;
    }
  }

  public static boolean isTimedOut()
  {
    return sTimedOut;
  }

  public static void setTimedOut(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      sTimedOut = paramBoolean;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  protected boolean areConnectionParametersInUrl(String paramString)
  {
    int j;
    int k;
    if (paramString.indexOf(';') != -1)
    {
      String[] arrayOfString = StringUtils.split(paramString.toLowerCase(), ';');
      j = 1;
      if (j < arrayOfString.length)
      {
        k = 0;
        label34: if (k < possibleParameters.length)
          if (arrayOfString[j].indexOf(possibleParameters[k]) == -1);
      }
    }
    for (int i = 1; ; i = 0)
    {
      return i;
      k++;
      break label34;
      j++;
      break;
    }
  }

  public ConnectionResult buildConnectionUrl(int paramInt, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public String generateTimeoutErrorString()
  {
    String str1 = null;
    if (getNetworkInfo() != null)
      str1 = getNetworkInfo().getTypeName();
    String str2 = "" + "; cdmasiglev=" + getCdmaSignal();
    String str3 = str2 + "; evdosiglev=" + getEvdoSignal();
    String str4 = str3 + "; gsmsiglev=" + getGsmSignal();
    String str5 = str4 + "; networkType=" + getNetworkTypeName();
    String str6 = str5 + "; connType=" + str1;
    if (str1.equalsIgnoreCase("wifi"))
      str6 = str6 + "; wifilinkspd=" + getWifiLinkSpeed();
    return str6;
  }

  public String getCarrierName()
  {
    return ((TelephonyManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("phone")).getNetworkOperatorName();
  }

  public int getCdmaSignal()
  {
    return this.cdmaSignal;
  }

  public Connection getConnection(String paramString, int paramInt, boolean paramBoolean)
    throws IOException
  {
    return getConnectionWithDetails(paramString, paramInt, paramBoolean, 25000, false).connection;
  }

  public int getConnectionTypeSetting()
  {
    return 0;
  }

  public ConnectionResult getConnectionWithDetails(String paramString, int paramInt, boolean paramBoolean)
    throws IOException
  {
    return getConnectionWithDetails(paramString, paramInt, paramBoolean, 25000, false);
  }

  public ConnectionResult getConnectionWithDetails(String paramString, int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2)
    throws IOException
  {
    AndroidVStreamConnection localAndroidVStreamConnection = VConnectionFactory.createConnection(paramString);
    if (paramInt1 == 0)
      localAndroidVStreamConnection.setDoOutput(false);
    ConnectionResult localConnectionResult = new ConnectionResult();
    localConnectionResult.connection = localAndroidVStreamConnection;
    localConnectionResult.connectionType = 0;
    return localConnectionResult;
  }

  public ConnectionResult getConnectionWithDetails(String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws IOException
  {
    return getConnectionWithDetails(paramString, paramInt, paramBoolean1, 25000, paramBoolean2);
  }

  public String getCurrentConnectionType()
  {
    return getConnectionTypeName(getConnectionTypeSetting());
  }

  public int getEvdoSignal()
  {
    return this.evdoSignal;
  }

  // ERROR //
  public int getGetResponseFromServer(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 281	java/net/URL
    //   5: dup
    //   6: aload_1
    //   7: invokespecial 282	java/net/URL:<init>	(Ljava/lang/String;)V
    //   10: invokevirtual 286	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   13: checkcast 288	java/net/HttpURLConnection
    //   16: astore_2
    //   17: aload_2
    //   18: sipush 25000
    //   21: invokevirtual 292	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   24: aload_2
    //   25: sipush 25000
    //   28: invokevirtual 295	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   31: aload_2
    //   32: ldc_w 297
    //   35: invokevirtual 300	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   38: aload_2
    //   39: ldc_w 302
    //   42: invokevirtual 300	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   45: new 304	java/io/BufferedInputStream
    //   48: dup
    //   49: aload_2
    //   50: invokevirtual 308	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   53: invokespecial 311	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   56: invokevirtual 316	java/io/InputStream:close	()V
    //   59: aload_2
    //   60: invokevirtual 319	java/net/HttpURLConnection:getResponseCode	()I
    //   63: istore 9
    //   65: iload 9
    //   67: istore 5
    //   69: aload_2
    //   70: ifnull +7 -> 77
    //   73: aload_2
    //   74: invokevirtual 322	java/net/HttpURLConnection:disconnect	()V
    //   77: iload 5
    //   79: ireturn
    //   80: astore 8
    //   82: bipush 255
    //   84: istore 5
    //   86: aload_2
    //   87: ifnull -10 -> 77
    //   90: aload_2
    //   91: invokevirtual 322	java/net/HttpURLConnection:disconnect	()V
    //   94: goto -17 -> 77
    //   97: astore 7
    //   99: bipush 254
    //   101: istore 5
    //   103: aload_2
    //   104: ifnull -27 -> 77
    //   107: aload_2
    //   108: invokevirtual 322	java/net/HttpURLConnection:disconnect	()V
    //   111: goto -34 -> 77
    //   114: astore 6
    //   116: bipush 253
    //   118: istore 5
    //   120: aload_2
    //   121: ifnull -44 -> 77
    //   124: aload_2
    //   125: invokevirtual 322	java/net/HttpURLConnection:disconnect	()V
    //   128: goto -51 -> 77
    //   131: astore 4
    //   133: bipush 252
    //   135: istore 5
    //   137: aload_2
    //   138: ifnull -61 -> 77
    //   141: aload_2
    //   142: invokevirtual 322	java/net/HttpURLConnection:disconnect	()V
    //   145: goto -68 -> 77
    //   148: astore_3
    //   149: aload_2
    //   150: ifnull +7 -> 157
    //   153: aload_2
    //   154: invokevirtual 322	java/net/HttpURLConnection:disconnect	()V
    //   157: aload_3
    //   158: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	65	80	org/apache/http/conn/ConnectTimeoutException
    //   2	65	97	java/net/SocketTimeoutException
    //   2	65	114	java/net/MalformedURLException
    //   2	65	131	java/io/IOException
    //   2	65	148	finally
  }

  public int getGsmSignal()
  {
    return this.gsmSignal;
  }

  public NetworkInfo getNetworkInfo()
  {
    return ((ConnectivityManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
  }

  public String getNetworkTypeName()
  {
    return getNetworkType((TelephonyManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("phone"));
  }

  public int getWifiLinkSpeed()
  {
    return ((WifiManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("wifi")).getConnectionInfo().getLinkSpeed();
  }

  public int getWifiSignalStrength()
  {
    return ((WifiManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("wifi")).getConnectionInfo().getRssi();
  }

  public boolean isAirplaneModeEnabled()
  {
    int i = 1;
    if (Settings.System.getInt(ApplicationAdapter.getInstance().getApplicationContext().getContentResolver(), "airplane_mode_on", 0) == i);
    while (true)
    {
      return i;
      i = 0;
    }
  }

  public boolean isConnectionTestRequired()
  {
    return false;
  }

  public void resetConnectionTest()
  {
  }

  public void setCdmaSignal(int paramInt)
  {
    this.cdmaSignal = paramInt;
  }

  public void setEvdoSignal(int paramInt)
  {
    this.evdoSignal = paramInt;
  }

  public void setGsmSignal(int paramInt)
  {
    this.gsmSignal = paramInt;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.net.ConnectionManager
 * JD-Core Version:    0.6.0
 */