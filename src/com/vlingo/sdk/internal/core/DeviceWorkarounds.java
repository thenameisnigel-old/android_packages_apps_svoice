package com.vlingo.sdk.internal.core;

import android.os.Build;
import android.os.Build.VERSION;

public class DeviceWorkarounds
{
  public static boolean isDeviceHTCIncredible2()
  {
    return Build.MODEL.equals("ADR6350");
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
 * Qualified Name:     com.vlingo.sdk.internal.core.DeviceWorkarounds
 * JD-Core Version:    0.6.0
 */