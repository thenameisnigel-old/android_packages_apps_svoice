package com.vlingo.midas.gui.customviews;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.RemoteViews.RemoteView;
import com.vlingo.midas.R.styleable;

@RemoteViews.RemoteView
public class AnalogClock extends View
{
  private boolean mAttached;
  private Time mCalendar;
  private boolean mChanged;
  private Drawable mDial;
  private int mDialHeight;
  private int mDialWidth;
  private final Handler mHandler = new Handler();
  private int mHandsOffsetX = 0;
  private int mHandsOffsetY = 0;
  private float mHour;
  private Drawable mHourHand;
  private final BroadcastReceiver mIntentReceiver = new AnalogClock.1(this);
  private Drawable mMinuteHand;
  private float mMinutes;
  private Drawable mSecHand;
  private float mSecond;

  public AnalogClock(Context paramContext)
  {
    this(paramContext, null);
  }

  public AnalogClock(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public AnalogClock(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    Resources localResources = getContext().getResources();
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AnalogClock, paramInt, 0);
    try
    {
      this.mDial = localTypedArray.getDrawable(0);
      if (this.mDial == null)
        this.mDial = localResources.getDrawable(2130837507);
      this.mHourHand = localTypedArray.getDrawable(1);
      if (this.mHourHand == null)
        this.mHourHand = localResources.getDrawable(2130837505);
      this.mMinuteHand = localTypedArray.getDrawable(2);
      if (this.mMinuteHand == null)
        this.mMinuteHand = localResources.getDrawable(2130837506);
      this.mCalendar = new Time();
      this.mDialWidth = this.mDial.getIntrinsicWidth();
      this.mDialHeight = this.mDial.getIntrinsicHeight();
      return;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      while (true)
      {
        Log.e("AnalogClock", "OutOfMemoryError" + localOutOfMemoryError);
        this.mDial = null;
      }
    }
  }

  private void onTimeChanged()
  {
    this.mCalendar.setToNow();
    int i = this.mCalendar.hour;
    int j = this.mCalendar.minute;
    int k = this.mCalendar.second;
    this.mMinutes = j;
    this.mHour = (i + this.mMinutes / 60.0F);
    this.mChanged = true;
    if (this.mSecond != k)
      invalidate();
    this.mSecond = k;
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (!this.mAttached)
    {
      this.mAttached = true;
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.TIME_TICK");
      localIntentFilter.addAction("android.intent.action.TIME_SET");
      localIntentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
      getContext().registerReceiver(this.mIntentReceiver, localIntentFilter, null, this.mHandler);
    }
    this.mCalendar = new Time();
    onTimeChanged();
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.mAttached)
    {
      getContext().unregisterReceiver(this.mIntentReceiver);
      this.mAttached = false;
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    boolean bool = this.mChanged;
    if (bool)
      this.mChanged = false;
    int i = getRight() - getLeft();
    int j = getBottom() - getTop();
    int k = i / 2;
    int m = j / 2;
    Drawable localDrawable1 = this.mDial;
    int n = localDrawable1.getIntrinsicWidth();
    int i1 = localDrawable1.getIntrinsicHeight();
    int i2 = 0;
    if ((i < n) || (j < i1))
    {
      i2 = 1;
      float f = Math.min(i / n, j / i1);
      paramCanvas.save();
      paramCanvas.scale(f, f, k, m);
    }
    if (bool)
      localDrawable1.setBounds(k - n / 2, m - i1 / 2, k + n / 2, m + i1 / 2);
    localDrawable1.draw(paramCanvas);
    paramCanvas.save();
    paramCanvas.rotate(360.0F * (this.mHour / 12.0F), k, m + this.mHandsOffsetY);
    Drawable localDrawable2 = this.mHourHand;
    if (bool)
    {
      int i7 = localDrawable2.getIntrinsicWidth();
      int i8 = localDrawable2.getIntrinsicHeight();
      localDrawable2.setBounds(k - i7 / 2, m - i8 / 2 + this.mHandsOffsetY, k + i7 / 2, m + i8 / 2 + this.mHandsOffsetY);
    }
    localDrawable2.draw(paramCanvas);
    paramCanvas.restore();
    paramCanvas.save();
    paramCanvas.rotate(360.0F * (this.mMinutes / 60.0F), k, m + this.mHandsOffsetY);
    Drawable localDrawable3 = this.mMinuteHand;
    if (bool)
    {
      int i5 = localDrawable3.getIntrinsicWidth();
      int i6 = localDrawable3.getIntrinsicHeight();
      localDrawable3.setBounds(k - i5 / 2, m - i6 / 2 + this.mHandsOffsetY, k + i5 / 2, m + i6 / 2 + this.mHandsOffsetY);
    }
    localDrawable3.draw(paramCanvas);
    paramCanvas.restore();
    if (this.mSecHand != null)
    {
      paramCanvas.save();
      paramCanvas.rotate(360.0F * (this.mSecond / 60.0F), k, m + this.mHandsOffsetY);
      Drawable localDrawable4 = this.mSecHand;
      if (bool)
      {
        int i3 = localDrawable4.getIntrinsicWidth();
        int i4 = localDrawable4.getIntrinsicWidth();
        localDrawable4.setBounds(k - i3 / 2, m - i4 / 2 + this.mHandsOffsetY, k + i3 / 2, m + i4 / 2 + this.mHandsOffsetY);
      }
      localDrawable4.draw(paramCanvas);
      paramCanvas.restore();
    }
    if (i2 != 0)
      paramCanvas.restore();
    onTimeChanged();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt1);
    int j = View.MeasureSpec.getSize(paramInt1);
    int k = View.MeasureSpec.getMode(paramInt2);
    int m = View.MeasureSpec.getSize(paramInt2);
    float f1 = 1.0F;
    float f2 = 1.0F;
    if ((i != 0) && (j < this.mDialWidth))
      f1 = j / this.mDialWidth;
    if ((k != 0) && (m < this.mDialHeight))
      f2 = m / this.mDialHeight;
    float f3 = Math.min(f1, f2);
    setMeasuredDimension(resolveSize((int)(f3 * this.mDialWidth), paramInt1), resolveSize((int)(f3 * this.mDialHeight), paramInt2));
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mChanged = true;
  }

  public void setHandsOffset(int paramInt1, int paramInt2)
  {
    this.mHandsOffsetX = paramInt1;
    this.mHandsOffsetY = paramInt2;
  }

  public void setSecondHand(Drawable paramDrawable)
  {
    this.mSecHand = paramDrawable;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.customviews.AnalogClock
 * JD-Core Version:    0.6.0
 */