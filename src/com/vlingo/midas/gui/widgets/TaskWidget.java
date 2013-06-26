package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.Settings.System;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.DecoratorType;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.schedule.ScheduleTask;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.gui.customviews.Button;
import com.vlingo.midas.settings.MidasSettings;
import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TaskWidget extends BargeInWidget<ScheduleTask>
  implements View.OnClickListener, View.OnTouchListener
{
  private String contentString = "";
  private boolean isClicked = false;
  boolean isComplete;
  boolean isSaveButton = false;
  private boolean lookupMode = false;
  private WidgetActionListener mActionListener;
  private View mBottomReminder;
  private Button mCancel;
  private Button mConfirm;
  private TextView mDate;
  private TextView mReminder;
  private ScheduleTask mTask;
  private TextView mTitle;
  private LinearLayout taskContainer;
  private LinearLayout task_button;
  private LinearLayout task_row_dueto;
  private LinearLayout task_row_reminder;
  private LinearLayout task_row_title;

  public TaskWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private String getKoreanMonth(String paramString)
  {
    if (paramString.contains(getResources().getString(2131362611)));
    while (true)
    {
      return paramString;
      paramString = paramString + getResources().getString(2131362611);
    }
  }

  private void launchTask()
  {
    Intent localIntent = new Intent("android.intent.action.INSERT");
    localIntent.setType("vnd.android.cursor.item/event");
    localIntent.putExtra("task", true);
    localIntent.putExtra("title", this.mTask.getTitle());
    localIntent.putExtra("beginTime", this.mTask.getBegin());
    getContext().startActivity(localIntent);
  }

  private void launchTaskDetail()
  {
    if (this.mTask.getEventID() <= 0L)
      return;
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setClassName("com.android.calendar", "com.android.calendar.task.TaskInfoActivity");
    localIntent.setType("vnd.android.cursor.item/event");
    localIntent.setData(Uri.parse(String.valueOf(this.mTask.getEventID())));
    localIntent.putExtra("title", this.mTask.getTitle());
    localIntent.putExtra("duedate", this.mTask.getBegin());
    if (this.mTask.getReminderTime() <= 0L)
      localIntent.putExtra("reminder", "Off");
    while (true)
    {
      localIntent.putExtra("groupid", this.mTask.getGroupid());
      localIntent.putExtra("description", this.mTask.getDescription());
      getContext().startActivity(localIntent);
      break;
      localIntent.putExtra("reminderdatetime", this.mTask.getReminderTime());
    }
  }

  public void initialize(ScheduleTask paramScheduleTask, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    if (paramScheduleTask == null);
    while (true)
    {
      return;
      this.lookupMode = true;
      this.mTask = paramScheduleTask;
      this.mActionListener = paramWidgetActionListener;
      this.isComplete = paramScheduleTask.getCompleted();
      String str1 = paramScheduleTask.getTitle();
      label73: long l1;
      label93: String str12;
      String str13;
      String str14;
      String str15;
      String str16;
      String str17;
      if (str1 != null)
      {
        if (this.isComplete)
        {
          this.mTitle.setPaintFlags(0x10 | this.mDate.getPaintFlags());
          this.mTitle.setText(str1);
        }
      }
      else
      {
        if ((paramWidgetKey != WidgetUtil.WidgetKey.AddTask) && (paramWidgetKey != WidgetUtil.WidgetKey.EditTask))
          break label402;
        l1 = paramScheduleTask.getBegin();
        if (l1 != 0L)
        {
          str12 = Settings.System.getString(getContext().getContentResolver(), "date_format");
          Calendar localCalendar2 = Calendar.getInstance();
          Date localDate2 = new Date(l1);
          localCalendar2.setTime(localDate2);
          str13 = String.valueOf(localCalendar2.get(1));
          str14 = String.valueOf(localCalendar2.get(5));
          str15 = localCalendar2.getDisplayName(2, 1, MidasSettings.getCurrentLocale());
          str16 = localCalendar2.getDisplayName(7, 1, MidasSettings.getCurrentLocale());
          if (!getResources().getConfiguration().locale.getLanguage().equals(Locale.KOREAN.getLanguage()))
            break label593;
          if ((str12 == null) || (!str12.equals("yyyy-MM-dd")))
            break label411;
          str17 = str16 + ", " + str13 + getResources().getString(2131362610) + " " + getKoreanMonth(str15) + " " + str14 + getResources().getString(2131362010);
          label305: if (!this.isComplete)
            break label784;
          this.mDate.setPaintFlags(0x10 | this.mDate.getPaintFlags());
          this.mDate.setText(str17);
        }
      }
      long l2;
      while (true)
      {
        l2 = paramScheduleTask.getReminderTime();
        if (!this.isComplete)
          break label796;
        this.task_row_reminder.setVisibility(8);
        this.mBottomReminder.setVisibility(8);
        break;
        this.mTitle.setText(str1);
        if (!StringUtils.isNullOrWhiteSpace(str1))
          break label73;
        this.mTitle.setText(2131362571);
        break label73;
        label402: l1 = paramScheduleTask.getUtcDueDate();
        break label93;
        label411: if ((str12 != null) && (str12.equals("MM-dd-yyyy")))
        {
          str17 = str16 + ", " + getKoreanMonth(str15) + " " + str14 + getResources().getString(2131362010) + " " + str13 + getResources().getString(2131362610);
          break label305;
        }
        str17 = str16 + ", " + str14 + getResources().getString(2131362010) + " " + getKoreanMonth(str15) + " " + str13 + getResources().getString(2131362610);
        break label305;
        label593: if ((str12 != null) && (str12.equals("yyyy-MM-dd")))
        {
          str17 = str16 + ", " + str13 + ", " + str15 + " " + str14;
          break label305;
        }
        if ((str12 != null) && (str12.equals("MM-dd-yyyy")))
        {
          str17 = str16 + ", " + str15 + ", " + str14 + ", " + str13;
          break label305;
        }
        str17 = str16 + ", " + str14 + " " + str15 + ", " + str13;
        break label305;
        label784: this.mDate.setText(str17);
      }
      label796: if (l2 <= 0L)
      {
        this.mReminder.setText("");
        this.contentString = (this.mTitle.getText().toString() + ", " + getContext().getString(2131362263) + this.mDate.getText() + ", " + getContext().getString(2131362264) + this.mReminder.getText());
        if (paramWidgetDecorator == null);
      }
      try
      {
        if (!paramWidgetDecorator.has(DecoratorType.ConfirmButton))
        {
          this.task_button.setVisibility(8);
          this.mConfirm.setVisibility(8);
          this.mCancel.setVisibility(8);
          this.task_row_title.setContentDescription(getAccessibilityString(this.contentString, 2));
          this.task_row_dueto.setContentDescription(getAccessibilityString(this.contentString, 2));
          this.task_row_reminder.setContentDescription(getAccessibilityString(this.contentString, 2));
          continue;
        }
      }
      catch (InvalidParameterException localInvalidParameterException)
      {
        continue;
        String str2 = Settings.System.getString(getContext().getContentResolver(), "date_format");
        Calendar localCalendar1 = Calendar.getInstance();
        Date localDate1 = new Date(l2);
        localCalendar1.setTime(localDate1);
        String str3 = String.valueOf(localCalendar1.get(1));
        String str4 = String.valueOf(localCalendar1.get(5));
        String str5 = localCalendar1.getDisplayName(2, 1, MidasSettings.getCurrentLocale());
        String str6 = localCalendar1.getDisplayName(7, 1, MidasSettings.getCurrentLocale());
        String str7 = String.valueOf(localCalendar1.get(11));
        String str8 = String.valueOf(localCalendar1.get(12));
        String str9;
        if (getResources().getConfiguration().locale.getLanguage().equals(Locale.KOREAN.getLanguage()))
          if ((str2 != null) && (str2.equals("yyyy-MM-dd")))
            str9 = str6 + ", " + str3 + getResources().getString(2131362610) + " " + getKoreanMonth(str5) + " " + str4 + getResources().getString(2131362010);
        while (true)
        {
          if (str8.length() == 1)
            str8 = "0" + str8;
          if (!DateFormat.is24HourFormat(getContext()))
            break label1721;
          if (str7.length() == 1)
            str7 = "0" + str7;
          String str11 = str7;
          this.mReminder.setText(str9 + ", " + str11 + ":" + str8);
          break;
          if ((str2 != null) && (str2.equals("MM-dd-yyyy")))
          {
            str9 = str6 + ", " + getKoreanMonth(str5) + " " + str4 + getResources().getString(2131362010) + " " + str3 + getResources().getString(2131362610);
            continue;
          }
          str9 = str6 + ", " + str4 + getResources().getString(2131362010) + " " + getKoreanMonth(str5) + " " + str3 + getResources().getString(2131362610);
          continue;
          if ((str2 != null) && (str2.equals("yyyy-MM-dd")))
          {
            str9 = str6 + ", " + str3 + ", " + str5 + " " + str4;
            continue;
          }
          if ((str2 != null) && (str2.equals("MM-dd-yyyy")))
          {
            str9 = str6 + ", " + str5 + " " + str4 + ", " + str3;
            continue;
          }
          str9 = str6 + ", " + str4 + " " + str5 + ", " + str3;
        }
        String str10 = str7;
        int i = Integer.parseInt(str7);
        if ((i <= 9) && (i > 0))
        {
          str10 = "0" + String.valueOf(i);
          str8 = str8 + getResources().getString(2131362516);
        }
        while (true)
        {
          this.mReminder.setText(str9 + ", " + str10 + ":" + str8);
          break;
          if ((i > 12) && (i <= 21))
          {
            int j = i - 12;
            str10 = "0" + String.valueOf(j);
            str8 = str8 + getResources().getString(2131362520);
            continue;
          }
          if ((i > 21) && (i <= 24))
          {
            str10 = String.valueOf(i - 12);
            str8 = str8 + getResources().getString(2131362520);
            continue;
          }
          if ((i > 9) && (i < 12))
          {
            str8 = str8 + getResources().getString(2131362516);
            continue;
          }
          if (i == 0)
          {
            str10 = "12";
            str8 = str8 + getResources().getString(2131362516);
            continue;
          }
          if (i != 12)
            continue;
          str10 = String.valueOf(i);
          str8 = str8 + getResources().getString(2131362520);
        }
        this.mConfirm.setVisibility(0);
        this.task_row_title.setContentDescription(getAccessibilityString(this.contentString, 1));
        this.task_row_dueto.setContentDescription(getAccessibilityString(this.contentString, 1));
        this.task_row_reminder.setContentDescription(getAccessibilityString(this.contentString, 1));
        continue;
      }
      catch (Exception localException)
      {
        label1721: continue;
      }
      finally
      {
      }
    }
    throw localObject;
  }

  public boolean isTranslated()
  {
    return this.isClicked;
  }

  public boolean isWidgetReplaceable()
  {
    return true;
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131559142:
    case 2131559141:
    }
    while (true)
    {
      return;
      try
      {
        if (this.mActionListener == null)
          continue;
        if (this.mConfirm != null)
          this.mConfirm.setVisibility(8);
        if (this.mCancel != null)
          this.mCancel.setVisibility(8);
        if (this.task_button != null)
          this.task_button.setVisibility(8);
        this.isClicked = true;
        this.lookupMode = false;
        retire();
        this.mActionListener.handleIntent(new Intent("com.vlingo.core.internal.dialogmanager.Default"), null);
      }
      catch (IllegalArgumentException localIllegalArgumentException2)
      {
        continue;
        try
        {
          if (this.mActionListener == null)
            continue;
          if (this.mConfirm != null)
            this.mConfirm.setVisibility(8);
          if (this.mCancel != null)
            this.mCancel.setVisibility(8);
          if (this.task_button != null)
            this.task_button.setVisibility(8);
          retire();
          Intent localIntent = new Intent();
          localIntent.setAction("com.vlingo.core.internal.dialogmanager.Cancel");
          this.mActionListener.handleIntent(localIntent, null);
        }
        catch (IllegalArgumentException localIllegalArgumentException1)
        {
        }
        catch (Exception localException1)
        {
        }
      }
      catch (Exception localException2)
      {
      }
    }
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.task_row_title = ((LinearLayout)findViewById(2131559133));
    this.task_row_dueto = ((LinearLayout)findViewById(2131559135));
    this.task_row_reminder = ((LinearLayout)findViewById(2131559138));
    this.task_button = ((LinearLayout)findViewById(2131559140));
    this.mBottomReminder = findViewById(2131559137);
    this.task_row_title.setOnTouchListener(this);
    this.task_row_dueto.setOnTouchListener(this);
    this.task_row_reminder.setOnTouchListener(this);
    try
    {
      this.mTitle = ((TextView)findViewById(2131559134));
      if (this.mTitle != null)
        this.mTitle.setOnFocusChangeListener(new TaskWidget.1(this));
      this.mDate = ((TextView)findViewById(2131559136));
      this.mReminder = ((TextView)findViewById(2131559139));
      this.mConfirm = ((Button)findViewById(2131559142));
      if (this.mConfirm != null)
        this.mConfirm.setOnClickListener(this);
      this.mCancel = ((Button)findViewById(2131559141));
      if (this.mCancel != null)
        this.mCancel.setOnClickListener(this);
      label217: return;
    }
    catch (Exception localException)
    {
      break label217;
    }
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 1)
    {
      if ((this.mConfirm == null) || (!this.mConfirm.isShown()))
        break label31;
      launchTask();
    }
    while (true)
    {
      return true;
      label31: launchTaskDetail();
      retire();
    }
  }

  public void retire()
  {
    super.retire();
    this.mConfirm.setVisibility(8);
    this.mCancel.setVisibility(8);
    this.task_button.setVisibility(8);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.TaskWidget
 * JD-Core Version:    0.6.0
 */