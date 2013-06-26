package com.vlingo.core.internal.recognition.acceptedtext;

public class BaseAcceptedText extends AcceptedText
{
  private String acceptedType;
  private String tag;
  private String text;

  public BaseAcceptedText(String paramString1, int paramInt, String paramString2, String paramString3, AcceptedText.TextType paramTextType)
  {
    super(paramString1, paramInt, paramString2);
    this.text = paramString3;
    switch (1.$SwitchMap$com$vlingo$core$internal$recognition$acceptedtext$AcceptedText$TextType[paramTextType.ordinal()])
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      return;
      this.acceptedType = "memo:def";
      this.tag = "text";
      continue;
      this.acceptedType = "dial:def";
      this.tag = "contact";
    }
  }

  public BaseAcceptedText(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    super(paramString1, paramInt, paramString2);
    this.text = paramString3;
    this.acceptedType = paramString4;
    this.tag = paramString5;
  }

  public BaseAcceptedText(String paramString, AcceptedText.TextType paramTextType)
  {
    this(null, -1, null, paramString, paramTextType);
  }

  public BaseAcceptedText(String paramString1, String paramString2, String paramString3)
  {
    this(null, paramString1, paramString2, paramString3);
  }

  public BaseAcceptedText(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(paramString1);
    this.text = paramString2;
    this.acceptedType = paramString3;
    this.tag = paramString4;
  }

  protected String getAcceptedTextXML()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<AcceptedText pt=\"");
    localStringBuffer.append(this.acceptedType);
    localStringBuffer.append("\">");
    localStringBuffer.append("<Tag u=\"");
    localStringBuffer.append(this.tag);
    localStringBuffer.append("\">");
    localStringBuffer.append(this.text);
    localStringBuffer.append("</Tag>");
    localStringBuffer.append("</AcceptedText>");
    return localStringBuffer.toString();
  }

  public String toString()
  {
    return "AcceptedText [ " + super.toString() + " , text=" + this.text + ", acceptedType=" + this.acceptedType + ", tag=" + this.tag + "]";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.acceptedtext.BaseAcceptedText
 * JD-Core Version:    0.6.0
 */