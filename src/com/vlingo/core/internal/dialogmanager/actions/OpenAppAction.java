package com.vlingo.core.internal.dialogmanager.actions;

import android.content.ActivityNotFoundException;
import android.content.Context;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.util.OpenAppUtil.AppInfo;

public class OpenAppAction extends DMAction
{
  private OpenAppUtil.AppInfo appInfo;

  public OpenAppAction appInfo(OpenAppUtil.AppInfo paramAppInfo)
  {
    this.appInfo = paramAppInfo;
    return this;
  }

  protected void execute()
  {
    if (this.appInfo != null);
    while (true)
    {
      try
      {
        getContext().startActivity(this.appInfo.getLaunchIntent());
        getListener().actionSuccess();
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        getListener().actionFail("Activity could not be found.");
        continue;
      }
      String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_tts_NO_APPMATCH_DEMAND);
      getListener().actionFail(str);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.OpenAppAction
 * JD-Core Version:    0.6.0
 */