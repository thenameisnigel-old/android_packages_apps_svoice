package com.vlingo.midas.gui;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import java.util.ArrayList;

public class AnimationUtils
{
  public static final int DURATION_DEFAULT = 300;

  public static Animation fadeInAnimation()
  {
    return fadeInAnimation(300);
  }

  public static Animation fadeInAnimation(int paramInt)
  {
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
    localAlphaAnimation.setDuration(paramInt);
    localAlphaAnimation.setInterpolator(new DecelerateInterpolator());
    return localAlphaAnimation;
  }

  public static Animation fadeOutAnimation()
  {
    return fadeOutAnimation(300);
  }

  public static Animation fadeOutAnimation(int paramInt)
  {
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.0F);
    localAlphaAnimation.setDuration(paramInt);
    localAlphaAnimation.setInterpolator(new AccelerateInterpolator());
    return localAlphaAnimation;
  }

  public static Animation inFromBottomAnimation()
  {
    return inFromBottomAnimation(300);
  }

  public static Animation inFromBottomAnimation(int paramInt)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 1.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(paramInt);
    localTranslateAnimation.setInterpolator(new DecelerateInterpolator());
    return localTranslateAnimation;
  }

  public static Animation inFromTopAnimation()
  {
    return inFromTopAnimation(300);
  }

  public static Animation inFromTopAnimation(int paramInt)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, -1.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(paramInt);
    localTranslateAnimation.setInterpolator(new DecelerateInterpolator());
    return localTranslateAnimation;
  }

  public static Animation outToBottomAnimation()
  {
    return outToBottomAnimation(300);
  }

  public static Animation outToBottomAnimation(int paramInt)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 0.0F, 2, 1.0F);
    localTranslateAnimation.setDuration(paramInt);
    localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
    return localTranslateAnimation;
  }

  public static Animation outToTopAnimation()
  {
    return outToTopAnimation(300);
  }

  public static Animation outToTopAnimation(int paramInt)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 0.0F, 2, -1.0F);
    localTranslateAnimation.setDuration(paramInt);
    localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
    return localTranslateAnimation;
  }

  public static abstract class AnimationGroup extends ArrayList<Animation>
    implements Animation.AnimationListener
  {
    public AnimationGroup()
    {
    }

    public AnimationGroup(Animation paramAnimation)
    {
      add(paramAnimation);
    }

    public void add(Animation[] paramArrayOfAnimation)
    {
      int i = paramArrayOfAnimation.length;
      for (int j = 0; j < i; j++)
        add(paramArrayOfAnimation[j]);
    }

    public boolean add(Animation paramAnimation)
    {
      paramAnimation.setAnimationListener(this);
      return super.add(paramAnimation);
    }

    public abstract void onAnimationEnd();

    public void onAnimationEnd(Animation paramAnimation)
    {
      monitorenter;
      try
      {
        if (contains(paramAnimation))
          remove(paramAnimation);
        if (size() == 0)
          onAnimationEnd();
        monitorexit;
        return;
      }
      finally
      {
        localObject = finally;
        monitorexit;
      }
      throw localObject;
    }

    public void onAnimationRepeat(Animation paramAnimation)
    {
    }

    public void onAnimationStart(Animation paramAnimation)
    {
    }
  }

  public static class AnimationListenerBase
    implements Animation.AnimationListener
  {
    public void onAnimationEnd()
    {
    }

    public void onAnimationEnd(Animation paramAnimation)
    {
      onAnimationEnd();
    }

    public void onAnimationRepeat(Animation paramAnimation)
    {
    }

    public void onAnimationStart(Animation paramAnimation)
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.AnimationUtils
 * JD-Core Version:    0.6.0
 */