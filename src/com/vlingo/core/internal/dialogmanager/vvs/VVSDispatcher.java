package com.vlingo.core.internal.dialogmanager.vvs;

import android.os.Build.VERSION;
import com.vlingo.core.internal.dialogmanager.DialogTurn;
import com.vlingo.core.internal.dialogmanager.WebSearchButtonHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.AnswerQuestionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.CallContactHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.ContactLookupHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.DateLookupHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.DialogCancelHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.ExecuteUnkownDefSearchHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.ListenHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.LocalSearchHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.MapHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.NavHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.NavigateHomeHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.NavigateHomeKoreanHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.SafeReaderReplyHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.SearchWebPromptHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.SendMessageHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.SetConfigHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.SetParamsHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.SetTurnParamsHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.ShowServerMessageHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.ShowSystemTurnHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.ShowUserTurnHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.SpeakMessageHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.WeatherLookupHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.CancelActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.CheckScheduleHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.ControllerPassThruHanlder;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.DefaultWebSearchHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.EngineWebSearchHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.EventHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.IntentHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.LPActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.PlayMediaHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.SMSPageHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.SettingChangeHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.ShowOpenAppWidgetHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.ShowSetAlarmHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.ShowSetTimerHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.SocialUpdateHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.VoiceDialPageHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.create.ShowComposeForwardMessageHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.create.ShowComposeMessageHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.create.ShowComposeReplyMessageHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.create.ShowCreateAppointmentHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.delete.ShowDeleteAppointmentHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.edit.ShowModifyAppointmentHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.ResolveAppointmentHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.ResolveContactHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.ShowAppointmentChoicesHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.ShowAppointmentsHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.ShowCallMessageWidgetHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.ShowCallWidgetHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.ShowContactChoicesHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.ShowContactTypeChoicesHandler;
import com.vlingo.sdk.recognition.VLAction;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VVSDispatcher
{
  private static final Map<String, Class<? extends VVSActionHandler>> handlers = new HashMap();

  // ERROR //
  protected static boolean executeAction(VLAction paramVLAction, com.vlingo.sdk.recognition.VLActionEvaluator paramVLActionEvaluator, VVSActionHandlerListener paramVVSActionHandlerListener, DialogTurn paramDialogTurn)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: aload_1
    //   4: aload_0
    //   5: invokevirtual 31	com/vlingo/sdk/recognition/VLActionEvaluator:evaluateAction	(Lcom/vlingo/sdk/recognition/VLAction;)Z
    //   8: ifeq +67 -> 75
    //   11: getstatic 16	com/vlingo/core/internal/dialogmanager/vvs/VVSDispatcher:handlers	Ljava/util/Map;
    //   14: aload_0
    //   15: invokeinterface 37 1 0
    //   20: invokeinterface 43 2 0
    //   25: checkcast 45	java/lang/Class
    //   28: astore 5
    //   30: aload 5
    //   32: ifnull +90 -> 122
    //   35: aload 5
    //   37: invokevirtual 49	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   40: checkcast 51	com/vlingo/core/internal/dialogmanager/vvs/VVSActionHandler
    //   43: astore 9
    //   45: aload 9
    //   47: ifnull +28 -> 75
    //   50: aload 9
    //   52: aload_2
    //   53: invokevirtual 55	com/vlingo/core/internal/dialogmanager/vvs/VVSActionHandler:setListener	(Lcom/vlingo/core/internal/dialogmanager/vvs/VVSActionHandlerListener;)V
    //   56: aload 9
    //   58: aload_3
    //   59: invokevirtual 59	com/vlingo/core/internal/dialogmanager/vvs/VVSActionHandler:setTurn	(Lcom/vlingo/core/internal/dialogmanager/DialogTurn;)V
    //   62: aload 9
    //   64: aload_0
    //   65: aload_2
    //   66: invokevirtual 62	com/vlingo/core/internal/dialogmanager/vvs/VVSActionHandler:executeAction	(Lcom/vlingo/sdk/recognition/VLAction;Lcom/vlingo/core/internal/dialogmanager/vvs/VVSActionHandlerListener;)Z
    //   69: istore 11
    //   71: iload 11
    //   73: istore 4
    //   75: iload 4
    //   77: ireturn
    //   78: astore 8
    //   80: aload 8
    //   82: invokevirtual 65	java/lang/InstantiationException:printStackTrace	()V
    //   85: goto -10 -> 75
    //   88: astore 7
    //   90: aload 7
    //   92: invokevirtual 66	java/lang/IllegalAccessException:printStackTrace	()V
    //   95: goto -20 -> 75
    //   98: astore 10
    //   100: aload_2
    //   101: new 68	com/vlingo/core/internal/dialogmanager/events/ActionFailedEvent
    //   104: dup
    //   105: aload 10
    //   107: invokevirtual 71	java/security/InvalidParameterException:getLocalizedMessage	()Ljava/lang/String;
    //   110: invokespecial 74	com/vlingo/core/internal/dialogmanager/events/ActionFailedEvent:<init>	(Ljava/lang/String;)V
    //   113: aload_3
    //   114: invokeinterface 80 3 0
    //   119: goto -44 -> 75
    //   122: new 82	java/lang/StringBuilder
    //   125: dup
    //   126: invokespecial 83	java/lang/StringBuilder:<init>	()V
    //   129: ldc 85
    //   131: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: aload_0
    //   135: invokeinterface 37 1 0
    //   140: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: ldc 91
    //   145: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   151: pop
    //   152: goto -77 -> 75
    //
    // Exception table:
    //   from	to	target	type
    //   35	45	78	java/lang/InstantiationException
    //   35	45	88	java/lang/IllegalAccessException
    //   50	71	98	java/security/InvalidParameterException
  }

  public static int processActionList(List<VLAction> paramList, VVSActionHandlerListener paramVVSActionHandlerListener, DialogTurn paramDialogTurn)
  {
    int i = 0;
    if (paramList != null)
    {
      PhoenixActionEvaluator localPhoenixActionEvaluator = new PhoenixActionEvaluator();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        VLAction localVLAction = (VLAction)localIterator.next();
        if ((localVLAction == null) || (!executeAction(localVLAction, localPhoenixActionEvaluator, paramVVSActionHandlerListener, paramDialogTurn)))
          continue;
        i++;
      }
    }
    return i;
  }

  public static void registerHandler(String paramString, Class<? extends VVSActionHandler> paramClass)
  {
    handlers.put(paramString, paramClass);
  }

  public static void registerVersionSpecificHandlers()
  {
    int i = Integer.parseInt(Build.VERSION.SDK);
    if (i >= 9)
      registerHandler("ShowSetAlarmWidget", ShowSetAlarmHandler.class);
    if (i >= 14)
    {
      registerHandler("ResolveAppointment", ResolveAppointmentHandler.class);
      registerHandler("ShowAppointmentsWidget", ShowAppointmentsHandler.class);
      registerHandler("ShowAppointmentChoicesWidget", ShowAppointmentChoicesHandler.class);
      registerHandler("ShowCreateAppointmentWidget", ShowCreateAppointmentHandler.class);
      registerHandler("ShowDeleteAppointmentWidget", ShowDeleteAppointmentHandler.class);
      registerHandler("ShowEditAppointmentWidget", ShowModifyAppointmentHandler.class);
    }
  }

  public static void setupStandardMappings()
  {
    registerHandler("AddressBook", ContactLookupHandler.class);
    registerHandler("AnswerQuestion", AnswerQuestionHandler.class);
    registerHandler("CalendarReadback", CheckScheduleHandler.class);
    registerHandler("CallContact", CallContactHandler.class);
    registerHandler("Cancel", CancelActionHandler.class);
    registerHandler("ContactLookup", ContactLookupHandler.class);
    registerHandler("DateLookup", DateLookupHandler.class);
    registerHandler("DefaultWebSearch", DefaultWebSearchHandler.class);
    registerHandler("DialogCancel", DialogCancelHandler.class);
    registerHandler("DialPage", VoiceDialPageHandler.class);
    registerHandler("Event", EventHandler.class);
    registerHandler("ExecuteUnknownDefSearch", ExecuteUnkownDefSearchHandler.class);
    registerHandler("Intent", IntentHandler.class);
    registerHandler("Listen", ListenHandler.class);
    registerHandler("LPAction", LPActionHandler.class);
    registerHandler("Map", MapHandler.class);
    registerHandler("NavigateHome", NavigateHomeHandler.class);
    registerHandler("NavigateHomeKorean", NavigateHomeKoreanHandler.class);
    registerHandler("Nav", NavHandler.class);
    registerHandler("PlayMedia", PlayMediaHandler.class);
    registerHandler("PopulateTextbox", ControllerPassThruHanlder.class);
    registerHandler("ResolveContact", ResolveContactHandler.class);
    registerHandler("SafereaderReply", SafeReaderReplyHandler.class);
    registerHandler("SearchWebPrompt", SearchWebPromptHandler.class);
    registerHandler("SendMessage", SendMessageHandler.class);
    registerHandler("SetConfig", SetConfigHandler.class);
    registerHandler("SetParams", SetParamsHandler.class);
    registerHandler("SetTurnParams", SetTurnParamsHandler.class);
    registerHandler("SettingChange", SettingChangeHandler.class);
    registerHandler("ShowCallWidget", ShowCallWidgetHandler.class);
    registerHandler("ShowCallMessageWidget", ShowCallMessageWidgetHandler.class);
    registerHandler("ShowComposeMessageWidget", ShowComposeMessageHandler.class);
    registerHandler("ShowContactChoicesWidget", ShowContactChoicesHandler.class);
    registerHandler("ShowContactTypeChoicesWidget", ShowContactTypeChoicesHandler.class);
    registerHandler("ShowForwardMessageWidget", ShowComposeForwardMessageHandler.class);
    registerHandler("ShowMessage", ShowServerMessageHandler.class);
    registerHandler("ShowOpenAppWidget", ShowOpenAppWidgetHandler.class);
    registerHandler("ShowReplyMessageWidget", ShowComposeReplyMessageHandler.class);
    registerHandler("ShowSetTimerWidget", ShowSetTimerHandler.class);
    registerHandler("ShowSystemTurn", ShowSystemTurnHandler.class);
    registerHandler("ShowUserTurn", ShowUserTurnHandler.class);
    registerHandler("SMSPage", SMSPageHandler.class);
    registerHandler("SocialPage", SocialUpdateHandler.class);
    registerHandler("SpeakMessage", SpeakMessageHandler.class);
    registerHandler("UpdatePage", ControllerPassThruHanlder.class);
    registerHandler("ShowLocalSearchWidget", LocalSearchHandler.class);
    registerHandler("WeatherLookup", WeatherLookupHandler.class);
    registerHandler("WebSearchPage", EngineWebSearchHandler.class);
    registerHandler("ShowWebSearchButton", WebSearchButtonHandler.class);
    registerVersionSpecificHandlers();
  }

  public static void unregisterHandler(String paramString)
  {
    handlers.remove(paramString);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.VVSDispatcher
 * JD-Core Version:    0.6.0
 */