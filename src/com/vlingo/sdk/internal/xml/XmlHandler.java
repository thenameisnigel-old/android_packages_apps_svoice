package com.vlingo.sdk.internal.xml;

public abstract interface XmlHandler
{
  public abstract void beginDocument();

  public abstract void beginElement(int paramInt1, XmlAttributes paramXmlAttributes, char[] paramArrayOfChar, int paramInt2);

  public abstract void characters(char[] paramArrayOfChar);

  public abstract void endDocument();

  public abstract void endElement(int paramInt1, int paramInt2);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.xml.XmlHandler
 * JD-Core Version:    0.6.0
 */