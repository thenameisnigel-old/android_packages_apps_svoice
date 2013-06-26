package com.sec.android.app.IWSpeechRecognizer;

public class IWSpeechRecognizerWrapper
{
  private static MMUIRecognizer uniqueInstance;

  public static MMUIRecognizer getInstance()
  {
    monitorenter;
    try
    {
      if (uniqueInstance == null)
      {
        MMUIRecognizer.init();
        uniqueInstance = new MMUIRecognizer();
      }
      MMUIRecognizer localMMUIRecognizer = uniqueInstance;
      monitorexit;
      return localMMUIRecognizer;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.app.IWSpeechRecognizer.IWSpeechRecognizerWrapper
 * JD-Core Version:    0.6.0
 */