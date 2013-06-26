package com.vlingo.midas.gui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.samsung.android.service.gesture.GestureEvent;
import com.samsung.android.service.gesture.GestureListener;
import com.samsung.android.service.gesture.GestureManager;
import com.samsung.android.service.gesture.GestureManager.ServiceConnectionListener;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.midas.VlingoApplication;
import com.vlingo.midas.gui.widgets.DrivingContents;
import com.vlingo.midas.gui.widgets.DrivingErrorWidget;
import com.vlingo.midas.gui.widgets.DrivingGreetingWidget;
import com.vlingo.midas.gui.widgets.DrivingMusicWidget;
import com.vlingo.midas.gui.widgets.DrivingNewsWidget;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.DrivingWidgetInterface;
import java.lang.ref.WeakReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrivingFragment extends ContentFragment
  implements View.OnClickListener
{
  private static final int GESTURE_LEFT = 1;
  private static final int GESTURE_RIGHT = 0;
  private static final int MAKE_BACKGROUND_FULLY_OPAQUE = 255;
  private static final int MAKE_BACKGROUND_OPACITY = 45;
  protected static final int MSG_REDUCE_WIDGET = 2;
  protected static final int MSG_RESET_DOMAIN = 4;
  protected static final int MSG_RETURN_TO_TIMESCREEN = 0;
  protected static final int MSG_RETURN_TO_TIMESCREEN_WITHMUSIC = 1;
  protected static final int MSG_TIMER_TICK = 3;
  private static final Pattern dm_main_pattern = Pattern.compile("dm_\\w*main");
  private static GestureListener mGestListener = new GestureListener()
  {
    Intent gestureIntent;

    public void onGestureEvent(GestureEvent paramGestureEvent)
    {
      switch (paramGestureEvent.getEvent())
      {
      default:
      case 1:
      case 0:
      }
      while (true)
      {
        return;
        this.gestureIntent = new Intent();
        this.gestureIntent.setAction("com.sec.android.app.music.intent.action.PLAY_PREVIOUS");
        this.gestureIntent.addFlags(268435456);
        VlingoApplication.getInstance().getApplicationContext().startActivity(this.gestureIntent);
        continue;
        this.gestureIntent = new Intent();
        this.gestureIntent.setAction("com.sec.android.app.music.intent.action.PLAY_NEXT");
        this.gestureIntent.addFlags(268435456);
        VlingoApplication.getInstance().getApplicationContext().startActivity(this.gestureIntent);
      }
    }
  };
  private static final int welcomeScreenTimeout = 30;
  View buffer;
  boolean cancelTimer = false;
  DrivingContents drivingContents = null;
  RelativeLayout fullContainer;
  private ViewGroup mBackgroundLayout;
  private boolean mConnected = false;
  private DrivingErrorWidget mErrorWidget;
  private GestureManager mGestMgr = null;
  private DrivingGreetingWidget mGreetingScreen;
  private DrivingFragmentHandler mHandler = new DrivingFragmentHandler(this);
  private DisplayMetrics mMetrics;
  private boolean mMinimized = false;
  private View mRootView;
  private final Handler mhandler = new Handler();
  private boolean musicWidgetEnabled = false;
  int ticksRemaining = 0;
  boolean timerRunning = false;

  private void ShowRegularWidgets()
  {
    cancelTimer();
  }

  private void cancelTimer()
  {
    this.cancelTimer = true;
  }

  private void clearDrivingWidget(boolean paramBoolean)
  {
    if ((this.drivingContents != null) && (this.drivingContents.getChildCount() > 0))
      makeGroundReset();
    this.mHandler.removeMessages(2);
    if (this.mMinimized)
      rearrangeMultiWidnow(0);
    stopMotionCatch();
    this.musicWidgetEnabled = false;
  }

  private DrivingErrorWidget getErrorWidget()
  {
    if ((this.mErrorWidget == null) && (getActivity() != null))
      this.mErrorWidget = ((DrivingErrorWidget)View.inflate(getActivity(), 2130903152, null));
    return this.mErrorWidget;
  }

  private DrivingGreetingWidget getGreetingWidget()
  {
    if ((this.mGreetingScreen == null) && (getActivity() != null))
      this.mGreetingScreen = ((DrivingGreetingWidget)View.inflate(getActivity(), 2130903153, null));
    return this.mGreetingScreen;
  }

  private void makeGroundReset()
  {
    if (getGreetingWidget() != null)
      getGreetingWidget().setAlpha(255);
    if (this.drivingContents != null)
      this.drivingContents.removeAllViews();
  }

  private void rearrangeMultiWidnow(int paramInt)
  {
    if ((getActivity() == null) || (!isAdded()))
      return;
    Intent localIntent;
    if (getResources() != null)
    {
      getActivity().getWindowManager().getDefaultDisplay().getMetrics(this.mMetrics);
      localIntent = new Intent("com.sec.android.action.ARRANGE_CONTROLL_BAR");
      if (getResources().getConfiguration().orientation != 2)
        break label112;
      localIntent.putExtra("com.sec.android.extra.CONTROL_BAR_POS", this.mMetrics.widthPixels - getResources().getDimensionPixelSize(2131427350) - getResources().getDimensionPixelSize(2131427351) - paramInt);
    }
    while (true)
    {
      getActivity().sendBroadcast(localIntent);
      break;
      break;
      label112: localIntent.putExtra("com.sec.android.extra.CONTROL_BAR_POS", this.mMetrics.heightPixels - getResources().getDimensionPixelSize(2131427350) - getResources().getDimensionPixelSize(2131427351) - paramInt);
    }
  }

  private void reduceDrivingWidget()
  {
    if ((this.drivingContents != null) && (this.drivingContents.getChildCount() > 0) && (isMinimizableState()))
    {
      View localView = this.drivingContents.getChildAt(0);
      if ((localView instanceof DrivingWidgetInterface))
      {
        DrivingWidgetInterface localDrivingWidgetInterface = (DrivingWidgetInterface)localView;
        if (!localDrivingWidgetInterface.isDecreasedSize())
          localDrivingWidgetInterface.setWidgetToDecreasedSize(true);
        this.drivingContents.invalidate();
      }
    }
  }

  private void resetAndclearDrivingWidget(boolean paramBoolean)
  {
    ConversationActivity localConversationActivity = (ConversationActivity)getActivity();
    if (localConversationActivity != null)
      localConversationActivity.initFlow();
    clearDrivingWidget(paramBoolean);
  }

  private void setTimer(int paramInt)
  {
    this.ticksRemaining = paramInt;
  }

  private void startTimer(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.cancelTimer = false;
    if ((!this.timerRunning) || (paramBoolean))
    {
      this.mHandler.sendEmptyMessageDelayed(3, 1000L);
      this.timerRunning = true;
    }
  }

  private void stopMotionCatch()
  {
    if ((this.mConnected) && (this.mGestMgr != null) && (mGestListener != null));
    try
    {
      this.mGestMgr.unregisterListener(mGestListener, "ir_provider");
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  private void unbindFromService()
  {
    if (this.mGestMgr != null);
    try
    {
      this.mGestMgr.unbindFromService();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void addCustonWakeUpInitialBody()
  {
  }

  public DialogBubble addDialogBubble(DialogBubble.BubbleType paramBubbleType, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBubbleType == DialogBubble.BubbleType.ERROR)
    {
      DrivingErrorWidget localDrivingErrorWidget = getErrorWidget();
      if (localDrivingErrorWidget != null)
      {
        localDrivingErrorWidget.setText(paramString);
        doAddView(getErrorWidget(), paramBoolean1);
      }
    }
    while (true)
    {
      return null;
      if (paramBubbleType == DialogBubble.BubbleType.WAKE_UP)
      {
        setGreetingText(paramString);
        continue;
      }
      if ((!MidasSettings.getBoolean("show_user_turn_in_driving_mode", false)) || (paramBubbleType != DialogBubble.BubbleType.USER))
        continue;
      setGreetingText(paramString);
    }
  }

  public void addDialogHelpScreen()
  {
  }

  public <T> void addDriveWidget(Widget<T> paramWidget, Object paramObject, WidgetActionListener paramWidgetActionListener)
  {
    ShowRegularWidgets();
    makeGroundReset();
    stopMotionCatch();
    this.drivingContents.addView(paramWidget);
    if ((this.mMinimized) && ((paramWidget instanceof DrivingWidgetInterface)))
    {
      ((DrivingWidgetInterface)paramWidget).setWidgetToDecreasedSize(this.mMinimized);
      rearrangeMultiWidnow(((DrivingWidgetInterface)paramWidget).getDecreasedHeight());
      this.drivingContents.getLayoutParams().height = -2;
      if (getResources().getConfiguration().orientation == 1)
        this.drivingContents.setPadding(12, 0, 12, 0);
      if ((!this.mMinimized) && (((paramWidget instanceof DrivingMusicWidget)) || ((paramWidget instanceof DrivingNewsWidget))))
      {
        this.mHandler.removeMessages(2);
        this.mHandler.sendEmptyMessageDelayed(2, 15000L);
      }
      if (!(paramWidget instanceof DrivingMusicWidget))
        break label230;
      if (this.mConnected)
        startMotionCatch();
    }
    label230: for (this.musicWidgetEnabled = true; ; this.musicWidgetEnabled = false)
    {
      getGreetingWidget().setAlpha(45);
      return;
      if (((paramWidget instanceof DrivingWidgetInterface)) && (((DrivingWidgetInterface)paramWidget).getProperHeight() != 0))
      {
        this.drivingContents.getLayoutParams().height = ((DrivingWidgetInterface)paramWidget).getProperHeight();
        break;
      }
      this.drivingContents.getLayoutParams().height = -2;
      break;
    }
  }

  public void addDummyView(View paramView)
  {
  }

  public void addHelpChoiceWidget()
  {
  }

  public void addHelpWidget(Intent paramIntent)
  {
  }

  public void addMainPrompt()
  {
  }

  public void addTimerWidget()
  {
  }

  public void addYouCanSayWidget()
  {
  }

  public void cancelAsrEditing()
  {
  }

  public boolean checkWakeUpView(View paramView)
  {
    return false;
  }

  public void cleanupPreviousBubbles()
  {
  }

  protected void doAddView(View paramView, boolean paramBoolean)
  {
    if (isAdded())
    {
      cancelAsrEditing();
      removeReallyWakeupBubble();
      ShowRegularWidgets();
      makeGroundReset();
      stopMotionCatch();
      this.musicWidgetEnabled = false;
      this.drivingContents.addView(paramView);
      if ((!this.mMinimized) || (!(paramView instanceof DrivingWidgetInterface)))
        break label101;
      ((DrivingWidgetInterface)paramView).setWidgetToDecreasedSize(this.mMinimized);
      rearrangeMultiWidnow(((DrivingWidgetInterface)paramView).getDecreasedHeight());
      this.drivingContents.getLayoutParams().height = -2;
    }
    while (true)
    {
      this.drivingContents.requestLayout();
      return;
      label101: if (((paramView instanceof DrivingWidgetInterface)) && (((DrivingWidgetInterface)paramView).getProperHeight() != 0))
      {
        this.drivingContents.getLayoutParams().height = ((DrivingWidgetInterface)paramView).getProperHeight();
        continue;
      }
      if (paramBoolean)
      {
        this.drivingContents.getLayoutParams().height = -1;
        continue;
      }
      this.drivingContents.getLayoutParams().height = -2;
    }
  }

  public void doScroll()
  {
  }

  public void finishHWR()
  {
  }

  public boolean getEnabledHWR()
  {
    return false;
  }

  protected int getProperTimeDelay()
  {
    int i;
    if ((this.drivingContents != null) && (this.drivingContents.getChildCount() > 0))
      if ((this.drivingContents.getChildAt(0) instanceof DrivingMusicWidget))
        i = 30;
    while (true)
    {
      return i;
      i = 3;
      continue;
      i = 1;
    }
  }

  protected boolean isMinimizableState()
  {
    int i = 0;
    if ((this.drivingContents != null) && (this.drivingContents.getChildCount() > 0))
    {
      View localView = this.drivingContents.getChildAt(0);
      if (((localView instanceof DrivingNewsWidget)) || ((localView instanceof DrivingMusicWidget)))
        i = 1;
    }
    return i;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.fullContainer = ((RelativeLayout)this.mRootView.findViewById(2131558620));
    this.drivingContents = ((DrivingContents)this.mRootView.findViewById(2131558623));
    this.mBackgroundLayout = ((LinearLayout)this.mRootView.findViewById(2131558622));
    this.fullContainer.setOnClickListener(this);
    this.mMetrics = new DisplayMetrics();
    getActivity().getWindowManager().getDefaultDisplay().getMetrics(this.mMetrics);
    getGreetingWidget().setAlpha(255);
    this.mBackgroundLayout.addView(getGreetingWidget());
    this.fragmentLanguage = Settings.getLanguageApplication();
    this.mGestMgr = new GestureManager(getActivity(), new GestureManager.ServiceConnectionListener()
    {
      public void onServiceConnected()
      {
        DrivingFragment.access$002(DrivingFragment.this, true);
      }

      public void onServiceDisconnected()
      {
        DrivingFragment.access$002(DrivingFragment.this, false);
      }
    });
  }

  public void onClick(View paramView)
  {
    ((ControlFragment)getActivity().getSupportFragmentManager().findFragmentById(2131558804)).performClickFromDriveControl();
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if ((this.fullContainer != null) && (this.drivingContents != null))
    {
      if (paramConfiguration.orientation != 1)
        break label121;
      this.fullContainer.setBackgroundResource(2130837508);
      if (!this.mMinimized)
        break label104;
      this.drivingContents.setPadding(12, 0, 12, 0);
    }
    try
    {
      while (true)
      {
        if ((this.drivingContents.getChildCount() != 0) && (this.mMinimized))
          rearrangeMultiWidnow(((DrivingWidgetInterface)this.drivingContents.getChildAt(-1 + this.drivingContents.getChildCount())).getDecreasedHeight());
        label98: super.onConfigurationChanged(paramConfiguration);
        return;
        label104: this.drivingContents.setPadding(12, 21, 12, 0);
        continue;
        label121: if (this.fullContainer != null)
          this.fullContainer.setBackgroundResource(2130837508);
        this.drivingContents.setPadding(12, 21, 12, 0);
      }
    }
    catch (ClassCastException localClassCastException)
    {
      break label98;
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mRootView = paramLayoutInflater.inflate(2130903073, paramViewGroup, false);
    this.mRootView.setOnClickListener(this);
    return this.mRootView;
  }

  public void onDestroy()
  {
    super.onDestroy();
    this.mHandler.removeMessages(3);
    this.mHandler.removeMessages(0);
    this.mHandler.removeMessages(2);
    this.mHandler.removeMessages(4);
    stopMotionCatch();
    unbindFromService();
    this.musicWidgetEnabled = false;
  }

  public void onHWRUpdated(String paramString)
  {
  }

  public void onHiddenChanged(boolean paramBoolean)
  {
    super.onHiddenChanged(paramBoolean);
  }

  protected void onIdle()
  {
    Matcher localMatcher = dm_main_pattern.matcher(DialogFlow.getInstance().getFieldID().toString());
    Log.d("getFieldID", "drivingfragment-onidle " + DialogFlow.getInstance().getFieldID().toString());
    if (localMatcher.matches())
    {
      int i = getProperTimeDelay();
      if (i != -1)
      {
        setTimer(i);
        startTimer(false);
      }
    }
    while (true)
    {
      return;
      this.mHandler.removeMessages(4);
      this.mHandler.sendEmptyMessageDelayed(4, 30000L);
    }
  }

  public void onLanguageChanged()
  {
    this.fragmentLanguage = Settings.getString("language", "en-US");
  }

  public void onPause()
  {
    super.onPause();
    stopMotionCatch();
  }

  public void onResume()
  {
    super.onResume();
    if (this.musicWidgetEnabled)
      startMotionCatch();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
  }

  protected void onUserCancel()
  {
    Log.d("getFieldID", "drivingfragment-onidle " + DialogFlow.getInstance().getFieldID().toString());
    clearDrivingWidget(true);
  }

  public void removeAlreadyExistingHelpScreen()
  {
  }

  public void removeDialogHelpScreen()
  {
  }

  public void removeReallyWakeupBubble()
  {
  }

  public void removeWakeupBubble()
  {
    setGreetingText("");
  }

  public DialogBubble replaceUserEditBubble(String paramString)
  {
    return null;
  }

  public void resetAllContent()
  {
    makeGroundReset();
  }

  public void setGreetingText(String paramString)
  {
    ConversationActivity localConversationActivity = (ConversationActivity)getActivity();
    if ((getGreetingWidget() != null) && (localConversationActivity != null))
      getActivity().runOnUiThread(new Runnable(paramString)
      {
        public void run()
        {
          DrivingFragment.this.getGreetingWidget().setText(this.val$text);
        }
      });
  }

  public void setHWRButton()
  {
  }

  public void setHWRButton(boolean paramBoolean)
  {
  }

  public void setHWRButtonOff()
  {
  }

  public void setMultiwindow(boolean paramBoolean)
  {
    boolean bool;
    if (!paramBoolean)
    {
      bool = true;
      this.mMinimized = bool;
      if (this.mBackgroundLayout != null)
      {
        if (!paramBoolean)
          break label36;
        this.mBackgroundLayout.setVisibility(0);
      }
    }
    while (true)
    {
      return;
      bool = false;
      break;
      label36: this.mBackgroundLayout.setVisibility(8);
    }
  }

  public void startAnimationFlipDown(View paramView)
  {
  }

  public void startAnimationFlipUp(View paramView)
  {
  }

  public void startHWR()
  {
  }

  void startMotionCatch()
  {
    if (this.mConnected);
    try
    {
      this.mGestMgr.registerListener(mGestListener, "ir_provider", "air_motion_turn");
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  private static class DrivingFragmentHandler extends Handler
  {
    private final WeakReference<DrivingFragment> outer;

    DrivingFragmentHandler(DrivingFragment paramDrivingFragment)
    {
      this.outer = new WeakReference(paramDrivingFragment);
    }

    public void handleMessage(Message paramMessage)
    {
      DrivingFragment localDrivingFragment = (DrivingFragment)this.outer.get();
      if (localDrivingFragment != null)
        switch (paramMessage.what)
        {
        case 1:
        default:
        case 0:
        case 2:
        case 3:
        case 4:
        }
      while (true)
      {
        return;
        localDrivingFragment.clearDrivingWidget(true);
        continue;
        localDrivingFragment.reduceDrivingWidget();
        continue;
        if (localDrivingFragment.cancelTimer)
        {
          localDrivingFragment.cancelTimer = false;
          localDrivingFragment.timerRunning = false;
          continue;
        }
        if (localDrivingFragment.ticksRemaining > 1)
        {
          localDrivingFragment.ticksRemaining = (-1 + localDrivingFragment.ticksRemaining);
          localDrivingFragment.mHandler.sendEmptyMessageDelayed(3, 1000L);
          continue;
        }
        localDrivingFragment.timerRunning = false;
        localDrivingFragment.clearDrivingWidget(false);
        continue;
        localDrivingFragment.resetAndclearDrivingWidget(false);
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.DrivingFragment
 * JD-Core Version:    0.6.0
 */