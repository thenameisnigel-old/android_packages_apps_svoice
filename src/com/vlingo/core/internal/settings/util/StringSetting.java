package com.vlingo.core.internal.settings.util;

public abstract class StringSetting extends Setting
{
  public static final String VALUE_EMPTY = "__EMPTY__";

  protected StringSetting(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1, 1, paramString2, paramString3);
  }

  public String getValue()
  {
    if ((this.value == null) || (((String)this.value).length() == 0));
    for (String str = null; ; str = (String)this.value)
      return str;
  }

  public void setValue(String paramString)
  {
    if (paramString == null)
      paramString = "";
    setValueInternal(paramString);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.settings.util.StringSetting
 * JD-Core Version:    0.6.0
 */