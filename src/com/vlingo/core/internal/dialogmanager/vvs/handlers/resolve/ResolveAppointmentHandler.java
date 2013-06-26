package com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve;

import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.events.ScheduleResolvedEvent;
import com.vlingo.core.internal.dialogmanager.util.EventUtil;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.schedule.ScheduleQueryObject;
import com.vlingo.core.internal.schedule.ScheduleUtil;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.sdk.recognition.VLAction;
import java.util.LinkedList;
import java.util.List;
import java.util.List<Lcom.vlingo.core.internal.schedule.ScheduleEvent;>;
import java.util.Set;

public class ResolveAppointmentHandler extends QueryHandler
{
  private void addQueriedAppointmentsToStore(List<ScheduleEvent> paramList1, List<ScheduleEvent> paramList2)
  {
    paramList2.addAll(paramList1);
    getListener().storeState(DialogDataType.CALENDAR_MATCHES, paramList2);
  }

  private void clearCacheOnRequest(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    if (VLActionUtil.getParamBool(paramVLAction, "clear.cache", false, false))
      paramVVSActionHandlerListener.storeState(DialogDataType.CALENDAR_MATCHES, null);
  }

  private List<ScheduleEvent> getAppointmentsByQuery(VLAction paramVLAction)
  {
    Object localObject;
    if (paramVLAction.getParameterNames().isEmpty())
    {
      localObject = new LinkedList();
      ScheduleEvent localScheduleEvent = ScheduleUtil.getNextScheduleEvent(getListener().getActivityContext());
      if (localScheduleEvent != null)
        ((List)localObject).add(localScheduleEvent);
    }
    while (true)
    {
      return localObject;
      ScheduleQueryObject localScheduleQueryObject = EventUtil.extractScheduleQuery(paramVLAction, null);
      localObject = ScheduleUtil.getScheduledEvents(getListener().getActivityContext(), localScheduleQueryObject);
    }
  }

  private List<ScheduleEvent> getStoredAppointments()
  {
    Object localObject = (List)(List)getListener().getState(DialogDataType.CALENDAR_MATCHES);
    if (localObject == null)
      localObject = new LinkedList();
    return (List<ScheduleEvent>)localObject;
  }

  private void sendScheduleResolvedEvent(List<ScheduleEvent> paramList, int paramInt)
  {
    ScheduleResolvedEvent localScheduleResolvedEvent = new ScheduleResolvedEvent(paramList, paramInt, paramList.size());
    getListener().sendEvent(localScheduleResolvedEvent, this.turn);
  }

  public boolean executeQuery(VLAction paramVLAction)
  {
    clearCacheOnRequest(paramVLAction, getListener());
    new Thread(new AppointmentQueryRunnable(this, paramVLAction), "AppointmentQueryRunnable").start();
    UserLoggingEngine.getInstance().landingPageViewed("appointment");
    return true;
  }

  protected void sendEmptyEvent()
  {
    ScheduleResolvedEvent localScheduleResolvedEvent = new ScheduleResolvedEvent();
    getListener().sendEvent(localScheduleResolvedEvent, this.turn);
  }

  private static class AppointmentQueryRunnable
    implements Runnable
  {
    VLAction action;
    ResolveAppointmentHandler resolveAppointmentHandler;

    public AppointmentQueryRunnable(ResolveAppointmentHandler paramResolveAppointmentHandler, VLAction paramVLAction)
    {
      this.resolveAppointmentHandler = paramResolveAppointmentHandler;
      this.action = paramVLAction;
    }

    public void run()
    {
      List localList1 = this.resolveAppointmentHandler.getAppointmentsByQuery(this.action);
      if (localList1 != null)
      {
        List localList2 = this.resolveAppointmentHandler.getStoredAppointments();
        this.resolveAppointmentHandler.sendScheduleResolvedEvent(localList1, localList2.size());
        this.resolveAppointmentHandler.addQueriedAppointmentsToStore(localList1, localList2);
      }
      this.resolveAppointmentHandler.getListener().asyncHandlerDone();
      this.resolveAppointmentHandler = null;
      this.action = null;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.ResolveAppointmentHandler
 * JD-Core Version:    0.6.0
 */