package com.vlingo.core.internal;

import java.util.HashMap;
import java.util.Map;

public class CoreAdapterRegistrar
{
  private static final Map<AdapterList, Class<? extends CoreAdapter>> handlers = new HashMap();

  public static Class<? extends CoreAdapter> get(AdapterList paramAdapterList)
  {
    return (Class)handlers.get(paramAdapterList);
  }

  public static void registerHandler(AdapterList paramAdapterList, Class<? extends CoreAdapter> paramClass)
  {
    handlers.put(paramAdapterList, paramClass);
  }

  public static void unregisterHandler(AdapterList paramAdapterList)
  {
    handlers.remove(paramAdapterList);
  }

  public static enum AdapterList
  {
    static
    {
      ExternalTTSEngine = new AdapterList("ExternalTTSEngine", 2);
      MicAnimation = new AdapterList("MicAnimation", 3);
      AudioSourceSelector = new AdapterList("AudioSourceSelector", 4);
      FbStringSelector = new AdapterList("FbStringSelector", 5);
      AdapterList[] arrayOfAdapterList = new AdapterList[6];
      arrayOfAdapterList[0] = NoiseCancel;
      arrayOfAdapterList[1] = PhraseSpotter;
      arrayOfAdapterList[2] = ExternalTTSEngine;
      arrayOfAdapterList[3] = MicAnimation;
      arrayOfAdapterList[4] = AudioSourceSelector;
      arrayOfAdapterList[5] = FbStringSelector;
      $VALUES = arrayOfAdapterList;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.CoreAdapterRegistrar
 * JD-Core Version:    0.6.0
 */