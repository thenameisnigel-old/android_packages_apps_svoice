package com.tencent.tauth.bean;

public class OpenId
{
  private String mClientId;
  private String mOpenId;

  public OpenId(String paramString1, String paramString2)
  {
    this.mOpenId = paramString1;
    this.mClientId = paramString2;
  }

  public String getClientId()
  {
    return this.mClientId;
  }

  public String getOpenId()
  {
    return this.mOpenId;
  }

  public void setClientId(String paramString)
  {
    this.mClientId = paramString;
  }

  public void setOpenId(String paramString)
  {
    this.mOpenId = paramString;
  }

  public String toString()
  {
    return this.mOpenId;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.bean.OpenId
 * JD-Core Version:    0.6.0
 */