package com.sec.android.svoice.equalizer;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ImageButton;

public class MicEqualizerView extends ImageButton
{
  public EqualizerBoard board;
  private int boardHeight = 0;
  private int boardWidth = 0;
  private int boardX;
  private int boardY;
  private float introAlpha = 1.0F;
  private int introCnt = 0;
  public boolean isAlreadyStart = false;
  private BitmapDrawable mBlueDots;
  private BitmapDrawable mBlueDots_h;
  private BitmapDrawable mBottomBluelight;
  private BitmapDrawable mBottomBluelight_h;
  private int mCurrentOrientation;
  public int mEqState;
  private EqualizerSpectrumFactory mFacroty;
  private BitmapDrawable mIconMic;
  private Bitmap mMaskBitmap;
  private Paint mPaint = null;
  private BitmapDrawable mStartCueDot;
  private BitmapDrawable mTopHighlight;
  private BitmapDrawable mTopHighlight_h;
  private Xfermode mXmode;

  public MicEqualizerView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public MicEqualizerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  private void detectDevice()
  {
    int i = getResources().getDisplayMetrics().widthPixels;
    int j = getResources().getDisplayMetrics().heightPixels;
    if (((i == 720) && (j == 1280)) || ((i == 1280) && (j == 720)))
    {
      this.boardWidth = 716;
      this.boardHeight = 98;
    }
    while (true)
    {
      return;
      if (((i != 800) || (j != 480)) && ((i != 480) || (j != 800)) && ((i != 720) || (j != 1184)) && ((i != 1196) || (j != 720)) && ((i != 800) || (j != 1232)) && ((i != 1280) || (j != 752)) && (((i == 1080) && (j == 1920)) || ((i == 1920) && (j == 1080))))
      {
        if (i == 1080)
        {
          this.boardWidth = 1078;
          this.boardHeight = 160;
          this.boardX = 81;
          this.boardY = 76;
          continue;
        }
        this.boardWidth = 160;
        this.boardHeight = 552;
        this.boardX = 67;
        this.boardY = -94;
        continue;
      }
    }
  }

  private void drawBoard(Canvas paramCanvas)
  {
    this.board.draw(paramCanvas);
  }

  private void drawMicIcon(Canvas paramCanvas)
  {
    if ((this.mIconMic != null) && (this.mIconMic.getBitmap() != null))
    {
      paramCanvas.save();
      int i = getMeasuredWidth() / 2 - this.mIconMic.getIntrinsicWidth() / 2;
      int j = getMeasuredHeight() / 2 - this.mIconMic.getIntrinsicHeight() / 2;
      paramCanvas.translate(i - 1, j - 1);
      this.mIconMic.setBounds(0, 0, this.mIconMic.getIntrinsicWidth(), this.mIconMic.getIntrinsicHeight());
      this.mIconMic.draw(paramCanvas);
      paramCanvas.restore();
    }
  }

  private void drawStartCue(Canvas paramCanvas)
  {
    this.board.setSpectrumFactory(null);
    if (this.mStartCueDot == null);
    while (true)
    {
      return;
      if (this.introCnt == 0)
      {
        this.introAlpha -= 0.1F;
        label34: if (this.introAlpha <= 0.15F)
        {
          this.introCnt = (1 + this.introCnt);
          if (this.introCnt < 3)
            break label201;
          if ((this.introAlpha < 0.0F) && (this.introCnt > 10))
          {
            this.board.setSpectrumFactory(this.mFacroty);
            this.isAlreadyStart = true;
            this.introCnt = 0;
          }
        }
      }
      label201: for (this.introAlpha = 0.0F; ; this.introAlpha = 1.0F)
      {
        if ((this.introCnt <= 0) || (this.introCnt > 3) || (this.introAlpha <= 0.0F))
          break label207;
        if (this.mCurrentOrientation != 1)
          break label209;
        this.mStartCueDot.setBounds(0, 0, this.mStartCueDot.getIntrinsicWidth(), this.mStartCueDot.getIntrinsicHeight());
        this.mStartCueDot.setAlpha((int)(255.0F * this.introAlpha));
        this.mStartCueDot.draw(paramCanvas);
        break;
        this.introAlpha -= 0.11F;
        break label34;
      }
      label207: continue;
      label209: paramCanvas.save();
      paramCanvas.translate(this.mStartCueDot.getMinimumHeight(), 0.0F);
      paramCanvas.rotate(90.0F);
      this.mStartCueDot.setBounds(0, 0, this.mStartCueDot.getIntrinsicWidth(), this.mStartCueDot.getIntrinsicHeight());
      this.mStartCueDot.setAlpha((int)(255.0F * this.introAlpha));
      this.mStartCueDot.draw(paramCanvas);
      paramCanvas.restore();
    }
  }

  private void init()
  {
    detectDevice();
    this.mIconMic = ((BitmapDrawable)getResources().getDrawable(2130837929));
    this.mStartCueDot = ((BitmapDrawable)getResources().getDrawable(2130837926));
    this.mBottomBluelight = ((BitmapDrawable)getResources().getDrawable(2130837526));
    this.mTopHighlight = ((BitmapDrawable)getResources().getDrawable(2130838003));
    this.mBottomBluelight_h = ((BitmapDrawable)getResources().getDrawable(2130837742));
    this.mTopHighlight_h = ((BitmapDrawable)getResources().getDrawable(2130837785));
    this.mBlueDots = ((BitmapDrawable)getResources().getDrawable(2130837925));
    this.mBlueDots_h = ((BitmapDrawable)getResources().getDrawable(2130837774));
    this.mPaint = new Paint();
    this.mPaint.setAntiAlias(true);
    this.mXmode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    this.mMaskBitmap = BitmapFactory.decodeResource(getResources(), 2130837851);
    this.mFacroty = new EqualizerSpectrumFactory();
    this.board = new EqualizerBoard(this.boardWidth, this.boardHeight);
    this.board.initialize();
  }

  public EqualizerSpectrumFactory getFactory()
  {
    if (this.board.getSpectrumFactory() != null);
    for (EqualizerSpectrumFactory localEqualizerSpectrumFactory = this.mFacroty; ; localEqualizerSpectrumFactory = null)
      return localEqualizerSpectrumFactory;
  }

  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    detectDevice();
    this.isAlreadyStart = true;
    setOrientation(getResources().getConfiguration().orientation);
    this.board.setSpectrumFactory(this.mFacroty);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    int i = getResources().getConfiguration().orientation;
    int j = paramCanvas.saveLayer(0.0F, 0.0F, getWidth(), getHeight(), null, 31);
    int k = 0;
    if (i == 1)
    {
      if (!this.isAlreadyStart)
      {
        if (this.mBlueDots != null)
        {
          this.mBlueDots.setBounds(0, 0, this.mBlueDots.getIntrinsicWidth(), this.mBlueDots.getIntrinsicHeight());
          this.mBlueDots.draw(paramCanvas);
        }
        drawStartCue(paramCanvas);
      }
      while (true)
      {
        drawMicIcon(paramCanvas);
        if (this.isAlreadyStart)
        {
          this.mBottomBluelight.setBounds(0, 0, this.mBottomBluelight.getIntrinsicWidth(), this.mBottomBluelight.getIntrinsicHeight());
          this.mBottomBluelight.draw(paramCanvas);
        }
        this.mTopHighlight.setBounds(0, 0, this.mTopHighlight.getIntrinsicWidth(), this.mTopHighlight.getIntrinsicHeight());
        this.mTopHighlight.draw(paramCanvas);
        int n = -5;
        int i2 = 0;
        this.mPaint.setXfermode(this.mXmode);
        paramCanvas.save();
        paramCanvas.translate(n, i2);
        paramCanvas.rotate(k);
        paramCanvas.drawBitmap(this.mMaskBitmap, 0.0F, 0.0F, this.mPaint);
        paramCanvas.restore();
        this.mPaint.setXfermode(null);
        paramCanvas.restoreToCount(j);
        invalidate();
        return;
        drawBoard(paramCanvas);
      }
    }
    if (!this.isAlreadyStart)
    {
      if (this.mBlueDots_h != null)
      {
        this.mBlueDots_h.setBounds(0, 0, this.mBlueDots_h.getIntrinsicWidth(), this.mBlueDots_h.getIntrinsicHeight());
        this.mBlueDots_h.draw(paramCanvas);
      }
      drawStartCue(paramCanvas);
    }
    while (true)
    {
      paramCanvas.save();
      paramCanvas.translate(-3.0F, 3.0F);
      drawMicIcon(paramCanvas);
      paramCanvas.restore();
      if (this.isAlreadyStart)
      {
        this.mBottomBluelight_h.setBounds(0, 0, this.mBottomBluelight_h.getIntrinsicWidth(), this.mBottomBluelight_h.getIntrinsicHeight());
        this.mBottomBluelight_h.draw(paramCanvas);
      }
      this.mTopHighlight_h.setBounds(0, 0, this.mTopHighlight_h.getIntrinsicWidth(), this.mTopHighlight_h.getIntrinsicHeight());
      this.mTopHighlight_h.draw(paramCanvas);
      int i1 = -9 + this.mMaskBitmap.getHeight();
      int i3 = -5;
      int m = 90;
      break;
      drawBoard(paramCanvas);
    }
  }

  public void remove()
  {
    if (this.board != null)
    {
      this.board.destroy();
      this.board = null;
    }
    if (this.mIconMic != null)
      this.mIconMic.setCallback(null);
    if (this.mStartCueDot != null)
      this.mStartCueDot.setCallback(null);
    if (this.mBottomBluelight != null)
      this.mBottomBluelight.setCallback(null);
    if (this.mTopHighlight != null)
      this.mTopHighlight.setCallback(null);
    if (this.mBottomBluelight_h != null)
      this.mBottomBluelight_h.setCallback(null);
    if (this.mTopHighlight_h != null)
      this.mTopHighlight_h.setCallback(null);
    if (this.mBlueDots != null)
      this.mBlueDots.setCallback(null);
    if (this.mBlueDots_h != null)
      this.mBlueDots_h.setCallback(null);
    if (this.mMaskBitmap != null)
      this.mMaskBitmap.recycle();
  }

  public void setOrientation(int paramInt)
  {
    this.mCurrentOrientation = paramInt;
    if (this.mCurrentOrientation == 1)
      this.mFacroty.registerNormalSpectrumDrawables(getContext(), "blue", 30, 14, this.boardHeight, this.boardX, this.boardY);
    while (true)
    {
      return;
      this.mFacroty.registerNormalSpectrumDrawables(getContext(), "h_blue", 14, 30, this.boardHeight, this.boardX, this.boardY);
    }
  }

  private static enum DeviceConfiguration
  {
    static
    {
      SMART_CAMERA = new DeviceConfiguration("SMART_CAMERA", 2);
      TABLET = new DeviceConfiguration("TABLET", 3);
      JDEVICE = new DeviceConfiguration("JDEVICE", 4);
      DeviceConfiguration[] arrayOfDeviceConfiguration = new DeviceConfiguration[5];
      arrayOfDeviceConfiguration[0] = QHD;
      arrayOfDeviceConfiguration[1] = WVGA;
      arrayOfDeviceConfiguration[2] = SMART_CAMERA;
      arrayOfDeviceConfiguration[3] = TABLET;
      arrayOfDeviceConfiguration[4] = JDEVICE;
      $VALUES = arrayOfDeviceConfiguration;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.svoice.equalizer.MicEqualizerView
 * JD-Core Version:    0.6.0
 */