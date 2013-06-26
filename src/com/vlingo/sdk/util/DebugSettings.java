package com.vlingo.sdk.util;

public class DebugSettings
{
  private String mCarrier = null;
  private String mCarrierCountry = null;
  private boolean mForceNonDM = false;
  private String mLocation = null;
  private boolean mLogRecoWaveform = false;

  public String getCarrier()
  {
    return this.mCarrier;
  }

  public String getCarrierCountry()
  {
    return this.mCarrierCountry;
  }

  public String getLocation()
  {
    return this.mLocation;
  }

  public boolean isForceNonDM()
  {
    return this.mForceNonDM;
  }

  public boolean isLogRecoWaveform()
  {
    return this.mLogRecoWaveform;
  }

  public void setCarrier(String paramString)
  {
    this.mCarrier = paramString;
  }

  public void setCarrierCountry(String paramString)
  {
    this.mCarrierCountry = paramString;
  }

  public void setForceNonDM(boolean paramBoolean)
  {
    this.mForceNonDM = paramBoolean;
  }

  public void setLocation(String paramString)
  {
    this.mLocation = paramString;
  }

  public void setLogRecoWaveform(boolean paramBoolean)
  {
    this.mLogRecoWaveform = paramBoolean;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.util.DebugSettings
 * JD-Core Version:    0.6.0
 */