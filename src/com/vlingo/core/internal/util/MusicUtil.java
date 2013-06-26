package com.vlingo.core.internal.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.Albums;
import android.provider.MediaStore.Audio.Artists;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Audio.Playlists;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class MusicUtil
{
  private static final Uri ALBUM_URI;
  private static final Uri ARTIST_URI;
  private static final Uri GENERAL_URI;
  private static final Uri PLAYLIST_URI = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;

  static
  {
    GENERAL_URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    ARTIST_URI = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
    ALBUM_URI = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
  }

  public static List<MusicInfo> findMatchingAlbumList(Context paramContext, String paramString)
  {
    return findMusicItems(paramContext, paramString, ALBUM_URI, "album", AlbumInfo.class, "album_key");
  }

  public static List<MusicInfo> findMatchingArtistList(Context paramContext, String paramString)
  {
    return findMusicItems(paramContext, paramString, ARTIST_URI, "artist", ArtistInfo.class, "artist_key");
  }

  public static List<MusicInfo> findMatchingPlaylistList(Context paramContext, String paramString)
  {
    return findMusicItems(paramContext, paramString, PLAYLIST_URI, "name", PlaylistInfo.class, "name");
  }

  public static List<MusicInfo> findMatchingTitleList(Context paramContext, String paramString)
  {
    return findMusicItems(paramContext, paramString, GENERAL_URI, "title", TitleInfo.class, "title_key");
  }

  private static List<MusicInfo> findMusicItems(Context paramContext, String paramString1, Uri paramUri, String paramString2, Class<? extends MusicInfo> paramClass, String paramString3)
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = null;
    int i = -1;
    int j = -1;
    try
    {
      localCursor = paramContext.getContentResolver().query(paramUri, null, null, null, paramString3);
      if (localCursor != null)
      {
        boolean bool1 = localCursor.moveToFirst();
        if (bool1);
      }
      else
      {
        localArrayList = null;
        if (localCursor != null);
        try
        {
          localCursor.close();
          return localArrayList;
        }
        catch (Exception localException4)
        {
          while (true)
            Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException4));
        }
      }
      label88: if (j == -1)
      {
        i = localCursor.getColumnIndex("_id");
        j = localCursor.getColumnIndex(paramString2);
      }
      int k = localCursor.getInt(i);
      String str = localCursor.getString(j);
      if (StringUtils.isNullOrWhiteSpace(str));
      while (true)
      {
        while (true)
        {
          boolean bool2 = localCursor.moveToNext();
          if (bool2)
            break label88;
          if (localCursor == null)
            break;
          try
          {
            localCursor.close();
          }
          catch (Exception localException5)
          {
            Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException5));
          }
        }
        break;
        if (!str.toLowerCase().contains(paramString1.toLowerCase()))
          continue;
        MusicInfo localMusicInfo = (MusicInfo)paramClass.newInstance();
        localMusicInfo.setId(k);
        localMusicInfo.setTitle(str);
        localArrayList.add(localMusicInfo);
      }
    }
    catch (Exception localException2)
    {
      while (true)
      {
        Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException2));
        localArrayList = null;
        if (localCursor == null)
          continue;
        try
        {
          localCursor.close();
        }
        catch (Exception localException3)
        {
          Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException3));
        }
      }
    }
    finally
    {
      if (localCursor == null);
    }
    try
    {
      localCursor.close();
      throw localObject;
    }
    catch (Exception localException1)
    {
      while (true)
        Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException1));
    }
  }

  public static int getPlayCount(Context paramContext, List<Long> paramList)
  {
    int i = 0;
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Uri localUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = "_id";
    arrayOfObject[1] = StringUtils.join(paramList, ", ");
    Cursor localCursor = localContentResolver.query(localUri, null, String.format("%s in (%s)", arrayOfObject), null, null);
    int j = localCursor.getColumnIndex("most_played");
    if ((j >= 0) && (localCursor.moveToFirst()))
      do
        i += localCursor.getInt(j);
      while (localCursor.moveToNext());
    localCursor.close();
    return i;
  }

  public static class AlbumInfo extends MusicUtil.MusicInfo
  {
    public AlbumInfo()
    {
      this.type = MusicUtil.MusicType.ALBUM;
    }

    public AlbumInfo(String paramString)
    {
      super("");
      this.type = MusicUtil.MusicType.ALBUM;
    }
  }

  public static class ArtistInfo extends MusicUtil.MusicInfo
  {
    public ArtistInfo()
    {
      this.type = MusicUtil.MusicType.ARTIST;
    }

    public ArtistInfo(String paramString)
    {
      super("");
      this.type = MusicUtil.MusicType.ARTIST;
    }
  }

  public static abstract class MusicInfo
    implements Comparable<MusicInfo>
  {
    private int id;
    protected String subtitle;
    protected String title;
    protected MusicUtil.MusicType type;

    protected MusicInfo()
    {
    }

    protected MusicInfo(String paramString1, String paramString2)
    {
      this.title = paramString1;
      this.subtitle = paramString2;
    }

    public int compareTo(MusicInfo paramMusicInfo)
    {
      return this.title.compareTo(paramMusicInfo.title);
    }

    public int getId()
    {
      return this.id;
    }

    public String getSubtitle()
    {
      return this.subtitle;
    }

    public String getTitle()
    {
      return this.title;
    }

    public MusicUtil.MusicType getType()
    {
      return this.type;
    }

    protected void setId(int paramInt)
    {
      this.id = paramInt;
    }

    public void setTitle(String paramString)
    {
      this.title = paramString;
    }
  }

  public static enum MusicType
  {
    static
    {
      ARTIST = new MusicType("ARTIST", 2);
      ALBUM = new MusicType("ALBUM", 3);
      MusicType[] arrayOfMusicType = new MusicType[4];
      arrayOfMusicType[0] = PLAYLIST;
      arrayOfMusicType[1] = TITLE;
      arrayOfMusicType[2] = ARTIST;
      arrayOfMusicType[3] = ALBUM;
      $VALUES = arrayOfMusicType;
    }
  }

  public static class PlaylistInfo extends MusicUtil.MusicInfo
  {
    public PlaylistInfo()
    {
      this.type = MusicUtil.MusicType.PLAYLIST;
    }

    public PlaylistInfo(String paramString, int paramInt)
    {
      super("");
      this.type = MusicUtil.MusicType.PLAYLIST;
      setId(paramInt);
    }
  }

  public static class TitleInfo extends MusicUtil.MusicInfo
  {
    public TitleInfo()
    {
      this.type = MusicUtil.MusicType.TITLE;
    }

    public TitleInfo(String paramString)
    {
      super("");
      this.type = MusicUtil.MusicType.TITLE;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.MusicUtil
 * JD-Core Version:    0.6.0
 */