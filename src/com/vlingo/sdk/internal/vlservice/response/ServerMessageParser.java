package com.vlingo.sdk.internal.vlservice.response;

import com.vlingo.sdk.internal.xml.XmlAttributes;

public class ServerMessageParser extends VLResponseSectionParser
{
  private final int XML_ELEMENT_CODE;
  private final int XML_ELEMENT_DETAILS;
  private final int XML_ELEMENT_ERROR;
  private final int XML_ELEMENT_MESSAGE;
  private final int XML_ELEMENT_STATUS;
  private final int XML_ELEMENT_WARNING;
  private boolean inDetails;
  private ServerMessage msg;

  public ServerMessageParser(VLResponseParser paramVLResponseParser)
  {
    super(paramVLResponseParser);
    this.XML_ELEMENT_STATUS = paramVLResponseParser.registerElement("Status");
    this.XML_ELEMENT_WARNING = paramVLResponseParser.registerElement("Warning");
    this.XML_ELEMENT_ERROR = paramVLResponseParser.registerElement("Error");
    this.XML_ELEMENT_MESSAGE = paramVLResponseParser.registerElement("Message");
    this.XML_ELEMENT_CODE = paramVLResponseParser.registerElement("Code");
    this.XML_ELEMENT_DETAILS = paramVLResponseParser.registerElement("Details");
  }

  public void beginElement(int paramInt1, XmlAttributes paramXmlAttributes, char[] paramArrayOfChar, int paramInt2)
  {
    String str = null;
    if (paramArrayOfChar != null)
      str = String.valueOf(paramArrayOfChar);
    if (this.XML_ELEMENT_STATUS == paramInt1)
    {
      this.msg = new ServerMessage();
      this.msg.setType(1);
    }
    while (true)
    {
      return;
      if (this.XML_ELEMENT_WARNING == paramInt1)
      {
        this.msg = new ServerMessage();
        this.msg.setType(2);
        continue;
      }
      if (this.XML_ELEMENT_ERROR == paramInt1)
      {
        this.msg = new ServerMessage();
        this.msg.setType(3);
        continue;
      }
      if (this.XML_ELEMENT_MESSAGE == paramInt1)
      {
        if (this.inDetails)
        {
          this.msg.setDetailMessage(str);
          continue;
        }
        this.msg.setMessage(str);
        continue;
      }
      if (this.XML_ELEMENT_CODE == paramInt1)
      {
        if (this.inDetails)
        {
          this.msg.setDetailCode(str);
          continue;
        }
        this.msg.setCode(str);
        continue;
      }
      if (this.XML_ELEMENT_DETAILS != paramInt1)
        continue;
      this.inDetails = true;
    }
  }

  public void endElement(int paramInt1, int paramInt2)
  {
    if ((this.XML_ELEMENT_STATUS == paramInt1) || (this.XML_ELEMENT_WARNING == paramInt1) || (this.XML_ELEMENT_ERROR == paramInt1))
    {
      this.responseParser.getResponse().addMessage(this.msg);
      this.responseParser.onSectionComplete();
    }
    while (true)
    {
      return;
      if ((this.inDetails) && (this.XML_ELEMENT_DETAILS == paramInt1))
      {
        this.inDetails = false;
        continue;
      }
    }
  }

  public boolean handlesElement(int paramInt)
  {
    if ((this.XML_ELEMENT_STATUS == paramInt) || (this.XML_ELEMENT_WARNING == paramInt) || (this.XML_ELEMENT_ERROR == paramInt));
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.vlservice.response.ServerMessageParser
 * JD-Core Version:    0.6.0
 */