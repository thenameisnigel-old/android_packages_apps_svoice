package com.vlingo.sdk.internal.util;

import com.vlingo.sdk.internal.settings.Settings;

public class Screen
{
  public static float defaultMagnification()
  {
    return 4.3F;
  }

  public static int defaultWidth()
  {
    return 1280;
  }

  public static Screen getInstance()
  {
    return new Screen();
  }

  public static float getMagnification()
  {
    return Float.valueOf(Settings.getPersistentString("screen.mag", Float.toString(defaultMagnification()))).floatValue();
  }

  public int getWidth()
  {
    return Integer.valueOf(Settings.getPersistentString("screen.width", Integer.toString(defaultWidth()))).intValue();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.util.Screen
 * JD-Core Version:    0.6.0
 */