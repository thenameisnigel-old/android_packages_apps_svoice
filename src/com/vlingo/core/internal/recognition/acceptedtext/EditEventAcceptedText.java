package com.vlingo.core.internal.recognition.acceptedtext;

public class EditEventAcceptedText extends AddEventAcceptedText
{
  private String newdate;
  private String newduration;
  private String newendtime;
  private String newinvitee;
  private String newinviteetype;
  private String newloc;
  private String newtime;
  private String newtitle;

  public EditEventAcceptedText(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, String paramString15, String paramString16)
  {
    super(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8);
    this.newtitle = paramString9;
    this.newdate = paramString10;
    this.newtime = paramString11;
    this.newendtime = paramString12;
    this.newduration = paramString13;
    this.newloc = paramString14;
    this.newinvitee = paramString15;
    this.newinviteetype = paramString16;
  }

  protected String getAcceptedTextXML()
  {
    StringBuffer localStringBuffer1 = new StringBuffer();
    localStringBuffer1.append("<AcceptedText pt=\"event:edit\">");
    StringBuffer localStringBuffer2 = getBodyElementsXML(localStringBuffer1);
    if (this.newdate != null)
    {
      localStringBuffer2.append("<Tag u=\"newdate\">");
      localStringBuffer2.append(this.newdate);
      localStringBuffer2.append("</Tag>");
    }
    if (this.newtime != null)
    {
      localStringBuffer2.append("<Tag u=\"newtime\">");
      localStringBuffer2.append(this.newtime);
      localStringBuffer2.append("</Tag>");
    }
    if (this.newtitle != null)
    {
      localStringBuffer2.append("<Tag u=\"newtitle\">");
      localStringBuffer2.append(this.newtitle);
      localStringBuffer2.append("</Tag>");
    }
    if (this.newendtime != null)
    {
      localStringBuffer2.append("<Tag u=\"newendtime\">");
      localStringBuffer2.append(this.newendtime);
      localStringBuffer2.append("</Tag>");
    }
    if (this.newloc != null)
    {
      localStringBuffer2.append("<Tag u=\"newloc\">");
      localStringBuffer2.append(this.newloc);
      localStringBuffer2.append("</Tag>");
    }
    if (this.newinvitee != null)
    {
      localStringBuffer2.append("<Tag u=\"newinvitee\">");
      localStringBuffer2.append(this.newinvitee);
      localStringBuffer2.append("</Tag>");
    }
    if (this.newinviteetype != null)
    {
      localStringBuffer2.append("<Tag u=\"newinviteetype\">");
      localStringBuffer2.append(this.newinviteetype);
      localStringBuffer2.append("</Tag>");
    }
    if (this.newduration != null)
    {
      localStringBuffer2.append("<Tag u=\"newduration\">");
      localStringBuffer2.append(this.newduration);
      localStringBuffer2.append("</Tag>");
    }
    localStringBuffer2.append("</AcceptedText>");
    return localStringBuffer2.toString();
  }

  public String toString()
  {
    return "EditEventAcceptedText [newtitle=" + this.newtitle + ", newdate=" + this.newdate + ", newtime=" + this.newtime + ", newendtime=" + this.newendtime + ", newduration=" + this.newduration + ", newloc=" + this.newloc + ", newinvitee=" + this.newinvitee + ", newinviteetype=" + this.newinviteetype + "]" + super.toString();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.acceptedtext.EditEventAcceptedText
 * JD-Core Version:    0.6.0
 */