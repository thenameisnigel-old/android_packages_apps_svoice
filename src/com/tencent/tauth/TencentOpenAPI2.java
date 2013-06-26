package com.tencent.tauth;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.widget.Toast;
import com.tencent.tauth.http.Callback;

public class TencentOpenAPI2
{
  private static final boolean IfCallQzone = false;
  private static final String TAG = "TencentOpenAPI2";

  public static void logIn(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, Bundle paramBundle, String paramString6)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("ACTION", 9001);
    localBundle.putString("APPID", paramString3);
    localBundle.putString("APPPKGNAME", paramContext.getApplicationInfo().name);
    localBundle.putString("openid", paramString1);
    localBundle.putString("from_cmd", paramString6);
    localBundle.putString("scope", paramString2);
    localBundle.putString("client_id", paramString3);
    localBundle.putString("target", paramString4);
    localBundle.putString("callback", paramString5);
    if ((paramBundle != null) && (paramString6.equals("send_store")))
    {
      String str1 = paramBundle.getString("title");
      String str2 = paramBundle.getString("url");
      String str3 = paramBundle.getString("comment");
      String str4 = paramBundle.getString("summary");
      String str5 = paramBundle.getString("images");
      String str6 = paramBundle.getString("type");
      String str7 = paramBundle.getString("playurl");
      String str8 = paramBundle.getString("source");
      String str9 = paramBundle.getString("site");
      localBundle.putString("title", str1);
      localBundle.putString("url", str2);
      localBundle.putString("comment", str3);
      localBundle.putString("summary", str4);
      localBundle.putString("images", str5);
      localBundle.putString("type", str6);
      localBundle.putString("playurl", str7);
      localBundle.putString("source", str8);
      localBundle.putString("site", str9);
    }
    Intent localIntent = new Intent(paramContext, TAuthView.class);
    localIntent.setFlags(268435456);
    localIntent.putExtras(localBundle);
    paramContext.startActivity(localIntent);
  }

  public static void sendStore(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, Bundle paramBundle, Callback paramCallback, String paramString5)
  {
    Bundle localBundle1 = new Bundle();
    localBundle1.putInt("ACTION", 9002);
    localBundle1.putString("APPID", paramString2);
    localBundle1.putString("APPPKGNAME", paramContext.getApplicationInfo().name);
    localBundle1.putString("OPENID", paramString3);
    if ((paramString5 != null) && ((paramString1 == null) || (paramString3 == null)))
    {
      Toast localToast = Toast.makeText(paramContext, "再次登陆失败，此次分享取消！", 0);
      localToast.setGravity(17, 0, 0);
      localToast.show();
    }
    while (true)
    {
      return;
      if (((paramString1 == null) || (paramString3 == null)) && (paramString5 == null))
      {
        Bundle localBundle2 = new Bundle();
        String str1 = paramBundle.getString("title");
        String str2 = paramBundle.getString("url");
        String str3 = paramBundle.getString("comment");
        String str4 = paramBundle.getString("summary");
        String str5 = paramBundle.getString("images");
        String str6 = paramBundle.getString("type");
        String str7 = paramBundle.getString("playurl");
        String str8 = paramBundle.getString("source");
        String str9 = paramBundle.getString("site");
        localBundle2.putString("title", str1);
        localBundle2.putString("url", str2);
        localBundle2.putString("comment", str3);
        localBundle2.putString("summary", str4);
        localBundle2.putString("images", str5);
        localBundle2.putString("type", str6);
        localBundle2.putString("playurl", str7);
        localBundle2.putString("source", str8);
        localBundle2.putString("site", str9);
        logIn(paramContext, paramString3, "add_share", paramString2, "_self", "auth://tauth.qq.com/", localBundle2, "send_store");
        continue;
      }
      if ((paramString1 == null) || (paramString3 == null))
        continue;
      Intent localIntent = new Intent(paramContext, AddShareView.class);
      localIntent.putExtra("client_id", paramString2);
      localIntent.putExtra("access_token", paramString1);
      localIntent.putExtra("openid", paramString3);
      localIntent.putExtra("target", paramString4);
      localIntent.putExtras(paramBundle);
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.TencentOpenAPI2
 * JD-Core Version:    0.6.0
 */