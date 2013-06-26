package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

class NaverMovieChoiceWidget$MovieAdapter$1
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    NaverMovieChoiceWidget.MovieHolder localMovieHolder = (NaverMovieChoiceWidget.MovieHolder)paramView.getTag();
    if (localMovieHolder.mServicelink != null)
    {
      localIntent.setData(Uri.parse(localMovieHolder.mServicelink));
      NaverMovieChoiceWidget.access$200(this.this$1.this$0).startActivity(localIntent);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.NaverMovieChoiceWidget.MovieAdapter.1
 * JD-Core Version:    0.6.0
 */