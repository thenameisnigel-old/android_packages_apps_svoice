package com.vlingo.midas.gui;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.preference.PreferenceManager;
import android.provider.Settings.System;
import android.sec.multiwindow.MultiWindow;
import android.sec.multiwindow.MultiWindowManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionMode;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.sec.android.app.sns3.svc.sp.FacebookSSOAPI;
import com.sec.android.app.sns3.svc.sp.FacebookSSOAPI.FbUpdateType;
import com.vlingo.core.internal.audio.AudioFocusManager;
import com.vlingo.core.internal.audio.AudioPlayerProxy;
import com.vlingo.core.internal.audio.AudioRequest;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored;
import com.vlingo.core.internal.audio.MicAnimationAdapter;
import com.vlingo.core.internal.audio.MicAnimationListener;
import com.vlingo.core.internal.bluetooth.BluetoothManager;
import com.vlingo.core.internal.bluetooth.BluetoothManager.CloseType;
import com.vlingo.core.internal.bluetooth.BluetoothManagerListener;
import com.vlingo.core.internal.contacts.ContactDBUtil;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.dialogmanager.DialogFlow.DialogFlowState;
import com.vlingo.core.internal.dialogmanager.DialogFlowListener;
import com.vlingo.core.internal.dialogmanager.vvs.VVSDispatcher;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.ListenHandler;
import com.vlingo.core.internal.logging.EventLog;
import com.vlingo.core.internal.phrasespotter.PhraseSpotter;
import com.vlingo.core.internal.recognition.service.VlingoVoiceRecognitionService;
import com.vlingo.core.internal.safereader.SafeReaderAlert;
import com.vlingo.core.internal.safereader.SafeReaderProxy;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ADMController;
import com.vlingo.core.internal.util.ADMFeatureListener;
import com.vlingo.core.internal.util.ActivityUtil;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.vlservice.VlingoApplicationService;
import com.vlingo.midas.DeviceConfigurationReceiver;
import com.vlingo.midas.MidasADMController;
import com.vlingo.midas.RegisterSoundPool;
import com.vlingo.midas.ServiceManager;
import com.vlingo.midas.VlingoApplication;
import com.vlingo.midas.drivingmode.DrivingModeUtil;
import com.vlingo.midas.help.HelpScreen;
import com.vlingo.midas.help.WhatCanISayScreen;
import com.vlingo.midas.iux.IUXManager;
import com.vlingo.midas.music.SearchMusic;
import com.vlingo.midas.news.NewsManager;
import com.vlingo.midas.phrasespotter.SeamlessRecoService;
import com.vlingo.midas.phrasespotter.dialog.DialogObject;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.settings.SettingsScreen;
import com.vlingo.midas.settings.debug.DebugSettings;
import com.vlingo.midas.tos.TermsOfServiceManager;
import com.vlingo.midas.ui.GestureListenerAdapter;
import com.vlingo.midas.ui.HomeKeyListener;
import com.vlingo.midas.ui.PackageInfoProvider;
import com.vlingo.midas.ui.VLActivity;
import com.vlingo.midas.util.ErrorCodeUtils;
import com.vlingo.midas.util.log.AsrEventLogUtil;
import com.vlingo.midas.util.log.NLUEventLogUtil;
import com.vlingo.sdk.recognition.NBestData;
import com.vlingo.sdk.recognition.VLRecognitionErrors;
import com.vlingo.sdk.recognition.VLRecognitionStates;
import com.vlingo.sdk.recognition.VLRecognitionWarnings;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

public class ConversationActivity extends VLActivity
  implements DialogFlowListener, IAudioPlaybackService.AudioPlaybackListener, BluetoothManagerListener, View.OnClickListener, PopupMenu.OnMenuItemClickListener, ADMFeatureListener, MicAnimationListener
{
  public static final String ACTION_SAFEREADER_LAUNCH = "ACTION_SAFEREADER_LAUNCH";
  public static final String AUTO_LISTEN = "AUTO_LISTEN";
  public static final String DISPLAY_FROM_BACKGROUND = "displayFromBackground";
  private static final String DRIVING_WAKE_LOCK_NAME = "SVoiceDrivingScreenSaver";
  public static final String EXTRA_MESSAGE_LIST = "EXTRA_MESSAGE_LIST";
  public static final String EXTRA_TIME_SENT = "EXTRA_TIME_SENT";
  private static final int FILE_PICKER_RESULT = 1;
  public static final String IS_FIRST_LAUNCH = "IS_FIRST_LAUNCH";
  public static final String LOCKSCREEN_BIOMETRIC_WEAK_FALLBACK = "lockscreen.biometric_weak_fallback";
  private static final int MSG_ADD_BUBBLE = 0;
  private static final int MSG_CLEAR_DISMISS_KEYGUARD_FLAG = 3;
  private static final int MSG_DRIVE_MODE_CONTROL = 5;
  private static final int MSG_FACEBOOK_LOGIN_FLOW = 6;
  private static final int MSG_REMOVE_LOCATION_LISTENER = 4;
  private static final int MSG_START_RECORDING = 2;
  private static final int MSG_START_SPOTTING = 1;
  public static final String PRESENT_EDITOR = "PRESENT_EDITOR";
  public static final String RESUME_FROM_BACKGROUND = "resumeFromBackground";
  public static final long SAFEREADER_INTENT_EXPIRATION_TIMEOUT = 5000L;
  public static final String SEND_ACTION = "com.vlingo.midas.action.SEND";
  public static final String SHOW_LIST_ACTION = "SHOW_LIST_ACTION";
  private static final String X_VLCONF_EYES_FREE = "isEyesFree";
  public static int dayType;
  static Widget<?> latestWidget;
  private static boolean mIsStartedCustomWakeUpSetting;
  private static int mTheme = 2131296526;
  private static ConversationActivity smCurrentActivity;
  private final int MAIN_MENU_WHAT_CAN_I_SAY = 17;
  private final int MENU_CUSTOM_WAKE_UP = 10;
  private final int MENU_DRIVING_MODE = 11;
  private final int MENU_DRIVING_MODE_OFF = 15;
  private final int MENU_DRIVING_MODE_ON = 16;
  private final int MENU_ENABLE_CUSTOM_WAKE_UP = 12;
  private final int MENU_HELP = 13;
  private final int MENU_HELP_DIALOG = 14;
  private final int MENU_USE_SPEAKER = 9;
  private final long SCREEN_SAVER_TIMEOUT = 300000L;
  private final int SCREEN_SAVER_TIMEOUT_MSG = 0;
  private final String TAG = "ConversationActivity";
  private AsrEventLogUtil asrEventLogUtil;
  private boolean autoStartOnResume = false;
  private DrivingMode bDrivingMode = DrivingMode.None;
  Calendar c;
  private BroadcastReceiver carDockBroadcastReceiver;
  SharedPreferences checkBoxPrefForAlert;
  private ContentFragment contentFragment;
  private View controlContainer;
  private ControlFragment controlFragment;
  ControlFragment.ControlFragmentCallback controlFragmentCallback = new ControlFragment.ControlFragmentCallback()
  {
    private void onBoth(boolean paramBoolean)
    {
      if (paramBoolean)
      {
        ConversationActivity.this.mhandler.removeMessages(0);
        ConversationActivity.this.mhandler.sendEmptyMessage(0);
      }
    }

    public void onRecognitionStarted()
    {
      if (ConversationActivity.latestWidget != null)
        ConversationActivity.latestWidget.onRecognitionStarted();
    }

    public void onStartSpotting()
    {
      onBoth(true);
    }

    public void onStopSpotting()
    {
      onBoth(false);
    }
  };
  private boolean customTitleSupported = false;
  private final String delimeter = "_";
  private View dialogContainer;
  private DialogFragment dialogFragment;
  private boolean displayFromBackground = false;
  private boolean doneTosAndIux = false;
  private ContentObserver driveObserver = null;
  private ContentResolver driveResolver = null;
  private DrivingControlFragment drivingControlFragment;
  private DrivingFragment drivingFragment;
  private BroadcastReceiver drivingModeBroadcastReceiver;
  private RelativeLayout driving_layout;
  private LinearLayout fullContainer;
  private GestureDetector gestures;
  private boolean handleTextReqeust = false;
  private BroadcastReceiver helpWidgetBroadcastReceiver;
  private HomeKeyListener homeKeyDetector;
  private boolean isWakeLockChanged = false;
  private LandRegularControlFragment landRegularControlFragment;
  private PowerManager.WakeLock mDrivingWakeLock;
  private GestureDetector mGestureDetector;
  private ConversationActivityHandler2 mHandy = new ConversationActivityHandler2(this);
  private boolean mIsCWChange = false;
  private final LocationListener mLocationListener = new LocationListener()
  {
    public void onLocationChanged(Location paramLocation)
    {
      paramLocation.getLatitude();
      paramLocation.getLongitude();
      ConversationActivity.this.mhandler.removeMessages(4);
      ConversationActivity.this.mhandler.sendEmptyMessage(4);
    }

    public void onProviderDisabled(String paramString)
    {
    }

    public void onProviderEnabled(String paramString)
    {
    }

    public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
    {
    }
  };
  protected MultiWindow mMW;
  private MultiWindowManager mMWM;
  private float mOldX = 0.0F;
  private float mOldY = 0.0F;
  private LinkedList<SafeReaderAlert> messages;
  private ConversationActivityHandler mhandler = new ConversationActivityHandler(this);
  private int my_orientation;
  private NLUEventLogUtil nluEventLogUtil;
  private RelativeLayout normal_layout;
  SharedPreferences prefForWishesEventAlert;
  private final String prefix = "greeting_tts";
  private boolean present_editor = false;
  private boolean radioflag = false;
  private RegularControlFragment regularControlFragment;
  private boolean releaseKeyguardByMsg = false;
  private boolean resumeFromBackground = false;
  private BroadcastReceiver screenEventBroadcastReceiver;
  String selectGreeting = null;
  List<String> selectedGreetingList = new ArrayList();
  private boolean shouldPlayGreeting = false;
  private boolean startRecoBySCO = false;
  private boolean startWithCarkitInNormal = false;
  private volatile boolean startedWithCarDock = false;
  private String textRequest = null;
  private BroadcastReceiver timeWidgetBroadcastReceiver;
  private AlertDialog tosDialog = null;

  static
  {
    latestWidget = null;
    mIsStartedCustomWakeUpSetting = false;
  }

  private void ConversationCustomViewInitial()
  {
    showVlingoText(getResources().getString(2131362256));
    this.contentFragment.addCustonWakeUpInitialBody();
  }

  private void acquireWakeLock(Intent paramIntent)
  {
    PowerManager localPowerManager = (PowerManager)getSystemService("power");
    long l;
    if (BluetoothManager.isHeadsetConnected())
      l = 4000L;
    while (true)
    {
      if (((paramIntent.getBooleanExtra("isThisComeFromHomeKeyDoubleClickConcept", false)) && (!this.isWakeLockChanged)) || (BluetoothManager.isHeadsetConnected()) || ("android.speech.action.VOICE_SEARCH_HANDS_FREE".equals(paramIntent.getAction())))
      {
        localPowerManager.newWakeLock(805306394, "VoiceTalkTemp").acquire(l);
        this.isWakeLockChanged = true;
      }
      return;
      l = 600L;
    }
  }

  private void checkDeviceConfiguration()
  {
    int i = 1;
    ComponentName localComponentName = new ComponentName(this, DeviceConfigurationReceiver.class);
    if (getPackageManager().getComponentEnabledSetting(localComponentName) != 2)
    {
      sendBroadcast(new Intent("com.vlingo.client.app.action.CHECK_DOUBLE_TAP_CONFIG"));
      return;
    }
    int j = Settings.System.getInt(getContentResolver(), "double_tab_launch", -1);
    if (MidasSettings.getBoolean("launch_voicetalk", false))
    {
      int k = i;
      label67: if ((j == k) || (j == -1))
        break label98;
      if (j != i)
        break label100;
    }
    while (true)
    {
      MidasSettings.setBoolean("launch_voicetalk", i);
      break;
      int m = 0;
      break label67;
      label98: break;
      label100: i = 0;
    }
  }

  private void checkFragmentLanguage()
  {
    String str = Settings.getString("language", "en-US");
    if ((this.contentFragment.getFragmentLanguage() != null) && (!this.contentFragment.getFragmentLanguage().equals(str)))
    {
      this.contentFragment.onLanguageChanged();
      this.controlFragment.onLanguageChanged();
      TextView localTextView = (TextView)findViewById(2131558542);
      if (localTextView != null)
        localTextView.setText(2131362232);
      if (isDialogMode())
      {
        ((ImageButton)findViewById(2131558546)).setContentDescription(getResources().getString(2131362641));
        ((ImageButton)findViewById(2131558547)).setContentDescription(getResources().getString(2131362314));
      }
    }
  }

  private void check_CarDock(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = PreferenceManager.getDefaultSharedPreferences(this).edit();
    localEditor.putBoolean("voice_cardock", paramBoolean);
    localEditor.commit();
  }

  private void clearDismissKeyguardFlag()
  {
    if (getWindow() != null)
      getWindow().clearFlags(4194304);
  }

  private void configureAutoStart(Intent paramIntent)
  {
    boolean bool1 = true;
    if (paramIntent == null)
      return;
    if (IUXManager.isIUXComplete())
      Settings.setBoolean("home_auto_listen", bool1);
    boolean bool2;
    if ((Settings.getBoolean("home_auto_listen", false)) && (paramIntent.getBooleanExtra("AUTO_LISTEN", bool1)) && (!MidasSettings.getBoolean("key_popup_window_opened", false)))
    {
      bool2 = bool1;
      label52: this.autoStartOnResume = bool2;
      this.resumeFromBackground = "resumeFromBackground".equals(paramIntent.getAction());
      if (((!"resumeFromBackground".equals(paramIntent.getAction())) && (!"displayFromBackground".equals(paramIntent.getAction()))) || (this.autoStartOnResume))
        break label114;
    }
    while (true)
    {
      this.displayFromBackground = bool1;
      break;
      bool2 = false;
      break label52;
      label114: bool1 = false;
    }
  }

  // ERROR //
  private void doDismissKeyguard()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: ldc_w 637
    //   5: invokestatic 643	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   8: astore 14
    //   10: aload 14
    //   12: astore_1
    //   13: aload_1
    //   14: ifnull +88 -> 102
    //   17: aconst_null
    //   18: astore_3
    //   19: iconst_1
    //   20: anewarray 639	java/lang/Class
    //   23: astore 12
    //   25: aload 12
    //   27: iconst_0
    //   28: ldc_w 466
    //   31: aastore
    //   32: aload_1
    //   33: ldc_w 645
    //   36: aload 12
    //   38: invokevirtual 649	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   41: astore 13
    //   43: aload 13
    //   45: astore_3
    //   46: aload_3
    //   47: ifnull +55 -> 102
    //   50: aconst_null
    //   51: astore 5
    //   53: iconst_1
    //   54: anewarray 651	java/lang/Object
    //   57: astore 11
    //   59: aload 11
    //   61: iconst_0
    //   62: ldc_w 653
    //   65: aastore
    //   66: aload_3
    //   67: aconst_null
    //   68: aload 11
    //   70: invokevirtual 659	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   73: checkcast 661	android/os/IBinder
    //   76: astore 5
    //   78: aload 5
    //   80: ifnull +22 -> 102
    //   83: aload 5
    //   85: invokestatic 667	android/view/IWindowManager$Stub:asInterface	(Landroid/os/IBinder;)Landroid/view/IWindowManager;
    //   88: astore 7
    //   90: aload 7
    //   92: ifnull +10 -> 102
    //   95: aload 7
    //   97: invokeinterface 672 1 0
    //   102: return
    //   103: astore_2
    //   104: aload_2
    //   105: invokevirtual 675	java/lang/ClassNotFoundException:printStackTrace	()V
    //   108: goto -95 -> 13
    //   111: astore 4
    //   113: aload 4
    //   115: invokevirtual 676	java/lang/NoSuchMethodException:printStackTrace	()V
    //   118: goto -72 -> 46
    //   121: astore 10
    //   123: aload 10
    //   125: invokevirtual 677	java/lang/IllegalArgumentException:printStackTrace	()V
    //   128: goto -50 -> 78
    //   131: astore 9
    //   133: aload 9
    //   135: invokevirtual 678	java/lang/IllegalAccessException:printStackTrace	()V
    //   138: goto -60 -> 78
    //   141: astore 6
    //   143: aload 6
    //   145: invokevirtual 679	java/lang/reflect/InvocationTargetException:printStackTrace	()V
    //   148: goto -70 -> 78
    //   151: astore 8
    //   153: aload 8
    //   155: invokevirtual 680	android/os/RemoteException:printStackTrace	()V
    //   158: goto -56 -> 102
    //
    // Exception table:
    //   from	to	target	type
    //   2	10	103	java/lang/ClassNotFoundException
    //   19	43	111	java/lang/NoSuchMethodException
    //   53	78	121	java/lang/IllegalArgumentException
    //   53	78	131	java/lang/IllegalAccessException
    //   53	78	141	java/lang/reflect/InvocationTargetException
    //   95	102	151	android/os/RemoteException
  }

  private void enableGestures()
  {
    this.gestures = new GestureDetector(new GestureListenerAdapter()
    {
      public boolean onDoubleTap(MotionEvent paramMotionEvent)
      {
        ConversationActivity.access$1502(ConversationActivity.this, true);
        ConversationActivity.this.handleTextRequestIntent(null);
        return true;
      }
    });
  }

  public static HashMap<String, String> generateConfigPairs()
  {
    HashMap localHashMap = new HashMap();
    if (!PackageInfoProvider.hasMessaging())
      localHashMap.put("hasMessagingApp", "false");
    if (!PackageInfoProvider.hasTimer())
      localHashMap.put("hasTimerApp", "false");
    if (!PackageInfoProvider.hasVoiceRecorder())
      localHashMap.put("hasRecordVoiceApp", "false");
    if (!PackageInfoProvider.hasDialing())
      localHashMap.put("hasPhoneApp", "false");
    if (!PackageInfoProvider.hasTelephony())
      localHashMap.put("hasTelephony", "false");
    if (!PackageInfoProvider.hasRadio())
      localHashMap.put("hasRadioApp", "false");
    if (!PackageInfoProvider.hasAlarm())
      localHashMap.put("hasAlarmApp", "false");
    if (!PackageInfoProvider.hasMaps())
      localHashMap.put("hasMapsApp", "false");
    if (!PackageInfoProvider.hasNav())
      localHashMap.put("hasNavigationApp", "false");
    if (!PackageInfoProvider.hasMemo())
      localHashMap.put("hasMemoApp", "false");
    if (!PackageInfoProvider.hasMusic())
      localHashMap.put("hasMusicApp", "false");
    if (!PackageInfoProvider.hasTask())
      localHashMap.put("hasTaskApp", "false");
    Locale localLocale = MidasSettings.getCurrentLocale();
    if ((isDrivingModeEnabled()) && (!ClientSuppliedValues.isTalkbackOn()))
    {
      localHashMap.put("isInDrivingMode", Boolean.TRUE.toString().toLowerCase(localLocale));
      if ((!isDrivingModeEnabled()) && (!isHeadsetModeEnabled()) && (!isDetailedTtsFeedback()))
        break label311;
      localHashMap.put("isEyesFree", Boolean.TRUE.toString().toLowerCase(localLocale));
      MidasSettings.setBoolean("is_eyes_free_mode", true);
    }
    while (true)
    {
      return localHashMap;
      localHashMap.put("isInDrivingMode", Boolean.FALSE.toString().toLowerCase(localLocale));
      break;
      label311: localHashMap.put("isEyesFree", Boolean.FALSE.toString().toLowerCase(localLocale));
      MidasSettings.setBoolean("is_eyes_free_mode", false);
    }
  }

  private boolean getCarDock()
  {
    return PreferenceManager.getDefaultSharedPreferences(this).getBoolean("voice_cardock", false);
  }

  public static Widget<?> getLatestWidget()
  {
    return latestWidget;
  }

  private String getProperGreetingMessage()
  {
    return "Good evening. Have a safe journey, wherever you're heading.";
  }

  private void getTimeConfig(int paramInt)
  {
    String str1;
    if (paramInt < 5)
      str1 = "dawn";
    while (true)
    {
      for (int i = 1; ; i++)
      {
        String str2 = "greeting_tts_" + str1 + "_" + i;
        int j = getResources().getIdentifier(str2, "string", getPackageName());
        if (j == 0)
          break;
        this.selectedGreetingList.add(getString(j));
      }
      if (paramInt < 12)
      {
        str1 = "morning";
        continue;
      }
      if (paramInt < 18)
      {
        str1 = "daytime";
        continue;
      }
      if (paramInt < 21)
      {
        str1 = "evening";
        continue;
      }
      str1 = "night";
    }
  }

  private void getWeekDayConfig(int[] paramArrayOfInt)
  {
    String str1;
    if ((paramArrayOfInt[1] == 2) && (paramArrayOfInt[0] < 11) && (paramArrayOfInt[0] >= 5))
      str1 = "week_start";
    while (true)
    {
      for (int i = 1; ; i++)
      {
        String str2 = "greeting_tts_" + str1 + "_" + i;
        int j = getResources().getIdentifier(str2, "string", getPackageName());
        if (j == 0)
          break;
        this.selectedGreetingList.add(getString(j));
      }
      if ((paramArrayOfInt[1] == 6) && (paramArrayOfInt[0] < 23) && (paramArrayOfInt[0] >= 18))
      {
        str1 = "week_last";
        continue;
      }
      if ((paramArrayOfInt[1] == 7) || ((paramArrayOfInt[1] == 1) && (paramArrayOfInt[0] < 11)))
      {
        str1 = "weekends";
        continue;
      }
      str1 = "others";
    }
  }

  private void initFragments(boolean paramBoolean)
  {
    int i;
    int j;
    if ((getResources().getConfiguration().orientation == 1) || (isDialogMode()))
    {
      i = 1;
      if (getResources().getConfiguration().orientation != 2)
        break label255;
      j = 1;
      label39: if ((paramBoolean) && (!ClientSuppliedValues.isTalkbackOn()))
        break label322;
      if (this.normal_layout == null)
      {
        View.inflate(this, 2130903111, (ViewGroup)findViewById(2131558801));
        this.normal_layout = ((RelativeLayout)findViewById(2131558805));
      }
      if (this.dialogContainer == null)
        this.dialogContainer = this.normal_layout.findViewById(2131558806);
      this.dialogFragment = ((DialogFragment)getSupportFragmentManager().findFragmentById(2131558806));
      if (i == 0)
        break label260;
      if (this.regularControlFragment == null)
      {
        View.inflate(this, 2130903112, this.normal_layout);
        this.regularControlFragment = ((RegularControlFragment)getSupportFragmentManager().findFragmentById(2131558517));
        this.regularControlFragment.setCallback(this.controlFragmentCallback);
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
        localLayoutParams.addRule(2, 2131558517);
        this.dialogContainer.setLayoutParams(localLayoutParams);
      }
      this.controlFragment = this.regularControlFragment;
      label216: if (this.controlContainer == null)
        this.controlContainer = findViewById(2131558517);
      if (this.contentFragment == null)
        this.contentFragment = this.dialogFragment;
    }
    while (true)
    {
      return;
      i = 0;
      break;
      label255: j = 0;
      break label39;
      label260: if (j == 0)
        break label216;
      if (this.landRegularControlFragment == null)
      {
        View.inflate(this, 2130903110, this.normal_layout);
        this.landRegularControlFragment = ((LandRegularControlFragment)getSupportFragmentManager().findFragmentById(2131558518));
        this.landRegularControlFragment.setCallback(this.controlFragmentCallback);
      }
      this.controlFragment = this.landRegularControlFragment;
      break label216;
      label322: if (this.driving_layout == null)
      {
        View.inflate(this, 2130903109, (ViewGroup)findViewById(2131558801));
        this.driving_layout = ((RelativeLayout)findViewById(2131558802));
      }
      if (this.drivingFragment == null)
        this.drivingFragment = ((DrivingFragment)getSupportFragmentManager().findFragmentById(2131558803));
      if (this.drivingControlFragment == null)
      {
        this.drivingControlFragment = ((DrivingControlFragment)getSupportFragmentManager().findFragmentById(2131558804));
        this.drivingControlFragment.setCallback(this.controlFragmentCallback);
      }
      if (this.controlFragment == null)
        this.controlFragment = this.drivingControlFragment;
      if (this.contentFragment != null)
        continue;
      this.contentFragment = this.drivingFragment;
    }
  }

  private static boolean isDetailedTtsFeedback()
  {
    return MidasSettings.getBoolean("detailed_tts_feedback", false);
  }

  private static boolean isDrivingModeEnabled()
  {
    return DrivingModeUtil.isDrivingModeEnabled(VlingoApplication.getInstance());
  }

  private static boolean isHeadsetModeEnabled()
  {
    AudioManager localAudioManager = (AudioManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("audio");
    if ((BluetoothManager.isHeadsetConnected()) || (localAudioManager.isWiredHeadsetOn()));
    for (int i = 1; ; i = 0)
      return i;
  }

  private void onResumeFromBackground(boolean paramBoolean)
  {
    ArrayList localArrayList = SeamlessRecoService.getDialogObjects();
    if (localArrayList == null);
    while (true)
    {
      return;
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
        ((DialogObject)localIterator.next()).execute(this);
      if (!paramBoolean)
        continue;
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          ConversationActivity.this.controlFragment.startRecognition(null);
        }
      }
      , 300L);
    }
  }

  private boolean processSafeReaderMessages(Intent paramIntent)
  {
    int i = 0;
    this.autoStartOnResume = false;
    long l = paramIntent.getLongExtra("EXTRA_TIME_SENT", 0L);
    if (System.currentTimeMillis() - l < 5000L)
    {
      ArrayList localArrayList = (ArrayList)paramIntent.getSerializableExtra("EXTRA_MESSAGE_LIST");
      this.messages = new LinkedList();
      this.messages.addAll(localArrayList);
      i = 1;
    }
    return i;
  }

  private void removeLocationListener()
  {
    Context localContext = getApplicationContext();
    if (localContext != null)
      ((LocationManager)localContext.getSystemService("location")).removeUpdates(this.mLocationListener);
  }

  private void requestLocationUpdate()
  {
    Context localContext = getApplicationContext();
    if (localContext != null)
    {
      LocationManager localLocationManager = (LocationManager)localContext.getSystemService("location");
      if (localLocationManager.isProviderEnabled("network") == true)
      {
        localLocationManager.requestLocationUpdates("network", 100L, 0.0F, this.mLocationListener);
        this.mhandler.removeMessages(4);
        this.mhandler.sendEmptyMessageDelayed(4, 5000L);
      }
    }
  }

  private void retireLastWidget()
  {
    if (latestWidget != null)
      latestWidget.retire();
  }

  private void scheduleScreenSaver()
  {
    this.mHandy.removeMessages(0);
    this.mHandy.sendMessageDelayed(Message.obtain(this.mHandy, 0), 300000L);
  }

  private void setDrivingMode(DrivingMode paramDrivingMode)
  {
    this.bDrivingMode = paramDrivingMode;
  }

  public static void setLatestWidget(Widget<?> paramWidget)
  {
    latestWidget = paramWidget;
  }

  public static boolean shouldReportError(VLRecognitionErrors paramVLRecognitionErrors)
  {
    int i = 0;
    if (VLRecognitionErrors.ERROR_SPEECH_TIMEOUT == paramVLRecognitionErrors);
    while (true)
    {
      return i;
      if (VLRecognitionErrors.ERROR_NO_MATCH == paramVLRecognitionErrors)
        continue;
      i = 1;
    }
  }

  private void unlockScreenIfLocked()
  {
    if (((KeyguardManager)SafeReaderProxy.getContext().getSystemService("keyguard")).inKeyguardRestrictedInputMode())
    {
      Window localWindow = getWindow();
      localWindow.addFlags(4194304);
      localWindow.addFlags(524288);
      localWindow.addFlags(2097152);
    }
  }

  private void unpackSavedInstanceState(Bundle paramBundle)
  {
    if (paramBundle != null)
    {
      this.startedWithCarDock = paramBundle.getBoolean("startedWithCarDock", false);
      this.autoStartOnResume = paramBundle.getBoolean("autoStartOnResume", false);
    }
  }

  public void addBlueBubble()
  {
    if (!Settings.getBoolean("car_word_spotter_enabled", true));
    while (true)
    {
      return;
      this.mhandler.removeMessages(0);
      this.mhandler.sendEmptyMessageDelayed(0, 100L);
    }
  }

  public void addCustomWakeupSettingView()
  {
    this.fullContainer.postDelayed(new Runnable()
    {
      public void run()
      {
        int i = 0;
        while (true)
        {
          if ((!ConversationActivity.this.contentFragment.isResumed()) && (i < 10));
          try
          {
            Thread.sleep(500L);
            i++;
            continue;
            if (!ConversationActivity.this.isFinishing())
              ConversationActivity.this.ConversationCustomViewInitial();
            return;
          }
          catch (InterruptedException localInterruptedException)
          {
          }
        }
      }
    }
    , 250L);
  }

  public <T> void addDriveWidget(Widget<T> paramWidget, Object paramObject, WidgetActionListener paramWidgetActionListener)
  {
    setLatestWidget(paramWidget);
    this.drivingFragment.addDriveWidget(paramWidget, paramObject, paramWidgetActionListener);
  }

  public void addHelpChoiceWidget()
  {
    this.contentFragment.removeWakeupBubble();
    this.contentFragment.addHelpChoiceWidget();
    addBlueBubble();
  }

  public void addHelpWidget(Intent paramIntent)
  {
    this.contentFragment.removeWakeupBubble();
    this.contentFragment.addHelpWidget(paramIntent);
    addBlueBubble();
  }

  public <T> void addWidget(Widget<T> paramWidget)
  {
    retireLastWidget();
    setLatestWidget(paramWidget);
    this.contentFragment.addWidget(paramWidget);
  }

  void afterViews()
  {
    if ((this.dialogContainer != null) && (this.controlContainer != null))
    {
      this.dialogContainer.startAnimation(AnimationUtils.inFromTopAnimation());
      this.controlContainer.startAnimation(AnimationUtils.inFromBottomAnimation());
    }
  }

  public void changeToDriveMode(boolean paramBoolean)
  {
    initFragments(paramBoolean);
    FragmentTransaction localFragmentTransaction1 = getSupportFragmentManager().beginTransaction();
    if ((!paramBoolean) || (ClientSuppliedValues.isTalkbackOn()))
    {
      getWakeupLock(false);
      if ((this.controlFragment.getCurrentState() == ControlFragment.ControlState.IDLE) && (isDrivingMode() == DrivingMode.Driving) && (this.dialogFragment != null))
        this.dialogFragment.resetAllContent();
      if (this.drivingControlFragment != null)
        this.drivingControlFragment.show(false);
      this.contentFragment = this.dialogFragment;
      if (this.driving_layout != null)
        this.driving_layout.setVisibility(8);
      this.normal_layout.setVisibility(0);
      setDrivingMode(DrivingMode.Regular);
      if (this.drivingControlFragment != null)
        localFragmentTransaction1.hide(this.drivingControlFragment);
      if ((getResources().getConfiguration().orientation == 1) || (isDialogMode()))
      {
        if (this.landRegularControlFragment != null)
          localFragmentTransaction1.hide(this.landRegularControlFragment);
        localFragmentTransaction1.show(this.regularControlFragment).commitAllowingStateLoss();
        this.controlFragment = this.regularControlFragment;
      }
      while (true)
      {
        FragmentTransaction localFragmentTransaction2 = getSupportFragmentManager().beginTransaction();
        if (this.drivingFragment != null)
          localFragmentTransaction2.hide(this.drivingFragment);
        localFragmentTransaction2.show(this.dialogFragment).commitAllowingStateLoss();
        PhraseSpotter.getInstance().init(this.controlFragment.getPhraseSpotterParams(), this.controlFragment);
        checkFragmentLanguage();
        return;
        if (getResources().getConfiguration().orientation != 2)
          continue;
        if (this.regularControlFragment != null)
          localFragmentTransaction1.hide(this.regularControlFragment);
        localFragmentTransaction1.show(this.landRegularControlFragment).commitAllowingStateLoss();
        this.controlFragment = this.landRegularControlFragment;
      }
    }
    getWakeupLock(true);
    if ((this.controlFragment.getCurrentState() == ControlFragment.ControlState.IDLE) && (isDrivingMode() == DrivingMode.Regular) && (this.drivingFragment != null))
      this.drivingFragment.resetAllContent();
    View localView1 = findViewById(2131558804);
    View localView2 = findViewById(2131558803);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -1);
    RelativeLayout.LayoutParams localLayoutParams2;
    if (getResources().getConfiguration().orientation == 2)
    {
      localLayoutParams2 = new RelativeLayout.LayoutParams(getResources().getDimensionPixelOffset(2131427350), -1);
      localLayoutParams2.addRule(11);
      localLayoutParams1.addRule(0, 2131558804);
    }
    while (true)
    {
      localView1.setLayoutParams(localLayoutParams2);
      localView2.setLayoutParams(localLayoutParams1);
      this.drivingControlFragment.show(true);
      this.contentFragment = this.drivingFragment;
      this.controlFragment = this.drivingControlFragment;
      this.driving_layout.setVisibility(0);
      if (this.normal_layout != null)
        this.normal_layout.setVisibility(8);
      setDrivingMode(DrivingMode.Driving);
      if (this.regularControlFragment != null)
        localFragmentTransaction1.hide(this.regularControlFragment);
      if (this.landRegularControlFragment != null)
        localFragmentTransaction1.hide(this.landRegularControlFragment);
      localFragmentTransaction1.show(this.drivingControlFragment).commitAllowingStateLoss();
      FragmentTransaction localFragmentTransaction3 = getSupportFragmentManager().beginTransaction();
      if (this.dialogFragment != null)
        localFragmentTransaction3.hide(this.dialogFragment);
      localFragmentTransaction3.show(this.drivingFragment).commitAllowingStateLoss();
      if ((this.controlFragment.getCurrentState() != ControlFragment.ControlState.IDLE) || (this.dialogFragment == null))
        break;
      this.dialogFragment.resetAllContent();
      break;
      localLayoutParams2 = new RelativeLayout.LayoutParams(-1, getResources().getDimensionPixelOffset(2131427350));
      localLayoutParams2.addRule(12);
      localLayoutParams1.addRule(2, 2131558804);
    }
  }

  protected void checkCarDockConnection()
  {
    Intent localIntent = getIntent();
    if (localIntent != null)
    {
      Set localSet = localIntent.getCategories();
      if (localSet != null)
      {
        Iterator localIterator = localSet.iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          if ((str == null) || (!str.equals("android.intent.category.CAR_DOCK")))
            continue;
          this.startedWithCarDock = true;
        }
      }
    }
    boolean bool;
    if (this.startedWithCarDock)
    {
      IntentFilter localIntentFilter = new IntentFilter("android.intent.action.DOCK_EVENT");
      registerReceiver(this.carDockBroadcastReceiver, localIntentFilter);
      bool = true;
      if (!isDrivingModeEnabled())
      {
        Settings.System.putInt(getContentResolver(), "driving_mode_on", 1);
        sendBroadcast(new Intent("DrivingMode"));
        this.startWithCarkitInNormal = true;
      }
    }
    try
    {
      Class localClass = Class.forName("com.android.internal.widget.LockPatternUtils");
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Context.class;
      Constructor localConstructor = localClass.getConstructor(arrayOfClass);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = getApplicationContext();
      Object localObject = localConstructor.newInstance(arrayOfObject);
      bool = ((Boolean)localClass.getMethod("isSecure", (Class[])null).invoke(localObject, new Object[0])).booleanValue();
      Log.d("ConversationActivity", "locked = " + bool);
      label250: if (!bool)
      {
        Window localWindow = getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        localLayoutParams.flags = (0x480000 | localLayoutParams.flags);
        localWindow.setAttributes(localLayoutParams);
      }
      return;
    }
    catch (Exception localException)
    {
      break label250;
    }
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((this.gestures != null) && (this.gestures.onTouchEvent(paramMotionEvent)));
    for (boolean bool = true; ; bool = super.dispatchTouchEvent(paramMotionEvent))
      return bool;
  }

  protected int[] getRealTime()
  {
    this.c = Calendar.getInstance();
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = this.c.get(11);
    arrayOfInt[1] = this.c.get(7);
    return arrayOfInt;
  }

  public void getWakeupLock(boolean paramBoolean)
  {
    if (paramBoolean)
      this.mDrivingWakeLock = ((PowerManager)getSystemService("power")).newWakeLock(6, "SVoiceDrivingScreenSaver");
    try
    {
      if ((this.mDrivingWakeLock != null) && (!this.mDrivingWakeLock.isHeld()))
        this.mDrivingWakeLock.acquire();
      while (true)
      {
        label49: return;
        try
        {
          if ((this.mDrivingWakeLock == null) || (!this.mDrivingWakeLock.isHeld()))
            continue;
          this.mDrivingWakeLock.release();
        }
        catch (Exception localException1)
        {
        }
      }
    }
    catch (Exception localException2)
    {
      break label49;
    }
  }

  protected String greetingTTS()
  {
    Random localRandom = new Random();
    if (this.selectedGreetingList.size() > 0)
      this.selectedGreetingList.clear();
    int[] arrayOfInt = getRealTime();
    getTimeConfig(arrayOfInt[0]);
    getWeekDayConfig(arrayOfInt);
    if (this.selectedGreetingList.size() > 0)
    {
      int i = localRandom.nextInt(this.selectedGreetingList.size());
      this.selectGreeting = ((String)this.selectedGreetingList.get(i));
    }
    return this.selectGreeting;
  }

  public void handleTextRequestIntent(Intent paramIntent)
  {
    this.handleTextReqeust = false;
    if (paramIntent != null)
    {
      this.present_editor = paramIntent.getBooleanExtra("PRESENT_EDITOR", false);
      this.textRequest = paramIntent.getStringExtra("android.intent.extra.TEXT");
    }
    if ((this.textRequest != null) && (!this.present_editor) && (IUXManager.isIUXComplete()))
      DialogFlow.getInstance().startUserFlow(this.textRequest, null);
  }

  public void hideControlFragment()
  {
    getSupportFragmentManager().beginTransaction().hide(this.controlFragment).commitAllowingStateLoss();
  }

  public void initFlow()
  {
    DialogFlow.getInstance().cancelTurn();
    DialogFlow.getInstance().releaseFlow(this);
    HashMap localHashMap = generateConfigPairs();
    DialogFlow.getInstance().initFlow(this, this, localHashMap, new WidgetBuilder(this));
  }

  public boolean isDialogMode()
  {
    if (mTheme != 2131296526);
    for (int i = 1; ; i = 0)
      return i;
  }

  public DrivingMode isDrivingMode()
  {
    return this.bDrivingMode;
  }

  public boolean isPassedAllInitSteps()
  {
    if ((!TermsOfServiceManager.isTOSRequired()) && (!IUXManager.requiresIUX()) && (IUXManager.isIUXComplete()));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isStartedForCustomWakeUpSetting()
  {
    return mIsStartedCustomWakeUpSetting;
  }

  public void onActionModeStarted(ActionMode paramActionMode)
  {
    View localView = findViewById(Resources.getSystem().getIdentifier("action_mode_close_button", "id", "android"));
    if (localView != null)
      localView.setContentDescription(getResources().getString(2131362178));
  }

  public void onBackPressed()
  {
    BluetoothManager.appCloseType(BluetoothManager.CloseType.NORMAL);
    super.onBackPressed();
    DialogFlow.getInstance().cancelTurn();
  }

  public void onClick(View paramView)
  {
    PopupMenu localPopupMenu = new PopupMenu(this, paramView);
    Menu localMenu = localPopupMenu.getMenu();
    localMenu.clear();
    MidasSettings.updateCurrentLocale();
    if (DebugSettings.SHOW_DEBUG)
    {
      localMenu.add(0, 1000, 4, "Debug Settings");
      localMenu.add(0, 13, 5, "Help");
    }
    if (Settings.System.getInt(getContentResolver(), "cradle_connect", 0) != 0)
    {
      if (Settings.System.getInt(getContentResolver(), "cradle_enable", 0) != 0)
        localMenu.add(0, 9, 4, getString(2131362316)).setIcon(2130838012);
    }
    else
    {
      if (isDrivingMode() == DrivingMode.Driving)
        localMenu.add(0, 15, 4, getString(2131362312));
      if (isDrivingMode() != DrivingMode.Driving)
      {
        localMenu.add(0, 16, 4, getString(2131362313));
        localMenu.add(0, 17, 4, 2131362509);
      }
      localMenu.add(0, 1, 4, getResources().getString(2131362314)).setIcon(2130837861);
      localPopupMenu.setOnMenuItemClickListener(this);
      if ((!DebugSettings.SHOW_DEBUG) && (Settings.System.getInt(getContentResolver(), "cradle_connect", 0) == 0))
        break label270;
      localPopupMenu.show();
    }
    while (true)
    {
      return;
      localMenu.add(0, 9, 4, getString(2131362315)).setIcon(2130838009);
      break;
      label270: onMenuItemClick(localMenu.findItem(1));
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (paramConfiguration != null)
      super.onConfigurationChanged(paramConfiguration);
    this.my_orientation = getResources().getConfiguration().orientation;
    if (this.controlFragment.getCurrentState() == ControlFragment.ControlState.IDLE)
    {
      this.mhandler.removeMessages(0);
      this.mhandler.sendEmptyMessage(0);
    }
    setOrientationDrivingMode();
  }

  protected void onCreate(Bundle paramBundle)
  {
    DisplayMetrics localDisplayMetrics1 = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics1);
    if (((localDisplayMetrics1.density == 1.0D) && (localDisplayMetrics1.heightPixels + localDisplayMetrics1.widthPixels == 2032) && (localDisplayMetrics1.xdpi == 149.82489F) && (localDisplayMetrics1.ydpi == 150.51852F)) || ((localDisplayMetrics1.density == 1.0D) && (localDisplayMetrics1.heightPixels + localDisplayMetrics1.widthPixels == 2080) && (localDisplayMetrics1.xdpi == 149.82401F) && (localDisplayMetrics1.ydpi == 150.51801F)))
      mTheme = 2131296529;
    while (true)
    {
      super.onCreate(paramBundle);
      1 local1 = new ContentObserver(new Handler())
      {
        public void onChange(boolean paramBoolean)
        {
          super.onChange(paramBoolean);
          ConversationActivity.this.toggleDriveMode();
        }
      };
      this.driveObserver = local1;
      this.my_orientation = getResources().getConfiguration().orientation;
      2 local2 = new Runnable()
      {
        public void run()
        {
          ConversationActivity.this.onHomePressed();
        }
      };
      this.homeKeyDetector = HomeKeyListener.onHomePressed(local2);
      DebugSettings.SHOW_DEBUG = false;
      Intent localIntent1 = getIntent();
      super.onNewIntent(localIntent1);
      this.handleTextReqeust = false;
      if (localIntent1 != null)
      {
        String str4 = localIntent1.getAction();
        String str5 = localIntent1.getType();
        if ((!"com.vlingo.midas.action.SEND".equals(str4)) || (str5 == null) || (!IUXManager.isIUXComplete()))
          break label1759;
        if ("text/plain".equals(str5))
        {
          this.handleTextReqeust = true;
          String str6 = localIntent1.getStringExtra("android.intent.extra.TEXT");
          if (str6 != null)
            this.textRequest = str6;
          this.present_editor = localIntent1.getBooleanExtra("PRESENT_EDITOR", false);
        }
      }
      label290: BluetoothManager.BluetoothManagerInit();
      BluetoothManager.addListener(this);
      this.customTitleSupported = requestWindowFeature(8);
      DisplayMetrics localDisplayMetrics2;
      float f1;
      float f2;
      if (isDialogMode())
      {
        getWindow().clearFlags(2);
        getWindow().addFlags(17039904);
        setContentView(2130903108);
        getWindow().setBackgroundDrawableResource(2130837952);
        ActionBar localActionBar = getActionBar();
        localActionBar.setCustomView(getLayoutInflater().inflate(2130903049, null), new ActionBar.LayoutParams(-1, 40));
        localActionBar.setDisplayUseLogoEnabled(false);
        localActionBar.setDisplayOptions(0x10 ^ localActionBar.getDisplayOptions(), 16);
        localActionBar.setDisplayShowHomeEnabled(false);
        View localView = localActionBar.getCustomView();
        3 local3 = new View.OnTouchListener()
        {
          public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
          {
            int i = 1;
            Log.e("ALEX", "Touch happened");
            WindowManager.LayoutParams localLayoutParams = ConversationActivity.this.getWindow().getAttributes();
            if (paramMotionEvent.getAction() == 0)
            {
              ConversationActivity.access$602(ConversationActivity.this, paramMotionEvent.getRawX());
              ConversationActivity.access$702(ConversationActivity.this, paramMotionEvent.getRawY());
            }
            while (true)
            {
              return i;
              if (paramMotionEvent.getAction() == 2)
              {
                float f1 = paramMotionEvent.getRawX() - ConversationActivity.this.mOldX;
                float f2 = paramMotionEvent.getRawY() - ConversationActivity.this.mOldY;
                if ((Math.abs(f1) <= 3.0F) && (Math.abs(f2) <= 3.0F))
                  continue;
                localLayoutParams.x -= (int)f1;
                localLayoutParams.y -= (int)f2;
                if (ConversationActivity.this.my_orientation == 2);
                ConversationActivity.access$602(ConversationActivity.this, paramMotionEvent.getRawX());
                ConversationActivity.access$702(ConversationActivity.this, paramMotionEvent.getRawY());
                ConversationActivity.this.getWindow().setAttributes(localLayoutParams);
                continue;
              }
              i = 0;
            }
          }
        };
        localView.setOnTouchListener(local3);
        ImageButton localImageButton = (ImageButton)findViewById(2131558546);
        if (localImageButton != null)
        {
          4 local4 = new View.OnClickListener()
          {
            public void onClick(View paramView)
            {
              ConversationActivity.this.finish();
            }
          };
          localImageButton.setOnClickListener(local4);
        }
        ((ImageButton)findViewById(2131558547)).setOnClickListener(this);
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.gravity = 85;
        localLayoutParams.type = 2006;
        localDisplayMetrics2 = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics2);
        Point localPoint = new Point();
        getWindowManager().getDefaultDisplay().getSize(localPoint);
        if ((getWindowManager().getDefaultDisplay().getRotation() == 1) || (getWindowManager().getDefaultDisplay().getRotation() == 3))
        {
          f1 = 436.0F * localDisplayMetrics2.density;
          f2 = 704.0F * localDisplayMetrics2.density;
          label606: Log.d("bluechip", "width : " + f1 + ", heigth : " + f2);
          localLayoutParams.height = (int)f2;
          localLayoutParams.width = (int)f1;
          localLayoutParams.x = ((localPoint.x - localLayoutParams.width) / 2);
          localLayoutParams.y = 0;
          getWindow().setAttributes(localLayoutParams);
          label694: this.mMW = MultiWindow.createInstance(this);
        }
      }
      try
      {
        MultiWindowManager localMultiWindowManager = new MultiWindowManager(this);
        this.mMWM = localMultiWindowManager;
        label718: initFragments(Settings.isDrivingModeEnabled());
        String[] arrayOfString = new String[5];
        arrayOfString[0] = MidasADMController.AGGRESSIVE_NOISE_CANCELLATION;
        arrayOfString[1] = MidasADMController.ENDPOINT_DETECTION;
        arrayOfString[2] = MidasADMController.EYES_FREE;
        arrayOfString[3] = MidasADMController.DRIVING_MODE_GUI;
        arrayOfString[4] = MidasADMController.TALKBACK;
        ClientSuppliedValues.getADMController().addListener(this, arrayOfString);
        if ((getWindowManager().getDefaultDisplay().getRotation() == 1) || (getWindowManager().getDefaultDisplay().getRotation() == 3))
          onConfigurationChanged(null);
        MidasSettings.setBoolean("key_popup_window_opened", false);
        FacebookSSOAPI.init();
        this.asrEventLogUtil = new AsrEventLogUtil();
        this.nluEventLogUtil = new NLUEventLogUtil();
        this.startRecoBySCO = false;
        if (localIntent1 != null)
        {
          boolean bool = localIntent1.getBooleanExtra("isSecure", false);
          if (IUXManager.isIUXComplete())
          {
            if ((ClientSuppliedValues.isDrivingModeEnabled()) && (!"ACTION_SAFEREADER_LAUNCH".equals(localIntent1.getAction())) && (!"com.vlingo.midas.action.SEND".equals(localIntent1.getAction())))
              this.shouldPlayGreeting = true;
            if ((localIntent1.getAction() != null) && ((("android.intent.action.VOICE_COMMAND".equals(localIntent1.getAction())) && (!localIntent1.getBooleanExtra("isThisComeFromHomeKeyDoubleClickConcept", false))) || ("com.sec.action.SVOICE".equals(localIntent1.getAction())) || ("android.speech.action.VOICE_SEARCH_HANDS_FREE".equals(localIntent1.getAction()))))
              this.startRecoBySCO = this.controlFragment.checkMissedEvents();
          }
          if (!bool)
            doDismissKeyguard();
          if ("com.vlingo.midas.action.DEBUG_VOICE_COMMAND".equals(localIntent1.getAction()))
            this.asrEventLogUtil.enabled();
          if ("com.vlingo.midas.action.DEBUG_NLU_LOGGING".equals(localIntent1.getAction()))
            this.nluEventLogUtil.enabled();
          if ("com.vlingo.midas.action.DEBUG_ASR_AND_NLU".equals(localIntent1.getAction()))
          {
            this.asrEventLogUtil.enabled();
            this.nluEventLogUtil.enabled();
          }
        }
        afterViews();
        this.asrEventLogUtil.enabled();
        this.nluEventLogUtil.enabled();
        DrivingModeBroadcastReceiver localDrivingModeBroadcastReceiver = new DrivingModeBroadcastReceiver(null);
        this.drivingModeBroadcastReceiver = localDrivingModeBroadcastReceiver;
        CarDockBroadcastReceiver localCarDockBroadcastReceiver = new CarDockBroadcastReceiver(null);
        this.carDockBroadcastReceiver = localCarDockBroadcastReceiver;
        ScreenEventBroadcastReceiver localScreenEventBroadcastReceiver = new ScreenEventBroadcastReceiver(null);
        this.screenEventBroadcastReceiver = localScreenEventBroadcastReceiver;
        HelpWidgetBroadcastReceiver localHelpWidgetBroadcastReceiver = new HelpWidgetBroadcastReceiver(null);
        this.helpWidgetBroadcastReceiver = localHelpWidgetBroadcastReceiver;
        TimeWidgetBroadcastReceiver localTimeWidgetBroadcastReceiver = new TimeWidgetBroadcastReceiver(null);
        this.timeWidgetBroadcastReceiver = localTimeWidgetBroadcastReceiver;
        registerReceiver(this.drivingModeBroadcastReceiver, new IntentFilter("driving_mode"));
        registerReceiver(this.screenEventBroadcastReceiver, new IntentFilter("android.intent.action.SCREEN_OFF"));
        registerReceiver(this.helpWidgetBroadcastReceiver, new IntentFilter("com.vlingo.midas.gui.widgets.HelpChoiceWidget"));
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("android.intent.action.TIME_TICK");
        localIntentFilter.addAction("android.intent.action.TIME_SET");
        localIntentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        registerReceiver(this.timeWidgetBroadcastReceiver, localIntentFilter);
        if (!MidasSettings.getBoolean("samsung_wakeup_data_enable", false))
          MidasSettings.setBoolean("samsung_wakeup_data_enable", true);
        MidasSettings.setBoolean("barge_in_enabled", false);
      }
      catch (NullPointerException localNullPointerException)
      {
        try
        {
          Process localProcess1 = Runtime.getRuntime().exec("getprop persist.sys.language");
          Process localProcess2 = Runtime.getRuntime().exec("getprop persist.sys.country");
          BufferedReader localBufferedReader1 = new BufferedReader(new InputStreamReader(localProcess1.getInputStream()));
          BufferedReader localBufferedReader2 = new BufferedReader(new InputStreamReader(localProcess2.getInputStream()));
          String str2 = localBufferedReader1.readLine();
          String str3 = localBufferedReader2.readLine();
          new StringBuilder().append(str2).append("_").append(str3).toString();
          localBufferedReader1.close();
          localBufferedReader2.close();
          label1403: if ((mIsStartedCustomWakeUpSetting) || (TermsOfServiceManager.isTOSRequired()))
          {
            configureAutoStart(localIntent1);
            if ((localIntent1 != null) && ("ACTION_SAFEREADER_LAUNCH".equals(localIntent1.getAction())) && (ClientSuppliedValues.isIUXComplete()) && (processSafeReaderMessages(localIntent1)))
            {
              this.displayFromBackground = true;
              this.resumeFromBackground = true;
              Intent localIntent2 = new Intent(getApplicationContext(), VlingoApplication.getInstance().getMainActivityClass());
              localIntent2.setFlags(268435456);
              setIntent(localIntent2);
            }
            setVolumeControlStream(3);
            this.checkBoxPrefForAlert = getSharedPreferences("checkBoxPrefForAlert", 0);
            PreferenceManager.setDefaultValues(this, 2131034112, false);
            requestLocationUpdate();
            MicAnimationAdapter.addListener(this);
            if (this.mMW != null)
              MidasSettings.setMultiwindowedMode(this.mMW.isMultiWindow());
            if (!ClientSuppliedValues.isDrivingModeEnabled())
              break label1953;
            MidasSettings.setInt("widget_display_max", 3);
            if (!ClientSuppliedValues.isTalkbackOn())
              break label1964;
            if (!MidasSettings.getBoolean("manually_prompt_on_in_talkback", false))
              this.controlFragment.onPromptOnClick(false);
            VVSDispatcher.unregisterHandler("Listen");
            if ((getResources().getDisplayMetrics().heightPixels == 1920) && (getResources().getDisplayMetrics().widthPixels == 1080) && (getResources().getDisplayMetrics().density == 3.0D))
            {
              this.prefForWishesEventAlert = getSharedPreferences("prefForWishesEventAlert", 0);
              String str1 = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
              if (this.prefForWishesEventAlert.getString("IS_FIRST_LAUNCH", "").equalsIgnoreCase(str1))
                break label1976;
              GetSpecialEventDates localGetSpecialEventDates = new GetSpecialEventDates(this);
              SharedPreferences.Editor localEditor = this.prefForWishesEventAlert.edit();
              localEditor.putString("IS_FIRST_LAUNCH", str1);
              localEditor.commit();
              dayType = localGetSpecialEventDates.checkSpecialDay(str1);
            }
          }
          while (true)
          {
            label1415: label1561: label1591: NewsManager.init();
            return;
            mTheme = 2131296526;
            break;
            label1759: if (("ACTION_SAFEREADER_LAUNCH".equals(localIntent1.getAction())) && (ClientSuppliedValues.isIUXComplete()))
            {
              processSafeReaderMessages(getIntent());
              KeyguardManager localKeyguardManager = (KeyguardManager)getSystemService("keyguard");
              if ((!DrivingModeUtil.isDrivingModeEnabled(SafeReaderProxy.getContext())) || (!localKeyguardManager.inKeyguardRestrictedInputMode()))
                break label290;
              this.releaseKeyguardByMsg = true;
              break label290;
            }
            if (!localIntent1.getBooleanExtra("SETTING_VOICE_LOCK", false))
              break label290;
            mIsStartedCustomWakeUpSetting = true;
            if (!localIntent1.getBooleanExtra("IUX_CUSTOM_WAKEUP", false))
              break label290;
            this.mIsCWChange = true;
            break label290;
            f1 = 436.0F * localDisplayMetrics2.density;
            f2 = 704.0F * localDisplayMetrics2.density;
            break label606;
            requestWindowFeature(1);
            getWindow().addFlags(16778240);
            setContentView(2130903108);
            break label694;
            localNullPointerException = localNullPointerException;
            localNullPointerException.printStackTrace();
            break label718;
            ServiceManager.getInstance().startAllServices(false);
            if ((!IUXManager.requiresIUX()) && (IUXManager.isIUXComplete()))
              break label1415;
            IUXManager.processIUX(this);
            this.doneTosAndIux = true;
            break label1415;
            label1953: MidasSettings.setInt("widget_display_max", 6);
            break label1561;
            label1964: VVSDispatcher.registerHandler("Listen", ListenHandler.class);
            break label1591;
            label1976: dayType = 0;
          }
        }
        catch (IOException localIOException)
        {
          break label1403;
        }
      }
    }
  }

  public CharSequence onCreateDescription()
  {
    this.homeKeyDetector.onCreateDescription();
    return super.onCreateDescription();
  }

  protected void onDestroy()
  {
    FacebookSSOAPI.unbindFacebookService(getApplicationContext());
    BluetoothManager.removeListener(this);
    if (smCurrentActivity == this)
      smCurrentActivity = null;
    NewsManager.deinit();
    TermsOfServiceManager.dismissTempDlg();
    if ((!MidasSettings.isSamsungDisclaimerAccepted()) || (!MidasSettings.isTOSAccepted()))
      MidasSettings.setSamsungDisclaimerAccepted(false);
    latestWidget = null;
    super.onDestroy();
    this.mhandler.removeMessages(4);
    removeLocationListener();
    this.mHandy.removeMessages(0);
    DialogFlow.getInstance().releaseFlow(this);
    BluetoothManager.BluetoothManagerDestroy();
    if (this.screenEventBroadcastReceiver != null)
      unregisterReceiver(this.screenEventBroadcastReceiver);
    if (this.helpWidgetBroadcastReceiver != null)
      unregisterReceiver(this.helpWidgetBroadcastReceiver);
    if (this.timeWidgetBroadcastReceiver != null)
      unregisterReceiver(this.timeWidgetBroadcastReceiver);
    ClientSuppliedValues.getADMController().removeListener(this);
    if (this.drivingModeBroadcastReceiver != null)
      unregisterReceiver(this.drivingModeBroadcastReceiver);
    if (this.driveResolver != null)
      this.driveResolver.unregisterContentObserver(this.driveObserver);
    if (this.fullContainer == null);
    while (true)
    {
      System.gc();
      MicAnimationAdapter.removeListener(this);
      return;
      this.fullContainer.setBackgroundColor(getResources().getColor(2131230721));
    }
  }

  public void onHomePressed()
  {
    Intent localIntent = new Intent(ApplicationAdapter.getInstance().getApplicationContext(), VlingoApplicationService.class);
    localIntent.setAction("com.vlingo.client.app.action.CLOSE_APPLICATION");
    startService(localIntent);
  }

  public boolean onInterceptStartReco()
  {
    return false;
  }

  public boolean onMenuItemClick(MenuItem paramMenuItem)
  {
    onOptionsItemSelected(paramMenuItem);
    return false;
  }

  public void onMicAnimationData(int[] paramArrayOfInt)
  {
    this.controlFragment.showSpectrum(paramArrayOfInt);
  }

  public void onNewIntent(Intent paramIntent)
  {
    if ((smCurrentActivity == this) && (paramIntent.getBooleanExtra("isThisComeFromHomeKeyDoubleClickConcept", false)))
      DialogFlow.getInstance().cancelTurn();
    if (((VlingoApplication)getApplication()).isSocialLogin())
      DialogFlow.getInstance().cancelDialog();
    super.onNewIntent(paramIntent);
    String str1 = paramIntent.getAction();
    String str2 = paramIntent.getType();
    if ((!"displayFromBackground".equals(str1)) && (!"resumeFromBackground".equals(str1)))
      this.controlFragment.cancelTTS();
    if (latestWidget != null)
      latestWidget.onStop();
    this.isWakeLockChanged = false;
    if (("com.vlingo.midas.action.SEND".equals(str1)) && (str2 != null) && (IUXManager.isIUXComplete()))
      if ("text/plain".equals(str2))
        handleTextRequestIntent(paramIntent);
    while (true)
    {
      this.contentFragment.cancelAsrEditing();
      return;
      if (("ACTION_SAFEREADER_LAUNCH".equals(paramIntent.getAction())) && (ClientSuppliedValues.isIUXComplete()))
      {
        unlockScreenIfLocked();
        processSafeReaderMessages(paramIntent);
        continue;
      }
      boolean bool = paramIntent.getBooleanExtra("isSecure", false);
      if ((paramIntent.getAction() != null) && ((("android.intent.action.VOICE_COMMAND".equals(paramIntent.getAction())) && (!paramIntent.getBooleanExtra("isThisComeFromHomeKeyDoubleClickConcept", false))) || ((("com.sec.action.SVOICE".equals(paramIntent.getAction())) || ("android.speech.action.VOICE_SEARCH_HANDS_FREE".equals(paramIntent.getAction()))) && (IUXManager.isIUXComplete()))))
        this.startRecoBySCO = this.controlFragment.checkMissedEvents();
      if (!bool)
      {
        getWindow().addFlags(4194304);
        doDismissKeyguard();
      }
      acquireWakeLock(paramIntent);
      configureAutoStart(paramIntent);
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = 0;
    boolean bool = super.onContextItemSelected(paramMenuItem);
    switch (paramMenuItem.getItemId())
    {
    default:
      bool = false;
    case 1000:
    case 1001:
    case 1002:
    case 1:
    case 9:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 11:
    }
    while (true)
    {
      return bool;
      startActivity(new Intent(this, DebugSettings.class).addFlags(67108864));
      bool = true;
      continue;
      bool = true;
      continue;
      bool = true;
      continue;
      if (isDialogMode())
        showPreferences();
      while (true)
      {
        bool = true;
        break;
        Intent localIntent2 = new Intent(this, SettingsScreen.class);
        localIntent2.putExtra("is_start_option_menu", true);
        startActivity(localIntent2);
      }
      if (Settings.System.getInt(getContentResolver(), "cradle_enable", 0) != 0);
      while (true)
      {
        Settings.System.putInt(getContentResolver(), "cradle_enable", i);
        Intent localIntent1 = new Intent();
        localIntent1.setAction("com.sec.android.intent.action.INTERNAL_SPEAKER");
        localIntent1.putExtra("state", i);
        sendBroadcast(localIntent1);
        bool = true;
        break;
        i = 1;
      }
      showHelp();
      continue;
      addHelpChoiceWidget();
      continue;
      Settings.System.putInt(getContentResolver(), "driving_mode_on", 0);
      sendBroadcast(new Intent("DrivingMode"));
      continue;
      Settings.System.putInt(getContentResolver(), "driving_mode_on", 1);
      sendBroadcast(new Intent("DrivingMode"));
      continue;
      if (isDialogMode())
        showPreferences();
      while (true)
      {
        bool = true;
        break;
        startActivity(new Intent(this, WhatCanISayScreen.class));
      }
      DrivingModeUtil.toggleDrivingMode(this);
      bool = true;
    }
  }

  protected void onPause()
  {
    this.homeKeyDetector.onPause();
    this.controlFragment.setTaskOnFinishGreetingTTS(null);
    AudioPlayerProxy.removeListener(this);
    AudioPlayerProxy.stop();
    if (this.startedWithCarDock)
      unregisterReceiver(this.carDockBroadcastReceiver);
    super.onPause();
    this.mHandy.removeMessages(0);
    this.mhandler.removeMessages(2);
    this.mhandler.removeMessages(1);
    this.mhandler.removeMessages(0);
    this.displayFromBackground = false;
    this.resumeFromBackground = false;
    this.autoStartOnResume = false;
    if ((smCurrentActivity == this) && (!this.releaseKeyguardByMsg))
      DialogFlow.getInstance().cancelTurn();
    this.releaseKeyguardByMsg = false;
    if (mIsStartedCustomWakeUpSetting)
      finish();
    if ((!this.doneTosAndIux) && (mIsStartedCustomWakeUpSetting))
      mIsStartedCustomWakeUpSetting = false;
    getWakeupLock(false);
    setControlFragmentActivityPaused(true);
    RegisterSoundPool.destroy();
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    boolean bool = super.onPrepareOptionsMenu(paramMenu);
    if (isDialogMode())
      return bool;
    MidasSettings.updateCurrentLocale();
    if (DebugSettings.SHOW_DEBUG)
    {
      paramMenu.add(0, 1000, 4, "Debug Settings");
      paramMenu.add(0, 13, 5, 2131362269);
    }
    if (Settings.System.getInt(getContentResolver(), "cradle_connect", 0) != 0)
    {
      if (Settings.System.getInt(getContentResolver(), "cradle_enable", 0) == 0)
        break label240;
      paramMenu.add(0, 9, 4, getString(2131362316)).setIcon(2130838012);
    }
    while (true)
    {
      if (isDrivingMode() == DrivingMode.Driving)
      {
        paramMenu.add(0, 15, 4, getString(2131362312)).setIcon(2130838008);
        paramMenu.add(0, 17, 4, 2131362509).setIcon(2130838011);
      }
      if (isDrivingMode() != DrivingMode.Driving)
        paramMenu.add(0, 16, 4, getString(2131362313)).setIcon(2130838008);
      paramMenu.add(0, 1, 4, getResources().getString(2131362314)).setIcon(2130837861);
      bool = true;
      break;
      label240: paramMenu.add(0, 9, 4, getString(2131362315)).setIcon(2130838009);
    }
  }

  public void onRecoCancelled()
  {
    if (this.controlFragment.getControlFragmentState() != ControlFragment.ControlState.IDLE)
      showDialogFlowStateChange(DialogFlow.DialogFlowState.BUSY);
  }

  public long onRecoToneStarting()
  {
    long l;
    if (this.controlFragment == this.drivingControlFragment)
      l = 250L;
    while (true)
    {
      return l;
      l = 100L;
    }
  }

  public void onRequestCancelled(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled paramReasonCanceled)
  {
    if (latestWidget != null)
      latestWidget.onRequestCancelled(paramAudioRequest, paramReasonCanceled);
    if (FacebookSSOAPI.facebookSSO())
      FacebookSSOAPI.actionCleanUp();
  }

  public void onRequestDidPlay(AudioRequest paramAudioRequest)
  {
    if (latestWidget != null)
      latestWidget.onRequestDidPlay(paramAudioRequest);
    if ((FacebookSSOAPI.facebookSSO()) && (FacebookSSOAPI.getFbUpdateType() == FacebookSSOAPI.FbUpdateType.FACEBOOK_ONLY))
      this.mhandler.sendEmptyMessageDelayed(6, 100L);
  }

  public void onRequestIgnored(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored paramReasonIgnored)
  {
    if (latestWidget != null)
      latestWidget.onRequestIgnored(paramAudioRequest, paramReasonIgnored);
    if (FacebookSSOAPI.facebookSSO())
      FacebookSSOAPI.actionCleanUp();
  }

  public void onRequestWillPlay(AudioRequest paramAudioRequest)
  {
    if (latestWidget != null)
      latestWidget.onRequestWillPlay(paramAudioRequest);
  }

  protected void onRestart()
  {
    super.onRestart();
  }

  public void onRestoreInstanceState(Bundle paramBundle)
  {
    unpackSavedInstanceState(paramBundle);
    super.onRestoreInstanceState(paramBundle);
  }

  public void onResultsNoAction()
  {
    this.asrEventLogUtil.onRecognitionResult(null);
    this.nluEventLogUtil.onRecognitionResult(null);
  }

  protected void onResume()
  {
    setControlFragmentActivityPaused(false);
    AudioFocusManager.getInstance(getBaseContext()).abandonAudioFocus();
    HashMap localHashMap = generateConfigPairs();
    if (!this.displayFromBackground)
    {
      DialogFlow localDialogFlow2 = DialogFlow.getInstance();
      WidgetBuilder localWidgetBuilder2 = new WidgetBuilder(this);
      localDialogFlow2.initFlow(this, this, localHashMap, localWidgetBuilder2);
    }
    while (true)
    {
      if (this.shouldPlayGreeting)
        promptUser(greetingTTS());
      label95: boolean bool;
      if (!MidasSettings.isSamsungDisclaimerAccepted())
      {
        6 local6 = new Runnable()
        {
          public void run()
          {
            ConversationActivity.this.showTOS();
          }
        };
        ActivityUtil.scheduleOnMainThread(local6, 120L);
        this.doneTosAndIux = true;
        this.driveResolver = getContentResolver();
        this.driveResolver.registerContentObserver(DrivingModeUtil.getUri(), false, this.driveObserver);
        smCurrentActivity = this;
        MidasSettings.updateCurrentLocale();
        super.onResume();
        if (this.mMW != null)
          MidasSettings.setMultiwindowedMode(this.mMW.isMultiWindow());
        RegisterSoundPool.init(getApplicationContext());
        bool = DrivingModeUtil.isDrivingModeEnabled(this);
        if ((!IUXManager.isIUXComplete()) || (isFinishing()));
      }
      try
      {
        if ((this.autoStartOnResume) && (((BluetoothManager.isHeadsetConnected()) && (!bool)) || (this.startRecoBySCO)))
        {
          local7 = new Runnable()
          {
            public void run()
            {
              if (VlingoApplication.getInstance().isAppInForeground())
                BluetoothManager.onAppStateChanged(true);
            }
          };
          if (this.shouldPlayGreeting)
            this.controlFragment.setTaskOnFinishGreetingTTS(local7);
        }
        else
        {
          this.shouldPlayGreeting = false;
          acquireWakeLock(getIntent());
          AudioPlayerProxy.addListener(this);
          DialogFlow.getInstance().sendPendingEvents();
          this.contentFragment.removeWakeupBubble();
          if ((!Settings.isDrivingModeEnabled()) || (this.drivingControlFragment == null))
            break label592;
          this.drivingControlFragment.setCallback(this.controlFragmentCallback);
          checkCarDockConnection();
          if (IUXManager.isIUXComplete())
          {
            if (!this.handleTextReqeust)
              break label655;
            handleTextRequestIntent(null);
          }
          checkFragmentLanguage();
          if (getCarDock() == true)
          {
            getWindow().addFlags(128);
            Log.d("isKeepScreen", "FLAG_KEEP_SCREEN_ON for CarDock");
            scheduleScreenSaver();
          }
          LinearLayout localLinearLayout = (LinearLayout)findViewById(2131558495);
          setOrientationDrivingMode();
          if (localLinearLayout != null)
            localLinearLayout.invalidate();
          if (this.contentFragment.isCalledOnConfigrationChanged())
            break label751;
          if (this.contentFragment.getEnabledHWR())
          {
            this.contentFragment.finishHWR();
            this.contentFragment.setHWRButtonOff();
          }
          if ((this.messages != null) && (!this.messages.isEmpty()))
          {
            LinkedList localLinkedList = new LinkedList(this.messages);
            this.messages = null;
            DialogFlow.getInstance().handleAlert(localLinkedList);
          }
          ((VlingoApplication)getApplication()).setSocialLogin(false);
        }
      }
      catch (Exception localException2)
      {
        try
        {
          while (true)
          {
            7 local7;
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            Field localField1 = localLayoutParams.getClass().getField("privateFlags");
            Field localField2 = localLayoutParams.getClass().getField("PRIVATE_FLAG_DISABLE_MULTI_WINDOW_TRAY_BAR");
            localField1.setInt(localLayoutParams, localField1.getInt(localLayoutParams) | localField2.getInt(localLayoutParams));
            getWindow().setAttributes(localLayoutParams);
            label515: if (isDrivingMode() == DrivingMode.Driving)
              getWakeupLock(true);
            Debug.stopMethodTracing();
            return;
            DialogFlow localDialogFlow1 = DialogFlow.getInstance();
            WidgetBuilder localWidgetBuilder1 = new WidgetBuilder(this);
            localDialogFlow1.stealFlow(this, this, localHashMap, localWidgetBuilder1);
            break;
            MidasSettings.languageSupportListCheck();
            break label95;
            AudioFocusManager.getInstance(this).setTaskOnGetAudioFocus(6, 2, local7);
            this.controlFragment.setTaskOnFinishGreetingTTS(null);
            continue;
            localException2 = localException2;
            continue;
            label592: if ((getResources().getConfiguration().orientation == 1) || (isDialogMode()))
            {
              this.regularControlFragment.setCallback(this.controlFragmentCallback);
              continue;
            }
            if (getResources().getConfiguration().orientation != 2)
              continue;
            this.landRegularControlFragment.setCallback(this.controlFragmentCallback);
            continue;
            label655: if (this.autoStartOnResume)
            {
              if (this.startRecoBySCO)
                continue;
              if ((BluetoothManager.isHeadsetConnected()) && (!bool))
              {
                AudioFocusManager.getInstance(this).setTaskOnGetAudioFocus(6, 2, new Runnable()
                {
                  public void run()
                  {
                    ConversationActivity.this.mhandler.removeMessages(2);
                    if ((!ClientSuppliedValues.isTalkbackOn()) && (!ClientSuppliedValues.isDrivingModeEnabled()))
                      ConversationActivity.this.mhandler.sendEmptyMessage(2);
                  }
                });
                continue;
              }
              this.mhandler.removeMessages(2);
              if ((ClientSuppliedValues.isTalkbackOn()) || (ClientSuppliedValues.isDrivingModeEnabled()))
                continue;
              this.mhandler.sendEmptyMessage(2);
              continue;
            }
            if (!this.displayFromBackground)
              continue;
            onResumeFromBackground(this.resumeFromBackground);
          }
          label751: this.contentFragment.startHWR();
          this.contentFragment.setCalledOnConfigrationChanged(false);
        }
        catch (Exception localException1)
        {
          while (true)
            localException1.printStackTrace();
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          break label515;
        }
      }
    }
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putBoolean("startedWithCarDock", this.startedWithCarDock);
    paramBundle.putBoolean("autoStartOnResume", this.autoStartOnResume);
    super.onSaveInstanceState(paramBundle);
  }

  public void onScoConnected()
  {
    setVolumeControlStream(6);
  }

  public void onScoDisconnected()
  {
    setVolumeControlStream(3);
    if (BluetoothManager.appCloseType() == BluetoothManager.CloseType.CALL)
      finish();
  }

  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return;
      if ("language".equals(paramString))
      {
        this.contentFragment.resetAllContent();
        continue;
      }
    }
  }

  protected void onStart()
  {
    super.onStart();
    VlingoApplication.getInstance().setIsInForeground(true);
    if ((ClientSuppliedValues.shouldIncomingMessagesReadout()) && (IUXManager.isIUXComplete()))
    {
      ClientSuppliedValues.getForegroundFocus(DialogFlow.getInstance());
      SafeReaderProxy.startSafeReading();
    }
    sendBroadcast(new Intent("seamless_pause"));
    this.mhandler.removeMessages(3);
    new Thread()
    {
      public void run()
      {
        FacebookSSOAPI.bindFacebookService(ConversationActivity.this.getApplicationContext());
        ContactDBUtil.populateContactMapping(ConversationActivity.this.getApplicationContext());
        SearchMusic.populateMusicMappings(ConversationActivity.this.getApplicationContext());
      }
    }
    .start();
  }

  public void onStatusChanged(String paramString, boolean paramBoolean)
  {
    if (paramString.equals(MidasADMController.AGGRESSIVE_NOISE_CANCELLATION))
      if (!paramBoolean);
    while (true)
    {
      return;
      if (paramString.equals(MidasADMController.ENDPOINT_DETECTION))
      {
        if (!paramBoolean)
          continue;
        continue;
      }
      if (paramString.equals(MidasADMController.EYES_FREE))
      {
        if (paramBoolean)
        {
          DialogFlow.getInstance().removeUserProperties("isEyesFree");
          DialogFlow.getInstance().addUserProperties("isEyesFree", Boolean.TRUE.toString().toLowerCase(MidasSettings.getCurrentLocale()));
          MidasSettings.setBoolean("is_eyes_free_mode", true);
          continue;
        }
        DialogFlow.getInstance().removeUserProperties("isEyesFree");
        DialogFlow.getInstance().addUserProperties("isEyesFree", Boolean.FALSE.toString().toLowerCase(MidasSettings.getCurrentLocale()));
        MidasSettings.setBoolean("is_eyes_free_mode", false);
        continue;
      }
      if (paramString.equals(MidasADMController.DRIVING_MODE_GUI))
      {
        if (paramBoolean)
        {
          MidasSettings.setInt("widget_display_max", 3);
          continue;
        }
        MidasSettings.setInt("widget_display_max", 6);
        continue;
      }
      if (!paramString.equals(MidasADMController.TALKBACK))
        continue;
      if (paramBoolean)
      {
        this.controlFragment.onPromptOnClick(false);
        VVSDispatcher.unregisterHandler("Listen");
        continue;
      }
      this.controlFragment.onPromptOffClick(false);
      VVSDispatcher.registerHandler("Listen", ListenHandler.class);
    }
  }

  protected void onStop()
  {
    sendBroadcast(new Intent("seamless_resume"));
    if ((this.tosDialog != null) && (this.tosDialog.isShowing()))
      this.tosDialog.dismiss();
    super.onStop();
    VlingoApplication.getInstance().setIsInForeground(false);
    if (!ClientSuppliedValues.shouldIncomingMessagesReadout())
    {
      ClientSuppliedValues.releaseForegroundFocus(DialogFlow.getInstance());
      SafeReaderProxy.stopSafeReading();
    }
    this.controlFragment.cancelTTS();
    this.mhandler.sendEmptyMessageDelayed(3, 300L);
    if (latestWidget != null)
      latestWidget.onStop();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.gestures != null);
    for (boolean bool = this.gestures.onTouchEvent(paramMotionEvent); ; bool = false)
      return bool;
  }

  public void onTrimMemory(int paramInt)
  {
    super.onTrimMemory(paramInt);
    this.homeKeyDetector.onTrimMemory(paramInt);
  }

  public void onUserInteraction()
  {
    this.mHandy.removeMessages(0);
    if (getCarDock() == true)
      scheduleScreenSaver();
  }

  protected void onUserLeaveHint()
  {
    this.homeKeyDetector.onUserLeaveHint();
    super.onUserLeaveHint();
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if ((paramBoolean) && (this.controlFragment.getControlFragmentState() == ControlFragment.ControlState.IDLE) && (!BluetoothManager.isHeadsetConnected()) && (!Settings.getBoolean("key_social_login_attemp_for_user_leave_hint", false)))
    {
      PhraseSpotter.getInstance().stopPhraseSpotting();
      this.mhandler.removeMessages(1);
      this.mhandler.sendEmptyMessageDelayed(1, 700L);
    }
    while (true)
    {
      return;
      this.mhandler.removeMessages(1);
      Settings.setBoolean("key_social_login_attemp_for_user_leave_hint", false);
    }
  }

  public void onWindowStatusChanged(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    String str1 = new String();
    String str2 = str1 + " / isMaximized : " + paramBoolean1;
    String str3 = str2 + " / isMinimized : " + paramBoolean2;
    String str4 = str3 + " / isPinup : " + paramBoolean3;
    Log.d("Hello", "onWindowStatusChanged is called : " + str4);
    this.drivingFragment.setMultiwindow(paramBoolean1);
    if (this.mMW != null)
    {
      if ((!MidasSettings.isMultiwindowedMode()) && (this.mMW.isMultiWindow()))
      {
        Intent localIntent = new Intent("com.sec.android.action.CONTROL_BAR_FIX");
        localIntent.putExtra("com.sec.android.extra.CONTROL_BAR_FIX_ENABLE", true);
        localIntent.putExtra("com.sec.android.extra.CONTROL_BAR_FIX_CALLER", getTaskId());
        sendBroadcast(localIntent);
      }
      MidasSettings.setMultiwindowedMode(this.mMW.isMultiWindow());
    }
  }

  public void promptUser(String paramString)
  {
    this.controlFragment.setState(ControlFragment.ControlState.IDLE);
    this.controlFragment.announceTTS(paramString);
    showVlingoText(paramString);
  }

  // ERROR //
  public void pushData()
  {
    // Byte code:
    //   0: new 2464	java/io/File
    //   3: dup
    //   4: ldc_w 2466
    //   7: invokespecial 2467	java/io/File:<init>	(Ljava/lang/String;)V
    //   10: astore_1
    //   11: aconst_null
    //   12: astore_2
    //   13: sipush 1024
    //   16: newarray byte
    //   18: astore_3
    //   19: new 2469	java/util/zip/ZipInputStream
    //   22: dup
    //   23: aload_0
    //   24: invokevirtual 1035	com/vlingo/midas/gui/ConversationActivity:getApplicationContext	()Landroid/content/Context;
    //   27: invokevirtual 2473	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   30: ldc_w 2475
    //   33: invokevirtual 2481	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   36: invokespecial 2482	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   39: astore 4
    //   41: aload 4
    //   43: invokevirtual 2486	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   46: astore 9
    //   48: aload 9
    //   50: ifnull +262 -> 312
    //   53: new 811	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 812	java/lang/StringBuilder:<init>	()V
    //   60: aload_1
    //   61: invokevirtual 2489	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   64: invokevirtual 818	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: getstatic 2492	java/io/File:separator	Ljava/lang/String;
    //   70: invokevirtual 818	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: aload 9
    //   75: invokevirtual 2497	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   78: invokevirtual 818	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 822	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: astore 11
    //   86: aload 9
    //   88: invokevirtual 2497	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   91: ldc_w 2499
    //   94: invokevirtual 2502	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   97: bipush 255
    //   99: if_icmpeq +81 -> 180
    //   102: new 2464	java/io/File
    //   105: dup
    //   106: aload 11
    //   108: invokespecial 2467	java/io/File:<init>	(Ljava/lang/String;)V
    //   111: invokevirtual 2506	java/io/File:getParentFile	()Ljava/io/File;
    //   114: astore 18
    //   116: aload 18
    //   118: invokevirtual 2509	java/io/File:exists	()Z
    //   121: ifne +59 -> 180
    //   124: aload 18
    //   126: invokevirtual 2512	java/io/File:mkdirs	()Z
    //   129: ifne +51 -> 180
    //   132: new 1521	java/io/IOException
    //   135: dup
    //   136: new 811	java/lang/StringBuilder
    //   139: dup
    //   140: invokespecial 812	java/lang/StringBuilder:<init>	()V
    //   143: ldc_w 2514
    //   146: invokevirtual 818	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: aload 18
    //   151: invokevirtual 2517	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   154: invokevirtual 822	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   157: invokespecial 2518	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   160: athrow
    //   161: astore 7
    //   163: aload 4
    //   165: astore_2
    //   166: aload 7
    //   168: invokevirtual 2519	java/io/IOException:printStackTrace	()V
    //   171: aload_2
    //   172: ifnull +7 -> 179
    //   175: aload_2
    //   176: invokevirtual 2520	java/util/zip/ZipInputStream:close	()V
    //   179: return
    //   180: aconst_null
    //   181: astore 12
    //   183: new 2522	java/io/FileOutputStream
    //   186: dup
    //   187: aload 11
    //   189: invokespecial 2523	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   192: astore 13
    //   194: aload 4
    //   196: aload_3
    //   197: invokevirtual 2527	java/util/zip/ZipInputStream:read	([B)I
    //   200: istore 16
    //   202: iload 16
    //   204: bipush 255
    //   206: if_icmpeq +27 -> 233
    //   209: aload 13
    //   211: aload_3
    //   212: iconst_0
    //   213: iload 16
    //   215: invokevirtual 2531	java/io/FileOutputStream:write	([BII)V
    //   218: aload 4
    //   220: aload_3
    //   221: invokevirtual 2527	java/util/zip/ZipInputStream:read	([B)I
    //   224: istore 17
    //   226: iload 17
    //   228: istore 16
    //   230: goto -28 -> 202
    //   233: aload 13
    //   235: ifnull +8 -> 243
    //   238: aload 13
    //   240: invokevirtual 2532	java/io/FileOutputStream:close	()V
    //   243: aload 4
    //   245: invokevirtual 2535	java/util/zip/ZipInputStream:closeEntry	()V
    //   248: goto -207 -> 41
    //   251: astore 14
    //   253: aload 14
    //   255: invokevirtual 2341	java/lang/Exception:printStackTrace	()V
    //   258: aload 12
    //   260: ifnull +8 -> 268
    //   263: aload 12
    //   265: invokevirtual 2532	java/io/FileOutputStream:close	()V
    //   268: aload 4
    //   270: invokevirtual 2535	java/util/zip/ZipInputStream:closeEntry	()V
    //   273: goto -232 -> 41
    //   276: astore 5
    //   278: aload 4
    //   280: astore_2
    //   281: aload_2
    //   282: ifnull +7 -> 289
    //   285: aload_2
    //   286: invokevirtual 2520	java/util/zip/ZipInputStream:close	()V
    //   289: aload 5
    //   291: athrow
    //   292: astore 15
    //   294: aload 12
    //   296: ifnull +8 -> 304
    //   299: aload 12
    //   301: invokevirtual 2532	java/io/FileOutputStream:close	()V
    //   304: aload 4
    //   306: invokevirtual 2535	java/util/zip/ZipInputStream:closeEntry	()V
    //   309: aload 15
    //   311: athrow
    //   312: aload 4
    //   314: ifnull +8 -> 322
    //   317: aload 4
    //   319: invokevirtual 2520	java/util/zip/ZipInputStream:close	()V
    //   322: goto -143 -> 179
    //   325: astore 10
    //   327: aload 10
    //   329: invokevirtual 2519	java/io/IOException:printStackTrace	()V
    //   332: goto -153 -> 179
    //   335: astore 8
    //   337: aload 8
    //   339: invokevirtual 2519	java/io/IOException:printStackTrace	()V
    //   342: goto -163 -> 179
    //   345: astore 6
    //   347: aload 6
    //   349: invokevirtual 2519	java/io/IOException:printStackTrace	()V
    //   352: goto -63 -> 289
    //   355: astore 5
    //   357: goto -76 -> 281
    //   360: astore 7
    //   362: goto -196 -> 166
    //   365: astore 15
    //   367: aload 13
    //   369: astore 12
    //   371: goto -77 -> 294
    //   374: astore 14
    //   376: aload 13
    //   378: astore 12
    //   380: goto -127 -> 253
    //
    // Exception table:
    //   from	to	target	type
    //   41	161	161	java/io/IOException
    //   238	248	161	java/io/IOException
    //   263	273	161	java/io/IOException
    //   299	312	161	java/io/IOException
    //   183	194	251	java/lang/Exception
    //   41	161	276	finally
    //   238	248	276	finally
    //   263	273	276	finally
    //   299	312	276	finally
    //   183	194	292	finally
    //   253	258	292	finally
    //   317	322	325	java/io/IOException
    //   175	179	335	java/io/IOException
    //   285	289	345	java/io/IOException
    //   19	41	355	finally
    //   166	171	355	finally
    //   19	41	360	java/io/IOException
    //   194	226	365	finally
    //   194	226	374	java/lang/Exception
  }

  public void saveScreen()
  {
    startActivity(new Intent(this, SaveScreen.class));
  }

  public void setControlFragmentActivityPaused(boolean paramBoolean)
  {
    if (this.landRegularControlFragment != null)
      this.landRegularControlFragment.setActivityPaused(paramBoolean);
    if (this.drivingControlFragment != null)
      this.drivingControlFragment.setActivityPaused(paramBoolean);
    if (this.regularControlFragment != null)
      this.regularControlFragment.setActivityPaused(paramBoolean);
  }

  protected void setOrientationDrivingMode()
  {
    ControlFragment.ControlState localControlState = this.controlFragment.getCurrentState();
    changeToDriveMode(Settings.isDrivingModeEnabled());
    this.controlFragment.setState(localControlState);
  }

  public void showControlFragment()
  {
    getSupportFragmentManager().beginTransaction().show(this.controlFragment).commitAllowingStateLoss();
  }

  public void showDialogFlowStateChange(DialogFlow.DialogFlowState paramDialogFlowState)
  {
    switch (17.$SwitchMap$com$vlingo$core$internal$dialogmanager$DialogFlow$DialogFlowState[paramDialogFlowState.ordinal()])
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      return;
      this.controlFragment.setState(ControlFragment.ControlState.IDLE);
      this.contentFragment.onIdle();
      if (this.drivingFragment == null)
        continue;
      this.drivingFragment.setGreetingText("");
      continue;
      this.contentFragment.removeWakeupBubble();
      this.mHandy.removeMessages(0);
      this.controlFragment.setState(ControlFragment.ControlState.DIALOG_BUSY);
    }
  }

  public void showError(VLRecognitionErrors paramVLRecognitionErrors, String paramString)
  {
    String str = ErrorCodeUtils.getLocalizedMessageForErrorCode(paramVLRecognitionErrors, this);
    if (shouldReportError(paramVLRecognitionErrors))
    {
      this.contentFragment.addDialogBubble(DialogBubble.BubbleType.ERROR, str, true, false);
      this.controlFragment.announceTTS(str);
    }
    this.controlFragment.setState(ControlFragment.ControlState.ASR_THINKING);
    this.asrEventLogUtil.onRecognitionError();
  }

  public void showHelp()
  {
    startActivity(new Intent(this, HelpScreen.class));
  }

  void showPreferences()
  {
    Intent localIntent = new Intent(this, SettingsScreen.class);
    localIntent.putExtra("is_start_option_menu", true);
    startActivity(localIntent);
  }

  public void showRMSChange(int paramInt)
  {
    if (isDrivingMode() == DrivingMode.Driving)
      this.controlFragment.setBlueHeight(paramInt);
  }

  public void showReceivedResults(EventLog paramEventLog)
  {
    this.controlFragment.setState(ControlFragment.ControlState.ASR_POST_RESPONSE);
    if (paramEventLog != null)
    {
      this.asrEventLogUtil.onRecognitionResult(paramEventLog.getAsrLogMessage());
      this.nluEventLogUtil.onRecognitionResult(paramEventLog.getNluLogMessage());
    }
  }

  public void showRecoStateChange(VLRecognitionStates paramVLRecognitionStates)
  {
    switch (17.$SwitchMap$com$vlingo$sdk$recognition$VLRecognitionStates[paramVLRecognitionStates.ordinal()])
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      return;
      this.radioflag = false;
      this.contentFragment.removeWakeupBubble();
      this.mHandy.removeMessages(0);
      this.controlFragment.setState(ControlFragment.ControlState.ASR_LISTENING);
      if (this.drivingFragment != null)
        this.drivingFragment.setGreetingText(getResources().getString(2131362358));
      this.asrEventLogUtil.onRecognitionStart();
      continue;
      this.mHandy.removeMessages(0);
      this.controlFragment.setState(ControlFragment.ControlState.ASR_THINKING);
      if (this.drivingFragment != null)
        this.drivingFragment.setGreetingText(getResources().getString(2131362757));
      if (getCarDock() == true)
        scheduleScreenSaver();
      this.asrEventLogUtil.onRecognitionComplete();
    }
  }

  protected void showSettings()
  {
  }

  protected void showTOS()
  {
    this.prefForWishesEventAlert = getSharedPreferences("prefForWishesEventAlert", 0);
    SharedPreferences.Editor localEditor = this.prefForWishesEventAlert.edit();
    localEditor.putString("IS_FIRST_LAUNCH", "0000-00-00");
    localEditor.commit();
    this.tosDialog = TermsOfServiceManager.getTOSDialog(this, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        MidasSettings.setLocationEnabled(true);
        VlingoVoiceRecognitionService.enable();
        ServiceManager.getInstance().startAllServices(false);
        if (IUXManager.requiresIUX())
          IUXManager.processIUX(ConversationActivity.this);
      }
    }
    , new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        ConversationActivity.this.stopService(new Intent("com.vlingo.midas.SeamlessRecognition"));
        ConversationActivity.this.finish();
      }
    }
    , new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramDialogInterface)
      {
        ConversationActivity.this.stopService(new Intent("com.vlingo.midas.SeamlessRecognition"));
        ConversationActivity.this.finish();
      }
    });
    this.tosDialog.show();
  }

  public void showUserText(String paramString, NBestData paramNBestData)
  {
    retireLastWidget();
    MidasSettings.setString("last_asr_result", paramString);
    Log.d("getFieldID", "conversationactivity-showUserText " + DialogFlow.getInstance().getFieldID().toString() + "  " + paramString);
    DialogBubble localDialogBubble = this.contentFragment.addDialogBubble(DialogBubble.BubbleType.USER, paramString, true, false);
    if (localDialogBubble != null)
      localDialogBubble.setTag(DialogFlow.getInstance().getPreservedState());
  }

  public void showVlingoText(String paramString)
  {
    Log.d("getFieldID", "conversationactivity-showVlingoText  " + DialogFlow.getInstance().getFieldID().toString() + "  " + paramString);
    if (isDrivingMode() == DrivingMode.Driving);
    while (true)
    {
      if (PhraseSpotter.getInstance().isListening())
      {
        this.mhandler.removeMessages(0);
        this.mhandler.sendEmptyMessage(0);
      }
      return;
      this.contentFragment.addDialogBubble(DialogBubble.BubbleType.VLINGO, paramString, true, false);
    }
  }

  public void showWarning(VLRecognitionWarnings paramVLRecognitionWarnings, String paramString)
  {
    if (paramVLRecognitionWarnings != VLRecognitionWarnings.WARNING_NOTHING_RECOGNIZED)
    {
      this.contentFragment.addDialogBubble(DialogBubble.BubbleType.WARNING, paramString, true, false);
      this.controlFragment.announceTTS(paramString);
    }
  }

  public void startActivity(Intent paramIntent)
  {
    this.homeKeyDetector.startActivity(paramIntent);
    super.startActivity(paramIntent);
  }

  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    try
    {
      this.homeKeyDetector.startActivityForResult(paramIntent, paramInt);
      super.startActivityForResult(paramIntent, paramInt);
      label15: return;
    }
    catch (Exception localException)
    {
      break label15;
    }
  }

  public void startActivityFromChild(Activity paramActivity, Intent paramIntent, int paramInt)
  {
    try
    {
      this.homeKeyDetector.startActivityFromChild(paramActivity, paramIntent, paramInt);
      super.startActivityFromChild(paramActivity, paramIntent, paramInt);
      label17: return;
    }
    catch (Exception localException)
    {
      break label17;
    }
  }

  public void startActivityFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt)
  {
    try
    {
      this.homeKeyDetector.startActivityFromFragment(paramFragment, paramIntent, paramInt);
      super.startActivityFromFragment(paramFragment, paramIntent, paramInt);
      label17: return;
    }
    catch (Exception localException)
    {
      break label17;
    }
  }

  public boolean startActivityIfNeeded(Intent paramIntent, int paramInt)
  {
    try
    {
      this.homeKeyDetector.startActivityIfNeeded(paramIntent, paramInt);
      boolean bool2 = super.startActivityIfNeeded(paramIntent, paramInt);
      bool1 = bool2;
      return bool1;
    }
    catch (Exception localException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public void toggleDriveMode()
  {
    if (Settings.isDrivingModeEnabled());
    for (boolean bool = true; ; bool = false)
    {
      ControlFragment.ControlState localControlState = this.controlFragment.getCurrentState();
      changeToDriveMode(bool);
      this.controlFragment.setState(localControlState);
      return;
    }
  }

  public void userCancel()
  {
    this.contentFragment.onUserCancel();
  }

  private class CarDockBroadcastReceiver extends BroadcastReceiver
  {
    private CarDockBroadcastReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getIntExtra("android.intent.extra.DOCK_STATE", 0) == 0)
      {
        ConversationActivity.this.mHandy.removeMessages(0);
        ConversationActivity.this.getWindow().clearFlags(128);
        ConversationActivity.this.check_CarDock(false);
        if (ConversationActivity.this.startWithCarkitInNormal)
        {
          Settings.System.putInt(ConversationActivity.this.getContentResolver(), "driving_mode_on", 0);
          ConversationActivity.this.sendBroadcast(new Intent("DrivingMode"));
          ConversationActivity.access$1902(ConversationActivity.this, false);
        }
        ConversationActivity.this.finish();
      }
      while (true)
      {
        return;
        if (2 == paramIntent.getIntExtra("android.intent.extra.DOCK_STATE", 2))
        {
          ConversationActivity.this.getWindow().addFlags(128);
          Log.d("isKeepScreen", "FLAG_KEEP_SCREEN_ON for CarDock");
          ConversationActivity.this.check_CarDock(true);
          ConversationActivity.this.scheduleScreenSaver();
          continue;
        }
      }
    }
  }

  private static class ConversationActivityHandler2 extends Handler
  {
    private final WeakReference<ConversationActivity> outer;

    ConversationActivityHandler2(ConversationActivity paramConversationActivity)
    {
      this.outer = new WeakReference(paramConversationActivity);
    }

    public void handleMessage(Message paramMessage)
    {
      ConversationActivity localConversationActivity = (ConversationActivity)this.outer.get();
      if ((localConversationActivity != null) && (paramMessage.what == 0))
        localConversationActivity.saveScreen();
    }
  }

  private static class ConversationActivityHandler extends Handler
  {
    private final WeakReference<ConversationActivity> outer;

    ConversationActivityHandler(ConversationActivity paramConversationActivity)
    {
      this.outer = new WeakReference(paramConversationActivity);
    }

    public void handleMessage(Message paramMessage)
    {
      ConversationActivity localConversationActivity = (ConversationActivity)this.outer.get();
      if (localConversationActivity != null)
        switch (paramMessage.what)
        {
        default:
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        }
      while (true)
      {
        return;
        if (!Settings.getBoolean("car_word_spotter_enabled", true))
          continue;
        Configuration localConfiguration = localConversationActivity.getResources().getConfiguration();
        localConfiguration.locale = MidasSettings.getCurrentLocale();
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        localConversationActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        new Resources(localConversationActivity.getAssets(), localDisplayMetrics, localConfiguration);
        if ((!BluetoothManager.isHeadsetConnected()) || (!BluetoothManager.isBluetoothAudioSupported()))
        {
          String str1 = localConversationActivity.getString(2131362211);
          String str2 = localConversationActivity.getString(2131362227);
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = localConversationActivity.getString(2131362604);
          String str3 = String.format(str2, arrayOfObject);
          if (MidasSettings.getBoolean("samsung_wakeup_engine_enable", false))
          {
            str1 = localConversationActivity.getString(2131362179);
            str3 = localConversationActivity.getString(2131362228);
          }
          localConversationActivity.contentFragment.addDialogBubble(DialogBubble.BubbleType.WAKE_UP, str1, true, false);
          if (localConversationActivity.drivingFragment != null)
            localConversationActivity.drivingFragment.setGreetingText(str3);
        }
        localConversationActivity.contentFragment.cleanupPreviousBubbles();
        continue;
        if (localConversationActivity.controlFragment.getControlFragmentState() != ControlFragment.ControlState.IDLE)
          continue;
        localConversationActivity.controlFragment.scheduleStartSpotter(200L);
        continue;
        if (localConversationActivity.autoStartOnResume)
          ConversationActivity.access$302(localConversationActivity, false);
        localConversationActivity.controlFragment.startRecognition(null);
        continue;
        localConversationActivity.clearDismissKeyguardFlag();
        continue;
        localConversationActivity.removeLocationListener();
        continue;
        localConversationActivity.controlFragment.performClickFromDriveControl();
        continue;
        FacebookSSOAPI.startFacebookSSO(localConversationActivity, true);
      }
    }
  }

  public static enum DrivingMode
  {
    static
    {
      Driving = new DrivingMode("Driving", 1);
      Regular = new DrivingMode("Regular", 2);
      DrivingMode[] arrayOfDrivingMode = new DrivingMode[3];
      arrayOfDrivingMode[0] = None;
      arrayOfDrivingMode[1] = Driving;
      arrayOfDrivingMode[2] = Regular;
      $VALUES = arrayOfDrivingMode;
    }
  }

  private class DrivingModeBroadcastReceiver extends BroadcastReceiver
  {
    private DrivingModeBroadcastReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ((paramIntent != null) && (paramIntent.getAction().equals("driving_mode")));
    }
  }

  private class HelpWidgetBroadcastReceiver extends BroadcastReceiver
  {
    private HelpWidgetBroadcastReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ((paramIntent != null) && (paramIntent.getAction().equals("com.vlingo.midas.gui.widgets.HelpChoiceWidget")))
        ConversationActivity.this.addHelpWidget(paramIntent);
    }
  }

  private class ScreenEventBroadcastReceiver extends BroadcastReceiver
  {
    private ScreenEventBroadcastReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ((paramIntent != null) && ("android.intent.action.SCREEN_OFF".equals(paramIntent.getAction())))
      {
        DialogFlow.getInstance().cancelDialog();
        Settings.setBoolean("reset_screen_off_after_launch_timer", true);
        ConversationActivity.this.contentFragment.cancelAsrEditing();
      }
    }
  }

  private class TimeWidgetBroadcastReceiver extends BroadcastReceiver
  {
    private TimeWidgetBroadcastReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (ConversationActivity.latestWidget != null)
        ConversationActivity.latestWidget.processSystemMessage(paramIntent);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.ConversationActivity
 * JD-Core Version:    0.6.0
 */