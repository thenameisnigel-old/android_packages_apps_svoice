package com.vlingo.sdk.internal.phrasespotter;

import android.content.Context;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.internal.core.ApplicationAdapter;
import java.io.File;
import java.nio.ByteBuffer;

public class SensoryJNI
{
  public static int GRAMMAR_FORMALITY_DEFAULT = 0;

  public static void init()
  {
    File localFile = ApplicationAdapter.getInstance().getApplicationContext().getDir("vlsdk_lib", 0);
    StringBuilder localStringBuilder = new StringBuilder().append(localFile.getAbsolutePath());
    if (VLSdk.isSensory2Using());
    for (String str = "/libsensory2.so"; ; str = "/libsensory.so")
    {
      System.load(str);
      return;
    }
  }

  public native void Deinitialize();

  public native float GetLastScore();

  public native boolean Initialize(String paramString1, String paramString2, int paramInt1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, String paramString3, int paramInt2);

  public native boolean InitializePhrases(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, String paramString4, int paramInt2);

  public native boolean MakeReady();

  public native String ProcessShortArray(short[] paramArrayOfShort, int paramInt);

  public void asyncPrint(String paramString)
  {
  }

  public native void phrasespotClose(long paramLong);

  public native long phrasespotInit(String paramString1, String paramString2, String paramString3);

  public native String phrasespotPipe(long paramLong1, ByteBuffer paramByteBuffer, long paramLong2);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.phrasespotter.SensoryJNI
 * JD-Core Version:    0.6.0
 */