package com.vlingo.core.internal.util;

import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.settings.Settings;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrdinalUtil
{
  public static final String all = "all";
  public static final String bottom = "bottom";
  public static final String last = "last";
  public static final String left = "left";
  public static final String middle = "middle";
  public static final String next = "next";
  public static final Pattern pattern = Pattern.compile("^(\\d{1,2})(st|nd|rd|th)$");
  public static final String right = "right";
  public static final String sPattern = "^(\\d{1,2})(st|nd|rd|th)$";
  public static final String top = "top";

  public static <T> void clearOrdinalData(VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    paramVVSActionHandlerListener.storeState(DialogDataType.ORDINAL_DATA, null);
    paramVVSActionHandlerListener.storeState(DialogDataType.ORDINAL_DATA_COUNT, null);
  }

  public static <T> T getElement(VVSActionHandlerListener paramVVSActionHandlerListener, String paramString)
  {
    Object localObject = null;
    List localList = (List)paramVVSActionHandlerListener.getState(DialogDataType.ORDINAL_DATA);
    Integer localInteger = (Integer)paramVVSActionHandlerListener.getState(DialogDataType.ORDINAL_DATA_COUNT);
    if (localList == null);
    int i;
    while (true)
    {
      return localObject;
      if (localInteger != null)
        break;
      i = Settings.getInt("widget_display_max", -1);
      if (i <= 0)
        continue;
      if (localList.size() <= i)
        break label86;
    }
    while (true)
    {
      localInteger = Integer.valueOf(i);
      localObject = getOrdinalElement(localList, localInteger.intValue(), paramString);
      break;
      label86: i = localList.size();
    }
  }

  public static <T> T getElementFromList(List<T> paramList, String paramString)
  {
    if (paramList == null);
    for (Object localObject = null; ; localObject = getOrdinalElement(paramList, paramList.size(), paramString))
      return localObject;
  }

  public static <T> List<T> getElements(VVSActionHandlerListener paramVVSActionHandlerListener, String paramString)
  {
    Object localObject1 = null;
    List localList = (List)paramVVSActionHandlerListener.getState(DialogDataType.ORDINAL_DATA);
    Integer localInteger = (Integer)paramVVSActionHandlerListener.getState(DialogDataType.ORDINAL_DATA_COUNT);
    if (localList == null);
    int i;
    while (true)
    {
      return localObject1;
      if (localInteger != null)
        break;
      i = Settings.getInt("widget_display_max", -1);
      if (i <= 0)
        continue;
      if (localList.size() <= i)
        break label101;
      label65: localInteger = Integer.valueOf(i);
    }
    Object localObject3;
    if ("all".equals(paramString))
      localObject3 = localList.subList(0, localInteger.intValue());
    while (true)
    {
      localObject1 = localObject3;
      break;
      label101: i = localList.size();
      break label65;
      Object localObject2 = getOrdinalElement(localList, localInteger.intValue(), paramString);
      localObject3 = new ArrayList();
      ((List)localObject3).add(localObject2);
    }
  }

  private static <T> T getOrdinalElement(List<T> paramList, int paramInt, String paramString)
  {
    int i = ordinalToInt(paramString, paramInt);
    if ((i >= 0) && (i < paramInt));
    for (Object localObject = paramList.get(i); ; localObject = null)
      return localObject;
  }

  public static int ordinalToInt(String paramString, int paramInt)
  {
    int i;
    if ("top".equalsIgnoreCase(paramString))
      i = 0;
    while (true)
    {
      return i;
      if (("last".equalsIgnoreCase(paramString)) || ("bottom".equalsIgnoreCase(paramString)))
      {
        i = paramInt - 1;
        continue;
      }
      if (("middle".equalsIgnoreCase(paramString)) && (paramInt % 2 == 1))
      {
        i = (paramInt - 1) / 2;
        continue;
      }
      Matcher localMatcher = pattern.matcher(paramString);
      if (localMatcher.find())
      {
        i = -1 + Integer.parseInt(localMatcher.group(1));
        continue;
      }
      System.out.println("NO MATCH");
      i = -1;
    }
  }

  public static <T> void storeOrdinalData(VVSActionHandlerListener paramVVSActionHandlerListener, int paramInt)
  {
    paramVVSActionHandlerListener.storeState(DialogDataType.ORDINAL_DATA_COUNT, Integer.valueOf(paramInt));
  }

  public static <T> void storeOrdinalData(VVSActionHandlerListener paramVVSActionHandlerListener, List<T> paramList)
  {
    paramVVSActionHandlerListener.storeState(DialogDataType.ORDINAL_DATA, paramList);
  }

  public static <T> void storeOrdinalData(VVSActionHandlerListener paramVVSActionHandlerListener, List<T> paramList, int paramInt)
  {
    paramVVSActionHandlerListener.storeState(DialogDataType.ORDINAL_DATA, paramList);
    paramVVSActionHandlerListener.storeState(DialogDataType.ORDINAL_DATA_COUNT, Integer.valueOf(paramInt));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.OrdinalUtil
 * JD-Core Version:    0.6.0
 */