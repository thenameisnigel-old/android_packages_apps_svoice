package com.vlingo.sdk.internal.recognizer.asr;

import com.vlingo.sdk.internal.net.ConnectionProvider;
import com.vlingo.sdk.internal.recognizer.ClientMeta;
import com.vlingo.sdk.internal.recognizer.SRContext;
import com.vlingo.sdk.internal.recognizer.SRManager;
import com.vlingo.sdk.internal.recognizer.SRRequest;
import com.vlingo.sdk.internal.recognizer.SRRequestListener;
import com.vlingo.sdk.internal.recognizer.SRServerDetails;
import com.vlingo.sdk.internal.recognizer.SRStatistics;
import com.vlingo.sdk.internal.recognizer.SRStatisticsCollection;
import com.vlingo.sdk.internal.recognizer.SoftwareMeta;
import com.vlingo.sdk.internal.recognizer.TimingRepository;
import java.io.IOException;
import java.net.HttpURLConnection;

public class ASRManager extends SRManager
{
  private ClientMeta mClientMeta;
  private volatile String mLastGuttId;
  private SRServerDetails mServerDetails;
  private SoftwareMeta mSoftwareMeta;
  private final TimingRepository mTimingRepository;

  public ASRManager(ConnectionProvider paramConnectionProvider, TimingRepository paramTimingRepository)
  {
    log("ctor");
    this.mTimingRepository = paramTimingRepository;
  }

  private void log(String paramString)
  {
  }

  public void destroy()
  {
    log("destroy()");
  }

  public HttpURLConnection getConnection(SRContext paramSRContext)
    throws IOException
  {
    log("getConnection()");
    return (HttpURLConnection)new java.net.URL(this.mServerDetails.getASRURL().url).openConnection();
  }

  public String getLastGuttID()
  {
    return this.mLastGuttId;
  }

  public SRServerDetails getServerDetails()
  {
    return this.mServerDetails;
  }

  public void init(SRServerDetails paramSRServerDetails, ClientMeta paramClientMeta, SoftwareMeta paramSoftwareMeta)
  {
    log("init()");
    this.mClientMeta = paramClientMeta;
    this.mSoftwareMeta = paramSoftwareMeta;
    setServer(paramSRServerDetails);
  }

  public SRRequest newRequest(SRContext paramSRContext, SRRequestListener paramSRRequestListener)
  {
    return newRequest(paramSRContext, paramSRRequestListener, true);
  }

  public SRRequest newRequest(SRContext paramSRContext, SRRequestListener paramSRRequestListener, boolean paramBoolean)
  {
    log("newRequest()");
    ASRRequest localASRRequest = new ASRRequest(paramSRContext, this.mClientMeta, this.mSoftwareMeta, this, this.mTimingRepository, paramBoolean);
    localASRRequest.setConnectTimeout(getConnectTimeout());
    localASRRequest.setReadTimeout(getReadTimeout());
    localASRRequest.addListener(paramSRRequestListener);
    localASRRequest.start();
    return localASRRequest;
  }

  public boolean readyForRecognition()
  {
    return true;
  }

  public void sendStatistics(SRStatistics paramSRStatistics)
  {
    if (isStatsEnabled())
    {
      log("sendStatistics(): queuing stats");
      SRStatisticsCollection localSRStatisticsCollection = new SRStatisticsCollection();
      localSRStatisticsCollection.addStatistics(paramSRStatistics);
      localSRStatisticsCollection.schedule(this.mServerDetails, this.mClientMeta, this.mSoftwareMeta);
    }
  }

  public void sendStatsCollection(SRStatisticsCollection paramSRStatisticsCollection)
  {
    if (isAcceptedTextEnabled())
    {
      log("sendStatsCollection(): queueing stats collection");
      paramSRStatisticsCollection.schedule(this.mServerDetails, this.mClientMeta, this.mSoftwareMeta);
    }
  }

  void setLastGuttID(String paramString)
  {
    this.mLastGuttId = paramString;
  }

  public void setServer(SRServerDetails paramSRServerDetails)
  {
    log("setServer()");
    this.mServerDetails = paramSRServerDetails;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.asr.ASRManager
 * JD-Core Version:    0.6.0
 */