package com.vlingo.core.internal.audio;

import com.vlingo.core.internal.CoreAdapter;

public abstract interface AudioFilterAdapter extends CoreAdapter
{
  public abstract int filter(short[] paramArrayOfShort, int paramInt1, int paramInt2);

  public abstract int filter(short[] paramArrayOfShort, int paramInt1, int paramInt2, int paramInt3);

  public abstract void init(int paramInt1, int paramInt2, int paramInt3);

  public abstract boolean quit();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.AudioFilterAdapter
 * JD-Core Version:    0.6.0
 */