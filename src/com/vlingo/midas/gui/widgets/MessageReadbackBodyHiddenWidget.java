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

public class MessageReadbackBodyHiddenWidget extends MessageWidgetBase
  implements View.OnClickListener
{
  private final int DETAIL = 2;
  private final int RETIRED = 0;
  private Button callbackBtn;
  private Button readoutBtn;

  public MessageReadbackBodyHiddenWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void initialize(MessageType paramMessageType, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    super.initialize(paramMessageType, paramWidgetDecorator, paramWidgetKey, paramWidgetActionListener);
    this.msgBody.setText(2131361797);
    if ((StringUtils.isNullOrWhiteSpace(this.name)) || (StringUtils.isNullOrWhiteSpace(this.text)))
    {
      this.readoutBtn.setVisibility(8);
      this.callbackBtn.setVisibility(8);
      this.messageReadBackButtonContainer.setVisibility(8);
    }
    if (!CorePackageInfoProvider.hasDialing())
      this.callbackBtn.setVisibility(8);
    if ((this.readoutBtn != null) && (this.readoutBtn.getVisibility() == 0) && (this.callbackBtn != null) && (this.callbackBtn.getVisibility() == 0))
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
    if ((paramView == this.messageContainer) && (this.readoutBtn != null) && (this.readoutBtn.isShown()) && (this.callbackBtn != null) && (this.callbackBtn.isShown()))
      openNativeSmsApp();
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.messageContainer.setOnClickListener(this);
    this.readoutBtn = ((Button)findViewById(2131559061));
    if (this.readoutBtn != null)
      this.readoutBtn.setOnClickListener(new MessageReadbackBodyHiddenWidget.1(this));
    this.callbackBtn = ((Button)findViewById(2131559060));
    if (this.callbackBtn != null)
      this.callbackBtn.setOnClickListener(new MessageReadbackBodyHiddenWidget.2(this));
  }

  public void retire()
  {
    super.retire();
    if (this.readoutBtn != null)
      this.readoutBtn.setVisibility(8);
    if (this.callbackBtn != null)
      this.callbackBtn.setVisibility(8);
    if (this.messageReadBackButtonContainer != null)
      this.messageReadBackButtonContainer.setVisibility(8);
    this.messageContainer.setContentDescription(getAccessibilityString(getContext().getString(2131362318) + this.name + this.text, 0));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.MessageReadbackBodyHiddenWidget
 * JD-Core Version:    0.6.0
 */