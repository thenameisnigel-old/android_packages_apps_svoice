package com.vlingo.core.internal.questions.parser;

import com.vlingo.core.internal.questions.DownloadableImage;
import java.util.concurrent.ConcurrentLinkedQueue;

class AnswerParser$4$1 extends Thread
{
  private String url = this.val$image.getURL();

  public void run()
  {
    AnswerParser.access$500(this.this$1.this$0).add(this);
    this.val$image.setDrawable(AnswerParser.access$600(this.this$1.this$0, this.url));
    AnswerParser.access$500(this.this$1.this$0).remove(this);
    synchronized (AnswerParser.access$500(this.this$1.this$0))
    {
      AnswerParser.access$500(this.this$1.this$0).notifyAll();
      return;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.questions.parser.AnswerParser.4.1
 * JD-Core Version:    0.6.0
 */