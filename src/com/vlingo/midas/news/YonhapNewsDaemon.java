package com.vlingo.midas.news;

import android.app.Application;
import android.net.Uri;
import android.provider.BaseColumns;

public class YonhapNewsDaemon extends Application
{
  public static final String ACTION_BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";
  public static final String ACTION_DAEMON_NEWS_AUTOREFRESH = "com.sec.android.daemonapp.ap.yonhapnews.intent.action.autorefresh";
  public static final String ACTION_DAEMON_NEWS_REFRESH = "com.sec.android.daemonapp.ap.yonhapnews.intent.action.refresh";
  public static final String ACTION_DAEMON_NEWS_RELOAD_SETTING = "com.sec.android.daemonapp.ap.yonhapnews.intent.action.reload.setting";
  public static final String ACTION_DAEMON_NEWS_SYNC_SETTING = "com.sec.android.daemonapp.ap.yonhapnews.intent.action.sync.setting";
  public static final String ACTION_DAEMON_NEWS_UPDATE_RESULT = "com.sec.android.daemonapp.ap.yonhapnews.intent.action.update_result";
  public static final String ACTION_WIDGET_NEWS_REFRESH = "action.widget.news.refresh";
  public static final String ACTION_WIDGET_NEWS_RELOAD_SETTING = "action.widget.news.reload.setting";
  public static final String ACTION_YONHAPNEWS_DAEMON_SERVICE_ON_OFF = "com.sec.android.daemonapp.ap.yonhapnews.intent.action.SERVICE_ON_OFF";
  public static final String ACTION_YONHAPNEWS_DATE_SYNC = "com.sec.android.daemonapp.ap.yonhapnews.intent.action.YONHAPNEWS_DATE_SYNC";
  public static final int ALARM_SERVICE_CODE = 4;
  public static final String AUTHORITY = "com.sec.android.daemonapp.ap.yonhapnews.provider";
  public static final String COMMON_PROCESS = "com.sec.android.daemonapp.ap.yonhapnews.intent.extra.common.process";
  public static final String DB_NAME = "YonhapNewsDaemon.db";
  public static final int DB_VERSION = 8;
  public static final boolean DEB_PRINT = true;
  public static final int ECONOMICS = 2;
  public static final int ENTERTAINMENT = 4;
  public static final String ERROR_CODE = "Error_Code";
  public static final int FUNTION_OFF = 0;
  public static final int FUNTION_ON = 1;
  public static final String GET_NEWS_DATA = "Get_News_Data";
  public static final String GET_NEWS_HEAD_DATA = "Get_News_Head_Data";
  public static final int HOURS_12 = 43200000;
  public static final int HOURS_3 = 10800000;
  public static final int HOURS_6 = 21600000;
  public static final int LOCK_SCREEN_SERVICE_CODE = 1;
  public static final int MAIN_NEWS = 0;
  public static final int MINUTE = 60000;
  public static final int NETWORK_TIMEOUT = 20000;
  public static final String NEWS_PROCESS = "com.sec.android.daemonapp.ap.yonhapnews.intent.extra.news.process";
  public static final String NEWS_SD_ECON = "4";
  public static final String NEWS_SD_ENT = "7";
  public static final String NEWS_SD_MAIN = "0";
  public static final String NEWS_SD_POLIT = "2";
  public static final String NEWS_SD_SOCI = "5";
  public static final String NEWS_SD_SPORT = "9";
  public static final String NONE = "None";
  public static final int POLITICS = 1;
  public static final int REFRESH_12H = 8;
  public static final int REFRESH_15M = 2;
  public static final int REFRESH_1H = 4;
  public static final int REFRESH_1M = 1;
  public static final int REFRESH_24H = 9;
  public static final int REFRESH_2H = 5;
  public static final int REFRESH_30M = 3;
  public static final int REFRESH_3H = 6;
  public static final int REFRESH_6H = 7;
  public static final int REFRESH_NONE = 0;
  public static final int SERVICE_OFF = 0;
  public static final int SERVICE_ON = 1;
  public static final int SET_DISABLE = 0;
  public static final int SET_ENABLE = 1;
  public static final int SOCIAL = 3;
  public static final int SPORTS = 5;
  public static final int STATE_DATA_NOT_FOUND = -4;
  public static final int STATE_DB_ERR = -3;
  public static final int STATE_LOADING = 1;
  public static final int STATE_NETWORK_ERROR = -2;
  public static final int STATE_NETWORK_UNAVAILABLE = -1;
  public static final int STATE_NORMAL = 0;
  public static final String TAG = "YonhapNewsDaemon";
  public static final int TOPICWALL_SERVICE_CODE = 8;
  public static final String TYPE = "Type";
  public static final int TYPE_DEFAULT = 0;
  public static final int TYPE_PREFERENCE = 30;
  public static final int TYPE_XML = 50;
  public static final int TYPE_YONHAPNEWS = 20;
  public static final int TYPE_YONHAPNEWS_IMAGE = 21;
  public static final int TYPE_YONHAPNEWS_REF_CONTENT = 40;
  public static final String UPDATED_NEWS = "com.sec.android.daemonapp.ap.yonhapnews.intent.action.Updated_news";
  public static final String UPDATED_RESULT = "com.sec.android.daemonapp.ap.yonhapnews.intent.action.Updated_result";
  public static final int WALLPAPER_SERVICE_CODE = 2;

  public static final class SystemColumns
    implements BaseColumns
  {
    public static final String KEY_APP_SERVICE_STATUS = "yonhap_daemon_service_key_app_service_status";
    public static final String KEY_AUTOREFRESH_INTERVAL = "yonhap_daemon_service_key_autorefresh_interval";
    public static final String KEY_AUTOREFRESH_TIME = "yonhap_daemon_service_key_autorefresh_time";
    public static final String KEY_CHARGING_NOTICE = "yonhap_daemon_service_key_charging_notice";
    public static final String KEY_SERVICE_STATUS = "yonhap_daemon_service_key_service_status";
    public static final String KEY_SET_DEFAULT_NEWS = "yonhap_daemon_service_key_set_default_news";
    public static final String YONHAP_UUID = "X-Client-UUID";
  }

  public static final class YonhapNewsColumns
    implements BaseColumns
  {
    public static final String[] CONTENTS_COLS;
    public static final String DEFAULT_SORT_ORDER = "NEWS_CATEGORY ASC, NEWS_PUBDATE DESC";
    public static final String NEWS_CATEGORY = "NEWS_CATEGORY";
    public static final String NEWS_CONTENTTEXT = "NEWS_CONTENTTEXT";
    public static final String NEWS_CREDIT = "NEWS_CREDIT";
    public static final String NEWS_DATE = "NEWS_DATE";
    public static final String NEWS_ID = "NEWS_ID";
    public static final String NEWS_IMAGEDATA = "NEWS_IMAGEDATA";
    public static final String NEWS_IMAGEURL = "NEWS_IMAGEURL";
    public static final String NEWS_INDEX = "NEWS_INDEX";
    public static final String NEWS_LANG = "NEWS_LANG";
    public static final String NEWS_LINK = "NEWS_LINK";
    public static final String NEWS_PUBDATE = "NEWS_PUBDATE";
    public static final String NEWS_REGION = "NEWS_REGION";
    public static final String NEWS_TIME = "NEWS_TIME";
    public static final String NEWS_TITLE = "NEWS_TITLE";
    public static final String ROWID = "_id";
    public static final String TABLE = "TABLE_YONHAP_NEWS_CONTENTS";
    public static final Uri TABLE_URI = Uri.parse("content://com.sec.android.daemonapp.ap.yonhapnews.provider/TABLE_YONHAP_NEWS_CONTENTS");
    public static final String UPDATE_STATE = "UPDATE_STATE";

    static
    {
      String[] arrayOfString = new String[16];
      arrayOfString[0] = "_id";
      arrayOfString[1] = "NEWS_INDEX";
      arrayOfString[2] = "NEWS_CATEGORY";
      arrayOfString[3] = "NEWS_REGION";
      arrayOfString[4] = "NEWS_LANG";
      arrayOfString[5] = "NEWS_ID";
      arrayOfString[6] = "NEWS_TITLE";
      arrayOfString[7] = "NEWS_LINK";
      arrayOfString[8] = "NEWS_TIME";
      arrayOfString[9] = "NEWS_IMAGEDATA";
      arrayOfString[10] = "NEWS_IMAGEURL";
      arrayOfString[11] = "NEWS_CONTENTTEXT";
      arrayOfString[12] = "NEWS_PUBDATE";
      arrayOfString[13] = "UPDATE_STATE";
      arrayOfString[14] = "NEWS_DATE";
      arrayOfString[15] = "NEWS_CREDIT";
      CONTENTS_COLS = arrayOfString;
    }
  }

  public static final class YonhapNewsRefContentColumns
    implements BaseColumns
  {
    public static final String[] CONTENTS_COLS;
    public static final String DEFAULT_SORT_ORDER = "_id ASC";
    public static final String NEWS_CATEGORY = "NEWS_CATEGORY";
    public static final String NEWS_ID = "NEWS_ID";
    public static final String NEWS_LINK = "NEWS_LINK";
    public static final String NEWS_MAIN_ID = "NEWS_MAIN_ID";
    public static final String NEWS_TITLE = "NEWS_TITLE";
    public static final String ROWID = "_id";
    public static final String TABLE = "TABLE_YONHAP_NEWS_REF_CONTENTS";
    public static final Uri TABLE_URI = Uri.parse("content://com.sec.android.daemonapp.ap.yonhapnews.provider/TABLE_YONHAP_NEWS_REF_CONTENTS");

    static
    {
      String[] arrayOfString = new String[6];
      arrayOfString[0] = "_id";
      arrayOfString[1] = "NEWS_MAIN_ID";
      arrayOfString[2] = "NEWS_LINK";
      arrayOfString[3] = "NEWS_ID";
      arrayOfString[4] = "NEWS_CATEGORY";
      arrayOfString[5] = "NEWS_TITLE";
      CONTENTS_COLS = arrayOfString;
    }
  }

  public static final class YonhapNewsXmlColumns
    implements BaseColumns
  {
    public static final String DEFAULT_SORT_ORDER = "_id ASC";
    public static final String NEWS_CATEGORY = "NEWS_CATEGORY";
    public static final String NEWS_XML = "NEWS_XML";
    public static final String ROWID = "_id";
    public static final String TABLE = "TABLE_YONHAP_NEWS_XML";
    public static final Uri TABLE_URI = Uri.parse("content://com.sec.android.daemonapp.ap.yonhapnews.provider/TABLE_YONHAP_NEWS_XML");
    public static final String[] XML_COLS;

    static
    {
      String[] arrayOfString = new String[3];
      arrayOfString[0] = "_id";
      arrayOfString[1] = "NEWS_CATEGORY";
      arrayOfString[2] = "NEWS_XML";
      XML_COLS = arrayOfString;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.news.YonhapNewsDaemon
 * JD-Core Version:    0.6.0
 */