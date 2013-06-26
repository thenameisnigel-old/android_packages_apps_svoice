package com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.DialogTurn;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.events.ChoiceSelectedEvent;
import com.vlingo.core.internal.dialogmanager.util.DialogDataUtil.ChoiceListUtil;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.sdk.recognition.VLAction;

public class ShowAppointmentChoicesHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private DialogDataUtil.ChoiceListUtil<ScheduleEvent> listUtil = new DialogDataUtil.ChoiceListUtil();
  private VVSActionHandlerListener listener;

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    this.listener = paramVVSActionHandlerListener;
    VLActionUtil.getParamString(paramVLAction, "choices", true);
    UserLoggingEngine.getInstance().landingPageViewed("appointment");
    this.listUtil.showChoiceListWidget(paramVVSActionHandlerListener, paramVLAction, DialogDataType.CALENDAR_MATCHES, WidgetUtil.WidgetKey.ShowEventChoices, this, null);
    return false;
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Choice"))
      if (FieldIds.DM_EVENTADDMAIN.getValue().equals(this.turn.getFieldId().getFieldId()))
      {
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setClassName("com.android.calendar", "com.android.calendar.EventInfoActivity");
        localIntent.setData(Uri.parse(paramIntent.getStringExtra("uri")));
        localIntent.putExtra("title", paramIntent.getStringExtra("title"));
        localIntent.putExtra("beginTime", paramIntent.getLongExtra("beginTime", -1L));
        localIntent.putExtra("endTime", paramIntent.getLongExtra("endTime", -1L));
        localIntent.addFlags(268435456);
        getListener().getActivityContext().startActivity(localIntent);
        this.listener.setFieldId(DialogFieldID.buildFromString(FieldIds.DEFAULT.getValue()));
      }
    while (true)
    {
      return;
      ChoiceSelectedEvent localChoiceSelectedEvent = new ChoiceSelectedEvent(this.listUtil.getIdOfSelection(paramIntent));
      getListener().sendEvent(localChoiceSelectedEvent, this.turn);
      continue;
      throwUnknownActionException(paramIntent.getAction());
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.ShowAppointmentChoicesHandler
 * JD-Core Version:    0.6.0
 */