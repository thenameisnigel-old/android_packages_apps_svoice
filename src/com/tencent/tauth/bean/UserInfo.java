package com.tencent.tauth.bean;

public class UserInfo
{
  private String mIcon_100;
  private String mIcon_30;
  private String mIcon_50;
  private String mNickName;

  public UserInfo(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.mNickName = paramString1;
    this.mIcon_30 = paramString2;
    this.mIcon_50 = paramString3;
    this.mIcon_100 = paramString4;
  }

  public String getIcon_100()
  {
    return this.mIcon_100;
  }

  public String getIcon_30()
  {
    return this.mIcon_30;
  }

  public String getIcon_50()
  {
    return this.mIcon_50;
  }

  public String getNickName()
  {
    return this.mNickName;
  }

  public void setIcon_100(String paramString)
  {
    this.mIcon_100 = paramString;
  }

  public void setIcon_30(String paramString)
  {
    this.mIcon_30 = paramString;
  }

  public void setIcon_50(String paramString)
  {
    this.mIcon_50 = paramString;
  }

  public void setNickName(String paramString)
  {
    this.mNickName = paramString;
  }

  public String toString()
  {
    return "nickname: " + this.mNickName + "\nicon_30: " + this.mIcon_30 + "\nicon_50: " + this.mIcon_50 + "\nicon_100: " + this.mIcon_100 + "\n";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.bean.UserInfo
 * JD-Core Version:    0.6.0
 */