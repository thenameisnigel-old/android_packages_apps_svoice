package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.naver.NaverAdaptor;
import com.vlingo.midas.naver.NaverMovieItem;
import com.vlingo.midas.naver.NaverMovieItem.GradeInt;
import com.vlingo.midas.naver.NaverXMLParser;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;

public class NaverMovieChoiceWidget extends BargeInWidget<NaverAdaptor>
  implements AdapterView.OnItemClickListener
{
  private WidgetActionListener mActionListener;
  private final Context mContext;
  private int mHour;
  private int mMin;
  private ListView mMovieList;
  private List<Hashtable<String, String>> mMovies;
  private int mTempHour;

  public NaverMovieChoiceWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  protected static void setListViewHeightBasedOnChildren(ListView paramListView)
  {
    ViewGroup.LayoutParams localLayoutParams = paramListView.getLayoutParams();
    localLayoutParams.height = (int)(paramListView.getCount() * (paramListView.getContext().getResources().getDimension(2131427358) + 2 * paramListView.getCount()));
    paramListView.setLayoutParams(localLayoutParams);
    paramListView.requestLayout();
  }

  public void initialize(NaverAdaptor paramNaverAdaptor, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.mActionListener = paramWidgetActionListener;
    TextView localTextView;
    NaverXMLParser localNaverXMLParser;
    if (paramNaverAdaptor != null)
    {
      this.mMovieList.setAdapter(new MovieAdapter(getContext(), paramNaverAdaptor));
      this.mMovies = paramNaverAdaptor.getNaverXML().itemList;
      new Thread(new Runnable()
      {
        public void run()
        {
          int i = 0;
          while (true)
            if ((i < NaverMovieChoiceWidget.this.mMovies.size()) && (i < NaverMovieChoiceWidget.this.mMovieList.getCount()))
            {
              Object localObject = null;
              Hashtable localHashtable = (Hashtable)NaverMovieChoiceWidget.this.mMovies.get(i);
              try
              {
                Bitmap localBitmap = BitmapFactory.decodeStream(new URL((String)localHashtable.get("poster")).openStream());
                localObject = localBitmap;
                if (localObject != null)
                {
                  localBitmapDrawable = new BitmapDrawable(localObject);
                  int j = i;
                  new Handler(NaverMovieChoiceWidget.this.getContext().getMainLooper()).postDelayed(new NaverMovieChoiceWidget.1.1(this, j, localBitmapDrawable), 500L);
                  i++;
                }
              }
              catch (Exception localException)
              {
                while (true)
                {
                  localException.printStackTrace();
                  continue;
                  BitmapDrawable localBitmapDrawable = null;
                }
              }
            }
        }
      }).start();
      localTextView = (TextView)findViewById(2131559081);
      localNaverXMLParser = paramNaverAdaptor.getNaverXML();
      if ((localNaverXMLParser.svoiceQuery == null) || (localNaverXMLParser.svoiceQuery.length() <= 0))
        break label139;
      localTextView.setText(localNaverXMLParser.svoiceQuery);
    }
    while (true)
    {
      ((Button)findViewById(2131559066)).setOnClickListener(new MoreClickListener((String)localNaverXMLParser.propertyList.get("moreUrl")));
      return;
      label139: if ((localNaverXMLParser.query != null) && (localNaverXMLParser.query.length() > 0))
      {
        localTextView.setText(localNaverXMLParser.query);
        continue;
      }
      localTextView.setText("--");
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
    this.mMovieList = ((ListView)findViewById(2131558893));
    this.mMovieList.setDivider(null);
    this.mMovieList.setOnItemClickListener(this);
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    setListViewHeightBasedOnChildren(this.mMovieList);
    super.onMeasure(paramInt1, paramInt2);
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
      if (this.url != null)
      {
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setData(Uri.parse(this.url));
        NaverMovieChoiceWidget.this.mContext.startActivity(localIntent);
      }
    }
  }

  private class MovieAdapter extends BaseAdapter
  {
    private final NaverAdaptor mAllMovies;
    private final LayoutInflater mInflater;
    private final List<Hashtable<String, String>> mMovies;

    public MovieAdapter(Context paramNaverAdaptor, NaverAdaptor arg3)
    {
      this.mInflater = LayoutInflater.from(paramNaverAdaptor);
      Object localObject;
      this.mMovies = localObject.getNaverXML().itemList;
      this.mAllMovies = localObject;
    }

    public int getCount()
    {
      int i = this.mMovies.size();
      return NaverMovieChoiceWidget.this.getLimitedCount(i);
    }

    public Object getItem(int paramInt)
    {
      return this.mMovies.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      (NaverMovieChoiceWidget.this.mContext.getResources().getDisplayMetrics().densityDpi / 160);
      NaverMovieChoiceWidget.MovieHolder localMovieHolder;
      ImageView localImageView;
      if (paramView == null)
      {
        paramView = this.mInflater.inflate(2130903096, null);
        localMovieHolder = new NaverMovieChoiceWidget.MovieHolder(NaverMovieChoiceWidget.this);
        localMovieHolder.mMovieTitle = ((TextView)paramView.findViewById(2131558768));
        localMovieHolder.mMovieGrade = ((ImageView)paramView.findViewById(2131558767));
        localMovieHolder.mMovieDirector = ((TextView)paramView.findViewById(2131558769));
        localMovieHolder.mMovieActor = ((TextView)paramView.findViewById(2131558770));
        localMovieHolder.mMovieRatingNumber = ((TextView)paramView.findViewById(2131558771));
        localMovieHolder.mMovieRating = ((RatingBar)paramView.findViewById(2131558726));
        localMovieHolder.mMoviePoster = ((ImageView)paramView.findViewById(2131558763));
        localMovieHolder.mMovieDivider = paramView.findViewById(2131558773);
        localMovieHolder.mMovieRank = ((TextView)paramView.findViewById(2131558766));
        localMovieHolder.mMovieOpenDate = ((TextView)paramView.findViewById(2131558772));
        paramView.setTag(localMovieHolder);
        paramView.findViewById(2131558765).setVisibility(8);
        paramView.findViewById(2131558766).setVisibility(8);
        NaverMovieItem localNaverMovieItem = new NaverMovieItem(NaverMovieChoiceWidget.this.getContext(), (Hashtable)this.mMovies.get(paramInt));
        localMovieHolder.mMovieTitle.setText(localNaverMovieItem.getTitle());
        localMovieHolder.mMovieRating.setRating(localNaverMovieItem.getRating().floatValue() / 10.0F * localMovieHolder.mMovieRating.getNumStars());
        localMovieHolder.mMovieRatingNumber.setText(String.valueOf(localNaverMovieItem.getRating()));
        localMovieHolder.mMovieRank.setText(String.valueOf(paramInt + 1));
        localMovieHolder.mMovieActor.setText(localNaverMovieItem.getActors());
        localMovieHolder.mMovieDirector.setText(localNaverMovieItem.getDirectors());
        localMovieHolder.mMovieOpenDate.setText(localNaverMovieItem.getOpenDate());
        localMovieHolder.mServicelink = localNaverMovieItem.getServiceUrl();
        localImageView = (ImageView)paramView.findViewById(2131558767);
        NaverMovieItem.GradeInt localGradeInt = localNaverMovieItem.getGradeToInt(localNaverMovieItem.getGrade());
        switch (NaverMovieChoiceWidget.2.$SwitchMap$com$vlingo$midas$naver$NaverMovieItem$GradeInt[localGradeInt.ordinal()])
        {
        default:
          localImageView.setVisibility(8);
          label435: if (paramInt != 0)
            break;
          paramView.setBackgroundResource(2130837820);
        case 1:
        case 2:
        case 3:
        case 4:
        }
      }
      while (true)
      {
        paramView.setOnClickListener(new NaverMovieChoiceWidget.MovieAdapter.1(this));
        return paramView;
        localMovieHolder = (NaverMovieChoiceWidget.MovieHolder)paramView.getTag();
        break;
        localImageView.setImageResource(2130837866);
        break label435;
        localImageView.setImageResource(2130837867);
        break label435;
        localImageView.setImageResource(2130837868);
        break label435;
        localImageView.setImageResource(2130837869);
        break label435;
        if (paramInt >= -1 + getCount())
        {
          paramView.setBackgroundResource(2130837817);
          localMovieHolder.mMovieDivider.setVisibility(4);
          continue;
        }
        paramView.setBackgroundResource(2130837819);
      }
    }
  }

  class MovieHolder
  {
    TextView mMovieActor;
    TextView mMovieDirector;
    View mMovieDivider;
    ImageView mMovieGrade;
    TextView mMovieOpenDate;
    ImageView mMoviePoster;
    TextView mMovieRank;
    RatingBar mMovieRating;
    TextView mMovieRatingNumber;
    TextView mMovieTitle;
    public String mServicelink;

    MovieHolder()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.NaverMovieChoiceWidget
 * JD-Core Version:    0.6.0
 */