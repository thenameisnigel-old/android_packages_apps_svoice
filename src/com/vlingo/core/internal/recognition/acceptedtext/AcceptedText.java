package com.vlingo.core.internal.recognition.acceptedtext;

public abstract class AcceptedText
{
  protected String GUttId;
  protected String dialogGuid;
  protected int dialogTurn;

  public AcceptedText(String paramString)
  {
    this.dialogGuid = null;
    this.dialogTurn = -1;
    this.GUttId = paramString;
  }

  public AcceptedText(String paramString1, int paramInt, String paramString2)
  {
    this.dialogGuid = paramString1;
    this.dialogTurn = paramInt;
    this.GUttId = paramString2;
  }

  protected abstract String getAcceptedTextXML();

  public String getDialogGuid()
  {
    return this.dialogGuid;
  }

  public int getDialogTurn()
  {
    return this.dialogTurn;
  }

  public String getDialogXML()
  {
    if ((this.dialogGuid == null) && (this.dialogTurn < 0));
    StringBuffer localStringBuffer;
    for (String str = ""; ; str = localStringBuffer.toString())
    {
      return str;
      localStringBuffer = new StringBuffer("<Dialog ");
      if (this.dialogGuid != null)
        localStringBuffer.append(" dialogGuid=\"").append(this.dialogGuid).append("\" ");
      if (this.dialogTurn > -1)
        localStringBuffer.append(" dialogTurn=\"").append(this.dialogTurn).append("\" ");
      localStringBuffer.append(" />");
    }
  }

  public String getGUttId()
  {
    return this.GUttId;
  }

  public String getXMLString()
  {
    StringBuffer localStringBuffer = new StringBuffer(getAcceptedTextXML());
    localStringBuffer.append(getDialogXML());
    return localStringBuffer.toString();
  }

  public void setDialogGuid(String paramString)
  {
    this.dialogGuid = paramString;
  }

  public void setDialogTurn(int paramInt)
  {
    this.dialogTurn = paramInt;
  }

  public void setGUttId(String paramString)
  {
    this.GUttId = paramString;
  }

  public String toString()
  {
    return "AcceptedText [dialogGuid=" + this.dialogGuid + ", dialogTurn=" + this.dialogTurn + ", GUttId=" + this.GUttId + "]";
  }

  public static enum ActionType
  {
    static
    {
      MAP = new ActionType("MAP", 2);
      RESERVE = new ActionType("RESERVE", 3);
      WEB = new ActionType("WEB", 4);
      ActionType[] arrayOfActionType = new ActionType[5];
      arrayOfActionType[0] = CALL;
      arrayOfActionType[1] = NAV;
      arrayOfActionType[2] = MAP;
      arrayOfActionType[3] = RESERVE;
      arrayOfActionType[4] = WEB;
      $VALUES = arrayOfActionType;
    }
  }

  public static enum TextType
  {
    static
    {
      MEMO = new TextType("MEMO", 1);
      DIAL = new TextType("DIAL", 2);
      TextType[] arrayOfTextType = new TextType[3];
      arrayOfTextType[0] = SMS;
      arrayOfTextType[1] = MEMO;
      arrayOfTextType[2] = DIAL;
      $VALUES = arrayOfTextType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.acceptedtext.AcceptedText
 * JD-Core Version:    0.6.0
 */