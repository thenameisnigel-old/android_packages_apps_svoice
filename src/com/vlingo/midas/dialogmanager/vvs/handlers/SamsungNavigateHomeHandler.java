package com.vlingo.midas.dialogmanager.vvs.handlers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.sec.multiwindow.MultiWindowManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.actions.ExecuteIntentAction;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.gui.ConversationActivity;
import com.vlingo.midas.gui.ConversationActivity.DrivingMode;
import com.vlingo.sdk.internal.util.PackageUtil;
import com.vlingo.sdk.recognition.VLAction;

public class SamsungNavigateHomeHandler extends SamsungNavBaseHandler
{
  private static final String ACTION_NAVIGATE_CHINESE = "com.autonavi.xmgd.action.NAVIGATE";
  private DisplayMetrics mMetrics = new DisplayMetrics();

  private boolean doExecuteActionOriginal(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener, String paramString)
  {
    if (!StringUtils.isNullOrWhiteSpace(paramString))
    {
      unified().showSystemTurn(ResourceIdProvider.string.core_navigate_home);
      if (VlingoAndroidCore.isChineseBuild())
        ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).name("com.autonavi.xmgd.action.NAVIGATE").extra("target," + paramString).queue();
    }
    while (true)
    {
      return false;
      ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).name("android.intent.action.VIEW").argument("google.navigation:q=" + paramString.replaceAll(" ", "+")).broadcast(false).queue();
      continue;
      unified().showSystemTurn(ResourceIdProvider.string.core_nav_home_prompt);
    }
  }

  private boolean doExecuteActionSamsung(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener, String paramString)
  {
    Context localContext = paramVVSActionHandlerListener.getActivityContext();
    Intent localIntent1 = null;
    int i = 0;
    ((Activity)localContext).getWindowManager().getDefaultDisplay().getMetrics(this.mMetrics);
    Intent localIntent2 = new Intent("com.sec.android.action.ARRANGE_CONTROLL_BAR");
    if (localContext.getResources().getConfiguration().orientation == 2)
    {
      localIntent2.putExtra("com.sec.android.extra.CONTROL_BAR_POS", this.mMetrics.widthPixels - localContext.getResources().getDimensionPixelSize(2131427350) - localContext.getResources().getDimensionPixelSize(2131427351));
      localContext.sendBroadcast(localIntent2);
      if (StringUtils.isNullOrWhiteSpace(paramString))
        break label586;
      unified().showSystemTurn(ResourceIdProvider.string.core_navigate_home);
      if (!PackageUtil.isAppInstalled("com.skt.skaf.l001mtm091", 1))
        break label301;
      localIntent1 = new Intent();
      localIntent1.setClassName("com.skt.skaf.l001mtm091", "com.skt.tmap.activity.TmapIntroActivity");
      localIntent1.setData(Uri.parse(String.valueOf("tmap://search?name=" + paramString)));
      label180: if (localIntent1 != null)
      {
        localIntent1.setFlags(268435456);
        localIntent1.putExtras(new MultiWindowManager(localContext).makeIntent(MultiWindowManager.FLAG_STYLE_FREE | MultiWindowManager.ZONE_A, null));
        if (!PackageUtil.isAppInstalled("kt.navi", 1))
          break label599;
        localContext.sendBroadcast(localIntent1);
      }
    }
    while (true)
    {
      while (true)
      {
        ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).intent(localIntent2).broadcast(true).queue();
        return false;
        localIntent2.putExtra("com.sec.android.extra.CONTROL_BAR_POS", this.mMetrics.heightPixels - localContext.getResources().getDimensionPixelSize(2131427350) - localContext.getResources().getDimensionPixelSize(2131427351));
        break;
        label301: if (PackageUtil.isAppInstalled("kt.navi", 1))
        {
          localIntent1 = new Intent("kt.navi.OLLEH_NAVIGATION");
          localIntent1.putExtra("CALLER_PACKAGE_NAME", "com.vlingo.midas");
          localIntent1.putExtra("EXTERN_LINK_TYPE", 1);
          localIntent1.putExtra("LINK_SEARCH_WORD", paramString);
          break label180;
        }
        if (!PackageUtil.isAppInstalled("com.mnsoft.mappy", 1))
          break label468;
        try
        {
          i = localContext.getPackageManager().getPackageInfo("com.mnsoft.mappy", 0).versionCode;
          if (i > 1)
          {
            localIntent1 = new Intent("com.mnsoft.mappy.MAPPYSMART_EXTERNAL_SERVICE");
            localIntent1.putExtra("com.mnsoft.mappy.action.REQUEST_TO_MAPPYSMART", 65536);
            localIntent1.putExtra("com.mnsoft.mappy.extra.EXTRA_KIND", "poi");
            localIntent1.putExtra("com.mnsoft.mappy.extra.EXTRA_VALUE", paramString);
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          while (true)
            localNameNotFoundException.printStackTrace();
          localIntent1 = new Intent();
          localIntent1.setClassName("com.mnsoft.mappy", "com.mnsoft.mappy.activities.etc.IntroActivity");
        }
      }
      break label180;
      label468: if (VlingoAndroidCore.isChineseBuild())
      {
        ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).name("com.autonavi.xmgd.action.NAVIGATE").extra("target," + paramString).queue();
        break label180;
      }
      localIntent1 = new Intent();
      localIntent1.setAction("android.intent.action.VIEW");
      localIntent1.setClassName("com.google.android.apps.maps", "com.google.android.maps.driveabout.app.NavigationActivity");
      localIntent1.setData(Uri.parse("google.navigation:q=" + paramString.replaceAll(" ", "+")));
      break label180;
      label586: unified().showSystemTurn(ResourceIdProvider.string.core_nav_home_prompt);
      break label180;
      label599: if ((PackageUtil.isAppInstalled("com.mnsoft.mappy", 1)) && (i > 1))
      {
        localContext.startService(localIntent1);
        continue;
      }
      ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).intent(localIntent1).queue();
    }
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    String str = ClientSuppliedValues.getHomeAddress();
    Context localContext = paramVVSActionHandlerListener.getActivityContext();
    if (((localContext instanceof ConversationActivity)) && (((ConversationActivity)localContext).isDrivingMode() == ConversationActivity.DrivingMode.Driving));
    for (boolean bool = doExecuteActionSamsung(paramVLAction, paramVVSActionHandlerListener, str); ; bool = doExecuteActionOriginal(paramVLAction, paramVVSActionHandlerListener, str))
      return bool;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.SamsungNavigateHomeHandler
 * JD-Core Version:    0.6.0
 */