package com.vlingo.core.internal.dialogmanager.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.contacts.ContactMatchListener;
import com.vlingo.core.internal.contacts.ContactMatcher;
import com.vlingo.core.internal.contacts.ContactType;
import com.vlingo.core.internal.dialogmanager.Controller;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.recognition.acceptedtext.AcceptedText.TextType;
import com.vlingo.core.internal.recognition.acceptedtext.BaseAcceptedText;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.ContactUtil;
import com.vlingo.core.internal.util.DialUtil;
import com.vlingo.core.internal.util.OrdinalUtil;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class VDSMSBaseController extends Controller
  implements WidgetActionListener
{
  protected static final String PARAM_VIDEO_MODE = "videophone";
  protected VLAction action;
  protected String address;
  protected CallMode callMode = CallMode.VOICECALL;
  protected ContactMatcher contactMatcher;
  protected ContactMatch contactSelected;
  protected String contactSelectedName;
  private List<ContactMatch> displayedContacts = null;
  private Map<String, ContactData> displayedPhoneInfo = null;
  private List<String> displayedPhoneLabels = null;
  private boolean executed = false;
  protected String message;
  protected String ordinal = null;
  protected String phoneType = "call";
  protected String query;
  protected State state = State.NEED_CONTACT;

  private void clearState()
  {
    this.state = State.NEED_CONTACT;
    this.executed = false;
    this.contactSelectedName = null;
    this.contactSelected = null;
    this.contactMatcher = null;
    this.displayedPhoneInfo = null;
    this.displayedPhoneLabels = null;
    this.displayedContacts = null;
    getListener().storeState(DialogDataType.SELECTED_CONTACT, null);
  }

  private boolean exists(String paramString)
  {
    if (!StringUtils.isNullOrWhiteSpace(paramString));
    for (int i = 1; ; i = 0)
      return i;
  }

  private static String getAddrBookEmptyPrompt()
  {
    return getString(ResourceIdProvider.string.core_address_book_is_empty, new Object[0]);
  }

  private String getParameter(String paramString, boolean paramBoolean)
  {
    if (paramString.equalsIgnoreCase("To"));
    for (String str = StringUtils.removePossessives(VLActionUtil.getParamString(this.action, paramString, paramBoolean)); ; str = VLActionUtil.getParamString(this.action, paramString, paramBoolean))
      return str;
  }

  private boolean handleLPAction()
  {
    boolean bool = true;
    String str = getParameter("Action", bool);
    if (str.equalsIgnoreCase("send"))
    {
      if (StringUtils.isNullOrWhiteSpace(this.message))
        handleNeedContact();
    }
    else
    {
      if (str.equalsIgnoreCase("cancel"))
      {
        getListener().interruptTurn();
        unified().showSystemTurn(ResourceIdProvider.string.core_car_tts_TASK_CANCELLED);
        reset();
      }
      bool = false;
    }
    while (true)
    {
      return bool;
      sendAction();
    }
  }

  private void setupAutoDial()
  {
    int i = 0;
    String str = Settings.getString("auto_dial", "confident");
    if ("always".equalsIgnoreCase(str))
      i = 2;
    while (true)
    {
      this.contactMatcher.setAutoActionType(i);
      return;
      if (!"confident".equalsIgnoreCase(str))
        continue;
      i = 1;
    }
  }

  protected void actionDefault()
  {
    getListener().interruptTurn();
    sendAction();
  }

  protected abstract boolean checkHistory(ContactMatch paramContactMatch);

  protected boolean checkMessageType(ContactMatch paramContactMatch)
  {
    return checkMessageType(paramContactMatch, true);
  }

  protected boolean checkMessageType(ContactMatch paramContactMatch, boolean paramBoolean)
  {
    int i = 0;
    if (this.executed)
      i = 1;
    while (true)
    {
      return i;
      if (paramContactMatch == null)
        continue;
      if (paramContactMatch.getPhoneData() != null)
      {
        if (paramContactMatch.getPhoneData().size() == 1)
        {
          this.address = ((ContactData)paramContactMatch.getPhoneData().get(0)).address;
          handleNeedMessage();
          i = 1;
          continue;
        }
        if (Settings.getBoolean("use_default_phone", true))
        {
          String str2 = DialUtil.getDefaultNumber(paramContactMatch.getPhoneData());
          if (!StringUtils.isNullOrWhiteSpace(str2))
          {
            this.address = str2;
            handleNeedMessage();
            i = 1;
            continue;
          }
        }
        if (!paramBoolean)
        {
          this.address = ((ContactData)Collections.max(paramContactMatch.getPhoneData())).address;
          handleNeedMessage();
          i = 1;
          continue;
        }
        Map localMap = handleTypeMatch();
        if (localMap == null)
        {
          i = 1;
          continue;
        }
        if (checkHistory(paramContactMatch))
        {
          i = 1;
          continue;
        }
        if (!paramBoolean)
          continue;
        UserLoggingEngine.getInstance().landingPageViewed("car-voicedial-phonetype");
        promptForMessageType(localMap);
        continue;
      }
      String str1 = getNoContactPrompt();
      showSystemTurn(str1, str1);
    }
  }

  public VDSMSBaseController clone()
    throws CloneNotSupportedException
  {
    List localList = null;
    VDSMSBaseController localVDSMSBaseController = (VDSMSBaseController)super.clone();
    ContactMatch localContactMatch;
    Map localMap;
    label34: ArrayList localArrayList;
    if (this.contactSelected == null)
    {
      localContactMatch = null;
      localVDSMSBaseController.contactSelected = localContactMatch;
      if (this.displayedPhoneInfo != null)
        break label81;
      localMap = null;
      localVDSMSBaseController.displayedPhoneInfo = localMap;
      if (this.displayedPhoneLabels != null)
        break label93;
      localArrayList = null;
      label50: localVDSMSBaseController.displayedPhoneLabels = localArrayList;
      if (this.displayedContacts != null)
        break label109;
    }
    while (true)
    {
      localVDSMSBaseController.displayedContacts = localList;
      return localVDSMSBaseController;
      localContactMatch = this.contactSelected.clone();
      break;
      label81: localMap = ContactData.clone(this.displayedPhoneInfo);
      break label34;
      label93: localArrayList = new ArrayList(this.displayedPhoneLabels);
      break label50;
      label109: localList = ContactMatch.clone(this.displayedContacts);
    }
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    this.action = paramVLAction;
    if (paramVLAction == null);
    for (boolean bool = false; ; bool = handleLPAction())
    {
      return bool;
      if (!paramVLAction.getName().equals("LPAction"))
        break;
    }
    this.query = getParameter("To", false);
    if (exists(this.query))
      clearState();
    String str1 = getParameter("Mode", false);
    if ((exists(str1)) && (str1.equalsIgnoreCase("videophone")) && (ClientSuppliedValues.isVideoCallingSupported()))
      this.callMode = CallMode.VOICEVIDEOCALL;
    String str2 = getParameter("Message", false);
    if (exists(str2))
      this.message = str2;
    this.ordinal = VLActionUtil.getParamString(paramVLAction, "Which", false);
    String str3 = getParameter("PhoneType", false);
    if (exists(str3))
    {
      this.phoneType = str3;
      checkMessageType(this.contactSelected);
      label185: switch (1.$SwitchMap$com$vlingo$core$internal$dialogmanager$controllers$VDSMSBaseController$State[this.state.ordinal()])
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      }
    }
    while (true)
    {
      bool = false;
      break;
      if (StringUtils.isNullOrWhiteSpace(this.ordinal))
        break label185;
      Object localObject = OrdinalUtil.getElement(getListener(), this.ordinal);
      if ((this.ordinal == null) || (localObject == null) || (!(localObject instanceof String)) || (!exists((String)localObject)))
        break label185;
      OrdinalUtil.clearOrdinalData(getListener());
      this.phoneType = localObject.toString();
      checkMessageType(this.contactSelected);
      break label185;
      if (exists(this.query))
      {
        if (this.query.matches("[\\d|-|\\ ]+"))
        {
          this.address = this.query;
          handleNeedMessage();
          continue;
        }
        getListener().storeState(DialogDataType.SELECTED_CONTACT, null);
        matchContacts(ContactType.CALL);
        bool = true;
        break;
      }
      handleNeedContact();
      continue;
      String str4 = getParameter("To", false);
      if (this.contactSelected != null)
      {
        this.query = this.contactSelectedName;
        this.contactSelectedName = null;
        matchContacts(ContactType.CALL);
        bool = true;
        break;
      }
      if ((str4 == null) || (this.contactSelected != null))
        continue;
      this.query = str4;
      matchContacts(ContactType.CALL);
      bool = true;
      break;
      if (exists(str3))
      {
        handleNeedMessage();
        continue;
      }
      matchContacts(ContactType.CALL);
      bool = true;
      break;
      handleNeedMessage();
      continue;
      promptForConfirmation();
    }
  }

  protected String findKey(Map<String, ContactData> paramMap, String paramString)
  {
    Iterator localIterator = paramMap.keySet().iterator();
    String str1;
    String str2;
    String str3;
    do
    {
      if (!localIterator.hasNext())
        break;
      str1 = (String)localIterator.next();
      ContactData localContactData = (ContactData)paramMap.get(str1);
      str2 = str1.replaceAll("\\D", "");
      str3 = ContactUtil.getTypeStringEN(localContactData.type);
    }
    while (!(str3 + str2).equalsIgnoreCase(paramString));
    while (true)
    {
      return str1;
      str1 = null;
    }
  }

  protected int[] getAddressTypes()
  {
    if (StringUtils.isNullOrWhiteSpace(getType()));
    while (true)
    {
      return null;
      if (getCapability() != ContactType.ADDRESS)
        continue;
    }
  }

  protected abstract ContactType getCapability();

  protected abstract String getContactPrompt();

  protected int[] getEmailTypes()
  {
    if (StringUtils.isNullOrWhiteSpace(getType()));
    while (true)
    {
      return null;
      if (getCapability() != ContactType.EMAIL)
        continue;
    }
  }

  protected DialogFieldID getFieldID(FieldID paramFieldID)
  {
    return (DialogFieldID)getFieldIds().get(paramFieldID);
  }

  protected abstract HashMap<FieldID, DialogFieldID> getFieldIds();

  protected abstract String getNoContactPrompt();

  protected int[] getPhoneTypes()
  {
    int[] arrayOfInt = null;
    if (StringUtils.isNullOrWhiteSpace(this.phoneType));
    while (true)
    {
      return arrayOfInt;
      if (getCapability() != ContactType.CALL)
        continue;
      if (this.phoneType.equalsIgnoreCase("work"))
      {
        arrayOfInt = new int[1];
        arrayOfInt[0] = 3;
        continue;
      }
      if (this.phoneType.equalsIgnoreCase("mobile"))
      {
        arrayOfInt = new int[1];
        arrayOfInt[0] = 2;
        continue;
      }
      if (this.phoneType.equalsIgnoreCase("home"))
      {
        arrayOfInt = new int[1];
        arrayOfInt[0] = 1;
        continue;
      }
      if (!this.phoneType.equalsIgnoreCase("other"))
        continue;
      arrayOfInt = new int[1];
      arrayOfInt[0] = 7;
    }
  }

  protected int[] getSocialTypes()
  {
    if (StringUtils.isNullOrWhiteSpace(getType()));
    while (true)
    {
      return null;
      if (getCapability() != ContactType.FACEBOOK)
        continue;
    }
  }

  protected abstract String getType();

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    Integer localInteger = null;
    Bundle localBundle = paramIntent.getExtras();
    if (paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.ContactChoice"))
    {
      getListener().interruptTurn();
      if (localBundle.containsKey("choice"))
      {
        getListener().storeState(DialogDataType.CONTACT_MATCHES, null);
        int j = localBundle.getInt("choice");
        this.contactSelected = ((ContactMatch)this.displayedContacts.get(j));
        this.contactSelectedName = this.contactSelected.primaryDisplayName;
        checkMessageType(this.contactSelected);
        sendAcceptedText(new BaseAcceptedText(this.contactSelectedName, AcceptedText.TextType.DIAL));
      }
    }
    while (true)
    {
      return;
      if (paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Choice"))
      {
        getListener().interruptTurn();
        if (!localBundle.containsKey("choice"))
          continue;
        int i = localBundle.getInt("choice");
        this.phoneType = ((String)this.displayedPhoneLabels.get(i));
        this.address = ((ContactData)this.displayedPhoneInfo.get(this.phoneType)).address;
        handleNeedMessage();
        sendAcceptedText(new BaseAcceptedText(this.address, AcceptedText.TextType.DIAL));
        continue;
      }
      if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Cancel"))
      {
        getListener().interruptTurn();
        unified().showSystemTurn(getString(ResourceIdProvider.string.core_car_tts_TASK_CANCELLED, new Object[0]));
        reset();
        continue;
      }
      if (paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Default"))
      {
        actionDefault();
        continue;
      }
      if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.BodyChange"))
      {
        if (paramIntent.hasExtra("message"))
        {
          getListener().finishTurn();
          this.message = VLActionUtil.extractParamString(paramIntent, "message");
          continue;
        }
        this.message = null;
        continue;
      }
      if ((getListener() == null) || (!"com.vlingo.core.internal.dialogmanager.DataTransfered".equals(paramIntent.getAction())))
        continue;
      VVSActionHandlerListener localVVSActionHandlerListener = getListener();
      if ((paramObject instanceof Integer))
        localInteger = Integer.valueOf(((Integer)paramObject).intValue());
      OrdinalUtil.storeOrdinalData(localVVSActionHandlerListener, localInteger.intValue());
    }
  }

  protected void handleNeedContact()
  {
    this.contactSelected = ((ContactMatch)getListener().getState(DialogDataType.SELECTED_CONTACT));
    String str1 = VLActionUtil.getParamString(this.action, "Action", false);
    if ((!StringUtils.isNullOrWhiteSpace(str1)) && (str1.equalsIgnoreCase("redial")));
    while (true)
    {
      return;
      if (this.contactSelected == null)
      {
        this.state = State.NEED_CONTACT;
        String str2 = getContactPrompt();
        boolean bool = true;
        if ((this.contactMatcher != null) && (this.contactMatcher.isAddressBookEmpty()))
        {
          str2 = getAddrBookEmptyPrompt();
          bool = false;
        }
        showSystemTurn(str2, str2, bool, getFieldID(FieldID.CONTACT));
        UserLoggingEngine.getInstance().landingPageViewed("car-voicedial-contact");
        continue;
      }
      this.contactSelectedName = this.contactSelected.primaryDisplayName;
      this.executed = checkMessageType(this.contactSelected);
    }
  }

  protected abstract void handleNeedMessage();

  protected Map<String, ContactData> handleTypeMatch()
  {
    List localList = this.contactSelected.getPhoneData();
    Map localMap1 = null;
    if (!localList.isEmpty())
    {
      localMap1 = DialUtil.getPhoneTypeMap(getListener().getActivityContext().getResources(), localList, this.phoneType);
      if (localMap1.isEmpty())
        localMap1 = DialUtil.getPhoneTypeMap(getListener().getActivityContext().getResources(), localList, "call");
      String str = findKey(localMap1, this.phoneType);
      if (str != null)
      {
        this.address = ((ContactData)localMap1.get(str)).address;
        handleNeedMessage();
      }
    }
    for (Map localMap2 = null; ; localMap2 = localMap1)
      return localMap2;
  }

  protected boolean isVideoCall()
  {
    if (this.callMode == CallMode.VOICEVIDEOCALL);
    for (int i = 1; ; i = 0)
      return i;
  }

  protected void matchContacts(ContactType paramContactType)
  {
    Context localContext = getListener().getActivityContext();
    String str = this.query;
    if (!StringUtils.isNullOrWhiteSpace(this.contactSelectedName))
      str = this.contactSelectedName;
    List localList = (List)(List)getListener().getState(DialogDataType.CONTACT_MATCHES);
    this.contactMatcher = new ContactMatcher(localContext, new LocalContactMatchListener(), paramContactType, getPhoneTypes(), getEmailTypes(), getSocialTypes(), getAddressTypes(), str, 100.0F, localList);
    if (paramContactType == ContactType.CALL)
      setupAutoDial();
  }

  protected abstract void promptForConfirmation();

  protected void promptForMessageType(Map<String, ContactData> paramMap)
  {
    if ((this.phoneType.equalsIgnoreCase("call")) && (StringUtils.isNullOrWhiteSpace(findKey(paramMap, this.phoneType))));
    for (this.state = State.NEED_CONTACT_REFINEMENT; ; this.state = State.NEED_TYPE)
    {
      unified().showSystemTurn(getString(ResourceIdProvider.string.core_multiple_phone_numbers2, new Object[0]), getFieldID(FieldID.TYPE));
      this.displayedPhoneInfo = paramMap;
      this.displayedPhoneLabels = new ArrayList(paramMap.keySet());
      OrdinalUtil.storeOrdinalData(getListener(), this.displayedPhoneLabels);
      getListener().showWidget(WidgetUtil.WidgetKey.ShowContactTypeChoices, null, paramMap, this);
      UserLoggingEngine.getInstance().landingPageViewed("car-voicedial-phonetype");
      return;
    }
  }

  protected void promptForRefineContact(List<ContactMatch> paramList)
  {
    getListener().storeState(DialogDataType.CONTACT_MATCHES, paramList);
    this.state = State.NEED_CONTACT_REFINEMENT;
    VVSActionBase.UnifiedPrompter localUnifiedPrompter = unified();
    ResourceIdProvider.string localstring = ResourceIdProvider.string.core_multiple_contacts;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.query;
    localUnifiedPrompter.showSystemTurn(getString(localstring, arrayOfObject), getFieldID(FieldID.CONTACT));
    this.displayedContacts = paramList;
    getListener().showWidget(WidgetUtil.WidgetKey.AddressBook, null, paramList, this);
    UserLoggingEngine.getInstance().landingPageViewed("car-voicedial-contact");
  }

  protected abstract void sendAction();

  protected void showCallContact(List<ContactMatch> paramList)
  {
    getListener().storeState(DialogDataType.CONTACT_MATCHES, paramList);
    this.state = State.NEED_CONTACT;
    this.displayedContacts = paramList;
    getListener().showWidget(WidgetUtil.WidgetKey.AddressBook, null, paramList, this);
    UserLoggingEngine.getInstance().landingPageViewed("car-voicedial-contact");
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("VDSMSBaseController{");
    localStringBuilder.append("executed=" + this.executed + " ");
    localStringBuilder.append("address=" + this.address + " ");
    localStringBuilder.append("contactSelectedName=" + this.contactSelectedName + " ");
    localStringBuilder.append("contactSelected=" + this.contactSelected + " ");
    localStringBuilder.append("contactMatcher=" + this.contactMatcher + " ");
    localStringBuilder.append("Map<String, ContactData>{");
    if (this.displayedPhoneInfo != null)
    {
      Iterator localIterator3 = this.displayedPhoneInfo.entrySet().iterator();
      while (localIterator3.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator3.next();
        String str2 = (String)localEntry.getKey();
        ContactData localContactData = (ContactData)localEntry.getValue();
        localStringBuilder.append("key=" + str2 + " ");
        localStringBuilder.append("value=" + localContactData.toString() + " ");
      }
    }
    localStringBuilder.append("null");
    localStringBuilder.append("}");
    localStringBuilder.append("List<String>displayedPhoneLabels{");
    if (this.displayedPhoneLabels != null)
    {
      Iterator localIterator2 = this.displayedPhoneLabels.iterator();
      while (localIterator2.hasNext())
      {
        String str1 = (String)localIterator2.next();
        localStringBuilder.append(str1 + " ");
      }
    }
    localStringBuilder.append("null");
    localStringBuilder.append("}");
    localStringBuilder.append("List<ContactMatch> displayedContacts{");
    if (this.displayedContacts != null)
    {
      Iterator localIterator1 = this.displayedContacts.iterator();
      while (localIterator1.hasNext())
        localStringBuilder.append(((ContactMatch)localIterator1.next()).toString());
    }
    localStringBuilder.append("null");
    localStringBuilder.append("}");
    localStringBuilder.append("message=" + this.message + " ");
    localStringBuilder.append("query=" + this.query + " ");
    localStringBuilder.append("phoneType=" + this.phoneType + " ");
    localStringBuilder.append("callMode=" + this.callMode + " ");
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }

  static enum CallMode
  {
    static
    {
      CallMode[] arrayOfCallMode = new CallMode[2];
      arrayOfCallMode[0] = VOICECALL;
      arrayOfCallMode[1] = VOICEVIDEOCALL;
      $VALUES = arrayOfCallMode;
    }
  }

  static enum FieldID
  {
    static
    {
      FieldID[] arrayOfFieldID = new FieldID[2];
      arrayOfFieldID[0] = CONTACT;
      arrayOfFieldID[1] = TYPE;
      $VALUES = arrayOfFieldID;
    }
  }

  protected class LocalContactMatchListener
    implements ContactMatchListener
  {
    protected LocalContactMatchListener()
    {
    }

    public void onAutoAction(ContactMatch paramContactMatch)
    {
      VDSMSBaseController.this.contactSelected = paramContactMatch;
      VDSMSBaseController.this.contactSelectedName = paramContactMatch.primaryDisplayName;
      VDSMSBaseController.access$002(VDSMSBaseController.this, VDSMSBaseController.this.checkMessageType(paramContactMatch, false));
    }

    public void onContactMatchResultsUpdated(List<ContactMatch> paramList)
    {
    }

    public void onContactMatchingFailed()
    {
      VDSMSBaseController.this.getListener().asyncHandlerDone();
    }

    public void onContactMatchingFinished(List<ContactMatch> paramList)
    {
      VDSMSBaseController.this.getListener().storeState(DialogDataType.CONTACT_MATCHES, null);
      if (!VDSMSBaseController.this.executed)
      {
        if ((StringUtils.isNullOrWhiteSpace(VDSMSBaseController.this.ordinal)) || (OrdinalUtil.getElement(VDSMSBaseController.this.getListener(), VDSMSBaseController.this.ordinal) == null))
          break label132;
        ContactMatch localContactMatch2 = (ContactMatch)OrdinalUtil.getElement(VDSMSBaseController.this.getListener(), VDSMSBaseController.this.ordinal);
        VDSMSBaseController.this.contactSelected = localContactMatch2;
        VDSMSBaseController.this.contactSelectedName = localContactMatch2.primaryDisplayName;
        VDSMSBaseController.access$002(VDSMSBaseController.this, VDSMSBaseController.this.checkMessageType(localContactMatch2));
      }
      while (true)
      {
        VDSMSBaseController.this.getListener().asyncHandlerDone();
        return;
        label132: OrdinalUtil.clearOrdinalData(VDSMSBaseController.this.getListener());
        if (paramList.size() == 1)
        {
          if (!VDSMSBaseController.this.contactMatcher.determinePerformAction(paramList))
          {
            VDSMSBaseController.this.showCallContact(paramList);
            continue;
          }
          ContactMatch localContactMatch1 = (ContactMatch)paramList.get(0);
          VDSMSBaseController.this.contactSelected = localContactMatch1;
          VDSMSBaseController.this.contactSelectedName = localContactMatch1.primaryDisplayName;
          VDSMSBaseController.access$002(VDSMSBaseController.this, VDSMSBaseController.this.checkMessageType(localContactMatch1));
          continue;
        }
        if (VDSMSBaseController.this.contactMatcher.getNumContacts() > 1)
        {
          VDSMSBaseController.this.promptForRefineContact(VDSMSBaseController.this.contactMatcher.getSortedContacts());
          OrdinalUtil.storeOrdinalData(VDSMSBaseController.this.getListener(), VDSMSBaseController.this.contactMatcher.getSortedContacts());
          continue;
        }
        try
        {
          Long.parseLong(VDSMSBaseController.this.query.replaceAll("\\D", ""));
          VDSMSBaseController.this.address = VDSMSBaseController.this.query;
          VDSMSBaseController.this.handleNeedMessage();
        }
        catch (NumberFormatException localNumberFormatException)
        {
          if (VDSMSBaseController.this.contactSelectedName != null);
        }
      }
      for (String str1 = VDSMSBaseController.this.query; ; str1 = VDSMSBaseController.this.contactSelectedName)
      {
        ResourceIdProvider.string localstring = ResourceIdProvider.string.core_contacts_no_match_openquote;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = str1;
        String str2 = VDSMSBaseController.access$600(localstring, arrayOfObject);
        VDSMSBaseController.this.getListener().showVlingoTextAndTTS(str2, str2);
        VDSMSBaseController.this.handleNeedContact();
        break;
      }
    }
  }

  static enum State
  {
    static
    {
      NEED_MESSAGE = new State("NEED_MESSAGE", 3);
      NEED_CONFIRMATION = new State("NEED_CONFIRMATION", 4);
      POST_CONFIRMATION = new State("POST_CONFIRMATION", 5);
      NEED_REDIAL_CONFIRMATION = new State("NEED_REDIAL_CONFIRMATION", 6);
      State[] arrayOfState = new State[7];
      arrayOfState[0] = NEED_CONTACT;
      arrayOfState[1] = NEED_CONTACT_REFINEMENT;
      arrayOfState[2] = NEED_TYPE;
      arrayOfState[3] = NEED_MESSAGE;
      arrayOfState[4] = NEED_CONFIRMATION;
      arrayOfState[5] = POST_CONFIRMATION;
      arrayOfState[6] = NEED_REDIAL_CONFIRMATION;
      $VALUES = arrayOfState;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.controllers.VDSMSBaseController
 * JD-Core Version:    0.6.0
 */