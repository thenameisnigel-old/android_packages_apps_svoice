package com.samsung.myplace;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.midas.settings.MidasSettings;
import java.util.ArrayList;
import java.util.Iterator;

public class MyPlaceActivity extends Activity
{
  static Uri CONTENT_URI = Uri.parse("content://com.android.settings.myplace.MyPlaceProvider");
  private static final String PROVIDER_NAME = "com.android.settings.myplace.MyPlaceProvider";
  private MyPlaceListAdapter adapter;
  SVoiceMyPlaceDBProvider dataBase;
  private ListView mPlaceList;
  private ArrayList<MyPlaceProfile> myPlaceProfileList = new ArrayList();
  private LinearLayout myPlaceTextContainer;

  public void loadFromLocalDatabase()
  {
    this.myPlaceProfileList.clear();
    ContentResolver localContentResolver = getContentResolver();
    Uri localUri = CONTENT_URI;
    String[] arrayOfString = new String[5];
    arrayOfString[0] = "_id";
    arrayOfString[1] = "profile_name";
    arrayOfString[2] = "gps_location";
    arrayOfString[3] = "wifi_ap_name";
    arrayOfString[4] = "bt_device_name";
    Cursor localCursor = localContentResolver.query(localUri, arrayOfString, "(profile_name != 'Home' and profile_name != 'Office') and (type = 2 or type = 3)", null, null);
    ArrayList localArrayList;
    MyPlaceProfile localMyPlaceProfile;
    String str2;
    if ((localCursor != null) && (localCursor.moveToFirst()))
    {
      localArrayList = this.dataBase.getAllMyPlaceDataList();
      if (localCursor.getCount() > 0)
      {
        localMyPlaceProfile = new MyPlaceProfile();
        localMyPlaceProfile.profileId = Integer.parseInt(localCursor.getString(localCursor.getColumnIndex("_id")));
        localMyPlaceProfile.profileName = localCursor.getString(localCursor.getColumnIndex("profile_name"));
        String str1 = localCursor.getString(localCursor.getColumnIndex("bt_device_name"));
        str2 = localCursor.getString(localCursor.getColumnIndex("wifi_ap_name"));
        if ((str1 == null) || (str1.length() <= 0))
          break label286;
        localMyPlaceProfile.selectedBTName = str1;
      }
    }
    while (true)
    {
      localMyPlaceProfile.isSelected = false;
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        if (((MyPlaceData)localIterator.next()).getProfile_id() != localMyPlaceProfile.profileId)
          continue;
        localMyPlaceProfile.isSelected = true;
      }
      this.myPlaceProfileList.add(localMyPlaceProfile);
      if (localCursor.moveToNext())
        break;
      if (localCursor != null)
        localCursor.close();
      return;
      label286: if ((str2 == null) || (str2.length() <= 0))
        continue;
      localMyPlaceProfile.selectWifiName = str2;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    MidasSettings.updateCurrentLocale(getResources());
    setContentView(2130903114);
    ActionBar localActionBar = getActionBar();
    localActionBar.setDisplayOptions(14);
    localActionBar.setTitle(getString(2131362754));
    this.mPlaceList = ((ListView)findViewById(2131558814));
    this.myPlaceTextContainer = ((LinearLayout)findViewById(2131558811));
    this.myPlaceTextContainer.setFocusable(true);
    this.myPlaceTextContainer.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        MyPlaceActivity.this.startActivity(new Intent("android.settings.MY_PLACE_SETTINGS"));
      }
    });
    getContentResolver().registerContentObserver(CONTENT_URI, true, new DbChangeObserver());
    this.dataBase = new SVoiceMyPlaceDBProvider(this);
    this.adapter = new MyPlaceListAdapter();
    this.mPlaceList.setAdapter(this.adapter);
    this.mPlaceList.setItemsCanFocus(true);
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
    MidasSettings.updateCurrentLocale(getResources());
    super.onResume();
    this.adapter.notifyDataSetChanged();
    loadFromLocalDatabase();
  }

  public void saveIntoLocalDatabase()
  {
    this.dataBase = new SVoiceMyPlaceDBProvider(this);
    ArrayList localArrayList = this.dataBase.getAllMyPlaceDataList();
    Cursor localCursor = null;
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
    {
      MyPlaceData localMyPlaceData1 = (MyPlaceData)localIterator.next();
      ContentResolver localContentResolver = getContentResolver();
      Uri localUri = CONTENT_URI;
      String[] arrayOfString = new String[5];
      arrayOfString[0] = "_id";
      arrayOfString[1] = "profile_name";
      arrayOfString[2] = "gps_location";
      arrayOfString[3] = "wifi_ap_name";
      arrayOfString[4] = "bt_device_name";
      localCursor = localContentResolver.query(localUri, arrayOfString, "_id = " + localMyPlaceData1.getProfile_id(), null, null);
      if ((localCursor != null) && (localCursor.moveToFirst()))
      {
        MyPlaceData localMyPlaceData2 = new MyPlaceData();
        localMyPlaceData2.setId(localMyPlaceData1.getId());
        localMyPlaceData2.setPlaceName(localCursor.getString(localCursor.getColumnIndex("profile_name")));
        localMyPlaceData2.setProfile_id(Integer.parseInt(localCursor.getString(localCursor.getColumnIndex("_id"))));
        String str1 = localCursor.getString(localCursor.getColumnIndex("bt_device_name"));
        String str2 = localCursor.getString(localCursor.getColumnIndex("wifi_ap_name"));
        if ((str1 != null) && (str1.length() > 0))
        {
          localMyPlaceData2.setType("3");
          localMyPlaceData2.setValue(str1);
        }
        while (true)
        {
          this.dataBase.updateMyPlaceData(localMyPlaceData2);
          break;
          if ((str2 != null) && (str2.length() > 0))
          {
            localMyPlaceData2.setType("2");
            localMyPlaceData2.setValue(str2);
            continue;
          }
          localMyPlaceData2.setType("0");
          localMyPlaceData2.setValue("None");
        }
      }
      this.dataBase.deleteMyPlaceData(localMyPlaceData1);
    }
    if (localCursor != null)
      localCursor.close();
  }

  class DbChangeObserver extends ContentObserver
  {
    public DbChangeObserver()
    {
      super();
    }

    public void onChange(boolean paramBoolean)
    {
      super.onChange(paramBoolean);
      MyPlaceActivity.this.saveIntoLocalDatabase();
    }
  }

  class MyPlaceListAdapter extends BaseAdapter
    implements View.OnClickListener
  {
    private LayoutInflater mInflater = LayoutInflater.from(MyPlaceActivity.this);

    public MyPlaceListAdapter()
    {
    }

    private String getSelectionTypeName(int paramInt)
    {
      String str = "None";
      if ((((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(paramInt)).selectedBTName != null) && (((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(paramInt)).selectedBTName.length() > 0))
        str = "Bluetooth ";
      if ((((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(paramInt)).selectedLocationName != null) && (((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(paramInt)).selectedLocationName.length() > 0))
        str = "Location ";
      if ((((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(paramInt)).selectWifiName != null) && (((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(paramInt)).selectWifiName.length() > 0))
        str = "Wi-Fi ";
      return str;
    }

    public int getCount()
    {
      return MyPlaceActivity.this.myPlaceProfileList.size();
    }

    public Object getItem(int paramInt)
    {
      return null;
    }

    public long getItemId(int paramInt)
    {
      return 0L;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
        paramView = this.mInflater.inflate(2130903090, null);
      for (ViewHolder localViewHolder = new ViewHolder(); ; localViewHolder = (ViewHolder)paramView.getTag())
      {
        localViewHolder.title = ((TextView)paramView.findViewById(2131558711));
        localViewHolder.checkbox = ((CheckBox)paramView.findViewById(2131558713));
        localViewHolder.checkbox.setFocusable(false);
        localViewHolder.checkbox.setClickable(false);
        localViewHolder.checkbox.setChecked(((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(paramInt)).isSelected);
        localViewHolder.type = ((TextView)paramView.findViewById(2131558712));
        localViewHolder.type.setText(getSelectionTypeName(paramInt));
        localViewHolder.title.setText(((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(paramInt)).profileName);
        localViewHolder.position = paramInt;
        paramView.setTag(localViewHolder);
        paramView.setOnClickListener(this);
        paramView.setFocusable(true);
        paramView.setBackgroundResource(2130837873);
        return paramView;
      }
    }

    public void onClick(View paramView)
    {
      ViewHolder localViewHolder;
      if (paramView.getId() == 2131558713)
      {
        localViewHolder = (ViewHolder)((View)paramView.getParent()).getTag();
        if (((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).isSelected)
          break label537;
        ((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).isSelected = true;
        if ((((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).selectedLocationName != null) && (((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).selectedLocationName.length() > 0))
        {
          MyPlaceData localMyPlaceData4 = new MyPlaceData();
          localMyPlaceData4.setPlaceName(((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).profileName);
          localMyPlaceData4.setProfile_id(((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).profileId);
          localMyPlaceData4.setType("1");
          MyPlaceActivity.this.dataBase.saveDataToDatabase(localMyPlaceData4);
        }
        if ((((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).selectWifiName != null) && (((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).selectWifiName.length() > 0))
        {
          MyPlaceData localMyPlaceData3 = new MyPlaceData();
          localMyPlaceData3.setPlaceName(((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).profileName);
          localMyPlaceData3.setProfile_id(((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).profileId);
          localMyPlaceData3.setValue(((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).selectWifiName);
          localMyPlaceData3.setType("2");
          MyPlaceActivity.this.dataBase.saveDataToDatabase(localMyPlaceData3);
        }
        if ((((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).selectedBTName != null) && (((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).selectedBTName.length() > 0))
        {
          MyPlaceData localMyPlaceData2 = new MyPlaceData();
          localMyPlaceData2.setPlaceName(((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).profileName);
          localMyPlaceData2.setProfile_id(((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).profileId);
          localMyPlaceData2.setValue(((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).selectedBTName);
          localMyPlaceData2.setType("3");
          MyPlaceActivity.this.dataBase.saveDataToDatabase(localMyPlaceData2);
        }
      }
      while (true)
      {
        localViewHolder.checkbox.setChecked(((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).isSelected);
        return;
        localViewHolder = (ViewHolder)paramView.getTag();
        break;
        label537: ((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).isSelected = false;
        MyPlaceData localMyPlaceData1 = new MyPlaceData();
        localMyPlaceData1.setProfile_id(((MyPlaceActivity.MyPlaceProfile)MyPlaceActivity.this.myPlaceProfileList.get(localViewHolder.position)).profileId);
        MyPlaceActivity.this.dataBase.deleteMyPlaceData(localMyPlaceData1);
      }
    }

    class ViewHolder
    {
      CheckBox checkbox;
      int position;
      TextView title;
      TextView type;

      ViewHolder()
      {
      }
    }
  }

  class MyPlaceProfile
  {
    boolean isSelected;
    int profileId;
    String profileName;
    String selectWifiName;
    String selectedBTName;
    String selectedLocationName;

    MyPlaceProfile()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.myplace.MyPlaceActivity
 * JD-Core Version:    0.6.0
 */