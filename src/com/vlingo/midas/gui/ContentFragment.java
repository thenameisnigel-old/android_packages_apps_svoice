package com.vlingo.midas.gui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import com.vlingo.midas.gui.widgets.HelpChoiceWidget;
import com.vlingo.midas.gui.widgets.YouCanSayWidget;
import com.vlingo.midas.settings.MidasSettings;
import java.util.LinkedList;
import java.util.Queue;

public abstract class ContentFragment extends Fragment
{
  public static final String EXTRA_LIST_HEIGHT = "EXTRA_LIST_HEIGHT";
  public static final String FHW_RESULT_STRING = "FHW_RESULT_STRING";
  public static final String LIST_MOVE_ACTION = "LIST_MOVE_ACTION";
  public static final String SHOW_LIST_ACTION = "SHOW_LIST_ACTION";
  private boolean calledOnConfigrationChanged;
  protected String fragmentLanguage;
  protected final Queue<View> mRunningQueue = new LinkedList();
  protected final Queue<View> mWaitingQueue = new LinkedList();

  public abstract void addCustonWakeUpInitialBody();

  public abstract DialogBubble addDialogBubble(DialogBubble.BubbleType paramBubbleType, String paramString, boolean paramBoolean1, boolean paramBoolean2);

  public abstract void addDialogHelpScreen();

  public abstract void addDummyView(View paramView);

  public abstract void addHelpChoiceWidget();

  public abstract void addHelpWidget(Intent paramIntent);

  public abstract void addMainPrompt();

  public abstract void addTimerWidget();

  protected void addView(View paramView, boolean paramBoolean)
  {
    if (this.mRunningQueue.isEmpty())
      doAddView(paramView, paramBoolean);
    while (true)
    {
      return;
      this.mWaitingQueue.offer(paramView);
    }
  }

  public void addWidget(View paramView)
  {
    if ((paramView instanceof HelpChoiceWidget))
      addHelpChoiceWidget();
    while (true)
    {
      return;
      if ((paramView instanceof YouCanSayWidget))
      {
        addYouCanSayWidget();
        continue;
      }
      addView(paramView, true);
      MidasSettings.setHelpVisible(false);
    }
  }

  public abstract void addYouCanSayWidget();

  public abstract void cancelAsrEditing();

  public abstract boolean checkWakeUpView(View paramView);

  public abstract void cleanupPreviousBubbles();

  protected abstract void doAddView(View paramView, boolean paramBoolean);

  public abstract void doScroll();

  public abstract void finishHWR();

  public abstract boolean getEnabledHWR();

  public String getFragmentLanguage()
  {
    return this.fragmentLanguage;
  }

  public boolean isCalledOnConfigrationChanged()
  {
    return this.calledOnConfigrationChanged;
  }

  public abstract void onHWRUpdated(String paramString);

  protected abstract void onIdle();

  public abstract void onLanguageChanged();

  protected abstract void onUserCancel();

  public abstract void removeAlreadyExistingHelpScreen();

  public abstract void removeDialogHelpScreen();

  public abstract void removeReallyWakeupBubble();

  public abstract void removeWakeupBubble();

  public abstract DialogBubble replaceUserEditBubble(String paramString);

  public abstract void resetAllContent();

  public void setCalledOnConfigrationChanged(boolean paramBoolean)
  {
    this.calledOnConfigrationChanged = paramBoolean;
  }

  public abstract void setHWRButton();

  public abstract void setHWRButton(boolean paramBoolean);

  public abstract void setHWRButtonOff();

  public abstract void startAnimationFlipDown(View paramView);

  public abstract void startAnimationFlipUp(View paramView);

  public abstract void startHWR();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.ContentFragment
 * JD-Core Version:    0.6.0
 */