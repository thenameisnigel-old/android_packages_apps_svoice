package com.vlingo.core.internal.answers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.location.LocationUtils;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ApplicationAdapter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AnswerManager
{
  private static final String UNKNOWN_LOCATION = "unknown";
  private static final String VARIABLE_POSTFIX = "}";
  private static final String VARIABLE_PREFIX = "${";

  private static String getDateString(Context paramContext)
  {
    return java.text.DateFormat.getDateInstance(0, Settings.getCurrentLocale()).format(new Date());
  }

  private static String getLocation(Context paramContext)
  {
    try
    {
      double d1 = LocationUtils.getLastLat();
      double d2 = LocationUtils.getLastLong();
      if ((d1 != 0.0D) || (d2 != 0.0D))
      {
        List localList = new Geocoder(paramContext, Locale.getDefault()).getFromLocation(d1, d2, 1);
        if (localList.size() > 0)
        {
          Address localAddress = (Address)localList.get(0);
          String str1 = localAddress.getThoroughfare();
          String str2 = localAddress.getLocality();
          if ((str1 != null) || (str2 != null))
          {
            localObject = "";
            if (str1 != null)
              localObject = (String)localObject + str1 + " ";
            if (str2 != null)
            {
              String str3 = (String)localObject + str2 + " ";
              localObject = str3;
            }
            return localObject;
          }
        }
      }
    }
    catch (Exception localException)
    {
      while (true)
        Object localObject = "unknown";
    }
  }

  private static String getTimeString(Context paramContext)
  {
    return android.text.format.DateFormat.getTimeFormat(paramContext).format(new Date());
  }

  private static String getVariable(String paramString)
  {
    return "${" + paramString + "}";
  }

  public static String replaceAnswerVariables(String paramString)
  {
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    if (paramString.contains(getVariable("time")))
    {
      String str4 = getTimeString(localContext);
      paramString = paramString.replace(getVariable("time"), str4);
    }
    if (paramString.contains(getVariable("date")))
    {
      String str3 = getDateString(localContext);
      paramString = paramString.replace(getVariable("date"), str3);
    }
    if (paramString.contains(getVariable("location")))
    {
      String str2 = getLocation(localContext);
      paramString = paramString.replace(getVariable("location"), str2);
    }
    String str1;
    if (paramString.contains(getVariable("whereami")))
    {
      str1 = getLocation(localContext);
      if (!"unknown".equalsIgnoreCase(str1))
        break label135;
    }
    label135: for (paramString = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_not_detected_current_location); ; paramString = paramString.replace(getVariable("whereami"), str1))
      return paramString;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.answers.AnswerManager
 * JD-Core Version:    0.6.0
 */