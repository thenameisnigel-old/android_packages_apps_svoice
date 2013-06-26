package com.vlingo.core.internal.audio;

public class ResourceManager
{
  private static ResourceManager mgr = new ResourceManager();
  private boolean recognitionInUse = false;

  public static ResourceManager getInstance()
  {
    return mgr;
  }

  public boolean isRecognizing()
  {
    monitorenter;
    try
    {
      boolean bool = this.recognitionInUse;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void releaseRecognition()
  {
    monitorenter;
    try
    {
      this.recognitionInUse = false;
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

  public boolean requestRecognition(boolean paramBoolean)
  {
    int i = 1;
    monitorenter;
    try
    {
      boolean bool = this.recognitionInUse;
      if (bool)
        i = 0;
      while (true)
      {
        return i;
        this.recognitionInUse = true;
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
 * Qualified Name:     com.vlingo.core.internal.audio.ResourceManager
 * JD-Core Version:    0.6.0
 */