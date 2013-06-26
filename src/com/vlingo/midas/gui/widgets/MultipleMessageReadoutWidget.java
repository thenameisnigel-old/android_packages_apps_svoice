package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.DecoratorType;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.types.MessageType;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.StringUtils;
import java.util.List;

public class MultipleMessageReadoutWidget extends BargeInWidget<List<MessageType>>
  implements AdapterView.OnItemClickListener
{
  protected static final int TRIMMED_MAX_MESSAGE_LENGTH = 30;
  protected Context context;
  protected WidgetActionListener listener;
  protected ListView mMultipleMessages;
  private List<MessageType> messages;
  protected boolean showMessageBody;

  public MultipleMessageReadoutWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    this.showMessageBody = false;
  }

  public void initialize(List paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.messages = paramList;
    this.listener = paramWidgetActionListener;
    if ((paramWidgetDecorator != null) && (paramWidgetDecorator.has(DecoratorType.ShowMessageBody)))
      this.showMessageBody = true;
    if (this.messages != null)
    {
      if (this.messages.size() > ClientSuppliedValues.getMaxDisplayNumber())
        this.messages = this.messages.subList(0, ClientSuppliedValues.getMaxDisplayNumber());
      this.mMultipleMessages.post(new Runnable(paramList)
      {
        public void run()
        {
          MultipleMessageReadoutWidget.ListAdapterMessages localListAdapterMessages = new MultipleMessageReadoutWidget.ListAdapterMessages(MultipleMessageReadoutWidget.this, MultipleMessageReadoutWidget.this.context, this.val$msgs);
          MultipleMessageReadoutWidget.this.mMultipleMessages.setAdapter(localListAdapterMessages);
          MultipleMessageReadoutWidget.this.mMultipleMessages.setOnItemClickListener(MultipleMessageReadoutWidget.this);
          localListAdapterMessages.notifyDataSetChanged();
        }
      });
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
    this.mMultipleMessages = ((ListView)findViewById(2131558893));
    this.mMultipleMessages.setDivider(null);
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    setListViewHeightBasedOnChildren(this.mMultipleMessages);
    super.onMeasure(paramInt1, paramInt2);
  }

  protected class ListAdapterMessages extends BaseAdapter
  {
    private final List list;

    public ListAdapterMessages(Context arg2)
    {
      this.list = null;
    }

    public ListAdapterMessages(Context paramList, List arg3)
    {
      Object localObject;
      this.list = localObject;
    }

    private String getDisplayMessage(MessageType paramMessageType, boolean paramBoolean)
    {
      if (paramBoolean)
      {
        str = paramMessageType.getMessage();
        if (str.length() <= 30);
      }
      for (String str = paramMessageType.getMessage().substring(0, 30) + "..."; ; str = MultipleMessageReadoutWidget.this.context.getResources().getString(2131361797))
        return str;
    }

    public int getCount()
    {
      int i = this.list.size();
      return MultipleMessageReadoutWidget.this.getLimitedCount(i);
    }

    public Object getItem(int paramInt)
    {
      return this.list.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      MessageType localMessageType = (MessageType)this.list.get(paramInt);
      TextView localTextView1;
      if (paramView == null)
      {
        paramView = View.inflate(MultipleMessageReadoutWidget.this.getContext(), 2130903095, null);
        MultipleMessageReadoutWidget.ViewHolder localViewHolder = new MultipleMessageReadoutWidget.ViewHolder(MultipleMessageReadoutWidget.this);
        localViewHolder.from = ((TextView)paramView.findViewById(2131558699));
        localViewHolder.body = ((TextView)paramView.findViewById(2131558701));
        paramView.setTag(localViewHolder);
        localTextView1 = (TextView)paramView.findViewById(2131558699);
        if (StringUtils.isNullOrWhiteSpace(localMessageType.getAddress()))
          break label234;
        localTextView1.setText("From:  " + localMessageType.getContactName() + " (" + localMessageType.getAddress() + ")");
        label148: TextView localTextView2 = (TextView)paramView.findViewById(2131558700);
        if (localTextView2 != null)
          localTextView2.setVisibility(8);
        ((TextView)paramView.findViewById(2131558701)).setText(getDisplayMessage(localMessageType, MultipleMessageReadoutWidget.this.showMessageBody));
        paramView.setOnClickListener(new MultipleMessageReadoutWidget.ListAdapterMessages.1(this, paramInt, localMessageType));
        if (paramInt != 0)
          break label265;
        paramView.setBackgroundResource(2130837820);
      }
      while (true)
      {
        return paramView;
        ((MultipleMessageReadoutWidget.ViewHolder)paramView.getTag());
        break;
        label234: localTextView1.setText("From:  " + localMessageType.getContactName());
        break label148;
        label265: if (paramInt >= -1 + getCount())
        {
          paramView.setBackgroundResource(2130837817);
          continue;
        }
        paramView.setBackgroundResource(2130837819);
      }
    }
  }

  class ViewHolder
  {
    TextView body;
    View divider;
    TextView from;

    ViewHolder()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.MultipleMessageReadoutWidget
 * JD-Core Version:    0.6.0
 */