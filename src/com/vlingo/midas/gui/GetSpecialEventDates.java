package com.vlingo.midas.gui;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Data;

public class GetSpecialEventDates
{
  Context mContext;

  GetSpecialEventDates(Context paramContext)
  {
    this.mContext = paramContext;
  }

  private boolean hasBirthdays(String paramString)
  {
    ContentResolver localContentResolver = this.mContext.getContentResolver();
    Uri localUri = ContactsContract.Data.CONTENT_URI;
    String[] arrayOfString1 = new String[1];
    arrayOfString1[0] = "display_name";
    String[] arrayOfString2 = new String[1];
    arrayOfString2[0] = ("%" + paramString);
    Cursor localCursor = localContentResolver.query(localUri, arrayOfString1, "data1 LIKE ? ", arrayOfString2, null);
    if (localCursor.getCount() > 0)
      localCursor.close();
    for (int i = 1; ; i = 0)
    {
      return i;
      if (localCursor == null)
        continue;
      localCursor.close();
    }
  }

  private boolean isChristmas(String paramString)
  {
    if (paramString.equalsIgnoreCase("12-25"));
    for (int i = 1; ; i = 0)
      return i;
  }

  private boolean isNewYear(String paramString)
  {
    if (paramString.equalsIgnoreCase("01-01"));
    for (int i = 1; ; i = 0)
      return i;
  }

  public int checkSpecialDay(String paramString)
  {
    String str = paramString.substring(5, 10);
    int i;
    if (isNewYear(str))
      i = 1;
    while (true)
    {
      return i;
      if (isChristmas(str))
      {
        i = 2;
        continue;
      }
      if (hasBirthdays(str))
      {
        i = 3;
        continue;
      }
      i = 0;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.GetSpecialEventDates
 * JD-Core Version:    0.6.0
 */