package com.vlingo.core.internal.settings.util;

public abstract class BooleanSetting extends Setting
{
  public static String[] LABELS_DISABLED_ENABLED;
  public static String[] LABELS_OFF_ON;
  protected final String[] labels;

  static
  {
    String[] arrayOfString1 = new String[2];
    arrayOfString1[0] = "Off";
    arrayOfString1[1] = "On";
    LABELS_OFF_ON = arrayOfString1;
    String[] arrayOfString2 = new String[2];
    arrayOfString2[0] = "Disabled";
    arrayOfString2[1] = "Enabled";
    LABELS_DISABLED_ENABLED = arrayOfString2;
  }

  protected BooleanSetting(String paramString1, boolean paramBoolean, String paramString2)
  {
    this(paramString1, paramBoolean, paramString2, LABELS_OFF_ON);
  }

  protected BooleanSetting(String paramString1, boolean paramBoolean, String paramString2, String[] paramArrayOfString)
  {
    super(paramString1, 0, getStringValue(paramBoolean), paramString2);
    this.labels = paramArrayOfString;
  }

  protected static String getStringValue(boolean paramBoolean)
  {
    if (paramBoolean);
    for (String str = "true"; ; str = "false")
      return str;
  }

  public static boolean getValueFromString(String paramString)
  {
    if (paramString != null)
      paramString = paramString.toLowerCase().trim();
    return "true".equals(paramString);
  }

  public boolean getValue()
  {
    return getValueFromString((String)this.value);
  }

  public void setValue(boolean paramBoolean)
  {
    setValueInternal(getStringValue(paramBoolean));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.settings.util.BooleanSetting
 * JD-Core Version:    0.6.0
 */