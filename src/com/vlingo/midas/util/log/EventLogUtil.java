package com.vlingo.midas.util.log;

import android.util.Log;
import com.vlingo.core.internal.settings.Settings;

public abstract class EventLogUtil
  implements IEventLogUtil
{
  public static final String ACTION_ENABLE_ASR_AND_NLU_EVENT_LOGGING = "com.vlingo.midas.action.DEBUG_ASR_AND_NLU";
  protected static final String LOG_TAG = "Vlingo";

  public void disabled()
  {
    Settings.setBoolean(getSettingKey(), false);
  }

  public void enabled()
  {
    Settings.setBoolean(getSettingKey(), true);
  }

  protected abstract String getRecognitionCompleteMessage();

  protected abstract String getRecognitionErrorMessage();

  protected abstract String getRecognitionResultMessage(String paramString);

  protected abstract String getRecognitionStartMessage();

  protected abstract String getSettingKey();

  public boolean isEnabled()
  {
    return Settings.getBoolean(getSettingKey(), false);
  }

  public void onRecognitionComplete()
  {
    if (isEnabled())
      Log.w("Vlingo", getRecognitionCompleteMessage());
  }

  public void onRecognitionError()
  {
    if (isEnabled())
      Log.w("Vlingo", getRecognitionErrorMessage());
  }

  public void onRecognitionResult(String paramString)
  {
    if (isEnabled())
      Log.w("Vlingo", getRecognitionResultMessage(paramString));
  }

  public void onRecognitionStart()
  {
    if (isEnabled())
      Log.w("Vlingo", getRecognitionStartMessage());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.log.EventLogUtil
 * JD-Core Version:    0.6.0
 */