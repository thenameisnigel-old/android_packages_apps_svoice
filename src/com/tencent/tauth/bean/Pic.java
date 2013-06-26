package com.tencent.tauth.bean;

public class Pic
{
  private String mAlbumId;
  private int mHeight;
  private String mLloc;
  private String mSloc;
  private int mWidth;

  public Pic(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2)
  {
    this.mAlbumId = paramString1;
    this.mLloc = paramString2;
    this.mSloc = paramString3;
    this.mWidth = paramInt1;
    this.mHeight = paramInt2;
  }

  public String getAlbumId()
  {
    return this.mAlbumId;
  }

  public int getHeight()
  {
    return this.mHeight;
  }

  public String getLloc()
  {
    return this.mLloc;
  }

  public String getSloc()
  {
    return this.mSloc;
  }

  public int getWidth()
  {
    return this.mWidth;
  }

  public void setAlbumId(String paramString)
  {
    this.mAlbumId = paramString;
  }

  public void setHeight(int paramInt)
  {
    this.mHeight = paramInt;
  }

  public void setLloc(String paramString)
  {
    this.mLloc = paramString;
  }

  public void setSloc(String paramString)
  {
    this.mSloc = paramString;
  }

  public void setWidth(int paramInt)
  {
    this.mWidth = paramInt;
  }

  public String toString()
  {
    return "albumid :" + this.mAlbumId + "\nlloc: " + this.mLloc + "\nsloc: " + this.mSloc + "\nheight: " + this.mHeight + "\nwidth: " + this.mWidth + "\n";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.bean.Pic
 * JD-Core Version:    0.6.0
 */