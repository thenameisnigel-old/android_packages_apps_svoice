package com.vlingo.sdk.internal.audio;

import android.content.Context;
import com.vlingo.sdk.internal.core.ApplicationAdapter;
import java.io.File;

public class SpeexJNI
{
  private static long nextID;
  private long ID;

  public SpeexJNI()
  {
    long l = 1L + nextID;
    nextID = l;
    this.ID = l;
  }

  public static void init()
  {
    String str = ApplicationAdapter.getInstance().getApplicationContext().getDir("vlsdk_lib", 0).getAbsolutePath();
    System.load(str + "/libogg.so");
    System.load(str + "/libspeex.so");
    System.load(str + "/libSpeexJNI.so");
  }

  public native int Encode(long paramLong, short[] paramArrayOfShort, byte[] paramArrayOfByte, int paramInt1, int paramInt2);

  public native int Initialize(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);

  public int encode(short[] paramArrayOfShort, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return Encode(this.ID, paramArrayOfShort, paramArrayOfByte, paramInt1, paramInt2);
  }

  public int initialize(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    return Initialize(this.ID, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.audio.SpeexJNI
 * JD-Core Version:    0.6.0
 */