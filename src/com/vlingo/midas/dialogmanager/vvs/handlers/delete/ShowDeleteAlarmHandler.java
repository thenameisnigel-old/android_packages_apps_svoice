package com.vlingo.midas.dialogmanager.vvs.handlers.delete;

import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.DeleteAlarmInterface;
import com.vlingo.core.internal.dialogmanager.events.ActionCompletedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionConfirmedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionFailedEvent;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.util.Alarm;
import com.vlingo.sdk.recognition.VLAction;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class ShowDeleteAlarmHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private Alarm alarm = null;
  private List<Alarm> listAlarms;

  private void deleteAlarm()
  {
    ((DeleteAlarmInterface)getAction(DMActionType.DELETE_ALARM, DeleteAlarmInterface.class)).alarm(this.alarm).queue();
  }

  private Alarm getAlarm(int paramInt)
    throws InvalidParameterException
  {
    List localList = (List)getListener().getState(DialogDataType.ALARM_MATCHES);
    Alarm localAlarm;
    if (localList == null)
      localAlarm = null;
    while (true)
    {
      return localAlarm;
      try
      {
        localAlarm = (Alarm)localList.get(paramInt);
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
      {
      }
    }
    throw new InvalidParameterException("Alarm index passed in is not in the cache");
  }

  public void actionFail(String paramString)
  {
    ActionFailedEvent localActionFailedEvent = new ActionFailedEvent(paramString);
    getListener().sendEvent(localActionFailedEvent, this.turn);
  }

  public void actionSuccess()
  {
    ActionCompletedEvent localActionCompletedEvent = new ActionCompletedEvent();
    getListener().sendEvent(localActionCompletedEvent, this.turn);
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
    throws InvalidParameterException
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    this.alarm = getAlarm(VLActionUtil.getParamInt(paramVLAction, "id", -1, true));
    if (this.alarm == null);
    while (true)
    {
      return false;
      this.listAlarms = new ArrayList();
      this.listAlarms.add(this.alarm);
      paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.DeleteAlarm, null, this.listAlarms, this);
      if (!VLActionUtil.getParamBool(paramVLAction, "execute", false, false))
        continue;
      deleteAlarm();
    }
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Default"))
    {
      ActionConfirmedEvent localActionConfirmedEvent = new ActionConfirmedEvent();
      getListener().sendEvent(localActionConfirmedEvent, this.turn);
    }
    while (true)
    {
      return;
      if (!paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Choice"))
      {
        throwUnknownActionException(paramIntent.getAction());
        continue;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.delete.ShowDeleteAlarmHandler
 * JD-Core Version:    0.6.0
 */