package com.vlingo.midas.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.vlingo.core.internal.bluetooth.BluetoothManager;
import com.vlingo.core.internal.vlservice.VlingoApplicationService;
import com.vlingo.midas.VlingoApplication;
import com.vlingo.midas.help.WhatCanISayScreen;
import com.vlingo.midas.settings.MidasSettings;
import java.io.File;

public class VLActivity extends FragmentActivity
{
  public static final int MENU_HELP = 0;
  public static final int MENU_LAST = 100;
  public static final int MENU_SETTINGS = 2;
  public static final int MENU_VOICE_TALK = 3;
  public static final int MENU_WCIS = 1;

  public File getSharedPrefsFile(String paramString)
  {
    return null;
  }

  protected boolean isRouteDisplayed()
  {
    return false;
  }

  protected void makeDefaultMenu(Menu paramMenu)
  {
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    MidasSettings.updateCurrentLocale(getResources());
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    MidasSettings.updateCurrentLocale(getResources());
    setVolumeControlStream(3);
    BluetoothManager.considerRightBeforeForeground(true);
  }

  public void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    if (paramIntent.getBooleanExtra("com.vlingo.core.internal.vlservice.FINISH_ACTIVITY", false))
      finish();
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = 1;
    switch (paramMenuItem.getItemId())
    {
    default:
      i = 0;
    case 0:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return i;
      showAbout();
      continue;
      showWCIS();
      continue;
      showSettings();
      continue;
      startActivity(new Intent(this, VlingoApplication.getInstance().getMainActivityClass()));
    }
  }

  protected void onPause()
  {
    Intent localIntent = new Intent(this, VlingoApplicationService.class);
    localIntent.setAction("com.vlingo.client.app.action.ACTIVITY_STATE_CHANGED");
    localIntent.putExtra("com.vlingo.client.app.extra.STATE", 0);
    startService(localIntent);
    super.onPause();
  }

  protected void onResume()
  {
    MidasSettings.updateCurrentLocale(getResources());
    Intent localIntent = new Intent(this, VlingoApplicationService.class);
    localIntent.setAction("com.vlingo.client.app.action.ACTIVITY_STATE_CHANGED");
    localIntent.putExtra("com.vlingo.client.app.extra.STATE", 1);
    startService(localIntent);
    super.onResume();
  }

  protected void showAbout()
  {
  }

  protected void showSettings()
  {
  }

  protected void showWCIS()
  {
    startActivity(new Intent(this, WhatCanISayScreen.class).addFlags(67108864));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.ui.VLActivity
 * JD-Core Version:    0.6.0
 */