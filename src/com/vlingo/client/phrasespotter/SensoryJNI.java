package com.vlingo.client.phrasespotter;

import android.util.Log;

public class SensoryJNI
{
  public static int GRAMMAR_FORMALITY_DEFAULT = 0;

  public static void init()
  {
    try
    {
      Log.e("SensoryJNI", "Trying to load libWakeUpSensory.so");
      System.loadLibrary("WakeUpSensory");
      Log.e("SensoryJNI", "Loading libWakeUpSensory.so done");
      return;
    }
    catch (Exception localException)
    {
      while (true)
        Log.e("SensoryJNI", "WARNING: Could not load libWakeUpSensory.so");
    }
  }

  public native boolean Initialize(String paramString1, String paramString2, int paramInt1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, String paramString3, int paramInt2);

  public native boolean MakeReady();

  public native int ProcessShortArray(short[] paramArrayOfShort, int paramInt);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.client.phrasespotter.SensoryJNI
 * JD-Core Version:    0.6.0
 */