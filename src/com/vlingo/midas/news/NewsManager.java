package com.vlingo.midas.news;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.vcs.WidgetResponseReceivedListener;
import com.vlingo.midas.settings.MidasSettings;
import flipboard.api.FlipFetchListener;
import flipboard.api.FlipManager;
import flipboard.api.FlipManager.ContentType;
import flipboard.api.FlipManager.ErrorMessage;
import flipboard.api.FlipboardItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class NewsManager
  implements FlipFetchListener
{
  private static final String Flip_SStoken = "819b8dcb";
  private static final String NEWS = "news";
  private static final String NewsDaemonPackageName = "com.sec.android.daemonapp";
  private static List<NewsItem> Newss = new ArrayList();
  private static NewsManager _instance;
  private static NewsItem current_news = null;
  public static FlipManager mManager;
  private static BroadcastReceiver mYonHapReceiver = null;
  private Locale mCurrentLocale;
  private FlipManager.ErrorMessage mError = FlipManager.ErrorMessage.DUPLICATE_REQUEST;
  private Thread mThread = null;
  private int requestTypeFlags = 0;
  private WidgetResponseReceivedListener widgetListener;

  private NewsManager()
  {
    mManager = FlipManager.getInstance(ApplicationAdapter.getInstance().getApplicationContext());
  }

  public static void deinit()
  {
    if (isYonhapAvailable())
    {
      startYonhapNewsDaemon(false);
      if (mYonHapReceiver != null)
        ApplicationAdapter.getInstance().getApplicationContext().unregisterReceiver(mYonHapReceiver);
      mYonHapReceiver = null;
    }
  }

  public static NewsManager getInstance()
  {
    if (_instance == null)
      _instance = new NewsManager();
    return _instance;
  }

  public static void init()
  {
    if (isYonhapAvailable())
    {
      startYonhapNewsDaemon(true);
      NewsManager localNewsManager = getInstance();
      localNewsManager.getClass();
      mYonHapReceiver = new ClientReceiver(null);
      ApplicationAdapter.getInstance().getApplicationContext().registerReceiver(mYonHapReceiver, new IntentFilter("com.sec.android.daemonapp.ap.yonhapnews.intent.action.YONHAPNEWS_DATE_SYNC"));
    }
    if (getInstance().isFlipAvailable());
  }

  private boolean isFlipAvailable()
  {
    return mManager.isFlipboardAvailable();
  }

  private static boolean isYonhapAvailable()
  {
    PackageManager localPackageManager = ApplicationAdapter.getInstance().getApplicationContext().getPackageManager();
    try
    {
      localPackageManager.getApplicationInfo("com.sec.android.daemonapp", 128);
      i = 1;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
      {
        localNameNotFoundException.printStackTrace();
        int i = 0;
      }
    }
  }

  private void reset()
  {
    if ((Newss != null) && (Newss.size() > 0))
      current_news = (NewsItem)Newss.get(0);
    while (true)
    {
      return;
      current_news = null;
    }
  }

  public static void startYonhapNewsDaemon(boolean paramBoolean)
  {
    Intent localIntent = new Intent("com.sec.android.daemonapp.ap.yonhapnews.intent.action.SERVICE_ON_OFF");
    if (!paramBoolean)
      localIntent.putExtra("START", false);
    while (true)
    {
      localIntent.putExtra("PACKAGE", "com.vlingo.midas");
      localIntent.putExtra("CP", "YonhapNews");
      ApplicationAdapter.getInstance().getApplicationContext().sendBroadcast(localIntent);
      return;
      localIntent.putExtra("START", true);
    }
  }

  private NewsItem updateCurrent(int paramInt, boolean paramBoolean)
  {
    NewsItem localNewsItem = null;
    if ((this.mCurrentLocale != null) && (!this.mCurrentLocale.equals(MidasSettings.getLocaleForIsoLanguage(MidasSettings.getLanguageApplication()))));
    while (true)
    {
      return localNewsItem;
      if (current_news == null)
        continue;
      int i = Newss.indexOf(current_news);
      if (i == -1)
        continue;
      int j = i + paramInt;
      if ((j >= Newss.size()) || (j < 0))
        continue;
      if (paramBoolean)
        current_news = (NewsItem)Newss.get(j);
      localNewsItem = (NewsItem)Newss.get(j);
    }
  }

  public boolean fetchNews()
  {
    this.mCurrentLocale = MidasSettings.getLocaleForIsoLanguage(MidasSettings.getLanguageApplication());
    this.mError = FlipManager.ErrorMessage.DUPLICATE_REQUEST;
    this.mThread = new Thread()
    {
      private boolean mExit = false;

      public void interrupt()
      {
        this.mExit = true;
      }

      public void run()
      {
        int i = 100;
        setName("waiting for news");
        Log.d("NewsManagerThread", "start waitting thread");
        while ((i > 0) && (!this.mExit))
        {
          i--;
          try
          {
            sleep(100L);
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
          }
        }
        Log.d("NewsManagerThread", "maybe interrupted thread +++++ ");
        if (NewsManager.this.requestTypeFlags == 0)
        {
          NewsManager._instance.getFlipNewsData();
          if (NewsManager._instance.widgetListener != null)
          {
            if (!NewsManager._instance.isHavingNewsCache())
              break label121;
            NewsManager._instance.widgetListener.onResponseReceived();
          }
        }
        while (true)
        {
          Log.d("NewsManagerThread", "maybe interrupted thread ----- ");
          return;
          NewsManager._instance.getYonhapNewsData();
          break;
          label121: NewsManager._instance.widgetListener.onRequestFailed();
        }
      }
    };
    if (isFlipAvailable());
    while (true)
    {
      try
      {
        this.requestTypeFlags = 0;
        mManager.fetchFlipboardItems("819b8dcb", "news", FlipManager.ContentType.TEXT, this.mCurrentLocale, this);
        Log.d("NewsManager", "request start flip request thread");
        this.mThread.start();
        i = 1;
        return i;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        int i = 0;
        continue;
      }
      this.requestTypeFlags = 1;
      Intent localIntent = new Intent("com.sec.android.daemonapp.ap.yonhapnews.intent.action.refresh");
      ApplicationAdapter.getInstance().getApplicationContext().sendBroadcast(localIntent);
      Log.d("NewsManager", "request start yonhap request thread");
    }
  }

  public NewsItem getCurrentNews()
  {
    return updateCurrent(0, false);
  }

  public void getFlipNewsData()
  {
    this.mCurrentLocale = MidasSettings.getLocaleForIsoLanguage(MidasSettings.getLanguageApplication());
    List localList = mManager.getItemsForFeed("news", this.mCurrentLocale, FlipManager.ContentType.TEXT);
    Log.d("NewsManager-flipNews", " refreshNewsData " + localList + localList.size());
    if ((localList != null) && (localList.size() > 0))
    {
      if (Newss != null)
        Newss.clear();
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        FlipboardItem localFlipboardItem = (FlipboardItem)localIterator.next();
        Newss.add(new NewsItem(localFlipboardItem));
      }
      if (updateCurrent(0, true) == null)
        reset();
    }
  }

  public int getNewsCount()
  {
    if (Newss != null);
    for (int i = Newss.size(); ; i = -1)
      return i;
  }

  public NewsItem getNextNews()
  {
    return updateCurrent(1, false);
  }

  public NewsItem getPrevNews()
  {
    return updateCurrent(-1, false);
  }

  public void getYonhapNewsData()
  {
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    String str1 = String.valueOf(0);
    Newss.clear();
    if (localContext != null)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("NEWS_CATEGORY");
      localStringBuffer.append(" = ?");
      ContentResolver localContentResolver = localContext.getContentResolver();
      Uri localUri = YonhapNewsDaemon.YonhapNewsColumns.TABLE_URI;
      String[] arrayOfString1 = YonhapNewsDaemon.YonhapNewsColumns.CONTENTS_COLS;
      String str2 = localStringBuffer.toString();
      String[] arrayOfString2 = new String[1];
      arrayOfString2[0] = str1;
      Cursor localCursor = localContentResolver.query(localUri, arrayOfString1, str2, arrayOfString2, "NEWS_CATEGORY ASC, NEWS_PUBDATE DESC");
      YonhapNewsContainer localYonhapNewsContainer = new YonhapNewsContainer();
      if (localCursor != null)
      {
        localCursor.moveToFirst();
        while (!localCursor.isAfterLast())
        {
          localYonhapNewsContainer.NewsLink = localCursor.getString(localCursor.getColumnIndex("NEWS_LINK"));
          localYonhapNewsContainer.NewsTime = Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("NEWS_TIME")));
          localYonhapNewsContainer.NewsCategory = localCursor.getString(localCursor.getColumnIndex("NEWS_CATEGORY"));
          localYonhapNewsContainer.NewsTitle = localCursor.getString(localCursor.getColumnIndex("NEWS_TITLE"));
          localYonhapNewsContainer.NewsImageUrl = localCursor.getString(localCursor.getColumnIndex("NEWS_IMAGEURL"));
          localYonhapNewsContainer.NewsImageData = localCursor.getBlob(localCursor.getColumnIndex("NEWS_IMAGEDATA"));
          localYonhapNewsContainer.NewsContentText = localCursor.getString(localCursor.getColumnIndex("NEWS_CONTENTTEXT"));
          localYonhapNewsContainer.NewsPublishTime = Long.valueOf(localCursor.getLong(localCursor.getColumnIndex("NEWS_DATE")));
          Newss.add(new NewsItem(localYonhapNewsContainer));
          localCursor.moveToNext();
        }
        localCursor.close();
      }
      if (getInstance().updateCurrent(0, true) == null)
        getInstance().reset();
    }
  }

  public boolean isAvailable()
  {
    if ((isFlipAvailable()) || (isYonhapAvailable()));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isHavingNewsCache()
  {
    if ((Newss != null) && (Newss.size() > 0));
    for (int i = 1; ; i = 0)
      return i;
  }

  public void onFailure(FlipManager.ErrorMessage paramErrorMessage)
  {
    Log.d("NewsManager-flipNews", " onFailure ");
    if (paramErrorMessage == FlipManager.ErrorMessage.DUPLICATE_REQUEST)
      onSuccess(true);
    while (true)
    {
      this.mThread.interrupt();
      return;
      this.mError = paramErrorMessage;
    }
  }

  public void onSuccess(boolean paramBoolean)
  {
    Log.d("NewsManager-flipNews", " onSuccess " + paramBoolean);
    this.mThread.interrupt();
  }

  public void setWidgetListener(WidgetResponseReceivedListener paramWidgetResponseReceivedListener)
  {
    this.widgetListener = paramWidgetResponseReceivedListener;
  }

  public NewsItem updateCurrent(int paramInt)
  {
    return updateCurrent(paramInt, true);
  }

  private class ClientReceiver extends BroadcastReceiver
  {
    private ClientReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      String str = paramIntent.getAction();
      if ((str != null) && (str.equals("com.sec.android.daemonapp.ap.yonhapnews.intent.action.YONHAPNEWS_DATE_SYNC")))
      {
        Log.d("NewsManager", "Receive Yonhap Data sync");
        if ((NewsManager.this.mThread != null) && (NewsManager.this.mThread.isAlive()))
          NewsManager.this.mThread.interrupt();
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.news.NewsManager
 * JD-Core Version:    0.6.0
 */