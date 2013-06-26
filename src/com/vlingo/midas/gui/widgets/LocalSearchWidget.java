package com.vlingo.midas.gui.widgets;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.localsearch.LocalSearchAdaptor;
import com.vlingo.core.internal.localsearch.LocalSearchListing;
import com.vlingo.core.internal.recognition.acceptedtext.AcceptedText.ActionType;
import com.vlingo.core.internal.recognition.acceptedtext.LocalSearchAcceptedText;
import com.vlingo.core.internal.util.CorePackageInfoProvider;
import com.vlingo.core.internal.util.DialUtil;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.vcs.WidgetResponseReceivedListener;
import com.vlingo.midas.ClientConfiguration;
import com.vlingo.midas.settings.MidasSettings;
import java.util.ArrayList;
import java.util.Vector;

public class LocalSearchWidget extends BargeInWidget<LocalSearchAdaptor>
{
  private static final String ACTION_NAVIGATE_CHINESE = "com.autonavi.xmgd.action.NAVIGATE";
  private static final String ACTION_SHOWMAP_CHINESE = "com.autonavi.xmgd.action.SHOWMAP";
  private Context context;
  private LocalSearchDialog dialog;
  private WidgetActionListener listener;
  private ArrayList<LocalSearchListing> localSearchListings;
  private LocalSearchAdaptor lsAdaptor;
  private ListView mLocalSearchList;

  public LocalSearchWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
  }

  public void initialize(LocalSearchAdaptor paramLocalSearchAdaptor, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.listener = paramWidgetActionListener;
    this.lsAdaptor = paramLocalSearchAdaptor;
    int i = this.lsAdaptor.getCount();
    this.localSearchListings = new ArrayList(i);
    for (int j = 0; j < i; j++)
      this.localSearchListings.add(this.lsAdaptor.getItem(j));
    this.mLocalSearchList.post(new Runnable()
    {
      public void run()
      {
        if (LocalSearchWidget.this.localSearchListings.size() > 0)
        {
          LocalSearchWidget.ListAdapter localListAdapter = new LocalSearchWidget.ListAdapter(LocalSearchWidget.this, null);
          LocalSearchWidget.this.mLocalSearchList.setAdapter(localListAdapter);
        }
      }
    });
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
    setListViewHeightBasedOnChildren(this.mLocalSearchList);
    super.onMeasure(paramInt1, paramInt2);
  }

  public void onRecognitionStarted()
  {
    if (this.dialog != null)
    {
      this.dialog.dismiss();
      this.dialog = null;
    }
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  private class CallClickListener
    implements View.OnClickListener
  {
    private String phoneNumber;

    public CallClickListener(String arg2)
    {
      Object localObject;
      this.phoneNumber = localObject;
    }

    public void onClick(View paramView)
    {
      Intent localIntent1 = new Intent("com.vlingo.core.internal.dialogmanager.AcceptedText");
      LocalSearchWidget.this.listener.handleIntent(localIntent1, new LocalSearchAcceptedText(LocalSearchWidget.this.lsAdaptor.getCurrentSearchQuery(), AcceptedText.ActionType.CALL.name()));
      Intent localIntent2 = DialUtil.getDialIntent(this.phoneNumber);
      LocalSearchWidget.this.context.startActivity(localIntent2);
    }
  }

  private class DetailsClickListener
    implements View.OnClickListener
  {
    LinearLayout layoutReviews;
    private LocalSearchListing listing;
    ProgressBar listingProgressBar;
    private String originalProvider;
    TextView providerView;
    TextView textReviews;

    public DetailsClickListener(LocalSearchListing arg2)
    {
      Object localObject;
      this.listing = localObject;
      this.originalProvider = localObject.getProvider();
    }

    private View getDetailLocalSearchView()
    {
      View localView = LayoutInflater.from(LocalSearchWidget.this.context).inflate(2130903092, null);
      TextView localTextView1 = (TextView)localView.findViewById(2131558692);
      TextView localTextView2 = (TextView)localView.findViewById(2131558748);
      TextView localTextView3 = (TextView)localView.findViewById(2131558730);
      TextView localTextView4 = (TextView)localView.findViewById(2131558731);
      TextView localTextView5 = (TextView)localView.findViewById(2131558732);
      TextView localTextView6 = (TextView)localView.findViewById(2131558727);
      TextView localTextView7 = (TextView)localView.findViewById(2131558729);
      TextView localTextView8 = (TextView)localView.findViewById(2131558749);
      RatingBar localRatingBar = (RatingBar)localView.findViewById(2131558726);
      this.listingProgressBar = ((ProgressBar)localView.findViewById(2131558723));
      ImageButton localImageButton1 = (ImageButton)localView.findViewById(2131558734);
      ImageButton localImageButton2 = (ImageButton)localView.findViewById(2131558735);
      ImageButton localImageButton3 = (ImageButton)localView.findViewById(2131558736);
      ImageButton localImageButton4 = (ImageButton)localView.findViewById(2131558737);
      LinearLayout localLinearLayout1 = (LinearLayout)localView.findViewById(2131558740);
      LinearLayout localLinearLayout2 = (LinearLayout)localView.findViewById(2131558745);
      ImageButton localImageButton5 = (ImageButton)localView.findViewById(2131558741);
      localTextView1.setText(this.listing.getName());
      int i = 0;
      LinearLayout localLinearLayout3 = (LinearLayout)localView.findViewById(2131558746);
      if (this.listing.getCaption() != null)
      {
        localTextView2.setText(this.listing.getCaption());
        localTextView2.setVisibility(0);
        i = 1;
        if (this.listing.getSynopsis() == null)
          break label655;
        localTextView8.setText(this.listing.getSynopsis());
        localTextView8.setVisibility(0);
        i = 1;
        label289: if (i == 0)
          break label665;
        localLinearLayout3.setVisibility(0);
        label300: if (this.listing.getReserveUrl() == null)
          break label675;
        localLinearLayout1.setVisibility(0);
        localLinearLayout2.setVisibility(0);
        label322: String str1 = this.listing.getAddressLine1();
        String str2 = this.listing.getAddressLine2();
        String str3 = this.listing.getDistanceString();
        if (StringUtils.isNullOrWhiteSpace(str1))
          break label692;
        localTextView3.setText(str1);
        localTextView3.setVisibility(0);
        label370: if (StringUtils.isNullOrWhiteSpace(str2))
          break label702;
        localTextView4.setText(str2);
        localTextView4.setVisibility(0);
        label391: if (StringUtils.isNullOrWhiteSpace(str3))
          break label712;
        localTextView5.setText(str3);
        localTextView5.setVisibility(0);
        label412: localRatingBar.setRating((float)this.listing.getRating());
        if (!this.listing.isSponsored())
          break label722;
        localTextView6.setVisibility(0);
        label441: localTextView7.setVisibility(8);
        1 local1 = new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            Intent localIntent1 = new Intent("com.vlingo.core.internal.dialogmanager.AcceptedText");
            LocalSearchWidget.this.listener.handleIntent(localIntent1, new LocalSearchAcceptedText(LocalSearchWidget.this.lsAdaptor.getCurrentSearchQuery(), AcceptedText.ActionType.MAP.name()));
            String str1 = LocalSearchWidget.DetailsClickListener.this.listing.getFullAddress();
            Intent localIntent2;
            if (ClientConfiguration.isChineseBuild())
            {
              localIntent2 = new Intent("com.autonavi.xmgd.action.SHOWMAP");
              localIntent2.putExtra("target", str1.replace("\n", ""));
            }
            while (true)
            {
              LocalSearchWidget.this.context.startActivity(localIntent2);
              return;
              String str2 = str1.replaceAll(" ", "+");
              String str3 = "geo:0,0?q==" + str2;
              localIntent2 = new Intent("android.intent.action.VIEW");
              localIntent2.setData(Uri.parse(str3));
            }
          }
        };
        localImageButton2.setOnClickListener(local1);
        2 local2 = new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            Intent localIntent1 = new Intent("com.vlingo.core.internal.dialogmanager.AcceptedText");
            LocalSearchWidget.this.listener.handleIntent(localIntent1, new LocalSearchAcceptedText(LocalSearchWidget.this.lsAdaptor.getCurrentSearchQuery(), AcceptedText.ActionType.NAV.name()));
            String str1 = LocalSearchWidget.DetailsClickListener.this.listing.getFullAddress();
            Intent localIntent2;
            if (ClientConfiguration.isChineseBuild())
            {
              localIntent2 = new Intent("com.autonavi.xmgd.action.NAVIGATE");
              localIntent2.putExtra("target", str1.replace("\n", ""));
            }
            while (true)
            {
              LocalSearchWidget.this.context.startActivity(localIntent2);
              return;
              String str2 = str1.replaceAll(" ", "+");
              String str3 = "google.navigation:q=" + str2;
              localIntent2 = new Intent("android.intent.action.VIEW");
              localIntent2.setData(Uri.parse(str3));
            }
          }
        };
        localImageButton3.setOnClickListener(local2);
        3 local3 = new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            Intent localIntent1 = new Intent("com.vlingo.core.internal.dialogmanager.AcceptedText");
            LocalSearchWidget.this.listener.handleIntent(localIntent1, new LocalSearchAcceptedText(LocalSearchWidget.this.lsAdaptor.getCurrentSearchQuery(), AcceptedText.ActionType.RESERVE.name()));
            Intent localIntent2 = new Intent("android.intent.action.VIEW");
            String str = LocalSearchWidget.DetailsClickListener.this.listing.getReserveUrl();
            if ((!StringUtils.isNullOrWhiteSpace(str)) && (!str.toLowerCase(MidasSettings.getCurrentLocale()).startsWith("http://")))
              str = "http://" + str;
            localIntent2.setData(Uri.parse(str));
            LocalSearchWidget.this.context.startActivity(localIntent2);
          }
        };
        localImageButton5.setOnClickListener(local3);
        if ((this.listing.getPhoneNumber() == null) || (!CorePackageInfoProvider.hasDialing()))
          break label732;
        localImageButton1.setImageResource(2130837529);
        localImageButton1.setOnClickListener(new LocalSearchWidget.CallClickListener(LocalSearchWidget.this, this.listing.getPhoneNumber()));
        label545: if ((this.listing.getClickUrl() == null) && (this.listing.getUrl() == null))
          break label759;
        localImageButton4.setImageResource(2130837527);
        5 local5 = new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            Intent localIntent1 = new Intent("com.vlingo.core.internal.dialogmanager.AcceptedText");
            LocalSearchWidget.this.listener.handleIntent(localIntent1, new LocalSearchAcceptedText(LocalSearchWidget.this.lsAdaptor.getCurrentSearchQuery(), AcceptedText.ActionType.WEB.name()));
            String str = LocalSearchWidget.DetailsClickListener.this.listing.getClickUrl();
            if (StringUtils.isNullOrWhiteSpace(str))
              str = LocalSearchWidget.DetailsClickListener.this.listing.getUrl();
            if ((!StringUtils.isNullOrWhiteSpace(str)) && (!str.toLowerCase(MidasSettings.getCurrentLocale()).startsWith("http://")))
              str = "http://" + str;
            Intent localIntent2 = new Intent("android.intent.action.VIEW");
            localIntent2.setData(Uri.parse(str));
            LocalSearchWidget.this.context.startActivity(localIntent2);
          }
        };
        localImageButton4.setOnClickListener(local5);
      }
      while (true)
      {
        this.listingProgressBar.setVisibility(8);
        this.textReviews = ((TextView)localView.findViewById(2131558750));
        this.layoutReviews = ((LinearLayout)localView.findViewById(2131558751));
        this.layoutReviews.removeAllViews();
        this.providerView = ((TextView)localView.findViewById(2131558752));
        return localView;
        localTextView2.setVisibility(8);
        break;
        label655: localTextView8.setVisibility(8);
        break label289;
        label665: localLinearLayout3.setVisibility(8);
        break label300;
        label675: localLinearLayout1.setVisibility(8);
        localLinearLayout2.setVisibility(8);
        break label322;
        label692: localTextView3.setVisibility(8);
        break label370;
        label702: localTextView4.setVisibility(8);
        break label391;
        label712: localTextView5.setVisibility(8);
        break label412;
        label722: localTextView6.setVisibility(8);
        break label441;
        label732: localImageButton1.setImageResource(2130837530);
        4 local4 = new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
          }
        };
        localImageButton1.setOnClickListener(local4);
        break label545;
        label759: localImageButton4.setImageResource(2130837528);
        6 local6 = new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
          }
        };
        localImageButton4.setOnClickListener(local6);
      }
    }

    public void onClick(View paramView)
    {
      LocalSearchWidget.access$002(LocalSearchWidget.this, new LocalSearchWidget.LocalSearchDialog(LocalSearchWidget.this, LocalSearchWidget.this.context));
      LocalSearchWidget.this.lsAdaptor.executeDetailRequest(this.listing, new WidgetResponseReceivedListenerImpl(LocalSearchWidget.this.dialog));
      View localView = getDetailLocalSearchView();
      DialogFlow.getInstance().interruptTurn();
      LocalSearchWidget.this.dialog.requestWindowFeature(1);
      LocalSearchWidget.this.dialog.setContentView(localView);
      LocalSearchWidget.this.dialog.show();
    }

    private class WidgetResponseReceivedListenerImpl
      implements WidgetResponseReceivedListener
    {
      Dialog dialog;

      public WidgetResponseReceivedListenerImpl(Dialog arg2)
      {
        Object localObject;
        this.dialog = localObject;
      }

      public void onRequestFailed()
      {
        if ((LocalSearchWidget.this.context instanceof Activity))
          ((Activity)LocalSearchWidget.this.context).runOnUiThread(new LocalSearchWidget.DetailsClickListener.WidgetResponseReceivedListenerImpl.3(this));
      }

      public void onRequestScheduled()
      {
        if ((LocalSearchWidget.this.context instanceof Activity))
          ((Activity)LocalSearchWidget.this.context).runOnUiThread(new LocalSearchWidget.DetailsClickListener.WidgetResponseReceivedListenerImpl.2(this));
      }

      public void onResponseReceived()
      {
        Vector localVector = LocalSearchWidget.this.lsAdaptor.getLocalSearchDetailsListing();
        if (localVector.size() > 0)
        {
          LocalSearchListing localLocalSearchListing = (LocalSearchListing)localVector.get(0);
          if ((LocalSearchWidget.this.context instanceof Activity))
            ((Activity)LocalSearchWidget.this.context).runOnUiThread(new LocalSearchWidget.DetailsClickListener.WidgetResponseReceivedListenerImpl.1(this, localLocalSearchListing));
        }
      }
    }
  }

  private class ListAdapter extends BaseAdapter
  {
    private ListAdapter()
    {
    }

    public int getCount()
    {
      int i = LocalSearchWidget.this.localSearchListings.size();
      return LocalSearchWidget.this.getLimitedCount(i);
    }

    public Object getItem(int paramInt)
    {
      return LocalSearchWidget.this.localSearchListings.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      LocalSearchWidget.ViewHolder localViewHolder;
      if (paramView == null)
      {
        paramView = LayoutInflater.from(LocalSearchWidget.this.context).inflate(2130903091, paramViewGroup, false);
        localViewHolder = new LocalSearchWidget.ViewHolder(LocalSearchWidget.this);
        localViewHolder.name = ((TextView)paramView.findViewById(2131558716));
        localViewHolder.rate_img = ((RatingBar)paramView.findViewById(2131558718));
        localViewHolder.review_count_num = ((TextView)paramView.findViewById(2131558719));
        localViewHolder.address = ((TextView)paramView.findViewById(2131558720));
        localViewHolder.call = ((ImageButton)paramView.findViewById(2131558721));
        localViewHolder.content = ((RelativeLayout)paramView.findViewById(2131558714));
        localViewHolder.divider = paramView.findViewById(2131558722);
        paramView.setTag(localViewHolder);
        LocalSearchListing localLocalSearchListing = (LocalSearchListing)LocalSearchWidget.this.localSearchListings.get(paramInt);
        localViewHolder.name.setText(localLocalSearchListing.getName());
        double d = localLocalSearchListing.getRating();
        localViewHolder.rate_img.setRating((float)d);
        int i = localLocalSearchListing.getReviewCount();
        localViewHolder.review_count_num.setText("(" + i + ")");
        String str1 = localLocalSearchListing.getFullAddress();
        String str2 = localLocalSearchListing.getDistanceString();
        String[] arrayOfString = str1.split("\n");
        String str3 = arrayOfString[0] + ", " + arrayOfString[1];
        if (!str2.isEmpty())
          str3 = str3 + ", " + str2;
        localViewHolder.address.setText(str3);
        if ((localLocalSearchListing.getPhoneNumber() == null) || (!CorePackageInfoProvider.hasDialing()))
          break label414;
        localViewHolder.call.setVisibility(0);
        localViewHolder.call.setOnClickListener(new LocalSearchWidget.CallClickListener(LocalSearchWidget.this, localLocalSearchListing.getPhoneNumber()));
        label369: localViewHolder.content.setOnClickListener(new LocalSearchWidget.DetailsClickListener(LocalSearchWidget.this, localLocalSearchListing));
        if (paramInt != 0)
          break label442;
        paramView.setBackgroundResource(2130837820);
      }
      while (true)
      {
        return paramView;
        localViewHolder = (LocalSearchWidget.ViewHolder)paramView.getTag();
        break;
        label414: localViewHolder.call.setVisibility(4);
        localViewHolder.call.setOnClickListener(new LocalSearchWidget.ListAdapter.1(this));
        break label369;
        label442: if (paramInt == -1 + getCount())
        {
          localViewHolder.divider.setVisibility(4);
          paramView.setBackgroundResource(2130837817);
          continue;
        }
        paramView.setBackgroundResource(2130837819);
      }
    }
  }

  private class LocalSearchDialog extends Dialog
  {
    public LocalSearchDialog(Context arg2)
    {
      super();
    }

    public void onBackPressed()
    {
      MidasSettings.setBoolean("key_popup_window_opened", false);
      super.onBackPressed();
    }

    protected void onStart()
    {
      MidasSettings.setBoolean("key_popup_window_opened", true);
      super.onStart();
    }

    protected void onStop()
    {
      MidasSettings.setBoolean("key_popup_window_opened", false);
      LocalSearchWidget.access$002(LocalSearchWidget.this, null);
      super.onStop();
    }
  }

  class ViewHolder
  {
    TextView address;
    ImageButton call;
    View content;
    View divider;
    TextView name;
    RatingBar rate_img;
    TextView review_count_num;

    ViewHolder()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.LocalSearchWidget
 * JD-Core Version:    0.6.0
 */