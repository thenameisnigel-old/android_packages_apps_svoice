package com.vlingo.core.internal.dialogmanager;

import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.audio.EndpointTimeWithSpeech;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;

public class DialogFieldID
{
  private String fieldId;
  private int timeNoSpeechInMillisecond;
  private int timeWithSpeechInMillisecond;

  public DialogFieldID(FieldIds paramFieldIds, int paramInt)
  {
    this(paramFieldIds.getValue(), paramInt);
  }

  private DialogFieldID(String paramString, int paramInt)
  {
    this(paramString, paramInt, Settings.getInt("endpoint.time_withoutspeech", 5000));
  }

  private DialogFieldID(String paramString, int paramInt1, int paramInt2)
  {
    this.fieldId = paramString;
    this.timeWithSpeechInMillisecond = paramInt1;
    this.timeNoSpeechInMillisecond = paramInt2;
  }

  public static DialogFieldID buildFromString(String paramString)
  {
    DialogFieldID localDialogFieldID = VlingoAndroidCore.getFieldId(paramString);
    if (localDialogFieldID != null);
    while (true)
    {
      return localDialogFieldID;
      localDialogFieldID = buildFromString(paramString, EndpointTimeWithSpeech.DEFAULT);
    }
  }

  public static DialogFieldID buildFromString(String paramString, EndpointTimeWithSpeech paramEndpointTimeWithSpeech)
  {
    return new DialogFieldID(paramString, paramEndpointTimeWithSpeech.getEndpointTimeWithSpeechMilliseconds());
  }

  private void setFieldId(String paramString)
  {
    this.fieldId = paramString;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool;
    if (this == paramObject)
      bool = true;
    while (true)
    {
      return bool;
      if ((paramObject == null) || (getClass() != paramObject.getClass()))
      {
        bool = false;
        continue;
      }
      bool = StringUtils.isEqual(this.fieldId, ((DialogFieldID)paramObject).getFieldId());
    }
  }

  public String getFieldId()
  {
    return this.fieldId;
  }

  public int getTimeNoSpeechInMillisecond()
  {
    return this.timeNoSpeechInMillisecond;
  }

  public int getTimeWithSpeechInMilliseconds()
  {
    return this.timeWithSpeechInMillisecond;
  }

  public int hashCode()
  {
    return this.fieldId.hashCode();
  }

  public String toString()
  {
    return this.fieldId;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.DialogFieldID
 * JD-Core Version:    0.6.0
 */