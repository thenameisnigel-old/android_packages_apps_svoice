package com.sec.android.internal.ims.external;

import android.os.Build;
import android.os.SystemProperties;
import android.util.Log;

public class HiddenMenuData
{
  public static final String DISABLE_SUBSCRIBE_AND_READ_TIMERS_FROM_ACS = "0";
  public static final String DISABLE_SUBSCRIBE_AND_READ_TIMERS_FROM_DB = "2";
  public static final String DefaultConfigKTServerServiceId = "VOLTE";
  public static final String DefaultConfigSKTServerServiceId = "VOLTE";
  public static final String DefaultConfigServerKTUrl = "https://128.134.98.231/Config";
  public static final String DefaultConfigServerSKTUrl = "https://apcs.sktelecom.com/VoLTE/Config";
  public static final String DefaultmAMRBundlingRate = "6";
  public static final String DefaultmAccessNwInfo = "3GPP-E-UTRAN;utran-cell-id-3gpp=4500527089c403";
  public static final String DefaultmAecVal = "On";
  public static final String DefaultmAlwaysOn = "1";
  public static final String DefaultmAudioBitRate = "8";
  public static String DefaultmAudioCodecMode;
  public static final String DefaultmAudioFirst = " ";
  public static String DefaultmAudioPort;
  public static final String DefaultmAudioVideoTx = "1";
  public static String DefaultmAutoTest;
  public static String DefaultmBitRate;
  public static String DefaultmCodecBandwidthVal;
  public static final String DefaultmCommercialPcscfIPVal = "10.113.13.211";
  public static final String DefaultmCurrDispFormat = "vga";
  public static final String DefaultmCurrLatchedNw = "LTE";
  public static final String DefaultmDynamicBitRate = "1";
  public static final String DefaultmEnableIMSOnRoaming = "1";
  public static String DefaultmEnableQos;
  public static String DefaultmFrameRate;
  public static String DefaultmImsPdnAutoCon;
  public static final String DefaultmJitterBufferSettingVal = "1";
  public static final String DefaultmLoopback = "End To End Call";
  public static final String DefaultmNsVal = "On";
  public static String DefaultmPDN;
  public static final String DefaultmPcscfTypeVal = "Commercial";
  public static final String DefaultmPrePCO = "1";
  public static final String DefaultmPrefAudioCodec = "AMR-WB,AMR-NB,EVRC";
  public static String DefaultmPrefCscfDns = "pcscf1.lteims.co.kr";
  public static String DefaultmPrefCscfDnsforWiFi = "wifi.lteims.co.kr";
  public static String DefaultmPrefCscfDomain;
  public static String DefaultmPrefCscfIp = "107.108.208.221";
  public static final String DefaultmPrefCscfIp2 = " ";
  public static final String DefaultmPrefCscfIp3 = " ";
  public static final String DefaultmPrefCscfIp4 = "";
  public static final String DefaultmPrefCscfIp5 = "";
  public static final String DefaultmPrefCscfIpCnt = "1";
  public static String DefaultmPrefCscfPort;
  public static String DefaultmPrefCscfPortforLTE;
  public static String DefaultmPrefCscfPortforWiFi;
  public static String DefaultmPrefNwType;
  public static final String DefaultmPrefVideoCodec = "H264,H263";
  public static String DefaultmPrefXdmDns;
  public static String DefaultmPrefXdmIp;
  public static String DefaultmPrefXdmIpforWiFi;
  public static final String DefaultmPrivateUserIdentity = "unknown";
  public static String DefaultmPublicUserIdentity;
  public static String DefaultmQosAware;
  public static String DefaultmRegisterTimer;
  public static final String DefaultmReservedVal1 = "1";
  public static final String DefaultmReservedVal2 = "VoLTE:VT";
  public static final String DefaultmReservedVal3 = "  ";
  public static final String DefaultmReservedVal4 = "0";
  public static String DefaultmReservedVal5;
  public static String DefaultmReservedVal6;
  public static String DefaultmReservedVal7;
  public static final String DefaultmReservedVal8 = "  ";
  public static String DefaultmSessionExpire;
  public static String DefaultmSipTimerF;
  public static String DefaultmSipTimerT1;
  public static String DefaultmSipTimerT2;
  public static final String DefaultmSubscriberTimer = "600000";
  public static final String DefaultmUserAgentVal = " ";
  public static final String DefaultmVTDebugging = "0";
  public static final String DefaultmVadVal = "Off";
  public static String DefaultmVideoPort;
  public static final String DefaultmVideoProfile = "0";
  public static final String DefaultmWiFiSetting = "0";
  public static final String HIDDEN_MENU_VALUE_VOLTE_BLOCKED = "VT";
  public static final String HIDDEN_MENU_VALUE_VOLTE_PREFERED = "VoLTE:VT";
  public static final String SEND_SUBSCRIBE = "1";
  public static final String mDefaultSKTPwd = "upwd";
  public static final String mDefaultSKTRealm = "domain";
  public static final String mDefaultSKTUname = "uname";
  public static final String mDefaultTimer1 = "2000";
  public static final String mDefaultTimer2 = "16000";
  public static final String mDefaultTimer4 = "17000";
  public static final String mDefaultTimerA = "2000";
  public static final String mDefaultTimerB = "44000";
  public static final String mDefaultTimerC = "72000";
  public static final String mDefaultTimerD = "32000";
  public static final String mDefaultTimerE = "2000";
  public static final String mDefaultTimerF = "44000";
  public static final String mDefaultTimerG = "2000";
  public static final String mDefaultTimerH = "36000";
  public static final String mDefaultTimerI = "17000";
  public static final String mDefaultTimerJ = "32000";
  public static final String mDefaultTimerK = "17000";
  public String DefaultmBuildIncrmntal = "CL1.0";
  public String DefaultmBuildRelVersion = "1.0";
  public String DefaultmBuildSdk = "2.3";
  public String DefaultmBuildType = "Release";
  public String DefaultmProductName = "Android 1.0";
  private boolean isAPVTUpgrade = false;
  private boolean isKTT = false;
  private boolean isLGT = false;
  private boolean isSKT = false;
  public String mAMRBundlingRateVal;
  public String mAccessNwInfoVal;
  public String mAecVal;
  public String mAlwaysOnVal;
  public String mAudioBitRateVal;
  public String mAudioCodecModeVal;
  public String mAudioFirstVal;
  public String mAudioPortVal;
  public String mAudioVideoTxVal;
  public String mAutoTestVal;
  public String mBitRateVal;
  public String mBuildIncrmntal;
  public String mBuildRelVersion;
  public String mBuildSdk;
  public String mBuildType;
  public String mCodecBandwidthVal;
  public String mCommercialPcscfIPVal;
  public String mConfigServerServiceId;
  public String mConfigServerUrl;
  public String mCurrDispFormatVal;
  public String mCurrLatchedNwVal;
  public String mDynamicBitRateVal;
  public String mEnableConfigServerAccess;
  public String mEnableIMSOnRoamingVal;
  public String mEnableQosVal;
  public String mFrameRateVal;
  public String mImsPdnAutoConVal;
  public String mJitterBufferSettingVal;
  public String mLoopbackVal;
  public String mNsVal;
  public String mPDNVal;
  public String mPcscfTypeVal;
  public String mPrefAudioCodecVal;
  public String mPrefCscfDnsVal;
  public String mPrefCscfDnsforWiFiVal;
  public String mPrefCscfDomainVal;
  public String mPrefCscfIpCnt;
  public String mPrefCscfIpVal;
  public String mPrefCscfIpVal2;
  public String mPrefCscfIpVal3;
  public String mPrefCscfIpVal4;
  public String mPrefCscfIpVal5;
  public String mPrefCscfPortVal;
  public String mPrefCscfPortforLTEVal;
  public String mPrefCscfPortforWiFiVal;
  public String mPrefNwTypeVal;
  public String mPrefVideoCodecVal;
  public String mPrefXdmDnsVal;
  public String mPrefXdmIpVal;
  public String mPrefXdmIpforWiFiVal;
  public String mPrivateUserIdentityVal;
  public String mProductName;
  public String mPublicUserIdentityVal;
  public String mPwd;
  public String mQosAwareVal;
  public String mRealm;
  public String mRegisterTimerVal;
  public String mReservedVal1;
  public String mReservedVal2;
  public String mReservedVal3;
  public String mReservedVal4;
  public String mReservedVal5;
  public String mReservedVal6;
  public String mReservedVal7;
  public String mReservedVal8;
  public String mSessionExpireVal;
  public String mSipTimerFVal;
  public String mSipTimerT1Val;
  public String mSipTimerT2Val;
  public String mSubscriberTimerVal;
  public String mTimer1;
  public String mTimer2;
  public String mTimer4;
  public String mTimerA;
  public String mTimerB;
  public String mTimerC;
  public String mTimerD;
  public String mTimerE;
  public String mTimerF;
  public String mTimerG;
  public String mTimerH;
  public String mTimerI;
  public String mTimerJ;
  public String mTimerK;
  public String mUname;
  public String mUserAgentVal;
  public String mVTDebuggingVal;
  public String mVadVal;
  public String mVideoPortVal;
  public String mVideoProfileVal;
  public String mWiFiSettingVal;

  static
  {
    DefaultmPrefCscfDomain = "lte-lguplus.co.kr";
    DefaultmPrefCscfPort = "5060";
    DefaultmPrefCscfPortforLTE = "5060";
    DefaultmPrefCscfPortforWiFi = "5060";
    DefaultmPrefXdmDns = "255";
    DefaultmPrefXdmIp = "1";
    DefaultmPrefXdmIpforWiFi = "1";
    DefaultmEnableQos = "2000$16000$44000";
    DefaultmSipTimerT1 = "2000";
    DefaultmSipTimerT2 = "16000";
    DefaultmSipTimerF = "44000";
    DefaultmQosAware = "SKT-LTE-VoLTE1.0 SHV-E210S_AND/1.0";
    DefaultmAutoTest = "0";
    DefaultmPrefNwType = "1";
    DefaultmFrameRate = "7";
    DefaultmBitRate = "1024000";
    DefaultmAudioCodecMode = "octet-aligned";
    DefaultmPublicUserIdentity = "unknown";
    DefaultmRegisterTimer = "600000";
    DefaultmSessionExpire = "90";
    DefaultmAudioPort = "1234";
    DefaultmVideoPort = "1254";
    DefaultmCodecBandwidthVal = "41";
    DefaultmPDN = "Internet PDN";
    DefaultmImsPdnAutoCon = "ON";
    DefaultmReservedVal5 = "  ";
    DefaultmReservedVal6 = "  ";
    DefaultmReservedVal7 = "  ";
  }

  public HiddenMenuData()
  {
    String str1 = SystemProperties.get("ro.csc.sales_code");
    String str2 = SystemProperties.get("ro.product.name");
    this.isSKT = "SKT".equals(str1);
    this.isLGT = "LGT".equals(str1);
    this.isKTT = "KTT".equals(str1);
    if (("SHV-E110S".equals(str2)) || ("SHV-E120S".equals(str2)) || ("SHV-E120L".equals(str2)) || ("SHV-E160S".equals(str2)) || ("SHV-E160L".equals(str2)) || ("jaguars".equals(str2)) || ("jaguarl".equals(str2)))
      bool = true;
    this.isAPVTUpgrade = bool;
    Log.i("Hidden Menu data", "isSKT >>> " + this.isSKT);
    Log.i("Hidden Menu data", "isLGT >>> " + this.isLGT);
    Log.i("Hidden Menu data", "isKTT >>> " + this.isKTT);
    Log.i("Hidden Menu data", "isAPVTUpgrade >>> " + this.isAPVTUpgrade);
    settoDefaultValues();
  }

  public String getDefaultUserAgentVal()
  {
    if (this.isAPVTUpgrade);
    for (String str = "SKT-LTE-VT1.0 " + Build.MODEL + "_AND/1.0"; ; str = "SKT-LTE-VoLTE1.0 " + Build.MODEL + "_AND/1.0")
      return str;
  }

  public String getKTUserAgentVal()
  {
    return "KT-LTE-VT1.0 " + Build.PRODUCT + "_AND/1.0";
  }

  public String getLguUserAgentVal()
  {
    if (this.isAPVTUpgrade);
    for (String str = "LGUPlus-client/LTE_VT1.0;" + Build.PRODUCT + ";Device_Type=Android_Phone;305_use-proxy"; ; str = "LGUPlus-client/LTE_AT1.0+LTE_VT2.0;" + Build.PRODUCT + ";Device_Type=Android_Phone;305_use-proxy")
      return str;
  }

  public String getRegistrationTags()
  {
    String str;
    if (this.isAPVTUpgrade)
      str = "VT";
    while (true)
    {
      return str;
      if ((this.isSKT) || (this.isKTT))
      {
        str = "VT";
        continue;
      }
      str = "VoLTE:VT";
    }
  }

  public boolean isIMSEnabledOnWiFI()
  {
    int i = 0;
    if (this.isLGT)
      if (this.mWiFiSettingVal.equals("1"))
        i = 1;
    while (true)
    {
      return i;
      if (!this.mVideoProfileVal.equals("1"))
        continue;
      i = 1;
    }
  }

  public void settoDefaultValues()
  {
    if ("eng".equals(Build.TYPE))
    {
      DefaultmPrefXdmDns = "255";
      if (!this.isSKT)
        break label834;
      DefaultmPrefCscfDns = "mss.nate.com";
      DefaultmPrefCscfDnsforWiFi = "abc.sktelecom.com";
      DefaultmPrefCscfIp = "220.103.220.10";
      DefaultmPrefCscfDomain = "sktims.net";
      DefaultmPrefCscfPort = "5076";
      DefaultmPrefCscfPortforWiFi = "5075";
      DefaultmPrefXdmIpforWiFi = "1";
      DefaultmEnableQos = "2000$16000$44000";
      DefaultmAutoTest = "0";
      DefaultmQosAware = getDefaultUserAgentVal();
      if (!this.isAPVTUpgrade)
        DefaultmPrefNwType = "0";
      DefaultmFrameRate = "15";
      DefaultmBitRate = "512000";
      DefaultmPublicUserIdentity = "unknown";
      DefaultmSessionExpire = "180";
      DefaultmAudioPort = "7010";
      DefaultmVideoPort = "7012";
      if (!this.isAPVTUpgrade)
        DefaultmPDN = "IMS PDN";
      DefaultmAudioCodecMode = "octet-aligned";
      DefaultmCodecBandwidthVal = "41";
      DefaultmReservedVal7 = "  ";
      label161: this.mPrefCscfDnsVal = DefaultmPrefCscfDns;
      this.mPrefCscfDnsforWiFiVal = DefaultmPrefCscfDnsforWiFi;
      this.mPrefCscfIpVal = DefaultmPrefCscfIp;
      this.mPrefCscfIpVal2 = " ";
      this.mPrefCscfIpVal3 = " ";
      this.mPrefCscfIpVal4 = "";
      this.mPrefCscfIpVal5 = "";
      this.mPrefCscfIpCnt = "1";
      this.mPrefCscfDomainVal = DefaultmPrefCscfDomain;
      this.mPrefCscfPortVal = DefaultmPrefCscfPort;
      this.mPrefCscfPortforLTEVal = DefaultmPrefCscfPortforLTE;
      this.mPrefCscfPortforWiFiVal = DefaultmPrefCscfPortforWiFi;
      this.mPrefXdmDnsVal = DefaultmPrefXdmDns;
      this.mPrefXdmIpVal = DefaultmPrefXdmIp;
      this.mPrefXdmIpforWiFiVal = DefaultmPrefXdmIpforWiFi;
      this.mPrefAudioCodecVal = "AMR-WB,AMR-NB,EVRC";
      this.mPrefVideoCodecVal = "H264,H263";
      this.mLoopbackVal = "End To End Call";
      this.mAlwaysOnVal = "1";
      this.mEnableQosVal = DefaultmEnableQos;
      this.mSipTimerT1Val = DefaultmSipTimerT1;
      this.mSipTimerT2Val = DefaultmSipTimerT2;
      this.mSipTimerFVal = DefaultmSipTimerF;
      if (!this.isLGT)
        break label1016;
      this.mVideoProfileVal = DefaultmPrefXdmDns;
      this.mAudioFirstVal = DefaultmPrefXdmDns;
      this.mQosAwareVal = getLguUserAgentVal();
      this.mVTDebuggingVal = DefaultmPrefXdmDns;
      this.mAutoTestVal = DefaultmPrefXdmDns;
      label356: if (!this.isKTT)
        break label1053;
      this.mConfigServerUrl = "https://128.134.98.231/Config";
      this.mConfigServerServiceId = "VOLTE";
      this.mQosAwareVal = getKTUserAgentVal();
      label383: this.mConfigServerServiceId = "VOLTE";
      this.mPrefNwTypeVal = DefaultmPrefNwType;
      this.mCurrLatchedNwVal = "LTE";
      this.mAccessNwInfoVal = "3GPP-E-UTRAN;utran-cell-id-3gpp=4500527089c403";
      this.mCurrDispFormatVal = "vga";
      this.mFrameRateVal = DefaultmFrameRate;
      this.mBitRateVal = DefaultmBitRate;
      this.mDynamicBitRateVal = "1";
      this.mAudioCodecModeVal = DefaultmAudioCodecMode;
      this.mPublicUserIdentityVal = DefaultmPublicUserIdentity;
      this.mPrivateUserIdentityVal = "unknown";
      this.mRegisterTimerVal = DefaultmRegisterTimer;
      this.mSubscriberTimerVal = "600000";
      this.mSessionExpireVal = DefaultmSessionExpire;
      this.mAudioPortVal = DefaultmAudioPort;
      this.mVideoPortVal = DefaultmVideoPort;
      this.mPcscfTypeVal = "Commercial";
      this.mCodecBandwidthVal = DefaultmCodecBandwidthVal;
      this.mAecVal = "On";
      this.mNsVal = "On";
      this.mVadVal = "Off";
      this.mUserAgentVal = " ";
      this.mCommercialPcscfIPVal = "10.113.13.211";
      this.mEnableIMSOnRoamingVal = "1";
      this.mProductName = this.DefaultmProductName;
      this.mBuildType = this.DefaultmBuildType;
      this.mBuildRelVersion = this.DefaultmBuildRelVersion;
      this.mBuildIncrmntal = this.DefaultmBuildIncrmntal;
      this.mBuildSdk = this.DefaultmBuildSdk;
      this.mWiFiSettingVal = "0";
      this.mAudioBitRateVal = "8";
      this.mAMRBundlingRateVal = "6";
      this.mAudioVideoTxVal = "1";
      this.mJitterBufferSettingVal = "1";
      this.mPDNVal = DefaultmPDN;
      this.mImsPdnAutoConVal = DefaultmImsPdnAutoCon;
      if (((!this.isSKT) || (this.isAPVTUpgrade)) && (!this.isKTT))
        break label1069;
    }
    label1053: label1069: for (this.mEnableConfigServerAccess = "1"; ; this.mEnableConfigServerAccess = "0")
    {
      this.mReservedVal1 = "1";
      this.mReservedVal2 = getRegistrationTags();
      this.mReservedVal3 = "  ";
      this.mReservedVal4 = "0";
      this.mReservedVal5 = DefaultmReservedVal5;
      this.mReservedVal6 = DefaultmReservedVal6;
      this.mTimerA = "2000";
      this.mTimerB = "44000";
      this.mTimerC = "72000";
      this.mTimerD = "32000";
      this.mTimerE = "2000";
      this.mTimerF = "44000";
      this.mTimerG = "2000";
      this.mTimerH = "36000";
      this.mTimerI = "17000";
      this.mTimerJ = "32000";
      this.mTimerK = "17000";
      this.mTimer1 = "2000";
      this.mTimer2 = "16000";
      this.mTimer4 = "17000";
      this.mUname = "uname";
      this.mPwd = "upwd";
      this.mRealm = "domain";
      if (this.isSKT)
      {
        this.mReservedVal7 = DefaultmReservedVal7;
        this.mReservedVal8 = "  ";
      }
      if (this.isKTT)
        this.mReservedVal7 = DefaultmReservedVal7;
      return;
      DefaultmPrefXdmDns = "100";
      break;
      label834: if (this.isLGT)
      {
        DefaultmPrefCscfIp = "10.113.13.75";
        DefaultmPrefCscfDomain = "lte-lguplus.co.kr";
        if (!this.isAPVTUpgrade)
          DefaultmPDN = "IMS PDN";
        DefaultmAudioCodecMode = "octet-aligned";
        DefaultmCodecBandwidthVal = "41";
        DefaultmQosAware = getLguUserAgentVal();
        break label161;
      }
      if (!this.isKTT)
        break label161;
      DefaultmPrefCscfIp = "125.144.129.7";
      DefaultmPrefCscfDomain = "ims.kt.com";
      DefaultmPrefCscfPortforWiFi = "5065";
      DefaultmPrefCscfPort = "5080";
      DefaultmPrefCscfPortforLTE = "5080";
      DefaultmQosAware = getKTUserAgentVal();
      DefaultmAudioPort = "49152";
      DefaultmVideoPort = "49154";
      DefaultmRegisterTimer = "28800";
      DefaultmPrefXdmIp = "0";
      DefaultmPDN = "IMS PDN";
      DefaultmSessionExpire = "360";
      DefaultmAudioCodecMode = "bandwidth-efficient;octet-aligned";
      DefaultmReservedVal5 = "0.0.0.0";
      DefaultmReservedVal6 = "178000";
      DefaultmReservedVal7 = "1";
      DefaultmCodecBandwidthVal = "41";
      DefaultmSipTimerT1 = "1000";
      DefaultmSipTimerT2 = "2000";
      DefaultmSipTimerF = "8000";
      break label161;
      label1016: this.mVideoProfileVal = "0";
      this.mAudioFirstVal = DefaultmPrefXdmDns;
      this.mQosAwareVal = getDefaultUserAgentVal();
      this.mVTDebuggingVal = "0";
      this.mAutoTestVal = DefaultmAutoTest;
      break label356;
      if (!this.isSKT)
        break label383;
      this.mConfigServerUrl = "https://apcs.sktelecom.com/VoLTE/Config";
      break label383;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.internal.ims.external.HiddenMenuData
 * JD-Core Version:    0.6.0
 */