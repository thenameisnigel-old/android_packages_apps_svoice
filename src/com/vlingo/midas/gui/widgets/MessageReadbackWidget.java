package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.types.MessageType;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.util.CorePackageInfoProvider;
import com.vlingo.core.internal.util.StringUtils;

public class MessageReadbackWidget extends MessageWidgetBase
  implements View.OnClickListener
{
  private final int DETAIL = 2;
  private final int RETIRED = 0;
  private Button callbackBtn;
  private Button replyBtn;

  public MessageReadbackWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void initialize(MessageType paramMessageType, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    super.initialize(paramMessageType, paramWidgetDecorator, paramWidgetKey, paramWidgetActionListener);
    this.msgBody.setEnabled(false);
    if ((StringUtils.isNullOrWhiteSpace(this.name)) || (StringUtils.isNullOrWhiteSpace(this.text)))
    {
      if (this.replyBtn != null)
        this.replyBtn.setVisibility(8);
      if (this.callbackBtn != null)
        this.callbackBtn.setVisibility(8);
      if (this.messageReadBackButtonContainer != null)
        this.messageReadBackButtonContainer.setVisibility(8);
    }
    if (!CorePackageInfoProvider.hasDialing())
      this.callbackBtn.setVisibility(8);
    if ((this.replyBtn != null) && (this.replyBtn.getVisibility() == 0) && (this.callbackBtn != null) && (this.callbackBtn.getVisibility() == 0))
      this.messageContainer.setContentDescription(getAccessibilityString(getContext().getString(2131362318) + this.name + this.text, 2));
    while (true)
    {
      return;
      this.messageContainer.setContentDescription(getAccessibilityString(getContext().getString(2131362318) + this.name + this.text, 0));
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

  public void onClick(View paramView)
  {
    if ((paramView == this.messageContainer) && (this.replyBtn != null) && (this.replyBtn.isShown()) && (this.callbackBtn != null) && (this.callbackBtn.isShown()))
      openNativeSmsApp();
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.messageContainer.setOnClickListener(this);
    this.replyBtn = ((Button)findViewById(2131559059));
    if (this.replyBtn != null)
      this.replyBtn.setOnClickListener(new MessageReadbackWidget.1(this));
    this.callbackBtn = ((Button)findViewById(2131559060));
    if (this.callbackBtn != null)
      this.callbackBtn.setOnClickListener(new MessageReadbackWidget.2(this));
  }

  public void retire()
  {
    super.retire();
    if (this.replyBtn != null)
      this.replyBtn.setVisibility(8);
    if (this.callbackBtn != null)
      this.callbackBtn.setVisibility(8);
    if (this.messageReadBackButtonContainer != null)
      this.messageReadBackButtonContainer.setVisibility(8);
    this.messageContainer.setContentDescription(getAccessibilityString(getContext().getString(2131362318) + this.name + this.text, 0));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.MessageReadbackWidget
 * JD-Core Version:    0.6.0
 */