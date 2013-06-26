package com.vlingo.sdk.services.userlogging;

import com.vlingo.sdk.internal.util.XmlUtils;

public class VLHelpPageRecord
{
  public static final int MAX_LENGTH = 128;
  private String mPageId;
  private int mViewCount;

  private VLHelpPageRecord(Builder paramBuilder)
  {
    this.mPageId = paramBuilder.pageId;
    this.mViewCount = paramBuilder.viewCount;
  }

  String generateXml()
  {
    return "<help-page page-id=\"" + XmlUtils.xmlEncode(this.mPageId) + "\" count=\"" + this.mViewCount + "\"/>";
  }

  public String getPageId()
  {
    return this.mPageId;
  }

  public int getViewCount()
  {
    return this.mViewCount;
  }

  public static class Builder
  {
    private String pageId;
    private int viewCount;

    public Builder(String paramString)
    {
      this.pageId = paramString;
    }

    public VLHelpPageRecord build()
    {
      return new VLHelpPageRecord(this, null);
    }

    public Builder pageViewed()
    {
      this.viewCount = (1 + this.viewCount);
      return this;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.services.userlogging.VLHelpPageRecord
 * JD-Core Version:    0.6.0
 */