package com.vlingo.core.internal.dialogmanager.actions.interfaces;

import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;

public abstract interface SettingChangeInterface extends ActionInterface
{
  public abstract SettingChangeInterface VVSlistener(VVSActionHandlerListener paramVVSActionHandlerListener);

  public abstract SettingChangeInterface alreadySet(String paramString);

  public abstract SettingChangeInterface confirmOff(String paramString);

  public abstract SettingChangeInterface confirmOffTTS(String paramString);

  public abstract SettingChangeInterface confirmOn(String paramString);

  public abstract SettingChangeInterface confirmOnTTS(String paramString);

  public abstract SettingChangeInterface name(String paramString);

  public abstract SettingChangeInterface state(String paramString);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.interfaces.SettingChangeInterface
 * JD-Core Version:    0.6.0
 */