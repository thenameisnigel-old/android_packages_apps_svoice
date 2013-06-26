package com.vlingo.midas.gui.customviews;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.sec.android.svoice.equalizer.EqualizerCircle;
import com.sec.android.svoice.equalizer.EqualizerSpectrumFactory;
import com.sec.android.svoice.equalizer.ListeningView;
import com.vlingo.midas.iux.IUXManager;
import com.vlingo.midas.tos.TermsOfServiceManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CustomMicButton extends RelativeLayout
{
  private static final double VOLUME_MORE_POWER_UP_VALUE = 1.6D;
  private static final double VOLUME_POWER_UP_VALUE = 1.4D;
  private AnimationDrawable animationDrawable;
  Drawable idleDrawable;
  private boolean mAnimationRunning = false;
  private Context mContext;
  private ControlMode mCurrentMode;
  private ImageView mGrayDots;
  private ImageView mGreenBack;
  private ImageView mGreenBar;
  private View mLandView;
  public ListeningView mListeningView;
  private ImageView mMicImage;
  private View mPortView;
  private CustomMicButtonHandler mhandler = new CustomMicButtonHandler(this);
  int previosY = 0;
  Drawable[] thinkingLayer = new Drawable[2];

  public CustomMicButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    ((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(2130903047, this);
    if (!isPassedAllInitSteps())
      setEnabled(false);
  }

  private boolean isPassedAllInitSteps()
  {
    if ((!isInEditMode()) && (!TermsOfServiceManager.isTOSRequired()) && (!IUXManager.requiresIUX()) && (IUXManager.isIUXComplete()));
    for (int i = 1; ; i = 0)
      return i;
  }

  private void moveToGreenBar(int paramInt)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.leftMargin = paramInt;
    this.mGreenBar.setLayoutParams(localLayoutParams);
  }

  protected void initViewId()
  {
    if (this.mPortView.getVisibility() == 0)
    {
      this.mGreenBack = ((ImageView)findViewById(2131558531));
      this.mGreenBar = ((ImageView)findViewById(2131558532));
      this.mListeningView = ((ListeningView)findViewById(2131558528));
      this.mGrayDots = ((ImageView)findViewById(2131558530));
    }
    for (this.mMicImage = ((ImageView)findViewById(2131558533)); ; this.mMicImage = ((ImageView)findViewById(2131558540)))
    {
      setMode(this.mCurrentMode, true);
      return;
      this.mGreenBack = ((ImageView)findViewById(2131558538));
      this.mGreenBar = ((ImageView)findViewById(2131558539));
      this.mListeningView = ((ListeningView)findViewById(2131558535));
      this.mGrayDots = ((ImageView)findViewById(2131558537));
    }
  }

  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (paramConfiguration.orientation == 2)
    {
      this.mPortView.setVisibility(8);
      this.mLandView.setVisibility(0);
    }
    while (true)
    {
      initViewId();
      super.onConfigurationChanged(paramConfiguration);
      return;
      this.mLandView.setVisibility(8);
      this.mPortView.setVisibility(0);
    }
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mLandView = findViewById(2131558534);
    this.mPortView = findViewById(2131558527);
    onConfigurationChanged(getResources().getConfiguration());
  }

  public void setBlueHeight(int paramInt)
  {
  }

  public boolean setMode(ControlMode paramControlMode, boolean paramBoolean)
  {
    int i = 0;
    if ((this.mCurrentMode != paramControlMode) || (paramBoolean))
    {
      this.mCurrentMode = paramControlMode;
      if ((paramControlMode != ControlMode.Idle) && (paramControlMode != ControlMode.Processing))
        break label85;
      this.mListeningView.setVisibility(8);
      this.mGrayDots.setVisibility(0);
      this.mGreenBack.setVisibility(8);
      this.mGreenBar.setVisibility(8);
      this.mMicImage.setVisibility(0);
    }
    while (true)
    {
      setBlueHeight(0);
      i = 1;
      return i;
      label85: if ((paramControlMode == ControlMode.Listening) || (paramControlMode == ControlMode.GettingReady))
      {
        if ((this.mListeningView.getVisibility() != 0) || (paramBoolean))
          this.mListeningView.setVisibility(0);
        this.mGrayDots.setVisibility(8);
        this.mGreenBack.setVisibility(8);
        this.mGreenBar.setVisibility(8);
        this.mMicImage.setVisibility(0);
        continue;
      }
      if (paramControlMode == ControlMode.Thinking)
      {
        this.mListeningView.setVisibility(8);
        this.mGrayDots.setVisibility(8);
        this.mGreenBack.setVisibility(0);
        this.mMicImage.setVisibility(0);
        startThinkingAnimation();
        continue;
      }
      this.mGreenBack.setVisibility(8);
      this.mGreenBar.setVisibility(8);
    }
  }

  public void showSpectrum(int[] paramArrayOfInt)
  {
    if (paramArrayOfInt == null);
    int i;
    do
    {
      do
        return;
      while (this.mListeningView == null);
      i = 0;
    }
    while (this.mListeningView.getFactory() == null);
    int j = 15;
    while (j <= paramArrayOfInt.length)
    {
      int k;
      label91: label113: int n;
      label228: int i1;
      label261: label272: label282: int i2;
      if (i < 54)
      {
        int m;
        int i3;
        int i4;
        if (i < 5)
        {
          k = (int)(0.300000011920929D * Math.sqrt(Math.sqrt(paramArrayOfInt[j])));
          if ((i <= 0) || (k - this.previosY <= 2))
            break label228;
          k = this.previosY + (int)(2.0D * Math.random());
          if (k > 8)
            k = 8;
          if (k < 0)
            k = 0;
          m = 0;
          if (m >= k)
            break label272;
          i3 = i + m * 54;
          i4 = 255 - m * 30;
          if (i4 <= 255)
            break label261;
          i4 = 255;
        }
        while (true)
        {
          ((EqualizerCircle)this.mListeningView.getFactory().getSpectrumLevelDrawables().get(i3)).setAlpha(i4);
          m++;
          break label113;
          if (i > 40)
          {
            k = (int)(0.699999988079071D * Math.sqrt(Math.sqrt(paramArrayOfInt[j])));
            break;
          }
          k = (int)(0.5D * Math.sqrt(Math.sqrt(paramArrayOfInt[j])));
          break;
          if ((i <= 0) || (this.previosY - k <= 2))
            break label91;
          k = this.previosY - (int)(2.0D * Math.random());
          break label91;
          if (i4 >= 0)
            continue;
          i4 = 0;
        }
        if (k <= 3)
        {
          n = k;
          if (n < k + 2)
          {
            i1 = i + n * 54;
            if (n == k)
            {
              i2 = (int)(0.6F * (255 - n * 30));
              label324: if (i2 < 0)
                i2 = 0;
            }
          }
        }
      }
      try
      {
        if (((EqualizerCircle)this.mListeningView.getFactory().getSpectrumLevelDrawables().get(i1)).getAlpha() < i2)
          ((EqualizerCircle)this.mListeningView.getFactory().getSpectrumLevelDrawables().get(i1)).setAlpha(i2);
        label381: n++;
        break label282;
        if (n == k + 1)
        {
          i2 = (int)(0.2F * (255 - n * 30));
          break label324;
        }
        i2 = (int)(0.3F * (255 - n * 30));
        break label324;
        i++;
        this.previosY = k;
        j++;
      }
      catch (Exception localException)
      {
        break label381;
      }
    }
  }

  public void startThinkingAnimation()
  {
    if (!this.mAnimationRunning)
    {
      if (this.mPortView.getVisibility() != 0)
        break label197;
      this.animationDrawable = new CustomAnimationDrawable();
      this.thinkingLayer[0] = new BitmapDrawable(BitmapFactory.decodeResource(getResources(), 2130837636));
      this.thinkingLayer[1] = new BitmapDrawable(BitmapFactory.decodeResource(getResources(), 2130837635));
      this.idleDrawable = new BitmapDrawable(BitmapFactory.decodeResource(getResources(), 2130837634));
      int k = -140;
      for (int m = 0; m < 15; m++)
      {
        LayerDrawable localLayerDrawable2 = new LayerDrawable(this.thinkingLayer);
        localLayerDrawable2.setLayerInset(1, 0, k, 0, -k);
        k += 20;
        this.animationDrawable.addFrame(localLayerDrawable2, 67);
      }
      this.mGreenBack.setImageDrawable(this.animationDrawable);
      this.mGreenBack.setVisibility(0);
      this.animationDrawable.setOneShot(false);
      this.animationDrawable.start();
    }
    for (this.mAnimationRunning = true; ; this.mAnimationRunning = true)
    {
      return;
      label197: this.animationDrawable = new CustomAnimationDrawable();
      this.thinkingLayer[0] = new BitmapDrawable(BitmapFactory.decodeResource(getResources(), 2130837752));
      this.thinkingLayer[1] = new BitmapDrawable(BitmapFactory.decodeResource(getResources(), 2130837751));
      this.idleDrawable = new BitmapDrawable(BitmapFactory.decodeResource(getResources(), 2130837750));
      int i = -140;
      for (int j = 0; j < 15; j++)
      {
        LayerDrawable localLayerDrawable1 = new LayerDrawable(this.thinkingLayer);
        localLayerDrawable1.setLayerInset(1, i, 0, -i, 0);
        i += 20;
        this.animationDrawable.addFrame(localLayerDrawable1, 67);
      }
      this.mGreenBack.setImageDrawable(this.animationDrawable);
      this.mGreenBack.setVisibility(0);
      this.animationDrawable.setOneShot(false);
      this.animationDrawable.start();
    }
  }

  public void stopThinkingAnimation()
  {
    if (this.animationDrawable != null)
      this.animationDrawable.stop();
    this.mGreenBack.setImageDrawable(this.idleDrawable);
    this.mAnimationRunning = false;
    this.mGreenBack.clearAnimation();
  }

  public static enum ControlMode
  {
    static
    {
      Listening = new ControlMode("Listening", 2);
      Processing = new ControlMode("Processing", 3);
      Empty = new ControlMode("Empty", 4);
      GettingReady = new ControlMode("GettingReady", 5);
      ControlMode[] arrayOfControlMode = new ControlMode[6];
      arrayOfControlMode[0] = Idle;
      arrayOfControlMode[1] = Thinking;
      arrayOfControlMode[2] = Listening;
      arrayOfControlMode[3] = Processing;
      arrayOfControlMode[4] = Empty;
      arrayOfControlMode[5] = GettingReady;
      $VALUES = arrayOfControlMode;
    }
  }

  class CustomAnimationDrawable extends AnimationDrawable
  {
    int countPlayed = 1;

    CustomAnimationDrawable()
    {
    }

    public boolean selectDrawable(int paramInt)
    {
      if ((paramInt == 1) && (CustomMicButton.this.mAnimationRunning))
      {
        int i = this.countPlayed;
        this.countPlayed = (i + 1);
        if (i % 2 == 0)
          CustomMicButton.this.getContext().sendBroadcast(new Intent("com.vlingo.client.app.action.THINKING_AUDIO_SYNC"));
      }
      return super.selectDrawable(paramInt);
    }
  }

  private static class CustomMicButtonHandler extends Handler
  {
    private final WeakReference<CustomMicButton> outer;

    CustomMicButtonHandler(CustomMicButton paramCustomMicButton)
    {
      this.outer = new WeakReference(paramCustomMicButton);
    }

    public void handleMessage(Message paramMessage)
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.customviews.CustomMicButton
 * JD-Core Version:    0.6.0
 */