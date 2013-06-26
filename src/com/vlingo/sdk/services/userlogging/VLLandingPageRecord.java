package com.vlingo.sdk.services.userlogging;

import com.vlingo.sdk.internal.util.XmlUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class VLLandingPageRecord
{
  private int actionCount;
  private int actionNoEditCount;
  private int actionUtteranceCount;
  private int alternativePhrasePickedCount;
  private int backCount;
  private int backNoEditCount;
  private int backUtteranceCount;
  private int contactChangeCount;
  private List<TextFieldRecord> fields;
  private int fromEmailChangedCount;
  private int launchCount;
  private long launchTimeTotal;
  private int noteTypeChangeCount;
  private String pageId;
  private int phoneChangeCount;
  private String reserved1;
  private String reserved2;
  private int undoCount;
  private int viewCount;

  VLLandingPageRecord(Builder paramBuilder)
  {
    this.pageId = paramBuilder.pageId;
    this.viewCount = paramBuilder.viewCount;
    this.launchTimeTotal = paramBuilder.launchTimeTotal;
    this.launchCount = paramBuilder.launchCount;
    this.backCount = paramBuilder.backCount;
    this.backNoEditCount = paramBuilder.backNoEditCount;
    this.backUtteranceCount = paramBuilder.backUtteranceCount;
    this.actionCount = paramBuilder.actionCount;
    this.actionNoEditCount = paramBuilder.actionNoEditCount;
    this.actionUtteranceCount = paramBuilder.actionUtteranceCount;
    this.fromEmailChangedCount = paramBuilder.fromEmailChangedCount;
    this.alternativePhrasePickedCount = paramBuilder.alternativePhrasePickedCount;
    this.contactChangeCount = paramBuilder.contactChangeCount;
    this.phoneChangeCount = paramBuilder.phoneChangeCount;
    this.noteTypeChangeCount = paramBuilder.noteTypeChangeCount;
    this.undoCount = paramBuilder.undoCount;
    this.reserved1 = paramBuilder.reserved1;
    this.reserved2 = paramBuilder.reserved2;
    this.fields = new LinkedList();
    this.fields.addAll(paramBuilder.fields);
  }

  String generateXml()
  {
    float f;
    if (this.launchCount == 0)
      f = 0.0F;
    StringBuilder localStringBuilder;
    while (true)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("<landing-page page-id=\"" + XmlUtils.xmlEncode(this.pageId) + "\" count=\"" + this.viewCount + "\" launch-time-avg=\"" + f + "\" back-count=\"" + this.backCount + "\" back-noedit-count=\"" + this.backNoEditCount + "\" back-utt-avg=\"" + this.backUtteranceCount + "\" action-count=\"" + this.actionCount + "\" action-noedit-count=\"" + this.actionNoEditCount + "\" action-utt-avg=\"" + this.actionUtteranceCount + "\" from-email-count=\"" + this.fromEmailChangedCount + "\" undo-count=\"" + this.undoCount + "\" alternative-count=\"" + this.alternativePhrasePickedCount + "\" contact-change-count=\"" + this.contactChangeCount + "\" phone-change-count=\"" + this.phoneChangeCount + "\" note-change-count=\"" + this.noteTypeChangeCount + "\" reserved1=\"" + this.reserved1 + "\" reserved2=\"" + this.reserved2 + "\">");
      Iterator localIterator = this.fields.iterator();
      while (localIterator.hasNext())
        localStringBuilder.append(((TextFieldRecord)localIterator.next()).generateXml());
      f = (float)this.launchTimeTotal / this.launchCount;
    }
    localStringBuilder.append("</landing-page>");
    return localStringBuilder.toString();
  }

  public String getPageId()
  {
    return this.pageId;
  }

  public static class Builder
  {
    private int actionCount;
    private int actionNoEditCount;
    private int actionUtteranceCount;
    private int alternativePhrasePickedCount;
    private int backCount;
    private int backNoEditCount;
    private int backUtteranceCount;
    private int contactChangeCount;
    private boolean fieldLoggingEnabled;
    private List<VLLandingPageRecord.TextFieldRecord> fields;
    private int fromEmailChangedCount;
    private int launchCount;
    private long launchTimeTotal;
    private int noteTypeChangeCount;
    private String pageId;
    private int phoneChangeCount;
    private String reserved1;
    private String reserved2;
    private int undoCount;
    private int viewCount;

    public Builder(String paramString, boolean paramBoolean)
    {
      this.pageId = paramString;
      this.fieldLoggingEnabled = paramBoolean;
      this.fields = new LinkedList();
    }

    private VLLandingPageRecord.TextFieldRecord getTextFieldRecord(String paramString)
    {
      Iterator localIterator = this.fields.iterator();
      VLLandingPageRecord.TextFieldRecord localTextFieldRecord;
      do
      {
        if (!localIterator.hasNext())
          break;
        localTextFieldRecord = (VLLandingPageRecord.TextFieldRecord)localIterator.next();
      }
      while (!localTextFieldRecord.getFieldId().equals(paramString));
      while (true)
      {
        return localTextFieldRecord;
        localTextFieldRecord = new VLLandingPageRecord.TextFieldRecord(paramString);
        this.fields.add(localTextFieldRecord);
      }
    }

    private boolean noTextFieldEdits(List<VLLandingPageRecord.TextFieldUsageCounts> paramList)
    {
      int j;
      if (paramList != null)
      {
        j = 0;
        if (j < paramList.size())
          if (!((VLLandingPageRecord.TextFieldUsageCounts)paramList.get(j)).isEdited());
      }
      for (int i = 0; ; i = 1)
      {
        return i;
        j++;
        break;
      }
    }

    private void updateTextFieldRecords(List<VLLandingPageRecord.TextFieldUsageCounts> paramList, boolean paramBoolean)
    {
      if ((this.fieldLoggingEnabled) && (paramList != null))
      {
        int i = 0;
        if (i < paramList.size())
        {
          VLLandingPageRecord.TextFieldUsageCounts localTextFieldUsageCounts = (VLLandingPageRecord.TextFieldUsageCounts)paramList.get(i);
          getTextFieldRecord(localTextFieldUsageCounts.getFieldID()).update(localTextFieldUsageCounts);
          if (paramBoolean)
            this.actionUtteranceCount += localTextFieldUsageCounts.getCountRecognitions();
          while (true)
          {
            i++;
            break;
            this.backUtteranceCount += localTextFieldUsageCounts.getCountRecognitions();
          }
        }
      }
    }

    public Builder actionClicked(List<VLLandingPageRecord.TextFieldUsageCounts> paramList)
    {
      updateTextFieldRecords(paramList, true);
      if (noTextFieldEdits(paramList))
        this.actionNoEditCount = (1 + this.actionNoEditCount);
      while (true)
      {
        return this;
        this.actionCount = (1 + this.actionCount);
      }
    }

    public Builder addLaunchTime(long paramLong)
    {
      this.launchTimeTotal = (paramLong + this.launchTimeTotal);
      this.launchCount = (1 + this.launchCount);
      return this;
    }

    public Builder alterPhrasePicked()
    {
      this.alternativePhrasePickedCount = (1 + this.alternativePhrasePickedCount);
      return this;
    }

    public Builder backClicked(List<VLLandingPageRecord.TextFieldUsageCounts> paramList)
    {
      updateTextFieldRecords(paramList, false);
      if (noTextFieldEdits(paramList))
        this.backNoEditCount = (1 + this.backNoEditCount);
      while (true)
      {
        return this;
        this.backCount = (1 + this.backCount);
      }
    }

    public VLLandingPageRecord build()
    {
      return new VLLandingPageRecord(this);
    }

    public Builder fromEmailChanged()
    {
      this.fromEmailChangedCount = (1 + this.fromEmailChangedCount);
      return this;
    }

    public int getFieldCount()
    {
      return this.fields.size();
    }

    public Builder incrContactChange()
    {
      this.contactChangeCount = (1 + this.contactChangeCount);
      return this;
    }

    public Builder incrNoteChanged()
    {
      this.noteTypeChangeCount = (1 + this.noteTypeChangeCount);
      return this;
    }

    public Builder incrPhoneChange()
    {
      this.phoneChangeCount = (1 + this.phoneChangeCount);
      return this;
    }

    public Builder incrUndoCount()
    {
      this.undoCount = (1 + this.undoCount);
      return this;
    }

    public Builder pageViewed()
    {
      this.viewCount = (1 + this.viewCount);
      return this;
    }

    public Builder setReserved1(String paramString)
    {
      this.reserved1 = paramString;
      return this;
    }

    public Builder setReserved2(String paramString)
    {
      this.reserved2 = paramString;
      return this;
    }
  }

  private static class TextFieldRecord
  {
    private int clearCount;
    private int deleteCount;
    private String fieldId;
    private int fixAcceptCount;
    private int fixInvokeCount;
    private int keyCount;
    private int recognitionCount;

    TextFieldRecord(String paramString)
    {
      this.fieldId = paramString;
    }

    String generateXml()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("<field field-id=\"" + XmlUtils.xmlEncode(this.fieldId) + "\" fix-invoke-count=\"" + this.fixInvokeCount + "\" fix-accept-count=\"" + this.fixAcceptCount + "\" clear-count=\"" + this.clearCount + "\" key-count=\"" + this.keyCount + "\" char-delete-count=\"" + this.deleteCount + "\" char-recs-count=\"" + this.recognitionCount + "\"/>");
      return localStringBuilder.toString();
    }

    String getFieldId()
    {
      return this.fieldId;
    }

    void update(VLLandingPageRecord.TextFieldUsageCounts paramTextFieldUsageCounts)
    {
      this.fixInvokeCount += paramTextFieldUsageCounts.getCountFixInvoke();
      this.fixAcceptCount += paramTextFieldUsageCounts.getCountFixAccept();
      this.clearCount += paramTextFieldUsageCounts.getCountClears();
      this.keyCount += paramTextFieldUsageCounts.getCountKeys();
      this.deleteCount += paramTextFieldUsageCounts.getCountDeletes();
      this.recognitionCount += paramTextFieldUsageCounts.getCountRecognitions();
    }
  }

  public static class TextFieldUsageCounts
  {
    private String fieldID;
    private HashMap<String, Integer> usageCounts;

    public TextFieldUsageCounts(String paramString, HashMap<String, Integer> paramHashMap)
    {
      this.fieldID = paramString;
      this.usageCounts = paramHashMap;
    }

    private int getCount(String paramString)
    {
      try
      {
        int j = ((Integer)this.usageCounts.get(paramString)).intValue();
        i = j;
        return i;
      }
      catch (Exception localException)
      {
        while (true)
          int i = -1;
      }
    }

    public int getCountClears()
    {
      return getCount("CLR");
    }

    public int getCountDeletes()
    {
      return getCount("CDEL") + getCount("WDEL");
    }

    public int getCountFixAccept()
    {
      return getCount("NBCOR");
    }

    public int getCountFixInvoke()
    {
      return -1;
    }

    public int getCountKeys()
    {
      return getCount("KEY") + getCount("NAV");
    }

    public int getCountRecognitions()
    {
      return getCount("CREC") + getCount("WREC");
    }

    public String getFieldID()
    {
      return this.fieldID;
    }

    public boolean isEdited()
    {
      if ((getCountKeys() > 0) || (getCountDeletes() > 0) || (getCountClears() > 0) || (getCountFixAccept() > 0) || (getCountRecognitions() > 0));
      for (int i = 1; ; i = 0)
        return i;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.services.userlogging.VLLandingPageRecord
 * JD-Core Version:    0.6.0
 */