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
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.actions.ExecuteIntentAction;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.location.LocationUtils;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.gui.ConversationActivity;
import com.vlingo.midas.gui.ConversationActivity.DrivingMode;
import com.vlingo.sdk.internal.util.PackageUtil;
import com.vlingo.sdk.recognition.VLAction;

public class SamsungMapHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private DisplayMetrics mMetrics = new DisplayMetrics();

  private boolean doExecuteActionOriginal(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    String str1 = null;
    String str2 = VLActionUtil.getParamString(paramVLAction, "IntentName", true);
    String str3 = getArgFromAction(paramVLAction);
    String str4 = VLActionUtil.getParamString(paramVLAction, "Extras", false);
    if (!LocationUtils.isGoogleNavAvailable())
      str1 = VLActionUtil.getParamString(paramVLAction, "ClassName", false);
    boolean bool = VLActionUtil.getParamBool(paramVLAction, "broadcast", false, false);
    ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).name(str2).argument(str3).extra(str4).broadcast(bool).className(str1).queue();
    return false;
  }

  private boolean doExecuteActionSamsung(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    Context localContext = paramVVSActionHandlerListener.getActivityContext();
    String str1 = getArgFromAction(paramVLAction);
    String str2 = VLActionUtil.getParamString(paramVLAction, "Query", false);
    String str3 = VLActionUtil.getParamString(paramVLAction, "action.prompt", false);
    int i = 0;
    ((Activity)localContext).getWindowManager().getDefaultDisplay().getMetrics(this.mMetrics);
    Intent localIntent1 = new Intent("com.sec.android.action.ARRANGE_CONTROLL_BAR");
    Intent localIntent2;
    if (localContext.getResources().getConfiguration().orientation == 2)
    {
      localIntent1.putExtra("com.sec.android.extra.CONTROL_BAR_POS", this.mMetrics.widthPixels - localContext.getResources().getDimensionPixelSize(2131427350) - localContext.getResources().getDimensionPixelSize(2131427351));
      localContext.sendBroadcast(localIntent1);
      if (StringUtils.isNullOrWhiteSpace(str3))
        break label497;
      if (!PackageUtil.isAppInstalled("com.skt.skaf.l001mtm091", 1))
        break label300;
      localIntent2 = new Intent();
      localIntent2.setClassName("com.skt.skaf.l001mtm091", "com.skt.tmap.activity.TmapIntroActivity");
      localIntent2.setData(Uri.parse(String.valueOf("tmap://search?name=" + str2)));
      label188: localIntent2.setFlags(268435456);
      localIntent2.putExtras(new MultiWindowManager(localContext).makeIntent(MultiWindowManager.FLAG_STYLE_FREE | MultiWindowManager.ZONE_A, null));
      if (!PackageUtil.isAppInstalled("kt.navi", 1))
        break label529;
      localContext.sendBroadcast(localIntent2);
    }
    while (true)
    {
      while (true)
      {
        ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).intent(localIntent1).broadcast(true).queue();
        return false;
        localIntent1.putExtra("com.sec.android.extra.CONTROL_BAR_POS", this.mMetrics.heightPixels - localContext.getResources().getDimensionPixelSize(2131427350) - localContext.getResources().getDimensionPixelSize(2131427351));
        break;
        label300: if (PackageUtil.isAppInstalled("kt.navi", 1))
        {
          localIntent2 = new Intent("kt.navi.OLLEH_NAVIGATION");
          localIntent2.putExtra("CALLER_PACKAGE_NAME", "com.vlingo.midas");
          localIntent2.putExtra("EXTERN_LINK_TYPE", 1);
          localIntent2.putExtra("LINK_SEARCH_WORD", str2);
          break label188;
        }
        if (!PackageUtil.isAppInstalled("com.mnsoft.mappy", 1))
          break label465;
        try
        {
          i = localContext.getPackageManager().getPackageInfo("com.mnsoft.mappy", 0).versionCode;
          if (i > 1)
          {
            localIntent2 = new Intent("com.mnsoft.mappy.MAPPYSMART_EXTERNAL_SERVICE");
            localIntent2.putExtra("com.mnsoft.mappy.action.REQUEST_TO_MAPPYSMART", 65536);
            localIntent2.putExtra("com.mnsoft.mappy.extra.EXTRA_KIND", "poi");
            localIntent2.putExtra("com.mnsoft.mappy.extra.EXTRA_VALUE", str2);
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          while (true)
            localNameNotFoundException.printStackTrace();
          localIntent2 = new Intent();
          localIntent2.setClassName("com.mnsoft.mappy", "com.mnsoft.mappy.activities.etc.IntroActivity");
        }
      }
      break label188;
      label465: localIntent2 = new Intent();
      localIntent2.setAction("android.intent.action.VIEW");
      localIntent2.setData(Uri.parse(str1));
      break label188;
      label497: localIntent2 = new Intent();
      localIntent2.setAction("android.intent.action.VIEW");
      localIntent2.setData(Uri.parse(str1));
      break label188;
      label529: if ((PackageUtil.isAppInstalled("com.mnsoft.mappy", 1)) && (i > 1))
      {
        localContext.startService(localIntent2);
        continue;
      }
      ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).intent(localIntent2).queue();
    }
  }

  public void actionSuccess()
  {
    super.actionSuccess();
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    String str1 = VLActionUtil.getParamString(paramVLAction, "Query", false);
    String str2 = VLActionUtil.getParamString(paramVLAction, "action.prompt", false);
    if ((StringUtils.isNullOrWhiteSpace(str2)) && (!StringUtils.isNullOrWhiteSpace(str1)))
    {
      ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_car_tts_MAP_OF;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = str1;
      str2 = getString(localstring2, arrayOfObject2);
    }
    if ((StringUtils.isNullOrWhiteSpace(str2)) && (VLActionUtil.getParamBool(paramVLAction, "CurrentLocation", false, false)))
    {
      ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_current_location;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = str1;
      str2 = getString(localstring1, arrayOfObject1);
    }
    if (!StringUtils.isNullOrWhiteSpace(str2))
      paramVVSActionHandlerListener.showVlingoTextAndTTS(str2, str2);
    UserLoggingEngine.getInstance().landingPageViewed("maps");
    Context localContext = paramVVSActionHandlerListener.getActivityContext();
    if (((localContext instanceof ConversationActivity)) && (((ConversationActivity)localContext).isDrivingMode() == ConversationActivity.DrivingMode.Driving))
      doExecuteActionSamsung(paramVLAction, paramVVSActionHandlerListener);
    while (true)
    {
      return false;
      doExecuteActionOriginal(paramVLAction, paramVVSActionHandlerListener);
    }
  }

  protected String getArgFromAction(VLAction paramVLAction)
  {
    String str1 = VLActionUtil.getParamString(paramVLAction, "IntentArgument", false);
    String str2 = VLActionUtil.getParamString(paramVLAction, "ZoomLevel", false);
    if (StringUtils.isNullOrWhiteSpace(str1))
    {
      double d3 = LocationUtils.getLastLat();
      double d4 = LocationUtils.getLastLong();
      str1 = "geo:" + Double.toString(d3) + "," + Double.toString(d4);
    }
    while (true)
    {
      if (!StringUtils.isNullOrWhiteSpace(str2))
        str1 = str1 + "?z=" + str2;
      return str1;
      if (!str1.contains("current location"))
        continue;
      double d1 = LocationUtils.getLastLat();
      double d2 = LocationUtils.getLastLong();
      str1 = str1.replace("current location", Double.toString(d1) + "," + Double.toString(d2));
    }
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.SamsungMapHandler
 * JD-Core Version:    0.6.0
 */