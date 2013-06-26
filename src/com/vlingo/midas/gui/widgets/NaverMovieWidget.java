package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
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
import com.vlingo.midas.naver.NaverAdaptor;
import com.vlingo.midas.naver.NaverMovieItem;
import com.vlingo.midas.naver.NaverXMLParser;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;

public class NaverMovieWidget extends BargeInWidget<NaverAdaptor>
  implements View.OnClickListener
{
  private Button btnBook;
  private Button btnBooking;
  private Button btnDetaillink;
  private Button btnMovieInfo;
  private Button btnSameNameMovies;
  NaverMovieItem item;
  private TextView mActors;
  private final Context mContext;
  private TextView mDirectors;
  private TextView mGenre;
  private TextView mGrade;
  private NaverMovieWidgetHandler mHandler = new NaverMovieWidgetHandler(this);
  ImageView mMoviePoster;
  TextView mMovieTitle;
  private TextView mOpenDate;
  private Bitmap mPosterImage;
  private TextView mRatings;
  RatingBar mRatingsView;
  private TextView mRunningTime;
  private VVSActionHandlerListener mhandler;
  private NaverAdaptor wAdaptor;

  public NaverMovieWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  public void initialize(NaverAdaptor paramNaverAdaptor, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.wAdaptor = paramNaverAdaptor;
    this.mhandler = this.wAdaptor.getVVSActionHandlerListener();
    NaverXMLParser localNaverXMLParser = this.wAdaptor.getNaverXML();
    this.item = new NaverMovieItem(getContext(), (Hashtable)localNaverXMLParser.itemList.get(0));
    this.mMovieTitle.setText(this.item.getTitle());
    if (findViewById(2131559068) != null)
    {
      if (!this.item.isOpenSoon())
        break label394;
      ((TextView)findViewById(2131559068)).setText(this.mContext.getString(2131362625));
    }
    label275: label533: label545: label555: 
    while (true)
    {
      this.mRatingsView.setRating(this.item.getRating().floatValue() / 10.0F * this.mRatingsView.getNumStars());
      this.mRatings.setText(String.valueOf(this.item.getRating()));
      if (this.item.getActors().equals("-"))
      {
        findViewById(2131559074).setVisibility(8);
        label180: if (!this.item.getDirectors().equals("-"))
          break label436;
        findViewById(2131559073).setVisibility(8);
        label206: if (this.item.getOpenDate().length() >= 4)
          break label453;
        this.mOpenDate.setVisibility(8);
        findViewById(2131559072).setVisibility(8);
        label240: if (!this.item.getGrade().equals("-"))
          break label470;
        this.mGrade.setVisibility(8);
        findViewById(2131559075).setVisibility(8);
        if (!this.item.getGenre().equals("-"))
          break label487;
        this.mGenre.setVisibility(8);
        findViewById(2131559070).setVisibility(8);
        label310: if (!this.item.getRunningTime().equals("-"))
          break label504;
        this.mRunningTime.setVisibility(8);
        findViewById(2131559070).setVisibility(8);
        label345: new Thread(new Runnable()
        {
          public void run()
          {
            String str = NaverMovieWidget.this.item.getPosterUrl();
            try
            {
              URL localURL = new URL(str);
              NaverMovieWidget.access$002(NaverMovieWidget.this, BitmapFactory.decodeStream(localURL.openStream()));
              NaverMovieWidget.this.mHandler.sendEmptyMessage(0);
              return;
            }
            catch (Exception localException)
            {
              while (true)
              {
                NaverMovieWidget.this.mHandler.sendEmptyMessage(0);
                localException.printStackTrace();
              }
            }
          }
        }).start();
        if (this.item.getDetailUrl() == null)
          break label521;
        label373: if (this.item.getBookingUrl() == null)
          break label533;
        label383: if (this.item.getSameNameUrl() == null)
          break label545;
      }
      while (true)
      {
        return;
        if (this.item.isNowShowing() == true)
          break label555;
        findViewById(2131559068).setVisibility(8);
        break;
        this.mActors.setText(this.item.getActors());
        break label180;
        label436: this.mDirectors.setText(this.item.getDirectors());
        break label206;
        label453: this.mOpenDate.setText(this.item.getOpenDate());
        break label240;
        label470: this.mGrade.setText(this.item.getGrade());
        break label275;
        label487: this.mGenre.setText(this.item.getGenre());
        break label310;
        label504: this.mRunningTime.setText(this.item.getRunningTime());
        break label345;
        this.btnDetaillink.setVisibility(8);
        break label373;
        this.btnBooking.setVisibility(8);
        break label383;
        this.btnSameNameMovies.setVisibility(8);
      }
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
    if ((paramView == this.btnDetaillink) && (this.item.getDetailUrl() != null))
      localIntent.setData(Uri.parse(this.item.getDetailUrl()));
    while (true)
    {
      if (localIntent.getData() != null)
        this.mContext.startActivity(localIntent);
      do
      {
        return;
        if ((paramView != this.btnBooking) || (this.item.getBookingUrl() == null))
          continue;
        localIntent.setData(Uri.parse(this.item.getBookingUrl()));
        break;
      }
      while ((paramView != this.btnSameNameMovies) || (this.item.getSameNameUrl() == null));
      localIntent.setData(Uri.parse(this.item.getSameNameUrl()));
    }
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.mMovieTitle = ((TextView)findViewById(2131558768));
    this.mMoviePoster = ((ImageView)findViewById(2131558763));
    this.mRatingsView = ((RatingBar)findViewById(2131558726));
    this.mRatings = ((TextView)findViewById(2131558771));
    this.mGenre = ((TextView)findViewById(2131559069));
    this.mOpenDate = ((TextView)findViewById(2131558772));
    this.mActors = ((TextView)findViewById(2131558770));
    this.mDirectors = ((TextView)findViewById(2131558769));
    this.mRunningTime = ((TextView)findViewById(2131559071));
    this.mGrade = ((TextView)findViewById(2131559076));
    this.btnDetaillink = ((Button)findViewById(2131559077));
    this.btnDetaillink.setOnClickListener(this);
    this.btnBooking = ((Button)findViewById(2131559078));
    this.btnBooking.setOnClickListener(this);
    this.btnSameNameMovies = ((Button)findViewById(2131559079));
    this.btnSameNameMovies.setOnClickListener(this);
  }

  private static class NaverMovieWidgetHandler extends Handler
  {
    private final WeakReference<NaverMovieWidget> outer;

    NaverMovieWidgetHandler(NaverMovieWidget paramNaverMovieWidget)
    {
      this.outer = new WeakReference(paramNaverMovieWidget);
    }

    public void handleMessage(Message paramMessage)
    {
      NaverMovieWidget localNaverMovieWidget = (NaverMovieWidget)this.outer.get();
      if (localNaverMovieWidget != null)
      {
        if (localNaverMovieWidget.mPosterImage != null)
          localNaverMovieWidget.mMoviePoster.setImageBitmap(localNaverMovieWidget.mPosterImage);
        localNaverMovieWidget.mMoviePoster.setVisibility(0);
        localNaverMovieWidget.findViewById(2131558764).setVisibility(8);
        localNaverMovieWidget.mMoviePoster.invalidate();
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.NaverMovieWidget
 * JD-Core Version:    0.6.0
 */