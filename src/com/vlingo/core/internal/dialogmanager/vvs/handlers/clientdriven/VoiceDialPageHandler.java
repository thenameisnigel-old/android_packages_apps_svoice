package com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven;

import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.Controller;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.controllers.VoiceDialController;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.ContactUtil;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.LinkedList;

public class VoiceDialPageHandler extends VVSActionHandler
{
  String address = null;
  VoiceDialController controller = null;
  LinkedList<SMSMMSAlert> messages = null;

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    String str1 = VLActionUtil.getParamString(paramVLAction, "Action", false);
    UserLoggingEngine.getInstance().landingPageViewed("voicedial");
    Controller localController = (Controller)paramVVSActionHandlerListener.getState(DialogDataType.ACTIVE_CONTROLLER);
    if ((localController == null) || (!(localController instanceof VoiceDialController)))
      localController = getController(VoiceDialController.class);
    this.controller = ((VoiceDialController)localController);
    this.messages = ((LinkedList)paramVVSActionHandlerListener.getState(DialogDataType.MESSAGE_MATCHES));
    boolean bool;
    if ((this.messages != null) && (!this.messages.isEmpty()))
    {
      this.address = ((SMSMMSAlert)this.messages.get(0)).getAddress();
      String str5 = ((SMSMMSAlert)this.messages.get(0)).getSenderName();
      if (!StringUtils.isNullOrWhiteSpace(this.address))
      {
        this.controller.redial(this.address, str5);
        bool = false;
      }
    }
    while (true)
    {
      return bool;
      bool = false;
      if ((str1 != null) && (str1.equalsIgnoreCase("redial")))
      {
        this.address = ContactUtil.getLastOutgoingCall(paramVVSActionHandlerListener.getActivityContext());
        String str2 = ContactUtil.getContactDisplayNameByAddress(paramVVSActionHandlerListener.getActivityContext(), this.address);
        String str3 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_no_call_log);
        if (this.address != null)
        {
          if ((ClientSuppliedValues.isEyesFree()) && (ClientSuppliedValues.isMessageReadbackFlowEnabled()))
          {
            if (this.address.equals(str2));
            for (ResourceIdProvider.string localstring = ResourceIdProvider.string.core_car_redial_confirm_number; ; localstring = ResourceIdProvider.string.core_car_redial_confirm_contact)
            {
              Object[] arrayOfObject = new Object[1];
              arrayOfObject[0] = str2;
              String str4 = getString(localstring, arrayOfObject);
              unified().showSystemTurn(str4, DialogFieldID.buildFromString("vp_car_confirm"));
              this.controller.setListener(paramVVSActionHandlerListener);
              this.controller.confirmRedial(this.address, str2);
              bool = false;
              break;
            }
          }
          this.controller.redial(this.address, str2);
          bool = false;
          continue;
        }
        getListener().showVlingoTextAndTTS(str3, str3);
        continue;
      }
      bool = this.controller.executeAction(paramVLAction, paramVVSActionHandlerListener);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.VoiceDialPageHandler
 * JD-Core Version:    0.6.0
 */