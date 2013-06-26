package com.vlingo.sdk.internal.recognizer.results;

import com.vlingo.sdk.internal.vlservice.response.VLServiceResponse;

public class SRRecognitionResponse extends VLServiceResponse
{
  private String guttid;
  private TaggedResults results = null;

  public String getGUttId()
  {
    return this.guttid;
  }

  public TaggedResults getResults()
  {
    return this.results;
  }

  public boolean hasResults()
  {
    if ((this.results != null) && (!this.results.isEmpty()));
    for (int i = 1; ; i = 0)
      return i;
  }

  public void setGUttId(String paramString)
  {
    this.guttid = paramString;
  }

  public void setTaggedResults(TaggedResults paramTaggedResults)
  {
    this.results = paramTaggedResults;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(super.toString());
    if (this.results != null)
      localStringBuffer.append(this.results.toString());
    while (true)
    {
      localStringBuffer.append("\n");
      return localStringBuffer.toString();
      localStringBuffer.append("<no results>");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.results.SRRecognitionResponse
 * JD-Core Version:    0.6.0
 */