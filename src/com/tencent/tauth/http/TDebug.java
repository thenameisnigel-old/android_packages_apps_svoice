package com.tencent.tauth.http;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class TDebug
{
  private static boolean bDebugMode = false;

  public static void d(String paramString1, String paramString2)
  {
    if (bDebugMode)
      Log.d(paramString1, paramString2);
  }

  public static void i(String paramString1, String paramString2)
  {
    if (bDebugMode)
      Log.i(paramString1, paramString2);
  }

  public static void msg(String paramString, Context paramContext)
  {
    Toast localToast = Toast.makeText(paramContext, paramString, 0);
    localToast.setGravity(17, 0, 0);
    localToast.show();
  }

  public static void setMode(boolean paramBoolean)
  {
    bDebugMode = paramBoolean;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.http.TDebug
 * JD-Core Version:    0.6.0
 */