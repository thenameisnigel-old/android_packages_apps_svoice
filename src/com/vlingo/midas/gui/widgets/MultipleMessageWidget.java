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
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.types.MessageType;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.StringUtils;
import java.util.List;

public class MultipleMessageWidget extends BargeInWidget<List<MessageType>>
  implements AdapterView.OnItemClickListener
{
  protected static final int TRIMMED_MAX_MESSAGE_LENGTH = 30;
  protected Context context;
  protected boolean fromReadMessages;
  protected WidgetActionListener listener;
  protected ListView mMultipleMessages;
  private List<MessageType> messages;

  public MultipleMessageWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    this.fromReadMessages = false;
  }

  public void initialize(List paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.messages = paramList;
    this.listener = paramWidgetActionListener;
    if (this.messages != null)
      this.mMultipleMessages.post(new Runnable(paramList)
      {
        public void run()
        {
          MultipleMessageWidget.ListAdapterMessages localListAdapterMessages = new MultipleMessageWidget.ListAdapterMessages(MultipleMessageWidget.this, MultipleMessageWidget.this.context, this.val$msgs);
          MultipleMessageWidget.this.mMultipleMessages.setAdapter(localListAdapterMessages);
          MultipleMessageWidget.this.mMultipleMessages.setOnItemClickListener(MultipleMessageWidget.this);
          localListAdapterMessages.notifyDataSetChanged();
        }
      });
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
      for (String str = paramMessageType.getMessage().substring(0, 30) + "..."; ; str = MultipleMessageWidget.this.context.getResources().getString(2131361797))
        return str;
    }

    public int getCount()
    {
      int i = this.list.size();
      return MultipleMessageWidget.this.getLimitedCount(i);
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
      label148: String str;
      if (paramView == null)
      {
        paramView = View.inflate(MultipleMessageWidget.this.getContext(), 2130903095, null);
        MultipleMessageWidget.ViewHolder localViewHolder = new MultipleMessageWidget.ViewHolder(MultipleMessageWidget.this);
        localViewHolder.from = ((TextView)paramView.findViewById(2131558699));
        localViewHolder.body = ((TextView)paramView.findViewById(2131558701));
        paramView.setTag(localViewHolder);
        localTextView1 = (TextView)paramView.findViewById(2131558699);
        if (StringUtils.isNullOrWhiteSpace(localMessageType.getAddress()))
          break label252;
        localTextView1.setText("From:  " + localMessageType.getContactName() + " (" + localMessageType.getAddress() + ")");
        TextView localTextView2 = (TextView)paramView.findViewById(2131558700);
        if (localTextView2 != null)
          localTextView2.setVisibility(8);
        TextView localTextView3 = (TextView)paramView.findViewById(2131558701);
        if (!MultipleMessageWidget.this.fromReadMessages)
          break label283;
        str = getDisplayMessage(localMessageType, true);
        label201: localTextView3.setText(str);
        paramView.setOnClickListener(new MultipleMessageWidget.ListAdapterMessages.1(this, localMessageType));
        if (paramInt != -1 + getCount())
          break label297;
        paramView.setBackgroundResource(2130837817);
      }
      while (true)
      {
        return paramView;
        ((MultipleMessageWidget.ViewHolder)paramView.getTag());
        break;
        label252: localTextView1.setText("From:  " + localMessageType.getContactName());
        break label148;
        label283: str = getDisplayMessage(localMessageType, ClientSuppliedValues.isReadMessageBodyEnabled());
        break label201;
        label297: paramView.setBackgroundResource(2130837819);
      }
    }
  }

  class ViewHolder
  {
    TextView body;
    TextView from;

    ViewHolder()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.MultipleMessageWidget
 * JD-Core Version:    0.6.0
 */