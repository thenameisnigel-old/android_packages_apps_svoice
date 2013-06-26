package com.vlingo.midas.help;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.vlingo.core.internal.vlservice.VlingoApplicationService;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.settings.SettingsScreen;
import com.vlingo.midas.ui.PackageInfoProvider;
import com.vlingo.midas.ui.VLActivity;

public class WCISCompositionScreen extends VLActivity
{
  public static final String EXTRA_DID_YOU_KNOW = "EXTRA_DID_YOU_KNOW";
  public static final String EXTRA_EXAMPLE_LIST = "EXTRA_EXAMPLE_LIST";
  public static final String EXTRA_SUBTITLE = "EXTRA_SUBTITLE";
  public static final String EXTRA_SUBTITLE_ICON = "EXTRA_SUBTITLE_ICON";
  public static final String EXTRA_TITLE_BAR = "EXTRA_TITLE_BAR";
  private static final int GONE = 8;
  private static int mTheme = 2131296526;
  private Button btnSetHomeAddress;
  private boolean customTitleSupported = false;
  private EditText editText;
  LayoutInflater inflater;
  private float mDensity = 0.0F;
  private float mOldLX = 0.0F;
  private float mOldLY = 0.0F;
  private float mOldPX = 0.0F;
  private float mOldPY = 0.0F;
  private float mOldX = 0.0F;
  private float mOldY = 0.0F;
  PackageInfoProvider mPackageInfo;
  private int my_orientation;
  private View.OnClickListener settingClicked = new WCISCompositionScreen.4(this);
  private TextView topTitle;

  private void checkIcon(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 2130837846:
    case 2130837843:
    case 2130837803:
    case 2130837808:
    case 2130837847:
    }
    while (true)
    {
      return;
      this.mPackageInfo.setMemoIcon();
      continue;
      this.mPackageInfo.setMessageIcon();
      continue;
      this.mPackageInfo.setNavigationIcon();
      continue;
      this.mPackageInfo.setWeatherIcon();
      continue;
      this.mPackageInfo.setPlannerIcon();
    }
  }

  private Drawable getDrawable(int paramInt)
  {
    Drawable localDrawable;
    switch (paramInt)
    {
    default:
      localDrawable = null;
    case 2130837846:
    case 2130837843:
    case 2130837803:
    case 2130837808:
    case 2130837847:
    }
    while (true)
    {
      return localDrawable;
      localDrawable = this.mPackageInfo.getMemoIcon();
      continue;
      localDrawable = this.mPackageInfo.getMessageIcon();
      continue;
      localDrawable = this.mPackageInfo.getNavigationIcon();
      continue;
      localDrawable = this.mPackageInfo.getWeatherIcon();
      continue;
      localDrawable = this.mPackageInfo.getPlannerIcon();
    }
  }

  private boolean isDialogMode()
  {
    if (mTheme != 2131296526);
    for (int i = 1; ; i = 0)
      return i;
  }

  private void setTextFromHtml(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    TextView localTextView = (TextView)findViewById(paramInt2);
    if ((localTextView != null) && (paramInt2 != 0))
    {
      if (paramBoolean)
        break label37;
      localTextView.setText(Html.fromHtml(getString(paramInt1)));
    }
    while (true)
    {
      return;
      label37: String str1 = "";
      for (String str2 : getResources().getStringArray(paramInt1))
        str1 = str1 + str2;
      localTextView.setText(Html.fromHtml(str1));
    }
  }

  private void showPreferences()
  {
    Intent localIntent = new Intent(this, SettingsScreen.class);
    localIntent.putExtra("is_start_option_menu", true);
    startActivity(localIntent);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    WindowManager.LayoutParams localLayoutParams;
    if (isDialogMode())
    {
      this.my_orientation = getResources().getConfiguration().orientation;
      if (paramConfiguration != null)
      {
        localLayoutParams = getWindow().getAttributes();
        Point localPoint = new Point();
        getWindowManager().getDefaultDisplay().getSize(localPoint);
        if (this.my_orientation != 2)
          break label97;
        localLayoutParams.x = (int)this.mOldLX;
      }
    }
    for (localLayoutParams.y = (int)this.mOldLY; ; localLayoutParams.y = (int)this.mOldPY)
    {
      getWindow().setAttributes(localLayoutParams);
      System.gc();
      return;
      label97: localLayoutParams.x = (int)this.mOldPX;
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    DisplayMetrics localDisplayMetrics1 = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics1);
    this.mDensity = localDisplayMetrics1.density;
    int i;
    int j;
    int m;
    int n;
    DisplayMetrics localDisplayMetrics2;
    Point localPoint;
    float f1;
    float f2;
    label527: label607: ImageView localImageView;
    if (((localDisplayMetrics1.density == 1.0D) && (localDisplayMetrics1.heightPixels + localDisplayMetrics1.widthPixels == 2032) && (localDisplayMetrics1.xdpi == 149.82489F) && (localDisplayMetrics1.ydpi == 150.51852F)) || ((localDisplayMetrics1.density == 1.0D) && (localDisplayMetrics1.heightPixels + localDisplayMetrics1.widthPixels == 2080) && (localDisplayMetrics1.xdpi == 149.82401F) && (localDisplayMetrics1.ydpi == 150.51801F)))
    {
      mTheme = 2131296529;
      setTheme(mTheme);
      super.onCreate(paramBundle);
      MidasSettings.updateCurrentLocale();
      this.customTitleSupported = requestWindowFeature(8);
      i = getIntent().getIntExtra("EXTRA_TITLE_BAR", 0);
      j = getIntent().getIntExtra("EXTRA_SUBTITLE_ICON", 0);
      int k = getIntent().getIntExtra("EXTRA_SUBTITLE", 0);
      m = getIntent().getIntExtra("EXTRA_EXAMPLE_LIST", 0);
      n = getIntent().getIntExtra("EXTRA_DID_YOU_KNOW", 0);
      if (!isDialogMode())
        break label911;
      getWindow().clearFlags(2);
      getWindow().addFlags(17170976);
      setContentView(2130903042);
      getWindow().setBackgroundDrawableResource(2130837952);
      ActionBar localActionBar2 = getActionBar();
      localActionBar2.setCustomView(getLayoutInflater().inflate(2130903048, null), new ActionBar.LayoutParams(-1, 40));
      localActionBar2.setDisplayUseLogoEnabled(false);
      localActionBar2.setDisplayOptions(0x10 ^ localActionBar2.getDisplayOptions(), 16);
      localActionBar2.setDisplayShowHomeEnabled(false);
      View localView = localActionBar2.getCustomView();
      WCISCompositionScreen.1 local1 = new WCISCompositionScreen.1(this);
      localView.setOnTouchListener(local1);
      ImageButton localImageButton = (ImageButton)findViewById(2131558546);
      if (localImageButton != null)
      {
        WCISCompositionScreen.2 local2 = new WCISCompositionScreen.2(this);
        localImageButton.setOnClickListener(local2);
      }
      WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
      localLayoutParams.gravity = 85;
      localLayoutParams.type = 2006;
      localDisplayMetrics2 = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics2);
      localPoint = new Point();
      getWindowManager().getDefaultDisplay().getSize(localPoint);
      if ((getWindowManager().getDefaultDisplay().getRotation() != 1) && (getWindowManager().getDefaultDisplay().getRotation() != 3))
        break label856;
      f1 = 476.0F * localDisplayMetrics2.density;
      f2 = 704.0F * localDisplayMetrics2.density;
      this.mOldLX = ((localPoint.y - f1) / 2.0F);
      this.mOldPX = ((localPoint.x - f1) / 2.0F);
      localLayoutParams.height = (int)f2;
      localLayoutParams.width = (int)f1;
      localLayoutParams.x = ((localPoint.x - localLayoutParams.width) / 2);
      localLayoutParams.y = 0;
      getWindow().setAttributes(localLayoutParams);
      this.topTitle = ((TextView)findViewById(2131558492));
      this.topTitle.setText(getIntent().getIntExtra("EXTRA_TITLE_BAR", 0));
      if ((getWindowManager().getDefaultDisplay().getRotation() == 1) || (getWindowManager().getDefaultDisplay().getRotation() == 3))
        onConfigurationChanged(null);
      PackageInfoProvider localPackageInfoProvider = new PackageInfoProvider(this);
      this.mPackageInfo = localPackageInfoProvider;
      if (j != 0)
      {
        localImageView = (ImageView)findViewById(2131558483);
        checkIcon(j);
        Drawable localDrawable = getDrawable(j);
        if (localDrawable == null)
          break label948;
        localImageView.setImageDrawable(localDrawable);
      }
      label703: setTextFromHtml(k, 2131558484, false);
      if ((m != 2131165205) || (PackageInfoProvider.hasRadio() == true))
        break label958;
      setTextFromHtml(2131165206, 2131558486, true);
      label744: if (n != 0)
        break label971;
      findViewById(2131558487).setVisibility(8);
      findViewById(2131558488).setVisibility(8);
    }
    while (true)
    {
      this.inflater = ((LayoutInflater)getSystemService("layout_inflater"));
      this.btnSetHomeAddress = ((Button)findViewById(2131558489));
      if (this.btnSetHomeAddress != null)
      {
        Button localButton = this.btnSetHomeAddress;
        WCISCompositionScreen.3 local3 = new WCISCompositionScreen.3(this);
        localButton.setOnClickListener(local3);
      }
      if (i != 2131362100)
        this.btnSetHomeAddress.setVisibility(8);
      return;
      mTheme = 2131296526;
      break;
      label856: f1 = 476.0F * localDisplayMetrics2.density;
      f2 = 704.0F * localDisplayMetrics2.density;
      this.mOldPX = ((localPoint.y - f1) / 2.0F);
      this.mOldLX = ((localPoint.x - f1) / 2.0F);
      break label527;
      label911: setContentView(2130903042);
      if (i == 0)
        break label607;
      ActionBar localActionBar1 = getActionBar();
      localActionBar1.setDisplayOptions(14);
      localActionBar1.setTitle(getString(i));
      break label607;
      label948: localImageView.setImageResource(j);
      break label703;
      label958: setTextFromHtml(m, 2131558486, true);
      break label744;
      label971: setTextFromHtml(n, 2131558488, false);
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
 * Qualified Name:     com.vlingo.midas.help.WCISCompositionScreen
 * JD-Core Version:    0.6.0
 */