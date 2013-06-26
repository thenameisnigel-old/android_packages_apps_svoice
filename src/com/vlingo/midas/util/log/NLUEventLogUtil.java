package com.vlingo.midas.util.log;

public class NLUEventLogUtil extends EventLogUtil
{
  public static final String ACTION_ENABLE_NLU_EVENT_LOGGING = "com.vlingo.midas.action.DEBUG_NLU_LOGGING";

  protected String getRecognitionCompleteMessage()
  {
    return null;
  }

  protected String getRecognitionErrorMessage()
  {
    return null;
  }

  protected String getRecognitionResultMessage(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("TEST_PLATFORM: NLU RESULTS: ");
    if (paramString == null);
    for (String str = ""; ; str = "\"" + paramString + "\"")
      return str;
  }

  protected String getRecognitionStartMessage()
  {
    return null;
  }

  protected String getSettingKey()
  {
    return "nlu_event_logging";
  }

  public void onRecognitionComplete()
  {
  }

  public void onRecognitionError()
  {
  }

  public void onRecognitionStart()
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.log.NLUEventLogUtil
 * JD-Core Version:    0.6.0
 */