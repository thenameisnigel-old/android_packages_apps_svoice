package com.vlingo.core.internal.recognition.acceptedtext;

public class SMSAcceptedText extends AcceptedText
{
  private String contact;
  private String text;

  public SMSAcceptedText(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4)
  {
    super(paramString1, paramInt, paramString2);
    this.text = paramString3;
    this.contact = paramString4;
  }

  public SMSAcceptedText(String paramString1, String paramString2)
  {
    this(null, paramString1, paramString2);
  }

  public SMSAcceptedText(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1);
    this.text = paramString2;
    this.contact = paramString3;
  }

  protected String getAcceptedTextXML()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<AcceptedText pt=\"sms:def\">");
    if (this.contact != null)
    {
      localStringBuffer.append("<Tag u=\"contact\">");
      localStringBuffer.append(this.contact);
      localStringBuffer.append("</Tag>");
    }
    if (this.text != null)
    {
      localStringBuffer.append("<Tag u=\"text\">");
      localStringBuffer.append(this.text);
      localStringBuffer.append("</Tag>");
    }
    localStringBuffer.append("</AcceptedText>");
    return localStringBuffer.toString();
  }

  public String toString()
  {
    return "SMSAcceptedText [" + super.toString() + ", text=" + this.text + ", contact=" + this.contact + "]";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.acceptedtext.SMSAcceptedText
 * JD-Core Version:    0.6.0
 */