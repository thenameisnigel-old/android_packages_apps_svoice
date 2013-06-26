package com.vlingo.sdk.internal;

import com.vlingo.sdk.internal.http.URL;
import com.vlingo.sdk.internal.recognizer.SRServerDetails;

public class AndroidServerDetails
  implements SRServerDetails
{
  public static final String DEFAULT_ASR_SERVER_HOST = "androidasr.vlingo.com";
  private static final int DEFAULT_PORT = 80;
  public static final String DEFAULT_TTS_SERVER_HOST = "tts.vlingo.com";
  public static final String DEFAULT_UTTSCOOP_ASR_SERVER_HOST = "uttscoop.vlingo.com";
  public static final String DEFAULT_UTTSCOOP_LIST_SERVER_HOST = "downloads.vlingo.com";
  public static final String DEFAULT_UTTSCOOP_LMDC_SERVER_HOST = "lmdcapps.vlingo.com";
  private static final String PATH_ACTIVITY_LOG = "/voicepad/activitylog";
  private static final String PATH_CANCEL = "/voicepad/srcancel";
  private static final String PATH_HELLO = "/voicepad/hello";
  private static final String PATH_LMTT = "/voicepad/lmtt";
  public static final String PATH_SR = "/voicepad/sr";
  private static final String PATH_STATS = "/voicepad/stats";
  private static final String PATH_TTS = "/tts/tts2";
  private static final String PATH_UTTSCOOP_LISTS = "/lmdc/lists";
  private static final String PATH_UTTSCOOP_LMDC = "/lmdc/go";
  private static URL s_LMTTURL;
  private static URL s_TTSServerURL;
  private static URL s_cancelURL;
  private static URL s_helloURL;
  private static URL s_srURL = new URL("androidasr.vlingo.com", 80, "/voicepad/sr");
  private static URL s_statsURL;
  private static URL s_userLoggingURL;
  private static URL s_uttscoopLMDCURL;
  private static URL s_uttscoopListURL;

  static
  {
    s_cancelURL = new URL("androidasr.vlingo.com", 80, "/voicepad/srcancel");
    s_statsURL = new URL("androidasr.vlingo.com", 80, "/voicepad/stats");
    s_LMTTURL = new URL("androidasr.vlingo.com", 80, "/voicepad/lmtt");
    s_userLoggingURL = new URL("androidasr.vlingo.com", 80, "/voicepad/activitylog");
    s_helloURL = new URL("androidasr.vlingo.com", 80, "/voicepad/hello");
    s_TTSServerURL = new URL("tts.vlingo.com", 80, "/tts/tts2");
    s_uttscoopLMDCURL = new URL("lmdcapps.vlingo.com", 80, "/lmdc/go");
    s_uttscoopListURL = new URL("downloads.vlingo.com", 80, "/lmdc/lists");
  }

  public static URL getHelloURL()
  {
    return s_helloURL;
  }

  public static URL getLMTTURL()
  {
    return s_LMTTURL;
  }

  public static URL getTTSURL()
  {
    return s_TTSServerURL;
  }

  public static URL getUserLoggingURL()
  {
    return s_userLoggingURL;
  }

  public static URL getUttscoopLMDCURL()
  {
    return s_uttscoopLMDCURL;
  }

  public static URL getUttscoopListURL()
  {
    return s_uttscoopListURL;
  }

  public static void setHelloServerName(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      throw new IllegalArgumentException("name cannot be null or empty");
    s_helloURL = new URL(paramString + "/voicepad/hello");
  }

  public static void setLMTTServerName(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      throw new IllegalArgumentException("name cannot be null or empty");
    s_LMTTURL = new URL(paramString + "/voicepad/lmtt");
  }

  public static void setLogServerName(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      throw new IllegalArgumentException("name cannot be null or empty");
    s_userLoggingURL = new URL(paramString + "/voicepad/activitylog");
  }

  public static void setServerName(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      throw new IllegalArgumentException("name cannot be null or empty");
    s_srURL = new URL(paramString + "/voicepad/sr");
    s_cancelURL = new URL(paramString + "/voicepad/srcancel");
    s_statsURL = new URL(paramString + "/voicepad/stats");
    s_LMTTURL = new URL(paramString + "/voicepad/lmtt");
    s_userLoggingURL = new URL(paramString + "/voicepad/activitylog");
    s_helloURL = new URL(paramString + "/voicepad/hello");
  }

  public static void setTTSServerName(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      throw new IllegalArgumentException("name cannot be null or empty");
    s_TTSServerURL = new URL(paramString + "/tts/tts2");
  }

  public URL getASRCancelURL()
  {
    return s_cancelURL;
  }

  public URL getASRURL()
  {
    return s_srURL;
  }

  public URL getStatsURL()
  {
    return s_statsURL;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.AndroidServerDetails
 * JD-Core Version:    0.6.0
 */