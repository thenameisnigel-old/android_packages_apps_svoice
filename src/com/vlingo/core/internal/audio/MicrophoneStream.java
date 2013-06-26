package com.vlingo.core.internal.audio;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaSyncEvent;
import com.vlingo.core.internal.CoreAdapterRegistrar;
import com.vlingo.core.internal.CoreAdapterRegistrar.AdapterList;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.DeviceWorkarounds;
import com.vlingo.sdk.recognition.VLRecognitionContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Vector;

public class MicrophoneStream extends InputStream
{
  public static final int BLUETOOTH_SAMPLE_RATE = 8000;
  public static final int DEFAULT_AUDIO_FORMAT = 2;
  public static final int DEFAULT_BUFFER_DURATION = 5;
  public static final int DEFAULT_CHANNEL_CONFIG = 2;
  public static final int DEFAULT_SAMPLE_RATE = 16000;
  private static final Object LOCK;
  public static final int SAMPLE_RATE_16KHZ = 16000;
  public static final int SAMPLE_RATE_8KHZ = 8000;
  private static final int START_RECORDING_MAX_RETRY = 5;
  private static final int START_RECORDING_RETRY_DELAY = 15;
  private static final int chan = 16;
  private static TaskType currentOwner = null;
  private static final int encoding = 2;
  public static InputStream testStream;
  private int bufferSize;
  private Vector<ByteBuffer> buffers;
  private RecorderState currentState;
  private AudioLogger mAudioLogger;
  private int mAudioSource;
  private short[] mBufferedData;
  private int mBufferedDataIndex;
  private AudioRecord mRecorder;
  private int mSampleRate;
  public int maxamp;
  private AudioFilterAdapter noiseCancelFilter;
  private volatile boolean noiseCancelFilterEnabled;
  AudioTrack oTrack;
  byte[] playData;
  private byte remainingByte;
  private int remainingByteOffset;
  public int samplerate;

  static
  {
    LOCK = new Object();
  }

  // ERROR //
  private MicrophoneStream(VLRecognitionContext paramVLRecognitionContext, TaskType paramTaskType, AudioSourceType paramAudioSourceType, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 88	java/io/InputStream:<init>	()V
    //   4: aload_0
    //   5: aconst_null
    //   6: putfield 90	com/vlingo/core/internal/audio/MicrophoneStream:oTrack	Landroid/media/AudioTrack;
    //   9: aload_0
    //   10: aconst_null
    //   11: putfield 92	com/vlingo/core/internal/audio/MicrophoneStream:playData	[B
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 94	com/vlingo/core/internal/audio/MicrophoneStream:bufferSize	I
    //   19: aload_0
    //   20: new 96	java/util/Vector
    //   23: dup
    //   24: invokespecial 97	java/util/Vector:<init>	()V
    //   27: putfield 99	com/vlingo/core/internal/audio/MicrophoneStream:buffers	Ljava/util/Vector;
    //   30: aload_0
    //   31: iconst_0
    //   32: putfield 101	com/vlingo/core/internal/audio/MicrophoneStream:maxamp	I
    //   35: invokestatic 105	com/vlingo/core/internal/audio/MicrophoneStream:useVoiceRecognitionAudioSource	()Z
    //   38: ifeq +285 -> 323
    //   41: bipush 6
    //   43: istore 5
    //   45: aconst_null
    //   46: astore 6
    //   48: getstatic 111	com/vlingo/core/internal/CoreAdapterRegistrar$AdapterList:AudioSourceSelector	Lcom/vlingo/core/internal/CoreAdapterRegistrar$AdapterList;
    //   51: invokestatic 117	com/vlingo/core/internal/CoreAdapterRegistrar:get	(Lcom/vlingo/core/internal/CoreAdapterRegistrar$AdapterList;)Ljava/lang/Class;
    //   54: astore 7
    //   56: aload 7
    //   58: ifnull +13 -> 71
    //   61: aload 7
    //   63: invokevirtual 123	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   66: checkcast 125	com/vlingo/core/internal/audio/AudioSourceUtil
    //   69: astore 6
    //   71: aload 6
    //   73: ifnull +14 -> 87
    //   76: aload 6
    //   78: aload_2
    //   79: aload_3
    //   80: invokeinterface 129 3 0
    //   85: istore 5
    //   87: invokestatic 135	com/vlingo/core/internal/audio/AudioDevice:getInstance	()Lcom/vlingo/core/internal/audio/AudioDevice;
    //   90: invokevirtual 138	com/vlingo/core/internal/audio/AudioDevice:isAudioBluetooth	()Z
    //   93: ifeq +256 -> 349
    //   96: sipush 8000
    //   99: istore 8
    //   101: aload_0
    //   102: iload 8
    //   104: putfield 140	com/vlingo/core/internal/audio/MicrophoneStream:mSampleRate	I
    //   107: aload_0
    //   108: getfield 140	com/vlingo/core/internal/audio/MicrophoneStream:mSampleRate	I
    //   111: iconst_2
    //   112: iconst_2
    //   113: invokestatic 146	android/media/AudioRecord:getMinBufferSize	(III)I
    //   116: istore 9
    //   118: iconst_5
    //   119: aload_0
    //   120: getfield 140	com/vlingo/core/internal/audio/MicrophoneStream:mSampleRate	I
    //   123: imul
    //   124: istore 10
    //   126: iconst_2
    //   127: iconst_2
    //   128: if_icmpne +229 -> 357
    //   131: iconst_2
    //   132: istore 11
    //   134: iload 10
    //   136: iload 11
    //   138: imul
    //   139: istore 12
    //   141: aload_0
    //   142: aload_1
    //   143: invokespecial 150	com/vlingo/core/internal/audio/MicrophoneStream:initializeNoiseCancelFilter	(Lcom/vlingo/sdk/recognition/VLRecognitionContext;)V
    //   146: getstatic 69	com/vlingo/core/internal/audio/MicrophoneStream:currentOwner	Lcom/vlingo/core/internal/audio/MicrophoneStream$TaskType;
    //   149: getstatic 153	com/vlingo/core/internal/audio/MicrophoneStream$TaskType:RECOGNITION	Lcom/vlingo/core/internal/audio/MicrophoneStream$TaskType;
    //   152: if_acmpne +7 -> 159
    //   155: aload_0
    //   156: invokevirtual 156	com/vlingo/core/internal/audio/MicrophoneStream:enableAudioFiltering	()V
    //   159: aload_0
    //   160: new 142	android/media/AudioRecord
    //   163: dup
    //   164: iload 5
    //   166: aload_0
    //   167: getfield 140	com/vlingo/core/internal/audio/MicrophoneStream:mSampleRate	I
    //   170: iconst_2
    //   171: iconst_2
    //   172: iload 9
    //   174: iload 12
    //   176: invokestatic 162	java/lang/Math:max	(II)I
    //   179: invokespecial 165	android/media/AudioRecord:<init>	(IIIII)V
    //   182: putfield 167	com/vlingo/core/internal/audio/MicrophoneStream:mRecorder	Landroid/media/AudioRecord;
    //   185: aload_0
    //   186: getfield 167	com/vlingo/core/internal/audio/MicrophoneStream:mRecorder	Landroid/media/AudioRecord;
    //   189: ifnonnull +39 -> 228
    //   192: invokestatic 105	com/vlingo/core/internal/audio/MicrophoneStream:useVoiceRecognitionAudioSource	()Z
    //   195: ifeq +326 -> 521
    //   198: bipush 6
    //   200: istore 5
    //   202: aload_0
    //   203: new 142	android/media/AudioRecord
    //   206: dup
    //   207: iload 5
    //   209: aload_0
    //   210: getfield 140	com/vlingo/core/internal/audio/MicrophoneStream:mSampleRate	I
    //   213: iconst_2
    //   214: iconst_2
    //   215: iload 9
    //   217: iload 12
    //   219: invokestatic 162	java/lang/Math:max	(II)I
    //   222: invokespecial 165	android/media/AudioRecord:<init>	(IIIII)V
    //   225: putfield 167	com/vlingo/core/internal/audio/MicrophoneStream:mRecorder	Landroid/media/AudioRecord;
    //   228: aload_0
    //   229: iload 5
    //   231: putfield 169	com/vlingo/core/internal/audio/MicrophoneStream:mAudioSource	I
    //   234: aload_0
    //   235: getfield 167	com/vlingo/core/internal/audio/MicrophoneStream:mRecorder	Landroid/media/AudioRecord;
    //   238: invokevirtual 173	android/media/AudioRecord:getState	()I
    //   241: iconst_1
    //   242: if_icmpeq +131 -> 373
    //   245: getstatic 69	com/vlingo/core/internal/audio/MicrophoneStream:currentOwner	Lcom/vlingo/core/internal/audio/MicrophoneStream$TaskType;
    //   248: invokevirtual 177	com/vlingo/core/internal/audio/MicrophoneStream$TaskType:name	()Ljava/lang/String;
    //   251: astore 22
    //   253: new 179	java/lang/StringBuilder
    //   256: dup
    //   257: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   260: ldc 182
    //   262: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: iload 5
    //   267: invokevirtual 189	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   270: ldc 191
    //   272: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: aload 22
    //   277: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: invokevirtual 194	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   283: astore 23
    //   285: aconst_null
    //   286: putstatic 69	com/vlingo/core/internal/audio/MicrophoneStream:currentOwner	Lcom/vlingo/core/internal/audio/MicrophoneStream$TaskType;
    //   289: aload_0
    //   290: getfield 167	com/vlingo/core/internal/audio/MicrophoneStream:mRecorder	Landroid/media/AudioRecord;
    //   293: invokevirtual 197	android/media/AudioRecord:release	()V
    //   296: aload_0
    //   297: aconst_null
    //   298: putfield 167	com/vlingo/core/internal/audio/MicrophoneStream:mRecorder	Landroid/media/AudioRecord;
    //   301: new 199	java/lang/IllegalStateException
    //   304: dup
    //   305: aload 23
    //   307: invokespecial 202	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   310: athrow
    //   311: astore 13
    //   313: new 199	java/lang/IllegalStateException
    //   316: dup
    //   317: ldc 204
    //   319: invokespecial 202	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   322: athrow
    //   323: iconst_1
    //   324: istore 5
    //   326: goto -281 -> 45
    //   329: astore 25
    //   331: aload 25
    //   333: invokevirtual 207	java/lang/InstantiationException:printStackTrace	()V
    //   336: goto -265 -> 71
    //   339: astore 24
    //   341: aload 24
    //   343: invokevirtual 208	java/lang/IllegalAccessException:printStackTrace	()V
    //   346: goto -275 -> 71
    //   349: sipush 16000
    //   352: istore 8
    //   354: goto -253 -> 101
    //   357: iconst_1
    //   358: istore 11
    //   360: goto -226 -> 134
    //   363: astore 14
    //   365: aload_0
    //   366: aconst_null
    //   367: putfield 167	com/vlingo/core/internal/audio/MicrophoneStream:mRecorder	Landroid/media/AudioRecord;
    //   370: goto -185 -> 185
    //   373: invokestatic 211	com/vlingo/core/internal/audio/MicrophoneStream:lockFileCreateIfNotExist	()V
    //   376: iconst_5
    //   377: istore 15
    //   379: iload 4
    //   381: ifeq +91 -> 472
    //   384: aload_0
    //   385: iload 4
    //   387: invokevirtual 215	com/vlingo/core/internal/audio/MicrophoneStream:startRecordingJB	(I)V
    //   390: aload_0
    //   391: getfield 167	com/vlingo/core/internal/audio/MicrophoneStream:mRecorder	Landroid/media/AudioRecord;
    //   394: invokevirtual 218	android/media/AudioRecord:getRecordingState	()I
    //   397: istore 18
    //   399: iload 18
    //   401: iconst_3
    //   402: if_icmpne +89 -> 491
    //   405: invokestatic 221	com/vlingo/core/internal/audio/MicrophoneStream:lockFileDeleteIfExists	()Z
    //   408: pop
    //   409: aload_0
    //   410: getfield 167	com/vlingo/core/internal/audio/MicrophoneStream:mRecorder	Landroid/media/AudioRecord;
    //   413: invokevirtual 218	android/media/AudioRecord:getRecordingState	()I
    //   416: iconst_3
    //   417: if_icmpeq +91 -> 508
    //   420: getstatic 69	com/vlingo/core/internal/audio/MicrophoneStream:currentOwner	Lcom/vlingo/core/internal/audio/MicrophoneStream$TaskType;
    //   423: invokevirtual 177	com/vlingo/core/internal/audio/MicrophoneStream$TaskType:name	()Ljava/lang/String;
    //   426: astore 21
    //   428: aconst_null
    //   429: putstatic 69	com/vlingo/core/internal/audio/MicrophoneStream:currentOwner	Lcom/vlingo/core/internal/audio/MicrophoneStream$TaskType;
    //   432: aload_0
    //   433: getfield 167	com/vlingo/core/internal/audio/MicrophoneStream:mRecorder	Landroid/media/AudioRecord;
    //   436: invokevirtual 197	android/media/AudioRecord:release	()V
    //   439: aload_0
    //   440: aconst_null
    //   441: putfield 167	com/vlingo/core/internal/audio/MicrophoneStream:mRecorder	Landroid/media/AudioRecord;
    //   444: new 199	java/lang/IllegalStateException
    //   447: dup
    //   448: new 179	java/lang/StringBuilder
    //   451: dup
    //   452: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   455: ldc 223
    //   457: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   460: aload 21
    //   462: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   465: invokevirtual 194	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   468: invokespecial 202	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   471: athrow
    //   472: aload_0
    //   473: getfield 167	com/vlingo/core/internal/audio/MicrophoneStream:mRecorder	Landroid/media/AudioRecord;
    //   476: invokevirtual 226	android/media/AudioRecord:startRecording	()V
    //   479: goto -89 -> 390
    //   482: astore 16
    //   484: invokestatic 221	com/vlingo/core/internal/audio/MicrophoneStream:lockFileDeleteIfExists	()Z
    //   487: pop
    //   488: aload 16
    //   490: athrow
    //   491: ldc2_w 227
    //   494: invokestatic 234	java/lang/Thread:sleep	(J)V
    //   497: iinc 15 255
    //   500: iload 15
    //   502: ifgt -123 -> 379
    //   505: goto -100 -> 405
    //   508: aload_0
    //   509: getstatic 237	com/vlingo/core/internal/audio/MicrophoneStream$RecorderState:STARTED	Lcom/vlingo/core/internal/audio/MicrophoneStream$RecorderState;
    //   512: putfield 239	com/vlingo/core/internal/audio/MicrophoneStream:currentState	Lcom/vlingo/core/internal/audio/MicrophoneStream$RecorderState;
    //   515: return
    //   516: astore 19
    //   518: goto -21 -> 497
    //   521: iconst_1
    //   522: istore 5
    //   524: goto -322 -> 202
    //
    // Exception table:
    //   from	to	target	type
    //   146	159	311	java/lang/Throwable
    //   159	185	311	java/lang/Throwable
    //   185	311	311	java/lang/Throwable
    //   365	376	311	java/lang/Throwable
    //   405	409	311	java/lang/Throwable
    //   484	491	311	java/lang/Throwable
    //   61	71	329	java/lang/InstantiationException
    //   61	71	339	java/lang/IllegalAccessException
    //   159	185	363	java/lang/IllegalArgumentException
    //   384	399	482	finally
    //   472	479	482	finally
    //   491	497	482	finally
    //   491	497	516	java/lang/InterruptedException
  }

  private static File getLockFile()
  {
    return new File(ApplicationAdapter.getInstance().getApplicationContext().getCacheDir(), "mic.lock.file");
  }

  private void initializeNoiseCancelFilter(VLRecognitionContext paramVLRecognitionContext)
  {
    Class localClass = CoreAdapterRegistrar.get(CoreAdapterRegistrar.AdapterList.NoiseCancel);
    if (localClass != null)
      while (true)
      {
        try
        {
          this.noiseCancelFilter = ((AudioFilterAdapter)localClass.newInstance());
          AudioFilterAdapter localAudioFilterAdapter = this.noiseCancelFilter;
          int i = this.mSampleRate;
          if (paramVLRecognitionContext != null)
            continue;
          int j = 1500;
          break label102;
          localAudioFilterAdapter.init(i, j, m);
          break;
          j = paramVLRecognitionContext.getSpeechEndpointTimeout();
          break label102;
          int k = paramVLRecognitionContext.getNoSpeechEndPointTimeout();
          m = k;
          continue;
        }
        catch (InstantiationException localInstantiationException)
        {
          localInstantiationException.printStackTrace();
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          localIllegalAccessException.printStackTrace();
        }
        label102: if (paramVLRecognitionContext != null)
          continue;
        int m = 5000;
      }
  }

  // ERROR //
  public static void lockFileCreateIfNotExist()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 285	com/vlingo/core/internal/audio/MicrophoneStream:getLockFile	()Ljava/io/File;
    //   6: astore_2
    //   7: aload_2
    //   8: invokevirtual 288	java/io/File:exists	()Z
    //   11: ifne +8 -> 19
    //   14: aload_2
    //   15: invokevirtual 291	java/io/File:createNewFile	()Z
    //   18: pop
    //   19: ldc 2
    //   21: monitorexit
    //   22: return
    //   23: astore_1
    //   24: ldc 2
    //   26: monitorexit
    //   27: aload_1
    //   28: athrow
    //   29: astore_0
    //   30: goto -11 -> 19
    //
    // Exception table:
    //   from	to	target	type
    //   3	19	23	finally
    //   3	19	29	java/lang/Exception
  }

  // ERROR //
  public static boolean lockFileDeleteIfExists()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 285	com/vlingo/core/internal/audio/MicrophoneStream:getLockFile	()Ljava/io/File;
    //   6: astore_3
    //   7: aload_3
    //   8: invokevirtual 288	java/io/File:exists	()Z
    //   11: istore_2
    //   12: iload_2
    //   13: ifeq +8 -> 21
    //   16: aload_3
    //   17: invokevirtual 294	java/io/File:delete	()Z
    //   20: pop
    //   21: ldc 2
    //   23: monitorexit
    //   24: iload_2
    //   25: ireturn
    //   26: astore_1
    //   27: iconst_0
    //   28: istore_2
    //   29: goto -8 -> 21
    //   32: astore_0
    //   33: ldc 2
    //   35: monitorexit
    //   36: aload_0
    //   37: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   3	21	26	java/lang/Exception
    //   3	21	32	finally
  }

  public static MicrophoneStream request(VLRecognitionContext paramVLRecognitionContext, TaskType paramTaskType)
  {
    return request(paramVLRecognitionContext, paramTaskType, 0);
  }

  public static MicrophoneStream request(VLRecognitionContext paramVLRecognitionContext, TaskType paramTaskType, int paramInt)
  {
    return request(paramVLRecognitionContext, paramTaskType, AudioSourceType.UNSPECIFIED, paramInt);
  }

  // ERROR //
  public static MicrophoneStream request(VLRecognitionContext paramVLRecognitionContext, TaskType paramTaskType, AudioSourceType paramAudioSourceType, int paramInt)
  {
    // Byte code:
    //   0: getstatic 76	com/vlingo/core/internal/audio/MicrophoneStream:LOCK	Ljava/lang/Object;
    //   3: astore 4
    //   5: aload 4
    //   7: monitorenter
    //   8: getstatic 69	com/vlingo/core/internal/audio/MicrophoneStream:currentOwner	Lcom/vlingo/core/internal/audio/MicrophoneStream$TaskType;
    //   11: ifnull +57 -> 68
    //   14: new 199	java/lang/IllegalStateException
    //   17: dup
    //   18: new 179	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   25: ldc_w 308
    //   28: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: aload_1
    //   32: invokevirtual 177	com/vlingo/core/internal/audio/MicrophoneStream$TaskType:name	()Ljava/lang/String;
    //   35: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: ldc_w 310
    //   41: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: getstatic 69	com/vlingo/core/internal/audio/MicrophoneStream:currentOwner	Lcom/vlingo/core/internal/audio/MicrophoneStream$TaskType;
    //   47: invokevirtual 177	com/vlingo/core/internal/audio/MicrophoneStream$TaskType:name	()Ljava/lang/String;
    //   50: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 194	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokespecial 202	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   59: athrow
    //   60: astore 5
    //   62: aload 4
    //   64: monitorexit
    //   65: aload 5
    //   67: athrow
    //   68: aload_1
    //   69: putstatic 69	com/vlingo/core/internal/audio/MicrophoneStream:currentOwner	Lcom/vlingo/core/internal/audio/MicrophoneStream$TaskType;
    //   72: new 2	com/vlingo/core/internal/audio/MicrophoneStream
    //   75: dup
    //   76: aload_0
    //   77: aload_1
    //   78: aload_2
    //   79: iload_3
    //   80: invokespecial 312	com/vlingo/core/internal/audio/MicrophoneStream:<init>	(Lcom/vlingo/sdk/recognition/VLRecognitionContext;Lcom/vlingo/core/internal/audio/MicrophoneStream$TaskType;Lcom/vlingo/core/internal/audio/MicrophoneStream$AudioSourceType;I)V
    //   83: astore 6
    //   85: aload 4
    //   87: monitorexit
    //   88: aload 6
    //   90: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   8	65	60	finally
    //   68	88	60	finally
  }

  private static boolean useVoiceRecognitionAudioSource()
  {
    return DeviceWorkarounds.useVoiceRecognitionAudioPath();
  }

  public void close()
    throws IOException
  {
    monitorenter;
    try
    {
      synchronized (LOCK)
      {
        if (this.currentState == RecorderState.STARTED)
        {
          this.mBufferedData = null;
          this.mRecorder.stop();
          this.mRecorder.release();
          this.mRecorder = null;
          this.currentState = RecorderState.CLOSED;
          if (this.mAudioLogger != null)
          {
            this.mAudioLogger.dumpToFile();
            this.mAudioLogger = null;
          }
          currentOwner = null;
        }
        if (this.noiseCancelFilter != null)
          this.noiseCancelFilter.quit();
        monitorexit;
        return;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public void disableAudioFiltering()
  {
    monitorenter;
    try
    {
      this.noiseCancelFilterEnabled = false;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void enableAudioFiltering()
  {
    monitorenter;
    try
    {
      this.noiseCancelFilterEnabled = true;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public AudioRecord getRecorder()
  {
    return this.mRecorder;
  }

  public int getSize()
  {
    monitorenter;
    try
    {
      int i = this.buffers.size();
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean is16KHz()
  {
    if (this.mSampleRate == 16000);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean is8KHz()
  {
    if (this.mSampleRate == 8000);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isRecording()
  {
    monitorenter;
    try
    {
      if (this.currentState == RecorderState.STARTED)
      {
        int j = this.mRecorder.getRecordingState();
        if (j == 3)
        {
          i = 1;
          return i;
        }
      }
      int i = 0;
    }
    finally
    {
      monitorexit;
    }
  }

  public int read()
    throws IOException
  {
    monitorenter;
    try
    {
      byte[] arrayOfByte = new byte[1];
      if (read(arrayOfByte, 0, 1) > 0)
      {
        i = arrayOfByte[0];
        return i;
      }
      int i = -1;
    }
    finally
    {
      monitorexit;
    }
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    monitorenter;
    try
    {
      int i = super.read(paramArrayOfByte);
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    monitorenter;
    int i = paramInt2 + 1;
    while (true)
    {
      try
      {
        int j = i / 2 - paramInt2 * this.remainingByteOffset % 2;
        int k = paramInt2;
        short[] arrayOfShort = new short[j];
        int m = read(arrayOfShort, 0, j);
        if (this.remainingByteOffset > 0)
        {
          paramArrayOfByte[paramInt1] = this.remainingByte;
          paramInt1++;
          k--;
          break label229;
          if (i1 >= m)
            continue;
          int i7 = arrayOfShort[i1];
          paramArrayOfByte[paramInt1] = (byte)(i7 & 0xFF);
          paramInt1++;
          k--;
          byte b = (byte)(0xFF & i7 >> 8);
          if (k <= 0)
            continue;
          paramArrayOfByte[paramInt1] = b;
          paramInt1++;
          k--;
          break label238;
          this.remainingByte = b;
          n = 1;
          break label238;
          if (m != -1)
            continue;
          if (this.remainingByteOffset <= 0)
            continue;
          this.remainingByteOffset = 0;
          int i6 = 1;
          return i6;
          i6 = -1;
          continue;
          int i2 = m * 2;
          int i3 = i2 + this.remainingByteOffset;
          if (n == 0)
            continue;
          int i4 = 1;
          this.remainingByteOffset = i4;
          int i5 = Math.min(i3, paramInt2);
          i6 = i5;
          continue;
          i4 = 0;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      label229: int n = 0;
      int i1 = 0;
      continue;
      label238: i1++;
    }
  }

  public int read(short[] paramArrayOfShort, int paramInt1, int paramInt2)
  {
    monitorenter;
    while (true)
    {
      try
      {
        RecorderState localRecorderState1 = this.currentState;
        RecorderState localRecorderState2 = RecorderState.STARTED;
        if (localRecorderState1 == localRecorderState2)
          continue;
        k = -1;
        return k;
        i = 0;
        if (this.mBufferedData != null)
        {
          int m = this.mBufferedData.length - this.mBufferedDataIndex;
          if (paramInt2 <= m)
            continue;
          paramInt2 = m;
          System.arraycopy(this.mBufferedData, this.mBufferedDataIndex, paramArrayOfShort, paramInt1, paramInt2);
          this.mBufferedDataIndex = (paramInt2 + this.mBufferedDataIndex);
          if (this.mBufferedDataIndex < this.mBufferedData.length)
            break label199;
          this.mBufferedData = null;
          break label199;
          if (k <= 0)
            continue;
          if ((!this.noiseCancelFilterEnabled) || (this.noiseCancelFilter == null))
            continue;
          k = this.noiseCancelFilter.filter(paramArrayOfShort, paramInt1, k, this.mAudioSource);
          if ((i == 0) || (this.mAudioLogger == null))
            continue;
          this.mAudioLogger.writeData(paramArrayOfShort, paramInt1, k);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      int j = this.mRecorder.read(paramArrayOfShort, paramInt1, paramInt2);
      int k = j;
      int i = 1;
      continue;
      label199: k = paramInt2;
    }
  }

  public void setAudioLogger(AudioLogger paramAudioLogger)
  {
    this.mAudioLogger = paramAudioLogger;
  }

  public void setBufferedData(short[] paramArrayOfShort, int paramInt)
  {
    if ((paramArrayOfShort != null) && (paramInt > 0) && (paramArrayOfShort.length >= 0))
    {
      this.mBufferedData = new short[paramInt];
      System.arraycopy(paramArrayOfShort, 0, this.mBufferedData, 0, paramInt);
    }
  }

  @TargetApi(16)
  public void startRecordingJB(int paramInt)
  {
    MediaSyncEvent localMediaSyncEvent = MediaSyncEvent.createEvent(1);
    localMediaSyncEvent.setAudioSessionId(paramInt);
    this.mRecorder.startRecording(localMediaSyncEvent);
  }

  public static enum AudioSourceType
  {
    static
    {
      ASR_DRIVING_MODE = new AudioSourceType("ASR_DRIVING_MODE", 1);
      SPOTTER_REGULAR = new AudioSourceType("SPOTTER_REGULAR", 2);
      SPOTTER_DRIVING_MODE = new AudioSourceType("SPOTTER_DRIVING_MODE", 3);
      GENERAL_VOICE_RECOGNITION = new AudioSourceType("GENERAL_VOICE_RECOGNITION", 4);
      UNSPECIFIED = new AudioSourceType("UNSPECIFIED", 5);
      AudioSourceType[] arrayOfAudioSourceType = new AudioSourceType[6];
      arrayOfAudioSourceType[0] = ASR_REGULAR;
      arrayOfAudioSourceType[1] = ASR_DRIVING_MODE;
      arrayOfAudioSourceType[2] = SPOTTER_REGULAR;
      arrayOfAudioSourceType[3] = SPOTTER_DRIVING_MODE;
      arrayOfAudioSourceType[4] = GENERAL_VOICE_RECOGNITION;
      arrayOfAudioSourceType[5] = UNSPECIFIED;
      $VALUES = arrayOfAudioSourceType;
    }
  }

  private static enum RecorderState
  {
    static
    {
      CLOSED = new RecorderState("CLOSED", 1);
      RecorderState[] arrayOfRecorderState = new RecorderState[2];
      arrayOfRecorderState[0] = STARTED;
      arrayOfRecorderState[1] = CLOSED;
      $VALUES = arrayOfRecorderState;
    }
  }

  public static enum TaskType
  {
    static
    {
      LOCALRECORD = new TaskType("LOCALRECORD", 2);
      TaskType[] arrayOfTaskType = new TaskType[3];
      arrayOfTaskType[0] = PHRASESPOTTING;
      arrayOfTaskType[1] = RECOGNITION;
      arrayOfTaskType[2] = LOCALRECORD;
      $VALUES = arrayOfTaskType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.MicrophoneStream
 * JD-Core Version:    0.6.0
 */