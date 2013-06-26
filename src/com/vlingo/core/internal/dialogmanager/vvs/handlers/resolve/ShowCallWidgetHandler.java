package com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve;

import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.events.ActionCompletedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionFailedEvent;
import com.vlingo.core.internal.dialogmanager.types.RecipientType;
import com.vlingo.core.internal.dialogmanager.util.DialogDataUtil;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.core.internal.recognition.acceptedtext.AcceptedText.TextType;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.DialUtil;
import com.vlingo.core.internal.util.PhoneUtil;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.json.JSONException;

public class ShowCallWidgetHandler extends VVSActionHandler
{
  private List<ContactMatch> allMatches;
  protected RecipientType forwardRecipient;
  protected LinkedList<SMSMMSAlert> messages;

  private void placeCall(RecipientType paramRecipientType, boolean paramBoolean)
    throws JSONException
  {
    turnOnSpeakerIfRequired();
    DialUtil.dial(getListener().getActivityContext(), paramRecipientType.getAddress(), this, getListener(), paramBoolean);
    if (paramRecipientType.getDisplayName() != null)
      sendAcceptedText(paramRecipientType.getDisplayName(), AcceptedText.TextType.DIAL);
  }

  public void actionFail(String paramString)
  {
    ActionFailedEvent localActionFailedEvent = new ActionFailedEvent(paramString);
    getListener().sendEvent(localActionFailedEvent, this.turn);
    getListener().asyncHandlerDone();
  }

  public void actionSuccess()
  {
    ActionCompletedEvent localActionCompletedEvent = new ActionCompletedEvent();
    getListener().sendEvent(localActionCompletedEvent, this.turn);
    getListener().asyncHandlerDone();
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("voicedial");
    this.allMatches = ((List)getListener().getState(DialogDataType.CONTACT_MATCHES));
    String str1 = VLActionUtil.getParamString(paramVLAction, "recipient", false);
    Set localSet1 = null;
    Object localObject = null;
    RecipientType localRecipientType = null;
    if ((StringUtils.isNullOrWhiteSpace(str1)) || (str1.equals("{}")) || ((this.allMatches != null) && (!this.allMatches.isEmpty())))
      try
      {
        localSet1 = DialogDataUtil.getMessageRecipientContacts(this.allMatches, str1);
        Set localSet3 = DialogDataUtil.getMessageRawPhoneNumbers(str1);
        localObject = localSet3;
        if (localSet1 != null)
        {
          Iterator localIterator2 = localSet1.iterator();
          while (localIterator2.hasNext())
          {
            ContactData localContactData = (ContactData)localIterator2.next();
            if ((localContactData == null) || (StringUtils.isNullOrWhiteSpace(localContactData.address)))
              continue;
            localRecipientType = new RecipientType(localContactData.contact.primaryDisplayName, localContactData.address);
          }
        }
      }
      catch (JSONException localJSONException3)
      {
      }
    while (true)
    {
      while (true)
      {
        return false;
        try
        {
          Set localSet2 = DialogDataUtil.getMessageRawPhoneNumbers(str1);
          localObject = localSet2;
        }
        catch (JSONException localJSONException2)
        {
        }
      }
      continue;
      if (localObject != null)
      {
        Iterator localIterator1 = localObject.iterator();
        while (localIterator1.hasNext())
          localRecipientType = new RecipientType("", (String)localIterator1.next());
      }
      if (this.forwardRecipient != null)
        localRecipientType = this.forwardRecipient;
      String str2 = VLActionUtil.getParamString(paramVLAction, "mode", false);
      boolean bool1;
      if ((!StringUtils.isNullOrWhiteSpace(str2)) && (str2.equalsIgnoreCase("videophone")))
      {
        bool1 = true;
        label302: boolean bool2 = VLActionUtil.getParamBool(paramVLAction, "confirm", true, false);
        boolean bool3 = VLActionUtil.getParamBool(paramVLAction, "execute", true, false);
        if ((bool2) || (!bool3))
          break label388;
        if (ClientSuppliedValues.isDrivingModeEnabled())
          paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.ShowCallWidget, null, localRecipientType, null);
        if (!VLActionUtil.getParamBool(paramVLAction, "execute", false, false));
      }
      try
      {
        placeCall(localRecipientType, bool1);
        getListener().finishDialog();
        continue;
        bool1 = false;
        break label302;
      }
      catch (JSONException localJSONException1)
      {
        while (true)
          label388: localJSONException1.printStackTrace();
      }
    }
  }

  protected void turnOnSpeakerIfRequired()
  {
    PhoneUtil.turnOnSpeakerphoneIfRequired(ApplicationAdapter.getInstance().getApplicationContext());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.ShowCallWidgetHandler
 * JD-Core Version:    0.6.0
 */