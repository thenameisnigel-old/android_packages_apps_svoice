package com.sec.android.internal.ims.external;

import android.content.ContentResolver;

public class ErrorTableData
{
  public static final String DefaultmErrorCode = "Error 2";
  public static final String DefaultmErrorString = "Error 3";
  public static final String DefaultmTimestamp = "Error 1";
  public String mErrorCode;
  public String mErrorString;
  public String mTimestamp;

  public ErrorTableData()
  {
    settoDefaultValues();
  }

  public void fillErrorData(ContentResolver paramContentResolver)
  {
    ErrorTableData localErrorTableData = new ErrorTableData();
    ErrorTableDataOperation localErrorTableDataOperation = new ErrorTableDataOperation();
    localErrorTableData.mErrorCode = "201";
    localErrorTableData.mTimestamp = "10:45";
    localErrorTableData.mErrorString = "Always On Off";
    localErrorTableDataOperation.insertDataIntoDB(localErrorTableData, paramContentResolver);
    localErrorTableData.mErrorCode = "202";
    localErrorTableData.mTimestamp = "10:45";
    localErrorTableData.mErrorString = "Register failure resp.202";
    localErrorTableDataOperation.insertDataIntoDB(localErrorTableData, paramContentResolver);
    localErrorTableData.mErrorCode = "205";
    localErrorTableData.mTimestamp = "10:45";
    localErrorTableData.mErrorString = "EVDO not available while registering";
    localErrorTableDataOperation.insertDataIntoDB(localErrorTableData, paramContentResolver);
    localErrorTableData.mErrorCode = "301";
    localErrorTableData.mTimestamp = "10:45";
    localErrorTableData.mErrorString = "Media RTP timer timeout";
    localErrorTableDataOperation.insertDataIntoDB(localErrorTableData, paramContentResolver);
    localErrorTableData.mErrorCode = "304";
    localErrorTableData.mTimestamp = "10:45";
    localErrorTableData.mErrorString = "User Cancelled call";
    localErrorTableDataOperation.insertDataIntoDB(localErrorTableData, paramContentResolver);
  }

  public void settoDefaultValues()
  {
    this.mTimestamp = "Error 1";
    this.mErrorCode = "Error 2";
    this.mErrorString = "Error 3";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.internal.ims.external.ErrorTableData
 * JD-Core Version:    0.6.0
 */