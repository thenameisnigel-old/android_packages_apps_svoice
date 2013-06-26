package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.dialogmanager.vvs.VLConfigImporter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.vlservice.VLConfigParser;
import com.vlingo.sdk.recognition.VLAction;

public class SetConfigHandler extends VVSActionHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    if (!Settings.getBoolean("dynamic_config_disabled", false))
    {
      String str = paramVLAction.getParamValue("Config");
      if (!StringUtils.isNullOrWhiteSpace(str))
        VLConfigImporter.importSettings(str, new VLConfigParser());
    }
    return false;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.SetConfigHandler
 * JD-Core Version:    0.6.0
 */