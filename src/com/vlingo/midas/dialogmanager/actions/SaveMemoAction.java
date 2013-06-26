package com.vlingo.midas.dialogmanager.actions;

import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.MemoInterface;
import com.vlingo.core.internal.memo.IMemoUtil;
import com.vlingo.midas.memo.MemoManager;

public class SaveMemoAction extends DMAction
  implements MemoInterface
{
  private String memo;

  protected void execute()
  {
    if (this.memo == null)
      getListener().actionFail("missing memo");
    while (true)
    {
      return;
      MemoManager.getMemoUtil().saveMemoData(getContext(), this.memo);
      getListener().actionSuccess();
    }
  }

  public MemoInterface memo(String paramString)
  {
    this.memo = paramString;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.actions.SaveMemoAction
 * JD-Core Version:    0.6.0
 */