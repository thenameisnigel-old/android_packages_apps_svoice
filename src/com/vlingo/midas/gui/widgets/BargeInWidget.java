package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sec.android.app.IWSpeechRecognizer.BargeInRecognizer;
import com.sec.android.app.IWSpeechRecognizer.IWSpeechRecognizerListener;
import com.vlingo.core.internal.audio.AudioRequest;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.gui.Widget;
import com.vlingo.midas.settings.MidasSettings;

public abstract class BargeInWidget<Type> extends Widget<Type>
  implements IWSpeechRecognizerListener
{
  public static final int MAX_WIDGET_ITEMS = 6;
  private static final boolean cappingItems = MidasSettings.getBoolean("multi.widget.client.capped", true);
  private int capSize = getMultiWidgetItemsInitialMax();
  private BargeInRecognizer mBargeInRecognizer;
  protected WordList mWordList = WordList.VoiceTalkAll;
  protected Button moreBtn = null;
  protected TextView msgCounts = null;
  private final boolean showMoreButton = MidasSettings.getBoolean("multi.widget.client.showmorebutton", false);
  private final boolean showMsgcounts = MidasSettings.getBoolean("multi.widget.client.showcounts", false);

  public BargeInWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private BargeInRecognizer getBargeInRecognizer()
  {
    if (this.mBargeInRecognizer == null)
    {
      this.mBargeInRecognizer = new BargeInRecognizer();
      this.mBargeInRecognizer.InitBargeInRecognizer(this);
    }
    return this.mBargeInRecognizer;
  }

  public static int getDisplayCount(int paramInt)
  {
    int i = paramInt;
    if (!cappingItems);
    for (int k = i; ; k = i)
    {
      return k;
      int j = getMultiWidgetItemsInitialMax();
      if (paramInt <= j)
        continue;
      i = j;
    }
  }

  protected static void setListViewHeightBasedOnChildren(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null);
    while (true)
    {
      return;
      if (paramListView.getLayoutParams().height <= 0)
      {
        int i = 0;
        int j = localListAdapter.getCount();
        int k = 0;
        while (true)
          if (k < j)
          {
            View localView = localListAdapter.getView(k, null, paramListView);
            try
            {
              localView.measure(0, 0);
              i += localView.getMeasuredHeight();
              k++;
            }
            catch (Exception localException)
            {
              while (true)
                localException.printStackTrace();
            }
          }
        ViewGroup.LayoutParams localLayoutParams = paramListView.getLayoutParams();
        localLayoutParams.height = (i + paramListView.getDividerHeight() * (-1 + localListAdapter.getCount()));
        paramListView.setLayoutParams(localLayoutParams);
        paramListView.requestLayout();
        continue;
      }
    }
  }

  public int getLimitedCount(int paramInt)
  {
    int i = getDisplayCount(paramInt);
    if ((this.moreBtn != null) || (this.msgCounts != null))
    {
      if (paramInt <= this.capSize)
        break label147;
      if (this.msgCounts != null)
      {
        this.msgCounts.setText(this.capSize + "/" + paramInt + "...");
        if (this.capSize != getMultiWidgetItemsUltimateMax())
          this.msgCounts.setVisibility(0);
      }
      if (this.moreBtn != null)
      {
        if (this.capSize != getMultiWidgetItemsUltimateMax())
          break label127;
        this.moreBtn.setVisibility(8);
        this.moreBtn.setText(2131361875);
      }
    }
    while (true)
    {
      return i;
      label127: this.moreBtn.setVisibility(0);
      this.moreBtn.setText(2131361875);
      continue;
      label147: if (this.moreBtn != null)
        this.moreBtn.setVisibility(8);
      if (this.msgCounts == null)
        continue;
      this.msgCounts.setVisibility(8);
    }
  }

  public void handleCancel()
  {
    DialogFlow.getInstance().cancelDialog();
    stopRecognition();
  }

  public void handleNext()
  {
  }

  public void handlePrevious()
  {
  }

  public void handleStop()
  {
    DialogFlow.getInstance().cancelTTS();
    DialogFlow.getInstance().deleteQueuedTtsTasks();
    stopRecognition();
  }

  public abstract void initialize(Type paramType, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener);

  public boolean isRecognizing()
  {
    int i = 1;
    if (getBargeInRecognizer().getState() == i);
    while (true)
    {
      return i;
      i = 0;
    }
  }

  public void onFinishInflate()
  {
    if (this.showMoreButton)
    {
      this.moreBtn = ((Button)findViewById(2131559066));
      if (!this.showMsgcounts)
        break label86;
    }
    label86: for (this.msgCounts = ((TextView)findViewById(2131559065)); ; this.msgCounts = null)
    {
      if ((this.moreBtn != null) && (this.msgCounts != null))
        this.moreBtn.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            if (BargeInWidget.this.capSize == Widget.getMultiWidgetItemsUltimateMax())
            {
              BargeInWidget.this.moreBtn.setVisibility(8);
              BargeInWidget.this.msgCounts.setVisibility(8);
            }
            while (true)
            {
              BargeInWidget.this.moreBtn.getParent().requestLayout();
              return;
              BargeInWidget.access$002(BargeInWidget.this, Widget.getMultiWidgetItemsUltimateMax());
            }
          }
        });
      startRecognition(this.mWordList);
      return;
      this.moreBtn = null;
      break;
    }
  }

  public void onRequestCancelled(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled paramReasonCanceled)
  {
    if (!DialogFlow.getInstance().isQueuedTtsTask())
      stopRecognition();
    super.onRequestCancelled(paramAudioRequest, paramReasonCanceled);
  }

  public void onRequestDidPlay(AudioRequest paramAudioRequest)
  {
    if (!DialogFlow.getInstance().isQueuedTtsTask())
      stopRecognition();
    super.onRequestDidPlay(paramAudioRequest);
  }

  public void onRequestIgnored(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored paramReasonIgnored)
  {
    if (!DialogFlow.getInstance().isQueuedTtsTask())
      stopRecognition();
    super.onRequestIgnored(paramAudioRequest, paramReasonIgnored);
  }

  public void onRequestWillPlay(AudioRequest paramAudioRequest)
  {
    super.onRequestWillPlay(paramAudioRequest);
  }

  public void onResults(String[] paramArrayOfString)
  {
    if (this.mBargeInRecognizer == null);
    while (true)
    {
      return;
      int i = this.mBargeInRecognizer.getIntBargeInResult();
      if (i == -1)
        continue;
      if (this.mWordList == WordList.VoiceTalkAll)
      {
        if (i != 1)
          continue;
        handleStop();
        continue;
      }
      if (this.mWordList != WordList.VoiceTalkSchedule)
        continue;
      if (i == 1)
      {
        handleStop();
        continue;
      }
      if (i == 2)
      {
        handleNext();
        continue;
      }
      if (i != 3)
        continue;
      handlePrevious();
    }
  }

  public void onStop()
  {
    stopRecognition();
  }

  public void setWordList(WordList paramWordList)
  {
    this.mWordList = paramWordList;
  }

  public void startRecognition(WordList paramWordList)
  {
    boolean bool = MidasSettings.getBoolean("barge_in_enabled", false);
    if ((!isRecognizing()) && (bool));
    try
    {
      getBargeInRecognizer().startBargeIn(paramWordList.ordinal());
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void stopRecognition()
  {
    if (isRecognizing())
      getBargeInRecognizer().stopBargeIn();
  }

  public static enum WordList
  {
    static
    {
      All = new WordList("All", 2);
      Alarm = new WordList("Alarm", 3);
      Music = new WordList("Music", 4);
      Radio = new WordList("Radio", 5);
      Video = new WordList("Video", 6);
      Camera = new WordList("Camera", 7);
      WordList[] arrayOfWordList = new WordList[8];
      arrayOfWordList[0] = VoiceTalkAll;
      arrayOfWordList[1] = VoiceTalkSchedule;
      arrayOfWordList[2] = All;
      arrayOfWordList[3] = Alarm;
      arrayOfWordList[4] = Music;
      arrayOfWordList[5] = Radio;
      arrayOfWordList[6] = Video;
      arrayOfWordList[7] = Camera;
      $VALUES = arrayOfWordList;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.BargeInWidget
 * JD-Core Version:    0.6.0
 */