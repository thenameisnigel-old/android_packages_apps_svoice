package com.vlingo.core.internal.safereader;

import android.app.Notification;

public abstract interface ISafeReaderServiceEngine
{
  public abstract void broadcastStatusUpdate();

  public abstract boolean doesSupportNotifications();

  public abstract Notification getNotification();

  public abstract int getNotificationId();

  public abstract boolean isSafeReaderOn();

  public abstract void pause();

  public abstract void registerAlertHandler(ISafeReaderAlertHandler paramISafeReaderAlertHandler);

  public abstract void resume();

  public abstract void safeReaderDeinit();

  public abstract void safeReaderInit(ISafeReaderServiceEngine paramISafeReaderServiceEngine);

  public abstract void skipCurrentlyPlayingItem();

  public abstract void startSafeReading();

  public abstract void stopSafeReading();

  public abstract void unregisterAlertHandler(ISafeReaderAlertHandler paramISafeReaderAlertHandler);

  public abstract void updateNotification();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.safereader.ISafeReaderServiceEngine
 * JD-Core Version:    0.6.0
 */