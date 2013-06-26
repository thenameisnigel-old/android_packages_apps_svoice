package com.vlingo.core.internal.naver;

import java.io.PrintStream;
import java.io.StringReader;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class NaverResponseParser
{
  public static final String DETAILS = "Details";
  public static final String MESSAGE = "Message";
  public static final String NAVER_PASS_THROUGH_RESPONSE = "NaverPassThroughResponse";
  public static final String QUERY_USED = "QueryUsed";
  public static final String RAW_RESPONSE_XML = "RawResponseXML";
  private String error;
  private String queryUsed;
  private String rawResponseXML;

  private void setError(String paramString)
  {
    this.error = paramString;
  }

  public String getError()
  {
    return this.error;
  }

  public String getQueryUsed()
  {
    return this.queryUsed;
  }

  public String getRawResponseXML()
  {
    return this.rawResponseXML;
  }

  public void parse(String paramString)
  {
    try
    {
      XMLReader localXMLReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
      localXMLReader.setContentHandler(new XMLHandler(this));
      localXMLReader.parse(new InputSource(new StringReader(paramString)));
      return;
    }
    catch (Exception localException)
    {
      while (true)
        System.out.println(localException);
    }
  }

  public void setQueryUsed(String paramString)
  {
    this.queryUsed = paramString;
  }

  public void setRawResponseXML(String paramString)
  {
    this.rawResponseXML = paramString;
  }

  private static class XMLHandler extends DefaultHandler
  {
    public NaverResponseParser data = null;
    Boolean elementOn = Boolean.valueOf(false);
    String elementValue = null;
    Boolean inDetails = Boolean.valueOf(false);

    XMLHandler(NaverResponseParser paramNaverResponseParser)
    {
      this.data = paramNaverResponseParser;
    }

    public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws SAXException
    {
      if (this.elementOn.booleanValue())
      {
        this.elementValue = new String(paramArrayOfChar, paramInt1, paramInt2);
        this.elementOn = Boolean.valueOf(false);
      }
    }

    public void endElement(String paramString1, String paramString2, String paramString3)
      throws SAXException
    {
      this.elementOn = Boolean.valueOf(false);
      if (paramString2.equalsIgnoreCase("QueryUsed"))
        this.data.setQueryUsed(this.elementValue);
      while (true)
      {
        return;
        if (paramString2.equalsIgnoreCase("RawResponseXML"))
        {
          this.data.setRawResponseXML(this.elementValue);
          continue;
        }
        if ((paramString2.equalsIgnoreCase("Message")) && (!this.inDetails.booleanValue()))
        {
          this.data.setError(this.elementValue);
          continue;
        }
        if (!paramString2.equalsIgnoreCase("Details"))
          continue;
        this.inDetails = Boolean.valueOf(false);
      }
    }

    public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
      throws SAXException
    {
      this.elementOn = Boolean.valueOf(true);
      if (paramString2.equals("NaverPassThroughResponse"))
      {
        int i = paramAttributes.getLength();
        int j = 0;
        if (j < i)
        {
          String str1 = paramAttributes.getQName(j);
          String str2 = paramAttributes.getValue(j);
          if (str1.equalsIgnoreCase("QueryUsed"))
            this.data.setQueryUsed(str2);
          while (true)
          {
            j++;
            break;
            if (!str1.equalsIgnoreCase("RawResponseXML"))
              continue;
            this.data.setRawResponseXML(str2);
          }
        }
      }
      else if (paramString2.equalsIgnoreCase("Details"))
      {
        this.inDetails = Boolean.valueOf(true);
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.naver.NaverResponseParser
 * JD-Core Version:    0.6.0
 */