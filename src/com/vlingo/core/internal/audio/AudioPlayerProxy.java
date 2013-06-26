package com.vlingo.core.internal.audio;

import android.content.Context;
import android.os.Message;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.settings.VoicePrompt;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.PhoneUtil;
import com.vlingo.core.internal.util.ServiceProxyBase;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AudioPlayerProxy extends ServiceProxyBase<IAudioPlaybackService, AudioPlaybackService>
{
  private static final int PLAY = 21;
  private static final int STOP = 22;
  private static int audioStream = 3;
  private static AudioPlayerProxy instance = null;
  private List<IAudioPlaybackService.AudioPlaybackListener> externalListeners = new CopyOnWriteArrayList();

  private AudioPlayerProxy(Context paramContext)
  {
    super(paramContext);
  }

  public static void addListener(IAudioPlaybackService.AudioPlaybackListener paramAudioPlaybackListener)
  {
    monitorenter;
    try
    {
      if ((instance != null) && (!instance.externalListeners.contains(paramAudioPlaybackListener)))
        instance.externalListeners.add(paramAudioPlaybackListener);
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

  public static void deinit()
  {
    monitorenter;
    try
    {
      if (instance != null)
      {
        instance.release();
        instance = null;
      }
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

  public static void init(Context paramContext)
  {
    monitorenter;
    try
    {
      if (instance == null)
        instance = new AudioPlayerProxy(paramContext);
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

  public static void play(AudioRequest paramAudioRequest)
  {
    monitorenter;
    try
    {
      if (instance != null)
        instance.playInternal(paramAudioRequest, null);
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

  public static void play(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener paramAudioPlaybackListener)
  {
    monitorenter;
    try
    {
      if (instance != null)
        instance.playInternal(paramAudioRequest, paramAudioPlaybackListener);
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

  public static void play(TTSRequest paramTTSRequest, IAudioPlaybackService.AudioPlaybackListener paramAudioPlaybackListener)
  {
    monitorenter;
    try
    {
      if ((!ClientSuppliedValues.isDrivingModeEnabled()) && (!VoicePrompt.isEnabled()))
      {
        TTSRequest.Type localType1 = paramTTSRequest.type;
        TTSRequest.Type localType2 = TTSRequest.Type.Prompt;
        if (localType1 != localType2);
      }
      while (true)
      {
        return;
        if (instance == null)
          continue;
        instance.playInternal(paramTTSRequest, paramAudioPlaybackListener);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void playAnyway(TTSRequest paramTTSRequest, IAudioPlaybackService.AudioPlaybackListener paramAudioPlaybackListener)
  {
    monitorenter;
    try
    {
      if (instance != null)
        instance.playInternal(paramTTSRequest, paramAudioPlaybackListener);
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

  private void playInternal(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener paramAudioPlaybackListener)
  {
    AudioPlaybackListenerProxy localAudioPlaybackListenerProxy = new AudioPlaybackListenerProxy(paramAudioPlaybackListener, null);
    usingBluetooth();
    paramAudioRequest.setStream(audioStream);
    paramAudioRequest.listener = localAudioPlaybackListenerProxy;
    execute(21, paramAudioRequest);
  }

  public static void playTone(int paramInt)
  {
    monitorenter;
    try
    {
      if (instance != null)
        instance.playToneInternal(paramInt, null);
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

  public static void playTone(int paramInt, IAudioPlaybackService.AudioPlaybackListener paramAudioPlaybackListener)
  {
    monitorenter;
    try
    {
      if (instance != null)
        instance.playToneInternal(paramInt, paramAudioPlaybackListener);
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

  private void playToneInternal(int paramInt, IAudioPlaybackService.AudioPlaybackListener paramAudioPlaybackListener)
  {
    playInternal(ToneAudioRequest.getRequest(paramInt), paramAudioPlaybackListener);
  }

  public static void removeListener(IAudioPlaybackService.AudioPlaybackListener paramAudioPlaybackListener)
  {
    monitorenter;
    try
    {
      if (instance != null)
        instance.externalListeners.remove(paramAudioPlaybackListener);
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

  public static void stop()
  {
    monitorenter;
    try
    {
      if (instance != null)
        instance.stopInternal();
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

  private void stopInternal()
  {
    execute(22);
  }

  public static void usingBluetooth()
  {
    monitorenter;
    try
    {
      audioStream = PhoneUtil.getCurrentStream();
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

  protected boolean allowsDelayDisconnect()
  {
    if (!Settings.isDrivingModeEnabled());
    for (int i = 1; ; i = 0)
      return i;
  }

  protected Class<IAudioPlaybackService> getInterfaceClass()
  {
    return IAudioPlaybackService.class;
  }

  protected String getMessageName(Message paramMessage)
  {
    String str;
    switch (paramMessage.what)
    {
    default:
      str = "UNKNOWN";
    case 22:
    case 21:
    }
    while (true)
    {
      return str;
      str = "STOP";
      continue;
      str = "PLAY";
    }
  }

  protected Class<AudioPlaybackService> getServiceClass()
  {
    return AudioPlaybackService.class;
  }

  protected void handleMessageForService(Message paramMessage, IAudioPlaybackService paramIAudioPlaybackService)
  {
    monitorenter;
    while (true)
    {
      try
      {
        int i = paramMessage.what;
        switch (i)
        {
        default:
          return;
        case 22:
          paramIAudioPlaybackService.stop();
          continue;
        case 21:
        }
      }
      finally
      {
        monitorexit;
      }
      AudioRequest localAudioRequest = (AudioRequest)paramMessage.obj;
      paramIAudioPlaybackService.play(localAudioRequest, localAudioRequest.listener);
    }
  }

  private class AudioPlaybackListenerProxy
    implements IAudioPlaybackService.AudioPlaybackListener
  {
    IAudioPlaybackService.AudioPlaybackListener requestListener;

    private AudioPlaybackListenerProxy(IAudioPlaybackService.AudioPlaybackListener arg2)
    {
      Object localObject;
      this.requestListener = localObject;
    }

    // ERROR //
    public void onRequestCancelled(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled paramReasonCanceled)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 22	com/vlingo/core/internal/audio/AudioPlayerProxy$AudioPlaybackListenerProxy:requestListener	Lcom/vlingo/core/internal/audio/IAudioPlaybackService$AudioPlaybackListener;
      //   4: ifnull +14 -> 18
      //   7: aload_0
      //   8: getfield 22	com/vlingo/core/internal/audio/AudioPlayerProxy$AudioPlaybackListenerProxy:requestListener	Lcom/vlingo/core/internal/audio/IAudioPlaybackService$AudioPlaybackListener;
      //   11: aload_1
      //   12: aload_2
      //   13: invokeinterface 29 3 0
      //   18: ldc 8
      //   20: monitorenter
      //   21: aload_0
      //   22: getfield 17	com/vlingo/core/internal/audio/AudioPlayerProxy$AudioPlaybackListenerProxy:this$0	Lcom/vlingo/core/internal/audio/AudioPlayerProxy;
      //   25: invokestatic 33	com/vlingo/core/internal/audio/AudioPlayerProxy:access$100	(Lcom/vlingo/core/internal/audio/AudioPlayerProxy;)Ljava/util/List;
      //   28: invokeinterface 39 1 0
      //   33: astore 4
      //   35: aload 4
      //   37: invokeinterface 45 1 0
      //   42: ifeq +29 -> 71
      //   45: aload 4
      //   47: invokeinterface 49 1 0
      //   52: checkcast 6	com/vlingo/core/internal/audio/IAudioPlaybackService$AudioPlaybackListener
      //   55: aload_1
      //   56: aload_2
      //   57: invokeinterface 29 3 0
      //   62: goto -27 -> 35
      //   65: astore_3
      //   66: ldc 8
      //   68: monitorexit
      //   69: aload_3
      //   70: athrow
      //   71: ldc 8
      //   73: monitorexit
      //   74: return
      //
      // Exception table:
      //   from	to	target	type
      //   21	69	65	finally
      //   71	74	65	finally
    }

    // ERROR //
    public void onRequestDidPlay(AudioRequest paramAudioRequest)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 22	com/vlingo/core/internal/audio/AudioPlayerProxy$AudioPlaybackListenerProxy:requestListener	Lcom/vlingo/core/internal/audio/IAudioPlaybackService$AudioPlaybackListener;
      //   4: ifnull +13 -> 17
      //   7: aload_0
      //   8: getfield 22	com/vlingo/core/internal/audio/AudioPlayerProxy$AudioPlaybackListenerProxy:requestListener	Lcom/vlingo/core/internal/audio/IAudioPlaybackService$AudioPlaybackListener;
      //   11: aload_1
      //   12: invokeinterface 53 2 0
      //   17: ldc 8
      //   19: monitorenter
      //   20: aload_0
      //   21: getfield 17	com/vlingo/core/internal/audio/AudioPlayerProxy$AudioPlaybackListenerProxy:this$0	Lcom/vlingo/core/internal/audio/AudioPlayerProxy;
      //   24: invokestatic 33	com/vlingo/core/internal/audio/AudioPlayerProxy:access$100	(Lcom/vlingo/core/internal/audio/AudioPlayerProxy;)Ljava/util/List;
      //   27: invokeinterface 39 1 0
      //   32: astore_3
      //   33: aload_3
      //   34: invokeinterface 45 1 0
      //   39: ifeq +27 -> 66
      //   42: aload_3
      //   43: invokeinterface 49 1 0
      //   48: checkcast 6	com/vlingo/core/internal/audio/IAudioPlaybackService$AudioPlaybackListener
      //   51: aload_1
      //   52: invokeinterface 53 2 0
      //   57: goto -24 -> 33
      //   60: astore_2
      //   61: ldc 8
      //   63: monitorexit
      //   64: aload_2
      //   65: athrow
      //   66: ldc 8
      //   68: monitorexit
      //   69: return
      //
      // Exception table:
      //   from	to	target	type
      //   20	64	60	finally
      //   66	69	60	finally
    }

    // ERROR //
    public void onRequestIgnored(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored paramReasonIgnored)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 22	com/vlingo/core/internal/audio/AudioPlayerProxy$AudioPlaybackListenerProxy:requestListener	Lcom/vlingo/core/internal/audio/IAudioPlaybackService$AudioPlaybackListener;
      //   4: ifnull +14 -> 18
      //   7: aload_0
      //   8: getfield 22	com/vlingo/core/internal/audio/AudioPlayerProxy$AudioPlaybackListenerProxy:requestListener	Lcom/vlingo/core/internal/audio/IAudioPlaybackService$AudioPlaybackListener;
      //   11: aload_1
      //   12: aload_2
      //   13: invokeinterface 57 3 0
      //   18: ldc 8
      //   20: monitorenter
      //   21: aload_0
      //   22: getfield 17	com/vlingo/core/internal/audio/AudioPlayerProxy$AudioPlaybackListenerProxy:this$0	Lcom/vlingo/core/internal/audio/AudioPlayerProxy;
      //   25: invokestatic 33	com/vlingo/core/internal/audio/AudioPlayerProxy:access$100	(Lcom/vlingo/core/internal/audio/AudioPlayerProxy;)Ljava/util/List;
      //   28: invokeinterface 39 1 0
      //   33: astore 4
      //   35: aload 4
      //   37: invokeinterface 45 1 0
      //   42: ifeq +29 -> 71
      //   45: aload 4
      //   47: invokeinterface 49 1 0
      //   52: checkcast 6	com/vlingo/core/internal/audio/IAudioPlaybackService$AudioPlaybackListener
      //   55: aload_1
      //   56: aload_2
      //   57: invokeinterface 57 3 0
      //   62: goto -27 -> 35
      //   65: astore_3
      //   66: ldc 8
      //   68: monitorexit
      //   69: aload_3
      //   70: athrow
      //   71: ldc 8
      //   73: monitorexit
      //   74: return
      //
      // Exception table:
      //   from	to	target	type
      //   21	69	65	finally
      //   71	74	65	finally
    }

    // ERROR //
    public void onRequestWillPlay(AudioRequest paramAudioRequest)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 22	com/vlingo/core/internal/audio/AudioPlayerProxy$AudioPlaybackListenerProxy:requestListener	Lcom/vlingo/core/internal/audio/IAudioPlaybackService$AudioPlaybackListener;
      //   4: ifnull +13 -> 17
      //   7: aload_0
      //   8: getfield 22	com/vlingo/core/internal/audio/AudioPlayerProxy$AudioPlaybackListenerProxy:requestListener	Lcom/vlingo/core/internal/audio/IAudioPlaybackService$AudioPlaybackListener;
      //   11: aload_1
      //   12: invokeinterface 60 2 0
      //   17: ldc 8
      //   19: monitorenter
      //   20: aload_0
      //   21: getfield 17	com/vlingo/core/internal/audio/AudioPlayerProxy$AudioPlaybackListenerProxy:this$0	Lcom/vlingo/core/internal/audio/AudioPlayerProxy;
      //   24: invokestatic 33	com/vlingo/core/internal/audio/AudioPlayerProxy:access$100	(Lcom/vlingo/core/internal/audio/AudioPlayerProxy;)Ljava/util/List;
      //   27: invokeinterface 39 1 0
      //   32: astore_3
      //   33: aload_3
      //   34: invokeinterface 45 1 0
      //   39: ifeq +27 -> 66
      //   42: aload_3
      //   43: invokeinterface 49 1 0
      //   48: checkcast 6	com/vlingo/core/internal/audio/IAudioPlaybackService$AudioPlaybackListener
      //   51: aload_1
      //   52: invokeinterface 60 2 0
      //   57: goto -24 -> 33
      //   60: astore_2
      //   61: ldc 8
      //   63: monitorexit
      //   64: aload_2
      //   65: athrow
      //   66: ldc 8
      //   68: monitorexit
      //   69: return
      //
      // Exception table:
      //   from	to	target	type
      //   20	64	60	finally
      //   66	69	60	finally
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.AudioPlayerProxy
 * JD-Core Version:    0.6.0
 */