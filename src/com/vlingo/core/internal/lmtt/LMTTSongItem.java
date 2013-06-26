package com.vlingo.core.internal.lmtt;

class LMTTSongItem extends LMTTItem
{
  String album;
  String artist;
  String composer;
  String folder;
  String genre;
  String title;
  int year;

  LMTTSongItem(int paramInt, LMTTItem.ChangeType paramChangeType)
  {
    this(null, null, null, null, null, -1, null, paramInt, paramChangeType);
  }

  LMTTSongItem(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt1, String paramString6, int paramInt2, LMTTItem.ChangeType paramChangeType)
  {
    super(LMTTItem.LmttItemType.TYPE_SONG, paramInt2, paramChangeType);
    if (paramString1 == null)
      paramString1 = "";
    this.title = paramString1;
    this.artist = paramString2;
    this.composer = paramString3;
    this.album = paramString4;
    this.genre = paramString5;
    this.year = paramInt1;
    this.folder = paramString6;
  }

  public int hashCode()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.title).append(this.artist).append(this.composer).append(this.album).append(this.genre).append(this.year).append(this.folder);
    return localStringBuilder.toString().hashCode();
  }

  public String toString()
  {
    return "LMTTSongItem | uid: " + this.uid + " | changeType: " + this.changeType + " | title: " + this.title;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.lmtt.LMTTSongItem
 * JD-Core Version:    0.6.0
 */