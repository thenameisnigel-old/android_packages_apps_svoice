package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.news.NewsItem;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.DrivingWidgetInterface;
import java.util.Calendar;

public class DrivingNewsWidget extends BargeInWidget<NewsItem>
  implements DrivingWidgetInterface
{
  private TextView head1;
  private TextView headline;
  boolean isNightMode;
  private Context mContext;
  private boolean mMinimized = false;
  private NewsItem mNewsItem;
  private TextView updateDate1;

  public DrivingNewsWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    setClickable(false);
  }

  private String makeCorrectDateString(NewsItem paramNewsItem)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramNewsItem.getTimeCreated());
    if (Calendar.getInstance().get(5) == localCalendar.get(5));
    for (String str = getResources().getString(2131362783) + " " + DateFormat.format("h:mm aa", localCalendar); ; str = getResources().getString(2131362783) + " " + DateFormat.format("EEE. dd LLL", localCalendar))
      return str;
  }

  public void fillupMessage(NewsItem paramNewsItem)
  {
    if (paramNewsItem != null)
    {
      this.head1.setText(paramNewsItem.getTitle());
      this.updateDate1.setText(makeCorrectDateString(paramNewsItem));
    }
  }

  public int getDecreasedHeight()
  {
    if (getResources().getConfiguration().orientation == 2);
    for (int i = getResources().getDimensionPixelSize(2131427352); ; i = 327)
    {
      return i;
      measure(0, 0);
      getMeasuredHeight();
    }
  }

  public int getProperHeight()
  {
    return 0;
  }

  public void initView()
  {
    if (MidasSettings.isNightMode())
    {
      findViewById(2131558938).setBackgroundResource(2130837875);
      findViewById(2131558941).setVisibility(0);
      findViewById(2131558944).setVisibility(0);
      this.headline.setTextColor(-2039584);
      this.head1.setTextColor(-2039584);
      this.updateDate1.setTextColor(-2039584);
    }
    while (true)
    {
      return;
      findViewById(2131558938).setBackgroundResource(2130837588);
      findViewById(2131558941).setVisibility(0);
      findViewById(2131558944).setVisibility(0);
      this.headline.setTextColor(-16777216);
      this.head1.setTextColor(-16777216);
      this.updateDate1.setTextColor(-16777216);
    }
  }

  public void initViewMini()
  {
    if (MidasSettings.isNightMode())
      if (getResources().getConfiguration().orientation == 1)
      {
        findViewById(2131558938).setBackgroundResource(2130837874);
        this.headline.setTextColor(-2039584);
        findViewById(2131558941).setVisibility(8);
        findViewById(2131558944).setVisibility(8);
      }
    while (true)
    {
      return;
      if (getResources().getConfiguration().orientation == 2)
      {
        findViewById(2131558938).setBackgroundResource(2130837875);
        findViewById(2131558941).setVisibility(0);
        findViewById(2131558944).setVisibility(0);
        this.headline.setTextColor(-2039584);
        this.head1.setTextColor(-2039584);
        this.updateDate1.setTextColor(-2039584);
        continue;
        if (getResources().getConfiguration().orientation == 1)
        {
          findViewById(2131558938).setBackgroundResource(2130837587);
          findViewById(2131558941).setVisibility(8);
          findViewById(2131558944).setVisibility(8);
          this.headline.setTextColor(-16777216);
          this.head1.setTextColor(-16777216);
          continue;
        }
        if (getResources().getConfiguration().orientation != 2)
          continue;
        findViewById(2131558938).setBackgroundResource(2130837588);
        findViewById(2131558941).setVisibility(0);
        findViewById(2131558944).setVisibility(0);
        this.headline.setTextColor(-16777216);
        this.head1.setTextColor(-16777216);
        this.updateDate1.setTextColor(-16777216);
        continue;
      }
    }
  }

  public void initialize(NewsItem paramNewsItem, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.mNewsItem = paramNewsItem;
    fillupMessage(this.mNewsItem);
    initView();
  }

  public boolean isDecreasedSize()
  {
    return this.mMinimized;
  }

  public boolean isTranslated()
  {
    return false;
  }

  public boolean isWidgetReplaceable()
  {
    return false;
  }

  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (MidasSettings.isMultiwindowedMode())
      initViewMini();
    while (true)
    {
      return;
      initView();
    }
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.headline = ((TextView)findViewById(2131558940));
    this.head1 = ((TextView)findViewById(2131558942));
    this.updateDate1 = ((TextView)findViewById(2131558943));
  }

  public void setMinimize(boolean paramBoolean)
  {
    if (paramBoolean)
      initViewMini();
    while (true)
    {
      return;
      initView();
    }
  }

  public int setNightMode(boolean paramBoolean)
  {
    return 0;
  }

  public int setWidgetToDecreasedSize(boolean paramBoolean)
  {
    this.mMinimized = paramBoolean;
    setMinimize(paramBoolean);
    return 0;
  }

  public void startAnimationTranslate(View paramView)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingNewsWidget
 * JD-Core Version:    0.6.0
 */