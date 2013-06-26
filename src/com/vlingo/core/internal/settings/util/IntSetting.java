package com.vlingo.core.internal.settings.util;

public abstract class IntSetting extends Setting
{
  protected IntSetting(String paramString1, int paramInt, String paramString2)
  {
    super(paramString1, 2, getStringValue(paramInt), paramString2);
  }

  protected static String getStringValue(int paramInt)
  {
    return Integer.toString(paramInt);
  }

  protected static int getValue(String paramString)
  {
    return Integer.parseInt(paramString);
  }

  public int getValue()
  {
    return getValue((String)this.value);
  }

  public void setValue(int paramInt)
  {
    setValueInternal(getStringValue(paramInt));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.settings.util.IntSetting
 * JD-Core Version:    0.6.0
 */