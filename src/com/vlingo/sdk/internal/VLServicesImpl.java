package com.vlingo.sdk.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.vlingo.sdk.internal.deviceinfo.PhoneInfo;
import com.vlingo.sdk.internal.http.HttpCallback;
import com.vlingo.sdk.internal.http.HttpRequest;
import com.vlingo.sdk.internal.http.HttpResponse;
import com.vlingo.sdk.internal.settings.Settings;
import com.vlingo.sdk.internal.util.StringUtils;
import com.vlingo.sdk.internal.vlservice.VLHttpServiceRequest;
import com.vlingo.sdk.internal.vlservice.response.ActionList;
import com.vlingo.sdk.internal.vlservice.response.VLServiceResponse;
import com.vlingo.sdk.recognition.VLAction;
import com.vlingo.sdk.services.VLServices;
import com.vlingo.sdk.services.VLServicesErrors;
import com.vlingo.sdk.services.VLServicesListener;
import com.vlingo.sdk.services.userlogging.VLUserLoggerLogRecord;
import java.util.ArrayList;
import java.util.List;

public class VLServicesImpl extends VLComponentImpl
  implements VLServices, HttpCallback
{
  private static final String HELLO_REQUEST = "VVHello";
  private static final int TYPE_HELLO = 2;
  private static final int TYPE_UAL = 1;
  private static final String UAL_REQUEST = "ActivityLog";
  private VLServicesListener mHelloListener;
  private NotificationHandler mNotificationHandler = new NotificationHandler();
  private VLServicesListener mUserActivityListener;

  public VLServicesImpl(VLComponentManager paramVLComponentManager, Handler paramHandler)
  {
    super(paramVLComponentManager, paramHandler);
  }

  private int getType(String paramString)
  {
    int i;
    if ("ActivityLog".equals(paramString))
      i = 1;
    while (true)
    {
      return i;
      if ("VVHello".equals(paramString))
      {
        i = 2;
        continue;
      }
      i = -1;
    }
  }

  private boolean stopProcessingIfDisabled(String paramString, VLServicesListener paramVLServicesListener)
  {
    boolean bool = true;
    if (!Settings.getPersistentBoolean(paramString, bool))
    {
      paramVLServicesListener.onError(VLServicesErrors.ERROR_CLIENT, "Sending messages is disabled.");
      bool = false;
    }
    return bool;
  }

  public void onCancelled(HttpRequest paramHttpRequest)
  {
  }

  void onDestroy()
  {
    this.mUserActivityListener = null;
    this.mHelloListener = null;
  }

  public void onFailure(HttpRequest paramHttpRequest)
  {
    this.mNotificationHandler.notifyError(getType(paramHttpRequest.getTaskName()), VLServicesErrors.ERROR_NETWORK, "Network error");
  }

  public void onResponse(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse)
  {
    if (paramHttpResponse.responseCode == 200)
      if ("ActivityLog".equals(paramHttpRequest.getTaskName()))
        this.mNotificationHandler.notifySuccess(getType(paramHttpRequest.getTaskName()), null);
    while (true)
    {
      return;
      if ("VVHello".equals(paramHttpRequest.getTaskName()))
      {
        VLServiceResponse localVLServiceResponse = VLServiceResponse.createFromXml(paramHttpResponse.getDataAsString());
        if (!localVLServiceResponse.isError())
        {
          ArrayList localArrayList = null;
          if (localVLServiceResponse.hasActions())
          {
            ActionList localActionList = localVLServiceResponse.getActionList();
            int i = localActionList.size();
            localArrayList = new ArrayList(i);
            for (int j = 0; j < i; j++)
              localArrayList.add(new VLActionImpl(localActionList.elementAt(j)));
          }
          this.mNotificationHandler.notifySuccess(getType(paramHttpRequest.getTaskName()), localArrayList);
          continue;
        }
      }
      this.mNotificationHandler.notifyError(getType(paramHttpRequest.getTaskName()), VLServicesErrors.ERROR_SERVER, paramHttpResponse.getDataAsString());
    }
  }

  public boolean onTimeout(HttpRequest paramHttpRequest)
  {
    this.mNotificationHandler.notifyError(getType(paramHttpRequest.getTaskName()), VLServicesErrors.ERROR_NETWORK_TIMEOUT, "Timeout waiting for request");
    return true;
  }

  public void onWillExecuteRequest(HttpRequest paramHttpRequest)
  {
  }

  public void sendActivityLog(String paramString, VLUserLoggerLogRecord paramVLUserLoggerLogRecord, VLServicesListener paramVLServicesListener)
  {
    validateInstance();
    if (StringUtils.isNullOrWhiteSpace(paramString))
      throw new IllegalArgumentException("language cannot be null or empty");
    if (paramVLUserLoggerLogRecord == null)
      throw new IllegalArgumentException("userLog cannot be null or empty");
    if (paramVLServicesListener == null)
      throw new IllegalArgumentException("listener must be specifed");
    if (this.mUserActivityListener != null)
      throw new IllegalStateException("UserActivity request already in progress");
    if (!stopProcessingIfDisabled("activitylog.enable", paramVLServicesListener));
    while (true)
    {
      return;
      this.mUserActivityListener = paramVLServicesListener;
      VLHttpServiceRequest localVLHttpServiceRequest = VLHttpServiceRequest.createVLRequest("ActivityLog", this, AndroidServerDetails.getUserLoggingURL(), paramVLUserLoggerLogRecord.getXML(), paramString);
      localVLHttpServiceRequest.setGzipPostData(true);
      localVLHttpServiceRequest.schedule(50L, false, false);
    }
  }

  public void sendHello(String paramString, VLServicesListener paramVLServicesListener)
  {
    validateInstance();
    if (StringUtils.isNullOrWhiteSpace(paramString))
      throw new IllegalArgumentException("language cannot be null or empty");
    if (paramVLServicesListener == null)
      throw new IllegalArgumentException("listener must be specifed");
    if (this.mHelloListener != null)
      throw new IllegalStateException("Hello request already in progress");
    if (!stopProcessingIfDisabled("hello.enable", paramVLServicesListener));
    while (true)
    {
      return;
      this.mHelloListener = paramVLServicesListener;
      String str = "<Hello PhoneHash=\"" + PhoneInfo.getInstance().getPhoneNumberHash() + "\"/>";
      VLHttpServiceRequest.createVLRequest("VVHello", this, AndroidServerDetails.getHelloURL(), str, paramString).schedule(50L, false, false);
    }
  }

  private class NotificationHandler extends Handler
  {
    static final int HELLO_ERROR = 2;
    static final int HELLO_SUCCESS = 1;
    static final int UAL_ERROR = 4;
    static final int UAL_SUCCESS = 3;

    NotificationHandler()
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      if ((paramMessage.what == 3) || (paramMessage.what == 4))
        if (VLServicesImpl.this.mUserActivityListener != null);
      while (true)
      {
        return;
        VLServicesListener localVLServicesListener1 = VLServicesImpl.this.mUserActivityListener;
        VLServicesImpl.access$002(VLServicesImpl.this, null);
        if (paramMessage.what == 3)
        {
          localVLServicesListener1.onSuccess(null);
          continue;
        }
        localVLServicesListener1.onError((VLServicesErrors)((Object[])(Object[])paramMessage.obj)[0], (String)((Object[])(Object[])paramMessage.obj)[1]);
        continue;
        if (((paramMessage.what != 1) && (paramMessage.what != 2)) || (VLServicesImpl.this.mHelloListener == null))
          continue;
        VLServicesListener localVLServicesListener2 = VLServicesImpl.this.mHelloListener;
        VLServicesImpl.access$102(VLServicesImpl.this, null);
        if (paramMessage.what == 1)
        {
          localVLServicesListener2.onSuccess((List)paramMessage.obj);
          continue;
        }
        localVLServicesListener2.onError((VLServicesErrors)((Object[])(Object[])paramMessage.obj)[0], (String)((Object[])(Object[])paramMessage.obj)[1]);
      }
    }

    void notifyError(int paramInt, VLServicesErrors paramVLServicesErrors, String paramString)
    {
      monitorenter;
      if (paramInt == 2);
      try
      {
        NotificationHandler localNotificationHandler2 = VLServicesImpl.this.mNotificationHandler;
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = paramVLServicesErrors;
        arrayOfObject2[1] = paramString;
        localNotificationHandler2.obtainMessage(2, arrayOfObject2).sendToTarget();
        while (true)
        {
          return;
          if (paramInt != 1)
            continue;
          NotificationHandler localNotificationHandler1 = VLServicesImpl.this.mNotificationHandler;
          Object[] arrayOfObject1 = new Object[2];
          arrayOfObject1[0] = paramVLServicesErrors;
          arrayOfObject1[1] = paramString;
          localNotificationHandler1.obtainMessage(4, arrayOfObject1).sendToTarget();
        }
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }

    void notifySuccess(int paramInt, List<VLAction> paramList)
    {
      monitorenter;
      if (paramInt == 2);
      try
      {
        VLServicesImpl.this.mNotificationHandler.obtainMessage(1, paramList).sendToTarget();
        while (true)
        {
          return;
          if (paramInt != 1)
            continue;
          VLServicesImpl.this.mNotificationHandler.obtainMessage(3).sendToTarget();
        }
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.VLServicesImpl
 * JD-Core Version:    0.6.0
 */