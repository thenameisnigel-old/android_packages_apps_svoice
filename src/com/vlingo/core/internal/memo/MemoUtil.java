package com.vlingo.core.internal.memo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.net.Uri;
import android.text.DynamicLayout;
import android.text.Layout.Alignment;
import android.text.TextPaint;
import com.vlingo.core.internal.dialogmanager.util.IntegratedAppInfoInterface;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.StringUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class MemoUtil
  implements IMemoUtil
{
  private static final int CANVAS_HEIGHT = 1210;
  private static final int CANVAS_WIDTH = 784;
  private static final float DEFAULT_PORT_LINE_HEIGHT = 47.0F;
  private static final float DEFAULT_PORT_TEXT_SIZE = 38.0F;
  private static final int DEFAULT_TEXT_PADDING = 26;
  private static final int DEFAULT_TEXT_PADDING_LEFT = 26;
  private static final int DEFAULT_TEXT_PADDING_TOP = 100;
  private static final int DEFAULT_THUMB_HEIGHT = 298;
  private static final int DEFAULT_THUMB_WIDTH = 226;
  protected static final String KEY_THUMB = "Thumb";
  private static final String strExtraName = "Memo_Text";
  private static final String strFileName = "FileName";
  private String memoXML;

  protected static void buildThumbnail(ContentValues paramContentValues, String paramString)
  {
    try
    {
      Bitmap localBitmap1 = Bitmap.createBitmap(784, 1210, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap1);
      TextPaint localTextPaint = new TextPaint(1);
      localTextPaint.setTextSize(38.0F);
      localTextPaint.setColor(-16777216);
      int i = (int)(47.0F - new DynamicLayout("foo", localTextPaint, 100, null, 1.0F, 0.0F, false).getHeight());
      DynamicLayout localDynamicLayout = new DynamicLayout(paramString, localTextPaint, 784, Layout.Alignment.ALIGN_NORMAL, 1.0F, i, true);
      localDynamicLayout.getPaint().baselineShift = 26;
      localCanvas.save();
      localCanvas.translate(26.0F, 100.0F);
      localDynamicLayout.draw(localCanvas);
      localCanvas.restore();
      Bitmap localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, 226, 298, true);
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      localBitmap2.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
      paramContentValues.put("Thumb", localByteArrayOutputStream.toByteArray());
      localBitmap2.recycle();
      localBitmap1.recycle();
      localByteArrayOutputStream.close();
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  private String getWhere(WhereType paramWhereType, String paramString, String[] paramArrayOfString)
  {
    StringBuilder localStringBuilder4;
    int i3;
    String str1;
    if (paramString != null)
    {
      String[] arrayOfString;
      String str5;
      int i4;
      int i5;
      switch (1.$SwitchMap$com$vlingo$core$internal$memo$MemoUtil$WhereType[paramWhereType.ordinal()])
      {
      default:
        arrayOfString = paramString.split("\\s+");
        localStringBuilder4 = new StringBuilder();
        int i2 = paramArrayOfString.length;
        i3 = 0;
        if (i3 >= i2)
          break;
        str5 = paramArrayOfString[i3];
        i4 = arrayOfString.length;
        i5 = 0;
      case 1:
        while (i5 < i4)
        {
          String str6 = arrayOfString[i5];
          if (localStringBuilder4.length() != 0)
            localStringBuilder4.append(" or ");
          String str7 = str6.replace("'", "''");
          localStringBuilder4.append(str5 + " LIKE '%" + str7 + "%'");
          i5++;
          continue;
          StringBuilder localStringBuilder3 = new StringBuilder();
          int n = paramArrayOfString.length;
          for (int i1 = 0; i1 < n; i1++)
          {
            String str4 = paramArrayOfString[i1];
            if (localStringBuilder3.length() != 0)
              localStringBuilder3.append(" or ");
            paramString = paramString.replace("'", "''");
            localStringBuilder3.append(str4 + " LIKE '" + paramString + "'");
          }
          str1 = localStringBuilder3.toString();
        }
      case 2:
      }
    }
    while (true)
    {
      return str1;
      StringBuilder localStringBuilder2 = new StringBuilder();
      int k = paramArrayOfString.length;
      for (int m = 0; m < k; m++)
      {
        String str3 = paramArrayOfString[m];
        if (localStringBuilder2.length() != 0)
          localStringBuilder2.append(" or ");
        paramString = paramString.replace("'", "''");
        localStringBuilder2.append(str3 + " LIKE '%" + paramString + "%'");
      }
      str1 = localStringBuilder2.toString();
      continue;
      i3++;
      break;
      str1 = localStringBuilder4.toString();
      continue;
      switch (1.$SwitchMap$com$vlingo$core$internal$memo$MemoUtil$WhereType[paramWhereType.ordinal()])
      {
      default:
        str1 = null;
        break;
      case 1:
        StringBuilder localStringBuilder1 = new StringBuilder();
        int i = paramArrayOfString.length;
        for (int j = 0; j < i; j++)
        {
          String str2 = paramArrayOfString[j];
          if (localStringBuilder1.length() != 0)
            localStringBuilder1.append(" or ");
          localStringBuilder1.append(str2 + " not null");
        }
        str1 = localStringBuilder1.toString();
      }
    }
  }

  public abstract String getCreateMemoAction();

  public Memo getMemo(Context paramContext, long paramLong)
    throws MemoUtilException
  {
    Cursor localCursor = paramContext.getContentResolver().query(ContentUris.withAppendedId(ClientSuppliedValues.getMemoApplicationInfo().getContentProviderUri(), paramLong), getProjection(), null, null, null);
    int i = localCursor.getCount();
    if ((localCursor == null) || (i < 1))
      throw new MemoUtilException("Error in getting memo.");
    Memo localMemo;
    if (localCursor.moveToFirst())
    {
      localMemo = getMemo(localCursor);
      if (localCursor != null)
        localCursor.close();
    }
    while (true)
    {
      return localMemo;
      localMemo = null;
    }
  }

  protected abstract Memo getMemo(Cursor paramCursor);

  public List<Memo> getMemos(Context paramContext, String paramString)
  {
    return getMemos(paramContext, paramString, null);
  }

  // ERROR //
  public List<Memo> getMemos(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: invokestatic 224	com/vlingo/core/internal/util/ClientSuppliedValues:getMemoApplicationInfo	()Lcom/vlingo/core/internal/dialogmanager/util/IntegratedAppInfoInterface;
    //   9: invokeinterface 230 1 0
    //   14: astore 7
    //   16: aload_1
    //   17: invokevirtual 218	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: aload 7
    //   22: aload_0
    //   23: invokevirtual 240	com/vlingo/core/internal/memo/MemoUtil:getProjection	()[Ljava/lang/String;
    //   26: aload_2
    //   27: aconst_null
    //   28: aload_3
    //   29: invokevirtual 246	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   32: astore 8
    //   34: aload 8
    //   36: astore 4
    //   38: aload 4
    //   40: ifnonnull +18 -> 58
    //   43: aload 4
    //   45: ifnull +10 -> 55
    //   48: aload 4
    //   50: invokeinterface 264 1 0
    //   55: aload 5
    //   57: areturn
    //   58: aload 4
    //   60: invokeinterface 260 1 0
    //   65: ifeq -22 -> 43
    //   68: new 271	java/util/LinkedList
    //   71: dup
    //   72: invokespecial 272	java/util/LinkedList:<init>	()V
    //   75: astore 9
    //   77: aload 9
    //   79: aload_0
    //   80: aload 4
    //   82: invokevirtual 263	com/vlingo/core/internal/memo/MemoUtil:getMemo	(Landroid/database/Cursor;)Lcom/vlingo/core/internal/memo/Memo;
    //   85: invokeinterface 278 2 0
    //   90: pop
    //   91: aload 4
    //   93: invokeinterface 281 1 0
    //   98: istore 11
    //   100: iload 11
    //   102: ifne -25 -> 77
    //   105: aload 9
    //   107: astore 5
    //   109: goto -66 -> 43
    //   112: astore 6
    //   114: aload 4
    //   116: ifnull +10 -> 126
    //   119: aload 4
    //   121: invokeinterface 264 1 0
    //   126: aload 6
    //   128: athrow
    //   129: astore 6
    //   131: goto -17 -> 114
    //
    // Exception table:
    //   from	to	target	type
    //   6	34	112	finally
    //   58	77	112	finally
    //   77	100	129	finally
  }

  public abstract Memo getMostRecentlyCreatedMemo(Context paramContext, String paramString)
    throws MemoUtilException;

  protected Memo getMostRecentlyCreatedMemo(Context paramContext, String paramString1, String[] paramArrayOfString, String paramString2)
  {
    Memo localMemo = null;
    List localList = searchMemos(paramContext, paramString1, paramArrayOfString, paramString2);
    if ((localList != null) && (localList.size() >= 1))
      localMemo = (Memo)localList.get(0);
    return localMemo;
  }

  protected abstract String[] getProjection();

  public abstract String getViewMemoAction();

  protected void prepareMemoData()
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      InputStream localInputStream = ClientSuppliedValues.getAssets().open("memo.xml");
      for (int i = localInputStream.read(); i != -1; i = localInputStream.read())
        localByteArrayOutputStream.write(i);
      localInputStream.close();
      this.memoXML = localByteArrayOutputStream.toString();
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public void saveMemoData(Context paramContext, String paramString)
  {
    IntegratedAppInfoInterface localIntegratedAppInfoInterface = ClientSuppliedValues.getMemoApplicationInfo();
    Intent localIntent = new Intent(localIntegratedAppInfoInterface.getIntentNameCreate());
    localIntent.putExtra("Memo_Text", paramString);
    if (localIntegratedAppInfoInterface.isSNote())
      localIntent.putExtra("FileName", paramString);
    if (localIntegratedAppInfoInterface.isBroadcast())
      paramContext.sendBroadcast(localIntent);
    while (true)
    {
      return;
      paramContext.startActivity(localIntent);
    }
  }

  public abstract List<Memo> searchMemos(Context paramContext, String paramString);

  protected List<Memo> searchMemos(Context paramContext, String paramString1, String[] paramArrayOfString, String paramString2)
  {
    String str1 = getWhere(WhereType.EXACT, paramString1, paramArrayOfString);
    String str2 = getWhere(WhereType.CONTAINS, paramString1, paramArrayOfString);
    String str3 = getWhere(WhereType.FALLBACK, paramString1, paramArrayOfString);
    List localList = getMemos(paramContext, str1, paramString2);
    LinkedList localLinkedList;
    if (localList == null)
    {
      localList = getMemos(paramContext, str2, paramString2);
      if (localList == null)
      {
        localList = getMemos(paramContext, str3, paramString2);
        if (localList == null)
          localLinkedList = null;
      }
    }
    while (true)
    {
      return localLinkedList;
      localLinkedList = new LinkedList();
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        Memo localMemo = (Memo)localIterator.next();
        if (StringUtils.isNullOrWhiteSpace(localMemo.getMemoName(false)))
          continue;
        localLinkedList.add(localMemo);
      }
      if (!localLinkedList.isEmpty())
        continue;
      localLinkedList = null;
    }
  }

  public abstract void updateMemo(Context paramContext, Memo paramMemo1, Memo paramMemo2)
    throws MemoUtilException;

  static final class Provider
  {
    public Query query;
    public Uri uri;

    public Provider(String paramString, Query paramQuery)
    {
      this.uri = Uri.parse(paramString);
      this.query = paramQuery;
    }

    public ContentValues values()
    {
      return this.query.build();
    }

    static abstract interface Query
    {
      public abstract ContentValues build();
    }
  }

  private static enum WhereType
  {
    static
    {
      CONTAINS = new WhereType("CONTAINS", 1);
      FALLBACK = new WhereType("FALLBACK", 2);
      WhereType[] arrayOfWhereType = new WhereType[3];
      arrayOfWhereType[0] = EXACT;
      arrayOfWhereType[1] = CONTAINS;
      arrayOfWhereType[2] = FALLBACK;
      $VALUES = arrayOfWhereType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.memo.MemoUtil
 * JD-Core Version:    0.6.0
 */