package com.vlingo.midas.dialogmanager.vvs.handlers.create;

import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.SetAlarmInterface;
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
import com.vlingo.core.internal.recognition.acceptedtext.AddAlarmAcceptedText;
import com.vlingo.core.internal.util.Alarm;
import com.vlingo.midas.util.SamsungAlarmUtil;
import com.vlingo.sdk.recognition.VLAction;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class ShowCreateAlarmHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private Alarm alarm = null;
  private List<Alarm> listAlarms;

  private void setAlarm(Alarm paramAlarm)
  {
    ((SetAlarmInterface)getAction(DMActionType.SET_ALARM, SetAlarmInterface.class)).alarm(paramAlarm).queue();
    if (paramAlarm != null)
      sendAcceptedText(new AddAlarmAcceptedText(paramAlarm.getTimeCanonical()));
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
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
    throws InvalidParameterException
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    boolean bool1 = VLActionUtil.getParamBool(paramVLAction, "confirm", true, false);
    boolean bool2 = VLActionUtil.getParamBool(paramVLAction, "execute", true, false);
    WidgetDecorator localWidgetDecorator = null;
    if ((bool1) || (!bool2))
      localWidgetDecorator = WidgetDecorator.makeConfirmButton();
    this.alarm = SamsungAlarmUtil.extractAlarm(paramVLAction);
    this.listAlarms = new ArrayList();
    this.listAlarms.add(this.alarm);
    paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.SetAlarm, localWidgetDecorator, this.listAlarms, this);
    if (VLActionUtil.getParamBool(paramVLAction, "execute", false, false))
      setAlarm(this.alarm);
    return false;
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Default"))
    {
      updateTime(paramIntent);
      ActionConfirmedEvent localActionConfirmedEvent = new ActionConfirmedEvent();
      getListener().sendEvent(localActionConfirmedEvent, this.turn);
    }
    while (true)
    {
      return;
      if (!paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Choice"))
      {
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
        continue;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.create.ShowCreateAlarmHandler
 * JD-Core Version:    0.6.0
 */