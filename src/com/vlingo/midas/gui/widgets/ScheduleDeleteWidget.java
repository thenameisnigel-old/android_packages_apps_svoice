package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.gui.customviews.Button;

public class ScheduleDeleteWidget extends ScheduleWidget
{
  private View mRowButtonline;

  public ScheduleDeleteWidget(Context paramContext, AttributeSet paramAttributeSet)
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
        if (this.mActionListener != null)
          this.mActionListener.handleIntent(new Intent("com.vlingo.core.internal.dialogmanager.Default"), null);
        label55: this.mRowButtonline.setVisibility(8);
        continue;
        cancel();
        this.mRowButtonline.setVisibility(8);
      }
      catch (Exception localException)
      {
        break label55;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        break label55;
      }
    }
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.mConfirm.setText(getResources().getString(2131362257));
    this.mRowButtonline = findViewById(2131559126);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.ScheduleDeleteWidget
 * JD-Core Version:    0.6.0
 */