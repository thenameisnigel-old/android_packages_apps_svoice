package com.vlingo.midas.dialogmanager.vvs.handlers.edit;

import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.ModifyTaskInterface;
import com.vlingo.core.internal.dialogmanager.events.ActionCancelledEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionCompletedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionConfirmedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionFailedEvent;
import com.vlingo.core.internal.dialogmanager.util.EventUtil;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.recognition.acceptedtext.EditTaskAcceptedText;
import com.vlingo.core.internal.schedule.ScheduleTask;
import com.vlingo.sdk.recognition.VLAction;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

public class ShowModifyTaskHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private ScheduleTask modifiedTask;
  private ScheduleTask originalTask;

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

  private void storeTask()
  {
    LinkedList localLinkedList = new LinkedList();
    localLinkedList.add(this.modifiedTask);
    getListener().storeState(DialogDataType.TASK_MATCHES, localLinkedList);
  }

  private void updateTask()
  {
    ((ModifyTaskInterface)getAction(DMActionType.MODIFY_TASK, ModifyTaskInterface.class)).original(this.originalTask).modified(this.modifiedTask).queue();
    if (this.modifiedTask != null)
      sendAcceptedText(new EditTaskAcceptedText(this.modifiedTask.getTitle(), this.modifiedTask.getBeginDate(), null, null));
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
    storeTask();
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
    throws InvalidParameterException
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    this.originalTask = getTask(VLActionUtil.getParamInt(paramVLAction, "id", -1, true));
    this.modifiedTask = EventUtil.mergeChanges(paramVLAction, this.originalTask);
    paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.EditTask, null, this.modifiedTask, this);
    if (VLActionUtil.getParamBool(paramVLAction, "execute", false, false))
      updateTask();
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
      if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Cancel"))
      {
        ActionCancelledEvent localActionCancelledEvent = new ActionCancelledEvent();
        getListener().sendEvent(localActionCancelledEvent, this.turn);
        continue;
      }
      throwUnknownActionException(paramIntent.getAction());
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.edit.ShowModifyTaskHandler
 * JD-Core Version:    0.6.0
 */