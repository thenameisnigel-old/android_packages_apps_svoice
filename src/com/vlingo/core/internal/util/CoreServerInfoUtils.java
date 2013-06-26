package com.vlingo.core.internal.util;

import com.vlingo.core.internal.settings.Settings;
import java.util.Random;

public class CoreServerInfoUtils
{
  private static final int DEFAULT_ROLLOUT_PERCENTAGE = 0;
  private static final String SCHEME_HTTP = "http://";
  private static final String SCHEME_HTTPS = "https://";

  public static String getAsrUri(CoreServerInfo paramCoreServerInfo)
  {
    return getUri(ServerType.ASR, paramCoreServerInfo);
  }

  public static String getHelloUri(CoreServerInfo paramCoreServerInfo)
  {
    return getUri(ServerType.HELLO, paramCoreServerInfo);
  }

  public static String getLMTTUri(CoreServerInfo paramCoreServerInfo)
  {
    return getUri(ServerType.LMTT, paramCoreServerInfo);
  }

  public static String getLogUri(CoreServerInfo paramCoreServerInfo)
  {
    return getUri(ServerType.LOG, paramCoreServerInfo);
  }

  public static String getTtsUri(CoreServerInfo paramCoreServerInfo)
  {
    return getUri(ServerType.TTS, paramCoreServerInfo);
  }

  private static String getUri(ServerType paramServerType, CoreServerInfo paramCoreServerInfo)
  {
    boolean bool = isUserInRolloutGroup();
    StringBuilder localStringBuilder = new StringBuilder();
    switch (1.$SwitchMap$com$vlingo$core$internal$util$CoreServerInfoUtils$ServerType[paramServerType.ordinal()])
    {
    default:
      return localStringBuilder.toString();
    case 1:
      if ((bool) && (Settings.getBoolean("https.asr_enabled", false)))
        localStringBuilder.append("https://");
      while (true)
      {
        localStringBuilder.append(paramCoreServerInfo.getASRHost());
        break;
        localStringBuilder.append("http://");
      }
    case 2:
      if ((bool) && (Settings.getBoolean("https.tts_enabled", false)))
        localStringBuilder.append("https://");
      while (true)
      {
        localStringBuilder.append(paramCoreServerInfo.getTTSHost());
        break;
        localStringBuilder.append("http://");
      }
    case 3:
      if ((bool) && (Settings.getBoolean("https.vcs_enabled", false)))
        localStringBuilder.append("https://");
      while (true)
      {
        localStringBuilder.append(paramCoreServerInfo.getVCSHost());
        break;
        localStringBuilder.append("http://");
      }
    case 4:
      if ((bool) && (Settings.getBoolean("https.log_enabled", false)))
        localStringBuilder.append("https://");
      while (true)
      {
        localStringBuilder.append(paramCoreServerInfo.getLogHost());
        break;
        localStringBuilder.append("http://");
      }
    case 5:
      if ((bool) && (Settings.getBoolean("https.lmtt_enabled", false)))
        localStringBuilder.append("https://");
      while (true)
      {
        localStringBuilder.append(paramCoreServerInfo.getLMTTHost());
        break;
        localStringBuilder.append("http://");
      }
    case 6:
    }
    if ((bool) && (Settings.getBoolean("https.hello_enabled", false)))
      localStringBuilder.append("https://");
    while (true)
    {
      localStringBuilder.append(paramCoreServerInfo.getHelloHost());
      break;
      localStringBuilder.append("http://");
    }
  }

  public static String getVcsUri(CoreServerInfo paramCoreServerInfo)
  {
    return getUri(ServerType.VCS, paramCoreServerInfo);
  }

  private static boolean isUserInRolloutGroup()
  {
    int i = 0;
    int j = Settings.getInt("https.rollout_groupid", -1);
    if (j < 0)
    {
      j = new Random(System.currentTimeMillis()).nextInt(100);
      Settings.setInt("https.rollout_groupid", j);
    }
    if (j < Settings.getInt("https.rollout_percentage", 0))
      i = 1;
    return i;
  }

  private static enum ServerType
  {
    static
    {
      LOG = new ServerType("LOG", 3);
      HELLO = new ServerType("HELLO", 4);
      LMTT = new ServerType("LMTT", 5);
      ServerType[] arrayOfServerType = new ServerType[6];
      arrayOfServerType[0] = ASR;
      arrayOfServerType[1] = TTS;
      arrayOfServerType[2] = VCS;
      arrayOfServerType[3] = LOG;
      arrayOfServerType[4] = HELLO;
      arrayOfServerType[5] = LMTT;
      $VALUES = arrayOfServerType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.CoreServerInfoUtils
 * JD-Core Version:    0.6.0
 */