package com.vlingo.core.internal.util;

import java.util.Iterator;
import java.util.List;

public class SQLExpressionUtil
{
  private static final String LIKE_PATTERN = "%s like '%%%s%%'";
  private static final String OR_PATTERN = "%s or %s";

  public static String escape(String paramString)
  {
    return paramString.replace("'", "''");
  }

  public static String like(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString1.length() == 0))
      throw new IllegalArgumentException("column name can not be null or empty");
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = escape(paramString2);
    return String.format("%s like '%%%s%%'", arrayOfObject);
  }

  public static String like(String paramString, List<String> paramList)
  {
    if (paramList.size() == 0)
      throw new IllegalArgumentException("values can not be empty");
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      localStringBuilder.append(like(paramString, escape((String)localIterator.next())));
      localStringBuilder.append(" or ");
    }
    return localStringBuilder.delete(-4 + localStringBuilder.length(), localStringBuilder.length()).toString();
  }

  public static String like(String paramString, String[] paramArrayOfString)
  {
    if (paramArrayOfString.length == 0)
      throw new IllegalArgumentException("values can not be empty");
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfString.length;
    for (int j = 0; j < i; j++)
    {
      localStringBuilder.append(like(paramString, escape(paramArrayOfString[j])));
      localStringBuilder.append(" or ");
    }
    return localStringBuilder.delete(-4 + localStringBuilder.length(), localStringBuilder.length()).toString();
  }

  public static String or(String paramString1, String paramString2)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = paramString2;
    return String.format("%s or %s", arrayOfObject);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.SQLExpressionUtil
 * JD-Core Version:    0.6.0
 */