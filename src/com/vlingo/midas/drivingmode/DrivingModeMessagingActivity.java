package com.vlingo.midas.drivingmode;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.TextView;
import java.lang.ref.WeakReference;

public class DrivingModeMessagingActivity extends Activity
{
  private DrivingModeMessagingActivityHandler mHandler = new DrivingModeMessagingActivityHandler(this);
  private TextView mReceiverView;
  String post = null;

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903072);
    Intent localIntent = getIntent();
    String str1 = localIntent.getStringExtra("name");
    String str2 = localIntent.getStringExtra("number");
    if (str1 == null);
    for (String str3 = getResources().getString(2131362733) + " " + str2 + " " + getResources().getString(2131362747); ; str3 = getResources().getString(2131362733) + " " + str1 + " " + getResources().getString(2131362747))
    {
      this.post = str3;
      this.mHandler.sendEmptyMessageDelayed(0, 1000L);
      return;
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return true;
  }

  private static class DrivingModeMessagingActivityHandler extends Handler
  {
    private final WeakReference<DrivingModeMessagingActivity> outer;

    DrivingModeMessagingActivityHandler(DrivingModeMessagingActivity paramDrivingModeMessagingActivity)
    {
      this.outer = new WeakReference(paramDrivingModeMessagingActivity);
    }

    public void handleMessage(Message paramMessage)
    {
      DrivingModeMessagingActivity localDrivingModeMessagingActivity = (DrivingModeMessagingActivity)this.outer.get();
      if (localDrivingModeMessagingActivity != null)
      {
        Intent localIntent = new Intent();
        localIntent.setAction("com.vlingo.midas.action.SEND");
        localIntent.putExtra("android.intent.extra.TEXT", localDrivingModeMessagingActivity.post);
        localIntent.setType("text/plain");
        localIntent.putExtra("AUTO_LISTEN", false);
        localIntent.setFlags(268435456);
        localDrivingModeMessagingActivity.startActivity(localIntent);
        localDrivingModeMessagingActivity.finish();
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.drivingmode.DrivingModeMessagingActivity
 * JD-Core Version:    0.6.0
 */