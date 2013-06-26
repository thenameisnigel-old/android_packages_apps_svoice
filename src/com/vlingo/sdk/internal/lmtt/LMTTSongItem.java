package com.vlingo.sdk.internal.lmtt;

import com.vlingo.sdk.internal.util.XmlUtils;

public class LMTTSongItem extends LMTTItem
{
  public String album;
  public String artist;
  public String composer;
  public String folder;
  public String genre;
  public String title;
  public String year;

  public LMTTSongItem(int paramInt, LMTTItem.ChangeType paramChangeType)
  {
    this(null, null, null, null, null, -1, null, paramInt, paramChangeType);
  }

  public LMTTSongItem(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt1, String paramString6, int paramInt2, LMTTItem.ChangeType paramChangeType)
  {
    super(LMTTItem.LmttItemType.TYPE_SONG, paramInt2, paramChangeType);
    if (paramString1 == null)
      paramString1 = "";
    this.title = paramString1;
    this.artist = paramString2;
    this.composer = paramString3;
    this.album = paramString4;
    this.genre = paramString5;
    if (paramInt1 > 0)
      this.year = String.valueOf(paramInt1);
    this.folder = paramString6;
  }

  public void getDelXML(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append("<SD");
    paramStringBuilder.append(" uid=\"");
    paramStringBuilder.append(this.uid);
    paramStringBuilder.append("\"/>");
  }

  public void getInsXML(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append("<SU");
    paramStringBuilder.append(" uid=\"");
    paramStringBuilder.append(this.uid);
    paramStringBuilder.append("\"");
    paramStringBuilder.append(" ttl=\"");
    XmlUtils.xmlEncode(this.title, paramStringBuilder);
    paramStringBuilder.append("\"");
    if (this.artist != null)
    {
      paramStringBuilder.append(" art=\"");
      XmlUtils.xmlEncode(this.artist, paramStringBuilder);
      paramStringBuilder.append("\"");
    }
    if (this.composer != null)
    {
      paramStringBuilder.append(" cmp=\"");
      XmlUtils.xmlEncode(this.composer, paramStringBuilder);
      paramStringBuilder.append('"');
    }
    if (this.album != null)
    {
      paramStringBuilder.append(" alb=\"");
      XmlUtils.xmlEncode(this.album, paramStringBuilder);
      paramStringBuilder.append('"');
    }
    if (this.genre != null)
    {
      paramStringBuilder.append(" gen=\"");
      XmlUtils.xmlEncode(this.genre, paramStringBuilder);
      paramStringBuilder.append('"');
    }
    if (this.year != null)
    {
      paramStringBuilder.append(" yr=\"");
      XmlUtils.xmlEncode(this.year, paramStringBuilder);
      paramStringBuilder.append('"');
    }
    if (this.folder != null)
    {
      paramStringBuilder.append(" fld=\"");
      XmlUtils.xmlEncode(this.folder, paramStringBuilder);
      paramStringBuilder.append('"');
    }
    paramStringBuilder.append("/>");
  }

  public void getUpXML(StringBuilder paramStringBuilder)
  {
    getInsXML(paramStringBuilder);
  }

  public String toString()
  {
    return "LMTTSongItem | uid: " + this.uid + " | changeType: " + this.changeType + " | title: " + this.title;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.lmtt.LMTTSongItem
 * JD-Core Version:    0.6.0
 */