package com.vlingo.midas.phrasespotter.dialog;

import com.vlingo.midas.gui.ConversationActivity;
import com.vlingo.midas.gui.Widget;

public class WidgetObject<T>
  implements DialogObject
{
  private Widget<T> widget;

  public WidgetObject(Widget<T> paramWidget)
  {
    this.widget = paramWidget;
  }

  public void execute(ConversationActivity paramConversationActivity)
  {
    paramConversationActivity.addWidget(this.widget);
  }

  public boolean mustBeShown()
  {
    return this.widget.mustBeShown();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.phrasespotter.dialog.WidgetObject
 * JD-Core Version:    0.6.0
 */