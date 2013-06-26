package com.vlingo.core.internal.audio;

import android.content.Context;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.DeviceWorkarounds;
import com.vlingo.core.internal.util.FileUtils;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TTSCache
{
  private static final int CACHE_PRUNE_SIZE = 50;
  public static final String DOMAIN_LOCAL_TTS = "local_tts";
  public static final String DOMAIN_NETWORK_TTS = "network_tts";
  private static final String FILETYPE_DEFAULT = ".tts";
  private static final String FILETYPE_LOCAL = ".wav";
  private static final String FILETYPE_NETWORK = ".mp3";
  private static final String LOG_TAG = "TTSCache";
  private static final int MAX_CACHE_SIZE = 100;
  private static final String PERSISTANT_CACHE_SUFFIX = "_persist";
  private static final String TTS_NONCACHE_TEMP_FILENAME_ROOT = "tmp";
  private static final String TTS_SUBDIRECTORY_GENERAL = "tts";
  private static final String TTS_SUBDIRECTORY_LOCAL_CACHING = "local_tts";
  private static final String TTS_SUBDIRECTORY_LOCAL_NO_CACHING = "files";

  public static String cacheTTSRequest(TTSRequest paramTTSRequest, String paramString1, String paramString2, Context paramContext)
  {
    if (!Settings.getBoolean("car_iux_tts_cacheing_required", true));
    while (true)
    {
      return paramString1;
      String str1 = paramTTSRequest.getCacheKey();
      String str2 = getFullFolderPath(paramString2, paramTTSRequest, paramContext);
      String str3 = getFileName(str1, paramString2);
      File localFile1 = new File(str2);
      if (!localFile1.exists())
        localFile1.mkdir();
      File localFile2 = new File(paramString1);
      File localFile3 = new File(str2 + str3);
      if (localFile3.exists())
        localFile3.delete();
      if (localFile2.exists())
      {
        if (localFile2.renameTo(localFile3))
        {
          paramString1 = localFile3.getAbsolutePath();
          continue;
        }
        if (FileUtils.copyFile(localFile2, localFile3))
        {
          paramString1 = localFile3.getAbsolutePath();
          continue;
        }
      }
      if (paramTTSRequest.isPersistentCache)
        continue;
    }
  }

  private static void cleanUp(String paramString)
  {
    File localFile = new File(paramString);
    if (localFile.exists())
    {
      String[] arrayOfString = localFile.list();
      if (arrayOfString.length > 100)
      {
        LinkedList localLinkedList = new LinkedList();
        for (int i = 0; i < arrayOfString.length; i++)
          localLinkedList.add(new File(arrayOfString[i]));
        Collections.sort(localLinkedList, new TTSCache.1());
        int j = -50 + arrayOfString.length;
        for (int k = 0; k < j; k++)
          localLinkedList.remove(0);
      }
    }
  }

  public static String getCachedTTSPath(TTSRequest paramTTSRequest, String paramString, Context paramContext)
  {
    String str1 = null;
    if ((DeviceWorkarounds.doesDeviceSupportCachedPaths()) && (Settings.getBoolean("car_iux_tts_cacheing_required", true)))
    {
      String str2 = paramTTSRequest.getCacheKey();
      String str3 = getFullFolderPath(paramString, paramTTSRequest, paramContext);
      String str4 = getFileName(str2, paramString);
      File localFile = new File(str3 + str4);
      if (localFile.exists())
      {
        localFile.setLastModified(System.currentTimeMillis());
        str1 = localFile.getAbsolutePath();
      }
    }
    return str1;
  }

  private static String getFileName(String paramString1, String paramString2)
  {
    String str;
    if ("local_tts".equals(paramString2))
      str = paramString1 + ".wav";
    while (true)
    {
      return str;
      if ("network_tts".equals(paramString2))
      {
        str = paramString1 + ".mp3";
        continue;
      }
      str = paramString1 + ".tts";
    }
  }

  private static String getFullFolderPath(String paramString, TTSRequest paramTTSRequest, Context paramContext)
  {
    File localFile = paramContext.getCacheDir();
    String str1 = Settings.getString("language", "en-US").replace('-', '_');
    String str2 = "";
    if ((paramTTSRequest != null) && (paramTTSRequest.isPersistentCache))
      str2 = "_persist";
    return localFile.getAbsolutePath() + "/" + paramString + str2 + "_" + str1 + "/";
  }

  private static File getTempDirectory(String paramString, Context paramContext, boolean paramBoolean)
  {
    File localFile;
    if (("local_tts".equals(paramString)) && (paramBoolean))
    {
      localFile = paramContext.getDir("local_tts", 3);
      if ((!localFile.exists()) && (localFile.mkdir()))
        break label69;
    }
    while (true)
    {
      return localFile;
      if (paramBoolean)
      {
        localFile = new File(paramContext.getCacheDir(), "tts");
        break;
      }
      localFile = paramContext.getDir("files", 3);
      break;
      label69: if ((localFile.canWrite()) || (localFile.setWritable(true)))
        continue;
    }
  }

  public static File getTempFile(TTSRequest paramTTSRequest, String paramString, Context paramContext)
  {
    boolean bool = Settings.getBoolean("car_iux_tts_cacheing_required", true);
    if (!DeviceWorkarounds.doesDeviceSupportCachedPaths());
    String str;
    for (File localFile = new File("/sdcard/tmp-" + paramTTSRequest.getTextToSpeak().hashCode() + ".wav"); ; localFile = new File(getTempDirectory(paramString, paramContext, bool), str))
    {
      return localFile;
      if (!bool);
      str = getTempFilename(paramTTSRequest, paramString, bool);
    }
  }

  private static String getTempFilename(TTSRequest paramTTSRequest, String paramString, boolean paramBoolean)
  {
    String str1;
    String str2;
    if (paramBoolean)
    {
      str1 = paramTTSRequest.getCacheKey() + "_" + System.currentTimeMillis();
      if (!"local_tts".equals(paramString))
        break label72;
      str2 = str1 + ".wav";
    }
    while (true)
    {
      return str2;
      str1 = "tmp";
      break;
      label72: if ("network_tts".equals(paramString))
      {
        str2 = str1 + ".mp3";
        continue;
      }
      str2 = str1 + ".tts";
    }
  }

  public static int purgeCache(Context paramContext, boolean paramBoolean)
  {
    if (!Settings.getBoolean("car_iux_tts_cacheing_required", true));
    int i = 0 + purgeCache("network_tts", paramContext);
    if (paramBoolean)
      i += purgeCache("network_tts_persist", paramContext);
    int j = i + purgeCache("local_tts", paramContext);
    if (paramBoolean)
      j += purgeCache("local_tts_persist", paramContext);
    return j;
  }

  public static int purgeCache(String paramString, Context paramContext)
  {
    int i = 0;
    if (!Settings.getBoolean("car_iux_tts_cacheing_required", true));
    File localFile = new File(getFullFolderPath(paramString, null, paramContext));
    if (localFile.exists())
    {
      File[] arrayOfFile = localFile.listFiles();
      int j = arrayOfFile.length;
      for (int k = 0; k < j; k++)
      {
        if (!arrayOfFile[k].delete())
          continue;
        i++;
      }
      localFile.delete();
    }
    return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.TTSCache
 * JD-Core Version:    0.6.0
 */