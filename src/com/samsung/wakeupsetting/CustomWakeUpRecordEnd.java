package com.samsung.wakeupsetting;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import com.vlingo.midas.gui.customviews.Button;

public class CustomWakeUpRecordEnd extends RelativeLayout
  implements View.OnClickListener
{
  private static OnCustonWakeUpRecordEndListener listener;
  private Context context;
  private Button doneBtn;

  public CustomWakeUpRecordEnd(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131558567:
    }
    while (true)
    {
      return;
      if (listener != null)
      {
        listener.OnCustomWakeUpRecordEndDoneButtonClicked();
        continue;
      }
    }
  }

  public void onFinishInflate()
  {
    this.doneBtn = ((Button)findViewById(2131558567));
    this.doneBtn.setOnClickListener(this);
  }

  public void setOnOnCustonWakeUpRecordEndListener(OnCustonWakeUpRecordEndListener paramOnCustonWakeUpRecordEndListener)
  {
    listener = paramOnCustonWakeUpRecordEndListener;
  }

  public static abstract interface OnCustonWakeUpRecordEndListener
  {
    public abstract void OnCustomWakeUpRecordEndDoneButtonClicked();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wakeupsetting.CustomWakeUpRecordEnd
 * JD-Core Version:    0.6.0
 */