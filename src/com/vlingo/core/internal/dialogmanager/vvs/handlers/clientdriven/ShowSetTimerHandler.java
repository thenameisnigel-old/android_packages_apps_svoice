package com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven;

import android.content.Intent;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.actions.SetTimerAction;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;

public class ShowSetTimerHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private String getCanonicalTimeStringFromParam(String paramString)
  {
    if ((paramString != null) && (paramString.contains("+")))
      paramString = paramString.substring(paramString.lastIndexOf("+"));
    return paramString;
  }

  private void save()
  {
    ((SetTimerAction)getAction(DMActionType.SET_ALARM, SetTimerAction.class)).time((String)getListener().getState(DialogDataType.TIMER_DATA)).queue();
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    String str1 = VLActionUtil.getParamString(paramVLAction, "time", false);
    UserLoggingEngine.getInstance().landingPageViewed("timer");
    if (!StringUtils.isNullOrWhiteSpace(str1))
      getListener().storeState(DialogDataType.TIMER_DATA, str1);
    WidgetDecorator localWidgetDecorator = null;
    if (VLActionUtil.getParamBool(paramVLAction, "doit", false, false))
      localWidgetDecorator = WidgetDecorator.makeTimerStartCmd();
    while (true)
    {
      paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.SetTimer, localWidgetDecorator, getCanonicalTimeStringFromParam(str1), this);
      return false;
      String str2 = VLActionUtil.getParamString(paramVLAction, "action", false);
      if (str2 == null)
        continue;
      if (str2.equalsIgnoreCase("stop"))
      {
        localWidgetDecorator = WidgetDecorator.makeTimerStopCmd();
        continue;
      }
      if (str2.equalsIgnoreCase("reset"))
      {
        localWidgetDecorator = WidgetDecorator.makeTimerResetCmd();
        continue;
      }
      if (str2.equalsIgnoreCase("restart"))
      {
        localWidgetDecorator = WidgetDecorator.makeTimerRestartCmd();
        continue;
      }
      if (!str2.equalsIgnoreCase("cancel"))
        continue;
      localWidgetDecorator = WidgetDecorator.makeTimerCancelCmd();
      unified().showSystemTurn(getString(ResourceIdProvider.string.core_car_tts_TASK_CANCELLED, new Object[0]));
      reset();
    }
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Save"))
    {
      getListener().interruptTurn();
      save();
    }
    while (true)
    {
      return;
      throwUnknownActionException(paramIntent.getAction());
    }
  }

  public void reset()
  {
    getListener().finishDialog();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.ShowSetTimerHandler
 * JD-Core Version:    0.6.0
 */