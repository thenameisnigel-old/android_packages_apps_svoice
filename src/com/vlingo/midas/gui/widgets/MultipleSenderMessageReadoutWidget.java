package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.DecoratorType;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.types.MessageReadoutType;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import java.util.List;
import java.util.Queue;

public class MultipleSenderMessageReadoutWidget extends MultipleMessageReadoutWidget
{
  private List<MessageReadoutType> summaries;

  public MultipleSenderMessageReadoutWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void initialize(List paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.summaries = paramList;
    this.listener = paramWidgetActionListener;
    if ((paramWidgetDecorator != null) && (paramWidgetDecorator.has(DecoratorType.ShowMessageBody)))
      this.showMessageBody = true;
    if (this.summaries != null)
    {
      if (this.summaries.size() > ClientSuppliedValues.getMaxDisplayNumber())
        this.summaries = this.summaries.subList(0, ClientSuppliedValues.getMaxDisplayNumber());
      this.mMultipleMessages.post(new Runnable()
      {
        public void run()
        {
          MultipleSenderMessageReadoutWidget.ListAdapterLineItems localListAdapterLineItems = new MultipleSenderMessageReadoutWidget.ListAdapterLineItems(MultipleSenderMessageReadoutWidget.this, MultipleSenderMessageReadoutWidget.this.context, MultipleSenderMessageReadoutWidget.this.summaries);
          MultipleSenderMessageReadoutWidget.this.mMultipleMessages.setAdapter(localListAdapterLineItems);
          MultipleSenderMessageReadoutWidget.this.mMultipleMessages.setOnItemClickListener(MultipleSenderMessageReadoutWidget.this);
          localListAdapterLineItems.notifyDataSetChanged();
        }
      });
    }
  }

  private class ListAdapterLineItems extends MultipleMessageReadoutWidget.ListAdapterMessages
  {
    private final List list;

    public ListAdapterLineItems(List<MessageReadoutType> arg2)
    {
      super(localContext, localList);
      this.list = localList;
    }

    private String getDisplayMessage(SMSMMSAlert paramSMSMMSAlert, boolean paramBoolean)
    {
      String str1 = paramSMSMMSAlert.getDisplayableMessageText(MultipleSenderMessageReadoutWidget.this.context);
      if (paramBoolean)
      {
        str2 = str1;
        if (str2.length() <= 30);
      }
      for (String str2 = str1.substring(0, 30) + "..."; ; str2 = MultipleSenderMessageReadoutWidget.this.context.getResources().getString(2131361797))
        return str2;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      MessageReadoutType localMessageReadoutType = (MessageReadoutType)this.list.get(paramInt);
      if (paramView == null)
        paramView = View.inflate(MultipleSenderMessageReadoutWidget.this.getContext(), 2130903095, null);
      TextView localTextView1 = (TextView)paramView.findViewById(2131558699);
      String str = localMessageReadoutType.getDisplayName();
      localTextView1.setText("From:  " + str);
      TextView localTextView2 = (TextView)paramView.findViewById(2131558700);
      int i = localMessageReadoutType.getMessageCount();
      if (localTextView2 != null)
      {
        if (i > 1)
          localTextView2.setText("" + localMessageReadoutType.getMessageCount());
      }
      else
      {
        TextView localTextView3 = (TextView)paramView.findViewById(2131558701);
        SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)localMessageReadoutType.getMessagesFromSender().peek();
        localTextView3.setText(getDisplayMessage(localSMSMMSAlert, MultipleSenderMessageReadoutWidget.this.showMessageBody));
        paramView.setOnClickListener(new MultipleSenderMessageReadoutWidget.ListAdapterLineItems.1(this, paramInt, localSMSMMSAlert, i));
        if (paramInt != 0)
          break label216;
        paramView.setBackgroundResource(2130837820);
      }
      while (true)
      {
        return paramView;
        localTextView2.setVisibility(8);
        break;
        label216: if (paramInt >= -1 + getCount())
        {
          paramView.setBackgroundResource(2130837817);
          continue;
        }
        paramView.setBackgroundResource(2130837819);
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.MultipleSenderMessageReadoutWidget
 * JD-Core Version:    0.6.0
 */