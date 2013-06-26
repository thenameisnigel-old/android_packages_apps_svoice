package com.vlingo.midas.social;

import com.sec.android.app.sns3.svc.sp.FacebookSSOAPI;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.social.api.FacebookAPI;
import com.vlingo.core.internal.social.api.SocialAPI;
import com.vlingo.core.internal.social.api.SocialNetworkType;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.SocialUtils;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.util.VlingoApplicationInterface;

class SocialAccountActivity$11$1
  implements Runnable
{
  public void run()
  {
    if ((this.this$1.this$0.isFinishing()) || (this.this$1.this$0.mDestroyed))
      SocialAccountActivity.access$100(this.this$1.this$0).clearCredentials();
    while (true)
    {
      return;
      SocialAccountActivity.access$300(this.this$1.this$0);
      Settings.setBoolean("key_social_login_attemp_for_resume", true);
      Settings.setBoolean("key_social_login_attemp_for_user_leave_hint", true);
      this.this$1.this$0.finish();
      if ((!StringUtils.isNullOrWhiteSpace(SocialAccountActivity.access$700(this.this$1.this$0))) && (SocialAccountActivity.access$700(this.this$1.this$0).equalsIgnoreCase("all")))
      {
        if (!FacebookSSOAPI.facebookSSO())
        {
          ApplicationAdapter.getInstance().getVlingoApp().startSocialLogin(ApplicationAdapter.getInstance().getApplicationContext(), SocialNetworkType.FACEBOOK, new FacebookAPI(this.this$1.this$0));
          continue;
        }
        if (!FacebookSSOAPI.isFacebookLoggedIn())
        {
          FacebookSSOAPI.startFacebookSSO(ApplicationAdapter.getInstance().getApplicationContext(), true);
          continue;
        }
        this.this$1.this$0.sendBroadcast(SocialUtils.loginIntent(true));
        continue;
      }
      this.this$1.this$0.sendBroadcast(SocialUtils.loginIntent(true));
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.social.SocialAccountActivity.11.1
 * JD-Core Version:    0.6.0
 */