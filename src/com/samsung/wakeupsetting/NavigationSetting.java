package com.samsung.wakeupsetting;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import com.vlingo.midas.ui.VLActivity;
import java.util.Timer;

public class NavigationSetting extends VLActivity
{
  private Intent intent;
  private Button mButtonSave;
  private EditText mEditAddress;
  private EditText mEditShortcutName;

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903115);
    Object localObject = null;
    try
    {
      Intent localIntent = new Intent();
      localIntent.setClassName("com.google.android.apps.maps", "com.google.googlenav.appwidget.gohome.GoHomeCreateShortcutActivityAlias");
      Drawable localDrawable = getPackageManager().getActivityIcon(localIntent);
      localObject = localDrawable;
      setTitle(getString(2131362631));
      ActionBar localActionBar = getActionBar();
      localActionBar.setDisplayOptions(14);
      localActionBar.setIcon(localObject);
      this.intent = getIntent();
      this.mEditAddress = ((EditText)findViewById(2131558816));
      this.mEditShortcutName = ((EditText)findViewById(2131558818));
      this.mButtonSave = ((Button)findViewById(2131558819));
      this.mButtonSave.setEnabled(false);
      new Timer().schedule(new NavigationSetting.1(this), 300L);
      this.mEditAddress.addTextChangedListener(new NavigationSetting.2(this));
      this.mEditShortcutName.addTextChangedListener(new NavigationSetting.3(this));
      this.mButtonSave.setOnClickListener(new NavigationSetting.4(this));
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        localNameNotFoundException.printStackTrace();
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
      setResult(0);
      finish();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wakeupsetting.NavigationSetting
 * JD-Core Version:    0.6.0
 */