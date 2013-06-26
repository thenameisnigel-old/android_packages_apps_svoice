package com.vlingo.sdk.internal.audio;

public class TTSRequest
{
  public String body;
  public String gender;
  public String senderAddress;
  public String senderDisplayName;
  public boolean senderOnly;
  public String speechRate = "Normal";
  public String subject;
  public String type;
  public int wordLimit = 0;

  public static TTSRequest getEmail(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    TTSRequest localTTSRequest = new TTSRequest();
    localTTSRequest.type = "Email";
    localTTSRequest.senderDisplayName = paramString1;
    localTTSRequest.subject = paramString2;
    localTTSRequest.body = paramString3;
    localTTSRequest.senderOnly = paramBoolean;
    return localTTSRequest;
  }

  public static TTSRequest getMMS(String paramString1, String paramString2, boolean paramBoolean)
  {
    TTSRequest localTTSRequest = new TTSRequest();
    localTTSRequest.type = "MMS";
    localTTSRequest.senderDisplayName = paramString1;
    localTTSRequest.subject = paramString2;
    localTTSRequest.senderOnly = paramBoolean;
    return localTTSRequest;
  }

  public static TTSRequest getSMS(String paramString1, String paramString2, boolean paramBoolean)
  {
    TTSRequest localTTSRequest = new TTSRequest();
    localTTSRequest.type = "SMS";
    localTTSRequest.senderDisplayName = paramString1;
    localTTSRequest.body = paramString2;
    localTTSRequest.senderOnly = paramBoolean;
    return localTTSRequest;
  }

  public static TTSRequest getText(String paramString)
  {
    TTSRequest localTTSRequest = new TTSRequest();
    localTTSRequest.type = "Confirm";
    localTTSRequest.body = paramString;
    return localTTSRequest;
  }

  public void setGender(String paramString)
  {
    this.gender = paramString;
  }

  public void setSpeechRate(String paramString)
  {
    this.speechRate = paramString;
  }

  public void setWordLimit(int paramInt)
  {
    this.wordLimit = paramInt;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.audio.TTSRequest
 * JD-Core Version:    0.6.0
 */