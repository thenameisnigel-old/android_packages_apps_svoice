package com.vlingo.midas.gui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.TextView;
import com.vlingo.core.internal.schedule.DateUtil;
import com.vlingo.midas.settings.MidasSettings;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class SaveScreen extends Activity
{
  private static final String M12 = "hhmm";
  private static final String M24 = "kkmm";
  private static final String WAKE_LOCK_NAME = "VlingoScreenSaver";
  private String ampm;
  View cradle_saver;
  private String date;
  private TextView mAmPm;
  private int[] mBigNumbers_night;
  public BroadcastReceiver mBroadcastReceiver = new SaveScreen.2(this);
  private Calendar mCalendar;
  private TextView mDate;
  private Date mDate1;
  private String mFormat;
  private View mHour1;
  private View mHour2;
  private boolean mLive = true;
  private View mMin1;
  private View mMin2;
  private Random mRNG;
  View mSaverView;
  private TextView mTime;
  private PowerManager powerManager;
  private boolean saveScreenMode = false;
  private String time;
  private PowerManager.WakeLock wakeLock;

  public SaveScreen()
  {
    int[] arrayOfInt = new int[10];
    arrayOfInt[0] = 2130837574;
    arrayOfInt[1] = 2130837575;
    arrayOfInt[2] = 2130837576;
    arrayOfInt[3] = 2130837577;
    arrayOfInt[4] = 2130837578;
    arrayOfInt[5] = 2130837579;
    arrayOfInt[6] = 2130837580;
    arrayOfInt[7] = 2130837581;
    arrayOfInt[8] = 2130837582;
    arrayOfInt[9] = 2130837583;
    this.mBigNumbers_night = arrayOfInt;
  }

  static boolean get24HourMode(Context paramContext)
  {
    return DateFormat.is24HourFormat(paramContext);
  }

  private void moveScreenSaver()
  {
    moveScreenSaverTo(-1, -1);
  }

  private void moveScreenSaverTo(int paramInt1, int paramInt2)
  {
    if (!this.saveScreenMode);
    while (true)
    {
      return;
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      if (this.mSaverView == null)
        this.mSaverView = findViewById(2131558505);
      if ((paramInt1 < 0) || (paramInt2 < 0))
      {
        int i = this.mSaverView.getMeasuredWidth();
        int j = this.mSaverView.getMeasuredHeight();
        paramInt1 = (int)(this.mRNG.nextFloat() * (localDisplayMetrics.widthPixels - i));
        paramInt2 = (int)(this.mRNG.nextFloat() * (localDisplayMetrics.heightPixels - j));
      }
      this.mSaverView.setLayoutParams(new AbsoluteLayout.LayoutParams(-2, -2, paramInt1, paramInt2));
    }
  }

  private void setDateFormat()
  {
    if (get24HourMode(getBaseContext()));
    for (String str = "kkmm"; ; str = "hhmm")
    {
      this.mFormat = str;
      setShowAmPm(this.mFormat.equals("hhmm"));
      return;
    }
  }

  private void updateTime()
  {
    int i = 1;
    if (this.mLive)
      this.mCalendar.setTimeInMillis(System.currentTimeMillis());
    CharSequence localCharSequence = DateFormat.format(this.mFormat, this.mCalendar);
    this.mDate1 = new Date();
    setDateFormat();
    setDataText(this.mDate1);
    this.mHour1.setBackgroundResource(this.mBigNumbers_night[java.lang.Integer.parseInt(localCharSequence.toString().substring(0, i))]);
    this.mHour2.setBackgroundResource(this.mBigNumbers_night[java.lang.Integer.parseInt(localCharSequence.toString().substring(i, 2))]);
    this.mMin1.setBackgroundResource(this.mBigNumbers_night[java.lang.Integer.parseInt(localCharSequence.toString().substring(2, 3))]);
    this.mMin2.setBackgroundResource(this.mBigNumbers_night[java.lang.Integer.parseInt(localCharSequence.toString().substring(3, 4))]);
    if (this.mCalendar.get(9) == 0);
    while (true)
    {
      setIsMorning(i);
      if (this.saveScreenMode)
        moveScreenSaver();
      return;
      int j = 0;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903044);
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.TIME_SET");
    localIntentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
    localIntentFilter.addAction("android.intent.action.DATE_CHANGED");
    localIntentFilter.addAction("android.intent.action.TIME_TICK");
    localIntentFilter.addAction("android.intent.action.DOCK_EVENT");
    this.mCalendar = Calendar.getInstance();
    this.mRNG = new Random();
    setupView();
    setDateFormat();
    updateTime();
    this.powerManager = ((PowerManager)getSystemService("power"));
    registerReceiver(this.mBroadcastReceiver, localIntentFilter);
    saveScreen();
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.saveScreenMode = false;
    if (this.mBroadcastReceiver != null)
      unregisterReceiver(this.mBroadcastReceiver);
  }

  protected void onPause()
  {
    super.onPause();
    try
    {
      if ((this.wakeLock != null) && (this.wakeLock.isHeld()))
      {
        this.wakeLock.release();
        this.wakeLock = null;
      }
      label33: return;
    }
    catch (Exception localException)
    {
      break label33;
    }
  }

  protected void onResume()
  {
    super.onResume();
    this.wakeLock = this.powerManager.newWakeLock(6, "VlingoScreenSaver");
    try
    {
      if ((this.wakeLock != null) && (!this.wakeLock.isHeld()))
        this.wakeLock.acquire();
      label43: return;
    }
    catch (Exception localException)
    {
      break label43;
    }
  }

  public void saveScreen()
  {
    setContentView(2130903044);
    findViewById(2131558505);
    this.saveScreenMode = true;
    int[] arrayOfInt = new int[2];
    getWindow().addFlags(1024);
    getWindow().clearFlags(2048);
    setupView();
    updateTime();
    this.cradle_saver.setOnTouchListener(new SaveScreen.1(this));
    moveScreenSaverTo(arrayOfInt[0], arrayOfInt[1]);
  }

  public void setDataText(Date paramDate)
  {
    this.date = DateUtil.formatWidgetDate(paramDate, MidasSettings.getCurrentLocale());
    this.mDate.setText(this.date);
  }

  void setIsMorning(boolean paramBoolean)
  {
    TextView localTextView = this.mAmPm;
    if (paramBoolean);
    for (int i = 2131362516; ; i = 2131362520)
    {
      localTextView.setText(i);
      return;
    }
  }

  void setShowAmPm(boolean paramBoolean)
  {
    TextView localTextView = this.mAmPm;
    if (paramBoolean);
    for (int i = 0; ; i = 8)
    {
      localTextView.setVisibility(i);
      return;
    }
  }

  public void setupView()
  {
    this.cradle_saver = findViewById(2131558504);
    this.mHour1 = findViewById(2131558506);
    this.mHour2 = findViewById(2131558507);
    this.mMin1 = findViewById(2131558509);
    this.mMin2 = findViewById(2131558510);
    this.mDate = ((TextView)findViewById(2131558512));
    this.mAmPm = ((TextView)findViewById(2131558511));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.SaveScreen
 * JD-Core Version:    0.6.0
 */