package com.vlingo.midas.gui.widgets;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

class NaverMovieChoiceWidget$1$1
  implements Runnable
{
  public void run()
  {
    NaverMovieChoiceWidget.MovieHolder localMovieHolder = (NaverMovieChoiceWidget.MovieHolder)NaverMovieChoiceWidget.access$100(this.this$1.this$0).getChildAt(this.val$ind).getTag();
    if (this.val$d != null)
      localMovieHolder.mMoviePoster.setImageDrawable(this.val$d);
    localMovieHolder.mMoviePoster.setVisibility(0);
    NaverMovieChoiceWidget.access$100(this.this$1.this$0).getChildAt(this.val$ind).findViewById(2131558764).setVisibility(8);
    localMovieHolder.mMoviePoster.invalidate();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.NaverMovieChoiceWidget.1.1
 * JD-Core Version:    0.6.0
 */