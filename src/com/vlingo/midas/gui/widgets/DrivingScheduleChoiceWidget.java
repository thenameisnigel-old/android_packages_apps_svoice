package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.provider.Settings.System;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.DrivingWidgetInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DrivingScheduleChoiceWidget extends BargeInWidget<List<ScheduleEvent>>
  implements DrivingWidgetInterface
{
  private int choice_orientation;
  boolean isMulti = MidasSettings.isMultiwindowedMode();
  private WidgetActionListener mActionListener;
  private RelativeLayout mChoiceBody;
  private LinearLayout mChoiceContainer;
  private View mChoiceLine;
  private TextView mChoiceTitle;
  private Context mContext;
  private TextView mListTime;
  private RelativeLayout mRowTitle;
  private ListView mScheduleList;
  LinearLayout.LayoutParams mct;

  public DrivingScheduleChoiceWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  public int getDecreasedHeight()
  {
    int i = 799;
    if (getResources().getConfiguration().orientation == 2)
      i = 100 + getResources().getDimensionPixelSize(2131427352);
    while (true)
    {
      return i;
      if (this.mScheduleList.getAdapter().getCount() != 2)
        continue;
      i = 550;
    }
  }

  public int getProperHeight()
  {
    return 0;
  }

  public void initViewScheduleChoice()
  {
    if (MidasSettings.isNightMode())
    {
      this.mChoiceBody.setBackgroundResource(2130837875);
      this.mChoiceTitle.setTextColor(-2039584);
    }
    while (true)
    {
      return;
      this.mChoiceBody.setBackgroundResource(2130837588);
      this.mChoiceTitle.setTextColor(-16777216);
    }
  }

  public void initViewScheduleChoiceMini()
  {
    if (MidasSettings.isNightMode())
      if (this.choice_orientation == 1)
      {
        this.mChoiceBody.setBackgroundResource(2130837874);
        this.mRowTitle.setVisibility(8);
      }
    while (true)
    {
      return;
      this.mChoiceBody.setBackgroundResource(2130837875);
      this.mRowTitle.setVisibility(0);
      continue;
      if (this.choice_orientation == 1)
      {
        this.mChoiceBody.setBackgroundResource(2130837590);
        this.mRowTitle.setVisibility(8);
        continue;
      }
      this.mChoiceBody.setBackgroundResource(2130837588);
      this.mRowTitle.setVisibility(0);
    }
  }

  public void initialize(List<ScheduleEvent> paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.mActionListener = paramWidgetActionListener;
    if (paramList != null)
    {
      ScheduleAdapter localScheduleAdapter = new ScheduleAdapter(getContext(), paramList);
      this.mScheduleList.setAdapter(localScheduleAdapter);
    }
    this.mChoiceContainer = ((LinearLayout)findViewById(2131558961));
    initViewScheduleChoice();
  }

  public boolean isDecreasedSize()
  {
    return false;
  }

  public boolean isTranslated()
  {
    return false;
  }

  public boolean isWidgetReplaceable()
  {
    return false;
  }

  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (MidasSettings.isMultiwindowedMode())
      if (MidasSettings.isNightMode())
        if (paramConfiguration.orientation == 1)
        {
          this.mChoiceBody.setBackgroundResource(2130837874);
          this.mRowTitle.setVisibility(8);
        }
    while (true)
    {
      return;
      this.mChoiceBody.setBackgroundResource(2130837875);
      this.mRowTitle.setVisibility(0);
      continue;
      if (paramConfiguration.orientation == 1)
      {
        this.mChoiceBody.setBackgroundResource(2130837590);
        this.mRowTitle.setVisibility(8);
        continue;
      }
      this.mChoiceBody.setBackgroundResource(2130837588);
      this.mRowTitle.setVisibility(0);
      continue;
      initViewScheduleChoice();
    }
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.mScheduleList = ((ListView)findViewById(2131558893));
    this.mScheduleList.setDivider(null);
    this.mRowTitle = ((RelativeLayout)findViewById(2131558947));
    this.mChoiceBody = ((RelativeLayout)findViewById(2131558960));
    this.mListTime = ((TextView)findViewById(2131558709));
    this.mChoiceTitle = ((TextView)findViewById(2131558962));
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    setListViewHeightBasedOnChildren(this.mScheduleList, true);
    super.onMeasure(paramInt1, paramInt2);
  }

  protected void setListViewHeightBasedOnChildren(ListView paramListView, boolean paramBoolean)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null);
    while (true)
    {
      return;
      if ((paramBoolean) || (paramListView.getLayoutParams().height <= 0))
      {
        int i = 0;
        int j = localListAdapter.getCount();
        int k = 0;
        while (true)
          if (k < j)
          {
            View localView = localListAdapter.getView(k, null, paramListView);
            try
            {
              localView.measure(0, 0);
              i += localView.getMeasuredHeight();
              k++;
            }
            catch (Exception localException)
            {
              while (true)
                localException.printStackTrace();
            }
          }
        ViewGroup.LayoutParams localLayoutParams = paramListView.getLayoutParams();
        localLayoutParams.height = (i + paramListView.getDividerHeight() * (-1 + localListAdapter.getCount()));
        paramListView.setLayoutParams(localLayoutParams);
        paramListView.requestLayout();
        continue;
      }
    }
  }

  public void setMinimize(boolean paramBoolean)
  {
    Configuration localConfiguration = getResources().getConfiguration();
    if (paramBoolean)
      if (MidasSettings.isNightMode())
        if (localConfiguration.orientation == 1)
        {
          this.mChoiceBody.setBackgroundResource(2130837874);
          this.mRowTitle.setVisibility(8);
        }
    while (true)
    {
      return;
      this.mChoiceBody.setBackgroundResource(2130837875);
      this.mRowTitle.setVisibility(0);
      continue;
      if (localConfiguration.orientation == 1)
      {
        this.mChoiceBody.setBackgroundResource(2130837590);
        this.mRowTitle.setVisibility(8);
        continue;
      }
      this.mChoiceBody.setBackgroundResource(2130837588);
      this.mRowTitle.setVisibility(0);
      continue;
      if (MidasSettings.isNightMode())
      {
        this.mChoiceBody.setBackgroundResource(2130837875);
        this.mChoiceTitle.setTextColor(-2039584);
        continue;
      }
      this.mChoiceBody.setBackgroundResource(2130837588);
      this.mChoiceTitle.setTextColor(-16777216);
    }
  }

  public int setNightMode(boolean paramBoolean)
  {
    return 0;
  }

  public int setWidgetToDecreasedSize(boolean paramBoolean)
  {
    setMinimize(paramBoolean);
    return 0;
  }

  public void startAnimationTranslate(View paramView)
  {
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
      return DrivingScheduleChoiceWidget.this.getLimitedCount(i);
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
      label120: Date localDate;
      label211: label242: LinearLayout localLinearLayout;
      if (paramView == null)
      {
        paramView = this.mInflater.inflate(2130903089, null);
        localViewHolder = new ViewHolder();
        localViewHolder.title = ((TextView)paramView.findViewById(2131558708));
        localViewHolder.date = ((TextView)paramView.findViewById(2131558710));
        localViewHolder.time = ((TextView)paramView.findViewById(2131558709));
        localViewHolder.divider = paramView.findViewById(2131558694);
        paramView.setTag(localViewHolder);
        localScheduleEvent = (ScheduleEvent)this.mEvents.get(paramInt);
        if (!StringUtils.isNullOrWhiteSpace(localScheduleEvent.getTitle()))
          break label375;
        localViewHolder.title.setText(2131362571);
        long l = localScheduleEvent.getBegin();
        if (l == 0L)
          break label452;
        localDate = new Date(l);
        String str = Settings.System.getString(DrivingScheduleChoiceWidget.this.getContext().getContentResolver(), "date_format");
        if (!localScheduleEvent.getAllDay())
          break label391;
        localViewHolder.time.setText(DrivingScheduleChoiceWidget.this.mContext.getResources().getString(2131362551) + " ");
        if (str == null)
          break label425;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("EEE. dd MMM. yyyy");
        localViewHolder.date.setText(localSimpleDateFormat.format(localDate));
        if (getCount() == 1)
          break label526;
        if (paramInt != 0)
          break label488;
        paramView.setBackgroundResource(2130837820);
        if (getCount() == 1)
          localViewHolder.divider.setVisibility(0);
        label277: localLinearLayout = (LinearLayout)paramView.findViewById(2131558707);
        if (localLinearLayout != null)
        {
          if (!MidasSettings.isMultiwindowedMode())
            break label559;
          localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, DrivingScheduleChoiceWidget.this.getResources().getDimensionPixelSize(2131427342)));
        }
        label325: if (!MidasSettings.isNightMode())
          break label652;
        localViewHolder.title.setTextColor(-2039584);
        localViewHolder.time.setTextColor(-2039584);
        localViewHolder.divider.setBackgroundColor(-16777216);
      }
      while (true)
      {
        return paramView;
        localViewHolder = (ViewHolder)paramView.getTag();
        break;
        label375: localViewHolder.title.setText(localScheduleEvent.getTitle());
        break label120;
        label391: localViewHolder.time.setText(localScheduleEvent.getBeginTime() + " ");
        break label211;
        label425: java.text.DateFormat localDateFormat = android.text.format.DateFormat.getDateFormat(this.context);
        localViewHolder.date.setText(localDateFormat.format(localDate));
        break label242;
        label452: if (DrivingScheduleChoiceWidget.this.isMulti)
        {
          localViewHolder.time.setVisibility(8);
          break label242;
        }
        localViewHolder.time.setVisibility(8);
        break label242;
        label488: if (paramInt >= -1 + getCount())
        {
          paramView.setBackgroundResource(2130837817);
          localViewHolder.divider.setVisibility(0);
          break label277;
        }
        paramView.setBackgroundResource(2130837819);
        break label277;
        label526: DrivingScheduleChoiceWidget.this.mScheduleList.setPadding(2, 2, 2, 2);
        paramView.setBackgroundResource(2130837818);
        localViewHolder.divider.setVisibility(0);
        break label277;
        label559: if (DrivingScheduleChoiceWidget.this.getResources().getConfiguration().orientation == 1)
        {
          localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, DrivingScheduleChoiceWidget.this.getResources().getDimensionPixelSize(2131427341)));
          break label325;
        }
        if (DrivingScheduleChoiceWidget.this.getResources().getConfiguration().orientation != 2)
          break label325;
        localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, DrivingScheduleChoiceWidget.this.getResources().getDimensionPixelSize(2131427342)));
        break label325;
        label652: localViewHolder.title.setTextColor(-16777216);
        localViewHolder.time.setTextColor(-14711891);
        localViewHolder.divider.setBackgroundColor(-5262925);
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
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingScheduleChoiceWidget
 * JD-Core Version:    0.6.0
 */