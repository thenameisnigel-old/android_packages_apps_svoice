package com.vlingo.sdk.internal.recognizer;

import com.vlingo.sdk.internal.http.URL;

public abstract interface SRServerDetails
{
  public abstract URL getASRCancelURL();

  public abstract URL getASRURL();

  public abstract URL getStatsURL();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.SRServerDetails
 * JD-Core Version:    0.6.0
 */