package com.vlingo.midas.dialogmanager.actions;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;

public class PlaySongListAction extends DMAction
{
  private long[] songList;

  protected void execute()
  {
    if (this.songList != null);
    while (true)
    {
      try
      {
        getContext().sendBroadcast(getPlayIntent());
        getContext().startActivity(getLaunchIntent(this.songList));
        getListener().actionSuccess();
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        getListener().actionFail("Activity could not be found.");
        continue;
      }
      String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_tts_NO_MUSICMATCH_DEMAND);
      getListener().actionFail(str);
    }
  }

  public Intent getLaunchIntent(long[] paramArrayOfLong)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setType("com.android.music/launchplayer");
    localIntent.putExtra("musicplayer.action", "launchplayer");
    localIntent.putExtra("list", paramArrayOfLong);
    localIntent.putExtra("list_position", 0);
    return localIntent;
  }

  public Intent getPlayIntent()
  {
    return new Intent("com.sec.android.app.music.musicservicecommand.play");
  }

  public PlaySongListAction songList(long[] paramArrayOfLong)
  {
    this.songList = paramArrayOfLong;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.actions.PlaySongListAction
 * JD-Core Version:    0.6.0
 */