package com.sec.android.svoice.equalizer;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class EqualizerCircle
{
  public Drawable blueDot;
  public int mAlpha = 0;
  private int mCircelFadeOutDuration = 5;
  public int mHeight;
  public boolean mIsVisible;
  public int mWidth;
  public float mX;
  public float mY;

  public EqualizerCircle(Drawable paramDrawable, int paramInt1, int paramInt2, int paramInt3)
  {
    this.blueDot = paramDrawable;
    this.mWidth = paramDrawable.getIntrinsicWidth();
    this.mHeight = paramDrawable.getIntrinsicHeight();
    this.mX = paramInt1;
    this.mY = paramInt2;
  }

  public void draw(Canvas paramCanvas)
  {
    if (this.mIsVisible)
    {
      if (this.mAlpha < 0)
      {
        this.mAlpha = 0;
        this.mIsVisible = false;
      }
      this.blueDot.setAlpha(this.mAlpha);
      this.blueDot.setBounds((int)this.mX, (int)this.mY, (int)this.mX + this.mWidth, (int)this.mY + this.mHeight);
      this.blueDot.draw(paramCanvas);
      this.mAlpha -= this.mCircelFadeOutDuration;
    }
  }

  public int getAlpha()
  {
    return this.mAlpha;
  }

  public void setAlpha(int paramInt)
  {
    this.mAlpha = paramInt;
    this.mIsVisible = true;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.svoice.equalizer.EqualizerCircle
 * JD-Core Version:    0.6.0
 */