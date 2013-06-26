package com.vlingo.core.internal.dialogmanager.actions;

import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.SendMessageInterface;
import com.vlingo.core.internal.util.SMSUtil;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SendMessageAction extends DMAction
  implements SendMessageInterface
{
  private List<String> addresses = new LinkedList();
  private String message;

  private String errString(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    case 3:
    default:
      str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_unknown);
    case 4:
    case 2:
    }
    while (true)
    {
      return str;
      str = "no-service";
      continue;
      str = "radio-off";
    }
  }

  private void sendSMS(String paramString)
  {
    SMSUtil.sendSMS(getContext(), paramString, this.message, new SendMessageAction.1(this));
  }

  public SendMessageAction address(String paramString)
  {
    this.addresses.add(paramString);
    return this;
  }

  public SendMessageAction addresses(List<String> paramList)
  {
    this.addresses.addAll(paramList);
    return this;
  }

  protected void execute()
  {
    if (this.addresses != null)
    {
      Iterator localIterator = this.addresses.iterator();
      while (localIterator.hasNext())
        sendSMS((String)localIterator.next());
    }
    getListener().actionFail("No address");
  }

  public SendMessageAction message(String paramString)
  {
    this.message = paramString;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.SendMessageAction
 * JD-Core Version:    0.6.0
 */