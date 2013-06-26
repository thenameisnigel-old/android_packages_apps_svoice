package com.weibo.sdk.android.api;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import java.net.URLEncoder;

public class ActivityInvokeAPI
{
  public static void openContact(Activity paramActivity)
  {
    if (paramActivity == null);
    while (true)
    {
      return;
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setData(Uri.parse("sinaweibo://contact"));
      paramActivity.startActivity(localIntent);
    }
  }

  public static void openDetail(Activity paramActivity, String paramString)
  {
    if (paramActivity == null);
    while (true)
    {
      return;
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setData(Uri.parse("sinaweibo://detail?mblogid=" + paramString));
      paramActivity.startActivity(localIntent);
    }
  }

  public static void openMessageListByNickName(Activity paramActivity, String paramString)
  {
    if (paramActivity == null);
    while (true)
    {
      return;
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setData(Uri.parse("sinaweibo://messagelist?nick=" + paramString));
      paramActivity.startActivity(localIntent);
    }
  }

  public static void openMessageListByUid(Activity paramActivity, String paramString)
  {
    if (paramActivity == null);
    while (true)
    {
      return;
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setData(Uri.parse("sinaweibo://messagelist?uid=" + paramString));
      paramActivity.startActivity(localIntent);
    }
  }

  public static void openNearbyPeople(Activity paramActivity)
  {
    if (paramActivity == null);
    while (true)
    {
      return;
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setData(Uri.parse("sinaweibo://nearbypeople"));
      paramActivity.startActivity(localIntent);
    }
  }

  public static void openNearbyWeibo(Activity paramActivity)
  {
    if (paramActivity == null);
    while (true)
    {
      return;
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setData(Uri.parse("sinaweibo://nearbyweibo"));
      paramActivity.startActivity(localIntent);
    }
  }

  public static void openSendWeibo(Activity paramActivity, String paramString)
  {
    if ((paramActivity == null) || (paramString == null));
    while (true)
    {
      return;
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setData(Uri.parse("sinaweibo://sendweibo?content=" + URLEncoder.encode(paramString)));
      paramActivity.startActivity(localIntent);
    }
  }

  public static void openSendWeibo(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    if (paramActivity == null);
    while (true)
    {
      return;
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setData(Uri.parse("sinaweibo://sendweibo?content=" + paramString1 + "&xid=" + paramString2 + "&poiid=" + paramString3 + "&poiname=" + paramString4 + "&longitude=" + paramString5 + "&latitude=" + paramString6));
      paramActivity.startActivity(localIntent);
    }
  }

  public static void openShake(Activity paramActivity)
  {
    if (paramActivity == null);
    while (true)
    {
      return;
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setData(Uri.parse("sinaweibo://shake"));
      paramActivity.startActivity(localIntent);
    }
  }

  public static void openUserInfoByNickName(Activity paramActivity, String paramString)
  {
    if (paramActivity == null);
    while (true)
    {
      return;
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setData(Uri.parse("sinaweibo://userinfo?nick=" + paramString));
      paramActivity.startActivity(localIntent);
    }
  }

  public static void openUserInfoByUid(Activity paramActivity, String paramString)
  {
    if (paramActivity == null);
    while (true)
    {
      return;
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setData(Uri.parse("sinaweibo://userinfo?uid=" + paramString));
      paramActivity.startActivity(localIntent);
    }
  }

  public static void openUserTrends(Activity paramActivity, String paramString)
  {
    if (paramActivity == null);
    while (true)
    {
      return;
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setData(Uri.parse("sinaweibo://usertrends?uid=" + paramString));
      paramActivity.startActivity(localIntent);
    }
  }

  public static void openWeibo(Activity paramActivity)
  {
    if (paramActivity == null);
    while (true)
    {
      return;
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setData(Uri.parse("sinaweibo://splash"));
      paramActivity.startActivity(localIntent);
    }
  }

  public static void openWeiboBrowser(Activity paramActivity, String paramString)
  {
    if (paramActivity == null);
    while (true)
    {
      return;
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setData(Uri.parse("sinaweibo://browser?url=" + paramString));
      paramActivity.startActivity(localIntent);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.api.ActivityInvokeAPI
 * JD-Core Version:    0.6.0
 */