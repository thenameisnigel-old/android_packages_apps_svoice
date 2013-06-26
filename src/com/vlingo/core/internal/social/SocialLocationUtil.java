package com.vlingo.core.internal.social;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.util.FloatMath;

public class SocialLocationUtil
{
  public static double distance(Location paramLocation, float paramFloat1, float paramFloat2)
  {
    float f1 = (float)paramLocation.getLatitude();
    float f2 = (float)paramLocation.getLongitude();
    float f3 = f1 / 57.294003F;
    float f4 = f2 / 57.294003F;
    float f5 = paramFloat1 / 57.294003F;
    float f6 = paramFloat2 / 57.294003F;
    float f7 = FloatMath.cos(f3) * FloatMath.cos(f4) * FloatMath.cos(f5) * FloatMath.cos(f6);
    float f8 = FloatMath.cos(f3) * FloatMath.sin(f4) * FloatMath.cos(f5) * FloatMath.sin(f6);
    return 6366000.0D * Math.acos(FloatMath.sin(f3) * FloatMath.sin(f5) + (f7 + f8));
  }

  public static Location getLastKnownLocation(Context paramContext)
  {
    LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
    Criteria localCriteria = new Criteria();
    localCriteria.setAccuracy(2);
    localCriteria.setAltitudeRequired(false);
    localCriteria.setBearingRequired(false);
    localCriteria.setCostAllowed(false);
    localCriteria.setSpeedRequired(false);
    String str = localLocationManager.getBestProvider(localCriteria, true);
    if (str != null);
    for (Location localLocation = localLocationManager.getLastKnownLocation(str); ; localLocation = null)
      return localLocation;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.social.SocialLocationUtil
 * JD-Core Version:    0.6.0
 */