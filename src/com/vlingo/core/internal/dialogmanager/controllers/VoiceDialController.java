package com.vlingo.core.internal.dialogmanager.controllers;

import android.content.Context;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.contacts.ContactType;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.recognition.acceptedtext.AcceptedText.TextType;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.DialUtil;
import com.vlingo.core.internal.util.PhoneUtil;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.HashMap;
import java.util.List;

public class VoiceDialController extends VDSMSBaseController
{
  private static final HashMap<VDSMSBaseController.FieldID, DialogFieldID> field_ids = new HashMap();

  static
  {
    field_ids.put(VDSMSBaseController.FieldID.CONTACT, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_DIAL_CONTACT));
    field_ids.put(VDSMSBaseController.FieldID.TYPE, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_DIAL_TYPE_NUM));
  }

  private String determineType()
  {
    List localList = this.contactSelected.getPhoneData();
    if (!localList.isEmpty());
    for (String str = findKey(DialUtil.getPhoneTypeMap(getListener().getActivityContext().getResources(), localList, this.phoneType), this.phoneType); ; str = null)
      return str;
  }

  protected boolean checkHistory(ContactMatch paramContactMatch)
  {
    return false;
  }

  public void confirmRedial(String paramString1, String paramString2)
  {
    this.address = paramString1;
    this.contactSelectedName = paramString2;
    this.state = VDSMSBaseController.State.NEED_REDIAL_CONFIRMATION;
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    setListener(paramVVSActionHandlerListener);
    if (this.state == VDSMSBaseController.State.NEED_REDIAL_CONFIRMATION)
    {
      getListener().setFieldId(VlingoAndroidCore.getFieldId(FieldIds.DEFAULT));
      redial(this.address, this.contactSelectedName);
    }
    for (boolean bool = false; ; bool = super.executeAction(paramVLAction, paramVVSActionHandlerListener))
      return bool;
  }

  protected ContactType getCapability()
  {
    return ContactType.CALL;
  }

  protected String getContactPrompt()
  {
    return getString(ResourceIdProvider.string.core_who_would_you_like_to_call, new Object[0]);
  }

  protected HashMap<VDSMSBaseController.FieldID, DialogFieldID> getFieldIds()
  {
    return field_ids;
  }

  protected String getNoContactPrompt()
  {
    return getString(ResourceIdProvider.string.core_car_tts_NO_MATCH_DEMAND_CALL, new Object[0]);
  }

  protected String getType()
  {
    return "call";
  }

  protected void handleNeedMessage()
  {
    promptForConfirmation();
  }

  protected void promptForConfirmation()
  {
    this.state = VDSMSBaseController.State.NEED_CONFIRMATION;
    String str1;
    String str2;
    String str3;
    if (this.address != null)
    {
      str1 = null;
      str2 = null;
      str3 = null;
      if (this.contactSelected != null)
      {
        str2 = this.contactSelected.primaryDisplayName;
        str1 = determineType();
      }
      if (StringUtils.isNullOrWhiteSpace(str2))
        if (StringUtils.isNullOrWhiteSpace(this.query))
          this.state = VDSMSBaseController.State.POST_CONFIRMATION;
    }
    while (true)
    {
      return;
      str2 = this.query;
      str3 = this.query.replaceAll("[-()]", " ");
      new String();
      String str4;
      label137: String str5;
      if (!StringUtils.isNullOrWhiteSpace(str1))
        if (this.callMode == VDSMSBaseController.CallMode.VOICEVIDEOCALL)
        {
          ResourceIdProvider.string localstring6 = ResourceIdProvider.string.core_voicevideodial_call_name_type;
          Object[] arrayOfObject6 = new Object[2];
          arrayOfObject6[0] = str2;
          arrayOfObject6[1] = str1;
          str4 = getString(localstring6, arrayOfObject6);
          if (str3 != null)
            break label282;
          str5 = str4;
        }
      while (true)
      {
        getListener().tts(str5);
        showSystemTurn(str4, null, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_DIAL_AUTODIAL));
        sendAction();
        this.state = VDSMSBaseController.State.POST_CONFIRMATION;
        break;
        ResourceIdProvider.string localstring5 = ResourceIdProvider.string.core_voicedial_call_name_type;
        Object[] arrayOfObject5 = new Object[2];
        arrayOfObject5[0] = str2;
        arrayOfObject5[1] = str1;
        str4 = getString(localstring5, arrayOfObject5);
        break label137;
        if (this.callMode == VDSMSBaseController.CallMode.VOICEVIDEOCALL)
        {
          ResourceIdProvider.string localstring4 = ResourceIdProvider.string.core_voicevideodial_call_name;
          Object[] arrayOfObject4 = new Object[1];
          arrayOfObject4[0] = str2;
          str4 = getString(localstring4, arrayOfObject4);
          break label137;
        }
        ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_voicedial_call_name;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = str2;
        str4 = getString(localstring1, arrayOfObject1);
        break label137;
        label282: if (this.callMode == VDSMSBaseController.CallMode.VOICEVIDEOCALL)
        {
          ResourceIdProvider.string localstring3 = ResourceIdProvider.string.core_voicevideodial_call_name;
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = DialUtil.getTTSForAddress(str3);
          str5 = getString(localstring3, arrayOfObject3);
          continue;
        }
        ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_voicedial_call_name;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = DialUtil.getTTSForAddress(str3);
        str5 = getString(localstring2, arrayOfObject2);
      }
      this.state = VDSMSBaseController.State.NEED_CONTACT_REFINEMENT;
    }
  }

  public void redial(String paramString1, String paramString2)
  {
    ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_voicedial_call_name;
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = paramString2;
    String str1 = getString(localstring1, arrayOfObject1);
    if (!paramString1.equals(paramString2));
    ResourceIdProvider.string localstring2;
    Object[] arrayOfObject2;
    for (String str2 = str1; ; str2 = getString(localstring2, arrayOfObject2))
    {
      getListener().showVlingoTextAndTTS(str1, str2);
      PhoneUtil.turnOnSpeakerphoneIfRequired(ApplicationAdapter.getInstance().getApplicationContext());
      DialUtil.dial(getListener().getActivityContext(), paramString1, this, getListener());
      return;
      localstring2 = ResourceIdProvider.string.core_voicedial_call_name;
      arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = DialUtil.getTTSForAddress(paramString1);
    }
  }

  protected void sendAction()
  {
    turnOnSpeakerIfRequired();
    DialUtil.dial(getListener().getActivityContext(), this.address, this, getListener(), isVideoCall());
    if (this.contactSelectedName != null)
      sendAcceptedText(this.contactSelectedName, AcceptedText.TextType.DIAL);
  }

  protected void turnOnSpeakerIfRequired()
  {
    PhoneUtil.turnOnSpeakerphoneIfRequired(ApplicationAdapter.getInstance().getApplicationContext());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.controllers.VoiceDialController
 * JD-Core Version:    0.6.0
 */