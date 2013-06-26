package com.facebook.android;

import android.webkit.WebView;
import android.widget.TextView;

class FbDialog$FbWebViewClient$1
  implements Runnable
{
  public void run()
  {
    String str = FbDialog.access$400(this.this$1.this$0).getTitle();
    if ((str != null) && (str.length() > 0))
      FbDialog.access$500(this.this$1.this$0).setText(str);
    FbDialog.access$600(this.this$1.this$0);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.facebook.android.FbDialog.FbWebViewClient.1
 * JD-Core Version:    0.6.0
 */