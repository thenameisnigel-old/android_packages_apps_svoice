package com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve;

import android.content.Intent;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.events.ChoiceSelectedEvent;
import com.vlingo.core.internal.dialogmanager.util.DialogDataUtil.ChoiceListUtil;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.sdk.recognition.VLAction;

public class ShowContactChoicesHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private DialogDataUtil.ChoiceListUtil<ContactMatch> listUtil = new DialogDataUtil.ChoiceListUtil();

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    VLActionUtil.getParamString(paramVLAction, "choices", true);
    UserLoggingEngine.getInstance().landingPageViewed("contact");
    this.listUtil.showChoiceListWidget(paramVVSActionHandlerListener, paramVLAction, DialogDataType.CONTACT_MATCHES, WidgetUtil.WidgetKey.ShowContactChoices, this, null);
    return false;
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Choice"))
    {
      ChoiceSelectedEvent localChoiceSelectedEvent = new ChoiceSelectedEvent(this.listUtil.getIdOfSelection(paramIntent));
      getListener().sendEvent(localChoiceSelectedEvent, this.turn);
    }
    while (true)
    {
      return;
      throwUnknownActionException(paramIntent.getAction());
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.ShowContactChoicesHandler
 * JD-Core Version:    0.6.0
 */