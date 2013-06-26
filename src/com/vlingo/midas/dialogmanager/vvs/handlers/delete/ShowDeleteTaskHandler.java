package com.vlingo.midas.dialogmanager.vvs.handlers.delete;

import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.DeleteTaskInterface;
import com.vlingo.core.internal.dialogmanager.events.ActionCompletedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionConfirmedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionFailedEvent;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.schedule.ScheduleTask;
import com.vlingo.sdk.recognition.VLAction;
import java.security.InvalidParameterException;
import java.util.List;

public class ShowDeleteTaskHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private ScheduleTask task = null;

  private void deleteTask()
  {
    ((DeleteTaskInterface)getAction(DMActionType.DELETE_TASK, DeleteTaskInterface.class)).task(this.task).queue();
  }

  private ScheduleTask getTask(int paramInt)
    throws InvalidParameterException
  {
    List localList = (List)getListener().getState(DialogDataType.TASK_MATCHES);
    try
    {
      ScheduleTask localScheduleTask = (ScheduleTask)localList.get(paramInt);
      return localScheduleTask;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
    }
    throw new InvalidParameterException("Task Index passed in is not in the cache");
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
    this.task = getTask(VLActionUtil.getParamInt(paramVLAction, "id", -1, true));
    paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.DeleteTask, null, this.task, this);
    if (VLActionUtil.getParamBool(paramVLAction, "execute", false, false))
      deleteTask();
    return false;
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
      throwUnknownActionException(paramIntent.getAction());
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.delete.ShowDeleteTaskHandler
 * JD-Core Version:    0.6.0
 */