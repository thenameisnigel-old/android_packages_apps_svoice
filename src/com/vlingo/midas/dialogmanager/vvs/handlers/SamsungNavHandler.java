package com.vlingo.midas.dialogmanager.vvs.handlers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.sec.multiwindow.MultiWindowManager;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.actions.ExecuteIntentAction;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.location.LocationUtils;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.gui.ConversationActivity;
import com.vlingo.midas.gui.ConversationActivity.DrivingMode;
import com.vlingo.sdk.recognition.VLAction;

public class SamsungNavHandler extends SamsungNavBaseHandler
{
  private boolean doExecuteActionOriginal(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    String str1 = null;
    String str2 = VLActionUtil.getParamString(paramVLAction, "IntentName", true);
    String str3 = VLActionUtil.getParamString(paramVLAction, "IntentArgument", false);
    String str4 = VLActionUtil.getParamString(paramVLAction, "Extras", false);
    if (!LocationUtils.isGoogleNavAvailable())
      str1 = VLActionUtil.getParamString(paramVLAction, "ClassName", false);
    boolean bool = VLActionUtil.getParamBool(paramVLAction, "broadcast", false, false);
    ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).name(str2).argument(str3).extra(str4).className(str1).broadcast(bool).queue();
    return false;
  }

  private boolean doExecuteActionSamsung(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    Context localContext = paramVVSActionHandlerListener.getActivityContext();
    String str = VLActionUtil.getParamString(paramVLAction, "IntentArgument", false);
    Intent localIntent1 = new Intent();
    localIntent1.setAction("android.intent.action.VIEW");
    localIntent1.setClassName("com.google.android.apps.maps", "com.google.android.maps.driveabout.app.NavigationActivity");
    localIntent1.setData(Uri.parse(str));
    localIntent1.setFlags(268435456);
    localIntent1.putExtras(new MultiWindowManager(localContext).makeIntent(MultiWindowManager.FLAG_STYLE_FREE | MultiWindowManager.ZONE_A, null));
    ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).intent(localIntent1).queue();
    Intent localIntent2 = new Intent("com.sec.android.action.ARRANGE_CONTROLL_BAR");
    localIntent2.putExtra("com.sec.android.extra.CONTROL_BAR_POS", 1800);
    localContext.sendBroadcast(localIntent2);
    ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).intent(localIntent2).broadcast(true).queue();
    return false;
  }

  private boolean doExecuteActionSamsung1(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    Context localContext = paramVVSActionHandlerListener.getActivityContext();
    String str = VLActionUtil.getParamString(paramVLAction, "IntentArgument", false);
    Intent localIntent1 = new Intent();
    localIntent1.setAction("android.intent.action.VIEW");
    localIntent1.setClassName("com.google.android.apps.maps", "com.google.android.maps.driveabout.app.NavigationActivity");
    localIntent1.setData(Uri.parse(str));
    localIntent1.setFlags(268435456);
    localIntent1.putExtras(new MultiWindowManager(localContext).makeIntent(MultiWindowManager.FLAG_STYLE_FREE | MultiWindowManager.ZONE_A, null));
    localContext.startActivity(localIntent1);
    Intent localIntent2 = new Intent("com.sec.android.action.ARRANGE_CONTROLL_BAR");
    localIntent2.putExtra("com.sec.android.extra.CONTROL_BAR_POS", 1800);
    localContext.sendBroadcast(localIntent2);
    return false;
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    String str1 = VLActionUtil.getParamString(paramVLAction, "Query", false);
    if (!StringUtils.isNullOrWhiteSpace(str1))
    {
      ResourceIdProvider.string localstring = ResourceIdProvider.string.core_car_tts_NAVIGATE_TO;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = str1;
      String str2 = getString(localstring, arrayOfObject);
      paramVVSActionHandlerListener.showVlingoTextAndTTS(str2, str2);
    }
    Context localContext = paramVVSActionHandlerListener.getActivityContext();
    if (((localContext instanceof ConversationActivity)) && (((ConversationActivity)localContext).isDrivingMode() == ConversationActivity.DrivingMode.Driving));
    for (boolean bool = doExecuteActionSamsung(paramVLAction, paramVVSActionHandlerListener); ; bool = doExecuteActionOriginal(paramVLAction, paramVVSActionHandlerListener))
      return bool;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.SamsungNavHandler
 * JD-Core Version:    0.6.0
 */