package com.vlingo.midas.news;

import java.io.Serializable;

public class YonhapNewsContainer
  implements Serializable
{
  public static final int TYPE_MAINNEWS = 1;
  public static final int TYPE_PRIMENEWS = 2;
  public static final int TYPE_SEPERATE = 3;
  public static final int TYPE_URGENCYNEWS;
  public String NewsCategory;
  public String NewsContentText;
  public String NewsCredit;
  public Long NewsDate;
  public String NewsID;
  public byte[] NewsImageData;
  public String NewsImageUrl;
  public String NewsIndex;
  public String NewsLang;
  public String NewsLink;
  public Long NewsPublishTime;
  public String NewsRegion;
  public Long NewsTime;
  public String NewsTitle;
  public String NewsXml;
  public int RowId;
  public int UpdateState;
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.news.YonhapNewsContainer
 * JD-Core Version:    0.6.0
 */