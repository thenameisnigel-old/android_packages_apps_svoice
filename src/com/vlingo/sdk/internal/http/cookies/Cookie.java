package com.vlingo.sdk.internal.http.cookies;

public abstract interface Cookie
{
  public abstract String getDomain();

  public abstract long getExpires();

  public abstract String getName();

  public abstract String getPath();

  public abstract String getValue();

  public abstract boolean isExpired();

  public abstract boolean isMatch(String paramString1, String paramString2);

  public abstract void setDomain(String paramString);

  public abstract void setExpires(long paramLong);

  public abstract void setPath(String paramString);

  public abstract void setValue(String paramString);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.cookies.Cookie
 * JD-Core Version:    0.6.0
 */