package com.vlingo.midas.naver;

import java.util.Hashtable;

public class NaverItem
{
  public static final String DETAIL_URL = "detailUrl";
  public static final String Empty = "-";
  public static final String MORE_URL = "moreUrl";
  Hashtable<String, String> table;

  public NaverItem(Hashtable<String, String> paramHashtable)
  {
    this.table = paramHashtable;
  }

  public String getDetailUrl()
  {
    return getString("detailUrl");
  }

  public String getFirstCSV(String paramString)
  {
    if (this.table.containsKey(paramString));
    for (String str = ((String)this.table.get(paramString)).split(",")[0]; ; str = "-")
      return str;
  }

  public String getMoreUrl()
  {
    return getString("moreUrl");
  }

  public String getString(String paramString)
  {
    if (this.table.containsKey(paramString));
    for (String str = (String)this.table.get(paramString); ; str = null)
      return str;
  }

  public String getStringOrEmptyString(String paramString)
  {
    if (getString(paramString) == null);
    for (String str = "-"; ; str = getString(paramString))
      return str;
  }

  public String getTitle()
  {
    return getStringOrEmptyString("title");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.naver.NaverItem
 * JD-Core Version:    0.6.0
 */