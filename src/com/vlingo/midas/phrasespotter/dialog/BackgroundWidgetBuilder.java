package com.vlingo.midas.phrasespotter.dialog;

import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionEavesdropper;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.gui.WidgetBuilder;
import com.vlingo.midas.phrasespotter.SeamlessRecoService;

public class BackgroundWidgetBuilder extends WidgetBuilder
{
  private SeamlessRecoService srs;

  public BackgroundWidgetBuilder(SeamlessRecoService paramSeamlessRecoService)
  {
    super(null, paramSeamlessRecoService);
    this.srs = paramSeamlessRecoService;
  }

  public void setEavesdropper(WidgetActionEavesdropper paramWidgetActionEavesdropper)
  {
  }

  public <T> void showWidget(WidgetUtil.WidgetKey paramWidgetKey, WidgetDecorator paramWidgetDecorator, T paramT, WidgetActionListener paramWidgetActionListener)
  {
    this.srs.addWidget(new WidgetObject(buildWidget(paramWidgetKey, paramWidgetDecorator, paramT, paramWidgetActionListener)));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.phrasespotter.dialog.BackgroundWidgetBuilder
 * JD-Core Version:    0.6.0
 */