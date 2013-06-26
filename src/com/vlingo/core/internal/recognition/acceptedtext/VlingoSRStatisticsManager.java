package com.vlingo.core.internal.recognition.acceptedtext;

import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.recognition.VLRecognizer;

public class VlingoSRStatisticsManager
{
  private static VlingoSRStatisticsManager instance;

  public static VlingoSRStatisticsManager getInstance()
  {
    if (instance == null)
      instance = new VlingoSRStatisticsManager();
    return instance;
  }

  public void sendAcceptedText(AcceptedText paramAcceptedText)
  {
    if ((paramAcceptedText != null) && (paramAcceptedText.getGUttId() != null))
      VLSdk.getInstance().getRecognizer().acceptedText(paramAcceptedText.getGUttId(), paramAcceptedText.getXMLString());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.acceptedtext.VlingoSRStatisticsManager
 * JD-Core Version:    0.6.0
 */