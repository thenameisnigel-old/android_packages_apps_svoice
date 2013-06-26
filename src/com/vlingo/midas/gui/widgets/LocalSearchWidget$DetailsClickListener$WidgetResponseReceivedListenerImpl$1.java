package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.vlingo.core.internal.localsearch.LocalSearchListing;
import com.vlingo.core.internal.localsearch.LocalSearchListing.Review;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.gui.customviews.ItemReviewDetailView;
import java.util.ArrayList;
import java.util.Iterator;

class LocalSearchWidget$DetailsClickListener$WidgetResponseReceivedListenerImpl$1
  implements Runnable
{
  public void run()
  {
    this.this$2.this$1.listingProgressBar.setVisibility(8);
    if (this.val$reviewListing.getReviews().size() > 0)
    {
      this.this$2.this$1.layoutReviews.setVisibility(0);
      this.this$2.this$1.textReviews.setVisibility(0);
      Iterator localIterator = this.val$reviewListing.getReviews().iterator();
      while (localIterator.hasNext())
      {
        LocalSearchListing.Review localReview = (LocalSearchListing.Review)localIterator.next();
        this.this$2.this$1.layoutReviews.addView(ItemReviewDetailView.create(LocalSearchWidget.access$600(this.this$2.this$1.this$0), localReview));
      }
    }
    if (StringUtils.isNullOrWhiteSpace(LocalSearchWidget.DetailsClickListener.access$800(this.this$2.this$1)))
      LocalSearchWidget.DetailsClickListener.access$802(this.this$2.this$1, LocalSearchWidget.DetailsClickListener.access$700(this.this$2.this$1).getProvider());
    if (!StringUtils.isNullOrWhiteSpace(LocalSearchWidget.DetailsClickListener.access$800(this.this$2.this$1)))
    {
      this.this$2.this$1.providerView.setText(this.this$2.this$1.this$0.getContext().getString(2131362303) + " " + LocalSearchWidget.DetailsClickListener.access$800(this.this$2.this$1));
      this.this$2.this$1.providerView.setVisibility(0);
    }
    while (true)
    {
      return;
      this.this$2.this$1.providerView.setVisibility(8);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.LocalSearchWidget.DetailsClickListener.WidgetResponseReceivedListenerImpl.1
 * JD-Core Version:    0.6.0
 */