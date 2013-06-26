package com.vlingo.core.internal.dialogmanager;

import com.vlingo.core.internal.settings.Settings;

public enum FieldIds
{
  private String value;

  static
  {
    VP_CAR_CONTACTLOOKUP_CHOOSE = new FieldIds("VP_CAR_CONTACTLOOKUP_CHOOSE", 4, "vp_car_contactlookup_choose");
    VP_CAR_MAIN_LAUNCHAPP = new FieldIds("VP_CAR_MAIN_LAUNCHAPP", 5, "vp_car_main_launchapp");
    VP_CAR_MUSIC_PLAYALBUMCHOICE = new FieldIds("VP_CAR_MUSIC_PLAYALBUMCHOICE", 6, "vp_car_music_playalbumchoice");
    VP_CAR_MUSIC_PLAYARTISTCHOICE = new FieldIds("VP_CAR_MUSIC_PLAYARTISTCHOICE", 7, "vp_car_music_playartistchoice");
    VP_CAR_MUSIC_PLAYTITLECHOICE = new FieldIds("VP_CAR_MUSIC_PLAYTITLECHOICE", 8, "vp_car_music_playtitlechoice");
    VP_CAR_MUSIC_PLAYLISTCHOICE = new FieldIds("VP_CAR_MUSIC_PLAYLISTCHOICE", 9, "vp_car_music_playlistchoice");
    VP_CAR_SOCIAL_CHOICE = new FieldIds("VP_CAR_SOCIAL_CHOICE", 10, "vp_car_social_choice");
    VP_CAR_SOCIAL_STATUS = new FieldIds("VP_CAR_SOCIAL_STATUS", 11, "vp_car_social_status");
    VP_CAR_MEMO_DELETEPROMPT = new FieldIds("VP_CAR_MEMO_DELETEPROMPT", 12, "vp_car_memo_deleteprompt");
    VP_CAR_SAFEREADER_NEWMSG = new FieldIds("VP_CAR_SAFEREADER_NEWMSG", 13, "vp_car_safereader_newmsg");
    VP_CAR_SAFEREADER_POSTMSG = new FieldIds("VP_CAR_SAFEREADER_POSTMSG", 14, "vp_car_safereader_postmsg");
    VP_CAR_READBACK_SENDERCHOOSE = new FieldIds("VP_CAR_READBACK_SENDERCHOOSE", 15, "vp_car_readback_senderchoose");
    VP_CAR_READBACK_MSGCHOOSE = new FieldIds("VP_CAR_READBACK_MSGCHOOSE", 16, "vp_car_readback_msgchoose");
    VP_CAR_SAFEREADER_READBACKMSG = new FieldIds("VP_CAR_SAFEREADER_READBACKMSG", 17, "vp_car_safereader_readbackmsg");
    VP_CAR_DIAL_AUTODIAL = new FieldIds("VP_CAR_DIAL_AUTODIAL", 18, "vp_car_dial_autodial");
    VP_CAR_MAIN_SMS = new FieldIds("VP_CAR_MAIN_SMS", 19, "vp_car_main_sms");
    VP_CAR_SMS_MSG = new FieldIds("VP_CAR_SMS_MSG", 20, "vp_car_sms_msg");
    VP_CAR_SMS_CONTACT = new FieldIds("VP_CAR_SMS_CONTACT", 21, "vp_car_sms_contact");
    VP_CAR_SMS_TYPE_NUM = new FieldIds("VP_CAR_SMS_TYPE_NUM", 22, "vp_car_sms_type_num");
    VP_CAR_DIAL_CONTACT = new FieldIds("VP_CAR_DIAL_CONTACT", 23, "vp_car_dial_contact");
    VP_CAR_DIAL_TYPE_NUM = new FieldIds("VP_CAR_DIAL_TYPE_NUM", 24, "vp_car_dial_type_num");
    VP_CAR_MEMO_MSG = new FieldIds("VP_CAR_MEMO_MSG", 25, "vp_car_memo_msg");
    VP_CAR_MAIN_MEMO = new FieldIds("VP_CAR_MAIN_MEMO", 26, "vp_car_main_memo");
    VP_MAIN_WSEARCH_PROMPT = new FieldIds("VP_MAIN_WSEARCH_PROMPT", 27, "vp_main_wsearch_prompt");
    DM_MAIN_SMS = new FieldIds("DM_MAIN_SMS", 28, "dm_main_sms");
    DM_EVENTADDMAIN = new FieldIds("DM_EVENTADDMAIN", 29, "dm_eventaddmain");
    VP_CAR_TASK_TITLE = new FieldIds("VP_CAR_TASK_TITLE", 30, "vp_car_task_title");
    VP_CAR_MEMO_DELETECHOOSE = new FieldIds("VP_CAR_MEMO_DELETECHOOSE", 31, "vp_car_memo_deletechoose");
    VP_CAR_MEMO_LOOKUPCHOOSE = new FieldIds("VP_CAR_MEMO_LOOKUPCHOOSE", 32, "vp_car_memo_lookupchoose");
    VP_CAR_MAIN_EVENT = new FieldIds("VP_CAR_MAIN_EVENT", 33, "vp_car_main_event");
    VP_CAR_EVENT_TITLE = new FieldIds("VP_CAR_EVENT_TITLE", 34, "vp_car_event_title");
    DM_ALARMADDTIME = new FieldIds("DM_ALARMADDTIME", 35, "dm_alarmaddtime");
    DM_SMS_CONTACT = new FieldIds("DM_SMS_CONTACT", 36, "dm_sms_contact");
    DM_ALARMADDMAIN = new FieldIds("DM_ALARMADDMAIN", 37, "dm_alarmaddmain");
    DM_ALARMDELETECHOOSE = new FieldIds("DM_ALARMDELETECHOOSE", 38, "dm_alarmdeletechoose");
    DM_ALARMDELETEMAIN = new FieldIds("DM_ALARMDELETEMAIN", 39, "dm_alarmdeletemain");
    DM_ALARMEDITCHOOSE = new FieldIds("DM_ALARMEDITCHOOSE", 40, "dm_alarmeditchoose");
    DM_ALARMEDITMAIN = new FieldIds("DM_ALARMEDITMAIN", 41, "dm_alarmeditmain");
    DM_ALARMLOOKUPMAIN = new FieldIds("DM_ALARMLOOKUPMAIN", 42, "dm_alarmlookupmain");
    DM_EVENTADDINVITEE = new FieldIds("DM_EVENTADDINVITEE", 43, "dm_eventaddinvitee");
    DM_EVENTADDINVITEETYPE = new FieldIds("DM_EVENTADDINVITEETYPE", 44, "dm_eventaddinviteetype");
    DM_EVENTADDTIME = new FieldIds("DM_EVENTADDTIME", 45, "dm_eventaddtime");
    DM_EVENTDELETECHOOSE = new FieldIds("DM_EVENTDELETECHOOSE", 46, "dm_eventdeletechoose");
    DM_EVENTDELETEMAIN = new FieldIds("DM_EVENTDELETEMAIN", 47, "dm_eventdeletemain");
    DM_EVENTEDITCHOOSE = new FieldIds("DM_EVENTEDITCHOOSE", 48, "dm_eventeditchoose");
    DM_EVENTEDITINVITEE = new FieldIds("DM_EVENTEDITINVITEE", 49, "dm_eventeditinvitee");
    DM_EVENTEDITINVITEETYPE = new FieldIds("DM_EVENTEDITINVITEETYPE", 50, "dm_eventeditinviteetype");
    DM_EVENTEDITMAIN = new FieldIds("DM_EVENTEDITMAIN", 51, "dm_eventeditmain");
    DM_EVENTLOOKUPMAIN = new FieldIds("DM_EVENTLOOKUPMAIN", 52, "dm_eventlookupmain");
    DM_SMS_TYPE = new FieldIds("DM_SMS_TYPE", 53, "dm_sms_type");
    DM_TASKADDMAIN = new FieldIds("DM_TASKADDMAIN", 54, "dm_taskaddmain");
    DM_TASKADDTITLE = new FieldIds("DM_TASKADDTITLE", 55, "dm_taskaddtitle");
    DM_TASKDELETECHOOSE = new FieldIds("DM_TASKDELETECHOOSE", 56, "dm_taskdeletechoose");
    DM_TASKDELETEMAIN = new FieldIds("DM_TASKDELETEMAIN", 57, "dm_taskdeletemain");
    DM_TASKEDITCHOOSE = new FieldIds("DM_TASKEDITCHOOSE", 58, "dm_taskeditchoose");
    DM_TASKEDITMAIN = new FieldIds("DM_TASKEDITMAIN", 59, "dm_taskeditmain");
    DM_TASKLOOKUPMAIN = new FieldIds("DM_TASKLOOKUPMAIN", 60, "dm_tasklookupmain");
    DM_SMS_MSG = new FieldIds("DM_SMS_MSG", 61, "dm_sms_msg");
    DM_SMS_MSG_APPEND = new FieldIds("DM_SMS_MSG_APPEND", 62, "dm_sms_msg_append");
    DM_SMS_MSG_REWRITE = new FieldIds("DM_SMS_MSG_REWRITE", 63, "dm_sms_msg_rewrite");
    FieldIds[] arrayOfFieldIds = new FieldIds[64];
    arrayOfFieldIds[0] = DEFAULT;
    arrayOfFieldIds[1] = VP_CAR_CONFIRM;
    arrayOfFieldIds[2] = VP_CAR_CONTACTLOOKUP;
    arrayOfFieldIds[3] = VP_CAR_MAIN_NAV;
    arrayOfFieldIds[4] = VP_CAR_CONTACTLOOKUP_CHOOSE;
    arrayOfFieldIds[5] = VP_CAR_MAIN_LAUNCHAPP;
    arrayOfFieldIds[6] = VP_CAR_MUSIC_PLAYALBUMCHOICE;
    arrayOfFieldIds[7] = VP_CAR_MUSIC_PLAYARTISTCHOICE;
    arrayOfFieldIds[8] = VP_CAR_MUSIC_PLAYTITLECHOICE;
    arrayOfFieldIds[9] = VP_CAR_MUSIC_PLAYLISTCHOICE;
    arrayOfFieldIds[10] = VP_CAR_SOCIAL_CHOICE;
    arrayOfFieldIds[11] = VP_CAR_SOCIAL_STATUS;
    arrayOfFieldIds[12] = VP_CAR_MEMO_DELETEPROMPT;
    arrayOfFieldIds[13] = VP_CAR_SAFEREADER_NEWMSG;
    arrayOfFieldIds[14] = VP_CAR_SAFEREADER_POSTMSG;
    arrayOfFieldIds[15] = VP_CAR_READBACK_SENDERCHOOSE;
    arrayOfFieldIds[16] = VP_CAR_READBACK_MSGCHOOSE;
    arrayOfFieldIds[17] = VP_CAR_SAFEREADER_READBACKMSG;
    arrayOfFieldIds[18] = VP_CAR_DIAL_AUTODIAL;
    arrayOfFieldIds[19] = VP_CAR_MAIN_SMS;
    arrayOfFieldIds[20] = VP_CAR_SMS_MSG;
    arrayOfFieldIds[21] = VP_CAR_SMS_CONTACT;
    arrayOfFieldIds[22] = VP_CAR_SMS_TYPE_NUM;
    arrayOfFieldIds[23] = VP_CAR_DIAL_CONTACT;
    arrayOfFieldIds[24] = VP_CAR_DIAL_TYPE_NUM;
    arrayOfFieldIds[25] = VP_CAR_MEMO_MSG;
    arrayOfFieldIds[26] = VP_CAR_MAIN_MEMO;
    arrayOfFieldIds[27] = VP_MAIN_WSEARCH_PROMPT;
    arrayOfFieldIds[28] = DM_MAIN_SMS;
    arrayOfFieldIds[29] = DM_EVENTADDMAIN;
    arrayOfFieldIds[30] = VP_CAR_TASK_TITLE;
    arrayOfFieldIds[31] = VP_CAR_MEMO_DELETECHOOSE;
    arrayOfFieldIds[32] = VP_CAR_MEMO_LOOKUPCHOOSE;
    arrayOfFieldIds[33] = VP_CAR_MAIN_EVENT;
    arrayOfFieldIds[34] = VP_CAR_EVENT_TITLE;
    arrayOfFieldIds[35] = DM_ALARMADDTIME;
    arrayOfFieldIds[36] = DM_SMS_CONTACT;
    arrayOfFieldIds[37] = DM_ALARMADDMAIN;
    arrayOfFieldIds[38] = DM_ALARMDELETECHOOSE;
    arrayOfFieldIds[39] = DM_ALARMDELETEMAIN;
    arrayOfFieldIds[40] = DM_ALARMEDITCHOOSE;
    arrayOfFieldIds[41] = DM_ALARMEDITMAIN;
    arrayOfFieldIds[42] = DM_ALARMLOOKUPMAIN;
    arrayOfFieldIds[43] = DM_EVENTADDINVITEE;
    arrayOfFieldIds[44] = DM_EVENTADDINVITEETYPE;
    arrayOfFieldIds[45] = DM_EVENTADDTIME;
    arrayOfFieldIds[46] = DM_EVENTDELETECHOOSE;
    arrayOfFieldIds[47] = DM_EVENTDELETEMAIN;
    arrayOfFieldIds[48] = DM_EVENTEDITCHOOSE;
    arrayOfFieldIds[49] = DM_EVENTEDITINVITEE;
    arrayOfFieldIds[50] = DM_EVENTEDITINVITEETYPE;
    arrayOfFieldIds[51] = DM_EVENTEDITMAIN;
    arrayOfFieldIds[52] = DM_EVENTLOOKUPMAIN;
    arrayOfFieldIds[53] = DM_SMS_TYPE;
    arrayOfFieldIds[54] = DM_TASKADDMAIN;
    arrayOfFieldIds[55] = DM_TASKADDTITLE;
    arrayOfFieldIds[56] = DM_TASKDELETECHOOSE;
    arrayOfFieldIds[57] = DM_TASKDELETEMAIN;
    arrayOfFieldIds[58] = DM_TASKEDITCHOOSE;
    arrayOfFieldIds[59] = DM_TASKEDITMAIN;
    arrayOfFieldIds[60] = DM_TASKLOOKUPMAIN;
    arrayOfFieldIds[61] = DM_SMS_MSG;
    arrayOfFieldIds[62] = DM_SMS_MSG_APPEND;
    arrayOfFieldIds[63] = DM_SMS_MSG_REWRITE;
    $VALUES = arrayOfFieldIds;
  }

  private FieldIds(String paramString)
  {
    this.value = paramString;
  }

  public String getValue()
  {
    return this.value;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.FieldIds
 * JD-Core Version:    0.6.0
 */