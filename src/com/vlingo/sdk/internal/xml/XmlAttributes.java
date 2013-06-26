package com.vlingo.sdk.internal.xml;

import com.vlingo.sdk.internal.util.ToIntHashtable;
import java.util.Vector;

public class XmlAttributes
{
  public static final byte ATTRIBUTE_UNDEF;
  private Vector<XmlAttribute> attributeList;
  private ToIntHashtable xmlAttributes;

  public XmlAttributes(int paramInt, ToIntHashtable paramToIntHashtable)
  {
    this.xmlAttributes = paramToIntHashtable;
    this.attributeList = new Vector(paramInt);
  }

  public XmlAttributes(ToIntHashtable paramToIntHashtable)
  {
    this.xmlAttributes = paramToIntHashtable;
    this.attributeList = new Vector();
  }

  public void add(byte paramByte, String paramString)
  {
    this.attributeList.addElement(new XmlAttribute(paramByte, paramString));
  }

  public XmlAttribute getAttribute(int paramInt)
  {
    return (XmlAttribute)this.attributeList.elementAt(paramInt);
  }

  public byte getAttributeType(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    String str = String.valueOf(paramArrayOfChar, paramInt1, paramInt2);
    if (this.xmlAttributes.containsKey(str));
    for (int i = (byte)this.xmlAttributes.get(str); ; i = 0)
      return i;
  }

  public int getLength()
  {
    return this.attributeList.size();
  }

  public int getType(int paramInt)
  {
    XmlAttribute localXmlAttribute;
    if (paramInt < this.attributeList.size())
    {
      localXmlAttribute = (XmlAttribute)this.attributeList.elementAt(paramInt);
      if (localXmlAttribute == null);
    }
    for (int i = localXmlAttribute.getType(); ; i = -1)
      return i;
  }

  public String getValue(int paramInt)
  {
    XmlAttribute localXmlAttribute;
    if (paramInt < this.attributeList.size())
    {
      localXmlAttribute = (XmlAttribute)this.attributeList.elementAt(paramInt);
      if (localXmlAttribute == null);
    }
    for (String str = localXmlAttribute.getValue(); ; str = null)
      return str;
  }

  public String lookup(int paramInt)
  {
    int i = 0;
    XmlAttribute localXmlAttribute;
    if (i < this.attributeList.size())
    {
      localXmlAttribute = (XmlAttribute)this.attributeList.elementAt(i);
      if ((localXmlAttribute == null) || (localXmlAttribute.getType() != paramInt));
    }
    for (String str = localXmlAttribute.getValue(); ; str = null)
    {
      return str;
      i++;
      break;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.xml.XmlAttributes
 * JD-Core Version:    0.6.0
 */