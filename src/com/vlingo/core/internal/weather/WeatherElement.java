package com.vlingo.core.internal.weather;

import com.vlingo.sdk.internal.util.StringUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherElement
  implements Serializable
{
  private static final long serialVersionUID = -764549335895920537L;
  private Map<String, String> attributes = null;
  private List<WeatherElement> child = null;
  private String errorMessage = null;
  private final String name;

  public WeatherElement(String paramString)
  {
    this.name = paramString;
  }

  private void setChild(List<WeatherElement> paramList)
  {
    this.child = paramList;
  }

  public void addAttribute(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null))
      throw new IllegalArgumentException("key OR value of attribute cannot be null");
    getAttributes().put(paramString1, paramString2);
  }

  public boolean addChild(WeatherElement paramWeatherElement)
  {
    if (this.child == null)
      this.child = new ArrayList();
    return this.child.add(paramWeatherElement);
  }

  protected WeatherElement clone()
  {
    WeatherElement localWeatherElement = new WeatherElement(this.name);
    localWeatherElement.setAttributes(this.attributes);
    this.attributes = null;
    localWeatherElement.setChild(this.child);
    this.child = null;
    return localWeatherElement;
  }

  public WeatherElement findChildWithAttr(String paramString1, String paramString2)
    throws WeatherElement.WeatherNotFound
  {
    if (!StringUtils.isNullOrWhiteSpace(paramString2))
      for (int i = 0; i < getChildCount(); i++)
      {
        WeatherElement localWeatherElement = getChild(i);
        if (paramString2.equalsIgnoreCase((String)localWeatherElement.getAttributes().get(paramString1)))
          return localWeatherElement;
      }
    throw new WeatherNotFound("child with '" + paramString1 + "' of '" + paramString2 + "' not found");
  }

  public WeatherElement findNamedChild(String paramString)
    throws WeatherElement.WeatherNotFound
  {
    for (int i = 0; i < getChildCount(); i++)
    {
      WeatherElement localWeatherElement = getChild(i);
      if (paramString.equals(localWeatherElement.getName()))
        return localWeatherElement;
    }
    throw new WeatherNotFound("named child '" + paramString + "' not found");
  }

  public Map<String, String> getAttributes()
  {
    if (this.attributes == null)
      this.attributes = new HashMap();
    return this.attributes;
  }

  public WeatherElement getChild(int paramInt)
  {
    WeatherElement localWeatherElement = null;
    if (this.child != null)
      localWeatherElement = (WeatherElement)this.child.get(paramInt);
    return localWeatherElement;
  }

  public int getChildCount()
  {
    int i = 0;
    if (this.child != null)
      i = this.child.size();
    return i;
  }

  public String getErrorMessage()
  {
    return this.errorMessage;
  }

  public String getName()
  {
    return this.name;
  }

  public void setAttributes(Map<String, String> paramMap)
  {
    this.attributes = paramMap;
  }

  public void setErrorMessage(String paramString)
  {
    this.errorMessage = paramString;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("\nName ");
    localStringBuilder.append(this.name);
    localStringBuilder.append("\n[Attributes] [ ");
    if (this.attributes != null)
      localStringBuilder.append(this.attributes.toString());
    localStringBuilder.append("]\n Children [");
    if (this.child != null)
      for (int i = 0; i < this.child.size(); i++)
        localStringBuilder.append(((WeatherElement)this.child.get(i)).toString());
    localStringBuilder.append("]\n");
    return localStringBuilder.toString();
  }

  public class WeatherNotFound extends Exception
  {
    public WeatherNotFound()
    {
    }

    public WeatherNotFound(String arg2)
    {
      super();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherElement
 * JD-Core Version:    0.6.0
 */