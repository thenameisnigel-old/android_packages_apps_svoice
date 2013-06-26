package com.vlingo.core.internal.settings.util;

public abstract class LongSetting extends Setting
{
  protected LongSetting(String paramString1, long paramLong, String paramString2)
  {
    super(paramString1, 4, getStringValue(paramLong), paramString2);
  }

  protected static String getStringValue(long paramLong)
  {
    return Long.toString(paramLong);
  }

  protected static long getValue(String paramString)
  {
    return Long.parseLong(paramString);
  }

  public long getValue()
  {
    return getValue((String)this.value);
  }

  public void setValue(long paramLong)
  {
    setValueInternal(getStringValue(paramLong));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.settings.util.LongSetting
 * JD-Core Version:    0.6.0
 */