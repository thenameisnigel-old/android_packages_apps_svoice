package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.sdk.recognition.VLAction;

public class ShowNavWidgetHandler extends VVSActionHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    if (ClientSuppliedValues.isDrivingModeEnabled())
      paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.ShowNavWidget, null, null, null);
    return false;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ShowNavWidgetHandler
 * JD-Core Version:    0.6.0
 */