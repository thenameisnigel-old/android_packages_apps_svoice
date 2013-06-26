package com.vlingo.core.internal.safereaderimpl;

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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class SafeReaderBackgroundHandler
  implements ISafeReaderAlertHandler, IAudioPlaybackService.AudioPlaybackListener
{
  private static final int FOCUS_DEFAULT_BACKGROUND = 0;
  private static final int FOCUS_FOREGROUND = 1;
  private static boolean isInProcess = false;
  private static int mFocus = 0;
  private static SafeReaderBackgroundHandler mInstance;
  private Queue<SafeReaderAlert> alerts;
  private boolean isSilentMode = false;
  private ISafeReaderAlertHandler listener = this;

  private SafeReaderBackgroundHandler()
  {
    SafeReaderProxy.registerSafeReaderListener(this.listener);
  }

  public static SafeReaderBackgroundHandler getInstance()
  {
    if (mInstance == null)
    {
      mInstance = new SafeReaderBackgroundHandler();
      mFocus = 0;
    }
    return mInstance;
  }

  private void readAlert(SafeReaderAlert paramSafeReaderAlert)
  {
    isInProcess = true;
    String str1;
    String str4;
    String str5;
    ResourceIdProvider localResourceIdProvider2;
    ResourceIdProvider.string localstring2;
    Object[] arrayOfObject2;
    if ((paramSafeReaderAlert.getType().equals("MMS")) || (paramSafeReaderAlert.getType().equals("SMS")))
    {
      SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)paramSafeReaderAlert;
      str1 = localSMSMMSAlert.getSenderDisplayName();
      String str2 = localSMSMMSAlert.getAddress();
      String str3 = localSMSMMSAlert.getMessageText();
      if (!StringUtils.isNullOrWhiteSpace(str1))
        break label125;
      str4 = str2;
      str5 = StringUtils.formatPhoneNumberForTTS(str4);
      if (!ClientSuppliedValues.isReadMessageBodyEnabled())
        break label131;
      localResourceIdProvider2 = VlingoAndroidCore.getResourceProvider();
      localstring2 = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_FROM_BODY_NO_PROMPT;
      arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = str5;
      arrayOfObject2[1] = str3;
    }
    label125: ResourceIdProvider localResourceIdProvider1;
    label131: ResourceIdProvider.string localstring1;
    Object[] arrayOfObject1;
    for (String str6 = localResourceIdProvider2.getString(localstring2, arrayOfObject2); ; str6 = localResourceIdProvider1.getString(localstring1, arrayOfObject1))
    {
      AudioPlayerProxy.play(TTSRequest.getMessageReadback(str6), this);
      return;
      str4 = str1;
      break;
      localResourceIdProvider1 = VlingoAndroidCore.getResourceProvider();
      localstring1 = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_SMS_FROM_INTRO;
      arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = str5;
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
      if ((bool) || (isSilentMode()))
        break label145;
      if (paramLinkedList == null)
        break label144;
      if (this.alerts == null)
        this.alerts = new LinkedList();
      Iterator localIterator = paramLinkedList.iterator();
      while (localIterator.hasNext())
      {
        SafeReaderAlert localSafeReaderAlert2 = (SafeReaderAlert)localIterator.next();
        if (this.alerts.contains(localSafeReaderAlert2))
          continue;
        this.alerts.add(localSafeReaderAlert2);
      }
    }
    finally
    {
      monitorexit;
    }
    if ((!PhoneUtil.phoneInUse(SafeReaderProxy.getContext())) && (!isInProcess))
    {
      SafeReaderAlert localSafeReaderAlert1 = (SafeReaderAlert)this.alerts.poll();
      if (localSafeReaderAlert1 != null)
        readAlert(localSafeReaderAlert1);
    }
    while (true)
    {
      label144: return;
      label145: if (bool)
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
    return Settings.getLong("safereader.delay", 0L);
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
 * Qualified Name:     com.vlingo.core.internal.safereaderimpl.SafeReaderBackgroundHandler
 * JD-Core Version:    0.6.0
 */