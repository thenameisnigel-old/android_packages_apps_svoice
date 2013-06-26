package com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven;

import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.sdk.recognition.VLAction;

public class ShowWCISWidget extends VVSActionHandler
  implements WidgetActionListener
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.ShowWCISWidget, null, null, this);
    return false;
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.ShowWCISWidget
 * JD-Core Version:    0.6.0
 */