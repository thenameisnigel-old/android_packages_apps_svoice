package com.vlingo.core.internal.userlogging;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.TaskQueue;
import com.vlingo.core.internal.util.TaskQueue.Task;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.recognition.VLAction;
import com.vlingo.sdk.services.VLServices;
import com.vlingo.sdk.services.VLServicesErrors;
import com.vlingo.sdk.services.VLServicesListener;
import com.vlingo.sdk.services.userlogging.VLUserLoggerLogRecord;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class UALService extends Service
{
  public static final String EXTRA_SKIP_INITIAL_DELAY = "com.vlingo.client.userlogging.skipInitialDelay";
  private static final long TRANSMIT_INTERVAL = 240000L;
  private TaskQueue mTaskQueue;
  private Timer mTransmitTimer;

  private void transmitData()
  {
    UserLogTask localUserLogTask = new UserLogTask(null);
    this.mTaskQueue.queueTask(localUserLogTask);
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    this.mTaskQueue = new TaskQueue(new Handler());
  }

  // ERROR //
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: aload_1
    //   4: ifnull +12 -> 16
    //   7: aload_1
    //   8: ldc 16
    //   10: iconst_0
    //   11: invokevirtual 71	android/content/Intent:getBooleanExtra	(Ljava/lang/String;Z)Z
    //   14: istore 4
    //   16: aload_0
    //   17: monitorenter
    //   18: aload_0
    //   19: getfield 32	com/vlingo/core/internal/userlogging/UALService:mTransmitTimer	Ljava/util/Timer;
    //   22: ifnonnull +56 -> 78
    //   25: iload 4
    //   27: ifeq +11 -> 38
    //   30: aload_0
    //   31: invokespecial 39	com/vlingo/core/internal/userlogging/UALService:transmitData	()V
    //   34: aload_0
    //   35: monitorexit
    //   36: iconst_1
    //   37: ireturn
    //   38: aload_0
    //   39: new 73	java/util/Timer
    //   42: dup
    //   43: invokespecial 74	java/util/Timer:<init>	()V
    //   46: putfield 32	com/vlingo/core/internal/userlogging/UALService:mTransmitTimer	Ljava/util/Timer;
    //   49: aload_0
    //   50: getfield 32	com/vlingo/core/internal/userlogging/UALService:mTransmitTimer	Ljava/util/Timer;
    //   53: new 8	com/vlingo/core/internal/userlogging/UALService$TransmitTask
    //   56: dup
    //   57: aload_0
    //   58: aconst_null
    //   59: invokespecial 75	com/vlingo/core/internal/userlogging/UALService$TransmitTask:<init>	(Lcom/vlingo/core/internal/userlogging/UALService;Lcom/vlingo/core/internal/userlogging/UALService$1;)V
    //   62: ldc2_w 19
    //   65: invokevirtual 79	java/util/Timer:schedule	(Ljava/util/TimerTask;J)V
    //   68: goto -34 -> 34
    //   71: astore 5
    //   73: aload_0
    //   74: monitorexit
    //   75: aload 5
    //   77: athrow
    //   78: iload 4
    //   80: ifeq -46 -> 34
    //   83: aload_0
    //   84: getfield 32	com/vlingo/core/internal/userlogging/UALService:mTransmitTimer	Ljava/util/Timer;
    //   87: invokevirtual 82	java/util/Timer:cancel	()V
    //   90: aload_0
    //   91: aconst_null
    //   92: putfield 32	com/vlingo/core/internal/userlogging/UALService:mTransmitTimer	Ljava/util/Timer;
    //   95: aload_0
    //   96: new 73	java/util/Timer
    //   99: dup
    //   100: invokespecial 74	java/util/Timer:<init>	()V
    //   103: putfield 32	com/vlingo/core/internal/userlogging/UALService:mTransmitTimer	Ljava/util/Timer;
    //   106: aload_0
    //   107: getfield 32	com/vlingo/core/internal/userlogging/UALService:mTransmitTimer	Ljava/util/Timer;
    //   110: new 8	com/vlingo/core/internal/userlogging/UALService$TransmitTask
    //   113: dup
    //   114: aload_0
    //   115: aconst_null
    //   116: invokespecial 75	com/vlingo/core/internal/userlogging/UALService$TransmitTask:<init>	(Lcom/vlingo/core/internal/userlogging/UALService;Lcom/vlingo/core/internal/userlogging/UALService$1;)V
    //   119: lconst_0
    //   120: invokevirtual 79	java/util/Timer:schedule	(Ljava/util/TimerTask;J)V
    //   123: goto -89 -> 34
    //
    // Exception table:
    //   from	to	target	type
    //   18	75	71	finally
    //   83	123	71	finally
  }

  private class TransmitTask extends TimerTask
  {
    private TransmitTask()
    {
    }

    public void run()
    {
      synchronized (UALService.this)
      {
        if (UALService.this.mTransmitTimer != null)
        {
          UALService.this.mTransmitTimer.cancel();
          UALService.access$202(UALService.this, null);
          UALService.this.transmitData();
        }
        return;
      }
    }
  }

  private class UserLogTask extends TaskQueue.Task
    implements VLServicesListener
  {
    private static final int MAX_RETRIES = 5;
    private static final long WAIT_TIME = 500L;

    private UserLogTask()
    {
    }

    private void done()
    {
      synchronized (UALService.this)
      {
        if ((!UALService.this.mTaskQueue.hasQueuedTask()) && (UALService.this.mTransmitTimer == null))
          UALService.this.stopSelf();
        notifyFinished();
        return;
      }
    }

    public void onError(VLServicesErrors paramVLServicesErrors, String paramString)
    {
      done();
    }

    public void onSuccess(List<VLAction> paramList)
    {
      done();
    }

    public void run()
    {
      VLUserLoggerLogRecord localVLUserLoggerLogRecord = UserLoggingEngine.getInstance().flushUserLogRecord();
      String str = Settings.getLanguageApplication();
      int i = 0;
      int j = 1;
      while (true)
        if ((j <= 5) && (i == 0))
          try
          {
            VLSdk.getInstance().getVLServices().sendActivityLog(str, localVLUserLoggerLogRecord, this);
            i = 1;
            j++;
          }
          catch (IllegalStateException localIllegalStateException)
          {
            while (true)
            {
              long l = 500L * j;
              try
              {
                Thread.sleep(l);
              }
              catch (InterruptedException localInterruptedException)
              {
              }
            }
          }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.userlogging.UALService
 * JD-Core Version:    0.6.0
 */