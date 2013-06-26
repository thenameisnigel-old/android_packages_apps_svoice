package com.vlingo.midas.help;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.vlservice.VlingoApplicationService;
import com.vlingo.midas.VlingoApplication;
import com.vlingo.midas.settings.SettingsScreen;
import com.vlingo.midas.ui.VLActivity;

public class AboutScreen extends VLActivity
{
  public static final int LANDSCAPE_MODE = 2;
  public static final String SAMSUNG_REVISION_NUMBER = "r1";
  private static int mTheme = 2131296527;
  private int button_clicked = 0;
  private boolean customTitleSupported = false;
  private String deviceID;
  private float mDensity = 0.0F;
  private float mOldX = 0.0F;
  private float mOldY = 0.0F;
  private View.OnClickListener settingClicked = new AboutScreen.7(this);

  private boolean isDialogMode()
  {
    if (mTheme != 2131296527);
    for (int i = 1; ; i = 0)
      return i;
  }

  private void showPreferences()
  {
    Intent localIntent = new Intent(this, SettingsScreen.class);
    localIntent.putExtra("is_start_option_menu", true);
    startActivity(localIntent);
  }

  protected void onCreate(Bundle paramBundle)
  {
    DisplayMetrics localDisplayMetrics1 = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics1);
    this.mDensity = localDisplayMetrics1.density;
    this.deviceID = (getString(2131362801) + " ");
    DisplayMetrics localDisplayMetrics2;
    float f1;
    float f2;
    float f3;
    if (((localDisplayMetrics1.density == 1.0D) && (localDisplayMetrics1.heightPixels + localDisplayMetrics1.widthPixels == 2032) && (localDisplayMetrics1.xdpi == 149.82489F) && (localDisplayMetrics1.ydpi == 150.51852F)) || ((localDisplayMetrics1.density == 1.0D) && (localDisplayMetrics1.heightPixels + localDisplayMetrics1.widthPixels == 2080) && (localDisplayMetrics1.xdpi == 149.82401F) && (localDisplayMetrics1.ydpi == 150.51801F)))
    {
      mTheme = 2131296529;
      setTheme(mTheme);
      super.onCreate(paramBundle);
      this.customTitleSupported = requestWindowFeature(8);
      if (!isDialogMode())
        break label782;
      getWindow().clearFlags(2);
      getWindow().addFlags(17170976);
      setContentView(2130903075);
      getWindow().setBackgroundDrawableResource(2130837952);
      ActionBar localActionBar2 = getActionBar();
      if (localActionBar2 != null)
      {
        localActionBar2.setCustomView(getLayoutInflater().inflate(2130903048, null), new ActionBar.LayoutParams(-1, 40));
        localActionBar2.setDisplayUseLogoEnabled(false);
        localActionBar2.setDisplayOptions(0x10 ^ localActionBar2.getDisplayOptions(), 16);
        localActionBar2.setDisplayShowHomeEnabled(false);
        View localView = localActionBar2.getCustomView();
        AboutScreen.1 local1 = new AboutScreen.1(this);
        localView.setOnTouchListener(local1);
      }
      ImageButton localImageButton = (ImageButton)findViewById(2131558546);
      if (localImageButton != null)
      {
        AboutScreen.2 local2 = new AboutScreen.2(this);
        localImageButton.setOnClickListener(local2);
      }
      WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
      localLayoutParams.gravity = 85;
      localLayoutParams.type = 2006;
      localDisplayMetrics2 = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics2);
      f1 = 0.0F;
      if ((getWindowManager().getDefaultDisplay().getRotation() != 1) && (getWindowManager().getDefaultDisplay().getRotation() != 3))
        break label752;
      f2 = 436.0F * localDisplayMetrics2.density;
      f3 = 704.0F * localDisplayMetrics2.density;
      label438: localLayoutParams.height = (int)f2;
      localLayoutParams.width = (int)f3;
      localLayoutParams.x = (int)f1;
      localLayoutParams.y = (int)0.0F;
      getWindow().setAttributes(localLayoutParams);
    }
    while (true)
    {
      VlingoAndroidCore.regBackgroundImage(this, (RelativeLayout)findViewById(2131558624), 2130837504, VlingoApplication.getVersion());
      ((ImageView)findViewById(2131558627)).setImageResource(2130837957);
      Button localButton1 = (Button)findViewById(2131558633);
      if (2 == getResources().getConfiguration().orientation)
      {
        AboutScreen.3 local3 = new AboutScreen.3(this);
        localButton1.setOnFocusChangeListener(local3);
      }
      AboutScreen.4 local4 = new AboutScreen.4(this);
      localButton1.setOnClickListener(local4);
      Button localButton2 = (Button)findViewById(2131558634);
      AboutScreen.5 local5 = new AboutScreen.5(this);
      localButton2.setOnClickListener(local5);
      ((TextView)findViewById(2131558629)).setText(getString(2131362277) + getResources().getString(2131362061) + VlingoApplication.getVersion() + " (" + "r1" + ")");
      TextView localTextView = (TextView)findViewById(2131558630);
      localTextView.setText(this.deviceID + Settings.getUUIDDeviceID().substring(0, 40 - this.deviceID.length()));
      AboutScreen.6 local6 = new AboutScreen.6(this);
      localTextView.setOnClickListener(local6);
      return;
      mTheme = 2131296527;
      break;
      label752: f3 = 436.0F * localDisplayMetrics2.density;
      f1 = 242.0F;
      f2 = 704.0F * localDisplayMetrics2.density;
      break label438;
      label782: setContentView(2130903075);
      ActionBar localActionBar1 = getActionBar();
      if (localActionBar1 == null)
        continue;
      localActionBar1.setDisplayOptions(14);
      localActionBar1.setTitle(getString(2131362273));
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
    case 16908332:
    }
    while (true)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      finish();
    }
  }

  protected void onResume()
  {
    super.onResume();
    this.button_clicked = 0;
    Intent localIntent = new Intent(this, VlingoApplicationService.class);
    localIntent.setAction("com.vlingo.client.app.action.ACTIVITY_STATE_CHANGED");
    localIntent.putExtra("com.vlingo.client.app.extra.STATE", 0);
    startService(localIntent);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.help.AboutScreen
 * JD-Core Version:    0.6.0
 */