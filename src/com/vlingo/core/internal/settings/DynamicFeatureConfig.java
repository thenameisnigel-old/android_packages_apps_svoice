package com.vlingo.core.internal.settings;

import com.vlingo.core.internal.util.StringUtils;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public enum DynamicFeatureConfig
{
  public static final String REASON_ACCOUNT = "Account";
  public static final String REASON_CARRIER = "Carrier";
  public static final String REASON_DEVICE = "Device";
  public static final String REASON_LANGUAGE = "Language";
  public static final String REASON_LOCATION = "Location";
  public static final String REASON_SMS_LOCK = "SmsLock";
  public static final String REASON_VERSION = "Version";
  private static final String STATUS_DISABLED = "DISABLED";
  public static final String STATUS_ENABLED = "ENABLED";
  private final String settingName;

  static
  {
    EVENT_ADD = new DynamicFeatureConfig("EVENT_ADD", 7, "event_add");
    LOCALSEARCH = new DynamicFeatureConfig("LOCALSEARCH", 8, "localsearch");
    MAPS = new DynamicFeatureConfig("MAPS", 9, "maps");
    MEMO = new DynamicFeatureConfig("MEMO", 10, "memo");
    MEMO_ADD = new DynamicFeatureConfig("MEMO_ADD", 11, "memo_add");
    MESSAGING = new DynamicFeatureConfig("MESSAGING", 12, "messaging");
    MUSIC = new DynamicFeatureConfig("MUSIC", 13, "music");
    NAVIGATION = new DynamicFeatureConfig("NAVIGATION", 14, "navigation");
    OPENAPP = new DynamicFeatureConfig("OPENAPP", 15, "openapp");
    RADIO = new DynamicFeatureConfig("RADIO", 16, "radio");
    READBACK = new DynamicFeatureConfig("READBACK", 17, "readback");
    SEARCH = new DynamicFeatureConfig("SEARCH", 18, "search");
    SETTINGS = new DynamicFeatureConfig("SETTINGS", 19, "settings");
    SOCIAL = new DynamicFeatureConfig("SOCIAL", 20, "social");
    STOCK = new DynamicFeatureConfig("STOCK", 21, "stock");
    TASK = new DynamicFeatureConfig("TASK", 22, "task");
    TIMER = new DynamicFeatureConfig("TIMER", 23, "timer");
    VOICERECORD = new DynamicFeatureConfig("VOICERECORD", 24, "voicerecord");
    WEATHER = new DynamicFeatureConfig("WEATHER", 25, "weather");
    DynamicFeatureConfig[] arrayOfDynamicFeatureConfig = new DynamicFeatureConfig[26];
    arrayOfDynamicFeatureConfig[0] = ALARM;
    arrayOfDynamicFeatureConfig[1] = ANSWER;
    arrayOfDynamicFeatureConfig[2] = CALL;
    arrayOfDynamicFeatureConfig[3] = CONTACT;
    arrayOfDynamicFeatureConfig[4] = EMAIL;
    arrayOfDynamicFeatureConfig[5] = EVENT;
    arrayOfDynamicFeatureConfig[6] = EVENT_LOOKUP;
    arrayOfDynamicFeatureConfig[7] = EVENT_ADD;
    arrayOfDynamicFeatureConfig[8] = LOCALSEARCH;
    arrayOfDynamicFeatureConfig[9] = MAPS;
    arrayOfDynamicFeatureConfig[10] = MEMO;
    arrayOfDynamicFeatureConfig[11] = MEMO_ADD;
    arrayOfDynamicFeatureConfig[12] = MESSAGING;
    arrayOfDynamicFeatureConfig[13] = MUSIC;
    arrayOfDynamicFeatureConfig[14] = NAVIGATION;
    arrayOfDynamicFeatureConfig[15] = OPENAPP;
    arrayOfDynamicFeatureConfig[16] = RADIO;
    arrayOfDynamicFeatureConfig[17] = READBACK;
    arrayOfDynamicFeatureConfig[18] = SEARCH;
    arrayOfDynamicFeatureConfig[19] = SETTINGS;
    arrayOfDynamicFeatureConfig[20] = SOCIAL;
    arrayOfDynamicFeatureConfig[21] = STOCK;
    arrayOfDynamicFeatureConfig[22] = TASK;
    arrayOfDynamicFeatureConfig[23] = TIMER;
    arrayOfDynamicFeatureConfig[24] = VOICERECORD;
    arrayOfDynamicFeatureConfig[25] = WEATHER;
    $VALUES = arrayOfDynamicFeatureConfig;
  }

  private DynamicFeatureConfig(String paramString)
  {
    this.settingName = paramString;
  }

  private void disable(String paramString)
  {
    if (StringUtils.isNullOrWhiteSpace(paramString));
    for (String str = ""; ; str = ":" + paramString)
    {
      Settings.setString(this.settingName, "DISABLED" + str);
      return;
    }
  }

  private String getSettingValue()
  {
    return Settings.getString(this.settingName, "ENABLED");
  }

  public static final void initDisabledFeatures(Map<DynamicFeatureConfig, String> paramMap)
  {
    if (paramMap != null)
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        ((DynamicFeatureConfig)localEntry.getKey()).disable((String)localEntry.getValue());
      }
    }
  }

  public String getReason()
  {
    String[] arrayOfString = getSettingValue().split(":");
    if (2 == arrayOfString.length);
    for (String str = arrayOfString[1]; ; str = "")
      return str;
  }

  public boolean isEnabled()
  {
    return getSettingValue().equals("ENABLED");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.settings.DynamicFeatureConfig
 * JD-Core Version:    0.6.0
 */