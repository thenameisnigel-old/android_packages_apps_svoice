package com.vlingo.sdk.internal.xml;

public class XmlAttribute
{
  private final int attributeType;
  private final String attributeValue;

  public XmlAttribute(int paramInt, String paramString)
  {
    this.attributeType = paramInt;
    this.attributeValue = paramString;
  }

  public int getType()
  {
    return this.attributeType;
  }

  public String getValue()
  {
    return this.attributeValue;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.xml.XmlAttribute
 * JD-Core Version:    0.6.0
 */