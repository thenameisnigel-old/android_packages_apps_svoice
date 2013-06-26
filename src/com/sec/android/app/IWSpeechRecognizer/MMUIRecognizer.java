package com.sec.android.app.IWSpeechRecognizer;

import android.util.Log;

public class MMUIRecognizer
{
  public static void init()
  {
    try
    {
      Log.e("MMUIRecognizer", "Trying to load libsasr-jni.so");
      System.loadLibrary("sasr-jni");
      Log.e("MMUIRecognizer", "Loading libsasr-jni.so done");
      return;
    }
    catch (Exception localException)
    {
      while (true)
        Log.e("MMUIRecognizer", "WARNING: Could not load libsasr-jni.so");
    }
  }

  public native int RECThread(short[] paramArrayOfShort);

  public native int ResetFx();

  public native int SASRClose();

  public native int SASRDoRecognition(float[] paramArrayOfFloat, String[] paramArrayOfString1, String paramString, short[] paramArrayOfShort, String[] paramArrayOfString2);

  public native int SASRInit(String paramString);

  public native int SASRLoadModel(String paramString);

  public native int SASRReset();

  public native int SaveChnUpdate(String paramString);

  public native int SetSRLanguage(int paramInt);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.app.IWSpeechRecognizer.MMUIRecognizer
 * JD-Core Version:    0.6.0
 */