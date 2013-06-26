package com.vlingo.core.internal.dialogmanager;

public abstract interface DialogFlowTaskRegulator
{
  public abstract void onTaskWaitingToStart(EventType paramEventType, ResumeControl paramResumeControl);

  public static enum EventType
  {
    static
    {
      EventType[] arrayOfEventType = new EventType[2];
      arrayOfEventType[0] = RECOGNITION_START;
      arrayOfEventType[1] = SYSTEM_TEXT_TTS;
      $VALUES = arrayOfEventType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.DialogFlowTaskRegulator
 * JD-Core Version:    0.6.0
 */