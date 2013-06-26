package com.vlingo.midas.dialogmanager.actions;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.PlayMusicInterface;
import com.vlingo.core.internal.util.MusicUtil.MusicInfo;

public class PlayArtistAction extends DMAction
  implements PlayMusicInterface
{
  private MusicUtil.MusicInfo musicInfo;

  protected void execute()
  {
    if (this.musicInfo != null);
    while (true)
    {
      try
      {
        getContext().startActivity(getLaunchIntent(this.musicInfo.getTitle()));
        getListener().actionSuccess();
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        getListener().actionFail("Activity could not be found.");
        continue;
      }
      String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_tts_NO_APPMATCH_DEMAND);
      getListener().actionFail(str);
    }
  }

  public Intent getLaunchIntent(String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.media.action.MEDIA_PLAY_FROM_SEARCH");
    localIntent.putExtra("android.intent.extra.focus", "vnd.android.cursor.item/playlist");
    localIntent.putExtra("android.intent.extra.focus", "vnd.android.cursor.item/artist");
    localIntent.putExtra("android.intent.extra.artist", paramString);
    return localIntent;
  }

  public PlayMusicInterface playlist(MusicUtil.MusicInfo paramMusicInfo)
  {
    this.musicInfo = paramMusicInfo;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.actions.PlayArtistAction
 * JD-Core Version:    0.6.0
 */