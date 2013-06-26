package com.tencent.tauth.http;

public final class ResponseData
{
  private byte[] responseBody;
  private int statusCode;

  public ResponseData(int paramInt, byte[] paramArrayOfByte)
  {
    this.statusCode = paramInt;
    this.responseBody = paramArrayOfByte;
  }

  public byte[] getResponseBody()
  {
    return this.responseBody;
  }

  public int getStatusCode()
  {
    return this.statusCode;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.http.ResponseData
 * JD-Core Version:    0.6.0
 */