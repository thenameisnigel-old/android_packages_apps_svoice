package com.vlingo.midas.help;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.vlingo.midas.ui.SimpleIconListScreen;
import java.util.ArrayList;
import java.util.HashMap;

class HelpScreen$6
  implements AdapterView.OnItemClickListener
{
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    Intent localIntent2;
    if (((HashMap)this.this$0.getWcisData().getItems().get(paramInt)).get("EXTRA_TITLE_BAR") == null)
    {
      Class localClass = (Class)((HashMap)this.this$0.getWcisData().getItems().get(paramInt)).get("EXTRA_SCREEN");
      localIntent2 = (Intent)((HashMap)this.this$0.getWcisData().getItems().get(paramInt)).get("EXTRA_INTENT");
      if (localClass != null)
      {
        Intent localIntent3 = new Intent(this.this$0, localClass);
        localIntent3.putExtra("wycs.is.iux", false);
        this.this$0.startActivity(localIntent3);
      }
    }
    while (true)
    {
      return;
      if (localIntent2 != null)
      {
        this.this$0.startActivity(localIntent2);
        continue;
        if (paramInt < this.this$0.getWcisData().getItems().size())
        {
          HashMap localHashMap = (HashMap)this.this$0.getWcisData().getItems().get(paramInt);
          Intent localIntent1 = new Intent(this.this$0, WCISCompositionScreen.class);
          localIntent1.putExtra("EXTRA_TITLE_BAR", (Integer)localHashMap.get("EXTRA_TITLE_BAR"));
          localIntent1.putExtra("EXTRA_SUBTITLE", (Integer)localHashMap.get("EXTRA_SUBTITLE"));
          localIntent1.putExtra("EXTRA_SUBTITLE_ICON", (Integer)localHashMap.get("EXTRA_SUBTITLE_ICON"));
          localIntent1.putExtra("EXTRA_EXAMPLE_LIST", (Integer)localHashMap.get("EXTRA_EXAMPLE_LIST"));
          localIntent1.putExtra("EXTRA_DID_YOU_KNOW", (Integer)localHashMap.get("EXTRA_DID_YOU_KNOW"));
          this.this$0.startActivity(localIntent1);
          continue;
        }
        this.this$0.startActivity(new Intent(this.this$0, SimpleIconListScreen.class));
        continue;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.help.HelpScreen.6
 * JD-Core Version:    0.6.0
 */