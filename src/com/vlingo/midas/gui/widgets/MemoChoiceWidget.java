package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.Settings.System;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.memo.Memo;
import com.vlingo.core.internal.schedule.DateUtil;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.settings.MidasSettings;
import java.util.List;
import java.util.Locale;

public class MemoChoiceWidget extends BargeInWidget<List<Memo>>
{
  private final Context context;
  private WidgetActionListener listener;
  private ListView mListView;

  public MemoChoiceWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
  }

  public void initialize(List<Memo> paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.listener = paramWidgetActionListener;
    if (paramList == null);
    while (true)
    {
      return;
      MemoAdapter localMemoAdapter = new MemoAdapter(paramList);
      this.mListView.setDivider(null);
      this.mListView.setAdapter(localMemoAdapter);
      localMemoAdapter.notifyDataSetChanged();
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
    this.mListView = ((ListView)findViewById(2131559051));
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    measureListviewHeight(this.mListView, 2131427345, true);
    super.onMeasure(paramInt1, paramInt2);
  }

  public class MemoAdapter extends BaseAdapter
  {
    private final LayoutInflater mInflater;
    private final List<Memo> memoList;

    public MemoAdapter()
    {
      Object localObject;
      this.memoList = localObject;
      this.mInflater = LayoutInflater.from(MemoChoiceWidget.this.context);
    }

    public int getCount()
    {
      int i = this.memoList.size();
      return MemoChoiceWidget.this.getLimitedCount(i);
    }

    public Object getItem(int paramInt)
    {
      return this.memoList.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      Memo localMemo = (Memo)this.memoList.get(paramInt);
      ViewHolder localViewHolder;
      label137: String str3;
      String str4;
      if (paramView == null)
      {
        paramView = this.mInflater.inflate(2130903094, null);
        localViewHolder = new ViewHolder();
        localViewHolder.content = ((TextView)paramView.findViewById(2131558758));
        localViewHolder.date = ((TextView)paramView.findViewById(2131558759));
        localViewHolder.time = ((TextView)paramView.findViewById(2131558760));
        localViewHolder.divider = paramView.findViewById(2131558761);
        paramView.setTag(localViewHolder);
        if (paramInt != 0)
          break label280;
        paramView.setBackgroundResource(2130837859);
        if (paramView.getBackground() == MemoChoiceWidget.this.getResources().getDrawable(2130837859));
        localViewHolder.divider.setVisibility(4);
        boolean bool = DateFormat.is24HourFormat(MemoChoiceWidget.this.context);
        String str1 = Settings.System.getString(MemoChoiceWidget.this.getContext().getContentResolver(), "date_format");
        Locale localLocale = MidasSettings.getCurrentLocale();
        long l = Long.parseLong(localMemo.getDate());
        String str2 = DateUtil.formatMemoDate(l, str1, localLocale);
        str3 = DateUtil.formatMemoTime(l, bool, localLocale);
        localViewHolder.date.setText(str2);
        str4 = localMemo.getMemoName(false);
        if (!StringUtils.isNullOrWhiteSpace(str4))
          break label318;
        localViewHolder.content.setText(2131362571);
      }
      while (true)
      {
        localViewHolder.time.setText(str3);
        1 local1 = new View.OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            Intent localIntent = new Intent();
            localIntent.setAction("com.vlingo.core.internal.dialogmanager.Choice");
            localIntent.putExtra("choice", this.val$position);
            Memo localMemo = (Memo)MemoChoiceWidget.MemoAdapter.this.memoList.get(this.val$position);
            MemoChoiceWidget.this.listener.handleIntent(localIntent, localMemo);
          }
        };
        paramView.setOnClickListener(local1);
        return paramView;
        localViewHolder = (ViewHolder)paramView.getTag();
        break;
        label280: if (paramInt >= -1 + getCount())
        {
          paramView.setBackgroundResource(2130837857);
          localViewHolder.divider.setVisibility(0);
          break label137;
        }
        paramView.setBackgroundResource(2130837858);
        break label137;
        label318: if ((str4 != null) && (str4.endsWith(".snb")))
        {
          localViewHolder.content.setText(str4.substring(0, -4 + str4.length()));
          continue;
        }
        String str5 = localMemo.getTitle();
        if (StringUtils.isNullOrWhiteSpace(str5))
        {
          str5 = localMemo.getContent();
          if (StringUtils.isNullOrWhiteSpace(str5))
            str5 = localMemo.getText();
        }
        localViewHolder.content.setText(str5);
      }
    }

    class ViewHolder
    {
      TextView content;
      TextView date;
      View divider;
      TextView time;

      ViewHolder()
      {
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.MemoChoiceWidget
 * JD-Core Version:    0.6.0
 */