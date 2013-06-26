package com.vlingo.core.internal.settings.util;

import java.lang.reflect.Array;

public abstract class EnumSetting extends Setting
{
  protected String[][] enumeration;

  protected EnumSetting(String paramString1, String[][] paramArrayOfString, String paramString2, String paramString3)
  {
    super(paramString1, 3, paramString2, paramString3);
    this.enumeration = paramArrayOfString;
  }

  private String[][] cloneEnum(String[][] paramArrayOfString)
  {
    int i = paramArrayOfString.length;
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = i;
    arrayOfInt[1] = 2;
    String[][] arrayOfString = (String[][])Array.newInstance(String.class, arrayOfInt);
    for (int j = 0; j < paramArrayOfString.length; j++)
      for (int k = 0; k < 2; k++)
        arrayOfString[j][k] = new String(paramArrayOfString[j][k]);
    return arrayOfString;
  }

  private boolean equal(String[][] paramArrayOfString1, String[][] paramArrayOfString2)
  {
    int i = 0;
    if (paramArrayOfString1.length != paramArrayOfString2.length);
    while (true)
    {
      return i;
      label56: for (int j = 0; ; j++)
      {
        if (j >= paramArrayOfString2.length)
          break label62;
        for (int k = 0; ; k++)
        {
          if (k >= 2)
            break label56;
          if (!paramArrayOfString1[j][k].equals(paramArrayOfString2[j][k]))
            break;
        }
      }
      label62: i = 1;
    }
  }

  public int getEnumerationLength()
  {
    return this.enumeration.length;
  }

  public int getIndexOfValue(String paramString)
  {
    int i = 0;
    if (i < this.enumeration.length)
      if (!paramString.equalsIgnoreCase(getValueOfIndex(i)));
    while (true)
    {
      return i;
      i++;
      break;
      i = -1;
    }
  }

  public String getNameForValue(String paramString)
  {
    int i = getIndexOfValue(paramString);
    if (i != -1);
    for (String str = getNameOfIndex(i); ; str = null)
      return str;
  }

  public String getNameOfIndex(int paramInt)
  {
    return this.enumeration[paramInt][0];
  }

  public String getValue()
  {
    return (String)this.value;
  }

  public String getValueOfIndex(int paramInt)
  {
    return this.enumeration[paramInt][1];
  }

  public boolean hasValue(String paramString)
  {
    int i = 1;
    int j = 0;
    if (j < this.enumeration.length)
      if (!paramString.equalsIgnoreCase(this.enumeration[j][i]));
    while (true)
    {
      return i;
      j++;
      break;
      i = 0;
    }
  }

  public void migrate(Setting paramSetting)
  {
    super.migrate(paramSetting);
    setEnumeration(((EnumSetting)paramSetting).enumeration);
  }

  public void onSavedValue(Object paramObject)
  {
    monitorenter;
    int i = 0;
    try
    {
      if (i < this.enumeration.length)
        if (getValueOfIndex(i).equals(paramObject))
          super.onSavedValue(paramObject);
      while (true)
      {
        return;
        i++;
        break;
        setDirty();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void setEnumeration(String[][] paramArrayOfString)
  {
    if (!equal(this.enumeration, paramArrayOfString))
    {
      this.enumeration = cloneEnum(paramArrayOfString);
      setDirty();
    }
    if (!hasValue((String)this.value))
      setDefault();
  }

  public void setValue(String paramString)
  {
    setValue(paramString, true);
  }

  public void setValue(String paramString, boolean paramBoolean)
  {
    if (hasValue(paramString))
      setValueInternal(paramString);
    while (true)
    {
      return;
      if (paramBoolean)
      {
        setDefault();
        continue;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.settings.util.EnumSetting
 * JD-Core Version:    0.6.0
 */