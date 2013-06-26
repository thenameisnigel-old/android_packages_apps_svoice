package com.tencent.tauth;

import android.widget.Toast;

class TAuthView$3$1$2
  implements Runnable
{
  public void run()
  {
    Toast localToast = Toast.makeText(TAuthView.3.access$0(TAuthView.3.1.access$0(this.this$2)).getBaseContext(), "分享失败，错误信息:" + this.val$ret + ", " + this.val$msg, 0);
    localToast.setGravity(17, 0, 0);
    localToast.show();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.TAuthView.3.1.2
 * JD-Core Version:    0.6.0
 */