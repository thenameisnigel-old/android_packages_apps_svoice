package com.vlingo.midas.dialogmanager.vvs.handlers.create;

import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.AddTaskInterface;
import com.vlingo.core.internal.dialogmanager.events.ActionCancelledEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionCompletedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionConfirmedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionFailedEvent;
import com.vlingo.core.internal.dialogmanager.events.ContentChangedEvent;
import com.vlingo.core.internal.dialogmanager.util.EventUtil;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.recognition.acceptedtext.AddTaskAcceptedText;
import com.vlingo.core.internal.schedule.ScheduleTask;
import com.vlingo.sdk.recognition.VLAction;
import java.security.InvalidParameterException;

public class ShowCreateTaskHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private final String TITLE = "title";
  private VVSActionHandlerListener listener;
  private ScheduleTask task;

  private void createTask(VLAction paramVLAction)
  {
    if (VLActionUtil.getParamBool(paramVLAction, "execute", false, false))
    {
      ((AddTaskInterface)getAction(DMActionType.CREATE_TASK, AddTaskInterface.class)).task(this.task).queue();
      if (this.task != null)
        sendAcceptedText(new AddTaskAcceptedText(this.task.getTitle(), this.task.getBeginDate(), null, null));
    }
  }

  private void showWidget(VLAction paramVLAction)
  {
    if (VLActionUtil.getParamBool(paramVLAction, "confirm", false, false))
      this.listener.showWidget(WidgetUtil.WidgetKey.AddTask, WidgetDecorator.makeConfirmButton(), this.task, this);
    while (true)
    {
      return;
      this.listener.showWidget(WidgetUtil.WidgetKey.AddTask, null, this.task, this);
    }
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
    this.task = EventUtil.extractTask(paramVLAction);
    this.listener = paramVVSActionHandlerListener;
    showWidget(paramVLAction);
    createTask(paramVLAction);
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
      if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.BodyChange"))
      {
        if (!paramIntent.hasExtra("title"))
          continue;
        ContentChangedEvent localContentChangedEvent = new ContentChangedEvent("title", VLActionUtil.extractParamString(paramIntent, "title"));
        getListener().queueEvent(localContentChangedEvent, this.turn);
        continue;
      }
      throwUnknownActionException(paramIntent.getAction());
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.create.ShowCreateTaskHandler
 * JD-Core Version:    0.6.0
 */