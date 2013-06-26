package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.recognition.acceptedtext.PlayMusicAcceptedText;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.MusicUtil.MusicInfo;
import com.vlingo.core.internal.util.OrdinalUtil;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.VlingoApplication;
import com.vlingo.midas.dialogmanager.actions.PlayMusicViaIntentAction;
import com.vlingo.midas.dialogmanager.actions.PlaySongListAction;
import com.vlingo.midas.gui.widgets.BargeInWidget;
import com.vlingo.midas.music.MusicDetails;
import com.vlingo.midas.music.SearchMusic;
import com.vlingo.midas.util.PlayMusicType;
import com.vlingo.sdk.internal.util.PackageUtil;
import com.vlingo.sdk.recognition.VLAction;
import java.util.List;

public abstract class ShowPlayMusicWidgetHandler extends VVSActionHandler
  implements WidgetActionListener
{
  VVSActionHandlerListener listener = null;
  private String type = null;

  private ResourceIdProvider.string getNoMatchAnyMusic()
  {
    return ResourceIdProvider.string.core_car_tts_NO_ANYMATCH_DEMAND;
  }

  private boolean isAnyMusic()
  {
    return SearchMusic.isAnyMusic(VlingoApplication.getInstance().getApplicationContext());
  }

  private void playMusic(List<MusicDetails> paramList, String paramString)
  {
    long[] arrayOfLong = new long[paramList.size()];
    for (int i = 0; i < paramList.size(); i++)
      arrayOfLong[i] = ((MusicDetails)paramList.get(i)).getSongId().longValue();
    ResourceIdProvider.string localstring = ResourceIdProvider.string.core_playing_music;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramString;
    String str = getString(localstring, arrayOfObject);
    unified().showSystemTurn(str);
    Intent localIntent = new Intent("com.sec.android.app.music.intent.action.PLAY_VIA");
    if (PackageUtil.canHandleIntent(getListener().getActivityContext(), localIntent))
      launchMusicPlayer(paramString);
    while (true)
    {
      UserLoggingEngine.getInstance().landingPageViewed("music-play-list");
      PlayMusicAcceptedText localPlayMusicAcceptedText = PlayMusicAcceptedText.resolved(getPlayMusicType().toString(), paramString, arrayOfLong);
      if (localPlayMusicAcceptedText != null)
        sendAcceptedText(localPlayMusicAcceptedText);
      return;
      launchList(arrayOfLong);
    }
  }

  public void actionSuccess()
  {
    if ((Settings.isDrivingModeEnabled()) && (!this.type.equalsIgnoreCase("Music:Pause")))
      getListener().showWidget(WidgetUtil.WidgetKey.MusicPlayingWidget, null, null, null);
    super.actionSuccess();
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    this.type = VLActionUtil.getParamString(paramVLAction, "Type", false);
    String str1 = VLActionUtil.getParamString(paramVLAction, "Query", false);
    PlayMusicAcceptedText.requested(VlingoApplication.getInstance().getApplicationContext(), this.type, str1);
    if (isAnyMusic())
      if ((this.type.equalsIgnoreCase("Music:Play")) && (StringUtils.isNullOrWhiteSpace(str1)))
        launchMusicPlayerWithType(PlayMusicType.PLAY, " ");
    while (true)
    {
      return false;
      if (this.type.equalsIgnoreCase("Music:Pause"))
      {
        launchMusicPlayerWithType(PlayMusicType.PAUSE, " ");
        continue;
      }
      if (this.type.equalsIgnoreCase("Music:Prev"))
      {
        launchMusicPlayerWithType(PlayMusicType.PREVIOUS, " ");
        continue;
      }
      if (this.type.equalsIgnoreCase("Music:Next"))
      {
        launchMusicPlayerWithType(PlayMusicType.NEXT, " ");
        continue;
      }
      String str2 = VLActionUtil.getParamString(paramVLAction, "Which", false);
      if ((str1 == null) || (str1.trim().length() < 1))
        str1 = " ";
      if ((StringUtils.isNullOrWhiteSpace(str2)) || (OrdinalUtil.getElement(paramVVSActionHandlerListener, str2) == null))
      {
        if (str1.trim().length() >= 1)
        {
          localList = getDisambiguationList(getListener().getActivityContext(), str1, true);
          if ((localList != null) && (localList.size() >= 1));
        }
        for (List localList = getDisambiguationList(getListener().getActivityContext(), str1, false); ; localList = (List)getListener().getState(DialogDataType.ORDINAL_DATA))
        {
          if ((localList == null) || (localList.size() <= 1))
            break label347;
          OrdinalUtil.storeOrdinalData(paramVVSActionHandlerListener, localList, BargeInWidget.getDisplayCount(localList.size()));
          promptForDisambiguation();
          getListener().showWidget(WidgetUtil.WidgetKey.PlayMusic, null, localList, this);
          UserLoggingEngine.getInstance().landingPageViewed("music-play-disambiguate");
          break;
        }
        label347: if ((localList != null) && (localList.size() == 1))
        {
          playByName(((MusicDetails)localList.get(0)).getTitle());
          continue;
        }
        this.listener = paramVVSActionHandlerListener;
        playByName(str1);
        continue;
      }
      playFromDetails((MusicDetails)OrdinalUtil.getElement(paramVVSActionHandlerListener, str2));
      OrdinalUtil.clearOrdinalData(paramVVSActionHandlerListener);
      continue;
      unified().showSystemTurn(getNoMatchAnyMusic());
    }
  }

  protected abstract List<MusicDetails> getDisambiguationList(Context paramContext, String paramString, boolean paramBoolean);

  protected abstract List<MusicUtil.MusicInfo> getList(Context paramContext, String paramString);

  protected abstract List<MusicDetails> getMusicList(Context paramContext, String paramString);

  protected abstract ResourceIdProvider.string getNoMatchPromptRes();

  protected abstract PlayMusicType getPlayMusicType();

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    getListener().interruptTurn();
    MusicDetails localMusicDetails = (MusicDetails)paramObject;
    if (localMusicDetails != null)
      playFromDetails(localMusicDetails);
  }

  void launchList(long[] paramArrayOfLong)
  {
    ((PlaySongListAction)getAction(DMActionType.PLAY_SONGLIST, PlaySongListAction.class)).songList(paramArrayOfLong).queue();
  }

  void launchMusicPlayer(String paramString)
  {
    ((PlayMusicViaIntentAction)getAction(DMActionType.PLAY_MUSIC, PlayMusicViaIntentAction.class)).playMusicType(getPlayMusicType()).name(paramString).queue();
  }

  void launchMusicPlayerWithType(PlayMusicType paramPlayMusicType, String paramString)
  {
    ((PlayMusicViaIntentAction)getAction(DMActionType.PLAY_MUSIC, PlayMusicViaIntentAction.class)).playMusicType(paramPlayMusicType).name(paramString).queue();
    UserLoggingEngine.getInstance().landingPageViewed("music-play-list");
  }

  void playByName(String paramString)
  {
    List localList = getMusicList(getListener().getActivityContext(), paramString);
    if ((StringUtils.isNullOrWhiteSpace(paramString)) && (localList != null))
      if (localList.size() == 1)
        playMusic(localList, ((MusicDetails)localList.get(0)).getTitle());
    while (true)
    {
      return;
      if (localList.size() > 0)
      {
        OrdinalUtil.storeOrdinalData(this.listener, localList, BargeInWidget.getDisplayCount(localList.size()));
        promptForDisambiguation();
        getListener().showWidget(WidgetUtil.WidgetKey.PlayMusic, null, localList, this);
        UserLoggingEngine.getInstance().landingPageViewed("music-play-disambiguate");
        continue;
      }
      unified().showSystemTurn(getNoMatchPromptRes());
      UserLoggingEngine.getInstance().landingPageViewed("music-play-no-match");
      continue;
      if (localList == null)
      {
        unified().showSystemTurn(getNoMatchPromptRes());
        UserLoggingEngine.getInstance().landingPageViewed("music-play-no-match");
        continue;
      }
      if (localList.size() > 0)
      {
        playMusic(localList, paramString);
        continue;
      }
      unified().showSystemTurn(getNoMatchPromptRes());
    }
  }

  protected abstract void playFromDetails(MusicDetails paramMusicDetails);

  protected abstract void promptForDisambiguation();

  public void reset()
  {
    getListener().finishDialog();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ShowPlayMusicWidgetHandler
 * JD-Core Version:    0.6.0
 */