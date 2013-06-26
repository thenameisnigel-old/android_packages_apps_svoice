package com.vlingo.core.internal.dialogmanager.util;

import android.net.Uri;

public abstract interface IntegratedAppInfoInterface
{
  public abstract IntegrateAppType getAppType();

  public abstract Uri getContentProviderUri();

  public abstract String getExecName();

  public abstract String getExecPackage();

  public abstract String getIntentNameCreate();

  public abstract String getIntentNameDelete();

  public abstract String getIntentNameStart();

  public abstract Uri getUpdateContentProviderUri();

  public abstract boolean isBroadcast();

  public abstract boolean isSNote();

  public abstract void setAppType(IntegrateAppType paramIntegrateAppType);

  public abstract void setBroadcast(boolean paramBoolean);

  public abstract void setContentProviderUri(Uri paramUri);

  public abstract void setExecName(String paramString);

  public abstract void setExecPackage(String paramString);

  public abstract void setIntentNameCreate(String paramString);

  public static enum IntegrateAppType
  {
    static
    {
      FM_RADIO = new IntegrateAppType("FM_RADIO", 1);
      IntegrateAppType[] arrayOfIntegrateAppType = new IntegrateAppType[2];
      arrayOfIntegrateAppType[0] = MEMO;
      arrayOfIntegrateAppType[1] = FM_RADIO;
      $VALUES = arrayOfIntegrateAppType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.util.IntegratedAppInfoInterface
 * JD-Core Version:    0.6.0
 */