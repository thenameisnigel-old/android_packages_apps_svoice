package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.types.MessageType;
import com.vlingo.core.internal.dialogmanager.types.RecipientType;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract class MessageWidgetBase extends BargeInWidget<MessageType>
{
  TextView contactName;
  Context context;
  WidgetActionListener listener;
  LinearLayout messageButtonContainer;
  LinearLayout messageContainer;
  LinearLayout messageReadBackButtonContainer;
  TextView msgBody;
  String name;
  protected ArrayList<String> receiverAddresses;
  LinearLayout subjRow;
  String text;

  public MessageWidgetBase(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
  }

  private String getRecipientListAsString(List<RecipientType> paramList)
  {
    String str = "";
    if (paramList != null)
    {
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        RecipientType localRecipientType = (RecipientType)localIterator.next();
        if (str.length() != 0)
          str = str + getContext().getString(2131362062) + getContext().getString(2131362061);
        if (!StringUtils.isNullOrWhiteSpace(localRecipientType.getDisplayName()))
        {
          str = str + localRecipientType.getDisplayName();
          continue;
        }
        str = str + localRecipientType.getAddress();
      }
    }
    return str;
  }

  protected void editInNativeSmsApp()
  {
    if (this.receiverAddresses != null)
    {
      Iterator localIterator = this.receiverAddresses.iterator();
      while (localIterator.hasNext())
      {
        Intent localIntent = new Intent("android.intent.action.SENDTO", Uri.fromParts("smsto", (String)localIterator.next(), null));
        localIntent.putExtra("sms_body", this.msgBody.getText().toString());
        getContext().startActivity(localIntent);
      }
    }
  }

  public void initialize(MessageType paramMessageType, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.listener = paramWidgetActionListener;
    this.receiverAddresses = null;
    if (paramMessageType == null)
      return;
    List localList = paramMessageType.getRecipients();
    ArrayList localArrayList = new ArrayList();
    if (localList != null)
    {
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        RecipientType localRecipientType = (RecipientType)localIterator.next();
        if (StringUtils.isNullOrWhiteSpace(localRecipientType.getAddress()))
          continue;
        localArrayList.add(localRecipientType.getAddress());
      }
      this.receiverAddresses = new ArrayList(localArrayList);
    }
    for (this.name = getRecipientListAsString(paramMessageType.getRecipients()); ; this.name = getResources().getString(2131362733))
    {
      this.text = paramMessageType.getMessage();
      String str = Settings.getLanguageApplication();
      if ((!StringUtils.isNullOrWhiteSpace(str)) && (str.equals("en-US")));
      this.contactName.setText(this.name);
      this.msgBody.setText(this.text);
      break;
    }
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.messageContainer = ((LinearLayout)findViewById(2131558928));
    this.messageButtonContainer = ((LinearLayout)findViewById(2131559054));
    this.messageReadBackButtonContainer = ((LinearLayout)findViewById(2131559058));
    this.contactName = ((TextView)findViewById(2131558898));
    this.msgBody = ((TextView)findViewById(2131558900));
    this.msgBody.setOnFocusChangeListener(new MessageWidgetBase.1(this));
  }

  protected void openNativeSmsApp()
  {
    if (this.receiverAddresses != null)
    {
      Iterator localIterator = this.receiverAddresses.iterator();
      while (localIterator.hasNext())
      {
        Intent localIntent = new Intent("android.intent.action.SENDTO", Uri.fromParts("smsto", (String)localIterator.next(), null));
        getContext().startActivity(localIntent);
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.MessageWidgetBase
 * JD-Core Version:    0.6.0
 */