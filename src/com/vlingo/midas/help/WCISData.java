package com.vlingo.midas.help;

import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.settings.DynamicFeatureConfig;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.midas.ClientConfiguration;
import com.vlingo.midas.MidasValues;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.ui.PackageInfoProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class WCISData
{
  public static final String CAPTION = "EXTRA_CAPTION";
  public static final String EXTRA_LIST_HEIGHT = "EXTRA_LIST_HEIGHT";
  public static final String INFO = "EXTRA_INFO";
  public static final String INTENT = "EXTRA_INTENT";
  private static final String IN_CAR = "InCar";
  public static final String LIST_EXAMPLE = "EXTRA_LIST_EXAMPLE";
  public static final String LIST_ICON = "EXTRA_LIST_ICON";
  public static final String LIST_ICON_DRAWABLE = "EXTRA_LIST_ICON_DRAWABLE";
  public static final String LIST_MOVE_ACTION = "LIST_MOVE_ACTION";
  public static final String LIST_TITLE = "EXTRA_LIST_TITLE";
  public static final String SCREEN = "EXTRA_SCREEN";
  public static final String SUBHEADING = "EXTRA_SUBHEADING";
  private boolean isSetKorEngAtChineseBuild = false;
  private ArrayList<HashMap<String, Object>> items = new ArrayList();
  PackageInfoProvider mPackageInfo;
  MidasValues mValue;

  private boolean isDfcEnabled(DynamicFeatureConfig paramDynamicFeatureConfig)
  {
    int i = 1;
    if (paramDynamicFeatureConfig.isEnabled());
    while (true)
    {
      return i;
      if (paramDynamicFeatureConfig.getReason().equalsIgnoreCase("InCar"))
      {
        if (!ClientSuppliedValues.isDrivingModeEnabled())
          continue;
        i = 0;
        continue;
      }
      i = 0;
    }
  }

  protected void addInfoItem(String paramString1, String paramString2, Class<?> paramClass)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("EXTRA_INFO", paramString1);
    localHashMap.put("EXTRA_CAPTION", paramString2);
    localHashMap.put("EXTRA_SCREEN", paramClass);
    this.items.add(localHashMap);
  }

  protected void addIntentItem(String paramString1, String paramString2, Intent paramIntent)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("EXTRA_INFO", paramString1);
    localHashMap.put("EXTRA_CAPTION", paramString2);
    localHashMap.put("EXTRA_INTENT", paramIntent);
    this.items.add(localHashMap);
  }

  protected void addItem(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    addItem(paramContext, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, 0);
  }

  protected void addItem(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("EXTRA_LIST_TITLE", paramContext.getString(paramInt1));
    localHashMap.put("EXTRA_LIST_EXAMPLE", paramContext.getString(paramInt2));
    localHashMap.put("EXTRA_LIST_ICON", Integer.valueOf(paramInt3));
    localHashMap.put("EXTRA_TITLE_BAR", Integer.valueOf(paramInt1));
    localHashMap.put("EXTRA_SUBTITLE", Integer.valueOf(paramInt4));
    localHashMap.put("EXTRA_SUBTITLE_ICON", Integer.valueOf(paramInt5));
    localHashMap.put("EXTRA_EXAMPLE_LIST", Integer.valueOf(paramInt6));
    localHashMap.put("EXTRA_DID_YOU_KNOW", Integer.valueOf(paramInt7));
    getItems().add(localHashMap);
  }

  public void addItems(Context paramContext)
  {
    this.mPackageInfo = new PackageInfoProvider(paramContext);
    this.mValue = new MidasValues(paramContext);
    int i;
    label83: label355: int j;
    if ((ClientConfiguration.isChinesePhone()) && (!MidasSettings.getCurrentLocale().toString().equals("zh_CN")))
    {
      i = 1;
      if (isDfcEnabled(DynamicFeatureConfig.CALL))
      {
        if (!this.mValue.isVideoCallingSupported())
          break label853;
        addItem(paramContext, 2131362278, 2131362093, 2130837845, 2131362501, 2130837845, 2131165199);
      }
      if (isDfcEnabled(DynamicFeatureConfig.MESSAGING))
        addItem(paramContext, 2131362157, 2131362111, 2130837843, 2131362497, 2130837843, 2131165201, 2131362261);
      if ((isDfcEnabled(DynamicFeatureConfig.CONTACT)) && (!MidasSettings.isDrivingModeEnabled()))
        addItem(paramContext, 2131362112, 2131362113, 2130837842, 2131362504, 2130837842, 2131165220);
      if (!isDfcEnabled(DynamicFeatureConfig.MEMO))
        break label874;
      addItem(paramContext, 2131362096, 2131362097, 2130837846, 2131362493, 2130837846, 2131165218);
      label172: if (!isDfcEnabled(DynamicFeatureConfig.EVENT))
        break label905;
      addItem(paramContext, 2131362282, 2131362106, 2130837847, 2131362495, 2130837847, 2131165214);
      label199: if ((isDfcEnabled(DynamicFeatureConfig.TASK)) && (!MidasSettings.isDrivingModeEnabled()))
        addItem(paramContext, 2131362114, 2131362115, 2130837847, 2131362149, 2130837847, 2131165207);
      if (isDfcEnabled(DynamicFeatureConfig.MUSIC))
        addItem(paramContext, 2131362098, 2131362099, 2130837844, 2131362124, 2130837844, 2131165205, 2131362212);
      if ((i == 0) && (isDfcEnabled(DynamicFeatureConfig.SOCIAL)) && (!MidasSettings.isDrivingModeEnabled()))
      {
        if (!ClientConfiguration.isChineseBuild())
          break label946;
        addItem(paramContext, 2131362108, 2131362110, 2130837849, 2131362499, 2130837849, 2131165209);
      }
      label304: if (i == 0)
        break label969;
      if ((isDfcEnabled(DynamicFeatureConfig.SEARCH)) && (!MidasSettings.isDrivingModeEnabled()) && (MidasSettings.getCurrentLocale().toString().equals("en_US")))
        addItem(paramContext, 2131362283, 2131362107, 2130837807, 2131362496, 2130837807, 2131165202);
      if ((isDfcEnabled(DynamicFeatureConfig.OPENAPP)) && (!MidasSettings.isDrivingModeEnabled()))
        addItem(paramContext, 2131362102, 2131362103, 2130837840, 2131362128, 2130837840, 2131165204);
      if ((isDfcEnabled(DynamicFeatureConfig.VOICERECORD)) && (!MidasSettings.isDrivingModeEnabled()))
        addItem(paramContext, 2131362104, 2131362105, 2130837850, 2131362133, 2130837850, 2131165212);
      if (isDfcEnabled(DynamicFeatureConfig.READBACK))
      {
        if (!PackageInfoProvider.hasDialing())
          break label1061;
        j = 2131362259;
        label449: addItem(paramContext, 2131362094, 2131362095, 2130837787, 2131362118, 2130837787, 2131165210, j);
      }
      if ((isDfcEnabled(DynamicFeatureConfig.ALARM)) && (!MidasSettings.isDrivingModeEnabled()))
        addItem(paramContext, 2131362159, 2131362160, 2130837841, 2131362116, 2130837841, 2131165216);
      if ((isDfcEnabled(DynamicFeatureConfig.TIMER)) && (!MidasSettings.isDrivingModeEnabled()))
        addItem(paramContext, 2131362161, 2131362162, 2130837841, 2131362146, 2130837841, 2131165217);
      if ((isDfcEnabled(DynamicFeatureConfig.SETTINGS)) && (!MidasSettings.isDrivingModeEnabled()))
        addItem(paramContext, 2131362167, 2131362168, 2130837848, 2131362155, 2130837848, 2131165223);
      if (i == 0)
      {
        if ((isDfcEnabled(DynamicFeatureConfig.NAVIGATION)) && (isDfcEnabled(DynamicFeatureConfig.MAPS)))
          addItem(paramContext, 2131362100, 2131362101, 2130837803, 2131362494, 2130837803, 2131165213, 2131362262);
        if (MidasSettings.isDrivingModeEnabled())
          addItem(paramContext, 2131362196, 2131362197, 2130837804, 2131362198, 2130837804, 2131165211);
        if (isDfcEnabled(DynamicFeatureConfig.WEATHER))
        {
          if (!ClientConfiguration.isChineseBuild())
            break label1068;
          addItem(paramContext, 2131362163, 2131362164, 2130837800, 2131362151, 2130837800, 2131165221);
        }
        label708: if ((isDfcEnabled(DynamicFeatureConfig.ANSWER)) && (ClientConfiguration.isEnabled(5)) && (!MidasSettings.isDrivingModeEnabled()))
          addItem(paramContext, 2131362165, 2131362166, 2130837806, 2131362153, 2130837806, 2131165222);
        if ((ClientConfiguration.isEnabled(15)) && (!ClientConfiguration.isChineseBuild()) && (!MidasSettings.isDrivingModeEnabled()))
          addItem(paramContext, 2131362279, 2131362202, 2130837794, 2131362207, 2130837794, 2131165230, 2131362210);
        if ((isDfcEnabled(DynamicFeatureConfig.LOCALSEARCH)) && (!MidasSettings.isDrivingModeEnabled()))
        {
          if (!ClientConfiguration.isEnabled(15))
            break label1094;
          addItem(paramContext, 2131362209, 2131362205, 2130837789, 2131362204, 2130837789, 2131165229);
        }
      }
    }
    while (true)
    {
      return;
      i = 0;
      break;
      label853: addItem(paramContext, 2131362278, 2131362093, 2130837845, 2131362501, 2130837845, 2131165200);
      break label83;
      label874: if (!isDfcEnabled(DynamicFeatureConfig.MEMO_ADD))
        break label172;
      addItem(paramContext, 2131362096, 2131362097, 2130837846, 2131362493, 2130837846, 2131165219);
      break label172;
      label905: if ((!isDfcEnabled(DynamicFeatureConfig.EVENT_ADD)) || (!isDfcEnabled(DynamicFeatureConfig.EVENT_LOOKUP)))
        break label199;
      addItem(paramContext, 2131362282, 2131362106, 2130837847, 2131362495, 2130837847, 2131165215);
      break label199;
      label946: addItem(paramContext, 2131362108, 2131362109, 2130837849, 2131362498, 2130837849, 2131165208);
      break label304;
      label969: if ((!isDfcEnabled(DynamicFeatureConfig.SEARCH)) || (MidasSettings.isDrivingModeEnabled()))
        break label355;
      if (ClientConfiguration.isChineseBuild())
      {
        addItem(paramContext, 2131362283, 2131362107, 2130837807, 2131362496, 2130837807, 2131165203);
        break label355;
      }
      if (ClientConfiguration.isEnabled(15))
      {
        addItem(paramContext, 2131362283, 2131362107, 2130837807, 2131362496, 2130837807, 2131165228);
        break label355;
      }
      addItem(paramContext, 2131362283, 2131362107, 2130837807, 2131362496, 2130837807, 2131165202);
      break label355;
      label1061: j = 2131362260;
      break label449;
      label1068: addItem(paramContext, 2131362163, 2131362164, 2130837808, 2131362151, 2130837808, 2131165221);
      break label708;
      label1094: if (!ClientConfiguration.isEnabled(10))
        continue;
      if (ClientConfiguration.isChineseBuild())
      {
        addItem(paramContext, 2131362180, 2131362181, 2130837790, 2131362182, 2130837790, 2131165224, 2131362570);
        continue;
      }
      addItem(paramContext, 2131362180, 2131362181, 2130837789, 2131362182, 2130837789, 2131165224, 2131362570);
    }
  }

  protected void addSubHeading(String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("EXTRA_SUBHEADING", paramString);
    this.items.add(localHashMap);
  }

  public ArrayList<HashMap<String, Object>> getItems()
  {
    return this.items;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.help.WCISData
 * JD-Core Version:    0.6.0
 */