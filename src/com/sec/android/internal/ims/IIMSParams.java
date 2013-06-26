package com.sec.android.internal.ims;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class IIMSParams
  implements Parcelable
{
  public static final Parcelable.Creator<IIMSParams> CREATOR = new IIMSParams.1();
  private String audioCodec;
  private String historyInfo;
  private String isConferenceCall;
  private String modifySupported;
  private String numberPlus;
  private String pLettering;
  private String reasonCode;

  public IIMSParams(Parcel paramParcel)
  {
    this.pLettering = paramParcel.readString();
    this.historyInfo = paramParcel.readString();
    this.modifySupported = paramParcel.readString();
    this.audioCodec = paramParcel.readString();
    this.numberPlus = paramParcel.readString();
    this.reasonCode = paramParcel.readString();
    this.isConferenceCall = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public String getAudioCodec()
  {
    return this.audioCodec;
  }

  public String getErrorReasonCode()
  {
    return this.reasonCode;
  }

  public String getHistoryInfo()
  {
    return this.historyInfo;
  }

  public String getIsConferenceCall()
  {
    return this.isConferenceCall;
  }

  public String getModifyHeader()
  {
    return this.modifySupported;
  }

  public String getNumberPlus()
  {
    return this.numberPlus;
  }

  public String getPLettering()
  {
    return this.pLettering;
  }

  public void setAudioCodec(String paramString)
  {
    this.audioCodec = paramString;
  }

  public void setErrorReasonCode(String paramString)
  {
    this.reasonCode = paramString;
  }

  public void setHistoryInfo(String paramString)
  {
    this.historyInfo = paramString;
  }

  public void setIsConferenceCall(String paramString)
  {
    this.isConferenceCall = paramString;
  }

  public void setModifyHeader(String paramString)
  {
    this.modifySupported = paramString;
  }

  public void setNumberPlus(String paramString)
  {
    this.numberPlus = paramString;
  }

  public void setPLettering(String paramString)
  {
    this.pLettering = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.pLettering);
    paramParcel.writeString(this.historyInfo);
    paramParcel.writeString(this.modifySupported);
    paramParcel.writeString(this.audioCodec);
    paramParcel.writeString(this.numberPlus);
    paramParcel.writeString(this.reasonCode);
    paramParcel.writeString(this.isConferenceCall);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.internal.ims.IIMSParams
 * JD-Core Version:    0.6.0
 */