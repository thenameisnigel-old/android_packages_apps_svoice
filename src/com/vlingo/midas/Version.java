package com.vlingo.midas;

public class Version
{
  private static final String c_Major = "11";
  private static final String c_Minor = "0";
  private static final String c_Patch = "1";
  private static final String c_Release = "0";
  private static final String c_SVN = "19199";

  String getMajor()
  {
    return "11";
  }

  String getMinor()
  {
    return "0";
  }

  String getPatch()
  {
    return "1";
  }

  String getRelease()
  {
    return "0";
  }

  String getSVN()
  {
    return "19199";
  }

  String getVersion()
  {
    return getMajor() + "." + getMinor() + "." + getRelease() + "." + getPatch() + "." + getSVN();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.Version
 * JD-Core Version:    0.6.0
 */