package com.vlingo.core.internal.localsearch;

import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;

public class LocalSearchRequestInfo
{
  private String city;
  private String query;
  private String state;

  public LocalSearchRequestInfo()
  {
    this(null, null, null);
  }

  public LocalSearchRequestInfo(String paramString1, String paramString2, String paramString3)
  {
    setQuery(paramString1);
    setCity(paramString2);
    setState(paramString3);
  }

  public String getCity()
  {
    return this.city;
  }

  public String getLocalizedCityState()
  {
    String str;
    if ((StringUtils.isNullOrWhiteSpace(this.city)) || (StringUtils.isNullOrWhiteSpace(this.state)))
      str = null;
    while (true)
    {
      return str;
      if (Settings.getLanguageApplication().equals("ko-KR"))
      {
        str = this.state + ", " + this.city;
        continue;
      }
      str = this.city + ", " + this.state;
    }
  }

  public String getQuery()
  {
    return this.query;
  }

  public String getState()
  {
    return this.state;
  }

  public void setCity(String paramString)
  {
    this.city = paramString;
  }

  public void setQuery(String paramString)
  {
    this.query = paramString;
  }

  public void setState(String paramString)
  {
    this.state = paramString;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.localsearch.LocalSearchRequestInfo
 * JD-Core Version:    0.6.0
 */