package com.vlingo.sdk.internal.core;

import android.content.Context;
import com.vlingo.sdk.internal.recognizer.SRServerDetails;

public class ApplicationAdapter
{
  protected static ApplicationAdapter instance = null;
  Context context = null;

  public static void destroy()
  {
    instance = null;
  }

  public static ApplicationAdapter getInstance()
  {
    if (instance == null)
      instance = new ApplicationAdapter();
    return instance;
  }

  public void DEBUG_errorLog(String paramString1, String paramString2)
  {
  }

  public Context getApplicationContext()
  {
    return this.context;
  }

  public String getConnectionTestFieldID()
  {
    return null;
  }

  public SRServerDetails getConnectionTestServerDetails()
  {
    return null;
  }

  public void init(Context paramContext)
  {
    this.context = paramContext;
  }

  public boolean isAudioStreamingEnabled()
  {
    return true;
  }

  public void showFatalDialog(String paramString)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.core.ApplicationAdapter
 * JD-Core Version:    0.6.0
 */