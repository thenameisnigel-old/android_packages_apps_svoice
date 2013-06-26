package com.vlingo.core.internal.lmtt;

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

  public int hashCode()
  {
    return this.title.hashCode();
  }

  public String toString()
  {
    return "LMTTPlaylistItem | uid: " + this.uid + " | changeType: " + this.changeType + " | title: " + this.title;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.lmtt.LMTTPlaylistItem
 * JD-Core Version:    0.6.0
 */