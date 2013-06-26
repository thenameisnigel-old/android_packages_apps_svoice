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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.vlservice.VlingoApplicationService;
import com.vlingo.midas.iux.IUXCompoundActivity;
import com.vlingo.midas.ui.PackageInfoProvider;

public class HelpScreen extends WhatCanISayScreen
{
  public static final String EXTRA_IS_IUX = "wycs.is.iux";
  private static int mTheme = 2131296526;
  private boolean customTitleSupported = false;
  private boolean inIUXMode = false;
  private float mDensity = 0.0F;
  private float mOldLX = 0.0F;
  private float mOldLY = 0.0F;
  private float mOldPX = 0.0F;
  private float mOldPY = 0.0F;
  private float mOldX = 0.0F;
  private float mOldY = 0.0F;
  private int my_orientation;
  private int prevPosition;
  private final View.OnClickListener settingClicked = new HelpScreen.7(this);

  private boolean isDialogMode()
  {
    if (mTheme != 2131296526);
    for (int i = 1; ; i = 0)
      return i;
  }

  protected void addItems()
  {
    if (!this.inIUXMode)
    {
      getWcisData().addSubHeading(getResources().getString(2131362488));
      getWcisData().addInfoItem(getResources().getString(2131362502), getResources().getString(2131362503), HelpWakeUpScreen.class);
      getWcisData().addInfoItem(getResources().getString(2131362341), getResources().getString(2131362490), HelpHowToUseScreen.class);
      if (Settings.isAsrEditingEnabled())
      {
        if (this.mPackageInfo.hasPenFeature())
          getWcisData().addInfoItem(getResources().getString(2131362609), getResources().getString(2131362505), HelpHandwritingScreen.class);
        getWcisData().addInfoItem(getResources().getString(2131362506), getResources().getString(2131362507), HelpEditWhatYouSaidScreen.class);
      }
      getWcisData().addSubHeading(getResources().getString(2131362509));
    }
    getWcisData().addItems(this);
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
      this.my_orientation = getResources().getConfiguration().orientation;
      getWindow().addFlags(17170976);
      setContentView(2130903134);
      getWindow().setBackgroundDrawableResource(2130837952);
      ActionBar localActionBar2 = getActionBar();
      if (localActionBar2 != null)
      {
        localActionBar2.setCustomView(getLayoutInflater().inflate(2130903048, null), new ActionBar.LayoutParams(-1, 40));
        localActionBar2.setDisplayUseLogoEnabled(false);
        localActionBar2.setDisplayOptions(0x10 ^ localActionBar2.getDisplayOptions(), 16);
        localActionBar2.setDisplayShowHomeEnabled(false);
        View localView = localActionBar2.getCustomView();
        HelpScreen.1 local1 = new HelpScreen.1(this);
        localView.setOnTouchListener(local1);
      }
      ImageButton localImageButton = (ImageButton)findViewById(2131558546);
      if (localImageButton != null)
      {
        HelpScreen.2 local2 = new HelpScreen.2(this);
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
        f1 = 436.0F * localDisplayMetrics.density;
        f2 = 704.0F * localDisplayMetrics.density;
        this.mOldLX = ((localPoint.y - f1) / 2.0F);
        this.mOldPX = ((localPoint.x - f1) / 2.0F);
        localLayoutParams.height = (int)f2;
        localLayoutParams.width = (int)f1;
        localLayoutParams.x = ((localPoint.x - localLayoutParams.width) / 2);
        localLayoutParams.y = 0;
        getWindow().setAttributes(localLayoutParams);
        ((TextView)findViewById(2131558492)).setText(getString(2131362269));
      }
    }
    while (true)
    {
      this.inIUXMode = getIntent().getBooleanExtra("wycs.show.done", false);
      if (this.inIUXMode)
      {
        ((ViewGroup)findViewById(2131558848)).setVisibility(0);
        Button localButton1 = (Button)findViewById(2131558849);
        localButton1.setVisibility(0);
        HelpScreen.3 local3 = new HelpScreen.3(this);
        localButton1.setOnClickListener(local3);
        Button localButton2 = (Button)findViewById(2131558785);
        localButton2.setVisibility(0);
        HelpScreen.4 local4 = new HelpScreen.4(this);
        localButton2.setOnClickListener(local4);
        setTitle(getString(2131362509));
      }
      ListView localListView = (ListView)findViewById(2131558850);
      addItems();
      localListView.setAdapter(new WhatCanISayScreen.RowAdapter(this));
      HelpScreen.5 local5 = new HelpScreen.5(this, localListView);
      localListView.setOnItemSelectedListener(local5);
      HelpScreen.6 local6 = new HelpScreen.6(this);
      localListView.setOnItemClickListener(local6);
      if ((getWindowManager().getDefaultDisplay().getRotation() == 1) || (getWindowManager().getDefaultDisplay().getRotation() == 3))
        onConfigurationChanged(null);
      return;
      f1 = 436.0F * localDisplayMetrics.density;
      f2 = 704.0F * localDisplayMetrics.density;
      this.mOldPX = ((localPoint.y - f1) / 2.0F);
      this.mOldLX = ((localPoint.x - f1) / 2.0F);
      break;
      setContentView(2130903134);
      ActionBar localActionBar1 = getActionBar();
      if (localActionBar1 == null)
        continue;
      localActionBar1.setDisplayOptions(14);
      localActionBar1.setTitle(getString(2131362269));
    }
  }

  public void onBackPressed()
  {
    if (this.inIUXMode)
      if (!Settings.isAsrEditingEnabled())
        break label43;
    label43: for (int i = 4; ; i = 2)
    {
      IUXCompoundActivity.setMStep(i);
      finish();
      startActivity(new Intent(this, IUXCompoundActivity.class));
      super.onBackPressed();
      return;
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    WindowManager.LayoutParams localLayoutParams2;
    WindowManager.LayoutParams localLayoutParams1;
    if (isDialogMode())
    {
      this.my_orientation = getResources().getConfiguration().orientation;
      localLayoutParams2 = getWindow().getAttributes();
      Point localPoint2 = new Point();
      getWindowManager().getDefaultDisplay().getSize(localPoint2);
      if (this.my_orientation == 2)
      {
        localLayoutParams2.x = (int)this.mOldLX;
        localLayoutParams2.y = (int)this.mOldLY;
        getWindow().setAttributes(localLayoutParams2);
      }
    }
    else if (isDialogMode())
    {
      this.my_orientation = getResources().getConfiguration().orientation;
      localLayoutParams1 = getWindow().getAttributes();
      Point localPoint1 = new Point();
      getWindowManager().getDefaultDisplay().getSize(localPoint1);
      if (this.my_orientation != 2)
        break label206;
      localLayoutParams1.x = (int)this.mOldLX;
    }
    for (localLayoutParams1.y = (int)this.mOldLY; ; localLayoutParams1.y = (int)this.mOldPY)
    {
      getWindow().setAttributes(localLayoutParams1);
      System.gc();
      return;
      localLayoutParams2.x = (int)this.mOldPX;
      localLayoutParams2.y = (int)this.mOldPY;
      break;
      label206: localLayoutParams1.x = (int)this.mOldPX;
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
      if (this.inIUXMode)
      {
        if (Settings.isAsrEditingEnabled());
        for (int i = 4; ; i = 2)
        {
          IUXCompoundActivity.setMStep(i);
          finish();
          startActivity(new Intent(this, IUXCompoundActivity.class));
          break;
        }
      }
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
 * Qualified Name:     com.vlingo.midas.help.HelpScreen
 * JD-Core Version:    0.6.0
 */