package com.vlingo.core.internal.dialogmanager;

import com.vlingo.sdk.internal.VLRecognizerImpl;
import com.vlingo.sdk.internal.recognizer.reader.DataReadyListner;
import com.vlingo.sdk.recognition.VLRecognitionContext;
import com.vlingo.sdk.recognition.VLRecognitionListener;
import com.vlingo.sdk.recognition.VLRecognizer;
import com.vlingo.sdk.recognition.dialog.VLDialogContext;

public class VLDummyRecognizer
  implements VLRecognizer
{
  VLRecognizerImpl mRealRecognizer;

  public void acceptedText(String paramString1, String paramString2)
  {
  }

  public void cancelRecognition()
  {
  }

  public void destroy()
  {
  }

  public String[] getSupportedLanguageList()
  {
    return null;
  }

  public boolean isValid()
  {
    if ((this.mRealRecognizer != null) && (this.mRealRecognizer.isValid()));
    for (int i = 1; ; i = 0)
      return i;
  }

  public void sendEvent(VLDialogContext paramVLDialogContext, VLRecognitionListener paramVLRecognitionListener)
  {
  }

  public void startRecognition(VLRecognitionContext paramVLRecognitionContext, VLRecognitionListener paramVLRecognitionListener, DataReadyListner paramDataReadyListner)
  {
  }

  public void stopRecognition()
  {
  }

  void unregister()
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.VLDummyRecognizer
 * JD-Core Version:    0.6.0
 */