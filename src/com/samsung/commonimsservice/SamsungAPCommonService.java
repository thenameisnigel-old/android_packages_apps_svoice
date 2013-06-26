package com.samsung.commonimsservice;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaScannerConnection;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import com.sec.android.ims.IMSEventListener;
import com.sec.android.internal.ims.IIMSService;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SamsungAPCommonService
  implements ICommonIMSService
{
  private static final boolean DBG = true;
  private static final int IMS_CALL_END_CALL_NW_HANDOVER = 4;
  public static final int IMS_CALL_LOW_BATTERY = 6;
  private static final int IMS_CALL_NORMAL_DISCONNECT = 5;
  public static final String IMS_CONN_ACTION = "com.sec.android.ims.IMSService";
  private static final int IMS_SVC_USER_REJECT_REASON_USR_BUSY = 2;
  private static final int IMS_SVC_USER_REJECT_REASON_USR_DECLINE = 3;
  public static final int IMS_USER_REJECT_REASON_USR_BUSY_CS_CALL = 7;
  private static final String LOG_TAG = "SamsungAPCommonService";
  private static final int MMTELSVCHDL = 0;
  private static final int MSG_IMS_SERVICE_CONNECTED = 101;
  private static final int MSG_IMS_SERVICE_DISCONNECTED = 102;
  private static final String SIPURI_TYPE = "0";
  private static final int SIPURI_VAL = 1;
  private static final String SMS_SIP_SIPURI_PREFIX = "sipuriprefix";
  private static final String SMS_SIP_URI_TYPE = "uritype";
  private static final String TELURI_TYPE = "1";
  private static final int TELURI_VAL = 3;
  private Handler H = new SamsungAPCommonService.1(this);
  private boolean isSpeakerOn = false;
  private AudioManager mAudioManager = null;
  ServiceConnection mConnection = new SamsungAPCommonService.2(this);
  private Context mContext;
  private final List<IIMSCallStateListener> mIMSCallStateListener = new CopyOnWriteArrayList();
  private final List<IIMSRegisterStateListener> mIMSRegListener = new CopyOnWriteArrayList();
  IMSEventListener mImsEventListener = new SamsungAPCommonService.3(this);
  private IIMSService mImsInterface;
  private boolean mMuted = false;
  private final List<IImsServiceConnectionListener> mServiceConnectionListener = new CopyOnWriteArrayList();
  private ImsParams params = new ImsParams();
  private int regExpiry = -1;
  private String regUri = null;
  private boolean registrationStatus = false;
  private boolean serviceConnStatus = false;

  public SamsungAPCommonService(Context paramContext)
  {
    this.mContext = paramContext;
    this.mAudioManager = ((AudioManager)paramContext.getSystemService("audio"));
    createSipService();
  }

  private void createSipService()
  {
    Log.d("SamsungAPCommonService", "Trying to connect to ims service");
    Intent localIntent = new Intent("com.sec.android.ims.IMSService");
    this.mContext.bindService(localIntent, this.mConnection, 1);
  }

  private int getCallType(int paramInt1, int paramInt2)
  {
    int i = -1;
    log("getCallType [" + paramInt2 + "]" + paramInt2);
    if (paramInt1 == 8)
      if (paramInt2 == 5)
        i = 5;
    while (true)
    {
      log("getCallType [" + i + "]" + i);
      return i;
      if (paramInt2 == 2)
      {
        i = 2;
        continue;
      }
      if (paramInt2 == 1)
      {
        i = 1;
        continue;
      }
      if (paramInt2 == 6)
      {
        i = 7;
        continue;
      }
      if (paramInt2 == 8)
      {
        i = 8;
        continue;
      }
      if (paramInt2 != 9)
        continue;
      i = 9;
      continue;
      if (paramInt1 == 4)
      {
        i = 2;
        continue;
      }
      if (paramInt1 != 6)
        continue;
      i = 3;
    }
  }

  private boolean getDeviceSpeakerStatus()
  {
    return ((AudioManager)this.mContext.getSystemService("audio")).isSpeakerphoneOn();
  }

  private boolean isSpeakerOn()
  {
    log("Inside isSpeakerOn  " + this.isSpeakerOn);
    return this.isSpeakerOn;
  }

  private void log(String paramString)
  {
    Log.d("SamsungAPCommonService", paramString);
  }

  private void notifyConnectionListeners(boolean paramBoolean)
  {
    int i = 0;
    if (i < this.mServiceConnectionListener.size())
    {
      IImsServiceConnectionListener localIImsServiceConnectionListener = (IImsServiceConnectionListener)this.mServiceConnectionListener.get(i);
      if (localIImsServiceConnectionListener != null)
      {
        this.serviceConnStatus = paramBoolean;
        if (!paramBoolean)
          break label54;
        localIImsServiceConnectionListener.onConnected();
      }
      while (true)
      {
        i++;
        break;
        label54: localIImsServiceConnectionListener.onDisconnected();
      }
    }
  }

  private void onBadRequest(int paramInt)
  {
    for (int i = 0; i < this.mIMSCallStateListener.size(); i++)
    {
      IIMSCallStateListener localIIMSCallStateListener = (IIMSCallStateListener)this.mIMSCallStateListener.get(i);
      if (localIIMSCallStateListener == null)
        continue;
      localIIMSCallStateListener.onError(paramInt, 400, "Bad Request");
    }
  }

  private void onCaptureSuccess(boolean paramBoolean, String paramString)
  {
    log("inside onCaptureSuccess() : nearEnd=" + paramBoolean + "; filename=" + paramString);
    if (paramString == null)
      return;
    String[] arrayOfString1 = new String[1];
    String[] arrayOfString2 = new String[1];
    arrayOfString1[0] = paramString;
    if (paramBoolean)
      arrayOfString2[0] = "videocallimages/jpeg";
    while (true)
    {
      MediaScannerConnection.scanFile(this.mContext, arrayOfString1, arrayOfString2, new SamsungAPCommonService.4(this));
      break;
      arrayOfString2[0] = "videocallimages/jpeg-scramble";
    }
  }

  private void onImsServiceConnected()
  {
    try
    {
      if (this.mImsInterface == null)
      {
        log("NULL IMS service received!!");
        notifyConnectionListeners(false);
      }
      else
      {
        this.mImsInterface.registerListener(this.mImsEventListener.callback, 0);
        notifyConnectionListeners(true);
      }
    }
    catch (RemoteException localRemoteException)
    {
      log("IMS event listener registration failed!!! with exception e - " + localRemoteException);
      localRemoteException.printStackTrace();
      this.mImsInterface = null;
      notifyConnectionListeners(false);
    }
  }

  private void onImsServiceDisconnected()
  {
    this.mImsInterface = null;
    notifyConnectionListeners(false);
  }

  private ImsParams setUriListForConference(ImsParams paramImsParams, String paramString)
  {
    String[] arrayOfString = paramString.split("\\$");
    if ((arrayOfString != null) && (arrayOfString.length > 0))
    {
      int i = arrayOfString.length;
      for (int j = 0; j <= i - 1; j++)
      {
        log("Individual Uris" + arrayOfString[j]);
        paramImsParams.set("Uri" + (j + 1), arrayOfString[j]);
      }
      paramImsParams.set("UriCount", i);
      log("Individual Uris" + i);
    }
    while (true)
    {
      return paramImsParams;
      log("setUriListForConference returned failure");
    }
  }

  private void updateMuteState(boolean paramBoolean)
  {
    log("Updating mute state to [" + paramBoolean + "]");
    this.mAudioManager.setMicrophoneMute(paramBoolean);
    this.mMuted = paramBoolean;
  }

  public void acceptChangeRequest(int paramInt)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot accept change request R[Service Not Up]");
    this.params.clearAll();
    this.params.set("Type", "accept");
    try
    {
      this.mImsInterface.mmTelSvcCallFuncAsync(15, 0, paramInt, this.params.flatten());
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Accept change request failed R[Service is not up]");
  }

  public int addUserForConferenceCall(int paramInt1, String paramString, int paramInt2)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot downgrade call R[Service Not Up]");
    String str1 = Settings.Secure.getString(this.mContext.getContentResolver(), "sipuriprefix");
    String str2 = Settings.Secure.getString(this.mContext.getContentResolver(), "uritype");
    if (paramString == null)
      throw new IMSException("Cannot make call R[invalid URI (" + paramString + ")]");
    this.params.clearAll();
    this.params.clearAll();
    this.params.set("AppType", 8);
    this.params.set("CallType", 5);
    int i;
    if ((str2 != null) && (!str2.equals("")))
      if ("1".equals(str2))
        i = 3;
    while (true)
    {
      log("DialType [" + str2 + "] DialTypeVal [" + i + "]");
      this.params.set("RmtUriType", i);
      if (str1 != null)
      {
        this.params.set("RmtUriPrefix", "");
        this.params = setUriListForConference(this.params, paramString);
      }
      try
      {
        this.mImsInterface.mmTelSvcCallFuncAsync(21, 0, paramInt1, this.params.flatten());
        return -1;
        i = 1;
        continue;
        i = 1;
        continue;
        log("UriPrefix is NULL");
        this.params.set("RmtUriPrefix", "");
      }
      catch (RemoteException localRemoteException)
      {
      }
    }
    throw new IMSException("Cannot make call R[Service Not Up]");
  }

  public void answerCall(int paramInt)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot answer call R[Service Not Up]");
    this.params.clearAll();
    this.params.set("eVVFtrType", 2);
    try
    {
      this.mImsInterface.mmTelSvcCallFuncAsync(5, 0, paramInt, this.params.flatten());
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Cannot answer call R[Service Not Up]");
  }

  public void answerCallAudioOnly(int paramInt)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot answer call audio only R[Service Not Up]");
    this.params.clearAll();
    this.params.set("eVVFtrType", 1);
    try
    {
      this.mImsInterface.mmTelSvcCallFuncAsync(5, 0, paramInt, this.params.flatten());
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Cannot answer call R[Service Not Up]");
  }

  public void cancelCall(int paramInt1, int paramInt2)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot cancel call R[Service Not Up]");
    if (paramInt2 == 2);
    try
    {
      while (true)
      {
        this.mImsInterface.mmTelSvcCallFuncAsync(4, 0, paramInt1, null);
        return;
        if (paramInt2 == 1)
          continue;
      }
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Can't end the call R[Service is not up]");
  }

  public void captureSurfaceImage(boolean paramBoolean, int paramInt)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot captureSurfaceEndImage");
    try
    {
      this.mImsInterface.captureSurfaceImage(paramBoolean, paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Cannot captureSurfaceEndImage");
  }

  public void changeCall(int paramInt1, int paramInt2, int paramInt3)
    throws IMSException
  {
    if (paramInt3 == 5)
      if ((paramInt2 == 2) || (paramInt2 == 3) || (paramInt2 == 4))
        downgradeCall(paramInt1, paramInt2, paramInt3);
    while (true)
    {
      return;
      if ((paramInt3 == 3) || (paramInt3 == 4))
      {
        if (paramInt2 == 2)
        {
          downgradeCall(paramInt1, paramInt2, paramInt3);
          continue;
        }
        if (paramInt2 != 5)
          continue;
        upgradeCall(paramInt1, paramInt2, paramInt3);
        continue;
      }
      if ((paramInt3 != 2) || ((paramInt2 != 3) && (paramInt2 != 4) && (paramInt2 != 5)))
        continue;
      upgradeCall(paramInt1, paramInt2, paramInt3);
    }
  }

  public void continueVideo(int paramInt)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot continue video R[Service Not Up]");
    this.params.clearAll();
    this.params.set("appType", 7);
    try
    {
      this.mImsInterface.mmTelSvcCallFuncAsync(14, 0, paramInt, this.params.flatten());
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Cannot continue call R[Service Not Up]");
  }

  public void deRegisterForCallStateListener(IIMSCallStateListener paramIIMSCallStateListener)
  {
    log("DeRegistering for call state change listener[" + paramIIMSCallStateListener + "]");
    if (this.mIMSCallStateListener.contains(paramIIMSCallStateListener))
    {
      log("Removing call state change listener[" + paramIIMSCallStateListener + "]");
      this.mIMSCallStateListener.remove(paramIIMSCallStateListener);
    }
  }

  public void deRegisterForRegStateListener(IIMSRegisterStateListener paramIIMSRegisterStateListener)
  {
    log("DeRegistering for register state change listener[" + paramIIMSRegisterStateListener + "]");
    if (this.mIMSRegListener.contains(paramIIMSRegisterStateListener))
    {
      log("Removing register state change listener[" + paramIIMSRegisterStateListener + "]");
      this.mIMSRegListener.remove(paramIIMSRegisterStateListener);
    }
  }

  public void deRegisterForServiceConnectionListener(IImsServiceConnectionListener paramIImsServiceConnectionListener)
  {
    log("DeRegistering for connection state change listener[" + paramIImsServiceConnectionListener + "]");
    if (this.mServiceConnectionListener.contains(paramIImsServiceConnectionListener))
    {
      log("Removing connection state change listener[" + paramIImsServiceConnectionListener + "]");
      this.mServiceConnectionListener.remove(paramIImsServiceConnectionListener);
    }
  }

  public void deinitSurface(boolean paramBoolean)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot deinitSurface");
    try
    {
      this.mImsInterface.deinitSurface(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Cannot deinitSurface");
  }

  public void downgradeCall(int paramInt1, int paramInt2, int paramInt3)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot downgrade call R[Service Not Up]");
    int i;
    if ((paramInt3 == 2) && (paramInt2 == 5))
      i = 1;
    while (true)
    {
      this.params.clearAll();
      this.params.set("appType", i);
      try
      {
        this.mImsInterface.mmTelSvcCallFuncAsync(14, 0, paramInt1, this.params.flatten());
        return;
        if ((paramInt3 == 2) && (paramInt2 == 3))
        {
          i = 3;
          continue;
        }
        if ((paramInt3 == 3) && (paramInt2 == 5))
        {
          i = 4;
          continue;
        }
        if ((paramInt3 == 4) && (paramInt2 == 5))
        {
          i = 4;
          continue;
        }
        throw new IMSException("Down grade is not allowed");
      }
      catch (RemoteException localRemoteException)
      {
      }
    }
    throw new IMSException("Cannot downgrade call R[Service Not Up]");
  }

  public void endCall(int paramInt1, int paramInt2)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot end call R[Service Not Up]");
    if (paramInt2 == 2);
    while (true)
    {
      this.params.clearAll();
      this.params.set("eDisconnectRsn", 5);
      try
      {
        this.mImsInterface.mmTelSvcCallFuncAsync(3, 0, paramInt1, this.params.flatten());
        return;
        if (paramInt2 == 1)
          continue;
      }
      catch (RemoteException localRemoteException)
      {
      }
    }
    throw new IMSException("Can't end the call R[Service is not up]");
  }

  public void endCall(int paramInt1, int paramInt2, int paramInt3)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot end call R[Service Not Up]");
    if (paramInt2 == 2);
    while (true)
    {
      this.params.clearAll();
      if (paramInt3 == 4)
      {
        log("End Call due to reason IMS_CALL_END_CALL_NW_HANDOVER" + paramInt3);
        this.params.set("eDisconnectRsn", paramInt3);
      }
      try
      {
        while (true)
        {
          this.mImsInterface.mmTelSvcCallFuncAsync(3, 0, paramInt1, this.params.flatten());
          return;
          if (paramInt2 == 1)
            break;
          break;
          if (paramInt3 != 6)
            continue;
          log("End Call due to reason IMS_CALL_LOW_BATTERY" + paramInt3);
          this.params.set("eDisconnectRsn", paramInt3);
        }
      }
      catch (RemoteException localRemoteException)
      {
      }
    }
    throw new IMSException("Can't end call R[Service is not up]");
  }

  public String getCurrentLatchedNetwork()
  {
    Object localObject = null;
    if (this.mImsInterface == null)
      log("Ims interface is null !!");
    while (true)
    {
      return localObject;
      try
      {
        String str = this.mImsInterface.getCurrentLatchedNetwork();
        localObject = str;
      }
      catch (RemoteException localRemoteException)
      {
        localRemoteException.printStackTrace();
      }
    }
  }

  public int getMaxVolume(int paramInt)
  {
    AudioManager localAudioManager = this.mAudioManager;
    return localAudioManager.getStreamMaxVolume(0);
  }

  public void holdCall(int paramInt)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot hold call R[Service Not Up]");
    this.params.clearAll();
    this.params.set("appType", 8);
    try
    {
      this.mImsInterface.mmTelSvcCallFuncAsync(8, 0, paramInt, this.params.flatten());
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Cannot hold call R[Service Not Up]");
  }

  public void holdVideo(int paramInt)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot hold call R[Service Not Up]");
    this.params.clearAll();
    this.params.set("appType", 6);
    try
    {
      this.mImsInterface.mmTelSvcCallFuncAsync(14, 0, paramInt, this.params.flatten());
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Cannot hold call: Service Not Up");
  }

  public boolean isDeviceOnEHRPD()
  {
    int i = 0;
    if (this.mImsInterface != null);
    try
    {
      boolean bool = this.mImsInterface.isOnEHRPD();
      i = bool;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        i = 0;
        localRemoteException.printStackTrace();
      }
    }
  }

  public boolean isDeviceOnLTE()
  {
    int i = 0;
    if (this.mImsInterface != null);
    try
    {
      boolean bool = this.mImsInterface.isOnLTE();
      i = bool;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        i = 0;
        localRemoteException.printStackTrace();
      }
    }
  }

  public boolean isFrontCamInUse()
    throws IMSException
  {
    int i = 0;
    if (this.mImsInterface != null);
    try
    {
      int j = this.mImsInterface.isFrontCamInUse();
      if (j == 1);
      for (i = 1; ; i = 0)
        return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        i = 0;
        localRemoteException.printStackTrace();
      }
    }
  }

  public boolean isIMSEnabledOnWifi()
  {
    int i = 0;
    if (this.mImsInterface == null)
      log("Ims interface is null !!");
    while (true)
    {
      return i;
      try
      {
        boolean bool = this.mImsInterface.isIMSEnabledOnWifi();
        i = bool;
      }
      catch (RemoteException localRemoteException)
      {
        localRemoteException.printStackTrace();
      }
    }
  }

  public boolean isImsForbidden()
    throws IMSException
  {
    if (this.mImsInterface == null)
    {
      log("Ims interface is null !!");
      throw new IMSException("Cannot check for isImsForbidden");
    }
    try
    {
      boolean bool = this.mImsInterface.isImsForbidden();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
    throw new IMSException("Cannot check for isImsForbidden");
  }

  public boolean isMuted(int paramInt)
  {
    log("isMuted = " + this.mAudioManager.isMicrophoneMute());
    return this.mAudioManager.isMicrophoneMute();
  }

  public int makeMediaCall(String paramString1, int paramInt, String paramString2)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot make media call R[Service Not Up]");
    String str1 = Settings.Secure.getString(this.mContext.getContentResolver(), "sipuriprefix");
    String str2 = Settings.Secure.getString(this.mContext.getContentResolver(), "uritype");
    if (paramString1 == null)
      throw new IMSException("Cannot make call R[invalid URI (" + paramString1 + ")]");
    if (paramString1.endsWith("#"));
    this.params.clearAll();
    if (paramInt == 5)
    {
      this.params.set("AppType", 8);
      this.params.set("CallType", 5);
    }
    while (true)
    {
      this.params.set("RmtDialNum", paramString1);
      if (paramInt == 7)
        this.params = setUriListForConference(this.params, paramString1);
      int i;
      label188: IMSEventListener localIMSEventListener;
      if ((str2 != null) && (!str2.equals("")))
        if ("1".equals(str2))
        {
          i = 3;
          log("DialType [" + str2 + "] DialTypeVal [" + i + "]");
          this.params.set("RmtUriType", i);
          if (str1 == null)
            break label449;
          this.params.set("RmtUriPrefix", "");
          label259: if (!TextUtils.isEmpty(paramString2))
          {
            log(" DisplayName (PLetteringText) - " + paramString2);
            this.params.set("DisplayName", paramString2);
          }
          localIMSEventListener = this.mImsEventListener;
          monitorenter;
          if (paramInt != 7)
            break label472;
        }
      try
      {
        int m = this.mImsInterface.mmTelSvcCallFunc(20, 0, 0, this.params.flatten(), false);
        label449: label472: int j;
        for (int k = m; ; k = j)
        {
          return k;
          if (paramInt == 1)
          {
            this.params.set("AppType", 8);
            this.params.set("CallType", 1);
            break;
          }
          if (paramInt == 7)
          {
            this.params.set("AppType", 8);
            this.params.set("CallType", 5);
            break;
          }
          this.params.set("AppType", 4);
          this.params.set("CallType", 2);
          break;
          i = 1;
          break label188;
          i = 1;
          break label188;
          log("UriPrefix is NULL");
          this.params.set("RmtUriPrefix", "");
          break label259;
          j = this.mImsInterface.mmTelSvcCallFunc(2, 0, 0, this.params.flatten(), false);
        }
      }
      catch (RemoteException localRemoteException)
      {
        throw new IMSException("Cannot make call R[Service Not Up]");
      }
      finally
      {
        monitorexit;
      }
    }
    throw localObject;
  }

  public void registerForCallStateListener(IIMSCallStateListener paramIIMSCallStateListener)
  {
    log("Register call state change listener [" + paramIIMSCallStateListener + "]");
    if (!this.mIMSCallStateListener.contains(paramIIMSCallStateListener))
    {
      log("Adding [" + paramIIMSCallStateListener + "] for call state change");
      this.mIMSCallStateListener.add(paramIIMSCallStateListener);
    }
    while (true)
    {
      return;
      log("Can't registered for call state change");
    }
  }

  public void registerForRegStateListener(IIMSRegisterStateListener paramIIMSRegisterStateListener)
  {
    log("Register register status state change listener [" + paramIIMSRegisterStateListener + "]");
    if (!this.mIMSRegListener.contains(paramIIMSRegisterStateListener))
    {
      log("Adding [" + paramIIMSRegisterStateListener + "] for register state change");
      this.mIMSRegListener.add(paramIIMSRegisterStateListener);
    }
    while (true)
    {
      return;
      log("Can't registered for register status state change");
    }
  }

  public void registerForServiceConnectionListener(IImsServiceConnectionListener paramIImsServiceConnectionListener)
  {
    log("Register connection status state change listener [" + paramIImsServiceConnectionListener + "]");
    if (!this.mServiceConnectionListener.contains(paramIImsServiceConnectionListener))
    {
      log("Adding [" + paramIImsServiceConnectionListener + "] for connection state change");
      this.mServiceConnectionListener.add(paramIImsServiceConnectionListener);
      if (!this.serviceConnStatus)
        break label97;
      paramIImsServiceConnectionListener.onConnected();
    }
    while (true)
    {
      return;
      label97: paramIImsServiceConnectionListener.onDisconnected();
    }
  }

  public void rejectCall(int paramInt1, int paramInt2)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot reject call R[Service Not Up]");
    int i;
    if (paramInt2 == 2)
      i = 2;
    while (true)
    {
      this.params.clearAll();
      this.params.set("eRejectRsn", 3);
      this.params.set("eVVFtrType", i);
      try
      {
        this.mImsInterface.mmTelSvcCallFuncAsync(6, 0, paramInt1, this.params.flatten());
        return;
        if (paramInt2 == 1)
        {
          i = 1;
          continue;
        }
        i = 5;
      }
      catch (RemoteException localRemoteException)
      {
      }
    }
    throw new IMSException("Can't reject call R[Service is not up]");
  }

  public void rejectCall(int paramInt1, int paramInt2, int paramInt3)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot reject call R[Service Not Up]");
    int i;
    if (paramInt2 == 2)
      i = 2;
    while (true)
    {
      this.params.clearAll();
      if (paramInt3 == 4)
      {
        log("Reject Call due to reason IMS_CALL_END_CALL_NW_HANDOVER: reason[" + paramInt3 + "]");
        this.params.set("eRejectRsn", paramInt3);
        label78: this.params.set("eVVFtrType", i);
      }
      try
      {
        this.mImsInterface.mmTelSvcCallFuncAsync(6, 0, paramInt1, this.params.flatten());
        return;
        if (paramInt2 == 1)
        {
          i = 1;
          continue;
        }
        i = 5;
        continue;
        if (paramInt3 == 6)
        {
          log("Reject Call due to reason IMS_CALL_LOW_BATTERY: reason[" + paramInt3 + "]");
          this.params.set("eRejectRsn", paramInt3);
          break label78;
        }
        if ((paramInt3 != 2) && (paramInt3 != 7))
          break label78;
        log("Reject Call due to reason IMS_SVC_USER_REJECT_REASON_USR_BUSY: reason[" + paramInt3 + "]");
        this.params.set("eRejectRsn", 2);
      }
      catch (RemoteException localRemoteException)
      {
      }
    }
    throw new IMSException("Can't reject call R[Service is not up]");
  }

  public void rejectChangeRequest(int paramInt)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot reject change request R[Service Not Up]");
    this.params.clearAll();
    this.params.set("Type", "reject");
    try
    {
      this.mImsInterface.mmTelSvcCallFuncAsync(16, 0, paramInt, this.params.flatten());
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Reject change request failed R[Service is not up]");
  }

  public void resetCameraID()
    throws IMSException
  {
    if (this.mImsInterface != null);
    while (true)
    {
      try
      {
        this.mImsInterface.resetCameraID();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new IMSException("Cannot upgrade call R[Service Not Up]");
      }
      log("Ims interface is null stop we can not reset camera ID now!!");
    }
  }

  public void resumeCall(int paramInt)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot resume call R[Service Not Up]");
    this.params.clearAll();
    this.params.set("appType", 8);
    this.params.set("ssId", 0);
    try
    {
      this.mImsInterface.mmTelSvcCallFuncAsync(11, 0, paramInt, this.params.flatten());
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Cannot continue call R[Service Not Up]");
  }

  public boolean sendDtmf(int paramInt1, int paramInt2)
  {
    int j;
    if (this.mImsInterface == null)
      j = 0;
    while (true)
    {
      return j;
      this.params.clearAll();
      this.params.set("keyvalue", paramInt2);
      this.params.set("KeyeventType", 1);
      try
      {
        this.mImsInterface.mmTelSvcCallFuncAsync(7, 0, paramInt1, this.params.flatten());
        i = 1;
        j = i;
      }
      catch (RemoteException localRemoteException)
      {
        while (true)
          int i = 0;
      }
    }
  }

  public void sendLiveVideo()
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot swipe video surface R[Service Not Up]");
    try
    {
      this.mImsInterface.sendLiveVideo();
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Cannot upgrade call R[Service Not Up]");
  }

  public void sendStillImage(String paramString)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot swipe video surface R[Service Not Up]");
    try
    {
      this.mImsInterface.sendStillImage(paramString);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Cannot upgrade call R[Service Not Up]");
  }

  public void setAudioMode(int paramInt)
  {
    if (this.mImsInterface == null)
      log("Ims interface is null !!");
    while (true)
    {
      return;
      try
      {
        this.mImsInterface.setAudioMode(paramInt);
      }
      catch (RemoteException localRemoteException)
      {
        localRemoteException.printStackTrace();
      }
    }
  }

  public void setAutoResponse(int paramInt1, int paramInt2)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot set auto response R[Service Not Up]");
    if (paramInt2 == -1);
    while (true)
    {
      return;
      try
      {
        this.mImsInterface.mmTelSvcCallFuncAsync(17, paramInt1, paramInt2, null);
      }
      catch (RemoteException localRemoteException)
      {
      }
    }
    throw new IMSException("Cannot make call R[Service Not Up]");
  }

  public void setCameraOrientation(int paramInt)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot hold call R[Service Not Up]");
    try
    {
      this.mImsInterface.setOrientation(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Cannot make call R[Service Not Up]");
  }

  public void setDisplay(int paramInt, SurfaceHolder paramSurfaceHolder, String paramString)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot set display R[Service Not Up]");
    try
    {
      this.mImsInterface.startVideoRenderer(paramSurfaceHolder.getSurface(), paramSurfaceHolder.getSurfaceFrame().width(), paramSurfaceHolder.getSurfaceFrame().height(), paramString);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Can't set display R[Service Not Up]");
  }

  public void setSpeakerMode(int paramInt, boolean paramBoolean)
  {
    this.mAudioManager.setSpeakerphoneOn(paramBoolean);
  }

  public void setVolume(int paramInt1, int paramInt2)
  {
    if (paramInt2 < 0);
    while (true)
    {
      return;
      if (paramInt2 > getMaxVolume(paramInt1))
      {
        AudioManager localAudioManager2 = this.mAudioManager;
        localAudioManager2.setStreamVolume(0, getMaxVolume(paramInt1), 0);
        continue;
      }
      AudioManager localAudioManager1 = this.mAudioManager;
      localAudioManager1.setStreamVolume(0, paramInt2, 0);
    }
  }

  public void startAudio(int paramInt)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot hold call R[Service Not Up]");
    try
    {
      this.mImsInterface.startAudio();
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Start audio failed R[Service is not up]");
  }

  public int startCamera(int paramInt1, int paramInt2, SurfaceHolder paramSurfaceHolder, boolean paramBoolean1, boolean paramBoolean2, String paramString)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot start camera R[Service Not Up]");
    if (paramSurfaceHolder != null);
    try
    {
      this.mImsInterface.startCamera(paramSurfaceHolder.getSurface(), paramSurfaceHolder.getSurfaceFrame().width(), paramSurfaceHolder.getSurfaceFrame().height(), paramInt2, paramBoolean1, paramBoolean2, paramString);
      break label100;
      this.mImsInterface.startCamera(null, 0, 0, paramInt2, paramBoolean1, paramBoolean2, paramString);
    }
    catch (RemoteException localRemoteException)
    {
      throw new IMSException("Camera cannot be acquired");
    }
    label100: return 0;
  }

  public void startMedia(int paramInt)
    throws IMSException
  {
    startAudio(paramInt);
    startVideo(paramInt);
  }

  public void startVideo(int paramInt)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Start video failed R[Service not up]");
    try
    {
      this.mImsInterface.startVideo();
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Start video failed R[Service not up]");
  }

  public int stopCamera(int paramInt)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot stop camera R[Service Not Up]");
    try
    {
      this.mImsInterface.stopCamera();
      return 0;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
    throw new IMSException("Camera not stopped");
  }

  public void swapVideoSurface(int paramInt)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot swipe video surface R[Service Not Up]");
    try
    {
      this.mImsInterface.swapVideoSurface();
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Cannot upgrade call R[Service Not Up]");
  }

  public void switchCamera()
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot switch camera R[Service Not Up]");
    try
    {
      this.mImsInterface.switchCamera();
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Camera cannot be acquired");
  }

  public void toggleMute(int paramInt)
  {
    log("Toggle mute before = " + this.mAudioManager.isMicrophoneMute());
    if (this.mAudioManager.isMicrophoneMute())
      this.mAudioManager.setMicrophoneMute(false);
    while (true)
    {
      log("Toggle mute after = " + this.mAudioManager.isMicrophoneMute());
      return;
      this.mAudioManager.setMicrophoneMute(true);
    }
  }

  public void upgradeCall(int paramInt1, int paramInt2, int paramInt3)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot upgrade call R[Service Not Up]");
    this.params.clearAll();
    int j;
    if (5 == paramInt3)
      if (3 == paramInt2)
      {
        j = 5;
        this.params.set("appType", j);
      }
    do
      try
      {
        this.mImsInterface.mmTelSvcCallFuncAsync(14, 0, paramInt1, this.params.flatten());
        return;
        j = 0;
      }
      catch (RemoteException localRemoteException2)
      {
        throw new IMSException("Upgrade failed R[Service is not up]");
      }
    while (paramInt3 != 3);
    if (3 == paramInt2);
    for (int i = 7; ; i = 2)
      while (true)
      {
        this.params.set("appType", i);
        try
        {
          this.mImsInterface.mmTelSvcCallFuncAsync(19, 0, paramInt1, this.params.flatten());
        }
        catch (RemoteException localRemoteException1)
        {
          throw new IMSException("Cannot upgrade call R[Service Not Up]");
        }
      }
  }

  public void voiceRecord(int paramInt1, int paramInt2, String paramString)
    throws IMSException
  {
    if (this.mImsInterface == null)
      throw new IMSException("Cannot start camera R[Service Not Up]");
    try
    {
      this.mImsInterface.voiceRecord(paramInt1, paramString);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IMSException("Camera cannot be acquired");
  }

  public void writeErrorData(String paramString1, String paramString2)
  {
    if (this.mImsInterface == null);
    while (true)
    {
      return;
      try
      {
        log("Ims interface is writeErrorData... !!errorCode" + paramString1 + "...errorString" + paramString2);
        this.mImsInterface.writeErrorData(paramString1, paramString2);
      }
      catch (RemoteException localRemoteException)
      {
        localRemoteException.printStackTrace();
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.commonimsservice.SamsungAPCommonService
 * JD-Core Version:    0.6.0
 */