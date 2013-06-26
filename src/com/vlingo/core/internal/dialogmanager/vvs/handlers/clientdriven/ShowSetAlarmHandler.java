package com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven;

import android.content.Intent;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.SetAlarmInterface;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.Alarm;
import com.vlingo.core.internal.util.AlarmUtil;
import com.vlingo.sdk.recognition.VLAction;

public class ShowSetAlarmHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private void doit(String paramString, boolean paramBoolean)
  {
    Alarm localAlarm = new Alarm();
    localAlarm.setTimeFromCanonical(paramString);
    localAlarm.setActive(paramBoolean);
    ((SetAlarmInterface)getAction(DMActionType.SET_ALARM, SetAlarmInterface.class)).alarm(localAlarm).queue();
  }

  public void actionSuccess()
  {
    super.actionSuccess();
    unified().showSystemTurn(getString(ResourceIdProvider.string.core_alarm_set, new Object[0]));
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("alarm");
    if (VLActionUtil.getParamBool(paramVLAction, "doit", false, false))
      doit((String)getListener().getState(DialogDataType.ALARM_DATA), true);
    while (true)
    {
      return false;
      boolean bool1 = VLActionUtil.getParamBool(paramVLAction, "confirm", true, false);
      boolean bool2 = VLActionUtil.getParamBool(paramVLAction, "execute", true, false);
      WidgetDecorator localWidgetDecorator = null;
      if ((bool1) || (!bool2))
        localWidgetDecorator = WidgetDecorator.makeConfirmButton();
      String str = VLActionUtil.getParamString(paramVLAction, "time", true);
      getListener().storeState(DialogDataType.ALARM_DATA, str);
      getListener().showWidget(WidgetUtil.WidgetKey.SetAlarm, localWidgetDecorator, AlarmUtil.extractAlarm(paramVLAction), this);
    }
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Default"))
    {
      getListener().interruptTurn();
      doit(VLActionUtil.extractParamString(paramIntent, "time"), VLActionUtil.extractParamBool(paramIntent, "enable", true));
    }
    while (true)
    {
      return;
      if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.UpdateTime"))
      {
        getListener().interruptTurn();
        String str = VLActionUtil.extractParamString(paramIntent, "time");
        getListener().storeState(DialogDataType.ALARM_DATA, str);
        continue;
      }
      throwUnknownActionException(paramIntent.getAction());
    }
  }

  public void reset()
  {
    getListener().finishDialog();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.ShowSetAlarmHandler
 * JD-Core Version:    0.6.0
 */