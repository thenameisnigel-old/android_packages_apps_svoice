package com.vlingo.midas.gui;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.WidgetFactory;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionEavesdropper;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.midas.drivingmode.DrivingModeUtil;
import java.util.EnumMap;

public class WidgetBuilder
  implements WidgetFactory
{
  protected static final EnumMap<WidgetUtil.WidgetKey, Integer> driving_widgets;
  protected static final EnumMap<WidgetUtil.WidgetKey, Integer> widgets = new EnumMap(WidgetUtil.WidgetKey.class);
  protected Context context = null;
  protected ConversationActivity parentActivity = null;

  static
  {
    driving_widgets = new EnumMap(WidgetUtil.WidgetKey.class);
    registerWidget(WidgetUtil.WidgetKey.AnswerQuestion, 2130903140);
    registerWidget(WidgetUtil.WidgetKey.ShowButton, 2130903142);
    registerWidget(WidgetUtil.WidgetKey.ContactDetail, 2130903148);
    registerWidget(WidgetUtil.WidgetKey.ComposeMessage, 2130903181);
    registerWidget(WidgetUtil.WidgetKey.MessageReadback, 2130903182);
    registerWidget(WidgetUtil.WidgetKey.MessageReadbackBodyHidden, 2130903183);
    registerWidget(WidgetUtil.WidgetKey.MultipleMessageDisplay, 2130903188);
    registerWidget(WidgetUtil.WidgetKey.MultipleMessageShowWidget, 2130903190);
    registerWidget(WidgetUtil.WidgetKey.DeleteEvent, 2130903203);
    registerWidget(WidgetUtil.WidgetKey.EditEvent, 2130903204);
    registerWidget(WidgetUtil.WidgetKey.ShowContactChoices, 2130903147);
    registerWidget(WidgetUtil.WidgetKey.ShowContactTypeChoices, 2130903199);
    registerWidget(WidgetUtil.WidgetKey.ShowEventChoices, 2130903202);
    registerWidget(WidgetUtil.WidgetKey.ShowEvent, 2130903205);
    registerWidget(WidgetUtil.WidgetKey.ScheduleEvent, 2130903200);
    registerWidget(WidgetUtil.WidgetKey.AddTask, 2130903207);
    registerWidget(WidgetUtil.WidgetKey.DeleteTask, 2130903207);
    registerWidget(WidgetUtil.WidgetKey.EditTask, 2130903207);
    registerWidget(WidgetUtil.WidgetKey.ShowTaskChoices, 2130903208);
    registerWidget(WidgetUtil.WidgetKey.ShowAlarmChoices, 2130903139);
    registerWidget(WidgetUtil.WidgetKey.AddressBook, 2130903137);
    registerWidget(WidgetUtil.WidgetKey.Map, 2130903178);
    registerWidget(WidgetUtil.WidgetKey.Memo, 2130903179);
    registerWidget(WidgetUtil.WidgetKey.MemoList, 2130903180);
    registerWidget(WidgetUtil.WidgetKey.OpenApp, 2130903141);
    registerWidget(WidgetUtil.WidgetKey.SetAlarm, 2130903139);
    registerWidget(WidgetUtil.WidgetKey.DeleteAlarm, 2130903139);
    registerWidget(WidgetUtil.WidgetKey.EditAlarm, 2130903139);
    registerWidget(WidgetUtil.WidgetKey.SetTimer, 2130903209);
    registerWidget(WidgetUtil.WidgetKey.ShowCallWidget, 2130903149);
    registerWidget(WidgetUtil.WidgetKey.ShowChatbotWidget, 2130903143);
    registerWidget(WidgetUtil.WidgetKey.DriveNewsWidget, 2130903198);
    registerWidget(WidgetUtil.WidgetKey.ShowWCISWidget, 2130903174);
    registerWidget(WidgetUtil.WidgetKey.ShowClock, 2130903144);
    registerWidget(WidgetUtil.WidgetKey.ShowLocalSearchWidget, 2130903177);
    registerWidget(WidgetUtil.WidgetKey.ShowNaverWidget, 2130903194);
    registerWidget(WidgetUtil.WidgetKey.ShowNavWidget, 2130903160);
    registerWidget(WidgetUtil.WidgetKey.ShowWeatherWidget, 2130903212);
    registerWidget(WidgetUtil.WidgetKey.ShowWeatherDailyWidget, 2130903211);
    registerWidget(WidgetUtil.WidgetKey.ShowCMAWeatherWidget, 2130903041);
    registerWidget(WidgetUtil.WidgetKey.ShowCMAWeatherDailyWidget, 2130903040);
    registerWidget(WidgetUtil.WidgetKey.SocialNetworkChoice, 2130903206);
    registerWidget(WidgetUtil.WidgetKey.ComposeSocialStatus, 2130903146);
    registerWidget(WidgetUtil.WidgetKey.ShowWolframWidget, 2130903213);
    registerWidget(WidgetUtil.WidgetKey.ShowTrueKnowledgeWidget, 2130903210);
    registerWidget(WidgetUtil.WidgetKey.PlayMusic, 2130903192);
    registerWidget(WidgetUtil.WidgetKey.MusicPlayingWidget, 2130903193);
    registerWidget(WidgetUtil.WidgetKey.MultipleSenderMessageReadoutWidget, 2130903191);
    registerWidget(WidgetUtil.WidgetKey.MultipleMessageReadoutWidget, 2130903189);
    registerDrivingWidget(WidgetUtil.WidgetKey.ComposeMessage, 2130903155);
    registerDrivingWidget(WidgetUtil.WidgetKey.MessageReadback, 2130903156);
    registerDrivingWidget(WidgetUtil.WidgetKey.MessageReadbackBodyHidden, 2130903157);
    registerDrivingWidget(WidgetUtil.WidgetKey.ShowWeatherDailyWidget, 2130903167);
    registerDrivingWidget(WidgetUtil.WidgetKey.ShowWeatherWidget, 2130903167);
    registerDrivingWidget(WidgetUtil.WidgetKey.Memo, 2130903154);
    registerDrivingWidget(WidgetUtil.WidgetKey.ShowChatbotWidget, 2130903143);
    registerDrivingWidget(WidgetUtil.WidgetKey.ShowContactChoices, 2130903151);
    registerDrivingWidget(WidgetUtil.WidgetKey.ShowContactTypeChoices, 2130903162);
    registerDrivingWidget(WidgetUtil.WidgetKey.ShowEventChoices, 2130903165);
    registerDrivingWidget(WidgetUtil.WidgetKey.ScheduleEvent, 2130903163);
    registerDrivingWidget(WidgetUtil.WidgetKey.ShowEvent, 2130903163);
    registerDrivingWidget(WidgetUtil.WidgetKey.ShowWCISWidget, 2130903169);
    registerDrivingWidget(WidgetUtil.WidgetKey.MultipleSenderMessageReadoutWidget, 2130903158);
    registerDrivingWidget(WidgetUtil.WidgetKey.PlayMusic, 2130903159);
  }

  protected WidgetBuilder(ConversationActivity paramConversationActivity)
  {
    this.parentActivity = paramConversationActivity;
    this.context = paramConversationActivity;
  }

  protected WidgetBuilder(ConversationActivity paramConversationActivity, Context paramContext)
  {
    this.parentActivity = paramConversationActivity;
    this.context = paramContext;
  }

  protected static void registerDrivingWidget(WidgetUtil.WidgetKey paramWidgetKey, int paramInt)
  {
    driving_widgets.put(paramWidgetKey, Integer.valueOf(paramInt));
  }

  protected static void registerWidget(WidgetUtil.WidgetKey paramWidgetKey, int paramInt)
  {
    widgets.put(paramWidgetKey, Integer.valueOf(paramInt));
  }

  protected <T> Widget<T> buildWidget(WidgetUtil.WidgetKey paramWidgetKey, WidgetDecorator paramWidgetDecorator, T paramT, WidgetActionListener paramWidgetActionListener)
  {
    int i;
    if (widgets.containsKey(paramWidgetKey))
      if ((DrivingModeUtil.isDrivingModeEnabled(this.context)) && (!ClientSuppliedValues.isTalkbackOn()) && (driving_widgets.containsKey(paramWidgetKey)))
        i = ((Integer)driving_widgets.get(paramWidgetKey)).intValue();
    for (Widget localWidget = (Widget)View.inflate(this.context, i, null); ; localWidget = (Widget)View.inflate(this.context, 2130903184, null))
    {
      if ((localWidget instanceof Widget))
        localWidget.initialize(paramT, paramWidgetDecorator, paramWidgetKey, paramWidgetActionListener);
      return localWidget;
      i = ((Integer)widgets.get(paramWidgetKey)).intValue();
      break;
      Toast.makeText(this.context, "Widget '" + paramWidgetKey + "' is missing", 1).show();
    }
  }

  public void setEavesdropper(WidgetActionEavesdropper paramWidgetActionEavesdropper)
  {
  }

  public <T> void showWidget(WidgetUtil.WidgetKey paramWidgetKey, WidgetDecorator paramWidgetDecorator, T paramT, WidgetActionListener paramWidgetActionListener)
  {
    Log.d("getFieldID", "drivingfragment-showWidget  " + DialogFlow.getInstance().getFieldID().toString());
    if (this.parentActivity.isDrivingMode() == ConversationActivity.DrivingMode.Driving)
      this.parentActivity.addDriveWidget(buildWidget(paramWidgetKey, paramWidgetDecorator, paramT, paramWidgetActionListener), paramT, paramWidgetActionListener);
    while (true)
    {
      return;
      this.parentActivity.addWidget(buildWidget(paramWidgetKey, paramWidgetDecorator, paramT, paramWidgetActionListener));
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.WidgetBuilder
 * JD-Core Version:    0.6.0
 */