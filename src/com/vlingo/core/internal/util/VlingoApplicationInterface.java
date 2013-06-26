package com.vlingo.core.internal.util;

import android.content.Context;
import com.vlingo.core.internal.social.api.SocialAPI;
import com.vlingo.core.internal.social.api.SocialNetworkType;

public abstract interface VlingoApplicationInterface
{
  public abstract boolean isAppInForeground();

  public abstract void startMainActivity();

  public abstract void startMainService(boolean paramBoolean);

  public abstract void startSocialLogin(Context paramContext, SocialNetworkType paramSocialNetworkType, SocialAPI paramSocialAPI);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.VlingoApplicationInterface
 * JD-Core Version:    0.6.0
 */