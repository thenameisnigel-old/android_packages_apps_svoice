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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.util.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScheduleChoiceWidget extends BargeInWidget<List<ScheduleEvent>>
  implements AdapterView.OnItemClickListener
{
  private WidgetActionListener mActionListener;
  private Context mContext;
  private ListView mScheduleList;

  public ScheduleChoiceWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  public void initialize(List<ScheduleEvent> paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.mActionListener = paramWidgetActionListener;
    if (paramList != null)
    {
      ScheduleAdapter localScheduleAdapter = new ScheduleAdapter(getContext(), paramList);
      this.mScheduleList.setAdapter(localScheduleAdapter);
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

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.mScheduleList = ((ListView)findViewById(2131558893));
    this.mScheduleList.setDivider(null);
    this.mScheduleList.setOnItemClickListener(this);
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    try
    {
      ScheduleEvent localScheduleEvent = (ScheduleEvent)paramAdapterView.getAdapter().getItem(paramInt);
      Intent localIntent = new Intent("com.vlingo.core.internal.dialogmanager.Choice");
      localIntent.putExtra("choice", String.valueOf(localScheduleEvent.getID()));
      this.mActionListener.handleIntent(localIntent, null);
      label54: return;
    }
    catch (Exception localException)
    {
      break label54;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      break label54;
    }
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    measureListviewHeight(this.mScheduleList, 2131427343, true);
    super.onMeasure(paramInt1, paramInt2);
  }

  private class ScheduleAdapter extends BaseAdapter
  {
    private Context context;
    private List<ScheduleEvent> mEvents;
    private LayoutInflater mInflater;

    public ScheduleAdapter(List<ScheduleEvent> arg2)
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
      return ScheduleChoiceWidget.this.getLimitedCount(i);
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
      ScheduleEvent localScheduleEvent;
      label136: Date localDate;
      label234: String str2;
      label265: String str3;
      String str4;
      String str5;
      if (paramView == null)
      {
        paramView = this.mInflater.inflate(2130903099, null);
        localViewHolder = new ViewHolder();
        TextView localTextView1 = (TextView)paramView.findViewById(2131558708);
        localViewHolder.title = localTextView1;
        TextView localTextView2 = (TextView)paramView.findViewById(2131558710);
        localViewHolder.date = localTextView2;
        TextView localTextView3 = (TextView)paramView.findViewById(2131558709);
        localViewHolder.time = localTextView3;
        View localView = paramView.findViewById(2131558694);
        localViewHolder.divider = localView;
        paramView.setTag(localViewHolder);
        localScheduleEvent = (ScheduleEvent)this.mEvents.get(paramInt);
        if (!StringUtils.isNullOrWhiteSpace(localScheduleEvent.getTitle()))
          break label552;
        localViewHolder.title.setText(2131362571);
        long l1 = localScheduleEvent.getBegin();
        long l2 = localScheduleEvent.getEnd();
        if (l1 == 0L)
          break label629;
        localDate = new Date(l1);
        String str1 = Settings.System.getString(ScheduleChoiceWidget.this.getContext().getContentResolver(), "date_format");
        if (str1 == null)
          break label568;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str1);
        localViewHolder.date.setText(localSimpleDateFormat.format(localDate) + ", ");
        if (!localScheduleEvent.getAllDay())
          break label613;
        localViewHolder.time.setText(ScheduleChoiceWidget.this.mContext.getResources().getString(2131362551));
        if ((!localScheduleEvent.getAllDay()) && (l2 != 0L))
        {
          Calendar localCalendar1 = Calendar.getInstance();
          localCalendar1.setTime(new Date(localScheduleEvent.getBegin()));
          str2 = String.valueOf(localCalendar1.get(11));
          str3 = String.valueOf(localCalendar1.get(12));
          Calendar localCalendar2 = Calendar.getInstance();
          localCalendar2.setTime(new Date(localScheduleEvent.getEnd()));
          str4 = String.valueOf(localCalendar2.get(11));
          str5 = String.valueOf(localCalendar2.get(12));
          if (str5.length() == 1)
            str5 = "0" + str5;
          if (str4.length() == 1)
            str4 = "0" + str4;
          if (!android.text.format.DateFormat.is24HourFormat(ScheduleChoiceWidget.this.mContext))
            break label642;
          localViewHolder.time.setText(localScheduleEvent.getBeginTime() + " - " + localScheduleEvent.getEndTime());
        }
        if (getCount() == 1)
          break label1495;
        if (paramInt != 0)
          break label1455;
        paramView.setBackgroundResource(2130837820);
        if (getCount() == 1)
          localViewHolder.divider.setVisibility(4);
      }
      while (true)
      {
        1 local1 = new View.OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            Intent localIntent = new Intent();
            localIntent.setAction("com.vlingo.core.internal.dialogmanager.Choice");
            localIntent.putExtra("choice", this.val$position);
            localIntent.putExtra("uri", String.valueOf(((ScheduleEvent)ScheduleChoiceWidget.ScheduleAdapter.this.mEvents.get(this.val$position)).getEventID()));
            localIntent.putExtra("title", ((ScheduleEvent)ScheduleChoiceWidget.ScheduleAdapter.this.mEvents.get(this.val$position)).getTitle());
            localIntent.putExtra("beginTime", ((ScheduleEvent)ScheduleChoiceWidget.ScheduleAdapter.this.mEvents.get(this.val$position)).getBegin());
            localIntent.putExtra("endTime", ((ScheduleEvent)ScheduleChoiceWidget.ScheduleAdapter.this.mEvents.get(this.val$position)).getEnd());
            ScheduleChoiceWidget.this.mActionListener.handleIntent(localIntent, null);
          }
        };
        paramView.setOnClickListener(local1);
        return paramView;
        localViewHolder = (ViewHolder)paramView.getTag();
        break;
        label552: localViewHolder.title.setText(localScheduleEvent.getTitle());
        break label136;
        label568: java.text.DateFormat localDateFormat = android.text.format.DateFormat.getDateFormat(this.context);
        localViewHolder.date.setText(localDateFormat.format(localDate) + ", ");
        break label234;
        label613: localViewHolder.time.setText(localScheduleEvent.getBeginTime());
        break label265;
        label629: localViewHolder.time.setVisibility(8);
        break label265;
        label642: String str6 = str2;
        String str7 = str4;
        int i = Integer.parseInt(str2);
        int j = Integer.parseInt(str4);
        if (str3.length() == 1)
          str3 = "0" + str3;
        if ((i <= 9) && (i > 0))
        {
          str6 = "0" + String.valueOf(i);
          str3 = str3 + ScheduleChoiceWidget.this.getResources().getString(2131362516);
          label765: if ((j > 9) || (j <= 0))
            break label1174;
          str7 = "0" + String.valueOf(j);
          str5 = str5 + ScheduleChoiceWidget.this.getResources().getString(2131362516);
        }
        while (true)
        {
          localViewHolder.time.setText(str6 + ":" + str3 + " - " + str7 + ":" + str5);
          break;
          if ((i <= 21) && (i > 12))
          {
            int m = i - 12;
            str6 = "0" + String.valueOf(m);
            str3 = str3 + ScheduleChoiceWidget.this.getResources().getString(2131362520);
            break label765;
          }
          if (i > 21)
          {
            str6 = String.valueOf(i - 12);
            str3 = str3 + ScheduleChoiceWidget.this.getResources().getString(2131362520);
            break label765;
          }
          if ((i > 9) && (i < 12))
          {
            str3 = str3 + ScheduleChoiceWidget.this.getResources().getString(2131362516);
            break label765;
          }
          if (i == 0)
          {
            str6 = "12";
            str3 = str3 + ScheduleChoiceWidget.this.getResources().getString(2131362516);
            break label765;
          }
          if (i != 12)
            break label765;
          str6 = String.valueOf(i);
          str3 = str3 + ScheduleChoiceWidget.this.getResources().getString(2131362520);
          break label765;
          label1174: if ((j > 9) && (j < 12))
          {
            str5 = str5 + ScheduleChoiceWidget.this.getResources().getString(2131362516);
            continue;
          }
          if ((j <= 21) && (j > 12))
          {
            int k = j - 12;
            str7 = "0" + String.valueOf(k);
            str5 = str5 + ScheduleChoiceWidget.this.getResources().getString(2131362520);
            continue;
          }
          if (j > 21)
          {
            str7 = String.valueOf(j - 12);
            str5 = str5 + ScheduleChoiceWidget.this.getResources().getString(2131362520);
            continue;
          }
          if (j == 0)
          {
            str7 = "12";
            str5 = str5 + ScheduleChoiceWidget.this.getResources().getString(2131362516);
            continue;
          }
          if (j != 12)
            continue;
          str7 = String.valueOf(j);
          str5 = str5 + ScheduleChoiceWidget.this.getResources().getString(2131362520);
        }
        label1455: if (paramInt >= -1 + getCount())
        {
          paramView.setBackgroundResource(2130837817);
          localViewHolder.divider.setVisibility(4);
          continue;
        }
        paramView.setBackgroundResource(2130837819);
        continue;
        label1495: ScheduleChoiceWidget.this.mScheduleList.setPadding(2, 2, 2, 2);
        paramView.setBackgroundResource(2130837818);
        localViewHolder.divider.setVisibility(4);
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
 * Qualified Name:     com.vlingo.midas.gui.widgets.ScheduleChoiceWidget
 * JD-Core Version:    0.6.0
 */