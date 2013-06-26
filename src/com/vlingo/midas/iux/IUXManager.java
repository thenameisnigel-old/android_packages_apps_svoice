package com.vlingo.midas.iux;

import android.app.Activity;
import android.content.Intent;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.SayHello;
import com.vlingo.midas.ServiceManager;
import com.vlingo.midas.VlingoApplication;

public class IUXManager
{
  public static String EXTRA_ONLY_TOS;
  public static String EXTRA_PENDING_INTENT = "com.vlingo.client.iux.pending_intent";

  static
  {
    EXTRA_ONLY_TOS = "com.vlingo.client.iux.only_tos";
  }

  public static void finishIUX(Activity paramActivity)
  {
    if (!isIUXComplete())
    {
      setIUXComplete(true);
      SayHello.sendHello();
    }
    ServiceManager.getInstance().startAllServices(true);
    paramActivity.startActivity(new Intent(paramActivity, VlingoApplication.getInstance().getMainActivityClass()));
    paramActivity.finish();
  }

  public static boolean isIUXComplete()
  {
    return Settings.getBoolean("iux_complete", false);
  }

  public static boolean isIUXIntroRequired()
  {
    return Settings.getBoolean("car_iux_intro_required", true);
  }

  public static boolean isTOSAccepted()
  {
    return Settings.getBoolean("tos_accepted", false);
  }

  public static void processIUX(Activity paramActivity)
  {
    if (isIUXIntroRequired())
    {
      paramActivity.startActivity(new Intent(paramActivity, IUXCompoundActivity.class));
      paramActivity.finish();
    }
    while (true)
    {
      return;
      finishIUX(paramActivity);
    }
  }

  public static boolean requiresIUX()
  {
    return isIUXIntroRequired();
  }

  public static void setIUXComplete(boolean paramBoolean)
  {
    Settings.setBoolean("iux_complete", paramBoolean);
  }

  public static void setIUXIntroRequired(boolean paramBoolean)
  {
    Settings.setBoolean("car_iux_intro_required", paramBoolean);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.iux.IUXManager
 * JD-Core Version:    0.6.0
 */