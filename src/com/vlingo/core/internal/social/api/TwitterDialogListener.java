package com.vlingo.core.internal.social.api;

import android.os.Bundle;

public abstract interface TwitterDialogListener
{
  public abstract void onCancel();

  public abstract void onComplete(Bundle paramBundle);

  public abstract void onError(String paramString);

  public abstract void onTwitterError(String paramString);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.social.api.TwitterDialogListener
 * JD-Core Version:    0.6.0
 */