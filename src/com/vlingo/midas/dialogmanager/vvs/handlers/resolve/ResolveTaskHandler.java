package com.vlingo.midas.dialogmanager.vvs.handlers.resolve;

import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.events.TaskResolvedEvent;
import com.vlingo.core.internal.dialogmanager.util.EventUtil;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.QueryHandler;
import com.vlingo.core.internal.schedule.ScheduleTask;
import com.vlingo.core.internal.schedule.ScheduleUtil;
import com.vlingo.core.internal.schedule.TaskQueryObject;
import com.vlingo.sdk.recognition.VLAction;
import java.util.LinkedList;
import java.util.List;
import java.util.List<Lcom.vlingo.core.internal.schedule.ScheduleTask;>;

public class ResolveTaskHandler extends QueryHandler
{
  private void addQueriedTasksToStore(List<ScheduleTask> paramList1, List<ScheduleTask> paramList2)
  {
    paramList2.addAll(paramList1);
    getListener().storeState(DialogDataType.TASK_MATCHES, paramList2);
  }

  private void clearCacheOnRequest(VLAction paramVLAction)
  {
    if (VLActionUtil.getParamBool(paramVLAction, "clear.cache", false, false))
      getListener().storeState(DialogDataType.TASK_MATCHES, null);
  }

  private List<ScheduleTask> getStoredTasks()
  {
    Object localObject = (List)(List)getListener().getState(DialogDataType.TASK_MATCHES);
    if (localObject == null)
      localObject = new LinkedList();
    return (List<ScheduleTask>)localObject;
  }

  private List<ScheduleTask> getTaskByQuery(VLAction paramVLAction)
  {
    TaskQueryObject localTaskQueryObject = EventUtil.extractTaskQuery(paramVLAction);
    return ScheduleUtil.getTasks(getListener().getActivityContext(), localTaskQueryObject);
  }

  private void sendTaskResolvedEvent(List<ScheduleTask> paramList, int paramInt)
  {
    TaskResolvedEvent localTaskResolvedEvent = new TaskResolvedEvent(paramList, paramInt, paramList.size());
    getListener().sendEvent(localTaskResolvedEvent, this.turn);
  }

  public boolean executeQuery(VLAction paramVLAction)
  {
    clearCacheOnRequest(paramVLAction);
    new Thread(new TaskQueryRunnable(this, paramVLAction), "TaskQueryRunnable").start();
    return true;
  }

  protected void sendEmptyEvent()
  {
    TaskResolvedEvent localTaskResolvedEvent = new TaskResolvedEvent();
    getListener().sendEvent(localTaskResolvedEvent, this.turn);
  }

  private static class TaskQueryRunnable
    implements Runnable
  {
    VLAction action;
    ResolveTaskHandler resolveTaskHandler;

    public TaskQueryRunnable(ResolveTaskHandler paramResolveTaskHandler, VLAction paramVLAction)
    {
      this.resolveTaskHandler = paramResolveTaskHandler;
      this.action = paramVLAction;
    }

    public void run()
    {
      List localList1 = this.resolveTaskHandler.getTaskByQuery(this.action);
      if (localList1 != null)
      {
        List localList2 = this.resolveTaskHandler.getStoredTasks();
        this.resolveTaskHandler.sendTaskResolvedEvent(localList1, localList2.size());
        this.resolveTaskHandler.addQueriedTasksToStore(localList1, localList2);
      }
      this.resolveTaskHandler.getListener().asyncHandlerDone();
      this.resolveTaskHandler = null;
      this.action = null;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.resolve.ResolveTaskHandler
 * JD-Core Version:    0.6.0
 */