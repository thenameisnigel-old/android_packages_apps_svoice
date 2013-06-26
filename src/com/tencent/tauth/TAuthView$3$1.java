package com.tencent.tauth;

import com.tencent.tauth.http.Callback;

class TAuthView$3$1
  implements Callback
{
  public void onCancel(int paramInt)
  {
  }

  public void onFail(int paramInt, String paramString)
  {
    TAuthView.3.access$0(this.this$1).runOnUiThread(new TAuthView.3.1.2(this, paramInt, paramString));
  }

  public void onSuccess(Object paramObject)
  {
    TAuthView.3.access$0(this.this$1).runOnUiThread(new TAuthView.3.1.1(this));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.TAuthView.3.1
 * JD-Core Version:    0.6.0
 */