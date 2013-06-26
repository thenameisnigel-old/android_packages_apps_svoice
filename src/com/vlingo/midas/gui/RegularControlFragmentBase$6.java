package com.vlingo.midas.gui;

class RegularControlFragmentBase$6
  implements Runnable
{
  private int mIndex = RegularControlFragmentBase.access$200(this.this$0);

  public void run()
  {
    this.this$0.onSpectrumUpdate(this.this$0.reco_listeningEqBtn, RegularControlFragmentBase.access$300(this.this$0)[this.mIndex]);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.RegularControlFragmentBase.6
 * JD-Core Version:    0.6.0
 */