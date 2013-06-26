package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.midas.dialogmanager.actions.PlayMusicViaIntentAction;
import com.vlingo.midas.util.PlayMusicType;
import com.vlingo.sdk.recognition.VLAction;

public class PlayMusicByCharacteristicHandler extends VVSActionHandler
{
  private static final String CHARACTERISTICS_EXTRA_MOOD_CALM = "calm";
  private static final String CHARACTERISTICS_EXTRA_MOOD_EXCITING = "exciting";
  private static final String CHARACTERISTICS_EXTRA_MOOD_JOYFUL = "joyful";
  private static final String CHARACTERISTICS_EXTRA_MOOD_PASSIONATE = "passionate";
  private static final String MUSIC_SQUARE_CONTENT_URL = "content://com.sec.android.music/music_square/";

  private String getMoodStringForIntent(MoodType paramMoodType)
  {
    String str;
    switch (1.$SwitchMap$com$vlingo$midas$dialogmanager$vvs$handlers$clientdriven$PlayMusicByCharacteristicHandler$MoodType[paramMoodType.ordinal()])
    {
    default:
      str = "";
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      return str;
      str = "joyful";
      continue;
      str = "passionate";
      continue;
      str = "calm";
      continue;
      str = "exciting";
      continue;
      str = "";
    }
  }

  private MoodType getMoodTypeFromAction(VLAction paramVLAction)
  {
    String str = VLActionUtil.getParamString(paramVLAction, "Characteristic", false);
    MoodType localMoodType = MoodType.UNDEFINED;
    if ("joyful".equalsIgnoreCase(str))
      localMoodType = MoodType.JOYFUL;
    if ("calm".equalsIgnoreCase(str))
      localMoodType = MoodType.CALM;
    if ("passionate".equalsIgnoreCase(str))
      localMoodType = MoodType.PASSIONATE;
    if ("exciting".equalsIgnoreCase(str))
      localMoodType = MoodType.EXCITING;
    return localMoodType;
  }

  private int getMusicCountForMood(MoodType paramMoodType)
  {
    Cursor localCursor;
    if (paramMoodType != MoodType.UNDEFINED)
    {
      ContentResolver localContentResolver = getListener().getActivityContext().getContentResolver();
      String str = getMoodStringForIntent(paramMoodType);
      localCursor = localContentResolver.query(Uri.parse("content://com.sec.android.music/music_square/" + str), null, null, null, null);
      if (localCursor == null);
    }
    for (int i = localCursor.getCount(); ; i = 0)
      return i;
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    VLActionUtil.getParamString(paramVLAction, "Query", false);
    MoodType localMoodType = getMoodTypeFromAction(paramVLAction);
    if (localMoodType != MoodType.UNDEFINED)
    {
      if (Settings.isDrivingModeEnabled())
        getListener().showWidget(WidgetUtil.WidgetKey.MusicPlayingWidget, null, null, null);
      launchMusicSquare(getMoodStringForIntent(localMoodType));
    }
    return false;
  }

  void launchMusicSquare(String paramString)
  {
    ((PlayMusicViaIntentAction)getAction(DMActionType.PLAY_MUSIC, PlayMusicViaIntentAction.class)).playMusicCharacteristic(paramString).playMusicType(PlayMusicType.MOOD).queue();
    UserLoggingEngine.getInstance().landingPageViewed("music-play-list");
  }

  private static enum MoodType
  {
    static
    {
      EXCITING = new MoodType("EXCITING", 2);
      CALM = new MoodType("CALM", 3);
      UNDEFINED = new MoodType("UNDEFINED", 4);
      MoodType[] arrayOfMoodType = new MoodType[5];
      arrayOfMoodType[0] = JOYFUL;
      arrayOfMoodType[1] = PASSIONATE;
      arrayOfMoodType[2] = EXCITING;
      arrayOfMoodType[3] = CALM;
      arrayOfMoodType[4] = UNDEFINED;
      $VALUES = arrayOfMoodType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.PlayMusicByCharacteristicHandler
 * JD-Core Version:    0.6.0
 */