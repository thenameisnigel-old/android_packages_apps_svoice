package com.vlingo.core.internal.recognition.acceptedtext;

public class EditAlarmAcceptedText extends AcceptedText
{
  private String time;

  public EditAlarmAcceptedText(String paramString)
  {
    this(null, paramString);
  }

  public EditAlarmAcceptedText(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    super(paramString1, paramInt, paramString2);
    this.time = paramString3;
  }

  public EditAlarmAcceptedText(String paramString1, String paramString2)
  {
    super(paramString1);
    this.time = paramString2;
  }

  protected String getAcceptedTextXML()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<AcceptedText pt=\"alarm:edit\">");
    if (this.time != null)
    {
      localStringBuffer.append("<Tag u=\"time\">");
      localStringBuffer.append(this.time);
      localStringBuffer.append("</Tag>");
    }
    localStringBuffer.append("</AcceptedText>");
    return localStringBuffer.toString();
  }

  public String toString()
  {
    return "ContactLookupAcceptedText [" + super.toString() + ", time=" + this.time + "]";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.acceptedtext.EditAlarmAcceptedText
 * JD-Core Version:    0.6.0
 */