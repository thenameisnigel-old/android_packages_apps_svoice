package com.vlingo.midas.dialogmanager.controllers;

import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.dialogmanager.StateController;
import com.vlingo.core.internal.dialogmanager.StateController.Rule;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.Map;

public class UnreadMessagesController extends StateController
  implements WidgetActionListener
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.MultipleMessageDisplay, null, null, this);
    DialogFlow.getInstance().cancelDialog();
    return false;
  }

  public Map<String, String> getRuleMappings()
  {
    return null;
  }

  public Map<String, StateController.Rule> getRules()
  {
    return null;
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    String str = (String)getListener().getState(DialogDataType.CURRENT_ACTION);
    if (StringUtils.isNullOrWhiteSpace(str));
    while (true)
    {
      return;
      if (str != null)
      {
        if (!str.equals("ShowUnreadMessagesWidget"))
          break;
        getListener().showWidget(WidgetUtil.WidgetKey.MultipleMessageDisplay, null, null, this);
        getListener().finishTurn();
        reset();
        continue;
      }
    }
    throw new IllegalArgumentException();
  }

  public boolean handleLPAction(VLAction paramVLAction)
  {
    return false;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.controllers.UnreadMessagesController
 * JD-Core Version:    0.6.0
 */