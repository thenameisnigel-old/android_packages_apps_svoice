package com.vlingo.core.internal.dialogmanager.vvs;

import com.vlingo.core.internal.settings.Settings;
import com.vlingo.sdk.recognition.VLActionEvaluator;

public class PhoenixActionEvaluator extends VLActionEvaluator
{
  protected boolean evaluateExpression(String paramString, String[] paramArrayOfString)
  {
    boolean bool = true;
    if ("setting-equals".equalsIgnoreCase(paramString))
      bool = settingEquals(paramArrayOfString[0], paramArrayOfString[bool]);
    while (true)
    {
      return bool;
      if ("set-setting".equalsIgnoreCase(paramString))
      {
        setSetting(paramArrayOfString[0], paramArrayOfString[bool]);
        continue;
      }
      if ("test-and-inc".equalsIgnoreCase(paramString))
      {
        bool = testAndIncrement(paramArrayOfString[0], paramArrayOfString[bool]);
        continue;
      }
      bool = super.evaluateExpression(paramString, paramArrayOfString);
    }
  }

  public void setSetting(String paramString1, String paramString2)
  {
    Settings.setString(paramString1, paramString2);
  }

  protected boolean settingEquals(String paramString1, String paramString2)
  {
    boolean bool = false;
    if (!Settings.hasSetting(paramString1));
    while (true)
    {
      return bool;
      if (isBoolean(paramString2))
      {
        if (Settings.getBoolean(paramString1, false) != "true".equalsIgnoreCase(paramString2))
          continue;
        bool = true;
        continue;
      }
      bool = paramString2.equalsIgnoreCase(Settings.getString(paramString1, ""));
    }
  }

  public boolean testAndIncrement(String paramString1, String paramString2)
  {
    int i = 0;
    int j = Settings.getInt(paramString1, 0);
    int k = 0;
    try
    {
      int m = Integer.parseInt(paramString2);
      k = m;
      label22: if (j < k)
      {
        Settings.setInt(paramString1, j + 1);
        i = 1;
      }
      return i;
    }
    catch (Exception localException)
    {
      break label22;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.PhoenixActionEvaluator
 * JD-Core Version:    0.6.0
 */