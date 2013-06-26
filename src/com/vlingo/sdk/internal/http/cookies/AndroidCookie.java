package com.vlingo.sdk.internal.http.cookies;

import com.vlingo.sdk.internal.util.StringUtils;
import java.util.Date;
import org.apache.http.impl.cookie.BasicClientCookie;

public class AndroidCookie extends BasicClientCookie
  implements Cookie
{
  private static final int FIELD_COUNT = 5;
  private static final char FIELD_DELIMITER = ',';
  private static final int FIELD_INDEX_DOMAIN = 2;
  private static final int FIELD_INDEX_EXPIRES = 4;
  private static final int FIELD_INDEX_NAME = 0;
  private static final int FIELD_INDEX_PATH = 3;
  private static final int FIELD_INDEX_VALUE = 1;

  public AndroidCookie(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }

  public static AndroidCookie deserialize(String paramString)
    throws Exception
  {
    String[] arrayOfString = StringUtils.split(paramString, ',');
    if (arrayOfString.length != 5)
      throw new Exception("Badly formatted cookie data");
    AndroidCookie localAndroidCookie = new AndroidCookie(arrayOfString[0], arrayOfString[1]);
    if ((arrayOfString[2] != null) && (arrayOfString[2].length() > 0))
      localAndroidCookie.setDomain(arrayOfString[2]);
    if ((arrayOfString[3] != null) && (arrayOfString[3].length() > 0))
      localAndroidCookie.setPath(arrayOfString[3]);
    if ((arrayOfString[4] != null) && (arrayOfString[4].length() > 0))
      localAndroidCookie.setExpires(Long.parseLong(arrayOfString[4]));
    return localAndroidCookie;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool;
    if ((paramObject instanceof AndroidCookie))
    {
      AndroidCookie localAndroidCookie = (AndroidCookie)paramObject;
      if ((StringUtils.isEqual(getName(), localAndroidCookie.getName())) && (StringUtils.isEqual(getValue(), localAndroidCookie.getValue())) && (StringUtils.isEqual(getPath(), localAndroidCookie.getPath())) && (StringUtils.isEqual(getDomain(), localAndroidCookie.getDomain())))
        bool = true;
    }
    while (true)
    {
      return bool;
      bool = false;
      continue;
      bool = super.equals(paramObject);
    }
  }

  public long getExpires()
  {
    Date localDate = getExpiryDate();
    long l;
    if (localDate == null)
      l = 0L;
    while (true)
    {
      return l;
      l = localDate.getTime();
    }
  }

  public int hashCode()
  {
    int i = 31;
    if (getName() != null)
      i *= getName().hashCode();
    if (getValue() != null)
      i *= getValue().hashCode();
    if (getPath() != null)
      i *= getPath().hashCode();
    if (getDomain() != null)
      i *= getDomain().hashCode();
    return i;
  }

  public boolean isExpired()
  {
    return isExpired(new Date());
  }

  public boolean isMatch(String paramString1, String paramString2)
  {
    int i = 1;
    int j = 1;
    int k;
    if ((getDomain() != null) && (getDomain().length() > 0))
    {
      if ((paramString1 != null) && (paramString1.equalsIgnoreCase(paramString1)))
      {
        k = i;
        j &= k;
      }
    }
    else if ((getPath() != null) && (getPath().length() > 0))
      if ((paramString2 == null) || (!paramString2.startsWith(getPath())))
        break label91;
    while (true)
    {
      j &= i;
      return j;
      k = 0;
      break;
      label91: i = 0;
    }
  }

  public String serialize()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getName());
    localStringBuilder.append(',');
    localStringBuilder.append(getValue());
    localStringBuilder.append(',');
    if (getDomain() != null)
      localStringBuilder.append(getDomain());
    localStringBuilder.append(',');
    if (getPath() != null)
      localStringBuilder.append(getPath());
    localStringBuilder.append(',');
    if (getExpiryDate() != null)
      localStringBuilder.append(getExpires());
    return localStringBuilder.toString();
  }

  public void setExpires(long paramLong)
  {
    setExpiryDate(new Date(paramLong));
  }

  public String toString()
  {
    return "AndroidCookie: expires=" + getExpires() + " isExpired=" + isExpired() + " domain=" + getDomain() + " path=" + getPath() + " name=" + getName() + " value=" + getValue() + " version=" + getVersion();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.cookies.AndroidCookie
 * JD-Core Version:    0.6.0
 */