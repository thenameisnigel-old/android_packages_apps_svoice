package com.vlingo.core.internal.dialogmanager;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.logging.Logger;
import com.vlingo.sdk.internal.recognizer.XMLResponseListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CoreTester
  implements XMLResponseListener
{
  private static final Logger log = Logger.getLogger(CoreTester.class);
  private static final String singleLineStringVal = "ONE_LINE";
  private VLDummyRecognizer mDummyRecognizer = null;
  private DialogFlow mFlow;
  String mInFilePath = null;
  private long mLastNanoTime = 0L;
  private String mOutFilePath = null;
  private ReplayThread mReplayThread;

  public CoreTester(DialogFlow paramDialogFlow)
  {
  }

  private void cleanUpRecognizer()
  {
  }

  private OutputStreamWriter getWriter()
  {
    return null;
  }

  private void recordDelay()
  {
  }

  private void recordResponseString(String paramString)
  {
  }

  public void notifyXMLResponse(String paramString)
  {
  }

  void recordIntent(Intent paramIntent, Object paramObject)
  {
  }

  void recordWidget(WidgetUtil.WidgetKey paramWidgetKey)
  {
  }

  public void startRecording(String paramString)
  {
  }

  public void startReplay(String paramString, OnFinishedCallback paramOnFinishedCallback)
    throws IOException
  {
  }

  public void stopRecording()
  {
  }

  public void stopReplay()
  {
  }

  static enum ExtraTypes
  {
    static
    {
      Invalid = new ExtraTypes("Invalid", 2);
      ExtraTypes[] arrayOfExtraTypes = new ExtraTypes[3];
      arrayOfExtraTypes[0] = Int;
      arrayOfExtraTypes[1] = Str;
      arrayOfExtraTypes[2] = Invalid;
      $VALUES = arrayOfExtraTypes;
    }
  }

  public static abstract interface OnFinishedCallback
  {
    public abstract void onFinished();
  }

  class ReplayThread extends Thread
  {
    private static final int defaultDelayTime = 10000;
    private final Logger log = Logger.getLogger(getClass());
    private int mDelayTime = 10000;
    private volatile boolean mDialogFlowRunning = false;
    private volatile boolean mDoingReplay = false;
    private FileInputStream mFis = null;
    private Handler mHandler;
    final CoreTester.OnFinishedCallback mOnReplayFinishedCallback;
    private BufferedReader mReader = null;
    private boolean mWaitingForWidget = false;

    ReplayThread(String paramOnFinishedCallback, CoreTester.OnFinishedCallback arg3)
      throws IOException
    {
      super();
      Object localObject;
      this.mOnReplayFinishedCallback = localObject;
    }

    private String getNextInput()
    {
      return null;
    }

    private boolean initializeMsg(String paramString, Message paramMessage)
    {
      return true;
    }

    private void makeIntent(String paramString, Message paramMessage)
    {
    }

    private void makeServerResponse(String paramString, Message paramMessage)
    {
    }

    private void waitForWidget(String paramString)
    {
    }

    public void run()
    {
    }

    public void stopReplay()
    {
    }
  }

  static enum ReplayTypes
  {
    static
    {
      INTENT = new ReplayTypes("INTENT", 1);
      DELAY = new ReplayTypes("DELAY", 2);
      WIDGET_ACTIVATED = new ReplayTypes("WIDGET_ACTIVATED", 3);
      QUIT = new ReplayTypes("QUIT", 4);
      ReplayTypes[] arrayOfReplayTypes = new ReplayTypes[5];
      arrayOfReplayTypes[0] = XML_RESPONSE;
      arrayOfReplayTypes[1] = INTENT;
      arrayOfReplayTypes[2] = DELAY;
      arrayOfReplayTypes[3] = WIDGET_ACTIVATED;
      arrayOfReplayTypes[4] = QUIT;
      $VALUES = arrayOfReplayTypes;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.CoreTester
 * JD-Core Version:    0.6.0
 */