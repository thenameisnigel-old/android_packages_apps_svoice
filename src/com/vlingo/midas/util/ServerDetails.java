package com.vlingo.midas.util;

import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.CoreServerInfo;

public class ServerDetails
  implements CoreServerInfo
{
  public static final String DEFAULT_ASR_SERVER_HOST = "samsungjuiasr.vlingo.com";
  public static final String DEFAULT_HELLO_SERVER_HOST = "samsungjuiasr.vlingo.com";
  public static final String DEFAULT_LMTT_SERVER_HOST = "samsungjuiasr.vlingo.com";
  public static final String DEFAULT_LOG_SERVER_HOST = "samsungjuiasr.vlingo.com";
  public static final String DEFAULT_TTS_SERVER_HOST = "samsungjuitts.vlingo.com";
  public static final String DEFAULT_VCS_SERVER_HOST = "samsungjuilocalsearch.vlingo.com";
  private static ServerDetails smInstance;

  public static ServerDetails getInstance()
  {
    if (smInstance == null)
      smInstance = new ServerDetails();
    return smInstance;
  }

  public String getASRHost()
  {
    return Settings.getString("SERVER_NAME", "samsungjuiasr.vlingo.com");
  }

  public String getHelloHost()
  {
    return Settings.getString("HELLO_HOST_NAME", "samsungjuiasr.vlingo.com");
  }

  public String getLMTTHost()
  {
    return Settings.getString("LMTT_HOST_NAME", "samsungjuiasr.vlingo.com");
  }

  public String getLogHost()
  {
    return Settings.getString("EVENTLOG_HOST_NAME", "samsungjuiasr.vlingo.com");
  }

  public String getTTSHost()
  {
    return null;
  }

  public String getVCSHost()
  {
    return Settings.getString("SERVICES_HOST_NAME", "samsungjuilocalsearch.vlingo.com");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.ServerDetails
 * JD-Core Version:    0.6.0
 */