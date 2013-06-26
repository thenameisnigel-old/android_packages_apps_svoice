package com.facebook.android;

public class FacebookError extends Throwable
{
  private static final long serialVersionUID = 1L;
  private int mErrorCode = 0;
  private String mErrorType;

  public FacebookError(String paramString)
  {
    super(paramString);
  }

  public FacebookError(String paramString1, String paramString2, int paramInt)
  {
    super(paramString1);
    this.mErrorType = paramString2;
    this.mErrorCode = paramInt;
  }

  public int getErrorCode()
  {
    return this.mErrorCode;
  }

  public String getErrorType()
  {
    return this.mErrorType;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.facebook.android.FacebookError
 * JD-Core Version:    0.6.0
 */