package com.vlingo.core.internal.localsearch;

import android.util.Pair;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ServerErrorUtil;
import com.vlingo.sdk.internal.http.HttpResponse;
import java.io.ByteArrayInputStream;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class LocalSearchResponseParser
  implements LocalSearchParser
{
  private void addFieldToReview(LocalSearchListing.Review paramReview, Node paramNode)
  {
    String str1 = paramNode.getNodeName();
    String str2 = paramNode.getNodeValue();
    if ((str2 != null) && (str1 != null))
    {
      if (!"Author".equalsIgnoreCase(str1))
        break label40;
      paramReview.author = str2;
    }
    while (true)
    {
      return;
      label40: if ("Body".equalsIgnoreCase(str1))
      {
        paramReview.body = str2;
        continue;
      }
      if ("Date".equalsIgnoreCase(str1))
      {
        paramReview.date = str2;
        continue;
      }
      if ("Id".equalsIgnoreCase(str1))
      {
        paramReview.id = str2;
        continue;
      }
      if ("Rating".equalsIgnoreCase(str1))
      {
        paramReview.rating = str2;
        continue;
      }
      if (!"Title".equalsIgnoreCase(str1))
        continue;
      paramReview.title = str2;
    }
  }

  private void addNodeToBusinessItem(Node paramNode, DefaultLocalSearchListing paramDefaultLocalSearchListing)
  {
    String str1 = paramNode.getNodeName();
    String str2 = paramNode.getNodeValue();
    if ((str2 != null) && (str1 != null))
      paramDefaultLocalSearchListing.putValue(str1, str2);
  }

  private String getAttributeValue(NamedNodeMap paramNamedNodeMap, String paramString)
  {
    Node localNode = paramNamedNodeMap.getNamedItem(paramString);
    if (localNode != null);
    for (String str = localNode.getNodeValue(); ; str = null)
      return str;
  }

  private void parseDetailsNodeList(NodeList paramNodeList, DefaultLocalSearchListing paramDefaultLocalSearchListing)
  {
    if (paramNodeList != null)
    {
      int i = paramNodeList.getLength();
      for (int j = 0; j < i; j++)
      {
        Node localNode = paramNodeList.item(j).getAttributes().getNamedItem("Name");
        if (localNode == null)
          continue;
        String str = localNode.getNodeValue();
        if (str == null)
          continue;
        paramDefaultLocalSearchListing.markValueAsAvailable(str);
      }
    }
  }

  private LocalSearchListing.Review parseReview(Node paramNode)
  {
    LocalSearchListing.Review localReview = new LocalSearchListing.Review();
    NamedNodeMap localNamedNodeMap = paramNode.getAttributes();
    int i = localNamedNodeMap.getLength();
    for (int j = 0; j < i; j++)
      addFieldToReview(localReview, localNamedNodeMap.item(j));
    if (paramNode.hasChildNodes())
    {
      NodeList localNodeList = paramNode.getChildNodes();
      int k = localNodeList.getLength();
      for (int m = 0; m < k; m++)
        addFieldToReview(localReview, localNodeList.item(m));
    }
    return localReview;
  }

  public Pair<LocalSearchRequestInfo, Vector<LocalSearchListing>> parseLocalSearchResponse(HttpResponse paramHttpResponse, LocalSearchListing paramLocalSearchListing)
  {
    Vector localVector = new Vector();
    LocalSearchRequestInfo localLocalSearchRequestInfo = new LocalSearchRequestInfo();
    try
    {
      DocumentBuilder localDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      LocalSearchListingCache localLocalSearchListingCache = ApplicationAdapter.getInstance().getBusinessItemCache();
      Element localElement = localDocumentBuilder.parse(new ByteArrayInputStream(paramHttpResponse.getDataAsBytes())).getDocumentElement();
      NodeList localNodeList1 = localElement.getElementsByTagName("Error");
      if (localNodeList1 != null)
      {
        if (localNodeList1.getLength() > 0)
        {
          localPair = null;
          break label661;
        }
        ServerErrorUtil localServerErrorUtil = new ServerErrorUtil(localElement);
        if (localServerErrorUtil.hasServerError())
        {
          localServerErrorUtil.logIt();
          localPair = null;
          break label661;
        }
      }
      NodeList localNodeList2 = localElement.getElementsByTagName("RequestInfo");
      if ((localNodeList2 != null) && (localNodeList2.getLength() > 0))
      {
        Node localNode4 = localNodeList2.item(0).getFirstChild();
        if (localNode4 != null)
        {
          localLocalSearchRequestInfo.setQuery(getAttributeValue(localNode4.getAttributes(), "Query"));
          if ((localNode4.getFirstChild() != null) && (localNode4.getFirstChild().getFirstChild() != null))
          {
            localLocalSearchRequestInfo.setCity(getAttributeValue(localNode4.getFirstChild().getFirstChild().getAttributes(), "City"));
            localLocalSearchRequestInfo.setState(getAttributeValue(localNode4.getFirstChild().getFirstChild().getAttributes(), "State"));
          }
        }
      }
      NodeList localNodeList3 = localElement.getElementsByTagName("LocalListings");
      int i;
      DefaultLocalSearchListing localDefaultLocalSearchListing;
      int m;
      label416: NodeList localNodeList5;
      NodeList localNodeList6;
      int n;
      if ((localNodeList3 != null) && (localNodeList3.getLength() > 0))
      {
        NodeList localNodeList4 = localNodeList3.item(0).getChildNodes();
        i = 0;
        if (i < localNodeList4.getLength())
        {
          Node localNode1 = localNodeList4.item(i);
          NamedNodeMap localNamedNodeMap = localNode1.getAttributes();
          String str1 = getAttributeValue(localNamedNodeMap, "ListingID");
          int j = 0;
          localDefaultLocalSearchListing = null;
          if ((paramLocalSearchListing != null) && (paramLocalSearchListing.getListingID().equals(str1)))
          {
            localDefaultLocalSearchListing = (DefaultLocalSearchListing)paramLocalSearchListing;
            if (localDefaultLocalSearchListing.isOrganic())
              j = 1;
          }
          if (localDefaultLocalSearchListing == null)
            localDefaultLocalSearchListing = new DefaultLocalSearchListing(str1);
          int k = localNamedNodeMap.getLength();
          m = 0;
          if (m < k)
          {
            Node localNode3 = localNamedNodeMap.item(m);
            if ((j != 0) && ("Type".equalsIgnoreCase(localNode3.getNodeName())))
              break label664;
            addNodeToBusinessItem(localNode3, localDefaultLocalSearchListing);
            break label664;
          }
          localNodeList5 = localNode1.getChildNodes();
          localNodeList6 = null;
          n = localNodeList5.getLength();
        }
      }
      for (int i1 = 0; ; i1++)
        if (i1 < n)
        {
          Node localNode2 = localNodeList5.item(i1);
          String str2 = localNode2.getNodeName();
          if ("details".equalsIgnoreCase(str2))
          {
            localNodeList6 = localNode2.getChildNodes();
          }
          else
          {
            if ("reviews".equalsIgnoreCase(str2))
            {
              NodeList localNodeList7 = localNode2.getChildNodes();
              int i2 = localNodeList7.getLength();
              for (int i3 = 0; i3 < i2; i3++)
                localDefaultLocalSearchListing.addReview(parseReview(localNodeList7.item(i3)));
            }
            if (localNode2.hasChildNodes())
              continue;
            addNodeToBusinessItem(localNode2, localDefaultLocalSearchListing);
          }
        }
        else
        {
          parseDetailsNodeList(localNodeList6, localDefaultLocalSearchListing);
          localVector.add(localDefaultLocalSearchListing);
          localLocalSearchListingCache.add(localDefaultLocalSearchListing);
          i++;
          break;
          localPair = new Pair(localLocalSearchRequestInfo, localVector);
          label661: return localPair;
          label664: m++;
          break label416;
        }
    }
    catch (Exception localException)
    {
      while (true)
        Pair localPair = null;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.localsearch.LocalSearchResponseParser
 * JD-Core Version:    0.6.0
 */