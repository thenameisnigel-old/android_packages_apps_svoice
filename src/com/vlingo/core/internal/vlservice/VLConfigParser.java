package com.vlingo.core.internal.vlservice;

import android.util.Pair;
import com.vlingo.core.internal.xml.SimpleXmlParser;
import com.vlingo.core.internal.xml.XmlAttributes;
import java.util.Hashtable;

public class VLConfigParser extends SimpleXmlParser
{
  public static final String TYPE_BOOLEAN = "boolean";
  public static final String TYPE_FLOAT = "float";
  public static final String TYPE_INTEGER = "integer";
  public static final String TYPE_LONG = "long";
  public static final String TYPE_STRING = "string";
  final int ATTR_MIN_APP_VERSION = registerAttribute("MinAppVersion");
  final int ATTR_N = registerAttribute("n");
  final int ATTR_T = registerAttribute("t");
  final int ATTR_V = registerAttribute("v");
  final int ELEM_SETTING = registerElement("Setting");
  final int ELEM_VLCONFIG = registerElement("VLConfig");
  String minimumAppVersion;
  Hashtable<String, Pair<String, String>> settings;

  private void addSetting(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 != null)
    {
      if (paramString2 == null)
        paramString2 = "";
      if (paramString3 == null)
        if ((!"false".equalsIgnoreCase(paramString2)) && (!"true".equalsIgnoreCase(paramString2)))
          break label62;
    }
    label62: for (paramString3 = "boolean"; ; paramString3 = "string")
    {
      String str = paramString3.toLowerCase();
      this.settings.put(paramString1, new Pair(paramString2, str));
      return;
    }
  }

  public void beginElement(int paramInt1, XmlAttributes paramXmlAttributes, char[] paramArrayOfChar, int paramInt2)
  {
    if (this.ELEM_VLCONFIG == paramInt1)
      this.minimumAppVersion = paramXmlAttributes.lookup(this.ATTR_MIN_APP_VERSION);
    while (true)
    {
      return;
      if (this.ELEM_SETTING == paramInt1)
      {
        String str1 = paramXmlAttributes.lookup(this.ATTR_N);
        String str2 = paramXmlAttributes.lookup(this.ATTR_T);
        String str3 = paramXmlAttributes.lookup(this.ATTR_V);
        if (str3 == null)
          str3 = new String(paramArrayOfChar);
        addSetting(str1, str3, str2);
        continue;
      }
    }
  }

  public void endElement(int paramInt1, int paramInt2)
  {
  }

  public String getMinimumAppVersion()
  {
    return this.minimumAppVersion;
  }

  public Hashtable<String, Pair<String, String>> getSettings()
  {
    return this.settings;
  }

  public void onParseBegin(char[] paramArrayOfChar)
  {
    this.settings = new Hashtable();
    this.minimumAppVersion = null;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.vlservice.VLConfigParser
 * JD-Core Version:    0.6.0
 */