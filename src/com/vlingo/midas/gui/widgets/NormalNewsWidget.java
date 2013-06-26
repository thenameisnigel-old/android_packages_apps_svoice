package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.news.NewsItem;
import java.util.Calendar;

public class NormalNewsWidget extends BargeInWidget<NewsItem>
{
  private String LOG_TAG = "DrivingNewsWidget";
  private TextView detail;
  private TextView headline;
  private Context mContext;
  private NewsItem mNewsItem;
  private TextView updateDate;

  public NormalNewsWidget(Context paramContext, AttributeSet paramAttributeSet)
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
      this.headline.setText(paramNewsItem.getTitle());
      this.detail.setText(paramNewsItem.getText());
      this.updateDate.setText(makeCorrectDateString(paramNewsItem));
    }
  }

  public void initialize(NewsItem paramNewsItem, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.mNewsItem = paramNewsItem;
    fillupMessage(this.mNewsItem);
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
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.headline = ((TextView)findViewById(2131559115));
    this.detail = ((TextView)findViewById(2131559119));
    this.updateDate = ((TextView)findViewById(2131559116));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.NormalNewsWidget
 * JD-Core Version:    0.6.0
 */