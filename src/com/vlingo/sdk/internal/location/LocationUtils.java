package com.vlingo.sdk.internal.location;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.internal.core.ApplicationAdapter;

public class LocationUtils
{
  private static LocationUtils instance = null;
  private final double NO_LAT = 0.0D;
  private final double NO_LONG = 0.0D;
  private double lastLat = 0.0D;
  private double lastLong = 0.0D;
  private String lastMCC = null;

  private String _getCarrierCountry(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkCountryIso();
  }

  private String _getCellTowerInfo()
  {
    return _getCellTowerInfo(getCurrentContext());
  }

  private String _getCellTowerInfo(Context paramContext)
  {
    String str1 = "";
    StringBuffer localStringBuffer;
    CellLocation localCellLocation;
    String str3;
    if ((VLSdk.getInstance().getLocationOn()) && (paramContext != null))
    {
      LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
      Criteria localCriteria = new Criteria();
      localCriteria.setAccuracy(2);
      localCriteria.setPowerRequirement(1);
      localCriteria.setAltitudeRequired(false);
      localCriteria.setBearingRequired(false);
      localCriteria.setCostAllowed(false);
      localCriteria.setSpeedRequired(false);
      localStringBuffer = new StringBuffer(50);
      String str2 = localLocationManager.getBestProvider(localCriteria, true);
      if (str2 != null)
      {
        Location localLocation = localLocationManager.getLastKnownLocation(str2);
        if ((localLocation != null) && (Math.abs(localLocation.getLatitude()) <= 180.0D) && (Math.abs(localLocation.getLongitude()) <= 180.0D))
        {
          this.lastLat = localLocation.getLatitude();
          this.lastLong = localLocation.getLongitude();
          localStringBuffer.append("Lat=" + this.lastLat + ";Long=" + this.lastLong + ";Alt=" + localLocation.getAltitude());
        }
      }
      TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
      localCellLocation = localTelephonyManager.getCellLocation();
      str3 = localTelephonyManager.getNetworkOperator();
      if ((str3 == null) || (str3.length() < 4) || (str3.contains("@")))
        str3 = "0000";
      if (!(localCellLocation instanceof CdmaCellLocation))
        break label394;
      CdmaCellLocation localCdmaCellLocation = (CdmaCellLocation)localCellLocation;
      if (localStringBuffer.length() > 0)
        localStringBuffer.append(';');
      this.lastMCC = str3.substring(0, 3);
      localStringBuffer.append("CDMA_MCC=" + this.lastMCC + ";CDMA_MNC=" + str3.substring(3) + ";BID=" + localCdmaCellLocation.getBaseStationId() + ";SID=" + localCdmaCellLocation.getSystemId() + ";NID=" + localCdmaCellLocation.getNetworkId());
    }
    while (true)
    {
      str1 = localStringBuffer.toString();
      return str1;
      label394: if (!(localCellLocation instanceof GsmCellLocation))
        continue;
      GsmCellLocation localGsmCellLocation = (GsmCellLocation)localCellLocation;
      if (localStringBuffer.length() > 0)
        localStringBuffer.append(';');
      this.lastMCC = str3.substring(0, 3);
      localStringBuffer.append("GSM_MCC=" + this.lastMCC + ";GSM_MNC=" + str3.substring(3) + ";CID=" + localGsmCellLocation.getCid() + ";LAC=" + localGsmCellLocation.getLac() + ";RAC=0");
    }
  }

  private double _getLastLat()
  {
    double d = 0.0D;
    if (VLSdk.getInstance().getLocationOn())
    {
      _getCellTowerInfo();
      d = this.lastLat;
    }
    return d;
  }

  private double _getLastLong()
  {
    double d = 0.0D;
    if (VLSdk.getInstance().getLocationOn())
    {
      _getCellTowerInfo();
      d = this.lastLong;
    }
    return d;
  }

  private String _getMCC()
  {
    String str = "";
    if (VLSdk.getInstance().getLocationOn())
    {
      _getCellTowerInfo();
      str = this.lastMCC;
    }
    return str;
  }

  public static void destroy()
  {
    instance = null;
  }

  public static String getCarrierCountry(Context paramContext)
  {
    return getInstance()._getCarrierCountry(paramContext);
  }

  public static String getCellTowerInfo()
  {
    return getInstance()._getCellTowerInfo();
  }

  private Context getCurrentContext()
  {
    return ApplicationAdapter.getInstance().getApplicationContext();
  }

  public static LocationUtils getInstance()
  {
    monitorenter;
    try
    {
      if (instance == null)
        instance = new LocationUtils();
      LocationUtils localLocationUtils = instance;
      monitorexit;
      return localLocationUtils;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static double getLastLat()
  {
    return getInstance()._getLastLat();
  }

  public static double getLastLong()
  {
    return getInstance()._getLastLong();
  }

  public static String getMCC()
  {
    return getInstance()._getMCC();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.location.LocationUtils
 * JD-Core Version:    0.6.0
 */