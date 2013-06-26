package com.vlingo.midas.dialogmanager.controllers;

import android.content.Intent;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.StateController;
import com.vlingo.core.internal.dialogmanager.StateController.Rule;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.AddTaskInterface;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.recognition.acceptedtext.AddTaskAcceptedText;
import com.vlingo.core.internal.schedule.DateUtil;
import com.vlingo.core.internal.schedule.ScheduleTask;
import com.vlingo.sdk.recognition.VLAction;
import java.util.HashMap;
import java.util.Map;

public class AddTaskController extends StateController
  implements WidgetActionListener
{
  private Map<String, String> maps;
  private Map<String, StateController.Rule> rules = new HashMap();
  private ScheduleTask st;

  public AddTaskController()
  {
    String str = VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_TASK_TITLE).getFieldId();
    this.rules.put("title", new StateController.Rule(str, getString(ResourceIdProvider.string.core_car_tts_TASK_SAY_TITLE, new Object[0]), true));
    this.rules.put("date", new StateController.Rule("", null, false));
    this.rules.put("Confirm", new StateController.Rule(str, getString(ResourceIdProvider.string.core_car_task_save_cancel_update_prompt, new Object[0]), false));
    this.maps = new HashMap();
    this.maps.put("Value", "title");
  }

  private void save()
  {
    ((AddTaskInterface)getAction(DMActionType.CREATE_TASK, AddTaskInterface.class)).task(this.st).queue();
    if (this.st != null)
      sendAcceptedText(new AddTaskAcceptedText(this.st.getTitle(), this.st.getBeginDate(), null, null));
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    if (paramVLAction.getName().equals("LPAction"));
    while (true)
    {
      return false;
      if (!isAllSlotsFilled())
        continue;
      this.st = new ScheduleTask();
      this.st.setTitle(((StateController.Rule)this.rules.get("title")).getValue());
      String str = ((StateController.Rule)this.rules.get("date")).getValue();
      if (str != null)
      {
        long l = DateUtil.getMillisFromString(str, true);
        this.st.setBegin(l);
      }
      paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.AddTask, WidgetDecorator.makeConfirmButton(), this.st, this);
      if (!hasConfirm())
        continue;
      executeConfirm();
    }
  }

  public Map<String, String> getRuleMappings()
  {
    return this.maps;
  }

  public Map<String, StateController.Rule> getRules()
  {
    return this.rules;
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
  }

  public boolean handleLPAction(VLAction paramVLAction)
  {
    int i = 0;
    String str = VLActionUtil.getParamString(paramVLAction, "Action", false);
    if ((str != null) && (("send".equalsIgnoreCase(str)) || ("save".equalsIgnoreCase(str))))
    {
      save();
      i = 1;
    }
    return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.controllers.AddTaskController
 * JD-Core Version:    0.6.0
 */