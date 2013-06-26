package com.tencent.tauth;

import android.widget.Toast;

class AddShareView$1$2
  implements Runnable
{
  int real_ret;

  public void run()
  {
    Toast localToast = Toast.makeText(AddShareView.1.access$0(this.this$1), "分享失败，错误信息:" + this.real_ret + ", " + this.val$msg, 0);
    localToast.setGravity(17, 0, 0);
    localToast.show();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.AddShareView.1.2
 * JD-Core Version:    0.6.0
 */