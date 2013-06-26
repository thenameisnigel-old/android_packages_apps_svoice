package com.vlingo.midas.gui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sec.android.svoice.equalizer.MicEqualizerView;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.midas.gui.customviews.CustomMicButton;
import com.vlingo.midas.gui.customviews.CustomMicButton.ControlMode;
import java.lang.reflect.Array;

public class DrivingControlFragment extends ControlFragment
{
  private int[][] abc;
  private int index = 0;
  public boolean isListeningStart = false;
  private long lastClicked = 0L;
  private int mCurrentVolume = 0;
  CustomMicButton micButton;

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.micButton = ((CustomMicButton)getView().findViewById(2131558621));
    this.micButton.setOnClickListener(new DrivingControlFragment.1(this));
    this.micButton.setOnLongClickListener(new DrivingControlFragment.2(this));
    setmActivityCreated(true);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (!isActivityCreated());
    while (true)
    {
      return;
      setState(getControlFragmentState());
      System.gc();
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = 5;
    arrayOfInt[1] = 128;
    this.abc = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt));
    View localView = paramLayoutInflater.inflate(2130903071, paramViewGroup, false);
    this.micButton = ((CustomMicButton)localView.findViewById(2131558621));
    return localView;
  }

  public void onDestroy()
  {
    this.micButton.setOnClickListener(null);
    this.micButton.setOnLongClickListener(null);
    super.onDestroy();
  }

  public void onSpectrumUpdate(MicEqualizerView paramMicEqualizerView, int[] paramArrayOfInt)
  {
  }

  public boolean performClickFromDriveControl()
  {
    monitorenter;
    while (true)
    {
      try
      {
        long l1 = System.currentTimeMillis();
        if (l1 - this.lastClicked >= 300L)
          continue;
        long l2 = this.lastClicked;
        if (l1 > l2)
          return true;
        this.lastClicked = l1;
        if (getControlFragmentState() == ControlFragment.ControlState.IDLE)
        {
          DialogFlow.getInstance().cancelTTS();
          startRecognition(null);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      if (getControlFragmentState() == ControlFragment.ControlState.ASR_LISTENING)
      {
        DialogFlow.getInstance().endpointReco();
        continue;
      }
      resetThinkingState();
      if ((getControlFragmentState() != ControlFragment.ControlState.ASR_THINKING) && (getControlFragmentState() != ControlFragment.ControlState.ASR_POST_RESPONSE) && (getControlFragmentState() != ControlFragment.ControlState.DIALOG_BUSY))
        continue;
      DialogFlow.getInstance().cancelAudio();
      DialogFlow.getInstance().cancelTurn();
    }
  }

  protected void resetThinkingState()
  {
    this.micButton.stopThinkingAnimation();
    Log.w("Vlingo", "DrivingControl reset ThinkingState");
  }

  public void setBlueHeight(int paramInt)
  {
  }

  protected void setButtonIdle(boolean paramBoolean)
  {
    this.micButton.mListeningView.isAlreadyStart = false;
    setMode(CustomMicButton.ControlMode.Idle);
  }

  protected void setButtonListening()
  {
    this.micButton.mListeningView.isStart = true;
    setMode(CustomMicButton.ControlMode.Listening);
  }

  protected void setButtonThinking()
  {
    this.micButton.mListeningView.isAlreadyStart = false;
    setMode(CustomMicButton.ControlMode.Thinking);
  }

  public boolean setMode(CustomMicButton.ControlMode paramControlMode)
  {
    boolean bool = false;
    Log.w("Vlingo", "DrivingControl Set Mode " + paramControlMode);
    if (this.micButton != null)
      bool = this.micButton.setMode(paramControlMode, false);
    return bool;
  }

  protected void setThinkingAnimation()
  {
    this.micButton.startThinkingAnimation();
    Log.w("Vlingo", "DrivingControl Set ThinkingAnimation");
  }

  public void show(boolean paramBoolean)
  {
    if (this.micButton == null)
      this.micButton = ((CustomMicButton)getView().findViewById(2131558621));
    CustomMicButton localCustomMicButton = this.micButton;
    if (paramBoolean);
    for (int i = 0; ; i = 8)
    {
      localCustomMicButton.setVisibility(i);
      return;
    }
  }

  public void showRMSChange(int paramInt)
  {
  }

  public void showSpectrum(int[] paramArrayOfInt)
  {
    System.arraycopy(paramArrayOfInt, 0, this.abc[this.index], 0, paramArrayOfInt.length);
    this.micButton.postDelayed(new DrivingControlFragment.3(this), 20 * this.index);
    this.index = (1 + this.index);
    this.index %= 5;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.DrivingControlFragment
 * JD-Core Version:    0.6.0
 */