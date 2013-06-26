package com.vlingo.midas.gui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.sec.android.svoice.equalizer.MicEqualizerView;

public class LandRegularControlFragment extends RegularControlFragmentBase
  implements View.OnClickListener
{
  int getThninkingBtnOver()
  {
    return 2130837546;
  }

  int getThninkingMicAnimationList()
  {
    return 2131034115;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.fullContainer = ((RelativeLayout)getView().findViewById(2131558790));
    this.reco_listeningEqBtn = ((MicEqualizerView)getView().findViewById(2131558795));
    this.reco_thinkingBtn = ((ImageView)getView().findViewById(2131558796));
    this.reco_idleBtn = ((ImageView)getView().findViewById(2131558797));
    this.prompt_onBtn = ((ImageView)getView().findViewById(2131558799));
    this.prompt_offBtn = ((ImageView)getView().findViewById(2131558800));
    this.writeBtn = ((ImageView)getView().findViewById(2131558793));
    setmActivityCreated(true);
    setNormalPanel();
    setState(ControlFragment.ControlState.IDLE);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903107, paramViewGroup, false);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.LandRegularControlFragment
 * JD-Core Version:    0.6.0
 */