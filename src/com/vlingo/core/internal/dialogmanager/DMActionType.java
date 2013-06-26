package com.vlingo.core.internal.dialogmanager;

public enum DMActionType
{
  static
  {
    PLAY_SONGLIST = new DMActionType("PLAY_SONGLIST", 18);
    SAVE_MEMO = new DMActionType("SAVE_MEMO", 19);
    SCHEDULE_EDIT = new DMActionType("SCHEDULE_EDIT", 20);
    SCHEDULE_VIEW = new DMActionType("SCHEDULE_VIEW", 21);
    SCHEDULE_APPOINTMENT = new DMActionType("SCHEDULE_APPOINTMENT", 22);
    SEND_EMAIL = new DMActionType("SEND_EMAIL", 23);
    SEND_MESSAGE = new DMActionType("SEND_MESSAGE", 24);
    SET_ALARM = new DMActionType("SET_ALARM", 25);
    SET_TIMER = new DMActionType("SET_TIMER", 26);
    SETTING_CHANGE = new DMActionType("SETTING_CHANGE", 27);
    SOCIAL_UPDATE = new DMActionType("SOCIAL_UPDATE", 28);
    VIEW_CONTACT = new DMActionType("VIEW_CONTACT", 29);
    VIDEO_DIAL = new DMActionType("VIDEO_DIAL", 30);
    VOICE_DIAL = new DMActionType("VOICE_DIAL", 31);
    DMActionType[] arrayOfDMActionType = new DMActionType[32];
    arrayOfDMActionType[0] = CREATE_TASK;
    arrayOfDMActionType[1] = DELETE_ALARM;
    arrayOfDMActionType[2] = DELETE_APPOINTMENT;
    arrayOfDMActionType[3] = DELETE_MEMO;
    arrayOfDMActionType[4] = DELETE_TASK;
    arrayOfDMActionType[5] = EXECUTE_INTENT;
    arrayOfDMActionType[6] = LAUNCH_ACTIVITY;
    arrayOfDMActionType[7] = MODIFY_ALARM;
    arrayOfDMActionType[8] = MODIFY_APPOINTMENT;
    arrayOfDMActionType[9] = MODIFY_TASK;
    arrayOfDMActionType[10] = OPEN_APP;
    arrayOfDMActionType[11] = PLAY_ALBUM;
    arrayOfDMActionType[12] = PLAY_ARTIST;
    arrayOfDMActionType[13] = PLAY_MUSIC;
    arrayOfDMActionType[14] = PLAY_OUT_NEWS;
    arrayOfDMActionType[15] = PLAY_OUT_NEWS_MULTI;
    arrayOfDMActionType[16] = PLAY_PLAYLIST;
    arrayOfDMActionType[17] = PLAY_TITLE;
    arrayOfDMActionType[18] = PLAY_SONGLIST;
    arrayOfDMActionType[19] = SAVE_MEMO;
    arrayOfDMActionType[20] = SCHEDULE_EDIT;
    arrayOfDMActionType[21] = SCHEDULE_VIEW;
    arrayOfDMActionType[22] = SCHEDULE_APPOINTMENT;
    arrayOfDMActionType[23] = SEND_EMAIL;
    arrayOfDMActionType[24] = SEND_MESSAGE;
    arrayOfDMActionType[25] = SET_ALARM;
    arrayOfDMActionType[26] = SET_TIMER;
    arrayOfDMActionType[27] = SETTING_CHANGE;
    arrayOfDMActionType[28] = SOCIAL_UPDATE;
    arrayOfDMActionType[29] = VIEW_CONTACT;
    arrayOfDMActionType[30] = VIDEO_DIAL;
    arrayOfDMActionType[31] = VOICE_DIAL;
    $VALUES = arrayOfDMActionType;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.DMActionType
 * JD-Core Version:    0.6.0
 */