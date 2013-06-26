package com.samsung.voiceshell;

public class VoiceEngine
{
  public static final int AL_ENROLL = 0;
  public static final int AL_VERIFY = 1;
  public static final int VR_RECOGNITON = 2;
  public static int isEarMic = 0;
  public static final String typeDefine = "/data/data/com.vlingo.midas/typeDefine.bin";
  public final String ROOT = "/data/data/com.vlingo.midas/";
  public String m_UBMpath_default = "/system/wakeupdata/samsung/models_16k_NoE.bin";

  public static void init()
  {
    throw new RuntimeException("Stub");
  }

  public native int checkFileExistence(String paramString, int paramInt, int[] paramArrayOfInt);

  public native int computeEnergyFrame(short[] paramArrayOfShort, int paramInt1, int paramInt2);

  public native int consistencyCheckEnroll(String paramString, short paramShort);

  public native int functionAssignment(String paramString, int[] paramArrayOfInt, int paramInt);

  public boolean getIsRunningAdaptation()
  {
    throw new RuntimeException("Stub");
  }

  public boolean getIsRunningVoiceEngine()
  {
    throw new RuntimeException("Stub");
  }

  public int getMode()
  {
    throw new RuntimeException("Stub");
  }

  public native int getModelStatus(String paramString, short paramShort);

  public native int getNoiseStats(long[] paramArrayOfLong);

  public native int getSpectrum(short[] paramArrayOfShort, int[] paramArrayOfInt);

  public native int initializeDRC();

  public native int initializeEnroll(String paramString);

  public native int initializeNS();

  public native int initializeVerify(String paramString1, String paramString2, int paramInt);

  public native int performContinuousAdaptation(String paramString1, String paramString2);

  public int processBuffer(short[] paramArrayOfShort1, int paramInt, String paramString, short[] paramArrayOfShort2, short[] paramArrayOfShort3)
  {
    throw new RuntimeException("Stub");
  }

  public native int processDRC(short[] paramArrayOfShort, int paramInt);

  public native int processEnroll(String paramString, short paramShort);

  public native int processEnroll(String paramString, short paramShort, short[] paramArrayOfShort);

  public native int processNSFrame(short[] paramArrayOfShort, int paramInt);

  public native int processNSFrame(short[] paramArrayOfShort, int paramInt1, int paramInt2);

  public native int processVerify(int paramInt);

  public native int processVerifyFrame(int paramInt1, short[] paramArrayOfShort1, int paramInt2, String paramString, short[] paramArrayOfShort2, short[] paramArrayOfShort3);

  public void setAdaptationModelPath(String paramString)
  {
    throw new RuntimeException("Stub");
  }

  public void setIsRunningAdaptation(boolean paramBoolean)
  {
    throw new RuntimeException("Stub");
  }

  public void setIsRunningVoiceEngine(boolean paramBoolean)
  {
    throw new RuntimeException("Stub");
  }

  public void setMode(int paramInt)
  {
    throw new RuntimeException("Stub");
  }

  public native int setModelStatus(String paramString, int paramInt, short paramShort);

  public native int startVerify();

  public native int store1chPCM(short[] paramArrayOfShort, int paramInt);

  public native int terminateEnroll();

  public native int terminateVerify();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.voiceshell.VoiceEngine
 * JD-Core Version:    0.6.0
 */