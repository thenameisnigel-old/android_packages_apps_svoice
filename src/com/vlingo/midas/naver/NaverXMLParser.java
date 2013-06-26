package com.vlingo.midas.naver;

import android.util.Xml;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class NaverXMLParser
{
  public int ErrorCode;
  public String answer;
  public String domain;
  public List<Hashtable<String, String>> itemList = new ArrayList();
  public String message;
  private final String ns = "nhncorp.naverkoreaservice.search.searchcommon.searchsystem.svoice";
  public Hashtable<String, String> propertyList = new Hashtable();
  public String query;
  public String svoiceQuery;
  public String ttsText;
  public String url;

  private NaverXMLParser getResult(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    if (paramXmlPullParser.getEventType() != 2)
      throw new IllegalStateException();
    paramXmlPullParser.require(2, "nhncorp.naverkoreaservice.search.searchcommon.searchsystem.svoice", "result");
    while (paramXmlPullParser.next() != 3)
    {
      if (paramXmlPullParser.getEventType() != 2)
        continue;
      String str = paramXmlPullParser.getName();
      if (str.equals("query"))
      {
        this.query = readCDATA(paramXmlPullParser);
        continue;
      }
      if (str.equals("domain"))
      {
        this.domain = readCDATA(paramXmlPullParser);
        continue;
      }
      if (str.equals("data"))
      {
        readData(paramXmlPullParser);
        continue;
      }
      if (str.equals("status"))
      {
        getStatus(paramXmlPullParser);
        continue;
      }
      skip(paramXmlPullParser);
    }
    return null;
  }

  private void getStatus(XmlPullParser paramXmlPullParser)
    throws IOException, XmlPullParserException
  {
    paramXmlPullParser.require(2, null, "status");
    paramXmlPullParser.next();
    paramXmlPullParser.require(2, null, "code");
    this.ErrorCode = Integer.valueOf(readText(paramXmlPullParser)).intValue();
    paramXmlPullParser.require(3, null, "code");
    paramXmlPullParser.next();
    paramXmlPullParser.require(2, null, "message");
    this.message = readText(paramXmlPullParser);
    paramXmlPullParser.require(3, null, "message");
    paramXmlPullParser.next();
    paramXmlPullParser.require(3, null, "status");
  }

  private String readCDATA(XmlPullParser paramXmlPullParser)
    throws IOException, XmlPullParserException
  {
    return readText(paramXmlPullParser);
  }

  private List readData(XmlPullParser paramXmlPullParser)
    throws IOException, XmlPullParserException
  {
    ArrayList localArrayList = null;
    paramXmlPullParser.require(2, null, "data");
    while (paramXmlPullParser.next() != 3)
    {
      if (paramXmlPullParser.getEventType() != 2)
        continue;
      String str = paramXmlPullParser.getName();
      if (str.equals("ttsText"))
      {
        this.ttsText = readText(paramXmlPullParser);
        continue;
      }
      if (str.equals("svoiceQuery"))
      {
        this.svoiceQuery = readText(paramXmlPullParser);
        continue;
      }
      if (str.equals("url"))
      {
        this.url = readText(paramXmlPullParser);
        continue;
      }
      if (str.equals("answer"))
      {
        this.answer = readText(paramXmlPullParser);
        continue;
      }
      if (str.equals("itemList"))
      {
        if (localArrayList == null)
          localArrayList = new ArrayList();
        while (paramXmlPullParser.next() != 3)
        {
          paramXmlPullParser.getName().equals("item");
          localArrayList.add(readEntry(paramXmlPullParser));
        }
        continue;
      }
      if (this.propertyList == null)
        this.propertyList = new Hashtable();
      this.propertyList.put(str, readCDATA(paramXmlPullParser));
    }
    this.itemList = localArrayList;
    return localArrayList;
  }

  private Hashtable<String, String> readEntry(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    paramXmlPullParser.require(2, null, "item");
    Hashtable localHashtable = null;
    while (paramXmlPullParser.next() != 3)
    {
      if (paramXmlPullParser.getEventType() != 2)
        continue;
      String str1 = paramXmlPullParser.getName();
      String str2 = readCDATA(paramXmlPullParser);
      if (localHashtable == null)
        localHashtable = new Hashtable();
      if ((str2 == null) || (str2.length() == 0))
        continue;
      localHashtable.put(str1, str2);
    }
    return localHashtable;
  }

  private void readNaverXML(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    paramXmlPullParser.require(2, null, "message");
    while (paramXmlPullParser.next() != 3)
    {
      if (paramXmlPullParser.getEventType() != 2)
        continue;
      if (paramXmlPullParser.getName().equals("result"))
      {
        getResult(paramXmlPullParser);
        continue;
      }
      skip(paramXmlPullParser);
    }
  }

  private String readText(XmlPullParser paramXmlPullParser)
    throws IOException, XmlPullParserException
  {
    String str = "";
    if (paramXmlPullParser.next() == 4)
    {
      str = paramXmlPullParser.getText();
      paramXmlPullParser.nextTag();
    }
    return str;
  }

  private void skip(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    if (paramXmlPullParser.getEventType() != 2)
      throw new IllegalStateException();
    int i = 1;
    while (i != 0)
      switch (paramXmlPullParser.next())
      {
      default:
        break;
      case 2:
        i++;
        break;
      case 3:
        i--;
      }
  }

  public void parse(String paramString)
  {
    try
    {
      XmlPullParser localXmlPullParser = Xml.newPullParser();
      ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramString.getBytes("UTF-8"));
      localXmlPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
      localXmlPullParser.setInput(localByteArrayInputStream, "UTF-8");
      localXmlPullParser.nextTag();
      readNaverXML(localXmlPullParser);
      localByteArrayInputStream.close();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
    finally
    {
    }
    throw localObject;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.naver.NaverXMLParser
 * JD-Core Version:    0.6.0
 */