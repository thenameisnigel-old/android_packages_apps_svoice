package com.vlingo.core.internal.phrasespotter;

import com.vlingo.core.internal.CoreAdapter;
import java.nio.ByteBuffer;

public abstract interface VLPhraseSpotter extends CoreAdapter
{
  public abstract void destroy();

  public abstract int getDeltaD();

  public abstract int getDeltaS();

  public abstract long getPhraseContext();

  public abstract float getSpottedPhraseScore();

  public abstract void init();

  public abstract String phrasespotPipe(long paramLong1, ByteBuffer paramByteBuffer, long paramLong2);

  public abstract String processShortArray(short[] paramArrayOfShort, int paramInt1, int paramInt2);

  public abstract boolean useSeamlessFeature(String paramString);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.phrasespotter.VLPhraseSpotter
 * JD-Core Version:    0.6.0
 */