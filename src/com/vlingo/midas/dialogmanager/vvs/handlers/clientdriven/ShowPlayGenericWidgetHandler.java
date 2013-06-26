package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import android.content.Context;
import android.util.Pair;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.util.MusicUtil.MusicInfo;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.music.MusicDetails;
import com.vlingo.midas.music.SearchMusic;
import com.vlingo.midas.util.PlayMusicType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShowPlayGenericWidgetHandler extends ShowPlayMusicWidgetHandler
{
  private PlayMusicType playMusicType;

  protected List<MusicDetails> getDisambiguationList(Context paramContext, String paramString, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    Pair localPair = SearchMusic.getGenericList(paramContext, paramString, paramBoolean);
    if (localPair != null)
    {
      if (localPair.first != null)
        this.playMusicType = ((PlayMusicType)localPair.first);
      if (localPair.second != null)
        localArrayList.addAll((Collection)localPair.second);
    }
    return localArrayList;
  }

  protected List<MusicUtil.MusicInfo> getList(Context paramContext, String paramString)
  {
    return null;
  }

  protected List<MusicDetails> getMusicList(Context paramContext, String paramString)
  {
    Object localObject;
    switch (1.$SwitchMap$com$vlingo$midas$util$PlayMusicType[getPlayMusicType().ordinal()])
    {
    default:
      localObject = new ArrayList();
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return localObject;
      localObject = SearchMusic.byPlaylist(paramContext, paramString);
      continue;
      localObject = SearchMusic.byTitle(paramContext, paramString);
      continue;
      localObject = SearchMusic.byAlbum(paramContext, paramString);
      continue;
      localObject = SearchMusic.byArtist(paramContext, paramString);
    }
  }

  protected ResourceIdProvider.string getNoMatchPromptRes()
  {
    return ResourceIdProvider.string.core_car_tts_NO_MUSICMATCH_DEMAND;
  }

  protected PlayMusicType getPlayMusicType()
  {
    return this.playMusicType;
  }

  protected void playFromDetails(MusicDetails paramMusicDetails)
  {
    if ((getPlayMusicType() == PlayMusicType.ARTIST) && (StringUtils.isNullOrWhiteSpace(paramMusicDetails.getArtist())))
      playByName(paramMusicDetails.getArtist());
    while (true)
    {
      return;
      playByName(paramMusicDetails.getTitle());
    }
  }

  protected void promptForDisambiguation()
  {
    switch (1.$SwitchMap$com$vlingo$midas$util$PlayMusicType[getPlayMusicType().ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return;
      unified().showSystemTurn(ResourceIdProvider.string.core_car_tts_PLAYPLAYLIST_PROMPT_DEMAND, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_MUSIC_PLAYLISTCHOICE));
      continue;
      unified().showSystemTurn(ResourceIdProvider.string.core_car_tts_MUSIC_PROMPT_DEMAND, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_MUSIC_PLAYTITLECHOICE));
      continue;
      unified().showSystemTurn(ResourceIdProvider.string.core_car_tts_ALBUM_PROMPT_DEMAND, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_MUSIC_PLAYALBUMCHOICE));
      continue;
      unified().showSystemTurn(ResourceIdProvider.string.core_car_tts_ARTIST_PROMPT_DEMAND, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_MUSIC_PLAYARTISTCHOICE));
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ShowPlayGenericWidgetHandler
 * JD-Core Version:    0.6.0
 */