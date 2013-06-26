package com.vlingo.midas.util;

public enum PlayMusicType
{
  static
  {
    SONGLIST = new PlayMusicType("SONGLIST", 8);
    MOOD = new PlayMusicType("MOOD", 9);
    PlayMusicType[] arrayOfPlayMusicType = new PlayMusicType[10];
    arrayOfPlayMusicType[0] = ALBUM;
    arrayOfPlayMusicType[1] = ARTIST;
    arrayOfPlayMusicType[2] = NEXT;
    arrayOfPlayMusicType[3] = PAUSE;
    arrayOfPlayMusicType[4] = PLAY;
    arrayOfPlayMusicType[5] = PLAYLIST;
    arrayOfPlayMusicType[6] = PREVIOUS;
    arrayOfPlayMusicType[7] = TITLE;
    arrayOfPlayMusicType[8] = SONGLIST;
    arrayOfPlayMusicType[9] = MOOD;
    $VALUES = arrayOfPlayMusicType;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.PlayMusicType
 * JD-Core Version:    0.6.0
 */