package com.vlingo.core.internal.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeMotionDetector
{
  private boolean isListening;
  private final ShakeMotionDetectorListener listener;
  private final SensorEventListener mSensorListener = new SensorEventListener()
  {
    public void onAccuracyChanged(Sensor paramSensor, int paramInt)
    {
    }

    public void onSensorChanged(SensorEvent paramSensorEvent)
    {
      long l;
      if (paramSensorEvent.sensor == ShakeMotionDetector.this.s_Accelerometer)
      {
        l = System.currentTimeMillis();
        if (l <= 250L + ShakeMotionDetector.this.m_LastSensorTime);
      }
      while (true)
      {
        int j;
        synchronized (ShakeMotionDetector.this.mSensorManager)
        {
          ShakeMotionDetector.access$102(ShakeMotionDetector.this, l);
          if (ShakeMotionDetector.this.m_LastValues != null)
            break label218;
          ShakeMotionDetector.access$302(ShakeMotionDetector.this, new float[3]);
          continue;
          if (j >= 3)
            continue;
          if (j >= paramSensorEvent.values.length)
            break label212;
          ShakeMotionDetector.this.m_LastValues[j] = paramSensorEvent.values[j];
          break label212;
          if (i >= 3)
            continue;
          if (i >= paramSensorEvent.values.length)
            break label227;
          Object localObject2;
          localObject2 += Math.abs(paramSensorEvent.values[i] - ShakeMotionDetector.this.m_LastValues[i]);
          break label227;
          if (f <= 16.0F)
            continue;
          ShakeMotionDetector.this.listener.onShakeDetected();
          ShakeMotionDetector.access$114(ShakeMotionDetector.this, 2147483647L);
        }
        continue;
        label212: j++;
        continue;
        label218: float f = 0.0F;
        int i = 0;
        continue;
        label227: i++;
      }
    }
  };
  private SensorManager mSensorManager;
  private long m_LastSensorTime;
  private float[] m_LastValues;
  private Sensor s_Accelerometer;

  public ShakeMotionDetector(ShakeMotionDetectorListener paramShakeMotionDetectorListener, Context paramContext)
  {
    this.mSensorManager = ((SensorManager)paramContext.getSystemService("sensor"));
    this.listener = paramShakeMotionDetectorListener;
    this.s_Accelerometer = this.mSensorManager.getDefaultSensor(1);
  }

  public void destroy()
  {
    monitorenter;
    try
    {
      stopListening();
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

  public void startListening()
  {
    monitorenter;
    try
    {
      if (!this.isListening)
      {
        this.isListening = true;
        this.mSensorManager.registerListener(this.mSensorListener, this.s_Accelerometer, 2);
        this.m_LastSensorTime = 0L;
        this.m_LastValues = null;
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

  public void stopListening()
  {
    monitorenter;
    try
    {
      if (this.isListening)
      {
        this.mSensorManager.unregisterListener(this.mSensorListener);
        this.isListening = false;
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

  public static abstract interface ShakeMotionDetectorListener
  {
    public abstract void onShakeDetected();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.ShakeMotionDetector
 * JD-Core Version:    0.6.0
 */