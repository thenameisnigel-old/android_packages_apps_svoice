package com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve;

import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.events.ChoiceSelectedEvent;
import com.vlingo.core.internal.dialogmanager.util.DialogDataUtil;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.DialUtil;
import com.vlingo.sdk.recognition.VLAction;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

public class ShowContactTypeChoicesHandler extends VVSActionHandler
  implements WidgetActionListener
{
  List<String> choiceIds;
  ContactMatch contactMatch;
  List<ContactData> mappedContacts;
  List<ContactData> matchContactData;

  private ArrayList<ContactData> matchData(ArrayList<ContactData> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      Iterator localIterator = this.choiceIds.iterator();
      while (localIterator.hasNext())
        localArrayList.add(paramArrayList.get(Integer.parseInt(com.vlingo.core.internal.util.StringUtils.split((String)localIterator.next(), '.')[1])));
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new InvalidParameterException("Expected Contact Data contactData to be of form <contact_id>.<address_id>: " + paramArrayList);
    }
    return localArrayList;
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    VLActionUtil.getParamString(paramVLAction, "choices", true);
    UserLoggingEngine.getInstance().landingPageViewed("contact");
    try
    {
      this.choiceIds = DialogDataUtil.getChoicesAsStrings(paramVLAction);
      if ((this.choiceIds == null) || (this.choiceIds.size() == 0))
        return false;
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        continue;
        int i = DialogDataUtil.splitAddressFromContactId((String)this.choiceIds.get(0))[0];
        this.contactMatch = DialogDataUtil.getContactMatchFromId((List)paramVVSActionHandlerListener.getState(DialogDataType.CONTACT_MATCHES), i);
        this.matchContactData = matchData(new ArrayList(this.contactMatch.getAllData()));
        Map localMap = null;
        if (!this.matchContactData.isEmpty())
          localMap = DialUtil.getPhoneTypeMap(getListener().getActivityContext().getResources(), this.matchContactData, "call");
        paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.ShowContactTypeChoices, null, localMap, this);
        this.mappedContacts = new ArrayList(localMap.values());
      }
    }
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    int j;
    if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Choice"))
    {
      int i = VLActionUtil.extractParamInt(paramIntent, "choice", -1);
      if (i == -1)
        throw new InvalidParameterException("Expected positive BUNDLE_CHOICE index for ShowContactTypeChoice");
      ContactData localContactData = (ContactData)this.mappedContacts.get(i);
      j = this.matchContactData.indexOf(localContactData);
      if (j != -1);
    }
    while (true)
    {
      return;
      ChoiceSelectedEvent localChoiceSelectedEvent = new ChoiceSelectedEvent((String)this.choiceIds.get(j) + "");
      getListener().sendEvent(localChoiceSelectedEvent, this.turn);
      continue;
      if ("com.vlingo.core.internal.dialogmanager.DataTransfered".equals(paramIntent.getAction()))
        continue;
      throwUnknownActionException(paramIntent.getAction());
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.ShowContactTypeChoicesHandler
 * JD-Core Version:    0.6.0
 */