package com.vlingo.midas.gui;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.sec.android.svoice.equalizer.EqualizerCircle;
import com.sec.android.svoice.equalizer.EqualizerSpectrumFactory;
import com.sec.android.svoice.equalizer.MicEqualizerView;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.settings.VoicePrompt;
import com.vlingo.core.internal.settings.VoicePrompt.Listener;
import com.vlingo.midas.gui.customviews.MicView;
import com.vlingo.midas.iux.IUXManager;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.settings.debug.DebugSettings;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class RegularControlFragmentBase extends ControlFragment
  implements View.OnClickListener
{
  private int DEBUG_UNLOCK_STEP = 0;
  private final int DEBUG_UNLOCK_TIMEOUT_MS = 15000;
  private long DEBUG_UNLOCK_TIMESTAMP = 0L;
  private int[][] abc;
  private AnimationDrawable animationDrawable;
  private ContentFragment dialogFragment;
  protected RelativeLayout fullContainer;
  private int index = 0;
  private VoicePrompt.Listener onChangeListener = new RegularControlFragmentBase.1(this);
  private VoicePrompt.Listener onChangeOffListener = new RegularControlFragmentBase.2(this);
  protected ImageView prompt_offBtn;
  protected ImageView prompt_onBtn;
  protected ImageView reco_idleBtn;
  protected MicView reco_listeningBtn;
  protected MicEqualizerView reco_listeningEqBtn;
  protected ImageView reco_thinkingBtn;
  protected ImageView writeBtn;

  private void finishHWR()
  {
    if (this.dialogFragment != null)
      this.dialogFragment.finishHWR();
  }

  protected void disableHelpWidgetButton()
  {
    if (!isActivityCreated());
    while (true)
    {
      return;
      FragmentActivity localFragmentActivity = getActivity();
      if ((localFragmentActivity == null) || (!(localFragmentActivity instanceof ConversationActivity)))
        continue;
      if (!((ConversationActivity)getActivity()).isDialogMode())
      {
        this.writeBtn.setEnabled(false);
        this.writeBtn.setBackgroundResource(2130838120);
        continue;
      }
      this.writeBtn.setEnabled(false);
      this.writeBtn.setBackgroundResource(2130838120);
    }
  }

  protected void enableHelpWidgetButton()
  {
    if (!isActivityCreated());
    while (true)
    {
      return;
      FragmentActivity localFragmentActivity = getActivity();
      if ((localFragmentActivity == null) || (!(localFragmentActivity instanceof ConversationActivity)))
        continue;
      if (!((ConversationActivity)getActivity()).isDialogMode())
      {
        this.writeBtn.setEnabled(true);
        this.writeBtn.setBackgroundResource(2130837540);
        continue;
      }
      this.writeBtn.setEnabled(true);
      this.writeBtn.setBackgroundResource(2130837540);
    }
  }

  abstract int getThninkingBtnOver();

  abstract int getThninkingMicAnimationList();

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131558500:
    case 2131558795:
    case 2131558501:
    case 2131558796:
    case 2131558494:
    case 2131558797:
    case 2131558497:
    case 2131558799:
    case 2131558498:
    case 2131558800:
    case 2131558503:
    case 2131558793:
    }
    FragmentActivity localFragmentActivity;
    do
    {
      while (true)
      {
        return;
        DialogFlow.getInstance().endpointReco();
        continue;
        resetThinkingState();
        DialogFlow.getInstance().cancelTTS();
        if ((getControlFragmentState() != ControlFragment.ControlState.ASR_THINKING) && (getControlFragmentState() != ControlFragment.ControlState.ASR_POST_RESPONSE))
          continue;
        DialogFlow.getInstance().cancelTurn();
        continue;
        if (getControlFragmentState() == ControlFragment.ControlState.DIALOG_BUSY)
        {
          resetThinkingState();
          DialogFlow.getInstance().cancelTTS();
          DialogFlow.getInstance().cancelTurn();
          continue;
        }
        finishHWR();
        if (!IUXManager.isIUXComplete())
          continue;
        DialogFlow.getInstance().cancelTTS();
        startRecognition(null);
        continue;
        onPromptOnClick(true);
        continue;
        onPromptOffClick(true);
      }
      localFragmentActivity = getActivity();
    }
    while ((localFragmentActivity == null) || (!(localFragmentActivity instanceof ConversationActivity)));
    if (DebugSettings.SHOW_DEBUG)
      ((ConversationActivity)getActivity()).openOptionsMenu();
    while (true)
    {
      finishHWR();
      break;
      ((ConversationActivity)getActivity()).addHelpChoiceWidget();
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (!isActivityCreated());
    FragmentActivity localFragmentActivity;
    do
    {
      return;
      localFragmentActivity = getActivity();
    }
    while ((localFragmentActivity == null) || (!(localFragmentActivity instanceof ConversationActivity)));
    if (getVoicePrompt().isOn())
    {
      this.prompt_onBtn.setVisibility(0);
      this.prompt_offBtn.setVisibility(8);
    }
    while (true)
    {
      if (this.animationDrawable != null)
      {
        this.animationDrawable.stop();
        this.animationDrawable = null;
      }
      setState(getControlFragmentState());
      System.gc();
      break;
      this.prompt_onBtn.setVisibility(8);
      this.prompt_offBtn.setVisibility(0);
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    this.dialogFragment = ((ContentFragment)getFragmentManager().findFragmentById(2131558806));
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = 5;
    arrayOfInt[1] = 128;
    this.abc = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt));
    super.onCreate(paramBundle);
  }

  public void onDestroy()
  {
    this.reco_listeningEqBtn.setOnClickListener(null);
    this.reco_thinkingBtn.setOnClickListener(null);
    this.reco_idleBtn.setOnClickListener(null);
    this.prompt_onBtn.setOnClickListener(null);
    this.prompt_offBtn.setOnClickListener(null);
    this.writeBtn.setOnClickListener(null);
    this.reco_listeningEqBtn = null;
    super.onDestroy();
  }

  public void onLanguageChanged()
  {
    this.prompt_onBtn.setContentDescription(getResources().getString(2131362605));
    this.prompt_offBtn.setContentDescription(getResources().getString(2131362605));
    this.writeBtn.setContentDescription(getResources().getString(2131362269));
    this.reco_listeningEqBtn.setContentDescription(getResources().getString(2131362637));
    this.reco_thinkingBtn.setContentDescription(getResources().getString(2131362637));
    this.reco_idleBtn.setContentDescription(getResources().getString(2131362637));
  }

  public void onPause()
  {
    if ((this.dialogFragment != null) && (!this.dialogFragment.getEnabledHWR()))
      stopSpotting();
    super.onPause();
  }

  public void onPromptOffClick(boolean paramBoolean)
  {
    VoicePrompt localVoicePrompt = getVoicePrompt();
    if (!localVoicePrompt.isOn())
    {
      if (paramBoolean)
        MidasSettings.setBoolean("manually_prompt_on_in_talkback", true);
      localVoicePrompt.setManually(paramBoolean);
      localVoicePrompt.addListener(this.onChangeOffListener).on();
      finishHWR();
    }
  }

  public void onPromptOnClick(boolean paramBoolean)
  {
    VoicePrompt localVoicePrompt = getVoicePrompt();
    if (localVoicePrompt.isOn())
    {
      MidasSettings.setBoolean("manually_prompt_on_in_talkback", false);
      localVoicePrompt.setManually(paramBoolean);
      localVoicePrompt.addListener(this.onChangeListener).off();
      finishHWR();
    }
  }

  public void onSpectrumUpdate(MicEqualizerView paramMicEqualizerView, int[] paramArrayOfInt)
  {
    if ((paramMicEqualizerView == null) || (paramArrayOfInt == null));
    label47: label331: label592: 
    while (true)
    {
      return;
      int i = 0;
      int j = 0;
      int k;
      int m;
      int n;
      int i1;
      float f;
      int i2;
      if (getResources().getConfiguration().orientation == 1)
      {
        k = 4;
        m = 30;
        n = 14;
        i1 = 2;
        f = 1.8F;
        if (paramMicEqualizerView.getFactory() == null)
          break label255;
        i2 = 0;
      }
      while (true)
      {
        if (i2 >= paramArrayOfInt.length)
          break label592;
        if (i < m)
        {
          int i3;
          label110: int i4;
          int i8;
          int i9;
          if (i < (int)(0.25F * m))
          {
            i3 = -1 + (i1 + (int)(0.300000011920929D * Math.sqrt(Math.sqrt(paramArrayOfInt[i2])) * f));
            if ((i <= 0) || (i3 - j <= 1))
              break label331;
            i3 = j + (int)(2.0D * Math.random());
            if (i3 > n)
              i3 = n;
            if (i3 < i1)
              i3 = i1;
            i4 = 0;
            if (i4 >= i3)
              break label371;
            i8 = i + i4 * m;
            i9 = 255 - (int)(255.0F / n * i4);
            if (i9 <= 255)
              break label360;
            i9 = 255;
          }
          while (true)
          {
            ((EqualizerCircle)paramMicEqualizerView.getFactory().getSpectrumLevelDrawables().get(i8)).setAlpha(i9);
            i4++;
            break label161;
            k = 8;
            m = 14;
            n = 30;
            i1 = 4;
            f = 3.8F;
            break label47;
            label255: break;
            if (i > (int)(0.6F * m))
            {
              i3 = -1 + (i1 + (int)(0.5D * Math.sqrt(Math.sqrt(paramArrayOfInt[i2])) * f));
              break label110;
            }
            i3 = -1 + (i1 + (int)(0.699999988079071D * Math.sqrt(Math.sqrt(paramArrayOfInt[i2])) * f));
            break label110;
            if ((i <= 0) || (j - i3 <= 1))
              break label136;
            i3 = j - (int)(2.0D * Math.random());
            break label136;
            label360: if (i9 >= 0)
              continue;
            i9 = 0;
          }
          label371: if (i3 <= (int)(0.7F * n))
          {
            int i5 = i3;
            if (i5 < i3 + 3)
            {
              int i6 = i + i5 * m;
              int i7;
              if (i5 == i3)
              {
                i7 = (int)(0.6F * (255 - (int)(255.0F / n * i5)));
                if (i7 >= 0)
                  break label557;
                i7 = 0;
              }
              while (true)
              {
                if (((EqualizerCircle)paramMicEqualizerView.getFactory().getSpectrumLevelDrawables().get(i6)).getAlpha() < i7)
                  ((EqualizerCircle)paramMicEqualizerView.getFactory().getSpectrumLevelDrawables().get(i6)).setAlpha(i7);
                i5++;
                break;
                if (i5 == i3 + 1)
                {
                  i7 = (int)(0.3F * (255 - (int)(255.0F / n * i5)));
                  break label437;
                }
                i7 = (int)(0.2F * (255 - (int)(255.0F / n * i5)));
                break label437;
                if (i7 <= 255)
                  continue;
                i7 = 255;
              }
            }
          }
          i++;
          j = i3;
        }
        paramMicEqualizerView.postInvalidate();
        i2 += k;
      }
    }
  }

  public void onStop()
  {
    finishHWR();
    super.onStop();
  }

  public boolean performClickFromDriveControl()
  {
    return false;
  }

  public void removeControlPanel()
  {
    this.fullContainer.setVisibility(8);
  }

  protected void resetThinkingState()
  {
    if (this.animationDrawable != null)
      this.animationDrawable.stop();
    this.reco_thinkingBtn.setBackgroundResource(getThninkingBtnOver());
    this.animationDrawable = null;
  }

  public void setBlueHeight(int paramInt)
  {
  }

  protected void setButtonIdle(boolean paramBoolean)
  {
    this.reco_listeningEqBtn.setVisibility(8);
    this.reco_thinkingBtn.setVisibility(8);
    this.reco_idleBtn.setVisibility(0);
    this.reco_idleBtn.setEnabled(paramBoolean);
  }

  protected void setButtonListening()
  {
    this.reco_listeningEqBtn.mEqState = 0;
    this.reco_listeningEqBtn.setVisibility(0);
    this.reco_thinkingBtn.setVisibility(8);
    this.reco_idleBtn.setVisibility(8);
    this.reco_listeningEqBtn.requestFocus();
  }

  protected void setButtonThinking()
  {
    this.reco_listeningEqBtn.isAlreadyStart = false;
    this.reco_listeningEqBtn.setVisibility(8);
    this.reco_thinkingBtn.setVisibility(0);
    this.reco_idleBtn.setVisibility(8);
  }

  public void setNormalPanel()
  {
    this.fullContainer.setVisibility(0);
    this.reco_listeningEqBtn.setOrientation(getResources().getConfiguration().orientation);
    this.reco_listeningEqBtn.setOnClickListener(this);
    this.reco_thinkingBtn.setOnClickListener(this);
    this.reco_idleBtn.setOnClickListener(this);
    this.prompt_onBtn.setOnClickListener(this);
    this.prompt_offBtn.setOnClickListener(this);
    this.writeBtn.setOnClickListener(this);
    this.writeBtn.setOnLongClickListener(new RegularControlFragmentBase.3(this));
    this.prompt_onBtn.setOnLongClickListener(new RegularControlFragmentBase.4(this));
    this.prompt_offBtn.setOnLongClickListener(new RegularControlFragmentBase.5(this));
    if (getVoicePrompt().isOn())
    {
      this.prompt_onBtn.setVisibility(0);
      this.prompt_offBtn.setVisibility(8);
    }
    while (true)
    {
      this.writeBtn.setVisibility(0);
      return;
      this.prompt_onBtn.setVisibility(8);
      this.prompt_offBtn.setVisibility(0);
    }
  }

  protected void setPrompt()
  {
    if (getVoicePrompt().isOn())
    {
      this.prompt_onBtn.setVisibility(0);
      this.prompt_offBtn.setVisibility(8);
    }
    while (true)
    {
      return;
      this.prompt_onBtn.setVisibility(8);
      this.prompt_offBtn.setVisibility(0);
    }
  }

  protected void setThinkingAnimation()
  {
    this.animationDrawable = ((AnimationDrawable)getResources().getDrawable(getThninkingMicAnimationList()));
    this.reco_thinkingBtn.setBackgroundDrawable(this.animationDrawable);
    this.animationDrawable.start();
  }

  public void showRMSChange(int paramInt)
  {
    if (!isActivityCreated());
  }

  public void showSpectrum(int[] paramArrayOfInt)
  {
    if (!isActivityCreated());
    while (true)
    {
      return;
      if ((this.reco_listeningEqBtn != null) && (this.reco_listeningEqBtn.getVisibility() == 0))
      {
        System.arraycopy(paramArrayOfInt, 0, this.abc[this.index], 0, paramArrayOfInt.length);
        this.reco_listeningEqBtn.postDelayed(new RegularControlFragmentBase.6(this), 20 * this.index);
        this.index = (1 + this.index);
        this.index %= 5;
        continue;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.RegularControlFragmentBase
 * JD-Core Version:    0.6.0
 */