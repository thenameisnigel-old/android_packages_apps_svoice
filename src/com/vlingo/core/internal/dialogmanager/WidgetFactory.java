package com.vlingo.core.internal.dialogmanager;

import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionEavesdropper;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;

public abstract interface WidgetFactory
{
  public abstract void setEavesdropper(WidgetActionEavesdropper paramWidgetActionEavesdropper);

  public abstract <T> void showWidget(WidgetUtil.WidgetKey paramWidgetKey, WidgetDecorator paramWidgetDecorator, T paramT, WidgetActionListener paramWidgetActionListener);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.WidgetFactory
 * JD-Core Version:    0.6.0
 */