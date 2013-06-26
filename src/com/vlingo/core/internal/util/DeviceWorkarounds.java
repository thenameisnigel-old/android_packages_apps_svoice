package com.vlingo.core.internal.util;

import android.os.Build;
import android.os.Build.VERSION;

public class DeviceWorkarounds
{
  public static boolean doesDeviceSupportCachedPaths()
  {
    if (!Build.MODEL.equals("Galaxy Nexus"));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean isDeviceHTCIncredible2()
  {
    return Build.MODEL.equals("ADR6350");
  }

  public static boolean shouldIgnoreRequiredSamsungTTSEngine()
  {
    if ((Build.MODEL.equals("ADR6350")) || (Build.MODEL.equals("Galaxy Nexus")));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean useVoiceRecognitionAudioPath()
  {
    int i = 0;
    if (Build.VERSION.SDK_INT < 7);
    while (true)
    {
      return i;
      if (isDeviceHTCIncredible2())
        continue;
      i = 1;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.DeviceWorkarounds
 * JD-Core Version:    0.6.0
 */