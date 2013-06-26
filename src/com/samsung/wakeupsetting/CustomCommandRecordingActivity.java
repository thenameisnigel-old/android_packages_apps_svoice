package com.samsung.wakeupsetting;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.media.SoundPool;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.samsung.voiceshell.VoiceEngineResultListener;
import com.samsung.voiceshell.WakeUpCmdRecognizer;
import com.sec.android.svoice.equalizer.MicEqualizerView;
import com.vlingo.core.internal.CoreAdapterRegistrar;
import com.vlingo.core.internal.CoreAdapterRegistrar.AdapterList;
import com.vlingo.core.internal.audio.AudioFocusManager;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.midas.gui.ControlFragment;
import com.vlingo.midas.gui.LandRegularControlFragment;
import com.vlingo.midas.gui.RegularControlFragment;
import com.vlingo.midas.iux.IUXCompoundActivity;
import com.vlingo.midas.phrasespotter.SamsungPhraseSpotter;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.ui.VLActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CustomCommandRecordingActivity extends VLActivity
  implements VoiceEngineResultListener
{
  public static final String LOCKSCREEN_BIOMETRIC_WEAK_FALLBACK = "lockscreen.biometric_weak_fallback";
  private static final int SCROLL_UP;
  private static int adaptationCount = 0;
  private static int enroll_count = 0;
  private static int mTheme = 2131296526;
  public static String[] m_lastEnroll;
  public static String[] m_realLastEnroll;
  private final int ENROLL_CNT = 4;
  private final int ENROLL_CNT_NEW = 6;
  private ImageView PromptButton;
  private ImageView SettingButton;
  private String TAG = "CustomCommandRecordingActivity";
  private Button adaptButton;
  private short commandType = -1;
  private ControlFragment controlFragment;
  private AppState currentState = AppState.IDLE;
  private LinearLayout customCommandHintLayout;
  private boolean customTitleSupported = false;
  private Button doneButton;
  private CustomWakeUpRecordEnd endBody;
  private CustomWakeUpRecordError errorBody;
  private boolean helpwakeup = false;
  private boolean inIUXMode = false;
  private boolean isAdaptVoice = false;
  private boolean isPhoneInUse = false;
  private boolean isTryAgain = false;
  private LandRegularControlFragment landRegularControlFragment;
  private boolean mBtNeeded = false;
  private Context mContext;
  private float mDensity = 0.0F;
  private boolean mIsStartedCWS;
  private boolean mOldAudioRoute = false;
  private float mOldLX = 0.0F;
  private float mOldLY = 0.0F;
  private float mOldPX = 0.0F;
  private float mOldPY = 0.0F;
  private float mOldX = 0.0F;
  private float mOldY = 0.0F;
  private WakeUpCmdRecognizer mRecognizer;
  private RelativeLayout mRelativeLayoutMain;
  private PowerManager.WakeLock mWakeLock;
  private short mWakeUpType = 1;
  private LinearLayout mainControlLL;
  private int my_orientation;
  private int orientation;
  private CustomWakeUpRecord recBody;
  private ImageView reco_idleBtn;
  private MicEqualizerView reco_listeningBtn;
  private ImageView reco_thinkingBtn;
  private RegularControlFragment regularControlFragment;
  private Button retryButton;
  private ScrollView scrollView;
  private SoundPool soundPool;
  private int soundStart;
  private TextView titleView;

  static
  {
    String[] arrayOfString1 = new String[5];
    arrayOfString1[0] = "/data/data/com.vlingo.midas/lastEnrollUtt_1.pcm";
    arrayOfString1[1] = "/data/data/com.vlingo.midas/lastEnrollUtt_2.pcm";
    arrayOfString1[2] = "/data/data/com.vlingo.midas/lastEnrollUtt_3.pcm";
    arrayOfString1[3] = "/data/data/com.vlingo.midas/lastEnrollUtt_4.pcm";
    arrayOfString1[4] = "/data/data/com.vlingo.midas/lastEnrollUtt_5.pcm";
    m_lastEnroll = arrayOfString1;
    String[] arrayOfString2 = new String[5];
    arrayOfString2[0] = "/data/data/com.vlingo.midas/realLastEnrollUtt_1.pcm";
    arrayOfString2[1] = "/data/data/com.vlingo.midas/realLastEnrollUtt_2.pcm";
    arrayOfString2[2] = "/data/data/com.vlingo.midas/realLastEnrollUtt_3.pcm";
    arrayOfString2[3] = "/data/data/com.vlingo.midas/realLastEnrollUtt_4.pcm";
    arrayOfString2[4] = "/data/data/com.vlingo.midas/realLastEnrollUtt_5.pcm";
    m_realLastEnroll = arrayOfString2;
  }

  private void acquireWakeLock()
  {
    if (this.mWakeLock == null)
      this.mWakeLock = ((PowerManager)getSystemService("power")).newWakeLock(805306394, "CstmCmd_ScrnKpr");
    if ((this.mWakeLock != null) && (!this.mWakeLock.isHeld()))
      this.mWakeLock.acquire();
  }

  private void displayRecordingEndPopup()
  {
    if (this.recBody != null)
      this.recBody.setVisibility(8);
    if (this.errorBody != null)
      this.errorBody.setVisibility(8);
    if (this.endBody != null)
      this.endBody.setVisibility(0);
    this.doneButton = ((Button)this.endBody.findViewById(2131558567));
    this.doneButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (CustomCommandRecordingActivity.this.mIsStartedCWS)
        {
          Intent localIntent1 = new Intent();
          localIntent1.setClassName("com.android.settings", "com.android.settings.ChooseLockGeneric");
          localIntent1.putExtra("lockscreen.biometric_weak_fallback", true);
          localIntent1.putExtra("lockscreen.biometric_weak_with_voice_fallback", true);
          CustomCommandRecordingActivity.this.startActivity(localIntent1);
          CustomCommandRecordingActivity.this.finish();
          if (!CustomCommandRecordingActivity.this.inIUXMode)
            break label273;
          CustomWakeupSettingActivity.enrolled = true;
          Intent localIntent2 = new Intent(CustomCommandRecordingActivity.this, IUXCompoundActivity.class);
          CustomCommandRecordingActivity.this.startActivity(localIntent2);
          CustomCommandRecordingActivity.access$1602(CustomCommandRecordingActivity.this, false);
          CustomCommandRecordingActivity.this.finish();
        }
        while (true)
        {
          CustomCommandRecordingActivity.this.setResult(-1);
          CustomCommandRecordingActivity.this.finish();
          return;
          if (CustomCommandRecordingActivity.this.commandType == 0)
          {
            if ((!CustomCommandRecordingActivity.this.mIsStartedCWS) && (!MidasSettings.getBoolean("samsung_wakeup_engine_enable", false)))
              CoreAdapterRegistrar.registerHandler(CoreAdapterRegistrar.AdapterList.PhraseSpotter, SamsungPhraseSpotter.class);
            MidasSettings.setBoolean("samsung_wakeup_engine_enable", true);
          }
          while (true)
            switch (CustomCommandRecordingActivity.this.commandType)
            {
            default:
              break;
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
              CustomCommandRecordingActivity.this.fileRename(CustomCommandRecordingActivity.m_lastEnroll[CustomCommandRecordingActivity.this.commandType], CustomCommandRecordingActivity.m_realLastEnroll[CustomCommandRecordingActivity.this.commandType]);
              break;
              if (MidasSettings.getBoolean("samsung_multi_engine_enable", false))
                continue;
              CoreAdapterRegistrar.registerHandler(CoreAdapterRegistrar.AdapterList.PhraseSpotter, SamsungPhraseSpotter.class);
              MidasSettings.setBoolean("samsung_multi_engine_enable", true);
            }
          label273: if (!CustomCommandRecordingActivity.this.helpwakeup)
            continue;
          CustomCommandRecordingActivity.this.finish();
          CustomWakeupSettingActivity.enrolled = true;
          CustomCommandRecordingActivity.access$1702(CustomCommandRecordingActivity.this, false);
        }
      }
    });
    this.adaptButton = ((Button)this.endBody.findViewById(2131558566));
    this.adaptButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if ((CustomCommandRecordingActivity.this.mIsStartedCWS) && (CustomCommandRecordingActivity.adaptationCount != 6))
        {
          CustomCommandRecordingActivity.this.setRecordingStateUI(CustomCommandRecordingActivity.AppState.ADAPTING_IDLE, false);
          DialogFlow.getInstance().tts(CustomCommandRecordingActivity.this.getString(2131362553));
        }
        while (true)
        {
          return;
          CustomCommandRecordingActivity.this.setRecordingStateUI(CustomCommandRecordingActivity.AppState.IDLE, false);
        }
      }
    });
    if ((this.mIsStartedCWS) && (adaptationCount != 6))
      this.adaptButton.setText(2131362511);
    while (true)
    {
      return;
      this.adaptButton.setText(2131362513);
    }
  }

  private void displayRecordingErrorPopup()
  {
    if (this.recBody != null)
      this.recBody.setVisibility(8);
    if (this.errorBody != null)
      this.errorBody.setVisibility(0);
    if (this.endBody != null)
      this.endBody.setVisibility(8);
    this.retryButton = ((Button)this.errorBody.findViewById(2131558570));
    this.retryButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        CustomCommandRecordingActivity.this.setRecordingStateUI(CustomCommandRecordingActivity.AppState.LISTENING, false);
        CustomCommandRecordingActivity.this.mRecognizer.startEnroll(1 + CustomCommandRecordingActivity.this.commandType, CustomCommandRecordingActivity.this.mWakeUpType);
      }
    });
  }

  private void displayRecordingPopup(boolean paramBoolean)
  {
    this.isAdaptVoice = paramBoolean;
    this.recBody.setImageVisible(this.isAdaptVoice);
    if (this.recBody != null)
      this.recBody.setVisibility(0);
    if (this.errorBody != null)
      this.errorBody.setVisibility(8);
    if (this.endBody != null)
      this.endBody.setVisibility(8);
  }

  private void initialize()
  {
    this.titleView = ((TextView)findViewById(2131558520));
    this.customCommandHintLayout = ((LinearLayout)findViewById(2131558521));
    this.mRelativeLayoutMain = ((RelativeLayout)findViewById(2131558515));
    this.regularControlFragment = ((RegularControlFragment)getSupportFragmentManager().findFragmentById(2131558517));
    this.landRegularControlFragment = ((LandRegularControlFragment)getSupportFragmentManager().findFragmentById(2131558518));
    LinearLayout localLinearLayout;
    if (this.orientation == 1)
    {
      this.PromptButton = ((ImageView)findViewById(2131558497));
      this.SettingButton = ((ImageView)findViewById(2131558503));
      this.reco_listeningBtn = ((MicEqualizerView)findViewById(2131558500));
      this.reco_thinkingBtn = ((ImageView)findViewById(2131558501));
      this.reco_idleBtn = ((ImageView)findViewById(2131558494));
      this.mainControlLL = ((LinearLayout)findViewById(2131558495));
      localLinearLayout = (LinearLayout)findViewById(2131558496);
      getSupportFragmentManager().beginTransaction().hide(this.landRegularControlFragment).show(this.regularControlFragment).commitAllowingStateLoss();
    }
    for (this.controlFragment = this.regularControlFragment; ; this.controlFragment = this.landRegularControlFragment)
    {
      localLinearLayout.setVisibility(4);
      this.PromptButton.setVisibility(4);
      this.SettingButton.setVisibility(4);
      this.soundPool = new SoundPool(1, 3, 0);
      this.soundStart = this.soundPool.load(getBaseContext(), 2131099664, 1);
      this.recBody = ((CustomWakeUpRecord)findViewById(2131558524));
      this.endBody = ((CustomWakeUpRecordEnd)findViewById(2131558526));
      this.errorBody = ((CustomWakeUpRecordError)findViewById(2131558525));
      this.scrollView = ((ScrollView)findViewById(2131558519));
      this.scrollView.requestFocus();
      return;
      this.PromptButton = ((ImageView)findViewById(2131558799));
      this.SettingButton = ((ImageView)findViewById(2131558793));
      this.reco_listeningBtn = ((MicEqualizerView)findViewById(2131558795));
      this.reco_thinkingBtn = ((ImageView)findViewById(2131558796));
      this.reco_idleBtn = ((ImageView)findViewById(2131558797));
      this.mainControlLL = ((LinearLayout)findViewById(2131558791));
      localLinearLayout = (LinearLayout)findViewById(2131558798);
      getSupportFragmentManager().beginTransaction().hide(this.regularControlFragment).show(this.landRegularControlFragment).commitAllowingStateLoss();
    }
  }

  private boolean isDialogMode()
  {
    if (mTheme != 2131296526);
    for (int i = 1; ; i = 0)
      return i;
  }

  private boolean isThisNOTE10()
  {
    Display localDisplay = ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay();
    if ((localDisplay.getWidth() == 1280) && (localDisplay.getHeight() == 752));
    for (int i = 1; ; i = 0)
      return i;
  }

  private void releaseWakeLock()
  {
    if ((this.mWakeLock != null) && (this.mWakeLock.isHeld()))
    {
      this.mWakeLock.release();
      this.mWakeLock = null;
    }
  }

  private void setButtonIdle()
  {
    this.reco_listeningBtn.setVisibility(8);
    this.reco_thinkingBtn.setVisibility(8);
    this.reco_idleBtn.setVisibility(0);
  }

  private void setButtonListening()
  {
    this.reco_thinkingBtn.setVisibility(8);
    this.reco_idleBtn.setVisibility(8);
    this.reco_listeningBtn.setVisibility(0);
  }

  private void setButtonThinking()
  {
    this.reco_listeningBtn.setVisibility(8);
    this.reco_thinkingBtn.setVisibility(0);
    this.reco_thinkingBtn.setClickable(false);
    this.reco_idleBtn.setVisibility(8);
  }

  private void setClickHandlers()
  {
    this.reco_idleBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (CustomCommandRecordingActivity.this.currentState == CustomCommandRecordingActivity.AppState.ADAPTING_IDLE)
        {
          CustomCommandRecordingActivity.this.mRecognizer.startVerify(0);
          CustomCommandRecordingActivity.access$902(0);
          CustomCommandRecordingActivity.this.setRecordingStateUI(CustomCommandRecordingActivity.AppState.ADAPTING, false);
        }
        while (true)
        {
          return;
          CustomCommandRecordingActivity.access$1102(CustomCommandRecordingActivity.this, false);
          CustomCommandRecordingActivity.this.setRecordingStateUI(CustomCommandRecordingActivity.AppState.LISTENING, false);
          if (CustomCommandRecordingActivity.this.isPhoneInUse)
            continue;
          CustomCommandRecordingActivity.this.startEnroll(1 + CustomCommandRecordingActivity.this.commandType, CustomCommandRecordingActivity.this.mWakeUpType);
        }
      }
    });
  }

  private void setOrientationChangeUI()
  {
    if ((this.orientation == 2) && (!isDialogMode()))
      getSupportFragmentManager().beginTransaction().hide(this.regularControlFragment).show(this.landRegularControlFragment).commitAllowingStateLoss();
    while (true)
    {
      return;
      getSupportFragmentManager().beginTransaction().hide(this.landRegularControlFragment).show(this.regularControlFragment).commitAllowingStateLoss();
    }
  }

  private void setRecordingStateUI(AppState paramAppState, boolean paramBoolean)
  {
    this.currentState = paramAppState;
    if (this.recBody != null)
      this.recBody.setVisibility(8);
    if (this.errorBody != null)
      this.errorBody.setVisibility(8);
    if (this.endBody != null)
      this.endBody.setVisibility(8);
    switch (7.$SwitchMap$com$samsung$wakeupsetting$CustomCommandRecordingActivity$AppState[paramAppState.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    }
    while (true)
    {
      return;
      releaseWakeLock();
      adaptationCount = 0;
      enroll_count = 0;
      if (this.isTryAgain)
      {
        this.titleView.setText(2131362350);
        this.customCommandHintLayout.setVisibility(8);
        setButtonIdle();
        continue;
      }
      if (this.mIsStartedCWS)
        this.titleView.setText(getString(2131362552));
      while (true)
      {
        this.customCommandHintLayout.setVisibility(0);
        break;
        this.titleView.setText(getString(2131362370));
      }
      if (((TelephonyManager)getSystemService("phone")).getCallState() != 0)
      {
        if (this.mRecognizer != null)
        {
          this.mRecognizer.stopVerify();
          this.mRecognizer.stopEnroll();
        }
        setRecordingStateUI(AppState.MIC_BUSY, false);
        continue;
      }
      if (!paramBoolean)
      {
        acquireWakeLock();
        AudioFocusManager.getInstance(getBaseContext()).requestAudioFocus(3, 2);
        this.isTryAgain = false;
        stopTTS();
        this.soundPool.play(this.soundStart, 1.0F, 1.0F, 0, 0, 1.0F);
        enroll_count = 0;
      }
      this.titleView.setText(getString(2131362525) + " (" + enroll_count + "/" + 4 + ")");
      this.customCommandHintLayout.setVisibility(8);
      setButtonListening();
      displayRecordingPopup(false);
      this.recBody.setSuccessCount(enroll_count);
      continue;
      releaseWakeLock();
      int k;
      if ((this.mIsStartedCWS) && (adaptationCount != 6))
      {
        TextView localTextView2 = this.titleView;
        if (MidasSettings.isUSEnglishEnabled())
        {
          k = 2131362557;
          label421: localTextView2.setText(k);
          this.scrollView.setVisibility(8);
          this.scrollView.setVisibility(0);
        }
      }
      while (true)
      {
        this.customCommandHintLayout.setVisibility(8);
        setButtonThinking();
        displayRecordingEndPopup();
        break;
        k = 2131362558;
        break label421;
        if ((this.mIsStartedCWS) && (adaptationCount == 6))
        {
          this.titleView.setText(2131362559);
          DialogFlow.getInstance().tts(getString(2131362559));
          continue;
        }
        if (this.commandType != 0)
          break label558;
        this.titleView.setText(2131362364);
        DialogFlow.getInstance().tts(getString(2131362364));
        Toast.makeText(this, 2131362799, 1).show();
      }
      label558: TextView localTextView1 = this.titleView;
      int i;
      label574: DialogFlow localDialogFlow;
      if (MidasSettings.isUSEnglishEnabled())
      {
        i = 2131362555;
        localTextView1.setText(i);
        localDialogFlow = DialogFlow.getInstance();
        if (!MidasSettings.isUSEnglishEnabled())
          break label629;
      }
      label629: for (int j = 2131362555; ; j = 2131362556)
      {
        localDialogFlow.tts(getString(j));
        Toast.makeText(this, 2131362800, 1).show();
        break;
        i = 2131362556;
        break label574;
      }
      if (!paramBoolean)
      {
        acquireWakeLock();
        AudioFocusManager.getInstance(getBaseContext()).requestAudioFocus(3, 2);
        stopTTS();
        adaptationCount = 0;
      }
      this.customCommandHintLayout.setVisibility(8);
      this.titleView.setText(getString(2131362525) + " (" + adaptationCount + "/" + 6 + ")");
      setButtonListening();
      displayRecordingPopup(true);
      this.recBody.setSuccessCount(adaptationCount);
      continue;
      releaseWakeLock();
      this.titleView.setText(2131362553);
      this.customCommandHintLayout.setVisibility(8);
      setButtonIdle();
      continue;
      releaseWakeLock();
      adaptationCount = 0;
      enroll_count = 0;
      this.titleView.setText(2131362550);
      DialogFlow.getInstance().tts(getString(2131362550));
      this.customCommandHintLayout.setVisibility(8);
      setButtonIdle();
      displayRecordingErrorPopup();
      continue;
      releaseWakeLock();
      adaptationCount = 0;
      enroll_count = 0;
      this.isPhoneInUse = true;
      this.titleView.setText(2131362649);
      this.customCommandHintLayout.setVisibility(8);
      setButtonIdle();
    }
  }

  private void startEnroll(short paramShort1, short paramShort2)
  {
    if (Build.VERSION.SDK_INT < 16)
      this.mRecognizer.startEnroll(paramShort1);
    while (true)
    {
      return;
      this.mRecognizer.startEnroll(paramShort1, paramShort2);
    }
  }

  private void startSettingsTTS()
  {
    if (getIntent().getAction() == "android.intent.action.VOICE_SETTING_LOCK")
      DialogFlow.getInstance().tts(getString(2131362552));
    while (true)
    {
      return;
      DialogFlow.getInstance().tts(getString(2131362370));
    }
  }

  private void stopTTS()
  {
    DialogFlow.getInstance().cancelTTS();
    DialogFlow.getInstance().cancelTurn();
  }

  public void OnEnrollResult(int paramInt1, int paramInt2, int paramInt3)
  {
    switch (paramInt1)
    {
    case 102:
    default:
    case 101:
    case 103:
    case 104:
    case 105:
    }
    while (true)
    {
      return;
      if (paramInt2 == 0);
      while (true)
      {
        while (true)
        {
          if ((paramInt2 < 4) && (paramInt2 > 0))
          {
            String str = getString(2131362525);
            this.titleView.setText(str + " (" + paramInt2 + "/" + 4 + ")");
            enroll_count = paramInt2;
          }
          if (paramInt2 == 4);
          try
          {
            Thread.sleep(500L);
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
          }
        }
        break;
        this.recBody.setSuccessCount(paramInt2);
        this.soundPool.play(this.soundStart, 1.0F, 1.0F, 0, 0, 1.0F);
      }
      if (paramInt3 == 4)
        setRecordingStateUI(AppState.THINKING, false);
      if ((paramInt3 == 5) && (paramInt3 != 6))
        continue;
      if (this.mRecognizer != null)
      {
        this.mRecognizer.stopVerify();
        this.mRecognizer.stopEnroll();
      }
      this.isTryAgain = true;
      setRecordingStateUI(AppState.IDLE, false);
      DialogFlow.getInstance().tts(getString(2131362350));
      continue;
      if (this.mRecognizer != null)
      {
        this.mRecognizer.stopVerify();
        this.mRecognizer.stopEnroll();
      }
      setRecordingStateUI(AppState.ERROR, false);
      continue;
      if (this.mRecognizer != null)
      {
        this.mRecognizer.stopVerify();
        this.mRecognizer.stopEnroll();
      }
      setRecordingStateUI(AppState.MIC_BUSY, false);
    }
  }

  public void OnRmsForWave(int paramInt)
  {
    Log.d("test", "OnRmsForWave");
  }

  public void OnSpectrumData(int[] paramArrayOfInt)
  {
    this.controlFragment.showSpectrum(paramArrayOfInt);
  }

  public void OnVerifyResult(int paramInt, short paramShort)
  {
    if (paramInt == 1)
    {
      adaptationCount = 1 + adaptationCount;
      this.soundPool.play(this.soundStart, 1.0F, 1.0F, 0, 0, 1.0F);
      String str = getString(2131362525);
      this.titleView.setText(str + " (" + adaptationCount + "/" + 6 + ")");
      this.mRecognizer.stopVerify();
      this.recBody.setSuccessCount(adaptationCount);
    }
    try
    {
      Thread.sleep(100L);
      if (adaptationCount < 6)
      {
        this.mRecognizer.startVerify(0);
        return;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
      {
        localInterruptedException.printStackTrace();
        continue;
        setRecordingStateUI(AppState.THINKING, false);
      }
    }
  }

  public void fileRename(String paramString1, String paramString2)
  {
    File localFile1 = new File(paramString1);
    File localFile2 = new File(paramString2);
    if (localFile1.exists())
      localFile1.renameTo(localFile2);
  }

  public void onBackPressed()
  {
    if (this.currentState != AppState.IDLE)
    {
      if (this.mRecognizer != null)
      {
        this.mRecognizer.stopVerify();
        this.mRecognizer.stopEnroll();
      }
      setRecordingStateUI(AppState.IDLE, false);
    }
    while (true)
    {
      return;
      if (this.inIUXMode)
      {
        finish();
        startActivity(new Intent(this, IUXCompoundActivity.class));
        continue;
      }
      super.onBackPressed();
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    MidasSettings.updateCurrentLocale();
    this.orientation = paramConfiguration.orientation;
    initialize();
    setClickHandlers();
    setOrientationChangeUI();
    setRecordingStateUI(this.currentState, true);
    this.mRelativeLayoutMain.invalidate();
    WindowManager.LayoutParams localLayoutParams;
    if (isDialogMode())
    {
      if (paramConfiguration != null)
        super.onConfigurationChanged(paramConfiguration);
      this.my_orientation = getResources().getConfiguration().orientation;
      if (paramConfiguration != null)
      {
        localLayoutParams = getWindow().getAttributes();
        Point localPoint = new Point();
        getWindowManager().getDefaultDisplay().getSize(localPoint);
        if (this.my_orientation != 2)
          break label145;
        localLayoutParams.x = (int)this.mOldLX;
      }
    }
    for (localLayoutParams.y = (int)this.mOldLY; ; localLayoutParams.y = (int)this.mOldPY)
    {
      getWindow().setAttributes(localLayoutParams);
      System.gc();
      return;
      label145: localLayoutParams.x = (int)this.mOldPX;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    DisplayMetrics localDisplayMetrics1 = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics1);
    this.mDensity = localDisplayMetrics1.density;
    if (((localDisplayMetrics1.density == 1.0D) && (localDisplayMetrics1.heightPixels + localDisplayMetrics1.widthPixels == 2032) && (localDisplayMetrics1.xdpi == 149.82489F) && (localDisplayMetrics1.ydpi == 150.51852F)) || ((localDisplayMetrics1.density == 1.0D) && (localDisplayMetrics1.heightPixels + localDisplayMetrics1.widthPixels == 2080) && (localDisplayMetrics1.xdpi == 149.82401F) && (localDisplayMetrics1.ydpi == 150.51801F)))
    {
      mTheme = 2131296529;
      setTheme(mTheme);
    }
    while (true)
    {
      super.onCreate(paramBundle);
      MidasSettings.updateCurrentLocale();
      this.mContext = this;
      this.currentState = AppState.IDLE;
      if (!MidasSettings.getBoolean("samsung_wakeup_data_enable", false))
        MidasSettings.setBoolean("samsung_wakeup_data_enable", true);
      Intent localIntent = getIntent();
      if (localIntent.getBooleanExtra("SETTING_VOICE_LOCK", false))
      {
        this.mIsStartedCWS = true;
        this.mWakeUpType = 0;
      }
      this.commandType = (short)localIntent.getIntExtra("trainType", -1);
      this.inIUXMode = localIntent.getBooleanExtra("wycs.show.done", false);
      this.helpwakeup = localIntent.getBooleanExtra("help", false);
      boolean bool;
      if (!localIntent.getBooleanExtra("svoice", false))
      {
        bool = true;
        label251: this.mBtNeeded = bool;
        if (this.mBtNeeded)
          BluetoothManager.getInstance().init();
      }
      try
      {
        Process localProcess1 = Runtime.getRuntime().exec("getprop persist.sys.language");
        Process localProcess2 = Runtime.getRuntime().exec("getprop persist.sys.country");
        BufferedReader localBufferedReader1 = new BufferedReader(new InputStreamReader(localProcess1.getInputStream()));
        BufferedReader localBufferedReader2 = new BufferedReader(new InputStreamReader(localProcess2.getInputStream()));
        String str1 = localBufferedReader1.readLine();
        String str2 = localBufferedReader2.readLine();
        new StringBuilder().append(str1).append("_").append(str2).toString();
        localBufferedReader1.close();
        localBufferedReader2.close();
        label385: startSettingsTTS();
        DisplayMetrics localDisplayMetrics2;
        Point localPoint;
        float f1;
        float f2;
        if (isDialogMode())
        {
          getWindow().clearFlags(2);
          getWindow().addFlags(17170976);
          setContentView(2130903046);
          getWindow().setBackgroundDrawableResource(2130837952);
          ActionBar localActionBar = getActionBar();
          localActionBar.setCustomView(getLayoutInflater().inflate(2130903048, null), new ActionBar.LayoutParams(-1, 40));
          localActionBar.setDisplayUseLogoEnabled(false);
          localActionBar.setDisplayOptions(0x10 ^ localActionBar.getDisplayOptions(), 16);
          localActionBar.setDisplayShowHomeEnabled(false);
          View localView = localActionBar.getCustomView();
          1 local1 = new View.OnTouchListener()
          {
            public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
            {
              int i = 1;
              WindowManager.LayoutParams localLayoutParams = CustomCommandRecordingActivity.this.getWindow().getAttributes();
              if (paramMotionEvent.getAction() == 0)
              {
                CustomCommandRecordingActivity.access$002(CustomCommandRecordingActivity.this, paramMotionEvent.getRawX());
                CustomCommandRecordingActivity.access$102(CustomCommandRecordingActivity.this, paramMotionEvent.getRawY());
              }
              while (true)
              {
                return i;
                if (paramMotionEvent.getAction() == 2)
                {
                  float f1 = paramMotionEvent.getRawX() - CustomCommandRecordingActivity.this.mOldX;
                  float f2 = paramMotionEvent.getRawY() - CustomCommandRecordingActivity.this.mOldY;
                  if ((Math.abs(f1) <= 3.0F) && (Math.abs(f2) <= 3.0F))
                    continue;
                  localLayoutParams.x -= (int)f1;
                  localLayoutParams.y -= (int)f2;
                  if (CustomCommandRecordingActivity.this.my_orientation == 2)
                  {
                    CustomCommandRecordingActivity.access$302(CustomCommandRecordingActivity.this, localLayoutParams.x);
                    CustomCommandRecordingActivity.access$402(CustomCommandRecordingActivity.this, localLayoutParams.y);
                  }
                  while (true)
                  {
                    CustomCommandRecordingActivity.access$002(CustomCommandRecordingActivity.this, paramMotionEvent.getRawX());
                    CustomCommandRecordingActivity.access$102(CustomCommandRecordingActivity.this, paramMotionEvent.getRawY());
                    CustomCommandRecordingActivity.this.getWindow().setAttributes(localLayoutParams);
                    break;
                    CustomCommandRecordingActivity.access$502(CustomCommandRecordingActivity.this, localLayoutParams.x);
                    CustomCommandRecordingActivity.access$602(CustomCommandRecordingActivity.this, localLayoutParams.y);
                  }
                }
                i = 0;
              }
            }
          };
          localView.setOnTouchListener(local1);
          ImageButton localImageButton = (ImageButton)findViewById(2131558546);
          if (localImageButton != null)
          {
            2 local2 = new View.OnClickListener()
            {
              public void onClick(View paramView)
              {
                CustomCommandRecordingActivity.this.finish();
              }
            };
            localImageButton.setOnClickListener(local2);
          }
          WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
          localLayoutParams.gravity = 85;
          localLayoutParams.type = 2006;
          localDisplayMetrics2 = new DisplayMetrics();
          getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics2);
          localPoint = new Point();
          getWindowManager().getDefaultDisplay().getSize(localPoint);
          if ((getWindowManager().getDefaultDisplay().getRotation() == 1) || (getWindowManager().getDefaultDisplay().getRotation() == 3))
          {
            f1 = 476.0F * localDisplayMetrics2.density;
            f2 = 704.0F * localDisplayMetrics2.density;
            this.mOldLX = ((localPoint.y - f1) / 2.0F);
            this.mOldPX = ((localPoint.x - f1) / 2.0F);
            label703: localLayoutParams.height = (int)f2;
            localLayoutParams.width = (int)f1;
            localLayoutParams.x = ((localPoint.x - localLayoutParams.width) / 2);
            localLayoutParams.y = 0;
            getWindow().setAttributes(localLayoutParams);
          }
        }
        while (true)
        {
          return;
          mTheme = 2131296526;
          break;
          bool = false;
          break label251;
          f1 = 476.0F * localDisplayMetrics2.density;
          f2 = 704.0F * localDisplayMetrics2.density;
          this.mOldPX = ((localPoint.y - f1) / 2.0F);
          this.mOldLX = ((localPoint.x - f1) / 2.0F);
          break label703;
          requestWindowFeature(1);
          setContentView(2130903046);
        }
      }
      catch (IOException localIOException)
      {
        break label385;
      }
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.soundPool.release();
    if (this.mRecognizer != null)
    {
      this.mRecognizer.destroy();
      this.mRecognizer = null;
    }
    this.mRelativeLayoutMain.setBackgroundColor(getResources().getColor(2131230721));
    if (this.mBtNeeded)
      BluetoothManager.getInstance().destroy();
    System.gc();
  }

  protected void onPause()
  {
    if (this.mRecognizer != null)
    {
      this.mRecognizer.stopVerify();
      this.mRecognizer.stopEnroll();
    }
    super.onPause();
    if (this.currentState == AppState.LISTENING)
      this.currentState = AppState.IDLE;
    if (this.currentState == AppState.ADAPTING)
      this.currentState = AppState.THINKING;
    if (this.mBtNeeded)
      BluetoothManager.getInstance().stopSCO();
    releaseWakeLock();
    if ((this.inIUXMode) || (this.helpwakeup))
      finish();
  }

  protected void onResume()
  {
    super.onResume();
    if (this.mBtNeeded)
      BluetoothManager.getInstance().startSCO();
    if (this.mRecognizer == null)
    {
      this.mRecognizer = new WakeUpCmdRecognizer(this);
      this.mRecognizer.init();
    }
    this.orientation = getResources().getConfiguration().orientation;
    initialize();
    setClickHandlers();
    setOrientationChangeUI();
    setRecordingStateUI(this.currentState, false);
    MidasSettings.updateCurrentLocale();
  }

  protected void onStop()
  {
    super.onStop();
    stopTTS();
    if (this.mIsStartedCWS)
      AudioFocusManager.getInstance(getBaseContext()).abandonAudioFocus();
  }

  // ERROR //
  public void pushData()
  {
    // Byte code:
    //   0: new 683	java/io/File
    //   3: dup
    //   4: ldc_w 1025
    //   7: invokespecial 685	java/io/File:<init>	(Ljava/lang/String;)V
    //   10: astore_1
    //   11: aconst_null
    //   12: astore_2
    //   13: sipush 1024
    //   16: newarray byte
    //   18: astore_3
    //   19: new 1027	java/util/zip/ZipInputStream
    //   22: dup
    //   23: aload_0
    //   24: invokevirtual 1030	com/samsung/wakeupsetting/CustomCommandRecordingActivity:getApplicationContext	()Landroid/content/Context;
    //   27: invokevirtual 1034	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   30: ldc_w 1036
    //   33: invokevirtual 1042	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   36: invokespecial 1043	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   39: astore 4
    //   41: aload 4
    //   43: invokevirtual 1047	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   46: astore 9
    //   48: aload 9
    //   50: ifnull +250 -> 300
    //   53: new 540	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 541	java/lang/StringBuilder:<init>	()V
    //   60: aload_1
    //   61: invokevirtual 1050	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   64: invokevirtual 546	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: getstatic 1053	java/io/File:separator	Ljava/lang/String;
    //   70: invokevirtual 546	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: aload 9
    //   75: invokevirtual 1058	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   78: invokevirtual 546	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 559	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: astore 11
    //   86: aload 9
    //   88: invokevirtual 1058	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   91: ldc_w 553
    //   94: invokevirtual 1062	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   97: bipush 255
    //   99: if_icmpeq +76 -> 175
    //   102: new 683	java/io/File
    //   105: dup
    //   106: aload 11
    //   108: invokespecial 685	java/io/File:<init>	(Ljava/lang/String;)V
    //   111: invokevirtual 1066	java/io/File:getParentFile	()Ljava/io/File;
    //   114: astore 19
    //   116: aload 19
    //   118: invokevirtual 688	java/io/File:exists	()Z
    //   121: ifne +54 -> 175
    //   124: aload 19
    //   126: invokevirtual 1069	java/io/File:mkdirs	()Z
    //   129: ifne +46 -> 175
    //   132: new 778	java/io/IOException
    //   135: dup
    //   136: new 540	java/lang/StringBuilder
    //   139: dup
    //   140: invokespecial 541	java/lang/StringBuilder:<init>	()V
    //   143: ldc_w 1071
    //   146: invokevirtual 546	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: aload 19
    //   151: invokevirtual 1074	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   154: invokevirtual 559	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   157: invokespecial 1075	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   160: athrow
    //   161: astore 7
    //   163: aload 4
    //   165: astore_2
    //   166: aload_2
    //   167: ifnull +7 -> 174
    //   170: aload_2
    //   171: invokevirtual 1076	java/util/zip/ZipInputStream:close	()V
    //   174: return
    //   175: aconst_null
    //   176: astore 12
    //   178: new 1078	java/io/FileOutputStream
    //   181: dup
    //   182: aload 11
    //   184: invokespecial 1079	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   187: astore 13
    //   189: aload 4
    //   191: aload_3
    //   192: invokevirtual 1083	java/util/zip/ZipInputStream:read	([B)I
    //   195: istore 16
    //   197: iload 16
    //   199: bipush 255
    //   201: if_icmpeq +27 -> 228
    //   204: aload 13
    //   206: aload_3
    //   207: iconst_0
    //   208: iload 16
    //   210: invokevirtual 1087	java/io/FileOutputStream:write	([BII)V
    //   213: aload 4
    //   215: aload_3
    //   216: invokevirtual 1083	java/util/zip/ZipInputStream:read	([B)I
    //   219: istore 17
    //   221: iload 17
    //   223: istore 16
    //   225: goto -28 -> 197
    //   228: aload 13
    //   230: ifnull +8 -> 238
    //   233: aload 13
    //   235: invokevirtual 1088	java/io/FileOutputStream:close	()V
    //   238: aload 4
    //   240: invokevirtual 1091	java/util/zip/ZipInputStream:closeEntry	()V
    //   243: goto -202 -> 41
    //   246: aload 12
    //   248: ifnull +8 -> 256
    //   251: aload 12
    //   253: invokevirtual 1088	java/io/FileOutputStream:close	()V
    //   256: aload 4
    //   258: invokevirtual 1091	java/util/zip/ZipInputStream:closeEntry	()V
    //   261: goto -220 -> 41
    //   264: astore 5
    //   266: aload 4
    //   268: astore_2
    //   269: aload_2
    //   270: ifnull +7 -> 277
    //   273: aload_2
    //   274: invokevirtual 1076	java/util/zip/ZipInputStream:close	()V
    //   277: aload 5
    //   279: athrow
    //   280: astore 15
    //   282: aload 12
    //   284: ifnull +8 -> 292
    //   287: aload 12
    //   289: invokevirtual 1088	java/io/FileOutputStream:close	()V
    //   292: aload 4
    //   294: invokevirtual 1091	java/util/zip/ZipInputStream:closeEntry	()V
    //   297: aload 15
    //   299: athrow
    //   300: aload 4
    //   302: ifnull +8 -> 310
    //   305: aload 4
    //   307: invokevirtual 1076	java/util/zip/ZipInputStream:close	()V
    //   310: goto -136 -> 174
    //   313: astore 10
    //   315: goto -141 -> 174
    //   318: astore 8
    //   320: goto -146 -> 174
    //   323: astore 6
    //   325: goto -48 -> 277
    //   328: astore 5
    //   330: goto -61 -> 269
    //   333: astore 20
    //   335: goto -169 -> 166
    //   338: astore 15
    //   340: aload 13
    //   342: astore 12
    //   344: goto -62 -> 282
    //   347: astore 14
    //   349: aload 13
    //   351: astore 12
    //   353: goto -107 -> 246
    //   356: astore 18
    //   358: goto -112 -> 246
    //
    // Exception table:
    //   from	to	target	type
    //   41	161	161	java/io/IOException
    //   233	261	161	java/io/IOException
    //   287	300	161	java/io/IOException
    //   41	161	264	finally
    //   233	261	264	finally
    //   287	300	264	finally
    //   178	189	280	finally
    //   305	310	313	java/io/IOException
    //   170	174	318	java/io/IOException
    //   273	277	323	java/io/IOException
    //   19	41	328	finally
    //   19	41	333	java/io/IOException
    //   189	221	338	finally
    //   189	221	347	java/lang/Exception
    //   178	189	356	java/lang/Exception
  }

  private static enum AppState
  {
    static
    {
      ADAPTING_IDLE = new AppState("ADAPTING_IDLE", 3);
      ADAPTING = new AppState("ADAPTING", 4);
      ERROR = new AppState("ERROR", 5);
      MIC_BUSY = new AppState("MIC_BUSY", 6);
      AppState[] arrayOfAppState = new AppState[7];
      arrayOfAppState[0] = IDLE;
      arrayOfAppState[1] = LISTENING;
      arrayOfAppState[2] = THINKING;
      arrayOfAppState[3] = ADAPTING_IDLE;
      arrayOfAppState[4] = ADAPTING;
      arrayOfAppState[5] = ERROR;
      arrayOfAppState[6] = MIC_BUSY;
      $VALUES = arrayOfAppState;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wakeupsetting.CustomCommandRecordingActivity
 * JD-Core Version:    0.6.0
 */