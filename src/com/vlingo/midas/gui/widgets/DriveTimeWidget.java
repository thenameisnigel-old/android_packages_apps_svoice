package com.vlingo.midas.gui.widgets;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.schedule.DateUtil;
import com.vlingo.midas.settings.MidasSettings;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DriveTimeWidget extends BargeInWidget<Object>
{
  private static final String M12 = "hhmm";
  private static final String M24 = "kkmm";
  private static final String WAKE_LOCK_NAME = "VlingoScreenSaver";
  private String day = "";
  private int hour = 0;
  private TextView mAmPm;
  private TextView mAmPmText;
  private boolean mAttached = false;
  private int[] mBigNumbers_night;
  private Calendar mCalendar;
  private TextView mColonView;
  private final Context mContext;
  private TextView mDate;
  private Date mDate1;
  private CharSequence mFormat;
  private final Handler mHandler = new Handler();
  private View mHour1;
  private View mHour2;
  private final BroadcastReceiver mIntentReceiver = new DriveTimeWidget.1(this);
  private boolean mLive = true;
  private View mMin1;
  private View mMin2;
  private String mStrDate;
  private int min = 0;
  private PowerManager powerManager;
  private PowerManager.WakeLock wakeLock;

  public DriveTimeWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
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
    this.mContext = paramContext;
  }

  static boolean get24HourMode(Context paramContext)
  {
    return DateFormat.is24HourFormat(paramContext);
  }

  private void onSetTime()
  {
    this.mColonView.setText(":");
  }

  private void setDataText(Date paramDate)
  {
    this.mStrDate = DateUtil.formatWidgetDate(paramDate, MidasSettings.getCurrentLocale());
    this.mDate.setText(this.mStrDate);
  }

  private void setDateFormat()
  {
    if (get24HourMode(getContext()));
    for (String str = "kkmm"; ; str = "hhmm")
    {
      this.mFormat = str;
      setShowAmPm(this.mFormat.equals("hhmm"));
      return;
    }
  }

  private void setupView()
  {
    this.mHour1 = findViewById(2131558506);
    this.mHour2 = findViewById(2131558507);
    this.mMin1 = findViewById(2131558509);
    this.mMin2 = findViewById(2131558510);
    this.mDate = ((TextView)findViewById(2131558512));
    this.mAmPm = ((TextView)findViewById(2131558511));
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
      return;
      int j = 0;
    }
  }

  public void initialize(Object paramObject, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    onTimeChanged();
    Locale localLocale = getResources().getConfiguration().locale;
    localLocale.getDisplayCountry();
    Calendar localCalendar = Calendar.getInstance();
    if (localLocale.getLanguage().equals(Locale.KOREAN.getLanguage()))
    {
      localCalendar.setTimeInMillis(localCalendar.getTimeInMillis());
      this.day += localCalendar.getDisplayName(2, 2, localLocale);
      this.day = (this.day + " " + localCalendar.get(5) + getResources().getString(2131362010));
      this.day = (this.day + " " + localCalendar.getDisplayName(7, 2, localLocale));
    }
    while (true)
    {
      TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
      localTimeZone.getDisplayName(localTimeZone.inDaylightTime(new Date()), 1);
      onSetTime();
      this.wakeLock = this.powerManager.newWakeLock(6, "VlingoScreenSaver");
      try
      {
        if ((this.wakeLock != null) && (!this.wakeLock.isHeld()))
          this.wakeLock.acquire();
        label246: return;
        localCalendar.setTimeInMillis(localCalendar.getTimeInMillis());
        this.day += localCalendar.getDisplayName(7, 1, localLocale);
        this.day = (this.day + " " + localCalendar.get(5));
        this.day = (this.day + " " + localCalendar.getDisplayName(2, 1, localLocale));
      }
      catch (Exception localException)
      {
        break label246;
      }
    }
  }

  public boolean isTranslated()
  {
    return false;
  }

  public boolean isWidgetReplaceable()
  {
    return false;
  }

  public void minimize(boolean paramBoolean)
  {
    if (paramBoolean);
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (!this.mAttached)
    {
      this.mAttached = true;
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.TIME_TICK");
      localIntentFilter.addAction("android.intent.action.TIME_SET");
      localIntentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
      localIntentFilter.addAction("android.intent.action.DATE_CHANGED");
      getContext().registerReceiver(this.mIntentReceiver, localIntentFilter, null, this.mHandler);
    }
    onTimeChanged();
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.mAttached)
    {
      getContext().unregisterReceiver(this.mIntentReceiver);
      this.mAttached = false;
    }
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.mAmPmText = ((TextView)findViewById(2131558888));
    this.mColonView = ((TextView)findViewById(2131558883));
    setupView();
    this.mCalendar = Calendar.getInstance();
    setDateFormat();
    this.powerManager = ((PowerManager)this.mContext.getSystemService("power"));
  }

  public void onTimeChanged()
  {
    updateTime();
  }

  public void processSystemMessage(Intent paramIntent)
  {
    if (paramIntent.getAction().equals("android.intent.action.TIMEZONE_CHANGED"))
      paramIntent.getStringExtra("time-zone");
    onTimeChanged();
    onSetTime();
    invalidate();
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
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DriveTimeWidget
 * JD-Core Version:    0.6.0
 */