package com.sec.android.internal.ims.external;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemProperties;
import android.util.Log;

public class HiddenMenuDataOperation
{
  public String LOG_TAG = "HiddenMenuDataOperation";

  public int deleteExistingRow(ContentResolver paramContentResolver)
  {
    int i = 0;
    if (getRowCount(paramContentResolver) > 0);
    try
    {
      int j = paramContentResolver.delete(MenuDetails.User.DB_HIDDEN_MENU_DEL_URI, null, null);
      i = j;
      return i;
    }
    catch (Exception localException)
    {
      while (true)
      {
        i = 0;
        localException.printStackTrace();
      }
    }
  }

  public HiddenMenuData getAllData(ContentResolver paramContentResolver)
  {
    HiddenMenuData localHiddenMenuData = null;
    Cursor localCursor = paramContentResolver.query(MenuDetails.User.DB_HIDDEN_MENU_TABLE_URI, null, null, null, null);
    if ((localCursor != null) && (localCursor.getCount() > 0))
    {
      String str1 = SystemProperties.get("ro.csc.sales_code");
      boolean bool1 = "SKT".equals(str1);
      boolean bool2 = "LGT".equals(str1);
      boolean bool3 = "KTT".equals(str1);
      Log.i("Hidden Menu data", "isSKT >>> " + bool1);
      Log.i("Hidden Menu data", "isLGT >>> " + bool2);
      localHiddenMenuData = new HiddenMenuData();
      localCursor.moveToLast();
      localHiddenMenuData.mPrefCscfDnsVal = localCursor.getString(localCursor.getColumnIndex("mPrefCscfDns"));
      localHiddenMenuData.mPrefCscfDnsforWiFiVal = localCursor.getString(localCursor.getColumnIndex("mPrefCscfDnsforWiFi"));
      localHiddenMenuData.mPrefCscfIpVal = localCursor.getString(localCursor.getColumnIndex("mPrefCscfIp"));
      localHiddenMenuData.mAlwaysOnVal = localCursor.getString(localCursor.getColumnIndex("mAlwaysOn"));
      if (bool2)
      {
        localHiddenMenuData.mPrefCscfIpVal2 = localCursor.getString(localCursor.getColumnIndex("mPrefCscfIp2"));
        localHiddenMenuData.mPrefCscfIpVal3 = localCursor.getString(localCursor.getColumnIndex("mPrefCscfIp3"));
        localHiddenMenuData.mPrefCscfIpCnt = localCursor.getString(localCursor.getColumnIndex("mPrefCscfIpCnt"));
      }
      localHiddenMenuData.mPrefCscfDomainVal = localCursor.getString(localCursor.getColumnIndex("mPrefCscfDomain"));
      localHiddenMenuData.mPrefCscfPortVal = localCursor.getString(localCursor.getColumnIndex("mPrefCscfPort"));
      localHiddenMenuData.mPrefCscfPortforLTEVal = localCursor.getString(localCursor.getColumnIndex("mPrefCscfPortforLTE"));
      localHiddenMenuData.mPrefCscfPortforWiFiVal = localCursor.getString(localCursor.getColumnIndex("mPrefCscfPortforWiFi"));
      localHiddenMenuData.mPrefXdmDnsVal = localCursor.getString(localCursor.getColumnIndex("mPrefXdmDns"));
      localHiddenMenuData.mPrefXdmIpVal = localCursor.getString(localCursor.getColumnIndex("mPrefXdmIp"));
      localHiddenMenuData.mPrefXdmIpforWiFiVal = localCursor.getString(localCursor.getColumnIndex("mPrefXdmIpforWiFi"));
      localHiddenMenuData.mPrefAudioCodecVal = localCursor.getString(localCursor.getColumnIndex("mPrefAudioCodec"));
      localHiddenMenuData.mPrefVideoCodecVal = localCursor.getString(localCursor.getColumnIndex("mPrefVideoCodec"));
      localHiddenMenuData.mLoopbackVal = localCursor.getString(localCursor.getColumnIndex("mLoopback"));
      localHiddenMenuData.mEnableQosVal = localCursor.getString(localCursor.getColumnIndex("mEnableQos"));
      String str2 = localHiddenMenuData.mEnableQosVal;
      Log.d(this.LOG_TAG, "SIP TIMERS String stored in DB Value is " + str2);
      if (str2 == null)
        str2 = HiddenMenuData.DefaultmEnableQos;
      if (str2.contains("$"))
      {
        String[] arrayOfString = str2.split("\\$");
        localHiddenMenuData.mSipTimerT1Val = arrayOfString[0];
        localHiddenMenuData.mSipTimerT2Val = arrayOfString[1];
        localHiddenMenuData.mSipTimerFVal = arrayOfString[2];
      }
      localHiddenMenuData.mVideoProfileVal = localCursor.getString(localCursor.getColumnIndex("mVideoProfile"));
      localHiddenMenuData.mAudioFirstVal = localCursor.getString(localCursor.getColumnIndex("mAudioFirst"));
      localHiddenMenuData.mQosAwareVal = localCursor.getString(localCursor.getColumnIndex("mQosAware"));
      localHiddenMenuData.mVTDebuggingVal = localCursor.getString(localCursor.getColumnIndex("mVTDebugging"));
      localHiddenMenuData.mAutoTestVal = localCursor.getString(localCursor.getColumnIndex("mAutoTest"));
      localHiddenMenuData.mPrefNwTypeVal = localCursor.getString(localCursor.getColumnIndex("mPrefNwType"));
      localHiddenMenuData.mCurrLatchedNwVal = localCursor.getString(localCursor.getColumnIndex("mCurrLatchedNw"));
      localHiddenMenuData.mAccessNwInfoVal = localCursor.getString(localCursor.getColumnIndex("mAccessNwInfo"));
      localHiddenMenuData.mCurrDispFormatVal = localCursor.getString(localCursor.getColumnIndex("mCurrDispFormat"));
      localHiddenMenuData.mFrameRateVal = localCursor.getString(localCursor.getColumnIndex("mFrameRate"));
      localHiddenMenuData.mBitRateVal = localCursor.getString(localCursor.getColumnIndex("mBitRate"));
      localHiddenMenuData.mDynamicBitRateVal = localCursor.getString(localCursor.getColumnIndex("mDynamicBitRate"));
      localHiddenMenuData.mAudioCodecModeVal = localCursor.getString(localCursor.getColumnIndex("mAudioCodecMode"));
      localHiddenMenuData.mPublicUserIdentityVal = localCursor.getString(localCursor.getColumnIndex("mPublicUserIdentity"));
      localHiddenMenuData.mPrivateUserIdentityVal = localCursor.getString(localCursor.getColumnIndex("mPrivateUserIdentity"));
      if (bool2)
        localHiddenMenuData.mWiFiSettingVal = localCursor.getString(localCursor.getColumnIndex("mWiFiSetting"));
      localHiddenMenuData.mRegisterTimerVal = localCursor.getString(localCursor.getColumnIndex("mRegisterTimer"));
      localHiddenMenuData.mSubscriberTimerVal = localCursor.getString(localCursor.getColumnIndex("mSubscriberTimer"));
      localHiddenMenuData.mSessionExpireVal = localCursor.getString(localCursor.getColumnIndex("mSessionExpire"));
      localHiddenMenuData.mAudioPortVal = localCursor.getString(localCursor.getColumnIndex("mAudioPort"));
      localHiddenMenuData.mVideoPortVal = localCursor.getString(localCursor.getColumnIndex("mVideoPort"));
      localHiddenMenuData.mPDNVal = localCursor.getString(localCursor.getColumnIndex("mPDN"));
      localHiddenMenuData.mImsPdnAutoConVal = localCursor.getString(localCursor.getColumnIndex("mImsPdnAutoCon"));
      if (bool2)
      {
        localHiddenMenuData.mPcscfTypeVal = localCursor.getString(localCursor.getColumnIndex("mPcscfType"));
        localHiddenMenuData.mCommercialPcscfIPVal = localCursor.getString(localCursor.getColumnIndex("mCommercialPcscfIP"));
        localHiddenMenuData.mCodecBandwidthVal = localCursor.getString(localCursor.getColumnIndex("mCodecBandwidth"));
        localHiddenMenuData.mAecVal = localCursor.getString(localCursor.getColumnIndex("mAec"));
        localHiddenMenuData.mNsVal = localCursor.getString(localCursor.getColumnIndex("mNs"));
        localHiddenMenuData.mVadVal = localCursor.getString(localCursor.getColumnIndex("mVad"));
        localHiddenMenuData.mUserAgentVal = localCursor.getString(localCursor.getColumnIndex("mUserAgent"));
      }
      localHiddenMenuData.mEnableIMSOnRoamingVal = localCursor.getString(localCursor.getColumnIndex("mEnableIMSOnRoamingVal"));
      localHiddenMenuData.mAudioBitRateVal = localCursor.getString(localCursor.getColumnIndex("mAudioBitRate"));
      localHiddenMenuData.mAMRBundlingRateVal = localCursor.getString(localCursor.getColumnIndex("mAMRBundlingRate"));
      localHiddenMenuData.mAudioVideoTxVal = localCursor.getString(localCursor.getColumnIndex("mAudioVideoTx"));
      localHiddenMenuData.mJitterBufferSettingVal = localCursor.getString(localCursor.getColumnIndex("mJitterBufferSetting"));
      if ((bool1) || (bool3))
      {
        localHiddenMenuData.mEnableConfigServerAccess = localCursor.getString(localCursor.getColumnIndex("mEnableConfigServer"));
        localHiddenMenuData.mConfigServerUrl = localCursor.getString(localCursor.getColumnIndex("mConfigServerUrl"));
        localHiddenMenuData.mConfigServerServiceId = localCursor.getString(localCursor.getColumnIndex("mConfigServerServiceId"));
        localHiddenMenuData.mTimerA = localCursor.getString(localCursor.getColumnIndex("mTimerA"));
        localHiddenMenuData.mTimerB = localCursor.getString(localCursor.getColumnIndex("mTimerB"));
        localHiddenMenuData.mTimerC = localCursor.getString(localCursor.getColumnIndex("mTimerC"));
        localHiddenMenuData.mTimerD = localCursor.getString(localCursor.getColumnIndex("mTimerD"));
        localHiddenMenuData.mTimerE = localCursor.getString(localCursor.getColumnIndex("mTimerE"));
        localHiddenMenuData.mTimerF = localCursor.getString(localCursor.getColumnIndex("mTimerF"));
        localHiddenMenuData.mTimerG = localCursor.getString(localCursor.getColumnIndex("mTimerG"));
        localHiddenMenuData.mTimerH = localCursor.getString(localCursor.getColumnIndex("mTimerH"));
        localHiddenMenuData.mTimerI = localCursor.getString(localCursor.getColumnIndex("mTimerI"));
        localHiddenMenuData.mTimerJ = localCursor.getString(localCursor.getColumnIndex("mTimerJ"));
        localHiddenMenuData.mTimerK = localCursor.getString(localCursor.getColumnIndex("mTimerK"));
        localHiddenMenuData.mTimer1 = localCursor.getString(localCursor.getColumnIndex("mTimer1"));
        localHiddenMenuData.mTimer2 = localCursor.getString(localCursor.getColumnIndex("mTimer2"));
        localHiddenMenuData.mTimer4 = localCursor.getString(localCursor.getColumnIndex("mTimer4"));
        localHiddenMenuData.mRealm = localCursor.getString(localCursor.getColumnIndex("mSKTRealm"));
        localHiddenMenuData.mUname = localCursor.getString(localCursor.getColumnIndex("mSKTUname"));
        localHiddenMenuData.mPwd = localCursor.getString(localCursor.getColumnIndex("mSKTPwd"));
      }
      localHiddenMenuData.mReservedVal1 = localCursor.getString(localCursor.getColumnIndex("mReserved1"));
      localHiddenMenuData.mReservedVal2 = localCursor.getString(localCursor.getColumnIndex("mReserved2"));
      localHiddenMenuData.mReservedVal3 = localCursor.getString(localCursor.getColumnIndex("mReserved3"));
      localHiddenMenuData.mReservedVal4 = localCursor.getString(localCursor.getColumnIndex("mReserved4"));
      localHiddenMenuData.mReservedVal5 = localCursor.getString(localCursor.getColumnIndex("mReserved5"));
      localHiddenMenuData.mReservedVal6 = localCursor.getString(localCursor.getColumnIndex("mReserved6"));
      if ((bool1) || (bool3))
      {
        localHiddenMenuData.mReservedVal7 = localCursor.getString(localCursor.getColumnIndex("mReserved7"));
        localHiddenMenuData.mReservedVal8 = localCursor.getString(localCursor.getColumnIndex("mReserved8"));
      }
    }
    if (localCursor != null)
      localCursor.close();
    return localHiddenMenuData;
  }

  public int getRowCount(ContentResolver paramContentResolver)
  {
    int i = 0;
    Cursor localCursor = paramContentResolver.query(MenuDetails.User.DB_HIDDEN_MENU_TABLE_URI, null, null, null, null);
    if (localCursor != null)
      i = localCursor.getCount();
    if (localCursor != null)
      localCursor.close();
    return i;
  }

  public Uri insertDataIntoDB(HiddenMenuData paramHiddenMenuData, ContentResolver paramContentResolver)
  {
    Uri localUri = null;
    if ((paramHiddenMenuData != null) && (paramContentResolver != null))
    {
      String str = SystemProperties.get("ro.csc.sales_code");
      boolean bool1 = "SKT".equals(str);
      boolean bool2 = "LGT".equals(str);
      boolean bool3 = "KTT".equals(str);
      Log.i("Hidden Menu data", "isSKT >>> " + bool1);
      Log.i("Hidden Menu data", "isLGT >>> " + bool2);
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("mPrefCscfDns".toString(), paramHiddenMenuData.mPrefCscfDnsVal);
      localContentValues.put("mPrefCscfDnsforWiFi".toString(), paramHiddenMenuData.mPrefCscfDnsforWiFiVal);
      localContentValues.put("mPrefCscfIp".toString(), paramHiddenMenuData.mPrefCscfIpVal);
      if (bool2)
      {
        localContentValues.put("mPrefCscfIp2".toString(), paramHiddenMenuData.mPrefCscfIpVal2);
        localContentValues.put("mPrefCscfIp3".toString(), paramHiddenMenuData.mPrefCscfIpVal3);
        localContentValues.put("mPrefCscfIpCnt".toString(), paramHiddenMenuData.mPrefCscfIpCnt);
      }
      localContentValues.put("mPrefCscfDomain".toString(), paramHiddenMenuData.mPrefCscfDomainVal);
      localContentValues.put("mPrefCscfPort".toString(), paramHiddenMenuData.mPrefCscfPortVal);
      localContentValues.put("mPrefCscfPortforLTE".toString(), paramHiddenMenuData.mPrefCscfPortforLTEVal);
      localContentValues.put("mPrefCscfPortforWiFi".toString(), paramHiddenMenuData.mPrefCscfPortforWiFiVal);
      localContentValues.put("mPrefXdmDns".toString(), paramHiddenMenuData.mPrefXdmDnsVal);
      localContentValues.put("mPrefXdmIp".toString(), paramHiddenMenuData.mPrefXdmIpVal);
      localContentValues.put("mPrefXdmIpforWiFi".toString(), paramHiddenMenuData.mPrefXdmIpforWiFiVal);
      localContentValues.put("mPrefAudioCodec".toString(), paramHiddenMenuData.mPrefAudioCodecVal);
      localContentValues.put("mPrefVideoCodec".toString(), paramHiddenMenuData.mPrefVideoCodecVal);
      localContentValues.put("mLoopback".toString(), paramHiddenMenuData.mLoopbackVal);
      localContentValues.put("mAlwaysOn".toString(), paramHiddenMenuData.mAlwaysOnVal);
      localContentValues.put("mEnableQos".toString(), paramHiddenMenuData.mEnableQosVal);
      localContentValues.put("mVideoProfile".toString(), paramHiddenMenuData.mVideoProfileVal);
      localContentValues.put("mAudioFirst".toString(), paramHiddenMenuData.mAudioFirstVal);
      localContentValues.put("mQosAware".toString(), paramHiddenMenuData.mQosAwareVal);
      localContentValues.put("mVTDebugging".toString(), paramHiddenMenuData.mVTDebuggingVal);
      localContentValues.put("mAutoTest".toString(), paramHiddenMenuData.mAutoTestVal);
      localContentValues.put("mPrefNwType".toString(), paramHiddenMenuData.mPrefNwTypeVal);
      localContentValues.put("mCurrLatchedNw".toString(), paramHiddenMenuData.mCurrLatchedNwVal);
      localContentValues.put("mAccessNwInfo".toString(), paramHiddenMenuData.mAccessNwInfoVal);
      localContentValues.put("mCurrDispFormat".toString(), paramHiddenMenuData.mCurrDispFormatVal);
      localContentValues.put("mFrameRate".toString(), paramHiddenMenuData.mFrameRateVal);
      localContentValues.put("mBitRate".toString(), paramHiddenMenuData.mBitRateVal);
      localContentValues.put("mDynamicBitRate".toString(), paramHiddenMenuData.mDynamicBitRateVal);
      localContentValues.put("mAudioCodecMode".toString(), paramHiddenMenuData.mAudioCodecModeVal);
      localContentValues.put("mPublicUserIdentity".toString(), paramHiddenMenuData.mPublicUserIdentityVal);
      localContentValues.put("mPrivateUserIdentity".toString(), paramHiddenMenuData.mPrivateUserIdentityVal);
      if (bool2)
        localContentValues.put("mWiFiSetting".toString(), paramHiddenMenuData.mWiFiSettingVal);
      localContentValues.put("mRegisterTimer".toString(), paramHiddenMenuData.mRegisterTimerVal);
      localContentValues.put("mSubscriberTimer".toString(), paramHiddenMenuData.mSubscriberTimerVal);
      localContentValues.put("mSessionExpire".toString(), paramHiddenMenuData.mSessionExpireVal);
      localContentValues.put("mAudioPort".toString(), paramHiddenMenuData.mAudioPortVal);
      localContentValues.put("mVideoPort".toString(), paramHiddenMenuData.mVideoPortVal);
      localContentValues.put("mPDN".toString(), paramHiddenMenuData.mPDNVal);
      localContentValues.put("mImsPdnAutoCon".toString(), paramHiddenMenuData.mImsPdnAutoConVal);
      if (bool2)
      {
        localContentValues.put("mPcscfType".toString(), paramHiddenMenuData.mPcscfTypeVal);
        localContentValues.put("mCommercialPcscfIP".toString(), paramHiddenMenuData.mCommercialPcscfIPVal);
        localContentValues.put("mCodecBandwidth".toString(), paramHiddenMenuData.mCodecBandwidthVal);
        localContentValues.put("mAec".toString(), paramHiddenMenuData.mAecVal);
        localContentValues.put("mNs".toString(), paramHiddenMenuData.mNsVal);
        localContentValues.put("mVad".toString(), paramHiddenMenuData.mVadVal);
        localContentValues.put("mUserAgent".toString(), paramHiddenMenuData.mUserAgentVal);
      }
      localContentValues.put("mAudioBitRate".toString(), paramHiddenMenuData.mAudioBitRateVal);
      localContentValues.put("mAMRBundlingRate".toString(), paramHiddenMenuData.mAMRBundlingRateVal);
      localContentValues.put("mAudioVideoTx".toString(), paramHiddenMenuData.mAudioVideoTxVal);
      localContentValues.put("mJitterBufferSetting".toString(), paramHiddenMenuData.mJitterBufferSettingVal);
      localContentValues.put("mEnableIMSOnRoamingVal".toString(), paramHiddenMenuData.mEnableIMSOnRoamingVal);
      if ((bool1) || (bool3))
      {
        localContentValues.put("mConfigServerUrl".toString(), paramHiddenMenuData.mConfigServerUrl);
        localContentValues.put("mConfigServerServiceId".toString(), paramHiddenMenuData.mConfigServerServiceId);
        localContentValues.put("mEnableConfigServer".toString(), paramHiddenMenuData.mEnableConfigServerAccess);
        localContentValues.put("mTimerA".toString(), paramHiddenMenuData.mTimerA);
        localContentValues.put("mTimerB".toString(), paramHiddenMenuData.mTimerB);
        localContentValues.put("mTimerC".toString(), paramHiddenMenuData.mTimerC);
        localContentValues.put("mTimerD".toString(), paramHiddenMenuData.mTimerD);
        localContentValues.put("mTimerE".toString(), paramHiddenMenuData.mTimerE);
        localContentValues.put("mTimerF".toString(), paramHiddenMenuData.mTimerF);
        localContentValues.put("mTimerG".toString(), paramHiddenMenuData.mTimerG);
        localContentValues.put("mTimerH".toString(), paramHiddenMenuData.mTimerH);
        localContentValues.put("mTimerI".toString(), paramHiddenMenuData.mTimerI);
        localContentValues.put("mTimerJ".toString(), paramHiddenMenuData.mTimerJ);
        localContentValues.put("mTimerK".toString(), paramHiddenMenuData.mTimerK);
        localContentValues.put("mTimer1".toString(), paramHiddenMenuData.mTimer1);
        localContentValues.put("mTimer2".toString(), paramHiddenMenuData.mTimer2);
        localContentValues.put("mTimer4".toString(), paramHiddenMenuData.mTimer4);
        localContentValues.put("mSKTPwd".toString(), paramHiddenMenuData.mPwd);
        localContentValues.put("mSKTRealm".toString(), paramHiddenMenuData.mRealm);
        localContentValues.put("mSKTUname".toString(), paramHiddenMenuData.mUname);
      }
      localContentValues.put("mReserved1".toString(), paramHiddenMenuData.mReservedVal1);
      localContentValues.put("mReserved2".toString(), paramHiddenMenuData.mReservedVal2);
      localContentValues.put("mReserved3".toString(), paramHiddenMenuData.mReservedVal3);
      localContentValues.put("mReserved4".toString(), paramHiddenMenuData.mReservedVal4);
      localContentValues.put("mReserved5".toString(), paramHiddenMenuData.mReservedVal5);
      localContentValues.put("mReserved6".toString(), paramHiddenMenuData.mReservedVal6);
      if ((bool1) || (bool3))
      {
        localContentValues.put("mReserved7".toString(), paramHiddenMenuData.mReservedVal7);
        localContentValues.put("mReserved8".toString(), paramHiddenMenuData.mReservedVal8);
      }
      localUri = paramContentResolver.insert(MenuDetails.User.DB_HIDDEN_MENU_TABLE_URI, localContentValues);
      if (localUri == null)
        break label1359;
      Log.d(this.LOG_TAG, "Saved new values successfuly to HiddenMenu DataBase");
    }
    while (true)
    {
      return localUri;
      label1359: Log.d(this.LOG_TAG, "HiddenMenu DataBase save operation failed!!!" + paramHiddenMenuData.mPrefCscfIpVal2 + "next value" + paramHiddenMenuData.mPrefCscfIpVal3);
    }
  }

  public int insertSingleItemToDB(String paramString1, String paramString2, ContentResolver paramContentResolver)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put(paramString1.toString(), paramString2);
    return paramContentResolver.update(MenuDetails.User.DB_HIDDEN_MENU_TABLE_URI, localContentValues, null, null);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.internal.ims.external.HiddenMenuDataOperation
 * JD-Core Version:    0.6.0
 */