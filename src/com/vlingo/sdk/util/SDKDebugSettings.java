package com.vlingo.sdk.util;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SDKDebugSettings
{
  private String mCarrier = null;
  private String mCarrierCountry = null;
  private boolean mForceNonDM = false;
  private String mLocation = null;
  private boolean mLogRecoWaveform = false;
  private String mModelNumber = null;
  private String mRawServerLogBase = "rawServerLog";
  private ServerResponseLoggingState serverResponseLoggingState = ServerResponseLoggingState.NONE;

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

  public String getModelNumber()
  {
    return this.mModelNumber;
  }

  public ServerResponseLoggingState getServerResponseLoggingState()
  {
    return this.serverResponseLoggingState;
  }

  public String getmRawServerLogBase()
  {
    return this.mRawServerLogBase;
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

  public void setModelNumber(String paramString)
  {
    this.mModelNumber = paramString;
  }

  public void setServerResponseLoggingState(ServerResponseLoggingState paramServerResponseLoggingState)
  {
    this.serverResponseLoggingState = paramServerResponseLoggingState;
  }

  public void setmRawServerLogBase(String paramString)
  {
    this.mRawServerLogBase = paramString;
  }

  public static enum ServerResponseLoggingState
  {
    private static final Map<String, ServerResponseLoggingState> lookup;
    private static String[] stringValues;
    private String code;

    static
    {
      REPLAY = new ServerResponseLoggingState("REPLAY", 2, "Replay");
      ServerResponseLoggingState[] arrayOfServerResponseLoggingState = new ServerResponseLoggingState[3];
      arrayOfServerResponseLoggingState[0] = NONE;
      arrayOfServerResponseLoggingState[1] = SAVE;
      arrayOfServerResponseLoggingState[2] = REPLAY;
      $VALUES = arrayOfServerResponseLoggingState;
      lookup = new HashMap();
      EnumSet localEnumSet = EnumSet.allOf(ServerResponseLoggingState.class);
      stringValues = new String[localEnumSet.size()];
      int i = 0;
      Iterator localIterator = localEnumSet.iterator();
      while (localIterator.hasNext())
      {
        ServerResponseLoggingState localServerResponseLoggingState = (ServerResponseLoggingState)localIterator.next();
        String[] arrayOfString = stringValues;
        int j = i + 1;
        arrayOfString[i] = localServerResponseLoggingState.getCode();
        lookup.put(localServerResponseLoggingState.getCode(), localServerResponseLoggingState);
        i = j;
      }
    }

    private ServerResponseLoggingState(String paramString)
    {
      this.code = paramString;
    }

    public static ServerResponseLoggingState get(String paramString)
    {
      return (ServerResponseLoggingState)lookup.get(paramString);
    }

    public static String[] getStringValues()
    {
      return stringValues;
    }

    public String getCode()
    {
      return this.code;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.util.SDKDebugSettings
 * JD-Core Version:    0.6.0
 */