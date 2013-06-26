package com.vlingo.sdk.internal.vlservice.response;

import com.vlingo.sdk.internal.recognizer.results.SRRecognitionResponse;
import com.vlingo.sdk.internal.recognizer.results.TaggedResults;
import com.vlingo.sdk.internal.xml.XmlAttributes;
import com.vlingo.sdk.internal.xml.XmlParser;

public class ActionParser extends VLResponseSectionParser
{
  private final int XML_ATTR_ELSE;
  private final int XML_ATTR_IF;
  private final int XML_ATTR_N;
  private final int XML_ATTR_V;
  private final int XML_ELEMENT_ACTION;
  private final int XML_ELEMENT_ACTIONLIST;
  private final int XML_ELEMENT_PARAM;
  ActionList actionList = null;
  Action currentAction = null;
  String currentParameterName;
  String currentParameterValue;
  int currentParameterValueStartPos;
  char[] origXML = null;
  TaggedResults taggedResults = null;

  public ActionParser(VLResponseParser paramVLResponseParser)
  {
    super(paramVLResponseParser);
    this.XML_ELEMENT_ACTIONLIST = paramVLResponseParser.registerElement("ActionList");
    this.XML_ELEMENT_ACTION = paramVLResponseParser.registerElement("Action");
    this.XML_ELEMENT_PARAM = paramVLResponseParser.registerElement("Param");
    this.XML_ATTR_N = paramVLResponseParser.registerAttribute("n");
    this.XML_ATTR_V = paramVLResponseParser.registerAttribute("v");
    this.XML_ATTR_IF = paramVLResponseParser.registerAttribute("if");
    this.XML_ATTR_ELSE = paramVLResponseParser.registerAttribute("else");
  }

  public void beginElement(int paramInt1, XmlAttributes paramXmlAttributes, char[] paramArrayOfChar, int paramInt2)
  {
    if (this.XML_ELEMENT_ACTIONLIST == paramInt1)
    {
      this.actionList = new ActionList();
      VLServiceResponse localVLServiceResponse = this.responseParser.getResponse();
      if ((localVLServiceResponse instanceof SRRecognitionResponse))
        this.taggedResults = ((SRRecognitionResponse)localVLServiceResponse).getResults();
    }
    while (true)
    {
      return;
      this.taggedResults = null;
      continue;
      if (this.XML_ELEMENT_ACTION == paramInt1)
      {
        this.currentAction = new Action(paramXmlAttributes.lookup(this.XML_ATTR_N));
        this.currentAction.ifCondition = paramXmlAttributes.lookup(this.XML_ATTR_IF);
        this.currentAction.elseStatement = paramXmlAttributes.lookup(this.XML_ATTR_ELSE);
        continue;
      }
      if (this.XML_ELEMENT_PARAM != paramInt1)
        continue;
      String str1 = paramXmlAttributes.lookup(this.XML_ATTR_N);
      String str2 = paramXmlAttributes.lookup(this.XML_ATTR_V);
      this.currentParameterName = str1;
      this.currentParameterValue = str2;
      this.currentParameterValueStartPos = paramInt2;
    }
  }

  public void endElement(int paramInt1, int paramInt2)
  {
    if (this.XML_ELEMENT_PARAM == paramInt1)
    {
      String str = this.currentParameterValue;
      if (str == null)
      {
        int i = paramInt2;
        for (int j = this.currentParameterValueStartPos; (this.origXML[j] == ' ') || (this.origXML[j] == '\t') || (this.origXML[j] == '\r') || (this.origXML[j] == '\n'); j++);
        while ((this.origXML[(i - 1)] == ' ') || (this.origXML[(i - 1)] == '\t') || (this.origXML[(i - 1)] == '\r') || (this.origXML[(i - 1)] == '\n'))
          i--;
        int k = i - j;
        str = XmlParser.createString(this.origXML, j, k, false);
      }
      this.currentAction.addParameter(this.currentParameterName, str, this.taggedResults);
    }
    while (true)
    {
      return;
      if (this.XML_ELEMENT_ACTIONLIST == paramInt1)
      {
        this.responseParser.getResponse().setActionList(this.actionList);
        this.responseParser.onSectionComplete();
        continue;
      }
      if (this.XML_ELEMENT_ACTION != paramInt1)
        continue;
      this.actionList.addElement(this.currentAction);
    }
  }

  public boolean handlesElement(int paramInt)
  {
    if (this.XML_ELEMENT_ACTIONLIST == paramInt);
    for (int i = 1; ; i = 0)
      return i;
  }

  public void onParseBegin(char[] paramArrayOfChar)
  {
    this.origXML = paramArrayOfChar;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.vlservice.response.ActionParser
 * JD-Core Version:    0.6.0
 */