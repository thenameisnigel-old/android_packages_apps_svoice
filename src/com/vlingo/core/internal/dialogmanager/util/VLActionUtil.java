package com.vlingo.core.internal.dialogmanager.util;

import android.content.Intent;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VLActionUtil
{
  public static boolean extractParamBool(Intent paramIntent, String paramString, boolean paramBoolean)
  {
    return paramIntent.getBooleanExtra(paramString, paramBoolean);
  }

  public static int extractParamInt(Intent paramIntent, String paramString, int paramInt)
  {
    return paramIntent.getIntExtra(paramString, paramInt);
  }

  public static String extractParamString(Intent paramIntent, String paramString)
    throws InvalidParameterException
  {
    String str = paramIntent.getStringExtra(paramString);
    if (StringUtils.isNullOrWhiteSpace(str))
      throw new InvalidParameterException("Null or missing required parameter: " + paramString + " for action " + paramIntent.getAction());
    return str;
  }

  public static boolean getParamBool(VLAction paramVLAction, String paramString, boolean paramBoolean1, boolean paramBoolean2)
    throws InvalidParameterException
  {
    Boolean localBoolean = getParamBoolOrNull(paramVLAction, paramString, paramBoolean2);
    if (localBoolean == null);
    while (true)
    {
      return paramBoolean1;
      paramBoolean1 = localBoolean.booleanValue();
    }
  }

  public static Boolean getParamBoolOrNull(VLAction paramVLAction, String paramString, boolean paramBoolean)
    throws InvalidParameterException
  {
    Boolean localBoolean = null;
    String str = paramVLAction.getParamValue(paramString);
    if (StringUtils.isNullOrWhiteSpace(str))
    {
      if (paramBoolean)
        throw new InvalidParameterException("empty value for " + paramString);
    }
    else
    {
      if (!str.equalsIgnoreCase("true"))
        break label67;
      localBoolean = Boolean.valueOf(true);
    }
    label67: 
    do
      while (true)
      {
        return localBoolean;
        if (!str.equalsIgnoreCase("false"))
          break;
        localBoolean = Boolean.valueOf(false);
      }
    while (!paramBoolean);
    throw new InvalidParameterException(paramString + " value " + str + " not specified as a boolean");
  }

  public static float getParamFloat(VLAction paramVLAction, String paramString, float paramFloat, boolean paramBoolean)
    throws InvalidParameterException
  {
    String str = paramVLAction.getParamValue(paramString);
    if (StringUtils.isNullOrWhiteSpace(str))
    {
      if (!paramBoolean)
        break label60;
      throw new InvalidParameterException("empty value for " + paramString);
    }
    try
    {
      float f = Float.parseFloat(str);
      paramFloat = f;
      label60: return paramFloat;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (!paramBoolean);
    }
    throw localNumberFormatException;
  }

  public static int getParamInt(VLAction paramVLAction, String paramString, int paramInt, boolean paramBoolean)
    throws InvalidParameterException
  {
    String str = paramVLAction.getParamValue(paramString);
    if (StringUtils.isNullOrWhiteSpace(str))
    {
      if (!paramBoolean)
        break label60;
      throw new InvalidParameterException("empty value for " + paramString);
    }
    try
    {
      int i = Integer.parseInt(str);
      paramInt = i;
      label60: return paramInt;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (!paramBoolean);
    }
    throw localNumberFormatException;
  }

  public static List<Integer> getParamIntList(VLAction paramVLAction, String paramString, boolean paramBoolean)
    throws InvalidParameterException
  {
    String str = getParamString(paramVLAction, paramString, paramBoolean);
    LinkedList localLinkedList = new LinkedList();
    if (!StringUtils.isNullOrWhiteSpace(str))
      try
      {
        JSONArray localJSONArray = new JSONObject(str).getJSONArray(paramString);
        for (int i = 0; i < localJSONArray.length(); i++)
          localLinkedList.add(Integer.valueOf(localJSONArray.getInt(i)));
      }
      catch (JSONException localJSONException)
      {
        throw new InvalidParameterException("Unable to parse choices into JSON ints: " + localJSONException.getLocalizedMessage());
      }
    return localLinkedList;
  }

  public static long getParamLong(VLAction paramVLAction, String paramString, long paramLong, boolean paramBoolean)
    throws InvalidParameterException
  {
    String str = paramVLAction.getParamValue(paramString);
    if (StringUtils.isNullOrWhiteSpace(str))
    {
      if (!paramBoolean)
        break label61;
      throw new InvalidParameterException("empty value for " + paramString);
    }
    try
    {
      long l = Long.parseLong(str);
      paramLong = l;
      label61: return paramLong;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (!paramBoolean);
    }
    throw localNumberFormatException;
  }

  public static String getParamString(VLAction paramVLAction, String paramString, boolean paramBoolean)
    throws InvalidParameterException
  {
    String str = paramVLAction.getParamValue(paramString);
    if ((StringUtils.isNullOrWhiteSpace(str)) && (paramBoolean))
      throw new InvalidParameterException("Missing required parameter: " + paramString);
    return str;
  }

  public static List<String> getParamStringList(VLAction paramVLAction, String paramString1, String paramString2, boolean paramBoolean)
    throws InvalidParameterException
  {
    String str = getParamString(paramVLAction, paramString1, paramBoolean);
    LinkedList localLinkedList = new LinkedList();
    if (!StringUtils.isNullOrWhiteSpace(str))
      try
      {
        JSONArray localJSONArray = new JSONObject(str).getJSONArray(paramString2);
        for (int i = 0; i < localJSONArray.length(); i++)
          localLinkedList.add(localJSONArray.getString(i));
      }
      catch (JSONException localJSONException)
      {
        throw new InvalidParameterException("Unable to parse choices into JSON Strings: " + localJSONException.getLocalizedMessage());
      }
    return localLinkedList;
  }

  public static boolean isParamSpecified(VLAction paramVLAction, String paramString)
  {
    return paramVLAction.getParameterNames().contains(paramString);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.util.VLActionUtil
 * JD-Core Version:    0.6.0
 */