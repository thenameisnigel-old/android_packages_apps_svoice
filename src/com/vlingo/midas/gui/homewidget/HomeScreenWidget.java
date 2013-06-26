package com.vlingo.midas.gui.homewidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.Html;
import android.widget.RemoteViews;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.midas.VlingoApplication;
import java.util.Calendar;

public class HomeScreenWidget extends AppWidgetProvider
{
  private static final int[] EXAMPLE_ID;
  private static final int[] SRC_ID;
  private static final int[] SUB_HEAD_ID;
  private static boolean isRemoveAllAppWidget;
  private static int mIndex = 0;

  static
  {
    isRemoveAllAppWidget = false;
    int[] arrayOfInt1 = new int[17];
    arrayOfInt1[0] = 2130838085;
    arrayOfInt1[1] = 2130838190;
    arrayOfInt1[2] = 2130838192;
    arrayOfInt1[3] = 2130838189;
    arrayOfInt1[4] = 2130838194;
    arrayOfInt1[5] = 2130838191;
    arrayOfInt1[6] = 2130838196;
    arrayOfInt1[7] = 2130838195;
    arrayOfInt1[8] = 2130838193;
    arrayOfInt1[9] = 2130838193;
    arrayOfInt1[10] = 2130838197;
    arrayOfInt1[11] = 2130838188;
    arrayOfInt1[12] = 2130838193;
    arrayOfInt1[13] = 2130838193;
    arrayOfInt1[14] = 2130838193;
    arrayOfInt1[15] = 2130838193;
    arrayOfInt1[16] = 2130838193;
    SRC_ID = arrayOfInt1;
    int[] arrayOfInt2 = new int[17];
    arrayOfInt2[0] = 2131362278;
    arrayOfInt2[1] = 2131362174;
    arrayOfInt2[2] = 2131362320;
    arrayOfInt2[3] = 2131362096;
    arrayOfInt2[4] = 2131362282;
    arrayOfInt2[5] = 2131362098;
    arrayOfInt2[6] = 2131362176;
    arrayOfInt2[7] = 2131362283;
    arrayOfInt2[8] = 2131362102;
    arrayOfInt2[9] = 2131362158;
    arrayOfInt2[10] = 2131362104;
    arrayOfInt2[11] = 2131362094;
    arrayOfInt2[12] = 2131362159;
    arrayOfInt2[13] = 2131362161;
    arrayOfInt2[14] = 2131362163;
    arrayOfInt2[15] = 2131362165;
    arrayOfInt2[16] = 2131362167;
    SUB_HEAD_ID = arrayOfInt2;
    int[] arrayOfInt3 = new int[16];
    arrayOfInt3[0] = 2131362093;
    arrayOfInt3[1] = 2131362111;
    arrayOfInt3[2] = 2131362101;
    arrayOfInt3[3] = 2131362097;
    arrayOfInt3[4] = 2131362106;
    arrayOfInt3[5] = 2131362099;
    arrayOfInt3[6] = 2131362109;
    arrayOfInt3[7] = 2131362107;
    arrayOfInt3[8] = 2131362103;
    arrayOfInt3[9] = 2131362105;
    arrayOfInt3[10] = 2131362095;
    arrayOfInt3[11] = 2131362160;
    arrayOfInt3[12] = 2131362162;
    arrayOfInt3[13] = 2131362164;
    arrayOfInt3[14] = 2131362154;
    arrayOfInt3[15] = 2131362168;
    EXAMPLE_ID = arrayOfInt3;
  }

  private static RemoteViews buildWidgetView(Context paramContext)
  {
    UserLoggingEngine.getInstance().landingPageViewed("widget-control");
    RemoteViews localRemoteViews = new RemoteViews(paramContext.getPackageName(), 2130903175);
    Intent localIntent = new Intent(paramContext, VlingoApplication.getInstance().getMainActivityClass());
    localIntent.putExtra("request_recognition", true);
    localRemoteViews.setOnClickPendingIntent(2131559039, PendingIntent.getActivity(paramContext, 0, localIntent, 0));
    localRemoteViews.setImageViewResource(2131559041, SRC_ID[mIndex]);
    int i = SUB_HEAD_ID[mIndex];
    localRemoteViews.setTextViewText(2131559042, paramContext.getResources().getString(i));
    int j = EXAMPLE_ID[mIndex];
    localRemoteViews.setTextViewText(2131559043, Html.fromHtml(paramContext.getResources().getString(j).replace("\"", "")));
    PendingIntent localPendingIntent = PendingIntent.getBroadcast(paramContext, 0, new Intent(paramContext, HomeWidgetHelpTextReceiver.class), 0);
    localRemoteViews.setOnClickPendingIntent(2131559040, localPendingIntent);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(System.currentTimeMillis());
    localCalendar.add(13, 3);
    long l = localCalendar.getTimeInMillis();
    ((AlarmManager)paramContext.getSystemService("alarm")).set(1, l, localPendingIntent);
    return localRemoteViews;
  }

  public static int getIndex()
  {
    return mIndex;
  }

  public static int getMaxIndex()
  {
    return SRC_ID.length;
  }

  public static void setIndex(int paramInt)
  {
    mIndex = paramInt;
  }

  public static void updateAllWidgets(Context paramContext)
  {
    if (isRemoveAllAppWidget);
    while (true)
    {
      return;
      RemoteViews localRemoteViews = buildWidgetView(paramContext);
      AppWidgetManager.getInstance(paramContext).updateAppWidget(new ComponentName(paramContext, HomeScreenWidget.class), localRemoteViews);
    }
  }

  public void onDisabled(Context paramContext)
  {
    super.onDisabled(paramContext);
    isRemoveAllAppWidget = true;
    PendingIntent localPendingIntent = PendingIntent.getBroadcast(paramContext, 0, new Intent(paramContext, InCarWidgetHelpTextReceiver.class), 0);
    ((AlarmManager)paramContext.getSystemService("alarm")).cancel(localPendingIntent);
  }

  public void onEnabled(Context paramContext)
  {
    super.onEnabled(paramContext);
    setIndex(0);
    isRemoveAllAppWidget = false;
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    super.onReceive(paramContext, paramIntent);
  }

  public void onUpdate(Context paramContext, AppWidgetManager paramAppWidgetManager, int[] paramArrayOfInt)
  {
    super.onUpdate(paramContext, paramAppWidgetManager, paramArrayOfInt);
    updateAllWidgets(paramContext);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.homewidget.HomeScreenWidget
 * JD-Core Version:    0.6.0
 */