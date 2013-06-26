package com.vlingo.core.internal.util;

import android.content.Context;
import com.vlingo.core.internal.localsearch.LocalSearchListingCache;

public class ApplicationAdapter
{
  public static String VERSION = "%VLINGOANDROIDCORE_VERSION%";
  protected static ApplicationAdapter instance = null;
  protected LocalSearchListingCache businessItemCache = null;
  Context context = null;
  VlingoApplicationInterface theVlingoApplicationInterface = null;

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

  public Context getApplicationContext()
  {
    return this.context;
  }

  public LocalSearchListingCache getBusinessItemCache()
  {
    if (this.businessItemCache == null)
      this.businessItemCache = new LocalSearchListingCache();
    return this.businessItemCache;
  }

  public String getConnectionTestFieldID()
  {
    return null;
  }

  public VlingoApplicationInterface getVlingoApp()
  {
    return this.theVlingoApplicationInterface;
  }

  public void init(Context paramContext)
  {
    this.context = paramContext;
  }

  public boolean isAudioStreamingEnabled()
  {
    return true;
  }

  public void setInterface(VlingoApplicationInterface paramVlingoApplicationInterface)
  {
    this.theVlingoApplicationInterface = paramVlingoApplicationInterface;
  }

  public void showFatalDialog(String paramString)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.ApplicationAdapter
 * JD-Core Version:    0.6.0
 */