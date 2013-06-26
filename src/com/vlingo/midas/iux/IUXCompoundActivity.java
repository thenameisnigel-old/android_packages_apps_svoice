package com.vlingo.midas.iux;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.text.Html;
import android.util.DisplayMetrics;
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
import android.widget.ScrollView;
import android.widget.TextView;
import com.samsung.wakeupsetting.CustomCommandRecordingActivity;
import com.vlingo.core.internal.bluetooth.BluetoothManager;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.midas.help.HelpScreen;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.settings.SettingsScreen;
import com.vlingo.midas.ui.PackageInfoProvider;
import com.vlingo.midas.ui.VLActivity;
import java.lang.ref.WeakReference;

public class IUXCompoundActivity extends VLActivity
{
  public static final String ACTION_CLEANUP = "com.vlingo.client.iux.cleanup";
  public static final String EXTRA_STEP = "com.vlingo.client.iux.extra.step";
  private static final int MSG_CLEAR_DISMISS_KEYGUARD_FLAG = 3;
  private static final String SAVED_STEP = "saved_iux_step";
  private static final int STEP_EDITWHATYOUSAID = 4;
  private static final int STEP_HANDWRITING = 3;
  private static final int STEP_HELP = 5;
  private static final int STEP_HOWTOUSE = 1;
  private static final int STEP_INTRO = 0;
  private static final int STEP_WAKEUP = 2;
  private static int mStep;
  private static int mTheme = 2131296290;
  private int STEP_NOW = 0;
  private AnimationDrawable animation;
  private Button changeNowButton;
  private boolean isWakeLockChanged = false;
  private Button listenExampleButton;
  private float mDensity = 0.0F;
  private float mOldLX = 0.0F;
  private float mOldLY = 0.0F;
  private float mOldPX = 0.0F;
  private float mOldPY = 0.0F;
  private float mOldX = 0.0F;
  private float mOldY = 0.0F;
  PackageInfoProvider mPackageInfo;
  private IUXCompoundActivityHandler mhandler = new IUXCompoundActivityHandler(this);
  private int my_orientation;
  private Button nextButton;
  private Button prevButton;
  private BroadcastReceiverImpl recv;
  private final View.OnClickListener settingClicked = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      if (IUXCompoundActivity.this.isDialogMode())
        IUXCompoundActivity.this.showPreferences();
    }
  };
  private Button skipButton;

  private void acquireWakeLock(Intent paramIntent)
  {
    PowerManager localPowerManager = (PowerManager)getSystemService("power");
    long l;
    if (BluetoothManager.isHeadsetConnected())
      l = 4000L;
    while (true)
    {
      localPowerManager.newWakeLock(805306394, "VoiceTalkTemp").acquire(l);
      this.isWakeLockChanged = true;
      return;
      l = 600L;
    }
  }

  private void checkOrientation()
  {
    WindowManager.LayoutParams localLayoutParams;
    if (isDialogMode())
    {
      this.my_orientation = getResources().getConfiguration().orientation;
      localLayoutParams = getWindow().getAttributes();
      Point localPoint = new Point();
      getWindowManager().getDefaultDisplay().getSize(localPoint);
      if (this.my_orientation != 2)
        break label88;
      localLayoutParams.x = (int)this.mOldLX;
    }
    for (localLayoutParams.y = (int)this.mOldLY; ; localLayoutParams.y = (int)this.mOldPY)
    {
      getWindow().setAttributes(localLayoutParams);
      System.gc();
      return;
      label88: localLayoutParams.x = (int)this.mOldPX;
    }
  }

  private void clearDismissKeyguardFlag()
  {
    if (getWindow() != null)
      getWindow().clearFlags(4194304);
  }

  private void createEditWhatYouSaid()
  {
    setContentView(2130903102);
    setTitle(getString(2131362506));
    this.skipButton = ((Button)findViewById(2131558786));
    this.skipButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        IUXManager.setIUXIntroRequired(false);
        IUXManager.finishIUX(IUXCompoundActivity.this);
        IUXCompoundActivity.this.finish();
      }
    });
    this.prevButton = ((Button)findViewById(2131558785));
    this.prevButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (IUXCompoundActivity.this.mPackageInfo.hasPenFeature())
          IUXCompoundActivity.this.recreateView(3);
        while (true)
        {
          return;
          IUXCompoundActivity.this.recreateView(2);
        }
      }
    });
    this.nextButton = ((Button)findViewById(2131558787));
    this.nextButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        IUXCompoundActivity.this.recreateView(5);
      }
    });
    ImageView localImageView = (ImageView)findViewById(2131558637);
    Settings.getLanguageApplication();
    localImageView.setImageResource(2130838173);
    localImageView.invalidate();
    ((ScrollView)findViewById(2131558784)).requestFocus();
  }

  private void createHandwriting()
  {
    setContentView(2130903103);
    setTitle(getString(2131362609));
    this.skipButton = ((Button)findViewById(2131558786));
    this.skipButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        IUXManager.setIUXIntroRequired(false);
        IUXManager.finishIUX(IUXCompoundActivity.this);
        IUXCompoundActivity.this.finish();
      }
    });
    this.prevButton = ((Button)findViewById(2131558785));
    this.prevButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        IUXCompoundActivity.this.recreateView(2);
      }
    });
    this.nextButton = ((Button)findViewById(2131558787));
    this.nextButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        IUXCompoundActivity.this.recreateView(4);
      }
    });
    ((ScrollView)findViewById(2131558784)).requestFocus();
  }

  private void createHowToUse()
  {
    setContentView(2130903104);
    setTitle(getString(2131362341));
    ImageView localImageView = (ImageView)findViewById(2131558657);
    if (MidasSettings.isHomeKeyEnabled());
    for (int i = 2130838168; ; i = 2130838169)
    {
      localImageView.setImageResource(i);
      TextView localTextView1 = (TextView)findViewById(2131558652);
      TextView localTextView2 = (TextView)findViewById(2131558654);
      TextView localTextView3 = (TextView)findViewById(2131558656);
      localTextView1.setText(Html.fromHtml(localTextView1.getText().toString()));
      localTextView2.setText(Html.fromHtml(localTextView2.getText().toString()));
      localTextView3.setText(Html.fromHtml(localTextView3.getText().toString()));
      this.skipButton = ((Button)findViewById(2131558786));
      this.skipButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          IUXManager.setIUXIntroRequired(false);
          IUXManager.finishIUX(IUXCompoundActivity.this);
          IUXCompoundActivity.this.finish();
        }
      });
      this.prevButton = ((Button)findViewById(2131558785));
      this.prevButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          IUXCompoundActivity.this.recreateView(0);
        }
      });
      this.nextButton = ((Button)findViewById(2131558787));
      this.nextButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          IUXCompoundActivity.this.recreateView(2);
        }
      });
      ((ScrollView)findViewById(2131558784)).requestFocus();
      return;
    }
  }

  private void createIntro()
  {
    setContentView(2130903105);
    setTitle(getString(2131362231));
    this.skipButton = ((Button)findViewById(2131558786));
    this.skipButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        IUXManager.setIUXIntroRequired(false);
        IUXManager.finishIUX(IUXCompoundActivity.this);
        IUXCompoundActivity.this.finish();
      }
    });
    this.nextButton = ((Button)findViewById(2131558787));
    this.nextButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        IUXCompoundActivity.this.recreateView(1);
      }
    });
    ((ScrollView)findViewById(2131558784)).requestFocus();
  }

  private void createWakeUp()
  {
    setContentView(2130903106);
    setTitle(getString(2131362502));
    findViewById(2131558663).setVisibility(0);
    TextView localTextView1 = (TextView)findViewById(2131558659);
    localTextView1.setText(Html.fromHtml(localTextView1.getText().toString()));
    TextView localTextView2 = (TextView)findViewById(2131558665);
    int i;
    int j;
    label103: ImageView localImageView2;
    if (MidasSettings.isHomeKeyEnabled())
    {
      i = 2131362288;
      localTextView2.setText(i);
      ImageView localImageView1 = (ImageView)findViewById(2131558660);
      if (!MidasSettings.isHomeKeyEnabled())
        break label278;
      j = 2130838168;
      localImageView1.setImageResource(j);
      localImageView2 = (ImageView)findViewById(2131558666);
      if (!MidasSettings.isHomeKeyEnabled())
        break label286;
    }
    label278: label286: for (int k = 2130838171; ; k = 2130838172)
    {
      localImageView2.setImageResource(k);
      this.skipButton = ((Button)findViewById(2131558786));
      this.skipButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          IUXManager.setIUXIntroRequired(false);
          IUXManager.finishIUX(IUXCompoundActivity.this);
          IUXCompoundActivity.this.finish();
        }
      });
      this.prevButton = ((Button)findViewById(2131558785));
      this.prevButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          IUXCompoundActivity.this.recreateView(1);
        }
      });
      this.nextButton = ((Button)findViewById(2131558787));
      this.nextButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          if (Settings.isAsrEditingEnabled())
            if (IUXCompoundActivity.this.mPackageInfo.hasPenFeature())
              IUXCompoundActivity.this.recreateView(3);
          while (true)
          {
            return;
            IUXCompoundActivity.this.recreateView(4);
            continue;
            IUXCompoundActivity.this.recreateView(5);
          }
        }
      });
      this.changeNowButton = ((Button)findViewById(2131558664));
      this.changeNowButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          Intent localIntent = new Intent(IUXCompoundActivity.this, CustomCommandRecordingActivity.class);
          localIntent.putExtra("wycs.show.done", true);
          localIntent.putExtra("trainType", 0);
          IUXCompoundActivity.this.startActivity(localIntent);
        }
      });
      ((ScrollView)findViewById(2131558784)).requestFocus();
      return;
      i = 2131362289;
      break;
      j = 2130838169;
      break label103;
    }
  }

  private boolean isDialogMode()
  {
    if (mTheme != 2131296290);
    for (int i = 1; ; i = 0)
      return i;
  }

  private void registerReceiver()
  {
    if (this.recv == null)
    {
      this.recv = new BroadcastReceiverImpl(null);
      IntentFilter localIntentFilter = new IntentFilter("com.vlingo.client.iux.cleanup");
      registerReceiver(this.recv, localIntentFilter);
    }
  }

  public static void setMStep(int paramInt)
  {
    mStep = paramInt;
  }

  private void showPreferences()
  {
    Intent localIntent = new Intent(this, SettingsScreen.class);
    localIntent.putExtra("is_start_option_menu", true);
    startActivity(localIntent);
  }

  private void unregisterReciever()
  {
    if (this.recv != null)
      unregisterReceiver(this.recv);
  }

  public void onBackPressed()
  {
    switch (this.STEP_NOW)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return;
      super.onBackPressed();
      continue;
      recreateView(0);
      continue;
      recreateView(1);
      continue;
      recreateView(2);
      continue;
      if (this.mPackageInfo.hasPenFeature())
      {
        recreateView(3);
        continue;
      }
      recreateView(2);
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    MidasSettings.updateCurrentLocale();
    checkOrientation();
    recreateView(mStep);
  }

  protected void onCreate(Bundle paramBundle)
  {
    this.mPackageInfo = new PackageInfoProvider(this);
    DisplayMetrics localDisplayMetrics1 = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics1);
    this.mDensity = localDisplayMetrics1.density;
    DisplayMetrics localDisplayMetrics2;
    Point localPoint;
    float f1;
    float f2;
    if (((localDisplayMetrics1.density == 1.0D) && (localDisplayMetrics1.heightPixels + localDisplayMetrics1.widthPixels == 2032) && (localDisplayMetrics1.xdpi == 149.82489F) && (localDisplayMetrics1.ydpi == 150.51852F)) || ((localDisplayMetrics1.density == 1.0D) && (localDisplayMetrics1.heightPixels + localDisplayMetrics1.widthPixels == 2080) && (localDisplayMetrics1.xdpi == 149.82401F) && (localDisplayMetrics1.ydpi == 150.51801F)))
    {
      mTheme = 2131296529;
      setTheme(mTheme);
      super.onCreate(paramBundle);
      this.my_orientation = getResources().getConfiguration().orientation;
      registerReceiver();
      MidasSettings.updateCurrentLocale();
      if (paramBundle != null)
      {
        int i = paramBundle.getInt("saved_iux_step");
        if (i != 0)
          mStep = i;
      }
      if (!isDialogMode())
        break label637;
      getWindow().clearFlags(2);
      getWindow().addFlags(17170976);
      boolean bool = requestWindowFeature(8);
      recreateView(mStep);
      getWindow().addFlags(4194304);
      getWindow().setBackgroundDrawableResource(2130837952);
      if (bool)
      {
        ActionBar localActionBar = getActionBar();
        localActionBar.setCustomView(getLayoutInflater().inflate(2130903048, null), new ActionBar.LayoutParams(-1, 40));
        localActionBar.setDisplayUseLogoEnabled(false);
        localActionBar.setDisplayOptions(0x10 ^ localActionBar.getDisplayOptions(), 16);
        localActionBar.setDisplayShowHomeEnabled(false);
        localActionBar.getCustomView().setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
          {
            int i = 1;
            WindowManager.LayoutParams localLayoutParams = IUXCompoundActivity.this.getWindow().getAttributes();
            if (paramMotionEvent.getAction() == 0)
            {
              IUXCompoundActivity.access$202(IUXCompoundActivity.this, paramMotionEvent.getRawX());
              IUXCompoundActivity.access$302(IUXCompoundActivity.this, paramMotionEvent.getRawY());
            }
            while (true)
            {
              return i;
              if (paramMotionEvent.getAction() == 2)
              {
                float f1 = paramMotionEvent.getRawX() - IUXCompoundActivity.this.mOldX;
                float f2 = paramMotionEvent.getRawY() - IUXCompoundActivity.this.mOldY;
                if ((Math.abs(f1) <= 3.0F) && (Math.abs(f2) <= 3.0F))
                  continue;
                localLayoutParams.x -= (int)f1;
                localLayoutParams.y -= (int)f2;
                if (IUXCompoundActivity.this.my_orientation == 2)
                {
                  IUXCompoundActivity.access$502(IUXCompoundActivity.this, localLayoutParams.x);
                  IUXCompoundActivity.access$602(IUXCompoundActivity.this, localLayoutParams.y);
                }
                while (true)
                {
                  IUXCompoundActivity.access$202(IUXCompoundActivity.this, paramMotionEvent.getRawX());
                  IUXCompoundActivity.access$302(IUXCompoundActivity.this, paramMotionEvent.getRawY());
                  IUXCompoundActivity.this.getWindow().setAttributes(localLayoutParams);
                  break;
                  IUXCompoundActivity.access$702(IUXCompoundActivity.this, localLayoutParams.x);
                  IUXCompoundActivity.access$802(IUXCompoundActivity.this, localLayoutParams.y);
                }
              }
              i = 0;
            }
          }
        });
        ImageButton localImageButton = (ImageButton)findViewById(2131558546);
        if (localImageButton != null)
          localImageButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramView)
            {
              IUXCompoundActivity.this.finish();
            }
          });
      }
      WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
      localLayoutParams.gravity = 85;
      localLayoutParams.type = 2006;
      localDisplayMetrics2 = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics2);
      localPoint = new Point();
      getWindowManager().getDefaultDisplay().getSize(localPoint);
      if ((getWindowManager().getDefaultDisplay().getRotation() != 1) && (getWindowManager().getDefaultDisplay().getRotation() != 3))
        break label582;
      f1 = 436.0F * localDisplayMetrics2.density;
      f2 = 704.0F * localDisplayMetrics2.density;
      this.mOldLX = ((localPoint.y - f2) / 2.0F);
      this.mOldPX = ((localPoint.x - f2) / 2.0F);
      label519: localLayoutParams.height = (int)f1;
      localLayoutParams.width = (int)f2;
      localLayoutParams.x = ((localPoint.x - localLayoutParams.width) / 2);
      localLayoutParams.y = 0;
      getWindow().setAttributes(localLayoutParams);
    }
    while (true)
    {
      acquireWakeLock(null);
      return;
      mTheme = 2131296290;
      break;
      label582: f2 = 436.0F * localDisplayMetrics2.density;
      f1 = 704.0F * localDisplayMetrics2.density;
      this.mOldPX = ((localPoint.y - f2) / 2.0F);
      this.mOldLX = ((localPoint.x - f2) / 2.0F);
      break label519;
      label637: recreateView(mStep);
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    unregisterReciever();
  }

  public void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    this.isWakeLockChanged = false;
    getWindow().addFlags(4194304);
    acquireWakeLock(paramIntent);
  }

  protected void onPause()
  {
    super.onPause();
    if (this.animation != null)
      this.animation.stop();
    if (isDialogMode())
      finish();
  }

  protected void onRestart()
  {
    super.onRestart();
    if (this.animation != null)
      this.animation.start();
  }

  protected void onResume()
  {
    super.onResume();
    recreateView(mStep);
    if (MidasSettings.isTOSAccepted())
      MidasSettings.updateCurrentLocale();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putInt("saved_iux_step", mStep);
  }

  protected void onStart()
  {
    super.onStart();
    this.mhandler.removeMessages(3);
  }

  protected void onStop()
  {
    super.onStop();
    this.mhandler.sendEmptyMessageDelayed(3, 300L);
  }

  public void recreateView(int paramInt)
  {
    mStep = paramInt;
    switch (paramInt)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      return;
      this.STEP_NOW = 0;
      createIntro();
      continue;
      this.STEP_NOW = 1;
      createHowToUse();
      continue;
      this.STEP_NOW = 2;
      createWakeUp();
      continue;
      this.STEP_NOW = 3;
      createHandwriting();
      continue;
      this.STEP_NOW = 4;
      createEditWhatYouSaid();
      continue;
      Intent localIntent = new Intent(this, HelpScreen.class);
      localIntent.putExtra("wycs.show.done", true);
      startActivity(localIntent);
    }
  }

  private class BroadcastReceiverImpl extends BroadcastReceiver
  {
    private BroadcastReceiverImpl()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      IUXCompoundActivity.this.finish();
    }
  }

  private static class IUXCompoundActivityHandler extends Handler
  {
    private final WeakReference<IUXCompoundActivity> outer;

    IUXCompoundActivityHandler(IUXCompoundActivity paramIUXCompoundActivity)
    {
      this.outer = new WeakReference(paramIUXCompoundActivity);
    }

    public void handleMessage(Message paramMessage)
    {
      IUXCompoundActivity localIUXCompoundActivity = (IUXCompoundActivity)this.outer.get();
      if (localIUXCompoundActivity != null)
        switch (paramMessage.what)
        {
        default:
        case 3:
        }
      while (true)
      {
        return;
        localIUXCompoundActivity.clearDismissKeyguardFlag();
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.iux.IUXCompoundActivity
 * JD-Core Version:    0.6.0
 */