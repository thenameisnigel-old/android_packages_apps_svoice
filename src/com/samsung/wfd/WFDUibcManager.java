package com.samsung.wfd;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.InputDevice;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.MotionEvent.PointerProperties;
import android.view.WindowManager;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class WFDUibcManager
{
  private static final int MAX_EVENTS = 10;
  private static final String TAG = "WFDUibcManager";
  private Context mContext;
  private EventDispatcher mEventDispatcher = null;
  private Thread mEventDispatcherThread;
  private Thread mEventReaderThread;

  public WFDUibcManager(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public boolean start()
  {
    return true;
  }

  public boolean start(int paramInt)
  {
    this.mEventDispatcher = new EventDispatcher();
    WFDNative.enableUIBC(paramInt);
    this.mEventDispatcherThread = new Thread(this.mEventDispatcher);
    WFDNative.startUIBC(this.mEventDispatcher);
    this.mEventDispatcherThread.start();
    Log.d("WFDUibcManager", "Uibc Manager started");
    return true;
  }

  public boolean stop()
  {
    return true;
  }

  public boolean stop(int paramInt)
  {
    if (this.mEventDispatcher == null);
    while (true)
    {
      return true;
      this.mEventDispatcher.running = false;
      WFDNative.stopUIBC();
      Log.d("WFDUibcManager", "Going to stop Uibc manager");
      try
      {
        this.mEventDispatcherThread.join();
        WFDNative.disableUIBC(paramInt);
      }
      catch (InterruptedException localInterruptedException)
      {
        while (true)
          Log.e("WFDUibcManager", "Error joining event dispatcher thread", localInterruptedException);
      }
    }
  }

  class EventDispatcher extends WFDUibcManager.EventQueue
    implements Runnable
  {
    private HashMap<Integer, MotionEvent.PointerCoords> mMap = new HashMap();
    private MotionEvent mPrevEvent = null;
    public volatile boolean running = true;

    EventDispatcher()
    {
      super(null);
    }

    public void run()
    {
      MotionEvent.PointerProperties[] arrayOfPointerProperties = new MotionEvent.PointerProperties[10];
      MotionEvent.PointerCoords[] arrayOfPointerCoords = new MotionEvent.PointerCoords[10];
      for (int i = 0; i < 10; i++)
      {
        arrayOfPointerProperties[i] = new MotionEvent.PointerProperties();
        arrayOfPointerCoords[i] = new MotionEvent.PointerCoords();
      }
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      WindowManager localWindowManager = (WindowManager)WFDUibcManager.this.mContext.getSystemService("window");
      while (this.running)
      {
        InputEvent localInputEvent = getNextEvent();
        if (localInputEvent == null)
          continue;
        Log.d("WFDUibcManager", "RecvdEvent: " + localInputEvent);
        if ((localInputEvent instanceof MotionEvent))
        {
          localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
          float f1 = 1.0E-004F * localDisplayMetrics.widthPixels;
          float f2 = 1.0E-004F * localDisplayMetrics.heightPixels;
          Log.v("WFDUibcManager", "kx " + f1 + " ky " + f2);
          MotionEvent localMotionEvent1 = (MotionEvent)localInputEvent;
          int j = localMotionEvent1.getPointerCount();
          for (int k = 0; k < j; k++)
          {
            arrayOfPointerProperties[k].clear();
            arrayOfPointerCoords[k].clear();
            MotionEvent.PointerProperties localPointerProperties = arrayOfPointerProperties[k];
            localMotionEvent1.getPointerProperties(k, localPointerProperties);
            MotionEvent.PointerCoords localPointerCoords = arrayOfPointerCoords[k];
            localMotionEvent1.getPointerCoords(k, localPointerCoords);
            arrayOfPointerCoords[k].setAxisValue(0, f1 * arrayOfPointerCoords[k].x);
            arrayOfPointerCoords[k].setAxisValue(1, f2 * arrayOfPointerCoords[k].y);
          }
          MotionEvent localMotionEvent2 = MotionEvent.obtain(SystemClock.uptimeMillis() - 100L, SystemClock.uptimeMillis(), localMotionEvent1.getAction(), localMotionEvent1.getPointerCount(), arrayOfPointerProperties, arrayOfPointerCoords, 0, 0, 1.0F, 1.0F, localMotionEvent1.getDevice().getId(), 0, localMotionEvent1.getSource(), 0);
          Log.d("WFDUibcManager", "InjectEvent: " + localMotionEvent2);
          InputManager.getInstance().injectInputEvent(localMotionEvent2, 1);
          continue;
        }
        if (!(localInputEvent instanceof KeyEvent))
          continue;
        KeyEvent localKeyEvent = (KeyEvent)localInputEvent;
        InputManager.getInstance().injectInputEvent(localKeyEvent, 1);
      }
    }
  }

  private class EventQueue
  {
    private BlockingQueue<InputEvent> queuedEvents = new LinkedBlockingQueue();

    private EventQueue()
    {
    }

    public void addEvent(InputEvent paramInputEvent)
    {
      try
      {
        this.queuedEvents.put(paramInputEvent);
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        while (true)
          Log.e("WFDUibcManager", "Interrupted when waiting to insert to queue", localInterruptedException);
      }
      catch (NullPointerException localNullPointerException)
      {
        while (true)
          Log.e("WFDUibcManager", "Null pointer exception", localNullPointerException);
      }
    }

    public InputEvent getNextEvent()
    {
      InputEvent localInputEvent;
      try
      {
        localInputEvent = (InputEvent)this.queuedEvents.poll(500L, TimeUnit.MILLISECONDS);
        if (localInputEvent == null)
        {
          Log.e("WFDUibcManager", "nextQueuedEvent is null");
          localInputEvent = null;
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        Log.e("WFDUibcManager", "Interrupted when waiting to read from queue", localInterruptedException);
        localInputEvent = null;
      }
      return localInputEvent;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wfd.WFDUibcManager
 * JD-Core Version:    0.6.0
 */