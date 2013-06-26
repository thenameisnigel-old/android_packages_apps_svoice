package com.sec.android.facedetection;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;

public class FaceDetectionManager
{
  public static final String FACESERVICE_DISABLED = "faceservice_disabled";
  public static final String FACESERVICE_ENABLED = "faceservice_enabled";
  public static final String FACESERVICE_FAILED = "faceservice_failed";
  public static final String FACE_SERVICE = "samsung.facedetection_service";
  private static final String TAG = "FaceDetectionManager";
  private FaceDetectionClient mClient = null;
  private IFaceDetectionService mService = null;

  public FaceDetectionManager(IFaceDetectionService paramIFaceDetectionService)
  {
    this.mService = paramIFaceDetectionService;
    this.mClient = new FaceDetectionClient();
  }

  public static FaceDetectionManager getFaceDetectionManager()
  {
    IBinder localIBinder = ServiceManager.getService("samsung.facedetection_service");
    FaceDetectionManager localFaceDetectionManager;
    if (localIBinder == null)
    {
      Log.e("FaceDetectionManager", "Fail binding the service; facedetection service may not be running properly.");
      localFaceDetectionManager = null;
    }
    while (true)
    {
      return localFaceDetectionManager;
      localFaceDetectionManager = new FaceDetectionManager(IFaceDetectionService.Stub.asInterface(localIBinder));
      Log.d("FaceDetectionManager", "A new instance of FaceDetectionManager is created");
    }
  }

  public void disable()
  {
    try
    {
      this.mService.disable(this.mClient);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        localRemoteException.printStackTrace();
    }
  }

  public void enable()
  {
    try
    {
      this.mService.enable(this.mClient);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        localRemoteException.printStackTrace();
    }
  }

  public SecFace[] getFaceInfo()
  {
    try
    {
      SecFace[] arrayOfSecFace2 = this.mService.getFaceInfo();
      arrayOfSecFace1 = arrayOfSecFace2;
      return arrayOfSecFace1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        localRemoteException.printStackTrace();
        SecFace[] arrayOfSecFace1 = null;
      }
    }
  }

  public SecFace[] getFaceInfo(int paramInt1, int paramInt2)
  {
    try
    {
      SecFace[] arrayOfSecFace2 = this.mService.getFaceInfoHint(paramInt1, paramInt2);
      arrayOfSecFace1 = arrayOfSecFace2;
      return arrayOfSecFace1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        localRemoteException.printStackTrace();
        SecFace[] arrayOfSecFace1 = null;
      }
    }
  }

  private class FaceDetectionClient extends IFaceDetectionClient.Stub
  {
    FaceDetectionClient()
    {
      Log.e("FaceDetectionManager", "New FaceDetectionClient");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.facedetection.FaceDetectionManager
 * JD-Core Version:    0.6.0
 */