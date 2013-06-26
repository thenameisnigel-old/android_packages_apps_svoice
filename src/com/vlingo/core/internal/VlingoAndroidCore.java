package com.vlingo.core.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.BitmapFactory;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.ViewGroup;
import com.vlingo.core.internal.audio.AudioPlayerProxy;
import com.vlingo.core.internal.audio.PhoneListenerImpl;
import com.vlingo.core.internal.audio.TTSEngine;
import com.vlingo.core.internal.contacts.mru.MRU;
import com.vlingo.core.internal.contacts.mru.MRUDatabaseStore;
import com.vlingo.core.internal.dialogmanager.CoreTester;
import com.vlingo.core.internal.dialogmanager.CoreTester.OnFinishedCallback;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.endpoints.EndpointManager;
import com.vlingo.core.internal.localsearch.LocalSearchServiceManager;
import com.vlingo.core.internal.safereader.SafeReaderProxy;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.CoreServerInfo;
import com.vlingo.core.internal.util.CoreServerInfoUtils;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.util.VlingoApplicationInterface;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.internal.vlservice.response.VLResponseParser;
import com.vlingo.sdk.util.SDKDebugSettings;
import com.vlingo.sdk.util.SDKDebugSettings.ServerResponseLoggingState;
import java.io.IOException;

public class VlingoAndroidCore
{
  private static CoreTester coreTester = null;
  private static String smAppChannel;
  private static String smAppId;
  private static String smAppName;
  private static String smAppVersion;
  private static ResourceIdProvider smCoreResourceProvider;
  private static EndpointManager smEndpointManager;
  private static PhoneStateListener smPhoneListener;
  private static PreferenceChangeListener smPrefListener;
  private static boolean smReloadSDKOnIdle;
  private static CoreServerInfo smServerInfo;
  private static TelephonyManager smTelephonyManager;

  private static SDKDebugSettings buildDebugSettings()
  {
    SDKDebugSettings localSDKDebugSettings = null;
    boolean bool1 = Settings.getBoolean("FAKE_LAT_LONG", false);
    boolean bool2 = Settings.getBoolean("FORCE_NON_DM", false);
    boolean bool3 = Settings.getBoolean("audiofilelog_enabled", false);
    boolean bool4 = Settings.getBoolean("FAKE_DEVICE_MODEL", false);
    SDKDebugSettings.ServerResponseLoggingState localServerResponseLoggingState = SDKDebugSettings.ServerResponseLoggingState.get(Settings.getString("SERVER_RESONSE_LOGGGING", SDKDebugSettings.ServerResponseLoggingState.NONE.getCode()));
    if ((bool3) || (bool1) || (bool2) || (bool4) || (localServerResponseLoggingState != SDKDebugSettings.ServerResponseLoggingState.NONE))
    {
      localSDKDebugSettings = new SDKDebugSettings();
      String str1 = Settings.getString("CARRIER_COUNTRY", null);
      if (!StringUtils.isNullOrWhiteSpace(str1))
        localSDKDebugSettings.setCarrierCountry(str1);
      String str2 = Settings.getString("CARRIER", null);
      if (!StringUtils.isNullOrWhiteSpace(str2))
        localSDKDebugSettings.setCarrier(str2);
      String str3 = Settings.getString("FAKE_LAT", null);
      String str4 = Settings.getString("FAKE_LONG", null);
      if ((!StringUtils.isNullOrWhiteSpace(str3)) && (!StringUtils.isNullOrWhiteSpace(str3)))
        localSDKDebugSettings.setLocation("Lat=" + str3 + ";Long=" + str4 + ";Alt=0.0");
      localSDKDebugSettings.setForceNonDM(bool2);
      if (localServerResponseLoggingState != SDKDebugSettings.ServerResponseLoggingState.NONE)
      {
        localSDKDebugSettings.setServerResponseLoggingState(localServerResponseLoggingState);
        localSDKDebugSettings.setmRawServerLogBase(Settings.getString("SERVER_RESONSE_FILE", "serverReponseFile"));
      }
      localSDKDebugSettings.setLogRecoWaveform(bool3);
      if (bool4)
        localSDKDebugSettings.setModelNumber(Settings.getString("DEVICE_MODEL", null));
    }
    return localSDKDebugSettings;
  }

  public static void destroy()
  {
    VLSdk.getInstance().destroy();
    TTSEngine.getInstance(ApplicationAdapter.getInstance().getApplicationContext()).destroy();
    SafeReaderProxy.deinit();
    smTelephonyManager.listen(smPhoneListener, 0);
    smPhoneListener = null;
    smTelephonyManager = null;
  }

  public static String getAppChannel()
  {
    return smAppChannel;
  }

  public static EndpointManager getEndpointManager()
  {
    return smEndpointManager;
  }

  public static DialogFieldID getFieldId(FieldIds paramFieldIds)
  {
    return smEndpointManager.getFieldId(paramFieldIds);
  }

  public static DialogFieldID getFieldId(String paramString)
  {
    return smEndpointManager.getFieldId(paramString);
  }

  public static ResourceIdProvider getResourceProvider()
  {
    return smCoreResourceProvider;
  }

  public static CoreServerInfo getServerInfo()
  {
    return smServerInfo;
  }

  public static EndpointManager getSmEndpointManager()
  {
    return smEndpointManager;
  }

  public static void init(Context paramContext, ResourceIdProvider paramResourceIdProvider, VlingoApplicationInterface paramVlingoApplicationInterface, String paramString1, String paramString2, String paramString3, String paramString4, CoreServerInfo paramCoreServerInfo)
  {
    smTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    smPhoneListener = new PhoneListenerImpl();
    smTelephonyManager.listen(smPhoneListener, 32);
    smCoreResourceProvider = paramResourceIdProvider;
    smReloadSDKOnIdle = false;
    smAppId = paramString1;
    smAppName = paramString2;
    smAppVersion = paramString3;
    smAppChannel = paramString4;
    smServerInfo = paramCoreServerInfo;
    UserLoggingEngine.getInstance().timeApplicationStart();
    ApplicationAdapter.getInstance().init(paramContext);
    Settings.init(paramContext);
    AudioPlayerProxy.init(paramContext);
    MRU.setMRUStoreImpl(MRUDatabaseStore.class);
    SafeReaderProxy.init(paramContext);
    ApplicationAdapter.getInstance().setInterface(paramVlingoApplicationInterface);
    initSdk();
    smPrefListener = new PreferenceChangeListener(null);
    Settings.getSharedPreferences().registerOnSharedPreferenceChangeListener(smPrefListener);
    smEndpointManager = EndpointManager.getInstance();
  }

  private static void initSdk()
  {
    SDKDebugSettings localSDKDebugSettings = buildDebugSettings();
    String str1 = CoreServerInfoUtils.getAsrUri(smServerInfo);
    String str2 = CoreServerInfoUtils.getTtsUri(smServerInfo);
    String str3 = CoreServerInfoUtils.getVcsUri(smServerInfo);
    String str4 = CoreServerInfoUtils.getLogUri(smServerInfo);
    String str5 = CoreServerInfoUtils.getHelloUri(smServerInfo);
    String str6 = CoreServerInfoUtils.getLMTTUri(smServerInfo);
    boolean bool = Settings.isLocationEnabled();
    LocalSearchServiceManager.setVcsUri(str3);
    VLSdk.create(ApplicationAdapter.getInstance().getApplicationContext(), smAppId, smAppName, smAppVersion, smAppChannel, bool, str1, str2, str4, str5, str6, localSDKDebugSettings);
  }

  public static boolean isChineseBuild()
  {
    return "Preinstall Free China".equalsIgnoreCase(smAppChannel);
  }

  public static boolean isChineseTestBuild()
  {
    return "Preinstall Free China Dev".equalsIgnoreCase(smAppChannel);
  }

  public static void onDialogIdle()
  {
    if (smReloadSDKOnIdle)
      reloadSDK(true);
  }

  public static void regBackgroundImage(Context paramContext, ViewGroup paramViewGroup, int paramInt, String paramString)
  {
    if (!Settings.getString("unwatermarked.versions", "").contains(paramString))
    {
      BitmapDrawable localBitmapDrawable = new BitmapDrawable(BitmapFactory.decodeResource(paramContext.getResources(), paramInt));
      localBitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
      paramViewGroup.setBackgroundDrawable(localBitmapDrawable);
    }
  }

  private static void reloadSDK()
  {
    reloadSDK(false);
  }

  private static void reloadSDK(boolean paramBoolean)
  {
    if ((paramBoolean) || (DialogFlow.getInstance().isIdle()))
    {
      smReloadSDKOnIdle = false;
      VLSdk.getInstance().destroy();
      initSdk();
    }
    while (true)
    {
      return;
      smReloadSDKOnIdle = true;
    }
  }

  public static void startAutomationPlayback(String paramString, CoreTester.OnFinishedCallback paramOnFinishedCallback)
    throws IOException
  {
    if (coreTester == null)
      coreTester = new CoreTester(DialogFlow.getInstance());
    coreTester.startReplay(paramString, paramOnFinishedCallback);
  }

  public static void startAutomationRecording(String paramString)
  {
    if (coreTester == null)
      coreTester = new CoreTester(DialogFlow.getInstance());
    coreTester.startRecording(paramString);
  }

  public static boolean stopAutomationPlayback()
  {
    if (coreTester == null);
    for (int i = 0; ; i = 1)
    {
      return i;
      coreTester.stopReplay();
      coreTester = null;
    }
  }

  public static void stopAutomationRecording()
  {
    if (coreTester == null);
    while (true)
    {
      return;
      coreTester.stopRecording();
    }
  }

  public static void updateServerInfo(CoreServerInfo paramCoreServerInfo)
  {
    smServerInfo = paramCoreServerInfo;
    reloadSDK();
  }

  public static void updateServerResponseLogging()
  {
    reloadSDK();
    VLResponseParser.resetResponseCount();
  }

  private static class PreferenceChangeListener
    implements SharedPreferences.OnSharedPreferenceChangeListener
  {
    public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
    {
      if (paramString == null);
      while (true)
      {
        return;
        if (("https.rollout_percentage".equalsIgnoreCase(paramString)) || ("https.asr_enabled".equalsIgnoreCase(paramString)) || ("https.tts_enabled".equalsIgnoreCase(paramString)) || ("https.vcs_enabled".equalsIgnoreCase(paramString)) || ("https.log_enabled".equalsIgnoreCase(paramString)) || ("location_enabled".equalsIgnoreCase(paramString)) || ("FAKE_LAT_LONG".equalsIgnoreCase(paramString)) || ("FORCE_NON_DM".equalsIgnoreCase(paramString)) || ("audiofilelog_enabled".equalsIgnoreCase(paramString)) || ("".equalsIgnoreCase(paramString)) || ("DEVICE_MODEL".equalsIgnoreCase(paramString)))
        {
          VlingoAndroidCore.access$100();
          continue;
        }
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.VlingoAndroidCore
 * JD-Core Version:    0.6.0
 */