package com.vlingo.midas.gui.timer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class NumberImageView extends ImageView
{
  private boolean isPressed;
  private int number;

  public NumberImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
    setMeasuredDimension(localLayoutParams.width, localLayoutParams.height);
  }

  public void setNumber(int paramInt)
  {
    this.number = paramInt;
    if (this.isPressed);
    for (Drawable localDrawable = com.vlingo.midas.gui.widgets.TimerWidget.selectedTimeNumber[paramInt]; ; localDrawable = com.vlingo.midas.gui.widgets.TimerWidget.timeNumber[paramInt])
    {
      setBackgroundDrawable(localDrawable);
      return;
    }
  }

  public void setPressed(boolean paramBoolean)
  {
    this.isPressed = paramBoolean;
    if (paramBoolean);
    for (Drawable localDrawable = com.vlingo.midas.gui.widgets.TimerWidget.selectedTimeNumber[this.number]; ; localDrawable = com.vlingo.midas.gui.widgets.TimerWidget.timeNumber[this.number])
    {
      setBackgroundDrawable(localDrawable);
      return;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.timer.NumberImageView
 * JD-Core Version:    0.6.0
 */