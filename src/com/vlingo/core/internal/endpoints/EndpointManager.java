package com.vlingo.core.internal.endpoints;

import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.settings.Settings;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class EndpointManager
{
  private static EndpointManager mInstance;
  private static Map<FieldIds, Integer> smFieldIds;

  private EndpointManager()
  {
    initializeFieldIds();
  }

  public static EndpointManager getInstance()
  {
    if (mInstance == null)
      mInstance = new EndpointManager();
    return mInstance;
  }

  private void initializeFieldIds()
  {
    int i = Settings.getInt("endpoint.time.speech.short", 400);
    int j = Settings.getInt("endpoint.time.speech.medium", 750);
    int k = Settings.getInt("endpoint.time.speech.long", 1750);
    smFieldIds = new HashMap(30);
    smFieldIds.put(FieldIds.DEFAULT, Integer.valueOf(k));
    smFieldIds.put(FieldIds.VP_CAR_CONTACTLOOKUP, Integer.valueOf(j));
    smFieldIds.put(FieldIds.VP_CAR_MAIN_NAV, Integer.valueOf(k));
    smFieldIds.put(FieldIds.VP_CAR_CONTACTLOOKUP_CHOOSE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.VP_CAR_MAIN_LAUNCHAPP, Integer.valueOf(j));
    smFieldIds.put(FieldIds.VP_CAR_MUSIC_PLAYALBUMCHOICE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.VP_CAR_MUSIC_PLAYARTISTCHOICE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.VP_CAR_MUSIC_PLAYTITLECHOICE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.VP_CAR_MUSIC_PLAYLISTCHOICE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.VP_CAR_SOCIAL_STATUS, Integer.valueOf(k));
    smFieldIds.put(FieldIds.VP_CAR_MEMO_DELETEPROMPT, Integer.valueOf(i));
    smFieldIds.put(FieldIds.VP_CAR_SAFEREADER_NEWMSG, Integer.valueOf(j));
    smFieldIds.put(FieldIds.VP_CAR_SAFEREADER_POSTMSG, Integer.valueOf(i));
    smFieldIds.put(FieldIds.VP_CAR_DIAL_AUTODIAL, Integer.valueOf(i));
    smFieldIds.put(FieldIds.VP_CAR_MAIN_SMS, Integer.valueOf(k));
    smFieldIds.put(FieldIds.VP_CAR_SMS_MSG, Integer.valueOf(k));
    smFieldIds.put(FieldIds.DM_SMS_MSG, Integer.valueOf(k));
    smFieldIds.put(FieldIds.DM_SMS_MSG_APPEND, Integer.valueOf(k));
    smFieldIds.put(FieldIds.DM_SMS_MSG_REWRITE, Integer.valueOf(k));
    smFieldIds.put(FieldIds.VP_CAR_SMS_CONTACT, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_SMS_CONTACT, Integer.valueOf(j));
    smFieldIds.put(FieldIds.VP_CAR_SMS_TYPE_NUM, Integer.valueOf(j));
    smFieldIds.put(FieldIds.VP_CAR_DIAL_CONTACT, Integer.valueOf(j));
    smFieldIds.put(FieldIds.VP_CAR_DIAL_TYPE_NUM, Integer.valueOf(j));
    smFieldIds.put(FieldIds.VP_CAR_MEMO_MSG, Integer.valueOf(k));
    smFieldIds.put(FieldIds.VP_CAR_MAIN_MEMO, Integer.valueOf(k));
    smFieldIds.put(FieldIds.DM_MAIN_SMS, Integer.valueOf(k));
    smFieldIds.put(FieldIds.DM_EVENTADDMAIN, Integer.valueOf(j));
    smFieldIds.put(FieldIds.VP_CAR_TASK_TITLE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.VP_CAR_MEMO_DELETECHOOSE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.VP_CAR_MEMO_LOOKUPCHOOSE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.VP_CAR_MAIN_EVENT, Integer.valueOf(j));
    smFieldIds.put(FieldIds.VP_CAR_EVENT_TITLE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_ALARMADDTIME, Integer.valueOf(i));
    smFieldIds.put(FieldIds.DM_ALARMADDMAIN, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_ALARMDELETECHOOSE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_ALARMDELETEMAIN, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_ALARMEDITCHOOSE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_ALARMEDITMAIN, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_ALARMLOOKUPMAIN, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_EVENTADDINVITEE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_EVENTADDINVITEETYPE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_EVENTADDTIME, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_EVENTDELETECHOOSE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_EVENTDELETEMAIN, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_EVENTEDITCHOOSE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_EVENTEDITINVITEE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_EVENTEDITINVITEETYPE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_EVENTEDITMAIN, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_EVENTLOOKUPMAIN, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_SMS_TYPE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_TASKADDMAIN, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_TASKADDTITLE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_TASKDELETECHOOSE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_TASKDELETEMAIN, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_TASKEDITCHOOSE, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_TASKEDITMAIN, Integer.valueOf(j));
    smFieldIds.put(FieldIds.DM_TASKLOOKUPMAIN, Integer.valueOf(j));
  }

  public DialogFieldID getFieldId(FieldIds paramFieldIds)
  {
    int i = Settings.getInt("endpoint.time.speech.medium", 750);
    if (smFieldIds.containsKey(paramFieldIds))
      i = ((Integer)smFieldIds.get(paramFieldIds)).intValue();
    return new DialogFieldID(paramFieldIds, i);
  }

  public DialogFieldID getFieldId(String paramString)
  {
    Iterator localIterator = smFieldIds.keySet().iterator();
    FieldIds localFieldIds;
    do
    {
      if (!localIterator.hasNext())
        break;
      localFieldIds = (FieldIds)localIterator.next();
    }
    while (!localFieldIds.getValue().equals(paramString));
    for (DialogFieldID localDialogFieldID = new DialogFieldID(localFieldIds, ((Integer)smFieldIds.get(localFieldIds)).intValue()); ; localDialogFieldID = null)
      return localDialogFieldID;
  }

  public int setFieldIdSilenceDuration(FieldIds paramFieldIds, int paramInt)
  {
    int i = paramInt;
    if (paramInt > 10000)
      i = 10000;
    while (true)
    {
      smFieldIds.put(paramFieldIds, new Integer(i));
      return i;
      if (paramInt >= 200)
        continue;
      i = 200;
    }
  }

  public int setSilenceDurationNoSpeech(int paramInt)
  {
    int i = paramInt;
    if (paramInt > 10000)
      i = 10000;
    while (true)
    {
      Settings.setInt("endpoint.time_withoutspeech", i);
      return i;
      if (paramInt >= 200)
        continue;
      i = 200;
    }
  }

  public int setSilenceDurationWithSpeech(WithSpeechSilenceDurationCategory paramWithSpeechSilenceDurationCategory, int paramInt)
  {
    int i = paramInt;
    if (paramInt > 10000)
    {
      i = 10000;
      if (WithSpeechSilenceDurationCategory.SHORT != paramWithSpeechSilenceDurationCategory)
        break label46;
      Settings.setInt("endpoint.time.speech.short", i);
    }
    while (true)
    {
      initializeFieldIds();
      return i;
      if (paramInt >= 200)
        break;
      i = 200;
      break;
      label46: if (WithSpeechSilenceDurationCategory.LONG == paramWithSpeechSilenceDurationCategory)
      {
        Settings.setInt("endpoint.time.speech.long", i);
        continue;
      }
      Settings.setInt("endpoint.time.speech.medium", i);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.endpoints.EndpointManager
 * JD-Core Version:    0.6.0
 */