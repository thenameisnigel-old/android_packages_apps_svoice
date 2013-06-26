package com.sec.android.internal.ims.external;

import android.net.Uri;
import android.provider.BaseColumns;

public class MenuDetails
{
  public static final String AUTHORITY = "com.example.MyContentProvider";

  public static final class User
    implements BaseColumns
  {
    public static final Uri CONTENT_URI = Uri.parse("content://com.example.MyContentProvider");
    public static final String DATABASE_NAME_HIDDEN_MENU = "HiddenMenuContent.db";
    public static final Uri DB_HIDDEN_MENU_DEL_URI;
    public static final Uri DB_HIDDEN_MENU_TABLE_URI = Uri.parse("content://com.example.MyContentProvider/HiddenMenu");
    public static final String TABLE_NAME_HIDDEN_MENU = "HiddenMenu";
    public static final String mAMRBundlingRate = "mAMRBundlingRate";
    public static final String mAccessNwInfo = "mAccessNwInfo";
    public static final String mAec = "mAec";
    public static final String mAlwaysOn = "mAlwaysOn";
    public static final String mAudioBitRate = "mAudioBitRate";
    public static final String mAudioCodecMode = "mAudioCodecMode";
    public static final String mAudioFirst = "mAudioFirst";
    public static final String mAudioPort = "mAudioPort";
    public static final String mAudioVideoTx = "mAudioVideoTx";
    public static final String mAutoTest = "mAutoTest";
    public static final String mBitRate = "mBitRate";
    public static final String mCodecBandwidth = "mCodecBandwidth";
    public static final String mCommercialPcscfIP = "mCommercialPcscfIP";
    public static final String mConfigServerServiceId = "mConfigServerServiceId";
    public static final String mConfigServerUrl = "mConfigServerUrl";
    public static final String mCurrDispFormat = "mCurrDispFormat";
    public static final String mCurrLatchedNw = "mCurrLatchedNw";
    public static final String mDynamicBitRate = "mDynamicBitRate";
    public static final String mEnableConfigServer = "mEnableConfigServer";
    public static final String mEnableIMSOnRoamingVal = "mEnableIMSOnRoamingVal";
    public static final String mEnableQos = "mEnableQos";
    public static final String mFrameRate = "mFrameRate";
    public static final String mImsPdnAutoCon = "mImsPdnAutoCon";
    public static final String mJitterBufferSetting = "mJitterBufferSetting";
    public static final String mLoopback = "mLoopback";
    public static final String mNs = "mNs";
    public static final String mPDN = "mPDN";
    public static final String mPcscfType = "mPcscfType";
    public static final String mPrefAudioCodec = "mPrefAudioCodec";
    public static final String mPrefCscfDns = "mPrefCscfDns";
    public static final String mPrefCscfDnsforWiFi = "mPrefCscfDnsforWiFi";
    public static final String mPrefCscfDomain = "mPrefCscfDomain";
    public static final String mPrefCscfIp = "mPrefCscfIp";
    public static final String mPrefCscfIp2 = "mPrefCscfIp2";
    public static final String mPrefCscfIp3 = "mPrefCscfIp3";
    public static final String mPrefCscfIpCnt = "mPrefCscfIpCnt";
    public static final String mPrefCscfPort = "mPrefCscfPort";
    public static final String mPrefCscfPortforLTE = "mPrefCscfPortforLTE";
    public static final String mPrefCscfPortforWiFi = "mPrefCscfPortforWiFi";
    public static final String mPrefNwType = "mPrefNwType";
    public static final String mPrefVideoCodec = "mPrefVideoCodec";
    public static final String mPrefXdmDns = "mPrefXdmDns";
    public static final String mPrefXdmIp = "mPrefXdmIp";
    public static final String mPrefXdmIpforWiFi = "mPrefXdmIpforWiFi";
    public static final String mPrivateUserIdentity = "mPrivateUserIdentity";
    public static final String mPublicUserIdentity = "mPublicUserIdentity";
    public static final String mPwd = "mSKTPwd";
    public static final String mQosAware = "mQosAware";
    public static final String mRealm = "mSKTRealm";
    public static final String mRegisterTimer = "mRegisterTimer";
    public static final String mReserved1 = "mReserved1";
    public static final String mReserved2 = "mReserved2";
    public static final String mReserved3 = "mReserved3";
    public static final String mReserved4 = "mReserved4";
    public static final String mReserved5 = "mReserved5";
    public static final String mReserved6 = "mReserved6";
    public static final String mReserved7 = "mReserved7";
    public static final String mReserved8 = "mReserved8";
    public static final String mSessionExpire = "mSessionExpire";
    public static final String mSubscriberTimer = "mSubscriberTimer";
    public static final String mTimer1 = "mTimer1";
    public static final String mTimer2 = "mTimer2";
    public static final String mTimer4 = "mTimer4";
    public static final String mTimerA = "mTimerA";
    public static final String mTimerB = "mTimerB";
    public static final String mTimerC = "mTimerC";
    public static final String mTimerD = "mTimerD";
    public static final String mTimerE = "mTimerE";
    public static final String mTimerF = "mTimerF";
    public static final String mTimerG = "mTimerG";
    public static final String mTimerH = "mTimerH";
    public static final String mTimerI = "mTimerI";
    public static final String mTimerJ = "mTimerJ";
    public static final String mTimerK = "mTimerK";
    public static final String mUname = "mSKTUname";
    public static final String mUserAgent = "mUserAgent";
    public static final String mVTDebugging = "mVTDebugging";
    public static final String mVad = "mVad";
    public static final String mVideoPort = "mVideoPort";
    public static final String mVideoProfile = "mVideoProfile";
    public static final String mWiFiSetting = "mWiFiSetting";

    static
    {
      DB_HIDDEN_MENU_DEL_URI = Uri.parse("content://com.example.MyContentProvider/HiddenMenu/0");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.internal.ims.external.MenuDetails
 * JD-Core Version:    0.6.0
 */