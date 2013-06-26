package com.vlingo.sdk.internal.recognizer.sr3;

import com.vlingo.sdk.internal.core.ApplicationAdapter;
import com.vlingo.sdk.internal.http.URL;
import com.vlingo.sdk.internal.http.custom.MultiplexHttpConnection;
import com.vlingo.sdk.internal.http.custom.VHttpConnection;
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
import com.vlingo.sdk.internal.recognizer.results.SRResponseParser;
import com.vlingo.sdk.internal.settings.Settings;
import com.vlingo.sdk.internal.util.ThreadPoolExecutor;
import java.io.IOException;

public class SR3Manager extends SRManager
{
  private final ConnectionProvider connectionProvider;
  private ClientMeta ivClientMeta;
  private VHttpConnection ivCon;
  private final ThreadPoolExecutor ivExecutor = new ThreadPoolExecutor("SR3Worker");
  private volatile String ivLastGuttID;
  private int ivRequestId = 1;
  private SoftwareMeta ivSoftwareMeta;
  private final SRResponseParser responseParser;
  SRServerDetails serverDetails;
  private final TimingRepository timings;

  public SR3Manager(ConnectionProvider paramConnectionProvider, TimingRepository paramTimingRepository)
  {
    this.connectionProvider = paramConnectionProvider;
    this.timings = paramTimingRepository;
    this.responseParser = new SRResponseParser();
  }

  private ChunkingHttpConnection getChunkingConnection(SRContext paramSRContext)
    throws IOException
  {
    ChunkingHttpConnection localChunkingHttpConnection;
    if (Settings.isAsrKeepAliveEnabled())
      if ((this.ivCon != null) && (this.ivCon.isOpen()))
      {
        VHttpConnection localVHttpConnection2 = this.ivCon;
        String str2 = this.serverDetails.getASRURL().path;
        ClientMeta localClientMeta3 = this.ivClientMeta;
        SoftwareMeta localSoftwareMeta3 = this.ivSoftwareMeta;
        int k = this.ivRequestId;
        this.ivRequestId = (k + 1);
        localChunkingHttpConnection = ChunkingHttpConnection.newConnection(localVHttpConnection2, "POST", str2, localClientMeta3, localSoftwareMeta3, null, k, paramSRContext);
      }
    while (true)
    {
      return localChunkingHttpConnection;
      if (this.ivCon != null);
      try
      {
        this.ivCon.close();
        label105: this.ivCon = new MultiplexHttpConnection(this.serverDetails.getASRURL());
        this.ivCon.open();
        VHttpConnection localVHttpConnection1 = this.ivCon;
        String str1 = this.serverDetails.getASRURL().path;
        ClientMeta localClientMeta2 = this.ivClientMeta;
        SoftwareMeta localSoftwareMeta2 = this.ivSoftwareMeta;
        int j = this.ivRequestId;
        this.ivRequestId = (j + 1);
        localChunkingHttpConnection = ChunkingHttpConnection.newConnection(localVHttpConnection1, "POST", str1, localClientMeta2, localSoftwareMeta2, null, j, paramSRContext);
        continue;
        ConnectionProvider localConnectionProvider = this.connectionProvider;
        URL localURL = this.serverDetails.getASRURL();
        ClientMeta localClientMeta1 = this.ivClientMeta;
        SoftwareMeta localSoftwareMeta1 = this.ivSoftwareMeta;
        int i = this.ivRequestId;
        this.ivRequestId = (i + 1);
        localChunkingHttpConnection = ChunkingHttpConnection.newConnection(localConnectionProvider, "POST", localURL, localClientMeta1, localSoftwareMeta1, null, i, paramSRContext);
      }
      catch (Exception localException)
      {
        break label105;
      }
    }
  }

  public void destroy()
  {
    this.ivExecutor.shutdown();
  }

  public HttpConnectionAdapter getConnection(SRContext paramSRContext)
    throws IOException
  {
    if (ApplicationAdapter.getInstance().isAudioStreamingEnabled());
    ConnectionProvider localConnectionProvider;
    String str;
    ClientMeta localClientMeta;
    SoftwareMeta localSoftwareMeta;
    int i;
    for (Object localObject = getChunkingConnection(paramSRContext); ; localObject = StandardHttpConnection.newConnection(localConnectionProvider, "POST", str, localClientMeta, localSoftwareMeta, null, i, paramSRContext))
    {
      return localObject;
      localConnectionProvider = this.connectionProvider;
      str = this.serverDetails.getASRURL().url;
      localClientMeta = this.ivClientMeta;
      localSoftwareMeta = this.ivSoftwareMeta;
      i = this.ivRequestId;
      this.ivRequestId = (i + 1);
    }
  }

  public String getLastGuttID()
  {
    return this.ivLastGuttID;
  }

  public SRResponseParser getResponseParser()
  {
    return this.responseParser;
  }

  public SRServerDetails getServerDetails()
  {
    return this.serverDetails;
  }

  public void init(SRServerDetails paramSRServerDetails, ClientMeta paramClientMeta, SoftwareMeta paramSoftwareMeta)
  {
    this.ivClientMeta = paramClientMeta;
    this.ivSoftwareMeta = paramSoftwareMeta;
    setServer(paramSRServerDetails);
    this.ivExecutor.setMaxPoolSize(8);
  }

  public SRRequest newRequest(SRContext paramSRContext, SRRequestListener paramSRRequestListener)
  {
    return newRequest(paramSRContext, paramSRRequestListener, true);
  }

  public SRRequest newRequest(SRContext paramSRContext, SRRequestListener paramSRRequestListener, boolean paramBoolean)
  {
    SR3Request localSR3Request = new SR3Request(paramSRContext, this.ivClientMeta, this.ivSoftwareMeta, this, this.timings, paramBoolean);
    localSR3Request.addListener(paramSRRequestListener);
    this.ivExecutor.execute(localSR3Request);
    return localSR3Request;
  }

  public boolean readyForRecognition()
  {
    if (!this.ivExecutor.isBusy());
    for (int i = 1; ; i = 0)
      return i;
  }

  public void sendStatistics(SRStatistics paramSRStatistics)
  {
    if (isStatsEnabled())
    {
      SRStatisticsCollection localSRStatisticsCollection = new SRStatisticsCollection();
      localSRStatisticsCollection.addStatistics(paramSRStatistics);
      localSRStatisticsCollection.schedule(this.serverDetails, this.ivClientMeta, this.ivSoftwareMeta);
    }
  }

  public void sendStatsCollection(SRStatisticsCollection paramSRStatisticsCollection)
  {
    if (isAcceptedTextEnabled())
      paramSRStatisticsCollection.schedule(this.serverDetails, this.ivClientMeta, this.ivSoftwareMeta);
  }

  void setLastGuttID(String paramString)
  {
    this.ivLastGuttID = paramString;
  }

  public void setServer(SRServerDetails paramSRServerDetails)
  {
    this.serverDetails = paramSRServerDetails;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.sr3.SR3Manager
 * JD-Core Version:    0.6.0
 */