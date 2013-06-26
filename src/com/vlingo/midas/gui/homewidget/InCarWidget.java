package com.vlingo.midas.gui.homewidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.RemoteViews;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.midas.VlingoApplication;
import com.vlingo.midas.drivingmode.DrivingModeUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class InCarWidget extends AppWidgetProvider
{
  private static final int[] EXAMPLE_ID;
  private static final int[] SRC_ID;
  private static final int[] SUB_HEAD_ID;
  private static boolean bRemoveAllAppWidget;
  private static int mIndex = 0;

  static
  {
    bRemoveAllAppWidget = false;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 2130838085;
    arrayOfInt1[1] = 2130838190;
    arrayOfInt1[2] = 2130838195;
    arrayOfInt1[3] = 2130838189;
    arrayOfInt1[4] = 2130838194;
    arrayOfInt1[5] = 2130838196;
    arrayOfInt1[6] = 2130838191;
    arrayOfInt1[7] = 2130838192;
    arrayOfInt1[8] = 2130838193;
    SRC_ID = arrayOfInt1;
    int[] arrayOfInt2 = new int[9];
    arrayOfInt2[0] = 2131362169;
    arrayOfInt2[1] = 2131362174;
    arrayOfInt2[2] = 2131362173;
    arrayOfInt2[3] = 2131362171;
    arrayOfInt2[4] = 2131362175;
    arrayOfInt2[5] = 2131362176;
    arrayOfInt2[6] = 2131362177;
    arrayOfInt2[7] = 2131362172;
    arrayOfInt2[8] = 2131362170;
    SUB_HEAD_ID = arrayOfInt2;
    int[] arrayOfInt3 = new int[9];
    arrayOfInt3[0] = 2131362063;
    arrayOfInt3[1] = 2131362067;
    arrayOfInt3[2] = 2131362086;
    arrayOfInt3[3] = 2131362070;
    arrayOfInt3[4] = 2131362072;
    arrayOfInt3[5] = 2131362081;
    arrayOfInt3[6] = 2131362076;
    arrayOfInt3[7] = 2131362084;
    arrayOfInt3[8] = 2131362090;
    EXAMPLE_ID = arrayOfInt3;
  }

  private static RemoteViews buildWidgetView(Context paramContext, boolean paramBoolean)
  {
    UserLoggingEngine.getInstance().landingPageViewed("widget-control");
    RemoteViews localRemoteViews = new RemoteViews(paramContext.getPackageName(), 2130903176);
    Intent localIntent = new Intent(paramContext, VlingoApplication.getInstance().getMainActivityClass());
    localIntent.putExtra("request_recognition", true);
    localRemoteViews.setOnClickPendingIntent(2131559045, PendingIntent.getActivity(paramContext, 0, localIntent, 0));
    localRemoteViews.setOnClickPendingIntent(2131559046, PendingIntent.getBroadcast(paramContext, 0, new Intent("com.vlingo.client.widget.action.driving_mode_change"), 0));
    if (paramBoolean)
    {
      localRemoteViews.setImageViewResource(2131559046, 2130838087);
      localRemoteViews.setImageViewResource(2131559047, 2130838077);
    }
    while (true)
    {
      localRemoteViews.setImageViewResource(2131559041, SRC_ID[mIndex]);
      int i = SUB_HEAD_ID[mIndex];
      localRemoteViews.setTextViewText(2131559042, paramContext.getResources().getString(i));
      localRemoteViews.setTextViewText(2131559044, "\"");
      localRemoteViews.setTextViewText(2131559043, changeKeywordsColor(paramContext, EXAMPLE_ID[mIndex]));
      PendingIntent localPendingIntent = PendingIntent.getBroadcast(paramContext, 0, new Intent(paramContext, InCarWidgetHelpTextReceiver.class), 0);
      localRemoteViews.setOnClickPendingIntent(2131559040, localPendingIntent);
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.setTimeInMillis(System.currentTimeMillis());
      localCalendar.add(13, 3);
      long l = localCalendar.getTimeInMillis();
      ((AlarmManager)paramContext.getSystemService("alarm")).set(1, l, localPendingIntent);
      return localRemoteViews;
      localRemoteViews.setImageViewResource(2131559046, 2130838086);
      localRemoteViews.setImageViewResource(2131559047, 2130838076);
    }
  }

  private static SpannableStringBuilder changeKeywordsColor(Context paramContext, int paramInt)
  {
    String str1 = paramContext.getResources().getString(paramInt) + "\"";
    ArrayList localArrayList = new ArrayList();
    while (str1.indexOf("[") != -1)
    {
      int i = str1.indexOf("[");
      int j = i + str1.substring(i).indexOf("]");
      String str2 = removeCharAt(str1, i);
      int k = j - 1;
      str1 = removeCharAt(str2, k);
      localArrayList.add(new SpanArea(i, k));
    }
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(str1);
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
    {
      SpanArea localSpanArea = (SpanArea)localIterator.next();
      localSpannableStringBuilder.setSpan(new ForegroundColorSpan(-10240), localSpanArea.getStart(), localSpanArea.getEnd(), 33);
    }
    localSpannableStringBuilder.setSpan(new ForegroundColorSpan(-10240), -1 + localSpannableStringBuilder.length(), localSpannableStringBuilder.length(), 33);
    return localSpannableStringBuilder;
  }

  public static int getIndex()
  {
    return mIndex;
  }

  public static int getMaxIndex()
  {
    return SRC_ID.length;
  }

  private static String removeCharAt(String paramString, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer(-1 + paramString.length());
    localStringBuffer.append(paramString.substring(0, paramInt)).append(paramString.substring(paramInt + 1));
    return localStringBuffer.toString();
  }

  public static void setIndex(int paramInt)
  {
    mIndex = paramInt;
  }

  public static void updateAllWidgets(Context paramContext, boolean paramBoolean)
  {
    if (bRemoveAllAppWidget);
    while (true)
    {
      return;
      RemoteViews localRemoteViews = buildWidgetView(paramContext, paramBoolean);
      AppWidgetManager.getInstance(paramContext).updateAppWidget(new ComponentName(paramContext, InCarWidget.class), localRemoteViews);
    }
  }

  public static void updateDrvToggleWidgets(Context paramContext, boolean paramBoolean)
  {
    if (bRemoveAllAppWidget)
      return;
    RemoteViews localRemoteViews = new RemoteViews(paramContext.getPackageName(), 2130903176);
    if (paramBoolean)
    {
      localRemoteViews.setImageViewResource(2131559046, 2130838087);
      localRemoteViews.setImageViewResource(2131559047, 2130838077);
    }
    while (true)
    {
      AppWidgetManager.getInstance(paramContext).updateAppWidget(new ComponentName(paramContext, InCarWidget.class), localRemoteViews);
      break;
      localRemoteViews.setImageViewResource(2131559046, 2130838086);
      localRemoteViews.setImageViewResource(2131559047, 2130838076);
    }
  }

  public void onDisabled(Context paramContext)
  {
    super.onDisabled(paramContext);
    bRemoveAllAppWidget = true;
    PendingIntent localPendingIntent = PendingIntent.getBroadcast(paramContext, 0, new Intent(paramContext, InCarWidgetHelpTextReceiver.class), 0);
    ((AlarmManager)paramContext.getSystemService("alarm")).cancel(localPendingIntent);
  }

  public void onEnabled(Context paramContext)
  {
    super.onEnabled(paramContext);
    setIndex(0);
    bRemoveAllAppWidget = false;
  }

  public void onUpdate(Context paramContext, AppWidgetManager paramAppWidgetManager, int[] paramArrayOfInt)
  {
    super.onUpdate(paramContext, paramAppWidgetManager, paramArrayOfInt);
    updateAllWidgets(paramContext, DrivingModeUtil.isDrivingModeEnabled(paramContext));
  }

  private static class SpanArea
  {
    private int end;
    private int start;

    SpanArea(int paramInt1, int paramInt2)
    {
      this.start = paramInt1;
      this.end = paramInt2;
    }

    int getEnd()
    {
      return this.end;
    }

    int getStart()
    {
      return this.start;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.homewidget.InCarWidget
 * JD-Core Version:    0.6.0
 */