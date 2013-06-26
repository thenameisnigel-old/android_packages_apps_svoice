package com.vlingo.sdk.internal.vlservice.response;

public class ServerMessage
{
  public static final int ERROR = 3;
  public static final String ERROR_INVALID_APPID = "012";
  public static final String ERROR_INVALID_FIELDID = "013";
  public static final int STATUS = 1;
  public static final int WARNING = 2;
  private String code = "";
  private String detailCode = "";
  private String detailMessage = "";
  private String message = "";
  private int type = -1;

  public String getCode()
  {
    return this.code;
  }

  public String getDetailCode()
  {
    return this.detailCode;
  }

  public String getDetailMessage()
  {
    return this.detailMessage;
  }

  public String getFullMessage()
  {
    return this.message + ": " + this.detailMessage + "(" + this.code + ")";
  }

  public String getMessage()
  {
    return this.message;
  }

  public int getType()
  {
    return this.type;
  }

  public void setCode(String paramString)
  {
    this.code = paramString;
  }

  public void setDetailCode(String paramString)
  {
    this.detailCode = paramString;
  }

  public void setDetailMessage(String paramString)
  {
    this.detailMessage = paramString;
  }

  public void setMessage(String paramString)
  {
    this.message = paramString;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }

  public String toString()
  {
    return getFullMessage();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.vlservice.response.ServerMessage
 * JD-Core Version:    0.6.0
 */