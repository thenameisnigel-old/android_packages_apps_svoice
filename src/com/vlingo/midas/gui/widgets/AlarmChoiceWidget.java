package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.util.Alarm;
import java.util.List;

public class AlarmChoiceWidget extends BargeInWidget<List<Alarm>>
  implements AdapterView.OnItemClickListener
{
  private static final String INTENT_ADD_ALARM_ACTION = "com.sec.android.clockpackage.ADD_ALARM";
  private static final String INTENT_EDIT_ALARM_ACTION = "com.sec.android.clockpackage.EDIT_ALARM";
  private static final String INTENT_EDIT_ALARM_EXTRA = "listitemId";
  private WidgetActionListener mActionListener;
  private ListView mAlarmList;
  private Context mContext;
  private int mHour;
  private int mMin;
  private int mTempHour;
  private WidgetUtil.WidgetKey widgetKey;

  public AlarmChoiceWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  private void openAlarmApp(int paramInt)
  {
    switch (1.$SwitchMap$com$vlingo$core$internal$dialogmanager$util$WidgetUtil$WidgetKey[this.widgetKey.ordinal()])
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      return;
      Intent localIntent2 = new Intent("com.sec.android.clockpackage.EDIT_ALARM");
      localIntent2.putExtra("listitemId", paramInt);
      getContext().sendBroadcast(localIntent2);
      continue;
      Intent localIntent1 = new Intent("android.intent.action.SET_ALARM");
      localIntent1.putExtra("android.intent.extra.alarm.MESSAGE", getContext().getResources().getString(2131361859));
      localIntent1.putExtra("android.intent.extra.alarm.HOUR", this.mHour);
      localIntent1.putExtra("android.intent.extra.alarm.MINUTES", this.mMin);
      localIntent1.putExtra("android.intent.extra.alarm.SKIP_UI", true);
      getContext().sendBroadcast(localIntent1);
    }
  }

  public void initialize(List<Alarm> paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.mActionListener = paramWidgetActionListener;
    this.widgetKey = paramWidgetKey;
    if (paramList != null)
      this.mAlarmList.setAdapter(new AlarmAdapter(getContext(), paramList));
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
    this.mAlarmList = ((ListView)findViewById(2131558893));
    this.mAlarmList.setDivider(null);
    this.mAlarmList.setOnItemClickListener(this);
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    setListViewHeightBasedOnChildren(this.mAlarmList);
    super.onMeasure(paramInt1, paramInt2);
  }

  private class AlarmAdapter extends BaseAdapter
  {
    private final List<Alarm> mAlarms;
    private final LayoutInflater mInflater;

    public AlarmAdapter(List<Alarm> arg2)
    {
      Context localContext;
      this.mInflater = LayoutInflater.from(localContext);
      Object localObject;
      this.mAlarms = localObject;
    }

    public int getCount()
    {
      int i = this.mAlarms.size();
      return AlarmChoiceWidget.this.getLimitedCount(i);
    }

    public Object getItem(int paramInt)
    {
      return this.mAlarms.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      ViewHolder localViewHolder;
      Alarm localAlarm;
      if (paramView == null)
      {
        paramView = this.mInflater.inflate(2130903081, null);
        localViewHolder = new ViewHolder();
        localViewHolder.mHourText = ((TextView)paramView.findViewById(2131558674));
        localViewHolder.mMinText = ((TextView)paramView.findViewById(2131558676));
        localViewHolder.mColon = ((TextView)paramView.findViewById(2131558675));
        localViewHolder.ampm = ((TextView)paramView.findViewById(2131558677));
        localViewHolder.enableAlarm = ((ImageView)paramView.findViewById(2131558685));
        localViewHolder.mAlarmDivider = paramView.findViewById(2131558686);
        localViewHolder.mSun = ((TextView)paramView.findViewById(2131558678));
        localViewHolder.mMon = ((TextView)paramView.findViewById(2131558679));
        localViewHolder.mTue = ((TextView)paramView.findViewById(2131558680));
        localViewHolder.mWed = ((TextView)paramView.findViewById(2131558681));
        localViewHolder.mThu = ((TextView)paramView.findViewById(2131558682));
        localViewHolder.mFri = ((TextView)paramView.findViewById(2131558683));
        localViewHolder.mSat = ((TextView)paramView.findViewById(2131558684));
        paramView.setTag(localViewHolder);
        localAlarm = (Alarm)this.mAlarms.get(paramInt);
        AlarmChoiceWidget.access$002(AlarmChoiceWidget.this, localAlarm.getHour());
        AlarmChoiceWidget.access$102(AlarmChoiceWidget.this, localAlarm.getMinute());
        AlarmChoiceWidget.access$202(AlarmChoiceWidget.this, AlarmChoiceWidget.this.mHour);
        if (AlarmChoiceWidget.this.mHour <= 12)
          break label657;
        localViewHolder.ampm.setText(2131362520);
        label288: boolean bool = DateFormat.is24HourFormat(AlarmChoiceWidget.this.mContext);
        if (!bool)
          break label718;
        localViewHolder.ampm.setVisibility(4);
        label314: if ((AlarmChoiceWidget.this.mHour >= 10) || (!bool))
          break label772;
        localViewHolder.mHourText.setText("0" + String.valueOf(AlarmChoiceWidget.this.mTempHour));
        label367: localViewHolder.mColon.setText(":");
        if (AlarmChoiceWidget.this.mMin >= 10)
          break label793;
        localViewHolder.mMinText.setText("0" + String.valueOf(AlarmChoiceWidget.this.mMin));
        label425: if (!localAlarm.isActive())
          break label814;
        localViewHolder.enableAlarm.setBackgroundDrawable(AlarmChoiceWidget.this.getResources().getDrawable(2130838104));
        label453: int i = localAlarm.getDayMask();
        int j = AlarmChoiceWidget.this.getResources().getColor(2131230728);
        if ((0x1000000 & i) != 0)
          localViewHolder.mSun.setTextColor(j);
        if ((0x100000 & i) != 0)
          localViewHolder.mMon.setTextColor(j);
        if ((0x10000 & i) != 0)
          localViewHolder.mTue.setTextColor(j);
        if ((i & 0x1000) != 0)
          localViewHolder.mWed.setTextColor(j);
        if ((i & 0x100) != 0)
          localViewHolder.mThu.setTextColor(j);
        if ((i & 0x10) != 0)
          localViewHolder.mFri.setTextColor(j);
        if ((i & 0x1) != 0)
          localViewHolder.mSat.setTextColor(j);
        if (paramInt != 0)
          break label838;
        paramView.setBackgroundResource(2130837820);
        if (getCount() == 1)
          localViewHolder.mAlarmDivider.setVisibility(4);
      }
      while (true)
      {
        paramView.setOnClickListener(new View.OnClickListener(paramInt, localAlarm)
        {
          public void onClick(View paramView)
          {
            AlarmChoiceWidget.this.retire();
            Intent localIntent = new Intent();
            localIntent.setAction("com.vlingo.core.internal.dialogmanager.Choice");
            localIntent.putExtra("choice", this.val$position);
            if (AlarmChoiceWidget.this.mAlarmList != null)
            {
              ListAdapter localListAdapter = AlarmChoiceWidget.this.mAlarmList.getAdapter();
              if ((localListAdapter == null) || (localListAdapter.getCount() != 1))
                break label98;
              AlarmChoiceWidget.this.openAlarmApp(this.val$alarm.getId());
            }
            while (true)
            {
              return;
              label98: AlarmChoiceWidget.this.mActionListener.handleIntent(localIntent, null);
            }
          }
        });
        return paramView;
        localViewHolder = (ViewHolder)paramView.getTag();
        break;
        label657: if (AlarmChoiceWidget.this.mHour == 0)
        {
          localViewHolder.ampm.setText(2131362516);
          break label288;
        }
        if (AlarmChoiceWidget.this.mHour == 12)
        {
          localViewHolder.ampm.setText(2131362520);
          break label288;
        }
        localViewHolder.ampm.setText(2131362516);
        break label288;
        label718: localViewHolder.ampm.setVisibility(0);
        if (AlarmChoiceWidget.this.mHour == 0)
          AlarmChoiceWidget.access$202(AlarmChoiceWidget.this, 12);
        if (AlarmChoiceWidget.this.mHour <= 12)
          break label314;
        AlarmChoiceWidget.access$220(AlarmChoiceWidget.this, 12);
        break label314;
        label772: localViewHolder.mHourText.setText(String.valueOf(AlarmChoiceWidget.this.mTempHour));
        break label367;
        label793: localViewHolder.mMinText.setText(String.valueOf(AlarmChoiceWidget.this.mMin));
        break label425;
        label814: localViewHolder.enableAlarm.setBackgroundDrawable(AlarmChoiceWidget.this.getResources().getDrawable(2130838103));
        break label453;
        label838: if (paramInt >= -1 + getCount())
        {
          paramView.setBackgroundResource(2130837817);
          localViewHolder.mAlarmDivider.setVisibility(4);
          continue;
        }
        paramView.setBackgroundResource(2130837819);
      }
    }

    class ViewHolder
    {
      TextView ampm;
      ImageView enableAlarm;
      View mAlarmDivider;
      TextView mColon;
      TextView mFri;
      TextView mHourText;
      TextView mMinText;
      TextView mMon;
      TextView mSat;
      TextView mSun;
      TextView mThu;
      TextView mTue;
      TextView mWed;

      ViewHolder()
      {
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.AlarmChoiceWidget
 * JD-Core Version:    0.6.0
 */