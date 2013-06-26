package com.vlingo.core.internal.contacts;

import android.content.Context;
import android.util.Log;
import com.vlingo.core.internal.logging.Logger;

public class ContactFetchAndSortRequest extends ContactSortRequest
{
  private static Logger log = Logger.getLogger(ContactMatcher.class);
  private Context context;

  ContactFetchAndSortRequest(Context paramContext, ContactType paramContactType, String paramString, int[] paramArrayOfInt, AsyncContactSorterCallback paramAsyncContactSorterCallback)
  {
    super(paramString, paramContactType, paramArrayOfInt, paramAsyncContactSorterCallback);
    this.context = paramContext;
  }

  public void findMatchesAndSortContacts()
  {
    try
    {
      this.matches = ContactDBUtil.getContactMatches(this.context, this.query, this.type.getLookupTypes(), this.type.keepUnrelatedContacts());
      this.context = null;
      scoreContacts(this.type.keepUnrelatedContacts());
      super.run();
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      while (true)
      {
        log.error("run(): Caught RuntimeException " + localRuntimeException.getMessage() + "; stack trace:" + Log.getStackTraceString(localRuntimeException));
        this.context = null;
      }
    }
    finally
    {
      this.context = null;
    }
    throw localObject;
  }

  public void run()
  {
    findMatchesAndSortContacts();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.contacts.ContactFetchAndSortRequest
 * JD-Core Version:    0.6.0
 */