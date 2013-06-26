package com.vlingo.core.internal.dialogmanager.actions;

import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.contacts.ContactType;
import com.vlingo.core.internal.contacts.mru.MRU;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.SendEmailInterface;
import com.vlingo.core.internal.util.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SendEmailAction extends DMAction
  implements SendEmailInterface
{
  private List<ContactData> contacts = new ArrayList();
  private String message;
  private String subject;

  private List<ContactData> contacts()
  {
    Object localObject;
    if (this.contacts == null)
    {
      localObject = new ArrayList();
      this.contacts = ((List)localObject);
    }
    while (true)
    {
      return localObject;
      localObject = this.contacts;
    }
  }

  private String[] extractAddresses()
  {
    String[] arrayOfString = new String[contacts().size()];
    for (int i = 0; i < contacts().size(); i++)
      arrayOfString[i] = ((ContactData)contacts().get(i)).address;
    return arrayOfString;
  }

  private void updateMRU()
  {
    Iterator localIterator = contacts().iterator();
    while (localIterator.hasNext())
    {
      ContactData localContactData = (ContactData)localIterator.next();
      MRU.getMRU().incrementCount(ContactType.EMAIL.toString(), (int)localContactData.contact.primaryContactID, localContactData.address);
    }
  }

  public SendEmailInterface contact(ContactData paramContactData)
  {
    this.contacts.add(paramContactData);
    return this;
  }

  public SendEmailInterface contacts(List<ContactData> paramList)
  {
    this.contacts = paramList;
    return this;
  }

  protected void execute()
  {
    if ((contacts() != null) && (!contacts().isEmpty()))
    {
      updateMRU();
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.putExtra("android.intent.extra.EMAIL", extractAddresses());
      if (!StringUtils.isNullOrWhiteSpace(this.subject))
        localIntent.putExtra("android.intent.extra.SUBJECT", this.subject);
      localIntent.putExtra("android.intent.extra.TEXT", this.message);
      localIntent.setType("message/rfc822");
      getContext().startActivity(localIntent);
      getListener().actionSuccess();
    }
  }

  public SendEmailAction message(String paramString)
  {
    this.message = paramString;
    return this;
  }

  public SendEmailInterface subject(String paramString)
  {
    this.subject = paramString;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.SendEmailAction
 * JD-Core Version:    0.6.0
 */