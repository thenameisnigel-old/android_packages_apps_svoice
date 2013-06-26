package com.vlingo.core.internal.recognition.acceptedtext;

public class AddCalEventAcceptedText extends AcceptedText
{
  private String contact;
  private String date;
  private String name;
  private String time;

  public AddCalEventAcceptedText(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(null);
    this.name = paramString1;
    this.contact = paramString2;
    this.date = paramString3;
    this.time = paramString4;
  }

  protected String getAcceptedTextXML()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<AcceptedText pt=\"cal:addevent\">");
    if (this.date != null)
    {
      localStringBuffer.append("<Tag u=\"startdate\">");
      localStringBuffer.append(this.date);
      localStringBuffer.append("</Tag>");
    }
    if (this.time != null)
    {
      localStringBuffer.append("<Tag u=\"starttime\">");
      localStringBuffer.append(this.time);
      localStringBuffer.append("</Tag>");
    }
    if (this.contact != null)
    {
      localStringBuffer.append("<Tag u=\"contact\">");
      localStringBuffer.append(this.contact);
      localStringBuffer.append("</Tag>");
    }
    if (this.name != null)
    {
      localStringBuffer.append("<Tag u=\"name\">");
      localStringBuffer.append(this.name);
      localStringBuffer.append("</Tag>");
    }
    localStringBuffer.append("</AcceptedText>");
    return localStringBuffer.toString();
  }

  public String toString()
  {
    return "AddCalEventAcceptedText [" + super.toString() + ", name=" + this.name + ", contact=" + this.contact + "]";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.acceptedtext.AddCalEventAcceptedText
 * JD-Core Version:    0.6.0
 */