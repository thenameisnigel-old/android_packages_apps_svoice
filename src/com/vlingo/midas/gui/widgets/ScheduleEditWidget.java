package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.gui.customviews.Button;

public class ScheduleEditWidget extends ScheduleWidget
{
  private View mRowButtonline;

  public ScheduleEditWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void launchSchedule()
  {
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131559129:
    case 2131559128:
    }
    while (true)
    {
      return;
      try
      {
        if (this.mActionListener == null)
          continue;
        if (this.mConfirm != null)
          this.mConfirm.setVisibility(8);
        if (this.mCancel != null)
          this.mCancel.setVisibility(8);
        this.isClicked = true;
        retire();
        this.mRowButtonline.setVisibility(8);
        this.mActionListener.handleIntent(new Intent("com.vlingo.core.internal.dialogmanager.Default"), null);
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        continue;
        cancel();
        this.mRowButtonline.setVisibility(8);
      }
      catch (Exception localException)
      {
      }
    }
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.mConfirm.setText(getResources().getString(2131362340));
    this.mRowButtonline = findViewById(2131559126);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.ScheduleEditWidget
 * JD-Core Version:    0.6.0
 */