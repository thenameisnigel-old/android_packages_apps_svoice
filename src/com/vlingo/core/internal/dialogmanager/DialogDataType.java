package com.vlingo.core.internal.dialogmanager;

public enum DialogDataType
{
  static
  {
    DialogDataType[] arrayOfDialogDataType = new DialogDataType[20];
    arrayOfDialogDataType[0] = ACTIVE_CONTROLLER;
    arrayOfDialogDataType[1] = ALARM_DATA;
    arrayOfDialogDataType[2] = ALARM_MATCHES;
    arrayOfDialogDataType[3] = CALENDAR_MATCHES;
    arrayOfDialogDataType[4] = CONTACT_MATCHES;
    arrayOfDialogDataType[5] = CONTACT_QUERY;
    arrayOfDialogDataType[6] = CURRENT_ACTION;
    arrayOfDialogDataType[7] = MEMO_DATA;
    arrayOfDialogDataType[8] = MESSAGE_MATCHES;
    arrayOfDialogDataType[9] = MUSIC_ITEM_MATCHES;
    arrayOfDialogDataType[10] = OPEN_APP;
    arrayOfDialogDataType[11] = ORDINAL_DATA;
    arrayOfDialogDataType[12] = ORDINAL_DATA_COUNT;
    arrayOfDialogDataType[13] = PARSE_TYPE;
    arrayOfDialogDataType[14] = PREVIOUS_FIELD_ID;
    arrayOfDialogDataType[15] = SELECTED_CONTACT;
    arrayOfDialogDataType[16] = SOCIAL_UPDATE;
    arrayOfDialogDataType[17] = TASK_MATCHES;
    arrayOfDialogDataType[18] = TIMER_DATA;
    arrayOfDialogDataType[19] = UNDEFINED;
    $VALUES = arrayOfDialogDataType;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.DialogDataType
 * JD-Core Version:    0.6.0
 */