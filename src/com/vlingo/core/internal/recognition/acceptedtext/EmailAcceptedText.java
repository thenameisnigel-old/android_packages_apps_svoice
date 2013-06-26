package com.vlingo.core.internal.recognition.acceptedtext;

public class EmailAcceptedText extends AcceptedText
{
  private String contact;
  private String subject;
  private String text;

  public EmailAcceptedText(String paramString1, String paramString2, String paramString3)
  {
    super(null);
    this.text = paramString3;
    this.contact = paramString1;
    this.subject = paramString2;
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
    if (this.subject != null)
    {
      localStringBuffer.append("<Tag u=\"subj\">");
      localStringBuffer.append(this.subject);
      localStringBuffer.append("</Tag>");
    }
    localStringBuffer.append("</AcceptedText>");
    return localStringBuffer.toString();
  }

  public String toString()
  {
    return "SMSAcceptedText [" + super.toString() + ", subject = " + this.subject + ", text=" + this.text + ", contact=" + this.contact + "]";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.acceptedtext.EmailAcceptedText
 * JD-Core Version:    0.6.0
 */