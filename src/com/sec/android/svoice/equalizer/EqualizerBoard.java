package com.sec.android.svoice.equalizer;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import java.util.ArrayList;

public class EqualizerBoard
{
  private Bitmap mBoardBitmap;
  private Canvas mBoardCanvas;
  private int mChannelCount;
  private Xfermode mClearXmode;
  private float mHeight;
  private boolean mIsMask = false;
  private int mLevelCount;
  private ArrayList<Integer> mSpectrumChannels;
  private EqualizerSpectrumFactory mSpectrumFactory;
  private float mWidth;
  private Xfermode mXmode;

  public EqualizerBoard(float paramFloat1, float paramFloat2)
  {
    this.mWidth = paramFloat1;
    this.mHeight = paramFloat2;
    this.mClearXmode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
  }

  public void destroy()
  {
    this.mSpectrumFactory.destroy();
    this.mSpectrumFactory = null;
    this.mSpectrumChannels.clear();
    this.mSpectrumChannels = null;
  }

  public void draw(Canvas paramCanvas)
  {
    if (this.mSpectrumFactory == null);
    while (true)
    {
      return;
      ArrayList localArrayList = this.mSpectrumFactory.getSpectrumLevelDrawables();
      for (int i = 0; i < localArrayList.size(); i++)
        ((EqualizerCircle)localArrayList.get(i)).draw(paramCanvas);
    }
  }

  public int getChannel()
  {
    return this.mChannelCount;
  }

  public float getHeight()
  {
    return this.mHeight;
  }

  public EqualizerSpectrumFactory getSpectrumFactory()
  {
    return this.mSpectrumFactory;
  }

  public int getSpectrumLevel()
  {
    return this.mLevelCount;
  }

  public float getWidth()
  {
    return this.mWidth;
  }

  public void initialize()
  {
    if (this.mSpectrumChannels == null)
    {
      this.mSpectrumChannels = new ArrayList();
      for (int i = 0; i < this.mChannelCount; i++)
        this.mSpectrumChannels.add(Integer.valueOf(0));
    }
  }

  public void setCanvasSize(int paramInt1, int paramInt2)
  {
    this.mBoardBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
    this.mBoardCanvas = new Canvas(this.mBoardBitmap);
  }

  public void setChannel(int paramInt)
  {
    this.mChannelCount = paramInt;
  }

  public void setEnabledMask(boolean paramBoolean)
  {
    this.mIsMask = paramBoolean;
    if ((this.mIsMask) && (this.mXmode == null))
      this.mXmode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    while (true)
    {
      return;
      this.mXmode = null;
      this.mClearXmode = null;
    }
  }

  public void setHeight(float paramFloat)
  {
    this.mHeight = paramFloat;
  }

  public void setSpectrumFactory(EqualizerSpectrumFactory paramEqualizerSpectrumFactory)
  {
    this.mSpectrumFactory = paramEqualizerSpectrumFactory;
  }

  public void setSpectrumLevel(int paramInt)
  {
    this.mLevelCount = paramInt;
  }

  public void setWidth(float paramFloat)
  {
    this.mWidth = paramFloat;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.svoice.equalizer.EqualizerBoard
 * JD-Core Version:    0.6.0
 */