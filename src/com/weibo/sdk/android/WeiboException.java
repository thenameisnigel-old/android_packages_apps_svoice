package com.weibo.sdk.android;

public class WeiboException extends Exception
{
  private static final long serialVersionUID = 475022994858770424L;
  private int statusCode = -1;

  public WeiboException()
  {
  }

  public WeiboException(int paramInt)
  {
    this.statusCode = paramInt;
  }

  public WeiboException(Exception paramException)
  {
    super(paramException);
  }

  public WeiboException(String paramString)
  {
    super(paramString);
  }

  public WeiboException(String paramString, int paramInt)
  {
    super(paramString);
    this.statusCode = paramInt;
  }

  public WeiboException(String paramString, Exception paramException)
  {
    super(paramString, paramException);
  }

  public WeiboException(String paramString, Exception paramException, int paramInt)
  {
    super(paramString, paramException);
    this.statusCode = paramInt;
  }

  public WeiboException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }

  public WeiboException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }

  public int getStatusCode()
  {
    return this.statusCode;
  }

  public void setStatusCode(int paramInt)
  {
    this.statusCode = paramInt;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.WeiboException
 * JD-Core Version:    0.6.0
 */