package com.vlingo.core.internal.util;

import android.content.ContentResolver;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import com.vlingo.core.internal.dialogmanager.util.IntegratedAppInfoInterface;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.display.WakeLockManager;
import com.vlingo.core.internal.safereader.ISafeReaderAlertHandler;
import com.vlingo.core.internal.safereader.SafeReaderAlert;
import java.util.LinkedList;

public abstract interface ClientSuppliedValuesInterface
{
  public abstract void disableDrivingModeSetting();

  public abstract void enableDrivingModeSetting();

  public abstract ADMController getADMController();

  public abstract AssetManager getAssets();

  public abstract Configuration getConfiguration();

  public abstract ContentResolver getContentResolver();

  public abstract int getDrivingModeWidgetMax();

  public abstract IntegratedAppInfoInterface getFmRadioApplicationInfo();

  public abstract void getForegroundFocus(ISafeReaderAlertHandler paramISafeReaderAlertHandler);

  public abstract String getHomeAddress();

  public abstract Class getLaunchingClass();

  public abstract int getMaxDisplayNumber();

  public abstract IntegratedAppInfoInterface getMemoApplicationInfo();

  public abstract String getMmsSubject(Cursor paramCursor);

  public abstract int getRegularWidgetMax();

  public abstract VVSActionHandler getSafeReaderHandler(boolean paramBoolean, LinkedList<SafeReaderAlert> paramLinkedList);

  public abstract WakeLockManager getWakeLockManager();

  public abstract boolean handleNoResultAnswersWithGoogleSearch();

  public abstract boolean isBlockingMode();

  public abstract boolean isDrivingModeEnabled();

  public abstract boolean isDrivingModeMessageNotificationEnabled();

  public abstract boolean isEyesFree();

  public abstract boolean isIUXComplete();

  public abstract boolean isLanguageChangeAllowed();

  public abstract boolean isMessageReadbackFlowEnabled();

  public abstract boolean isMessagingLocked();

  public abstract boolean isReadMessageBodyEnabled();

  public abstract boolean isSeamless();

  public abstract boolean isTalkbackOn();

  public abstract boolean isVideoCallingSupported();

  public abstract void releaseForegroundFocus(ISafeReaderAlertHandler paramISafeReaderAlertHandler);

  public abstract boolean shouldIncomingMessagesReadout();

  public abstract void updateCurrentLocale(Resources paramResources);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.ClientSuppliedValuesInterface
 * JD-Core Version:    0.6.0
 */