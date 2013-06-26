package com.vlingo.sdk.internal.vlservice;

import android.text.format.DateFormat;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.internal.audio.AudioDevice;
import com.vlingo.sdk.internal.core.ApplicationAdapter;
import com.vlingo.sdk.internal.http.cookies.CookieJar;
import com.vlingo.sdk.internal.http.cookies.CookieJarManager;
import com.vlingo.sdk.internal.net.ConnectionManager;
import com.vlingo.sdk.internal.recognizer.ClientMeta;
import com.vlingo.sdk.internal.recognizer.SRContext;
import com.vlingo.sdk.internal.recognizer.SoftwareMeta;
import com.vlingo.sdk.internal.recognizer.sr3.HttpConnectionAdapter;
import com.vlingo.sdk.internal.settings.Settings;
import com.vlingo.sdk.internal.util.Screen;
import com.vlingo.sdk.internal.util.StringUtils;
import com.vlingo.sdk.util.SDKDebugSettings;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

public abstract class VLServiceUtil
{
  public static final String VLINGO_PROTOCOL_VERSION = "3.8";
  static final String XVL_PROTOCOL_HEADER_VALUE = "Version=3.8; ResponseEncoding=";

  public static void addStandardVlingoHttpHeaders(Hashtable<String, String> paramHashtable, ClientMeta paramClientMeta, SoftwareMeta paramSoftwareMeta, SRContext paramSRContext)
  {
    addStandardVlingoHttpHeaders(paramHashtable, paramClientMeta, paramSoftwareMeta, null, paramSRContext);
  }

  public static void addStandardVlingoHttpHeaders(Hashtable<String, String> paramHashtable, ClientMeta paramClientMeta, SoftwareMeta paramSoftwareMeta, String paramString, SRContext paramSRContext)
  {
    paramHashtable.put("Cache-Control", "no-cache,no-store,max-age=0,no-transform");
    if (paramHashtable.get("X-vlprotocol") == null)
      setProtocolHeader(paramHashtable, "text/xml");
    Screen localScreen = Screen.getInstance();
    Integer localInteger1 = Integer.valueOf(Settings.getPersistentString("max.width", "0"));
    Integer localInteger2 = Integer.valueOf(Settings.getPersistentString("plot.width", "0"));
    StringBuilder localStringBuilder1 = new StringBuilder();
    if (localScreen.getWidth() > 0)
      localStringBuilder1.append("Width=" + Integer.toString(localScreen.getWidth()));
    if (Screen.getMagnification() > 0.0F)
    {
      updateWithDivider(localStringBuilder1);
      localStringBuilder1.append("Mag=" + Float.toString(Screen.getMagnification()));
    }
    if (localInteger1.intValue() > 0)
    {
      updateWithDivider(localStringBuilder1);
      localStringBuilder1.append("MaxWidth=" + Integer.toString(localInteger1.intValue()));
    }
    if (localInteger2.intValue() > 0)
    {
      updateWithDivider(localStringBuilder1);
      localStringBuilder1.append("PlotWidth=" + Integer.toString(localInteger2.intValue()));
    }
    paramHashtable.put("X-vldisplaygeometry", localStringBuilder1.toString());
    String str1 = Settings.getPersistentString("tos_accepted_date", null);
    if (str1 != null)
      paramHashtable.put("x-vleula", "tos_accepted_date=" + str1);
    String str2;
    if (StringUtils.isNullOrWhiteSpace(paramString))
      str2 = paramClientMeta.getLanguage();
    while (true)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("DeviceMake=" + paramClientMeta.getDeviceMake());
      localStringBuffer.append("; DeviceOSName=" + paramClientMeta.getDeviceOSName());
      localStringBuffer.append("; DeviceModel=" + paramClientMeta.getDeviceModel());
      localStringBuffer.append("; DeviceOS=" + paramClientMeta.getDeviceOS());
      localStringBuffer.append("; Language=" + str2);
      localStringBuffer.append("; ConnectionType=" + ConnectionManager.getInstance().getCurrentConnectionType());
      localStringBuffer.append("; Carrier=" + paramClientMeta.getCarrier());
      String str3 = paramClientMeta.getCarrierCountry();
      if ((str3 != null) && (str3.length() > 0))
        localStringBuffer.append("; CarrierCountry=" + str3);
      String str4 = paramClientMeta.getDeviceID();
      if ((str4 != null) && (str4.length() > 0))
        localStringBuffer.append("; DeviceID=" + str4);
      if (paramClientMeta.getPhoneNumber().length() > 0)
        localStringBuffer.append("; PhoneNumber=" + paramClientMeta.getPhoneNumber());
      localStringBuffer.append("; AudioDevice=" + AudioDevice.getInstance().getAudioDeviceName());
      paramHashtable.put("X-vlclient", localStringBuffer.toString());
      String str5 = "Name=" + paramSoftwareMeta.getName() + "; Version=" + paramSoftwareMeta.getVersion();
      String str6 = paramSoftwareMeta.getAppChannel();
      if ((str6 != null) && (str6.length() > 0))
        str5 = str5 + "; AppChannel=" + str6;
      paramHashtable.put("X-vlsoftware", str5);
      paramHashtable.put("X-vlsdk", "Name=" + paramSoftwareMeta.getSdkName() + "; Version=" + paramSoftwareMeta.getSdkVersion());
      if ((VLSdk.getInstance().getLocationOn()) && (VLSdk.getInstance().isSendingLocationEnabled()))
      {
        String str12 = paramClientMeta.getLocation();
        if ((str12 != null) && (str12.length() > 0))
          paramHashtable.put("X-vllocation", str12);
      }
      paramHashtable.put("X-vlsr", "AppID=" + paramSoftwareMeta.getAppId());
      if (!paramHashtable.containsKey("Content-Type"))
        paramHashtable.put("Content-Type", "application/octet-stream;boundary=" + HttpConnectionAdapter.ivBoundary);
      paramHashtable.put("Accept-Charset", "utf-8,ISO-8859-1;q=0.5,*;q=0.5");
      String str7 = str2.substring(0, 2);
      paramHashtable.put("Accept-Language", str2 + "," + str7 + ";q=0.7,en;q=0.5");
      if ((paramSRContext != null) && (paramSRContext.isDMRequest()))
      {
        paramHashtable.put("X-vvs", "Version=2.0");
        SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        paramHashtable.put("X-vlclientdate", "Date=" + localSimpleDateFormat1.format(new Date()));
        SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz", Locale.US);
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("ClientTime=").append(localSimpleDateFormat2.format(new Date()));
        String str8 = paramSRContext.getUsername();
        if (str8 == null)
          str8 = "";
        String str9 = str8.replaceAll("(;|=)+", "");
        if (!StringUtils.isNullOrWhiteSpace(str9))
          localStringBuilder2.append("; UserName=").append(str9);
        String str10 = paramSRContext.getDialogGUID();
        if (!StringUtils.isNullOrWhiteSpace(str10))
          localStringBuilder2.append("; DialogGUID=").append(str10);
        int i = paramSRContext.getDialogTurnNumber();
        if (i > -1)
          localStringBuilder2.append("; TurnNumber=").append(i);
        SDKDebugSettings localSDKDebugSettings = VLSdk.getInstance().getDebugSettings();
        if ((localSDKDebugSettings != null) && (localSDKDebugSettings.isForceNonDM()))
          localStringBuilder2.append("; DisableDM=true");
        localStringBuilder2.append("; Use24HourTime=").append(DateFormat.is24HourFormat(ApplicationAdapter.getInstance().getApplicationContext()));
        paramHashtable.put("X-vldm", localStringBuilder2.toString());
        StringBuilder localStringBuilder3 = new StringBuilder();
        HashMap localHashMap = paramSRContext.getDMHeaderKVPairs();
        Iterator localIterator = localHashMap.keySet().iterator();
        while (true)
          if (localIterator.hasNext())
          {
            String str11 = (String)localIterator.next();
            localStringBuilder3.append(str11).append("=").append((String)localHashMap.get(str11)).append("; ");
            continue;
            str2 = paramString;
            break;
          }
        if (localStringBuilder3.length() <= 0)
          break;
        localStringBuilder3.delete(localStringBuilder3.lastIndexOf(";"), -1 + localStringBuilder3.length());
        paramHashtable.put("X-vlconfiguration", localStringBuilder3.toString());
      }
    }
    while (true)
    {
      return;
      paramHashtable.put("X-vvs", "Version=1.0");
    }
  }

  public static Hashtable<String, String> addVLServiceCookies(Hashtable<String, String> paramHashtable, String paramString1, String paramString2)
  {
    if (paramHashtable == null)
      paramHashtable = new Hashtable();
    VLServiceCookieManager.getInstance().addAllCookiesToHashtable(paramHashtable, paramString1, paramString2);
    return paramHashtable;
  }

  public static String getSpeakerID()
  {
    return VLServiceCookieManager.getInstance().getCookieValue("VLSPEAKER");
  }

  public static void handleResponseCookies(CookieJar paramCookieJar)
  {
    VLServiceCookieManager.getInstance().mergeCookies(paramCookieJar);
  }

  public static void setProtocolHeader(Hashtable<String, String> paramHashtable, String paramString)
  {
    paramHashtable.put("X-vlprotocol", "Version=3.8; ResponseEncoding=" + paramString);
  }

  private static void updateWithDivider(StringBuilder paramStringBuilder)
  {
    if (paramStringBuilder.length() > 0)
      paramStringBuilder.append("; ");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.vlservice.VLServiceUtil
 * JD-Core Version:    0.6.0
 */