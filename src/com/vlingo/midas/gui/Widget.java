package com.vlingo.midas.gui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import com.vlingo.core.internal.audio.AudioRequest;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.RegisterSoundPool;
import com.vlingo.midas.RegisterSoundPool.SoundType;
import com.vlingo.midas.settings.MidasSettings;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class Widget<Type> extends RelativeLayout
  implements IAudioPlaybackService.AudioPlaybackListener
{
  public static int multiWidgetItemsInitialMax = MidasSettings.getMultiWidgetItemsInitialMax();
  public static int multiWidgetItemsUltimateMax = MidasSettings.getMultiWidgetItemsUltimateMax();
  private final int ANIMATE_TRANSLATE_UP_DURATION = 866;
  private boolean isPlayingAudio = false;

  public Widget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void disableEnableControls(boolean paramBoolean, ViewGroup paramViewGroup)
  {
    int i = 0;
    if (i < paramViewGroup.getChildCount())
    {
      View localView = paramViewGroup.getChildAt(i);
      if ((localView instanceof RatingBar));
      while (true)
      {
        if ((localView instanceof ViewGroup))
          disableEnableControls(paramBoolean, (ViewGroup)localView);
        i++;
        break;
        localView.setEnabled(paramBoolean);
      }
    }
  }

  public static int getMultiWidgetItemsInitialMax()
  {
    return multiWidgetItemsInitialMax;
  }

  public static int getMultiWidgetItemsUltimateMax()
  {
    return multiWidgetItemsUltimateMax;
  }

  protected CharSequence getAccessibilityString(CharSequence paramCharSequence, int paramInt)
  {
    Object localObject = paramCharSequence;
    switch (paramInt)
    {
    case 0:
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      return localObject;
      localObject = localObject + getContext().getString(2131362670);
      continue;
      localObject = localObject + getContext().getString(2131362669);
    }
  }

  public abstract void initialize(Type paramType, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener);

  public boolean isPlayingAudio()
  {
    return this.isPlayingAudio;
  }

  public abstract boolean isTranslated();

  public abstract boolean isWidgetReplaceable();

  protected void measureListviewHeight(ListView paramListView, int paramInt, boolean paramBoolean)
  {
    View localView = paramListView.getChildAt(0);
    ViewGroup.LayoutParams localLayoutParams = paramListView.getLayoutParams();
    int i = paramListView.getCount();
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter != null)
      i = localListAdapter.getCount();
    float f;
    if (localView != null)
      f = i * localView.getHeight();
    while (true)
    {
      int j = paramListView.getPaddingTop();
      int k = paramListView.getPaddingBottom();
      localLayoutParams.height = (int)(f + j + k);
      if (paramListView.getHeight() != localLayoutParams.height)
      {
        paramListView.setLayoutParams(localLayoutParams);
        if (paramBoolean)
          setMeasuredDimension(localLayoutParams.width, localLayoutParams.height);
      }
      return;
      f = getResources().getDimension(paramInt) * i;
    }
  }

  public boolean mustBeShown()
  {
    return true;
  }

  public void onRecognitionStarted()
  {
  }

  public void onRequestCancelled(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled paramReasonCanceled)
  {
    this.isPlayingAudio = false;
  }

  public void onRequestDidPlay(AudioRequest paramAudioRequest)
  {
    this.isPlayingAudio = false;
  }

  public void onRequestIgnored(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored paramReasonIgnored)
  {
    this.isPlayingAudio = false;
  }

  public void onRequestWillPlay(AudioRequest paramAudioRequest)
  {
    this.isPlayingAudio = true;
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if ((paramInt2 > 0) && (!isTranslated()))
    {
      startAnimationTranslate(this);
      RegisterSoundPool.play(RegisterSoundPool.SoundType.WIDGET_IN_OUT);
    }
  }

  public void onStop()
  {
  }

  public void processSystemMessage(Intent paramIntent)
  {
  }

  protected void promptUser(String paramString)
  {
    try
    {
      Class localClass = getContext().getClass();
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = String.class;
      Method localMethod = localClass.getMethod("promptUser", arrayOfClass);
      Context localContext = getContext();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramString;
      localMethod.invoke(localContext, arrayOfObject);
      label59: return;
    }
    catch (Exception localException)
    {
      break label59;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      break label59;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      break label59;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      break label59;
    }
  }

  public void retire()
  {
    disableEnableControls(false, this);
  }

  public void startAnimationTranslate(View paramView)
  {
    float[] arrayOfFloat = new float[2];
    arrayOfFloat[0] = paramView.getRootView().getHeight();
    arrayOfFloat[1] = 0.0F;
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView, "translationY", arrayOfFloat);
    localObjectAnimator.setDuration(866L);
    localObjectAnimator.start();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.Widget
 * JD-Core Version:    0.6.0
 */