package com.vlingo.sdk.internal.util;

public class XmlUtils
{
  public static String wrapInCDATA(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<![CDATA[");
    localStringBuffer.append(StringUtils.replace(paramString, "]]>", "]]]><![CDATA[]>"));
    localStringBuffer.append("]]>");
    return localStringBuffer.toString();
  }

  public static String xmlDecode(String paramString)
  {
    if (paramString == null);
    StringBuffer localStringBuffer;
    for (String str = null; ; str = localStringBuffer.toString())
    {
      return str;
      localStringBuffer = new StringBuffer(paramString.length());
      xmlDecode(paramString, localStringBuffer);
    }
  }

  public static void xmlDecode(String paramString, StringBuffer paramStringBuffer)
  {
    if (paramString == null)
      return;
    int i = 0;
    label7: char c;
    String str;
    if (i < paramString.length())
    {
      c = paramString.charAt(i);
      if (c != '&')
        break label161;
      str = paramString.substring(i);
      if (!str.startsWith("&quot;"))
        break label60;
      i += 5;
      paramStringBuffer.append('"');
    }
    while (true)
    {
      i++;
      break label7;
      break;
      label60: if (str.startsWith("&apos;"))
      {
        i += 5;
        paramStringBuffer.append('\'');
        continue;
      }
      if (str.startsWith("&amp;"))
      {
        i += 4;
        paramStringBuffer.append('&');
        continue;
      }
      if (str.startsWith("&lt;"))
      {
        i += 3;
        paramStringBuffer.append('<');
        continue;
      }
      if (str.startsWith("&gt;"))
      {
        i += 3;
        paramStringBuffer.append('>');
        continue;
      }
      paramStringBuffer.append(c);
      continue;
      label161: paramStringBuffer.append(c);
    }
  }

  public static String xmlEncode(String paramString)
  {
    if (paramString == null);
    StringBuffer localStringBuffer;
    for (String str = null; ; str = localStringBuffer.toString())
    {
      return str;
      localStringBuffer = new StringBuffer(paramString.length());
      xmlEncode(paramString, localStringBuffer);
    }
  }

  public static void xmlEncode(String paramString, StringBuffer paramStringBuffer)
  {
    if (paramString == null)
      return;
    int i = 0;
    label7: char c;
    if (i < paramString.length())
    {
      c = paramString.charAt(i);
      if (c != '"')
        break label40;
      paramStringBuffer.append("&quot;");
    }
    while (true)
    {
      i++;
      break label7;
      break;
      label40: if (c == '\'')
      {
        paramStringBuffer.append("&apos;");
        continue;
      }
      if (c == '&')
      {
        paramStringBuffer.append("&amp;");
        continue;
      }
      if (c == '<')
      {
        paramStringBuffer.append("&lt;");
        continue;
      }
      if (c == '>')
      {
        paramStringBuffer.append("&gt;");
        continue;
      }
      if (c <= '\037')
        continue;
      paramStringBuffer.append(c);
    }
  }

  public static void xmlEncode(String paramString, StringBuilder paramStringBuilder)
  {
    if (paramString == null)
      return;
    int i = 0;
    label7: char c;
    if (i < paramString.length())
    {
      c = paramString.charAt(i);
      if (c != '"')
        break label40;
      paramStringBuilder.append("&quot;");
    }
    while (true)
    {
      i++;
      break label7;
      break;
      label40: if (c == '\'')
      {
        paramStringBuilder.append("&apos;");
        continue;
      }
      if (c == '&')
      {
        paramStringBuilder.append("&amp;");
        continue;
      }
      if (c == '<')
      {
        paramStringBuilder.append("&lt;");
        continue;
      }
      if (c == '>')
      {
        paramStringBuilder.append("&gt;");
        continue;
      }
      if (c <= '\037')
        continue;
      paramStringBuilder.append(c);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.util.XmlUtils
 * JD-Core Version:    0.6.0
 */