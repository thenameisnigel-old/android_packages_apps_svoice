package com.vlingo.midas.navigation;

import android.content.Intent;

public abstract interface INavigationUtil
{
  public abstract Intent getIntent();

  public abstract boolean setDestination(String paramString);

  public abstract boolean setDestination(String paramString, long paramLong1, long paramLong2);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.navigation.INavigationUtil
 * JD-Core Version:    0.6.0
 */