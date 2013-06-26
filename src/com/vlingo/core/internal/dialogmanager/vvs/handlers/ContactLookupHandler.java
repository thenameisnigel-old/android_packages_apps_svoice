package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.contacts.ContactDBUtil;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactLookupType;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.contacts.ContactMatchListener;
import com.vlingo.core.internal.contacts.ContactMatcher;
import com.vlingo.core.internal.contacts.ContactType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.schedule.DateUtil;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.OrdinalUtil;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

public class ContactLookupHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private ContactMatcher contactMatcher = null;
  protected WidgetDecorator decor;
  private List<ContactMatch> displayedContacts;
  protected String name = "";
  private String ordinal;
  protected String query;
  private ContactType type = ContactType.UNDEFINED;

  private int[] getAddressTypes()
  {
    int[] arrayOfInt = new int[4];
    arrayOfInt[0] = 2;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 1;
    arrayOfInt[3] = 3;
    return arrayOfInt;
  }

  private int[] getEmailTypes()
  {
    int[] arrayOfInt = new int[4];
    arrayOfInt[0] = 2;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 1;
    arrayOfInt[3] = 3;
    return arrayOfInt;
  }

  private int[] getPhoneTypes()
  {
    int[] arrayOfInt = new int[4];
    arrayOfInt[0] = 3;
    arrayOfInt[1] = 2;
    arrayOfInt[2] = 1;
    arrayOfInt[3] = 7;
    return arrayOfInt;
  }

  private int[] getSocialTypes()
  {
    return null;
  }

  private void matchContacts(ContactType paramContactType)
  {
    Context localContext = getListener().getActivityContext();
    List localList = (List)(List)getListener().getState(DialogDataType.CONTACT_MATCHES);
    this.contactMatcher = new ContactMatcher(localContext, new LocalContactMatchListener(null), paramContactType, getPhoneTypes(), getEmailTypes(), getSocialTypes(), getAddressTypes(), this.name, 100.0F, localList);
  }

  private void multipleContacts(List<ContactMatch> paramList)
  {
    getListener().storeState(DialogDataType.CONTACT_MATCHES, paramList);
    VVSActionBase.UnifiedPrompter localUnifiedPrompter = unified();
    ResourceIdProvider.string localstring = ResourceIdProvider.string.core_multiple_contacts;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.name;
    localUnifiedPrompter.showSystemTurn(getString(localstring, arrayOfObject), true, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_CONTACTLOOKUP_CHOOSE));
    this.displayedContacts = paramList;
    getListener().showWidget(WidgetUtil.WidgetKey.AddressBook, null, paramList, this);
  }

  protected void checkWrongDateOfBirthday(ContactMatch paramContactMatch)
  {
    List localList = paramContactMatch.getBirthdayData();
    if ((localList != null) && (!localList.isEmpty()))
    {
      ContactData localContactData = (ContactData)localList.get(0);
      String str1 = localContactData.address;
      try
      {
        if (str1.contains("-"));
        String str2;
        for (Object localObject = DateUtil.getDayAndMonthFromString(str1); ; localObject = str2)
        {
          localList.set(0, new ContactData(localContactData.contact, localContactData.kind, (String)localObject, localContactData.type, localContactData.isDefault));
          break;
          str2 = DateUtil.getLongDateStringFromPhonePreferencesString(str1);
        }
      }
      catch (Exception localException)
      {
      }
    }
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("contact");
    if (VLActionUtil.isParamSpecified(paramVLAction, "Name"))
      this.name = StringUtils.removePossessives(VLActionUtil.getParamString(paramVLAction, "Name", true));
    this.query = VLActionUtil.getParamString(paramVLAction, "Query", false);
    HashMap localHashMap;
    ArrayList localArrayList;
    Context localContext;
    label207: int i;
    if (StringUtils.isNullOrWhiteSpace(this.query))
    {
      this.query = ((String)paramVVSActionHandlerListener.getState(DialogDataType.CONTACT_QUERY));
      this.ordinal = VLActionUtil.getParamString(paramVLAction, "Which", false);
      this.type = ContactType.UNDEFINED;
      this.decor = null;
      localHashMap = new HashMap();
      localArrayList = new ArrayList();
      localContext = getListener().getActivityContext();
      ContactDBUtil.findMatchesByName(localContext, this.name, null, localHashMap, localArrayList);
      if ((localHashMap.size() > 0) && (this.query != null))
      {
        if (!this.query.contains("email"))
          break label288;
        int j = ContactDBUtil.getContactDetails(localContext, EnumSet.of(ContactLookupType.EMAIL_ADDRESS), localHashMap, localArrayList);
        this.type = ContactType.EMAIL;
        if (j <= 0)
          break label280;
        this.decor = WidgetDecorator.makeContactShowEmail();
      }
      if (StringUtils.isNullOrWhiteSpace(this.ordinal))
        break label507;
      if (OrdinalUtil.getElement(getListener(), this.ordinal) == null)
        break label444;
      openContact((ContactMatch)OrdinalUtil.getElement(getListener(), this.ordinal));
      getListener().asyncHandlerDone();
      label258: i = 1;
    }
    while (true)
    {
      return i;
      paramVVSActionHandlerListener.storeState(DialogDataType.CONTACT_QUERY, this.query);
      break;
      label280: this.decor = null;
      break label207;
      label288: if (this.query.contains("pn"))
      {
        this.type = ContactType.CALL;
        this.decor = WidgetDecorator.makeContactShowPhone();
        break label207;
      }
      if (this.query.contains("address"))
      {
        this.type = ContactType.ADDRESS;
        this.decor = WidgetDecorator.makeContactShowAddress();
        if (ContactDBUtil.getContactDetails(localContext, EnumSet.of(ContactLookupType.ADDRESS), localHashMap, localArrayList) > 0)
        {
          this.decor = WidgetDecorator.makeContactShowAddress();
          break label207;
        }
        this.decor = null;
        break label207;
      }
      if (!this.query.contains("birthday"))
        break label207;
      this.type = ContactType.BIRTHDAY;
      this.decor = WidgetDecorator.makeContactShowBirthday();
      if (ContactDBUtil.getContactDetails(localContext, EnumSet.of(ContactLookupType.BIRTHDAY), localHashMap, localArrayList) > 0)
      {
        this.decor = WidgetDecorator.makeContactShowBirthday();
        break label207;
      }
      this.decor = null;
      break label207;
      label444: this.displayedContacts = ((List)getListener().getState(DialogDataType.ORDINAL_DATA));
      getListener().storeState(DialogDataType.CONTACT_MATCHES, null);
      OrdinalUtil.storeOrdinalData(getListener(), this.displayedContacts);
      multipleContacts(this.displayedContacts);
      getListener().asyncHandlerDone();
      break label258;
      label507: if (StringUtils.isNullOrWhiteSpace(this.name))
      {
        unified().showSystemTurn(ResourceIdProvider.string.core_car_tts_WHICH_CONTACT_DEMAND, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_CONTACTLOOKUP_CHOOSE));
        i = 0;
        continue;
      }
      matchContacts(this.type);
      i = 1;
    }
  }

  public String getContactData(ContactMatch paramContactMatch)
  {
    String str = "";
    if (this.query.contains("email"))
    {
      List localList4 = paramContactMatch.getEmailData();
      if ((localList4 != null) && (!localList4.isEmpty()))
        str = ((ContactData)localList4.get(0)).address;
    }
    while (true)
    {
      return str;
      if (this.query.contains("pn"))
      {
        List localList3 = paramContactMatch.getPhoneData();
        if ((localList3 == null) || (localList3.isEmpty()))
          continue;
        str = ((ContactData)localList3.get(0)).address;
        continue;
      }
      if (this.query.contains("address"))
      {
        List localList2 = paramContactMatch.getAddressData();
        if ((localList2 == null) || (localList2.isEmpty()))
          continue;
        str = ((ContactData)localList2.get(0)).address;
        continue;
      }
      if (!this.query.contains("birthday"))
        continue;
      List localList1 = paramContactMatch.getBirthdayData();
      if ((localList1 == null) || (localList1.isEmpty()))
        continue;
      str = ((ContactData)localList1.get(0)).address;
    }
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    if (paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.ContactChoice"))
    {
      int i = paramIntent.getExtras().getInt("choice");
      getListener().interruptTurn();
      openContact((ContactMatch)this.displayedContacts.get(i));
    }
    while (true)
    {
      return;
      if ((getListener() != null) && ("com.vlingo.core.internal.dialogmanager.DataTransfered".equals(paramIntent.getAction())))
      {
        VVSActionHandlerListener localVVSActionHandlerListener = getListener();
        if ((paramObject instanceof Integer));
        for (Integer localInteger = Integer.valueOf(((Integer)paramObject).intValue()); ; localInteger = null)
        {
          OrdinalUtil.storeOrdinalData(localVVSActionHandlerListener, localInteger.intValue());
          break;
        }
      }
      throwUnknownActionException(paramIntent.getAction());
    }
  }

  protected void openContact(ContactMatch paramContactMatch)
  {
    WidgetDecorator localWidgetDecorator = null;
    checkWrongDateOfBirthday(paramContactMatch);
    String str2;
    if (!StringUtils.isNullOrWhiteSpace(this.query))
    {
      str2 = getContactData(paramContactMatch);
      if (!StringUtils.isNullOrWhiteSpace(str2))
        if (this.query.contains("pn"))
        {
          showSystemTurn(str2, StringUtils.formatPhoneNumberForTTS(str2));
          localWidgetDecorator = this.decor;
        }
    }
    while (true)
    {
      getListener().showWidget(WidgetUtil.WidgetKey.ContactDetail, localWidgetDecorator, paramContactMatch, this);
      getListener().setFieldId(VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_CONTACTLOOKUP));
      getListener().storeState(DialogDataType.SELECTED_CONTACT, paramContactMatch);
      getListener().storeState(DialogDataType.CONTACT_QUERY, null);
      getListener().storeState(DialogDataType.CONTACT_MATCHES, null);
      return;
      showSystemTurn(str2, str2);
      break;
      ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_single_contact;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = paramContactMatch.primaryDisplayName;
      String str3 = getString(localstring2, arrayOfObject2);
      getListener().showVlingoTextAndTTS(str3, str3);
      continue;
      ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_single_contact;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = paramContactMatch.primaryDisplayName;
      String str1 = getString(localstring1, arrayOfObject1);
      getListener().showVlingoTextAndTTS(str1, str1);
    }
  }

  public void reset()
  {
    getListener().finishDialog();
  }

  private class LocalContactMatchListener
    implements ContactMatchListener
  {
    private LocalContactMatchListener()
    {
    }

    public void onAutoAction(ContactMatch paramContactMatch)
    {
    }

    public void onContactMatchResultsUpdated(List<ContactMatch> paramList)
    {
    }

    public void onContactMatchingFailed()
    {
      ContactLookupHandler.this.getListener().asyncHandlerDone();
    }

    public void onContactMatchingFinished(List<ContactMatch> paramList)
    {
      while (true)
      {
        try
        {
          ContactLookupHandler.this.getListener().storeState(DialogDataType.CONTACT_MATCHES, null);
          if (paramList == null)
            continue;
          if (paramList.size() != 1)
            continue;
          ContactLookupHandler.this.openContact((ContactMatch)paramList.get(0));
          return;
          if (paramList.size() > 1)
          {
            OrdinalUtil.storeOrdinalData(ContactLookupHandler.this.getListener(), paramList);
            ContactLookupHandler.this.multipleContacts(paramList);
            continue;
          }
        }
        finally
        {
          ContactLookupHandler.this.getListener().asyncHandlerDone();
        }
        if (ContactLookupHandler.this.type == ContactType.EMAIL)
        {
          ResourceIdProvider.string localstring4 = ResourceIdProvider.string.core_contact_email_not_found;
          Object[] arrayOfObject4 = new Object[1];
          arrayOfObject4[0] = ContactLookupHandler.this.name;
          String str4 = ContactLookupHandler.access$500(localstring4, arrayOfObject4);
          ContactLookupHandler.this.getListener().showVlingoTextAndTTS(str4, str4);
          ContactLookupHandler.this.getListener().setFieldId(VlingoAndroidCore.getFieldId(FieldIds.DEFAULT));
          continue;
        }
        if (ContactLookupHandler.this.type == ContactType.ADDRESS)
        {
          ResourceIdProvider.string localstring3 = ResourceIdProvider.string.core_contact_address_not_found;
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = ContactLookupHandler.this.name;
          String str3 = ContactLookupHandler.access$800(localstring3, arrayOfObject3);
          ContactLookupHandler.this.getListener().showVlingoTextAndTTS(str3, str3);
          ContactLookupHandler.this.getListener().setFieldId(VlingoAndroidCore.getFieldId(FieldIds.DEFAULT));
          continue;
        }
        if (ContactLookupHandler.this.type == ContactType.BIRTHDAY)
        {
          ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_contact_birthday_not_found;
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = ContactLookupHandler.this.name;
          String str2 = ContactLookupHandler.access$1100(localstring2, arrayOfObject2);
          ContactLookupHandler.this.getListener().showVlingoTextAndTTS(str2, str2);
          ContactLookupHandler.this.getListener().setFieldId(VlingoAndroidCore.getFieldId(FieldIds.DEFAULT));
          continue;
        }
        ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_contacts_no_match_openquote;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = ContactLookupHandler.this.name;
        String str1 = ContactLookupHandler.access$1400(localstring1, arrayOfObject1);
        ContactLookupHandler.this.getListener().showVlingoTextAndTTS(str1, str1);
        ContactLookupHandler.this.getListener().setFieldId(VlingoAndroidCore.getFieldId(FieldIds.DEFAULT));
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.ContactLookupHandler
 * JD-Core Version:    0.6.0
 */