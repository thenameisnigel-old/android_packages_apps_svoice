package com.vlingo.core.internal.audio;

import android.media.AudioTrack;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

public class TonePlayer
{
  private static final int MAX_TONE_LENGTH_MS = 1000;
  private boolean asynchronous;
  private AudioTrack audioTrack;
  private AudioType audioType;
  private volatile boolean busy;
  private NotificationHandler notificationHandler;
  private PlaybackHandler toneHandler;
  private int toneResourceId;
  private int totalSamples;
  private HandlerThread workerThread;

  public TonePlayer(int paramInt, AudioType paramAudioType)
  {
    this(paramInt, paramAudioType, true);
  }

  public TonePlayer(int paramInt, AudioType paramAudioType, boolean paramBoolean)
  {
    if (paramAudioType == null)
      throw new IllegalArgumentException("audioType cannot be null");
    this.toneResourceId = paramInt;
    this.audioType = paramAudioType;
    this.asynchronous = paramBoolean;
  }

  private void cleanup()
  {
    if (this.audioTrack != null)
    {
      this.audioTrack.release();
      this.audioTrack = null;
    }
    this.workerThread.quit();
    this.workerThread = null;
    this.toneHandler = null;
    this.notificationHandler = null;
    this.busy = false;
  }

  // ERROR //
  private void playInternal()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: getfield 50	com/vlingo/core/internal/audio/TonePlayer:audioType	Lcom/vlingo/core/internal/audio/AudioType;
    //   6: getfield 99	com/vlingo/core/internal/audio/AudioType:frequency	I
    //   9: istore 6
    //   11: aload_0
    //   12: getfield 50	com/vlingo/core/internal/audio/TonePlayer:audioType	Lcom/vlingo/core/internal/audio/AudioType;
    //   15: getfield 102	com/vlingo/core/internal/audio/AudioType:channel	I
    //   18: istore 7
    //   20: aload_0
    //   21: getfield 50	com/vlingo/core/internal/audio/TonePlayer:audioType	Lcom/vlingo/core/internal/audio/AudioType;
    //   24: getfield 105	com/vlingo/core/internal/audio/AudioType:encoding	I
    //   27: istore 8
    //   29: aload_0
    //   30: getfield 50	com/vlingo/core/internal/audio/TonePlayer:audioType	Lcom/vlingo/core/internal/audio/AudioType;
    //   33: getfield 108	com/vlingo/core/internal/audio/AudioType:bytesPerSample	I
    //   36: istore 9
    //   38: iload 9
    //   40: iload 6
    //   42: sipush 1000
    //   45: imul
    //   46: imul
    //   47: sipush 1000
    //   50: idiv
    //   51: newarray byte
    //   53: astore 10
    //   55: invokestatic 114	com/vlingo/core/internal/util/ApplicationAdapter:getInstance	()Lcom/vlingo/core/internal/util/ApplicationAdapter;
    //   58: invokevirtual 118	com/vlingo/core/internal/util/ApplicationAdapter:getApplicationContext	()Landroid/content/Context;
    //   61: invokevirtual 124	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   64: aload_0
    //   65: getfield 48	com/vlingo/core/internal/audio/TonePlayer:toneResourceId	I
    //   68: invokevirtual 130	android/content/res/Resources:openRawResourceFd	(I)Landroid/content/res/AssetFileDescriptor;
    //   71: invokevirtual 136	android/content/res/AssetFileDescriptor:createInputStream	()Ljava/io/FileInputStream;
    //   74: astore_1
    //   75: iconst_0
    //   76: istore 11
    //   78: aload_1
    //   79: aload 10
    //   81: iload 11
    //   83: aload 10
    //   85: arraylength
    //   86: iload 11
    //   88: isub
    //   89: invokevirtual 142	java/io/InputStream:read	([BII)I
    //   92: istore 12
    //   94: iload 12
    //   96: bipush 255
    //   98: if_icmpeq +11 -> 109
    //   101: aload 10
    //   103: arraylength
    //   104: iload 11
    //   106: if_icmpne +178 -> 284
    //   109: aload_0
    //   110: iload 11
    //   112: iload 9
    //   114: idiv
    //   115: putfield 65	com/vlingo/core/internal/audio/TonePlayer:totalSamples	I
    //   118: iload 6
    //   120: iload 7
    //   122: iload 8
    //   124: invokestatic 146	android/media/AudioTrack:getMinBufferSize	(III)I
    //   127: iload 11
    //   129: invokestatic 152	java/lang/Math:max	(II)I
    //   132: istore 13
    //   134: invokestatic 157	com/vlingo/core/internal/bluetooth/BluetoothManager:isBluetoothAudioOn	()Z
    //   137: ifne +157 -> 294
    //   140: aload_0
    //   141: new 75	android/media/AudioTrack
    //   144: dup
    //   145: iconst_3
    //   146: iload 6
    //   148: iload 7
    //   150: iload 8
    //   152: iload 13
    //   154: iconst_0
    //   155: invokespecial 160	android/media/AudioTrack:<init>	(IIIIII)V
    //   158: putfield 61	com/vlingo/core/internal/audio/TonePlayer:audioTrack	Landroid/media/AudioTrack;
    //   161: aload_0
    //   162: getfield 61	com/vlingo/core/internal/audio/TonePlayer:audioTrack	Landroid/media/AudioTrack;
    //   165: aload 10
    //   167: iconst_0
    //   168: iload 11
    //   170: invokevirtual 163	android/media/AudioTrack:write	([BII)I
    //   173: pop
    //   174: aload_0
    //   175: getfield 61	com/vlingo/core/internal/audio/TonePlayer:audioTrack	Landroid/media/AudioTrack;
    //   178: invokevirtual 167	android/media/AudioTrack:getState	()I
    //   181: iconst_1
    //   182: if_icmpne +176 -> 358
    //   185: aload_0
    //   186: getfield 61	com/vlingo/core/internal/audio/TonePlayer:audioTrack	Landroid/media/AudioTrack;
    //   189: invokevirtual 170	android/media/AudioTrack:play	()V
    //   192: getstatic 175	android/os/Build$VERSION:SDK_INT	I
    //   195: bipush 16
    //   197: if_icmplt +155 -> 352
    //   200: aload_0
    //   201: getfield 61	com/vlingo/core/internal/audio/TonePlayer:audioTrack	Landroid/media/AudioTrack;
    //   204: invokevirtual 178	android/media/AudioTrack:getAudioSessionId	()I
    //   207: istore 18
    //   209: aload_0
    //   210: getfield 69	com/vlingo/core/internal/audio/TonePlayer:notificationHandler	Lcom/vlingo/core/internal/audio/TonePlayer$NotificationHandler;
    //   213: iconst_1
    //   214: iload 18
    //   216: iconst_0
    //   217: invokevirtual 182	com/vlingo/core/internal/audio/TonePlayer$NotificationHandler:obtainMessage	(III)Landroid/os/Message;
    //   220: invokevirtual 187	android/os/Message:sendToTarget	()V
    //   223: aload_0
    //   224: getfield 88	com/vlingo/core/internal/audio/TonePlayer:toneHandler	Lcom/vlingo/core/internal/audio/TonePlayer$PlaybackHandler;
    //   227: iconst_2
    //   228: invokevirtual 191	com/vlingo/core/internal/audio/TonePlayer$PlaybackHandler:sendEmptyMessage	(I)Z
    //   231: pop
    //   232: aload_0
    //   233: getfield 52	com/vlingo/core/internal/audio/TonePlayer:asynchronous	Z
    //   236: istore 15
    //   238: iload 15
    //   240: ifne +35 -> 275
    //   243: aload_0
    //   244: getfield 90	com/vlingo/core/internal/audio/TonePlayer:busy	Z
    //   247: ifeq +28 -> 275
    //   250: invokestatic 114	com/vlingo/core/internal/util/ApplicationAdapter:getInstance	()Lcom/vlingo/core/internal/util/ApplicationAdapter;
    //   253: invokevirtual 195	com/vlingo/core/internal/util/ApplicationAdapter:getVlingoApp	()Lcom/vlingo/core/internal/util/VlingoApplicationInterface;
    //   256: invokeinterface 200 1 0
    //   261: ifeq +126 -> 387
    //   264: ldc2_w 201
    //   267: invokestatic 208	java/lang/Thread:sleep	(J)V
    //   270: goto -27 -> 243
    //   273: astore 17
    //   275: aload_1
    //   276: ifnull +7 -> 283
    //   279: aload_1
    //   280: invokevirtual 211	java/io/InputStream:close	()V
    //   283: return
    //   284: iload 11
    //   286: iload 12
    //   288: iadd
    //   289: istore 11
    //   291: goto -213 -> 78
    //   294: aload_0
    //   295: new 75	android/media/AudioTrack
    //   298: dup
    //   299: bipush 6
    //   301: iload 6
    //   303: iload 7
    //   305: iload 8
    //   307: iload 13
    //   309: iconst_0
    //   310: invokespecial 160	android/media/AudioTrack:<init>	(IIIIII)V
    //   313: putfield 61	com/vlingo/core/internal/audio/TonePlayer:audioTrack	Landroid/media/AudioTrack;
    //   316: goto -155 -> 161
    //   319: astore 4
    //   321: aload_0
    //   322: getfield 69	com/vlingo/core/internal/audio/TonePlayer:notificationHandler	Lcom/vlingo/core/internal/audio/TonePlayer$NotificationHandler;
    //   325: iconst_2
    //   326: invokevirtual 214	com/vlingo/core/internal/audio/TonePlayer$NotificationHandler:obtainMessage	(I)Landroid/os/Message;
    //   329: invokevirtual 187	android/os/Message:sendToTarget	()V
    //   332: aload_0
    //   333: invokespecial 73	com/vlingo/core/internal/audio/TonePlayer:cleanup	()V
    //   336: aload_1
    //   337: ifnull -54 -> 283
    //   340: aload_1
    //   341: invokevirtual 211	java/io/InputStream:close	()V
    //   344: goto -61 -> 283
    //   347: astore 5
    //   349: goto -66 -> 283
    //   352: iconst_0
    //   353: istore 18
    //   355: goto -146 -> 209
    //   358: aload_0
    //   359: getfield 69	com/vlingo/core/internal/audio/TonePlayer:notificationHandler	Lcom/vlingo/core/internal/audio/TonePlayer$NotificationHandler;
    //   362: iconst_2
    //   363: invokevirtual 214	com/vlingo/core/internal/audio/TonePlayer$NotificationHandler:obtainMessage	(I)Landroid/os/Message;
    //   366: invokevirtual 187	android/os/Message:sendToTarget	()V
    //   369: aload_0
    //   370: invokespecial 73	com/vlingo/core/internal/audio/TonePlayer:cleanup	()V
    //   373: goto -141 -> 232
    //   376: astore_2
    //   377: aload_1
    //   378: ifnull +7 -> 385
    //   381: aload_1
    //   382: invokevirtual 211	java/io/InputStream:close	()V
    //   385: aload_2
    //   386: athrow
    //   387: aload_0
    //   388: getfield 69	com/vlingo/core/internal/audio/TonePlayer:notificationHandler	Lcom/vlingo/core/internal/audio/TonePlayer$NotificationHandler;
    //   391: iconst_2
    //   392: invokevirtual 214	com/vlingo/core/internal/audio/TonePlayer$NotificationHandler:obtainMessage	(I)Landroid/os/Message;
    //   395: invokevirtual 187	android/os/Message:sendToTarget	()V
    //   398: aload_0
    //   399: invokespecial 73	com/vlingo/core/internal/audio/TonePlayer:cleanup	()V
    //   402: goto -159 -> 243
    //   405: astore 16
    //   407: goto -124 -> 283
    //   410: astore_3
    //   411: goto -26 -> 385
    //
    // Exception table:
    //   from	to	target	type
    //   243	270	273	java/lang/InterruptedException
    //   387	402	273	java/lang/InterruptedException
    //   2	238	319	java/io/IOException
    //   243	270	319	java/io/IOException
    //   294	316	319	java/io/IOException
    //   358	373	319	java/io/IOException
    //   387	402	319	java/io/IOException
    //   340	344	347	java/io/IOException
    //   2	238	376	finally
    //   243	270	376	finally
    //   294	316	376	finally
    //   321	336	376	finally
    //   358	373	376	finally
    //   387	402	376	finally
    //   279	283	405	java/io/IOException
    //   381	385	410	java/io/IOException
  }

  public void play()
  {
    play(null);
  }

  public void play(Listener paramListener)
  {
    if (this.busy)
      throw new IllegalStateException("Busy");
    this.busy = true;
    this.notificationHandler = new NotificationHandler(paramListener);
    this.workerThread = new HandlerThread("TonePlayer");
    this.workerThread.start();
    this.toneHandler = new PlaybackHandler(this.workerThread.getLooper());
    if (this.asynchronous)
      this.toneHandler.sendEmptyMessage(1);
    while (true)
    {
      return;
      playInternal();
    }
  }

  public static abstract interface Listener
  {
    public abstract void onStarted(int paramInt);

    public abstract void onStopped();
  }

  private static class NotificationHandler extends Handler
  {
    private static final int STARTED = 1;
    private static final int STOPPED = 2;
    private TonePlayer.Listener listener;

    NotificationHandler(TonePlayer.Listener paramListener)
    {
      this.listener = paramListener;
    }

    public void handleMessage(Message paramMessage)
    {
      if (this.listener == null);
      while (true)
      {
        return;
        switch (paramMessage.what)
        {
        default:
          break;
        case 1:
          this.listener.onStarted(paramMessage.arg1);
          break;
        case 2:
          this.listener.onStopped();
        }
      }
    }
  }

  private class PlaybackHandler extends Handler
  {
    private static final int PLAY = 1;
    private static final int WAIT_FOR_STOP = 2;

    PlaybackHandler(Looper arg2)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 2:
      }
      while (true)
      {
        return;
        TonePlayer.this.playInternal();
        continue;
        if (TonePlayer.this.audioTrack.getPlaybackHeadPosition() >= TonePlayer.this.totalSamples)
        {
          TonePlayer.this.audioTrack.stop();
          TonePlayer.this.notificationHandler.obtainMessage(2).sendToTarget();
          TonePlayer.this.cleanup();
          continue;
        }
        sendEmptyMessageDelayed(2, 10L);
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.TonePlayer
 * JD-Core Version:    0.6.0
 */