package com.vlingo.midas.dialogmanager.actions;

import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.SettingChangeInterface;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.util.SystemServicesUtil;
import com.vlingo.midas.VlingoApplication;

public class SamsungSettingChangeAction extends DMAction
  implements SettingChangeInterface
{
  private VVSActionHandlerListener VVSlistener;
  private String alreadySet;
  private String confirmOff;
  private String confirmOffTTS;
  private String confirmOn;
  private String confirmOnTTS;
  private String name;
  private String state;

  private void fakeSystemPrompt(int paramInt1, int paramInt2)
  {
    fakeSystemPrompt(VlingoApplication.getInstance().getString(paramInt1), VlingoApplication.getInstance().getString(paramInt2));
  }

  private void fakeSystemPrompt(String paramString1, String paramString2)
  {
    if (StringUtils.isNullOrWhiteSpace(paramString2))
      paramString2 = paramString1;
    this.VVSlistener.showVlingoTextAndTTS(paramString1, paramString2);
  }

  public SamsungSettingChangeAction VVSlistener(VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    this.VVSlistener = paramVVSActionHandlerListener;
    return this;
  }

  public SamsungSettingChangeAction alreadySet(String paramString)
  {
    this.alreadySet = paramString;
    return this;
  }

  public SamsungSettingChangeAction confirmOff(String paramString)
  {
    this.confirmOff = paramString;
    return this;
  }

  public SamsungSettingChangeAction confirmOffTTS(String paramString)
  {
    this.confirmOffTTS = paramString;
    return this;
  }

  public SamsungSettingChangeAction confirmOn(String paramString)
  {
    this.confirmOn = paramString;
    return this;
  }

  public SamsungSettingChangeAction confirmOnTTS(String paramString)
  {
    this.confirmOnTTS = paramString;
    return this;
  }

  protected void execute()
  {
    boolean bool1 = true;
    SystemServicesUtil localSystemServicesUtil;
    String str3;
    if ((this.name != null) || (this.state != null))
    {
      localSystemServicesUtil = new SystemServicesUtil(getContext());
      if (this.name.equalsIgnoreCase("wifi"))
      {
        str3 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_wifi_setting_change_on_error);
        if (this.state.equalsIgnoreCase("on"))
          if (localSystemServicesUtil.isWifiEnabled())
          {
            fakeSystemPrompt(this.alreadySet, this.alreadySet);
            getListener().actionSuccess();
          }
      }
    }
    while (true)
    {
      return;
      if (localSystemServicesUtil.setWifiEnabled(bool1))
      {
        fakeSystemPrompt(this.confirmOn, this.confirmOnTTS);
        break;
      }
      fakeSystemPrompt(str3, str3);
      break;
      if (this.state.equalsIgnoreCase("off"))
      {
        if (!localSystemServicesUtil.isWifiEnabled())
        {
          fakeSystemPrompt(this.alreadySet, this.alreadySet);
          break;
        }
        if (localSystemServicesUtil.setWifiEnabled(false))
        {
          fakeSystemPrompt(this.confirmOff, this.confirmOffTTS);
          break;
        }
        fakeSystemPrompt(str3, str3);
        break;
      }
      if (this.state.equalsIgnoreCase("toggle"))
      {
        boolean bool4 = localSystemServicesUtil.isWifiEnabled();
        label219: String str4;
        if (!bool4)
        {
          if (!localSystemServicesUtil.setWifiEnabled(bool1))
            break label283;
          if (bool4)
            break label265;
          str4 = this.confirmOn;
          label238: if (bool4)
            break label274;
        }
        label265: label274: for (String str5 = this.confirmOnTTS; ; str5 = this.confirmOffTTS)
        {
          fakeSystemPrompt(str4, str5);
          break;
          bool1 = false;
          break label219;
          str4 = this.confirmOff;
          break label238;
        }
        label283: fakeSystemPrompt(str3, str3);
        break;
      }
      getListener().actionFail("unsupported state");
      break;
      if (this.name.equalsIgnoreCase("bt"))
      {
        if (this.state.equalsIgnoreCase("on"))
        {
          if (localSystemServicesUtil.isBluetoothEnabled())
          {
            fakeSystemPrompt(this.alreadySet, this.alreadySet);
            break;
          }
          localSystemServicesUtil.setBluetoothEnabled(bool1);
          fakeSystemPrompt(this.confirmOn, this.confirmOnTTS);
          break;
        }
        if (this.state.equalsIgnoreCase("off"))
        {
          if (!localSystemServicesUtil.isBluetoothEnabled())
          {
            fakeSystemPrompt(this.alreadySet, this.alreadySet);
            break;
          }
          localSystemServicesUtil.setBluetoothEnabled(false);
          fakeSystemPrompt(this.confirmOff, this.confirmOffTTS);
          break;
        }
        if (this.state.equalsIgnoreCase("toggle"))
        {
          boolean bool3 = localSystemServicesUtil.isBluetoothEnabled();
          label451: String str1;
          if (!bool3)
          {
            localSystemServicesUtil.setBluetoothEnabled(bool1);
            if (bool3)
              break label494;
            str1 = this.confirmOn;
            label467: if (bool3)
              break label503;
          }
          label494: label503: for (String str2 = this.confirmOnTTS; ; str2 = this.confirmOffTTS)
          {
            fakeSystemPrompt(str1, str2);
            break;
            bool1 = false;
            break label451;
            str1 = this.confirmOff;
            break label467;
          }
        }
        getListener().actionFail("unsupported state");
        break;
      }
      if (this.name.equalsIgnoreCase("safereader"))
      {
        if (this.state.equalsIgnoreCase("on"))
        {
          if (Settings.isDrivingModeEnabled())
          {
            fakeSystemPrompt(2131362676, 2131362677);
            break;
          }
          Settings.enableDrivingModeSetting();
          fakeSystemPrompt(2131362672, 2131362673);
          break;
        }
        if (this.state.equalsIgnoreCase("off"))
        {
          if (!Settings.isDrivingModeEnabled())
          {
            fakeSystemPrompt(2131362678, 2131362679);
            break;
          }
          Settings.disableDrivingModeSetting();
          fakeSystemPrompt(2131362674, 2131362675);
          break;
        }
        if (this.state.equalsIgnoreCase("toggle"))
        {
          boolean bool2 = Settings.isDrivingModeEnabled();
          Settings.toggleDrivingMode();
          if (bool2)
          {
            fakeSystemPrompt(2131362674, 2131362675);
            break;
          }
          fakeSystemPrompt(2131362672, 2131362673);
          break;
        }
        getListener().actionFail("unsupported state");
        break;
      }
      getListener().actionFail("unsupported device");
      break;
      getListener().actionFail("unsupported device");
    }
  }

  public SamsungSettingChangeAction name(String paramString)
  {
    this.name = paramString;
    return this;
  }

  public SamsungSettingChangeAction state(String paramString)
  {
    this.state = paramString;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.actions.SamsungSettingChangeAction
 * JD-Core Version:    0.6.0
 */