package com.vlingo.core.internal.util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ServerErrorUtil
{
  private final String CHILD_ELEMENT_CODE = "Code";
  private final String CHILD_ELEMENT_DETAILS = "Details";
  private final String CHILD_ELEMENT_MESSAGE = "Message";
  private final String ROOT_ELEMENT_ERROR = "Error";
  private NodeList errorNodes = null;
  private Element root;

  public ServerErrorUtil(Element paramElement)
  {
    this.root = paramElement;
  }

  public String getCode()
  {
    NodeList localNodeList = this.root.getElementsByTagName("Code");
    if (localNodeList.getLength() > 0);
    for (String str = localNodeList.item(0).getTextContent(); ; str = null)
      return str;
  }

  public boolean hasServerError()
  {
    int i = 1;
    if (this.errorNodes == null)
      this.errorNodes = this.root.getElementsByTagName("Error");
    if (this.errorNodes != null)
      if (this.errorNodes.getLength() <= 0);
    while (true)
    {
      return i;
      int j;
      label66: int k;
      if (this.root.getElementsByTagName("Code").getLength() > 0)
      {
        j = i;
        if (this.root.getElementsByTagName("Message").getLength() <= 0)
          break label132;
        k = i;
        label87: if (this.root.getElementsByTagName("Details").getLength() <= 0)
          break label137;
      }
      label132: label137: for (int m = i; ; m = 0)
      {
        if ((j != 0) && (k != 0) && (m != 0))
          break label141;
        i = 0;
        break;
        j = 0;
        break label66;
        k = 0;
        break label87;
      }
      label141: continue;
      i = 0;
    }
  }

  public void logIt()
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.ServerErrorUtil
 * JD-Core Version:    0.6.0
 */