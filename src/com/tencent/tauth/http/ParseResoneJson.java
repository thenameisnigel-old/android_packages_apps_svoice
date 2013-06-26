package com.tencent.tauth.http;

import org.json.JSONException;
import org.json.JSONObject;

public class ParseResoneJson
{
  public static JSONObject parseJson(String paramString)
    throws JSONException, NumberFormatException, CommonException
  {
    if (paramString.equals("false"))
      throw new CommonException("request failed");
    if (paramString.equals("true"))
      paramString = "{value : true}";
    if (paramString.contains("allback("))
      paramString = paramString.replaceFirst("[\\s\\S]*allback\\(([\\s\\S]*)\\);[^\\)]*\\z", "$1").trim();
    return new JSONObject(paramString);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.http.ParseResoneJson
 * JD-Core Version:    0.6.0
 */