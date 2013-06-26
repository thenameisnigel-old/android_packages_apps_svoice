package com.samsung.commonimsservice;

import android.view.SurfaceHolder;

public abstract interface ICommonIMSService
{
  public abstract void acceptChangeRequest(int paramInt)
    throws IMSException;

  public abstract int addUserForConferenceCall(int paramInt1, String paramString, int paramInt2)
    throws IMSException;

  public abstract void answerCall(int paramInt)
    throws IMSException;

  public abstract void answerCallAudioOnly(int paramInt)
    throws IMSException;

  public abstract void cancelCall(int paramInt1, int paramInt2)
    throws IMSException;

  public abstract void captureSurfaceImage(boolean paramBoolean, int paramInt)
    throws IMSException;

  public abstract void changeCall(int paramInt1, int paramInt2, int paramInt3)
    throws IMSException;

  public abstract void continueVideo(int paramInt)
    throws IMSException;

  public abstract void deRegisterForCallStateListener(IIMSCallStateListener paramIIMSCallStateListener);

  public abstract void deRegisterForRegStateListener(IIMSRegisterStateListener paramIIMSRegisterStateListener);

  public abstract void deRegisterForServiceConnectionListener(IImsServiceConnectionListener paramIImsServiceConnectionListener);

  public abstract void deinitSurface(boolean paramBoolean)
    throws IMSException;

  public abstract void downgradeCall(int paramInt1, int paramInt2, int paramInt3)
    throws IMSException;

  public abstract void endCall(int paramInt1, int paramInt2)
    throws IMSException;

  public abstract void endCall(int paramInt1, int paramInt2, int paramInt3)
    throws IMSException;

  public abstract String getCurrentLatchedNetwork();

  public abstract int getMaxVolume(int paramInt);

  public abstract void holdCall(int paramInt)
    throws IMSException;

  public abstract void holdVideo(int paramInt)
    throws IMSException;

  public abstract boolean isDeviceOnEHRPD();

  public abstract boolean isDeviceOnLTE();

  public abstract boolean isFrontCamInUse()
    throws IMSException;

  public abstract boolean isIMSEnabledOnWifi();

  public abstract boolean isImsForbidden()
    throws IMSException;

  public abstract boolean isMuted(int paramInt);

  public abstract int makeMediaCall(String paramString1, int paramInt, String paramString2)
    throws IMSException;

  public abstract void registerForCallStateListener(IIMSCallStateListener paramIIMSCallStateListener);

  public abstract void registerForRegStateListener(IIMSRegisterStateListener paramIIMSRegisterStateListener);

  public abstract void registerForServiceConnectionListener(IImsServiceConnectionListener paramIImsServiceConnectionListener);

  public abstract void rejectCall(int paramInt1, int paramInt2)
    throws IMSException;

  public abstract void rejectCall(int paramInt1, int paramInt2, int paramInt3)
    throws IMSException;

  public abstract void rejectChangeRequest(int paramInt)
    throws IMSException;

  public abstract void resetCameraID()
    throws IMSException;

  public abstract void resumeCall(int paramInt)
    throws IMSException;

  public abstract boolean sendDtmf(int paramInt1, int paramInt2);

  public abstract void sendLiveVideo()
    throws IMSException;

  public abstract void sendStillImage(String paramString)
    throws IMSException;

  public abstract void setAudioMode(int paramInt);

  public abstract void setAutoResponse(int paramInt1, int paramInt2)
    throws IMSException;

  public abstract void setCameraOrientation(int paramInt)
    throws IMSException;

  public abstract void setDisplay(int paramInt, SurfaceHolder paramSurfaceHolder, String paramString)
    throws IMSException;

  public abstract void setSpeakerMode(int paramInt, boolean paramBoolean);

  public abstract void setVolume(int paramInt1, int paramInt2);

  public abstract void startAudio(int paramInt)
    throws IMSException;

  public abstract int startCamera(int paramInt1, int paramInt2, SurfaceHolder paramSurfaceHolder, boolean paramBoolean1, boolean paramBoolean2, String paramString)
    throws IMSException;

  public abstract void startMedia(int paramInt)
    throws IMSException;

  public abstract void startVideo(int paramInt)
    throws IMSException;

  public abstract int stopCamera(int paramInt)
    throws IMSException;

  public abstract void swapVideoSurface(int paramInt)
    throws IMSException;

  public abstract void switchCamera()
    throws IMSException;

  public abstract void toggleMute(int paramInt);

  public abstract void upgradeCall(int paramInt1, int paramInt2, int paramInt3)
    throws IMSException;

  public abstract void voiceRecord(int paramInt1, int paramInt2, String paramString)
    throws IMSException;
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.commonimsservice.ICommonIMSService
 * JD-Core Version:    0.6.0
 */