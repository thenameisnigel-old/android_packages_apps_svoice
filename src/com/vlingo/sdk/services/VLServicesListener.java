package com.vlingo.sdk.services;

import com.vlingo.sdk.recognition.VLAction;
import java.util.List;

public abstract interface VLServicesListener
{
  public abstract void onError(VLServicesErrors paramVLServicesErrors, String paramString);

  public abstract void onSuccess(List<VLAction> paramList);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.services.VLServicesListener
 * JD-Core Version:    0.6.0
 */