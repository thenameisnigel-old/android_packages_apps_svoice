package com.vlingo.midas.dialogmanager.vvs.handlers.edit;

import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.ModifyAlarmInterface;
import com.vlingo.core.internal.dialogmanager.events.ActionCancelledEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionCompletedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionConfirmedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionFailedEvent;
import com.vlingo.core.internal.dialogmanager.events.ContentChangedEvent;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.recognition.acceptedtext.EditAlarmAcceptedText;
import com.vlingo.core.internal.util.Alarm;
import com.vlingo.core.internal.util.AlarmUtil;
import com.vlingo.sdk.recognition.VLAction;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShowModifyAlarmHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private List<Alarm> listAlarms;
  private Alarm modifiedAlarm;
  private Alarm originalAlarm;

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
    throw new InvalidParameterException("Alarm Index passed in is not in the cache");
  }

  private void storeAlarm()
  {
    LinkedList localLinkedList = new LinkedList();
    localLinkedList.add(this.modifiedAlarm);
    getListener().storeState(DialogDataType.ALARM_MATCHES, localLinkedList);
  }

  private void updateAlarm()
  {
    ((ModifyAlarmInterface)getAction(DMActionType.MODIFY_ALARM, ModifyAlarmInterface.class)).original(this.originalAlarm).modified(this.modifiedAlarm).queue();
    if (this.modifiedAlarm != null)
      sendAcceptedText(new EditAlarmAcceptedText(this.modifiedAlarm.getTimeCanonical()));
  }

  private void updateTime(Intent paramIntent)
  {
    ContentChangedEvent localContentChangedEvent = new ContentChangedEvent("time", VLActionUtil.extractParamString(paramIntent, "time"));
    getListener().queueEvent(localContentChangedEvent, this.turn);
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
    storeAlarm();
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
    throws InvalidParameterException
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    this.originalAlarm = getAlarm(VLActionUtil.getParamInt(paramVLAction, "id", -1, true));
    if (this.originalAlarm == null);
    while (true)
    {
      return false;
      this.modifiedAlarm = AlarmUtil.mergeChanges(paramVLAction, this.originalAlarm);
      this.listAlarms = new ArrayList();
      this.listAlarms.add(this.modifiedAlarm);
      paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.EditAlarm, null, this.listAlarms, this);
      if (!VLActionUtil.getParamBool(paramVLAction, "execute", false, false))
        continue;
      updateAlarm();
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
      if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Cancel"))
      {
        ActionCancelledEvent localActionCancelledEvent = new ActionCancelledEvent();
        getListener().sendEvent(localActionCancelledEvent, this.turn);
        continue;
      }
      if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.UpdateTime"))
      {
        updateTime(paramIntent);
        continue;
      }
      throwUnknownActionException(paramIntent.getAction());
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.edit.ShowModifyAlarmHandler
 * JD-Core Version:    0.6.0
 */