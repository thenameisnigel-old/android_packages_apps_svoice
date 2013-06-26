package com.samsung.commonimsservice;

public abstract interface IIMSRegisterStateListener
{
  public static final int DEREGISTERED = 4;
  public static final int DEREGISTERING = 2;
  public static final int DEREGISTER_FAILED = 5;
  public static final int REGISTERED = 3;
  public static final int REGISTERING = 1;
  public static final int REGISTER_FAILED = 6;

  public abstract void onNetworkTransition(int paramInt1, int paramInt2);

  public abstract void onRegistering(String paramString);

  public abstract void onRegistrationDone(String paramString, long paramLong);

  public abstract void onRegistrationFailed(String paramString1, int paramInt, String paramString2);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.commonimsservice.IIMSRegisterStateListener
 * JD-Core Version:    0.6.0
 */