package com.vlingo.core.internal.dialogmanager.actions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.internal.util.PackageUtil;

public class ExecuteIntentAction extends DMAction
{
  private String className;
  private String extras;
  private Intent intent;
  private String intentArgument;
  private String intentName;
  private boolean isBroadcast;
  private boolean isService;
  private String typeName;

  private Intent buildIntent()
  {
    Intent localIntent;
    if (this.intentName != null)
    {
      localIntent = new Intent();
      localIntent.setAction(this.intentName);
      if (this.intentArgument != null)
        localIntent.setData(Uri.parse(this.intentArgument));
      if (!StringUtils.isNullOrWhiteSpace(this.typeName))
        localIntent.setType(this.typeName);
      if (this.extras != null)
      {
        String[] arrayOfString2 = this.extras.split(";");
        int i = arrayOfString2.length;
        int j = 0;
        if (j < i)
        {
          String[] arrayOfString3 = arrayOfString2[j].split(",");
          String str1;
          String str2;
          if (arrayOfString3.length == 2)
          {
            str1 = arrayOfString3[0];
            str2 = arrayOfString3[1];
            if (!str2.equals("true"))
              break label150;
            localIntent.putExtra(str1, true);
          }
          while (true)
          {
            j++;
            break;
            label150: if (str2.equals("false"))
            {
              localIntent.putExtra(str1, false);
              continue;
            }
            localIntent.putExtra(str1, str2);
          }
        }
      }
      if ((!StringUtils.isNullOrWhiteSpace(this.className)) && (this.className.contains(",")))
      {
        String[] arrayOfString1 = this.className.split(",");
        localIntent.setClassName(arrayOfString1[0], arrayOfString1[1]);
      }
    }
    while (true)
    {
      return localIntent;
      localIntent = null;
    }
  }

  public ExecuteIntentAction argument(String paramString)
  {
    this.intentArgument = paramString;
    return this;
  }

  public ExecuteIntentAction broadcast(boolean paramBoolean)
  {
    this.isBroadcast = paramBoolean;
    return this;
  }

  public ExecuteIntentAction className(String paramString)
  {
    this.className = paramString;
    return this;
  }

  protected void execute()
  {
    if (this.intent != null)
    {
      getContext().startActivity(this.intent);
      getListener().actionSuccess();
    }
    while (true)
    {
      return;
      if (this.intentName != null)
      {
        Intent localIntent = buildIntent();
        if (this.isBroadcast)
          getContext().sendBroadcast(localIntent);
        while (true)
        {
          getListener().actionSuccess();
          break;
          if (this.isService)
          {
            getContext().startService(localIntent);
            continue;
          }
          localIntent.addFlags(268435456);
          getContext().startActivity(localIntent);
        }
      }
      getListener().actionFail("No intent name");
    }
  }

  public ExecuteIntentAction extra(String paramString)
  {
    this.extras = paramString;
    return this;
  }

  public ExecuteIntentAction intent(Intent paramIntent)
  {
    this.intent = paramIntent;
    return this;
  }

  public boolean isAvailable()
  {
    Intent localIntent;
    boolean bool;
    if (this.intent == null)
    {
      localIntent = buildIntent();
      if (!this.isBroadcast)
        break label38;
      bool = PackageUtil.canHandleBroadcastIntent(getContext(), localIntent);
    }
    while (true)
    {
      return bool;
      localIntent = this.intent;
      break;
      label38: if (this.isService)
      {
        bool = PackageUtil.canHandleServiceIntent(getContext(), localIntent);
        continue;
      }
      bool = PackageUtil.canHandleIntent(getContext(), localIntent);
    }
  }

  public ExecuteIntentAction name(String paramString)
  {
    this.intentName = paramString;
    return this;
  }

  public ExecuteIntentAction service(boolean paramBoolean)
  {
    this.isService = paramBoolean;
    return this;
  }

  public ExecuteIntentAction type(String paramString)
  {
    this.typeName = paramString;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.ExecuteIntentAction
 * JD-Core Version:    0.6.0
 */