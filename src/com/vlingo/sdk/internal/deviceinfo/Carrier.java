package com.vlingo.sdk.internal.deviceinfo;

public class Carrier
{
  public String iso2letterCountry;
  public String iso3letterCountry;
  public String name;

  public Carrier(String paramString1, String paramString2, String paramString3)
  {
    this.name = paramString1;
    this.iso2letterCountry = paramString2;
    this.iso3letterCountry = paramString3;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.deviceinfo.Carrier
 * JD-Core Version:    0.6.0
 */