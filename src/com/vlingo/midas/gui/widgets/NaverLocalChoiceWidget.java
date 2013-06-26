package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.util.DialUtil;
import com.vlingo.midas.gui.customviews.Button;
import com.vlingo.midas.naver.NaverAdaptor;
import com.vlingo.midas.naver.NaverLocalItem;
import com.vlingo.midas.naver.NaverXMLParser;
import java.util.Hashtable;
import java.util.List;

public class NaverLocalChoiceWidget extends BargeInWidget<NaverAdaptor>
{
  private static int mDensity = 2;
  private WidgetActionListener listener;
  private List<Hashtable<String, String>> localSearchListings;
  private NaverAdaptor lsAdaptor;
  private final Context mContext;
  private ListView mLocalSearchList;
  private String mMainLocation;

  public NaverLocalChoiceWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    mDensity = this.mContext.getResources().getDisplayMetrics().densityDpi / 160;
  }

  protected static void setListViewHeightBasedOnChildren(ListView paramListView)
  {
    ViewGroup.LayoutParams localLayoutParams = paramListView.getLayoutParams();
    localLayoutParams.height = (int)(paramListView.getCount() * paramListView.getContext().getResources().getDimension(2131427357) + (-1 + paramListView.getCount()) * mDensity);
    paramListView.setLayoutParams(localLayoutParams);
    paramListView.requestLayout();
  }

  public void initialize(NaverAdaptor paramNaverAdaptor, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.listener = paramWidgetActionListener;
    this.lsAdaptor = paramNaverAdaptor;
    this.localSearchListings = paramNaverAdaptor.getNaverXML().itemList;
    ListAdapter localListAdapter = new ListAdapter(null);
    this.mLocalSearchList.setAdapter(localListAdapter);
    TextView localTextView1 = (TextView)findViewById(2131559104);
    TextView localTextView2 = (TextView)findViewById(2131559105);
    NaverXMLParser localNaverXMLParser = paramNaverAdaptor.getNaverXML();
    String str = (String)localNaverXMLParser.propertyList.get("biz");
    this.mMainLocation = ((String)localNaverXMLParser.propertyList.get("location"));
    if ((str != null) && (!str.isEmpty()))
    {
      localTextView1.setText(str);
      if ((this.mMainLocation == null) || (this.mMainLocation.isEmpty()))
        break label222;
      localTextView2.setText(this.mMainLocation + " " + this.mContext.getString(2131362201));
    }
    while (true)
    {
      ((Button)findViewById(2131559066)).setOnClickListener(new MoreClickListener((String)localNaverXMLParser.propertyList.get("moreUrl")));
      return;
      localTextView1.setText("--");
      break;
      label222: localTextView2.setVisibility(8);
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

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.mLocalSearchList = ((ListView)findViewById(2131558893));
    this.mLocalSearchList.setDivider(null);
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    measureListviewHeight(this.mLocalSearchList, 2131427357, false);
    super.onMeasure(paramInt1, paramInt2);
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  private class CallClickListener
    implements View.OnClickListener
  {
    private final String phoneNumber;

    public CallClickListener(String arg2)
    {
      Object localObject;
      this.phoneNumber = localObject;
    }

    public void onClick(View paramView)
    {
      new Intent("com.vlingo.core.internal.dialogmanager.AcceptedText");
      Intent localIntent = DialUtil.getDialIntent(this.phoneNumber);
      NaverLocalChoiceWidget.this.mContext.startActivity(localIntent);
    }
  }

  private class ListAdapter extends BaseAdapter
  {
    private ListAdapter()
    {
    }

    public int getCount()
    {
      int i = NaverLocalChoiceWidget.this.localSearchListings.size();
      return NaverLocalChoiceWidget.this.getLimitedCount(i);
    }

    public Object getItem(int paramInt)
    {
      return NaverLocalChoiceWidget.this.localSearchListings.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      NaverLocalChoiceWidget.ViewHolder localViewHolder;
      NaverLocalItem localNaverLocalItem;
      if (paramView == null)
      {
        paramView = LayoutInflater.from(NaverLocalChoiceWidget.this.mContext).inflate(2130903097, paramViewGroup, false);
        localViewHolder = new NaverLocalChoiceWidget.ViewHolder(NaverLocalChoiceWidget.this);
        localViewHolder.name = ((TextView)paramView.findViewById(2131558716));
        localViewHolder.rate_img = ((RatingBar)paramView.findViewById(2131558718));
        localViewHolder.review_count_num = ((TextView)paramView.findViewById(2131558719));
        localViewHolder.distance = ((TextView)paramView.findViewById(2131558775));
        localViewHolder.category = ((TextView)paramView.findViewById(2131558776));
        localViewHolder.address = ((TextView)paramView.findViewById(2131558720));
        localViewHolder.call = ((ImageButton)paramView.findViewById(2131558721));
        localViewHolder.content = paramView.findViewById(2131558714);
        localViewHolder.mDivider = paramView.findViewById(2131558773);
        paramView.setTag(localViewHolder);
        localNaverLocalItem = new NaverLocalItem((Hashtable)NaverLocalChoiceWidget.this.localSearchListings.get(paramInt));
        localViewHolder.name.setText(localNaverLocalItem.getTitle());
        localViewHolder.rate_img.setRating(localNaverLocalItem.getRating().floatValue() / 10.0F * localViewHolder.rate_img.getNumStars());
        int i = localNaverLocalItem.getReviewCount().intValue();
        localViewHolder.review_count_num.setText("(" + i + ")");
        localViewHolder.distance.setText(localNaverLocalItem.getDistance());
        String str = NaverLocalItem.getSeparatedLocation(NaverLocalChoiceWidget.this.mMainLocation, localNaverLocalItem.getAddress());
        localNaverLocalItem.getDistance();
        if ((str != null) && (str.length() > 0))
          localViewHolder.address.setText(str);
        localViewHolder.category.setText(localNaverLocalItem.getCategory());
        NaverLocalChoiceWidget.ViewHolder.access$402(localViewHolder, localNaverLocalItem.getDetailUrl());
        if (localNaverLocalItem.getPhoneUrl() != null)
          break label412;
        localViewHolder.call.setVisibility(8);
        label372: localViewHolder.content.setOnClickListener(new NaverLocalChoiceWidget.ListAdapter.1(this));
        paramView.setBackgroundResource(2130837819);
        if (paramInt != 0)
          break label439;
      }
      while (true)
      {
        return paramView;
        localViewHolder = (NaverLocalChoiceWidget.ViewHolder)paramView.getTag();
        break;
        label412: localViewHolder.call.setOnClickListener(new NaverLocalChoiceWidget.CallClickListener(NaverLocalChoiceWidget.this, localNaverLocalItem.getPhoneUrl()));
        break label372;
        label439: if (paramInt < -1 + getCount())
          continue;
        localViewHolder.mDivider.setVisibility(8);
      }
    }
  }

  private class MoreClickListener
    implements View.OnClickListener
  {
    private final String url;

    public MoreClickListener(String arg2)
    {
      Object localObject;
      this.url = localObject;
    }

    public void onClick(View paramView)
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse(this.url));
      NaverLocalChoiceWidget.this.mContext.startActivity(localIntent);
    }
  }

  class ViewHolder
  {
    TextView address;
    ImageButton call;
    public TextView category;
    View content;
    private String detailLink;
    TextView distance;
    public View mDivider;
    TextView name;
    RatingBar rate_img;
    TextView review_count_num;

    ViewHolder()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.NaverLocalChoiceWidget
 * JD-Core Version:    0.6.0
 */