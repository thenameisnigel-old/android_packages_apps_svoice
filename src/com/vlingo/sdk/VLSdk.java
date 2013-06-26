package com.vlingo.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.vlingo.sdk.internal.AndroidServerDetails;
import com.vlingo.sdk.internal.VLComponentManager;
import com.vlingo.sdk.internal.audio.AudioDevice;
import com.vlingo.sdk.internal.core.ApplicationAdapter;
import com.vlingo.sdk.internal.deviceinfo.PhoneInfo;
import com.vlingo.sdk.internal.http.HttpManager;
import com.vlingo.sdk.internal.http.cookies.AndroidCookieJar;
import com.vlingo.sdk.internal.http.cookies.CookieJarFactory;
import com.vlingo.sdk.internal.http.cookies.CookieJarManagerSingleton;
import com.vlingo.sdk.internal.location.LocationUtils;
import com.vlingo.sdk.internal.net.ConnectionManager;
import com.vlingo.sdk.internal.recognizer.ClientMeta;
import com.vlingo.sdk.internal.recognizer.SoftwareMeta;
import com.vlingo.sdk.internal.util.FileUtils;
import com.vlingo.sdk.internal.util.StringUtils;
import com.vlingo.sdk.internal.util.TimerSingleton;
import com.vlingo.sdk.internal.vlservice.AndroidVLServiceCookieManager;
import com.vlingo.sdk.recognition.VLRecognizer;
import com.vlingo.sdk.recognition.speech.VLSpeechDetector;
import com.vlingo.sdk.recognition.spotter.VLSpotter;
import com.vlingo.sdk.services.VLServices;
import com.vlingo.sdk.training.VLTrainer;
import com.vlingo.sdk.tts.VLTextToSpeech;
import com.vlingo.sdk.util.SDKDebugSettings;
import java.io.File;

public class VLSdk
{
  private static final String CHINESE_APP_CHANNEL = "Preinstall Free China";
  public static final String SENSORY = "USE_SENSORY_2.0";
  private static final String USE_SENSORY_2 = "USE_SENSORY_2.0";
  public static String VERSION = "2.0.473";
  public static boolean smAssetsExtracted = false;
  private static VLSdk smInstance;
  private VLComponentManager mComponentManager;
  private SDKDebugSettings mDebugSettings;
  private boolean mIsInvalid = false;
  private boolean mLocationOn;
  private String mLogServerURI;
  private String mRecoServerURI;
  private Handler mSdkHandler;
  private String mTTSServerURI;

  private VLSdk(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, SDKDebugSettings paramSDKDebugSettings)
  {
    if (paramContext == null)
      throw new IllegalArgumentException("context cannot be null");
    if (StringUtils.isNullOrWhiteSpace(paramString1))
      throw new IllegalArgumentException("appId must be specified");
    if (StringUtils.isNullOrWhiteSpace(paramString2))
      throw new IllegalArgumentException("appName must be specified");
    if (StringUtils.isNullOrWhiteSpace(paramString3))
      throw new IllegalArgumentException("appVersion must be specified");
    if (StringUtils.isNullOrWhiteSpace(paramString4))
      throw new IllegalArgumentException("appChannel must be specified");
    if (StringUtils.isNullOrWhiteSpace(paramString5))
      throw new IllegalArgumentException("recoServerURI must be specified");
    if (StringUtils.isNullOrWhiteSpace(paramString6))
      throw new IllegalArgumentException("ttsServerURI must be specified");
    if (StringUtils.isNullOrWhiteSpace(paramString7))
      throw new IllegalArgumentException("logServerURI must be specified");
    if (StringUtils.isNullOrWhiteSpace(paramString8))
      throw new IllegalArgumentException("helloServerURI must be specified");
    if (StringUtils.isNullOrWhiteSpace(paramString9))
      throw new IllegalArgumentException("lmttServerURI must be specified");
    HandlerThread localHandlerThread = new HandlerThread("SDKWorker");
    localHandlerThread.start();
    this.mSdkHandler = new Handler(localHandlerThread.getLooper());
    this.mRecoServerURI = paramString5;
    this.mTTSServerURI = paramString6;
    this.mLogServerURI = paramString7;
    this.mLocationOn = paramBoolean;
    this.mDebugSettings = paramSDKDebugSettings;
    AndroidServerDetails.setServerName(paramString5);
    AndroidServerDetails.setTTSServerName(this.mTTSServerURI);
    AndroidServerDetails.setLogServerName(paramString7);
    AndroidServerDetails.setHelloServerName(paramString8);
    AndroidServerDetails.setLMTTServerName(paramString9);
    ApplicationAdapter.getInstance().init(paramContext.getApplicationContext());
    SoftwareMeta.getInstance().setAppId(paramString1);
    SoftwareMeta.getInstance().setAppName(paramString2);
    SoftwareMeta.getInstance().setAppVersion(paramString3);
    SoftwareMeta.getInstance().setAppChannel(paramString4);
    CookieJarManagerSingleton.setCookieJarManagerImpl(AndroidVLServiceCookieManager.class);
    CookieJarFactory.setCookieJarImpl(AndroidCookieJar.class);
    File localFile1;
    File localFile2;
    if (!smAssetsExtracted)
    {
      localFile1 = paramContext.getDir("vlsdk_lib", 0);
      if (!isSensory2Using())
        break label399;
      FileUtils.extractAssetZipIfNeeded("vlsdk_lib_s2.0.zip", localFile1);
      localFile2 = paramContext.getDir("vlsdk_raw", 0);
      if (!isSensory2Using())
        break label409;
      FileUtils.extractAssetZipIfNeeded("vlsdk_acoustic_raw_s2.0.zip", localFile2);
    }
    while (true)
    {
      FileUtils.extractAssetZipIfNeeded("vlsdk_lts_raw.zip", localFile2);
      smAssetsExtracted = true;
      this.mComponentManager = new VLComponentManager(this.mSdkHandler);
      return;
      label399: FileUtils.extractAssetZipIfNeeded("vlsdk_lib.zip", localFile1);
      break;
      label409: FileUtils.extractAssetZipIfNeeded("vlsdk_acoustic_raw.zip", localFile2);
    }
  }

  public static VLSdk create(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, SDKDebugSettings paramSDKDebugSettings)
  {
    if (smInstance != null)
      throw new IllegalStateException("singelton VLSdk already exists!");
    smInstance = new VLSdk(paramContext, paramString1, paramString2, paramString3, paramString4, paramBoolean, paramString5, paramString6, paramString7, paramString8, paramString9, paramSDKDebugSettings);
    return smInstance;
  }

  public static VLSdk getInstance()
  {
    if (smInstance == null)
      throw new IllegalStateException("VLSdk not initialized!");
    return smInstance;
  }

  public static boolean isSensory2Using()
  {
    return "USE_SENSORY_2.0".equals("USE_SENSORY_2.0");
  }

  private void validateInstance()
  {
    if (this.mIsInvalid)
      throw new IllegalStateException("VLSdk instance is no longer valid!");
  }

  public void destroy()
  {
    validateInstance();
    this.mComponentManager.destroyAll();
    ApplicationAdapter.destroy();
    SoftwareMeta.destroy();
    ClientMeta.destroy();
    AudioDevice.destroy();
    HttpManager.destroy();
    PhoneInfo.destroy();
    LocationUtils.destroy();
    TimerSingleton.destroy();
    ConnectionManager.destroy();
    CookieJarManagerSingleton.cleanup();
    CookieJarFactory.cleanup();
    this.mSdkHandler.getLooper().quit();
    this.mSdkHandler = null;
    this.mIsInvalid = true;
    smInstance = null;
  }

  public String getAppChannel()
  {
    return SoftwareMeta.getInstance().getAppChannel();
  }

  public String getAppId()
  {
    return SoftwareMeta.getInstance().getAppId();
  }

  public String getAppName()
  {
    return SoftwareMeta.getInstance().getName();
  }

  public String getAppVersion()
  {
    return SoftwareMeta.getInstance().getVersion();
  }

  public SDKDebugSettings getDebugSettings()
  {
    return this.mDebugSettings;
  }

  public String getDeviceID()
  {
    validateInstance();
    return ClientMeta.getInstance().getDeviceID();
  }

  public boolean getLocationOn()
  {
    validateInstance();
    return this.mLocationOn;
  }

  public String getLogServerURI()
  {
    validateInstance();
    return this.mLogServerURI;
  }

  public String getRecoServerURI()
  {
    validateInstance();
    return this.mRecoServerURI;
  }

  public VLRecognizer getRecognizer()
  {
    validateInstance();
    return this.mComponentManager.getRecognizer();
  }

  public VLSpeechDetector getSpeechDetector()
  {
    validateInstance();
    return this.mComponentManager.getSpeechDetector();
  }

  public VLSpotter getSpotter()
  {
    validateInstance();
    return this.mComponentManager.getSpotter();
  }

  public String getTTSServerURI()
  {
    validateInstance();
    return this.mTTSServerURI;
  }

  public VLTextToSpeech getTextToSpeech()
  {
    validateInstance();
    return this.mComponentManager.getTextToSpeech();
  }

  public VLTrainer getTrainer()
  {
    validateInstance();
    return this.mComponentManager.getTrainer();
  }

  public VLServices getVLServices()
  {
    validateInstance();
    return this.mComponentManager.getVLServices();
  }

  public String getVersion()
  {
    return VERSION;
  }

  public boolean isSendingLocationEnabled()
  {
    validateInstance();
    if (!getAppChannel().equalsIgnoreCase("Preinstall Free China"));
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.VLSdk
 * JD-Core Version:    0.6.0
 */