package com.vlingo.core.internal.util;

import android.content.ContentResolver;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import com.vlingo.core.internal.dialogmanager.util.IntegratedAppInfoInterface;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.display.WakeLockManager;
import com.vlingo.core.internal.display.WakeLockManagerNoop;
import com.vlingo.core.internal.safereader.ISafeReaderAlertHandler;
import com.vlingo.core.internal.safereader.SafeReaderAlert;
import java.util.LinkedList;

public class ClientSuppliedValues
{
  private static ClientSuppliedValuesInterface clientInterface;

  public static void disableDrivingModeSetting()
  {
    clientInterface.disableDrivingModeSetting();
  }

  public static void enableDrivingModeSetting()
  {
    clientInterface.enableDrivingModeSetting();
  }

  public static ADMController getADMController()
  {
    return clientInterface.getADMController();
  }

  public static AssetManager getAssets()
  {
    return clientInterface.getAssets();
  }

  public static Configuration getConfiguration()
  {
    return clientInterface.getConfiguration();
  }

  public static ContentResolver getContentResolver()
  {
    return clientInterface.getContentResolver();
  }

  public static int getDrivingModeWidgetMax()
  {
    return clientInterface.getDrivingModeWidgetMax();
  }

  public static IntegratedAppInfoInterface getFmRadioApplicationInfo()
  {
    return clientInterface.getFmRadioApplicationInfo();
  }

  public static void getForegroundFocus(ISafeReaderAlertHandler paramISafeReaderAlertHandler)
  {
    clientInterface.getForegroundFocus(paramISafeReaderAlertHandler);
  }

  public static String getHomeAddress()
  {
    return clientInterface.getHomeAddress();
  }

  public static Class getLaunchingClass()
  {
    return clientInterface.getLaunchingClass();
  }

  public static int getMaxDisplayNumber()
  {
    return clientInterface.getMaxDisplayNumber();
  }

  public static IntegratedAppInfoInterface getMemoApplicationInfo()
  {
    return clientInterface.getMemoApplicationInfo();
  }

  public static String getMmsSubject(Cursor paramCursor)
  {
    return clientInterface.getMmsSubject(paramCursor);
  }

  public static int getRegularWidgetMax()
  {
    return clientInterface.getRegularWidgetMax();
  }

  public static VVSActionHandler getSafeReaderHandler(boolean paramBoolean, LinkedList<SafeReaderAlert> paramLinkedList)
  {
    return clientInterface.getSafeReaderHandler(paramBoolean, paramLinkedList);
  }

  public static WakeLockManager getWakeLockManager()
  {
    WakeLockManager localWakeLockManager = clientInterface.getWakeLockManager();
    if (localWakeLockManager == null)
      localWakeLockManager = WakeLockManagerNoop.getInstance();
    return localWakeLockManager;
  }

  public static boolean handleNoResultAnswersWithGoogleSearch()
  {
    return clientInterface.handleNoResultAnswersWithGoogleSearch();
  }

  public static boolean isBlockingMode()
  {
    return clientInterface.isBlockingMode();
  }

  public static boolean isDrivingModeEnabled()
  {
    return clientInterface.isDrivingModeEnabled();
  }

  public static boolean isDrivingModeMessageNotificationEnabled()
  {
    return clientInterface.isDrivingModeMessageNotificationEnabled();
  }

  public static boolean isEyesFree()
  {
    return clientInterface.isEyesFree();
  }

  public static boolean isIUXComplete()
  {
    return clientInterface.isIUXComplete();
  }

  public static boolean isLanguageChangeAllowed()
  {
    return clientInterface.isLanguageChangeAllowed();
  }

  public static boolean isMessageReadbackFlowEnabled()
  {
    return clientInterface.isMessageReadbackFlowEnabled();
  }

  public static boolean isMessagingLocked()
  {
    return clientInterface.isMessagingLocked();
  }

  public static boolean isReadMessageBodyEnabled()
  {
    return clientInterface.isReadMessageBodyEnabled();
  }

  public static boolean isSeamless()
  {
    return clientInterface.isSeamless();
  }

  public static boolean isTalkbackOn()
  {
    return clientInterface.isTalkbackOn();
  }

  public static boolean isVideoCallingSupported()
  {
    return clientInterface.isVideoCallingSupported();
  }

  public static void releaseForegroundFocus(ISafeReaderAlertHandler paramISafeReaderAlertHandler)
  {
    clientInterface.releaseForegroundFocus(paramISafeReaderAlertHandler);
  }

  public static void setInterface(ClientSuppliedValuesInterface paramClientSuppliedValuesInterface)
  {
    clientInterface = paramClientSuppliedValuesInterface;
  }

  public static boolean shouldIncomingMessagesReadout()
  {
    return clientInterface.shouldIncomingMessagesReadout();
  }

  public static void updateCurrentLocale(Resources paramResources)
  {
    clientInterface.updateCurrentLocale(paramResources);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.ClientSuppliedValues
 * JD-Core Version:    0.6.0
 */