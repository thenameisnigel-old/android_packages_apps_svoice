package com.vlingo.sdk.internal.vlservice;

import com.vlingo.sdk.internal.http.HttpCallback;
import com.vlingo.sdk.internal.http.HttpRequest;
import com.vlingo.sdk.internal.http.HttpResponse;
import com.vlingo.sdk.internal.http.HttpUtil;
import com.vlingo.sdk.internal.http.URL;
import com.vlingo.sdk.internal.util.StringUtils;
import java.util.Hashtable;

public class VLHttpServiceRequest extends HttpRequest
{
  public static final String RESPONSE_ENCODING_JSON = "application/json";
  public static final String RESPONSE_ENCODING_XML = "text/xml";
  private String mLanguage;

  public VLHttpServiceRequest(String paramString1, HttpCallback paramHttpCallback, String paramString2, byte[] paramArrayOfByte, String paramString3)
  {
    super(paramString1, paramHttpCallback, paramString2, paramArrayOfByte);
    this.mLanguage = paramString3;
  }

  public static VLHttpServiceRequest createVLRequest(String paramString1, HttpCallback paramHttpCallback, URL paramURL, String paramString2)
  {
    return new VLHttpServiceRequest(paramString1, paramHttpCallback, paramURL.url, StringUtils.convertStringToBytes(paramString2), null);
  }

  public static VLHttpServiceRequest createVLRequest(String paramString1, HttpCallback paramHttpCallback, URL paramURL, String paramString2, String paramString3)
  {
    return new VLHttpServiceRequest(paramString1, paramHttpCallback, paramURL.url, StringUtils.convertStringToBytes(paramString2), paramString3);
  }

  public HttpResponse fetchResponse()
  {
    if (this.extraHeaders == null)
      this.extraHeaders = new Hashtable();
    if (this.cookies == null)
      this.cookies = new Hashtable();
    VLServiceUtil.addStandardVlingoHttpHeaders(this.extraHeaders, this.client, this.software, this.mLanguage, null);
    this.cookies = VLServiceUtil.addVLServiceCookies(this.cookies, HttpUtil.getDomain(this.url), HttpUtil.getPath(this.url));
    HttpResponse localHttpResponse = super.fetchResponse();
    VLServiceUtil.handleResponseCookies(localHttpResponse.getCookies());
    return localHttpResponse;
  }

  public void setResponseEncoding(String paramString)
  {
    if (this.extraHeaders == null)
      this.extraHeaders = new Hashtable();
    VLServiceUtil.setProtocolHeader(this.extraHeaders, paramString);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.vlservice.VLHttpServiceRequest
 * JD-Core Version:    0.6.0
 */