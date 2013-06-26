package com.vlingo.midas.memo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import com.vlingo.core.internal.dialogmanager.util.IntegratedAppInfoInterface;
import com.vlingo.core.internal.memo.IMemoUtil;
import com.vlingo.core.internal.memo.Memo;
import com.vlingo.core.internal.memo.MemoUtilException;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import java.util.Iterator;
import java.util.List;

public class SMemo2Util extends TMemoUtil
  implements IMemoUtil
{
  private static final String KEY_CONTENT = "content";
  private static final String KEY_DATE = "ModifiedTime";
  private static final String KEY_HAS_VOICERECORDER = "HasVoiceRecord";
  private static final String KEY_ID = "_id";
  private static final String KEY_NAME = "name";
  private static final String KEY_TAG_CONTENT = "Tag_Content";
  private static final String KEY_TEMPLATE_TIME = "TemplateType";
  private static final String[] PROJECTION;
  private static final String SORT_ORDER = "ModifiedTime DESC";
  private static IMemoUtil instance;
  private static final String strContent = "content";
  private static final String strId = "id";
  private static final String strTitle = "title";

  static
  {
    String[] arrayOfString = new String[5];
    arrayOfString[0] = "_id";
    arrayOfString[1] = "name";
    arrayOfString[2] = "ModifiedTime";
    arrayOfString[3] = "content";
    arrayOfString[4] = "Tag_Content";
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
      instance = new SMemo2Util();
    return instance;
  }

  private Memo getMemo(Cursor paramCursor, MemoIndices paramMemoIndices)
  {
    Memo localMemo = new Memo();
    localMemo.setTitle(paramCursor.getString(paramMemoIndices.KEY_NAME_COL));
    localMemo.setContent(paramCursor.getString(paramMemoIndices.KEY_CONTENT_COL));
    localMemo.setText(paramCursor.getString(paramMemoIndices.KEY_CONTENT_COL));
    localMemo.setId(paramCursor.getInt(paramMemoIndices.KEY_ID_COL));
    localMemo.setDate(paramCursor.getString(paramMemoIndices.KEY_DATE_COL));
    return localMemo;
  }

  public static int getPackageVersionCode(Context paramContext, String paramString)
  {
    try
    {
      i = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (Exception localException)
    {
      while (true)
        int i = -1;
    }
  }

  private Uri getUriFromId(long paramLong)
  {
    return ContentUris.withAppendedId(ClientSuppliedValues.getMemoApplicationInfo().getUpdateContentProviderUri(), paramLong);
  }

  public static boolean isAppInstalled(String paramString, int paramInt)
  {
    int i = 0;
    PackageManager localPackageManager = ApplicationAdapter.getInstance().getApplicationContext().getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    Iterator localIterator = localPackageManager.queryIntentActivities(localIntent, 0).iterator();
    while (localIterator.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
      localResolveInfo.activityInfo.packageName.equalsIgnoreCase(paramString);
      localResolveInfo.activityInfo.packageName.equals(paramString);
      if ((localResolveInfo.activityInfo.packageName == null) || (!localResolveInfo.activityInfo.packageName.equalsIgnoreCase(paramString)))
        continue;
      i = 1;
    }
    return i;
  }

  public static boolean isInstalled()
  {
    int i = 0;
    boolean bool1 = isAppInstalled("com.sec.android.widgetapp.diotek.smemo", 1);
    boolean bool2 = isAppInstalled("com.sec.android.app.memo", 1);
    if ((bool1) || (bool2));
    try
    {
      ApplicationAdapter.getInstance().getApplicationContext().getPackageManager().getApplicationInfo("com.sec.android.provider.snote", 8192);
      i = 1;
      label44: return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      break label44;
    }
  }

  public void deleteMemo(Context paramContext, long paramLong)
    throws MemoUtilException
  {
    Intent localIntent = new Intent(ClientSuppliedValues.getMemoApplicationInfo().getIntentNameDelete());
    localIntent.putExtra("id", paramLong);
    if (ClientSuppliedValues.getMemoApplicationInfo().isBroadcast())
      paramContext.sendBroadcast(localIntent);
    while (true)
    {
      return;
      paramContext.startActivity(localIntent);
    }
  }

  public String getCreateMemoAction()
  {
    return "com.sec.android.memo.CREATE_TMEMO";
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
    //   6: invokestatic 160	com/vlingo/core/internal/util/ClientSuppliedValues:getMemoApplicationInfo	()Lcom/vlingo/core/internal/dialogmanager/util/IntegratedAppInfoInterface;
    //   9: invokeinterface 284 1 0
    //   14: astore 7
    //   16: aload_2
    //   17: ifnonnull +51 -> 68
    //   20: ldc_w 286
    //   23: astore 8
    //   25: aload_1
    //   26: invokevirtual 64	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   29: aload 7
    //   31: aload_0
    //   32: invokevirtual 290	com/vlingo/midas/memo/SMemo2Util:getProjection	()[Ljava/lang/String;
    //   35: aload 8
    //   37: aconst_null
    //   38: aload_3
    //   39: invokevirtual 74	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   42: astore 9
    //   44: aload 9
    //   46: astore 4
    //   48: aload 4
    //   50: ifnonnull +64 -> 114
    //   53: aload 4
    //   55: ifnull +10 -> 65
    //   58: aload 4
    //   60: invokeinterface 93 1 0
    //   65: aload 5
    //   67: areturn
    //   68: aload_2
    //   69: invokevirtual 294	java/lang/String:length	()I
    //   72: ifne +11 -> 83
    //   75: ldc_w 286
    //   78: astore 8
    //   80: goto -55 -> 25
    //   83: new 296	java/lang/StringBuilder
    //   86: dup
    //   87: invokespecial 297	java/lang/StringBuilder:<init>	()V
    //   90: aload_2
    //   91: invokevirtual 301	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: ldc_w 303
    //   97: invokevirtual 301	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: ldc_w 286
    //   103: invokevirtual 301	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   109: astore 8
    //   111: goto -86 -> 25
    //   114: aload 4
    //   116: invokeinterface 80 1 0
    //   121: ifeq -68 -> 53
    //   124: new 308	java/util/LinkedList
    //   127: dup
    //   128: invokespecial 309	java/util/LinkedList:<init>	()V
    //   131: astore 10
    //   133: aload 10
    //   135: aload_0
    //   136: aload 4
    //   138: invokevirtual 311	com/vlingo/midas/memo/SMemo2Util:getMemo	(Landroid/database/Cursor;)Lcom/vlingo/core/internal/memo/Memo;
    //   141: invokeinterface 314 2 0
    //   146: pop
    //   147: aload 4
    //   149: invokeinterface 317 1 0
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
    //   177: invokeinterface 93 1 0
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
    return "com.sec.android.memo.OPEN_ID";
  }

  public void saveMemoData(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(ClientSuppliedValues.getMemoApplicationInfo().getIntentNameCreate());
    localIntent.putExtra("title", paramString);
    localIntent.putExtra("content", paramString);
    if (ClientSuppliedValues.getMemoApplicationInfo().isBroadcast())
      paramContext.sendBroadcast(localIntent);
    while (true)
    {
      return;
      paramContext.startActivity(localIntent);
    }
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
    final int KEY_CONTENT_COL;
    final int KEY_DATE_COL;
    final int KEY_ID_COL;
    final int KEY_NAME_COL;
    final int KEY_TAG_CONTENT_COL;

    public MemoIndices(Cursor paramCursor)
    {
      this.KEY_ID_COL = paramCursor.getColumnIndexOrThrow("_id");
      this.KEY_NAME_COL = paramCursor.getColumnIndexOrThrow("name");
      this.KEY_DATE_COL = paramCursor.getColumnIndexOrThrow("ModifiedTime");
      this.KEY_CONTENT_COL = paramCursor.getColumnIndexOrThrow("content");
      this.KEY_TAG_CONTENT_COL = paramCursor.getColumnIndexOrThrow("Tag_Content");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.memo.SMemo2Util
 * JD-Core Version:    0.6.0
 */