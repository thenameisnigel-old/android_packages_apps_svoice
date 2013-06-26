package com.vlingo.core.internal.localsearch;

import android.util.Pair;
import com.vlingo.core.internal.location.LocationUtils;
import com.vlingo.core.internal.naver.NaverResponseParser;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.weather.WeatherElement;
import com.vlingo.core.internal.weather.WeatherResponseParser;
import com.vlingo.sdk.internal.http.HttpCallback;
import com.vlingo.sdk.internal.http.HttpRequest;
import com.vlingo.sdk.internal.http.HttpResponse;
import com.vlingo.sdk.internal.http.URL;
import com.vlingo.sdk.internal.vlservice.VLHttpServiceRequest;
import java.io.ByteArrayInputStream;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LocalSearchServiceManager
{
  static final int AUDIT_LOG_MAX_RETRYS = 10;
  public static final String INFO_ACTIVITY_AUTO_DIALED_CALL = "UserAutoDialCall";
  public static final String INFO_ACTIVITY_CLICKED_DETAILS = "UserClickedDetails";
  public static final String INFO_ACTIVITY_CLICKED_LINK = "UserClickedLink";
  public static final String INFO_ACTIVITY_CLICKED_NAVIGATE = "UserClickedNavigate";
  public static final String INFO_ACTIVITY_CLICKED_ON_MAP = "UserClickedOnMap";
  public static final String INFO_ACTIVITY_CLICKED_RESERVE = "UserClickedReserve";
  public static final String INFO_ACTIVITY_CLICKED_TO_CALL_BRIEF = "UserClickedToCallBrief";
  public static final String INFO_ACTIVITY_CLICKED_TO_CALL_DETAIL = "UserClickedToCallDetail";
  static final int INFO_ACTIVITY_MAX_RETRYS = 10;
  static final int INFO_ACTIVITY_RETRY_DELAY_MS = 20000;
  private static int maxSponListings;
  private static URL sm_vcsUrl;
  HttpRequest m_currentRequest = null;
  SimpleDateFormat m_dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
  LocalSearchRequestListener m_requestListener;

  public LocalSearchServiceManager()
  {
    maxSponListings = Settings.getInt("localsearch.max_spon_listing", 2);
  }

  public static void setVcsUri(String paramString)
  {
    sm_vcsUrl = new URL(paramString + "/localsearch/localsearch");
  }

  void addActionTime(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append("<ActionTime>");
    paramStringBuilder.append(this.m_dateFormat.format(new Date()));
    paramStringBuilder.append("</ActionTime>");
  }

  void addLocation(StringBuilder paramStringBuilder)
  {
    String str1 = LocationUtils.getCellTowerInfo();
    String str2 = getStringValue(str1, "Lat=");
    String str3 = getStringValue(str1, "Long=");
    if ((str2 != null) && (str3 != null))
    {
      paramStringBuilder.append("<Lat>");
      paramStringBuilder.append(str2);
      paramStringBuilder.append("</Lat><Lon>");
      paramStringBuilder.append(str3);
      paramStringBuilder.append("</Lon>");
    }
  }

  void cancelCurrentRequest()
  {
    if (this.m_currentRequest != null)
    {
      this.m_currentRequest.cancel();
      this.m_currentRequest = null;
    }
  }

  String getStringValue(String paramString1, String paramString2)
  {
    String str = null;
    int i = paramString2.length();
    int j = paramString1.indexOf(paramString2);
    if (j < 0);
    while (true)
    {
      return str;
      int k = j + i;
      int m = paramString1.indexOf(';', k);
      if (m < 0)
        continue;
      str = paramString1.substring(k, m);
    }
  }

  public void sendAuditLogRequest(String paramString1, String paramString2, String paramString3)
  {
    sendAuditLogRequest(paramString1, paramString2, paramString3, 10);
  }

  public void sendAuditLogRequest(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder(200);
    localStringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
    localStringBuilder.append("<");
    localStringBuilder.append(paramString1);
    localStringBuilder.append(">");
    localStringBuilder.append("<TrackingID>");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("</TrackingID>");
    localStringBuilder.append("<Action>");
    localStringBuilder.append(paramString3);
    localStringBuilder.append("</Action> ");
    addActionTime(localStringBuilder);
    localStringBuilder.append("</");
    localStringBuilder.append(paramString1);
    localStringBuilder.append(">");
    VLHttpServiceRequest.createVLRequest("LocalSearch-AuditLogHandler", new AuditLogHTTPHandler(paramString1, paramString2, paramString3, paramInt), sm_vcsUrl, localStringBuilder.toString()).start();
  }

  public void sendChineseMoreDetailsRequest(LocalSearchListing paramLocalSearchListing, LocalSearchRequestListener paramLocalSearchRequestListener)
  {
    monitorenter;
    try
    {
      cancelCurrentRequest();
      try
      {
        String str = LocalSearchChineseURLMaker.getUrlRecentReviewsForBusiness(paramLocalSearchListing.getListingID());
        this.m_currentRequest = HttpRequest.createRequest("ChineseLocalSearch-Details", new LocalSearchHTTPHandler(paramLocalSearchListing, paramLocalSearchRequestListener, new LocalSearchChineseResponseParser()), str);
        this.m_currentRequest.setMethod("GET");
        this.m_currentRequest.setTimeout(10000);
        this.m_currentRequest.start();
        monitorexit;
        return;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        while (true)
          paramLocalSearchRequestListener.onRequestFailed(localNoSuchAlgorithmException.getMessage());
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void sendChineseSearchRequest(String paramString1, String paramString2, String paramString3, LocalSearchRequestListener paramLocalSearchRequestListener)
  {
    monitorenter;
    try
    {
      cancelCurrentRequest();
      try
      {
        String str = LocalSearchChineseURLMaker.getBusinessURL(paramString2, paramString3, paramString1);
        this.m_currentRequest = HttpRequest.createRequest("ChineseLocalSearch-Search", new LocalSearchHTTPHandler(paramLocalSearchRequestListener, new LocalSearchChineseResponseParser()), str);
        this.m_currentRequest.setMethod("GET");
        this.m_currentRequest.setTimeout(10000);
        this.m_currentRequest.start();
        monitorexit;
        return;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        while (true)
          paramLocalSearchRequestListener.onRequestFailed(localNoSuchAlgorithmException.getMessage());
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void sendInfoActivityRequest(LocalSearchListing paramLocalSearchListing, String paramString)
  {
    sendInfoActivityRequest(paramLocalSearchListing, paramString, 10);
  }

  public void sendInfoActivityRequest(LocalSearchListing paramLocalSearchListing, String paramString, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder(200);
    localStringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
    localStringBuilder.append("<InfoActivityRequest><ListingID>");
    localStringBuilder.append(paramLocalSearchListing.getListingID());
    localStringBuilder.append("</ListingID>");
    String str = paramLocalSearchListing.getPhoneNumber();
    if ((str != null) && (str.length() > 0))
    {
      localStringBuilder.append("<PhoneNumber>");
      localStringBuilder.append(str);
      localStringBuilder.append("</PhoneNumber>");
    }
    localStringBuilder.append("<Action>");
    localStringBuilder.append(paramString);
    localStringBuilder.append("</Action> ");
    addActionTime(localStringBuilder);
    localStringBuilder.append("</InfoActivityRequest>");
    VLHttpServiceRequest.createVLRequest("LocalSearch-InfoActivity", new InfoActivityHTTPHandler(paramLocalSearchListing, paramString, paramInt), sm_vcsUrl, localStringBuilder.toString()).start();
  }

  public void sendMoreDetailsRequest(LocalSearchListing paramLocalSearchListing, LocalSearchRequestListener paramLocalSearchRequestListener)
  {
    monitorenter;
    try
    {
      cancelCurrentRequest();
      StringBuilder localStringBuilder = new StringBuilder(200);
      localStringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      localStringBuilder.append("<ListingDetailRequest><ListingID>");
      localStringBuilder.append(paramLocalSearchListing.getListingID());
      localStringBuilder.append("</ListingID>");
      addActionTime(localStringBuilder);
      localStringBuilder.append("</ListingDetailRequest>");
      this.m_currentRequest = VLHttpServiceRequest.createVLRequest("LocalSearch-Details", new LocalSearchHTTPHandler(paramLocalSearchListing, paramLocalSearchRequestListener, new LocalSearchResponseParser()), sm_vcsUrl, localStringBuilder.toString());
      updateLocalSearchRequestTimeout(this.m_currentRequest);
      this.m_currentRequest.start();
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

  public void sendNaverRequest(String paramString, LocalSearchRequestListener paramLocalSearchRequestListener)
  {
    monitorenter;
    try
    {
      cancelCurrentRequest();
      StringBuilder localStringBuilder = new StringBuilder(200);
      localStringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      localStringBuilder.append("<NaverPassThroughRequest Query=\"");
      localStringBuilder.append(paramString);
      localStringBuilder.append("\"");
      localStringBuilder.append("/>");
      this.m_currentRequest = VLHttpServiceRequest.createVLRequest("NaverRequest-Request", new NaverHTTPHandler(paramLocalSearchRequestListener), sm_vcsUrl, localStringBuilder.toString());
      this.m_currentRequest.start();
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

  public void sendRequest(String paramString1, String paramString2, LocalSearchRequestListener paramLocalSearchRequestListener)
  {
    monitorenter;
    try
    {
      cancelCurrentRequest();
      this.m_currentRequest = HttpRequest.createRequest(paramString1, new HttpCallbackHandler(paramLocalSearchRequestListener), paramString2);
      this.m_currentRequest.start();
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

  public void sendSearchRequest(String paramString1, String paramString2, LocalSearchRequestListener paramLocalSearchRequestListener)
  {
    monitorenter;
    try
    {
      cancelCurrentRequest();
      StringBuilder localStringBuilder = new StringBuilder(200);
      localStringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      localStringBuilder.append("<LocalSearchRequest><Query>");
      localStringBuilder.append(paramString1);
      localStringBuilder.append("</Query>");
      if (!StringUtils.isNullOrWhiteSpace(paramString2))
      {
        localStringBuilder.append("<SpokenLocation>");
        localStringBuilder.append(paramString2);
        localStringBuilder.append("</SpokenLocation>");
      }
      localStringBuilder.append("<MaxListings>20</MaxListings><MaxSponListings>").append(maxSponListings).append("</MaxSponListings></LocalSearchRequest>");
      this.m_currentRequest = VLHttpServiceRequest.createVLRequest("LocalSearch-Search", new LocalSearchHTTPHandler(paramLocalSearchRequestListener, new LocalSearchResponseParser()), sm_vcsUrl, localStringBuilder.toString());
      updateLocalSearchRequestTimeout(this.m_currentRequest);
      this.m_currentRequest.start();
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

  public void sendWeatherRequest(String paramString, LocalSearchRequestListener paramLocalSearchRequestListener)
  {
    monitorenter;
    try
    {
      sendWeatherRequest(paramString, "1", paramLocalSearchRequestListener, 1);
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

  public void sendWeatherRequest(String paramString1, String paramString2, LocalSearchRequestListener paramLocalSearchRequestListener, int paramInt)
  {
    monitorenter;
    try
    {
      cancelCurrentRequest();
      StringBuilder localStringBuilder = new StringBuilder(200);
      localStringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      localStringBuilder.append("<WeatherRequest");
      if (!StringUtils.isNullOrWhiteSpace(paramString1))
      {
        localStringBuilder.append(" Query=\"");
        localStringBuilder.append(paramString1);
        localStringBuilder.append("\"");
      }
      if (paramString2 != null)
      {
        localStringBuilder.append(" ForecastDays=\"");
        localStringBuilder.append(paramString2);
        localStringBuilder.append("\"");
      }
      if (paramInt > 1)
      {
        localStringBuilder.append(" Version=\"");
        localStringBuilder.append(paramInt);
        localStringBuilder.append("\"");
      }
      localStringBuilder.append("/>");
      this.m_currentRequest = VLHttpServiceRequest.createVLRequest("WeatherRequest-Request", new WeatherHTTPHandler(paramLocalSearchRequestListener), sm_vcsUrl, localStringBuilder.toString());
      this.m_currentRequest.start();
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

  protected void updateLocalSearchRequestTimeout(HttpRequest paramHttpRequest)
  {
    int i = Settings.getInt("vcs.timeout.ms", -1);
    if (i > 0)
      paramHttpRequest.setTimeout(i);
  }

  private class AuditLogHTTPHandler
    implements HttpCallback
  {
    String action;
    String requestType;
    int retryNum;
    String trackingId;

    public AuditLogHTTPHandler(String paramString1, String paramString2, String paramInt, int arg5)
    {
      this.requestType = paramString1;
      this.trackingId = paramString2;
      this.action = paramInt;
      int i;
      this.retryNum = i;
    }

    public void onCancelled(HttpRequest paramHttpRequest)
    {
    }

    public void onFailure(HttpRequest paramHttpRequest)
    {
    }

    public void onResponse(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse)
    {
      int i = 0;
      if ((paramHttpResponse.responseCode == 200) && (paramHttpResponse.getDataAsString().indexOf("Status=\"OK\"") != -1))
        i = 1;
      if ((i == 0) && (this.retryNum > 0))
      {
        this.retryNum = (-1 + this.retryNum);
        LocalSearchServiceManager.this.sendAuditLogRequest(this.requestType, this.trackingId, this.action, this.retryNum);
      }
    }

    public boolean onTimeout(HttpRequest paramHttpRequest)
    {
      return false;
    }

    public void onWillExecuteRequest(HttpRequest paramHttpRequest)
    {
    }
  }

  private class HttpCallbackHandler
    implements HttpCallback
  {
    LocalSearchRequestListener m_listener = null;
    private boolean processingDone;

    HttpCallbackHandler(LocalSearchRequestListener arg2)
    {
      Object localObject;
      this.m_listener = localObject;
    }

    public void onCancelled(HttpRequest paramHttpRequest)
    {
      this.m_listener.onRequestFailed("Cancelled");
      this.processingDone = true;
    }

    public void onFailure(HttpRequest paramHttpRequest)
    {
      this.m_listener.onRequestFailed("Failure");
      this.processingDone = true;
    }

    public void onResponse(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse)
    {
      if (paramHttpResponse.responseCode == 200)
        if (!StringUtils.isNullOrWhiteSpace(paramHttpResponse.getDataAsString()))
          this.m_listener.onRequestComplete(true, paramHttpResponse.getDataAsString());
      while (true)
      {
        return;
        this.m_listener.onRequestFailed("Response is empty");
        continue;
        if (this.processingDone)
          continue;
        this.m_listener.onRequestFailed("responseCode=" + paramHttpResponse.responseCode);
      }
    }

    public boolean onTimeout(HttpRequest paramHttpRequest)
    {
      this.m_listener.onRequestFailed("Timeout");
      this.processingDone = true;
      return false;
    }

    public void onWillExecuteRequest(HttpRequest paramHttpRequest)
    {
      this.m_listener.onRequestScheduled();
    }
  }

  private class InfoActivityHTTPHandler
    implements HttpCallback
  {
    String m_actionType;
    LocalSearchListing m_bi;
    int m_retryNum;

    public InfoActivityHTTPHandler(LocalSearchListing paramString, String paramInt, int arg4)
    {
      this.m_bi = paramString;
      this.m_actionType = paramInt;
      int i;
      this.m_retryNum = i;
    }

    public void onCancelled(HttpRequest paramHttpRequest)
    {
    }

    public void onFailure(HttpRequest paramHttpRequest)
    {
    }

    public void onResponse(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse)
    {
      int i = 0;
      if ((paramHttpResponse.responseCode == 200) && (paramHttpResponse.getDataAsString().indexOf("<InfoActivityResponse Status=\"OK\"/>") != -1))
        i = 1;
      if ((i == 0) && (this.m_retryNum > 0))
      {
        this.m_retryNum = (-1 + this.m_retryNum);
        LocalSearchServiceManager.this.sendInfoActivityRequest(this.m_bi, this.m_actionType, this.m_retryNum);
      }
    }

    public boolean onTimeout(HttpRequest paramHttpRequest)
    {
      return false;
    }

    public void onWillExecuteRequest(HttpRequest paramHttpRequest)
    {
    }
  }

  private class LocalSearchHTTPHandler
    implements HttpCallback
  {
    LocalSearchListing m_currentBusinessItem = null;
    LocalSearchRequestListener m_listener = null;
    LocalSearchParser m_responseParser;
    private boolean processingDone;

    LocalSearchHTTPHandler(LocalSearchListing paramLocalSearchRequestListener, LocalSearchRequestListener paramLocalSearchParser, LocalSearchParser arg4)
    {
      this.m_listener = paramLocalSearchParser;
      this.m_currentBusinessItem = paramLocalSearchRequestListener;
      Object localObject;
      this.m_responseParser = localObject;
    }

    LocalSearchHTTPHandler(LocalSearchRequestListener paramLocalSearchParser, LocalSearchParser arg3)
    {
      this.m_listener = paramLocalSearchParser;
      Object localObject;
      this.m_responseParser = localObject;
    }

    public void onCancelled(HttpRequest paramHttpRequest)
    {
      monitorenter;
      try
      {
        boolean bool = this.processingDone;
        if (bool);
        while (true)
        {
          return;
          this.m_listener.onRequestFailed("Cancelled");
          this.processingDone = true;
        }
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }

    public void onFailure(HttpRequest paramHttpRequest)
    {
      monitorenter;
      try
      {
        boolean bool = this.processingDone;
        if (bool);
        while (true)
        {
          return;
          this.m_listener.onRequestFailed("Failure");
          this.processingDone = true;
        }
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }

    public void onResponse(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse)
    {
      monitorenter;
      while (true)
      {
        try
        {
          boolean bool1 = this.processingDone;
          if (bool1)
            return;
          if (paramHttpResponse.responseCode != 200)
            break label106;
          Pair localPair = this.m_responseParser.parseLocalSearchResponse(paramHttpResponse, this.m_currentBusinessItem);
          boolean bool2 = false;
          if ((localPair == null) || (localPair.second == null))
            continue;
          bool2 = true;
          if (bool2)
          {
            this.m_listener.onRequestComplete(bool2, localPair);
            this.processingDone = true;
            continue;
          }
        }
        finally
        {
          monitorexit;
        }
        this.m_listener.onRequestFailed("no results");
        continue;
        label106: if (this.processingDone)
          continue;
        this.m_listener.onRequestFailed("responseCode=" + paramHttpResponse.responseCode);
      }
    }

    public boolean onTimeout(HttpRequest paramHttpRequest)
    {
      monitorenter;
      try
      {
        boolean bool = this.processingDone;
        if (bool);
        while (true)
        {
          return false;
          this.m_listener.onRequestFailed("Timeout");
          this.processingDone = true;
        }
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }

    public void onWillExecuteRequest(HttpRequest paramHttpRequest)
    {
      this.m_listener.onRequestScheduled();
    }
  }

  private class NaverHTTPHandler
    implements HttpCallback
  {
    LocalSearchRequestListener m_listener = null;

    NaverHTTPHandler(LocalSearchRequestListener arg2)
    {
      Object localObject;
      this.m_listener = localObject;
    }

    public void onCancelled(HttpRequest paramHttpRequest)
    {
      this.m_listener.onRequestFailed("Cancelled");
    }

    public void onFailure(HttpRequest paramHttpRequest)
    {
      this.m_listener.onRequestFailed("Failure");
    }

    public void onResponse(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse)
    {
      boolean bool;
      if (paramHttpResponse.responseCode == 200)
      {
        NaverResponseParser localNaverResponseParser = new NaverResponseParser();
        localNaverResponseParser.parse(paramHttpResponse.getDataAsString());
        if ((localNaverResponseParser != null) && (localNaverResponseParser.getError() == null))
        {
          bool = true;
          this.m_listener.onRequestComplete(bool, localNaverResponseParser);
        }
      }
      while (true)
      {
        return;
        bool = false;
        break;
        this.m_listener.onRequestFailed("responseCode=" + paramHttpResponse.responseCode);
      }
    }

    public boolean onTimeout(HttpRequest paramHttpRequest)
    {
      this.m_listener.onRequestFailed("Timeout");
      return false;
    }

    public void onWillExecuteRequest(HttpRequest paramHttpRequest)
    {
      this.m_listener.onRequestScheduled();
    }
  }

  private class WeatherHTTPHandler
    implements HttpCallback
  {
    LocalSearchRequestListener m_listener = null;

    WeatherHTTPHandler(LocalSearchRequestListener arg2)
    {
      Object localObject;
      this.m_listener = localObject;
    }

    public void onCancelled(HttpRequest paramHttpRequest)
    {
      this.m_listener.onRequestFailed("Cancelled");
    }

    public void onFailure(HttpRequest paramHttpRequest)
    {
      this.m_listener.onRequestFailed("Failure");
    }

    public void onResponse(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse)
    {
      boolean bool;
      if (paramHttpResponse.responseCode == 200)
      {
        WeatherElement localWeatherElement = WeatherResponseParser.parse(new ByteArrayInputStream(paramHttpResponse.getDataAsBytes()));
        if ((localWeatherElement != null) && (localWeatherElement.getErrorMessage() == null))
        {
          bool = true;
          this.m_listener.onRequestComplete(bool, localWeatherElement);
        }
      }
      while (true)
      {
        return;
        bool = false;
        break;
        this.m_listener.onRequestFailed("responseCode=" + paramHttpResponse.responseCode);
      }
    }

    public boolean onTimeout(HttpRequest paramHttpRequest)
    {
      this.m_listener.onRequestFailed("Timeout");
      return false;
    }

    public void onWillExecuteRequest(HttpRequest paramHttpRequest)
    {
      this.m_listener.onRequestScheduled();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.localsearch.LocalSearchServiceManager
 * JD-Core Version:    0.6.0
 */