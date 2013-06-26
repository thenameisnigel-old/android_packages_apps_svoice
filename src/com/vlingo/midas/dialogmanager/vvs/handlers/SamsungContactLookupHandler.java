package com.vlingo.midas.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.ContactLookupHandler;
import com.vlingo.core.internal.util.StringUtils;

public class SamsungContactLookupHandler extends ContactLookupHandler
{
  protected void openContact(ContactMatch paramContactMatch)
  {
    WidgetDecorator localWidgetDecorator = null;
    checkWrongDateOfBirthday(paramContactMatch);
    String str2;
    String str4;
    if (!StringUtils.isNullOrWhiteSpace(this.query))
    {
      str2 = getContactData(paramContactMatch);
      if (!StringUtils.isNullOrWhiteSpace(str2))
        if (this.query.contains("pn"))
        {
          ResourceIdProvider.string localstring5 = ResourceIdProvider.string.core_contact_phone_number;
          Object[] arrayOfObject5 = new Object[1];
          arrayOfObject5[0] = this.name;
          str4 = getString(localstring5, arrayOfObject5);
          showSystemTurn(str4, str4);
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
      if (this.query.contains("email"))
      {
        ResourceIdProvider.string localstring4 = ResourceIdProvider.string.core_contact_email;
        Object[] arrayOfObject4 = new Object[1];
        arrayOfObject4[0] = this.name;
        str4 = getString(localstring4, arrayOfObject4);
        break;
      }
      if (this.query.contains("address"))
      {
        ResourceIdProvider.string localstring3 = ResourceIdProvider.string.core_contact_address;
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = this.name;
        str4 = getString(localstring3, arrayOfObject3);
        break;
      }
      str4 = str2;
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
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.SamsungContactLookupHandler
 * JD-Core Version:    0.6.0
 */