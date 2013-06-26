package com.vlingo.core.internal.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog.Calls;
import java.util.ArrayList;

public class CallLogUtil
{
  public static ArrayList<LoggedCall> getLastNMissedCalls(Context paramContext, int paramInt)
  {
    ArrayList localArrayList = new ArrayList(paramInt);
    String[] arrayOfString1 = new String[4];
    arrayOfString1[0] = "name";
    arrayOfString1[1] = "number";
    arrayOfString1[2] = "date";
    arrayOfString1[3] = "new";
    try
    {
      ContentResolver localContentResolver = paramContext.getContentResolver();
      Uri localUri = CallLog.Calls.CONTENT_URI;
      String[] arrayOfString2 = new String[2];
      arrayOfString2[0] = Integer.toString(3);
      arrayOfString2[1] = "1";
      Cursor localCursor = localContentResolver.query(localUri, arrayOfString1, "type = ? AND new = ?", arrayOfString2, "date DESC ");
      if ((CursorUtil.isValid(localCursor)) && (localCursor.moveToFirst()))
      {
        int i = localCursor.getColumnIndex("name");
        int j = localCursor.getColumnIndex("date");
        int k = localCursor.getColumnIndex("number");
        do
        {
          String str = localCursor.getString(i);
          long l = localCursor.getLong(j);
          if (StringUtils.isNullOrWhiteSpace(str))
            str = localCursor.getString(k);
          localArrayList.add(LoggedCall.newInstance(str, l));
        }
        while ((localCursor.moveToNext()) && (paramInt < 0));
      }
      localCursor.close();
      return localArrayList;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.CallLogUtil
 * JD-Core Version:    0.6.0
 */