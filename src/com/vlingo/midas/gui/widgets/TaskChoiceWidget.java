package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.Settings.System;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.schedule.ScheduleTask;
import com.vlingo.core.internal.util.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TaskChoiceWidget extends BargeInWidget<List<ScheduleTask>>
  implements AdapterView.OnItemClickListener
{
  private WidgetActionListener mActionListener;
  private Context mContext;
  private ListView mTaskList;

  public TaskChoiceWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  public void initialize(List<ScheduleTask> paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.mActionListener = paramWidgetActionListener;
    TaskAdapter localTaskAdapter = new TaskAdapter(getContext(), paramList);
    this.mTaskList.setAdapter(localTaskAdapter);
  }

  public boolean isTranslated()
  {
    return false;
  }

  public boolean isWidgetReplaceable()
  {
    return false;
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.mTaskList = ((ListView)findViewById(2131558893));
    this.mTaskList.setDivider(null);
    this.mTaskList.setOnItemClickListener(this);
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    measureListviewHeight(this.mTaskList, 2131427346, true);
    super.onMeasure(paramInt1, paramInt2);
  }

  private class TaskAdapter extends BaseAdapter
  {
    private Context context;
    private List<ScheduleTask> mEvents;
    private LayoutInflater mInflater;

    public TaskAdapter(List<ScheduleTask> arg2)
    {
      Context localContext;
      this.mInflater = LayoutInflater.from(localContext);
      Object localObject;
      this.mEvents = localObject;
      this.context = localContext;
    }

    public int getCount()
    {
      int i = this.mEvents.size();
      return TaskChoiceWidget.this.getLimitedCount(i);
    }

    public Object getItem(int paramInt)
    {
      return this.mEvents.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      ViewHolder localViewHolder;
      ScheduleTask localScheduleTask;
      label120: Date localDate;
      String str2;
      String str3;
      if (paramView == null)
      {
        paramView = this.mInflater.inflate(2130903099, null);
        localViewHolder = new ViewHolder();
        localViewHolder.title = ((TextView)paramView.findViewById(2131558708));
        localViewHolder.date = ((TextView)paramView.findViewById(2131558710));
        localViewHolder.time = ((TextView)paramView.findViewById(2131558709));
        localViewHolder.divider = paramView.findViewById(2131558694);
        paramView.setTag(localViewHolder);
        localScheduleTask = (ScheduleTask)this.mEvents.get(paramInt);
        if (!StringUtils.isNullOrWhiteSpace(localScheduleTask.getTitle()))
          break label444;
        localViewHolder.title.setText(2131362571);
        long l = localScheduleTask.getBegin();
        if (l == 0L)
          break label851;
        localDate = new Date(l);
        String str1 = Settings.System.getString(TaskChoiceWidget.this.getContext().getContentResolver(), "date_format");
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(new Date(localScheduleTask.getReminderTime()));
        str2 = String.valueOf(localCalendar.get(11));
        str3 = String.valueOf(localCalendar.get(12));
        if (str1 == null)
          break label460;
        String str7 = new SimpleDateFormat(str1).format(localDate);
        if (localScheduleTask.getReminderTime() > 0L)
          str7 = str7 + ", ";
        localViewHolder.date.setText(str7);
        label271: if (str3.length() == 1)
          str3 = "0" + str3;
        if (!android.text.format.DateFormat.is24HourFormat(TaskChoiceWidget.this.getContext()))
          break label519;
        if (str2.length() == 1)
          str2 = "0" + str2;
        String str6 = str2;
        localViewHolder.time.setText(str6 + ":" + str3);
        if (localScheduleTask.getReminderTime() <= 0L)
          localViewHolder.time.setVisibility(8);
        label403: if (paramInt != 0)
          break label864;
        paramView.setBackgroundResource(2130837820);
      }
      while (true)
      {
        1 local1 = new View.OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            TaskChoiceWidget.this.retire();
            Intent localIntent = new Intent();
            localIntent.setAction("com.vlingo.core.internal.dialogmanager.Choice");
            localIntent.putExtra("choice", this.val$position);
            TaskChoiceWidget.this.mActionListener.handleIntent(localIntent, null);
          }
        };
        paramView.setOnClickListener(local1);
        return paramView;
        localViewHolder = (ViewHolder)paramView.getTag();
        break;
        label444: localViewHolder.title.setText(localScheduleTask.getTitle());
        break label120;
        label460: String str4 = android.text.format.DateFormat.getDateFormat(this.context).format(localDate);
        if (localScheduleTask.getReminderTime() > 0L)
          str4 = str4 + ", ";
        localViewHolder.date.setText(str4);
        break label271;
        label519: String str5 = str2;
        int i = Integer.parseInt(str2);
        if ((i <= 9) && (i > 0))
        {
          str5 = "0" + String.valueOf(i);
          str3 = str3 + TaskChoiceWidget.this.getResources().getString(2131362516);
        }
        while (true)
        {
          localViewHolder.time.setText(str5 + ":" + str3);
          break;
          if (i > 12)
          {
            int j = i - 12;
            str5 = "0" + String.valueOf(j);
            str3 = str3 + TaskChoiceWidget.this.getResources().getString(2131362520);
            continue;
          }
          if ((i > 9) && (i < 12))
          {
            str3 = str3 + TaskChoiceWidget.this.getResources().getString(2131362516);
            continue;
          }
          if (i == 0)
          {
            str5 = "12";
            str3 = str3 + TaskChoiceWidget.this.getResources().getString(2131362516);
            continue;
          }
          if (i != 12)
            continue;
          str5 = String.valueOf(i);
          str3 = str3 + TaskChoiceWidget.this.getResources().getString(2131362520);
        }
        label851: localViewHolder.time.setVisibility(8);
        break label403;
        label864: if (paramInt == -1 + this.mEvents.size())
        {
          localViewHolder.divider.setVisibility(4);
          paramView.setBackgroundResource(2130837817);
          continue;
        }
        paramView.setBackgroundResource(2130837819);
      }
    }

    class ViewHolder
    {
      TextView date;
      View divider;
      TextView time;
      TextView title;

      ViewHolder()
      {
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.TaskChoiceWidget
 * JD-Core Version:    0.6.0
 */