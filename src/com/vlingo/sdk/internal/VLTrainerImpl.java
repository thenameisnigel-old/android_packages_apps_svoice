package com.vlingo.sdk.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.vlingo.sdk.internal.http.HttpCallback;
import com.vlingo.sdk.internal.http.HttpRequest;
import com.vlingo.sdk.internal.http.HttpResponse;
import com.vlingo.sdk.internal.lmtt.LMTTComm;
import com.vlingo.sdk.internal.lmtt.LMTTItem.LmttItemType;
import com.vlingo.sdk.internal.util.StringUtils;
import com.vlingo.sdk.internal.vlservice.VLHttpServiceRequest;
import com.vlingo.sdk.training.VLTrainer;
import com.vlingo.sdk.training.VLTrainer.TrainerItemType;
import com.vlingo.sdk.training.VLTrainerErrors;
import com.vlingo.sdk.training.VLTrainerListener;
import com.vlingo.sdk.training.VLTrainerUpdateList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public final class VLTrainerImpl extends VLComponentImpl
  implements VLTrainer, HttpCallback
{
  private NotificationHandler mNotificationHandler = new NotificationHandler();
  private VLTrainerListener mTrainerListener;

  public VLTrainerImpl(VLComponentManager paramVLComponentManager, Handler paramHandler)
  {
    super(paramVLComponentManager, paramHandler);
  }

  private static HashMap<VLTrainer.TrainerItemType, Integer> lmttType2TrainerType(HashMap<LMTTItem.LmttItemType, Integer> paramHashMap)
  {
    HashMap localHashMap;
    if (paramHashMap == null)
      localHashMap = null;
    while (true)
    {
      return localHashMap;
      localHashMap = new HashMap(paramHashMap.size());
      Iterator localIterator = paramHashMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        LMTTItem.LmttItemType localLmttItemType = (LMTTItem.LmttItemType)localIterator.next();
        switch (2.$SwitchMap$com$vlingo$sdk$internal$lmtt$LMTTItem$LmttItemType[localLmttItemType.ordinal()])
        {
        default:
          break;
        case 1:
          localHashMap.put(VLTrainer.TrainerItemType.CONTACT, paramHashMap.get(localLmttItemType));
          break;
        case 2:
          localHashMap.put(VLTrainer.TrainerItemType.SONG, paramHashMap.get(localLmttItemType));
          break;
        case 3:
          localHashMap.put(VLTrainer.TrainerItemType.PLAYLIST, paramHashMap.get(localLmttItemType));
        }
      }
    }
  }

  private void sendUpdate(VLTrainerUpdateList paramVLTrainerUpdateList, String paramString, VLTrainerListener paramVLTrainerListener, boolean paramBoolean)
  {
    validateInstance();
    if (paramVLTrainerUpdateList == null)
      throw new IllegalArgumentException("list cannot be null");
    if (!(paramVLTrainerUpdateList instanceof VLTrainerUpdateListImpl))
      throw new IllegalArgumentException("VLTrainerUpdateList instance must be obtained through VLTrainer.getUpdateListIntance()");
    if (((VLTrainerUpdateListImpl)paramVLTrainerUpdateList).isEmpty())
      throw new IllegalArgumentException("list cannot be empty");
    if (StringUtils.isNullOrWhiteSpace(paramString))
      throw new IllegalArgumentException("language cannot be null or empty");
    if (paramVLTrainerListener == null)
      throw new IllegalArgumentException("listener must be specifed");
    if (isBusy())
      throw new IllegalStateException("Trainer request already in progress");
    setBusy(true);
    this.mTrainerListener = paramVLTrainerListener;
    getHandler().post(new Runnable(paramVLTrainerUpdateList, paramBoolean, paramString)
    {
      public void run()
      {
        LMTTComm.createLMTTRequest(((VLTrainerUpdateListImpl)this.val$list).compact(), this.val$doWhole, this.val$language, VLTrainerImpl.this).schedule(0L, true, true);
      }
    });
  }

  public void clearAllItems(String paramString, VLTrainerListener paramVLTrainerListener)
  {
    VLTrainerUpdateListImpl localVLTrainerUpdateListImpl = new VLTrainerUpdateListImpl();
    localVLTrainerUpdateListImpl.addNOOPItem(VLTrainer.TrainerItemType.CONTACT);
    localVLTrainerUpdateListImpl.addNOOPItem(VLTrainer.TrainerItemType.SONG);
    sendUpdate(localVLTrainerUpdateListImpl, paramString, paramVLTrainerListener, true);
  }

  public void clearContactItems(String paramString, VLTrainerListener paramVLTrainerListener)
  {
    VLTrainerUpdateListImpl localVLTrainerUpdateListImpl = new VLTrainerUpdateListImpl();
    localVLTrainerUpdateListImpl.addNOOPItem(VLTrainer.TrainerItemType.CONTACT);
    sendUpdate(localVLTrainerUpdateListImpl, paramString, paramVLTrainerListener, true);
  }

  public void clearMusicItems(String paramString, VLTrainerListener paramVLTrainerListener)
  {
    VLTrainerUpdateListImpl localVLTrainerUpdateListImpl = new VLTrainerUpdateListImpl();
    localVLTrainerUpdateListImpl.addNOOPItem(VLTrainer.TrainerItemType.SONG);
    sendUpdate(localVLTrainerUpdateListImpl, paramString, paramVLTrainerListener, true);
  }

  public void onCancelled(HttpRequest paramHttpRequest)
  {
  }

  void onDestroy()
  {
    this.mTrainerListener = null;
  }

  public void onFailure(HttpRequest paramHttpRequest)
  {
    this.mNotificationHandler.notifyError(VLTrainerErrors.ERROR_NETWORK, "Network error");
  }

  public void onResponse(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse)
  {
    if (paramHttpResponse.responseCode == 200)
    {
      HashMap localHashMap = LMTTComm.parseLMTTResponse(paramHttpResponse);
      if ((localHashMap != null) && (localHashMap.size() > 0))
        this.mNotificationHandler.notifySuccess(lmttType2TrainerType(localHashMap));
    }
    while (true)
    {
      return;
      this.mNotificationHandler.notifyError(VLTrainerErrors.ERROR_SERVER, paramHttpResponse.getDataAsString());
    }
  }

  public boolean onTimeout(HttpRequest paramHttpRequest)
  {
    this.mNotificationHandler.notifyError(VLTrainerErrors.ERROR_NETWORK_TIMEOUT, "Timeout waiting for request");
    return true;
  }

  public void onWillExecuteRequest(HttpRequest paramHttpRequest)
  {
  }

  public void sendFullUpdate(VLTrainerUpdateList paramVLTrainerUpdateList, String paramString, VLTrainerListener paramVLTrainerListener)
  {
    sendUpdate(paramVLTrainerUpdateList, paramString, paramVLTrainerListener, true);
  }

  public void sendPartialUpdate(VLTrainerUpdateList paramVLTrainerUpdateList, String paramString, VLTrainerListener paramVLTrainerListener)
  {
    sendUpdate(paramVLTrainerUpdateList, paramString, paramVLTrainerListener, false);
  }

  public void updateTrainerModelLanguage(String paramString, VLTrainerListener paramVLTrainerListener)
  {
    VLTrainerUpdateListImpl localVLTrainerUpdateListImpl = new VLTrainerUpdateListImpl();
    localVLTrainerUpdateListImpl.addNOOPItem(VLTrainer.TrainerItemType.CONTACT);
    localVLTrainerUpdateListImpl.addNOOPItem(VLTrainer.TrainerItemType.SONG);
    sendUpdate(localVLTrainerUpdateListImpl, paramString, paramVLTrainerListener, false);
  }

  private class NotificationHandler extends Handler
  {
    static final int ERROR = 2;
    static final int SUCCESS = 1;

    NotificationHandler()
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      VLTrainerImpl.this.setBusy(false);
      VLTrainerListener localVLTrainerListener = VLTrainerImpl.this.mTrainerListener;
      VLTrainerImpl.access$002(VLTrainerImpl.this, null);
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 2:
      }
      while (true)
      {
        return;
        if (localVLTrainerListener == null)
          continue;
        localVLTrainerListener.onUpdateReceived((HashMap)paramMessage.obj);
        continue;
        if (localVLTrainerListener == null)
          continue;
        localVLTrainerListener.onError((VLTrainerErrors)((Object[])(Object[])paramMessage.obj)[0], (String)((Object[])(Object[])paramMessage.obj)[1]);
      }
    }

    void notifyError(VLTrainerErrors paramVLTrainerErrors, String paramString)
    {
      monitorenter;
      try
      {
        NotificationHandler localNotificationHandler = VLTrainerImpl.this.mNotificationHandler;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramVLTrainerErrors;
        arrayOfObject[1] = paramString;
        localNotificationHandler.obtainMessage(2, arrayOfObject).sendToTarget();
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

    void notifySuccess(HashMap<VLTrainer.TrainerItemType, Integer> paramHashMap)
    {
      monitorenter;
      try
      {
        VLTrainerImpl.this.mNotificationHandler.obtainMessage(1, paramHashMap).sendToTarget();
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
 * Qualified Name:     com.vlingo.sdk.internal.VLTrainerImpl
 * JD-Core Version:    0.6.0
 */