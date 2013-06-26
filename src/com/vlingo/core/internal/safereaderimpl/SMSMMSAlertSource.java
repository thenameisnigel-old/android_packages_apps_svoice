package com.vlingo.core.internal.safereaderimpl;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import com.vlingo.core.internal.messages.SMSMMSProvider;
import com.vlingo.core.internal.safereader.ISafeReaderAlertChangeListener;
import com.vlingo.core.internal.safereader.ISafeReaderAlertSource;
import com.vlingo.core.internal.util.ActivityUtil;

public class SMSMMSAlertSource
  implements ISafeReaderAlertSource
{
  private final long HANDLE_CHANGE_WAIT_MS = 500L;
  private ContentObserver observer = null;

  public String getName()
  {
    return "SMSMMSAlertSource";
  }

  public void onStart(Context paramContext, ISafeReaderAlertChangeListener paramISafeReaderAlertChangeListener)
  {
    this.observer = new SMSMMSContentObserver(new Handler(), paramISafeReaderAlertChangeListener);
    SMSMMSProvider.getInstance().registerSMSMMSContentObserver(this.observer);
  }

  public void onStop(Context paramContext)
  {
    if (this.observer != null)
    {
      SMSMMSProvider.getInstance().unregisterContentObserver(this.observer);
      this.observer = null;
    }
  }

  private class SMSMMSContentObserver extends ContentObserver
  {
    private ISafeReaderAlertChangeListener safeReaderAlertChangeListener;

    public SMSMMSContentObserver(Handler paramISafeReaderAlertChangeListener, ISafeReaderAlertChangeListener arg3)
    {
      super();
      Object localObject;
      this.safeReaderAlertChangeListener = localObject;
    }

    public boolean deliverSelfNotifications()
    {
      return false;
    }

    public void onChange(boolean paramBoolean)
    {
      super.onChange(paramBoolean);
      if (!paramBoolean)
        ActivityUtil.scheduleOnMainThread(new SMSMMSAlertSource.SMSMMSContentObserver.1(this), 500L);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.safereaderimpl.SMSMMSAlertSource
 * JD-Core Version:    0.6.0
 */