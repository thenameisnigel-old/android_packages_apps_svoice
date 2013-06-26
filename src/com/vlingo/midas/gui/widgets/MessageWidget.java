package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import com.vlingo.core.internal.dialogmanager.DecoratorType;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.types.MessageType;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.util.StringUtils;

public class MessageWidget extends MessageWidgetBase
  implements View.OnClickListener
{
  private final int DETAIL = 2;
  private final int EDIT = 1;
  private final int RETIRED = 0;
  private Button cancelBtn;
  private boolean isClicked = false;
  private Button sendBtn;

  public MessageWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void initialize(MessageType paramMessageType, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    super.initialize(paramMessageType, paramWidgetDecorator, paramWidgetKey, paramWidgetActionListener);
    if ((this.sendBtn != null) && (StringUtils.isNullOrWhiteSpace(paramMessageType.getMessage())))
      this.sendBtn.setEnabled(false);
    if ((paramWidgetDecorator == null) || (!paramWidgetDecorator.has(DecoratorType.SendButton)) || (!paramWidgetDecorator.has(DecoratorType.CancelButton)))
      retire();
    if ((this.sendBtn != null) && (this.sendBtn.getVisibility() == 0) && (this.cancelBtn != null) && (this.cancelBtn.getVisibility() == 0))
      this.messageContainer.setContentDescription(getAccessibilityString(getContext().getString(2131362319) + this.name + this.text, 1));
    while (true)
    {
      return;
      this.messageContainer.setContentDescription(getAccessibilityString(getContext().getString(2131362319) + this.name + this.text, 0));
    }
  }

  public boolean isTranslated()
  {
    return this.isClicked;
  }

  public boolean isWidgetReplaceable()
  {
    return true;
  }

  public void onClick(View paramView)
  {
    if ((paramView == this.messageContainer) && (this.sendBtn != null) && (this.sendBtn.isShown()) && (this.cancelBtn != null) && (this.cancelBtn.isShown()))
      editInNativeSmsApp();
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.messageContainer.setOnClickListener(this);
    this.sendBtn = ((Button)findViewById(2131559056));
    if (this.sendBtn != null)
      this.sendBtn.setOnClickListener(new MessageWidget.1(this));
    this.cancelBtn = ((Button)findViewById(2131559055));
    if (this.cancelBtn != null)
      this.cancelBtn.setOnClickListener(new MessageWidget.2(this));
  }

  public void retire()
  {
    super.retire();
    if (this.sendBtn != null)
      this.sendBtn.setVisibility(8);
    if (this.cancelBtn != null)
      this.cancelBtn.setVisibility(8);
    if (this.messageButtonContainer != null)
      this.messageButtonContainer.setVisibility(8);
    this.messageContainer.setContentDescription(getAccessibilityString(getContext().getString(2131362318) + this.name + this.text, 0));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.MessageWidget
 * JD-Core Version:    0.6.0
 */