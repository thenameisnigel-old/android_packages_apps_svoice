package com.vlingo.sdk.internal.recognizer.results;

import com.vlingo.sdk.internal.vlservice.response.VLResponseParser;

public class SRResponseParser
{
  VLResponseParser parser = new VLResponseParser();

  public SRResponseParser()
  {
    this.parser.addParser(new TaggedResultsParser(this));
  }

  public SRRecognitionResponse getResponse()
  {
    return (SRRecognitionResponse)this.parser.getResponse();
  }

  public SRRecognitionResponse parseResponseXml(String paramString)
  {
    return (SRRecognitionResponse)this.parser.parseResponseXml(paramString, new SRRecognitionResponse());
  }

  public int registerAttribute(String paramString)
  {
    return this.parser.registerAttribute(paramString);
  }

  public int registerElement(String paramString)
  {
    return this.parser.registerElement(paramString);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.results.SRResponseParser
 * JD-Core Version:    0.6.0
 */