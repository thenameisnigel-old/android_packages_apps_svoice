package com.vlingo.sdk.internal.recognizer;

import android.net.NetworkInfo;
import com.vlingo.sdk.internal.net.ConnectionManager;
import com.vlingo.sdk.recognition.AudioSourceInfo;
import com.vlingo.sdk.recognition.VLRecognitionContext.CapitalizationMode;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent;
import java.util.HashMap;
import java.util.List;

public class SRContext
{
  protected volatile AudioSourceInfo audioSourceInfo;
  protected volatile boolean autoEndpointing = true;
  protected volatile boolean autoPunctuation = false;
  protected volatile VLRecognitionContext.CapitalizationMode capitalizationMode = VLRecognitionContext.CapitalizationMode.DEFAULT;
  protected volatile String curText = null;
  protected volatile int cursorPos = 0;
  protected volatile String custom6 = null;
  public volatile boolean customFlag = false;
  protected volatile String dialogGUID = null;
  protected volatile byte[] dialogState = null;
  protected volatile int dialogTurnNumber = -1;
  protected volatile HashMap<String, String> dmHeaderKVPairs;
  protected volatile List<VLDialogEvent> eventList = null;
  protected volatile String fieldContext = null;
  protected volatile String fieldID = null;
  protected volatile String fieldType = null;
  protected volatile boolean isDMRequest = false;
  protected volatile int maxAudioTime = 40000;
  protected volatile float minVoiceDuration = 0.08F;
  protected volatile float minVoiceLevel = 57.0F;
  protected volatile int noSpeechEndpointTimeout = 5000;
  protected volatile boolean profanityFilter = true;
  protected volatile float silenceThreshold = 11.0F;
  protected volatile int speechEndpointTimeout = 1500;
  protected volatile int speexComplexity = 3;
  protected volatile int speexQuality = 8;
  protected volatile int speexVariableBitrate = 0;
  protected volatile int speexVoiceActivityDetection = 1;
  protected volatile String username = null;
  protected volatile float voicePortion = 0.02F;

  public SRContext()
  {
  }

  public SRContext(String paramString)
  {
    this.fieldID = paramString;
  }

  public SRContext(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt)
  {
    this.fieldID = paramString1;
    this.fieldType = paramString2;
    this.fieldContext = paramString3;
    this.curText = paramString4;
    this.cursorPos = paramInt;
  }

  public AudioSourceInfo getAudioSourceInfo()
  {
    return this.audioSourceInfo;
  }

  public boolean getAutoEndpointing()
  {
    return this.autoEndpointing;
  }

  public boolean getAutoPunctuation()
  {
    return this.autoPunctuation;
  }

  public String getCapitalization()
  {
    String str;
    switch (1.$SwitchMap$com$vlingo$sdk$recognition$VLRecognitionContext$CapitalizationMode[this.capitalizationMode.ordinal()])
    {
    default:
      str = "Default";
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return str;
      str = "Off";
      continue;
      str = "Sentences";
      continue;
      str = "ProperNoun";
    }
  }

  public String getCurrentText()
  {
    if (this.curText == null);
    for (String str = ""; ; str = this.curText.trim())
      return str;
  }

  public String getCursorPosition()
  {
    if (this.curText == null);
    for (String str = "0"; ; str = "" + this.cursorPos)
      return str;
  }

  public int getCursorPositionInt()
  {
    return this.cursorPos;
  }

  public String getCustom6()
  {
    return this.custom6;
  }

  public String getCustomParam(String paramString)
  {
    String str = "";
    ConnectionManager localConnectionManager = ConnectionManager.getInstance();
    if (("Custom1".equals(paramString)) && (localConnectionManager.getNetworkInfo() != null))
      str = "ConnType=" + localConnectionManager.getNetworkInfo().getTypeName();
    if (("Custom2".equals(paramString)) && (localConnectionManager.getNetworkInfo() != null) && (localConnectionManager.getNetworkInfo().getTypeName().equalsIgnoreCase("wifi")))
      str = "WifiLinkSpd=" + localConnectionManager.getWifiLinkSpeed();
    if ((localConnectionManager.getNetworkInfo() != null) && (!localConnectionManager.getNetworkInfo().getTypeName().equalsIgnoreCase("wifi")))
    {
      if ("Custom3".equals(paramString))
        str = "NetworkType=" + localConnectionManager.getNetworkTypeName();
      if ("Custom4".equals(paramString))
        str = "CdmaSigLev=" + localConnectionManager.getCdmaSignal();
      if ("Custom5".equals(paramString))
        str = "EvdoSigLev=" + localConnectionManager.getEvdoSignal();
      if ("Custom6".equals(paramString))
        str = "GsmSigLev=" + localConnectionManager.getGsmSignal();
    }
    return str;
  }

  public HashMap<String, String> getDMHeaderKVPairs()
  {
    return this.dmHeaderKVPairs;
  }

  public String getDialogGUID()
  {
    return this.dialogGUID;
  }

  public byte[] getDialogState()
  {
    return this.dialogState;
  }

  public int getDialogTurnNumber()
  {
    return this.dialogTurnNumber;
  }

  public List<VLDialogEvent> getEvents()
  {
    return this.eventList;
  }

  public String getFieldContext()
  {
    if (this.fieldContext == null);
    for (String str = ""; ; str = this.fieldContext)
      return str;
  }

  public String getFieldID()
  {
    return this.fieldID;
  }

  public String getFieldType()
  {
    if (getProfanityFilter());
    for (String str = "<xml><taboofilter>" + "on"; ; str = "<xml><taboofilter>" + "off")
      return str + "</taboofilter></xml>";
  }

  public int getMaxAudioTime()
  {
    return this.maxAudioTime;
  }

  public float getMinVoiceDuration()
  {
    return this.minVoiceDuration;
  }

  public float getMinVoiceLevel()
  {
    return this.minVoiceLevel;
  }

  public int getNoSpeechEndpointTimeout()
  {
    return this.noSpeechEndpointTimeout;
  }

  public boolean getProfanityFilter()
  {
    return this.profanityFilter;
  }

  public float getSilenceThreshold()
  {
    return this.silenceThreshold;
  }

  public int getSpeechEndpointTimeout()
  {
    return this.speechEndpointTimeout;
  }

  public int getSpeexComplexity()
  {
    return this.speexComplexity;
  }

  public int getSpeexQuality()
  {
    return this.speexQuality;
  }

  public int getSpeexVariableBitrate()
  {
    return this.speexVariableBitrate;
  }

  public int getSpeexVoiceActivityDetection()
  {
    return this.speexVoiceActivityDetection;
  }

  public String getUsername()
  {
    return this.username;
  }

  public float getVoicePortion()
  {
    return this.voicePortion;
  }

  public boolean isDMRequest()
  {
    return this.isDMRequest;
  }

  public void logNetworkInfo()
  {
  }

  public void setAudioSourceInfo(AudioSourceInfo paramAudioSourceInfo)
  {
    this.audioSourceInfo = paramAudioSourceInfo;
  }

  public void setAutoEndpointing(boolean paramBoolean)
  {
    this.autoEndpointing = paramBoolean;
  }

  public void setAutoPunctuation(boolean paramBoolean)
  {
    this.autoPunctuation = paramBoolean;
  }

  public void setCapitalizationMode(VLRecognitionContext.CapitalizationMode paramCapitalizationMode)
  {
    this.capitalizationMode = paramCapitalizationMode;
  }

  public void setCurText(String paramString)
  {
    this.curText = paramString;
  }

  public void setCursorPos(int paramInt)
  {
    this.cursorPos = paramInt;
  }

  public void setCustom6(String paramString)
  {
    this.custom6 = paramString;
  }

  public void setDMHeaderKVPairs(HashMap<String, String> paramHashMap)
  {
    this.dmHeaderKVPairs = paramHashMap;
  }

  public void setDialogGUID(String paramString)
  {
    this.dialogGUID = paramString;
  }

  public void setDialogState(byte[] paramArrayOfByte)
  {
    this.dialogState = paramArrayOfByte;
  }

  public void setDialogTurnNumber(int paramInt)
  {
    this.dialogTurnNumber = paramInt;
  }

  public void setEvents(List<VLDialogEvent> paramList)
  {
    this.eventList = paramList;
  }

  public void setFieldContext(String paramString)
  {
    this.fieldContext = paramString;
  }

  public void setFieldID(String paramString)
  {
    this.fieldID = paramString;
  }

  public void setFieldType(String paramString)
  {
    this.fieldType = paramString;
  }

  public void setIsDMRequest(boolean paramBoolean)
  {
    this.isDMRequest = paramBoolean;
  }

  public void setMaxAudioTime(int paramInt)
  {
    this.maxAudioTime = paramInt;
  }

  public void setNoSpeechEndpointTimeout(int paramInt)
  {
    this.noSpeechEndpointTimeout = paramInt;
  }

  public void setProfanityFilter(boolean paramBoolean)
  {
    this.profanityFilter = paramBoolean;
  }

  public void setSilenceDetectionParams(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this.silenceThreshold = paramFloat1;
    this.minVoiceDuration = paramFloat2;
    this.voicePortion = paramFloat3;
    this.minVoiceLevel = paramFloat4;
  }

  public void setSpeechEndpointTimeout(int paramInt)
  {
    this.speechEndpointTimeout = paramInt;
  }

  public void setSpeexParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.speexComplexity = paramInt1;
    this.speexQuality = paramInt2;
    this.speexVariableBitrate = paramInt3;
    this.speexVoiceActivityDetection = paramInt4;
  }

  public void setUsername(String paramString)
  {
    this.username = paramString;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.SRContext
 * JD-Core Version:    0.6.0
 */