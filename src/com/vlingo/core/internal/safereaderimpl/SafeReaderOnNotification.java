package com.vlingo.core.internal.safereaderimpl;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.drawable;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.safereader.SafeReaderService;

public class SafeReaderOnNotification
{
  private boolean isSilent = false;
  final int mainNotificationId;
  final NotificationManager nm;

  public SafeReaderOnNotification(Context paramContext, int paramInt, boolean paramBoolean)
  {
    this.mainNotificationId = paramInt;
    this.nm = ((NotificationManager)paramContext.getSystemService("notification"));
    this.isSilent = paramBoolean;
  }

  public Notification getNotification(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    String str1 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_safereader_enabled);
    String str2;
    String str3;
    PendingIntent localPendingIntent;
    if (paramBoolean1)
    {
      str2 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_safereader_notif_reading_title);
      str3 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_safereader_notif_reading_ticker);
      i = VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.drawable.core_stat_safereader_sms);
      Intent localIntent = new Intent("com.vlingo.client.safereader.ACTION_SKIP_CURRENT_ITEM");
      localIntent.setComponent(new ComponentName(paramContext, SafeReaderService.class));
      localPendingIntent = PendingIntent.getService(paramContext, 0, localIntent, 0);
      Notification localNotification = new Notification(i, str1, 0L);
      localNotification.flags = (0x22 | localNotification.flags);
      localNotification.setLatestEventInfo(paramContext, str3, str2, localPendingIntent);
      return localNotification;
    }
    if (this.isSilent)
    {
      str2 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_safereader_notif_title_silent);
      label155: str3 = str1;
      if (!this.isSilent)
        break label215;
    }
    label215: for (int i = VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.drawable.core_stat_safereader_silent); ; i = VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.drawable.core_stat_safereader_on))
    {
      localPendingIntent = PendingIntent.getBroadcast(paramContext, 0, new Intent("com.vlingo.client.safereader.ACTION_SAFEREADER_OFF"), 0);
      break;
      str2 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_safereader_notif_title);
      break label155;
    }
  }

  public boolean isSilent()
  {
    return this.isSilent;
  }

  public void setSilent(boolean paramBoolean)
  {
    this.isSilent = paramBoolean;
  }

  public void updateNotification(Context paramContext, boolean paramBoolean)
  {
    this.nm.notify(this.mainNotificationId, getNotification(paramContext, paramBoolean, false));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.safereaderimpl.SafeReaderOnNotification
 * JD-Core Version:    0.6.0
 */