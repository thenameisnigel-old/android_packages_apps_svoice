package com.vlingo.sdk.internal.vlservice.response;

import com.vlingo.sdk.internal.xml.XmlHandler;

public abstract class VLResponseSectionParser
  implements XmlHandler
{
  protected VLResponseParser responseParser = null;

  public VLResponseSectionParser(VLResponseParser paramVLResponseParser)
  {
    this.responseParser = paramVLResponseParser;
  }

  public void beginDocument()
  {
  }

  public void characters(char[] paramArrayOfChar)
  {
  }

  public void endDocument()
  {
  }

  public abstract boolean handlesElement(int paramInt);

  public void onParseBegin(char[] paramArrayOfChar)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.vlservice.response.VLResponseSectionParser
 * JD-Core Version:    0.6.0
 */