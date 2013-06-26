package com.sec.android.svoice.equalizer;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import com.vlingo.midas.settings.MidasSettings;

public class ListeningView extends ImageView
{
  public EqualizerBoard board;
  private int boardHeight = 0;
  private int boardWidth = 0;
  float iconScaleRatio = 1.05F;
  public int introAlpha = 200;
  public float introAlpha2 = 0.0F;
  private int introCnt = 0;
  public boolean isAlreadyStart = false;
  public boolean isStart = false;
  public Canvas mCanvas;
  public int mEqState = 0;
  private EqualizerSpectrumFactory mFacroty;
  public float mIconMicRotation = 90.0F;
  public float mIconMicTargetRotation = 0.0F;
  private BitmapDrawable mStartCueDot;
  public int mViewType = 5;

  public ListeningView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public ListeningView(Context paramContext, AttributeSet paramAttributeSet)
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
        this.boardWidth = 1078;
        this.boardHeight = 160;
        continue;
      }
    }
  }

  private void drawBoard(Canvas paramCanvas)
  {
    this.board.draw(paramCanvas);
  }

  private void drawStartCue(Canvas paramCanvas, int paramInt)
  {
    this.board.setSpectrumFactory(null);
    if (this.mStartCueDot == null)
      return;
    if (this.introCnt == 0)
    {
      this.introAlpha2 -= 0.5F;
      label34: if (this.introAlpha2 <= 0.15F)
      {
        this.introCnt = (1 + this.introCnt);
        if (this.introCnt < 4)
          break label199;
        if ((this.introAlpha2 < 0.0F) && (this.introCnt > 15))
        {
          this.board.setSpectrumFactory(this.mFacroty);
          this.isStart = false;
          this.isAlreadyStart = true;
          this.introCnt = 0;
          this.introAlpha2 = 0.0F;
        }
      }
      label111: if ((this.introCnt <= 1) || (this.introAlpha2 <= 0.0F))
        break label205;
      if (paramInt != 1)
        break label207;
      this.mStartCueDot.setBounds(0, 0, this.mStartCueDot.getIntrinsicWidth(), this.mStartCueDot.getIntrinsicHeight());
    }
    while (true)
    {
      this.mStartCueDot.setAlpha((int)(255.0F * this.introAlpha2));
      this.mStartCueDot.draw(paramCanvas);
      break;
      this.introAlpha2 = (float)(this.introAlpha2 - 0.1D);
      break label34;
      label199: this.introAlpha2 = 1.0F;
      break label111;
      label205: break;
      label207: this.mStartCueDot.setBounds(-40, -1, -40 + this.mStartCueDot.getIntrinsicWidth(), -1 + this.mStartCueDot.getIntrinsicHeight());
    }
  }

  private void init()
  {
    detectDevice();
    this.mStartCueDot = ((BitmapDrawable)getResources().getDrawable(2130837571));
    this.mFacroty = new EqualizerSpectrumFactory();
    this.mFacroty.registerSpectrumDrawables(getContext(), "car_blue", 54, 8, this.boardHeight);
    this.board = new EqualizerBoard(this.boardWidth, this.boardHeight);
    this.board.initialize();
  }

  public void drawStartCue()
  {
    int i = getResources().getConfiguration().orientation;
    if (this.mEqState == 0)
      drawStartCue(this.mCanvas, i);
    while (true)
    {
      invalidate();
      return;
      drawBoard(this.mCanvas);
    }
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
    this.isAlreadyStart = true;
    this.board.setSpectrumFactory(this.mFacroty);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    this.mCanvas = paramCanvas;
    int i = getResources().getConfiguration().orientation;
    boolean bool = MidasSettings.isMultiwindowedMode();
    if (i == 1)
    {
      if ((this.isStart) && (!this.isAlreadyStart))
        drawStartCue(this.mCanvas, i);
      while (true)
      {
        paramCanvas.restore();
        invalidate();
        return;
        drawBoard(this.mCanvas);
      }
    }
    this.mCanvas.save();
    if (bool)
      if ((this.isStart) && (!this.isAlreadyStart))
      {
        this.mCanvas.translate(1.0F, 1 + getMeasuredHeight());
        label109: this.mCanvas.scale(1.0F, 0.93F);
        label119: this.mCanvas.rotate(-90.0F);
        if ((!this.isStart) || (this.isAlreadyStart))
          break label195;
        drawStartCue(this.mCanvas, i);
      }
    while (true)
    {
      this.mCanvas.restore();
      break;
      this.mCanvas.translate(0.0F, getMeasuredHeight());
      break label109;
      this.mCanvas.translate(0.0F, 1 + getMeasuredHeight());
      break label119;
      label195: drawBoard(this.mCanvas);
    }
  }

  public void remove()
  {
    if (this.board != null)
    {
      this.board.destroy();
      this.board = null;
    }
    if (this.mStartCueDot != null)
      this.mStartCueDot.setCallback(null);
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
 * Qualified Name:     com.sec.android.svoice.equalizer.ListeningView
 * JD-Core Version:    0.6.0
 */