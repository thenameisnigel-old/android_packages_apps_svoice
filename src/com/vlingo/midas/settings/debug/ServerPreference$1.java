package com.vlingo.midas.settings.debug;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import java.util.ArrayList;

class ServerPreference$1
  implements AdapterView.OnItemSelectedListener
{
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (paramInt != 0)
    {
      ServerPreference.access$002(this.this$0, (String)this.this$0.servers.get(paramInt));
      ServerPreference.access$100(this.this$0).setText(ServerPreference.access$000(this.this$0));
    }
  }

  public void onNothingSelected(AdapterView<?> paramAdapterView)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.ServerPreference.1
 * JD-Core Version:    0.6.0
 */