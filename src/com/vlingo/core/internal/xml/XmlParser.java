package com.vlingo.core.internal.xml;

import com.vlingo.core.internal.util.ToIntHashtable;
import com.vlingo.core.internal.util.ToIntHashtableFactory;
import java.util.Vector;

public class XmlParser
{
  private static final int STATE_ADVANCE = 4;
  private static final int STATE_ADVANCE_COMMENTS = 6;
  private static final int STATE_BEGIN = 0;
  private static final int STATE_DONE = 5;
  private static final int STATE_IN_CDATA = 3;
  private static final int STATE_IN_ELEMENT = 1;
  private static final int maxEscapeLen = 6;
  private static final ToIntHashtable xmlEscapes = ToIntHashtableFactory.createNewHashtable();
  private int attributeNameEnd;
  private int attributeNameStart;
  private int attributeValueEnd;
  private int attributeValueStart;
  private XmlAttributes attributes = null;
  protected int cDataEnd;
  protected int cDataStart;
  int cStartElementEnd = 0;
  private XmlAttributes cachedAttributes = null;
  private char[] cachedCData = null;
  private int cachedElementType = -1;
  private boolean checkCDataSpacing = false;
  private boolean checkEscapes = true;
  private boolean checkForEscapes_AttributeValues = false;
  private boolean checkForEscapes_CData = false;
  private boolean currentElementIsBegin;
  private boolean currentElementIsEnd;
  private int currentState;
  protected int elementEnd;
  protected int elementStart;
  protected int endIndex;
  private XmlHandler handler = null;
  protected int index;
  private char[] xml;
  private ToIntHashtable xmlAttributes = null;
  private ToIntHashtable xmlElements = null;

  static
  {
    xmlEscapes.put("quot", 34);
    xmlEscapes.put("amp", 38);
    xmlEscapes.put("apos", 39);
    xmlEscapes.put("lt", 60);
    xmlEscapes.put("gt", 62);
    xmlEscapes.put("nbsp", 32);
    xmlEscapes.put("excl", 33);
    xmlEscapes.put("sol", 47);
    xmlEscapes.put("equals", 61);
    xmlEscapes.put("lsqb", 91);
    xmlEscapes.put("rsqb", 93);
    xmlEscapes.put("trade", 8482);
  }

  public XmlParser(char[] paramArrayOfChar, int paramInt1, int paramInt2, XmlHandler paramXmlHandler, ToIntHashtable paramToIntHashtable1, ToIntHashtable paramToIntHashtable2, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.xml = paramArrayOfChar;
    this.index = paramInt1;
    this.endIndex = (-1 + (paramInt2 + this.index));
    this.handler = paramXmlHandler;
    this.xmlElements = paramToIntHashtable1;
    this.checkEscapes = paramBoolean1;
    this.checkCDataSpacing = paramBoolean2;
    this.currentState = 0;
    if (paramToIntHashtable2 == null)
      paramToIntHashtable2 = ToIntHashtableFactory.createNewHashtable();
    this.xmlAttributes = paramToIntHashtable2;
  }

  private static char[] accountForEscapes(Vector paramVector, char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int i = getEscapedArrayLength(paramVector, paramInt2);
    char[] arrayOfChar = new char[i];
    int j = 0;
    for (int k = 0; k < paramVector.size(); k++)
    {
      int[] arrayOfInt = (int[])(int[])paramVector.elementAt(k);
      System.arraycopy(paramArrayOfChar, paramInt1, arrayOfChar, j, arrayOfInt[0] - paramInt1);
      j += arrayOfInt[0] - paramInt1;
      int m = getEscapedByte(paramArrayOfChar, arrayOfInt[0], arrayOfInt[1]);
      paramInt1 = -1 + (arrayOfInt[0] + arrayOfInt[1]);
      paramArrayOfChar[paramInt1] = m;
    }
    if (j < i)
      System.arraycopy(paramArrayOfChar, paramInt1, arrayOfChar, j, i - j);
    return arrayOfChar;
  }

  private void addAttribute()
  {
    if ((this.attributeNameStart < 0) || (this.attributeNameEnd < 0))
      return;
    int i = this.attributeNameEnd - this.attributeNameStart;
    int j = this.attributeValueEnd - this.attributeValueStart;
    if (this.attributeValueStart == -1)
    {
      j = 0;
      this.attributeValueStart = 0;
      this.attributeValueEnd = 0;
    }
    if (this.attributes == null)
      this.attributes = new XmlAttributes(this.xmlAttributes);
    byte b = this.attributes.getAttributeType(this.xml, this.attributeNameStart, i);
    char[] arrayOfChar = this.xml;
    int k = this.attributeValueStart;
    if ((this.checkEscapes) || (this.checkForEscapes_AttributeValues));
    for (boolean bool = true; ; bool = false)
    {
      String str = createString(arrayOfChar, k, j, bool);
      this.attributes.add(b, str);
      break;
    }
  }

  private static Vector addEscape(Vector paramVector, int paramInt1, int paramInt2)
  {
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = paramInt1;
    arrayOfInt[1] = paramInt2;
    if (paramVector == null)
      paramVector = new Vector();
    paramVector.addElement(arrayOfInt);
    return paramVector;
  }

  private int advanceIndex()
  {
    int i = 5;
    while (true)
    {
      int j;
      if (this.index <= this.endIndex)
      {
        j = this.xml[this.index];
        if (j != 60)
          break label33;
        i = 1;
      }
      while (true)
      {
        return i;
        label33: if (j == 0)
          continue;
        if (j < 32)
          break;
        i = 3;
      }
      this.index = (1 + this.index);
    }
  }

  public static char[] createByteArray(char[] paramArrayOfChar, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    Vector localVector = null;
    Object localObject;
    if (paramBoolean1)
    {
      int i = paramInt1;
      while (i != -1)
      {
        i = findBegEscape(paramArrayOfChar, paramInt1, paramInt2, i);
        if (i == -1)
          continue;
        int j = findEndEscape(paramArrayOfChar, paramInt1, paramInt2, i + 1);
        if (j != -1)
        {
          localVector = addEscape(localVector, i, 1 + (j - i));
          i = j;
          continue;
        }
        i++;
      }
      if (localVector != null)
        localObject = accountForEscapes(localVector, paramArrayOfChar, paramInt1, paramInt2);
    }
    while (true)
    {
      return localObject;
      if (paramBoolean2)
      {
        localObject = null;
        continue;
      }
      char[] arrayOfChar = new char[paramInt2];
      System.arraycopy(paramArrayOfChar, paramInt1, arrayOfChar, 0, paramInt2);
      localObject = arrayOfChar;
    }
  }

  public static String createString(char[] paramArrayOfChar, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    char[] arrayOfChar;
    if (paramBoolean)
    {
      arrayOfChar = createByteArray(paramArrayOfChar, paramInt1, paramInt2, paramBoolean, true);
      if (arrayOfChar == null);
    }
    for (String str = String.valueOf(arrayOfChar); ; str = String.valueOf(paramArrayOfChar, paramInt1, paramInt2))
      return str;
  }

  private static int findBegEscape(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = -1;
    int j = paramInt3;
    int k = paramInt1 + paramInt2;
    if (paramInt3 >= k)
      break label21;
    while (true)
    {
      return i;
      label21: j++;
      if (j >= k)
        continue;
      if (paramArrayOfChar[j] != '&')
        break;
      i = j;
    }
  }

  private static int findEndEscape(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = -1;
    int j = paramInt1 + paramInt2;
    int k = paramInt3;
    int m = 0;
    if (paramInt3 >= j);
    while (true)
    {
      return i;
      while ((k < j) && (m < 6))
        switch (paramArrayOfChar[k])
        {
        case '&':
        default:
          k++;
          m++;
        case ';':
        }
      i = k;
    }
  }

  private int getElementCode(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int i = 0;
    String str = String.valueOf(paramArrayOfChar, paramInt1, paramInt2);
    if (this.xmlElements.containsKey(str))
      i = this.xmlElements.get(str);
    return i;
  }

  private static int getEscapedArrayLength(Vector paramVector, int paramInt)
  {
    for (int i = 0; i < paramVector.size(); i++)
      paramInt -= -1 + ((int[])(int[])paramVector.elementAt(i))[1];
    return paramInt;
  }

  private static char getEscapedByte(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int i = 32;
    String str = String.valueOf(paramArrayOfChar, paramInt1 + 1, paramInt2 - 2);
    if (str.startsWith("#"))
      i = (char)Integer.parseInt(str.substring(1));
    while (true)
    {
      return i;
      if (!xmlEscapes.containsKey(str))
        continue;
      i = (char)xmlEscapes.get(str);
    }
  }

  private void handleAttributes()
  {
    int i = 0;
    int j = 0;
    resetAttributeIndicies();
    if (this.index <= this.endIndex)
      switch (this.xml[this.index])
      {
      default:
        label88: if ((i != 0) || (this.attributeNameStart >= 0))
          break;
        this.attributeNameStart = this.index;
      case ' ':
      case '=':
      case '"':
      case '\'':
      case '/':
      case '>':
      }
    while (true)
    {
      this.index = (1 + this.index);
      break;
      if (i != 0)
        continue;
      this.attributeNameEnd = this.index;
      continue;
      if ((i != 0) && (j != 0))
      {
        this.attributeValueEnd = this.index;
        addAttribute();
        resetAttributeIndicies();
        i = 0;
        continue;
      }
      if ((i != 0) && (j == 0))
        continue;
      i = 1;
      j = 1;
      continue;
      if ((i != 0) && (j == 0) && (this.attributeValueStart > 0))
      {
        this.attributeValueEnd = this.index;
        addAttribute();
        resetAttributeIndicies();
        i = 0;
        continue;
      }
      if ((i != 0) && (j != 0))
        continue;
      i = 1;
      j = 0;
      continue;
      if (i != 0)
        break label88;
      this.index = (-1 + this.index);
      return;
      if ((i == 0) || (this.attributeValueStart >= 0))
        continue;
      this.attributeValueStart = this.index;
    }
  }

  private int handleBegin()
  {
    if (this.index <= this.endIndex)
      if (this.xml[this.index] != '<');
    for (int i = 1; ; i = 5)
    {
      return i;
      this.index = (1 + this.index);
      break;
    }
  }

  private int handleCData()
  {
    int i = 5;
    int j = -1;
    int k = 1;
    if (this.checkCDataSpacing)
    {
      if ((this.index <= this.endIndex) && (this.xml[this.index] <= ' '));
    }
    else
    {
      int m = this.index;
      this.cDataEnd = m;
      this.cDataStart = m;
      label57: if (this.index > this.endIndex)
        break label230;
      switch (this.xml[this.index])
      {
      default:
        k = 0;
        j = -1;
      case '<':
      case '\000':
      case '\t':
      case '\n':
      case '\013':
      case '\f':
      case '\r':
      case ' ':
      }
    }
    while (true)
    {
      this.index = (1 + this.index);
      break label57;
      this.index = (1 + this.index);
      break;
      if ((this.checkCDataSpacing) && (j > -1));
      for (this.cDataEnd = j; ; this.cDataEnd = this.index)
      {
        if ((!this.checkCDataSpacing) || (k == 0))
          outputChars();
        if (this.checkForEscapes_CData)
          this.checkForEscapes_CData = false;
        i = 1;
        label230: return i;
      }
      if ((this.checkCDataSpacing) && (j > -1));
      for (this.cDataEnd = j; ; this.cDataEnd = this.index)
      {
        if ((!this.checkCDataSpacing) || (k == 0))
          outputChars();
        if (!this.checkForEscapes_CData)
          break;
        this.checkForEscapes_CData = false;
        break;
      }
      this.xml[this.index] = ' ';
      if (j != -1)
        continue;
      j = this.index;
    }
  }

  private int handleElement()
  {
    this.currentElementIsBegin = true;
    this.currentElementIsEnd = false;
    this.elementStart = -1;
    this.elementEnd = -1;
    int i;
    while (this.index <= this.endIndex)
      switch (this.xml[this.index])
      {
      default:
        if (this.elementStart < 0)
          this.elementStart = this.index;
      case '"':
      case '\'':
      case '<':
        this.index = (1 + this.index);
        break;
      case '!':
        i = 6;
      case '/':
      case ' ':
      case '>':
      }
    while (true)
    {
      return i;
      if (this.elementStart < 0)
        this.currentElementIsBegin = false;
      this.currentElementIsEnd = true;
      if ((this.elementEnd >= 0) || (this.elementStart <= 0))
        break;
      this.elementEnd = this.index;
      break;
      if ((this.elementEnd >= 0) || (this.elementStart < 0))
        break;
      this.elementEnd = this.index;
      handleAttributes();
      if (!this.checkForEscapes_AttributeValues)
        break;
      this.checkForEscapes_AttributeValues = false;
      break;
      if ((this.elementEnd < 0) && (this.elementStart >= 0))
        this.elementEnd = this.index;
      outputElement();
      this.index = (1 + this.index);
      i = 4;
      continue;
      i = 5;
    }
  }

  private void outputChars()
  {
    boolean bool = true;
    int i = this.cDataEnd - this.cDataStart;
    if (this.cachedElementType != 255)
    {
      char[] arrayOfChar3 = this.xml;
      int k = this.cDataStart;
      if ((this.checkEscapes) || (this.checkForEscapes_CData));
      while (true)
      {
        this.cachedCData = createByteArray(arrayOfChar3, k, i, bool, false);
        return;
        bool = false;
      }
    }
    char[] arrayOfChar1 = this.xml;
    int j = this.cDataStart;
    if ((this.checkEscapes) || (this.checkForEscapes_CData));
    while (true)
    {
      char[] arrayOfChar2 = createByteArray(arrayOfChar1, j, i, bool, false);
      if (arrayOfChar2 == null)
        break;
      this.handler.characters(arrayOfChar2);
      break;
      bool = false;
    }
  }

  private void outputElement()
  {
    int i = this.elementEnd - this.elementStart;
    if (this.cachedElementType != 255)
    {
      this.handler.beginElement(this.cachedElementType, this.cachedAttributes, this.cachedCData, this.cStartElementEnd);
      this.cachedElementType = 255;
      this.cachedAttributes = null;
      this.cachedCData = null;
    }
    int j;
    if (this.elementStart > 0)
    {
      char[] arrayOfChar = new char[i];
      System.arraycopy(this.xml, this.elementStart, arrayOfChar, 0, i);
      j = getElementCode(this.xml, this.elementStart, i);
      if (!this.currentElementIsEnd)
        break label157;
      if (this.currentElementIsBegin)
        this.handler.beginElement(j, this.attributes, null, this.elementEnd);
      this.handler.endElement(j, -2 + this.elementStart);
    }
    while (true)
    {
      this.attributes = null;
      return;
      label157: if (!this.currentElementIsBegin)
        continue;
      this.cachedElementType = j;
      this.cStartElementEnd = (1 + this.index);
      if (this.attributes == null)
        continue;
      this.cachedAttributes = this.attributes;
    }
  }

  private void resetAttributeIndicies()
  {
    this.attributeNameStart = -1;
    this.attributeNameEnd = -1;
    this.attributeValueStart = -1;
    this.attributeValueEnd = -1;
  }

  private int skipComments()
  {
    int i = 0;
    if (this.index <= this.endIndex)
    {
      switch (this.xml[this.index])
      {
      default:
        i = 0;
      case '-':
      case '>':
      }
      do
        while (true)
        {
          this.index = (1 + this.index);
          break;
          i++;
        }
      while (i < 2);
    }
    for (int j = 4; ; j = 5)
      return j;
  }

  public void parseXml()
  {
    while (true)
    {
      if (this.currentState != 5);
      try
      {
        switch (this.currentState)
        {
        case 0:
          this.handler.beginDocument();
          this.currentState = handleBegin();
          break;
        case 1:
          this.currentState = handleElement();
          break;
        case 3:
          this.currentState = handleCData();
          break;
        case 4:
          this.currentState = advanceIndex();
          break;
        case 6:
          this.currentState = skipComments();
          continue;
          this.handler.endDocument();
          return;
        case 2:
        case 5:
        }
      }
      catch (Throwable localThrowable)
      {
      }
    }
  }

  public void stopParsing()
  {
    this.index = (1 + this.endIndex);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.xml.XmlParser
 * JD-Core Version:    0.6.0
 */