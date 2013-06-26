package com.tencent.tauth.bean;

public class TopicRichInfo
{
  private int mRtype;
  private String mUrl2;
  private String mUrl3;
  private int mWho;

  public TopicRichInfo(int paramInt1, String paramString1, String paramString2, int paramInt2)
  {
    this.mRtype = paramInt1;
    this.mUrl2 = paramString1;
    this.mUrl3 = paramString2;
    this.mWho = paramInt2;
  }

  public int getRtype()
  {
    return this.mRtype;
  }

  public String getUrl2()
  {
    return this.mUrl2;
  }

  public String getUrl3()
  {
    return this.mUrl3;
  }

  public int getWho()
  {
    return this.mWho;
  }

  public void setRtype(int paramInt)
  {
    this.mRtype = paramInt;
  }

  public void setUrl2(String paramString)
  {
    this.mUrl2 = paramString;
  }

  public void setUrl3(String paramString)
  {
    this.mUrl3 = paramString;
  }

  public void setWho(int paramInt)
  {
    this.mWho = paramInt;
  }

  public String toString()
  {
    return "rtype: " + this.mRtype + "\nurl2: " + this.mUrl2 + "\nurl3: " + this.mUrl3 + "\nwho: " + this.mWho + "\n";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.bean.TopicRichInfo
 * JD-Core Version:    0.6.0
 */