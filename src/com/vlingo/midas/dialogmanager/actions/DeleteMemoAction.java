package com.vlingo.midas.dialogmanager.actions;

import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.DeleteMemoInterface;
import com.vlingo.core.internal.memo.IMemoUtil;
import com.vlingo.core.internal.memo.Memo;
import com.vlingo.core.internal.memo.MemoUtilException;
import com.vlingo.midas.memo.MemoManager;

public class DeleteMemoAction extends DMAction
  implements DeleteMemoInterface
{
  private Memo memo;

  protected void execute()
  {
    try
    {
      MemoManager.getMemoUtil().deleteMemo(getContext(), this.memo.getId());
      getListener().actionSuccess();
      return;
    }
    catch (MemoUtilException localMemoUtilException)
    {
      while (true)
      {
        localMemoUtilException.printStackTrace();
        getListener().actionFail(localMemoUtilException.getMessage());
      }
    }
  }

  public DeleteMemoInterface memo(Memo paramMemo)
  {
    this.memo = paramMemo;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.actions.DeleteMemoAction
 * JD-Core Version:    0.6.0
 */