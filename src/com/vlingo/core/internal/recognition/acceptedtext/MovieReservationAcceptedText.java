package com.vlingo.core.internal.recognition.acceptedtext;

public class MovieReservationAcceptedText extends AcceptedText
{
  private String action;
  private String text;

  public MovieReservationAcceptedText(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4)
  {
    super(paramString1, paramInt, paramString2);
    this.text = paramString3;
    this.action = paramString4;
  }

  public MovieReservationAcceptedText(String paramString1, String paramString2)
  {
    this(null, paramString1, paramString2);
  }

  public MovieReservationAcceptedText(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1);
    this.text = paramString2;
    this.action = paramString3;
  }

  protected String getAcceptedTextXML()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<AcceptedText pt=\"localsearch:def\">");
    if (this.action != null)
    {
      localStringBuffer.append("<Tag u=\"action\">");
      localStringBuffer.append(this.action);
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
    return "MovieReservationAcceptedText [" + super.toString() + ", text=" + this.text + ", action=" + this.action + "]";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.acceptedtext.MovieReservationAcceptedText
 * JD-Core Version:    0.6.0
 */