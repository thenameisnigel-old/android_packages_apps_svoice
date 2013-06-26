package com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve;

import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.contacts.ContactMatchListener;
import com.vlingo.core.internal.contacts.ContactMatcher;
import com.vlingo.core.internal.contacts.ContactType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.events.ContactResolvedEvent;
import com.vlingo.core.internal.dialogmanager.util.DialogDataUtil;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResolveContactHandler extends QueryHandler
{
  private static final String ANAPHORA = "ANAPHORA";
  ContactType capability = ContactType.UNDEFINED;
  private ContactMatcher contactMatcher;
  boolean detailed;
  int limit;
  private String query;
  private String type;

  private int[] getAddressTypes()
  {
    return null;
  }

  private int[] getEmailTypes()
  {
    return null;
  }

  private int[] getPhoneTypes()
  {
    int[] arrayOfInt = null;
    if (StringUtils.isNullOrWhiteSpace(this.type));
    while (true)
    {
      return arrayOfInt;
      if ((this.capability != ContactType.CALL) && (this.capability != ContactType.SMS))
        continue;
      if (this.type.equalsIgnoreCase("work"))
      {
        arrayOfInt = new int[1];
        arrayOfInt[0] = 3;
        continue;
      }
      if (this.type.equalsIgnoreCase("mobile"))
      {
        arrayOfInt = new int[1];
        arrayOfInt[0] = 2;
        continue;
      }
      if (this.type.equalsIgnoreCase("home"))
      {
        arrayOfInt = new int[1];
        arrayOfInt[0] = 1;
        continue;
      }
      if (!this.type.equalsIgnoreCase("other"))
        continue;
      arrayOfInt = new int[1];
      arrayOfInt[0] = 7;
    }
  }

  private int[] getSocialTypes()
  {
    return null;
  }

  private void matchContacts(ContactType paramContactType)
  {
    this.contactMatcher = new ContactMatcher(getListener().getActivityContext(), new LocalContactMatchListener(null), paramContactType, getPhoneTypes(), getEmailTypes(), getSocialTypes(), getAddressTypes(), this.query, 100.0F, null);
  }

  public boolean executeQuery(VLAction paramVLAction)
  {
    int i = 0;
    UserLoggingEngine.getInstance().landingPageViewed("contact");
    this.query = paramVLAction.getParamValue("name");
    if (StringUtils.isNullOrWhiteSpace(this.query));
    while (true)
    {
      return i;
      String str = paramVLAction.getParamValue("capability");
      if (!StringUtils.isNullOrWhiteSpace(str))
        this.capability = ContactType.of(str);
      this.type = paramVLAction.getParamValue("type");
      this.limit = VLActionUtil.getParamInt(paramVLAction, "limit", -1, false);
      this.detailed = VLActionUtil.getParamBool(paramVLAction, "detail", true, false);
      if (VLActionUtil.getParamBool(paramVLAction, "clear.cache", false, false))
        getListener().storeState(DialogDataType.CONTACT_MATCHES, null);
      if (this.query.equals("ANAPHORA"))
      {
        ContactMatch localContactMatch = (ContactMatch)getListener().getState(DialogDataType.SELECTED_CONTACT);
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(localContactMatch);
        getListener().storeState(DialogDataType.CONTACT_MATCHES, localArrayList);
        ContactResolvedEvent localContactResolvedEvent = new ContactResolvedEvent(localArrayList, this.capability, this.detailed, 0, localArrayList.size());
        if (localContactResolvedEvent.getVLDialogEvent() == null)
          continue;
        getListener().sendEvent(localContactResolvedEvent, this.turn);
        continue;
      }
      matchContacts(this.capability);
      i = 1;
    }
  }

  protected void sendEmptyEvent()
  {
    ContactResolvedEvent localContactResolvedEvent = new ContactResolvedEvent();
    getListener().sendEvent(localContactResolvedEvent, this.turn);
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
      ResolveContactHandler.this.getListener().asyncHandlerDone();
    }

    public void onContactMatchingFinished(List<ContactMatch> paramList)
    {
      int i;
      if (paramList != null)
        i = 0;
      while (true)
      {
        try
        {
          Object localObject2 = (List)(List)ResolveContactHandler.this.getListener().getState(DialogDataType.CONTACT_MATCHES);
          if (localObject2 != null)
            continue;
          localObject2 = new ArrayList(paramList.size());
          if (ResolveContactHandler.this.limit > 0)
          {
            localObject3 = DialogDataUtil.getPrunedList(paramList, ResolveContactHandler.this.limit, false);
            ContactResolvedEvent localContactResolvedEvent = new ContactResolvedEvent((List)localObject3, ResolveContactHandler.this.capability, ResolveContactHandler.this.detailed, i, paramList.size());
            if (localContactResolvedEvent.getVLDialogEvent() == null)
              continue;
            ResolveContactHandler.this.getListener().sendEvent(localContactResolvedEvent, ResolveContactHandler.this.turn);
            if ((paramList.size() != 1) || (((!ResolveContactHandler.this.capability.equals(ContactType.EMAIL)) || (((ContactMatch)paramList.get(0)).getEmailData().size() != 1)) && ((!ResolveContactHandler.this.capability.equals(ContactType.SMS)) || (((ContactMatch)paramList.get(0)).getPhoneData().size() != 1))))
              continue;
            int j = 0;
            Iterator localIterator = ((List)localObject2).iterator();
            if (!localIterator.hasNext())
              continue;
            ContactMatch localContactMatch = (ContactMatch)localIterator.next();
            if (!((ContactMatch)paramList.get(0)).getLookupKey().equals(localContactMatch.getLookupKey()))
              continue;
            j = 1;
            if (j != 0)
              continue;
            ((List)localObject2).add(paramList.get(0));
            ResolveContactHandler.this.getListener().storeState(DialogDataType.CONTACT_MATCHES, localObject2);
            return;
            i = ((List)localObject2).size();
            continue;
            ((List)localObject2).addAll(paramList);
            continue;
          }
        }
        finally
        {
          ResolveContactHandler.this.getListener().asyncHandlerDone();
        }
        Object localObject3 = paramList;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.ResolveContactHandler
 * JD-Core Version:    0.6.0
 */