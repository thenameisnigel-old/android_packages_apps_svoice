package com.vlingo.core.internal.dialogmanager.actions;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;

public class ViewContactAction extends DMAction
{
  private ContactMatch contact;

  public ViewContactAction contact(ContactMatch paramContactMatch)
  {
    this.contact = paramContactMatch;
    return this;
  }

  protected void execute()
  {
    Intent localIntent;
    if (this.contact != null)
    {
      localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(this.contact.primaryContactID)));
    }
    try
    {
      getContext().startActivity(localIntent);
      label46: getListener().actionSuccess();
      while (true)
      {
        return;
        getListener().actionSuccess();
      }
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      break label46;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.ViewContactAction
 * JD-Core Version:    0.6.0
 */