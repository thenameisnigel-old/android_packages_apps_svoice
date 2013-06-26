package com.vlingo.core.internal.dialogmanager.util;

public class WidgetUtil
{
  public static enum WidgetKey
  {
    static
    {
      AddTask = new WidgetKey("AddTask", 1);
      AnswerQuestion = new WidgetKey("AnswerQuestion", 2);
      ComposeEmail = new WidgetKey("ComposeEmail", 3);
      ComposeMessage = new WidgetKey("ComposeMessage", 4);
      ComposeSocialStatus = new WidgetKey("ComposeSocialStatus", 5);
      ContactDetail = new WidgetKey("ContactDetail", 6);
      DeleteAlarm = new WidgetKey("DeleteAlarm", 7);
      DeleteEvent = new WidgetKey("DeleteEvent", 8);
      DeleteTask = new WidgetKey("DeleteTask", 9);
      DriveNewsWidget = new WidgetKey("DriveNewsWidget", 10);
      EditAlarm = new WidgetKey("EditAlarm", 11);
      EditEvent = new WidgetKey("EditEvent", 12);
      EditTask = new WidgetKey("EditTask", 13);
      Map = new WidgetKey("Map", 14);
      Memo = new WidgetKey("Memo", 15);
      MemoList = new WidgetKey("MemoList", 16);
      MultipleMessageDisplay = new WidgetKey("MultipleMessageDisplay", 17);
      MultipleMessageShowWidget = new WidgetKey("MultipleMessageShowWidget", 18);
      MultipleMessageReadoutWidget = new WidgetKey("MultipleMessageReadoutWidget", 19);
      MultipleSenderMessageReadoutWidget = new WidgetKey("MultipleSenderMessageReadoutWidget", 20);
      MusicPlayingWidget = new WidgetKey("MusicPlayingWidget", 21);
      MessageReadback = new WidgetKey("MessageReadback", 22);
      MessageReadbackBodyHidden = new WidgetKey("MessageReadbackBodyHidden", 23);
      Notification = new WidgetKey("Notification", 24);
      OpenApp = new WidgetKey("OpenApp", 25);
      PlayMusic = new WidgetKey("PlayMusic", 26);
      ScheduleEvent = new WidgetKey("ScheduleEvent", 27);
      SetAlarm = new WidgetKey("SetAlarm", 28);
      SetTimer = new WidgetKey("SetTimer", 29);
      ShowAlarmChoices = new WidgetKey("ShowAlarmChoices", 30);
      ShowButton = new WidgetKey("ShowButton", 31);
      ShowCallWidget = new WidgetKey("ShowCallWidget", 32);
      ShowChatbotWidget = new WidgetKey("ShowChatbotWidget", 33);
      ShowClock = new WidgetKey("ShowClock", 34);
      ShowContactChoices = new WidgetKey("ShowContactChoices", 35);
      ShowContactLookup = new WidgetKey("ShowContactLookup", 36);
      ShowContactTypeChoices = new WidgetKey("ShowContactTypeChoices", 37);
      ShowEvent = new WidgetKey("ShowEvent", 38);
      ShowEventChoices = new WidgetKey("ShowEventChoices", 39);
      ShowLocalSearchWidget = new WidgetKey("ShowLocalSearchWidget", 40);
      ShowNavWidget = new WidgetKey("ShowNavWidget", 41);
      ShowTaskChoices = new WidgetKey("ShowTaskChoices", 42);
      ShowWCISWidget = new WidgetKey("ShowWCISWidget", 43);
      ShowNaverWidget = new WidgetKey("ShowNaverWidget", 44);
      ShowWeatherWidget = new WidgetKey("ShowWeatherWidget", 45);
      ShowWeatherDailyWidget = new WidgetKey("ShowWeatherDailyWidget", 46);
      ShowCMAWeatherWidget = new WidgetKey("ShowCMAWeatherWidget", 47);
      ShowCMAWeatherDailyWidget = new WidgetKey("ShowCMAWeatherDailyWidget", 48);
      ShowWolframWidget = new WidgetKey("ShowWolframWidget", 49);
      ShowTrueKnowledgeWidget = new WidgetKey("ShowTrueKnowledgeWidget", 50);
      SocialNetworkChoice = new WidgetKey("SocialNetworkChoice", 51);
      UnsupportedSuggestion = new WidgetKey("UnsupportedSuggestion", 52);
      WidgetKey[] arrayOfWidgetKey = new WidgetKey[53];
      arrayOfWidgetKey[0] = AddressBook;
      arrayOfWidgetKey[1] = AddTask;
      arrayOfWidgetKey[2] = AnswerQuestion;
      arrayOfWidgetKey[3] = ComposeEmail;
      arrayOfWidgetKey[4] = ComposeMessage;
      arrayOfWidgetKey[5] = ComposeSocialStatus;
      arrayOfWidgetKey[6] = ContactDetail;
      arrayOfWidgetKey[7] = DeleteAlarm;
      arrayOfWidgetKey[8] = DeleteEvent;
      arrayOfWidgetKey[9] = DeleteTask;
      arrayOfWidgetKey[10] = DriveNewsWidget;
      arrayOfWidgetKey[11] = EditAlarm;
      arrayOfWidgetKey[12] = EditEvent;
      arrayOfWidgetKey[13] = EditTask;
      arrayOfWidgetKey[14] = Map;
      arrayOfWidgetKey[15] = Memo;
      arrayOfWidgetKey[16] = MemoList;
      arrayOfWidgetKey[17] = MultipleMessageDisplay;
      arrayOfWidgetKey[18] = MultipleMessageShowWidget;
      arrayOfWidgetKey[19] = MultipleMessageReadoutWidget;
      arrayOfWidgetKey[20] = MultipleSenderMessageReadoutWidget;
      arrayOfWidgetKey[21] = MusicPlayingWidget;
      arrayOfWidgetKey[22] = MessageReadback;
      arrayOfWidgetKey[23] = MessageReadbackBodyHidden;
      arrayOfWidgetKey[24] = Notification;
      arrayOfWidgetKey[25] = OpenApp;
      arrayOfWidgetKey[26] = PlayMusic;
      arrayOfWidgetKey[27] = ScheduleEvent;
      arrayOfWidgetKey[28] = SetAlarm;
      arrayOfWidgetKey[29] = SetTimer;
      arrayOfWidgetKey[30] = ShowAlarmChoices;
      arrayOfWidgetKey[31] = ShowButton;
      arrayOfWidgetKey[32] = ShowCallWidget;
      arrayOfWidgetKey[33] = ShowChatbotWidget;
      arrayOfWidgetKey[34] = ShowClock;
      arrayOfWidgetKey[35] = ShowContactChoices;
      arrayOfWidgetKey[36] = ShowContactLookup;
      arrayOfWidgetKey[37] = ShowContactTypeChoices;
      arrayOfWidgetKey[38] = ShowEvent;
      arrayOfWidgetKey[39] = ShowEventChoices;
      arrayOfWidgetKey[40] = ShowLocalSearchWidget;
      arrayOfWidgetKey[41] = ShowNavWidget;
      arrayOfWidgetKey[42] = ShowTaskChoices;
      arrayOfWidgetKey[43] = ShowWCISWidget;
      arrayOfWidgetKey[44] = ShowNaverWidget;
      arrayOfWidgetKey[45] = ShowWeatherWidget;
      arrayOfWidgetKey[46] = ShowWeatherDailyWidget;
      arrayOfWidgetKey[47] = ShowCMAWeatherWidget;
      arrayOfWidgetKey[48] = ShowCMAWeatherDailyWidget;
      arrayOfWidgetKey[49] = ShowWolframWidget;
      arrayOfWidgetKey[50] = ShowTrueKnowledgeWidget;
      arrayOfWidgetKey[51] = SocialNetworkChoice;
      arrayOfWidgetKey[52] = UnsupportedSuggestion;
      $VALUES = arrayOfWidgetKey;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.util.WidgetUtil
 * JD-Core Version:    0.6.0
 */