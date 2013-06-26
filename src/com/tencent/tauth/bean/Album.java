package com.tencent.tauth.bean;

public class Album
{
  private String mAlbumid;
  private int mClassid;
  private long mCreatetime;
  private String mDesc;
  private String mName;
  private long mPicnum;
  private int mPriv;

  public Album(String paramString1, int paramInt1, long paramLong1, String paramString2, String paramString3, long paramLong2, int paramInt2)
  {
    this.mAlbumid = paramString1;
    this.mClassid = paramInt1;
    this.mCreatetime = paramLong1;
    this.mDesc = paramString2;
    this.mName = paramString3;
    this.mPicnum = paramLong2;
    this.mPriv = paramInt2;
  }

  public String getAlbumid()
  {
    return this.mAlbumid;
  }

  public int getClassid()
  {
    return this.mClassid;
  }

  public long getCreatetime()
  {
    return this.mCreatetime;
  }

  public String getDesc()
  {
    return this.mDesc;
  }

  public String getName()
  {
    return this.mName;
  }

  public long getPicnum()
  {
    return this.mPicnum;
  }

  public int getPriv()
  {
    return this.mPriv;
  }

  public void setAlbumid(String paramString)
  {
    this.mAlbumid = paramString;
  }

  public void setClassid(int paramInt)
  {
    this.mClassid = paramInt;
  }

  public void setCreatetime(long paramLong)
  {
    this.mCreatetime = paramLong;
  }

  public void setDesc(String paramString)
  {
    this.mDesc = paramString;
  }

  public void setName(String paramString)
  {
    this.mName = paramString;
  }

  public void setPicnum(long paramLong)
  {
    this.mPicnum = paramLong;
  }

  public void setPriv(int paramInt)
  {
    this.mPriv = paramInt;
  }

  public String toString()
  {
    return "albumid: " + this.mAlbumid + "\nclassid: " + this.mClassid + "\ncreatetime: " + this.mCreatetime + "\ndesc: " + this.mDesc + "\nname: " + this.mName + "\npicnum: " + this.mPicnum + "\npriv: " + this.mPriv + "\n";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.bean.Album
 * JD-Core Version:    0.6.0
 */