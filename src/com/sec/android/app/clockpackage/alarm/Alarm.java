package com.sec.android.app.clockpackage.alarm;

import com.sec.android.app.CscFeature;
import java.util.Locale;

public class Alarm
{
  public static final int ALARM_ACTIVE = 1;
  public static final int ALARM_AT_EVERYDAY = 1;
  public static final int ALARM_AT_ONCE = 0;
  public static final int ALARM_AT_WEEKDAY = 2;
  public static final int ALARM_AT_WEEKLY = 3;
  public static final String ALARM_DATA = "com.samsung.sec.android.clockpackage.alarm.ALARM_DATA";
  public static final int ALARM_DEFAULT_VOLUME = 7;
  public static final int[] ALARM_DURATION_TABLE;
  public static final int ALARM_INACTIVE = 0;
  public static final int ALARM_SNOOZE = 2;
  public static final int[] ALARM_SNOOZE_COUNT_TABLE;
  public static final int ALARM_SUBDUE = 3;
  public static final int ALARM_SUBDUE_NEXT = 10;
  public static final String BROADCAST_ALARM = "com.samsung.sec.android.clockpackage.alarm.ALARM_ALERT";
  public static final boolean DEBUG_ENG = false;
  public static final String NOTIFY_ALARM_CHANGE = "com.samsung.sec.android.clockpackage.alarm.NOTIFY_ALARM_CHANGE";
  private static final String TAG = "Alarm";

  static
  {
    int[] arrayOfInt1 = new int[5];
    arrayOfInt1[0] = 1;
    arrayOfInt1[1] = 2;
    arrayOfInt1[2] = 3;
    arrayOfInt1[3] = 5;
    arrayOfInt1[4] = 10;
    ALARM_SNOOZE_COUNT_TABLE = arrayOfInt1;
    int[] arrayOfInt2 = new int[5];
    arrayOfInt2[0] = 3;
    arrayOfInt2[1] = 5;
    arrayOfInt2[2] = 10;
    arrayOfInt2[3] = 15;
    arrayOfInt2[4] = 30;
    ALARM_DURATION_TABLE = arrayOfInt2;
  }

  public static final String digitToAlphabetStr(String paramString)
  {
    String[] arrayOfString = new String[10];
    arrayOfString[0] = "A";
    arrayOfString[1] = "B";
    arrayOfString[2] = "C";
    arrayOfString[3] = "D";
    arrayOfString[4] = "E";
    arrayOfString[5] = "F";
    arrayOfString[6] = "G";
    arrayOfString[7] = "H";
    arrayOfString[8] = "I";
    arrayOfString[9] = "J";
    String str = paramString.replaceAll(":", "##");
    for (int i = 0; ; i++)
    {
      if (i > 9)
        return str;
      str = str.replace(Integer.toString(i), arrayOfString[i]);
    }
  }

  public static boolean isMirroringEnabled()
  {
    int i = 0;
    if (CscFeature.getInstance().getEnableStatus("CscFeature_Common_EnableUiDisplayMirroring"))
    {
      String str = Locale.getDefault().getLanguage();
      if ((str.equalsIgnoreCase("ar")) || (str.equalsIgnoreCase("iw")) || (str.equalsIgnoreCase("he")))
        i = 1;
    }
    return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.app.clockpackage.alarm.Alarm
 * JD-Core Version:    0.6.0
 */