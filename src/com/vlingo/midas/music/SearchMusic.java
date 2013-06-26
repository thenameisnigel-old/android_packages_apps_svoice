package com.vlingo.midas.music;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Audio.Playlists;
import android.util.Pair;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.SQLExpressionUtil;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.gui.widgets.BargeInWidget;
import com.vlingo.midas.util.PlayMusicType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.List<Ljava.lang.String;>;

public class SearchMusic
{
  private static final String ALBUM_ART_URI_PREFIX = "content://media/external/audio/albumart/";
  private static int MAX_COUNT = 0;
  private static final String QUICK_LIST_DEFAULT_VALUE = "Quick list";
  private static final int SEARCH_MUSIC_MAX = 2 * BargeInWidget.getMultiWidgetItemsUltimateMax();

  private static List<String> augmentQuery(String paramString, List<String> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator;
    if ((paramList != null) && (!paramList.isEmpty()))
      localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (!StringUtils.containsString(paramString.toLowerCase(), str.toLowerCase()))
        continue;
      localArrayList.add(str);
      continue;
      localArrayList.add(paramString);
    }
    return localArrayList;
  }

  private static List<String> augmentWords(String[] paramArrayOfString, List<String> paramList)
  {
    Object localObject = new ArrayList();
    int i;
    int j;
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      i = paramArrayOfString.length;
      j = 0;
    }
    while (j < i)
    {
      ((ArrayList)localObject).addAll(augmentQuery(paramArrayOfString[j], paramList));
      j++;
      continue;
      localObject = Arrays.asList(paramArrayOfString);
    }
    return (List<String>)localObject;
  }

  private static String buildWhereExpression(String paramString1, String paramString2, boolean paramBoolean, PlayMusicType paramPlayMusicType)
  {
    boolean bool = StringUtils.stringIsNonAsciiWithCase(paramString2);
    ArrayList localArrayList = null;
    if (bool);
    String str;
    switch (1.$SwitchMap$com$vlingo$midas$util$PlayMusicType[paramPlayMusicType.ordinal()])
    {
    default:
      localArrayList = new ArrayList();
      localArrayList.add(paramString2);
      if (paramBoolean)
        if (bool)
        {
          String[] arrayOfString2 = new String[1];
          arrayOfString2[0] = paramString2;
          List localList2 = augmentWords(arrayOfString2, localArrayList);
          if ((localList2 == null) || (localList2.isEmpty()))
            break;
          str = SQLExpressionUtil.like(paramString1, localList2);
        }
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return str;
      localArrayList = Settings.getAlbumNameList();
      break;
      localArrayList = Settings.getArtistNameList();
      break;
      localArrayList = Settings.getPlaylistNameList();
      break;
      localArrayList = Settings.getSongNameList();
      break;
      str = SQLExpressionUtil.like(paramString1, paramString2);
      continue;
      str = SQLExpressionUtil.like(paramString1, paramString2);
      continue;
      String[] arrayOfString1 = paramString2.split(" ");
      if (bool)
      {
        List localList1 = augmentWords(arrayOfString1, localArrayList);
        if ((localList1 != null) && (!localList1.isEmpty()))
        {
          str = SQLExpressionUtil.like(paramString1, localList1);
          continue;
        }
        str = SQLExpressionUtil.like(paramString1, arrayOfString1);
        continue;
      }
      str = SQLExpressionUtil.like(paramString1, arrayOfString1);
    }
  }

  public static List<MusicDetails> byAlbum(Context paramContext, String paramString)
  {
    if (paramString.trim().isEmpty());
    for (List localList = getAlbumList(paramContext, null, true); ; localList = getDataFromSongsList("album", paramContext, paramString, PlayMusicType.ALBUM))
      return localList;
  }

  public static List<MusicDetails> byArtist(Context paramContext, String paramString)
  {
    if (paramString.trim().isEmpty());
    for (List localList = getArtistList(paramContext, null, true); ; localList = getDataFromSongsList("artist", paramContext, paramString, PlayMusicType.ARTIST))
      return localList;
  }

  // ERROR //
  private static List<MusicDetails> byEverything(Context paramContext, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: iconst_5
    //   6: anewarray 58	java/lang/String
    //   9: astore 5
    //   11: aload 5
    //   13: iconst_0
    //   14: ldc 170
    //   16: aastore
    //   17: aload 5
    //   19: iconst_1
    //   20: ldc 172
    //   22: aastore
    //   23: aload 5
    //   25: iconst_2
    //   26: ldc 162
    //   28: aastore
    //   29: aload 5
    //   31: iconst_3
    //   32: ldc 174
    //   34: aastore
    //   35: aload 5
    //   37: iconst_4
    //   38: ldc 148
    //   40: aastore
    //   41: new 176	java/lang/StringBuilder
    //   44: dup
    //   45: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   48: ldc 162
    //   50: aload_1
    //   51: iconst_1
    //   52: getstatic 165	com/vlingo/midas/util/PlayMusicType:ARTIST	Lcom/vlingo/midas/util/PlayMusicType;
    //   55: invokestatic 179	com/vlingo/midas/music/SearchMusic:buildWhereExpression	(Ljava/lang/String;Ljava/lang/String;ZLcom/vlingo/midas/util/PlayMusicType;)Ljava/lang/String;
    //   58: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: ldc 185
    //   63: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: ldc 174
    //   68: aload_1
    //   69: iconst_1
    //   70: getstatic 188	com/vlingo/midas/util/PlayMusicType:TITLE	Lcom/vlingo/midas/util/PlayMusicType;
    //   73: invokestatic 179	com/vlingo/midas/music/SearchMusic:buildWhereExpression	(Ljava/lang/String;Ljava/lang/String;ZLcom/vlingo/midas/util/PlayMusicType;)Ljava/lang/String;
    //   76: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: ldc 185
    //   81: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: ldc 148
    //   86: aload_1
    //   87: iconst_1
    //   88: getstatic 152	com/vlingo/midas/util/PlayMusicType:ALBUM	Lcom/vlingo/midas/util/PlayMusicType;
    //   91: invokestatic 179	com/vlingo/midas/music/SearchMusic:buildWhereExpression	(Ljava/lang/String;Ljava/lang/String;ZLcom/vlingo/midas/util/PlayMusicType;)Ljava/lang/String;
    //   94: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: invokevirtual 191	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   100: astore 6
    //   102: aload_0
    //   103: invokevirtual 197	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   106: getstatic 203	android/provider/MediaStore$Audio$Media:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
    //   109: aload 5
    //   111: aload 6
    //   113: aconst_null
    //   114: ldc 205
    //   116: invokevirtual 211	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   119: astore_3
    //   120: new 36	java/util/ArrayList
    //   123: dup
    //   124: invokespecial 37	java/util/ArrayList:<init>	()V
    //   127: astore 9
    //   129: aload_3
    //   130: ifnull +132 -> 262
    //   133: aload_3
    //   134: invokeinterface 216 1 0
    //   139: ifeq +123 -> 262
    //   142: aload_3
    //   143: aload_3
    //   144: ldc 170
    //   146: invokeinterface 220 2 0
    //   151: invokeinterface 224 2 0
    //   156: invokestatic 230	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   159: astore 11
    //   161: aload_3
    //   162: aload_3
    //   163: ldc 172
    //   165: invokeinterface 220 2 0
    //   170: invokeinterface 234 2 0
    //   175: astore 12
    //   177: aload_3
    //   178: aload_3
    //   179: ldc 162
    //   181: invokeinterface 220 2 0
    //   186: invokeinterface 234 2 0
    //   191: astore 13
    //   193: aload 9
    //   195: new 236	com/vlingo/midas/music/MusicDetails
    //   198: dup
    //   199: aload 11
    //   201: aload_3
    //   202: aload_3
    //   203: ldc 174
    //   205: invokeinterface 220 2 0
    //   210: invokeinterface 234 2 0
    //   215: aload 13
    //   217: new 176	java/lang/StringBuilder
    //   220: dup
    //   221: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   224: ldc 10
    //   226: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   229: aload 12
    //   231: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: invokevirtual 191	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   237: invokestatic 242	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   240: invokespecial 245	com/vlingo/midas/music/MusicDetails:<init>	(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;)V
    //   243: invokeinterface 246 2 0
    //   248: pop
    //   249: aload_3
    //   250: invokeinterface 249 1 0
    //   255: istore 15
    //   257: iload 15
    //   259: ifne -117 -> 142
    //   262: aload_3
    //   263: ifnull +74 -> 337
    //   266: aload_3
    //   267: invokeinterface 252 1 0
    //   272: aload 9
    //   274: astore 4
    //   276: aload 4
    //   278: invokeinterface 255 1 0
    //   283: ifne +7 -> 290
    //   286: iload_2
    //   287: ifeq +3 -> 290
    //   290: aload 4
    //   292: areturn
    //   293: astore 8
    //   295: aload_3
    //   296: ifnull -20 -> 276
    //   299: aload_3
    //   300: invokeinterface 252 1 0
    //   305: goto -29 -> 276
    //   308: astore 7
    //   310: aload_3
    //   311: ifnull +9 -> 320
    //   314: aload_3
    //   315: invokeinterface 252 1 0
    //   320: aload 7
    //   322: athrow
    //   323: astore 7
    //   325: goto -15 -> 310
    //   328: astore 10
    //   330: aload 9
    //   332: astore 4
    //   334: goto -39 -> 295
    //   337: aload 9
    //   339: astore 4
    //   341: goto -65 -> 276
    //
    // Exception table:
    //   from	to	target	type
    //   102	129	293	java/lang/Exception
    //   102	129	308	finally
    //   133	257	323	finally
    //   133	257	328	java/lang/Exception
  }

  public static List<MusicDetails> byPlaylist(Context paramContext, String paramString)
  {
    if (paramString.trim().isEmpty());
    for (List localList = getPlaylistList(paramContext, null, true); ; localList = searchSongForPlaylist(paramContext, paramString))
      return localList;
  }

  public static List<MusicDetails> byTitle(Context paramContext, String paramString)
  {
    return getTitleList(paramContext, paramString, true);
  }

  // ERROR //
  public static List<MusicDetails> getAlbumList(Context paramContext, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: iconst_3
    //   6: anewarray 58	java/lang/String
    //   9: astore 5
    //   11: aload 5
    //   13: iconst_0
    //   14: ldc 170
    //   16: aastore
    //   17: aload 5
    //   19: iconst_1
    //   20: ldc 148
    //   22: aastore
    //   23: aload 5
    //   25: iconst_2
    //   26: ldc 162
    //   28: aastore
    //   29: aload_1
    //   30: invokestatic 269	com/vlingo/core/internal/util/StringUtils:isNullOrWhiteSpace	(Ljava/lang/String;)Z
    //   33: ifeq +176 -> 209
    //   36: aconst_null
    //   37: astore 6
    //   39: aload_0
    //   40: invokevirtual 197	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   43: getstatic 272	android/provider/MediaStore$Audio$Albums:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
    //   46: aload 5
    //   48: aload 6
    //   50: aconst_null
    //   51: ldc_w 274
    //   54: invokevirtual 211	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   57: astore_3
    //   58: aload_3
    //   59: ifnull +137 -> 196
    //   62: aload_3
    //   63: invokeinterface 216 1 0
    //   68: pop
    //   69: iconst_0
    //   70: istore 10
    //   72: new 36	java/util/ArrayList
    //   75: dup
    //   76: invokespecial 37	java/util/ArrayList:<init>	()V
    //   79: astore 11
    //   81: aload_3
    //   82: aload_3
    //   83: ldc 170
    //   85: invokeinterface 220 2 0
    //   90: invokeinterface 224 2 0
    //   95: invokestatic 230	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   98: astore 13
    //   100: aload 11
    //   102: new 236	com/vlingo/midas/music/MusicDetails
    //   105: dup
    //   106: aload 13
    //   108: aload_3
    //   109: aload_3
    //   110: ldc 148
    //   112: invokeinterface 220 2 0
    //   117: invokeinterface 234 2 0
    //   122: aload_3
    //   123: aload_3
    //   124: ldc 162
    //   126: invokeinterface 220 2 0
    //   131: invokeinterface 234 2 0
    //   136: new 176	java/lang/StringBuilder
    //   139: dup
    //   140: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   143: ldc 10
    //   145: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: aload 13
    //   150: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   153: invokevirtual 191	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   156: invokestatic 242	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   159: invokespecial 245	com/vlingo/midas/music/MusicDetails:<init>	(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;)V
    //   162: invokeinterface 246 2 0
    //   167: pop
    //   168: iinc 10 1
    //   171: aload_3
    //   172: invokeinterface 249 1 0
    //   177: ifeq +15 -> 192
    //   180: getstatic 29	com/vlingo/midas/music/SearchMusic:SEARCH_MUSIC_MAX	I
    //   183: istore 15
    //   185: iload 10
    //   187: iload 15
    //   189: if_icmplt -108 -> 81
    //   192: aload 11
    //   194: astore 4
    //   196: aload_3
    //   197: ifnull +9 -> 206
    //   200: aload_3
    //   201: invokeinterface 252 1 0
    //   206: aload 4
    //   208: areturn
    //   209: ldc 148
    //   211: aload_1
    //   212: iload_2
    //   213: getstatic 152	com/vlingo/midas/util/PlayMusicType:ALBUM	Lcom/vlingo/midas/util/PlayMusicType;
    //   216: invokestatic 179	com/vlingo/midas/music/SearchMusic:buildWhereExpression	(Ljava/lang/String;Ljava/lang/String;ZLcom/vlingo/midas/util/PlayMusicType;)Ljava/lang/String;
    //   219: astore 6
    //   221: goto -182 -> 39
    //   224: astore 8
    //   226: aload_3
    //   227: ifnull -21 -> 206
    //   230: aload_3
    //   231: invokeinterface 252 1 0
    //   236: goto -30 -> 206
    //   239: astore 7
    //   241: aload_3
    //   242: ifnull +9 -> 251
    //   245: aload_3
    //   246: invokeinterface 252 1 0
    //   251: aload 7
    //   253: athrow
    //   254: astore 7
    //   256: goto -15 -> 241
    //   259: astore 12
    //   261: aload 11
    //   263: astore 4
    //   265: goto -39 -> 226
    //
    // Exception table:
    //   from	to	target	type
    //   39	81	224	java/lang/Exception
    //   39	81	239	finally
    //   81	185	254	finally
    //   81	185	259	java/lang/Exception
  }

  // ERROR //
  public static List<MusicDetails> getArtistList(Context paramContext, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: aconst_null
    //   6: astore 5
    //   8: iconst_3
    //   9: anewarray 58	java/lang/String
    //   12: astore 6
    //   14: aload 6
    //   16: iconst_0
    //   17: ldc 170
    //   19: aastore
    //   20: aload 6
    //   22: iconst_1
    //   23: ldc 162
    //   25: aastore
    //   26: aload 6
    //   28: iconst_2
    //   29: ldc_w 279
    //   32: aastore
    //   33: aload_1
    //   34: invokestatic 269	com/vlingo/core/internal/util/StringUtils:isNullOrWhiteSpace	(Ljava/lang/String;)Z
    //   37: ifeq +311 -> 348
    //   40: aconst_null
    //   41: astore 7
    //   43: aload_0
    //   44: invokevirtual 197	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   47: getstatic 282	android/provider/MediaStore$Audio$Artists:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
    //   50: aload 6
    //   52: aload 7
    //   54: aconst_null
    //   55: ldc_w 279
    //   58: invokevirtual 211	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   61: astore_3
    //   62: aload_3
    //   63: ifnull +260 -> 323
    //   66: aload_3
    //   67: invokeinterface 216 1 0
    //   72: ifeq +251 -> 323
    //   75: iconst_0
    //   76: istore 10
    //   78: new 36	java/util/ArrayList
    //   81: dup
    //   82: invokespecial 37	java/util/ArrayList:<init>	()V
    //   85: astore 11
    //   87: aload_3
    //   88: ldc 170
    //   90: invokeinterface 220 2 0
    //   95: istore 13
    //   97: aload_3
    //   98: iload 13
    //   100: invokeinterface 224 2 0
    //   105: invokestatic 230	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   108: astore 14
    //   110: aload_3
    //   111: ldc 162
    //   113: invokeinterface 220 2 0
    //   118: istore 15
    //   120: aload_3
    //   121: iload 15
    //   123: invokeinterface 234 2 0
    //   128: astore 16
    //   130: aload_3
    //   131: ldc_w 279
    //   134: invokeinterface 220 2 0
    //   139: istore 17
    //   141: aload_3
    //   142: iload 17
    //   144: invokeinterface 234 2 0
    //   149: astore 18
    //   151: iconst_1
    //   152: anewarray 58	java/lang/String
    //   155: astore 19
    //   157: aload 19
    //   159: iconst_0
    //   160: ldc 172
    //   162: aastore
    //   163: ldc_w 279
    //   166: aload 18
    //   168: iconst_1
    //   169: getstatic 165	com/vlingo/midas/util/PlayMusicType:ARTIST	Lcom/vlingo/midas/util/PlayMusicType;
    //   172: invokestatic 179	com/vlingo/midas/music/SearchMusic:buildWhereExpression	(Ljava/lang/String;Ljava/lang/String;ZLcom/vlingo/midas/util/PlayMusicType;)Ljava/lang/String;
    //   175: astore 20
    //   177: getstatic 285	android/net/Uri:EMPTY	Landroid/net/Uri;
    //   180: astore 21
    //   182: aload_0
    //   183: invokevirtual 197	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   186: getstatic 203	android/provider/MediaStore$Audio$Media:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
    //   189: aload 19
    //   191: aload 20
    //   193: aconst_null
    //   194: ldc_w 279
    //   197: invokevirtual 211	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   200: astore 4
    //   202: aload 4
    //   204: ifnull +67 -> 271
    //   207: aload 4
    //   209: invokeinterface 216 1 0
    //   214: ifeq +50 -> 264
    //   217: aload 4
    //   219: ldc 172
    //   221: invokeinterface 220 2 0
    //   226: istore 24
    //   228: aload 4
    //   230: iload 24
    //   232: invokeinterface 234 2 0
    //   237: astore 25
    //   239: new 176	java/lang/StringBuilder
    //   242: dup
    //   243: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   246: ldc 10
    //   248: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: aload 25
    //   253: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: invokevirtual 191	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   259: invokestatic 242	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   262: astore 21
    //   264: aload 4
    //   266: invokeinterface 252 1 0
    //   271: aload 11
    //   273: new 236	com/vlingo/midas/music/MusicDetails
    //   276: dup
    //   277: aload 14
    //   279: aload 16
    //   281: ldc_w 287
    //   284: aload 21
    //   286: invokespecial 245	com/vlingo/midas/music/MusicDetails:<init>	(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;)V
    //   289: invokeinterface 246 2 0
    //   294: pop
    //   295: iinc 10 1
    //   298: aload_3
    //   299: invokeinterface 249 1 0
    //   304: ifeq +15 -> 319
    //   307: getstatic 29	com/vlingo/midas/music/SearchMusic:SEARCH_MUSIC_MAX	I
    //   310: istore 23
    //   312: iload 10
    //   314: iload 23
    //   316: if_icmplt -229 -> 87
    //   319: aload 11
    //   321: astore 5
    //   323: aload_3
    //   324: ifnull +9 -> 333
    //   327: aload_3
    //   328: invokeinterface 252 1 0
    //   333: aload 4
    //   335: ifnull +10 -> 345
    //   338: aload 4
    //   340: invokeinterface 252 1 0
    //   345: aload 5
    //   347: areturn
    //   348: ldc 162
    //   350: aload_1
    //   351: iload_2
    //   352: getstatic 165	com/vlingo/midas/util/PlayMusicType:ARTIST	Lcom/vlingo/midas/util/PlayMusicType;
    //   355: invokestatic 179	com/vlingo/midas/music/SearchMusic:buildWhereExpression	(Ljava/lang/String;Ljava/lang/String;ZLcom/vlingo/midas/util/PlayMusicType;)Ljava/lang/String;
    //   358: astore 7
    //   360: goto -317 -> 43
    //   363: astore 9
    //   365: aload_3
    //   366: ifnull +9 -> 375
    //   369: aload_3
    //   370: invokeinterface 252 1 0
    //   375: aload 4
    //   377: ifnull -32 -> 345
    //   380: aload 4
    //   382: invokeinterface 252 1 0
    //   387: goto -42 -> 345
    //   390: astore 8
    //   392: aload_3
    //   393: ifnull +9 -> 402
    //   396: aload_3
    //   397: invokeinterface 252 1 0
    //   402: aload 4
    //   404: ifnull +10 -> 414
    //   407: aload 4
    //   409: invokeinterface 252 1 0
    //   414: aload 8
    //   416: athrow
    //   417: astore 8
    //   419: goto -27 -> 392
    //   422: astore 12
    //   424: aload 11
    //   426: astore 5
    //   428: goto -63 -> 365
    //
    // Exception table:
    //   from	to	target	type
    //   43	87	363	java/lang/Exception
    //   43	87	390	finally
    //   87	312	417	finally
    //   87	312	422	java/lang/Exception
  }

  private static List<MusicDetails> getDataFromSongsList(String paramString1, Context paramContext, String paramString2, PlayMusicType paramPlayMusicType)
  {
    Object localObject = getDataFromSongsList(paramString1, paramContext, paramString2, true, paramPlayMusicType);
    if (((List)localObject).size() > 0);
    while (true)
    {
      return localObject;
      if (paramString2.split(" ").length > 1)
      {
        paramString2 = paramString2.replace(" ", "%");
        List localList = getDataFromSongsList(paramString1, paramContext, paramString2, true, paramPlayMusicType);
        if (localList.size() > 0)
        {
          localObject = localList;
          continue;
        }
      }
      localObject = getDataFromSongsList(paramString1, paramContext, paramString2, false, paramPlayMusicType);
    }
  }

  // ERROR //
  private static List<MusicDetails> getDataFromSongsList(String paramString1, Context paramContext, String paramString2, boolean paramBoolean, PlayMusicType paramPlayMusicType)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: iconst_4
    //   7: anewarray 58	java/lang/String
    //   10: astore 7
    //   12: aload 7
    //   14: iconst_0
    //   15: ldc 170
    //   17: aastore
    //   18: aload 7
    //   20: iconst_1
    //   21: ldc 172
    //   23: aastore
    //   24: aload 7
    //   26: iconst_2
    //   27: ldc 162
    //   29: aastore
    //   30: aload 7
    //   32: iconst_3
    //   33: ldc 174
    //   35: aastore
    //   36: aload_0
    //   37: aload_2
    //   38: iload_3
    //   39: aload 4
    //   41: invokestatic 179	com/vlingo/midas/music/SearchMusic:buildWhereExpression	(Ljava/lang/String;Ljava/lang/String;ZLcom/vlingo/midas/util/PlayMusicType;)Ljava/lang/String;
    //   44: astore 8
    //   46: aload_1
    //   47: invokevirtual 197	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   50: getstatic 203	android/provider/MediaStore$Audio$Media:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
    //   53: aload 7
    //   55: aload 8
    //   57: aconst_null
    //   58: ldc 205
    //   60: invokevirtual 211	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   63: astore 5
    //   65: new 36	java/util/ArrayList
    //   68: dup
    //   69: invokespecial 37	java/util/ArrayList:<init>	()V
    //   72: astore 11
    //   74: aload 5
    //   76: ifnull +142 -> 218
    //   79: aload 5
    //   81: invokeinterface 216 1 0
    //   86: ifeq +132 -> 218
    //   89: aload 5
    //   91: aload 5
    //   93: ldc 170
    //   95: invokeinterface 220 2 0
    //   100: invokeinterface 224 2 0
    //   105: invokestatic 230	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   108: astore 13
    //   110: aload 5
    //   112: aload 5
    //   114: ldc 172
    //   116: invokeinterface 220 2 0
    //   121: invokeinterface 234 2 0
    //   126: astore 14
    //   128: aload 5
    //   130: aload 5
    //   132: ldc 162
    //   134: invokeinterface 220 2 0
    //   139: invokeinterface 234 2 0
    //   144: astore 15
    //   146: aload 11
    //   148: new 236	com/vlingo/midas/music/MusicDetails
    //   151: dup
    //   152: aload 13
    //   154: aload 5
    //   156: aload 5
    //   158: ldc 174
    //   160: invokeinterface 220 2 0
    //   165: invokeinterface 234 2 0
    //   170: aload 15
    //   172: new 176	java/lang/StringBuilder
    //   175: dup
    //   176: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   179: ldc 10
    //   181: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: aload 14
    //   186: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: invokevirtual 191	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   192: invokestatic 242	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   195: invokespecial 245	com/vlingo/midas/music/MusicDetails:<init>	(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;)V
    //   198: invokeinterface 246 2 0
    //   203: pop
    //   204: aload 5
    //   206: invokeinterface 249 1 0
    //   211: istore 17
    //   213: iload 17
    //   215: ifne -126 -> 89
    //   218: aload 5
    //   220: ifnull +75 -> 295
    //   223: aload 5
    //   225: invokeinterface 252 1 0
    //   230: aload 11
    //   232: astore 6
    //   234: aload 6
    //   236: invokeinterface 255 1 0
    //   241: ifne +3 -> 244
    //   244: aload 6
    //   246: areturn
    //   247: astore 10
    //   249: aload 5
    //   251: ifnull -17 -> 234
    //   254: aload 5
    //   256: invokeinterface 252 1 0
    //   261: goto -27 -> 234
    //   264: astore 9
    //   266: aload 5
    //   268: ifnull +10 -> 278
    //   271: aload 5
    //   273: invokeinterface 252 1 0
    //   278: aload 9
    //   280: athrow
    //   281: astore 9
    //   283: goto -17 -> 266
    //   286: astore 12
    //   288: aload 11
    //   290: astore 6
    //   292: goto -43 -> 249
    //   295: aload 11
    //   297: astore 6
    //   299: goto -65 -> 234
    //
    // Exception table:
    //   from	to	target	type
    //   46	74	247	java/lang/Exception
    //   46	74	264	finally
    //   79	213	281	finally
    //   79	213	286	java/lang/Exception
  }

  public static Pair<PlayMusicType, List<MusicDetails>> getGenericList(Context paramContext, String paramString, boolean paramBoolean)
  {
    List localList1 = getPlaylistList(paramContext, paramString, paramBoolean);
    Pair localPair;
    if ((localList1 != null) && (!localList1.isEmpty()))
      localPair = new Pair(PlayMusicType.PLAYLIST, localList1);
    while (true)
    {
      return localPair;
      List localList2 = getTitleList(paramContext, paramString, paramBoolean);
      if ((localList2 != null) && (!localList2.isEmpty()))
      {
        localPair = new Pair(PlayMusicType.TITLE, localList2);
        continue;
      }
      List localList3 = getAlbumList(paramContext, paramString, paramBoolean);
      if ((localList3 != null) && (!localList3.isEmpty()))
      {
        localPair = new Pair(PlayMusicType.ALBUM, localList3);
        continue;
      }
      List localList4 = getArtistList(paramContext, paramString, paramBoolean);
      localPair = new Pair(PlayMusicType.ARTIST, localList4);
    }
  }

  // ERROR //
  public static List<MusicDetails> getPlaylistList(Context paramContext, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: iconst_2
    //   6: anewarray 58	java/lang/String
    //   9: astore 5
    //   11: aload 5
    //   13: iconst_0
    //   14: ldc 170
    //   16: aastore
    //   17: aload 5
    //   19: iconst_1
    //   20: ldc_w 308
    //   23: aastore
    //   24: aload_1
    //   25: invokestatic 269	com/vlingo/core/internal/util/StringUtils:isNullOrWhiteSpace	(Ljava/lang/String;)Z
    //   28: ifeq +196 -> 224
    //   31: aconst_null
    //   32: astore 6
    //   34: aload_0
    //   35: invokevirtual 197	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   38: getstatic 311	android/provider/MediaStore$Audio$Playlists:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
    //   41: aload 5
    //   43: aload 6
    //   45: aconst_null
    //   46: ldc_w 308
    //   49: invokevirtual 211	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   52: astore_3
    //   53: aload_3
    //   54: ifnull +157 -> 211
    //   57: aload_3
    //   58: invokeinterface 216 1 0
    //   63: ifeq +148 -> 211
    //   66: iconst_0
    //   67: istore 9
    //   69: new 36	java/util/ArrayList
    //   72: dup
    //   73: invokespecial 37	java/util/ArrayList:<init>	()V
    //   76: astore 10
    //   78: aload_3
    //   79: aload_3
    //   80: ldc 170
    //   82: invokeinterface 220 2 0
    //   87: invokeinterface 224 2 0
    //   92: invokestatic 230	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   95: astore 12
    //   97: aload_3
    //   98: aload_3
    //   99: ldc_w 308
    //   102: invokeinterface 220 2 0
    //   107: invokeinterface 234 2 0
    //   112: astore 13
    //   114: aload 13
    //   116: ldc 16
    //   118: invokevirtual 314	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   121: ifeq +119 -> 240
    //   124: invokestatic 320	com/vlingo/midas/VlingoApplication:getInstance	()Lcom/vlingo/midas/VlingoApplication;
    //   127: ldc_w 321
    //   130: invokevirtual 322	com/vlingo/midas/VlingoApplication:getString	(I)Ljava/lang/String;
    //   133: astore 14
    //   135: aload_0
    //   136: aload 13
    //   138: invokestatic 262	com/vlingo/midas/music/SearchMusic:searchSongForPlaylist	(Landroid/content/Context;Ljava/lang/String;)Ljava/util/List;
    //   141: astore 15
    //   143: aload 15
    //   145: ifnull +41 -> 186
    //   148: aload 15
    //   150: invokeinterface 255 1 0
    //   155: ifeq +31 -> 186
    //   158: aload 10
    //   160: new 236	com/vlingo/midas/music/MusicDetails
    //   163: dup
    //   164: aload 12
    //   166: aload 14
    //   168: ldc_w 287
    //   171: getstatic 285	android/net/Uri:EMPTY	Landroid/net/Uri;
    //   174: invokespecial 245	com/vlingo/midas/music/MusicDetails:<init>	(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;)V
    //   177: invokeinterface 246 2 0
    //   182: pop
    //   183: iinc 9 1
    //   186: aload_3
    //   187: invokeinterface 249 1 0
    //   192: ifeq +15 -> 207
    //   195: getstatic 29	com/vlingo/midas/music/SearchMusic:SEARCH_MUSIC_MAX	I
    //   198: istore 16
    //   200: iload 9
    //   202: iload 16
    //   204: if_icmplt -126 -> 78
    //   207: aload 10
    //   209: astore 4
    //   211: aload_3
    //   212: ifnull +9 -> 221
    //   215: aload_3
    //   216: invokeinterface 252 1 0
    //   221: aload 4
    //   223: areturn
    //   224: ldc_w 308
    //   227: aload_1
    //   228: iload_2
    //   229: getstatic 303	com/vlingo/midas/util/PlayMusicType:PLAYLIST	Lcom/vlingo/midas/util/PlayMusicType;
    //   232: invokestatic 179	com/vlingo/midas/music/SearchMusic:buildWhereExpression	(Ljava/lang/String;Ljava/lang/String;ZLcom/vlingo/midas/util/PlayMusicType;)Ljava/lang/String;
    //   235: astore 6
    //   237: goto -203 -> 34
    //   240: aload 13
    //   242: astore 14
    //   244: goto -109 -> 135
    //   247: astore 8
    //   249: aload_3
    //   250: ifnull -29 -> 221
    //   253: aload_3
    //   254: invokeinterface 252 1 0
    //   259: goto -38 -> 221
    //   262: astore 7
    //   264: aload_3
    //   265: ifnull +9 -> 274
    //   268: aload_3
    //   269: invokeinterface 252 1 0
    //   274: aload 7
    //   276: athrow
    //   277: astore 7
    //   279: goto -15 -> 264
    //   282: astore 11
    //   284: aload 10
    //   286: astore 4
    //   288: goto -39 -> 249
    //
    // Exception table:
    //   from	to	target	type
    //   34	78	247	java/lang/Exception
    //   34	78	262	finally
    //   78	200	277	finally
    //   78	200	282	java/lang/Exception
  }

  // ERROR //
  public static List<MusicDetails> getTitleList(Context paramContext, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: iconst_4
    //   6: anewarray 58	java/lang/String
    //   9: astore 5
    //   11: aload 5
    //   13: iconst_0
    //   14: ldc 170
    //   16: aastore
    //   17: aload 5
    //   19: iconst_1
    //   20: ldc 172
    //   22: aastore
    //   23: aload 5
    //   25: iconst_2
    //   26: ldc 162
    //   28: aastore
    //   29: aload 5
    //   31: iconst_3
    //   32: ldc 174
    //   34: aastore
    //   35: new 176	java/lang/StringBuilder
    //   38: dup
    //   39: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   42: ldc 174
    //   44: aload_1
    //   45: iload_2
    //   46: getstatic 188	com/vlingo/midas/util/PlayMusicType:TITLE	Lcom/vlingo/midas/util/PlayMusicType;
    //   49: invokestatic 179	com/vlingo/midas/music/SearchMusic:buildWhereExpression	(Ljava/lang/String;Ljava/lang/String;ZLcom/vlingo/midas/util/PlayMusicType;)Ljava/lang/String;
    //   52: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: ldc_w 324
    //   58: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: ldc_w 326
    //   64: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: ldc_w 328
    //   70: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: invokevirtual 191	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   76: astore 6
    //   78: aload_0
    //   79: invokevirtual 197	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   82: getstatic 203	android/provider/MediaStore$Audio$Media:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
    //   85: aload 5
    //   87: aload 6
    //   89: aconst_null
    //   90: ldc 205
    //   92: invokevirtual 211	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   95: astore_3
    //   96: new 36	java/util/ArrayList
    //   99: dup
    //   100: invokespecial 37	java/util/ArrayList:<init>	()V
    //   103: astore 9
    //   105: aload_3
    //   106: ifnull +146 -> 252
    //   109: aload_3
    //   110: invokeinterface 216 1 0
    //   115: ifeq +137 -> 252
    //   118: iconst_0
    //   119: istore 11
    //   121: aload_3
    //   122: aload_3
    //   123: ldc 170
    //   125: invokeinterface 220 2 0
    //   130: invokeinterface 224 2 0
    //   135: invokestatic 230	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   138: astore 12
    //   140: aload_3
    //   141: aload_3
    //   142: ldc 172
    //   144: invokeinterface 220 2 0
    //   149: invokeinterface 234 2 0
    //   154: astore 13
    //   156: aload_3
    //   157: aload_3
    //   158: ldc 162
    //   160: invokeinterface 220 2 0
    //   165: invokeinterface 234 2 0
    //   170: astore 14
    //   172: aload 9
    //   174: new 236	com/vlingo/midas/music/MusicDetails
    //   177: dup
    //   178: aload 12
    //   180: aload_3
    //   181: aload_3
    //   182: ldc 174
    //   184: invokeinterface 220 2 0
    //   189: invokeinterface 234 2 0
    //   194: aload 14
    //   196: new 176	java/lang/StringBuilder
    //   199: dup
    //   200: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   203: ldc 10
    //   205: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: aload 13
    //   210: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: invokevirtual 191	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   216: invokestatic 242	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   219: invokespecial 245	com/vlingo/midas/music/MusicDetails:<init>	(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;)V
    //   222: invokeinterface 246 2 0
    //   227: pop
    //   228: iinc 11 1
    //   231: aload_3
    //   232: invokeinterface 249 1 0
    //   237: ifeq +15 -> 252
    //   240: getstatic 29	com/vlingo/midas/music/SearchMusic:SEARCH_MUSIC_MAX	I
    //   243: istore 16
    //   245: iload 11
    //   247: iload 16
    //   249: if_icmplt -128 -> 121
    //   252: aload_3
    //   253: ifnull +70 -> 323
    //   256: aload_3
    //   257: invokeinterface 252 1 0
    //   262: aload 9
    //   264: astore 4
    //   266: aload 4
    //   268: invokeinterface 255 1 0
    //   273: ifne +3 -> 276
    //   276: aload 4
    //   278: areturn
    //   279: astore 8
    //   281: aload_3
    //   282: ifnull -16 -> 266
    //   285: aload_3
    //   286: invokeinterface 252 1 0
    //   291: goto -25 -> 266
    //   294: astore 7
    //   296: aload_3
    //   297: ifnull +9 -> 306
    //   300: aload_3
    //   301: invokeinterface 252 1 0
    //   306: aload 7
    //   308: athrow
    //   309: astore 7
    //   311: goto -15 -> 296
    //   314: astore 10
    //   316: aload 9
    //   318: astore 4
    //   320: goto -39 -> 281
    //   323: aload 9
    //   325: astore 4
    //   327: goto -61 -> 266
    //
    // Exception table:
    //   from	to	target	type
    //   78	105	279	java/lang/Exception
    //   78	105	294	finally
    //   109	245	309	finally
    //   109	245	314	java/lang/Exception
  }

  public static boolean isAnyMusic(Context paramContext)
  {
    String[] arrayOfString = new String[5];
    arrayOfString[0] = "_id";
    arrayOfString[1] = "album_id";
    arrayOfString[2] = "artist";
    arrayOfString[3] = "title";
    arrayOfString[4] = "album";
    Cursor localCursor = null;
    int i;
    try
    {
      localCursor = paramContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, arrayOfString, "is_music != 0 ", null, "title_key");
      if (localCursor == null)
      {
        i = 0;
      }
      else if (localCursor.getCount() < 1)
      {
        localCursor.close();
        i = 0;
      }
      else
      {
        localCursor.close();
        i = 1;
      }
    }
    catch (Exception localException)
    {
      if (localCursor != null)
        localCursor.close();
      i = 0;
    }
    return i;
  }

  public static void populateMusicMappings(Context paramContext)
  {
    ArrayList localArrayList1 = Settings.getSongNameList();
    ArrayList localArrayList2 = Settings.getAlbumNameList();
    ArrayList localArrayList3 = Settings.getArtistNameList();
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "artist";
    arrayOfString[1] = "title";
    arrayOfString[2] = "album";
    Cursor localCursor = paramContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, arrayOfString, null, null, "title_key");
    if (localCursor != null);
    try
    {
      if (localCursor.moveToFirst())
      {
        boolean bool;
        do
        {
          String str1 = localCursor.getString(localCursor.getColumnIndexOrThrow("title"));
          String str2 = localCursor.getString(localCursor.getColumnIndexOrThrow("album"));
          String str3 = localCursor.getString(localCursor.getColumnIndexOrThrow("artist"));
          if (!localArrayList1.contains(str1))
            localArrayList1.add(str1);
          if (!localArrayList2.contains(str2))
            localArrayList2.add(str2);
          if (!localArrayList3.contains(str3))
            localArrayList3.add(str3);
          bool = localCursor.moveToNext();
        }
        while (bool);
      }
      localCursor.close();
      populatePlaylistMappings(paramContext);
      return;
    }
    finally
    {
      localCursor.close();
    }
    throw localObject;
  }

  private static void populatePlaylistMappings(Context paramContext)
  {
    ArrayList localArrayList = Settings.getPlaylistNameList();
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "name";
    Cursor localCursor = paramContext.getContentResolver().query(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, arrayOfString, null, null, "name");
    if (localCursor != null);
    try
    {
      if (localCursor.moveToFirst())
      {
        boolean bool;
        do
        {
          String str = localCursor.getString(localCursor.getColumnIndexOrThrow("name"));
          if (!localArrayList.contains(str))
            localArrayList.add(str);
          bool = localCursor.moveToNext();
        }
        while (bool);
      }
      return;
    }
    finally
    {
      localCursor.close();
    }
    throw localObject;
  }

  // ERROR //
  private static List<MusicDetails> searchSongForPlaylist(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_3
    //   4: ldc_w 308
    //   7: aload_1
    //   8: invokestatic 127	com/vlingo/core/internal/util/SQLExpressionUtil:like	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   11: astore 4
    //   13: aload_0
    //   14: invokevirtual 197	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   17: getstatic 311	android/provider/MediaStore$Audio$Playlists:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
    //   20: aconst_null
    //   21: aload 4
    //   23: aconst_null
    //   24: ldc_w 345
    //   27: invokevirtual 211	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   30: astore_2
    //   31: aload_2
    //   32: ifnull +263 -> 295
    //   35: aload_2
    //   36: invokeinterface 216 1 0
    //   41: ifeq +254 -> 295
    //   44: aload_2
    //   45: ldc 170
    //   47: invokeinterface 220 2 0
    //   52: istore 7
    //   54: aload_2
    //   55: iload 7
    //   57: invokeinterface 224 2 0
    //   62: invokestatic 230	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   65: astore 8
    //   67: aload_2
    //   68: invokeinterface 252 1 0
    //   73: iconst_5
    //   74: anewarray 58	java/lang/String
    //   77: astore 9
    //   79: aload 9
    //   81: iconst_0
    //   82: ldc 170
    //   84: aastore
    //   85: aload 9
    //   87: iconst_1
    //   88: ldc 162
    //   90: aastore
    //   91: aload 9
    //   93: iconst_2
    //   94: ldc 174
    //   96: aastore
    //   97: aload 9
    //   99: iconst_3
    //   100: ldc_w 347
    //   103: aastore
    //   104: aload 9
    //   106: iconst_4
    //   107: ldc 172
    //   109: aastore
    //   110: aload_0
    //   111: invokevirtual 197	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   114: ldc_w 349
    //   117: aload 8
    //   119: invokevirtual 353	java/lang/Long:longValue	()J
    //   122: invokestatic 359	android/provider/MediaStore$Audio$Playlists$Members:getContentUri	(Ljava/lang/String;J)Landroid/net/Uri;
    //   125: aload 9
    //   127: ldc_w 332
    //   130: aconst_null
    //   131: ldc_w 361
    //   134: invokevirtual 211	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   137: astore_2
    //   138: new 36	java/util/ArrayList
    //   141: dup
    //   142: invokespecial 37	java/util/ArrayList:<init>	()V
    //   145: astore 10
    //   147: aload_2
    //   148: invokeinterface 216 1 0
    //   153: ifeq +139 -> 292
    //   156: aload_2
    //   157: ldc_w 347
    //   160: invokeinterface 220 2 0
    //   165: istore 12
    //   167: aload_2
    //   168: iload 12
    //   170: invokeinterface 224 2 0
    //   175: invokestatic 230	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   178: astore 13
    //   180: aload_2
    //   181: ldc 172
    //   183: invokeinterface 220 2 0
    //   188: istore 14
    //   190: aload_2
    //   191: iload 14
    //   193: invokeinterface 234 2 0
    //   198: pop
    //   199: aload_2
    //   200: ldc 162
    //   202: invokeinterface 220 2 0
    //   207: istore 16
    //   209: aload_2
    //   210: iload 16
    //   212: invokeinterface 234 2 0
    //   217: astore 17
    //   219: aload_2
    //   220: ldc 174
    //   222: invokeinterface 220 2 0
    //   227: istore 18
    //   229: aload 10
    //   231: new 236	com/vlingo/midas/music/MusicDetails
    //   234: dup
    //   235: aload 13
    //   237: aload_2
    //   238: iload 18
    //   240: invokeinterface 234 2 0
    //   245: aload 17
    //   247: new 176	java/lang/StringBuilder
    //   250: dup
    //   251: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   254: ldc 10
    //   256: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: aload 13
    //   261: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   264: invokevirtual 191	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   267: invokestatic 242	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   270: invokespecial 245	com/vlingo/midas/music/MusicDetails:<init>	(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;)V
    //   273: invokeinterface 246 2 0
    //   278: pop
    //   279: aload_2
    //   280: invokeinterface 249 1 0
    //   285: istore 20
    //   287: iload 20
    //   289: ifne -133 -> 156
    //   292: aload 10
    //   294: astore_3
    //   295: aload_2
    //   296: ifnull +9 -> 305
    //   299: aload_2
    //   300: invokeinterface 252 1 0
    //   305: aload_3
    //   306: areturn
    //   307: astore 6
    //   309: aload_2
    //   310: ifnull -5 -> 305
    //   313: aload_2
    //   314: invokeinterface 252 1 0
    //   319: goto -14 -> 305
    //   322: astore 5
    //   324: aload_2
    //   325: ifnull +9 -> 334
    //   328: aload_2
    //   329: invokeinterface 252 1 0
    //   334: aload 5
    //   336: athrow
    //   337: astore 5
    //   339: goto -15 -> 324
    //   342: astore 11
    //   344: aload 10
    //   346: astore_3
    //   347: goto -38 -> 309
    //
    // Exception table:
    //   from	to	target	type
    //   13	147	307	java/lang/Exception
    //   13	147	322	finally
    //   147	287	337	finally
    //   147	287	342	java/lang/Exception
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.music.SearchMusic
 * JD-Core Version:    0.6.0
 */