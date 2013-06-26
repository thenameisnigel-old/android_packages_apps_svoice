package com.samsung.wfd;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class WfdInfo$1
  implements Parcelable.Creator<WfdInfo>
{
  public WfdInfo createFromParcel(Parcel paramParcel)
  {
    boolean bool1 = true;
    WfdInfo localWfdInfo = new WfdInfo();
    localWfdInfo.deviceAddress = paramParcel.readString();
    localWfdInfo.dev_info = paramParcel.readInt();
    localWfdInfo.ctrl_port = paramParcel.readInt();
    localWfdInfo.max_tput = paramParcel.readInt();
    localWfdInfo.cpled_sink_status = paramParcel.readInt();
    localWfdInfo.sessionMgmtCtrlPort = paramParcel.readInt();
    localWfdInfo.maxThroughput = paramParcel.readInt();
    localWfdInfo.deviceType = paramParcel.readInt();
    localWfdInfo.preferredConnectivity = paramParcel.readInt();
    localWfdInfo.coupledSinkStatus = paramParcel.readInt();
    localWfdInfo.coupledDeviceAddress = paramParcel.readString();
    boolean bool2;
    boolean bool3;
    label124: boolean bool4;
    label140: boolean bool5;
    label156: boolean bool6;
    label172: boolean bool7;
    label188: boolean bool8;
    label204: boolean bool9;
    label220: boolean bool10;
    if (paramParcel.readByte() != 0)
    {
      bool2 = bool1;
      localWfdInfo.isCoupledSinkSupportedBySource = bool2;
      if (paramParcel.readByte() == 0)
        break label262;
      bool3 = bool1;
      localWfdInfo.isCoupledSinkSupportedBySink = bool3;
      if (paramParcel.readByte() == 0)
        break label268;
      bool4 = bool1;
      localWfdInfo.isAvailableForSession = bool4;
      if (paramParcel.readByte() == 0)
        break label274;
      bool5 = bool1;
      localWfdInfo.isCoupledSinkSupportedBySource = bool5;
      if (paramParcel.readByte() == 0)
        break label280;
      bool6 = bool1;
      localWfdInfo.isContentProtectionSupported = bool6;
      if (paramParcel.readByte() == 0)
        break label286;
      bool7 = bool1;
      localWfdInfo.isTimeSynchronizationSupported = bool7;
      if (paramParcel.readByte() == 0)
        break label292;
      bool8 = bool1;
      localWfdInfo.isAudioUnspportedAtPrimarySink = bool8;
      if (paramParcel.readByte() == 0)
        break label298;
      bool9 = bool1;
      localWfdInfo.isAudioOnlySupportedAtSource = bool9;
      if (paramParcel.readByte() == 0)
        break label304;
      bool10 = bool1;
      label236: localWfdInfo.isTDLSPersistentGroupIntended = bool10;
      if (paramParcel.readByte() == 0)
        break label310;
    }
    while (true)
    {
      localWfdInfo.isTDLSReInvokePersistentGroupReq = bool1;
      return localWfdInfo;
      bool2 = false;
      break;
      label262: bool3 = false;
      break label124;
      label268: bool4 = false;
      break label140;
      label274: bool5 = false;
      break label156;
      label280: bool6 = false;
      break label172;
      label286: bool7 = false;
      break label188;
      label292: bool8 = false;
      break label204;
      label298: bool9 = false;
      break label220;
      label304: bool10 = false;
      break label236;
      label310: bool1 = false;
    }
  }

  public WfdInfo[] newArray(int paramInt)
  {
    return new WfdInfo[paramInt];
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wfd.WfdInfo.1
 * JD-Core Version:    0.6.0
 */