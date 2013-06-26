package com.vlingo.midas.memo;

import com.vlingo.core.internal.memo.IMemoUtil;

public class MemoManager
{
  public static IMemoUtil getMemoUtil()
  {
    IMemoUtil localIMemoUtil;
    if (SMemo2Util.isInstalled())
      localIMemoUtil = SMemo2Util.getInstance();
    while (true)
    {
      return localIMemoUtil;
      if (TMemoUtil.isInstalled())
      {
        localIMemoUtil = TMemoUtil.getInstance();
        continue;
      }
      if (SNoteUtil.isInstalled())
      {
        localIMemoUtil = SNoteUtil.getInstance();
        continue;
      }
      localIMemoUtil = SMemoUtil.getInstance();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.memo.MemoManager
 * JD-Core Version:    0.6.0
 */