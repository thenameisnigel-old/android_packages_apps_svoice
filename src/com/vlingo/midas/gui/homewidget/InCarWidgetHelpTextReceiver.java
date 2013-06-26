package com.vlingo.midas.gui.homewidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.vlingo.midas.drivingmode.DrivingModeUtil;
import java.lang.ref.WeakReference;

public class InCarWidgetHelpTextReceiver extends BroadcastReceiver
{
  public static final String ACTION_DRIVRING_MODE_CHANGED = "com.vlingo.client.widget.action.driving_mode_changed";
  private static boolean bUpdate = false;
  private Context mContext;
  private InCarWidgetHelpTextReceiverHandler mHandler = new InCarWidgetHelpTextReceiverHandler(this);

  private void updateIndex()
  {
    int i = 1 + InCarWidget.getIndex();
    if (i < InCarWidget.getMaxIndex());
    while (true)
    {
      InCarWidget.setIndex(i);
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
      if ((paramIntent.getAction() != null) && (paramIntent.getAction().equals("com.vlingo.client.widget.action.driving_mode_changed")))
      {
        InCarWidget.updateDrvToggleWidgets(paramContext, DrivingModeUtil.isDrivingModeEnabled(paramContext));
        continue;
      }
      this.mContext = paramContext;
      1 local1 = new Thread()
      {
        public void run()
        {
          InCarWidgetHelpTextReceiver.this.updateIndex();
          InCarWidgetHelpTextReceiver.this.mHandler.sendEmptyMessage(0);
        }
      };
      bUpdate = true;
      local1.start();
    }
  }

  private static class InCarWidgetHelpTextReceiverHandler extends Handler
  {
    private final WeakReference<InCarWidgetHelpTextReceiver> outer;

    InCarWidgetHelpTextReceiverHandler(InCarWidgetHelpTextReceiver paramInCarWidgetHelpTextReceiver)
    {
      this.outer = new WeakReference(paramInCarWidgetHelpTextReceiver);
    }

    public void handleMessage(Message paramMessage)
    {
      InCarWidgetHelpTextReceiver localInCarWidgetHelpTextReceiver = (InCarWidgetHelpTextReceiver)this.outer.get();
      if (localInCarWidgetHelpTextReceiver != null)
      {
        InCarWidget.updateAllWidgets(localInCarWidgetHelpTextReceiver.mContext, DrivingModeUtil.isDrivingModeEnabled(localInCarWidgetHelpTextReceiver.mContext));
        InCarWidgetHelpTextReceiver.access$302(false);
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.homewidget.InCarWidgetHelpTextReceiver
 * JD-Core Version:    0.6.0
 */