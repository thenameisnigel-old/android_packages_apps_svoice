package com.vlingo.midas.dialogmanager.vvs.handlers.resolve;

import com.vlingo.core.internal.alarms.AlarmQueryObject;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.events.AlarmResolvedEvent;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.QueryHandler;
import com.vlingo.core.internal.util.Alarm;
import com.vlingo.midas.util.SamsungAlarmUtil;
import com.vlingo.sdk.recognition.VLAction;
import java.util.LinkedList;
import java.util.List;
import java.util.List<Lcom.vlingo.core.internal.util.Alarm;>;

public class ResolveAlarmHandler extends QueryHandler
{
  private void addQueriedAlarmsToStore(List<Alarm> paramList1, List<Alarm> paramList2)
  {
    paramList2.addAll(paramList1);
    getListener().storeState(DialogDataType.ALARM_MATCHES, paramList2);
  }

  private void clearCacheOnRequest(VLAction paramVLAction)
  {
    if (VLActionUtil.getParamBool(paramVLAction, "clear.cache", false, false))
      getListener().storeState(DialogDataType.ALARM_MATCHES, null);
  }

  private List<Alarm> getAlarmsByQuery(VLAction paramVLAction)
  {
    AlarmQueryObject localAlarmQueryObject = SamsungAlarmUtil.extractAlarmQuery(paramVLAction);
    return SamsungAlarmUtil.getAlarms(getListener().getActivityContext(), localAlarmQueryObject);
  }

  private List<Alarm> getStoredAlarms()
  {
    Object localObject = (List)(List)getListener().getState(DialogDataType.ALARM_MATCHES);
    if (localObject == null)
      localObject = new LinkedList();
    return (List<Alarm>)localObject;
  }

  private void sendAlarmResolvedEvent(List<Alarm> paramList, int paramInt)
  {
    AlarmResolvedEvent localAlarmResolvedEvent = new AlarmResolvedEvent(paramList, paramInt, paramList.size());
    getListener().sendEvent(localAlarmResolvedEvent, this.turn);
  }

  public boolean executeQuery(VLAction paramVLAction)
  {
    clearCacheOnRequest(paramVLAction);
    new Thread(new AlarmQueryRunnable(this, paramVLAction), "AlarmQueryRunnable").start();
    return true;
  }

  protected void sendEmptyEvent()
  {
    AlarmResolvedEvent localAlarmResolvedEvent = new AlarmResolvedEvent();
    getListener().sendEvent(localAlarmResolvedEvent, this.turn);
  }

  private static class AlarmQueryRunnable
    implements Runnable
  {
    VLAction action;
    ResolveAlarmHandler resolveAlarmHandler;

    public AlarmQueryRunnable(ResolveAlarmHandler paramResolveAlarmHandler, VLAction paramVLAction)
    {
      this.resolveAlarmHandler = paramResolveAlarmHandler;
      this.action = paramVLAction;
    }

    public void run()
    {
      List localList1 = this.resolveAlarmHandler.getAlarmsByQuery(this.action);
      if (localList1 != null)
      {
        List localList2 = this.resolveAlarmHandler.getStoredAlarms();
        this.resolveAlarmHandler.sendAlarmResolvedEvent(localList1, localList2.size());
        this.resolveAlarmHandler.addQueriedAlarmsToStore(localList1, localList2);
      }
      this.resolveAlarmHandler.getListener().asyncHandlerDone();
      this.resolveAlarmHandler = null;
      this.action = null;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.resolve.ResolveAlarmHandler
 * JD-Core Version:    0.6.0
 */