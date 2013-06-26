package com.vlingo.core.internal.recognition.acceptedtext;

public class AddTaskAcceptedText extends AcceptedText
{
  private String date;
  private String reminderdate;
  private String remindertime;
  private String title;

  public AddTaskAcceptedText(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(null);
    this.title = paramString1;
    this.date = paramString2;
    this.reminderdate = paramString3;
    this.remindertime = paramString4;
  }

  protected String getAcceptedTextXML()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<AcceptedText pt=\"task:add\">");
    if (this.date != null)
    {
      localStringBuffer.append("<Tag u=\"date\">");
      localStringBuffer.append(this.date);
      localStringBuffer.append("</Tag>");
    }
    if (this.remindertime != null)
    {
      localStringBuffer.append("<Tag u=\"remindertime\">");
      localStringBuffer.append(this.remindertime);
      localStringBuffer.append("</Tag>");
    }
    if (this.reminderdate != null)
    {
      localStringBuffer.append("<Tag u=\"reminderdate\">");
      localStringBuffer.append(this.reminderdate);
      localStringBuffer.append("</Tag>");
    }
    if (this.title != null)
    {
      localStringBuffer.append("<Tag u=\"title\">");
      localStringBuffer.append(this.title);
      localStringBuffer.append("</Tag>");
    }
    localStringBuffer.append("</AcceptedText>");
    return localStringBuffer.toString();
  }

  public String toString()
  {
    return "AddCalEventAcceptedText [" + super.toString() + ", title=" + this.title + ", reminderdate=" + this.reminderdate + "]";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.acceptedtext.AddTaskAcceptedText
 * JD-Core Version:    0.6.0
 */