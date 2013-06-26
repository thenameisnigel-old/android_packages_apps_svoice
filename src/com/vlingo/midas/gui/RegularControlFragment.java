package com.vlingo.midas.gui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.sec.android.svoice.equalizer.MicEqualizerView;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.midas.settings.MidasSettings;

public class RegularControlFragment extends RegularControlFragmentBase
  implements View.OnClickListener
{
  int getThninkingBtnOver()
  {
    return 2130837545;
  }

  int getThninkingMicAnimationList()
  {
    return 2131034114;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.fullContainer = ((RelativeLayout)getView().findViewById(2131558493));
    this.reco_listeningEqBtn = ((MicEqualizerView)getView().findViewById(2131558500));
    this.reco_thinkingBtn = ((ImageView)getView().findViewById(2131558501));
    this.reco_idleBtn = ((ImageView)getView().findViewById(2131558494));
    this.prompt_onBtn = ((ImageView)getView().findViewById(2131558497));
    this.prompt_offBtn = ((ImageView)getView().findViewById(2131558498));
    if (((getActivity() instanceof ConversationActivity)) && (ClientSuppliedValues.isTalkbackOn()) && (!MidasSettings.getBoolean("manually_prompt_on_in_talkback", false)))
      onPromptOnClick(false);
    this.writeBtn = ((ImageView)getView().findViewById(2131558503));
    setmActivityCreated(true);
    setNormalPanel();
    setState(ControlFragment.ControlState.IDLE);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903043, paramViewGroup, false);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.RegularControlFragment
 * JD-Core Version:    0.6.0
 */