package com.vlingo.core.internal.dialogmanager.actions.interfaces;

import java.util.List;

public abstract interface SendMessageInterface extends ActionInterface
{
  public abstract SendMessageInterface address(String paramString);

  public abstract SendMessageInterface addresses(List<String> paramList);

  public abstract SendMessageInterface message(String paramString);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.interfaces.SendMessageInterface
 * JD-Core Version:    0.6.0
 */