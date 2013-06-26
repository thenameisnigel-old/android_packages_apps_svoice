package com.vlingo.sdk.internal.lmtt;

import com.vlingo.sdk.internal.util.XmlUtils;

public class LMTTContactItem extends LMTTItem
{
  public String companyName;
  public String firstName;
  public String lastName;

  public LMTTContactItem(int paramInt, LMTTItem.ChangeType paramChangeType)
  {
    this(null, null, null, paramInt, paramChangeType);
  }

  public LMTTContactItem(String paramString1, String paramString2, String paramString3, int paramInt, LMTTItem.ChangeType paramChangeType)
  {
    super(LMTTItem.LmttItemType.TYPE_CONTACT, paramInt, paramChangeType);
    if (paramString1 == null)
      paramString1 = "";
    if (paramString2 == null)
      paramString2 = "";
    if (paramString3 == null)
      paramString3 = "";
    this.firstName = paramString1;
    this.lastName = paramString2;
    this.companyName = paramString3;
  }

  public void getDelXML(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append("<e uid=\"");
    paramStringBuilder.append(this.uid);
    paramStringBuilder.append("\"");
    paramStringBuilder.append(" t=\"d\">");
    paramStringBuilder.append("</e>");
  }

  public void getInsXML(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append("<e uid=\"");
    paramStringBuilder.append(this.uid);
    paramStringBuilder.append("\"");
    paramStringBuilder.append("><fn>");
    XmlUtils.xmlEncode(this.firstName, paramStringBuilder);
    paramStringBuilder.append("</fn><ln>");
    XmlUtils.xmlEncode(this.lastName, paramStringBuilder);
    paramStringBuilder.append("</ln><c>");
    XmlUtils.xmlEncode(this.companyName, paramStringBuilder);
    paramStringBuilder.append("</c>");
    paramStringBuilder.append("</e>");
  }

  public void getUpXML(StringBuilder paramStringBuilder)
  {
    getInsXML(paramStringBuilder);
  }

  public String toString()
  {
    return "LMTTContact: " + this.firstName + " " + this.lastName + " | " + this.companyName + " | uid: " + this.uid + " changeType: " + this.changeType;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.lmtt.LMTTContactItem
 * JD-Core Version:    0.6.0
 */