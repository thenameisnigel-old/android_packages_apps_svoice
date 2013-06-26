package com.vlingo.midas.phrasespotter;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageButton;
import com.sec.android.svoice.equalizer.EqualizerCircle;
import com.sec.android.svoice.equalizer.EqualizerSpectrumFactory;
import com.sec.android.svoice.equalizer.MicEqualizerView;
import com.vlingo.core.internal.audio.MicAnimationAdapter;
import com.vlingo.core.internal.audio.MicAnimationListener;
import com.vlingo.core.internal.bluetooth.BluetoothManager;
import com.vlingo.core.internal.vlservice.VlingoApplicationService;
import com.vlingo.midas.gui.ConversationActivity;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SeamlessRecoActivity extends Activity
  implements View.OnClickListener, MicAnimationListener
{
  static View layout = null;
  private int[][] abc;
  private int index = 0;
  private boolean keepRecoAlive = false;
  private MicEqualizerView micView;
  private RecoStateChangeReceiver stateChangeReceiver;
  private ImageButton thinkingView;

  private void dismissKeyguard()
  {
    int i = 1;
    try
    {
      Class localClass = Class.forName("com.android.internal.widget.LockPatternUtils");
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Context.class;
      Constructor localConstructor = localClass.getConstructor(arrayOfClass);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = getApplicationContext();
      Object localObject = localConstructor.newInstance(arrayOfObject);
      boolean bool = ((Boolean)localClass.getMethod("isSecure", (Class[])null).invoke(localObject, new Object[0])).booleanValue();
      i = bool;
      label84: if (i == 0)
      {
        Window localWindow = getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        localLayoutParams.flags = (0x680000 | localLayoutParams.flags);
        localWindow.setAttributes(localLayoutParams);
      }
      return;
    }
    catch (Exception localException)
    {
      break label84;
    }
  }

  public static int getRandom(int paramInt1, int paramInt2)
  {
    return (int)(paramInt1 + Math.floor(Math.random() * (paramInt2 + 1 - paramInt1)));
  }

  static void init(Context paramContext)
  {
    layout = LayoutInflater.from(paramContext).inflate(2130903118, null, false);
  }

  private void switchToConversationActivity(boolean paramBoolean)
  {
    Intent localIntent = new Intent(this, ConversationActivity.class);
    if (paramBoolean)
      localIntent.setAction("resumeFromBackground");
    while (true)
    {
      localIntent.putExtra("AUTO_LISTEN", false);
      localIntent.addFlags(268435456);
      startActivity(localIntent);
      finish();
      return;
      localIntent.setAction("displayFromBackground");
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131558822:
    case 2131558823:
    }
    while (true)
    {
      return;
      sendBroadcast(new Intent("listen_clicked"));
      continue;
      sendBroadcast(new Intent("abort_reco"));
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setVolumeControlStream(3);
    BluetoothManager.considerRightBeforeForeground(true);
    setContentView(layout);
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = 5;
    arrayOfInt[1] = 128;
    this.abc = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt));
    this.stateChangeReceiver = new RecoStateChangeReceiver(null);
    registerReceiver(this.stateChangeReceiver, new IntentFilter("reco_status_change"));
    dismissKeyguard();
  }

  public void onDestroy()
  {
    unregisterReceiver(this.stateChangeReceiver);
    this.stateChangeReceiver = null;
    super.onDestroy();
  }

  public void onMicAnimationData(int[] paramArrayOfInt)
  {
    showSpectrum(paramArrayOfInt);
  }

  public void onPause()
  {
    if (!((KeyguardManager)getSystemService("keyguard")).inKeyguardRestrictedInputMode())
      finish();
    if (!this.keepRecoAlive)
      sendBroadcast(new Intent("abort_reco"));
    ((ViewGroup)layout.getParent()).removeView(layout);
    MicAnimationAdapter.removeListener(this);
    Intent localIntent = new Intent(this, VlingoApplicationService.class);
    localIntent.setAction("com.vlingo.client.app.action.ACTIVITY_STATE_CHANGED");
    localIntent.putExtra("com.vlingo.client.app.extra.STATE", 0);
    startService(localIntent);
    super.onPause();
  }

  public void onResume()
  {
    super.onResume();
    Intent localIntent = new Intent(this, VlingoApplicationService.class);
    localIntent.setAction("com.vlingo.client.app.action.ACTIVITY_STATE_CHANGED");
    localIntent.putExtra("com.vlingo.client.app.extra.STATE", 1);
    startService(localIntent);
    findViewById(2131558493).setVisibility(0);
    this.micView = ((MicEqualizerView)findViewById(2131558822));
    this.micView.setOnClickListener(this);
    this.micView.setVisibility(0);
    this.thinkingView = ((ImageButton)findViewById(2131558823));
    this.thinkingView.setOnClickListener(this);
    this.thinkingView.setVisibility(8);
    MicAnimationAdapter.addListener(this);
  }

  public void onSpectrumUpdate(MicEqualizerView paramMicEqualizerView, int[] paramArrayOfInt)
  {
    if ((paramMicEqualizerView == null) || (paramArrayOfInt == null));
    label47: label331: label592: 
    while (true)
    {
      return;
      int i = 0;
      int j = 0;
      int k;
      int m;
      int n;
      int i1;
      float f;
      int i2;
      if (getResources().getConfiguration().orientation == 1)
      {
        k = 4;
        m = 30;
        n = 14;
        i1 = 2;
        f = 1.8F;
        if (paramMicEqualizerView.getFactory() == null)
          break label255;
        i2 = 0;
      }
      while (true)
      {
        if (i2 >= paramArrayOfInt.length)
          break label592;
        if (i < m)
        {
          int i3;
          label110: int i4;
          int i8;
          int i9;
          if (i < (int)(0.25F * m))
          {
            i3 = -1 + (i1 + (int)(0.300000011920929D * Math.sqrt(Math.sqrt(paramArrayOfInt[i2])) * f));
            if ((i <= 0) || (i3 - j <= 1))
              break label331;
            i3 = j + (int)(2.0D * Math.random());
            if (i3 > n)
              i3 = n;
            if (i3 < i1)
              i3 = i1;
            i4 = 0;
            if (i4 >= i3)
              break label371;
            i8 = i + i4 * m;
            i9 = 255 - (int)(255.0F / n * i4);
            if (i9 <= 255)
              break label360;
            i9 = 255;
          }
          while (true)
          {
            ((EqualizerCircle)paramMicEqualizerView.getFactory().getSpectrumLevelDrawables().get(i8)).setAlpha(i9);
            i4++;
            break label161;
            k = 8;
            m = 14;
            n = 30;
            i1 = 4;
            f = 3.8F;
            break label47;
            label255: break;
            if (i > (int)(0.6F * m))
            {
              i3 = -1 + (i1 + (int)(0.5D * Math.sqrt(Math.sqrt(paramArrayOfInt[i2])) * f));
              break label110;
            }
            i3 = -1 + (i1 + (int)(0.699999988079071D * Math.sqrt(Math.sqrt(paramArrayOfInt[i2])) * f));
            break label110;
            if ((i <= 0) || (j - i3 <= 1))
              break label136;
            i3 = j - (int)(2.0D * Math.random());
            break label136;
            label360: if (i9 >= 0)
              continue;
            i9 = 0;
          }
          label371: if (i3 <= (int)(0.7F * n))
          {
            int i5 = i3;
            if (i5 < i3 + 3)
            {
              int i6 = i + i5 * m;
              int i7;
              if (i5 == i3)
              {
                i7 = (int)(0.6F * (255 - (int)(255.0F / n * i5)));
                if (i7 >= 0)
                  break label557;
                i7 = 0;
              }
              while (true)
              {
                if (((EqualizerCircle)paramMicEqualizerView.getFactory().getSpectrumLevelDrawables().get(i6)).getAlpha() < i7)
                  ((EqualizerCircle)paramMicEqualizerView.getFactory().getSpectrumLevelDrawables().get(i6)).setAlpha(i7);
                i5++;
                break;
                if (i5 == i3 + 1)
                {
                  i7 = (int)(0.3F * (255 - (int)(255.0F / n * i5)));
                  break label437;
                }
                i7 = (int)(0.2F * (255 - (int)(255.0F / n * i5)));
                break label437;
                if (i7 <= 255)
                  continue;
                i7 = 255;
              }
            }
          }
          i++;
          j = i3;
        }
        paramMicEqualizerView.postInvalidate();
        i2 += k;
      }
    }
  }

  public void showSpectrum(int[] paramArrayOfInt)
  {
    if ((this.micView != null) && (this.micView.getVisibility() == 0))
      onSpectrumUpdate(this.micView, paramArrayOfInt);
    if ((this.micView != null) && (this.micView.getVisibility() == 0))
    {
      System.arraycopy(paramArrayOfInt, 0, this.abc[this.index], 0, paramArrayOfInt.length);
      this.micView.postDelayed(new Runnable()
      {
        private int mIndex = SeamlessRecoActivity.this.index;

        public void run()
        {
          SeamlessRecoActivity.this.onSpectrumUpdate(SeamlessRecoActivity.this.micView, SeamlessRecoActivity.this.abc[this.mIndex]);
        }
      }
      , 20 * this.index);
      this.index = (1 + this.index);
      this.index %= 5;
    }
  }

  private class RecoStateChangeReceiver extends BroadcastReceiver
  {
    private RecoStateChangeReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ("speech_thinking".equals(paramIntent.getStringExtra("reco_status")))
      {
        SeamlessRecoActivity.this.micView.setVisibility(8);
        SeamlessRecoActivity.this.thinkingView.setVisibility(0);
        if (SeamlessRecoActivity.this.getResources().getConfiguration().orientation != 1)
          break label172;
        AnimationDrawable localAnimationDrawable2 = (AnimationDrawable)SeamlessRecoActivity.this.getResources().getDrawable(2131034114);
        SeamlessRecoActivity.this.thinkingView.setBackgroundDrawable(localAnimationDrawable2);
        localAnimationDrawable2.start();
      }
      while (true)
      {
        if ("speech_display".equals(paramIntent.getStringExtra("reco_status")))
        {
          SeamlessRecoActivity.access$302(SeamlessRecoActivity.this, true);
          SeamlessRecoActivity.this.switchToConversationActivity(false);
        }
        if ("speech_passing".equals(paramIntent.getStringExtra("reco_status")))
        {
          SeamlessRecoActivity.access$302(SeamlessRecoActivity.this, true);
          SeamlessRecoActivity.this.switchToConversationActivity(true);
        }
        if ("speech_idle".equals(paramIntent.getStringExtra("reco_status")))
          SeamlessRecoActivity.this.finish();
        return;
        label172: AnimationDrawable localAnimationDrawable1 = (AnimationDrawable)SeamlessRecoActivity.this.getResources().getDrawable(2131034115);
        SeamlessRecoActivity.this.thinkingView.setBackgroundDrawable(localAnimationDrawable1);
        localAnimationDrawable1.start();
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.phrasespotter.SeamlessRecoActivity
 * JD-Core Version:    0.6.0
 */