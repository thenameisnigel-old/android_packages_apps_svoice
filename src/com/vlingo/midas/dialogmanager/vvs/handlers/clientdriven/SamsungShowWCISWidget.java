package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.sdk.recognition.VLAction;

public class SamsungShowWCISWidget extends VVSActionHandler
  implements WidgetActionListener
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    if (ClientSuppliedValues.isDrivingModeEnabled())
    {
      String str = paramVVSActionHandlerListener.getActivityContext().getResources().getString(2131362187);
      paramVVSActionHandlerListener.showVlingoTextAndTTS(str, str);
    }
    paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.ShowWCISWidget, null, null, this);
    return false;
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.SamsungShowWCISWidget
 * JD-Core Version:    0.6.0
 */