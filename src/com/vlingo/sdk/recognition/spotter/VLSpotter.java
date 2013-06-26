package com.vlingo.sdk.recognition.spotter;

import com.vlingo.sdk.VLComponent;
import java.nio.ByteBuffer;

public abstract interface VLSpotter extends VLComponent
{
  public abstract float getLastScore();

  public abstract long getSpotterContext();

  public abstract String[] getSupportedLanguageList();

  public abstract String phrasespotPipe(long paramLong1, ByteBuffer paramByteBuffer, long paramLong2);

  public abstract String processShortArray(short[] paramArrayOfShort, int paramInt1, int paramInt2);

  public abstract boolean startSpotter(VLSpotterContext paramVLSpotterContext, String paramString);

  public abstract void stopSpotter();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.spotter.VLSpotter
 * JD-Core Version:    0.6.0
 */