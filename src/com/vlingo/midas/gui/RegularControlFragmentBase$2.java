package com.vlingo.midas.gui;

import android.widget.ImageView;
import com.vlingo.core.internal.settings.VoicePrompt.Listener;

class RegularControlFragmentBase$2
  implements VoicePrompt.Listener
{
  public void onChanged(boolean paramBoolean)
  {
    if (this.this$0.isActivityCreated())
    {
      this.this$0.prompt_onBtn.setVisibility(0);
      this.this$0.prompt_offBtn.setVisibility(8);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.RegularControlFragmentBase.2
 * JD-Core Version:    0.6.0
 */