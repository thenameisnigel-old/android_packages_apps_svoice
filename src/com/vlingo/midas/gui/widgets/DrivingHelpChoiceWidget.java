package com.vlingo.midas.gui.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.gui.Widget;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.DrivingWidgetInterface;

@SuppressLint({"ParserError", "ParserError"})
public class DrivingHelpChoiceWidget extends Widget<Object>
  implements DrivingWidgetInterface
{
  private static boolean MultiWindowMode = false;
  private TextView callRow;
  private View line1;
  private View line2;
  private View line3;
  private View line4;
  private View line5;
  private View line6;
  private View line7;
  private TextView memoRow;
  private TextView messRow;
  private TextView musicRow;
  private TextView naviRow;
  private TextView newsRow;
  private TextView schedRow;
  private TableLayout table;
  private LinearLayout tableBackground;
  private TextView weatherRow;

  public DrivingHelpChoiceWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public int getDecreasedHeight()
  {
    if (getResources().getConfiguration().orientation == 1);
    for (int i = 800; ; i = 900)
      return i;
  }

  public int getProperHeight()
  {
    return 0;
  }

  public void initialize(Object paramObject, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    MultiWindowMode = MidasSettings.isMultiwindowedMode();
    this.callRow = ((TextView)findViewById(2131559016));
    this.naviRow = ((TextView)findViewById(2131559018));
    this.messRow = ((TextView)findViewById(2131559020));
    this.musicRow = ((TextView)findViewById(2131559022));
    this.schedRow = ((TextView)findViewById(2131559024));
    this.memoRow = ((TextView)findViewById(2131559026));
    this.newsRow = ((TextView)findViewById(2131559028));
    this.weatherRow = ((TextView)findViewById(2131559030));
    this.line1 = findViewById(2131559017);
    this.line2 = findViewById(2131559019);
    this.line3 = findViewById(2131559021);
    this.line4 = findViewById(2131559023);
    this.line5 = findViewById(2131559025);
    this.line6 = findViewById(2131559027);
    this.line7 = findViewById(2131559029);
    this.table = ((TableLayout)findViewById(2131559015));
    this.tableBackground = ((LinearLayout)findViewById(2131559014));
    showAll();
    if (MidasSettings.isNightMode())
    {
      this.tableBackground.setBackgroundResource(2130837882);
      this.callRow.setTextColor(-2039584);
      this.naviRow.setTextColor(-2039584);
      this.messRow.setTextColor(-2039584);
      this.musicRow.setTextColor(-2039584);
      this.schedRow.setTextColor(-2039584);
      this.memoRow.setTextColor(-2039584);
      this.newsRow.setTextColor(-2039584);
      this.weatherRow.setTextColor(-2039584);
      this.line1.setBackgroundColor(-16777216);
      this.line2.setBackgroundColor(-16777216);
      this.line3.setBackgroundColor(-16777216);
      this.line4.setBackgroundColor(-16777216);
      this.line5.setBackgroundColor(-16777216);
      this.line6.setBackgroundColor(-16777216);
      this.line7.setBackgroundColor(-16777216);
    }
    while (true)
    {
      onConfigurationChanged(getResources().getConfiguration());
      return;
      this.tableBackground.setBackgroundResource(2130837786);
      this.callRow.setTextColor(-16777216);
      this.naviRow.setTextColor(-16777216);
      this.messRow.setTextColor(-16777216);
      this.musicRow.setTextColor(-16777216);
      this.schedRow.setTextColor(-16777216);
      this.memoRow.setTextColor(-16777216);
      this.newsRow.setTextColor(-16777216);
      this.weatherRow.setTextColor(-16777216);
      this.line1.setBackgroundColor(-5262925);
      this.line2.setBackgroundColor(-5262925);
      this.line3.setBackgroundColor(-5262925);
      this.line4.setBackgroundColor(-5262925);
      this.line5.setBackgroundColor(-5262925);
      this.line6.setBackgroundColor(-5262925);
      this.line7.setBackgroundColor(-5262925);
    }
  }

  public boolean isDecreasedSize()
  {
    return false;
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
    if (paramConfiguration != null)
      super.onConfigurationChanged(paramConfiguration);
    if (paramConfiguration.orientation == 2)
      showRandomList(3);
    while (true)
    {
      return;
      if (paramConfiguration.orientation == 1)
      {
        if (MultiWindowMode)
        {
          showRandomList(5);
          continue;
        }
        showAll();
        continue;
      }
    }
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
  }

  public int setNightMode(boolean paramBoolean)
  {
    return 0;
  }

  public int setWidgetToDecreasedSize(boolean paramBoolean)
  {
    return 0;
  }

  protected void showAll()
  {
    int i = 0;
    if (i <= 14)
    {
      if ((i == 11) || (i == 12))
        this.table.getChildAt(i).setVisibility(0);
      while (true)
      {
        i++;
        break;
        this.table.getChildAt(i).setVisibility(0);
      }
    }
  }

  protected void showRandomList(int paramInt)
  {
    showAll();
    int i = 0;
    while (i < paramInt)
    {
      int m = (int)(15.0D * Math.random());
      if ((m % 2 != 0) || (this.table.getChildAt(m).getVisibility() == 8))
        continue;
      this.table.getChildAt(m).setVisibility(8);
      i++;
      if (m != 0)
      {
        this.table.getChildAt(m - 1).setVisibility(8);
        continue;
      }
      this.table.getChildAt(m + 1).setVisibility(8);
    }
    int j = 0;
    if ((j >= 15) || (this.table.getChildAt(j).getVisibility() != 8));
    for (int k = 1; ; k += 2)
    {
      if ((k < 15) && (this.table.getChildAt(k).getVisibility() == 8))
        continue;
      if (k < j)
        this.table.getChildAt(k).setVisibility(8);
      return;
      j += 2;
      break;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingHelpChoiceWidget
 * JD-Core Version:    0.6.0
 */