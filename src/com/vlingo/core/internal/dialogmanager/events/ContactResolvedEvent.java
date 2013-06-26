package com.vlingo.core.internal.dialogmanager.events;

import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.contacts.ContactType;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent.Builder;
import com.vlingo.sdk.recognition.dialog.VLDialogEventFieldGroup.Builder;
import java.util.Iterator;
import java.util.List;

public class ContactResolvedEvent extends CountQueryEvent<ContactMatch>
{
  private final String NAME = "contact-resolved";
  private final boolean detailed;
  private final ContactType type;

  public ContactResolvedEvent()
  {
    this.type = ContactType.UNDEFINED;
    this.detailed = false;
  }

  public ContactResolvedEvent(List<ContactMatch> paramList, ContactType paramContactType, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    super(paramList, paramInt1, paramInt2);
    this.type = paramContactType;
    this.detailed = paramBoolean;
  }

  private String getTypeString(ContactData paramContactData)
  {
    String str = "";
    switch (paramContactData.type)
    {
    case 4:
    case 5:
    case 6:
    default:
    case 2:
    case 1:
    case 7:
    case 3:
    }
    while (true)
    {
      return str;
      str = "mobile";
      continue;
      str = "home";
      continue;
      str = "other";
      continue;
      str = "work";
    }
  }

  public String getName()
  {
    return "contact-resolved";
  }

  public VLDialogEvent getVLDialogEvent()
  {
    VLDialogEvent localVLDialogEvent;
    if (getItems() == null)
    {
      localVLDialogEvent = null;
      return localVLDialogEvent;
    }
    int i = 0;
    VLDialogEvent.Builder localBuilder = new VLDialogEvent.Builder("contact-resolved");
    VLDialogEventFieldGroup.Builder localBuilder1 = new VLDialogEventFieldGroup.Builder("contacts");
    int j = getOffset();
    Iterator localIterator1 = getItems().iterator();
    while (localIterator1.hasNext())
    {
      ContactMatch localContactMatch = (ContactMatch)localIterator1.next();
      List localList = localContactMatch.getData(this.type);
      if (localList == null)
        continue;
      VLDialogEventFieldGroup.Builder localBuilder2 = new VLDialogEventFieldGroup.Builder("contact");
      localBuilder2.eventField("name", localContactMatch.primaryDisplayName);
      String str = String.valueOf(j);
      localBuilder2.eventField("id", str);
      j++;
      localBuilder2.eventField("score", String.valueOf(localContactMatch.getScore(true)));
      VLDialogEventFieldGroup.Builder localBuilder3 = new VLDialogEventFieldGroup.Builder("addresses");
      int k = 0;
      Iterator localIterator2 = localList.iterator();
      while (true)
      {
        if (localIterator2.hasNext())
        {
          ContactData localContactData = (ContactData)localIterator2.next();
          VLDialogEventFieldGroup.Builder localBuilder4 = new VLDialogEventFieldGroup.Builder("address");
          if (this.detailed)
            localBuilder4.eventField("detail", localContactData.address);
          localBuilder4.eventField("type", getTypeString(localContactData));
          localBuilder4.eventField("id", str + "." + String.valueOf(k));
          k++;
          localBuilder3.eventFieldGroup(localBuilder4.build());
          if ((Settings.getBoolean("use_default_phone", true)) && (localContactData.isDefault > 0))
          {
            localBuilder3 = new VLDialogEventFieldGroup.Builder("addresses");
            localBuilder3.eventFieldGroup(localBuilder4.build());
          }
        }
        else
        {
          localBuilder2.eventFieldGroup(localBuilder3.build());
          localBuilder1.eventFieldGroup(localBuilder2.build());
          break;
        }
        i++;
      }
    }
    if (i == 0)
      writeTotalCount(localBuilder, 0);
    while (true)
    {
      localBuilder.eventFieldGroup(localBuilder1.build());
      localVLDialogEvent = localBuilder.build();
      break;
      writeTotalCount(localBuilder);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.events.ContactResolvedEvent
 * JD-Core Version:    0.6.0
 */