package com.vlingo.midas.util;

import android.view.View;

public abstract interface DrivingWidgetInterface
{
  public abstract int getDecreasedHeight();

  public abstract int getProperHeight();

  public abstract boolean isDecreasedSize();

  public abstract int setNightMode(boolean paramBoolean);

  public abstract int setWidgetToDecreasedSize(boolean paramBoolean);

  public abstract void startAnimationTranslate(View paramView);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.DrivingWidgetInterface
 * JD-Core Version:    0.6.0
 */