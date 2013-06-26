package com.vlingo.core.internal.lmtt;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore.Audio.Genres;
import android.util.Log;
import com.vlingo.core.internal.util.ApplicationAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class LMTTDBUtil
{
  public static final String COLUMN_HASHCODE = "HASHCODE";
  public static final String COLUMN_ID = "ID";
  public static final String COLUMN_TYPE = "TYPE";
  public static final String LMTT_TABLE = "LMTT";
  public static final String WHERE_ID = "ID=?";
  public static final String WHERE_TYPE = "TYPE=?";
  public static final String WHERE_TYPE_ID = "TYPE=? AND ID=?";

  // ERROR //
  public static void clearLMTTTable(LMTTItem.LmttItemType paramLmttItemType)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: iconst_1
    //   3: anewarray 36	java/lang/String
    //   6: astore_2
    //   7: aload_2
    //   8: iconst_0
    //   9: aload_0
    //   10: invokevirtual 42	com/vlingo/core/internal/lmtt/LMTTItem$LmttItemType:ordinal	()I
    //   13: invokestatic 46	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   16: aastore
    //   17: invokestatic 50	com/vlingo/core/internal/lmtt/LMTTDBUtil:openDB	()Landroid/database/sqlite/SQLiteDatabase;
    //   20: astore_1
    //   21: aload_1
    //   22: ifnull +13 -> 35
    //   25: aload_1
    //   26: ldc 17
    //   28: ldc 23
    //   30: aload_2
    //   31: invokevirtual 56	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   34: pop
    //   35: aload_1
    //   36: ifnull +7 -> 43
    //   39: aload_1
    //   40: invokevirtual 59	android/database/sqlite/SQLiteDatabase:close	()V
    //   43: return
    //   44: astore 9
    //   46: ldc 61
    //   48: aload 9
    //   50: invokestatic 67	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   53: invokestatic 71	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   56: pop
    //   57: goto -14 -> 43
    //   60: astore 6
    //   62: aload_1
    //   63: ifnull -20 -> 43
    //   66: aload_1
    //   67: invokevirtual 59	android/database/sqlite/SQLiteDatabase:close	()V
    //   70: goto -27 -> 43
    //   73: astore 7
    //   75: ldc 61
    //   77: aload 7
    //   79: invokestatic 67	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   82: invokestatic 71	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   85: pop
    //   86: goto -43 -> 43
    //   89: astore_3
    //   90: aload_1
    //   91: ifnull +7 -> 98
    //   94: aload_1
    //   95: invokevirtual 59	android/database/sqlite/SQLiteDatabase:close	()V
    //   98: aload_3
    //   99: athrow
    //   100: astore 4
    //   102: ldc 61
    //   104: aload 4
    //   106: invokestatic 67	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   109: invokestatic 71	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   112: pop
    //   113: goto -15 -> 98
    //
    // Exception table:
    //   from	to	target	type
    //   39	43	44	java/lang/Exception
    //   17	35	60	java/lang/Exception
    //   66	70	73	java/lang/Exception
    //   17	35	89	finally
    //   94	98	100	java/lang/Exception
  }

  public static void deleteItem(LMTTItem paramLMTTItem, SQLiteDatabase paramSQLiteDatabase)
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = String.valueOf(paramLMTTItem.type.ordinal());
    arrayOfString[1] = String.valueOf(paramLMTTItem.uid);
    if (paramSQLiteDatabase.delete("LMTT", "TYPE=? AND ID=?", arrayOfString) <= 0);
  }

  public static String getSongGenres(ContentResolver paramContentResolver, int paramInt)
  {
    Uri localUri1 = MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI;
    HashMap localHashMap = new HashMap();
    String[] arrayOfString1 = new String[2];
    arrayOfString1[0] = "_id";
    arrayOfString1[1] = "name";
    Cursor localCursor1 = paramContentResolver.query(localUri1, arrayOfString1, null, null, null);
    localCursor1.moveToFirst();
    while (!localCursor1.isAfterLast())
    {
      localHashMap.put(localCursor1.getString(0), localCursor1.getString(1));
      localCursor1.moveToNext();
    }
    localCursor1.close();
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = localHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      Uri localUri2 = Uri.parse(localUri1.toString() + "/" + str2 + "/" + "members");
      String[] arrayOfString2 = new String[1];
      arrayOfString2[0] = "_id";
      String str3 = "_id" + "=?";
      String[] arrayOfString3 = new String[1];
      arrayOfString3[0] = String.valueOf(paramInt);
      Cursor localCursor2 = paramContentResolver.query(localUri2, arrayOfString2, str3, arrayOfString3, null);
      if (localCursor2.getCount() != 0)
        localStringBuilder.append((String)localHashMap.get(str2)).append(',');
      localCursor2.close();
    }
    if (localStringBuilder.length() > 0)
      localStringBuilder.setLength(-1 + localStringBuilder.length());
    for (String str1 = localStringBuilder.toString(); ; str1 = null)
      return str1;
  }

  // ERROR //
  public static HashMap<Integer, Integer> getSynchedItems(LMTTItem.LmttItemType paramLmttItemType)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aconst_null
    //   3: astore_2
    //   4: aconst_null
    //   5: astore_3
    //   6: invokestatic 50	com/vlingo/core/internal/lmtt/LMTTDBUtil:openDB	()Landroid/database/sqlite/SQLiteDatabase;
    //   9: astore_1
    //   10: iconst_1
    //   11: anewarray 36	java/lang/String
    //   14: astore 14
    //   16: aload 14
    //   18: iconst_0
    //   19: aload_0
    //   20: invokevirtual 42	com/vlingo/core/internal/lmtt/LMTTItem$LmttItemType:ordinal	()I
    //   23: invokestatic 46	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   26: aastore
    //   27: aload_1
    //   28: ifnull +153 -> 181
    //   31: iconst_2
    //   32: anewarray 36	java/lang/String
    //   35: astore 19
    //   37: aload 19
    //   39: iconst_0
    //   40: ldc 11
    //   42: aastore
    //   43: aload 19
    //   45: iconst_1
    //   46: ldc 8
    //   48: aastore
    //   49: aload_1
    //   50: ldc 17
    //   52: aload 19
    //   54: ldc 23
    //   56: aload 14
    //   58: aconst_null
    //   59: aconst_null
    //   60: aconst_null
    //   61: invokevirtual 189	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   64: astore_2
    //   65: aload_2
    //   66: ifnull +115 -> 181
    //   69: new 93	java/util/HashMap
    //   72: dup
    //   73: aload_2
    //   74: invokeinterface 170 1 0
    //   79: invokespecial 191	java/util/HashMap:<init>	(I)V
    //   82: astore 20
    //   84: aload_2
    //   85: ldc 11
    //   87: invokeinterface 195 2 0
    //   92: istore 22
    //   94: aload_2
    //   95: ldc 8
    //   97: invokeinterface 195 2 0
    //   102: istore 23
    //   104: iconst_0
    //   105: istore 24
    //   107: aload_2
    //   108: invokeinterface 110 1 0
    //   113: ifeq +65 -> 178
    //   116: aload 20
    //   118: aload_2
    //   119: iload 22
    //   121: invokeinterface 199 2 0
    //   126: invokestatic 204	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   129: aload_2
    //   130: iload 23
    //   132: invokeinterface 199 2 0
    //   137: invokestatic 204	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   140: invokevirtual 120	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   143: pop
    //   144: iinc 24 1
    //   147: iload 24
    //   149: bipush 10
    //   151: irem
    //   152: istore 26
    //   154: iload 26
    //   156: ifne +9 -> 165
    //   159: ldc2_w 205
    //   162: invokestatic 212	java/lang/Thread:sleep	(J)V
    //   165: aload_2
    //   166: invokeinterface 123 1 0
    //   171: istore 27
    //   173: iload 27
    //   175: ifne -59 -> 116
    //   178: aload 20
    //   180: astore_3
    //   181: aload_2
    //   182: ifnull +9 -> 191
    //   185: aload_2
    //   186: invokeinterface 124 1 0
    //   191: aload_1
    //   192: ifnull +7 -> 199
    //   195: aload_1
    //   196: invokevirtual 59	android/database/sqlite/SQLiteDatabase:close	()V
    //   199: aload_3
    //   200: areturn
    //   201: astore 28
    //   203: ldc 61
    //   205: aload 28
    //   207: invokestatic 67	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   210: invokestatic 71	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   213: pop
    //   214: goto -49 -> 165
    //   217: astore 21
    //   219: aload 20
    //   221: astore_3
    //   222: aload_2
    //   223: ifnull +9 -> 232
    //   226: aload_2
    //   227: invokeinterface 124 1 0
    //   232: aload_1
    //   233: ifnull -34 -> 199
    //   236: aload_1
    //   237: invokevirtual 59	android/database/sqlite/SQLiteDatabase:close	()V
    //   240: goto -41 -> 199
    //   243: astore 5
    //   245: ldc 61
    //   247: aload 5
    //   249: invokestatic 67	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   252: invokestatic 71	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   255: pop
    //   256: goto -57 -> 199
    //   259: astore 17
    //   261: ldc 61
    //   263: aload 17
    //   265: invokestatic 67	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   268: invokestatic 71	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   271: pop
    //   272: goto -81 -> 191
    //   275: astore 15
    //   277: ldc 61
    //   279: aload 15
    //   281: invokestatic 67	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   284: invokestatic 71	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   287: pop
    //   288: goto -89 -> 199
    //   291: astore 7
    //   293: ldc 61
    //   295: aload 7
    //   297: invokestatic 67	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   300: invokestatic 71	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   303: pop
    //   304: goto -72 -> 232
    //   307: astore 9
    //   309: aload_2
    //   310: ifnull +9 -> 319
    //   313: aload_2
    //   314: invokeinterface 124 1 0
    //   319: aload_1
    //   320: ifnull +7 -> 327
    //   323: aload_1
    //   324: invokevirtual 59	android/database/sqlite/SQLiteDatabase:close	()V
    //   327: aload 9
    //   329: athrow
    //   330: astore 12
    //   332: ldc 61
    //   334: aload 12
    //   336: invokestatic 67	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   339: invokestatic 71	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   342: pop
    //   343: goto -24 -> 319
    //   346: astore 10
    //   348: ldc 61
    //   350: aload 10
    //   352: invokestatic 67	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   355: invokestatic 71	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   358: pop
    //   359: goto -32 -> 327
    //   362: astore 9
    //   364: goto -55 -> 309
    //   367: astore 4
    //   369: goto -147 -> 222
    //
    // Exception table:
    //   from	to	target	type
    //   159	165	201	java/lang/Exception
    //   84	154	217	java/lang/Exception
    //   165	173	217	java/lang/Exception
    //   203	214	217	java/lang/Exception
    //   236	240	243	java/lang/Exception
    //   185	191	259	java/lang/Exception
    //   195	199	275	java/lang/Exception
    //   226	232	291	java/lang/Exception
    //   6	84	307	finally
    //   313	319	330	java/lang/Exception
    //   323	327	346	java/lang/Exception
    //   84	154	362	finally
    //   159	165	362	finally
    //   165	173	362	finally
    //   203	214	362	finally
    //   6	84	367	java/lang/Exception
  }

  public static void insertItem(LMTTItem paramLMTTItem, SQLiteDatabase paramSQLiteDatabase)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("TYPE", String.valueOf(paramLMTTItem.type.ordinal()));
    localContentValues.put("ID", Integer.valueOf(paramLMTTItem.uid));
    localContentValues.put("HASHCODE", Integer.valueOf(paramLMTTItem.hashCode()));
    if (paramSQLiteDatabase.insert("LMTT", null, localContentValues) == -1L);
  }

  private static SQLiteDatabase openDB()
  {
    SQLiteDatabase localSQLiteDatabase = null;
    try
    {
      Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
      if (localContext != null)
      {
        localSQLiteDatabase = localContext.openOrCreateDatabase("LMTTv2", 0, null);
        localSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS LMTT (TYPE INT, ID INT, HASHCODE INT)");
      }
      label28: return localSQLiteDatabase;
    }
    catch (Exception localException)
    {
      break label28;
    }
  }

  public static void updateItem(LMTTItem paramLMTTItem, SQLiteDatabase paramSQLiteDatabase)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("HASHCODE", Integer.valueOf(paramLMTTItem.hashCode()));
    String[] arrayOfString = new String[2];
    arrayOfString[0] = String.valueOf(paramLMTTItem.type.ordinal());
    arrayOfString[1] = String.valueOf(paramLMTTItem.uid);
    if (paramSQLiteDatabase.update("LMTT", localContentValues, "TYPE=? AND ID=?", arrayOfString) <= 0);
  }

  public static boolean updateSynchedItems(ArrayList<LMTTItem> paramArrayList)
  {
    SQLiteDatabase localSQLiteDatabase = null;
    try
    {
      localSQLiteDatabase = openDB();
      if (localSQLiteDatabase != null)
      {
        Iterator localIterator = paramArrayList.iterator();
        while (localIterator.hasNext())
        {
          LMTTItem localLMTTItem = (LMTTItem)localIterator.next();
          localLMTTItem.changeType.dbUpdate(localLMTTItem, localSQLiteDatabase);
          try
          {
            Thread.sleep(10L);
          }
          catch (Exception localException6)
          {
            Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException6));
          }
        }
      }
    }
    catch (Exception localException2)
    {
      if (localSQLiteDatabase != null);
      try
      {
        localSQLiteDatabase.close();
        while (true)
        {
          int i = 0;
          while (true)
          {
            return i;
            i = 1;
            if (localSQLiteDatabase == null)
              continue;
            try
            {
              localSQLiteDatabase.close();
            }
            catch (Exception localException5)
            {
              Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException5));
            }
          }
          if (localSQLiteDatabase == null)
            continue;
          try
          {
            localSQLiteDatabase.close();
          }
          catch (Exception localException4)
          {
            Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException4));
          }
        }
      }
      catch (Exception localException3)
      {
        while (true)
          Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException3));
      }
    }
    finally
    {
      if (localSQLiteDatabase == null);
    }
    try
    {
      localSQLiteDatabase.close();
      throw localObject;
    }
    catch (Exception localException1)
    {
      while (true)
        Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException1));
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.lmtt.LMTTDBUtil
 * JD-Core Version:    0.6.0
 */