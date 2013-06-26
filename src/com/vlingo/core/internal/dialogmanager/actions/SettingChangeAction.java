package com.vlingo.core.internal.dialogmanager.actions;

import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.SettingChangeInterface;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.safereader.SafeReaderProxy;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.util.SystemServicesUtil;

public class SettingChangeAction extends DMAction
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

  private void fakeSystemPrompt(String paramString1, String paramString2)
  {
    if (StringUtils.isNullOrWhiteSpace(paramString2))
      paramString2 = paramString1;
    this.VVSlistener.showVlingoTextAndTTS(paramString1, paramString2);
  }

  public SettingChangeAction VVSlistener(VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    this.VVSlistener = paramVVSActionHandlerListener;
    return this;
  }

  public SettingChangeAction alreadySet(String paramString)
  {
    this.alreadySet = paramString;
    return this;
  }

  public SettingChangeAction confirmOff(String paramString)
  {
    this.confirmOff = paramString;
    return this;
  }

  public SettingChangeAction confirmOffTTS(String paramString)
  {
    this.confirmOffTTS = paramString;
    return this;
  }

  public SettingChangeAction confirmOn(String paramString)
  {
    this.confirmOn = paramString;
    return this;
  }

  public SettingChangeAction confirmOnTTS(String paramString)
  {
    this.confirmOnTTS = paramString;
    return this;
  }

  protected void execute()
  {
    boolean bool1 = true;
    SystemServicesUtil localSystemServicesUtil;
    String str5;
    if ((this.name != null) || (this.state != null))
    {
      localSystemServicesUtil = new SystemServicesUtil(getContext());
      if (this.name.equalsIgnoreCase("wifi"))
      {
        str5 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_wifi_setting_change_on_error);
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
      fakeSystemPrompt(str5, str5);
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
        fakeSystemPrompt(str5, str5);
        break;
      }
      if (this.state.equalsIgnoreCase("toggle"))
      {
        boolean bool4 = localSystemServicesUtil.isWifiEnabled();
        label219: String str6;
        if (!bool4)
        {
          if (!localSystemServicesUtil.setWifiEnabled(bool1))
            break label283;
          if (bool4)
            break label265;
          str6 = this.confirmOn;
          label238: if (bool4)
            break label274;
        }
        label265: label274: for (String str7 = this.confirmOnTTS; ; str7 = this.confirmOffTTS)
        {
          fakeSystemPrompt(str6, str7);
          break;
          bool1 = false;
          break label219;
          str6 = this.confirmOff;
          break label238;
        }
        label283: fakeSystemPrompt(str5, str5);
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
          label451: String str3;
          if (!bool3)
          {
            localSystemServicesUtil.setBluetoothEnabled(bool1);
            if (bool3)
              break label494;
            str3 = this.confirmOn;
            label467: if (bool3)
              break label503;
          }
          label494: label503: for (String str4 = this.confirmOnTTS; ; str4 = this.confirmOffTTS)
          {
            fakeSystemPrompt(str3, str4);
            break;
            bool1 = false;
            break label451;
            str3 = this.confirmOff;
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
          ClientSuppliedValues.getForegroundFocus(DialogFlow.getInstance());
          Settings.enableDrivingModeSetting();
          SafeReaderProxy.startSafeReading();
          fakeSystemPrompt(this.confirmOn, this.confirmOnTTS);
          break;
        }
        if (this.state.equalsIgnoreCase("off"))
        {
          ClientSuppliedValues.releaseForegroundFocus(DialogFlow.getInstance());
          Settings.disableDrivingModeSetting();
          SafeReaderProxy.stopSafeReading();
          fakeSystemPrompt(this.confirmOff, this.confirmOffTTS);
          break;
        }
        if (this.state.equalsIgnoreCase("toggle"))
        {
          boolean bool2 = Settings.isDrivingModeEnabled();
          Settings.toggleDrivingMode();
          label648: String str1;
          if (!bool2)
          {
            ClientSuppliedValues.getForegroundFocus(DialogFlow.getInstance());
            SafeReaderProxy.startSafeReading();
            if (bool2)
              break label691;
            str1 = this.confirmOn;
            label658: if (bool2)
              break label700;
          }
          label691: label700: for (String str2 = this.confirmOnTTS; ; str2 = this.confirmOffTTS)
          {
            fakeSystemPrompt(str1, str2);
            break;
            ClientSuppliedValues.releaseForegroundFocus(DialogFlow.getInstance());
            SafeReaderProxy.stopSafeReading();
            break label648;
            str1 = this.confirmOff;
            break label658;
          }
        }
        getListener().actionFail("unsupported state");
        break;
      }
      getListener().actionFail("unsupported device");
      break;
      getListener().actionFail("unsupported device");
    }
  }

  public SettingChangeAction name(String paramString)
  {
    this.name = paramString;
    return this;
  }

  public SettingChangeAction state(String paramString)
  {
    this.state = paramString;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.SettingChangeAction
 * JD-Core Version:    0.6.0
 */