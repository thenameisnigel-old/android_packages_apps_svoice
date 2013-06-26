package com.vlingo.midas.gui.customviews;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ImageButton;

public class MicView extends ImageButton
{
  private Bitmap blueLight;
  private Bitmap blueLight_land;
  private Bitmap bmp;
  private Bitmap cornerBitmap;
  private DeviceConfiguration currentConfig = DeviceConfiguration.QHD;
  private int height;
  private Bitmap mBGBitmap;
  private Bitmap mBGBitmapNormal;
  private Bitmap mBGBitmapNormal_land;
  private Bitmap mBGBitmap_land;
  private Bitmap mBGBitmap_land_softKey;
  private Bitmap mBGBitmap_softKey;
  private Bitmap mMicActiveBitmap;
  private Bitmap mMicActive_land;
  public Rect mRect;
  private float mRms;
  private NinePatchDrawable squareImage;
  private int width;

  public MicView(Context paramContext)
  {
    super(paramContext);
  }

  public MicView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private Bitmap createScaledBitmapByResource(int paramInt1, int paramInt2, int paramInt3)
  {
    BitmapDrawable localBitmapDrawable = (BitmapDrawable)getResources().getDrawable(paramInt1);
    if (localBitmapDrawable == null)
      localBitmapDrawable = (BitmapDrawable)getResources().getDrawable(paramInt1);
    return Bitmap.createScaledBitmap(localBitmapDrawable.getBitmap(), paramInt2, paramInt3, true);
  }

  private void init(int paramInt1, int paramInt2)
  {
    int i = getResources().getDisplayMetrics().widthPixels;
    int j = getResources().getDisplayMetrics().heightPixels;
    if (((i == 720) && (j == 1280)) || ((i == 1280) && (j == 720)))
      this.currentConfig = DeviceConfiguration.QHD;
    while (true)
    {
      this.squareImage = ((NinePatchDrawable)getResources().getDrawable(2130837524));
      this.mBGBitmapNormal = createScaledBitmapByResource(2130837932, paramInt1, paramInt2);
      this.mBGBitmap = createScaledBitmapByResource(2130837891, paramInt1, paramInt2);
      this.mMicActiveBitmap = createScaledBitmapByResource(2130837949, paramInt1, paramInt2);
      this.cornerBitmap = createScaledBitmapByResource(2130837565, 60, 51);
      this.mBGBitmapNormal_land = createScaledBitmapByResource(2130837776, paramInt1, paramInt2);
      this.mBGBitmap_land = createScaledBitmapByResource(2130837772, paramInt1, paramInt2);
      this.mMicActive_land = createScaledBitmapByResource(2130837950, paramInt1, paramInt2);
      this.blueLight = createScaledBitmapByResource(2130837525, paramInt1, paramInt2);
      this.blueLight_land = createScaledBitmapByResource(2130837741, paramInt1, paramInt2);
      return;
      if (((i == 800) && (j == 480)) || ((i == 480) && (j == 800)))
      {
        this.currentConfig = DeviceConfiguration.WVGA;
        continue;
      }
      if (((i == 720) && (j == 1184)) || ((i == 1196) && (j == 720)))
      {
        this.currentConfig = DeviceConfiguration.SMART_CAMERA;
        this.mBGBitmap_softKey = createScaledBitmapByResource(2130837892, paramInt1, paramInt2);
        this.mBGBitmap_land_softKey = createScaledBitmapByResource(2130837773, paramInt1, paramInt2);
        continue;
      }
      if (((i == 800) && (j == 1232)) || ((i == 1280) && (j == 752)))
      {
        this.currentConfig = DeviceConfiguration.TABLET;
        continue;
      }
      if (((i != 1080) || (j != 1920)) && ((i != 1920) || (j != 1080)))
        continue;
      this.currentConfig = DeviceConfiguration.JDEVICE;
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    int i = getResources().getConfiguration().orientation;
    switch (1.$SwitchMap$com$vlingo$midas$gui$customviews$MicView$DeviceConfiguration[this.currentConfig.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      return;
      if (this.width > this.height)
      {
        int i15 = getPaddingLeft();
        int i16 = getPaddingTop();
        paramCanvas.drawBitmap(this.mBGBitmapNormal, i15, i16 - 1, null);
        DrawAnimationTablet.drawPotraitAnimation(paramCanvas, this.cornerBitmap, this.squareImage, this.mRms);
        paramCanvas.drawBitmap(this.mBGBitmap, i15, i16, null);
        paramCanvas.drawBitmap(this.blueLight, i15, i16 - 1, null);
        paramCanvas.drawBitmap(this.mMicActiveBitmap, i15, i16, null);
        if (isPressed())
        {
          this.bmp = createScaledBitmapByResource(2130837944, this.width, this.height);
          paramCanvas.drawBitmap(this.bmp, i15, i16, null);
          continue;
        }
        if (!isFocused())
          continue;
        this.bmp = createScaledBitmapByResource(2130837631, this.width, this.height);
        paramCanvas.drawBitmap(this.bmp, i15, i16, null);
        continue;
      }
      int i13 = getPaddingLeft();
      int i14 = getPaddingTop();
      paramCanvas.drawBitmap(this.mBGBitmapNormal_land, i13 - 30, i14 - 30, null);
      DrawAnimationTablet.drawLandscapeAnimation(paramCanvas, this.cornerBitmap, this.squareImage, this.mRms);
      paramCanvas.drawBitmap(this.mBGBitmap_land, i13, i14, null);
      paramCanvas.drawBitmap(this.blueLight_land, i13, i14, null);
      paramCanvas.drawBitmap(this.mMicActive_land, i13, i14, null);
      if (isPressed())
      {
        this.bmp = createScaledBitmapByResource(2130837783, this.width, this.height);
        paramCanvas.drawBitmap(this.bmp, i13, i14, null);
        continue;
      }
      if (!isFocused())
        continue;
      this.bmp = createScaledBitmapByResource(2130837747, this.width, this.height);
      paramCanvas.drawBitmap(this.bmp, i13, i14, null);
      continue;
      if (i == 1)
      {
        int i11 = getPaddingLeft();
        int i12 = getPaddingTop();
        paramCanvas.drawBitmap(this.mBGBitmapNormal, i11, i12 - 1, null);
        DrawAnimationQHD.drawPotraitAnimation(paramCanvas, this.cornerBitmap, this.squareImage, this.mRms);
        paramCanvas.drawBitmap(this.mBGBitmap, i11, i12 - 1, null);
        paramCanvas.drawBitmap(this.blueLight, i11, i12 - 1, null);
        paramCanvas.drawBitmap(this.mMicActiveBitmap, i11, i12, null);
        if (isPressed())
        {
          this.bmp = createScaledBitmapByResource(2130837944, this.width, this.height);
          paramCanvas.drawBitmap(this.bmp, i11, i12 - 2, null);
          continue;
        }
        if (!isFocused())
          continue;
        this.bmp = createScaledBitmapByResource(2130837631, this.width, this.height);
        paramCanvas.drawBitmap(this.bmp, i11, i12 - 2, null);
        continue;
      }
      int i9 = getPaddingLeft();
      int i10 = getPaddingTop();
      paramCanvas.drawBitmap(this.mBGBitmapNormal_land, i9 - 30, i10 - 30, null);
      DrawAnimationQHD.drawLandscapeAnimation(paramCanvas, this.cornerBitmap, this.squareImage, this.mRms);
      paramCanvas.drawBitmap(this.mBGBitmap_land, i9, i10, null);
      paramCanvas.drawBitmap(this.blueLight_land, i9, i10, null);
      paramCanvas.drawBitmap(this.mMicActive_land, i9, i10, null);
      if (isPressed())
      {
        this.bmp = createScaledBitmapByResource(2130837783, this.width, this.height);
        paramCanvas.drawBitmap(this.bmp, i9, i10, null);
        continue;
      }
      if (!isFocused())
        continue;
      this.bmp = createScaledBitmapByResource(2130837747, this.width, this.height);
      paramCanvas.drawBitmap(this.bmp, i9, i10, null);
      continue;
      if (getResources().getConfiguration().orientation == 1)
      {
        int i7 = getPaddingLeft();
        int i8 = getPaddingTop();
        paramCanvas.drawBitmap(this.mBGBitmapNormal, i7, i8, null);
        DrawAnimationWVGA.drawPotraitAnimation(paramCanvas, this.cornerBitmap, this.squareImage, this.mRms);
        paramCanvas.drawBitmap(this.mBGBitmap, i7, i8, null);
        paramCanvas.drawBitmap(this.blueLight, i7, i8, null);
        paramCanvas.drawBitmap(this.mMicActiveBitmap, i7, i8, null);
        if (isPressed())
        {
          this.bmp = createScaledBitmapByResource(2130837944, this.width, this.height);
          paramCanvas.drawBitmap(this.bmp, i7, i8, null);
          continue;
        }
        if (!isFocused())
          continue;
        this.bmp = createScaledBitmapByResource(2130837631, this.width, this.height);
        paramCanvas.drawBitmap(this.bmp, i7, i8, null);
        continue;
      }
      int i5 = getPaddingLeft();
      int i6 = getPaddingTop();
      paramCanvas.drawBitmap(this.mBGBitmapNormal_land, i5, i6, null);
      DrawAnimationWVGA.drawLandscapeAnimation(paramCanvas, this.cornerBitmap, this.squareImage, this.mRms);
      paramCanvas.drawBitmap(this.mBGBitmap_land, i5, i6, null);
      paramCanvas.drawBitmap(this.blueLight_land, i5, i6, null);
      paramCanvas.drawBitmap(this.mMicActive_land, i5, i6, null);
      if (isPressed())
      {
        this.bmp = createScaledBitmapByResource(2130837783, this.width, this.height);
        paramCanvas.drawBitmap(this.bmp, i5, i6, null);
        continue;
      }
      if (!isFocused())
        continue;
      this.bmp = createScaledBitmapByResource(2130837747, this.width, this.height);
      paramCanvas.drawBitmap(this.bmp, i5, i6, null);
      continue;
      if (getResources().getConfiguration().orientation == 1)
      {
        int i3 = getPaddingLeft();
        int i4 = getPaddingTop();
        paramCanvas.drawBitmap(this.mBGBitmapNormal, i3, i4, null);
        DrawAnimationQHD.drawPotraitAnimation(paramCanvas, this.cornerBitmap, this.squareImage, this.mRms);
        paramCanvas.drawBitmap(this.mBGBitmap_softKey, i3, i4, null);
        paramCanvas.drawBitmap(this.blueLight, i3, i4, null);
        paramCanvas.drawBitmap(this.mMicActiveBitmap, i3, i4, null);
        if (isPressed())
        {
          this.bmp = ((BitmapDrawable)getResources().getDrawable(2130837945)).getBitmap();
          this.bmp = Bitmap.createScaledBitmap(this.bmp, this.width, this.height, true);
          paramCanvas.drawBitmap(this.bmp, i3, i4, null);
          continue;
        }
        if (!isFocused())
          continue;
        this.bmp = ((BitmapDrawable)getResources().getDrawable(2130837632)).getBitmap();
        this.bmp = Bitmap.createScaledBitmap(this.bmp, this.width, this.height, true);
        paramCanvas.drawBitmap(this.bmp, i3, i4, null);
        continue;
      }
      int i1 = getPaddingLeft();
      int i2 = getPaddingTop();
      paramCanvas.drawBitmap(this.mBGBitmapNormal_land, i1 - 30, i2 - 30, null);
      DrawAnimationQHD.drawLandscapeAnimation(paramCanvas, this.cornerBitmap, this.squareImage, this.mRms);
      paramCanvas.drawBitmap(this.mBGBitmap_land_softKey, i1, 1.6F + i2, null);
      paramCanvas.drawBitmap(this.blueLight_land, i1, 1.6F + i2, null);
      paramCanvas.drawBitmap(this.mMicActive_land, i1, 1.6F + i2, null);
      if (isPressed())
      {
        this.bmp = ((BitmapDrawable)getResources().getDrawable(2130837784)).getBitmap();
        this.bmp = Bitmap.createScaledBitmap(this.bmp, this.width, this.height, true);
        paramCanvas.drawBitmap(this.bmp, i1, 1.6F + i2, null);
        continue;
      }
      if (!isFocused())
        continue;
      this.bmp = ((BitmapDrawable)getResources().getDrawable(2130837748)).getBitmap();
      this.bmp = Bitmap.createScaledBitmap(this.bmp, this.width, this.height, true);
      paramCanvas.drawBitmap(this.bmp, i1, 1.6F + i2, null);
      continue;
      if (i == 1)
      {
        int m = getPaddingLeft();
        int n = getPaddingTop();
        paramCanvas.drawBitmap(this.mBGBitmapNormal, m, n - 1, null);
        DrawAnimationJDevice.drawPotraitAnimation(paramCanvas, this.cornerBitmap, this.squareImage, this.mRms);
        paramCanvas.drawBitmap(this.mBGBitmap, m, n - 1, null);
        paramCanvas.drawBitmap(this.blueLight, m, n - 1, null);
        paramCanvas.drawBitmap(this.mMicActiveBitmap, m, n, null);
        if (isPressed())
        {
          this.bmp = createScaledBitmapByResource(2130837944, this.width, this.height);
          paramCanvas.drawBitmap(this.bmp, m, n - 2, null);
          continue;
        }
        if (!isFocused())
          continue;
        this.bmp = createScaledBitmapByResource(2130837631, this.width, this.height);
        paramCanvas.drawBitmap(this.bmp, m, n - 2, null);
        continue;
      }
      int j = getPaddingLeft();
      int k = getPaddingTop();
      paramCanvas.drawBitmap(this.mBGBitmapNormal_land, j, k, null);
      DrawAnimationJDevice.drawLandscapeAnimation(paramCanvas, this.cornerBitmap, this.squareImage, this.mRms);
      paramCanvas.drawBitmap(this.mBGBitmap_land, j, k, null);
      paramCanvas.drawBitmap(this.blueLight_land, j, k, null);
      paramCanvas.drawBitmap(this.mMicActive_land, j, k, null);
      if (isPressed())
      {
        this.bmp = createScaledBitmapByResource(2130837783, this.width, this.height);
        paramCanvas.drawBitmap(this.bmp, j, k, null);
        continue;
      }
      if (!isFocused())
        continue;
      this.bmp = createScaledBitmapByResource(2130837747, this.width, this.height);
      paramCanvas.drawBitmap(this.bmp, j, k, null);
    }
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.width = paramInt1;
    this.height = paramInt2;
    init(paramInt1, paramInt2);
  }

  public void setRMSValue(float paramFloat)
  {
    this.mRms = paramFloat;
    if (this.mRms < 0.0F)
      this.mRms = 0.0F;
    invalidate();
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
 * Qualified Name:     com.vlingo.midas.gui.customviews.MicView
 * JD-Core Version:    0.6.0
 */