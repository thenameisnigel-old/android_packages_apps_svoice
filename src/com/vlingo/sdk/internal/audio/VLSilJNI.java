package com.vlingo.sdk.internal.audio;

import android.content.Context;
import com.vlingo.sdk.internal.core.ApplicationAdapter;
import java.io.File;

public class VLSilJNI
{
  public static void init()
  {
    File localFile = ApplicationAdapter.getInstance().getApplicationContext().getDir("vlsdk_lib", 0);
    System.load(localFile.getAbsolutePath() + "/libVLSilJNI.so");
  }

  public native boolean initialize(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);

  public native int processShortArray(short[] paramArrayOfShort, int paramInt);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.audio.VLSilJNI
 * JD-Core Version:    0.6.0
 */