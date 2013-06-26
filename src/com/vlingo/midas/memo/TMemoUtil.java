package com.vlingo.midas.memo;

import android.content.ContentResolver;
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

public class TMemoUtil extends MemoUtil
  implements IMemoUtil
{
  protected static final String KEY_CONTENT = "Content";
  protected static final String KEY_CREATE = "create_t";
  protected static final String KEY_ID = "_id";
  private static final String[] PROJECTION;
  private static final String SORT_ORDER = "create_t DESC";
  private static IMemoUtil instance;

  static
  {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "_id";
    arrayOfString[1] = "Content";
    arrayOfString[2] = "create_t";
    PROJECTION = arrayOfString;
    instance = null;
  }

  public static IMemoUtil getInstance()
  {
    if (instance == null)
      instance = new TMemoUtil();
    return instance;
  }

  private Memo getMemo(Cursor paramCursor, MemoIndices paramMemoIndices)
  {
    Memo localMemo = new Memo();
    localMemo.setContent(paramCursor.getString(paramMemoIndices.KEY_CONTENT_COL));
    localMemo.setText(localMemo.getContent());
    localMemo.setTitle(null);
    localMemo.setId(paramCursor.getInt(paramMemoIndices.KEY_ID_COL));
    localMemo.setDate(paramCursor.getString(paramMemoIndices.KEY_CREATE_COL));
    return localMemo;
  }

  public static boolean isInstalled()
  {
    return PackageUtil.isAppInstalled("com.sec.android.app.memo", 1);
  }

  public void deleteMemo(Context paramContext, long paramLong)
    throws MemoUtilException
  {
    try
    {
      if (paramContext.getContentResolver().delete(ClientSuppliedValues.getMemoApplicationInfo().getUpdateContentProviderUri(), "_id = " + paramLong, null) != 1)
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

  public Memo getMostRecentlyCreatedMemo(Context paramContext, String paramString)
    throws MemoUtilException
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "Content";
    return getMostRecentlyCreatedMemo(paramContext, paramString, arrayOfString, "create_t DESC");
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
    arrayOfString[0] = "Content";
    return searchMemos(paramContext, paramString, arrayOfString, "create_t DESC");
  }

  public void updateMemo(Context paramContext, Memo paramMemo1, Memo paramMemo2)
    throws MemoUtilException
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("Content", paramMemo2.getContent());
    Uri localUri = ClientSuppliedValues.getMemoApplicationInfo().getUpdateContentProviderUri();
    if (paramContext.getContentResolver().update(localUri, localContentValues, "_id = " + paramMemo2.getId(), null) != 1)
      throw new MemoUtilException("Error in updating memo.");
  }

  protected static class MemoIndices
  {
    final int KEY_CONTENT_COL;
    final int KEY_CREATE_COL;
    final int KEY_ID_COL;

    public MemoIndices(Cursor paramCursor)
    {
      this.KEY_ID_COL = paramCursor.getColumnIndexOrThrow("_id");
      this.KEY_CONTENT_COL = paramCursor.getColumnIndexOrThrow("Content");
      this.KEY_CREATE_COL = paramCursor.getColumnIndexOrThrow("create_t");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.memo.TMemoUtil
 * JD-Core Version:    0.6.0
 */