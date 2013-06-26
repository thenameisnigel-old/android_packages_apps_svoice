package com.vlingo.core.internal.social.api;

import android.webkit.WebView;
import android.widget.TextView;

class TwitterDialog$TwitterWebViewClient$1
  implements Runnable
{
  public void run()
  {
    if (TwitterDialog.access$400(this.this$1.this$0))
    {
      String str = TwitterDialog.access$500(this.this$1.this$0).getTitle();
      if ((str != null) && (str.length() > 0))
        TwitterDialog.access$600(this.this$1.this$0).setText(str);
      TwitterDialog.access$700(this.this$1.this$0);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.social.api.TwitterDialog.TwitterWebViewClient.1
 * JD-Core Version:    0.6.0
 */