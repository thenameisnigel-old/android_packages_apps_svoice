package com.samsung.wfd;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;

public class WfdInfo
  implements Parcelable
{
  public static final int COUPLED = 1;
  public static final Parcelable.Creator<WfdInfo> CREATOR = new WfdInfo.1();
  public static final int DEFAULT_SESSION_MGMT_CTRL_PORT = 49152;
  public static final int DEVICETYPE_INVALID = 4;
  public static final int DEVICETYPE_PRIMARYSINK = 1;
  public static final int DEVICETYPE_SECONDARYSINK = 2;
  public static final int DEVICETYPE_SOURCE = 0;
  public static final int DEVICETYPE_SOURCE_PRIMARYSINK = 3;
  public static final int NOT_COUPLED_AVAILABLE = 0;
  public static final int PC_P2P = 0;
  public static final int PC_TDLS = 1;
  private static final String TAG = "WfdInfo";
  public static final int TEARDOWN_COUPLING = 2;
  public String coupledDeviceAddress;
  public int coupledSinkStatus = 0;
  public int cpled_sink_status;
  public int ctrl_port;
  public int dev_info;
  public String deviceAddress;
  public String deviceName;
  public int deviceType = 0;
  public boolean isAudioOnlySupportedAtSource = false;
  public boolean isAudioUnspportedAtPrimarySink = false;
  public boolean isAvailableForSession = false;
  public boolean isContentProtectionSupported = false;
  public boolean isCoupledSinkSupportedBySink = false;
  public boolean isCoupledSinkSupportedBySource = false;
  public boolean isServiceDiscoverySupported = false;
  public boolean isTDLSPersistentGroupIntended = false;
  public boolean isTDLSReInvokePersistentGroupReq = false;
  public boolean isTimeSynchronizationSupported = false;
  public int maxThroughput;
  public int max_tput;
  public int preferredConnectivity = 0;
  public int sessionMgmtCtrlPort = 49152;

  public WfdInfo()
  {
    this.deviceType = 4;
    this.preferredConnectivity = 0;
    this.coupledSinkStatus = 0;
    this.sessionMgmtCtrlPort = 49152;
  }

  public WfdInfo(int paramInt)
  {
    this.deviceType = paramInt;
    this.preferredConnectivity = 0;
    this.coupledSinkStatus = 0;
    this.sessionMgmtCtrlPort = 49152;
  }

  public WfdInfo(WfdInfo paramWfdInfo)
  {
    if (paramWfdInfo != null)
    {
      this.deviceAddress = paramWfdInfo.deviceAddress;
      this.dev_info = paramWfdInfo.dev_info;
      this.ctrl_port = paramWfdInfo.ctrl_port;
      this.max_tput = paramWfdInfo.max_tput;
      this.cpled_sink_status = paramWfdInfo.cpled_sink_status;
      this.sessionMgmtCtrlPort = paramWfdInfo.ctrl_port;
      this.maxThroughput = paramWfdInfo.maxThroughput;
      this.deviceType = paramWfdInfo.deviceType;
      this.preferredConnectivity = paramWfdInfo.preferredConnectivity;
      this.coupledSinkStatus = paramWfdInfo.coupledSinkStatus;
      this.coupledDeviceAddress = paramWfdInfo.coupledDeviceAddress;
      this.isCoupledSinkSupportedBySource = paramWfdInfo.isCoupledSinkSupportedBySource;
      this.isCoupledSinkSupportedBySink = paramWfdInfo.isCoupledSinkSupportedBySink;
      this.isAvailableForSession = paramWfdInfo.isAvailableForSession;
      this.isCoupledSinkSupportedBySource = paramWfdInfo.isCoupledSinkSupportedBySource;
      this.isContentProtectionSupported = paramWfdInfo.isContentProtectionSupported;
      this.isTimeSynchronizationSupported = paramWfdInfo.isTimeSynchronizationSupported;
      this.isAudioUnspportedAtPrimarySink = paramWfdInfo.isAudioUnspportedAtPrimarySink;
      this.isAudioOnlySupportedAtSource = paramWfdInfo.isAudioOnlySupportedAtSource;
      this.isTDLSPersistentGroupIntended = paramWfdInfo.isTDLSPersistentGroupIntended;
      this.isTDLSReInvokePersistentGroupReq = paramWfdInfo.isTDLSReInvokePersistentGroupReq;
    }
  }

  public WfdInfo(String paramString)
    throws IllegalArgumentException
  {
    String[] arrayOfString1 = paramString.split(" ");
    if (arrayOfString1.length < 1)
      throw new IllegalArgumentException("Malformed wfd information");
    int i = arrayOfString1.length;
    int j = 0;
    if (j < i)
    {
      String[] arrayOfString2 = arrayOfString1[j].split("=");
      if (arrayOfString2[0].equals("dev_addr"))
        this.deviceAddress = arrayOfString2[1];
      while (true)
      {
        j++;
        break;
        if (arrayOfString2[0].equals("wfd_dev_info"))
        {
          this.dev_info = parseHex(arrayOfString2[1]);
          continue;
        }
        if (arrayOfString2[0].equals("wfd_ctrl_port"))
        {
          this.ctrl_port = Integer.parseInt(arrayOfString2[1]);
          continue;
        }
        if (arrayOfString2[0].equals("wfd_max_tput"))
        {
          this.max_tput = Integer.parseInt(arrayOfString2[1]);
          continue;
        }
        if (!arrayOfString2[0].equals("wfd_cpled_sink_status"))
          continue;
        this.cpled_sink_status = parseHex(arrayOfString2[1]);
      }
    }
  }

  private int parseHex(String paramString)
  {
    int i = 0;
    if ((paramString.startsWith("0x")) || (paramString.startsWith("0X")))
      paramString = paramString.substring(2);
    try
    {
      int j = Integer.parseInt(paramString, 16);
      i = j;
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
        Log.e("WfdInfo", "Failed to parse hex string " + paramString);
    }
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean isWFDDevice()
  {
    if (this.deviceType != 4);
    for (int i = 1; ; i = 0)
      return i;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("device_address: ").append(this.deviceAddress);
    localStringBuffer.append("\n dev_info: ").append(this.dev_info);
    localStringBuffer.append("\n ctrl_port: ").append(this.ctrl_port);
    localStringBuffer.append("\n max_tput: ").append(this.max_tput);
    localStringBuffer.append("\n cpled_sink_status: ").append(this.cpled_sink_status);
    localStringBuffer.append("\n Control Port: ").append(this.sessionMgmtCtrlPort);
    localStringBuffer.append("\n MaxThroughput: ").append(this.maxThroughput);
    localStringBuffer.append("\n DeviceType: ").append(this.deviceType);
    localStringBuffer.append("\n PreferredConnectivity: ").append(this.preferredConnectivity);
    localStringBuffer.append("\n CoupledSinkStatus: ").append(this.coupledSinkStatus);
    localStringBuffer.append("\n CoupledDeviceAddress: ").append(this.coupledDeviceAddress);
    localStringBuffer.append("\n IsCoupledSinkSupportedBySource: ").append(this.isCoupledSinkSupportedBySource);
    localStringBuffer.append("\n IsCoupledSinkSupportedBySink: ").append(this.isCoupledSinkSupportedBySink);
    localStringBuffer.append("\n IsAvailableForSession: ").append(this.isAvailableForSession);
    localStringBuffer.append("\n IsCoupledSinkSupportedBySource: ").append(this.isCoupledSinkSupportedBySource);
    localStringBuffer.append("\n IsContentProtectionSupported: ").append(this.isContentProtectionSupported);
    localStringBuffer.append("\n IsTimeSynchronizationSupported: ").append(this.isTimeSynchronizationSupported);
    localStringBuffer.append("\n IsAudioUnspportedAtPrimarySink: ").append(this.isAudioUnspportedAtPrimarySink);
    localStringBuffer.append("\n IsAudioOnlySupportedAtSource: ").append(this.isAudioOnlySupportedAtSource);
    localStringBuffer.append("\n IsTDLSPersistentGroupIntended: ").append(this.isTDLSPersistentGroupIntended);
    localStringBuffer.append("\n IsTDLSReInvokePersistentGroupReq: ").append(this.isTDLSReInvokePersistentGroupReq);
    return localStringBuffer.toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 1;
    paramParcel.writeString(this.deviceAddress);
    paramParcel.writeInt(this.dev_info);
    paramParcel.writeInt(this.ctrl_port);
    paramParcel.writeInt(this.max_tput);
    paramParcel.writeInt(this.cpled_sink_status);
    paramParcel.writeInt(this.sessionMgmtCtrlPort);
    paramParcel.writeInt(this.maxThroughput);
    paramParcel.writeInt(this.deviceType);
    paramParcel.writeInt(this.preferredConnectivity);
    paramParcel.writeInt(this.coupledSinkStatus);
    paramParcel.writeString(this.coupledDeviceAddress);
    int j;
    int k;
    label117: int m;
    label134: int n;
    label151: int i1;
    label168: int i2;
    label185: int i3;
    label202: int i4;
    label219: int i5;
    if (this.isCoupledSinkSupportedBySource)
    {
      j = i;
      paramParcel.writeByte((byte)j);
      if (!this.isCoupledSinkSupportedBySink)
        break label263;
      k = i;
      paramParcel.writeByte((byte)k);
      if (!this.isAvailableForSession)
        break label269;
      m = i;
      paramParcel.writeByte((byte)m);
      if (!this.isServiceDiscoverySupported)
        break label275;
      n = i;
      paramParcel.writeByte((byte)n);
      if (!this.isContentProtectionSupported)
        break label281;
      i1 = i;
      paramParcel.writeByte((byte)i1);
      if (!this.isTimeSynchronizationSupported)
        break label287;
      i2 = i;
      paramParcel.writeByte((byte)i2);
      if (!this.isAudioUnspportedAtPrimarySink)
        break label293;
      i3 = i;
      paramParcel.writeByte((byte)i3);
      if (!this.isAudioOnlySupportedAtSource)
        break label299;
      i4 = i;
      paramParcel.writeByte((byte)i4);
      if (!this.isTDLSPersistentGroupIntended)
        break label305;
      i5 = i;
      label236: paramParcel.writeByte((byte)i5);
      if (!this.isTDLSReInvokePersistentGroupReq)
        break label311;
    }
    while (true)
    {
      paramParcel.writeByte((byte)i);
      return;
      j = 0;
      break;
      label263: k = 0;
      break label117;
      label269: m = 0;
      break label134;
      label275: n = 0;
      break label151;
      label281: i1 = 0;
      break label168;
      label287: i2 = 0;
      break label185;
      label293: i3 = 0;
      break label202;
      label299: i4 = 0;
      break label219;
      label305: i5 = 0;
      break label236;
      label311: i = 0;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wfd.WfdInfo
 * JD-Core Version:    0.6.0
 */