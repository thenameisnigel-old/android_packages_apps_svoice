package com.vlingo.sdk.recognition;

import com.vlingo.sdk.internal.recognizer.results.TaggedResults.ParseGroup;
import java.util.List;

public abstract interface VLRecognitionResult
{
  public abstract List<VLAction> getActions();

  public abstract String getDialogGUID();

  public abstract byte[] getDialogState();

  public abstract int getDialogTurn();

  public abstract String getGUttId();

  public abstract NBestData getNBestData();

  public abstract TaggedResults.ParseGroup getParseGroup();

  public abstract String getResultString();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.VLRecognitionResult
 * JD-Core Version:    0.6.0
 */