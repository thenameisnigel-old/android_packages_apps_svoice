package com.vlingo.core.internal.questions.parser;

public class ImageElement extends ResponseElement
{
  public ImageElement(String paramString)
  {
    super(paramString);
  }

  public String getURL()
  {
    return getAttribute("Src");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.questions.parser.ImageElement
 * JD-Core Version:    0.6.0
 */