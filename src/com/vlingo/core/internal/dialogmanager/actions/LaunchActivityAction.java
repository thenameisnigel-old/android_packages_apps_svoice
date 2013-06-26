package com.vlingo.core.internal.dialogmanager.actions;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;

public class LaunchActivityAction extends DMAction
{
  private String action;
  private String activityName;
  private String appName;
  private String extras;
  private String packageName;

  public LaunchActivityAction action(String paramString)
  {
    this.action = paramString;
    return this;
  }

  public LaunchActivityAction activity(String paramString)
  {
    this.activityName = paramString;
    return this;
  }

  public LaunchActivityAction app(String paramString)
  {
    this.appName = paramString;
    return this;
  }

  public LaunchActivityAction enclosingPackage(String paramString)
  {
    this.packageName = paramString;
    return this;
  }

  protected void execute()
  {
    Intent localIntent = new Intent();
    int j;
    label75: String str1;
    String str2;
    if (this.action == null)
    {
      localIntent.setAction("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.LAUNCHER");
      localIntent.setComponent(new ComponentName(this.packageName, this.activityName));
      if (this.extras == null)
        break label182;
      String[] arrayOfString1 = this.extras.split(";");
      int i = arrayOfString1.length;
      j = 0;
      if (j >= i)
        break label182;
      String[] arrayOfString2 = arrayOfString1[j].split(",");
      if (arrayOfString2.length == 2)
      {
        str1 = arrayOfString2[0];
        str2 = arrayOfString2[1];
        if (!str2.equals("true"))
          break label149;
        localIntent.putExtra(str1, true);
      }
    }
    while (true)
    {
      j++;
      break label75;
      localIntent.setAction(this.action);
      break;
      label149: if (str2.equals("false"))
      {
        localIntent.putExtra(str1, false);
        continue;
      }
      localIntent.putExtra(str1, str2);
    }
    try
    {
      label182: localIntent.addFlags(270598144);
      getContext().startActivity(localIntent);
      UserLoggingEngine.getInstance().landingPageViewed("launch " + this.appName);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      while (true)
      {
        getListener().actionFail("Cannot start intent " + localIntent);
        getListener().actionSuccess();
      }
    }
    finally
    {
      getListener().actionSuccess();
    }
    throw localObject;
  }

  public LaunchActivityAction extra(String paramString)
  {
    this.extras = paramString;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.LaunchActivityAction
 * JD-Core Version:    0.6.0
 */