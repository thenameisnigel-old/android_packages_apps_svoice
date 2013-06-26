package com.vlingo.core.internal.dialogmanager;

import android.content.Context;
import com.vlingo.core.internal.dialogmanager.actions.DeleteAppointmentAction;
import com.vlingo.core.internal.dialogmanager.actions.EditScheduleAction;
import com.vlingo.core.internal.dialogmanager.actions.ExecuteIntentAction;
import com.vlingo.core.internal.dialogmanager.actions.LaunchActivityAction;
import com.vlingo.core.internal.dialogmanager.actions.ModifyAppointmentAction;
import com.vlingo.core.internal.dialogmanager.actions.OpenAppAction;
import com.vlingo.core.internal.dialogmanager.actions.ScheduleAppointmentAction;
import com.vlingo.core.internal.dialogmanager.actions.SendEmailAction;
import com.vlingo.core.internal.dialogmanager.actions.SendMessageAction;
import com.vlingo.core.internal.dialogmanager.actions.SetAlarmAction;
import com.vlingo.core.internal.dialogmanager.actions.SetTimerAction;
import com.vlingo.core.internal.dialogmanager.actions.SettingChangeAction;
import com.vlingo.core.internal.dialogmanager.actions.SocialUpdateAction;
import com.vlingo.core.internal.dialogmanager.actions.VideoDialAction;
import com.vlingo.core.internal.dialogmanager.actions.ViewContactAction;
import com.vlingo.core.internal.dialogmanager.actions.ViewScheduleAction;
import com.vlingo.core.internal.dialogmanager.actions.VoiceDialAction;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import java.util.HashMap;
import java.util.Map;

public class DMActionFactory
{
  private static final Map<DMActionType, Class<? extends DMAction>> actions = new HashMap();

  public static DMAction getInstance(DMActionType paramDMActionType)
  {
    Class localClass = (Class)actions.get(paramDMActionType);
    DMAction localDMAction;
    if (localClass == null)
      localDMAction = null;
    while (true)
    {
      return localDMAction;
      try
      {
        localDMAction = (DMAction)localClass.newInstance();
      }
      catch (InstantiationException localInstantiationException)
      {
        localInstantiationException.printStackTrace();
        localDMAction = null;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        localIllegalAccessException.printStackTrace();
        localDMAction = null;
      }
    }
  }

  public static DMAction getInstance(DMActionType paramDMActionType, Context paramContext, DMAction.Listener paramListener, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    DMAction localDMAction = getInstance(paramDMActionType);
    if (localDMAction != null)
    {
      localDMAction.setContext(paramContext);
      localDMAction.setListener(paramListener);
      localDMAction.setActionHandlerListener(paramVVSActionHandlerListener);
    }
    return localDMAction;
  }

  public static <T extends DMAction> T getInstance(DMActionType paramDMActionType, Class<T> paramClass)
  {
    return (DMAction)paramClass.cast(getInstance(paramDMActionType));
  }

  public static void registerHandler(DMActionType paramDMActionType, Class<? extends DMAction> paramClass)
  {
    actions.put(paramDMActionType, paramClass);
  }

  public static void setupStandardMappings()
  {
    registerHandler(DMActionType.DELETE_APPOINTMENT, DeleteAppointmentAction.class);
    registerHandler(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class);
    registerHandler(DMActionType.MODIFY_APPOINTMENT, ModifyAppointmentAction.class);
    registerHandler(DMActionType.OPEN_APP, OpenAppAction.class);
    registerHandler(DMActionType.SCHEDULE_VIEW, ViewScheduleAction.class);
    registerHandler(DMActionType.SCHEDULE_EDIT, EditScheduleAction.class);
    registerHandler(DMActionType.SCHEDULE_APPOINTMENT, ScheduleAppointmentAction.class);
    registerHandler(DMActionType.SEND_EMAIL, SendEmailAction.class);
    registerHandler(DMActionType.SEND_MESSAGE, SendMessageAction.class);
    registerHandler(DMActionType.SET_ALARM, SetAlarmAction.class);
    registerHandler(DMActionType.SET_TIMER, SetTimerAction.class);
    registerHandler(DMActionType.SETTING_CHANGE, SettingChangeAction.class);
    registerHandler(DMActionType.SOCIAL_UPDATE, SocialUpdateAction.class);
    registerHandler(DMActionType.VIEW_CONTACT, ViewContactAction.class);
    registerHandler(DMActionType.VIDEO_DIAL, VideoDialAction.class);
    registerHandler(DMActionType.VOICE_DIAL, VoiceDialAction.class);
    registerHandler(DMActionType.LAUNCH_ACTIVITY, LaunchActivityAction.class);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.DMActionFactory
 * JD-Core Version:    0.6.0
 */