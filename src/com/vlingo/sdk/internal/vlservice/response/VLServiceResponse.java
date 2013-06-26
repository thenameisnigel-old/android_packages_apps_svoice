package com.vlingo.sdk.internal.vlservice.response;

import java.util.Vector;

public class VLServiceResponse
{
  protected ActionList actionList;
  private String dialogGUID;
  protected byte[] dialogState;
  private int dialogTurn = -1;
  protected boolean isError = false;
  protected Vector<ServerMessage> messages = new Vector();

  public static VLServiceResponse createFromXml(String paramString)
  {
    return new VLResponseParser().parseResponseXml(paramString);
  }

  public void addAction(Action paramAction)
  {
    if (this.actionList == null)
      this.actionList = new ActionList();
    this.actionList.addElement(paramAction);
  }

  public void addMessage(ServerMessage paramServerMessage)
  {
    this.messages.addElement(paramServerMessage);
  }

  public ActionList getActionList()
  {
    return this.actionList;
  }

  public String getDialogGuid()
  {
    return this.dialogGUID;
  }

  public byte[] getDialogState()
  {
    return this.dialogState;
  }

  public int getDialogTurn()
  {
    return this.dialogTurn;
  }

  public ServerMessage getFirstMessage()
  {
    if (this.messages.size() > 0);
    for (ServerMessage localServerMessage = (ServerMessage)this.messages.elementAt(0); ; localServerMessage = null)
      return localServerMessage;
  }

  public Vector<ServerMessage> getMessages()
  {
    return this.messages;
  }

  public boolean hasActions()
  {
    if ((this.actionList != null) && (this.actionList.size() > 0));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean hasDialogState()
  {
    if ((this.dialogState != null) && (this.dialogState.length > 0));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean hasMessageOfType(int paramInt)
  {
    int j;
    if (this.messages.size() > 0)
    {
      j = 0;
      if (j < this.messages.size())
        if (((ServerMessage)this.messages.elementAt(j)).getType() != paramInt);
    }
    for (int i = 1; ; i = 0)
    {
      return i;
      j++;
      break;
    }
  }

  public boolean hasMessages()
  {
    if (this.messages.size() > 0);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean hasWarnings()
  {
    return hasMessageOfType(2);
  }

  public boolean isError()
  {
    return hasMessageOfType(3);
  }

  public void setActionList(ActionList paramActionList)
  {
    this.actionList = paramActionList;
  }

  public void setDialogGuid(String paramString)
  {
    this.dialogGUID = paramString;
  }

  public void setDialogState(byte[] paramArrayOfByte)
  {
    this.dialogState = paramArrayOfByte;
  }

  public void setDialogTurn(int paramInt)
  {
    this.dialogTurn = paramInt;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (getFirstMessage() != null)
      for (int i = 0; i < this.messages.size(); i++)
      {
        localStringBuffer.append(((ServerMessage)this.messages.elementAt(i)).toString());
        localStringBuffer.append("\n");
      }
    localStringBuffer.append("<no message>");
    localStringBuffer.append("\n");
    if (this.actionList != null)
      localStringBuffer.append(this.actionList.toString());
    while (true)
    {
      return localStringBuffer.toString();
      localStringBuffer.append("<no actions>");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.vlservice.response.VLServiceResponse
 * JD-Core Version:    0.6.0
 */