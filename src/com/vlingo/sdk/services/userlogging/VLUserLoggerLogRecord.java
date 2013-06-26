package com.vlingo.sdk.services.userlogging;

import com.vlingo.sdk.internal.deviceinfo.PhoneInfo;
import com.vlingo.sdk.internal.util.XmlUtils;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class VLUserLoggerLogRecord
{
  private List<VLErrorRecord> errors;
  private List<VLHelpPageRecord> helpPages;
  private List<VLLandingPageRecord> landingPages;
  private String settings = null;
  private boolean setupFinished = false;
  private boolean setupStarted = false;

  private VLUserLoggerLogRecord(Builder paramBuilder)
  {
    this.setupStarted = paramBuilder.setupStarted;
    this.setupFinished = paramBuilder.setupFinished;
    this.settings = paramBuilder.settings;
    this.helpPages = new LinkedList();
    this.helpPages.addAll(paramBuilder.helpPages);
    this.errors = new LinkedList();
    this.errors.addAll(paramBuilder.errors);
    this.landingPages = new LinkedList();
    this.landingPages.addAll(paramBuilder.landingPages);
  }

  public String getXML()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<user-log>");
    localStringBuilder.append("<user-id>" + PhoneInfo.getInstance().getDeviceID() + "</user-id>");
    localStringBuilder.append("<setup started=\"" + this.setupStarted + "\" finished=\"" + this.setupFinished + "\"/>");
    if (this.settings != null)
    {
      localStringBuilder.append("<settings values=\"");
      localStringBuilder.append(XmlUtils.xmlEncode(this.settings));
      localStringBuilder.append("\"/>");
    }
    Iterator localIterator1 = this.helpPages.iterator();
    while (localIterator1.hasNext())
      localStringBuilder.append(((VLHelpPageRecord)localIterator1.next()).generateXml());
    Iterator localIterator2 = this.errors.iterator();
    while (localIterator2.hasNext())
      localStringBuilder.append(((VLErrorRecord)localIterator2.next()).generateXml());
    localStringBuilder.append("<landing-pages>");
    Iterator localIterator3 = this.landingPages.iterator();
    while (localIterator3.hasNext())
      ((VLLandingPageRecord)localIterator3.next()).generateXml();
    localStringBuilder.append("</landing-pages>");
    localStringBuilder.append("</user-log>");
    return localStringBuilder.toString();
  }

  public static class Builder
  {
    private List<VLErrorRecord> errors = new LinkedList();
    private List<VLHelpPageRecord> helpPages = new LinkedList();
    private List<VLLandingPageRecord> landingPages = new LinkedList();
    private String settings = null;
    private boolean setupFinished = false;
    private boolean setupStarted = false;

    public Builder addErrorRecord(VLErrorRecord paramVLErrorRecord)
    {
      this.errors.add(paramVLErrorRecord);
      return this;
    }

    public Builder addHelpPageRecord(VLHelpPageRecord paramVLHelpPageRecord)
    {
      this.helpPages.add(paramVLHelpPageRecord);
      return this;
    }

    public Builder addLandingPageRecord(VLLandingPageRecord paramVLLandingPageRecord)
    {
      this.landingPages.add(paramVLLandingPageRecord);
      return this;
    }

    public VLUserLoggerLogRecord build()
    {
      return new VLUserLoggerLogRecord(this, null);
    }

    public Builder settings(String paramString)
    {
      this.settings = paramString;
      return this;
    }

    public Builder setupFinished(boolean paramBoolean)
    {
      this.setupFinished = paramBoolean;
      return this;
    }

    public Builder setupStarted(boolean paramBoolean)
    {
      this.setupStarted = paramBoolean;
      return this;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.services.userlogging.VLUserLoggerLogRecord
 * JD-Core Version:    0.6.0
 */