package com.vlingo.core.internal.dialogmanager.vvs;

import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.util.Alarm;
import com.vlingo.sdk.recognition.VLAction;

public abstract interface VVSWidgetBuilder
{
  public abstract void showWidget(WidgetUtil.WidgetKey paramWidgetKey, VLAction paramVLAction, Object paramObject, WidgetActionListener paramWidgetActionListener);

  public abstract void showWidget(Alarm paramAlarm, WidgetActionListener paramWidgetActionListener);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.VVSWidgetBuilder
 * JD-Core Version:    0.6.0
 */