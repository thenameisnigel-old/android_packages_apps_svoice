package com.vlingo.midas.naver;

import java.util.Hashtable;

public class NaverRegionItem extends NaverLocalItem
{
  public NaverRegionItem(Hashtable<String, String> paramHashtable)
  {
    super(paramHashtable);
  }

  public String getMatchAddress()
  {
    return getStringOrEmptyString("matchAddress");
  }

  public String getZipCode()
  {
    return getStringOrEmptyString("zipCode");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.naver.NaverRegionItem
 * JD-Core Version:    0.6.0
 */