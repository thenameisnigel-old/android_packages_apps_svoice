package com.vlingo.core.internal.dialogmanager.actions.interfaces;

import com.vlingo.core.internal.contacts.ContactData;
import java.util.List;

public abstract interface SendEmailInterface extends ActionInterface
{
  public abstract SendEmailInterface contact(ContactData paramContactData);

  public abstract SendEmailInterface contacts(List<ContactData> paramList);

  public abstract SendEmailInterface message(String paramString);

  public abstract SendEmailInterface subject(String paramString);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.interfaces.SendEmailInterface
 * JD-Core Version:    0.6.0
 */