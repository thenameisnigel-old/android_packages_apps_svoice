package com.vlingo.sdk.services.userlogging;

import com.vlingo.sdk.internal.util.XmlUtils;

public class VLErrorRecord
{
  private String mErrorId;
  private int mViewCount;

  private VLErrorRecord(Builder paramBuilder)
  {
    this.mErrorId = paramBuilder.errorId;
    this.mViewCount = paramBuilder.viewCount;
  }

  String generateXml()
  {
    return "<error msg-id=\"" + XmlUtils.xmlEncode(this.mErrorId) + "\" count=\"" + this.mViewCount + "\"/>";
  }

  public int getErrorCount()
  {
    return this.mViewCount;
  }

  public String getErrorId()
  {
    return this.mErrorId;
  }

  public static class Builder
  {
    private String errorId;
    private int viewCount;

    public Builder(String paramString)
    {
      this.errorId = paramString;
    }

    public VLErrorRecord build()
    {
      return new VLErrorRecord(this, null);
    }

    public Builder errorDisplayed()
    {
      this.viewCount = (1 + this.viewCount);
      return this;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.services.userlogging.VLErrorRecord
 * JD-Core Version:    0.6.0
 */