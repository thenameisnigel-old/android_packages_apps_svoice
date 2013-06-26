package com.vlingo.midas.ui;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;
import java.util.HashMap;

class SimpleListScreen$1
  implements AdapterView.OnItemClickListener
{
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    HashMap localHashMap = (HashMap)this.this$0.m_targets.get(paramInt);
    Object localObject1 = localHashMap.get("activity");
    Object localObject2 = localHashMap.get("url");
    Intent localIntent = null;
    if (localObject1 != null)
    {
      localIntent = new Intent(this.this$0, (Class)localObject1);
      if (localObject2 != null)
        localIntent.putExtra("listitemparam", (String)localObject2);
    }
    while (true)
    {
      if (localIntent != null)
      {
        this.this$0.startActivity(localIntent);
        if (SimpleListScreen.access$000(this.this$0))
          this.this$0.finish();
      }
      return;
      if (localObject2 == null)
        continue;
      localIntent = new Intent("android.intent.action.VIEW", Uri.parse((String)localObject2));
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.ui.SimpleListScreen.1
 * JD-Core Version:    0.6.0
 */