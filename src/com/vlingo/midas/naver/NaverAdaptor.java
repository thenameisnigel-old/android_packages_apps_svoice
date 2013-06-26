package com.vlingo.midas.naver;

import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.localsearch.LocalSearchRequestListener;
import com.vlingo.core.internal.localsearch.LocalSearchServiceManager;
import com.vlingo.core.internal.naver.NaverResponseParser;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.vcs.WidgetResponseReceivedListener;

public class NaverAdaptor
  implements LocalSearchRequestListener
{
  private String content;
  private String currentRequest = "";
  private String failReason = null;
  private boolean isRequestComplete = false;
  private boolean isRequestFailed = false;
  private final LocalSearchServiceManager lsManager = new LocalSearchServiceManager();
  private NaverXMLParser naverXML;
  private NaverResponseParser responseParser;
  private VVSActionHandlerListener vvsActionHandlerListener;
  private WidgetResponseReceivedListener widgetListener = null;

  public String getContent()
  {
    return this.content;
  }

  public String getFailReason()
  {
    return this.failReason;
  }

  public NaverXMLParser getNaverXML()
  {
    return this.naverXML;
  }

  public VVSActionHandlerListener getVVSActionHandlerListener()
  {
    return this.vvsActionHandlerListener;
  }

  public WidgetResponseReceivedListener getWidgetListener()
  {
    return this.widgetListener;
  }

  public boolean isRequestComplete()
  {
    return this.isRequestComplete;
  }

  public boolean isRequestFailed()
  {
    return this.isRequestFailed;
  }

  public void onRequestComplete(boolean paramBoolean, Object paramObject)
  {
    this.isRequestComplete = true;
    if ((paramBoolean) && ((paramObject instanceof NaverResponseParser)))
    {
      this.responseParser = ((NaverResponseParser)paramObject);
      setContent(StringUtils.unescapeXml(this.responseParser.getRawResponseXML()));
      this.naverXML = new NaverXMLParser();
      this.naverXML.parse(this.responseParser.getRawResponseXML());
    }
    if (this.widgetListener != null)
      this.widgetListener.onResponseReceived();
  }

  public void onRequestFailed(String paramString)
  {
    this.isRequestComplete = true;
    this.isRequestFailed = true;
    this.failReason = paramString;
    if (this.widgetListener != null)
      this.widgetListener.onRequestFailed();
  }

  public void onRequestScheduled()
  {
    this.isRequestComplete = false;
    this.isRequestFailed = false;
    if (this.widgetListener != null)
      this.widgetListener.onRequestScheduled();
  }

  public void sendNaverRequest(String paramString, boolean paramBoolean)
  {
    this.isRequestComplete = false;
    if (paramString == null)
      paramString = "";
    if (this.currentRequest == null)
      this.currentRequest = "";
    if ((paramString.length() > 0) && ((!this.currentRequest.equals(paramString)) || (paramBoolean)))
    {
      this.currentRequest = paramString;
      this.lsManager.sendNaverRequest(paramString, this);
    }
  }

  public void setContent(String paramString)
  {
    this.content = paramString;
  }

  public void setVVSActionHandlerListener(VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    this.vvsActionHandlerListener = paramVVSActionHandlerListener;
  }

  public void setWidgetListener(WidgetResponseReceivedListener paramWidgetResponseReceivedListener)
  {
    this.widgetListener = paramWidgetResponseReceivedListener;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.naver.NaverAdaptor
 * JD-Core Version:    0.6.0
 */