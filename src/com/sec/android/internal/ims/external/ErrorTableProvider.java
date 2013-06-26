package com.sec.android.internal.ims.external;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.net.Uri.Builder;

public class ErrorTableProvider extends ContentProvider
{
  private static final String DATABASE_NAME = "VTErrorTableContent1.db";
  private static final int DATABASE_VERSION = 1;
  private static final String TABLE_NAME = "VTErrorTable";
  private static final String TAG = "MyContentProvider";
  private static DatabaseHelper dbHelper;
  private static SQLiteDatabase sqlDB;

  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    return sqlDB.delete("VTErrorTable", paramString, paramArrayOfString);
  }

  public String getType(Uri paramUri)
  {
    return null;
  }

  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    if (paramContentValues != null);
    for (ContentValues localContentValues = new ContentValues(paramContentValues); ; localContentValues = new ContentValues())
    {
      long l = sqlDB.insert("VTErrorTable", null, localContentValues);
      if (l <= 0L)
        break;
      Uri localUri = ContentUris.appendId(MenuDetails.User.CONTENT_URI.buildUpon(), l).build();
      getContext().getContentResolver().notifyChange(localUri, null);
      return localUri;
    }
    throw new SQLException("Failed to insert row into " + paramUri);
  }

  public boolean onCreate()
  {
    dbHelper = new DatabaseHelper(getContext());
    sqlDB = dbHelper.getWritableDatabase();
    if (dbHelper == null);
    for (int i = 0; ; i = 1)
      return i;
  }

  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    SQLiteQueryBuilder localSQLiteQueryBuilder = new SQLiteQueryBuilder();
    SQLiteDatabase localSQLiteDatabase = dbHelper.getReadableDatabase();
    localSQLiteQueryBuilder.setTables("VTErrorTable");
    Cursor localCursor = localSQLiteQueryBuilder.query(localSQLiteDatabase, paramArrayOfString1, paramString1, null, null, null, paramString2);
    localCursor.setNotificationUri(getContext().getContentResolver(), paramUri);
    return localCursor;
  }

  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }

  private static class DatabaseHelper extends SQLiteOpenHelper
  {
    DatabaseHelper(Context paramContext)
    {
      super("VTErrorTableContent1.db", null, 1);
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("Create table VTErrorTable( _id INTEGER PRIMARY KEY AUTOINCREMENT, Timestamp TEXT,ErrorCode TEXT,ErrorString TEXT);");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS VTErrorTable");
      onCreate(paramSQLiteDatabase);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.internal.ims.external.ErrorTableProvider
 * JD-Core Version:    0.6.0
 */