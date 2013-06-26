package com.vlingo.core.internal.audio;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.vlingo.core.internal.util.ActivityUtil;
import com.vlingo.core.internal.util.ApplicationAdapter;

public abstract class AudioFocusManager
{
  public static final String ACTION_AUDIO_FOCUS_CHANGED = "com.vlingo.client.app.action.AUDIO_FOCUS_CHANGED";
  public static final String EXTRA_FOCUS_CHANGE = "com.vlingo.client.app.extra.FOCUS_CHANGE";
  public static final int FOCUS_TYPE_FULL = 1;
  public static final int FOCUS_TYPE_TRANSIENT = 2;
  public static final int FOCUS_TYPE_TRANSIENT_MAY_DUCK = 3;
  private static boolean bHasFocus = false;
  private static AudioFocusManager instance = null;

  public static AudioFocusManager getInstance(Context paramContext)
  {
    monitorenter;
    try
    {
      if (instance == null)
        instance = newInstance(paramContext);
      AudioFocusManager localAudioFocusManager = instance;
      monitorexit;
      return localAudioFocusManager;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private static AudioFocusManager newInstance(Context paramContext)
  {
    if (Integer.parseInt(Build.VERSION.SDK) < 8);
    for (Object localObject = new EclairAudioFocusManager(null); ; localObject = new FroyoAudioFocusManager(paramContext))
      return localObject;
  }

  public abstract void abandonAudioFocus();

  public abstract void requestAudioFocus(int paramInt1, int paramInt2);

  public abstract void setTaskOnGetAudioFocus(int paramInt1, int paramInt2, Runnable paramRunnable);

  private static class EclairAudioFocusManager extends AudioFocusManager
  {
    public void abandonAudioFocus()
    {
    }

    public void requestAudioFocus(int paramInt1, int paramInt2)
    {
    }

    public void setTaskOnGetAudioFocus(int paramInt1, int paramInt2, Runnable paramRunnable)
    {
    }
  }

  private static class FroyoAudioFocusManager extends AudioFocusManager.EclairAudioFocusManager
    implements AudioManager.OnAudioFocusChangeListener
  {
    private static final int AUDIO_FOCUS_DELAY = 200;
    private static Runnable mSetTaskOnAudioFocus;
    private AudioManager mAudioManager;
    private Handler mHandler;

    FroyoAudioFocusManager(Context paramContext)
    {
      super();
      this.mAudioManager = ((AudioManager)paramContext.getSystemService("audio"));
      this.mHandler = new FocusHandler(null);
    }

    private void broadcastFocuseChanged(int paramInt)
    {
      Intent localIntent = new Intent("com.vlingo.client.app.action.AUDIO_FOCUS_CHANGED");
      localIntent.putExtra("com.vlingo.client.app.extra.FOCUS_CHANGE", paramInt);
      ApplicationAdapter.getInstance().getApplicationContext().sendBroadcast(localIntent);
    }

    public void abandonAudioFocus()
    {
      monitorenter;
      try
      {
        if (mSetTaskOnAudioFocus == null)
        {
          this.mHandler.removeMessages(1);
          this.mHandler.removeMessages(2);
          this.mHandler.sendEmptyMessageDelayed(2, 200L);
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

    public void onAudioFocusChange(int paramInt)
    {
    }

    public void requestAudioFocus(int paramInt1, int paramInt2)
    {
      monitorenter;
      try
      {
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        Message localMessage = Message.obtain(null, 1, paramInt1, paramInt2);
        this.mHandler.sendMessageDelayed(localMessage, 200L);
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

    public void setTaskOnGetAudioFocus(int paramInt1, int paramInt2, Runnable paramRunnable)
    {
      mSetTaskOnAudioFocus = paramRunnable;
      requestAudioFocus(paramInt1, paramInt2);
    }

    private class FocusHandler extends Handler
      implements AudioManager.OnAudioFocusChangeListener
    {
      private static final int ABANDON_FOCUS = 2;
      private static final int REQUEST_FOCUS = 1;

      private FocusHandler()
      {
        super();
      }

      public void handleMessage(Message paramMessage)
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
            case 1:
              if (AudioFocusManager.FroyoAudioFocusManager.this.mAudioManager.requestAudioFocus(this, paramMessage.arg1, paramMessage.arg2) == 0)
              {
                if ((hasMessages(1)) || (hasMessages(2)))
                  continue;
                sendMessageDelayed(Message.obtain(paramMessage), 200L);
                continue;
              }
            case 2:
            }
          }
          finally
          {
            monitorexit;
          }
          if (AudioFocusManager.FroyoAudioFocusManager.mSetTaskOnAudioFocus != null)
          {
            ActivityUtil.scheduleOnMainThread(AudioFocusManager.FroyoAudioFocusManager.mSetTaskOnAudioFocus);
            AudioFocusManager.FroyoAudioFocusManager.access$302(null);
          }
          if (!AudioFocusManager.bHasFocus)
            AudioFocusManager.FroyoAudioFocusManager.this.broadcastFocuseChanged(paramMessage.arg1);
          AudioFocusManager.access$402(true);
          continue;
          if ((AudioFocusManager.FroyoAudioFocusManager.this.mAudioManager == null) || (AudioFocusManager.bHasFocus != true) || (AudioFocusManager.FroyoAudioFocusManager.this.mAudioManager.abandonAudioFocus(this) != 1))
            continue;
          AudioFocusManager.access$402(false);
          AudioFocusManager.FroyoAudioFocusManager.this.broadcastFocuseChanged(-1);
        }
      }

      public void onAudioFocusChange(int paramInt)
      {
        switch (paramInt)
        {
        case 0:
        default:
        case 1:
        case 2:
        case 3:
        case -3:
        case -2:
        case -1:
        }
        while (true)
        {
          AudioFocusManager.FroyoAudioFocusManager.this.broadcastFocuseChanged(paramInt);
          return;
          AudioFocusManager.access$402(true);
          continue;
          AudioFocusManager.access$402(false);
          if (AudioFocusManager.FroyoAudioFocusManager.this.mAudioManager == null)
            continue;
          AudioFocusManager.FroyoAudioFocusManager.this.mAudioManager.abandonAudioFocus(this);
        }
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.AudioFocusManager
 * JD-Core Version:    0.6.0
 */