package com.vlingo.sdk.recognition;

import com.vlingo.sdk.VLComponent;
import com.vlingo.sdk.internal.recognizer.reader.DataReadyListner;
import com.vlingo.sdk.recognition.dialog.VLDialogContext;

public abstract interface VLRecognizer extends VLComponent
{
  public abstract void acceptedText(String paramString1, String paramString2);

  public abstract void cancelRecognition();

  public abstract String[] getSupportedLanguageList();

  public abstract void sendEvent(VLDialogContext paramVLDialogContext, VLRecognitionListener paramVLRecognitionListener);

  public abstract void startRecognition(VLRecognitionContext paramVLRecognitionContext, VLRecognitionListener paramVLRecognitionListener, DataReadyListner paramDataReadyListner);

  public abstract void stopRecognition();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.VLRecognizer
 * JD-Core Version:    0.6.0
 */