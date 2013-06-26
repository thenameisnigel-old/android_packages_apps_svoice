package com.vlingo.sdk.internal.http.custom;

public class HttpInteraction
{
  private VHttpConnection ivCon;
  private HttpRequest ivRequest;
  private HttpResponse ivResponse;

  public HttpInteraction(VHttpConnection paramVHttpConnection)
  {
    if (paramVHttpConnection == null)
      throw new IllegalArgumentException("Connection is required");
    this.ivCon = paramVHttpConnection;
    this.ivRequest = new HttpRequest(this);
    this.ivResponse = new HttpResponse(this);
  }

  public VHttpConnection getHTTPConnection()
  {
    return this.ivCon;
  }

  public HttpRequest getRequest()
  {
    return this.ivRequest;
  }

  public HttpResponse getResponse()
  {
    return this.ivResponse;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.custom.HttpInteraction
 * JD-Core Version:    0.6.0
 */