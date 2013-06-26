package com.vlingo.sdk.internal.http.date;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class HttpDateParser
{
  private static final String[] months;

  static
  {
    String[] arrayOfString = new String[12];
    arrayOfString[0] = "Jan";
    arrayOfString[1] = "Feb";
    arrayOfString[2] = "Mar";
    arrayOfString[3] = "Apr";
    arrayOfString[4] = "May";
    arrayOfString[5] = "Jun";
    arrayOfString[6] = "Jul";
    arrayOfString[7] = "Aug";
    arrayOfString[8] = "Sep";
    arrayOfString[9] = "Oct";
    arrayOfString[10] = "Nov";
    arrayOfString[11] = "Dec";
    months = arrayOfString;
  }

  public static long parse(String paramString)
  {
    long l;
    if ((paramString == null) || (paramString.length() < 26))
    {
      l = 0L;
      return l;
    }
    String str1 = paramString.substring(5, 7);
    String str2 = paramString.substring(8, 11);
    String str3 = paramString.substring(12, 16);
    String str4 = paramString.substring(17, 19);
    String str5 = paramString.substring(20, 22);
    String str6 = paramString.substring(23, 25);
    Calendar localCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    while (true)
    {
      int j;
      try
      {
        localCalendar.set(5, Integer.parseInt(str1));
        localCalendar.set(1, Integer.parseInt(str3));
        localCalendar.set(11, Integer.parseInt(str4));
        localCalendar.set(12, Integer.parseInt(str5));
        localCalendar.set(13, Integer.parseInt(str6));
        int i = -1;
        j = 0;
        if (j >= months.length)
          continue;
        if (!months[j].equalsIgnoreCase(str2))
          break label252;
        i = j;
        switch (i)
        {
        default:
          l = 0L;
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
        case 10:
        case 11:
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        l = 0L;
      }
      break;
      label252: j++;
    }
    int k = 0;
    while (true)
    {
      localCalendar.set(2, k);
      l = localCalendar.getTime().getTime();
      break;
      k = 1;
      continue;
      k = 2;
      continue;
      k = 3;
      continue;
      k = 4;
      continue;
      k = 5;
      continue;
      k = 6;
      continue;
      k = 7;
      continue;
      k = 8;
      continue;
      k = 9;
      continue;
      k = 10;
      continue;
      k = 11;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.date.HttpDateParser
 * JD-Core Version:    0.6.0
 */