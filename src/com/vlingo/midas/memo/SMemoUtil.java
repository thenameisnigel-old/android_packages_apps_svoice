package com.vlingo.midas.memo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.vlingo.core.internal.dialogmanager.util.IntegratedAppInfoInterface;
import com.vlingo.core.internal.memo.IMemoUtil;
import com.vlingo.core.internal.memo.Memo;
import com.vlingo.core.internal.memo.MemoUtil;
import com.vlingo.core.internal.memo.MemoUtilException;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.sdk.internal.util.PackageUtil;
import java.util.List;

public class SMemoUtil extends MemoUtil
  implements IMemoUtil
{
  private static final String KEY_CONTENT = "Content";
  private static final String KEY_DATE = "Date";
  private static final String KEY_ID = "_id";
  private static final String KEY_TEXT = "Text";
  private static final String KEY_TITLE = "Title";
  private static final String[] PROJECTION;
  private static final String SORT_ORDER = "Date DESC";
  private static IMemoUtil instance;

  static
  {
    String[] arrayOfString = new String[5];
    arrayOfString[0] = "_id";
    arrayOfString[1] = "Title";
    arrayOfString[2] = "Text";
    arrayOfString[3] = "Content";
    arrayOfString[4] = "Date";
    PROJECTION = arrayOfString;
    instance = null;
  }

  public static IMemoUtil getInstance()
  {
    if (instance == null)
      instance = new SMemoUtil();
    return instance;
  }

  private Memo getMemo(Cursor paramCursor, MemoIndices paramMemoIndices)
  {
    Memo localMemo = new Memo();
    localMemo.setTitle(paramCursor.getString(paramMemoIndices.KEY_TITLE_COL));
    localMemo.setContent(paramCursor.getString(paramMemoIndices.KEY_CONTENT_COL));
    localMemo.setText(paramCursor.getString(paramMemoIndices.KEY_TEXT_COL));
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
    return PackageUtil.isAppInstalled("com.sec.android.widgetapp.diotek.smemo", 1);
  }

  public void deleteMemo(Context paramContext, long paramLong)
    throws MemoUtilException
  {
    try
    {
      if (paramContext.getContentResolver().delete(getUriFromId(paramLong), null, null) != 1)
        throw new MemoUtilException("Error in deleting a memo.");
    }
    catch (MemoUtilException localMemoUtilException)
    {
      Log.e("MemoUtilException:", localMemoUtilException.getMessage());
      localMemoUtilException.printStackTrace();
      throw localMemoUtilException;
    }
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
    //   6: aload_2
    //   7: ifnonnull +60 -> 67
    //   10: ldc 174
    //   12: astore 7
    //   14: invokestatic 102	com/vlingo/core/internal/util/ClientSuppliedValues:getMemoApplicationInfo	()Lcom/vlingo/core/internal/dialogmanager/util/IntegratedAppInfoInterface;
    //   17: invokeinterface 177 1 0
    //   22: astore 8
    //   24: aload_1
    //   25: invokevirtual 134	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   28: aload 8
    //   30: aload_0
    //   31: invokevirtual 181	com/vlingo/midas/memo/SMemoUtil:getProjection	()[Ljava/lang/String;
    //   34: aload 7
    //   36: aconst_null
    //   37: aload_3
    //   38: invokevirtual 185	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   41: astore 9
    //   43: aload 9
    //   45: astore 4
    //   47: aload 4
    //   49: ifnonnull +66 -> 115
    //   52: aload 4
    //   54: ifnull +10 -> 64
    //   57: aload 4
    //   59: invokeinterface 188 1 0
    //   64: aload 5
    //   66: areturn
    //   67: aload_2
    //   68: invokevirtual 192	java/lang/String:length	()I
    //   71: ifne +10 -> 81
    //   74: ldc 174
    //   76: astore 7
    //   78: goto -64 -> 14
    //   81: new 194	java/lang/StringBuilder
    //   84: dup
    //   85: invokespecial 195	java/lang/StringBuilder:<init>	()V
    //   88: ldc 197
    //   90: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: aload_2
    //   94: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: ldc 203
    //   99: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: ldc 205
    //   104: invokevirtual 201	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   110: astore 7
    //   112: goto -98 -> 14
    //   115: aload 4
    //   117: invokeinterface 211 1 0
    //   122: ifeq -70 -> 52
    //   125: new 213	java/util/LinkedList
    //   128: dup
    //   129: invokespecial 214	java/util/LinkedList:<init>	()V
    //   132: astore 10
    //   134: aload 10
    //   136: aload_0
    //   137: aload 4
    //   139: invokevirtual 216	com/vlingo/midas/memo/SMemoUtil:getMemo	(Landroid/database/Cursor;)Lcom/vlingo/core/internal/memo/Memo;
    //   142: invokeinterface 222 2 0
    //   147: pop
    //   148: aload 4
    //   150: invokeinterface 225 1 0
    //   155: istore 12
    //   157: iload 12
    //   159: ifne -25 -> 134
    //   162: aload 10
    //   164: astore 5
    //   166: goto -114 -> 52
    //   169: astore 6
    //   171: aload 4
    //   173: ifnull +10 -> 183
    //   176: aload 4
    //   178: invokeinterface 188 1 0
    //   183: aload 6
    //   185: athrow
    //   186: astore 6
    //   188: goto -17 -> 171
    //
    // Exception table:
    //   from	to	target	type
    //   10	43	169	finally
    //   67	134	169	finally
    //   134	157	186	finally
  }

  public Memo getMostRecentlyCreatedMemo(Context paramContext, String paramString)
    throws MemoUtilException
  {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "Title";
    arrayOfString[1] = "Text";
    arrayOfString[2] = "Content";
    return getMostRecentlyCreatedMemo(paramContext, paramString, arrayOfString, "Date DESC");
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
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "Title";
    arrayOfString[1] = "Text";
    arrayOfString[2] = "Content";
    return searchMemos(paramContext, paramString, arrayOfString, "Date DESC");
  }

  public void updateMemo(Context paramContext, Memo paramMemo1, Memo paramMemo2)
    throws MemoUtilException
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("Title", paramMemo2.getTitle());
    localContentValues.put("Content", paramMemo2.getContent());
    Uri localUri = getUriFromId(paramMemo1.getId());
    if (paramContext.getContentResolver().update(localUri, localContentValues, null, null) != 1)
      throw new MemoUtilException("Error in updating memo.");
  }

  protected static class MemoIndices
  {
    final int KEY_CONTENT_COL;
    public final int KEY_DATE_COL;
    final int KEY_ID_COL;
    public final int KEY_TEXT_COL;
    final int KEY_TITLE_COL;

    public MemoIndices(Cursor paramCursor)
    {
      this.KEY_ID_COL = paramCursor.getColumnIndexOrThrow("_id");
      this.KEY_TITLE_COL = paramCursor.getColumnIndexOrThrow("Title");
      this.KEY_CONTENT_COL = paramCursor.getColumnIndexOrThrow("Content");
      this.KEY_TEXT_COL = paramCursor.getColumnIndexOrThrow("Text");
      this.KEY_DATE_COL = paramCursor.getColumnIndexOrThrow("Date");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.memo.SMemoUtil
 * JD-Core Version:    0.6.0
 */