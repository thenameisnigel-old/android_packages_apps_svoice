package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import android.content.Intent;
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
import com.vlingo.sdk.recognition.VLAction;

public class MapHandler extends VVSActionHandler
  implements WidgetActionListener
{
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
    String str3 = VLActionUtil.getParamString(paramVLAction, "IntentName", true);
    String str4 = VLActionUtil.getParamString(paramVLAction, "IntentArgument", false);
    String str5 = VLActionUtil.getParamString(paramVLAction, "Extras", false);
    String str6 = VLActionUtil.getParamString(paramVLAction, "ClassName", false);
    String str7 = VLActionUtil.getParamString(paramVLAction, "ZoomLevel", false);
    boolean bool = VLActionUtil.getParamBool(paramVLAction, "broadcast", false, false);
    if (StringUtils.isNullOrWhiteSpace(str4))
    {
      double d3 = LocationUtils.getLastLat();
      double d4 = LocationUtils.getLastLong();
      str4 = "geo:" + Double.toString(d3) + "," + Double.toString(d4);
    }
    while (true)
    {
      if (!StringUtils.isNullOrWhiteSpace(str7))
        str4 = str4 + "?z=" + str7;
      UserLoggingEngine.getInstance().landingPageViewed("maps");
      ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).name(str3).argument(str4).extra(str5).broadcast(bool).className(str6).queue();
      return false;
      if (!str4.contains("current location"))
        continue;
      double d1 = LocationUtils.getLastLat();
      double d2 = LocationUtils.getLastLong();
      str4 = str4.replace("current location", Double.toString(d1) + "," + Double.toString(d2));
    }
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.MapHandler
 * JD-Core Version:    0.6.0
 */