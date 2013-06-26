package com.vlingo.sdk.internal.deviceinfo;

public class CarrierUtils
{
  public static Carrier getCarrier(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0));
    for (Carrier localCarrier = null; ; localCarrier = Carriers.getInstance().getCarrierByName(paramString))
      return localCarrier;
  }

  public static String getCarrierISO2Country(String paramString)
  {
    Carrier localCarrier = getCarrier(paramString);
    if (localCarrier != null);
    for (String str = localCarrier.iso2letterCountry; ; str = null)
      return str;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.deviceinfo.CarrierUtils
 * JD-Core Version:    0.6.0
 */