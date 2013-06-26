package com.vlingo.midas.safereader;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.audio.AudioFocusManager;
import com.vlingo.core.internal.audio.AudioPlayerProxy;
import com.vlingo.core.internal.audio.AudioRequest;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored;
import com.vlingo.core.internal.audio.TTSRequest;
import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.core.internal.safereader.ISafeReaderAlertHandler;
import com.vlingo.core.internal.safereader.SafeReaderAlert;
import com.vlingo.core.internal.safereader.SafeReaderProxy;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.PhoneUtil;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.gui.ConversationActivity;
import java.util.Iterator;
import java.util.LinkedList;

public class JProjectBackgroundHandler
  implements ISafeReaderAlertHandler, IAudioPlaybackService.AudioPlaybackListener
{
  private static final int FOCUS_DEFAULT_BACKGROUND = 0;
  private static final int FOCUS_FOREGROUND = 1;
  private static boolean isInProcess = false;
  private static int mFocus = 0;
  private static JProjectBackgroundHandler mInstance;
  private LinkedList<SafeReaderAlert> alerts;
  private boolean isSilentMode = false;
  private ISafeReaderAlertHandler listener = this;

  private JProjectBackgroundHandler()
  {
    SafeReaderProxy.registerSafeReaderListener(this.listener);
  }

  public static JProjectBackgroundHandler getInstance()
  {
    if (mInstance == null)
    {
      mInstance = new JProjectBackgroundHandler();
      mFocus = 0;
    }
    return mInstance;
  }

  private boolean isKeygaurdSecureActive()
  {
    KeyguardManager localKeyguardManager = (KeyguardManager)SafeReaderProxy.getContext().getSystemService("keyguard");
    if ((localKeyguardManager.inKeyguardRestrictedInputMode()) && (localKeyguardManager.isKeyguardSecure()));
    for (int i = 1; ; i = 0)
      return i;
  }

  private void readAlert(SafeReaderAlert paramSafeReaderAlert)
  {
    if (paramSafeReaderAlert != null)
    {
      isInProcess = true;
      if ((paramSafeReaderAlert.getType().equals("MMS")) || (paramSafeReaderAlert.getType().equals("SMS")))
      {
        SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)paramSafeReaderAlert;
        String str1 = localSMSMMSAlert.getSenderName();
        localSMSMMSAlert.getMessageText();
        String str2 = StringUtils.formatPhoneNumberForTTS(str1);
        ResourceIdProvider localResourceIdProvider = VlingoAndroidCore.getResourceProvider();
        ResourceIdProvider.string localstring = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_SMS_FROM_INTRO;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = str2;
        AudioPlayerProxy.play(TTSRequest.getMessageReadback(localResourceIdProvider.getString(localstring, arrayOfObject)), this);
      }
    }
    while (true)
    {
      return;
      isInProcess = false;
    }
  }

  public void getForegroundFocus(ISafeReaderAlertHandler paramISafeReaderAlertHandler)
  {
    monitorenter;
    try
    {
      mFocus = 1;
      SafeReaderProxy.unregisterSafeReaderListener(this);
      SafeReaderProxy.registerSafeReaderListener(paramISafeReaderAlertHandler);
      this.listener = paramISafeReaderAlertHandler;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void handleAlert(LinkedList<? extends SafeReaderAlert> paramLinkedList)
  {
    monitorenter;
    boolean bool;
    try
    {
      bool = isFocusForeground();
      monitorexit;
      if ((bool) || (isSilentMode()) || (!ClientSuppliedValues.isDrivingModeMessageNotificationEnabled()))
        break label220;
      if (paramLinkedList == null)
        break label202;
      if (this.alerts == null)
        this.alerts = new LinkedList();
      Iterator localIterator = paramLinkedList.iterator();
      while (localIterator.hasNext())
      {
        SafeReaderAlert localSafeReaderAlert = (SafeReaderAlert)localIterator.next();
        if (this.alerts.contains(localSafeReaderAlert))
          continue;
        this.alerts.add(localSafeReaderAlert);
      }
    }
    finally
    {
      monitorexit;
    }
    if ((!PhoneUtil.phoneInUse(SafeReaderProxy.getContext())) && (!isInProcess) && (paramLinkedList != null) && (!paramLinkedList.isEmpty()))
    {
      if (isKeygaurdSecureActive())
        break label203;
      Intent localIntent = new Intent(SafeReaderProxy.getContext(), ConversationActivity.class);
      localIntent.setAction("ACTION_SAFEREADER_LAUNCH");
      localIntent.setFlags(268435456);
      localIntent.putExtra("EXTRA_MESSAGE_LIST", paramLinkedList);
      localIntent.putExtra("EXTRA_TIME_SENT", System.currentTimeMillis());
      SafeReaderProxy.getContext().startActivity(localIntent);
      this.alerts = null;
    }
    while (true)
    {
      label202: return;
      label203: readAlert((SafeReaderAlert)this.alerts.poll());
      continue;
      label220: if (!bool)
        continue;
    }
  }

  public boolean isFocusForeground()
  {
    int i = 1;
    if (mFocus == i);
    while (true)
    {
      return i;
      i = 0;
    }
  }

  public boolean isSilentMode()
  {
    return this.isSilentMode;
  }

  public void onRequestCancelled(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled paramReasonCanceled)
  {
    isInProcess = false;
    AudioFocusManager.getInstance(ApplicationAdapter.getInstance().getApplicationContext()).abandonAudioFocus();
  }

  public void onRequestDidPlay(AudioRequest paramAudioRequest)
  {
    if (!this.alerts.isEmpty())
      readAlert((SafeReaderAlert)this.alerts.poll());
    while (true)
    {
      return;
      isInProcess = false;
      AudioFocusManager.getInstance(ApplicationAdapter.getInstance().getApplicationContext()).abandonAudioFocus();
    }
  }

  public void onRequestIgnored(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored paramReasonIgnored)
  {
    isInProcess = false;
    AudioFocusManager.getInstance(ApplicationAdapter.getInstance().getApplicationContext()).abandonAudioFocus();
  }

  public void onRequestWillPlay(AudioRequest paramAudioRequest)
  {
  }

  public long readoutDelay()
  {
    return Settings.getLong("safereader.delay", 7000L);
  }

  public void release()
  {
    SafeReaderProxy.unregisterSafeReaderListener(this.listener);
    this.listener = null;
    mInstance = null;
  }

  public void releaseForegroundFocus(ISafeReaderAlertHandler paramISafeReaderAlertHandler)
  {
    monitorenter;
    try
    {
      mFocus = 0;
      SafeReaderProxy.unregisterSafeReaderListener(paramISafeReaderAlertHandler);
      SafeReaderProxy.registerSafeReaderListener(this);
      this.listener = this;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setSilentMode(boolean paramBoolean)
  {
    this.isSilentMode = paramBoolean;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.safereader.JProjectBackgroundHandler
 * JD-Core Version:    0.6.0
 */