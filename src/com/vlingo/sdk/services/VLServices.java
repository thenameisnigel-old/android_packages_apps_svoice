package com.vlingo.sdk.services;

import com.vlingo.sdk.VLComponent;
import com.vlingo.sdk.services.userlogging.VLUserLoggerLogRecord;

public abstract interface VLServices extends VLComponent
{
  public abstract void sendActivityLog(String paramString, VLUserLoggerLogRecord paramVLUserLoggerLogRecord, VLServicesListener paramVLServicesListener);

  public abstract void sendHello(String paramString, VLServicesListener paramVLServicesListener);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.services.VLServices
 * JD-Core Version:    0.6.0
 */