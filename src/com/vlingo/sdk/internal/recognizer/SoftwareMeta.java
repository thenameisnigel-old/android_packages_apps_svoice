package com.vlingo.sdk.internal.recognizer;

import com.vlingo.sdk.VLSdk;

public class SoftwareMeta
{
  private static SoftwareMeta instance = null;
  String appChannel;
  String appid;
  String name;
  String version;

  public static void destroy()
  {
    instance = null;
  }

  public static SoftwareMeta getInstance()
  {
    if (instance == null)
      instance = new SoftwareMeta();
    return instance;
  }

  public String getAppChannel()
  {
    return this.appChannel;
  }

  public String getAppId()
  {
    return this.appid;
  }

  public String getName()
  {
    return this.name;
  }

  public String getSdkName()
  {
    return "AndroidSDK";
  }

  public String getSdkVersion()
  {
    return VLSdk.VERSION;
  }

  public String getVersion()
  {
    return this.version;
  }

  public void setAppChannel(String paramString)
  {
    this.appChannel = paramString;
  }

  public void setAppId(String paramString)
  {
    this.appid = paramString;
  }

  public void setAppName(String paramString)
  {
    this.name = paramString;
  }

  public void setAppVersion(String paramString)
  {
    this.version = paramString;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.SoftwareMeta
 * JD-Core Version:    0.6.0
 */