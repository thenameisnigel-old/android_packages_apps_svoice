package com.vlingo.core.internal.recognition.acceptedtext;

public class SocialAcceptedText extends AcceptedText
{
  private String site;
  private String status;
  private String text;

  public SocialAcceptedText(String paramString1, String paramString2, String paramString3)
  {
    super(null);
    this.site = paramString1;
    this.status = paramString2;
    this.text = paramString3;
  }

  protected String getAcceptedTextXML()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<AcceptedText pt=\"social:add\">");
    if (this.status != null)
    {
      localStringBuffer.append("<Tag u=\"status\">");
      localStringBuffer.append(this.status);
      localStringBuffer.append("</Tag>");
    }
    if (this.text != null)
    {
      localStringBuffer.append("<Tag u=\"text\">");
      localStringBuffer.append(this.text);
      localStringBuffer.append("</Tag>");
    }
    if (this.site != null)
    {
      localStringBuffer.append("<Tag u=\"site\">");
      localStringBuffer.append(this.site);
      localStringBuffer.append("</Tag>");
    }
    localStringBuffer.append("</AcceptedText>");
    return localStringBuffer.toString();
  }

  public String toString()
  {
    return "SocialAcceptedText [site=" + this.site + ", status=" + this.status + ", text=" + this.text + "]" + super.toString();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.acceptedtext.SocialAcceptedText
 * JD-Core Version:    0.6.0
 */