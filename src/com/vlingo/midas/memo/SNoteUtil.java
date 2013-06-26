package com.vlingo.midas.memo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.vlingo.core.internal.dialogmanager.util.IntegratedAppInfoInterface;
import com.vlingo.core.internal.memo.IMemoUtil;
import com.vlingo.core.internal.memo.Memo;
import com.vlingo.core.internal.memo.MemoUtilException;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.internal.util.PackageUtil;
import java.util.List;

public class SNoteUtil extends TMemoUtil
  implements IMemoUtil
{
  private static final String KEY_DATE = "ModifiedTime";
  private static final String KEY_HAS_FAVORITES = "HasFavorites";
  private static final String KEY_HAS_VOICERECORDER = "HasVoiceRecord";
  private static final String KEY_ID = "_id";
  private static final String KEY_NAME = "name";
  private static final String KEY_PATH = "path";
  private static final String KEY_TAG = "HasTag";
  private static final String KEY_TEMPLATE_TIME = "TemplateType";
  private static final String[] PROJECTION;
  private static final String SORT_ORDER = "ModifiedTime DESC";
  private static IMemoUtil instance;

  static
  {
    String[] arrayOfString = new String[8];
    arrayOfString[0] = "_id";
    arrayOfString[1] = "path";
    arrayOfString[2] = "name";
    arrayOfString[3] = "ModifiedTime";
    arrayOfString[4] = "HasFavorites";
    arrayOfString[5] = "HasVoiceRecord";
    arrayOfString[6] = "HasTag";
    arrayOfString[7] = "TemplateType";
    PROJECTION = arrayOfString;
    instance = null;
  }

  private String getFilePathFromId(Context paramContext, long paramLong)
  {
    Object localObject1 = null;
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Uri localUri = getUriFromId(paramLong);
    Cursor localCursor = null;
    try
    {
      localCursor = localContentResolver.query(localUri, null, null, null, null);
      if (localCursor.moveToFirst())
      {
        String str = localCursor.getString(localCursor.getColumnIndex("path"));
        localObject1 = str;
      }
      return localObject1;
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
    }
    throw localObject2;
  }

  public static IMemoUtil getInstance()
  {
    if (instance == null)
      instance = new SNoteUtil();
    return instance;
  }

  private Memo getMemo(Cursor paramCursor, MemoIndices paramMemoIndices)
  {
    Memo localMemo = new Memo();
    String str = truncateDateFromString(paramCursor.getString(paramMemoIndices.KEY_NAME_COL));
    localMemo.setTitle(str);
    localMemo.setContent(str);
    localMemo.setText(str);
    localMemo.setId(paramCursor.getInt(paramMemoIndices.KEY_ID_COL));
    localMemo.setDate(paramCursor.getString(paramMemoIndices.KEY_DATE_COL));
    return localMemo;
  }

  private Uri getUriFromId(long paramLong)
  {
    return ContentUris.withAppendedId(ClientSuppliedValues.getMemoApplicationInfo().getUpdateContentProviderUri(), paramLong);
  }

  public static boolean isInstalled()
  {
    return PackageUtil.isAppInstalled("com.sec.android.app.snotebook", 1);
  }

  public static String truncateDateFromString(String paramString)
  {
    if (StringUtils.isNullOrWhiteSpace(paramString));
    while (true)
    {
      return paramString;
      paramString = paramString.replaceAll(".snb$", "").replaceAll("\\_\\d{6,10}\\_\\d{6,10}$", "");
    }
  }

  // ERROR //
  public void deleteMemo(Context paramContext, long paramLong)
    throws MemoUtilException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 60	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: astore 6
    //   6: aload_0
    //   7: aload_1
    //   8: lload_2
    //   9: invokespecial 183	com/vlingo/midas/memo/SNoteUtil:getFilePathFromId	(Landroid/content/Context;J)Ljava/lang/String;
    //   12: astore 7
    //   14: iconst_0
    //   15: istore 8
    //   17: aload 7
    //   19: ifnull +431 -> 450
    //   22: new 185	java/io/File
    //   25: dup
    //   26: aload 7
    //   28: invokespecial 187	java/io/File:<init>	(Ljava/lang/String;)V
    //   31: astore 9
    //   33: aload 9
    //   35: invokevirtual 190	java/io/File:exists	()Z
    //   38: ifeq +10 -> 48
    //   41: aload 9
    //   43: invokevirtual 193	java/io/File:delete	()Z
    //   46: istore 8
    //   48: iload 8
    //   50: ifeq +400 -> 450
    //   53: ldc 195
    //   55: invokestatic 201	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   58: astore 10
    //   60: aconst_null
    //   61: astore 11
    //   63: aload 7
    //   65: ifnull +30 -> 95
    //   68: new 203	java/lang/StringBuilder
    //   71: dup
    //   72: invokespecial 204	java/lang/StringBuilder:<init>	()V
    //   75: ldc 206
    //   77: invokevirtual 210	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: aload 7
    //   82: invokevirtual 210	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: ldc 212
    //   87: invokevirtual 210	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   93: astore 11
    //   95: aconst_null
    //   96: astore 12
    //   98: aload 11
    //   100: ifnull +338 -> 438
    //   103: iconst_1
    //   104: anewarray 45	java/lang/String
    //   107: astore 38
    //   109: aload 38
    //   111: iconst_0
    //   112: ldc 218
    //   114: aastore
    //   115: aload 6
    //   117: aload 10
    //   119: aload 38
    //   121: aload 11
    //   123: aconst_null
    //   124: aconst_null
    //   125: invokevirtual 70	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   128: astore 39
    //   130: aload 39
    //   132: ifnull +306 -> 438
    //   135: aload 39
    //   137: invokeinterface 222 1 0
    //   142: ifgt +121 -> 263
    //   145: ldc 167
    //   147: astore 12
    //   149: aload 39
    //   151: invokeinterface 87 1 0
    //   156: goto +282 -> 438
    //   159: aload 11
    //   161: ifnonnull +123 -> 284
    //   164: new 224	android/content/ContentValues
    //   167: dup
    //   168: invokespecial 225	android/content/ContentValues:<init>	()V
    //   171: astore 14
    //   173: aload 14
    //   175: ldc 227
    //   177: iconst_1
    //   178: invokestatic 233	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   181: invokevirtual 237	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   184: aload 14
    //   186: ldc 239
    //   188: iconst_1
    //   189: invokestatic 233	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   192: invokevirtual 237	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   195: invokestatic 245	java/lang/System:currentTimeMillis	()J
    //   198: lstore 15
    //   200: aload_1
    //   201: invokevirtual 60	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   204: ldc 247
    //   206: invokestatic 253	android/provider/Settings$System:getLong	(Landroid/content/ContentResolver;Ljava/lang/String;)J
    //   209: lstore 24
    //   211: lload 24
    //   213: lstore 18
    //   215: lload 15
    //   217: lload 18
    //   219: lsub
    //   220: lstore 20
    //   222: aload 14
    //   224: ldc 255
    //   226: lload 20
    //   228: invokestatic 260	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   231: invokevirtual 263	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   234: aload 6
    //   236: aload 10
    //   238: aload 14
    //   240: ldc_w 265
    //   243: aconst_null
    //   244: invokevirtual 269	android/content/ContentResolver:update	(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   247: pop
    //   248: aload 6
    //   250: aload 10
    //   252: ldc_w 271
    //   255: aconst_null
    //   256: invokevirtual 274	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   259: pop
    //   260: goto +190 -> 450
    //   263: aload 39
    //   265: invokeinterface 76 1 0
    //   270: pop
    //   271: aload 39
    //   273: iconst_0
    //   274: invokeinterface 84 2 0
    //   279: astore 12
    //   281: goto -132 -> 149
    //   284: aload 12
    //   286: ldc_w 276
    //   289: invokevirtual 280	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   292: ifeq +132 -> 424
    //   295: new 224	android/content/ContentValues
    //   298: dup
    //   299: invokespecial 225	android/content/ContentValues:<init>	()V
    //   302: astore 26
    //   304: aload 26
    //   306: ldc 227
    //   308: iconst_1
    //   309: invokestatic 233	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   312: invokevirtual 237	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   315: aload 26
    //   317: ldc 239
    //   319: iconst_1
    //   320: invokestatic 233	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   323: invokevirtual 237	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   326: invokestatic 245	java/lang/System:currentTimeMillis	()J
    //   329: lstore 27
    //   331: aload_1
    //   332: invokevirtual 60	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   335: ldc 247
    //   337: invokestatic 253	android/provider/Settings$System:getLong	(Landroid/content/ContentResolver;Ljava/lang/String;)J
    //   340: lstore 35
    //   342: lload 35
    //   344: lstore 30
    //   346: lload 27
    //   348: lload 30
    //   350: lsub
    //   351: lstore 32
    //   353: aload 26
    //   355: ldc 255
    //   357: lload 32
    //   359: invokestatic 260	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   362: invokevirtual 263	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   365: aload 6
    //   367: aload 10
    //   369: aload 26
    //   371: aload 11
    //   373: aconst_null
    //   374: invokevirtual 269	android/content/ContentResolver:update	(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   377: pop
    //   378: goto +72 -> 450
    //   381: astore 13
    //   383: new 177	com/vlingo/core/internal/memo/MemoUtilException
    //   386: dup
    //   387: ldc_w 282
    //   390: invokespecial 283	com/vlingo/core/internal/memo/MemoUtilException:<init>	(Ljava/lang/String;)V
    //   393: athrow
    //   394: astore 4
    //   396: ldc_w 285
    //   399: aload 4
    //   401: invokevirtual 288	com/vlingo/core/internal/memo/MemoUtilException:getMessage	()Ljava/lang/String;
    //   404: invokestatic 294	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   407: pop
    //   408: aload 4
    //   410: invokevirtual 297	com/vlingo/core/internal/memo/MemoUtilException:printStackTrace	()V
    //   413: aload 4
    //   415: athrow
    //   416: astore 29
    //   418: lconst_0
    //   419: lstore 30
    //   421: goto -75 -> 346
    //   424: aload 6
    //   426: aload 10
    //   428: aload 11
    //   430: aconst_null
    //   431: invokevirtual 274	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   434: pop
    //   435: goto +15 -> 450
    //   438: aload 12
    //   440: ifnonnull -281 -> 159
    //   443: ldc 167
    //   445: astore 12
    //   447: goto -288 -> 159
    //   450: return
    //   451: astore 17
    //   453: lconst_0
    //   454: lstore 18
    //   456: goto -241 -> 215
    //
    // Exception table:
    //   from	to	target	type
    //   68	200	381	java/lang/Exception
    //   200	211	381	java/lang/Exception
    //   222	331	381	java/lang/Exception
    //   331	342	381	java/lang/Exception
    //   353	378	381	java/lang/Exception
    //   424	435	381	java/lang/Exception
    //   0	60	394	com/vlingo/core/internal/memo/MemoUtilException
    //   68	200	394	com/vlingo/core/internal/memo/MemoUtilException
    //   200	211	394	com/vlingo/core/internal/memo/MemoUtilException
    //   222	331	394	com/vlingo/core/internal/memo/MemoUtilException
    //   331	342	394	com/vlingo/core/internal/memo/MemoUtilException
    //   353	378	394	com/vlingo/core/internal/memo/MemoUtilException
    //   383	394	394	com/vlingo/core/internal/memo/MemoUtilException
    //   424	435	394	com/vlingo/core/internal/memo/MemoUtilException
    //   331	342	416	android/provider/Settings$SettingNotFoundException
    //   200	211	451	android/provider/Settings$SettingNotFoundException
  }

  public String getCreateMemoAction()
  {
    return "android.intent.action.VOICETALK_NEW_SMEMO";
  }

  protected Memo getMemo(Cursor paramCursor)
  {
    return getMemo(paramCursor, new MemoIndices(paramCursor));
  }

  // ERROR //
  public List<Memo> getMemos(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: invokestatic 136	com/vlingo/core/internal/util/ClientSuppliedValues:getMemoApplicationInfo	()Lcom/vlingo/core/internal/dialogmanager/util/IntegratedAppInfoInterface;
    //   9: invokeinterface 311 1 0
    //   14: astore 7
    //   16: aload_2
    //   17: ifnonnull +51 -> 68
    //   20: ldc_w 313
    //   23: astore 8
    //   25: aload_1
    //   26: invokevirtual 60	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   29: aload 7
    //   31: aload_0
    //   32: invokevirtual 317	com/vlingo/midas/memo/SNoteUtil:getProjection	()[Ljava/lang/String;
    //   35: aload 8
    //   37: aconst_null
    //   38: aload_3
    //   39: invokevirtual 70	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   42: astore 9
    //   44: aload 9
    //   46: astore 4
    //   48: aload 4
    //   50: ifnonnull +64 -> 114
    //   53: aload 4
    //   55: ifnull +10 -> 65
    //   58: aload 4
    //   60: invokeinterface 87 1 0
    //   65: aload 5
    //   67: areturn
    //   68: aload_2
    //   69: invokevirtual 320	java/lang/String:length	()I
    //   72: ifne +11 -> 83
    //   75: ldc_w 313
    //   78: astore 8
    //   80: goto -55 -> 25
    //   83: new 203	java/lang/StringBuilder
    //   86: dup
    //   87: invokespecial 204	java/lang/StringBuilder:<init>	()V
    //   90: aload_2
    //   91: invokevirtual 210	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: ldc_w 322
    //   97: invokevirtual 210	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: ldc_w 313
    //   103: invokevirtual 210	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   109: astore 8
    //   111: goto -86 -> 25
    //   114: aload 4
    //   116: invokeinterface 76 1 0
    //   121: ifeq -68 -> 53
    //   124: new 324	java/util/LinkedList
    //   127: dup
    //   128: invokespecial 325	java/util/LinkedList:<init>	()V
    //   131: astore 10
    //   133: aload 10
    //   135: aload_0
    //   136: aload 4
    //   138: invokevirtual 327	com/vlingo/midas/memo/SNoteUtil:getMemo	(Landroid/database/Cursor;)Lcom/vlingo/core/internal/memo/Memo;
    //   141: invokeinterface 332 2 0
    //   146: pop
    //   147: aload 4
    //   149: invokeinterface 335 1 0
    //   154: istore 12
    //   156: iload 12
    //   158: ifne -25 -> 133
    //   161: aload 10
    //   163: astore 5
    //   165: goto -112 -> 53
    //   168: astore 6
    //   170: aload 4
    //   172: ifnull +10 -> 182
    //   175: aload 4
    //   177: invokeinterface 87 1 0
    //   182: aload 6
    //   184: athrow
    //   185: astore 6
    //   187: goto -17 -> 170
    //
    // Exception table:
    //   from	to	target	type
    //   6	44	168	finally
    //   68	133	168	finally
    //   133	156	185	finally
  }

  public Memo getMostRecentlyCreatedMemo(Context paramContext, String paramString)
    throws MemoUtilException
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "name";
    return getMostRecentlyCreatedMemo(paramContext, paramString, arrayOfString, "ModifiedTime DESC");
  }

  protected String[] getProjection()
  {
    return PROJECTION;
  }

  public String getViewMemoAction()
  {
    return "android.intent.action.VOICETALK_VIEW_SMEMO";
  }

  public List<Memo> searchMemos(Context paramContext, String paramString)
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "name";
    return searchMemos(paramContext, paramString, arrayOfString, "ModifiedTime DESC");
  }

  public void updateMemo(Context paramContext, Memo paramMemo1, Memo paramMemo2)
    throws MemoUtilException
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("name", paramMemo2.getContent());
    Uri localUri = getUriFromId(paramMemo1.getId());
    if (paramContext.getContentResolver().update(localUri, localContentValues, null, null) != 1)
      throw new MemoUtilException("Error in updating memo.");
  }

  protected static class MemoIndices
  {
    public final int KEY_DATE_COL;
    final int KEY_ID_COL;
    final int KEY_NAME_COL;
    final int KEY_PATH_COL;

    public MemoIndices(Cursor paramCursor)
    {
      this.KEY_ID_COL = paramCursor.getColumnIndexOrThrow("_id");
      this.KEY_PATH_COL = paramCursor.getColumnIndexOrThrow("path");
      this.KEY_NAME_COL = paramCursor.getColumnIndexOrThrow("name");
      this.KEY_DATE_COL = paramCursor.getColumnIndexOrThrow("ModifiedTime");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.memo.SNoteUtil
 * JD-Core Version:    0.6.0
 */