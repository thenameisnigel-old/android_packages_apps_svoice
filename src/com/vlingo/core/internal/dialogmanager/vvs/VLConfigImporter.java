package com.vlingo.core.internal.dialogmanager.vvs;

import android.content.SharedPreferences.Editor;
import android.util.Pair;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.vlservice.VLConfigParser;
import java.util.Enumeration;
import java.util.Hashtable;

public class VLConfigImporter
{
  static Hashtable<Object, Object> configNameMappingTable = new Hashtable();

  public static void importSettings(String paramString)
  {
    importSettings(paramString, new VLConfigParser());
  }

  public static void importSettings(String paramString, VLConfigParser paramVLConfigParser)
  {
    try
    {
      paramVLConfigParser.parseXML(paramString);
      Hashtable localHashtable = paramVLConfigParser.getSettings();
      Enumeration localEnumeration = localHashtable.keys();
      SharedPreferences.Editor localEditor = Settings.startBatchEdit();
      try
      {
        if (localEnumeration.hasMoreElements())
        {
          String str = (String)localEnumeration.nextElement();
          if (configNameMappingTable.containsKey(str))
            str = (String)configNameMappingTable.get(str);
          Pair localPair = (Pair)localHashtable.get(str);
          updateSetting(str, (String)localPair.first, (String)localPair.second, localEditor);
        }
      }
      finally
      {
        Settings.commitBatchEdit(localEditor);
      }
      Settings.setLong("appstate.config_last_update", System.currentTimeMillis());
      Settings.setLong("appstate.config_update_count", 1L + Settings.getLong("appstate.config_update_count", 0L));
    }
    catch (Exception localException)
    {
    }
  }

  protected static void updateSetting(String paramString1, String paramString2, String paramString3, SharedPreferences.Editor paramEditor)
  {
    if ("string".equals(paramString3))
      Settings.setString(paramEditor, paramString1, paramString2);
    while (true)
    {
      return;
      if ("boolean".equals(paramString3))
      {
        Settings.setBoolean(paramEditor, paramString1, "true".equalsIgnoreCase(paramString2));
        continue;
      }
      if ("long".equals(paramString3))
      {
        try
        {
          Settings.setLong(paramEditor, paramString1, Long.parseLong(paramString2));
        }
        catch (NumberFormatException localNumberFormatException3)
        {
        }
        continue;
      }
      if ("integer".equals(paramString3))
      {
        try
        {
          Settings.setInt(paramEditor, paramString1, Integer.parseInt(paramString2));
        }
        catch (NumberFormatException localNumberFormatException2)
        {
        }
        continue;
      }
      if (!"float".equals(paramString3))
        continue;
      try
      {
        Settings.setFloat(paramEditor, paramString1, Float.parseFloat(paramString2));
      }
      catch (NumberFormatException localNumberFormatException1)
      {
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.VLConfigImporter
 * JD-Core Version:    0.6.0
 */