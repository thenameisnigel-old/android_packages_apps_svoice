package com.sec.android.internal.ims.external;

import android.net.Uri;
import android.provider.BaseColumns;

public class ErrorTableDetails
{
  public static final String AUTHORITY = "com.example.ErrorTableProvider";

  public static final class User
    implements BaseColumns
  {
    public static final Uri CONTENT_URI = Uri.parse("content://com.example.ErrorTableProvider");
    public static final String DATABASE_NAME_ERROR_TABLE = "VTErrorTableContent1.db";
    public static final Uri DB_ERROR_TABLE_DEL_URI;
    public static final Uri DB_ERROR_TABLE_TABLE_URI = Uri.parse("content://com.example.ErrorTableProvider/VTErrorTable");
    public static final String ErrorCode = "ErrorCode";
    public static final String ErrorString = "ErrorString";
    public static final String TABLE_NAME_ERROR_TABLE = "VTErrorTable";
    public static final String Timestamp = "Timestamp";

    static
    {
      DB_ERROR_TABLE_DEL_URI = Uri.parse("content://com.example.ErrorTableProvider/VTErrorTable");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.internal.ims.external.ErrorTableDetails
 * JD-Core Version:    0.6.0
 */