package com.vlingo.core.internal.recognition.acceptedtext;

public class AddEventAcceptedText extends AcceptedText
{
  private String date;
  private String duration;
  private String endtime;
  private String invitee;
  private String inviteetype;
  private String loc;
  private String time;
  private String title;

  public AddEventAcceptedText(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8)
  {
    super(null);
    this.title = paramString1;
    this.date = paramString2;
    this.time = paramString3;
    this.endtime = paramString4;
    this.duration = paramString5;
    this.loc = paramString6;
    this.invitee = paramString7;
    this.inviteetype = paramString8;
  }

  protected String getAcceptedTextXML()
  {
    StringBuffer localStringBuffer1 = new StringBuffer();
    localStringBuffer1.append("<AcceptedText pt=\"event:add\">");
    StringBuffer localStringBuffer2 = getBodyElementsXML(localStringBuffer1);
    localStringBuffer2.append("</AcceptedText>");
    return localStringBuffer2.toString();
  }

  protected StringBuffer getBodyElementsXML(StringBuffer paramStringBuffer)
  {
    if (paramStringBuffer == null)
      paramStringBuffer = null;
    while (true)
    {
      return paramStringBuffer;
      if (this.date != null)
      {
        paramStringBuffer.append("<Tag u=\"date\">");
        paramStringBuffer.append(this.date);
        paramStringBuffer.append("</Tag>");
      }
      if (this.time != null)
      {
        paramStringBuffer.append("<Tag u=\"time\">");
        paramStringBuffer.append(this.time);
        paramStringBuffer.append("</Tag>");
      }
      if (this.title != null)
      {
        paramStringBuffer.append("<Tag u=\"title\">");
        paramStringBuffer.append(this.title);
        paramStringBuffer.append("</Tag>");
      }
      if (this.endtime != null)
      {
        paramStringBuffer.append("<Tag u=\"endtime\">");
        paramStringBuffer.append(this.endtime);
        paramStringBuffer.append("</Tag>");
      }
      if (this.loc != null)
      {
        paramStringBuffer.append("<Tag u=\"loc\">");
        paramStringBuffer.append(this.loc);
        paramStringBuffer.append("</Tag>");
      }
      if (this.invitee != null)
      {
        paramStringBuffer.append("<Tag u=\"invitee\">");
        paramStringBuffer.append(this.invitee);
        paramStringBuffer.append("</Tag>");
      }
      if (this.inviteetype != null)
      {
        paramStringBuffer.append("<Tag u=\"inviteetype\">");
        paramStringBuffer.append(this.inviteetype);
        paramStringBuffer.append("</Tag>");
      }
      if (this.duration == null)
        continue;
      paramStringBuffer.append("<Tag u=\"duration\">");
      paramStringBuffer.append(this.duration);
      paramStringBuffer.append("</Tag>");
    }
  }

  public String toString()
  {
    return "AddEventAcceptedText [title=" + this.title + ", date=" + this.date + ", time=" + this.time + ", endtime=" + this.endtime + ", duration=" + this.duration + ", loc=" + this.loc + ", invitee=" + this.invitee + ", inviteetype=" + this.inviteetype + "]" + super.toString();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.acceptedtext.AddEventAcceptedText
 * JD-Core Version:    0.6.0
 */