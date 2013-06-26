package com.vlingo.midas.gui.homewidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

public class HomeWidgetHelpTextReceiver extends BroadcastReceiver
{
  private static boolean bUpdate = false;
  private Context mContext;
  private HomeWidgetHelpTextReceiverHandler mHandler = new HomeWidgetHelpTextReceiverHandler(this);

  private void updateIndex()
  {
    int i = 1 + HomeScreenWidget.getIndex();
    if (i < HomeScreenWidget.getMaxIndex());
    while (true)
    {
      HomeScreenWidget.setIndex(i);
      return;
      i = 0;
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (bUpdate);
    while (true)
    {
      return;
      this.mContext = paramContext;
      1 local1 = new Thread()
      {
        public void run()
        {
          HomeWidgetHelpTextReceiver.this.updateIndex();
          HomeWidgetHelpTextReceiver.this.mHandler.sendEmptyMessage(0);
        }
      };
      bUpdate = true;
      local1.start();
    }
  }

  private static class HomeWidgetHelpTextReceiverHandler extends Handler
  {
    private final WeakReference<HomeWidgetHelpTextReceiver> outer;

    HomeWidgetHelpTextReceiverHandler(HomeWidgetHelpTextReceiver paramHomeWidgetHelpTextReceiver)
    {
      this.outer = new WeakReference(paramHomeWidgetHelpTextReceiver);
    }

    public void handleMessage(Message paramMessage)
    {
      HomeWidgetHelpTextReceiver localHomeWidgetHelpTextReceiver = (HomeWidgetHelpTextReceiver)this.outer.get();
      if (localHomeWidgetHelpTextReceiver != null)
      {
        HomeScreenWidget.updateAllWidgets(localHomeWidgetHelpTextReceiver.mContext);
        HomeWidgetHelpTextReceiver.access$302(false);
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.homewidget.HomeWidgetHelpTextReceiver
 * JD-Core Version:    0.6.0
 */