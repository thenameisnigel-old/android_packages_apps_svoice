package com.vlingo.core.internal.audio;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;

public abstract class ASyncAudioDataSink
  implements AudioFilterAdapter, Handler.Callback
{
  public static final int FILTER_WITHOUT_SOURCE = 2;
  public static final int FILTER_WITH_SOURCE = 1;
  private Handler handler;
  private HandlerThread handlerThread;

  public int filter(short[] paramArrayOfShort, int paramInt1, int paramInt2)
  {
    this.handler.sendMessage(this.handler.obtainMessage(2, new MicData(paramArrayOfShort, paramInt1, paramInt2, -1)));
    return 0;
  }

  public int filter(short[] paramArrayOfShort, int paramInt1, int paramInt2, int paramInt3)
  {
    this.handler.sendMessage(this.handler.obtainMessage(1, new MicData(paramArrayOfShort, paramInt1, paramInt2, paramInt3)));
    return 0;
  }

  public boolean handleMessage(Message paramMessage)
  {
    MicData localMicData = (MicData)paramMessage.obj;
    switch (paramMessage.what)
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      return false;
      sink(localMicData.getAudioData(), localMicData.getOffsetInShorts(), localMicData.getSizeInShorts(), localMicData.getAudioSourceId());
      continue;
      sink(localMicData.getAudioData(), localMicData.getOffsetInShorts(), localMicData.getSizeInShorts());
    }
  }

  public void init(int paramInt1, int paramInt2, int paramInt3)
  {
    init(paramInt1, paramInt2, paramInt3, 0);
  }

  public void init(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.handlerThread = new HandlerThread("ASyncAudioDataSink", paramInt4);
    this.handlerThread.start();
    this.handler = new Handler(this.handlerThread.getLooper(), this);
  }

  public boolean quit()
  {
    this.handlerThread.quit();
    return false;
  }

  public abstract int sink(short[] paramArrayOfShort, int paramInt1, int paramInt2);

  public abstract int sink(short[] paramArrayOfShort, int paramInt1, int paramInt2, int paramInt3);

  public static class MicData
  {
    private final short[] audioData;
    private final int audioSourceId;
    private final int offsetInShorts;
    private final int sizeInShorts;

    public MicData(short[] paramArrayOfShort, int paramInt1, int paramInt2, int paramInt3)
    {
      this.audioData = new short[paramArrayOfShort.length];
      System.arraycopy(paramArrayOfShort, 0, this.audioData, 0, paramArrayOfShort.length);
      this.offsetInShorts = paramInt1;
      this.sizeInShorts = paramInt2;
      this.audioSourceId = paramInt3;
    }

    public short[] getAudioData()
    {
      return this.audioData;
    }

    public int getAudioSourceId()
    {
      return this.audioSourceId;
    }

    public int getOffsetInShorts()
    {
      return this.offsetInShorts;
    }

    public int getSizeInShorts()
    {
      return this.sizeInShorts;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.ASyncAudioDataSink
 * JD-Core Version:    0.6.0
 */