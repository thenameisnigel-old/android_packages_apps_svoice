package com.vlingo.midas.help;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.settings.SettingsScreen;

public class HelpHowToUseScreen extends HelpScreen
{
  private static int mTheme = 2131296526;
  private boolean customTitleSupported = false;
  private float mDensity = 0.0F;
  private float mOldLX = 0.0F;
  private float mOldLY = 0.0F;
  private float mOldPX = 0.0F;
  private float mOldPY = 0.0F;
  private float mOldX = 0.0F;
  private float mOldY = 0.0F;
  private int my_orientation;
  private final View.OnClickListener settingClicked = new HelpHowToUseScreen.3(this);

  private boolean isDialogMode()
  {
    if (mTheme != 2131296526);
    for (int i = 1; ; i = 0)
      return i;
  }

  private void showPreferences()
  {
    Intent localIntent = new Intent(this, SettingsScreen.class);
    localIntent.putExtra("is_start_option_menu", true);
    startActivity(localIntent);
  }

  protected void init()
  {
    this.customTitleSupported = requestWindowFeature(8);
    DisplayMetrics localDisplayMetrics;
    Point localPoint;
    float f1;
    float f2;
    label384: ImageView localImageView;
    if (isDialogMode())
    {
      getWindow().clearFlags(2);
      getWindow().addFlags(17170976);
      setContentView(2130903078);
      getWindow().setBackgroundDrawableResource(2130837952);
      ActionBar localActionBar2 = getActionBar();
      localActionBar2.setCustomView(getLayoutInflater().inflate(2130903048, null), new ActionBar.LayoutParams(-1, 40));
      localActionBar2.setDisplayUseLogoEnabled(false);
      localActionBar2.setDisplayOptions(0x10 ^ localActionBar2.getDisplayOptions(), 16);
      localActionBar2.setDisplayShowHomeEnabled(false);
      View localView = localActionBar2.getCustomView();
      HelpHowToUseScreen.1 local1 = new HelpHowToUseScreen.1(this);
      localView.setOnTouchListener(local1);
      ImageButton localImageButton = (ImageButton)findViewById(2131558546);
      if (localImageButton != null)
      {
        HelpHowToUseScreen.2 local2 = new HelpHowToUseScreen.2(this);
        localImageButton.setOnClickListener(local2);
      }
      WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
      localLayoutParams.gravity = 85;
      localLayoutParams.type = 2006;
      localDisplayMetrics = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      localPoint = new Point();
      getWindowManager().getDefaultDisplay().getSize(localPoint);
      if ((getWindowManager().getDefaultDisplay().getRotation() == 1) || (getWindowManager().getDefaultDisplay().getRotation() == 3))
      {
        f1 = 476.0F * localDisplayMetrics.density;
        f2 = 704.0F * localDisplayMetrics.density;
        this.mOldLX = ((localPoint.y - f1) / 2.0F);
        this.mOldPX = ((localPoint.x - f1) / 2.0F);
        localLayoutParams.height = (int)f2;
        localLayoutParams.width = (int)f1;
        localLayoutParams.x = ((localPoint.x - localLayoutParams.width) / 2);
        localLayoutParams.y = 0;
        getWindow().setAttributes(localLayoutParams);
        ((TextView)findViewById(2131558492)).setText(getString(2131362341));
        localImageView = (ImageView)findViewById(2131558657);
        if (!MidasSettings.isHomeKeyEnabled())
          break label615;
      }
    }
    label615: for (int i = 2130838168; ; i = 2130838169)
    {
      localImageView.setImageResource(i);
      TextView localTextView1 = (TextView)findViewById(2131558652);
      TextView localTextView2 = (TextView)findViewById(2131558654);
      TextView localTextView3 = (TextView)findViewById(2131558656);
      localTextView1.setText(Html.fromHtml(localTextView1.getText().toString()));
      localTextView2.setText(Html.fromHtml(localTextView2.getText().toString()));
      localTextView3.setText(Html.fromHtml(localTextView3.getText().toString()));
      if ((getWindowManager().getDefaultDisplay().getRotation() == 1) || (getWindowManager().getDefaultDisplay().getRotation() == 3))
        onConfigurationChanged(null);
      return;
      f1 = 476.0F * localDisplayMetrics.density;
      f2 = 704.0F * localDisplayMetrics.density;
      this.mOldPX = ((localPoint.y - f1) / 2.0F);
      this.mOldLX = ((localPoint.x - f1) / 2.0F);
      break;
      setContentView(2130903078);
      ActionBar localActionBar1 = getActionBar();
      localActionBar1.setDisplayOptions(14);
      localActionBar1.setTitle(getString(2131362341));
      break label384;
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    WindowManager.LayoutParams localLayoutParams;
    if (isDialogMode())
    {
      if (paramConfiguration != null)
        super.onConfigurationChanged(paramConfiguration);
      this.my_orientation = getResources().getConfiguration().orientation;
      if (paramConfiguration != null)
      {
        localLayoutParams = getWindow().getAttributes();
        Point localPoint = new Point();
        getWindowManager().getDefaultDisplay().getSize(localPoint);
        if (this.my_orientation != 2)
          break label101;
        localLayoutParams.x = (int)this.mOldLX;
      }
    }
    for (localLayoutParams.y = (int)this.mOldLY; ; localLayoutParams.y = (int)this.mOldPY)
    {
      getWindow().setAttributes(localLayoutParams);
      System.gc();
      return;
      label101: localLayoutParams.x = (int)this.mOldPX;
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.mDensity = localDisplayMetrics.density;
    if (((localDisplayMetrics.density == 1.0D) && (localDisplayMetrics.heightPixels + localDisplayMetrics.widthPixels == 2032) && (localDisplayMetrics.xdpi == 149.82489F) && (localDisplayMetrics.ydpi == 150.51852F)) || ((localDisplayMetrics.density == 1.0D) && (localDisplayMetrics.heightPixels + localDisplayMetrics.widthPixels == 2080) && (localDisplayMetrics.xdpi == 149.82401F) && (localDisplayMetrics.ydpi == 150.51801F)))
    {
      mTheme = 2131296529;
      setTheme(mTheme);
    }
    while (true)
    {
      super.onCreate(paramBundle);
      this.my_orientation = getResources().getConfiguration().orientation;
      return;
      mTheme = 2131296526;
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

  protected void onPause()
  {
    super.onPause();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.help.HelpHowToUseScreen
 * JD-Core Version:    0.6.0
 */