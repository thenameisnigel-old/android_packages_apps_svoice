package com.vlingo.midas.mimic;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import com.vlingo.core.internal.dialogmanager.tasks.PausableTask;
import com.vlingo.sdk.recognition.VLRecognitionListener;
import com.vlingo.sdk.recognition.VLRecognitionStates;
import java.io.File;
import java.lang.ref.WeakReference;

public abstract class MimicBaseTask extends PausableTask
{
  public static final int BUFFER_SIZE = 320;
  protected Context context;
  protected File filePlay;
  protected MimicBaseTaskHandler notificationHandler = new MimicBaseTaskHandler(this);

  public MimicBaseTask(Context paramContext)
  {
    this.context = paramContext;
    String str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
    this.filePlay = new File(str + "mimic.raw");
  }

  static class MimicBaseTaskHandler extends Handler
  {
    static final int RMS = 2;
    static final int STATE = 1;
    private final WeakReference<MimicBaseTask> outer;

    MimicBaseTaskHandler(MimicBaseTask paramMimicBaseTask)
    {
      this.outer = new WeakReference(paramMimicBaseTask);
    }

    void clear()
    {
      monitorenter;
      try
      {
        removeMessages(2);
        removeMessages(1);
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

    public void handleMessage(Message paramMessage)
    {
      MimicBaseTask localMimicBaseTask = (MimicBaseTask)this.outer.get();
      if (localMimicBaseTask != null)
        switch (paramMessage.what)
        {
        default:
        case 2:
        case 1:
        }
      while (true)
      {
        return;
        if (localMimicBaseTask.getVlRecognitionListener() == null)
          continue;
        localMimicBaseTask.getVlRecognitionListener().onRmsChanged(((Integer)paramMessage.obj).intValue());
        continue;
        if (localMimicBaseTask.getVlRecognitionListener() == null)
          continue;
        localMimicBaseTask.getVlRecognitionListener().onStateChanged((VLRecognitionStates)paramMessage.obj);
      }
    }

    void notifyEvent(VLRecognitionStates paramVLRecognitionStates)
    {
      monitorenter;
      try
      {
        obtainMessage(1, paramVLRecognitionStates).sendToTarget();
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

    void notifyRmsChange(Object paramObject)
    {
      monitorenter;
      try
      {
        obtainMessage(2, paramObject).sendToTarget();
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
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.mimic.MimicBaseTask
 * JD-Core Version:    0.6.0
 */