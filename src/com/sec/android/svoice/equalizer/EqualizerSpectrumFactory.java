package com.sec.android.svoice.equalizer;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.util.ArrayList;

public class EqualizerSpectrumFactory
{
  public static final int NORMAL = 1;
  public static final int PREFIX_ZERO;
  final int defaultDPI = 480;
  private ArrayList<EqualizerCircle> mSpectrumLevelDrawables;

  public void destroy()
  {
    if (this.mSpectrumLevelDrawables != null)
    {
      for (int i = 0; i < this.mSpectrumLevelDrawables.size(); i++)
        ((EqualizerCircle)this.mSpectrumLevelDrawables.get(i)).blueDot.setCallback(null);
      this.mSpectrumLevelDrawables.clear();
    }
  }

  public ArrayList<EqualizerCircle> getSpectrumLevelDrawables()
  {
    return this.mSpectrumLevelDrawables;
  }

  public void registerNormalSpectrumDrawables(Context paramContext, String paramString, int paramInt1, int paramInt2, int paramInt3, float paramFloat1, float paramFloat2)
  {
    destroy();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((Activity)paramContext).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    float f = localDisplayMetrics.densityDpi / 480.0F;
    this.mSpectrumLevelDrawables = new ArrayList();
    Resources localResources = paramContext.getResources();
    String str1 = paramContext.getPackageName();
    for (int i = 0; i < paramInt2; i++)
    {
      String str2;
      Drawable localDrawable;
      int j;
      label130: int k;
      int m;
      int n;
      if (i < 9)
      {
        str2 = "0";
        localDrawable = localResources.getDrawable(localResources.getIdentifier(paramString + str2 + (i + 1), "drawable", str1));
        j = 0;
        if (j >= paramInt1)
          continue;
        k = (int)(f * (0.0F + paramFloat1 + j * 13));
        m = (int)(0.0F - f * (-paramFloat2 + 13.0F * i) + paramInt3);
        n = 255 - i * 10;
        if (n <= 255)
          break label233;
      }
      while (true)
      {
        EqualizerCircle localEqualizerCircle = new EqualizerCircle(localDrawable, k, m, 0);
        this.mSpectrumLevelDrawables.add(localEqualizerCircle);
        j++;
        break label130;
        str2 = "";
        break;
        label233: if (n >= 0)
          continue;
      }
    }
  }

  public void registerSpectrumDrawables(Context paramContext, String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    destroy();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((Activity)paramContext).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    float f = localDisplayMetrics.densityDpi / 480.0F;
    this.mSpectrumLevelDrawables = new ArrayList();
    Resources localResources = paramContext.getResources();
    String str = paramContext.getPackageName();
    for (int i = 0; i < paramInt2; i++)
    {
      Drawable localDrawable = localResources.getDrawable(localResources.getIdentifier(paramString + "0" + (i + 1), "drawable", str));
      int j = 0;
      if (j >= paramInt1)
        continue;
      int k = (int)(0.0F + f * (j * 20));
      int m = (int)(0.0F - f * (20.0F * i) + paramInt3);
      int n = 255 - i * 20;
      if (n > 255);
      while (true)
      {
        EqualizerCircle localEqualizerCircle = new EqualizerCircle(localDrawable, k, m, 0);
        this.mSpectrumLevelDrawables.add(localEqualizerCircle);
        j++;
        break;
        if (n >= 0)
          continue;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.svoice.equalizer.EqualizerSpectrumFactory
 * JD-Core Version:    0.6.0
 */