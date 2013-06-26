package com.vlingo.core.internal.recognition.acceptedtext;

public class ContactLookupAcceptedText extends AcceptedText
{
  private String action;

  public ContactLookupAcceptedText(String paramString)
  {
    this(null, paramString);
  }

  public ContactLookupAcceptedText(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    super(paramString1, paramInt, paramString2);
    this.action = paramString3;
  }

  public ContactLookupAcceptedText(String paramString1, String paramString2)
  {
    super(paramString1);
    this.action = paramString2;
  }

  protected String getAcceptedTextXML()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<AcceptedText pt=\"contact:lookup\">");
    if (this.action != null)
    {
      localStringBuffer.append("<Tag u=\"action\">");
      localStringBuffer.append(this.action);
      localStringBuffer.append("</Tag>");
    }
    localStringBuffer.append("</AcceptedText>");
    return localStringBuffer.toString();
  }

  public String toString()
  {
    return "ContactLookupAcceptedText [" + super.toString() + ", action=" + this.action + "]";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.acceptedtext.ContactLookupAcceptedText
 * JD-Core Version:    0.6.0
 */