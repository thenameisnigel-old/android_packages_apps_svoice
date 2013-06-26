package com.samsung.wakeupsetting;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TwoLineListItem;
import java.util.ArrayList;
import java.util.List;

public class CustomWakeupSetting extends Activity
{
  private AlertDialog alert;
  private View customCommandlist;
  final String[] wakeupCommand;
  ListView wakeupList;

  public CustomWakeupSetting()
  {
    String[] arrayOfString = new String[4];
    arrayOfString[0] = getString(2131362660);
    arrayOfString[1] = getString(2131362661);
    arrayOfString[2] = getString(2131362662);
    arrayOfString[3] = getString(2131362663);
    this.wakeupCommand = arrayOfString;
  }

  private void showCustomCommandlist()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new WakeupCommand(getString(2131362660), "Hi, Galaxy"));
    localArrayList.add(new WakeupCommand(getString(2131362661), "Check schedule"));
    localArrayList.add(new WakeupCommand(getString(2131362662), "Play radio"));
    localArrayList.add(new WakeupCommand(getString(2131362663), ""));
    3 local3 = new ArrayAdapter(this, 17367044, localArrayList, localArrayList)
    {
      public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
      {
        if (paramView == null);
        for (TwoLineListItem localTwoLineListItem = (TwoLineListItem)((LayoutInflater)CustomWakeupSetting.this.getApplicationContext().getSystemService("layout_inflater")).inflate(17367044, null); ; localTwoLineListItem = (TwoLineListItem)paramView)
        {
          CustomWakeupSetting.WakeupCommand localWakeupCommand = (CustomWakeupSetting.WakeupCommand)this.val$list.get(paramInt);
          localTwoLineListItem.getText1().setText(localWakeupCommand.getName());
          localTwoLineListItem.getText2().setText(localWakeupCommand.getValue());
          return localTwoLineListItem;
        }
      }
    };
    this.wakeupList.setAdapter(local3);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903127);
    this.wakeupList = ((ListView)findViewById(2131558840));
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }

  protected void onPause()
  {
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage("WakeUp Command is available only when Settings > Security > Screen Lock is set as None only. Set?").setCancelable(false).setPositiveButton(2131362527, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        paramDialogInterface.dismiss();
        CustomWakeupSetting.this.showCustomCommandlist();
      }
    }).setNegativeButton(2131362528, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        CustomWakeupSetting.this.finish();
      }
    });
    this.alert = localBuilder.create();
    this.alert.show();
  }

  public class WakeupCommand
  {
    public String function;
    public String wakeupcommand;

    public WakeupCommand(String paramString1, String arg3)
    {
      this.function = paramString1;
      Object localObject;
      this.wakeupcommand = localObject;
    }

    public String getName()
    {
      return this.function;
    }

    public String getValue()
    {
      return this.wakeupcommand;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wakeupsetting.CustomWakeupSetting
 * JD-Core Version:    0.6.0
 */