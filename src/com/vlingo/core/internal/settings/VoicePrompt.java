package com.vlingo.core.internal.settings;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class VoicePrompt
{
  private ConfirmationDelegate confirmationDelegate;
  private Set<Listener> listeners = new CopyOnWriteArraySet();
  private boolean manually = false;

  private void confirmSetting(boolean paramBoolean)
  {
    1 local1 = new Runnable(paramBoolean)
    {
      public void run()
      {
        Settings.setBoolean("use_voice_prompt", this.val$_setting);
        VoicePrompt.this.notifyListeners(this.val$_setting);
      }
    };
    if ((shouldAsk()) && (this.manually))
      getConfirmationDelegate().confirm(this, local1);
    while (true)
    {
      return;
      local1.run();
    }
  }

  private ConfirmationDelegate getConfirmationDelegate()
  {
    if (this.confirmationDelegate == null);
    for (Object localObject = new ConfirmationDelegate()
    {
      public void confirm(VoicePrompt paramVoicePrompt, Runnable paramRunnable)
      {
        paramRunnable.run();
      }
    }
    ; ; localObject = this.confirmationDelegate)
    {
      this.confirmationDelegate = ((ConfirmationDelegate)localObject);
      return localObject;
    }
  }

  public static boolean isEnabled()
  {
    return new VoicePrompt().isOn();
  }

  private void notifyListeners(boolean paramBoolean)
  {
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
      ((Listener)localIterator.next()).onChanged(paramBoolean);
  }

  private boolean shouldAsk()
  {
    return Settings.getBoolean("use_voice_prompt_confirm_with_user", true);
  }

  public VoicePrompt addListener(Listener paramListener)
  {
    this.listeners.add(paramListener);
    return this;
  }

  public boolean isManually()
  {
    return this.manually;
  }

  public boolean isOn()
  {
    return Settings.getBoolean("use_voice_prompt", true);
  }

  public void off()
  {
    confirmSetting(false);
  }

  public void on()
  {
    confirmSetting(true);
  }

  public VoicePrompt registerDelegate(ConfirmationDelegate paramConfirmationDelegate)
  {
    this.confirmationDelegate = paramConfirmationDelegate;
    return this;
  }

  public VoicePrompt removeListener(Listener paramListener)
  {
    this.listeners.remove(paramListener);
    return this;
  }

  public void setManually(boolean paramBoolean)
  {
    this.manually = paramBoolean;
  }

  public void shouldAsk(boolean paramBoolean)
  {
    Settings.setBoolean("use_voice_prompt_confirm_with_user", paramBoolean);
  }

  public VoicePrompt unregisterDelegate(ConfirmationDelegate paramConfirmationDelegate)
  {
    if (this.confirmationDelegate == paramConfirmationDelegate)
      this.confirmationDelegate = null;
    return this;
  }

  public static abstract interface ConfirmationDelegate
  {
    public abstract void confirm(VoicePrompt paramVoicePrompt, Runnable paramRunnable);
  }

  public static abstract interface Listener
  {
    public abstract void onChanged(boolean paramBoolean);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.settings.VoicePrompt
 * JD-Core Version:    0.6.0
 */