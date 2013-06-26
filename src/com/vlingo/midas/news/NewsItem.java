package com.vlingo.midas.news;

import flipboard.api.FlipboardItem;

public class NewsItem
{
  public static final int NEWS_FROM_FLIPBOARD = 1;
  public static final int NEWS_FROM_YONHAP = 2;
  private int NewsCP = 1;
  private String Text = null;
  private long TimeCreated = 0L;
  private String Title = null;
  String mUniqueId;

  public NewsItem(YonhapNewsContainer paramYonhapNewsContainer)
  {
    this.TimeCreated = paramYonhapNewsContainer.NewsPublishTime.longValue();
    this.mUniqueId = paramYonhapNewsContainer.NewsID;
    this.Text = paramYonhapNewsContainer.NewsContentText;
    this.Title = paramYonhapNewsContainer.NewsTitle;
    this.NewsCP = 2;
  }

  public NewsItem(FlipboardItem paramFlipboardItem)
  {
    this.mUniqueId = paramFlipboardItem.getPageKey();
    this.TimeCreated = (1000L * paramFlipboardItem.getTimeCreated());
    this.Text = paramFlipboardItem.getText();
    this.Title = paramFlipboardItem.getTitle();
    this.NewsCP = 1;
  }

  public int getNewsCP()
  {
    return this.NewsCP;
  }

  public String getText()
  {
    return this.Text;
  }

  public long getTimeCreated()
  {
    return this.TimeCreated;
  }

  public String getTitle()
  {
    return this.Title;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.news.NewsItem
 * JD-Core Version:    0.6.0
 */