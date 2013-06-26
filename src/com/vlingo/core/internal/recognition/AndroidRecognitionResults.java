package com.vlingo.core.internal.recognition;

public class AndroidRecognitionResults
{
  private String errorMessageString = null;
  private String recognitionResultPhrase = null;

  protected AndroidRecognitionResults(String paramString1, String paramString2)
  {
    this.errorMessageString = paramString1;
    this.recognitionResultPhrase = paramString2;
  }

  public String getErrorMessageString()
  {
    return this.errorMessageString;
  }

  public String getRecognitionResultPhrase()
  {
    return this.recognitionResultPhrase;
  }

  public boolean hasError()
  {
    if (this.errorMessageString != null);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean hasResultPhrase()
  {
    if (this.recognitionResultPhrase != null);
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.AndroidRecognitionResults
 * JD-Core Version:    0.6.0
 */