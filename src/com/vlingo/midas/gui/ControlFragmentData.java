package com.vlingo.midas.gui;

import java.util.ArrayList;

public class ControlFragmentData
{
  private static ControlFragmentData singletonObject;
  private ControlFragment.ControlState currentState = ControlFragment.ControlState.IDLE;
  private boolean isPaused = true;
  private ArrayList<String> mTTSOnIdleList = new ArrayList();
  private boolean startRecoOnSpotterStop = false;

  public static ControlFragmentData getIntance()
  {
    if (singletonObject == null)
      singletonObject = new ControlFragmentData();
    return singletonObject;
  }

  public ControlFragment.ControlState getCurrentState()
  {
    return this.currentState;
  }

  public ArrayList<String> getTTSOnIdleList()
  {
    return this.mTTSOnIdleList;
  }

  public boolean isPaused()
  {
    return this.isPaused;
  }

  public boolean isStartRecoOnSpotterStop()
  {
    return this.startRecoOnSpotterStop;
  }

  public void setCurrentState(ControlFragment.ControlState paramControlState)
  {
    this.currentState = paramControlState;
  }

  public void setPaused(boolean paramBoolean)
  {
    this.isPaused = paramBoolean;
  }

  public void setStartRecoOnSpotterStop(boolean paramBoolean)
  {
    this.startRecoOnSpotterStop = paramBoolean;
  }

  public void setTTSOnIdleList(ArrayList<String> paramArrayList)
  {
    this.mTTSOnIdleList = paramArrayList;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.ControlFragmentData
 * JD-Core Version:    0.6.0
 */