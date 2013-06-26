package com.vlingo.sdk.internal.xml;

import com.vlingo.sdk.internal.util.ToIntHashtable;
import com.vlingo.sdk.internal.util.ToIntHashtableFactory;

public abstract class SimpleXmlParser
  implements XmlHandler
{
  private byte attributeIndex = 1;
  private byte elementIndex = 50;
  final ToIntHashtable xmlAttributes = ToIntHashtableFactory.createNewHashtable();
  final ToIntHashtable xmlElements = ToIntHashtableFactory.createNewHashtable();

  public void beginDocument()
  {
  }

  public void characters(char[] paramArrayOfChar)
  {
  }

  public void endDocument()
  {
  }

  public void onParseBegin(char[] paramArrayOfChar)
  {
  }

  public void parseXML(String paramString)
  {
    char[] arrayOfChar = paramString.toCharArray();
    onParseBegin(arrayOfChar);
    new XmlParser(arrayOfChar, 0, arrayOfChar.length, this, this.xmlElements, this.xmlAttributes, true, false).parseXml();
  }

  protected int registerAttribute(String paramString)
  {
    if (this.xmlAttributes.containsKey(paramString));
    for (int i = this.xmlAttributes.get(paramString); ; i = this.attributeIndex)
    {
      return i;
      this.attributeIndex = (1 + this.attributeIndex);
      this.xmlAttributes.put(paramString, this.attributeIndex);
    }
  }

  protected int registerElement(String paramString)
  {
    if (this.xmlElements.containsKey(paramString));
    for (int i = this.xmlElements.get(paramString); ; i = this.elementIndex)
    {
      return i;
      this.elementIndex = (1 + this.elementIndex);
      this.xmlElements.put(paramString, this.elementIndex);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.xml.SimpleXmlParser
 * JD-Core Version:    0.6.0
 */