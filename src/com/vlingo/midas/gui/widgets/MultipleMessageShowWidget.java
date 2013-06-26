package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.messages.MessageSenderSummary;
import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.core.internal.safereader.SafeReaderAlert;
import com.vlingo.core.internal.util.StringUtils;
import java.util.List;

public class MultipleMessageShowWidget extends MultipleMessageWidget
{
  private List<MessageSenderSummary> senderSummaries;

  public MultipleMessageShowWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.fromReadMessages = true;
  }

  public void initialize(List paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.senderSummaries = paramList;
    this.listener = paramWidgetActionListener;
    if (paramList != null)
      this.mMultipleMessages.post(new Runnable(paramList)
      {
        public void run()
        {
          MultipleMessageShowWidget.ListAdapterLineItems localListAdapterLineItems = new MultipleMessageShowWidget.ListAdapterLineItems(MultipleMessageShowWidget.this, MultipleMessageShowWidget.this.context, this.val$summaries);
          MultipleMessageShowWidget.this.mMultipleMessages.setAdapter(localListAdapterLineItems);
          MultipleMessageShowWidget.this.mMultipleMessages.setOnItemClickListener(MultipleMessageShowWidget.this);
          localListAdapterLineItems.notifyDataSetChanged();
        }
      });
  }

  private class ListAdapterLineItems extends MultipleMessageWidget.ListAdapterMessages
  {
    private final List list;

    public ListAdapterLineItems(List<MessageSenderSummary> arg2)
    {
      super(localContext, localList);
      this.list = localList;
    }

    private String getDisplayMessage(SMSMMSAlert paramSMSMMSAlert, boolean paramBoolean)
    {
      String str1 = paramSMSMMSAlert.getDisplayableMessageText(MultipleMessageShowWidget.this.context);
      if (paramBoolean)
      {
        str2 = str1;
        if (str2.length() <= 30);
      }
      for (String str2 = str1.substring(0, 30) + "..."; ; str2 = MultipleMessageShowWidget.this.context.getResources().getString(2131361797))
        return str2;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      MessageSenderSummary localMessageSenderSummary = (MessageSenderSummary)this.list.get(paramInt);
      SMSMMSAlert localSMSMMSAlert = localMessageSenderSummary.getAlert();
      if (paramView == null)
        paramView = View.inflate(MultipleMessageShowWidget.this.getContext(), 2130903095, null);
      TextView localTextView1 = (TextView)paramView.findViewById(2131558699);
      String str = localSMSMMSAlert.getSenderDisplayName();
      if (StringUtils.isNullOrWhiteSpace(str))
        str = localSMSMMSAlert.getAddress();
      localTextView1.setText("From:  " + str);
      TextView localTextView2 = (TextView)paramView.findViewById(2131558700);
      int i = localMessageSenderSummary.getCount();
      if (localTextView2 != null)
      {
        if (i > 1)
          localTextView2.setText("" + localMessageSenderSummary.getCount());
      }
      else
      {
        ((TextView)paramView.findViewById(2131558701)).setText(getDisplayMessage((SMSMMSAlert)localSMSMMSAlert, true));
        paramView.setOnClickListener(new MultipleMessageShowWidget.ListAdapterLineItems.1(this, localSMSMMSAlert, i));
        if (paramInt != -1 + getCount())
          break label222;
        paramView.setBackgroundResource(2130837817);
      }
      while (true)
      {
        return paramView;
        localTextView2.setVisibility(8);
        break;
        label222: paramView.setBackgroundResource(2130837819);
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.MultipleMessageShowWidget
 * JD-Core Version:    0.6.0
 */