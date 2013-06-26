package com.vlingo.sdk.internal.lmtt;

import com.vlingo.sdk.internal.util.XmlUtils;

public class LMTTPlaylistItem extends LMTTItem
{
  public String title;

  public LMTTPlaylistItem(int paramInt, LMTTItem.ChangeType paramChangeType)
  {
    this(null, paramInt, paramChangeType);
  }

  public LMTTPlaylistItem(String paramString, int paramInt, LMTTItem.ChangeType paramChangeType)
  {
    super(LMTTItem.LmttItemType.TYPE_PLAYLIST, paramInt, paramChangeType);
    if (paramString == null)
      paramString = "";
    this.title = paramString;
  }

  public void getDelXML(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append("<PD");
    paramStringBuilder.append(" uid=\"");
    paramStringBuilder.append(this.uid);
    paramStringBuilder.append("\"/>");
  }

  public void getInsXML(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append("<PU");
    paramStringBuilder.append(" uid=\"");
    paramStringBuilder.append(this.uid);
    paramStringBuilder.append("\"");
    paramStringBuilder.append(" ttl=\"");
    XmlUtils.xmlEncode(this.title, paramStringBuilder);
    paramStringBuilder.append("\"");
    paramStringBuilder.append("/>");
  }

  public void getUpXML(StringBuilder paramStringBuilder)
  {
    getInsXML(paramStringBuilder);
  }

  public String toString()
  {
    return "LMTTPlaylistItem | uid: " + this.uid + " | changeType: " + this.changeType + " | title: " + this.title;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.lmtt.LMTTPlaylistItem
 * JD-Core Version:    0.6.0
 */