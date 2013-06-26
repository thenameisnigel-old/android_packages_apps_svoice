package com.vlingo.core.internal.logging;

import com.vlingo.sdk.internal.recognizer.results.TaggedResults.ParseGroup;
import com.vlingo.sdk.internal.recognizer.results.TaggedResults.Tag;
import com.vlingo.sdk.recognition.VLRecognitionResult;
import java.util.Vector;

public class EventLog
{
  private String asrLogMessage;
  private String nluLogMessage;

  public EventLog(VLRecognitionResult paramVLRecognitionResult)
  {
    this.asrLogMessage = paramVLRecognitionResult.getResultString();
    buildNluLogMessages(paramVLRecognitionResult);
  }

  private void buildNluLogMessages(VLRecognitionResult paramVLRecognitionResult)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramVLRecognitionResult != null)
    {
      TaggedResults.ParseGroup localParseGroup = paramVLRecognitionResult.getParseGroup();
      if (localParseGroup != null)
      {
        localStringBuilder.append("Parse type: ");
        int i;
        if (localParseGroup.getParseType() != null)
        {
          localStringBuilder.append(localParseGroup.getParseType());
          localStringBuilder.append(", Slots:");
          Vector localVector = localParseGroup.getTags();
          if (localVector == null)
            break label200;
          i = 0;
          label72: if (i >= localVector.size())
            break label207;
          TaggedResults.Tag localTag = (TaggedResults.Tag)localVector.get(i);
          localStringBuilder.append(" Slot " + i + " : ");
          if (localTag == null)
            break label190;
          localStringBuilder.append(localTag.getName());
          localStringBuilder.append(" value : ");
          if (localTag.getRecResults() == null)
            break label180;
          localStringBuilder.append(localTag.getRecResults());
        }
        while (true)
        {
          i++;
          break label72;
          localStringBuilder.append("no parseType");
          break;
          label180: localStringBuilder.append(" no value");
          continue;
          label190: localStringBuilder.append("no slot");
        }
        label200: localStringBuilder.append("no slots");
      }
    }
    while (true)
    {
      label207: this.nluLogMessage = localStringBuilder.toString();
      return;
      localStringBuilder.append("no parseGroup");
      continue;
      localStringBuilder.append("no result");
    }
  }

  public String getAsrLogMessage()
  {
    return this.asrLogMessage;
  }

  public String getNluLogMessage()
  {
    return this.nluLogMessage;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.logging.EventLog
 * JD-Core Version:    0.6.0
 */