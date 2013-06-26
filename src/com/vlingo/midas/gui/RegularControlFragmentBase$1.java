package com.vlingo.midas.gui;

import android.widget.ImageView;
import com.vlingo.core.internal.settings.VoicePrompt.Listener;

class RegularControlFragmentBase$1
  implements VoicePrompt.Listener
{
  public void onChanged(boolean paramBoolean)
  {
    if (this.this$0.isActivityCreated())
    {
      this.this$0.prompt_onBtn.setVisibility(8);
      this.this$0.prompt_offBtn.setVisibility(0);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.RegularControlFragmentBase.1
 * JD-Core Version:    0.6.0
 */