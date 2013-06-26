package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

class NaverLocalChoiceWidget$ListAdapter$1
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    NaverLocalChoiceWidget.ViewHolder localViewHolder = (NaverLocalChoiceWidget.ViewHolder)paramView.getTag();
    if (NaverLocalChoiceWidget.ViewHolder.access$400(localViewHolder) != null)
    {
      localIntent.setData(Uri.parse(NaverLocalChoiceWidget.ViewHolder.access$400(localViewHolder)));
      NaverLocalChoiceWidget.access$100(this.this$1.this$0).startActivity(localIntent);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.NaverLocalChoiceWidget.ListAdapter.1
 * JD-Core Version:    0.6.0
 */