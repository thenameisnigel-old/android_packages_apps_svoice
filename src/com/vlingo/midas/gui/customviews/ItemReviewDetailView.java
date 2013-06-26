package com.vlingo.midas.gui.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.vlingo.core.internal.localsearch.LocalSearchListing.Review;

public class ItemReviewDetailView extends RelativeLayout
{
  private RatingBar ratingView;
  private TextView textAuthorView;
  private TextView textDateView;
  private TextView textReviewBodyView;
  private TextView textTitleView;

  public ItemReviewDetailView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public static ItemReviewDetailView create(Context paramContext, LocalSearchListing.Review paramReview)
  {
    ItemReviewDetailView localItemReviewDetailView = (ItemReviewDetailView)View.inflate(paramContext, 2130903093, null);
    localItemReviewDetailView.ratingView.setRating(paramReview.getRating());
    localItemReviewDetailView.textTitleView.setText(paramReview.title);
    localItemReviewDetailView.textAuthorView.setText(paramReview.author);
    localItemReviewDetailView.textDateView.setText(paramReview.date);
    localItemReviewDetailView.textReviewBodyView.setText(paramReview.body);
    return localItemReviewDetailView;
  }

  public RatingBar getRatingView()
  {
    return this.ratingView;
  }

  public TextView getTextAuthorView()
  {
    return this.textAuthorView;
  }

  public TextView getTextDateView()
  {
    return this.textDateView;
  }

  public TextView getTextReviewBodyView()
  {
    return this.textReviewBodyView;
  }

  public TextView getTextTitleView()
  {
    return this.textTitleView;
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.ratingView = ((RatingBar)findViewById(2131558754));
    this.textTitleView = ((TextView)findViewById(2131558692));
    this.textAuthorView = ((TextView)findViewById(2131558755));
    this.textDateView = ((TextView)findViewById(2131558757));
    this.textReviewBodyView = ((TextView)findViewById(2131558756));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.customviews.ItemReviewDetailView
 * JD-Core Version:    0.6.0
 */