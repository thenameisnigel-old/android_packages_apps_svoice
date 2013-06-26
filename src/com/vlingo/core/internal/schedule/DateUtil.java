package com.vlingo.core.internal.schedule;

import android.text.format.Time;
import android.util.Pair;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.array;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ApplicationAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

public class DateUtil
{
  public static String CANNONICAL_FULL_DATE_FORMAT;
  public static String CANONICAL_DATE_AND_TIME_FORMAT;
  public static String CANONICAL_DATE_FORMAT;
  public static String CANONICAL_RELAIVE_TIME_FORMAT;
  public static String CANONICAL_TIME_FORMAT;
  public static String DAY_AND_MONTH_BAD_FORMAT;
  public static String DAY_AND_MONTH_GOOD_FORMAT;
  public static long ONE_DAY;
  public static long ONE_HOUR;
  public static long ONE_MINUTE;
  public static long ONE_SECOND = 1000L;
  private static ArrayList<Pair<String, Pair<DateType, Integer>>> dateMatcher;

  static
  {
    ONE_MINUTE = 60L * ONE_SECOND;
    ONE_HOUR = 60L * ONE_MINUTE;
    ONE_DAY = 24L * ONE_HOUR;
    CANONICAL_DATE_FORMAT = "yyyy-MM-dd";
    CANNONICAL_FULL_DATE_FORMAT = "MMMM dd, yyyy";
    CANONICAL_TIME_FORMAT = "HH:mm";
    CANONICAL_DATE_AND_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    CANONICAL_RELAIVE_TIME_FORMAT = "+HH:mm:ss";
    DAY_AND_MONTH_BAD_FORMAT = "--MM-dd";
    DAY_AND_MONTH_GOOD_FORMAT = "dd MMMM";
  }

  private static Time adjustTimeForCriteria(Time paramTime, DateType paramDateType, int paramInt)
  {
    if (paramDateType == DateType.WEEK_DAY)
      paramTime.setJulianDay(Time.getJulianDay(paramTime.normalize(true), 0L) + (7 + (paramInt - paramTime.weekDay)) % 7);
    while (true)
    {
      return paramTime;
      if (paramDateType == DateType.MONTH_DAY)
      {
        if (paramInt < paramTime.monthDay)
        {
          if (12 == paramTime.month)
          {
            paramTime.set(paramInt, 1, 1 + paramTime.year);
            continue;
          }
          paramTime.set(paramInt, 1 + paramTime.month, paramTime.year);
          continue;
        }
        paramTime.set(paramInt, paramTime.month, paramTime.year);
        continue;
      }
      if ((paramDateType != DateType.MONTH) || (paramInt == paramTime.month))
        continue;
      if (paramInt < paramTime.month)
        paramTime.set(1, paramInt, 1 + paramTime.year);
      paramTime.set(1, paramInt, paramTime.year);
    }
  }

  public static long adjustTimeForDateString(String paramString, Time paramTime)
  {
    Time localTime = new Time(paramTime);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(CANONICAL_DATE_FORMAT);
    long l1;
    String str;
    int i;
    try
    {
      Date localDate = localSimpleDateFormat.parse(paramString);
      localDate.setHours(localTime.hour);
      localDate.setMinutes(localTime.minute);
      long l2 = localDate.getTime();
      l1 = l2;
      return l1;
    }
    catch (ParseException localParseException)
    {
      while (true)
      {
        str = paramString.toLowerCase();
        if (str.contains("today"))
        {
          l1 = localTime.normalize(true);
          continue;
        }
        if (!str.contains("tomorrow"))
          break;
        localTime.set(localTime.normalize(true) + ONE_DAY);
        l1 = localTime.normalize(true);
      }
      initMatcher();
      Iterator localIterator = dateMatcher.iterator();
      while (localIterator.hasNext())
      {
        Pair localPair = (Pair)localIterator.next();
        if (!str.matches(".*(" + (String)localPair.first + ").*"))
          continue;
        adjustTimeForCriteria(localTime, (DateType)((Pair)localPair.second).first, ((Integer)((Pair)localPair.second).second).intValue());
      }
      i = 31;
    }
    while (true)
    {
      if (i > 0)
      {
        if (str.matches(".*" + Integer.toString(i) + ".*"))
          adjustTimeForCriteria(localTime, DateType.MONTH_DAY, i);
      }
      else
      {
        l1 = localTime.normalize(true);
        break;
      }
      i--;
    }
  }

  public static long adjustTimeForTimeString(String paramString, Time paramTime)
  {
    return getTimeFromTimeString(paramString, paramTime, true);
  }

  public static long endOfGivenDay(long paramLong)
  {
    Time localTime = new Time();
    localTime.set(paramLong);
    localTime.hour = 23;
    localTime.minute = 59;
    localTime.second = 59;
    return localTime.normalize(false);
  }

  public static String formatMemoDate(long paramLong, String paramString, Locale paramLocale)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    String str1 = "";
    if (!paramLocale.getLanguage().equals(Locale.KOREAN.getLanguage()))
      str1 = str1 + localCalendar.getDisplayName(7, 1, paramLocale) + ", ";
    String str2;
    if ((paramString != null) && (paramString.equals(CANONICAL_DATE_FORMAT)))
    {
      StringBuilder localStringBuilder3 = new StringBuilder().append(str1);
      Object[] arrayOfObject3 = new Object[3];
      arrayOfObject3[0] = localCalendar;
      arrayOfObject3[1] = localCalendar;
      arrayOfObject3[2] = localCalendar;
      str2 = String.format("%tY.%tm.%td", arrayOfObject3);
    }
    while (true)
    {
      if (paramLocale.getLanguage().equals(Locale.KOREAN.getLanguage()))
        str2 = str2 + " " + localCalendar.getDisplayName(7, 1, paramLocale);
      return str2;
      if ((paramString != null) && (paramString.equals("dd-MM-yyyy")))
      {
        StringBuilder localStringBuilder2 = new StringBuilder().append(str1);
        Object[] arrayOfObject2 = new Object[3];
        arrayOfObject2[0] = localCalendar;
        arrayOfObject2[1] = localCalendar;
        arrayOfObject2[2] = localCalendar;
        str2 = String.format("%td.%tm.%tY", arrayOfObject2);
        continue;
      }
      StringBuilder localStringBuilder1 = new StringBuilder().append(str1);
      Object[] arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = localCalendar;
      arrayOfObject1[1] = localCalendar;
      arrayOfObject1[2] = localCalendar;
      str2 = String.format("%tm.%td.%tY", arrayOfObject1);
    }
  }

  public static String formatMemoTime(long paramLong, boolean paramBoolean, Locale paramLocale)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    StringBuilder localStringBuilder;
    Object[] arrayOfObject2;
    if (paramBoolean)
    {
      localStringBuilder = new StringBuilder().append("");
      arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = localCalendar;
    }
    String str1;
    for (String str2 = String.format("%tR", arrayOfObject2); ; str2 = str1 + " " + localCalendar.getDisplayName(9, 2, paramLocale))
    {
      return str2;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = localCalendar;
      arrayOfObject1[1] = localCalendar;
      str1 = String.format("%tI:%tM", arrayOfObject1);
    }
  }

  public static String formatWidgetDate(Date paramDate, Locale paramLocale)
  {
    String str1 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_cradle_date);
    SimpleDateFormat localSimpleDateFormat1;
    String str4;
    if ((paramLocale.equals(Locale.KOREA)) || (paramLocale.equals(Locale.JAPAN)))
    {
      String str2 = new SimpleDateFormat("MMMM d", paramLocale).format(paramDate);
      if (paramLocale.equals(Locale.JAPAN))
      {
        localSimpleDateFormat1 = new SimpleDateFormat(" (EEEE)", paramLocale);
        String str3 = localSimpleDateFormat1.format(paramDate);
        str4 = str2 + str1 + str3;
      }
    }
    while (true)
    {
      return str4;
      localSimpleDateFormat1 = new SimpleDateFormat(", EEEE", paramLocale);
      break;
      if (paramLocale.equals(Locale.CHINA))
      {
        SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("EEEE, MMM d", paramLocale);
        str4 = localSimpleDateFormat2.format(paramDate) + str1;
        continue;
      }
      str4 = new SimpleDateFormat("EEE, d MMM", paramLocale).format(paramDate);
    }
  }

  public static long fromNow(long paramLong)
  {
    return paramLong + now();
  }

  public static Date getDateFromCanonicalDateAndTimeString(String paramString)
    throws ParseException
  {
    return new SimpleDateFormat(CANONICAL_DATE_AND_TIME_FORMAT).parse(paramString);
  }

  public static Date getDateFromCanonicalString(String paramString)
    throws ParseException
  {
    return new SimpleDateFormat(CANONICAL_DATE_FORMAT).parse(paramString);
  }

  public static String getDayAndMonthFromString(String paramString)
    throws ParseException
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(DAY_AND_MONTH_BAD_FORMAT);
    return new SimpleDateFormat(DAY_AND_MONTH_GOOD_FORMAT).format(localSimpleDateFormat.parse(paramString));
  }

  public static String getLocailizeDateStringFromCanonicalString(String paramString)
    throws ParseException
  {
    return java.text.DateFormat.getDateInstance(1, Settings.getCurrentLocale()).format(getDateFromCanonicalString(paramString));
  }

  public static String getLongDateStringFromPhonePreferencesString(String paramString)
    throws ParseException
  {
    return java.text.DateFormat.getDateInstance(1, Settings.getCurrentLocale()).format(android.text.format.DateFormat.getDateFormat(ApplicationAdapter.getInstance().getApplicationContext()).parse(paramString));
  }

  public static long getMillisFromString(String paramString, boolean paramBoolean)
  {
    Time localTime = new Time();
    localTime.set(now());
    return getMillisFromString(paramString, paramBoolean, localTime);
  }

  public static long getMillisFromString(String paramString, boolean paramBoolean, Time paramTime)
  {
    long l;
    if (paramBoolean)
      l = adjustTimeForDateString(paramString, paramTime);
    while (true)
    {
      return l;
      l = adjustTimeForTimeString(paramString, paramTime);
    }
  }

  public static long getMillisFromTimeString(String paramString)
  {
    Time localTime = new Time();
    localTime.set(now());
    return adjustTimeForTimeString(paramString, localTime);
  }

  public static Date getTimeFromCanonicalTimeString(String paramString)
    throws ParseException
  {
    return new SimpleDateFormat(CANONICAL_RELAIVE_TIME_FORMAT).parse(paramString);
  }

  public static long getTimeFromTimeString(String paramString, Time paramTime, boolean paramBoolean)
  {
    int i = 0;
    if (paramString.startsWith("+"))
    {
      i = 1;
      paramString = paramString.substring(1);
    }
    String str1 = null;
    String str2 = null;
    if ((paramString.length() == 5) || (paramString.length() == 4))
    {
      int j = paramString.indexOf(':');
      if (j > 0)
      {
        str1 = paramString.substring(0, j);
        int i4 = j + 1;
        int i5 = paramString.length();
        str2 = paramString.substring(i4, i5);
      }
    }
    int k = -1;
    int m = -1;
    if ((str1 != null) && (str1.matches("\\d+")) && (str2 != null) && (str2.matches("\\d+")))
    {
      k = Integer.parseInt(str1);
      m = Integer.parseInt(str2);
      if ((k >= 24) || (m >= 60) || (k < 0) || (m < 0))
      {
        k = -1;
        m = -1;
      }
    }
    if ((-1 != k) && (-1 != m) && (i != 0))
    {
      int i1 = paramTime.hour;
      int i2 = paramTime.minute;
      int i3 = (i2 + m) / 60;
      m = (i2 + m) % 60;
      k = (i3 + (i1 + k)) % 24;
    }
    int n = 0;
    if ((paramBoolean) && (m + k * 60 < 60 * paramTime.hour + paramTime.minute))
      n = 1;
    Time localTime = new Time();
    localTime.set(paramTime.normalize(true) + 1000 * (60 * (60 * (n * 24))));
    localTime.set(0, m, k, localTime.monthDay, localTime.month, localTime.year);
    return localTime.normalize(true);
  }

  private static void initMatcher()
  {
    dateMatcher = new ArrayList();
    String[] arrayOfString1 = VlingoAndroidCore.getResourceProvider().getStringArray(ResourceIdProvider.array.core_monthMatchers);
    for (int i = 0; i < 12; i++)
    {
      if (i > arrayOfString1.length)
        continue;
      dateMatcher.add(new Pair(arrayOfString1[i], new Pair(DateType.MONTH, Integer.valueOf(i))));
    }
    String[] arrayOfString2 = VlingoAndroidCore.getResourceProvider().getStringArray(ResourceIdProvider.array.core_dayMatchers);
    for (int j = 0; j < 7; j++)
    {
      if (j > arrayOfString2.length)
        continue;
      dateMatcher.add(new Pair(arrayOfString2[j], new Pair(DateType.WEEK_DAY, Integer.valueOf(j))));
    }
  }

  public static boolean isToday(String paramString)
  {
    boolean bool = true;
    long l1 = getMillisFromString(paramString, bool);
    long l2 = now();
    long l3 = startOfGivenDay(l2);
    long l4 = endOfGivenDay(l2);
    if ((l1 >= l3) && (l1 <= l4));
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  public static long now()
  {
    return System.currentTimeMillis();
  }

  public static long startOfGivenDay(long paramLong)
  {
    Time localTime = new Time();
    localTime.set(paramLong);
    localTime.hour = 0;
    localTime.minute = 0;
    localTime.second = 0;
    return localTime.normalize(false);
  }

  public static Time topOfTheHour(long paramLong)
  {
    Time localTime = new Time();
    localTime.set(paramLong);
    localTime.second = 0;
    localTime.minute = 0;
    return localTime;
  }

  private static enum DateType
  {
    static
    {
      MONTH_DAY = new DateType("MONTH_DAY", 1);
      MONTH = new DateType("MONTH", 2);
      DateType[] arrayOfDateType = new DateType[3];
      arrayOfDateType[0] = WEEK_DAY;
      arrayOfDateType[1] = MONTH_DAY;
      arrayOfDateType[2] = MONTH;
      $VALUES = arrayOfDateType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.schedule.DateUtil
 * JD-Core Version:    0.6.0
 */