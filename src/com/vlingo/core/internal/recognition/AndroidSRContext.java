package com.vlingo.core.internal.recognition;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.sdk.internal.recognizer.SRContext;

public class AndroidSRContext extends SRContext
{
  private String m_Custom6Context = null;

  public AndroidSRContext()
  {
  }

  public AndroidSRContext(String paramString)
  {
    super(paramString);
    this.customFlag = false;
  }

  public String getCustomParam(String paramString)
  {
    String str;
    if ("Custom2".equals(paramString))
    {
      WifiInfo localWifiInfo = ((WifiManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("wifi")).getConnectionInfo();
      if ((localWifiInfo != null) && (localWifiInfo.getNetworkId() >= 0))
        str = "Wifi";
    }
    while (true)
    {
      return str;
      str = "Carrier";
      continue;
      if (("Custom4".equals(paramString)) && (this.customFlag))
      {
        str = "RecoStartedByWakeUpWord";
        continue;
      }
      if ("Custom5".equals(paramString))
      {
        if (Settings.getBoolean("audiofilelog_enabled", false))
        {
          str = "AudioFileLoggingEnabled";
          continue;
        }
      }
      else if ((this.m_Custom6Context != null) && ("Custom6".equals(paramString)))
      {
        str = this.m_Custom6Context;
        continue;
      }
      str = "";
    }
  }

  public String getFieldType()
  {
    if (Settings.getBoolean("profanity_filter", true));
    for (String str = "<xml><taboofilter>" + "on"; ; str = "<xml><taboofilter>" + "off")
      return str + "</taboofilter></xml>";
  }

  public void setCustom6Context(String paramString)
  {
    this.m_Custom6Context = paramString;
  }

  public void setRecoStartedByPhraseSpotter(boolean paramBoolean)
  {
    this.customFlag = paramBoolean;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.AndroidSRContext
 * JD-Core Version:    0.6.0
 */