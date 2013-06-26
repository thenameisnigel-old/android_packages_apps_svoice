package com.vlingo.midas.gui.widgets;

import android.graphics.drawable.Drawable;
import android.widget.ProgressBar;
import com.vlingo.core.internal.questions.DownloadableImage;

class AnswerQuestionWidget$3$1
  implements Runnable
{
  public void run()
  {
    this.this$1.val$indicator.setVisibility(4);
    AnswerQuestionWidget.RenderedImage localRenderedImage = this.this$1.val$ri;
    if (this.val$downloaded);
    for (Drawable localDrawable = this.val$image.getDrawable(); ; localDrawable = null)
    {
      localRenderedImage.setImageDrawable(localDrawable);
      return;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.AnswerQuestionWidget.3.1
 * JD-Core Version:    0.6.0
 */