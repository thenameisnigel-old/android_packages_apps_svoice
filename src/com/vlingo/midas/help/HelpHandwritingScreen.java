package com.vlingo.midas.help;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.vlingo.core.internal.vlservice.VlingoApplicationService;

public class HelpHandwritingScreen extends WhatCanISayScreen
{
  private static int mTheme = 2131296526;
  private boolean customTitleSupported = false;
  private Button listenExampleButton;
  private float mDensity = 0.0F;
  private float mOldLX = 0.0F;
  private float mOldLY = 0.0F;
  private float mOldPX = 0.0F;
  private float mOldPY = 0.0F;
  private float mOldX = 0.0F;
  private float mOldY = 0.0F;
  private int my_orientation;

  private boolean isDialogMode()
  {
    if (mTheme != 2131296526);
    for (int i = 1; ; i = 0)
      return i;
  }

  protected void init()
  {
    this.customTitleSupported = requestWindowFeature(8);
    DisplayMetrics localDisplayMetrics;
    Point localPoint;
    float f1;
    float f2;
    if (isDialogMode())
    {
      getWindow().clearFlags(2);
      getWindow().addFlags(17170976);
      setContentView(2130903077);
      getWindow().setBackgroundDrawableResource(2130837952);
      ActionBar localActionBar2 = getActionBar();
      if (localActionBar2 != null)
      {
        localActionBar2.setCustomView(getLayoutInflater().inflate(2130903048, null), new ActionBar.LayoutParams(-1, 40));
        localActionBar2.setDisplayUseLogoEnabled(false);
        localActionBar2.setDisplayOptions(0x10 ^ localActionBar2.getDisplayOptions(), 16);
        localActionBar2.setDisplayShowHomeEnabled(false);
        localActionBar2.getCustomView().setOnTouchListener(new HelpHandwritingScreen.1(this));
      }
      ImageButton localImageButton = (ImageButton)findViewById(2131558546);
      if (localImageButton != null)
        localImageButton.setOnClickListener(new HelpHandwritingScreen.2(this));
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
        ((TextView)findViewById(2131558650)).setText(getString(2131362609));
      }
    }
    while (true)
    {
      if ((getWindowManager().getDefaultDisplay().getRotation() == 1) || (getWindowManager().getDefaultDisplay().getRotation() == 3))
        onConfigurationChanged(null);
      return;
      f1 = 476.0F * localDisplayMetrics.density;
      f2 = 704.0F * localDisplayMetrics.density;
      this.mOldPX = ((localPoint.y - f1) / 2.0F);
      this.mOldLX = ((localPoint.x - f1) / 2.0F);
      break;
      setContentView(2130903077);
      ActionBar localActionBar1 = getActionBar();
      localActionBar1.setDisplayOptions(14);
      localActionBar1.setTitle(getString(2131362609));
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

  protected void onResume()
  {
    super.onResume();
    Intent localIntent = new Intent(this, VlingoApplicationService.class);
    localIntent.setAction("com.vlingo.client.app.action.ACTIVITY_STATE_CHANGED");
    localIntent.putExtra("com.vlingo.client.app.extra.STATE", 0);
    startService(localIntent);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.help.HelpHandwritingScreen
 * JD-Core Version:    0.6.0
 */