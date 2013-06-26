package com.vlingo.sdk.internal.vlservice.response;

import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.internal.recognizer.results.SRRecognitionResponse;
import com.vlingo.sdk.internal.util.ToIntHashtable;
import com.vlingo.sdk.internal.util.ToIntHashtableFactory;
import com.vlingo.sdk.internal.xml.XmlAttributes;
import com.vlingo.sdk.internal.xml.XmlHandler;
import com.vlingo.sdk.internal.xml.XmlParser;
import com.vlingo.sdk.util.SDKDebugSettings;
import com.vlingo.sdk.util.SDKDebugSettings.ServerResponseLoggingState;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Vector;

public class VLResponseParser
{
  private static SDKDebugSettings dbgSettings = null;
  static final String path = "/sdcard/vlingo";
  static int reponseCount = 0;
  private byte attributeIndex = 1;
  VLResponseSectionParser currentParser = null;
  VLServiceResponse currentResponse = null;
  private byte elementIndex = 50;
  Vector<VLResponseSectionParser> sectionParsers = new Vector();
  private final ToIntHashtable xmlAttributes = ToIntHashtableFactory.createNewHashtable();
  private final ToIntHashtable xmlElements = ToIntHashtableFactory.createNewHashtable();

  public VLResponseParser()
  {
    initParsers();
    initDebugSettings();
  }

  private static void clearOldRecordings()
  {
    if (dbgSettings.getServerResponseLoggingState() == SDKDebugSettings.ServerResponseLoggingState.SAVE)
    {
      reponseCount = 0;
      File[] arrayOfFile = new File("/sdcard/vlingo").listFiles(new FilenameFilter()
      {
        public boolean accept(File paramFile, String paramString)
        {
          return paramString.startsWith(VLResponseParser.dbgSettings.getmRawServerLogBase());
        }
      });
      int i = arrayOfFile.length;
      for (int j = 0; j < i; j++)
        arrayOfFile[j].delete();
    }
  }

  private VLResponseSectionParser getParserForElement(int paramInt)
  {
    int i = 0;
    VLResponseSectionParser localVLResponseSectionParser;
    if (i < this.sectionParsers.size())
    {
      localVLResponseSectionParser = (VLResponseSectionParser)this.sectionParsers.elementAt(i);
      if (!localVLResponseSectionParser.handlesElement(paramInt));
    }
    while (true)
    {
      return localVLResponseSectionParser;
      i++;
      break;
      localVLResponseSectionParser = null;
    }
  }

  public static void initDebugSettings()
  {
    dbgSettings = VLSdk.getInstance().getDebugSettings();
    if ((dbgSettings != null) && (dbgSettings.getServerResponseLoggingState() != SDKDebugSettings.ServerResponseLoggingState.NONE))
    {
      File localFile = new File("/sdcard/vlingo");
      if (localFile.isDirectory())
        break label50;
      localFile.mkdirs();
    }
    while (true)
    {
      return;
      label50: clearOldRecordings();
    }
  }

  private void onParseBegin(char[] paramArrayOfChar)
  {
    for (int i = 0; i < this.sectionParsers.size(); i++)
      ((VLResponseSectionParser)this.sectionParsers.elementAt(i)).onParseBegin(paramArrayOfChar);
  }

  public static void resetResponseCount()
  {
    reponseCount = 0;
    initDebugSettings();
  }

  public void addParser(VLResponseSectionParser paramVLResponseSectionParser)
  {
    this.sectionParsers.addElement(paramVLResponseSectionParser);
  }

  public VLServiceResponse getResponse()
  {
    return this.currentResponse;
  }

  public void initParsers()
  {
    addParser(new DialogParser(this));
    addParser(new ServerMessageParser(this));
    addParser(new ActionParser(this));
  }

  public void onSectionComplete()
  {
    this.currentParser = null;
  }

  public VLServiceResponse parseResponseXml(String paramString)
  {
    return parseResponseXml(paramString, new SRRecognitionResponse());
  }

  public VLServiceResponse parseResponseXml(String paramString, VLServiceResponse paramVLServiceResponse)
  {
    try
    {
      if (dbgSettings != null)
      {
        if (dbgSettings.getServerResponseLoggingState() == SDKDebugSettings.ServerResponseLoggingState.SAVE)
        {
          StringBuilder localStringBuilder2 = new StringBuilder().append("/sdcard/vlingo/").append(dbgSettings.getmRawServerLogBase()).append(".");
          int j = reponseCount;
          reponseCount = j + 1;
          BufferedWriter localBufferedWriter = new BufferedWriter(new FileWriter(j));
          localBufferedWriter.write(paramString);
          localBufferedWriter.close();
        }
      }
      else
      {
        this.currentResponse = paramVLServiceResponse;
        this.currentParser = null;
      }
    }
    catch (IOException localIOException)
    {
      try
      {
        while (true)
        {
          char[] arrayOfChar = paramString.toCharArray();
          onParseBegin(arrayOfChar);
          new XmlParser(arrayOfChar, 0, arrayOfChar.length, new XmlHandlerImpl(), this.xmlElements, this.xmlAttributes, true, false).parseXml();
          label151: return this.currentResponse;
          if (dbgSettings.getServerResponseLoggingState() != SDKDebugSettings.ServerResponseLoggingState.REPLAY)
            continue;
          StringBuilder localStringBuilder1 = new StringBuilder().append("/sdcard/vlingo/").append(dbgSettings.getmRawServerLogBase()).append(".");
          int i = reponseCount;
          reponseCount = i + 1;
          BufferedReader localBufferedReader = new BufferedReader(new FileReader(i));
          paramString = localBufferedReader.readLine();
          localBufferedReader.close();
        }
        localIOException = localIOException;
      }
      catch (Exception localException)
      {
        break label151;
      }
    }
  }

  public int registerAttribute(String paramString)
  {
    if (this.xmlAttributes.containsKey(paramString));
    for (int i = this.xmlAttributes.get(paramString); ; i = this.attributeIndex)
    {
      return i;
      this.attributeIndex = (1 + this.attributeIndex);
      this.xmlAttributes.put(paramString, this.attributeIndex);
    }
  }

  public int registerElement(String paramString)
  {
    if (this.xmlElements.containsKey(paramString));
    for (int i = this.xmlElements.get(paramString); ; i = this.elementIndex)
    {
      return i;
      this.elementIndex = (1 + this.elementIndex);
      this.xmlElements.put(paramString, this.elementIndex);
    }
  }

  class XmlHandlerImpl
    implements XmlHandler
  {
    XmlHandlerImpl()
    {
    }

    public void beginDocument()
    {
      if (VLResponseParser.this.currentParser != null)
        VLResponseParser.this.currentParser.beginDocument();
    }

    public void beginElement(int paramInt1, XmlAttributes paramXmlAttributes, char[] paramArrayOfChar, int paramInt2)
    {
      if (VLResponseParser.this.currentParser == null)
        VLResponseParser.this.currentParser = VLResponseParser.this.getParserForElement(paramInt1);
      if (VLResponseParser.this.currentParser != null)
        VLResponseParser.this.currentParser.beginElement(paramInt1, paramXmlAttributes, paramArrayOfChar, paramInt2);
    }

    public void characters(char[] paramArrayOfChar)
    {
      if (VLResponseParser.this.currentParser != null)
        VLResponseParser.this.currentParser.characters(paramArrayOfChar);
    }

    public void endDocument()
    {
      if (VLResponseParser.this.currentParser != null)
        VLResponseParser.this.currentParser.endDocument();
    }

    public void endElement(int paramInt1, int paramInt2)
    {
      if (VLResponseParser.this.currentParser != null)
        VLResponseParser.this.currentParser.endElement(paramInt1, paramInt2);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.vlservice.response.VLResponseParser
 * JD-Core Version:    0.6.0
 */