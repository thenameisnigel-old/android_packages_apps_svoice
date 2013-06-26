package com.vlingo.midas.gui.widgets;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.DecoratorType;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.schedule.DateUtil;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.gui.timer.NumberImageView;
import com.vlingo.midas.gui.timer.TimeImageView;
import java.util.Date;

public class TimerWidget extends BargeInWidget<String>
  implements View.OnClickListener, View.OnTouchListener, View.OnFocusChangeListener
{
  private static final long COUNT_DOWN_INTERVAL = 1000L;
  private static final long DEFAULT_TIME = 60000L;
  public static final float JAPAN_FONT_SIZE = 18.34F;
  private static final int RESET = 3;
  private static final int STARTED = 1;
  private static final int STOPPED = 2;
  private static final String TIMER_CURRENT_STATE = "timer_current_state";
  private static final String TIMER_ELAPSED_REALTIME = "timer_elapsed_realtime";
  private static final String TIMER_INPUTMILLIS = "timer_inputMillis";
  public static final String TIMER_INPUT_TIME = ".timer.input.time";
  public static final int TIMER_NOTI_ID = 84637;
  public static final String TIMER_PLAYSOUND = ".timer.playsound";
  public static final String TIMER_POPUP = ".timer.popup";
  private static final String TIMER_REMAINMILLIS = "timer_remainMillis";
  private static boolean canceled = false;
  static int currentInputPosition;
  private static long inputMillis;
  static boolean isFirst = true;
  static boolean isViewChanged = false;
  static NumberImageView mHourPostfix;
  static NumberImageView mHourPrefix;
  static NumberImageView mMinutePostfix;
  static NumberImageView mMinutePrefix;
  static NumberImageView mSecondPostfix;
  static NumberImageView mSecondPrefix;
  private static long originalMillis;
  static boolean pause;
  static int pressedTimeUnit;
  private static long remainMillis;
  public static Drawable[] selectedTimeNumber;
  static int state;
  public static Drawable[] timeNumber;
  private static CountDownTimer timer;
  private AlarmManager alarmManager;
  private Context context;
  private int hour;
  private WidgetActionListener listener;
  private TextView mHeadTextHour;
  private TextView mHeadTextMin;
  private TextView mHeadTextSec;
  private TimeImageView mHourBackground;
  private TimeImageView mMinuteBackground;
  private TimeImageView mSecondBackground;
  private int minute;
  private Resources res = null;
  private Button resetBtn;
  private Button restartBtn;
  private int second;
  private Button startBtn;
  private Button stopBtn;
  private RelativeLayout timerContainer;
  private Intent timerIntent;
  private PendingIntent timerSender;
  private RelativeLayout widgetTimer;

  static
  {
    currentInputPosition = 0;
  }

  public TimerWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    this.res = getResources();
  }

  public static void cancelTimer()
  {
    timer.cancel();
  }

  private void chkInputData()
  {
  }

  private long convertMillis(int paramInt1, int paramInt2, int paramInt3)
  {
    return 3600000 * paramInt1 + 60000 * paramInt2 + paramInt3 * 1000;
  }

  private String getInputTime()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Integer.valueOf(this.hour);
    localStringBuilder.append(String.format("%02d", arrayOfObject1)).append(':');
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = Integer.valueOf(this.minute);
    localStringBuilder.append(String.format("%02d", arrayOfObject2)).append(':');
    Object[] arrayOfObject3 = new Object[1];
    arrayOfObject3[0] = Integer.valueOf(this.second);
    localStringBuilder.append(String.format("%02d", arrayOfObject3));
    return localStringBuilder.toString();
  }

  private CharSequence getTimerAccessibilityString()
  {
    String str = "";
    if (this.hour > 0)
    {
      StringBuilder localStringBuilder3 = new StringBuilder().append(str);
      Resources localResources4 = this.res;
      int k = this.hour;
      Object[] arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = Integer.valueOf(this.hour);
      str = localResources4.getQuantityString(2131492869, k, arrayOfObject4);
    }
    if (this.minute > 0)
    {
      StringBuilder localStringBuilder2 = new StringBuilder().append(str);
      Resources localResources3 = this.res;
      int j = this.minute;
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = Integer.valueOf(this.minute);
      str = localResources3.getQuantityString(2131492870, j, arrayOfObject3);
    }
    if (this.second > 0)
    {
      StringBuilder localStringBuilder1 = new StringBuilder().append(str);
      Resources localResources2 = this.res;
      int i = this.second;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(this.second);
      str = localResources2.getQuantityString(2131492871, i, arrayOfObject2);
    }
    Resources localResources1 = this.res;
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = str;
    return localResources1.getString(2131362716, arrayOfObject1);
  }

  private void initAlarm()
  {
    if (this.timerIntent != null);
    while (true)
    {
      return;
      this.timerIntent = new Intent();
      this.timerIntent.setAction(".timer.playsound");
      this.timerSender = PendingIntent.getBroadcast(this.context, 0, this.timerIntent, 0);
      this.alarmManager = ((AlarmManager)this.context.getSystemService("alarm"));
    }
  }

  private void initTimeView()
  {
    this.hour = (int)(remainMillis / 3600000L);
    int i = (int)(remainMillis - 3600000 * this.hour);
    this.minute = (i / 60000);
    this.second = ((i - 60000 * this.minute) / 1000);
    setTimeToView(mHourPrefix, mHourPostfix, this.hour);
    setTimeToView(mMinutePrefix, mMinutePostfix, this.minute);
    setTimeToView(mSecondPrefix, mSecondPostfix, this.second);
  }

  private void initTimerView()
  {
    this.mHourBackground = ((TimeImageView)findViewById(2131559150));
    this.mMinuteBackground = ((TimeImageView)findViewById(2131559151));
    this.mSecondBackground = ((TimeImageView)findViewById(2131559152));
    this.mHourBackground.setOnTouchListener(this);
    this.mHourBackground.setOnFocusChangeListener(this);
    this.mMinuteBackground.setOnTouchListener(this);
    this.mMinuteBackground.setOnFocusChangeListener(this);
    this.mSecondBackground.setOnTouchListener(this);
    this.mSecondBackground.setOnFocusChangeListener(this);
    mHourPrefix = (NumberImageView)findViewById(2131559153);
    mHourPostfix = (NumberImageView)findViewById(2131559154);
    mMinutePrefix = (NumberImageView)findViewById(2131559156);
    mMinutePostfix = (NumberImageView)findViewById(2131559157);
    mSecondPrefix = (NumberImageView)findViewById(2131559159);
    mSecondPostfix = (NumberImageView)findViewById(2131559160);
    this.mHeadTextHour = ((TextView)findViewById(2131559146));
    this.mHeadTextMin = ((TextView)findViewById(2131559147));
    this.mHeadTextSec = ((TextView)findViewById(2131559148));
  }

  private void launchTimer()
  {
    Intent localIntent = new Intent("com.sec.android.app.clockpackage.TIMER_ACTION");
    localIntent.setClassName("com.sec.android.app.clockpackage", "com.sec.android.app.clockpackage.ClockPackage");
    this.context.startActivity(localIntent);
  }

  private void preLoadImage()
  {
    if (timeNumber != null);
    while (true)
    {
      return;
      timeNumber = new Drawable[10];
      timeNumber[0] = getResources().getDrawable(2130837975);
      timeNumber[1] = getResources().getDrawable(2130837977);
      timeNumber[2] = getResources().getDrawable(2130837979);
      timeNumber[3] = getResources().getDrawable(2130837981);
      timeNumber[4] = getResources().getDrawable(2130837983);
      timeNumber[5] = getResources().getDrawable(2130837985);
      timeNumber[6] = getResources().getDrawable(2130837987);
      timeNumber[7] = getResources().getDrawable(2130837989);
      timeNumber[8] = getResources().getDrawable(2130837991);
      timeNumber[9] = getResources().getDrawable(2130837993);
      if (selectedTimeNumber != null)
        continue;
      selectedTimeNumber = new Drawable[10];
      selectedTimeNumber[0] = getResources().getDrawable(2130837976);
      selectedTimeNumber[1] = getResources().getDrawable(2130837978);
      selectedTimeNumber[2] = getResources().getDrawable(2130837980);
      selectedTimeNumber[3] = getResources().getDrawable(2130837982);
      selectedTimeNumber[4] = getResources().getDrawable(2130837984);
      selectedTimeNumber[5] = getResources().getDrawable(2130837986);
      selectedTimeNumber[6] = getResources().getDrawable(2130837988);
      selectedTimeNumber[7] = getResources().getDrawable(2130837990);
      selectedTimeNumber[8] = getResources().getDrawable(2130837992);
      selectedTimeNumber[9] = getResources().getDrawable(2130837994);
    }
  }

  private void pressNumberViews(View[] paramArrayOfView)
  {
    int i = paramArrayOfView.length;
    for (int j = 0; j < i; j++)
      paramArrayOfView[j].setPressed(true);
  }

  private void releaseNumberViews(View[] paramArrayOfView)
  {
    int i = paramArrayOfView.length;
    for (int j = 0; j < i; j++)
      paramArrayOfView[j].setPressed(false);
  }

  private void reset()
  {
    state = 3;
    long l = originalMillis;
    inputMillis = l;
    remainMillis = l;
    resetTime(true);
    this.alarmManager.cancel(this.timerSender);
    try
    {
      if (timer != null)
        timer.cancel();
      updateTimerApp();
      setViewState();
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      while (true)
        localNullPointerException.printStackTrace();
    }
  }

  private void resetTime(boolean paramBoolean)
  {
    if ((!paramBoolean) && (isViewChanged));
    while (true)
    {
      return;
      initTimeView();
      pressedTimeUnit = 2131559151;
    }
  }

  private void restart()
  {
    state = 1;
    this.alarmManager.cancel(this.timerSender);
    try
    {
      if (timer != null)
        timer.cancel();
      setTimer(remainMillis);
      timer.start();
      setAlarm();
      setViewState();
      startTimerApp();
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      while (true)
        localNullPointerException.printStackTrace();
    }
  }

  private void setAlarm()
  {
    this.timerIntent.putExtra("EXTRA_HOUR", this.hour);
    this.timerIntent.putExtra("EXTRA_MINUTES", this.minute);
    this.timerIntent.putExtra("EXTRA_SECOND", this.second);
    this.timerSender = PendingIntent.getBroadcast(this.context, 0, this.timerIntent, 134217728);
    this.alarmManager.set(2, SystemClock.elapsedRealtime() + remainMillis, this.timerSender);
  }

  private static void setInputTime(Context paramContext, String paramString)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("TIMER", 0).edit();
    localEditor.putString(".timer.input.time", paramString);
    localEditor.commit();
  }

  private void setStartedViewState(boolean paramBoolean)
  {
  }

  private void setStopedViewState(boolean paramBoolean)
  {
  }

  private void setTimeToView(NumberImageView paramNumberImageView1, NumberImageView paramNumberImageView2, int paramInt)
  {
    paramNumberImageView1.setNumber(paramInt / 10);
    paramNumberImageView2.setNumber(paramInt % 10);
  }

  private void setTimer(long paramLong)
  {
    timer = new TimerWidget.1(this, paramLong, 1000L);
  }

  private void setViewState()
  {
    switch (state)
    {
    default:
      setStartedViewState(false);
      if (isFirst)
      {
        inputMillis = 60000L;
        remainMillis = 60000L;
        pressedTimeUnit = 2131559151;
        isFirst = false;
      }
      updateTimeViews(pressedTimeUnit);
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      initTimeView();
      chkInputData();
      return;
      setStartedViewState(true);
      continue;
      setStopedViewState(true);
      continue;
      setStartedViewState(false);
      remainMillis = inputMillis;
      resetTime(false);
    }
  }

  private boolean shouldSkipEvent()
  {
    int i = 1;
    if ((state == i) || (state == 2));
    while (true)
    {
      return i;
      i = 0;
    }
  }

  private void startTimerApp()
  {
    Intent localIntent = new Intent("com.sec.android.app.clockpackage.timer.TIMER_START");
    localIntent.putExtra("Hour", this.hour);
    localIntent.putExtra("Minute", this.minute);
    localIntent.putExtra("Second", this.second);
    this.context.sendBroadcast(localIntent);
  }

  private void stop()
  {
    state = 2;
    this.alarmManager.cancel(this.timerSender);
    if (timer != null)
      timer.cancel();
    updateTimerApp();
    setViewState();
  }

  private void updateTime()
  {
    int i = (int)(remainMillis / 3600000L);
    int j = (int)(remainMillis - 3600000 * i);
    int k = j / 60000;
    int m = (j - k * 60000) / 1000;
    if (m != this.second)
    {
      this.second = m;
      setTimeToView(mSecondPrefix, mSecondPostfix, m);
      if (k != this.minute)
      {
        this.minute = k;
        setTimeToView(mMinutePrefix, mMinutePostfix, k);
        if (i != this.hour)
        {
          this.hour = i;
          setTimeToView(mHourPrefix, mHourPostfix, i);
        }
      }
    }
  }

  private void updateTimeViews(int paramInt)
  {
    pressedTimeUnit = paramInt;
    if (shouldSkipEvent())
      return;
    switch (paramInt)
    {
    default:
    case 2131559150:
    case 2131559151:
    case 2131559152:
    }
    while (true)
    {
      isViewChanged = true;
      break;
      View[] arrayOfView5 = new View[3];
      arrayOfView5[0] = this.mHourBackground;
      arrayOfView5[1] = mHourPrefix;
      arrayOfView5[2] = mHourPostfix;
      pressNumberViews(arrayOfView5);
      View[] arrayOfView6 = new View[6];
      arrayOfView6[0] = this.mMinuteBackground;
      arrayOfView6[1] = mMinutePrefix;
      arrayOfView6[2] = mMinutePostfix;
      arrayOfView6[3] = this.mSecondBackground;
      arrayOfView6[4] = mSecondPrefix;
      arrayOfView6[5] = mSecondPostfix;
      releaseNumberViews(arrayOfView6);
      continue;
      View[] arrayOfView3 = new View[3];
      arrayOfView3[0] = this.mMinuteBackground;
      arrayOfView3[1] = mMinutePrefix;
      arrayOfView3[2] = mMinutePostfix;
      pressNumberViews(arrayOfView3);
      View[] arrayOfView4 = new View[6];
      arrayOfView4[0] = this.mHourBackground;
      arrayOfView4[1] = mHourPrefix;
      arrayOfView4[2] = mHourPostfix;
      arrayOfView4[3] = this.mSecondBackground;
      arrayOfView4[4] = mSecondPrefix;
      arrayOfView4[5] = mSecondPostfix;
      releaseNumberViews(arrayOfView4);
      continue;
      View[] arrayOfView1 = new View[3];
      arrayOfView1[0] = this.mSecondBackground;
      arrayOfView1[1] = mSecondPrefix;
      arrayOfView1[2] = mSecondPostfix;
      pressNumberViews(arrayOfView1);
      View[] arrayOfView2 = new View[6];
      arrayOfView2[0] = this.mHourBackground;
      arrayOfView2[1] = mHourPrefix;
      arrayOfView2[2] = mHourPostfix;
      arrayOfView2[3] = this.mMinuteBackground;
      arrayOfView2[4] = mMinutePrefix;
      arrayOfView2[5] = mMinutePostfix;
      releaseNumberViews(arrayOfView2);
    }
  }

  private void updateTimerApp()
  {
    Intent localIntent1 = new Intent("com.sec.android.app.clockpackage.timer.TIMER_RESET");
    this.context.sendBroadcast(localIntent1);
    Intent localIntent2 = new Intent("com.sec.android.app.clockpackage.timer.TIMER_SET_TIME");
    localIntent2.putExtra("Hour", this.hour);
    localIntent2.putExtra("Minute", this.minute);
    localIntent2.putExtra("Second", this.second);
    this.context.sendBroadcast(localIntent2);
  }

  public void initialize(String paramString, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.listener = paramWidgetActionListener;
    if ((paramWidgetDecorator != null) && (paramWidgetDecorator.has(DecoratorType.TimerCancelCmd)))
    {
      canceled = true;
      stop();
      this.widgetTimer.setVisibility(8);
    }
    while (true)
    {
      return;
      canceled = false;
      if (!StringUtils.isNullOrWhiteSpace(paramString));
      try
      {
        Date localDate = DateUtil.getTimeFromCanonicalTimeString(paramString);
        long l = -60000 * localDate.getTimezoneOffset() + localDate.getTime();
        inputMillis = l;
        remainMillis = l;
        originalMillis = inputMillis;
        initTimeView();
        updateTimerApp();
        start();
        if ((paramWidgetDecorator == null) || (paramWidgetDecorator.has(DecoratorType.TimerResetCmd)))
        {
          reset();
          Settings.setBoolean("reset_screen_off_after_launch_timer", false);
          this.timerContainer.setContentDescription(getTimerAccessibilityString());
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          localException.printStackTrace();
          continue;
          if (paramWidgetDecorator.has(DecoratorType.TimerStartCmd))
          {
            start();
            continue;
          }
          if (paramWidgetDecorator.has(DecoratorType.TimerStopCmd))
          {
            stop();
            continue;
          }
          if (!paramWidgetDecorator.has(DecoratorType.TimerRestartCmd))
            continue;
          restart();
        }
      }
    }
  }

  public boolean isTranslated()
  {
    return false;
  }

  public boolean isWidgetReplaceable()
  {
    if (!canceled);
    for (int i = 1; ; i = 0)
      return i;
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131559144:
    }
    while (true)
    {
      return;
      launchTimer();
    }
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.timerContainer = ((RelativeLayout)findViewById(2131559144));
    this.widgetTimer = ((RelativeLayout)findViewById(2131559143));
    preLoadImage();
    initTimerView();
    initAlarm();
    initTimeView();
    updateTime();
  }

  public void onFocusChange(View paramView, boolean paramBoolean)
  {
  }

  public boolean onRestoreInstanceState(Bundle paramBundle)
  {
    int i = 1;
    state = paramBundle.getInt("timer_current_state");
    long l = paramBundle.getLong("timer_elapsed_realtime");
    inputMillis = paramBundle.getLong("timer_inputMillis");
    if (state == i)
      remainMillis = paramBundle.getLong("timer_remainMillis") - (SystemClock.elapsedRealtime() - l);
    while (true)
    {
      try
      {
        timer.cancel();
        setTimer(125L + remainMillis);
        timer.start();
        return i;
      }
      catch (NullPointerException localNullPointerException)
      {
        localNullPointerException.printStackTrace();
        continue;
      }
      if (state == 2)
      {
        remainMillis = paramBundle.getLong("timer_remainMillis");
        i = 0;
        continue;
      }
      i = 0;
    }
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putInt("timer_current_state", state);
    paramBundle.putLong("timer_elapsed_realtime", SystemClock.elapsedRealtime());
    paramBundle.putLong("timer_remainMillis", remainMillis);
    paramBundle.putLong("timer_inputMillis", inputMillis);
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    return false;
  }

  public void start()
  {
    if (state == 1)
      timer.cancel();
    if (state != 2)
    {
      long l2 = convertMillis(this.hour, this.minute, this.second);
      inputMillis = l2;
      remainMillis = l2;
      if (inputMillis != 0L);
    }
    while (true)
    {
      return;
      state = 1;
      isViewChanged = false;
      View[] arrayOfView = new View[9];
      arrayOfView[0] = this.mHourBackground;
      arrayOfView[1] = mHourPrefix;
      arrayOfView[2] = mHourPostfix;
      arrayOfView[3] = this.mMinuteBackground;
      arrayOfView[4] = mMinutePrefix;
      arrayOfView[5] = mMinutePostfix;
      arrayOfView[6] = this.mSecondBackground;
      arrayOfView[7] = mSecondPrefix;
      arrayOfView[8] = mSecondPostfix;
      releaseNumberViews(arrayOfView);
      setInputTime(this.context, getInputTime());
      long l1 = convertMillis(this.hour, this.minute, this.second);
      inputMillis = l1;
      remainMillis = l1;
      setTimer(1125L + inputMillis);
      timer.start();
      setAlarm();
      setViewState();
      startTimerApp();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.TimerWidget
 * JD-Core Version:    0.6.0
 */