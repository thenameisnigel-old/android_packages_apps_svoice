package com.vlingo.sdk.internal.http;

public abstract interface HttpCallback
{
  public abstract void onCancelled(HttpRequest paramHttpRequest);

  public abstract void onFailure(HttpRequest paramHttpRequest);

  public abstract void onResponse(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse);

  public abstract boolean onTimeout(HttpRequest paramHttpRequest);

  public abstract void onWillExecuteRequest(HttpRequest paramHttpRequest);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.HttpCallback
 * JD-Core Version:    0.6.0
 */