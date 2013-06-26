package com.vlingo.midas;

import android.content.Context;
import android.media.SoundPool;
import java.util.HashMap;

public class RegisterSoundPool
{
  private static SoundPool smSoundPool;
  private static HashMap<SoundType, Integer> smSoundPoolMap;

  public static void destroy()
  {
    monitorenter;
    try
    {
      if (smSoundPool != null)
      {
        smSoundPool.release();
        smSoundPool = null;
      }
      if (smSoundPoolMap != null)
      {
        smSoundPoolMap.clear();
        smSoundPoolMap = null;
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
      if (smSoundPool == null)
      {
        smSoundPool = new SoundPool(10, 3, 0);
        smSoundPoolMap = new HashMap();
        smSoundPoolMap.put(SoundType.WIDGET_IN_OUT, Integer.valueOf(smSoundPool.load(paramContext, 2131099666, 1)));
        smSoundPoolMap.put(SoundType.NETWORK_ERROR, Integer.valueOf(smSoundPool.load(paramContext, 2131099665, 1)));
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

  public static void play(SoundType paramSoundType)
  {
    monitorenter;
    try
    {
      SoundPool localSoundPool = smSoundPool;
      if (localSoundPool == null);
      while (true)
      {
        return;
        smSoundPool.play(((Integer)smSoundPoolMap.get(paramSoundType)).intValue(), 1.0F, 1.0F, 1, 0, 2.0F);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static enum SoundType
  {
    static
    {
      NETWORK_ERROR = new SoundType("NETWORK_ERROR", 1);
      SoundType[] arrayOfSoundType = new SoundType[2];
      arrayOfSoundType[0] = WIDGET_IN_OUT;
      arrayOfSoundType[1] = NETWORK_ERROR;
      $VALUES = arrayOfSoundType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.RegisterSoundPool
 * JD-Core Version:    0.6.0
 */