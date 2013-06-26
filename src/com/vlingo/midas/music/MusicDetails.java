package com.vlingo.midas.music;

import android.net.Uri;

public class MusicDetails
{
  private final Uri albumArtUri;
  private final String artistName;
  private final Long songId;
  private final String songTitle;

  public MusicDetails(Long paramLong, String paramString1, String paramString2, Uri paramUri)
  {
    this.songId = paramLong;
    this.songTitle = paramString1;
    this.artistName = paramString2;
    this.albumArtUri = paramUri;
  }

  public String getArtist()
  {
    return this.artistName;
  }

  public Uri getMusicThumbnail()
  {
    return this.albumArtUri;
  }

  public Long getSongId()
  {
    return this.songId;
  }

  public String getTitle()
  {
    return this.songTitle;
  }

  public String toString()
  {
    return "Song ID : " + this.songId + ", Title : " + this.songTitle + ", Artist : " + this.artistName + ", Image Url : " + this.albumArtUri;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.music.MusicDetails
 * JD-Core Version:    0.6.0
 */