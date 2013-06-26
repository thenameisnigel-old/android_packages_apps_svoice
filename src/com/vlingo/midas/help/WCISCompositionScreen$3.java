package com.vlingo.midas.help;

import android.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.vlingo.core.internal.settings.Settings;

class WCISCompositionScreen$3
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    LinearLayout localLinearLayout = (LinearLayout)this.this$0.inflater.inflate(2130903065, null);
    String str1 = this.this$0.getString(2131362396);
    String str2 = this.this$0.getString(2131362324);
    String str3 = this.this$0.getString(2131362248);
    WCISCompositionScreen.access$702(this.this$0, (EditText)localLinearLayout.findViewById(2131558578));
    String str4 = Settings.getString("car_nav_home_address", null);
    if ((str4 != null) && (str4.length() > 0))
      WCISCompositionScreen.access$700(this.this$0).setText(str4);
    WCISCompositionScreen.access$700(this.this$0).requestFocus();
    WCISCompositionScreen.access$700(this.this$0).setFocusable(true);
    WCISCompositionScreen.access$700(this.this$0).selectAll();
    WCISCompositionScreen.access$700(this.this$0).postDelayed(new WCISCompositionScreen.3.1(this), 200L);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.this$0);
    localBuilder.setTitle(str1);
    localBuilder.setView(localLinearLayout);
    localBuilder.setPositiveButton(str2, new WCISCompositionScreen.3.2(this));
    localBuilder.setNegativeButton(str3, new WCISCompositionScreen.3.3(this));
    localBuilder.show();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.help.WCISCompositionScreen.3
 * JD-Core Version:    0.6.0
 */