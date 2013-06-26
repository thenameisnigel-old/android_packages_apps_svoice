package com.vlingo.sdk.internal.http;

public class BaseHttpCallback
  implements HttpCallback
{
  public void onCancelled(HttpRequest paramHttpRequest)
  {
  }

  public void onFailure(HttpRequest paramHttpRequest)
  {
  }

  public void onResponse(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse)
  {
  }

  public boolean onTimeout(HttpRequest paramHttpRequest)
  {
    return true;
  }

  public void onWillExecuteRequest(HttpRequest paramHttpRequest)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.BaseHttpCallback
 * JD-Core Version:    0.6.0
 */