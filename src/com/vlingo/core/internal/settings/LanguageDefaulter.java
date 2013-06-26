package com.vlingo.core.internal.settings;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.array;
import com.vlingo.core.internal.VlingoAndroidCore;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

public class LanguageDefaulter
{
  private static String chooseBestLanguage(Set<String> paramSet, Locale paramLocale)
  {
    String str;
    if (paramLocale.getLanguage().equalsIgnoreCase("es"))
    {
      Iterator localIterator = paramSet.iterator();
      do
      {
        if (!localIterator.hasNext())
          break;
        str = (String)localIterator.next();
      }
      while ((!str.equals("v-es-LA")) && (!str.equals("v-es-NA")));
    }
    while (true)
    {
      return str;
      str = (String)paramSet.toArray()[0];
    }
  }

  private static String getCountrySubString(String paramString)
  {
    if (isVlingoSpecialLanguage(paramString));
    for (String str = paramString.substring(5, 7); ; str = paramString.substring(3, 5))
      return str;
  }

  public static String getDefaultLanguage(Context paramContext)
  {
    String[] arrayOfString = VlingoAndroidCore.getResourceProvider().getStringArray(ResourceIdProvider.array.core_languages_iso);
    Configuration localConfiguration = paramContext.getResources().getConfiguration();
    if (localConfiguration != null);
    for (String str = getDefaultLanguage(localConfiguration.locale, arrayOfString); ; str = "en-US")
      return str;
  }

  private static String getDefaultLanguage(Locale paramLocale, String[] paramArrayOfString)
  {
    String str1 = paramLocale.getLanguage() + "-" + paramLocale.getCountry();
    int i = paramArrayOfString.length;
    int j = 0;
    if (j < i)
      if (!str1.equals(paramArrayOfString[j]));
    while (true)
    {
      return str1;
      j++;
      break;
      HashSet localHashSet = new HashSet();
      int k = paramArrayOfString.length;
      for (int m = 0; m < k; m++)
      {
        String str2 = paramArrayOfString[m];
        String str3 = getLanguageSubString(str2);
        if (!paramLocale.getLanguage().equals(str3))
          continue;
        localHashSet.add(str2);
      }
      if (localHashSet.size() == 1)
      {
        str1 = (String)localHashSet.toArray()[0];
        continue;
      }
      if (localHashSet.size() > 1)
      {
        str1 = chooseBestLanguage(localHashSet, paramLocale);
        continue;
      }
      str1 = "en-US";
    }
  }

  private static String getLanguageSubString(String paramString)
  {
    if (isVlingoSpecialLanguage(paramString));
    for (String str = paramString.substring(2, 4); ; str = paramString.substring(0, 2))
      return str;
  }

  private static boolean isVlingoSpecialLanguage(String paramString)
  {
    return paramString.substring(0, 2).equals("v-");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.settings.LanguageDefaulter
 * JD-Core Version:    0.6.0
 */