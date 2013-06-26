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
import android.os.Build;
import android.os.SystemProperties;
import android.provider.Settings.System;
import android.util.Log;

public class HiddenMenuContent extends ContentProvider
{
  private static final String DATABASE_NAME = "HiddenMenuContent.db";
  private static final int DATABASE_VERSION = 4;
  private static final boolean DEBUG = false;
  private static final String TABLE_NAME = "HiddenMenu";
  private static final String TAG = "MyContentProvider";
  private static DatabaseHelper dbHelper;
  private static boolean isAPVTUpgrade;
  private static boolean isKTT;
  private static boolean isLGT;
  private static boolean isSKT = false;
  private static SQLiteDatabase sqlDB;

  static
  {
    isLGT = false;
    isKTT = false;
    isAPVTUpgrade = false;
  }

  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    return sqlDB.delete("HiddenMenu", paramString, paramArrayOfString);
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
      long l = sqlDB.insert("HiddenMenu", null, localContentValues);
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
    localSQLiteQueryBuilder.setTables("HiddenMenu");
    Cursor localCursor = localSQLiteQueryBuilder.query(localSQLiteDatabase, paramArrayOfString1, paramString1, null, null, null, paramString2);
    localCursor.setNotificationUri(getContext().getContentResolver(), paramUri);
    return localCursor;
  }

  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    if (paramContentValues != null);
    for (ContentValues localContentValues = new ContentValues(paramContentValues); ; localContentValues = new ContentValues())
    {
      int i = sqlDB.update("HiddenMenu", localContentValues, null, null);
      if (i <= 0)
        break;
      return i;
    }
    throw new SQLException("Failed to update row into " + paramUri);
  }

  private static class DatabaseHelper extends SQLiteOpenHelper
  {
    private final Context mContext;

    DatabaseHelper(Context paramContext)
    {
      super("HiddenMenuContent.db", null, 4);
      this.mContext = paramContext;
    }

    private void setDefaultPreferredValue()
    {
      if (HiddenMenuContent.DEBUG)
        Log.d("MyContentProvider", "VOICECALL_TYPE will set default value.");
      if (HiddenMenuContent.isLGT)
        Settings.System.putInt(this.mContext.getContentResolver(), "voicecall_type", 0);
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      if (HiddenMenuContent.DEBUG)
        Log.d("MyContentProvider", "onCreate");
      String str = SystemProperties.get("ro.csc.sales_code");
      HiddenMenuContent.access$102("SKT".equals(str));
      HiddenMenuContent.access$202("LGT".equals(str));
      HiddenMenuContent.access$302("KTT".equals(str));
      if (HiddenMenuContent.isLGT)
        paramSQLiteDatabase.execSQL("Create table HiddenMenu( _id INTEGER PRIMARY KEY AUTOINCREMENT, mPrefCscfDns TEXT,mPrefCscfDnsforWiFi TEXT,mPrefCscfIp TEXT,mPrefCscfIp2 TEXT,mPrefCscfIp3 TEXT,mPrefCscfIpCnt TEXT,mPrefCscfDomain TEXT,mPrefCscfPort TEXT,mPrefCscfPortforLTE TEXT,mPrefCscfPortforWiFi TEXT,mPrefXdmDns TEXT,mPrefXdmIp TEXT,mPrefXdmIpforWiFi TEXT,mPrefAudioCodec TEXT,mPrefVideoCodec TEXT,mLoopback TEXT,mAlwaysOn TEXT,mEnableQos TEXT,mVideoProfile TEXT,mAudioFirst TEXT,mQosAware TEXT,mVTDebugging TEXT,mAutoTest TEXT,mPrefNwType TEXT,mCurrLatchedNw TEXT,mAccessNwInfo TEXT,mCurrDispFormat TEXT,mFrameRate TEXT,mBitRate TEXT,mDynamicBitRate TEXT,mAudioCodecMode TEXT,mPublicUserIdentity TEXT,mPrivateUserIdentity TEXT,mWiFiSetting TEXT,mRegisterTimer TEXT,mSubscriberTimer TEXT,mSessionExpire TEXT,mAudioPort TEXT,mVideoPort TEXT,mPDN TEXT,mImsPdnAutoCon TEXT,mPcscfType TEXT,mCommercialPcscfIP TEXT,mCodecBandwidth TEXT,mAec TEXT,mNs TEXT,mVad TEXT,mUserAgent TEXT,mAudioBitRate TEXT,mAMRBundlingRate TEXT,mAudioVideoTx TEXT,mJitterBufferSetting TEXT,mReserved1 TEXT,mReserved2 TEXT,mReserved3 TEXT,mReserved4 TEXT,mReserved5 TEXT,mReserved6 TEXT,mEnableIMSOnRoamingVal TEXT);");
      while (true)
      {
        return;
        paramSQLiteDatabase.execSQL("Create table HiddenMenu( _id INTEGER PRIMARY KEY AUTOINCREMENT, mPrefCscfDns TEXT,mPrefCscfDnsforWiFi TEXT,mPrefCscfIp TEXT,mPrefCscfDomain TEXT,mPrefCscfPort TEXT,mPrefCscfPortforLTE TEXT,mPrefCscfPortforWiFi TEXT,mPrefXdmDns TEXT,mPrefXdmIp TEXT,mPrefXdmIpforWiFi TEXT,mPrefAudioCodec TEXT,mPrefVideoCodec TEXT,mLoopback TEXT,mAlwaysOn TEXT,mEnableQos TEXT,mVideoProfile TEXT,mAudioFirst TEXT,mQosAware TEXT,mVTDebugging TEXT,mAutoTest TEXT,mPrefNwType TEXT,mCurrLatchedNw TEXT,mAccessNwInfo TEXT,mCurrDispFormat TEXT,mFrameRate TEXT,mBitRate TEXT,mDynamicBitRate TEXT,mAudioCodecMode TEXT,mPublicUserIdentity TEXT,mPrivateUserIdentity TEXT,mRegisterTimer TEXT,mSubscriberTimer TEXT,mSessionExpire TEXT,mAudioPort TEXT,mVideoPort TEXT,mPDN TEXT,mImsPdnAutoCon TEXT,mAMRBundlingRate TEXT,mAudioVideoTx TEXT,mAudioBitRate TEXT,mJitterBufferSetting TEXT,mConfigServerUrl TEXT,mEnableConfigServer TEXT,mConfigServerServiceId TEXT,mReserved1 TEXT,mReserved2 TEXT,mReserved3 TEXT,mReserved4 TEXT,mReserved5 TEXT,mReserved6 TEXT,mReserved7 TEXT,mReserved8 TEXT,mTimerA TEXT,mTimerB TEXT,mTimerC TEXT,mTimerD TEXT,mTimerE TEXT,mTimerF TEXT,mTimerG TEXT,mTimerH TEXT,mTimerI TEXT,mTimerJ TEXT,mTimerK TEXT,mTimer1 TEXT,mTimer2 TEXT,mTimer4 TEXT,mSKTPwd TEXT,mSKTRealm TEXT,mSKTUname TEXT,mEnableIMSOnRoamingVal TEXT);");
      }
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      if (HiddenMenuContent.DEBUG)
        Log.d("MyContentProvider", "onUpgrade start");
      String str1 = SystemProperties.get("ro.product.name");
      boolean bool1;
      if (("SHV-E110S".equals(str1)) || ("SHV-E120S".equals(str1)) || ("SHV-E120L".equals(str1)) || ("SHV-E160S".equals(str1)) || ("SHV-E160L".equals(str1)) || ("jaguars".equals(str1)) || ("jaguarl".equals(str1)))
        bool1 = true;
      while (true)
      {
        HiddenMenuContent.access$402(bool1);
        if (HiddenMenuContent.DEBUG)
          Log.d("MyContentProvider", "isAPVTUpgrade = " + HiddenMenuContent.isAPVTUpgrade);
        String str2 = null;
        Cursor localCursor;
        if ((!HiddenMenuContent.isAPVTUpgrade) && ("LGT".equals(SystemProperties.get("ro.csc.sales_code"))))
        {
          if (HiddenMenuContent.DEBUG)
            Log.d("MyContentProvider", "oldVersion = " + paramInt1 + " newVersion = " + paramInt2);
          paramSQLiteDatabase.beginTransaction();
          localCursor = null;
        }
        try
        {
          SQLiteQueryBuilder localSQLiteQueryBuilder = new SQLiteQueryBuilder();
          localSQLiteQueryBuilder.setTables("HiddenMenu");
          String[] arrayOfString = new String[1];
          arrayOfString[0] = "mReserved2";
          localCursor = localSQLiteQueryBuilder.query(paramSQLiteDatabase, arrayOfString, null, null, null, null, null);
          if ((localCursor != null) && (localCursor.moveToFirst()))
          {
            str2 = localCursor.getString(0);
            if (HiddenMenuContent.DEBUG)
              Log.d("MyContentProvider", "reserved2 = " + str2);
          }
          paramSQLiteDatabase.setTransactionSuccessful();
          paramSQLiteDatabase.endTransaction();
          if (localCursor != null)
            localCursor.close();
          paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS HiddenMenu");
          onCreate(paramSQLiteDatabase);
          if (("LGT".equals(SystemProperties.get("ro.csc.sales_code"))) && (str2 != null))
          {
            boolean bool2 = "SEC_AP_VTONLY".equals("");
            if (("VT".equals(str2)) && (!bool2))
              setDefaultPreferredValue();
          }
          if (HiddenMenuContent.DEBUG)
            Log.d("MyContentProvider", "onUpgrade end");
          return;
          bool1 = false;
          continue;
        }
        catch (SQLException localSQLException)
        {
          while (true)
          {
            if (HiddenMenuContent.DEBUG)
              Log.e("MyContentProvider", "exception query for reserved attribute." + localSQLException.toString());
            paramSQLiteDatabase.endTransaction();
            if (localCursor == null)
              continue;
          }
        }
        finally
        {
          paramSQLiteDatabase.endTransaction();
          if (localCursor != null)
            localCursor.close();
        }
      }
      throw localObject;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.internal.ims.external.HiddenMenuContent
 * JD-Core Version:    0.6.0
 */