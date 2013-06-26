package com.vlingo.core.internal.contacts.mru;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.vlingo.core.internal.util.ApplicationAdapter;

public class MRUDatabaseStore
  implements MRUStore
{
  private String[] m_applicationNames;
  private String[] m_columns;
  private int m_mruMaxSize;

  private MRUTable loadMRUTable(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
    Cursor localCursor = paramSQLiteDatabase.query(paramString, this.m_columns, null, null, null, null, null);
    int i = this.m_mruMaxSize;
    MRUTable localMRUTable = new MRUTable(paramString, i);
    int j;
    if (localCursor != null)
    {
      if (!localCursor.moveToFirst())
        break label189;
      j = localCursor.getColumnIndex("contactID");
      if (j >= 0)
        break label77;
      localCursor.close();
      paramSQLiteDatabase.close();
      localMRUTable = null;
    }
    while (true)
    {
      return localMRUTable;
      label77: int k = localCursor.getColumnIndex("address");
      if (k < 0)
      {
        localCursor.close();
        paramSQLiteDatabase.close();
        localMRUTable = null;
        continue;
      }
      int m = localCursor.getColumnIndex("count");
      if (m < 0)
      {
        localCursor.close();
        paramSQLiteDatabase.close();
        localMRUTable = null;
        continue;
      }
      do
      {
        float f = localCursor.getFloat(m);
        String str = localCursor.getString(k);
        int n = localCursor.getInt(j);
        localMRUTable.setCount(n, str, f);
      }
      while (localCursor.moveToNext());
      label189: localCursor.close();
    }
  }

  private SQLiteDatabase openDb()
  {
    SQLiteDatabase localSQLiteDatabase = null;
    try
    {
      Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
      if (localContext != null)
      {
        localSQLiteDatabase = localContext.openOrCreateDatabase("MRU", 0, null);
        for (String str : this.m_applicationNames)
          localSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + str + " (contactID INT, address VARCHAR, count REAL)");
      }
    }
    catch (SQLiteException localSQLiteException)
    {
    }
    return localSQLiteDatabase;
  }

  public void init(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt)
  {
    this.m_applicationNames = paramArrayOfString1;
    this.m_columns = paramArrayOfString2;
    this.m_mruMaxSize = paramInt;
  }

  public MRUTable[] loadMRUTables()
  {
    MRUTable[] arrayOfMRUTable = null;
    SQLiteDatabase localSQLiteDatabase = openDb();
    if (localSQLiteDatabase != null)
    {
      arrayOfMRUTable = new MRUTable[this.m_applicationNames.length];
      for (int i = 0; i < this.m_applicationNames.length; i++)
        arrayOfMRUTable[i] = loadMRUTable(localSQLiteDatabase, this.m_applicationNames[i]);
      localSQLiteDatabase.close();
    }
    return arrayOfMRUTable;
  }

  public void removeEntry(String paramString, int paramInt)
  {
    SQLiteDatabase localSQLiteDatabase = openDb();
    if (localSQLiteDatabase != null)
    {
      localSQLiteDatabase.execSQL("DELETE FROM " + paramString + " WHERE contactID = " + paramInt + ";");
      localSQLiteDatabase.close();
    }
  }

  public boolean rescaleAllCounts(String paramString, float paramFloat)
  {
    SQLiteDatabase localSQLiteDatabase = openDb();
    if (localSQLiteDatabase == null);
    for (int i = 0; ; i = 1)
    {
      return i;
      localSQLiteDatabase.execSQL("UPDATE " + paramString + " SET count = count * " + paramFloat + ";");
      localSQLiteDatabase.close();
    }
  }

  public void setEntryCount(String paramString1, int paramInt, String paramString2, float paramFloat)
  {
    SQLiteDatabase localSQLiteDatabase = openDb();
    if (localSQLiteDatabase != null)
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("count", Float.valueOf(paramFloat));
      String str = paramString2.replaceAll("'", "''");
      if (localSQLiteDatabase.update(paramString1, localContentValues, "contactID=" + paramInt + " AND address='" + str + "'", null) <= 0)
        localSQLiteDatabase.execSQL(" INSERT INTO " + paramString1 + " (contactID, address, count) " + " VALUES (" + paramInt + ", " + "'" + str + "', " + paramFloat + ");");
      localSQLiteDatabase.close();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.contacts.mru.MRUDatabaseStore
 * JD-Core Version:    0.6.0
 */