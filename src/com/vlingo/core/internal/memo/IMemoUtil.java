package com.vlingo.core.internal.memo;

import android.content.Context;
import java.util.List;

public abstract interface IMemoUtil
{
  public abstract void deleteMemo(Context paramContext, long paramLong)
    throws MemoUtilException;

  public abstract String getCreateMemoAction();

  public abstract Memo getMemo(Context paramContext, long paramLong)
    throws MemoUtilException;

  public abstract List<Memo> getMemos(Context paramContext, String paramString);

  public abstract List<Memo> getMemos(Context paramContext, String paramString1, String paramString2);

  public abstract Memo getMostRecentlyCreatedMemo(Context paramContext, String paramString)
    throws MemoUtilException;

  public abstract String getViewMemoAction();

  public abstract void saveMemoData(Context paramContext, String paramString);

  public abstract List<Memo> searchMemos(Context paramContext, String paramString);

  public abstract void updateMemo(Context paramContext, Memo paramMemo1, Memo paramMemo2)
    throws MemoUtilException;
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.memo.IMemoUtil
 * JD-Core Version:    0.6.0
 */