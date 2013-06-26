package com.vlingo.midas.gui;

import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings.System;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnHoverListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import com.vlingo.core.internal.audio.AudioFocusManager;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.dialogmanager.DialogFlowTaskRegulator;
import com.vlingo.core.internal.dialogmanager.DialogFlowTaskRegulator.EventType;
import com.vlingo.core.internal.dialogmanager.ResumeControl;
import com.vlingo.core.internal.settings.LanguageChangeReceiver;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.gui.customviews.RelativeLayout;
import com.vlingo.midas.gui.effect.Rotate3dAnimation;
import com.vlingo.midas.gui.widgets.ButtonWidget;
import com.vlingo.midas.gui.widgets.HelpChoiceWidget;
import com.vlingo.midas.gui.widgets.HelpWidget;
import com.vlingo.midas.gui.widgets.TimerWidget;
import com.vlingo.midas.gui.widgets.YouCanSayWidget;
import com.vlingo.midas.iux.IUXManager;
import com.vlingo.midas.settings.MidasSettings;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class DialogFragment extends ContentFragment
  implements DialogFlowTaskRegulator, View.OnTouchListener, OnEditListener, DialogBubble.OnSlideTextListener, View.OnHoverListener
{
  private static final int MAX_DUMMY_COUNT = 5;
  private static final int MAX_RESUME_CONTROL_POLLS = 3;
  private static final int MSG_ADD_GESTUREPAD = 0;
  private static final int MSG_FLAG_HOVER_EVENT = 1;
  private static final int REMAIN_DUMMY_COUNT = 2;
  private static final int RESUME_CONTOL_POLL_DURATION = 750;
  private final int ANIMATE_FLIP_UP_DOWN_DURATION = 300;
  private final String FHW_HEIGHT = "FHW_HEIGHT";
  private final String FHW_LAND_HEIGHT = "FHW_LAND_HEIGHT";
  private final String FHW_LAND_WIDTH = "FHW_LAND_WIDTH";
  private final String FHW_LAND_X = "FHW_LAND_X";
  private final String FHW_LAND_Y = "FHW_LAND_Y";
  private final String FHW_LANGUAGE = "FHW_LANGUAGE";
  private final String FHW_TIMEOUT_PAD = "FHW_TIMEOUT";
  private final String FHW_WIDTH = "FHW_WIDTH";
  private final String FHW_X = "FHW_X";
  private final String FHW_Y = "FHW_Y";
  private View buffer;
  private LinearLayout dialogScrollContent;
  private ScrollView dialogScrollView;
  private boolean enabledHWR;
  private RelativeLayout fullContainer;
  private BroadcastReceiver gesturePadBroadcastReceiver;
  private Button hwrButton;
  private Button hwrButton_h;
  private ImageView hwrLine;
  private ImageView hwrLine_h;
  boolean isBtnAndWakeBubble = false;
  private boolean isEditStarted = false;
  private boolean isFullScreen;
  private boolean isWolfWidgetExist = false;
  private LanguageChangeListener languageListener;
  private boolean lastBubbleIsGreeting;
  private ViewGroup.LayoutParams lp;
  private ViewGroup.LayoutParams lp_h;
  private boolean mActivityCreated = false;
  private DialogBubble mDb;
  private boolean mHelpWidgetExist = false;
  private boolean mIsPenInserted = true;
  private int mOldButtonState;
  private ProgressBar mProgressBar;
  private ResumeControl mResumeControl;
  private Timer mTimer;
  private DialogFragmentHandler mhandler = new DialogFragmentHandler(this);
  private BroadcastReceiver moveListBroadcastReceiver;
  private float openCloseThreshold;
  private ImageView pullTab;
  private TimerWidget timerWidget;
  private boolean trackingTouch;

  private void animateDialogExpand()
  {
    float f1 = getActivity().getWindowManager().getDefaultDisplay().getRefreshRate();
    float f2 = 0.3F * f1;
    int i = this.dialogScrollView.getLayoutParams().height;
    new AnimateDialogExpandThread(this, f1, f2, i, (this.fullContainer.getBottom() - i) / f2).start();
  }

  private void doScrollAnim(int paramInt)
  {
    this.dialogScrollView.post(new Runnable(paramInt)
    {
      public void run()
      {
        DialogFragment.this.dialogScrollView.smoothScrollTo(0, this.val$height);
      }
    });
  }

  private DialogHelpScreen getDialogHelpScreen()
  {
    ConversationActivity localConversationActivity = (ConversationActivity)getActivity();
    DialogHelpScreen localDialogHelpScreen = null;
    if (localConversationActivity != null)
      localDialogHelpScreen = (DialogHelpScreen)localConversationActivity.findViewById(2131558579);
    return localDialogHelpScreen;
  }

  private int getReservedHeight()
  {
    return 0;
  }

  private void helpScrollAnim()
  {
    new AnimThread(this).start();
  }

  private boolean isEnableASREditing(String paramString)
  {
    if ("All".equals("All"));
    for (boolean bool = true; ; bool = Settings.getString("asr.editing.enabled.languages", "All").contains(paramString))
      return bool;
  }

  private boolean isEqual(String paramString, int paramInt)
  {
    if (paramString.equals(getResources().getString(paramInt)));
    for (int i = 1; ; i = 0)
      return i;
  }

  private void removeOldContent()
  {
    int i = this.dialogScrollContent.getChildCount();
    if (this.dialogScrollView.getMeasuredHeight() > this.dialogScrollContent.getMeasuredHeight());
    for (int j = 1; j != 0; j = 0)
    {
      ViewGroup.LayoutParams localLayoutParams2 = this.buffer.getLayoutParams();
      this.buffer.setLayoutParams(localLayoutParams2);
      return;
    }
    int k = 0;
    for (int m = i - 1; m >= 0; m--)
    {
      View localView = this.dialogScrollContent.getChildAt(m);
      localView.measure(0, 0);
      k += localView.getMeasuredHeight();
    }
    ViewGroup.LayoutParams localLayoutParams1 = this.buffer.getLayoutParams();
    int n = this.dialogScrollView.getMeasuredHeight() - k;
    if (n < 0);
    for (localLayoutParams1.height = 40; ; localLayoutParams1.height = n)
    {
      this.buffer.setLayoutParams(localLayoutParams1);
      break;
    }
  }

  private void setDialogHeight(float paramFloat)
  {
    this.dialogScrollView.post(new Runnable(paramFloat)
    {
      public void run()
      {
        ViewGroup.LayoutParams localLayoutParams = DialogFragment.this.dialogScrollView.getLayoutParams();
        localLayoutParams.height = (int)this.val$y;
        DialogFragment.this.dialogScrollView.setLayoutParams(localLayoutParams);
      }
    });
  }

  private void setReloadGesture()
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.setClassName("com.sec.android.gesturepad", "com.sec.android.gesturepad.GesturePadActivity");
    localIntent.setFlags(335544320);
    String str = Settings.getLanguageApplication().replace("-", "_");
    Bundle localBundle = new Bundle();
    localBundle.putString("FHW_LANGUAGE", str);
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    this.dialogScrollView.getLocationOnScreen(arrayOfInt);
    int i = 0;
    if (getActivity().getActionBar() != null)
      i = getActivity().getActionBar().getHeight();
    localBundle.putInt("FHW_X", arrayOfInt[0]);
    localBundle.putInt("FHW_Y", arrayOfInt[1] - i);
    localBundle.putInt("FHW_HEIGHT", i + this.dialogScrollView.getBottom() - this.hwrButton.getHeight());
    localBundle.putInt("FHW_WIDTH", this.dialogScrollView.getRight());
    localBundle.putInt("FHW_LAND_X", arrayOfInt[0]);
    localBundle.putInt("FHW_LAND_Y", arrayOfInt[1] - i);
    localBundle.putInt("FHW_LAND_HEIGHT", i + this.dialogScrollView.getBottom() - this.hwrButton.getHeight());
    localBundle.putInt("FHW_LAND_WIDTH", this.dialogScrollView.getRight());
    localBundle.putInt("FHW_TIMEOUT", 10000);
    localIntent.putExtras(localBundle);
    startActivity(localIntent);
    this.mhandler.postDelayed(new Runnable()
    {
      public void run()
      {
        if (DialogFragment.this.mActivityCreated)
          DialogFragment.this.mProgressBar.setVisibility(8);
      }
    }
    , 500L);
  }

  public void addCustonWakeUpInitialBody()
  {
    if (!this.mActivityCreated);
    while (true)
    {
      return;
      addView(View.inflate(getActivity(), 2130903063, null), true);
    }
  }

  public DialogBubble addDialogBubble(DialogBubble.BubbleType paramBubbleType, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    DialogBubble localDialogBubble = null;
    if (((paramBoolean2) && (this.lastBubbleIsGreeting)) || (this.dialogScrollContent == null));
    while (true)
    {
      return localDialogBubble;
      if (this.dialogScrollContent.getChildCount() > 1)
      {
        View localView3 = this.dialogScrollContent.getChildAt(-2 + this.dialogScrollContent.getChildCount());
        if ((localView3 instanceof MyDummyView))
          localView3 = this.dialogScrollContent.getChildAt(-3 + this.dialogScrollContent.getChildCount());
        if (((localView3 instanceof DialogBubble)) && (paramBubbleType == DialogBubble.BubbleType.WAKE_UP) && (((DialogBubble)localView3).getType() == DialogBubble.BubbleType.WAKE_UP))
        {
          ((DialogBubble)localView3).initialize(paramBubbleType, paramString);
          localView3.setVisibility(0);
          continue;
        }
      }
      if ((paramBubbleType == DialogBubble.BubbleType.WAKE_UP) && (this.mActivityCreated) && (getActivity() != null) && (!(this.dialogScrollContent.getChildAt(-2 + this.dialogScrollContent.getChildCount()) instanceof MyDummyView)))
        addDummyView(new MyDummyView(getActivity()));
      this.lastBubbleIsGreeting = paramBoolean2;
      localDialogBubble = null;
      try
      {
        if (paramBubbleType != DialogBubble.BubbleType.USER)
          break;
        localDialogBubble = replaceUserEditBubble(paramString);
        if (localDialogBubble == null)
          break;
        localDialogBubble.setReplaceAvailable(false);
      }
      catch (NullPointerException localNullPointerException)
      {
        localNullPointerException.printStackTrace();
      }
    }
    int j;
    if (localDialogBubble == null)
    {
      j = 2130903050;
      switch (12.$SwitchMap$com$vlingo$midas$gui$DialogBubble$BubbleType[paramBubbleType.ordinal()])
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      }
    }
    while (true)
    {
      localDialogBubble = (DialogBubble)View.inflate(getActivity(), j, null);
      localDialogBubble.setOnSlideTextListenr(this);
      if (paramBubbleType == DialogBubble.BubbleType.USER);
      for (int i = 0; ; i++)
        if (i < this.dialogScrollContent.getChildCount())
        {
          View localView2 = this.dialogScrollContent.getChildAt(i);
          if ((!(localView2 instanceof DialogBubble)) || (((DialogBubble)localView2).getType() != DialogBubble.BubbleType.USER))
            continue;
          ((DialogBubble)localView2).setOnEditListener(null);
          ((DialogBubble)localView2).setEditable(false);
          ((DialogBubble)localView2).setTalkbackString(false);
        }
        else
        {
          localDialogBubble.setOnEditListener(this);
          boolean bool = Settings.isAsrEditingEnabled();
          localDialogBubble.setEditable(bool);
          localDialogBubble.setTalkbackString(bool);
          localDialogBubble.setOnSlideTextListenr(this);
          View localView1 = this.dialogScrollContent.getChildAt(-2 + this.dialogScrollContent.getChildCount());
          if ((localView1 instanceof DialogBubble))
          {
            if (((DialogBubble)localView1).getType() == DialogBubble.BubbleType.WAKE_UP)
              localView1 = this.dialogScrollContent.getChildAt(-4 + this.dialogScrollContent.getChildCount());
            if (((localView1 instanceof DialogBubble)) && (((DialogBubble)localView1).getType() == DialogBubble.BubbleType.USER) && (((DialogBubble)localView1).isReplaceAvailable()))
              this.dialogScrollContent.removeView(localView1);
          }
          if (paramBubbleType == DialogBubble.BubbleType.HWR)
            this.mDb = localDialogBubble;
          if (this.mRunningQueue.isEmpty())
          {
            this.mRunningQueue.offer(localDialogBubble);
            localDialogBubble.initialize(paramBubbleType, paramString);
            doAddView(localDialogBubble, paramBoolean1);
            break;
          }
          localDialogBubble.saveParam(paramBubbleType, paramString, paramBoolean1);
          this.mWaitingQueue.offer(localDialogBubble);
          break;
        }
      j = 2130903056;
      continue;
      j = 2130903058;
      continue;
      j = 2130903060;
      continue;
      j = 2130903054;
    }
  }

  public void addDialogHelpScreen()
  {
    if (!this.mActivityCreated);
    while (true)
    {
      return;
      DialogHelpScreen localDialogHelpScreen = (DialogHelpScreen)View.inflate(getActivity(), 2130903066, null);
      localDialogHelpScreen.resetScroll();
      localDialogHelpScreen.setDialogScreen();
      doAddView(localDialogHelpScreen, true);
      localDialogHelpScreen.setVisibility(4);
    }
  }

  public void addDummyView(View paramView)
  {
    doAddView(paramView, true);
  }

  public void addHelpChoiceWidget()
  {
    if (!this.mActivityCreated);
    while (true)
    {
      return;
      Intent localIntent = new Intent();
      localIntent.setAction("com.sec.android.intent.action.DVFS_BOOSTER");
      localIntent.putExtra("PACKAGE", "com.vlingo.midas");
      localIntent.putExtra("DURATION", "2000");
      getActivity().sendBroadcast(localIntent);
      if (this.mHelpWidgetExist)
        removeAlreadyExistingHelpScreen();
      HelpChoiceWidget localHelpChoiceWidget = (HelpChoiceWidget)View.inflate(getActivity(), 2130903174, null);
      this.mHelpWidgetExist = true;
      doAddView(localHelpChoiceWidget, true);
      MidasSettings.setHelpVisible(true);
    }
  }

  public void addHelpWidget(Intent paramIntent)
  {
    if (!this.mActivityCreated);
    while (true)
    {
      return;
      Intent localIntent = new Intent();
      localIntent.setAction("com.sec.android.intent.action.DVFS_BOOSTER");
      localIntent.putExtra("PACKAGE", "com.vlingo.midas");
      localIntent.putExtra("DURATION", "2000");
      getActivity().sendBroadcast(localIntent);
      HelpWidget localHelpWidget = (HelpWidget)View.inflate(getActivity(), 2130903173, null);
      localHelpWidget.setAddView(paramIntent);
      doAddView(localHelpWidget, true);
    }
  }

  public void addMainPrompt()
  {
    String str = MidasSettings.getHomeMainPrompt();
    addDialogBubble(DialogBubble.BubbleType.VLINGO, str, false, true);
    ControlFragment localControlFragment = (ControlFragment)getActivity().getSupportFragmentManager().findFragmentById(2131558517);
    if ((localControlFragment != null) && (localControlFragment.isActivityCreated()));
    while (true)
    {
      return;
      if (localControlFragment == null)
        continue;
    }
  }

  public void addSpecialEvent(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return;
      View localView = View.inflate(getActivity(), 2130903122, null);
      TextView localTextView1 = (TextView)localView.findViewById(2131558832);
      TextView localTextView2 = (TextView)localView.findViewById(2131558833);
      String str = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()).substring(0, 4);
      localTextView1.setText(str);
      localTextView2.setText(getString(2131362749) + " " + str + ".");
      doAddView(localView, true);
      continue;
      doAddView(View.inflate(getActivity(), 2130903121, null), true);
      continue;
      doAddView(View.inflate(getActivity(), 2130903120, null), true);
    }
  }

  public void addTimerWidget()
  {
    if (!this.mActivityCreated);
    while (true)
    {
      return;
      doAddView((TimerWidget)View.inflate(getActivity(), 2130903209, null), true);
    }
  }

  public void addYouCanSayWidget()
  {
    YouCanSayWidget localYouCanSayWidget = (YouCanSayWidget)View.inflate(getActivity(), 2130903214, null);
    localYouCanSayWidget.setAddView();
    doAddView(localYouCanSayWidget, true);
  }

  public void cancelAsrEditing()
  {
    if (this.mActivityCreated)
      for (int i = 0; i < -1 + this.dialogScrollContent.getChildCount(); i++)
      {
        View localView = this.dialogScrollContent.getChildAt(i);
        if ((!(localView instanceof DialogBubble)) || (((DialogBubble)localView).getType() != DialogBubble.BubbleType.USER) || (((DialogBubble)localView).isUserTextReadyToTouch()))
          continue;
        ((DialogBubble)localView).setUserTextReadyToTouch();
      }
  }

  public boolean checkWakeUpView(View paramView)
  {
    if (((paramView instanceof DialogBubble)) && (((DialogBubble)paramView).getType() == DialogBubble.BubbleType.WAKE_UP));
    for (int i = 1; ; i = 0)
      return i;
  }

  public void cleanupPreviousBubbles()
  {
    if ((this.dialogScrollContent != null) && (this.dialogScrollContent.getChildCount() > 10))
    {
      int i = 0;
      for (int j = -1 + this.dialogScrollContent.getChildCount(); j >= 0; j--)
      {
        if (!(this.dialogScrollContent.getChildAt(j) instanceof MyDummyView))
          continue;
        i++;
        if (i < 5)
          continue;
        for (int k = j - 2; k >= 0; k--)
          this.dialogScrollContent.removeViewAt(k);
      }
    }
  }

  protected void doAddView(View paramView, boolean paramBoolean)
  {
    int i;
    boolean bool;
    if (isAdded())
    {
      i = 0;
      bool = false;
      if ((this.buffer.isFocusable()) || (this.buffer.isFocusableInTouchMode()))
      {
        this.buffer.setFocusable(false);
        this.buffer.setFocusableInTouchMode(false);
      }
      cancelAsrEditing();
      removeReallyWakeupBubble();
      if ((paramBoolean) && (!this.isFullScreen))
      {
        animateDialogExpand();
        this.isFullScreen = true;
      }
      if ((paramView instanceof Widget))
      {
        if (paramView.getClass().getSimpleName().equals("NaverWidget"))
          break label691;
        if ((!isActivityCreated()) || (((getActivity().getResources().getDisplayMetrics().heightPixels != 752) || (getActivity().getResources().getDisplayMetrics().widthPixels != 1280)) && ((getActivity().getResources().getDisplayMetrics().heightPixels != 1232) || (getActivity().getResources().getDisplayMetrics().widthPixels != 800))))
          break label663;
        paramView.setPadding(0, 10, 0, 20);
      }
    }
    label644: label900: 
    while (true)
    {
      int m = -1 + this.dialogScrollContent.getChildCount();
      label204: if (m >= 0)
      {
        View localView2 = this.dialogScrollContent.getChildAt(m);
        if (!(localView2 instanceof Widget))
          break label703;
        if ((localView2.getClass().getSimpleName().equals("ButtonWidget")) && (paramView.getClass().getSimpleName().equals("WolframAlphaWidget")))
        {
          bool = true;
          this.isWolfWidgetExist = true;
          int n = localView2.getHeight();
          i = n - n / 4;
        }
        if ((((Widget)localView2).isWidgetReplaceable()) && (localView2.getClass().getName().equals(paramView.getClass().getName())))
          this.dialogScrollContent.removeViewAt(m);
      }
      if (checkWakeUpView(paramView))
      {
        int k = this.dialogScrollContent.getChildCount();
        if (this.dialogScrollContent.getChildAt(k - 3).getClass().getSimpleName().equals("ButtonWidget"))
          this.isBtnAndWakeBubble = true;
      }
      if (((paramView instanceof DialogBubble)) && (this.dialogScrollContent.getChildCount() == 1))
      {
        if ((isActivityCreated()) && (((getActivity().getResources().getDisplayMetrics().heightPixels == 752) && (getResources().getDisplayMetrics().widthPixels == 1280)) || ((getActivity().getResources().getDisplayMetrics().heightPixels == 1232) && (getResources().getDisplayMetrics().widthPixels == 800))))
          paramView.setPadding(0, 10, 0, 0);
      }
      else
      {
        label477: if (((paramView instanceof HelpChoiceWidget)) && (this.dialogScrollContent.getChildCount() == 1))
        {
          if ((!isActivityCreated()) || (((getActivity().getResources().getDisplayMetrics().heightPixels != 752) || (getResources().getDisplayMetrics().widthPixels != 1280)) && ((getActivity().getResources().getDisplayMetrics().heightPixels != 1232) || (getResources().getDisplayMetrics().widthPixels != 800))))
            break label720;
          paramView.setPadding(0, 10, 0, 20);
        }
        label582: this.dialogScrollContent.addView(paramView, -1 + this.dialogScrollContent.getChildCount());
        paramView.requestFocus();
        removeOldContent();
        if (!bool)
          break label747;
        if (!this.isBtnAndWakeBubble)
          break label733;
        removeWakeupBubble();
        new AnimThread(this, i, bool).start();
        this.isBtnAndWakeBubble = false;
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              Thread.sleep(3000L);
              ConversationActivity localConversationActivity = (ConversationActivity)DialogFragment.this.getActivity();
              if (localConversationActivity != null)
                localConversationActivity.addBlueBubble();
              label25: return;
            }
            catch (InterruptedException localInterruptedException)
            {
              break label25;
            }
          }
        }).start();
      }
      while (true)
      {
        return;
        label663: if (paramView.getClass().getSimpleName().equals("ButtonWidget"))
          break label900;
        paramView.setPadding(0, 45, 0, 0);
        break;
        label691: paramView.setPadding(0, 0, 0, 20);
        break;
        label703: m--;
        break label204;
        paramView.setPadding(0, 0, 0, 0);
        break label477;
        label720: paramView.setPadding(0, 20, 0, 40);
        break label582;
        label733: new AnimThread(this).start();
        break label644;
        label747: if ((paramView instanceof MyDummyView))
          continue;
        if (!(paramView instanceof MyDummyView))
        {
          Rect localRect = new Rect();
          if (checkWakeUpView(paramView))
          {
            View localView1 = null;
            for (int j = -1 + this.dialogScrollContent.getChildCount(); j > 0; j--)
            {
              localView1 = this.dialogScrollContent.getChildAt(j);
              if ((!(localView1 instanceof MyDummyView)) && (!(localView1 instanceof ButtonWidget)) && (localView1 != this.buffer) && (localView1 != paramView))
                break;
            }
            if (localView1 != null)
            {
              localView1.getHeight();
              localView1.getGlobalVisibleRect(localRect);
            }
          }
          this.mhandler.postDelayed(new Runnable(this)
          {
            public void run()
            {
              if (DialogFragment.this.mActivityCreated)
                new DialogFragment.AnimThread(this.val$dialogFragment).start();
            }
          }
          , 100L);
        }
        this.isWolfWidgetExist = false;
        continue;
        DialogFlow.getInstance().interruptTurn();
      }
    }
  }

  public void doScroll()
  {
    if (this.dialogScrollView != null)
      this.dialogScrollView.postDelayed(new Runnable()
      {
        public void run()
        {
          DialogFragment.this.dialogScrollView.fullScroll(130);
        }
      }
      , 500L);
  }

  DialogBubble findLastBubbleByType(DialogBubble.BubbleType paramBubbleType)
  {
    int i;
    View localView;
    if (this.dialogScrollContent != null)
    {
      i = -1 + this.dialogScrollContent.getChildCount();
      if (i >= 0)
      {
        localView = this.dialogScrollContent.getChildAt(i);
        if ((localView == null) || (!(localView instanceof DialogBubble)) || (((DialogBubble)localView).getType() != paramBubbleType));
      }
    }
    for (DialogBubble localDialogBubble = (DialogBubble)localView; ; localDialogBubble = null)
    {
      return localDialogBubble;
      i--;
      break;
    }
  }

  public void finishHWR()
  {
    if (this.enabledHWR)
    {
      Intent localIntent = new Intent("com.sec.android.gesturepad.Action_Exit_FHW");
      getActivity().sendBroadcast(localIntent);
      setHWRButtonOff();
      this.mProgressBar.setVisibility(8);
    }
  }

  public boolean getEnabledHWR()
  {
    return this.enabledHWR;
  }

  public boolean isActivityCreated()
  {
    if ((getActivity() != null) && (this.mActivityCreated));
    for (int i = 1; ; i = 0)
      return i;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.fullContainer = ((RelativeLayout)getView().findViewById(2131558597));
    this.fullContainer.setOnHoverListener(this);
    this.dialogScrollContent = ((LinearLayout)getView().findViewById(2131558599));
    this.dialogScrollView = ((ScrollView)getView().findViewById(2131558598));
    this.pullTab = ((ImageView)getView().findViewById(2131558601));
    this.buffer = this.dialogScrollContent.findViewById(2131558600);
    setCalledOnConfigrationChanged(false);
    this.hwrButton = ((Button)getView().findViewById(2131558602));
    this.hwrButton_h = ((Button)getView().findViewById(2131558603));
    this.hwrLine = ((ImageView)getView().findViewById(2131558604));
    this.hwrLine_h = ((ImageView)getView().findViewById(2131558605));
    if (this.hwrButton_h == null)
      this.hwrButton_h = this.hwrButton;
    if (this.hwrLine_h == null)
      this.hwrLine_h = this.hwrLine;
    if ((this.hwrButton != null) && (this.hwrButton_h != null) && (this.hwrLine != null) && (this.hwrLine_h != null))
    {
      this.lp = this.hwrButton.getLayoutParams();
      this.lp_h = this.hwrButton_h.getLayoutParams();
      this.enabledHWR = false;
      this.hwrLine.bringToFront();
      this.hwrLine_h.bringToFront();
      this.hwrButton.bringToFront();
      this.hwrButton_h.bringToFront();
      this.hwrButton.setVisibility(8);
      this.hwrButton_h.setVisibility(4);
      this.hwrLine.setVisibility(8);
      this.hwrLine_h.setVisibility(8);
      this.hwrButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          DialogFlow.getInstance().endpointReco();
          DialogFlow.getInstance().cancelTTS();
          DialogFlow.getInstance().cancelTurn();
          if (!DialogFragment.this.enabledHWR)
            DialogFragment.this.startHWR();
          while (true)
          {
            return;
            DialogFragment.this.finishHWR();
          }
        }
      });
    }
    String str = Settings.getLanguageApplication();
    this.languageListener = new LanguageChangeListener();
    this.languageListener.register(getActivity());
    this.languageListener.onLanguageChanged(str);
    this.fragmentLanguage = str;
    this.mProgressBar = ((ProgressBar)getView().findViewById(2131558606));
    if (this.mProgressBar != null)
      this.mProgressBar.setVisibility(8);
    this.trackingTouch = false;
    this.openCloseThreshold = 0.0F;
    this.fullContainer.setOnTouchListener(this);
    animateDialogExpand();
    this.isFullScreen = true;
    boolean bool = false;
    this.timerWidget = ((TimerWidget)getView().findViewById(2131559143));
    if (this.timerWidget != null)
    {
      bool = this.timerWidget.onRestoreInstanceState(paramBundle);
      if (bool)
        addTimerWidget();
    }
    this.moveListBroadcastReceiver = new MoveListBroadcastReceiver(null);
    IntentFilter localIntentFilter1 = new IntentFilter("LIST_MOVE_ACTION");
    localIntentFilter1.addAction("LIST_MOVE_ACTION");
    localIntentFilter1.addAction("com.vlingo.LANGUAGE_CHANGED");
    localIntentFilter1.addAction("SHOW_LIST_ACTION");
    getActivity().registerReceiver(this.moveListBroadcastReceiver, localIntentFilter1);
    this.gesturePadBroadcastReceiver = new GesturePadBroadcastReceiver(null);
    IntentFilter localIntentFilter2 = new IntentFilter("com.vlingo.midas.gui.gesturepad.send_result_string");
    localIntentFilter2.addAction("com.vlingo.midas.gui.gesturepad.send_result_string");
    localIntentFilter2.addAction("com.vlingo.midas.gui.gesturepad.finish_timeout");
    localIntentFilter2.addAction("com.samsung.pen.INSERT");
    getActivity().registerReceiver(this.gesturePadBroadcastReceiver, localIntentFilter2);
    DialogFlow.getInstance().registerTaskRegulator(DialogFlowTaskRegulator.EventType.RECOGNITION_START, this);
    ConversationActivity localConversationActivity = (ConversationActivity)getActivity();
    if ((localConversationActivity != null) && (localConversationActivity.isStartedForCustomWakeUpSetting()))
      localConversationActivity.addCustomWakeupSettingView();
    while (true)
    {
      this.mActivityCreated = true;
      return;
      if (!IUXManager.isIUXComplete())
        continue;
      if (localConversationActivity.isDrivingMode() != ConversationActivity.DrivingMode.Driving)
      {
        if ((getResources().getDisplayMetrics().heightPixels == 1920) && (getResources().getDisplayMetrics().widthPixels == 1080) && (getResources().getDisplayMetrics().density == 3.0D))
          addSpecialEvent(ConversationActivity.dayType);
        addYouCanSayWidget();
        addMainPrompt();
      }
      if ((this.timerWidget != null) || (bool))
        continue;
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    boolean bool = isEnableASREditing(Settings.getLanguageApplication());
    if ((!bool) || ((bool) && (!this.isEditStarted)))
      doScroll();
    if ((this.hwrButton != null) && (this.hwrLine != null) && (this.hwrLine_h != null) && (!((ConversationActivity)getActivity()).isDialogMode()))
      if (paramConfiguration.orientation == 2)
      {
        this.hwrButton.setLayoutParams(this.lp_h);
        if (this.enabledHWR)
        {
          this.hwrButton.setBackgroundResource(2130837550);
          this.hwrLine.setVisibility(8);
          this.hwrLine_h.setVisibility(0);
          finishHWR();
          this.mhandler.postDelayed(new Runnable()
          {
            public void run()
            {
              DialogFragment.this.startHWR();
            }
          }
          , 1400L);
        }
      }
    while (true)
    {
      return;
      this.hwrButton.setBackgroundResource(2130837548);
      continue;
      if ((paramConfiguration.orientation != 1) && (!((ConversationActivity)getActivity()).isDialogMode()))
        continue;
      this.hwrButton.setLayoutParams(this.lp);
      if (this.enabledHWR)
      {
        this.hwrButton.setBackgroundResource(2130837549);
        this.hwrLine.setVisibility(0);
        this.hwrLine_h.setVisibility(8);
        finishHWR();
        this.mhandler.postDelayed(new Runnable()
        {
          public void run()
          {
            DialogFragment.this.startHWR();
          }
        }
        , 1400L);
        continue;
      }
      this.hwrButton.setBackgroundResource(2130837547);
      continue;
      if ((!((ConversationActivity)getActivity()).isDialogMode()) || (!getEnabledHWR()))
        continue;
      finishHWR();
      setCalledOnConfigrationChanged(true);
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903067, paramViewGroup, false);
    this.isFullScreen = false;
    return localView;
  }

  public void onDestroy()
  {
    super.onDestroy();
    this.mActivityCreated = false;
    this.isEditStarted = false;
    MidasSettings.setHelpVisible(false);
    if (this.languageListener != null)
      getActivity().unregisterReceiver(this.languageListener);
    if (this.moveListBroadcastReceiver != null)
      getActivity().unregisterReceiver(this.moveListBroadcastReceiver);
    if (this.gesturePadBroadcastReceiver != null)
      getActivity().unregisterReceiver(this.gesturePadBroadcastReceiver);
    DialogFlow.getInstance().unregisterTaskFlowRegulator(DialogFlowTaskRegulator.EventType.RECOGNITION_START, this);
    DialogFlow.getInstance().unregisterTaskFlowRegulator(DialogFlowTaskRegulator.EventType.RECOGNITION_START, this);
  }

  public void onEditCanceled(View paramView)
  {
    if (this.mActivityCreated)
    {
      ControlFragment localControlFragment = (ControlFragment)getActivity().getSupportFragmentManager().findFragmentById(2131558517);
      if ((localControlFragment != null) && (localControlFragment.isActivityCreated()))
        localControlFragment.setState(ControlFragment.ControlState.IDLE);
      setHWRButton(this.mIsPenInserted);
      removeOldContent();
      this.buffer.setFocusableInTouchMode(true);
      this.buffer.setFocusable(true);
      this.buffer.requestFocus();
      new AnimThread(this).start();
    }
    ConversationActivity localConversationActivity = (ConversationActivity)getActivity();
    if (localConversationActivity != null)
      localConversationActivity.showControlFragment();
  }

  public void onEditFinished(View paramView)
  {
    if (!this.mActivityCreated)
      return;
    this.isEditStarted = false;
    int i = -2 + this.dialogScrollContent.getChildCount();
    label24: View localView2;
    if (i >= 0)
    {
      localView2 = this.dialogScrollContent.getChildAt(i);
      if (paramView != localView2)
        break label196;
    }
    while (true)
    {
      setHWRButton(this.mIsPenInserted);
      removeOldContent();
      this.isEditStarted = false;
      View localView1 = this.dialogScrollContent.getChildAt(-2 + this.dialogScrollContent.getChildCount());
      if (((localView1 instanceof DialogBubble)) && (((DialogBubble)localView1).getType() == DialogBubble.BubbleType.WAKE_UP))
        localView1 = this.dialogScrollContent.getChildAt(-3 + this.dialogScrollContent.getChildCount());
      int j = localView1.getBottom() - paramView.getBottom();
      int k = 800;
      if (j > this.dialogScrollView.getMeasuredHeight())
        k = 1300;
      this.mhandler.postDelayed(new Runnable()
      {
        public void run()
        {
          if (DialogFragment.this.mActivityCreated)
          {
            ConversationActivity localConversationActivity = (ConversationActivity)DialogFragment.this.getActivity();
            if (localConversationActivity != null)
              localConversationActivity.showControlFragment();
            DialogFragment.this.helpScrollAnim();
          }
        }
      }
      , k);
      this.buffer.setFocusableInTouchMode(true);
      this.buffer.setFocusable(true);
      this.buffer.requestFocus();
      break;
      label196: if (((localView2 instanceof DialogBubble)) && (((DialogBubble)localView2).getType() == DialogBubble.BubbleType.WAKE_UP))
      {
        i++;
        break label24;
      }
      DialogBubble localDialogBubble = (DialogBubble)paramView;
      String str = localDialogBubble.getText();
      addDialogBubble(DialogBubble.BubbleType.USER, str, true, false).setReplaceAvailable(true);
      localDialogBubble.replaceOriginalText();
    }
  }

  public void onEditStarted(View paramView)
  {
    if (this.mActivityCreated)
    {
      ConversationActivity localConversationActivity = (ConversationActivity)getActivity();
      if (localConversationActivity != null)
        localConversationActivity.hideControlFragment();
      removeWakeupBubble();
      ControlFragment localControlFragment = (ControlFragment)getActivity().getSupportFragmentManager().findFragmentById(2131558517);
      if ((localControlFragment != null) && (localControlFragment.isActivityCreated()))
        localControlFragment.setState(ControlFragment.ControlState.ASR_EDITING);
      this.isEditStarted = true;
      this.hwrButton.setVisibility(8);
    }
  }

  public void onHWRUpdated(String paramString)
  {
    if (this.mDb != null)
      this.mDb.setHWRText(paramString);
  }

  public boolean onHover(View paramView, MotionEvent paramMotionEvent)
  {
    int i = 1;
    boolean bool = isEnableASREditing(Settings.getLanguageApplication());
    if ((!this.mIsPenInserted) && (bool) && (!this.isEditStarted))
    {
      int j = paramMotionEvent.getButtonState();
      if ((paramMotionEvent.getToolType(0) == 2) && (j == 2) && (this.mOldButtonState != j))
      {
        this.mOldButtonState = j;
        this.mhandler.sendEmptyMessageDelayed(i, 1000L);
        if (!this.enabledHWR)
          startHWR();
      }
    }
    while (true)
    {
      return i;
      finishHWR();
      continue;
      i = 0;
    }
  }

  protected void onIdle()
  {
  }

  public void onLanguageChanged()
  {
    this.fragmentLanguage = Settings.getString("language", "en-US");
    resetAllContent();
    ViewGroup.LayoutParams localLayoutParams = this.buffer.getLayoutParams();
    localLayoutParams.height = 0;
    this.buffer.setLayoutParams(localLayoutParams);
    if ((this.hwrButton != null) && (this.hwrButton_h != null) && (this.hwrLine != null) && (this.hwrLine_h != null))
    {
      this.hwrButton.setContentDescription(getResources().getString(2131362609));
      this.hwrButton_h.setContentDescription(getResources().getString(2131362609));
    }
  }

  public void onPause()
  {
    super.onPause();
    if (this.mTimer != null)
      this.mTimer.cancel();
  }

  public void onResume()
  {
    super.onResume();
    setHWRButton();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    this.timerWidget = ((TimerWidget)getView().findViewById(2131559143));
    if (this.timerWidget != null)
      this.timerWidget.onSaveInstanceState(paramBundle);
  }

  public void onSlideTextEnd(String paramString)
  {
    String str = MidasSettings.getHomeMainPrompt();
    if ((!StringUtils.isNullOrWhiteSpace(paramString)) && (paramString.equals(str)))
    {
      FragmentActivity localFragmentActivity = getActivity();
      if (localFragmentActivity != null)
      {
        DialogHelpScreen localDialogHelpScreen = (DialogHelpScreen)localFragmentActivity.findViewById(2131558579);
        if (localDialogHelpScreen != null)
        {
          localDialogHelpScreen.setVisibility(0);
          localDialogHelpScreen.setEffect(300, 0.1F, 1.0F, 0.0F, 1.0F);
          localDialogHelpScreen.startLayoutAnimation();
        }
      }
    }
    View localView;
    if ((View)this.mRunningQueue.peek() != null)
    {
      this.mRunningQueue.poll();
      if (!this.mWaitingQueue.isEmpty())
      {
        localView = (View)this.mWaitingQueue.poll();
        if (!(localView instanceof DialogBubble))
          break label183;
        this.mRunningQueue.offer(localView);
        ((DialogBubble)localView).initialize(((DialogBubble)localView).getType(), ((DialogBubble)localView).getText());
        doAddView(localView, ((DialogBubble)localView).isFillScreen());
      }
    }
    while (true)
    {
      return;
      label183: doAddView(localView, true);
    }
  }

  public void onSlideTextStart(String paramString)
  {
    if ((!this.mWaitingQueue.isEmpty()) && ((this.mWaitingQueue.peek() instanceof Widget)))
      doAddView((View)this.mWaitingQueue.poll(), true);
  }

  public void onTaskWaitingToStart(DialogFlowTaskRegulator.EventType paramEventType, ResumeControl paramResumeControl)
  {
    synchronized (this.mWaitingQueue)
    {
      this.mResumeControl = paramResumeControl;
      this.mTimer = new Timer(true);
      this.mTimer.schedule(new ResumeControlPollTask(null), 0L, 750L);
      return;
    }
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    float f = paramMotionEvent.getY();
    if ((i == 0) && (f > this.pullTab.getTop()) && (f < this.pullTab.getBottom()))
    {
      this.trackingTouch = true;
      this.openCloseThreshold = f;
      setDialogHeight(paramMotionEvent.getY());
    }
    while (true)
    {
      return true;
      if ((this.trackingTouch) && ((i == 3) || (i == 1)))
      {
        this.trackingTouch = false;
        if (f > this.openCloseThreshold)
        {
          animateDialogExpand();
          continue;
        }
        if (f >= this.openCloseThreshold)
          continue;
        getActivity().onBackPressed();
        continue;
      }
      if (!this.trackingTouch)
        continue;
      setDialogHeight(paramMotionEvent.getY());
    }
  }

  protected void onUserCancel()
  {
  }

  public void removeAlreadyExistingHelpScreen()
  {
    for (int i = -1 + this.dialogScrollContent.getChildCount(); ; i--)
    {
      if (i >= 0)
      {
        View localView = this.dialogScrollContent.getChildAt(i);
        if ((localView == null) || (!(localView instanceof HelpChoiceWidget)))
          continue;
        this.dialogScrollContent.removeView(localView);
      }
      return;
    }
  }

  public void removeDialogHelpScreen()
  {
    DialogHelpScreen localDialogHelpScreen = getDialogHelpScreen();
    if (localDialogHelpScreen != null)
    {
      localDialogHelpScreen.setEffect(200, 1.0F, 0.1F, 1.0F, 0.0F);
      localDialogHelpScreen.getAnimation().setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnimation)
        {
          DialogHelpScreen localDialogHelpScreen = DialogFragment.this.getDialogHelpScreen();
          if (localDialogHelpScreen != null)
          {
            localDialogHelpScreen.setVisibility(8);
            DialogFragment.this.dialogScrollContent.postDelayed(new DialogFragment.9.1(this), 1000L);
          }
        }

        public void onAnimationRepeat(Animation paramAnimation)
        {
        }

        public void onAnimationStart(Animation paramAnimation)
        {
        }
      });
      localDialogHelpScreen.startLayoutAnimation();
    }
  }

  public void removeReallyWakeupBubble()
  {
    DialogBubble localDialogBubble = findLastBubbleByType(DialogBubble.BubbleType.WAKE_UP);
    if (localDialogBubble != null)
      this.dialogScrollContent.removeView(localDialogBubble);
  }

  public void removeWakeupBubble()
  {
    DialogBubble localDialogBubble = findLastBubbleByType(DialogBubble.BubbleType.WAKE_UP);
    if (localDialogBubble != null)
      localDialogBubble.setVisibility(4);
  }

  public DialogBubble replaceUserEditBubble(String paramString)
  {
    int i = -1 + this.dialogScrollContent.getChildCount();
    DialogBubble localDialogBubble2;
    if (i >= 0)
    {
      View localView = this.dialogScrollContent.getChildAt(i);
      if ((localView != null) && ((localView instanceof DialogBubble)) && (((DialogBubble)localView).isReplaceAvailable()))
      {
        localDialogBubble2 = (DialogBubble)localView;
        localDialogBubble2.setText(paramString);
        localDialogBubble2.setType(DialogBubble.BubbleType.USER);
      }
    }
    for (DialogBubble localDialogBubble1 = localDialogBubble2; ; localDialogBubble1 = null)
    {
      return localDialogBubble1;
      i--;
      break;
    }
  }

  public void resetAllContent()
  {
    if (!this.mWaitingQueue.isEmpty())
      this.mWaitingQueue.clear();
    if (!this.mRunningQueue.isEmpty())
      this.mRunningQueue.clear();
    if (this.dialogScrollContent == null);
    while (true)
    {
      return;
      this.dialogScrollContent.removeAllViews();
      ViewGroup.LayoutParams localLayoutParams = this.buffer.getLayoutParams();
      localLayoutParams.height = getReservedHeight();
      this.buffer.setLayoutParams(localLayoutParams);
      this.dialogScrollContent.addView(this.buffer);
      this.lastBubbleIsGreeting = false;
      if (1 != Settings.System.getInt(getActivity().getContentResolver(), "driving_mode_on", 0))
        addYouCanSayWidget();
      addMainPrompt();
    }
  }

  public void setHWRButton()
  {
    if ((getActivity().getWindowManager().getDefaultDisplay().getOrientation() == 0) || (getActivity().getWindowManager().getDefaultDisplay().getRotation() == 2) || (((ConversationActivity)getActivity()).isDialogMode()))
    {
      this.hwrButton.setLayoutParams(this.lp);
      if (this.enabledHWR)
      {
        this.hwrButton.setBackgroundResource(2130837549);
        this.hwrLine.setVisibility(0);
        this.hwrLine_h.setVisibility(8);
      }
    }
    while (true)
    {
      return;
      this.hwrButton.setBackgroundResource(2130837547);
      continue;
      this.hwrButton.setLayoutParams(this.lp_h);
      if (this.enabledHWR)
      {
        this.hwrButton.setBackgroundResource(2130837550);
        this.hwrLine.setVisibility(8);
        this.hwrLine_h.setVisibility(0);
        continue;
      }
      this.hwrButton.setBackgroundResource(2130837548);
    }
  }

  public void setHWRButton(boolean paramBoolean)
  {
    if (Settings.isAsrEditingEnabled())
      if (paramBoolean)
      {
        if (this.enabledHWR)
        {
          Intent localIntent = new Intent("com.sec.android.gesturepad.Action_Exit_FHW");
          getActivity().sendBroadcast(localIntent);
          setHWRButtonOff();
        }
        this.hwrButton.setVisibility(8);
      }
    while (true)
    {
      return;
      this.hwrButton.setVisibility(0);
      setHWRButton();
      continue;
      this.hwrButton.setVisibility(8);
    }
  }

  public void setHWRButtonOff()
  {
    AudioFocusManager.getInstance(getActivity()).requestAudioFocus(3, 2);
    this.enabledHWR = false;
    setHWRButton();
    this.hwrLine.setVisibility(8);
    this.hwrLine_h.setVisibility(8);
  }

  public void startAnimationFlipDown(View paramView)
  {
    Rotate3dAnimation localRotate3dAnimation = new Rotate3dAnimation(0.0F, 90.0F, paramView.getMeasuredWidth() / 2.0F, paramView.getMeasuredHeight() / 2.0F, 310.0F, true, true);
    localRotate3dAnimation.setDuration(300L);
    localRotate3dAnimation.setFillAfter(true);
    localRotate3dAnimation.setInterpolator(new AccelerateInterpolator());
    paramView.startAnimation(localRotate3dAnimation);
  }

  public void startAnimationFlipUp(View paramView)
  {
    Rotate3dAnimation localRotate3dAnimation = new Rotate3dAnimation(-90.0F, 0.0F, paramView.getMeasuredWidth() / 2.0F, paramView.getMeasuredHeight() / 2.0F, 310.0F, false, true);
    localRotate3dAnimation.setDuration(300L);
    localRotate3dAnimation.setFillAfter(true);
    localRotate3dAnimation.setInterpolator(new DecelerateInterpolator());
    paramView.startAnimation(localRotate3dAnimation);
  }

  public void startHWR()
  {
    if (!this.enabledHWR)
    {
      AudioFocusManager.getInstance(getActivity()).abandonAudioFocus();
      this.enabledHWR = true;
      this.mhandler.sendEmptyMessageDelayed(0, 200L);
      setHWRButton();
      this.mProgressBar.setVisibility(0);
    }
  }

  private static class AnimThread extends Thread
  {
    private static final int ANIM_FRAME_DELAY = 5;
    private static final int ANIM_FRAME_MAX = 20;
    private static final int ANIM_FRAME_MIN = 1;
    private static final int ANIM_FRAME_MIN_START_POINT = 20;
    private final WeakReference<DialogFragment> dialogFragment;
    private boolean isWolframFlag = false;
    private int mFromHeight;
    private final int mToHeight;
    private int padding = 0;

    public AnimThread(DialogFragment paramDialogFragment)
    {
      this.dialogFragment = new WeakReference(paramDialogFragment);
      this.mToHeight = paramDialogFragment.dialogScrollContent.getHeight();
      this.mFromHeight = paramDialogFragment.dialogScrollView.getScrollY();
    }

    public AnimThread(DialogFragment paramDialogFragment, int paramInt)
    {
      this.dialogFragment = new WeakReference(paramDialogFragment);
      this.mToHeight = (paramDialogFragment.dialogScrollContent.getHeight() - paramInt);
      this.mFromHeight = paramDialogFragment.dialogScrollView.getScrollY();
    }

    public AnimThread(DialogFragment paramDialogFragment, int paramInt, boolean paramBoolean)
    {
      this.dialogFragment = new WeakReference(paramDialogFragment);
      this.padding = paramInt;
      this.isWolframFlag = paramBoolean;
      this.mToHeight = paramDialogFragment.dialogScrollContent.getHeight();
      this.mFromHeight = paramDialogFragment.dialogScrollView.getScrollY();
    }

    private boolean continueLoop()
    {
      DialogFragment localDialogFragment = (DialogFragment)this.dialogFragment.get();
      if (localDialogFragment == null);
      for (boolean bool = false; ; bool = localDialogFragment.mActivityCreated)
        return bool;
    }

    public void run()
    {
      int i = 20;
      while (true)
      {
        DialogFragment localDialogFragment;
        if ((this.mToHeight > this.mFromHeight) && (continueLoop()))
        {
          localDialogFragment = (DialogFragment)this.dialogFragment.get();
          if (localDialogFragment == null)
            return;
          if (this.isWolframFlag)
          {
            localDialogFragment.doScrollAnim(this.mFromHeight - this.padding);
            label57: this.mFromHeight = (i + this.mFromHeight);
          }
        }
        try
        {
          Thread.sleep(5L);
          label73: if (this.mToHeight - this.mFromHeight >= 20)
            continue;
          i = 1;
          continue;
          localDialogFragment.doScrollAnim(this.mFromHeight);
          break label57;
          this.isWolframFlag = false;
        }
        catch (InterruptedException localInterruptedException)
        {
          break label73;
        }
      }
    }
  }

  private static class AnimateDialogExpandThread extends Thread
  {
    final float delta;
    private final WeakReference<DialogFragment> dialogFragment;
    final float frames;
    final int initialHeight;
    final float refreshRate;

    public AnimateDialogExpandThread(DialogFragment paramDialogFragment, float paramFloat1, float paramFloat2, int paramInt, float paramFloat3)
    {
      this.refreshRate = paramFloat1;
      this.frames = paramFloat2;
      this.initialHeight = paramInt;
      this.delta = paramFloat3;
      this.dialogFragment = new WeakReference(paramDialogFragment);
    }

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore_1
      //   2: iload_1
      //   3: i2f
      //   4: fstore_2
      //   5: fload_2
      //   6: aload_0
      //   7: getfield 25	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread:frames	F
      //   10: fcmpg
      //   11: ifge +64 -> 75
      //   14: aload_0
      //   15: getfield 36	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread:dialogFragment	Ljava/lang/ref/WeakReference;
      //   18: invokevirtual 47	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
      //   21: checkcast 6	com/vlingo/midas/gui/DialogFragment
      //   24: invokestatic 51	com/vlingo/midas/gui/DialogFragment:access$400	(Lcom/vlingo/midas/gui/DialogFragment;)Z
      //   27: ifeq +48 -> 75
      //   30: aload_0
      //   31: getfield 36	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread:dialogFragment	Ljava/lang/ref/WeakReference;
      //   34: invokevirtual 47	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
      //   37: checkcast 6	com/vlingo/midas/gui/DialogFragment
      //   40: aload_0
      //   41: getfield 27	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread:initialHeight	I
      //   44: i2f
      //   45: iload_1
      //   46: i2f
      //   47: aload_0
      //   48: getfield 29	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread:delta	F
      //   51: fmul
      //   52: fadd
      //   53: f2i
      //   54: i2f
      //   55: invokestatic 55	com/vlingo/midas/gui/DialogFragment:access$800	(Lcom/vlingo/midas/gui/DialogFragment;F)V
      //   58: ldc 56
      //   60: aload_0
      //   61: getfield 23	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread:refreshRate	F
      //   64: fdiv
      //   65: f2l
      //   66: invokestatic 60	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread:sleep	(J)V
      //   69: iinc 1 1
      //   72: goto -70 -> 2
      //   75: aload_0
      //   76: getfield 36	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread:dialogFragment	Ljava/lang/ref/WeakReference;
      //   79: invokevirtual 47	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
      //   82: checkcast 6	com/vlingo/midas/gui/DialogFragment
      //   85: iconst_1
      //   86: invokestatic 64	com/vlingo/midas/gui/DialogFragment:access$902	(Lcom/vlingo/midas/gui/DialogFragment;Z)Z
      //   89: pop
      //   90: aload_0
      //   91: getfield 36	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread:dialogFragment	Ljava/lang/ref/WeakReference;
      //   94: invokevirtual 47	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
      //   97: checkcast 6	com/vlingo/midas/gui/DialogFragment
      //   100: invokestatic 68	com/vlingo/midas/gui/DialogFragment:access$500	(Lcom/vlingo/midas/gui/DialogFragment;)Landroid/widget/ScrollView;
      //   103: new 70	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread$1
      //   106: dup
      //   107: aload_0
      //   108: invokespecial 73	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread$1:<init>	(Lcom/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread;)V
      //   111: invokevirtual 79	android/widget/ScrollView:post	(Ljava/lang/Runnable;)Z
      //   114: pop
      //   115: return
      //   116: astore 6
      //   118: aload_0
      //   119: getfield 36	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread:dialogFragment	Ljava/lang/ref/WeakReference;
      //   122: invokevirtual 47	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
      //   125: checkcast 6	com/vlingo/midas/gui/DialogFragment
      //   128: iconst_1
      //   129: invokestatic 64	com/vlingo/midas/gui/DialogFragment:access$902	(Lcom/vlingo/midas/gui/DialogFragment;Z)Z
      //   132: pop
      //   133: aload_0
      //   134: getfield 36	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread:dialogFragment	Ljava/lang/ref/WeakReference;
      //   137: invokevirtual 47	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
      //   140: checkcast 6	com/vlingo/midas/gui/DialogFragment
      //   143: invokestatic 68	com/vlingo/midas/gui/DialogFragment:access$500	(Lcom/vlingo/midas/gui/DialogFragment;)Landroid/widget/ScrollView;
      //   146: new 70	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread$1
      //   149: dup
      //   150: aload_0
      //   151: invokespecial 73	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread$1:<init>	(Lcom/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread;)V
      //   154: invokevirtual 79	android/widget/ScrollView:post	(Ljava/lang/Runnable;)Z
      //   157: pop
      //   158: goto -43 -> 115
      //   161: astore_3
      //   162: aload_0
      //   163: getfield 36	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread:dialogFragment	Ljava/lang/ref/WeakReference;
      //   166: invokevirtual 47	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
      //   169: checkcast 6	com/vlingo/midas/gui/DialogFragment
      //   172: iconst_1
      //   173: invokestatic 64	com/vlingo/midas/gui/DialogFragment:access$902	(Lcom/vlingo/midas/gui/DialogFragment;Z)Z
      //   176: pop
      //   177: aload_0
      //   178: getfield 36	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread:dialogFragment	Ljava/lang/ref/WeakReference;
      //   181: invokevirtual 47	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
      //   184: checkcast 6	com/vlingo/midas/gui/DialogFragment
      //   187: invokestatic 68	com/vlingo/midas/gui/DialogFragment:access$500	(Lcom/vlingo/midas/gui/DialogFragment;)Landroid/widget/ScrollView;
      //   190: new 70	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread$1
      //   193: dup
      //   194: aload_0
      //   195: invokespecial 73	com/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread$1:<init>	(Lcom/vlingo/midas/gui/DialogFragment$AnimateDialogExpandThread;)V
      //   198: invokevirtual 79	android/widget/ScrollView:post	(Ljava/lang/Runnable;)Z
      //   201: pop
      //   202: aload_3
      //   203: athrow
      //   204: astore 11
      //   206: goto -137 -> 69
      //
      // Exception table:
      //   from	to	target	type
      //   5	58	116	java/lang/RuntimeException
      //   58	69	116	java/lang/RuntimeException
      //   5	58	161	finally
      //   58	69	161	finally
      //   58	69	204	java/lang/InterruptedException
    }
  }

  private static class DialogFragmentHandler extends Handler
  {
    private final WeakReference<DialogFragment> outer;

    DialogFragmentHandler(DialogFragment paramDialogFragment)
    {
      this.outer = new WeakReference(paramDialogFragment);
    }

    public void handleMessage(Message paramMessage)
    {
      DialogFragment localDialogFragment = (DialogFragment)this.outer.get();
      if (localDialogFragment != null)
        switch (paramMessage.what)
        {
        default:
        case 0:
        case 1:
        }
      while (true)
      {
        return;
        localDialogFragment.removeWakeupBubble();
        localDialogFragment.setReloadGesture();
        continue;
        DialogFragment.access$1702(localDialogFragment, -1);
      }
    }
  }

  private class GesturePadBroadcastReceiver extends BroadcastReceiver
  {
    private GesturePadBroadcastReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      String str1;
      if (paramIntent != null)
      {
        str1 = paramIntent.getAction();
        if (!str1.equals("com.vlingo.midas.gui.gesturepad.send_result_string"))
          break label67;
        String str2 = paramIntent.getStringExtra("FHW_RESULT_STRING");
        if (str2 != null)
        {
          DialogFragment.this.addDialogBubble(DialogBubble.BubbleType.USER, str2, true, false).setReplaceAvailable(true);
          DialogFragment.this.setHWRButtonOff();
          DialogFlow.getInstance().startUserFlow(str2, null);
        }
      }
      while (true)
      {
        return;
        label67: if (str1.equals("com.vlingo.midas.gui.gesturepad.finish_timeout"))
        {
          DialogFragment.this.setHWRButtonOff();
          continue;
        }
        if (!str1.equals("com.samsung.pen.INSERT"))
          continue;
        DialogFragment.access$1402(DialogFragment.this, paramIntent.getBooleanExtra("penInsert", false));
        DialogFragment.this.setHWRButton(DialogFragment.this.mIsPenInserted);
      }
    }
  }

  class LanguageChangeListener extends LanguageChangeReceiver
  {
    LanguageChangeListener()
    {
    }

    public void onLanguageChanged(String paramString)
    {
      boolean bool = DialogFragment.this.isEnableASREditing(paramString);
      int i = 0;
      if ((!bool) || (DialogFragment.this.mIsPenInserted))
        i = 8;
      if (DialogFragment.this.hwrButton != null)
        DialogFragment.this.hwrButton.setVisibility(i);
    }
  }

  private class MoveListBroadcastReceiver extends BroadcastReceiver
  {
    private MoveListBroadcastReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      String str = paramIntent.getAction();
      if ("LIST_MOVE_ACTION".equals(str))
        if (paramIntent != null)
        {
          int i = paramIntent.getIntExtra("EXTRA_LIST_HEIGHT", 0);
          int j = 0;
          for (int k = -1 + DialogFragment.this.dialogScrollContent.getChildCount(); k >= 0; k--)
          {
            View localView = DialogFragment.this.dialogScrollContent.getChildAt(k);
            j += localView.getMeasuredHeight();
            if ((localView == null) || (!(localView instanceof HelpChoiceWidget)))
              continue;
            k = 0;
            j -= localView.getMeasuredHeight();
          }
          DialogFragment.this.doScrollAnim(DialogFragment.this.dialogScrollContent.getHeight() - DialogFragment.this.dialogScrollView.getHeight() - i - j);
        }
      while (true)
      {
        return;
        if (("com.vlingo.LANGUAGE_CHANGED".equals(str)) && (MidasSettings.getHelpVisible()))
        {
          DialogFragment.this.addHelpChoiceWidget();
          continue;
        }
      }
    }
  }

  class MyDummyView extends View
  {
    public MyDummyView(Context arg2)
    {
      super();
    }
  }

  private class ResumeControlPollTask extends TimerTask
  {
    private int mTimesWaited = 0;

    private ResumeControlPollTask()
    {
    }

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 15	com/vlingo/midas/gui/DialogFragment$ResumeControlPollTask:this$0	Lcom/vlingo/midas/gui/DialogFragment;
      //   4: getfield 28	com/vlingo/midas/gui/DialogFragment:mWaitingQueue	Ljava/util/Queue;
      //   7: astore_1
      //   8: aload_1
      //   9: monitorenter
      //   10: aload_0
      //   11: getfield 15	com/vlingo/midas/gui/DialogFragment$ResumeControlPollTask:this$0	Lcom/vlingo/midas/gui/DialogFragment;
      //   14: invokevirtual 32	com/vlingo/midas/gui/DialogFragment:getActivity	()Landroid/support/v4/app/FragmentActivity;
      //   17: checkcast 34	com/vlingo/midas/gui/ConversationActivity
      //   20: astore_3
      //   21: aload_3
      //   22: ifnull +13 -> 35
      //   25: aload_0
      //   26: getfield 15	com/vlingo/midas/gui/DialogFragment$ResumeControlPollTask:this$0	Lcom/vlingo/midas/gui/DialogFragment;
      //   29: getfield 28	com/vlingo/midas/gui/DialogFragment:mWaitingQueue	Ljava/util/Queue;
      //   32: ifnonnull +8 -> 40
      //   35: aload_1
      //   36: monitorexit
      //   37: goto +100 -> 137
      //   40: aload_0
      //   41: getfield 15	com/vlingo/midas/gui/DialogFragment$ResumeControlPollTask:this$0	Lcom/vlingo/midas/gui/DialogFragment;
      //   44: getfield 28	com/vlingo/midas/gui/DialogFragment:mWaitingQueue	Ljava/util/Queue;
      //   47: invokeinterface 40 1 0
      //   52: ifne +21 -> 73
      //   55: aload_0
      //   56: getfield 20	com/vlingo/midas/gui/DialogFragment$ResumeControlPollTask:mTimesWaited	I
      //   59: iconst_3
      //   60: if_icmpgt +13 -> 73
      //   63: aload_3
      //   64: invokevirtual 44	com/vlingo/midas/gui/ConversationActivity:isDrivingMode	()Lcom/vlingo/midas/gui/ConversationActivity$DrivingMode;
      //   67: getstatic 50	com/vlingo/midas/gui/ConversationActivity$DrivingMode:Driving	Lcom/vlingo/midas/gui/ConversationActivity$DrivingMode;
      //   70: if_acmpne +54 -> 124
      //   73: aload_0
      //   74: getfield 15	com/vlingo/midas/gui/DialogFragment$ResumeControlPollTask:this$0	Lcom/vlingo/midas/gui/DialogFragment;
      //   77: invokestatic 54	com/vlingo/midas/gui/DialogFragment:access$1200	(Lcom/vlingo/midas/gui/DialogFragment;)Lcom/vlingo/core/internal/dialogmanager/ResumeControl;
      //   80: ifnull +24 -> 104
      //   83: aload_0
      //   84: getfield 15	com/vlingo/midas/gui/DialogFragment$ResumeControlPollTask:this$0	Lcom/vlingo/midas/gui/DialogFragment;
      //   87: invokestatic 54	com/vlingo/midas/gui/DialogFragment:access$1200	(Lcom/vlingo/midas/gui/DialogFragment;)Lcom/vlingo/core/internal/dialogmanager/ResumeControl;
      //   90: invokeinterface 59 1 0
      //   95: aload_0
      //   96: getfield 15	com/vlingo/midas/gui/DialogFragment$ResumeControlPollTask:this$0	Lcom/vlingo/midas/gui/DialogFragment;
      //   99: aconst_null
      //   100: invokestatic 63	com/vlingo/midas/gui/DialogFragment:access$1202	(Lcom/vlingo/midas/gui/DialogFragment;Lcom/vlingo/core/internal/dialogmanager/ResumeControl;)Lcom/vlingo/core/internal/dialogmanager/ResumeControl;
      //   103: pop
      //   104: aload_0
      //   105: getfield 15	com/vlingo/midas/gui/DialogFragment$ResumeControlPollTask:this$0	Lcom/vlingo/midas/gui/DialogFragment;
      //   108: invokestatic 67	com/vlingo/midas/gui/DialogFragment:access$1300	(Lcom/vlingo/midas/gui/DialogFragment;)Ljava/util/Timer;
      //   111: invokevirtual 72	java/util/Timer:cancel	()V
      //   114: aload_1
      //   115: monitorexit
      //   116: goto +21 -> 137
      //   119: astore_2
      //   120: aload_1
      //   121: monitorexit
      //   122: aload_2
      //   123: athrow
      //   124: aload_0
      //   125: iconst_1
      //   126: aload_0
      //   127: getfield 20	com/vlingo/midas/gui/DialogFragment$ResumeControlPollTask:mTimesWaited	I
      //   130: iadd
      //   131: putfield 20	com/vlingo/midas/gui/DialogFragment$ResumeControlPollTask:mTimesWaited	I
      //   134: goto -20 -> 114
      //   137: return
      //
      // Exception table:
      //   from	to	target	type
      //   10	122	119	finally
      //   124	134	119	finally
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.DialogFragment
 * JD-Core Version:    0.6.0
 */