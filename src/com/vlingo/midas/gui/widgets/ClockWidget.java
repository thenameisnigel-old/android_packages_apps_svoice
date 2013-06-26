package com.vlingo.midas.gui.widgets;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Paint;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.settings.MidasSettings;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ClockWidget extends BargeInWidget<Object>
{
  private static final String M12 = "hhmm";
  private static final String M24 = "kkmm";
  private TextView mAmPmText;
  private boolean mAttached = false;
  private int[] mBigNumbers_night;
  private Calendar mCalendar;
  private Context mContext;
  private Date mDate;
  private TextView mDayView;
  private CharSequence mFormat;
  private ImageView mHourPostView;
  private ImageView mHourPreView;
  private final BroadcastReceiver mIntentReceiver;
  private boolean mLive = true;
  private ImageView mMinPostView;
  private ImageView mMinPreView;
  private TextView mMonthText;
  private String mStrDate;
  private String month = "";

  public ClockWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    int[] arrayOfInt = new int[10];
    arrayOfInt[0] = 2130838127;
    arrayOfInt[1] = 2130838128;
    arrayOfInt[2] = 2130838129;
    arrayOfInt[3] = 2130838130;
    arrayOfInt[4] = 2130838131;
    arrayOfInt[5] = 2130838132;
    arrayOfInt[6] = 2130838133;
    arrayOfInt[7] = 2130838134;
    arrayOfInt[8] = 2130838135;
    arrayOfInt[9] = 2130838136;
    this.mBigNumbers_night = arrayOfInt;
    this.mIntentReceiver = new ClockWidget.1(this);
    this.mContext = paramContext;
  }

  static boolean get24HourMode(Context paramContext)
  {
    return DateFormat.is24HourFormat(paramContext);
  }

  private void setDataText(Date paramDate)
  {
    this.mStrDate = formatWidgetDate1(paramDate, MidasSettings.getCurrentLocale());
    this.mDayView.setText(this.mStrDate);
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

  private void updateTime()
  {
    int i = 1;
    if (this.mLive)
      this.mCalendar.setTimeInMillis(System.currentTimeMillis());
    CharSequence localCharSequence = DateFormat.format(this.mFormat, this.mCalendar);
    this.mDate = new Date();
    setDateFormat();
    setDataText(this.mDate);
    this.mHourPreView.setBackgroundResource(this.mBigNumbers_night[java.lang.Integer.parseInt(localCharSequence.toString().substring(0, i))]);
    this.mHourPostView.setBackgroundResource(this.mBigNumbers_night[java.lang.Integer.parseInt(localCharSequence.toString().substring(i, 2))]);
    this.mMinPreView.setBackgroundResource(this.mBigNumbers_night[java.lang.Integer.parseInt(localCharSequence.toString().substring(2, 3))]);
    this.mMinPostView.setBackgroundResource(this.mBigNumbers_night[java.lang.Integer.parseInt(localCharSequence.toString().substring(3, 4))]);
    if (this.mCalendar.get(9) == 0);
    while (true)
    {
      setIsMorning(i);
      return;
      int j = 0;
    }
  }

  public String formatWidgetDate1(Date paramDate, Locale paramLocale)
  {
    SimpleDateFormat localSimpleDateFormat;
    String str3;
    if ((paramLocale.equals(Locale.KOREA)) || (paramLocale.equals(Locale.JAPAN)))
    {
      String str1 = new SimpleDateFormat("MM/dd", paramLocale).format(paramDate);
      if (paramLocale.equals(Locale.JAPAN))
      {
        localSimpleDateFormat = new SimpleDateFormat(" (EEEE)", paramLocale);
        String str2 = localSimpleDateFormat.format(paramDate);
        str3 = str1 + str2;
      }
    }
    while (true)
    {
      return str3;
      localSimpleDateFormat = new SimpleDateFormat(" EEEE", paramLocale);
      break;
      if (paramLocale.equals(Locale.CHINA))
      {
        str3 = new SimpleDateFormat("EEE MM/dd", paramLocale).format(paramDate);
        continue;
      }
      str3 = new SimpleDateFormat("EEE dd/MM", paramLocale).format(paramDate);
    }
  }

  public void initialize(Object paramObject, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    int i = 1;
    Locale localLocale = getResources().getConfiguration().locale;
    Calendar localCalendar = Calendar.getInstance();
    if (localLocale.getLanguage().equals(Locale.KOREAN.getLanguage()))
    {
      this.month = (localCalendar.get(i) + getResources().getString(2131362610) + " " + localCalendar.getDisplayName(2, 2, localLocale));
      this.mMonthText.setText(this.month);
      onTimeChanged();
      if (this.mCalendar.get(9) != 0)
        break label171;
    }
    while (true)
    {
      setIsMorning(i);
      return;
      this.month = (localCalendar.getDisplayName(2, 2, localLocale) + " " + localCalendar.get(i));
      break;
      label171: int j = 0;
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
    this.mHourPreView = ((ImageView)findViewById(2131558881));
    this.mHourPostView = ((ImageView)findViewById(2131558882));
    this.mMinPreView = ((ImageView)findViewById(2131558884));
    this.mMinPostView = ((ImageView)findViewById(2131558885));
    this.mAmPmText = ((TextView)findViewById(2131558888));
    this.mDayView = ((TextView)findViewById(2131558889));
    this.mMonthText = ((TextView)findViewById(2131558891));
    this.mCalendar = Calendar.getInstance();
    setDateFormat();
  }

  public void onTimeChanged()
  {
    updateTime();
  }

  public void processSystemMessage(Intent paramIntent)
  {
    onTimeChanged();
    invalidate();
  }

  void setIsMorning(boolean paramBoolean)
  {
    TextView localTextView = this.mAmPmText;
    if (paramBoolean);
    for (int i = 2131362516; ; i = 2131362520)
    {
      localTextView.setText(i);
      return;
    }
  }

  void setShowAmPm(boolean paramBoolean)
  {
    TextView localTextView = this.mAmPmText;
    if (paramBoolean);
    for (int i = 0; ; i = 8)
    {
      localTextView.setVisibility(i);
      return;
    }
  }

  public void setTextViewScale(TextView paramTextView, int paramInt)
  {
    float f1 = paramTextView.getTextScaleX();
    float f2 = f1;
    String str = paramTextView.getText().toString();
    Paint localPaint;
    for (Object localObject = paramTextView.getPaint(); ((Paint)localObject).measureText(str) > paramInt; localObject = localPaint)
    {
      localPaint = new Paint((Paint)localObject);
      f2 -= 0.1F;
      localPaint.setTextScaleX(f2);
    }
    if (f1 != f2)
      paramTextView.setTextScaleX(f2);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.ClockWidget
 * JD-Core Version:    0.6.0
 */