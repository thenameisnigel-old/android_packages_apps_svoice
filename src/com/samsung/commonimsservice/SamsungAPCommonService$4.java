package com.samsung.commonimsservice;

import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.util.Log;

class SamsungAPCommonService$4
  implements MediaScannerConnection.OnScanCompletedListener
{
  public void onScanCompleted(String paramString, Uri paramUri)
  {
    Log.d("SamsungAPCommonService", "inside onScanCompleted() : path=" + paramString);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.commonimsservice.SamsungAPCommonService.4
 * JD-Core Version:    0.6.0
 */