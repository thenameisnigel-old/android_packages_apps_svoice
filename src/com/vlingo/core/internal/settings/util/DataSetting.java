package com.vlingo.core.internal.settings.util;

public abstract class DataSetting extends Setting
{
  protected DataSetting(String paramString1, byte[] paramArrayOfByte, String paramString2)
  {
    super(paramString1, 6, paramArrayOfByte, paramString2);
  }

  public byte[] getValue()
  {
    return (byte[])(byte[])this.value;
  }

  public void setValue(byte[] paramArrayOfByte)
  {
    setValueInternal(paramArrayOfByte);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.settings.util.DataSetting
 * JD-Core Version:    0.6.0
 */