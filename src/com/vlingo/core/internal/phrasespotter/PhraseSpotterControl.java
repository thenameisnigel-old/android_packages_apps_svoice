package com.vlingo.core.internal.phrasespotter;

import android.media.AudioRecord;
import com.vlingo.core.internal.CoreAdapterRegistrar;
import com.vlingo.core.internal.CoreAdapterRegistrar.AdapterList;
import com.vlingo.core.internal.audio.MicrophoneStream;
import com.vlingo.core.internal.audio.MicrophoneStream.AudioSourceType;
import com.vlingo.core.internal.audio.MicrophoneStream.TaskType;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.dialogmanager.DialogFlowTaskRegulator;
import com.vlingo.core.internal.dialogmanager.DialogFlowTaskRegulator.EventType;
import com.vlingo.core.internal.dialogmanager.ResumeControl;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.CircularShortBlockBuffer;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.recognition.speech.VLSpeechDetector;
import com.vlingo.sdk.recognition.speech.VLSpeechDetectorContext.Builder;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Vector;

class PhraseSpotterControl
{
  private static boolean isDefaultWakeUpCommand;
  private static int totalThreads = 0;
  private DialogFlowTaskRegulator dialogFlowTaskRegulator = null;
  final SpotterState idle = new Idle();
  private IAudioWrapper logger = new IAudioWrapper();
  private int mChunkSize = 0;
  private PhraseSpotterListener mListener;
  private PhraseSpotterParameters mPhraseSpotterParams;
  private short[] mRawAudioBuffer = null;
  Thread mRecordThread = null;
  private volatile boolean mRestartWhenIdle = false;
  private int mSampleRate = 0;
  SpotterState presentState = getStateInstance(State.IDLE);
  private Recording recording = new Recording();
  private ResumeControl resumeControlOnPhraseSpotterStopped;
  final SpotterState spot = new Spotting();
  final SpotterState start = new Starting();
  private final Object stateTransition = new Object();
  final SpotterState stop = new Stopping();

  static
  {
    isDefaultWakeUpCommand = true;
  }

  private ArrayList<VLPhraseSpotter> createSpotters()
  {
    ArrayList localArrayList = new ArrayList();
    Class localClass = CoreAdapterRegistrar.get(CoreAdapterRegistrar.AdapterList.PhraseSpotter);
    if (localClass != null);
    try
    {
      VLPhraseSpotter localVLPhraseSpotter = (VLPhraseSpotter)localClass.newInstance();
      isDefaultWakeUpCommand = localVLPhraseSpotter instanceof CoreSpotter;
      localArrayList.add(localVLPhraseSpotter);
      if (this.mPhraseSpotterParams.getCoreSpotterParams() != null)
        localArrayList.add(0, CoreSpotter.getInstance(this.mPhraseSpotterParams.getCoreSpotterParams()));
      return localArrayList;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  private void establishChunkSize()
  {
    int i = this.mPhraseSpotterParams.getChunkLength() * this.mSampleRate / 1000;
    if (i != this.mChunkSize)
    {
      this.mChunkSize = i;
      this.mRawAudioBuffer = new short[this.mChunkSize];
    }
  }

  private void establishLogging()
  {
    this.logger.setLogger(null);
  }

  private IAudioWrapper getAudioLogger()
  {
    if (this.logger == null);
    for (IAudioWrapper localIAudioWrapper = new IAudioWrapper(); ; localIAudioWrapper = this.logger)
    {
      this.logger = localIAudioWrapper;
      return localIAudioWrapper;
    }
  }

  private PhraseSpotterListener getListener()
  {
    if (this.mListener == null)
      this.mListener = new PhraseSpotterListener()
      {
        public void onPhraseDetected(String paramString)
        {
        }

        public void onPhraseSpotted(String paramString)
        {
        }

        public void onPhraseSpotterStarted()
        {
        }

        public void onPhraseSpotterStopped()
        {
        }

        public void onSeamlessPhraseSpotted(String paramString, MicrophoneStream paramMicrophoneStream)
        {
        }
      };
    return this.mListener;
  }

  public DialogFlowTaskRegulator getDialogFlowTaskRegulator()
  {
    return this.dialogFlowTaskRegulator;
  }

  SpotterState getState()
  {
    synchronized (this.stateTransition)
    {
      SpotterState localSpotterState = this.presentState;
      return localSpotterState;
    }
  }

  final SpotterState getStateInstance(State paramState)
  {
    SpotterState localSpotterState = this.idle;
    switch (2.$SwitchMap$com$vlingo$core$internal$phrasespotter$PhraseSpotterControl$State[paramState.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return localSpotterState;
      localSpotterState = this.spot;
      continue;
      localSpotterState = this.stop;
      continue;
      localSpotterState = this.start;
      continue;
      localSpotterState = this.idle;
    }
  }

  boolean isSpotting()
  {
    if ((getState().is(State.SPOTTING)) || (getState().is(State.STARTING)));
    for (int i = 1; ; i = 0)
      return i;
  }

  public void setDialogFlowTaskRegulator(DialogFlowTaskRegulator paramDialogFlowTaskRegulator)
  {
    this.dialogFlowTaskRegulator = paramDialogFlowTaskRegulator;
  }

  void setState(SpotterState paramSpotterState)
  {
    synchronized (this.stateTransition)
    {
      this.presentState.onExit();
      if (paramSpotterState == null)
        paramSpotterState = getStateInstance(State.IDLE);
      this.presentState = paramSpotterState;
      this.presentState.onEnter();
      return;
    }
  }

  void setState(State paramState)
  {
    setState(getStateInstance(paramState));
  }

  public void start(PhraseSpotterParameters paramPhraseSpotterParameters, PhraseSpotterListener paramPhraseSpotterListener)
  {
    getState().start(paramPhraseSpotterParameters, paramPhraseSpotterListener);
  }

  public void stop()
  {
    getState().stop();
  }

  public void stop(ResumeControl paramResumeControl)
  {
    int i = 1;
    synchronized (this.stateTransition)
    {
      if (!getState().is(State.IDLE))
      {
        i = 0;
        if (this.resumeControlOnPhraseSpotterStopped != null)
          this.resumeControlOnPhraseSpotterStopped.resume();
        this.resumeControlOnPhraseSpotterStopped = paramResumeControl;
        getState().stop();
      }
      if (i != 0)
        paramResumeControl.resume();
      return;
    }
  }

  abstract class BaseState
    implements PhraseSpotterControl.SpotterState
  {
    BaseState()
    {
    }

    public abstract boolean is(PhraseSpotterControl.State paramState);

    public void onEnter()
    {
    }

    public void onExit()
    {
    }

    public void start(PhraseSpotterParameters paramPhraseSpotterParameters, PhraseSpotterListener paramPhraseSpotterListener)
    {
    }

    public void stop()
    {
    }

    public abstract String toString();
  }

  static class IAudioWrapper
  {
    private PhraseSpotterAudioLogger logger;

    public void dumpToFile()
    {
      if (hasTarget())
        target().dumpToFile();
    }

    public boolean hasTarget()
    {
      if (this.logger != null);
      for (int i = 1; ; i = 0)
        return i;
    }

    public void markDecisionSample(boolean paramBoolean)
    {
      if (hasTarget())
        target().markDecisionSample(paramBoolean);
    }

    public void markP()
    {
      if (hasTarget())
        target().markP();
    }

    public void markS()
    {
      if (hasTarget())
        target().markS();
    }

    public void release()
    {
      this.logger = null;
    }

    public void setLogger(PhraseSpotterAudioLogger paramPhraseSpotterAudioLogger)
    {
      this.logger = paramPhraseSpotterAudioLogger;
    }

    public void setScore(float paramFloat)
    {
      if (hasTarget())
        target().setScore(paramFloat);
    }

    public PhraseSpotterAudioLogger target()
    {
      return this.logger;
    }

    public void writeData(short[] paramArrayOfShort, int paramInt1, int paramInt2)
    {
      if (hasTarget())
        target().writeData(paramArrayOfShort, paramInt1, paramInt2);
    }
  }

  class Idle extends PhraseSpotterControl.BaseState
  {
    Idle()
    {
      super();
    }

    public boolean is(PhraseSpotterControl.State paramState)
    {
      if (paramState == PhraseSpotterControl.State.IDLE);
      for (int i = 1; ; i = 0)
        return i;
    }

    public void onEnter()
    {
      super.onEnter();
      synchronized (PhraseSpotterControl.this.stateTransition)
      {
        if (PhraseSpotterControl.this.mRestartWhenIdle)
        {
          PhraseSpotterControl.access$402(PhraseSpotterControl.this, false);
          start(PhraseSpotterControl.this.mPhraseSpotterParams, PhraseSpotterControl.this.mListener);
        }
        return;
      }
    }

    public void start(PhraseSpotterParameters paramPhraseSpotterParameters, PhraseSpotterListener paramPhraseSpotterListener)
    {
      synchronized (PhraseSpotterControl.this.stateTransition)
      {
        super.start(paramPhraseSpotterParameters, paramPhraseSpotterListener);
        PhraseSpotterControl.access$102(PhraseSpotterControl.this, paramPhraseSpotterParameters);
        PhraseSpotterControl.access$202(PhraseSpotterControl.this, paramPhraseSpotterListener);
        PhraseSpotterControl.access$302(PhraseSpotterControl.this, 16000);
        PhraseSpotterControl.this.setState(PhraseSpotterControl.State.STARTING);
        return;
      }
    }

    public String toString()
    {
      return "Idle";
    }
  }

  private static class RecordThread
    implements Runnable
  {
    private static final int chan = 16;
    private static final int encoding = 2;
    private int MAXQUEUE = 50;
    private int bufferSize = 0;
    private Vector<ByteBuffer> buffers = new Vector();
    private int mChunkSize = 0;
    private PhraseSpotterControl mPsc;
    private int mSampleRate = 0;
    public int maxamp = 0;
    private CircularShortBlockBuffer preBuffer;
    public int samplerate;

    public RecordThread(PhraseSpotterControl paramPhraseSpotterControl, int paramInt1, int paramInt2)
    {
      this.mPsc = paramPhraseSpotterControl;
      this.mChunkSize = paramInt1;
      this.mSampleRate = paramInt2;
    }

    private void addBuffer(ByteBuffer paramByteBuffer, int paramInt)
    {
      monitorenter;
      while (true)
      {
        int i;
        try
        {
          ShortBuffer localShortBuffer = paramByteBuffer.asShortBuffer();
          this.maxamp = 0;
          i = 0;
          if (i >= paramInt)
            continue;
          int j = Short.reverseBytes(localShortBuffer.get(i));
          if (j <= this.maxamp)
            break label96;
          this.maxamp = j;
          break label96;
          if (this.buffers.size() == this.MAXQUEUE)
            throw new IllegalStateException("addBuffer: BUFFER OVERFLOW");
        }
        finally
        {
          monitorexit;
        }
        this.buffers.addElement(paramByteBuffer);
        notify();
        monitorexit;
        return;
        label96: i++;
      }
    }

    private CircularShortBlockBuffer getPrebuffer()
    {
      if (this.preBuffer == null)
      {
        int i = this.mPsc.mPhraseSpotterParams.getPreBufferLength() * this.mSampleRate / (1000 * this.mChunkSize);
        this.preBuffer = new CircularShortBlockBuffer(this.mChunkSize, i);
      }
      return this.preBuffer;
    }

    private void handlePhraseSpotted(String paramString, boolean paramBoolean, int paramInt1, int paramInt2)
    {
      int i4;
      if (paramBoolean)
      {
        VLSpeechDetectorContext.Builder localBuilder = new VLSpeechDetectorContext.Builder().speechDetectorParams(Settings.getFloat("endpoint.speechdetect_threshold", 11.0F), Settings.getFloat("endpoint.speechdetect_voice_duration", 0.08F), Settings.getFloat("endpoint.speechdetect_voice_portion", 0.02F), Settings.getFloat("endpoint.speechdetect_min_voice_level", 57.0F));
        VLSpeechDetector localVLSpeechDetector = VLSdk.getInstance().getSpeechDetector();
        localVLSpeechDetector.startSpeechDetector(localBuilder.build());
        short[] arrayOfShort1 = getPrebuffer().getArray();
        localVLSpeechDetector.processShortArray(arrayOfShort1, 0, arrayOfShort1.length);
        this.mPsc.getAudioLogger().markP();
        int i = 0;
        if (paramInt1 < 0)
          i = Math.abs(paramInt1 * this.mSampleRate / 1000);
        int n;
        short[] arrayOfShort2;
        int i1;
        while (true)
        {
          int m = (paramInt2 - paramInt1) * this.mSampleRate / 1000 - i;
          n = this.mPsc.mPhraseSpotterParams.getSeamlessTimeout() * this.mSampleRate / 1000;
          arrayOfShort2 = new short[n + (i + m)];
          i1 = 0;
          if (i > 0)
          {
            System.arraycopy(arrayOfShort1, arrayOfShort1.length - i, arrayOfShort2, 0, i);
            i1 = 0 + i;
          }
          for (int i2 = m / this.mChunkSize; i2 > 0; i2--)
          {
            readChunk();
            System.arraycopy(this.mPsc.mRawAudioBuffer, 0, arrayOfShort2, i1, this.mChunkSize);
            i1 += this.mChunkSize;
          }
          this.preBuffer = null;
          if (paramInt1 <= 0)
            continue;
          int j = paramInt1 * this.mSampleRate / (1000 * this.mChunkSize);
          for (int k = 0; k < j; k++)
            readChunk();
        }
        this.mPsc.getAudioLogger().markS();
        int i3 = n / this.mChunkSize;
        boolean bool = false;
        i4 = 0;
        if (i4 < i3)
        {
          readChunk();
          System.arraycopy(this.mPsc.mRawAudioBuffer, 0, arrayOfShort2, i1, this.mChunkSize);
          i1 += this.mChunkSize;
          if (localVLSpeechDetector.processShortArray(this.mPsc.mRawAudioBuffer, 0, this.mChunkSize))
            bool = true;
        }
        else
        {
          localVLSpeechDetector.stopSpeechDetector();
          this.mPsc.getAudioLogger().markDecisionSample(bool);
          if (!bool)
            break label511;
          MicrophoneStream localMicrophoneStream = this.mPsc.recording.target();
          localMicrophoneStream.enableAudioFiltering();
          localMicrophoneStream.setBufferedData(arrayOfShort2, i1);
          if (this.mPsc.getAudioLogger().hasTarget())
            localMicrophoneStream.setAudioLogger(this.mPsc.getAudioLogger().target());
          this.mPsc.recording.drop();
          this.mPsc.setState(PhraseSpotterControl.State.STOPPING);
          this.mPsc.getListener().onSeamlessPhraseSpotted(paramString, localMicrophoneStream);
        }
      }
      while (true)
      {
        return;
        i4++;
        break;
        label511: this.mPsc.getListener().onPhraseSpotted(paramString);
      }
    }

    private int readChunk()
    {
      int i = 0;
      int j = 0;
      int k = this.mChunkSize;
      while ((i < k) && (this.mPsc.getState() == this.mPsc.spot) && (this.mPsc.recording.isRecording()))
      {
        j = this.mPsc.recording.read(this.mPsc.mRawAudioBuffer, i, k);
        if ((j == -3) || (j == -2))
          continue;
        k -= j;
        i += j;
        if (i >= k)
          continue;
        try
        {
          Thread.sleep(this.mPsc.mPhraseSpotterParams.getRecorderSleep());
        }
        catch (InterruptedException localInterruptedException)
        {
        }
      }
      this.mPsc.getAudioLogger().writeData(this.mPsc.mRawAudioBuffer, 0, this.mChunkSize);
      return j;
    }

    private int readChunkForSensory2(byte[] paramArrayOfByte)
    {
      ByteBuffer localByteBuffer = ByteBuffer.allocateDirect(2 * this.bufferSize);
      int i = this.mPsc.recording.target().getRecorder().read(localByteBuffer, 2 * this.bufferSize) / 2;
      if (i == -3)
        throw new IllegalStateException("read() returned AudioRecord.ERROR_INVALID_OPERATION");
      if (i == -2)
        throw new IllegalStateException("read() returned AudioRecord.ERROR_BAD_VALUE");
      if (i == -3)
        throw new IllegalStateException("read() returned AudioRecord.ERROR_INVALID_OPERATION");
      System.arraycopy(localByteBuffer.array(), 0, paramArrayOfByte, 0, i * 2);
      if (i > 0)
        addBuffer(localByteBuffer, i);
      return i * 2;
    }

    public short convertToShort(byte[] paramArrayOfByte, int paramInt)
    {
      return (short)(paramArrayOfByte[(paramInt + 1)] << 8 | 0xFF & paramArrayOfByte[paramInt]);
    }

    public ByteBuffer getBuffer()
      throws InterruptedException
    {
      monitorenter;
      try
      {
        while (this.buffers.size() == 0)
          wait();
      }
      finally
      {
        monitorexit;
      }
      int i = Math.min(this.buffers.size(), 4);
      int j = 0;
      for (int k = 0; k < i; k++)
        j += ((ByteBuffer)this.buffers.elementAt(k)).capacity();
      ByteBuffer localByteBuffer = ByteBuffer.allocateDirect(j);
      for (int m = 0; m < i; m++)
      {
        localByteBuffer.put((ByteBuffer)this.buffers.firstElement());
        this.buffers.removeElementAt(0);
      }
      monitorexit;
      return localByteBuffer;
    }

    boolean initAudio()
    {
      int i = 0;
      int j = 0;
      int[] arrayOfInt = new int[2];
      arrayOfInt[0] = 16000;
      arrayOfInt[1] = 8000;
      int k = 0;
      if (k < arrayOfInt.length)
      {
        this.samplerate = arrayOfInt[k];
        this.bufferSize = AudioRecord.getMinBufferSize(this.samplerate, 16, 2);
        if (this.bufferSize == -2);
        do
        {
          k++;
          break;
          if (this.bufferSize < 4096)
            this.bufferSize = 4800;
          if (this.bufferSize % 160 != 0)
            this.bufferSize -= this.bufferSize % 160;
          while ((i == 0) && (j < 2))
          {
            j++;
            i = 1;
          }
        }
        while (i == 0);
      }
      return i;
    }

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore_1
      //   2: aconst_null
      //   3: astore_2
      //   4: aload_0
      //   5: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   8: invokestatic 358	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$000	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)Ljava/lang/Object;
      //   11: astore 9
      //   13: aload 9
      //   15: monitorenter
      //   16: aload_0
      //   17: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   20: getfield 361	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:presentState	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$SpotterState;
      //   23: aload_0
      //   24: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   27: getfield 364	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:start	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$SpotterState;
      //   30: if_acmpeq +76 -> 106
      //   33: aload 9
      //   35: monitorexit
      //   36: aload_0
      //   37: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   40: invokestatic 207	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$1200	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$Recording;
      //   43: invokevirtual 367	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$Recording:stop	()V
      //   46: aload_0
      //   47: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   50: invokestatic 358	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$000	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)Ljava/lang/Object;
      //   53: astore 42
      //   55: aload 42
      //   57: monitorenter
      //   58: aload_0
      //   59: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   62: getstatic 370	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$State:IDLE	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$State;
      //   65: invokevirtual 246	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:setState	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$State;)V
      //   68: aload_0
      //   69: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   72: getfield 374	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:mRecordThread	Ljava/lang/Thread;
      //   75: invokestatic 378	java/lang/Thread:currentThread	()Ljava/lang/Thread;
      //   78: if_acmpne +11 -> 89
      //   81: aload_0
      //   82: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   85: aconst_null
      //   86: putfield 374	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:mRecordThread	Ljava/lang/Thread;
      //   89: aload 42
      //   91: monitorexit
      //   92: aload_0
      //   93: aconst_null
      //   94: putfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   97: return
      //   98: astore 43
      //   100: aload 42
      //   102: monitorexit
      //   103: aload 43
      //   105: athrow
      //   106: aload_0
      //   107: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   110: getstatic 381	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$State:SPOTTING	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$State;
      //   113: invokevirtual 246	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:setState	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$State;)V
      //   116: aload 9
      //   118: monitorexit
      //   119: aload_0
      //   120: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   123: invokestatic 385	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$1300	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)Ljava/util/ArrayList;
      //   126: astore 11
      //   128: aload 11
      //   130: invokevirtual 388	java/util/ArrayList:size	()I
      //   133: ifle +649 -> 782
      //   136: aload 11
      //   138: invokevirtual 392	java/util/ArrayList:iterator	()Ljava/util/Iterator;
      //   141: astore 14
      //   143: aload 14
      //   145: invokeinterface 397 1 0
      //   150: ifeq +104 -> 254
      //   153: aload 14
      //   155: invokeinterface 400 1 0
      //   160: checkcast 402	com/vlingo/core/internal/phrasespotter/VLPhraseSpotter
      //   163: astore 39
      //   165: aload 39
      //   167: invokeinterface 405 1 0
      //   172: goto -29 -> 143
      //   175: astore 41
      //   177: goto -34 -> 143
      //   180: astore 10
      //   182: aload 9
      //   184: monitorexit
      //   185: aload 10
      //   187: athrow
      //   188: astore 6
      //   190: aload_0
      //   191: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   194: invokestatic 207	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$1200	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$Recording;
      //   197: invokevirtual 367	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$Recording:stop	()V
      //   200: aload_0
      //   201: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   204: invokestatic 358	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$000	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)Ljava/lang/Object;
      //   207: astore 7
      //   209: aload 7
      //   211: monitorenter
      //   212: aload_0
      //   213: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   216: getstatic 370	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$State:IDLE	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$State;
      //   219: invokevirtual 246	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:setState	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$State;)V
      //   222: aload_0
      //   223: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   226: getfield 374	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:mRecordThread	Ljava/lang/Thread;
      //   229: invokestatic 378	java/lang/Thread:currentThread	()Ljava/lang/Thread;
      //   232: if_acmpne +11 -> 243
      //   235: aload_0
      //   236: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   239: aconst_null
      //   240: putfield 374	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:mRecordThread	Ljava/lang/Thread;
      //   243: aload 7
      //   245: monitorexit
      //   246: aload_0
      //   247: aconst_null
      //   248: putfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   251: goto -154 -> 97
      //   254: invokestatic 410	com/vlingo/core/internal/recognition/AndroidRecognitionManager:getInstance	()Lcom/vlingo/core/internal/recognition/AndroidRecognitionManager;
      //   257: invokevirtual 413	com/vlingo/core/internal/recognition/AndroidRecognitionManager:isActive	()Z
      //   260: ifeq +47 -> 307
      //   263: aload 11
      //   265: invokevirtual 392	java/util/ArrayList:iterator	()Ljava/util/Iterator;
      //   268: astore 34
      //   270: aload 34
      //   272: invokeinterface 397 1 0
      //   277: ifeq +505 -> 782
      //   280: aload 34
      //   282: invokeinterface 400 1 0
      //   287: checkcast 402	com/vlingo/core/internal/phrasespotter/VLPhraseSpotter
      //   290: astore 35
      //   292: aload 35
      //   294: invokeinterface 416 1 0
      //   299: goto -29 -> 270
      //   302: astore 37
      //   304: goto -34 -> 270
      //   307: aload_0
      //   308: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   311: invokestatic 207	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$1200	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$Recording;
      //   314: aload_0
      //   315: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   318: invokestatic 95	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$100	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterParameters;
      //   321: invokevirtual 420	com/vlingo/core/internal/phrasespotter/PhraseSpotterParameters:getAudioSourceType	()Lcom/vlingo/core/internal/audio/MicrophoneStream$AudioSourceType;
      //   324: invokevirtual 424	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$Recording:create	(Lcom/vlingo/core/internal/audio/MicrophoneStream$AudioSourceType;)V
      //   327: aload_0
      //   328: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   331: invokestatic 207	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$1200	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$Recording;
      //   334: aload_0
      //   335: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   338: invokestatic 166	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$1400	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$IAudioWrapper;
      //   341: invokevirtual 428	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$Recording:setLogger	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$IAudioWrapper;)V
      //   344: invokestatic 431	com/vlingo/sdk/VLSdk:isSensory2Using	()Z
      //   347: ifeq +8 -> 355
      //   350: aload_0
      //   351: invokevirtual 433	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:initAudio	()Z
      //   354: pop
      //   355: aload_0
      //   356: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   359: invokevirtual 265	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:getState	()Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$SpotterState;
      //   362: getstatic 381	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$State:SPOTTING	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$State;
      //   365: invokeinterface 439 2 0
      //   370: ifeq -107 -> 263
      //   373: aconst_null
      //   374: astore 15
      //   376: invokestatic 431	com/vlingo/sdk/VLSdk:isSensory2Using	()Z
      //   379: ifeq +262 -> 641
      //   382: iconst_2
      //   383: aload_0
      //   384: getfield 38	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:bufferSize	I
      //   387: imul
      //   388: newarray byte
      //   390: astore_2
      //   391: aload_0
      //   392: aload_2
      //   393: invokespecial 441	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:readChunkForSensory2	([B)I
      //   396: istore 31
      //   398: iload 31
      //   400: istore_1
      //   401: aload_0
      //   402: invokevirtual 443	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:getBuffer	()Ljava/nio/ByteBuffer;
      //   405: astore 33
      //   407: aload 33
      //   409: astore 15
      //   411: aload 11
      //   413: invokevirtual 392	java/util/ArrayList:iterator	()Ljava/util/Iterator;
      //   416: astore 17
      //   418: aload 17
      //   420: invokeinterface 397 1 0
      //   425: ifeq -70 -> 355
      //   428: aload 17
      //   430: invokeinterface 400 1 0
      //   435: checkcast 402	com/vlingo/core/internal/phrasespotter/VLPhraseSpotter
      //   438: astore 18
      //   440: aconst_null
      //   441: astore 19
      //   443: sipush 160
      //   446: newarray short
      //   448: astore 22
      //   450: invokestatic 431	com/vlingo/sdk/VLSdk:isSensory2Using	()Z
      //   453: ifeq +301 -> 754
      //   456: aload 18
      //   458: instanceof 445
      //   461: ifeq +202 -> 663
      //   464: aload 18
      //   466: aload 18
      //   468: invokeinterface 449 1 0
      //   473: aload 15
      //   475: aload_0
      //   476: getfield 347	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:samplerate	I
      //   479: i2l
      //   480: invokeinterface 453 6 0
      //   485: astore 19
      //   487: aload 19
      //   489: ifnull -71 -> 418
      //   492: aload 18
      //   494: invokeinterface 457 1 0
      //   499: fstore 24
      //   501: aload_0
      //   502: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   505: invokestatic 166	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$1400	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$IAudioWrapper;
      //   508: fload 24
      //   510: invokevirtual 461	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$IAudioWrapper:setScore	(F)V
      //   513: aload_0
      //   514: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   517: invokestatic 250	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$900	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterListener;
      //   520: aload 19
      //   522: invokeinterface 464 2 0
      //   527: aload_0
      //   528: aload 19
      //   530: aload 18
      //   532: aload 19
      //   534: invokeinterface 468 2 0
      //   539: aload 18
      //   541: invokeinterface 471 1 0
      //   546: aload 18
      //   548: invokeinterface 474 1 0
      //   553: invokespecial 476	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:handlePhraseSpotted	(Ljava/lang/String;ZII)V
      //   556: goto -201 -> 355
      //   559: astore 21
      //   561: goto -143 -> 418
      //   564: astore 32
      //   566: new 75	java/lang/IllegalStateException
      //   569: dup
      //   570: ldc_w 478
      //   573: invokespecial 80	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
      //   576: athrow
      //   577: astore_3
      //   578: aload_0
      //   579: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   582: invokestatic 207	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$1200	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$Recording;
      //   585: invokevirtual 367	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$Recording:stop	()V
      //   588: aload_0
      //   589: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   592: invokestatic 358	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$000	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)Ljava/lang/Object;
      //   595: astore 4
      //   597: aload 4
      //   599: monitorenter
      //   600: aload_0
      //   601: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   604: getstatic 370	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$State:IDLE	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$State;
      //   607: invokevirtual 246	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:setState	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$State;)V
      //   610: aload_0
      //   611: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   614: getfield 374	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:mRecordThread	Ljava/lang/Thread;
      //   617: invokestatic 378	java/lang/Thread:currentThread	()Ljava/lang/Thread;
      //   620: if_acmpne +11 -> 631
      //   623: aload_0
      //   624: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   627: aconst_null
      //   628: putfield 374	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:mRecordThread	Ljava/lang/Thread;
      //   631: aload 4
      //   633: monitorexit
      //   634: aload_0
      //   635: aconst_null
      //   636: putfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   639: aload_3
      //   640: athrow
      //   641: aload_0
      //   642: invokespecial 189	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:readChunk	()I
      //   645: pop
      //   646: aload_0
      //   647: invokespecial 154	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:getPrebuffer	()Lcom/vlingo/core/internal/util/CircularShortBlockBuffer;
      //   650: aload_0
      //   651: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   654: invokestatic 193	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$1500	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)[S
      //   657: invokevirtual 482	com/vlingo/core/internal/util/CircularShortBlockBuffer:write	([S)V
      //   660: goto -249 -> 411
      //   663: iconst_0
      //   664: istore 25
      //   666: iconst_0
      //   667: istore 26
      //   669: iload 25
      //   671: iload_1
      //   672: iconst_2
      //   673: idiv
      //   674: if_icmpge +211 -> 885
      //   677: iload 25
      //   679: iconst_2
      //   680: imul
      //   681: istore 28
      //   683: aload_0
      //   684: aload_2
      //   685: iload 28
      //   687: invokevirtual 484	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:convertToShort	([BI)S
      //   690: istore 29
      //   692: iload 26
      //   694: iconst_1
      //   695: iadd
      //   696: istore 30
      //   698: aload 22
      //   700: iload 26
      //   702: iload 29
      //   704: sastore
      //   705: iload 25
      //   707: ifeq +184 -> 891
      //   710: iload 30
      //   712: sipush 160
      //   715: irem
      //   716: ifne +175 -> 891
      //   719: aload_0
      //   720: invokespecial 154	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:getPrebuffer	()Lcom/vlingo/core/internal/util/CircularShortBlockBuffer;
      //   723: aload 22
      //   725: invokevirtual 482	com/vlingo/core/internal/util/CircularShortBlockBuffer:write	([S)V
      //   728: aload 18
      //   730: aload 22
      //   732: iconst_0
      //   733: sipush 160
      //   736: invokeinterface 487 4 0
      //   741: astore 19
      //   743: iconst_0
      //   744: istore 30
      //   746: aload 19
      //   748: ifnonnull -261 -> 487
      //   751: goto +140 -> 891
      //   754: aload 18
      //   756: aload_0
      //   757: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   760: invokestatic 193	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$1500	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)[S
      //   763: iconst_0
      //   764: aload_0
      //   765: getfield 34	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mChunkSize	I
      //   768: invokeinterface 487 4 0
      //   773: astore 23
      //   775: aload 23
      //   777: astore 19
      //   779: goto -292 -> 487
      //   782: aload_0
      //   783: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   786: invokestatic 207	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$1200	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$Recording;
      //   789: invokevirtual 367	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$Recording:stop	()V
      //   792: aload_0
      //   793: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   796: invokestatic 358	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:access$000	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;)Ljava/lang/Object;
      //   799: astore 12
      //   801: aload 12
      //   803: monitorenter
      //   804: aload_0
      //   805: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   808: getstatic 370	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$State:IDLE	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$State;
      //   811: invokevirtual 246	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:setState	(Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl$State;)V
      //   814: aload_0
      //   815: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   818: getfield 374	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:mRecordThread	Ljava/lang/Thread;
      //   821: invokestatic 378	java/lang/Thread:currentThread	()Ljava/lang/Thread;
      //   824: if_acmpne +11 -> 835
      //   827: aload_0
      //   828: getfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   831: aconst_null
      //   832: putfield 374	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl:mRecordThread	Ljava/lang/Thread;
      //   835: aload 12
      //   837: monitorexit
      //   838: aload_0
      //   839: aconst_null
      //   840: putfield 49	com/vlingo/core/internal/phrasespotter/PhraseSpotterControl$RecordThread:mPsc	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterControl;
      //   843: goto -746 -> 97
      //   846: astore 13
      //   848: aload 12
      //   850: monitorexit
      //   851: aload 13
      //   853: athrow
      //   854: astore 8
      //   856: aload 7
      //   858: monitorexit
      //   859: aload 8
      //   861: athrow
      //   862: astore 5
      //   864: aload 4
      //   866: monitorexit
      //   867: aload 5
      //   869: athrow
      //   870: astore 40
      //   872: goto -729 -> 143
      //   875: astore 36
      //   877: goto -607 -> 270
      //   880: astore 20
      //   882: goto -464 -> 418
      //   885: iload 26
      //   887: pop
      //   888: goto -401 -> 487
      //   891: iinc 25 1
      //   894: iload 30
      //   896: istore 26
      //   898: goto -229 -> 669
      //
      // Exception table:
      //   from	to	target	type
      //   58	92	98	finally
      //   100	103	98	finally
      //   165	172	175	java/lang/IllegalStateException
      //   16	36	180	finally
      //   106	119	180	finally
      //   182	185	180	finally
      //   4	16	188	java/lang/Exception
      //   119	165	188	java/lang/Exception
      //   185	188	188	java/lang/Exception
      //   254	292	188	java/lang/Exception
      //   307	398	188	java/lang/Exception
      //   401	407	188	java/lang/Exception
      //   411	440	188	java/lang/Exception
      //   566	577	188	java/lang/Exception
      //   641	660	188	java/lang/Exception
      //   292	299	302	java/lang/IllegalStateException
      //   443	556	559	java/lang/IllegalStateException
      //   669	775	559	java/lang/IllegalStateException
      //   401	407	564	java/lang/InterruptedException
      //   4	16	577	finally
      //   119	165	577	finally
      //   165	172	577	finally
      //   185	188	577	finally
      //   254	292	577	finally
      //   292	299	577	finally
      //   307	398	577	finally
      //   401	407	577	finally
      //   411	440	577	finally
      //   443	556	577	finally
      //   566	577	577	finally
      //   641	660	577	finally
      //   669	775	577	finally
      //   804	838	846	finally
      //   848	851	846	finally
      //   212	246	854	finally
      //   856	859	854	finally
      //   600	634	862	finally
      //   864	867	862	finally
      //   165	172	870	java/lang/Exception
      //   292	299	875	java/lang/Exception
      //   443	556	880	java/lang/Exception
      //   669	775	880	java/lang/Exception
    }
  }

  static class Recording
  {
    private PhraseSpotterControl.IAudioWrapper logger;
    private MicrophoneStream mMicStream;

    public void create(MicrophoneStream.AudioSourceType paramAudioSourceType)
    {
      this.mMicStream = MicrophoneStream.request(null, MicrophoneStream.TaskType.PHRASESPOTTING, paramAudioSourceType, 0);
    }

    public void drop()
    {
      this.mMicStream = null;
    }

    public boolean isRecording()
    {
      return this.mMicStream.isRecording();
    }

    public int read(short[] paramArrayOfShort, int paramInt1, int paramInt2)
    {
      return this.mMicStream.read(paramArrayOfShort, paramInt1, paramInt2);
    }

    public void setLogger(PhraseSpotterControl.IAudioWrapper paramIAudioWrapper)
    {
      this.logger = paramIAudioWrapper;
    }

    public void stop()
    {
      if (this.mMicStream != null)
        this.logger.dumpToFile();
      try
      {
        this.mMicStream.close();
        label21: this.mMicStream = null;
        return;
      }
      catch (Exception localException)
      {
        break label21;
      }
    }

    public MicrophoneStream target()
    {
      return this.mMicStream;
    }
  }

  static abstract interface SpotterState
  {
    public abstract boolean is(PhraseSpotterControl.State paramState);

    public abstract void onEnter();

    public abstract void onExit();

    public abstract void start(PhraseSpotterParameters paramPhraseSpotterParameters, PhraseSpotterListener paramPhraseSpotterListener);

    public abstract void stop();
  }

  class Spotting extends PhraseSpotterControl.BaseState
  {
    Spotting()
    {
      super();
    }

    public boolean is(PhraseSpotterControl.State paramState)
    {
      if (paramState == PhraseSpotterControl.State.SPOTTING);
      for (int i = 1; ; i = 0)
        return i;
    }

    public void stop()
    {
      super.stop();
      PhraseSpotterControl.this.setState(PhraseSpotterControl.State.STOPPING);
    }

    public String toString()
    {
      return "Spotting";
    }
  }

  class Starting extends PhraseSpotterControl.BaseState
  {
    Starting()
    {
      super();
    }

    public boolean is(PhraseSpotterControl.State paramState)
    {
      if (paramState == PhraseSpotterControl.State.STARTING);
      for (int i = 1; ; i = 0)
        return i;
    }

    public void onEnter()
    {
      PhraseSpotterControl.this.establishChunkSize();
      try
      {
        PhraseSpotterControl.this.establishLogging();
        PhraseSpotterControl.this.mRecordThread = new Thread(new PhraseSpotterControl.RecordThread(PhraseSpotterControl.this, PhraseSpotterControl.this.mChunkSize, PhraseSpotterControl.this.mSampleRate), "PhraseSpotting-" + PhraseSpotterControl.access$804());
        PhraseSpotterControl.this.mRecordThread.start();
        PhraseSpotterControl.this.getListener().onPhraseSpotterStarted();
        return;
      }
      catch (Exception localException)
      {
      }
      throw new IllegalStateException("Error starting phrasespotter: " + localException.toString());
    }

    public void stop()
    {
      super.stop();
      PhraseSpotterControl.this.setState(PhraseSpotterControl.State.STOPPING);
    }

    public String toString()
    {
      return "Starting";
    }
  }

  protected static enum State
  {
    static
    {
      SPOTTING = new State("SPOTTING", 2);
      STOPPING = new State("STOPPING", 3);
      State[] arrayOfState = new State[4];
      arrayOfState[0] = IDLE;
      arrayOfState[1] = STARTING;
      arrayOfState[2] = SPOTTING;
      arrayOfState[3] = STOPPING;
      $VALUES = arrayOfState;
    }
  }

  class Stopping extends PhraseSpotterControl.BaseState
  {
    Stopping()
    {
      super();
    }

    public boolean is(PhraseSpotterControl.State paramState)
    {
      if (paramState == PhraseSpotterControl.State.STOPPING);
      for (int i = 1; ; i = 0)
        return i;
    }

    public void onExit()
    {
      super.onExit();
      if (PhraseSpotterControl.this.resumeControlOnPhraseSpotterStopped != null)
      {
        PhraseSpotterControl.this.resumeControlOnPhraseSpotterStopped.resume();
        PhraseSpotterControl.access$1002(PhraseSpotterControl.this, null);
      }
      DialogFlowTaskRegulator localDialogFlowTaskRegulator = PhraseSpotterControl.this.getDialogFlowTaskRegulator();
      if (localDialogFlowTaskRegulator != null)
      {
        DialogFlow.getInstance().unregisterTaskFlowRegulator(DialogFlowTaskRegulator.EventType.RECOGNITION_START, localDialogFlowTaskRegulator);
        PhraseSpotterControl.this.setDialogFlowTaskRegulator(null);
      }
      PhraseSpotterControl.this.getListener().onPhraseSpotterStopped();
      PhraseSpotterControl.this.logger.release();
    }

    public void start(PhraseSpotterParameters paramPhraseSpotterParameters, PhraseSpotterListener paramPhraseSpotterListener)
    {
      super.start(paramPhraseSpotterParameters, paramPhraseSpotterListener);
      synchronized (PhraseSpotterControl.this.stateTransition)
      {
        PhraseSpotterControl.access$402(PhraseSpotterControl.this, true);
        if (PhraseSpotterControl.this.mRecordThread == null)
          PhraseSpotterControl.this.setState(PhraseSpotterControl.State.IDLE);
        return;
      }
    }

    public String toString()
    {
      return "Stopping";
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.phrasespotter.PhraseSpotterControl
 * JD-Core Version:    0.6.0
 */