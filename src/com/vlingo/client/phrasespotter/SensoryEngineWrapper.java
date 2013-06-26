package com.vlingo.client.phrasespotter;

import android.util.Log;

public class SensoryEngineWrapper
{
  private static SensoryJNI uniqueInstance;

  public static SensoryJNI getInstance()
  {
    monitorenter;
    try
    {
      if (uniqueInstance == null)
      {
        Log.e("SensoryEngineWrapper", "getInstance() : make new SensoryJNI");
        SensoryJNI.init();
        uniqueInstance = new SensoryJNI();
      }
      while (true)
      {
        SensoryJNI localSensoryJNI = uniqueInstance;
        return localSensoryJNI;
        Log.e("SensoryEngineWrapper", "getInstance() : get existed SensoryJNI");
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.client.phrasespotter.SensoryEngineWrapper
 * JD-Core Version:    0.6.0
 */