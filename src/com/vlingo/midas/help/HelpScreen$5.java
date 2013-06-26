package com.vlingo.midas.help;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.midas.ui.PackageInfoProvider;

class HelpScreen$5
  implements AdapterView.OnItemSelectedListener
{
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    int i = 3;
    if (Settings.isAsrEditingEnabled())
    {
      if (this.this$0.mPackageInfo.hasPenFeature())
        i++;
      i++;
    }
    if (!HelpScreen.access$700(this.this$0))
    {
      if (paramInt != 0)
        break label71;
      this.val$lv.setSelectionFromTop(1, 100);
      HelpScreen.access$802(this.this$0, paramInt);
    }
    while (true)
    {
      HelpScreen.access$802(this.this$0, paramInt);
      return;
      label71: if ((paramInt == i) && (HelpScreen.access$800(this.this$0) == i - 1))
      {
        this.val$lv.setSelectionFromTop(i + 1, 1000);
        continue;
      }
      if ((paramInt != i) || (HelpScreen.access$800(this.this$0) != i + 1))
        continue;
      this.val$lv.setSelectionFromTop(i - 1, 1000);
    }
  }

  public void onNothingSelected(AdapterView<?> paramAdapterView)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.help.HelpScreen.5
 * JD-Core Version:    0.6.0
 */