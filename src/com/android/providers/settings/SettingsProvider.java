package com.android.providers.settings;

import android.app.backup.BackupManager;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileObserver;
import android.os.ParcelFileDescriptor;
import android.os.SystemProperties;
import android.provider.DrmStore;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SettingsProvider extends ContentProvider
{
  private static final String[] COLUMN_VALUE;
  private static final boolean LOCAL_LOGV = false;
  private static final int MAX_CACHE_ENTRIES = 200;
  private static final int MAX_CACHE_ENTRY_SIZE = 500;
  private static final Bundle NULL_SETTING;
  private static final String TABLE_FAVORITES = "favorites";
  private static final String TABLE_OLD_FAVORITES = "old_favorites";
  private static final String TAG = "SettingsProvider";
  private static final Bundle TOO_LARGE_TO_CACHE_MARKER;
  private static final AtomicInteger sKnownMutationsInFlight;
  private static SettingsFileObserver sObserverInstance;
  private static final SettingsCache sSecureCache;
  private static final SettingsCache sSystemCache;
  private BackupManager mBackupManager;
  protected DatabaseHelper mOpenHelper;

  static
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "value";
    COLUMN_VALUE = arrayOfString;
    sSystemCache = new SettingsCache("system");
    sSecureCache = new SettingsCache("secure");
    sKnownMutationsInFlight = new AtomicInteger(0);
    NULL_SETTING = Bundle.forPair("value", null);
    TOO_LARGE_TO_CACHE_MARKER = Bundle.forPair("_dummy", null);
  }

  private void checkWritePermissions(SqlArguments paramSqlArguments)
  {
    if (("secure".equals(paramSqlArguments.table)) && (getContext().checkCallingOrSelfPermission("android.permission.WRITE_SECURE_SETTINGS") != 0))
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = "android.permission.WRITE_SECURE_SETTINGS";
      throw new SecurityException(String.format("Permission denial: writing to secure settings requires %1$s", arrayOfObject));
    }
  }

  private boolean ensureAndroidIdIsSet()
  {
    Uri localUri1 = Settings.Secure.CONTENT_URI;
    String[] arrayOfString1 = new String[1];
    arrayOfString1[0] = "value";
    String[] arrayOfString2 = new String[1];
    arrayOfString2[0] = "android_id";
    Cursor localCursor = query(localUri1, arrayOfString1, "name=?", arrayOfString2, null);
    try
    {
      if (localCursor.moveToNext());
      for (String str1 = localCursor.getString(0); str1 == null; str1 = null)
      {
        String str2 = Long.toHexString(new SecureRandom().nextLong());
        Log.d("SettingsProvider", "Generated and saved new ANDROID_ID [" + str2 + "]");
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("name", "android_id");
        localContentValues.put("value", str2);
        Uri localUri2 = insert(Settings.Secure.CONTENT_URI, localContentValues);
        if (localUri2 != null)
          break;
        localCursor.close();
        return i;
      }
      localCursor.close();
      int i = 1;
    }
    finally
    {
      localCursor.close();
    }
  }

  // ERROR //
  private void fullyPopulateCache(String paramString, SettingsCache paramSettingsCache)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 214	com/android/providers/settings/SettingsProvider:mOpenHelper	Lcom/android/providers/settings/DatabaseHelper;
    //   4: invokevirtual 220	com/android/providers/settings/DatabaseHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore_3
    //   8: iconst_2
    //   9: anewarray 53	java/lang/String
    //   12: astore 4
    //   14: aload 4
    //   16: iconst_0
    //   17: ldc 199
    //   19: aastore
    //   20: aload 4
    //   22: iconst_1
    //   23: ldc 55
    //   25: aastore
    //   26: aload_3
    //   27: aload_1
    //   28: aload 4
    //   30: aconst_null
    //   31: aconst_null
    //   32: aconst_null
    //   33: aconst_null
    //   34: aconst_null
    //   35: ldc 222
    //   37: invokevirtual 227	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   40: astore 5
    //   42: aload_2
    //   43: monitorenter
    //   44: aload_2
    //   45: invokevirtual 230	com/android/providers/settings/SettingsProvider$SettingsCache:clear	()V
    //   48: aload_2
    //   49: iconst_1
    //   50: invokevirtual 234	com/android/providers/settings/SettingsProvider$SettingsCache:setFullyMatchesDisk	(Z)V
    //   53: iconst_0
    //   54: istore 8
    //   56: aload 5
    //   58: invokeinterface 156 1 0
    //   63: ifeq +48 -> 111
    //   66: iinc 8 1
    //   69: aload_2
    //   70: aload 5
    //   72: iconst_0
    //   73: invokeinterface 160 2 0
    //   78: aload 5
    //   80: iconst_1
    //   81: invokeinterface 160 2 0
    //   86: invokevirtual 237	com/android/providers/settings/SettingsProvider$SettingsCache:populate	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: goto -33 -> 56
    //   92: astore 7
    //   94: aload_2
    //   95: monitorexit
    //   96: aload 7
    //   98: athrow
    //   99: astore 6
    //   101: aload 5
    //   103: invokeinterface 210 1 0
    //   108: aload 6
    //   110: athrow
    //   111: iload 8
    //   113: sipush 200
    //   116: if_icmple +33 -> 149
    //   119: aload_2
    //   120: iconst_0
    //   121: invokevirtual 234	com/android/providers/settings/SettingsProvider$SettingsCache:setFullyMatchesDisk	(Z)V
    //   124: ldc 37
    //   126: new 175	java/lang/StringBuilder
    //   129: dup
    //   130: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   133: ldc 239
    //   135: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: aload_1
    //   139: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: invokestatic 194	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   148: pop
    //   149: ldc 37
    //   151: new 175	java/lang/StringBuilder
    //   154: dup
    //   155: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   158: ldc 241
    //   160: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: aload_1
    //   164: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: ldc 243
    //   169: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: iload 8
    //   174: invokevirtual 246	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   177: ldc 248
    //   179: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: aload_2
    //   183: invokevirtual 251	com/android/providers/settings/SettingsProvider$SettingsCache:fullyMatchesDisk	()Z
    //   186: invokevirtual 254	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   189: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   192: invokestatic 194	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   195: pop
    //   196: aload_2
    //   197: monitorexit
    //   198: aload 5
    //   200: invokeinterface 210 1 0
    //   205: return
    //
    // Exception table:
    //   from	to	target	type
    //   44	96	92	finally
    //   119	198	92	finally
    //   42	44	99	finally
    //   96	99	99	finally
  }

  private void fullyPopulateCaches()
  {
    fullyPopulateCache("secure", sSecureCache);
    fullyPopulateCache("system", sSystemCache);
  }

  private Uri getUriFor(Uri paramUri, ContentValues paramContentValues, long paramLong)
  {
    if (paramUri.getPathSegments().size() != 1)
      throw new IllegalArgumentException("Invalid URI: " + paramUri);
    String str = (String)paramUri.getPathSegments().get(0);
    if (("system".equals(str)) || ("secure".equals(str)));
    for (Uri localUri = Uri.withAppendedPath(paramUri, paramContentValues.getAsString("name")); ; localUri = ContentUris.withAppendedId(paramUri, paramLong))
      return localUri;
  }

  // ERROR //
  private Bundle lookupValue(String paramString1, SettingsCache paramSettingsCache, String paramString2)
  {
    // Byte code:
    //   0: aload_2
    //   1: monitorenter
    //   2: aload_2
    //   3: aload_3
    //   4: invokevirtual 303	com/android/providers/settings/SettingsProvider$SettingsCache:containsKey	(Ljava/lang/Object;)Z
    //   7: ifeq +26 -> 33
    //   10: aload_2
    //   11: aload_3
    //   12: invokevirtual 306	com/android/providers/settings/SettingsProvider$SettingsCache:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   15: checkcast 78	android/os/Bundle
    //   18: astore 10
    //   20: aload 10
    //   22: getstatic 88	com/android/providers/settings/SettingsProvider:TOO_LARGE_TO_CACHE_MARKER	Landroid/os/Bundle;
    //   25: if_acmpeq +32 -> 57
    //   28: aload_2
    //   29: monitorexit
    //   30: goto +208 -> 238
    //   33: aload_2
    //   34: invokevirtual 251	com/android/providers/settings/SettingsProvider$SettingsCache:fullyMatchesDisk	()Z
    //   37: ifeq +20 -> 57
    //   40: getstatic 84	com/android/providers/settings/SettingsProvider:NULL_SETTING	Landroid/os/Bundle;
    //   43: astore 10
    //   45: aload_2
    //   46: monitorexit
    //   47: goto +191 -> 238
    //   50: astore 4
    //   52: aload_2
    //   53: monitorexit
    //   54: aload 4
    //   56: athrow
    //   57: aload_2
    //   58: monitorexit
    //   59: aload_0
    //   60: getfield 214	com/android/providers/settings/SettingsProvider:mOpenHelper	Lcom/android/providers/settings/DatabaseHelper;
    //   63: invokevirtual 220	com/android/providers/settings/DatabaseHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   66: astore 5
    //   68: aconst_null
    //   69: astore 6
    //   71: getstatic 57	com/android/providers/settings/SettingsProvider:COLUMN_VALUE	[Ljava/lang/String;
    //   74: astore 11
    //   76: iconst_1
    //   77: anewarray 53	java/lang/String
    //   80: astore 12
    //   82: aload 12
    //   84: iconst_0
    //   85: aload_3
    //   86: aastore
    //   87: aload 5
    //   89: aload_1
    //   90: aload 11
    //   92: ldc 147
    //   94: aload 12
    //   96: aconst_null
    //   97: aconst_null
    //   98: aconst_null
    //   99: aconst_null
    //   100: invokevirtual 227	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   103: astore 6
    //   105: aload 6
    //   107: ifnull +56 -> 163
    //   110: aload 6
    //   112: invokeinterface 309 1 0
    //   117: iconst_1
    //   118: if_icmpne +45 -> 163
    //   121: aload 6
    //   123: invokeinterface 312 1 0
    //   128: pop
    //   129: aload_2
    //   130: aload_3
    //   131: aload 6
    //   133: iconst_0
    //   134: invokeinterface 160 2 0
    //   139: invokevirtual 315	com/android/providers/settings/SettingsProvider$SettingsCache:putIfAbsent	(Ljava/lang/String;Ljava/lang/String;)Landroid/os/Bundle;
    //   142: astore 15
    //   144: aload 15
    //   146: astore 10
    //   148: aload 6
    //   150: ifnull +88 -> 238
    //   153: aload 6
    //   155: invokeinterface 210 1 0
    //   160: goto +78 -> 238
    //   163: aload 6
    //   165: ifnull +10 -> 175
    //   168: aload 6
    //   170: invokeinterface 210 1 0
    //   175: aload_2
    //   176: aload_3
    //   177: aconst_null
    //   178: invokevirtual 315	com/android/providers/settings/SettingsProvider$SettingsCache:putIfAbsent	(Ljava/lang/String;Ljava/lang/String;)Landroid/os/Bundle;
    //   181: pop
    //   182: getstatic 84	com/android/providers/settings/SettingsProvider:NULL_SETTING	Landroid/os/Bundle;
    //   185: astore 10
    //   187: goto +51 -> 238
    //   190: astore 8
    //   192: ldc 37
    //   194: ldc_w 317
    //   197: aload 8
    //   199: invokestatic 321	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   202: pop
    //   203: aconst_null
    //   204: astore 10
    //   206: aload 6
    //   208: ifnull +30 -> 238
    //   211: aload 6
    //   213: invokeinterface 210 1 0
    //   218: goto +20 -> 238
    //   221: astore 7
    //   223: aload 6
    //   225: ifnull +10 -> 235
    //   228: aload 6
    //   230: invokeinterface 210 1 0
    //   235: aload 7
    //   237: athrow
    //   238: aload 10
    //   240: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   2	54	50	finally
    //   57	59	50	finally
    //   71	144	190	android/database/sqlite/SQLiteException
    //   71	144	221	finally
    //   192	203	221	finally
  }

  private boolean parseProviderList(Uri paramUri, ContentValues paramContentValues)
  {
    String str1 = paramContentValues.getAsString("value");
    int j;
    String str2;
    Object localObject1;
    Cursor localCursor;
    if ((str1 != null) && (str1.length() > 1))
    {
      j = str1.charAt(0);
      if ((j == 43) || (j == 45))
      {
        str2 = str1.substring(1);
        localObject1 = "";
        String[] arrayOfString = new String[1];
        arrayOfString[0] = "value";
        localCursor = query(paramUri, arrayOfString, "name='location_providers_allowed'", null, null);
        if ((localCursor == null) || (localCursor.getCount() != 1));
      }
    }
    while (true)
    {
      int k;
      int m;
      try
      {
        localCursor.moveToFirst();
        String str4 = localCursor.getString(0);
        localObject1 = str4;
        localCursor.close();
        k = ((String)localObject1).indexOf(str2);
        m = k + str2.length();
        if ((k <= 0) || (((String)localObject1).charAt(k - 1) == ','))
          continue;
        k = -1;
        if ((m >= ((String)localObject1).length()) || (((String)localObject1).charAt(m) == ','))
          continue;
        k = -1;
        if ((j != 43) || (k >= 0))
          break label276;
        if (((String)localObject1).length() == 0)
        {
          str3 = str2;
          if (str3 == null)
            continue;
          paramContentValues.put("value", str3);
          i = 1;
          return i;
        }
      }
      finally
      {
        localCursor.close();
      }
      String str3 = (String)localObject1 + ',' + str2;
      continue;
      label276: if ((j == 45) && (k >= 0))
      {
        if (k > 0)
          k--;
        while (true)
        {
          str3 = ((String)localObject1).substring(0, k);
          if (m >= ((String)localObject1).length())
            break;
          str3 = str3 + ((String)localObject1).substring(m);
          break;
          if (m >= ((String)localObject1).length())
            continue;
          m++;
        }
      }
      int i = 0;
    }
  }

  private void sendNotify(Uri paramUri)
  {
    int i = 0;
    String str1 = null;
    String str2 = (String)paramUri.getPathSegments().get(0);
    if (str2.equals("system"))
    {
      str1 = "sys.settings_system_version";
      i = 1;
    }
    while (true)
    {
      if (str1 != null)
        SystemProperties.set(str1, Long.toString(1L + SystemProperties.getLong(str1, 0L)));
      if (i != 0)
        this.mBackupManager.dataChanged();
      String str3 = paramUri.getQueryParameter("notify");
      if ((str3 == null) || ("true".equals(str3)))
        getContext().getContentResolver().notifyChange(paramUri, null);
      return;
      if (!str2.equals("secure"))
        continue;
      str1 = "sys.settings_secure_version";
      i = 1;
    }
  }

  private void startAsyncCachePopulation()
  {
    new Thread("populate-settings-caches")
    {
      public void run()
      {
        SettingsProvider.this.fullyPopulateCaches();
      }
    }
    .start();
  }

  public int bulkInsert(Uri paramUri, ContentValues[] paramArrayOfContentValues)
  {
    int i = 0;
    SqlArguments localSqlArguments = new SqlArguments(paramUri);
    if ("favorites".equals(localSqlArguments.table));
    while (true)
    {
      return i;
      checkWritePermissions(localSqlArguments);
      SettingsCache localSettingsCache = SettingsCache.forTable(localSqlArguments.table);
      sKnownMutationsInFlight.incrementAndGet();
      SQLiteDatabase localSQLiteDatabase = this.mOpenHelper.getWritableDatabase();
      localSQLiteDatabase.beginTransaction();
      try
      {
        int j = paramArrayOfContentValues.length;
        for (int k = 0; ; k++)
        {
          if (k >= j)
            break label132;
          long l = localSQLiteDatabase.insert(localSqlArguments.table, null, paramArrayOfContentValues[k]);
          if (l < 0L)
          {
            localSQLiteDatabase.endTransaction();
            sKnownMutationsInFlight.decrementAndGet();
            break;
          }
          SettingsCache.populate(localSettingsCache, paramArrayOfContentValues[k]);
        }
        label132: localSQLiteDatabase.setTransactionSuccessful();
        localSQLiteDatabase.endTransaction();
        sKnownMutationsInFlight.decrementAndGet();
        sendNotify(paramUri);
        i = paramArrayOfContentValues.length;
        continue;
      }
      finally
      {
        localSQLiteDatabase.endTransaction();
        sKnownMutationsInFlight.decrementAndGet();
      }
    }
    throw localObject;
  }

  public Bundle call(String paramString1, String paramString2, Bundle paramBundle)
  {
    Bundle localBundle;
    if ("GET_system".equals(paramString1))
      localBundle = lookupValue("system", sSystemCache, paramString2);
    while (true)
    {
      return localBundle;
      if ("GET_secure".equals(paramString1))
      {
        localBundle = lookupValue("secure", sSecureCache, paramString2);
        continue;
      }
      localBundle = null;
    }
  }

  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    SqlArguments localSqlArguments = new SqlArguments(paramUri, paramString, paramArrayOfString);
    int i;
    if ("favorites".equals(localSqlArguments.table))
      i = 0;
    while (true)
    {
      return i;
      if ("old_favorites".equals(localSqlArguments.table))
        localSqlArguments.table = "favorites";
      checkWritePermissions(localSqlArguments);
      sKnownMutationsInFlight.incrementAndGet();
      i = this.mOpenHelper.getWritableDatabase().delete(localSqlArguments.table, localSqlArguments.where, localSqlArguments.args);
      sKnownMutationsInFlight.decrementAndGet();
      if (i > 0)
      {
        SettingsCache.invalidate(localSqlArguments.table);
        sendNotify(paramUri);
      }
      startAsyncCachePopulation();
    }
  }

  public String getType(Uri paramUri)
  {
    SqlArguments localSqlArguments = new SqlArguments(paramUri, null, null);
    if (TextUtils.isEmpty(localSqlArguments.where));
    for (String str = "vnd.android.cursor.dir/" + localSqlArguments.table; ; str = "vnd.android.cursor.item/" + localSqlArguments.table)
      return str;
  }

  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    Object localObject = null;
    SqlArguments localSqlArguments = new SqlArguments(paramUri);
    if ("favorites".equals(localSqlArguments.table));
    while (true)
    {
      return localObject;
      checkWritePermissions(localSqlArguments);
      String str = paramContentValues.getAsString("name");
      if (("location_providers_allowed".equals(str)) && (!parseProviderList(paramUri, paramContentValues)))
        continue;
      SettingsCache localSettingsCache = SettingsCache.forTable(localSqlArguments.table);
      if (SettingsCache.isRedundantSetValue(localSettingsCache, str, paramContentValues.getAsString("value")))
      {
        localObject = Uri.withAppendedPath(paramUri, str);
        continue;
      }
      sKnownMutationsInFlight.incrementAndGet();
      long l = this.mOpenHelper.getWritableDatabase().insert(localSqlArguments.table, null, paramContentValues);
      sKnownMutationsInFlight.decrementAndGet();
      if (l <= 0L)
        continue;
      SettingsCache.populate(localSettingsCache, paramContentValues);
      Uri localUri = getUriFor(paramUri, paramContentValues, l);
      sendNotify(localUri);
      localObject = localUri;
    }
  }

  public boolean onCreate()
  {
    this.mOpenHelper = new DatabaseHelper(getContext());
    this.mBackupManager = new BackupManager(getContext());
    if (!ensureAndroidIdIsSet());
    for (int i = 0; ; i = 1)
    {
      return i;
      sObserverInstance = new SettingsFileObserver(this.mOpenHelper.getReadableDatabase().getPath());
      sObserverInstance.startWatching();
      startAsyncCachePopulation();
    }
  }

  // ERROR //
  public android.content.res.AssetFileDescriptor openAssetFile(Uri paramUri, String paramString)
    throws FileNotFoundException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 507	android/media/RingtoneManager:getDefaultType	(Landroid/net/Uri;)I
    //   4: istore_3
    //   5: iload_3
    //   6: bipush 255
    //   8: if_icmpeq +151 -> 159
    //   11: aload_0
    //   12: invokevirtual 116	com/android/providers/settings/SettingsProvider:getContext	()Landroid/content/Context;
    //   15: astore 5
    //   17: aload 5
    //   19: iload_3
    //   20: invokestatic 511	android/media/RingtoneManager:getActualDefaultRingtoneUri	(Landroid/content/Context;I)Landroid/net/Uri;
    //   23: astore 6
    //   25: aload 6
    //   27: ifnull +124 -> 151
    //   30: aload 6
    //   32: invokevirtual 514	android/net/Uri:getAuthority	()Ljava/lang/String;
    //   35: astore 7
    //   37: aload 7
    //   39: ldc_w 516
    //   42: invokevirtual 112	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   45: istore 8
    //   47: iload 8
    //   49: ifne +14 -> 63
    //   52: aload 7
    //   54: ldc_w 518
    //   57: invokevirtual 112	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   60: ifeq +57 -> 117
    //   63: iload 8
    //   65: ifeq +8 -> 73
    //   68: aload 5
    //   70: invokestatic 523	android/provider/DrmStore:enforceAccessDrmPermission	(Landroid/content/Context;)V
    //   73: new 525	android/content/res/AssetFileDescriptor
    //   76: dup
    //   77: aload 5
    //   79: invokevirtual 379	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   82: aload 6
    //   84: aload_2
    //   85: invokevirtual 529	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;)Landroid/os/ParcelFileDescriptor;
    //   88: lconst_0
    //   89: ldc2_w 530
    //   92: invokespecial 534	android/content/res/AssetFileDescriptor:<init>	(Landroid/os/ParcelFileDescriptor;JJ)V
    //   95: astore 4
    //   97: aload 4
    //   99: areturn
    //   100: astore 12
    //   102: new 501	java/io/FileNotFoundException
    //   105: dup
    //   106: aload 12
    //   108: invokevirtual 537	java/lang/SecurityException:getMessage	()Ljava/lang/String;
    //   111: invokespecial 538	java/io/FileNotFoundException:<init>	(Ljava/lang/String;)V
    //   114: athrow
    //   115: astore 9
    //   117: aload_0
    //   118: aload 6
    //   120: aload_2
    //   121: invokespecial 540	android/content/ContentProvider:openAssetFile	(Landroid/net/Uri;Ljava/lang/String;)Landroid/content/res/AssetFileDescriptor;
    //   124: astore 11
    //   126: aload 11
    //   128: astore 4
    //   130: goto -33 -> 97
    //   133: astore 10
    //   135: aload 5
    //   137: invokevirtual 544	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   140: ldc_w 545
    //   143: invokevirtual 551	android/content/res/Resources:openRawResourceFd	(I)Landroid/content/res/AssetFileDescriptor;
    //   146: astore 4
    //   148: goto -51 -> 97
    //   151: new 501	java/io/FileNotFoundException
    //   154: dup
    //   155: invokespecial 552	java/io/FileNotFoundException:<init>	()V
    //   158: athrow
    //   159: aload_0
    //   160: aload_1
    //   161: aload_2
    //   162: invokespecial 540	android/content/ContentProvider:openAssetFile	(Landroid/net/Uri;Ljava/lang/String;)Landroid/content/res/AssetFileDescriptor;
    //   165: astore 4
    //   167: goto -70 -> 97
    //
    // Exception table:
    //   from	to	target	type
    //   68	73	100	java/lang/SecurityException
    //   73	97	115	java/io/FileNotFoundException
    //   117	126	133	java/io/FileNotFoundException
  }

  public ParcelFileDescriptor openFile(Uri paramUri, String paramString)
    throws FileNotFoundException
  {
    int i = RingtoneManager.getDefaultType(paramUri);
    Context localContext;
    Uri localUri;
    if (i != -1)
    {
      localContext = getContext();
      localUri = RingtoneManager.getActualDefaultRingtoneUri(localContext, i);
      if (localUri != null)
      {
        String str = localUri.getAuthority();
        boolean bool = str.equals("drm");
        if ((bool) || (str.equals("media")))
          if (!bool);
      }
    }
    while (true)
    {
      try
      {
        DrmStore.enforceAccessDrmPermission(localContext);
        localParcelFileDescriptor = localContext.getContentResolver().openFileDescriptor(localUri, paramString);
        return localParcelFileDescriptor;
      }
      catch (SecurityException localSecurityException)
      {
        throw new FileNotFoundException(localSecurityException.getMessage());
      }
      ParcelFileDescriptor localParcelFileDescriptor = super.openFile(paramUri, paramString);
    }
  }

  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    SqlArguments localSqlArguments = new SqlArguments(paramUri, paramString1, paramArrayOfString2);
    SQLiteDatabase localSQLiteDatabase = this.mOpenHelper.getReadableDatabase();
    Cursor localCursor1;
    if ("favorites".equals(localSqlArguments.table))
      localCursor1 = null;
    while (true)
    {
      return localCursor1;
      if ("old_favorites".equals(localSqlArguments.table))
      {
        localSqlArguments.table = "favorites";
        Cursor localCursor2 = localSQLiteDatabase.rawQuery("PRAGMA table_info(favorites);", null);
        if (localCursor2 != null)
        {
          if (localCursor2.getCount() > 0);
          for (int i = 1; ; i = 0)
          {
            localCursor2.close();
            if (i != 0)
              break label120;
            localCursor1 = null;
            break;
          }
        }
        localCursor1 = null;
        continue;
      }
      label120: SQLiteQueryBuilder localSQLiteQueryBuilder = new SQLiteQueryBuilder();
      localSQLiteQueryBuilder.setTables(localSqlArguments.table);
      localCursor1 = localSQLiteQueryBuilder.query(localSQLiteDatabase, paramArrayOfString1, localSqlArguments.where, localSqlArguments.args, null, null, paramString2);
      localCursor1.setNotificationUri(getContext().getContentResolver(), paramUri);
    }
  }

  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    SqlArguments localSqlArguments = new SqlArguments(paramUri, paramString, paramArrayOfString);
    int i;
    if ("favorites".equals(localSqlArguments.table))
      i = 0;
    while (true)
    {
      return i;
      checkWritePermissions(localSqlArguments);
      sKnownMutationsInFlight.incrementAndGet();
      i = this.mOpenHelper.getWritableDatabase().update(localSqlArguments.table, paramContentValues, localSqlArguments.where, localSqlArguments.args);
      sKnownMutationsInFlight.decrementAndGet();
      if (i > 0)
      {
        SettingsCache.invalidate(localSqlArguments.table);
        sendNotify(paramUri);
      }
      startAsyncCachePopulation();
    }
  }

  private static final class SettingsCache extends LinkedHashMap<String, Bundle>
  {
    private boolean mCacheFullyMatchesDisk = false;
    private final String mCacheName;

    public SettingsCache(String paramString)
    {
      super(0.75F, true);
      this.mCacheName = paramString;
    }

    public static SettingsCache forTable(String paramString)
    {
      SettingsCache localSettingsCache;
      if ("system".equals(paramString))
        localSettingsCache = SettingsProvider.sSystemCache;
      while (true)
      {
        return localSettingsCache;
        if ("secure".equals(paramString))
        {
          localSettingsCache = SettingsProvider.sSecureCache;
          continue;
        }
        localSettingsCache = null;
      }
    }

    public static void invalidate(String paramString)
    {
      SettingsCache localSettingsCache = forTable(paramString);
      if (localSettingsCache == null);
      while (true)
      {
        return;
        monitorenter;
        try
        {
          localSettingsCache.clear();
          localSettingsCache.mCacheFullyMatchesDisk = false;
          monitorexit;
          continue;
        }
        finally
        {
          localObject = finally;
          monitorexit;
        }
      }
      throw localObject;
    }

    // ERROR //
    public static boolean isRedundantSetValue(SettingsCache paramSettingsCache, String paramString1, String paramString2)
    {
      // Byte code:
      //   0: iconst_1
      //   1: istore_3
      //   2: iconst_0
      //   3: istore 4
      //   5: aload_0
      //   6: ifnonnull +6 -> 12
      //   9: iload 4
      //   11: ireturn
      //   12: aload_0
      //   13: monitorenter
      //   14: aload_0
      //   15: aload_1
      //   16: invokevirtual 54	com/android/providers/settings/SettingsProvider$SettingsCache:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   19: checkcast 56	android/os/Bundle
      //   22: astore 6
      //   24: aload 6
      //   26: ifnonnull +15 -> 41
      //   29: aload_0
      //   30: monitorexit
      //   31: goto -22 -> 9
      //   34: astore 5
      //   36: aload_0
      //   37: monitorexit
      //   38: aload 5
      //   40: athrow
      //   41: aload 6
      //   43: invokevirtual 60	android/os/Bundle:getPairValue	()Ljava/lang/String;
      //   46: astore 7
      //   48: aload 7
      //   50: ifnonnull +39 -> 89
      //   53: aload_2
      //   54: ifnonnull +35 -> 89
      //   57: aload_0
      //   58: monitorexit
      //   59: iload_3
      //   60: istore 4
      //   62: goto -53 -> 9
      //   65: iload 8
      //   67: iload_3
      //   68: if_icmpeq +8 -> 76
      //   71: aload_0
      //   72: monitorexit
      //   73: goto -64 -> 9
      //   76: aload 7
      //   78: aload_2
      //   79: invokevirtual 32	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   82: istore 4
      //   84: aload_0
      //   85: monitorexit
      //   86: goto -77 -> 9
      //   89: aload 7
      //   91: ifnonnull +13 -> 104
      //   94: iload_3
      //   95: istore 8
      //   97: aload_2
      //   98: ifnonnull +12 -> 110
      //   101: goto -36 -> 65
      //   104: iconst_0
      //   105: istore 8
      //   107: goto -10 -> 97
      //   110: iconst_0
      //   111: istore_3
      //   112: goto -47 -> 65
      //
      // Exception table:
      //   from	to	target	type
      //   14	38	34	finally
      //   41	86	34	finally
    }

    public static void populate(SettingsCache paramSettingsCache, ContentValues paramContentValues)
    {
      if (paramSettingsCache == null);
      while (true)
      {
        return;
        String str = paramContentValues.getAsString("name");
        if (str == null)
        {
          Log.w("SettingsProvider", "null name populating settings cache.");
          continue;
        }
        paramSettingsCache.populate(str, paramContentValues.getAsString("value"));
      }
    }

    public boolean fullyMatchesDisk()
    {
      monitorenter;
      try
      {
        boolean bool = this.mCacheFullyMatchesDisk;
        monitorexit;
        return bool;
      }
      finally
      {
        localObject = finally;
        monitorexit;
      }
      throw localObject;
    }

    public void populate(String paramString1, String paramString2)
    {
      monitorenter;
      if (paramString2 != null);
      try
      {
        if (paramString2.length() <= 500)
        {
          put(paramString1, Bundle.forPair("value", paramString2));
          return;
        }
        put(paramString1, SettingsProvider.TOO_LARGE_TO_CACHE_MARKER);
      }
      finally
      {
        monitorexit;
      }
    }

    public Bundle putIfAbsent(String paramString1, String paramString2)
    {
      Bundle localBundle;
      if (paramString2 == null)
        localBundle = SettingsProvider.NULL_SETTING;
      while (true)
      {
        if ((paramString2 == null) || (paramString2.length() <= 500))
          monitorenter;
        try
        {
          if (!containsKey(paramString1))
            put(paramString1, localBundle);
          return localBundle;
          localBundle = Bundle.forPair("value", paramString2);
          continue;
        }
        finally
        {
          monitorexit;
        }
      }
      throw localObject;
    }

    protected boolean removeEldestEntry(Map.Entry paramEntry)
    {
      int i = 0;
      if (size() <= 200);
      while (true)
      {
        return i;
        monitorenter;
        try
        {
          this.mCacheFullyMatchesDisk = false;
          monitorexit;
          i = 1;
        }
        finally
        {
          monitorexit;
        }
      }
    }

    public void setFullyMatchesDisk(boolean paramBoolean)
    {
      monitorenter;
      try
      {
        this.mCacheFullyMatchesDisk = paramBoolean;
        monitorexit;
        return;
      }
      finally
      {
        localObject = finally;
        monitorexit;
      }
      throw localObject;
    }
  }

  private class SettingsFileObserver extends FileObserver
  {
    private final AtomicBoolean mIsDirty = new AtomicBoolean(false);
    private final String mPath;

    public SettingsFileObserver(String arg2)
    {
      super(906);
      this.mPath = str;
    }

    public void onEvent(int paramInt, String paramString)
    {
      if (SettingsProvider.sKnownMutationsInFlight.get() > 0);
      while (true)
      {
        return;
        Log.d("SettingsProvider", "external modification to " + this.mPath + "; event=" + paramInt);
        if (!this.mIsDirty.compareAndSet(false, true))
          continue;
        Log.d("SettingsProvider", "updating our caches for " + this.mPath);
        SettingsProvider.this.fullyPopulateCaches();
        this.mIsDirty.set(false);
      }
    }
  }

  private static class SqlArguments
  {
    public final String[] args;
    public String table;
    public final String where;

    SqlArguments(Uri paramUri)
    {
      if (paramUri.getPathSegments().size() == 1)
      {
        this.table = ((String)paramUri.getPathSegments().get(0));
        if (!DatabaseHelper.isValidTable(this.table))
          throw new IllegalArgumentException("Bad root path: " + this.table);
        this.where = null;
        this.args = null;
        return;
      }
      throw new IllegalArgumentException("Invalid URI: " + paramUri);
    }

    SqlArguments(Uri paramUri, String paramString, String[] paramArrayOfString)
    {
      if (paramUri.getPathSegments().size() == 1)
      {
        this.table = ((String)paramUri.getPathSegments().get(0));
        if (!DatabaseHelper.isValidTable(this.table))
          throw new IllegalArgumentException("Bad root path: " + this.table);
        this.where = paramString;
        this.args = paramArrayOfString;
      }
      while (true)
      {
        return;
        if (paramUri.getPathSegments().size() != 2)
          throw new IllegalArgumentException("Invalid URI: " + paramUri);
        if (!TextUtils.isEmpty(paramString))
          throw new UnsupportedOperationException("WHERE clause not supported: " + paramUri);
        this.table = ((String)paramUri.getPathSegments().get(0));
        if (!DatabaseHelper.isValidTable(this.table))
          throw new IllegalArgumentException("Bad root path: " + this.table);
        if (("system".equals(this.table)) || ("secure".equals(this.table)))
        {
          this.where = "name=?";
          String[] arrayOfString = new String[1];
          arrayOfString[0] = ((String)paramUri.getPathSegments().get(1));
          this.args = arrayOfString;
          continue;
        }
        this.where = ("_id=" + ContentUris.parseId(paramUri));
        this.args = null;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.providers.settings.SettingsProvider
 * JD-Core Version:    0.6.0
 */