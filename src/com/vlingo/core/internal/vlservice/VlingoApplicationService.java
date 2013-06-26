package com.vlingo.core.internal.vlservice;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import com.vlingo.core.internal.bluetooth.BluetoothManager;
import com.vlingo.core.internal.util.ApplicationAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VlingoApplicationService extends Service
{
  public static final String ACTION_ACTIVITY_STATE_CHANGED = "com.vlingo.client.app.action.ACTIVITY_STATE_CHANGED";
  public static final String ACTION_APPLICATION_STATE_CHANGED = "com.vlingo.client.app.action.APPLICATION_STATE_CHANGED";
  public static final String ACTION_CLOSE_APPLICATION = "com.vlingo.client.app.action.CLOSE_APPLICATION";
  public static final String EXTRA_FINISH_ACTIVITY = "com.vlingo.core.internal.vlservice.FINISH_ACTIVITY";
  public static final String EXTRA_STATE = "com.vlingo.client.app.extra.STATE";
  private static final int ITERATIONS = 30;
  private static final int MSG_ACTIVITY_STATE = 1;
  private static final int MSG_CLOSE_APP = 2;
  private static final int RESUME_WAIT = 50;
  public static final int STATE_HIDDEN = 0;
  public static final int STATE_SHOWN = 1;
  private static final String VLINGO_PKG_PREFIX = "com.vlingo";
  private static volatile int mLastState = -1;
  private volatile ServiceHandler mServiceHandler;
  private volatile Looper mServiceLooper;

  public static boolean isAppInForeground()
  {
    int i = 1;
    if (mLastState == i);
    while (true)
    {
      return i;
      i = 0;
    }
  }

  private static boolean isAppInForegroundInternal()
  {
    int i = 0;
    int j = Process.myPid();
    List localList = ((ActivityManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("activity")).getRunningAppProcesses();
    if (localList == null)
      break label37;
    while (true)
    {
      return i;
      Iterator localIterator = localList.iterator();
      label37: if (!localIterator.hasNext())
        continue;
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      if (j != localRunningAppProcessInfo.pid)
        break;
      if (!isForeground(localRunningAppProcessInfo))
        continue;
      i = 1;
    }
  }

  private static boolean isForeground(ActivityManager.RunningAppProcessInfo paramRunningAppProcessInfo)
  {
    if (paramRunningAppProcessInfo.importance == 100);
    for (int i = 1; ; i = 0)
      return i;
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    mLastState = -1;
    HandlerThread localHandlerThread = new HandlerThread("VAS Worker");
    localHandlerThread.start();
    this.mServiceLooper = localHandlerThread.getLooper();
    this.mServiceHandler = new ServiceHandler(this.mServiceLooper);
  }

  public void onDestroy()
  {
    this.mServiceLooper.quit();
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    String str = paramIntent.getAction();
    int i = 1;
    if ("com.vlingo.client.app.action.CLOSE_APPLICATION".equals(str))
      i = 2;
    Message localMessage = this.mServiceHandler.obtainMessage(i);
    localMessage.arg1 = paramInt2;
    localMessage.obj = paramIntent;
    this.mServiceHandler.sendMessage(localMessage);
    return 3;
  }

  private final class ServiceHandler extends Handler
  {
    public ServiceHandler(Looper arg2)
    {
      super();
    }

    private void processIntent(Intent paramIntent, int paramInt)
    {
      String str1 = paramIntent.getAction();
      int i;
      if ("com.vlingo.client.app.action.ACTIVITY_STATE_CHANGED".equals(str1))
      {
        i = paramIntent.getIntExtra("com.vlingo.client.app.extra.STATE", -1);
        if ((i == 1) || (i == 0));
      }
      while (true)
      {
        return;
        if (VlingoApplicationService.mLastState == -1)
          VlingoApplicationService.access$002(i);
        if (i == VlingoApplicationService.mLastState)
        {
          BluetoothManager.considerRightBeforeForeground(false);
          continue;
        }
        boolean bool1;
        int j;
        if (i == 1)
        {
          bool1 = true;
          j = 0;
        }
        while (true)
          while (true)
          {
            if (j >= 30)
              break label196;
            try
            {
              Thread.sleep(50L);
              if (hasMessages(1))
                break;
              boolean bool2 = VlingoApplicationService.access$100();
              if (bool2 == bool1)
              {
                VlingoApplicationService.access$002(i);
                Intent localIntent2 = new Intent("com.vlingo.client.app.action.APPLICATION_STATE_CHANGED");
                if (bool1)
                {
                  k = 1;
                  localIntent2.putExtra("com.vlingo.client.app.extra.STATE", k);
                  VlingoApplicationService.this.sendBroadcast(localIntent2);
                  if (bool2)
                    break;
                  VlingoApplicationService.this.stopSelf(paramInt);
                  break;
                  bool1 = false;
                }
              }
            }
            catch (Exception localException)
            {
              while (true)
              {
                localException.printStackTrace();
                continue;
                int k = 0;
              }
              j++;
            }
          }
        label196: continue;
        if (!"com.vlingo.client.app.action.CLOSE_APPLICATION".equals(str1))
          continue;
        Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
        ArrayList localArrayList = new ArrayList();
        List localList = ((ActivityManager)localContext.getSystemService("activity")).getRunningTasks(2);
        if (localList != null)
        {
          Iterator localIterator2 = localList.iterator();
          while (localIterator2.hasNext())
          {
            String str2 = ((ActivityManager.RunningTaskInfo)localIterator2.next()).baseActivity.getClassName();
            if (!str2.startsWith("com.vlingo"))
              continue;
            try
            {
              localArrayList.add(Class.forName(str2));
            }
            catch (ClassNotFoundException localClassNotFoundException)
            {
              Log.e("VLG_EXCEPTION", Log.getStackTraceString(localClassNotFoundException));
            }
          }
        }
        Iterator localIterator1 = localArrayList.iterator();
        while (localIterator1.hasNext())
        {
          Intent localIntent1 = new Intent(localContext, (Class)localIterator1.next());
          localIntent1.addFlags(603979776);
          localIntent1.addFlags(268435456);
          localIntent1.putExtra("com.vlingo.core.internal.vlservice.FINISH_ACTIVITY", true);
          localContext.startActivity(localIntent1);
        }
      }
    }

    public void handleMessage(Message paramMessage)
    {
      Intent localIntent = (Intent)paramMessage.obj;
      if ((localIntent != null) && (localIntent.getAction() != null))
        processIntent(localIntent, paramMessage.arg1);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.vlservice.VlingoApplicationService
 * JD-Core Version:    0.6.0
 */