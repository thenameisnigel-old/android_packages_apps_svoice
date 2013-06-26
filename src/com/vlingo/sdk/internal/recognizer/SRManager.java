package com.vlingo.sdk.internal.recognizer;

import com.vlingo.sdk.internal.net.ConnectionManager;
import com.vlingo.sdk.internal.net.ConnectionProvider;
import com.vlingo.sdk.internal.recognizer.asr.ASRManager;
import com.vlingo.sdk.internal.recognizer.sr3.SR3Manager;
import com.vlingo.sdk.internal.settings.Settings;

public abstract class SRManager
{
  public static final int DEFAULT_TIMEOUT = -1;
  public static final boolean USE_ASR = true;
  private int mConnectTimeout = -1;
  private int mReadTimeout = -1;

  public static SRManager create(ConnectionProvider paramConnectionProvider, TimingRepository paramTimingRepository)
  {
    if (Settings.getPersistentBoolean("asr.manager", true));
    for (Object localObject = new ASRManager(paramConnectionProvider, paramTimingRepository); ; localObject = new SR3Manager(paramConnectionProvider, paramTimingRepository))
      return localObject;
  }

  public static boolean isAsrManager()
  {
    return true;
  }

  public abstract void destroy();

  public int getConnectTimeout()
  {
    if (this.mConnectTimeout == -1);
    for (int i = ConnectionManager.getOptimalConnectTimeout(); ; i = this.mConnectTimeout)
    {
      this.mConnectTimeout = i;
      return i;
    }
  }

  public abstract String getLastGuttID();

  public int getReadTimeout()
  {
    if (this.mReadTimeout == -1);
    for (int i = ConnectionManager.getOptimalNetworkTimeout(); ; i = this.mReadTimeout)
    {
      this.mReadTimeout = i;
      return i;
    }
  }

  public abstract void init(SRServerDetails paramSRServerDetails, ClientMeta paramClientMeta, SoftwareMeta paramSoftwareMeta);

  protected boolean isAcceptedTextEnabled()
  {
    return Settings.getPersistentBoolean("acceptedtext.enable", true);
  }

  protected boolean isStatsEnabled()
  {
    return Settings.getPersistentBoolean("stats.enable", true);
  }

  public abstract SRRequest newRequest(SRContext paramSRContext, SRRequestListener paramSRRequestListener);

  public abstract SRRequest newRequest(SRContext paramSRContext, SRRequestListener paramSRRequestListener, boolean paramBoolean);

  public abstract boolean readyForRecognition();

  public abstract void sendStatistics(SRStatistics paramSRStatistics);

  public abstract void sendStatsCollection(SRStatisticsCollection paramSRStatisticsCollection);

  public void setConnectTimeout(int paramInt)
  {
    this.mConnectTimeout = paramInt;
  }

  public void setReadTimeout(int paramInt)
  {
    this.mReadTimeout = paramInt;
  }

  public abstract void setServer(SRServerDetails paramSRServerDetails);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.SRManager
 * JD-Core Version:    0.6.0
 */