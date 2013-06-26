package com.vlingo.sdk.internal.vlservice.response;

import com.vlingo.sdk.internal.util.Base64;
import com.vlingo.sdk.internal.util.Base64DecoderException;
import com.vlingo.sdk.internal.xml.XmlAttributes;

public class DialogParser extends VLResponseSectionParser
{
  private final int XML_ATTR_GUID;
  private final int XML_ATTR_TURN;
  private final int XML_ELEMENT_DIALOGSTATE;
  private final int XML_ELEMENT_VVS;
  int currentParameterValueStartPos;
  char[] origXML = null;

  public DialogParser(VLResponseParser paramVLResponseParser)
  {
    super(paramVLResponseParser);
    this.XML_ELEMENT_VVS = paramVLResponseParser.registerElement("VV");
    this.XML_ELEMENT_DIALOGSTATE = paramVLResponseParser.registerElement("DialogState");
    this.XML_ATTR_GUID = paramVLResponseParser.registerAttribute("dialog-guid");
    this.XML_ATTR_TURN = paramVLResponseParser.registerAttribute("turn");
  }

  public void beginElement(int paramInt1, XmlAttributes paramXmlAttributes, char[] paramArrayOfChar, int paramInt2)
  {
    if (this.XML_ELEMENT_VVS == paramInt1)
    {
      if (paramXmlAttributes != null)
      {
        String str1 = paramXmlAttributes.lookup(this.XML_ATTR_GUID);
        String str2 = paramXmlAttributes.lookup(this.XML_ATTR_TURN);
        int i = -1;
        if ((str2 != null) && (str2.length() > 0))
          i = Integer.parseInt(str2);
        this.responseParser.getResponse().setDialogGuid(str1);
        this.responseParser.getResponse().setDialogTurn(i);
      }
      this.responseParser.onSectionComplete();
    }
    while (true)
    {
      return;
      if (this.XML_ELEMENT_DIALOGSTATE == paramInt1)
      {
        try
        {
          byte[] arrayOfByte = Base64.decode(new String(paramArrayOfChar));
          this.responseParser.getResponse().setDialogState(arrayOfByte);
        }
        catch (Base64DecoderException localBase64DecoderException)
        {
          localBase64DecoderException.printStackTrace();
        }
        continue;
      }
    }
  }

  public void endElement(int paramInt1, int paramInt2)
  {
    if (this.XML_ELEMENT_DIALOGSTATE == paramInt1)
      this.responseParser.onSectionComplete();
  }

  public boolean handlesElement(int paramInt)
  {
    if ((this.XML_ELEMENT_VVS == paramInt) || (this.XML_ELEMENT_DIALOGSTATE == paramInt));
    for (int i = 1; ; i = 0)
      return i;
  }

  public void onParseBegin(char[] paramArrayOfChar)
  {
    this.origXML = paramArrayOfChar;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.vlservice.response.DialogParser
 * JD-Core Version:    0.6.0
 */