package com.vlingo.core.internal.audio;

import com.vlingo.core.internal.CoreAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MicAnimationAdapter
  implements CoreAdapter
{
  private static List<MicAnimationListener> listeners = null;

  public static void addListener(MicAnimationListener paramMicAnimationListener)
  {
    init();
    listeners.add(paramMicAnimationListener);
  }

  public static void init()
  {
    if (listeners == null)
      listeners = new ArrayList();
  }

  public static void notifyListeners(int[] paramArrayOfInt)
  {
    if (listeners != null)
    {
      Iterator localIterator = listeners.iterator();
      while (localIterator.hasNext())
        ((MicAnimationListener)localIterator.next()).onMicAnimationData(paramArrayOfInt);
    }
  }

  public static void removeListener(MicAnimationListener paramMicAnimationListener)
  {
    listeners.remove(paramMicAnimationListener);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.MicAnimationAdapter
 * JD-Core Version:    0.6.0
 */