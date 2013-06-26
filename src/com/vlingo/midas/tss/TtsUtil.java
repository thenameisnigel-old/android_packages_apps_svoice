package com.vlingo.midas.tss;

import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import java.util.ArrayList;

public class TtsUtil
{
  private static final int TTS_CHUNK_SIZE_MAX = 250;
  private static final int TTS_CHUNK_SIZE_TARGET = 150;
  private static final String commaSpace;
  private static final String periodSpace = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_dot) + VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_space);
  private static final String questionMarkSpace = "?" + VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_space);

  static
  {
    commaSpace = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_comma) + VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_space);
  }

  private static int getBreakPoint(String paramString)
  {
    int m;
    if (paramString.length() <= 250)
      m = paramString.length();
    while (true)
    {
      return m;
      int i = paramString.indexOf(periodSpace, 150);
      if ((i >= 0) && (i <= 250))
      {
        m = i + 1;
        continue;
      }
      int j = paramString.indexOf(questionMarkSpace, 150);
      if ((j >= 0) && (j <= 250))
      {
        m = j + 1;
        continue;
      }
      int k = paramString.indexOf(commaSpace, 150);
      if ((k >= 0) && (k <= 250))
      {
        m = k + 1;
        continue;
      }
      m = 150;
    }
  }

  public static ArrayList<String> getTtsArray(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    int i;
    for (String str = paramString; str.length() > 0; str = str.substring(i + 1))
    {
      i = getBreakPoint(str);
      localArrayList.add(str.substring(0, i));
      if (str.length() <= 250)
        break;
    }
    return localArrayList;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.tss.TtsUtil
 * JD-Core Version:    0.6.0
 */