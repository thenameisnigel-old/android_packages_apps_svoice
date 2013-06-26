package com.sec.android.internal.ims.external;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class ErrorTableDataOperation
{
  public int deleteExistingRow(ContentResolver paramContentResolver)
  {
    int i = 0;
    if (getRowCount(paramContentResolver) > 0)
      i = paramContentResolver.delete(ErrorTableDetails.User.DB_ERROR_TABLE_DEL_URI, null, null);
    return i;
  }

  public ErrorTableData getAllData(ContentResolver paramContentResolver)
  {
    log("Get All Data _________");
    ErrorTableData localErrorTableData = null;
    Cursor localCursor = paramContentResolver.query(ErrorTableDetails.User.DB_ERROR_TABLE_TABLE_URI, null, null, null, null);
    if ((localCursor != null) && (localCursor.getCount() > 0))
    {
      localErrorTableData = new ErrorTableData();
      localCursor.moveToLast();
      localErrorTableData.mTimestamp = localCursor.getString(localCursor.getColumnIndex("Timestamp"));
      localErrorTableData.mErrorCode = localCursor.getString(localCursor.getColumnIndex("ErrorCode"));
      localErrorTableData.mErrorString = localCursor.getString(localCursor.getColumnIndex("ErrorString"));
    }
    if (localCursor != null)
      localCursor.close();
    return localErrorTableData;
  }

  public int getRowCount(ContentResolver paramContentResolver)
  {
    int i = 0;
    Cursor localCursor = paramContentResolver.query(ErrorTableDetails.User.DB_ERROR_TABLE_TABLE_URI, null, null, null, null);
    if (localCursor != null)
    {
      i = localCursor.getCount();
      localCursor.close();
    }
    return i;
  }

  public Uri insertDataIntoDB(ErrorTableData paramErrorTableData, ContentResolver paramContentResolver)
  {
    Uri localUri = null;
    if ((paramErrorTableData != null) && (paramContentResolver != null))
    {
      int i = 0;
      ContentValues localContentValues = new ContentValues();
      ErrorTableData[] arrayOfErrorTableData = new ErrorTableData[5];
      Cursor localCursor = paramContentResolver.query(ErrorTableDetails.User.DB_ERROR_TABLE_TABLE_URI, null, null, null, null);
      log("insertDataIntoDB __________");
      if (localCursor != null)
      {
        int j = localCursor.getCount();
        log("count db exist = " + j);
        if (j > 0)
        {
          log("There are already existing records r = " + j);
          localCursor.moveToFirst();
          int k = 0;
          while (k < j)
          {
            log("getting records record NO :" + k);
            arrayOfErrorTableData[i] = new ErrorTableData();
            int i1 = localCursor.getColumnIndex("Timestamp");
            arrayOfErrorTableData[i].mTimestamp = localCursor.getString(i1);
            log(" time stamp = " + arrayOfErrorTableData[i].mTimestamp);
            int i2 = localCursor.getColumnIndex("ErrorCode");
            arrayOfErrorTableData[i].mErrorCode = localCursor.getString(i2);
            int i3 = localCursor.getColumnIndex("ErrorString");
            arrayOfErrorTableData[i].mErrorString = localCursor.getString(i3);
            log("insertDataIntoDB __________");
            i++;
            k++;
            localCursor.moveToNext();
          }
          deleteExistingRow(paramContentResolver);
          log("Writing New Value to DB_____TimeStamp = " + paramErrorTableData.mTimestamp);
          localContentValues.put("Timestamp".toString(), paramErrorTableData.mTimestamp);
          log(paramErrorTableData.mTimestamp);
          localContentValues.put("ErrorCode".toString(), paramErrorTableData.mErrorCode);
          log(paramErrorTableData.mErrorCode);
          localContentValues.put("ErrorString".toString(), paramErrorTableData.mErrorString);
          log(paramErrorTableData.mErrorString);
          localUri = paramContentResolver.insert(ErrorTableDetails.User.DB_ERROR_TABLE_TABLE_URI, localContentValues);
          int m = 1;
          log("Writing New Value - first value inserted -" + paramErrorTableData.mTimestamp);
          int n = 0;
          while ((n < j) && (m < 5))
          {
            log("Writing Next Values -" + arrayOfErrorTableData[n].mTimestamp);
            localContentValues.put("Timestamp".toString(), arrayOfErrorTableData[n].mTimestamp);
            localContentValues.put("ErrorCode".toString(), arrayOfErrorTableData[n].mErrorCode);
            localContentValues.put("ErrorString".toString(), arrayOfErrorTableData[n].mErrorString);
            localUri = paramContentResolver.insert(ErrorTableDetails.User.DB_ERROR_TABLE_TABLE_URI, localContentValues);
            log(" Inserted values to DB time stamp = " + arrayOfErrorTableData[n].mTimestamp);
            n++;
            m++;
          }
        }
        log("Writing New Value to DB_____TimeStamp = " + paramErrorTableData.mTimestamp);
        localContentValues.put("Timestamp".toString(), paramErrorTableData.mTimestamp);
        log(paramErrorTableData.mTimestamp);
        localContentValues.put("ErrorCode".toString(), paramErrorTableData.mErrorCode);
        log(paramErrorTableData.mErrorCode);
        localContentValues.put("ErrorString".toString(), paramErrorTableData.mErrorString);
        log(paramErrorTableData.mErrorString);
        localUri = paramContentResolver.insert(ErrorTableDetails.User.DB_ERROR_TABLE_TABLE_URI, localContentValues);
        log("insertDataIntoDB __");
        localCursor.close();
      }
    }
    return localUri;
  }

  void log(String paramString)
  {
    Log.d("ErrorTableDataOperation", paramString);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.internal.ims.external.ErrorTableDataOperation
 * JD-Core Version:    0.6.0
 */