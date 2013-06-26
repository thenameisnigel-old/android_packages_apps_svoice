package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.schedule.ScheduleUtil;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.DrivingWidgetInterface;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DrivingScheduleWidget extends BargeInWidget<ScheduleEvent>
  implements DrivingWidgetInterface
{
  boolean checkLocation = false;
  boolean checkParticipants = false;
  private Context context;
  protected boolean isClicked = false;
  boolean isParticipants = false;
  boolean isSaveButton = false;
  boolean isloaction = false;
  protected WidgetActionListener mActionListener;
  private TextView mAttendee;
  private TextView mDay;
  private TextView mLocation;
  private TextView mMonth;
  private View mRowButtonline;
  private LinearLayout mRowDate;
  private LinearLayout mRowLocation;
  private View mRowLocationline;
  private LinearLayout mRowParticipants;
  private View mRowParticipantsline;
  private RelativeLayout mRowTitle;
  private View mRowTitleline;
  private ScheduleEvent mScheduleEvent;
  private TextView mTime;
  private TextView mTitle;
  private TextView mWeek;
  private int participants_line = 55;
  private LinearLayout scheduleContainer;
  private int single_orientation = getResources().getConfiguration().orientation;

  public DrivingScheduleWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
  }

  private void initialize(ScheduleEvent paramScheduleEvent)
  {
    this.mScheduleEvent = paramScheduleEvent;
    SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("EEE. dd MMM. yyyy");
    SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("yyyy.MM.dd EEE.");
    this.mRowTitleline.setVisibility(0);
    String str8;
    label243: String str2;
    label281: String str3;
    String str4;
    String str5;
    String str1;
    label558: List localList;
    if (StringUtils.isNullOrWhiteSpace(this.mScheduleEvent.getTitle()))
    {
      this.mTitle.setText(2131362571);
      long l1 = this.mScheduleEvent.getBegin();
      long l2 = this.mScheduleEvent.getEnd();
      if (l1 == 0L)
        break label791;
      Calendar localCalendar3 = Calendar.getInstance();
      localCalendar3.setTime(new Date(this.mScheduleEvent.getBegin()));
      str8 = localSimpleDateFormat1.format(localCalendar3.getTime());
      String str9 = localSimpleDateFormat2.format(localCalendar3.getTime());
      Log.d("test_schedule", "getTime " + localSimpleDateFormat1.format(localCalendar3.getTime()));
      localSimpleDateFormat1.format(new Date(this.mScheduleEvent.getBegin()));
      Log.d("test_schedule", "new Date " + localSimpleDateFormat1.format(new Date(this.mScheduleEvent.getBegin())));
      if (!MidasSettings.getCurrentLocale().toString().equals("ko_KR"))
        break label762;
      this.mDay.setText(str9);
      if (!this.mScheduleEvent.getAllDay())
        break label774;
      this.mTime.setText("All Day");
      this.mTime.setText(this.context.getResources().getString(2131362551));
      if ((!paramScheduleEvent.getAllDay()) && (l2 != 0L))
      {
        Calendar localCalendar1 = Calendar.getInstance();
        localCalendar1.setTime(new Date(this.mScheduleEvent.getEnd()));
        str2 = String.valueOf(localCalendar1.get(11));
        str3 = String.valueOf(localCalendar1.get(12));
        Calendar localCalendar2 = Calendar.getInstance();
        localCalendar2.setTime(new Date(this.mScheduleEvent.getBegin()));
        str4 = String.valueOf(localCalendar2.get(11));
        str5 = String.valueOf(localCalendar2.get(12));
        if (str3.length() == 1)
          str3 = "0" + str3;
        if (str2.length() == 1)
          str2 = "0" + str2;
        if (!DateFormat.is24HourFormat(getContext()))
          break label803;
        this.mTime.setText(this.mScheduleEvent.getBeginTime() + " - " + str2 + ":" + str3);
      }
      str1 = this.mScheduleEvent.getLocation();
      if (!StringUtils.isNullOrWhiteSpace(str1))
        break label1577;
      this.mRowLocationline.setVisibility(0);
      this.mRowLocation.setVisibility(0);
      this.mLocation.setText("");
      this.checkLocation = false;
      localList = this.mScheduleEvent.getAttendeeNames();
      if ((localList != null) && (localList.size() != 0) && (ScheduleUtil.canSaveAttendee(getContext())))
        break label1602;
      this.checkParticipants = false;
      this.mRowParticipantsline.setVisibility(0);
      this.mRowParticipants.setVisibility(0);
      this.mAttendee.setText("");
      label623: if (this.mAttendee.getText() == "")
      {
        if (this.mLocation.getText() != "")
          break label1785;
        this.isloaction = true;
      }
    }
    while (true)
    {
      if (MidasSettings.isNightMode())
      {
        this.scheduleContainer.setBackgroundResource(2130837875);
        this.mTitle.setTextColor(-2039584);
        this.mTime.setTextColor(-2039584);
        this.mLocation.setTextColor(-2039584);
        this.mAttendee.setTextColor(-2039584);
        this.mRowParticipantsline.setBackgroundColor(-16777216);
        this.mRowLocationline.setBackgroundColor(-16777216);
        this.mRowTitleline.setBackgroundColor(-16777216);
      }
      initScheduleView();
      return;
      this.mTitle.setText(this.mScheduleEvent.getTitle());
      break;
      label762: this.mDay.setText(str8);
      break label243;
      label774: this.mTime.setText(this.mScheduleEvent.getBeginTime());
      break label281;
      label791: this.mTime.setVisibility(8);
      break label281;
      label803: String str6 = str4;
      String str7 = str2;
      int m = Integer.parseInt(str4);
      int n = Integer.parseInt(str2);
      if (str5.length() == 1)
        str5 = "0" + str5;
      if ((m <= 9) && (m > 0))
      {
        str6 = "0" + String.valueOf(m);
        str5 = str5 + getResources().getString(2131362516);
        label923: if ((n > 9) || (n <= 0))
          break label1311;
        str7 = "0" + String.valueOf(n);
        str3 = str3 + getResources().getString(2131362516);
      }
      while (true)
      {
        this.mTime.setText(str6 + ":" + str5 + " - " + str7 + ":" + str3);
        break;
        if ((m <= 21) && (m > 12))
        {
          int i2 = m - 12;
          str6 = "0" + String.valueOf(i2);
          str5 = str5 + getResources().getString(2131362520);
          break label923;
        }
        if (m > 21)
        {
          str6 = String.valueOf(m - 12);
          str5 = str5 + getResources().getString(2131362520);
          break label923;
        }
        if ((m > 9) && (m < 12))
        {
          str5 = str5 + getResources().getString(2131362516);
          break label923;
        }
        if (m == 0)
        {
          str6 = "12";
          str5 = str5 + getResources().getString(2131362516);
          break label923;
        }
        if (m != 12)
          break label923;
        str6 = String.valueOf(m);
        str5 = str5 + getResources().getString(2131362520);
        break label923;
        label1311: if ((n > 9) && (n < 12))
        {
          str3 = str3 + getResources().getString(2131362516);
          continue;
        }
        if ((n <= 21) && (n > 12))
        {
          int i1 = n - 12;
          str7 = "0" + String.valueOf(i1);
          str3 = str3 + getResources().getString(2131362520);
          continue;
        }
        if (n > 21)
        {
          str7 = String.valueOf(n - 12);
          str3 = str3 + getResources().getString(2131362520);
          continue;
        }
        if (n == 0)
        {
          str7 = "12";
          str3 = str3 + getResources().getString(2131362516);
          continue;
        }
        if (n != 12)
          continue;
        str7 = String.valueOf(n);
        str3 = str3 + getResources().getString(2131362520);
      }
      label1577: this.checkLocation = true;
      this.mRowLocationline.setVisibility(0);
      this.mLocation.setText(str1);
      break label558;
      label1602: this.mRowParticipantsline.setVisibility(0);
      this.checkParticipants = true;
      StringBuilder localStringBuilder = new StringBuilder();
      for (int i = 0; ; i++)
      {
        int j = -1 + localList.size();
        if (i >= j)
          break;
        localStringBuilder.append((String)localList.get(i) + getContext().getString(2131362062) + getContext().getString(2131362061));
      }
      localStringBuilder.append((String)localList.get(-1 + localList.size()));
      this.mAttendee.setText(localStringBuilder);
      int k = this.mAttendee.getText().length() / 30;
      if (k <= 0)
        break label623;
      this.mAttendee.setHeight(this.participants_line * (k + 1));
      break label623;
      label1785: this.isParticipants = true;
    }
  }

  protected void cancel()
  {
    try
    {
      if (this.mActionListener != null)
      {
        this.isClicked = true;
        retire();
        Intent localIntent = new Intent();
        localIntent.setAction("com.vlingo.core.internal.dialogmanager.Cancel");
        this.mActionListener.handleIntent(localIntent, null);
      }
      label43: return;
    }
    catch (Exception localException)
    {
      break label43;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      break label43;
    }
  }

  public int getDecreasedHeight()
  {
    if (getResources().getConfiguration().orientation == 2);
    for (int i = getResources().getDimensionPixelSize(2131427352); ; i = 978)
      return i;
  }

  public int getProperHeight()
  {
    return 0;
  }

  public void initScheduleView()
  {
    this.single_orientation = getResources().getConfiguration().orientation;
    if (this.single_orientation == 2)
    {
      LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, 153);
      localLayoutParams1.setMargins(60, 36, 60, 15);
      this.mRowTitle.setLayoutParams(localLayoutParams1);
      LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, 195);
      localLayoutParams2.setMargins(60, 15, 60, 30);
      this.mRowDate.setLayoutParams(localLayoutParams2);
      LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-1, 231);
      localLayoutParams3.setMargins(60, 15, 60, 30);
      this.mRowLocation.setLayoutParams(localLayoutParams3);
      LinearLayout.LayoutParams localLayoutParams4 = new LinearLayout.LayoutParams(-1, 210);
      localLayoutParams4.setMargins(60, 15, 60, 30);
      this.mRowParticipants.setLayoutParams(localLayoutParams4);
      this.mLocation.setMaxLines(1);
      this.mRowParticipantsline.setVisibility(4);
    }
    while (true)
    {
      return;
      if (this.single_orientation == 1)
      {
        this.mRowParticipantsline.setVisibility(0);
        LinearLayout.LayoutParams localLayoutParams5 = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams5.setMargins(60, 15, 72, 45);
        this.mRowTitle.setLayoutParams(localLayoutParams5);
        LinearLayout.LayoutParams localLayoutParams6 = new LinearLayout.LayoutParams(-1, getResources().getDimensionPixelSize(2131427337));
        localLayoutParams6.setMargins(60, 15, 72, 45);
        this.mRowDate.setLayoutParams(localLayoutParams6);
        LinearLayout.LayoutParams localLayoutParams7 = new LinearLayout.LayoutParams(-1, getResources().getDimensionPixelSize(2131427337));
        localLayoutParams7.setMargins(60, 45, 72, 60);
        this.mRowLocation.setLayoutParams(localLayoutParams7);
        LinearLayout.LayoutParams localLayoutParams8 = new LinearLayout.LayoutParams(-1, getResources().getDimensionPixelSize(2131427337));
        localLayoutParams8.setMargins(60, 45, 72, 45);
        this.mRowParticipants.setLayoutParams(localLayoutParams8);
        this.mLocation.setMaxLines(2);
        continue;
      }
    }
  }

  public void initViewScheduleMini()
  {
    this.single_orientation = getResources().getConfiguration().orientation;
    if (this.single_orientation == 2)
    {
      LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, 153);
      localLayoutParams1.setMargins(60, 36, 60, 15);
      this.mRowTitle.setLayoutParams(localLayoutParams1);
      LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, 195);
      localLayoutParams2.setMargins(60, 15, 60, 30);
      this.mRowDate.setLayoutParams(localLayoutParams2);
      LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-1, 207);
      localLayoutParams3.setMargins(60, 15, 60, 30);
      this.mRowLocation.setLayoutParams(localLayoutParams3);
      LinearLayout.LayoutParams localLayoutParams4 = new LinearLayout.LayoutParams(-1, 204);
      localLayoutParams4.setMargins(60, 15, 60, 30);
      this.mRowParticipants.setLayoutParams(localLayoutParams4);
      this.mLocation.setMaxLines(1);
      this.mRowParticipantsline.setVisibility(4);
    }
    while (true)
    {
      return;
      if (this.single_orientation == 1)
      {
        LinearLayout.LayoutParams localLayoutParams5 = new LinearLayout.LayoutParams(-1, 153);
        localLayoutParams5.setMargins(60, 36, 60, 15);
        this.mRowTitle.setLayoutParams(localLayoutParams5);
        LinearLayout.LayoutParams localLayoutParams6 = new LinearLayout.LayoutParams(-1, 228);
        localLayoutParams6.setMargins(51, 3, 57, 0);
        this.mRowDate.setLayoutParams(localLayoutParams6);
        LinearLayout.LayoutParams localLayoutParams7 = new LinearLayout.LayoutParams(-1, 237);
        localLayoutParams7.setMargins(51, 3, 57, 0);
        this.mRowLocation.setLayoutParams(localLayoutParams7);
        LinearLayout.LayoutParams localLayoutParams8 = new LinearLayout.LayoutParams(-1, 222);
        localLayoutParams8.setMargins(51, 3, 57, 0);
        this.mRowParticipants.setLayoutParams(localLayoutParams8);
        this.mLocation.setMaxLines(1);
        continue;
      }
    }
  }

  public void initialize(ScheduleEvent paramScheduleEvent, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    initialize(paramScheduleEvent);
  }

  public boolean isDecreasedSize()
  {
    return false;
  }

  public boolean isTranslated()
  {
    return this.isClicked;
  }

  public boolean isWidgetReplaceable()
  {
    return true;
  }

  public void launchSchedule()
  {
    Intent localIntent = new Intent("com.vlingo.core.internal.dialogmanager.Edit");
    this.mActionListener.handleIntent(localIntent, this.mScheduleEvent);
  }

  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (paramConfiguration != null)
      super.onConfigurationChanged(paramConfiguration);
    if (MidasSettings.isMultiwindowedMode())
      initViewScheduleMini();
    while (true)
    {
      return;
      initScheduleView();
    }
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.mDay = ((TextView)findViewById(2131558950));
    this.mRowTitle = ((RelativeLayout)findViewById(2131558947));
    this.mTitle = ((TextView)findViewById(2131558708));
    this.mTitle.setOnFocusChangeListener(new DrivingScheduleWidget.1(this));
    this.mTime = ((TextView)findViewById(2131558709));
    this.mRowLocation = ((LinearLayout)findViewById(2131558952));
    this.mLocation = ((TextView)findViewById(2131558954));
    this.mRowParticipants = ((LinearLayout)findViewById(2131558956));
    this.mAttendee = ((TextView)findViewById(2131558958));
    this.scheduleContainer = ((LinearLayout)findViewById(2131558946));
    this.mRowParticipantsline = findViewById(2131558959);
    this.mRowLocationline = findViewById(2131558955);
    this.mRowTitleline = findViewById(2131558951);
    this.mRowButtonline = findViewById(2131559126);
    this.mRowDate = ((LinearLayout)findViewById(2131558948));
  }

  public void retire()
  {
    super.retire();
  }

  public void setMinimize(boolean paramBoolean)
  {
    if (paramBoolean)
      initViewScheduleMini();
    while (true)
    {
      return;
      initScheduleView();
    }
  }

  public int setNightMode(boolean paramBoolean)
  {
    return 0;
  }

  public int setWidgetToDecreasedSize(boolean paramBoolean)
  {
    this.mRowParticipantsline.setVisibility(8);
    setMinimize(paramBoolean);
    return 0;
  }

  public void startAnimationTranslate(View paramView)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingScheduleWidget
 * JD-Core Version:    0.6.0
 */