package com.vlingo.midas.help;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.midas.VlingoApplication;
import com.vlingo.midas.iux.UsingVlingoScreen;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.ui.PackageInfoProvider;
import com.vlingo.midas.ui.SimpleIconListScreen;
import com.vlingo.midas.ui.VLActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class WhatCanISayScreen extends VLActivity
{
  public static final String EXTRA_SHOW_DONE = "wycs.show.done";
  PackageInfoProvider mPackageInfo;
  private WCISData wcisData = new WCISData();

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

  public WCISData getWcisData()
  {
    return this.wcisData;
  }

  protected void init()
  {
    setContentView(2130903131);
    ActionBar localActionBar = getActionBar();
    if (localActionBar != null)
      localActionBar.setDisplayOptions(14);
    localActionBar.setTitle(getString(2131362509));
    getWcisData().addItems(this);
    if (getIntent().getBooleanExtra("wycs.show.done", false))
    {
      ((ViewGroup)findViewById(2131558848)).setVisibility(0);
      findViewById(2131558849).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          Intent localIntent = new Intent(WhatCanISayScreen.this, VlingoApplication.getInstance().getMainActivityClass());
          WhatCanISayScreen.this.startActivity(localIntent);
          WhatCanISayScreen.this.overridePendingTransition(17432576, 17432577);
          WhatCanISayScreen.this.finish();
        }
      });
    }
    ListView localListView = (ListView)findViewById(2131558845);
    localListView.setAdapter(new RowAdapter());
    localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        String str = (String)((HashMap)WhatCanISayScreen.this.getWcisData().getItems().get(paramInt)).get("EXTRA_CAPTION");
        if ((str != null) && (WhatCanISayScreen.this.getString(2131362491).equals(str)))
          WhatCanISayScreen.this.startActivity(new Intent(WhatCanISayScreen.this, AboutScreen.class));
        Intent localIntent2;
        if (((HashMap)WhatCanISayScreen.this.getWcisData().getItems().get(paramInt)).get("EXTRA_TITLE_BAR") == null)
        {
          Class localClass = (Class)((HashMap)WhatCanISayScreen.this.getWcisData().getItems().get(paramInt)).get("EXTRA_SCREEN");
          localIntent2 = (Intent)((HashMap)WhatCanISayScreen.this.getWcisData().getItems().get(paramInt)).get("EXTRA_INTENT");
          if ((localClass != null) && (((HashMap)WhatCanISayScreen.this.getWcisData().getItems().get(paramInt)).get("EXTRA_SCREEN") != UsingVlingoScreen.class))
          {
            Intent localIntent3 = new Intent(WhatCanISayScreen.this, localClass);
            localIntent3.putExtra("wycs.is.iux", false);
            WhatCanISayScreen.this.startActivity(localIntent3);
          }
        }
        while (true)
        {
          return;
          if (localIntent2 != null)
          {
            WhatCanISayScreen.this.startActivity(localIntent2);
            continue;
            if (paramInt < WhatCanISayScreen.this.getWcisData().getItems().size())
            {
              HashMap localHashMap = (HashMap)WhatCanISayScreen.this.getWcisData().getItems().get(paramInt);
              Intent localIntent1 = new Intent(WhatCanISayScreen.this, WCISCompositionScreen.class);
              localIntent1.putExtra("EXTRA_TITLE_BAR", (Integer)localHashMap.get("EXTRA_TITLE_BAR"));
              localIntent1.putExtra("EXTRA_SUBTITLE", (Integer)localHashMap.get("EXTRA_SUBTITLE"));
              localIntent1.putExtra("EXTRA_SUBTITLE_ICON", (Integer)localHashMap.get("EXTRA_SUBTITLE_ICON"));
              localIntent1.putExtra("EXTRA_EXAMPLE_LIST", (Integer)localHashMap.get("EXTRA_EXAMPLE_LIST"));
              localIntent1.putExtra("EXTRA_DID_YOU_KNOW", (Integer)localHashMap.get("EXTRA_DID_YOU_KNOW"));
              WhatCanISayScreen.this.startActivity(localIntent1);
              continue;
            }
            WhatCanISayScreen.this.startActivity(new Intent(WhatCanISayScreen.this, SimpleIconListScreen.class));
            continue;
          }
        }
      }
    });
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    MidasSettings.updateCurrentLocale();
    this.mPackageInfo = new PackageInfoProvider(this);
    checkIcon(2130837847);
    checkIcon(2130837808);
    checkIcon(2130837846);
    checkIcon(2130837843);
    checkIcon(2130837803);
    init();
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

  public void setWcisData(WCISData paramWCISData)
  {
    this.wcisData = paramWCISData;
  }

  public class RowAdapter extends BaseAdapter
  {
    public RowAdapter()
    {
    }

    public int getCount()
    {
      return WhatCanISayScreen.this.getWcisData().getItems().size();
    }

    public Object getItem(int paramInt)
    {
      return Integer.valueOf(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      HashMap localHashMap;
      View localView;
      if (paramInt < WhatCanISayScreen.this.getWcisData().getItems().size())
      {
        localHashMap = (HashMap)WhatCanISayScreen.this.getWcisData().getItems().get(paramInt);
        if (localHashMap.containsKey("EXTRA_INFO"))
        {
          localView = View.inflate(WhatCanISayScreen.this, 2130903130, null);
          TextView localTextView3 = (TextView)localView.findViewById(2131558843);
          TextView localTextView4 = (TextView)localView.findViewById(2131558838);
          localTextView3.setText((String)localHashMap.get("EXTRA_INFO"));
          localTextView4.setText((String)localHashMap.get("EXTRA_CAPTION"));
          localView.findViewById(2131558694).setVisibility(8);
        }
      }
      while (true)
      {
        return localView;
        if (localHashMap.containsKey("EXTRA_SUBHEADING"))
        {
          localView = View.inflate(WhatCanISayScreen.this, 2130903133, null);
          ((TextView)localView.findViewById(2131558843)).setText((String)localHashMap.get("EXTRA_SUBHEADING"));
          continue;
        }
        localView = View.inflate(WhatCanISayScreen.this, 2130903132, null);
        TextView localTextView1 = (TextView)localView.findViewById(2131558843);
        TextView localTextView2 = (TextView)localView.findViewById(2131558838);
        ImageView localImageView = (ImageView)localView.findViewById(2131558724);
        Drawable localDrawable = WhatCanISayScreen.this.getDrawable(((Integer)localHashMap.get("EXTRA_LIST_ICON")).intValue());
        if (localDrawable != null)
          localImageView.setImageDrawable(localDrawable);
        while (true)
        {
          localTextView1.setText((String)localHashMap.get("EXTRA_LIST_TITLE"));
          localTextView2.setText((String)localHashMap.get("EXTRA_LIST_EXAMPLE"));
          break;
          localImageView.setImageResource(((Integer)localHashMap.get("EXTRA_LIST_ICON")).intValue());
        }
        localView = View.inflate(WhatCanISayScreen.this, 2130903129, null);
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.help.WhatCanISayScreen
 * JD-Core Version:    0.6.0
 */