package com.vlingo.core.internal.questions.parser;

import java.util.HashMap;
import java.util.Map;

public class ResponseElement
{
  public static String PROVIDER = "Provider";
  public static String RAW_RESPONSE;
  public static String SIMPLE_RESPONSE = "Response";
  protected Map<String, String> attributes = new HashMap();
  private String mTag;

  static
  {
    RAW_RESPONSE = "RawResponse";
  }

  public ResponseElement()
  {
  }

  public ResponseElement(String paramString)
  {
    setTag(paramString);
  }

  public String getAttribute(String paramString)
  {
    return (String)this.attributes.get(paramString);
  }

  public Map<String, String> getAttributes()
  {
    return this.attributes;
  }

  public String getTag()
  {
    return this.mTag;
  }

  protected String quoted(String paramString)
  {
    return "\"" + paramString + "\"";
  }

  public void setTag(String paramString)
  {
    this.mTag = paramString;
  }

  public String toString()
  {
    return quoted(getTag());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.questions.parser.ResponseElement
 * JD-Core Version:    0.6.0
 */