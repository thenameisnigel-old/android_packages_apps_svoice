package com.vlingo.sdk.internal.audio;

import com.vlingo.sdk.internal.AndroidServerDetails;
import com.vlingo.sdk.internal.http.HttpCallback;
import com.vlingo.sdk.internal.http.HttpResponse;
import com.vlingo.sdk.internal.util.StringUtils;
import com.vlingo.sdk.internal.util.XmlUtils;
import com.vlingo.sdk.internal.vlservice.VLHttpServiceRequest;
import java.util.Hashtable;

public class TTSServerManager
{
  private static int ttsSequenceId = 0;

  public static VLHttpServiceRequest createTTSRequest(TTSRequest paramTTSRequest, String paramString, HttpCallback paramHttpCallback)
  {
    String str = getTTSRequestXML(paramTTSRequest, paramString);
    Hashtable localHashtable = new Hashtable(2);
    localHashtable.put("Content-type", "application/x-www-form-urlencoded");
    localHashtable.put("X-vlrequest", "CL=true; RequestID=" + ttsSequenceId);
    ttsSequenceId = 1 + ttsSequenceId;
    VLHttpServiceRequest localVLHttpServiceRequest = VLHttpServiceRequest.createVLRequest("TTS", paramHttpCallback, AndroidServerDetails.getTTSURL(), str, paramString);
    localVLHttpServiceRequest.setExtraHeaders(localHashtable);
    localVLHttpServiceRequest.setMaxRetry(0);
    return localVLHttpServiceRequest;
  }

  private static String getTTSRequestXML(TTSRequest paramTTSRequest, String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (StringUtils.isNullOrWhiteSpace(paramString));
    for (String str = "en-US"; ; str = paramString)
    {
      localStringBuffer.append("<").append("TTSMessageRequest").append(" ");
      localStringBuffer.append("SenderOnly").append("=\"").append(paramTTSRequest.senderOnly).append("\" ");
      localStringBuffer.append("Gender").append("=\"").append(paramTTSRequest.gender).append("\" ");
      localStringBuffer.append("Language").append("=\"").append(str).append("\" ");
      localStringBuffer.append("AudioFormat").append("=\"").append("MP3").append("\" ");
      localStringBuffer.append("MessageType").append("=\"").append(paramTTSRequest.type).append("\" ");
      localStringBuffer.append("SpeechRate").append("=\"").append(paramTTSRequest.speechRate).append("\" ");
      if (paramTTSRequest.wordLimit > 0)
        localStringBuffer.append("WordLimit").append("=\"").append(paramTTSRequest.wordLimit).append("\" ");
      localStringBuffer.append("Volume").append("=\"").append(100).append("\" ");
      localStringBuffer.append("Version").append("=\"").append("1.0").append("\" ");
      localStringBuffer.append("PreSilence").append("=\"").append("0").append("\"");
      localStringBuffer.append(">");
      if (!StringUtils.isNullOrWhiteSpace(paramTTSRequest.senderDisplayName))
      {
        localStringBuffer.append("<").append("Sender").append(">");
        localStringBuffer.append(XmlUtils.wrapInCDATA(paramTTSRequest.senderDisplayName));
        localStringBuffer.append("</").append("Sender").append(">");
      }
      if (!StringUtils.isNullOrWhiteSpace(paramTTSRequest.subject))
      {
        localStringBuffer.append("<").append("Subject").append(">");
        localStringBuffer.append(XmlUtils.wrapInCDATA(paramTTSRequest.subject));
        localStringBuffer.append("</").append("Subject").append(">");
      }
      if (!StringUtils.isNullOrWhiteSpace(paramTTSRequest.body))
      {
        localStringBuffer.append("<").append("Body").append(">");
        localStringBuffer.append(XmlUtils.wrapInCDATA(paramTTSRequest.body));
        localStringBuffer.append("</").append("Body").append(">");
      }
      localStringBuffer.append("</").append("TTSMessageRequest").append(">");
      return localStringBuffer.toString();
    }
  }

  public static byte[] parseTTSResponse(HttpResponse paramHttpResponse)
  {
    byte[] arrayOfByte = paramHttpResponse.getDataAsBytes();
    if (arrayOfByte != null)
      if (arrayOfByte.length >= 400)
        break label21;
    for (arrayOfByte = null; ; arrayOfByte = null)
      label21: 
      do
        return arrayOfByte;
      while ((arrayOfByte[0] != 60) || (arrayOfByte[1] != 63) || (arrayOfByte[2] != 120) || (arrayOfByte[3] != 109) || (arrayOfByte[4] != 108));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.audio.TTSServerManager
 * JD-Core Version:    0.6.0
 */