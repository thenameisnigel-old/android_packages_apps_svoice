package com.samsung.myplace;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class SVoiceMyPlaceDBProvider extends SQLiteOpenHelper
{
  public static final String BT_DEVICE_NAME = "bt_device_name";
  public static final String BT_MAC_ADDRESS = "bt_mac_address";
  static final String DATABASE_NAME = "myPlaceSvoiceDatabase";
  private static final int DATABASE_VERSION = 2;
  public static final String GPS_LATITUDE = "gps_latitude";
  public static final String GPS_LOCATION = "gps_location";
  public static final String GPS_LONGITUDE = "gps_longitude";
  public static final String GPS_MAP = "gps_map";
  static final String KEY_ID = "_ID";
  static final String KEY_PLACE_NAME = "_PLACE_NAME";
  static final String KEY_PROFILE_ID = "_PROFILE_ID";
  static final String KEY_TYPE = "_TYPE";
  static final String KEY_VALUE = "_VALUE";
  public static final String METHOD_TYPE = "type";
  public static final String PROFILE_KEY_ID = "_id";
  public static final String PROFILE_NAME = "profile_name";
  static final String TABLE_NAME = "myPlaceTable";
  public static final String TIMESTAMP = "timestamp";
  public static final String WIFI_AP_AUTO_CONNECT = "wifi_auto_connect";
  public static final String WIFI_AP_BSSID = "wifi_bssid";
  public static final String WIFI_AP_FREQUENCY = "wifi_frequency";
  public static final String WIFI_AP_NAME = "wifi_ap_name";
  SVoiceMyPlaceDBProvider mSvoiceMyPlaceDBProvider = null;

  public SVoiceMyPlaceDBProvider(Context paramContext)
  {
    super(paramContext, "myPlaceSvoiceDatabase", null, 2);
  }

  public void deleteAllMyPlaceData()
  {
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
    localSQLiteDatabase.delete("myPlaceTable", null, null);
    localSQLiteDatabase.close();
  }

  public void deleteMyPlaceData(MyPlaceData paramMyPlaceData)
  {
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
    String[] arrayOfString = new String[1];
    arrayOfString[0] = String.valueOf(paramMyPlaceData.getProfile_id());
    localSQLiteDatabase.delete("myPlaceTable", "_PROFILE_ID= ?", arrayOfString);
    localSQLiteDatabase.close();
  }

  public void dropTable()
  {
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
    localSQLiteDatabase.execSQL("DROP TABLE IF EXISTS myPlaceTable");
    localSQLiteDatabase.close();
  }

  public ArrayList<MyPlaceData> getAllMyPlaceDataList()
  {
    ArrayList localArrayList = new ArrayList();
    SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
    Cursor localCursor = localSQLiteDatabase.query("myPlaceTable", null, null, null, null, null, null);
    if ((localCursor != null) && (localCursor.moveToFirst()))
      do
        localArrayList.add(new MyPlaceData(Integer.parseInt(localCursor.getString(0)), localCursor.getString(1), localCursor.getString(2), localCursor.getString(4), Integer.parseInt(localCursor.getString(3))));
      while (localCursor.moveToNext());
    if (localCursor != null)
      localCursor.close();
    localSQLiteDatabase.close();
    return localArrayList;
  }

  public MyPlaceData getMyPlaceData(int paramInt)
  {
    SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
    String[] arrayOfString = new String[1];
    arrayOfString[0] = String.valueOf(paramInt);
    Cursor localCursor = localSQLiteDatabase.query("myPlaceTable", null, "_PROFILE_ID= ?", arrayOfString, null, null, null);
    MyPlaceData localMyPlaceData = null;
    if ((localCursor != null) && (localCursor.moveToFirst()))
    {
      localMyPlaceData = new MyPlaceData(Integer.parseInt(localCursor.getString(0)), localCursor.getString(1), localCursor.getString(2), localCursor.getString(4), Integer.parseInt(localCursor.getString(3)));
      localCursor.close();
    }
    localSQLiteDatabase.close();
    return localMyPlaceData;
  }

  public ArrayList<MyPlaceData> getSelectedMyPlaceDataList(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
    String[] arrayOfString = new String[1];
    arrayOfString[0] = String.valueOf(paramString);
    Cursor localCursor = localSQLiteDatabase.query("myPlaceTable", null, "_TYPE = ?", arrayOfString, null, null, null);
    if ((localCursor != null) && (localCursor.moveToFirst()))
      do
        localArrayList.add(new MyPlaceData(Integer.parseInt(localCursor.getString(0)), localCursor.getString(1), localCursor.getString(2), localCursor.getString(4), Integer.parseInt(localCursor.getString(3))));
      while (localCursor.moveToNext());
    if (localCursor != null)
      localCursor.close();
    localSQLiteDatabase.close();
    return localArrayList;
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE myPlaceTable(_ID INTEGER PRIMARY KEY , _PLACE_NAME TEXT , _TYPE TEXT , _PROFILE_ID INTEGER ,_VALUE TEXT )");
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS myPlaceTable");
    onCreate(paramSQLiteDatabase);
  }

  public void saveDataToDatabase(MyPlaceData paramMyPlaceData)
  {
    if (getMyPlaceData(paramMyPlaceData.getProfile_id()) != null)
      updateMyPlaceData(paramMyPlaceData);
    while (true)
    {
      return;
      SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("_PLACE_NAME", paramMyPlaceData.getPlaceName());
      localContentValues.put("_TYPE", paramMyPlaceData.getType());
      localContentValues.put("_VALUE", paramMyPlaceData.getValue());
      localContentValues.put("_PROFILE_ID", Integer.valueOf(paramMyPlaceData.getProfile_id()));
      localSQLiteDatabase.insert("myPlaceTable", null, localContentValues);
      localSQLiteDatabase.close();
    }
  }

  public int updateMyPlaceData(MyPlaceData paramMyPlaceData)
  {
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_ID", Integer.valueOf(paramMyPlaceData.getId()));
    localContentValues.put("_PLACE_NAME", paramMyPlaceData.getPlaceName());
    localContentValues.put("_TYPE", paramMyPlaceData.getType());
    localContentValues.put("_VALUE", paramMyPlaceData.getValue());
    String[] arrayOfString = new String[2];
    arrayOfString[0] = String.valueOf(paramMyPlaceData.getProfile_id());
    arrayOfString[1] = String.valueOf(paramMyPlaceData.getId());
    int i = localSQLiteDatabase.update("myPlaceTable", localContentValues, "_PROFILE_ID= ? and _ID = ?", arrayOfString);
    localSQLiteDatabase.close();
    return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.myplace.SVoiceMyPlaceDBProvider
 * JD-Core Version:    0.6.0
 */