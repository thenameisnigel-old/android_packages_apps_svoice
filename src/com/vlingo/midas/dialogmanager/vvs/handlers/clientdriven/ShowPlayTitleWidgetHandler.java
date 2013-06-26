package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import android.content.Context;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.util.MusicUtil;
import com.vlingo.core.internal.util.MusicUtil.MusicInfo;
import com.vlingo.midas.music.MusicDetails;
import com.vlingo.midas.music.SearchMusic;
import com.vlingo.midas.util.PlayMusicType;
import java.util.List;

public class ShowPlayTitleWidgetHandler extends ShowPlayMusicWidgetHandler
{
  protected List<MusicDetails> getDisambiguationList(Context paramContext, String paramString, boolean paramBoolean)
  {
    return SearchMusic.getTitleList(paramContext, paramString, paramBoolean);
  }

  protected List<MusicUtil.MusicInfo> getList(Context paramContext, String paramString)
  {
    return MusicUtil.findMatchingTitleList(paramContext, paramString);
  }

  protected List<MusicDetails> getMusicList(Context paramContext, String paramString)
  {
    return SearchMusic.byTitle(paramContext, paramString);
  }

  protected ResourceIdProvider.string getNoMatchPromptRes()
  {
    return ResourceIdProvider.string.core_car_tts_NO_TITLEMATCH_DEMAND;
  }

  protected PlayMusicType getPlayMusicType()
  {
    return PlayMusicType.TITLE;
  }

  protected void playFromDetails(MusicDetails paramMusicDetails)
  {
    playByName(paramMusicDetails.getTitle());
  }

  protected void promptForDisambiguation()
  {
    unified().showSystemTurn(ResourceIdProvider.string.core_car_tts_TITLE_PROMPT_DEMAND, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_MUSIC_PLAYTITLECHOICE));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ShowPlayTitleWidgetHandler
 * JD-Core Version:    0.6.0
 */