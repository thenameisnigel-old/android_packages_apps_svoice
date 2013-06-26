package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.util.DialUtil;
import com.vlingo.midas.naver.NaverAdaptor;
import com.vlingo.midas.naver.NaverLocalItem;
import com.vlingo.midas.naver.NaverXMLParser;
import java.util.Hashtable;
import java.util.List;

public class NaverLocalWidget extends BargeInWidget<NaverAdaptor>
  implements View.OnClickListener
{
  private Button btnCall;
  private Button btnFindRoute;
  private Button btnMore;
  private NaverLocalItem item;
  private TextView mAddress;
  private TextView mCallNumber;
  private TextView mCategory;
  private final Context mContext;
  private TextView mDistance;
  private ImageView mMap;
  private RatingBar mRate_img;
  private TextView mReview_count_num;
  private TextView mTitle;
  private VVSActionHandlerListener mhandler;
  private NaverAdaptor wAdaptor;

  public NaverLocalWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  public void initialize(NaverAdaptor paramNaverAdaptor, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.wAdaptor = paramNaverAdaptor;
    this.mhandler = this.wAdaptor.getVVSActionHandlerListener();
    this.item = new NaverLocalItem((Hashtable)this.wAdaptor.getNaverXML().itemList.get(0));
    this.mTitle.setText(this.item.getTitle());
    this.mAddress.setText(this.item.getAddress());
    this.mCallNumber.setText(this.item.getPhoneUrl());
    int i = this.item.getReviewCount().intValue();
    this.mReview_count_num.setText("(" + i + ")");
    this.mDistance.setText(this.item.getDistance());
    this.mRate_img.setRating(this.item.getRating().floatValue() / 10.0F * this.mRate_img.getNumStars());
    this.mCategory.setText(this.item.getCategory());
    new Thread(new NaverLocalWidget.1(this)).start();
    if (this.item.getPhoneUrl() != null)
    {
      if (this.item.getRouteUrl() == null)
        break label250;
      label227: if (this.item.getDetailUrl() == null)
        break label262;
    }
    while (true)
    {
      return;
      this.btnCall.setVisibility(8);
      break;
      label250: this.btnFindRoute.setVisibility(8);
      break label227;
      label262: this.btnMore.setVisibility(8);
    }
  }

  public boolean isTranslated()
  {
    return false;
  }

  public boolean isWidgetReplaceable()
  {
    return false;
  }

  public void onClick(View paramView)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    if ((paramView == this.btnFindRoute) && (this.item.getRouteUrl() != null))
      localIntent.setData(Uri.parse(this.item.getRouteUrl()));
    while (true)
    {
      this.mContext.startActivity(localIntent);
      do
      {
        return;
        if ((paramView != this.btnMore) || (this.item.getDetailUrl() == null))
          continue;
        localIntent.setData(Uri.parse(this.item.getDetailUrl()));
        break;
      }
      while ((paramView != this.btnCall) || (this.item.getPhoneUrl() == null));
      localIntent = DialUtil.getDialIntent(this.item.getPhoneUrl());
    }
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.mTitle = ((TextView)findViewById(2131559094));
    this.mMap = ((ImageView)findViewById(2131559098));
    this.mAddress = ((TextView)findViewById(2131559096));
    this.mCallNumber = ((TextView)findViewById(2131559097));
    this.btnFindRoute = ((Button)findViewById(2131559101));
    this.btnFindRoute.setOnClickListener(this);
    this.btnMore = ((Button)findViewById(2131559102));
    this.btnMore.setOnClickListener(this);
    this.btnCall = ((Button)findViewById(2131559100));
    this.btnCall.setOnClickListener(this);
    this.mRate_img = ((RatingBar)findViewById(2131558718));
    this.mReview_count_num = ((TextView)findViewById(2131558719));
    this.mDistance = ((TextView)findViewById(2131559095));
    this.mCategory = ((TextView)findViewById(2131558776));
  }

  public void retire()
  {
    super.retire();
    this.btnMore.setVisibility(8);
    this.btnCall.setVisibility(8);
    this.btnFindRoute.setVisibility(8);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.NaverLocalWidget
 * JD-Core Version:    0.6.0
 */