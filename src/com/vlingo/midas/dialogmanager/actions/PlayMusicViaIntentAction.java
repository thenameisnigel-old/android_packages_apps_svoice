package com.vlingo.midas.dialogmanager.actions;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.util.PlayMusicType;

public class PlayMusicViaIntentAction extends DMAction
{
  private static final String EXTRA_LAUNCH_MUSIC_PLAYER = "launchMusicPlayer";
  private static final String EXTRA_LAUNCH_SQUARE = "launchMusicSquare";
  private static final String EXTRA_NAME = "extra_name";
  private static final String EXTRA_TYPE = "extra_type";
  private static final String EXTRA_TYPE_ALBUM = "album";
  private static final String EXTRA_TYPE_ARTIST = "artist";
  private static final String EXTRA_TYPE_PLAYLIST = "playlist";
  private static final String EXTRA_TYPE_TITLE = "title";
  public static final String PLAYER_PAUSE_ACTION = "com.sec.android.app.music.intent.action.STOP";
  public static final String PLAYER_PLAY_ACTION = "com.sec.android.music.intent.action.PLAY";
  public static final String PLAYER_PLAY_NEXT_ACTION = "com.sec.android.app.music.intent.action.PLAY_NEXT";
  public static final String PLAYER_PLAY_PREVIOUS_ACTION = "com.sec.android.app.music.intent.action.PLAY_PREVIOUS";
  public static final String PLAYER_PLAY_VIA_ACTION = "com.sec.android.app.music.intent.action.PLAY_VIA";
  private static final String PLAY_BY_MOOD_ACTION = "com.sec.android.app.music.intent.action.PLAY_BY_MOOD";
  private String moodType;
  private String name;
  private PlayMusicType playMusicType;

  private String getPlayMusicTypeStr()
  {
    String str;
    switch (1.$SwitchMap$com$vlingo$midas$util$PlayMusicType[this.playMusicType.ordinal()])
    {
    default:
      str = null;
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      return str;
      str = "album";
      continue;
      str = "artist";
      continue;
      str = "playlist";
      continue;
      str = "title";
      continue;
      str = "playlist";
    }
  }

  protected void execute()
  {
    String str1 = getPlayMusicTypeStr();
    Boolean localBoolean = Boolean.valueOf(false);
    if (this.moodType != null)
    {
      try
      {
        getListener().actionSuccess();
        Intent localIntent6 = new Intent("com.sec.android.app.music.intent.action.PLAY_BY_MOOD");
        localIntent6.putExtra("extra_type", this.moodType);
        if (!ClientSuppliedValues.isDrivingModeEnabled())
          localIntent6.putExtra("launchMusicSquare", true);
        Object localObject = null;
        try
        {
          Cursor localCursor = getContext().getContentResolver().query(Uri.parse("content://com.sec.music/music_square/" + this.moodType), null, null, null, null);
          localObject = localCursor;
          if ((localBoolean.booleanValue()) || ((localObject != null) && (localObject.getCount() != 0)))
          {
            getContext().startActivity(localIntent6);
            if (localObject == null)
              return;
            localObject.close();
          }
        }
        catch (SecurityException localSecurityException)
        {
          while (true)
          {
            localBoolean = Boolean.valueOf(true);
            continue;
            Toast.makeText(getContext(), 2131362793, 0).show();
          }
        }
      }
      catch (ActivityNotFoundException localActivityNotFoundException2)
      {
        getListener().actionFail("Activity could not be found.");
      }
    }
    else if ((!StringUtils.isNullOrWhiteSpace(this.name)) && (!StringUtils.isNullOrWhiteSpace(str1)))
    {
      try
      {
        getListener().actionSuccess();
        Intent localIntent5 = getPlayIntent(str1);
        if (!ClientSuppliedValues.isDrivingModeEnabled())
          localIntent5.putExtra("launchMusicPlayer", true);
        getContext().startActivity(localIntent5);
      }
      catch (ActivityNotFoundException localActivityNotFoundException1)
      {
        getListener().actionFail("Activity could not be found.");
      }
    }
    else if (this.playMusicType == PlayMusicType.PLAY)
    {
      getListener().actionSuccess();
      Intent localIntent4 = new Intent("com.sec.android.music.intent.action.PLAY");
      if (!ClientSuppliedValues.isDrivingModeEnabled())
        localIntent4.putExtra("launchMusicPlayer", true);
      getContext().startActivity(localIntent4);
    }
    else if (this.playMusicType == PlayMusicType.PAUSE)
    {
      getListener().actionSuccess();
      Intent localIntent3 = new Intent("com.sec.android.app.music.intent.action.STOP");
      getContext().startActivity(localIntent3);
    }
    else if (this.playMusicType == PlayMusicType.NEXT)
    {
      getListener().actionSuccess();
      Intent localIntent2 = new Intent("com.sec.android.app.music.intent.action.PLAY_NEXT");
      if (!ClientSuppliedValues.isDrivingModeEnabled())
        localIntent2.putExtra("launchMusicPlayer", true);
      getContext().startActivity(localIntent2);
    }
    else if (this.playMusicType == PlayMusicType.PREVIOUS)
    {
      getListener().actionSuccess();
      Intent localIntent1 = new Intent("com.sec.android.app.music.intent.action.PLAY_PREVIOUS");
      if (!ClientSuppliedValues.isDrivingModeEnabled())
        localIntent1.putExtra("launchMusicPlayer", true);
      getContext().startActivity(localIntent1);
    }
    else
    {
      String str2 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_tts_NO_MUSICMATCH_DEMAND);
      getListener().actionFail(str2);
    }
  }

  public Intent getPlayIntent(String paramString)
  {
    Intent localIntent = new Intent("com.sec.android.app.music.intent.action.PLAY_VIA");
    localIntent.putExtra("extra_type", paramString);
    localIntent.putExtra("extra_name", this.name);
    return localIntent;
  }

  public PlayMusicViaIntentAction name(String paramString)
  {
    this.name = paramString;
    return this;
  }

  public PlayMusicViaIntentAction playMusicCharacteristic(String paramString)
  {
    this.moodType = paramString;
    return this;
  }

  public PlayMusicViaIntentAction playMusicType(PlayMusicType paramPlayMusicType)
  {
    this.playMusicType = paramPlayMusicType;
    return this;
  }

  public static enum MoodType
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
 * Qualified Name:     com.vlingo.midas.dialogmanager.actions.PlayMusicViaIntentAction
 * JD-Core Version:    0.6.0
 */