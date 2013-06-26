package com.vlingo.midas.util.log;

public class AsrEventLogUtil extends EventLogUtil
{
  public static final String ACTION_ENABLE_ASR_EVENT_LOGGING = "com.vlingo.midas.action.DEBUG_VOICE_COMMAND";

  protected String getRecognitionCompleteMessage()
  {
    return "TEST_PLATFORM: VOICE_SEARCH_COMPLETE";
  }

  protected String getRecognitionErrorMessage()
  {
    return "TEST_PLATFORM: ERROR";
  }

  protected String getRecognitionResultMessage(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("TEST_PLATFORM: RESULTS: result: ");
    if (paramString == null);
    for (String str = ""; ; str = "\"" + paramString + "\"")
      return str;
  }

  protected String getRecognitionStartMessage()
  {
    return "TEST_PLATFORM: SPEAK_NOW";
  }

  protected String getSettingKey()
  {
    return "asr_event_logging";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.log.AsrEventLogUtil
 * JD-Core Version:    0.6.0
 */