package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.DecoratorType;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.types.MessageReadoutType;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.DrivingWidgetInterface;
import java.util.List;
import java.util.Queue;

public class DrivingMultipleSenderMessageReadoutWidget extends MultipleMessageReadoutWidget
  implements DrivingWidgetInterface
{
  private boolean isMinimized = false;
  private View itemLine;
  private boolean mIsMultipleSender;
  LinearLayout messageContainer;
  private LinearLayout messageListContainer;
  TextView messageTotalCount;
  private List<MessageReadoutType> summaries;

  public DrivingMultipleSenderMessageReadoutWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void setNightModeGui()
  {
    if (MidasSettings.isNightMode())
    {
      if (!this.mIsMultipleSender)
        break label92;
      if ((getResources().getConfiguration().orientation != 2) || (!MidasSettings.isMultiwindowedMode()))
        break label65;
      setBackgroundResource(2130837875);
      setPadding(0, 0, 0, 0);
      this.messageListContainer.setVisibility(0);
      this.messageTotalCount.setTextColor(-2039584);
    }
    while (true)
    {
      return;
      label65: setBackgroundResource(2130837874);
      setPadding(0, 0, 0, 21);
      this.messageListContainer.setVisibility(8);
      break;
      label92: setBackgroundResource(2130837875);
      setPadding(0, 0, 0, 0);
      this.messageTotalCount.setTextColor(-2039584);
    }
  }

  public int getDecreasedHeight()
  {
    int i;
    if (getResources().getConfiguration().orientation == 2)
      i = getResources().getDimensionPixelSize(2131427352);
    while (true)
    {
      return i;
      if (this.mIsMultipleSender)
      {
        i = 254;
        continue;
      }
      i = 594;
    }
  }

  public int getProperHeight()
  {
    if (this.mIsMultipleSender);
    for (int i = 240; ; i = 0)
      return i;
  }

  public void initialize(List paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.summaries = paramList;
    this.listener = paramWidgetActionListener;
    if ((paramWidgetDecorator != null) && (paramWidgetDecorator.has(DecoratorType.ShowMessageBody)))
      this.showMessageBody = true;
    int i = 0;
    for (int j = 0; j < this.summaries.size(); j++)
      i += ((MessageReadoutType)this.summaries.get(j)).getMessageCount();
    Resources localResources = getResources();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(i);
    String str = String.format(localResources.getQuantityString(2131492866, i, arrayOfObject), new Object[0]);
    this.messageTotalCount.setText(str);
    if (this.summaries != null)
    {
      if (this.summaries.size() > ClientSuppliedValues.getMaxDisplayNumber())
        this.summaries = this.summaries.subList(0, ClientSuppliedValues.getMaxDisplayNumber());
      if (this.summaries.size() != 1)
        break label213;
      this.mIsMultipleSender = false;
      if (this.mIsMultipleSender)
        break label221;
      this.mMultipleMessages.post(new Runnable()
      {
        public void run()
        {
          DrivingMultipleSenderMessageReadoutWidget.ListAdapterLineItems localListAdapterLineItems = new DrivingMultipleSenderMessageReadoutWidget.ListAdapterLineItems(DrivingMultipleSenderMessageReadoutWidget.this, DrivingMultipleSenderMessageReadoutWidget.this.context, DrivingMultipleSenderMessageReadoutWidget.this.summaries);
          DrivingMultipleSenderMessageReadoutWidget.this.mMultipleMessages.setAdapter(localListAdapterLineItems);
          DrivingMultipleSenderMessageReadoutWidget.this.mMultipleMessages.setOnItemClickListener(DrivingMultipleSenderMessageReadoutWidget.this);
          localListAdapterLineItems.notifyDataSetChanged();
        }
      });
    }
    while (true)
    {
      setNightModeGui();
      return;
      label213: this.mIsMultipleSender = true;
      break;
      label221: setBackgroundResource(2130837587);
      setPadding(0, 0, 0, 21);
      this.messageListContainer.setVisibility(8);
    }
  }

  public boolean isDecreasedSize()
  {
    return this.isMinimized;
  }

  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (this.mIsMultipleSender)
    {
      if ((getResources().getConfiguration().orientation != 2) || (!MidasSettings.isMultiwindowedMode()))
        break label60;
      setBackgroundResource(2130837588);
      this.messageListContainer.setVisibility(0);
    }
    while (true)
    {
      setPadding(0, 0, 0, 21);
      setNightModeGui();
      return;
      label60: setBackgroundResource(2130837587);
      this.messageListContainer.setVisibility(8);
    }
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.messageContainer = ((LinearLayout)findViewById(2131558928));
    this.messageTotalCount = ((TextView)findViewById(2131558929));
    this.messageListContainer = ((LinearLayout)findViewById(2131558930));
  }

  public int setNightMode(boolean paramBoolean)
  {
    return 0;
  }

  public int setWidgetToDecreasedSize(boolean paramBoolean)
  {
    this.isMinimized = paramBoolean;
    return 0;
  }

  public void startAnimationTranslate(View paramView)
  {
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
      String str1 = paramSMSMMSAlert.getDisplayableMessageText(DrivingMultipleSenderMessageReadoutWidget.this.context);
      if (paramBoolean)
      {
        str2 = str1;
        if (str2.length() <= 30);
      }
      for (String str2 = str1.substring(0, 30) + "..."; ; str2 = DrivingMultipleSenderMessageReadoutWidget.this.context.getResources().getString(2131361797))
        return str2;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      MessageReadoutType localMessageReadoutType = (MessageReadoutType)this.list.get(paramInt);
      if (paramView == null)
        paramView = View.inflate(DrivingMultipleSenderMessageReadoutWidget.this.getContext(), 2130903086, null);
      TextView localTextView1 = (TextView)paramView.findViewById(2131558699);
      View localView = paramView.findViewById(2131558702);
      localTextView1.setText(localMessageReadoutType.getDisplayName());
      if (MidasSettings.isNightMode())
      {
        localTextView1.setTextColor(-2039584);
        localView.setBackgroundColor(-16777216);
      }
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
        localTextView3.setText(getDisplayMessage(localSMSMMSAlert, true));
        DrivingMultipleSenderMessageReadoutWidget.ListAdapterLineItems.1 local1 = new DrivingMultipleSenderMessageReadoutWidget.ListAdapterLineItems.1(this, paramInt, localSMSMMSAlert, i);
        paramView.setOnClickListener(local1);
        if (paramInt != 0)
          break label220;
        paramView.setBackgroundResource(2130837820);
      }
      while (true)
      {
        return paramView;
        localTextView2.setVisibility(8);
        break;
        label220: if (paramInt >= -1 + getCount())
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
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingMultipleSenderMessageReadoutWidget
 * JD-Core Version:    0.6.0
 */