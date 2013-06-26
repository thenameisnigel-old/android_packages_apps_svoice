package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.vlingo.midas.settings.MidasSettings;

public class DrivingErrorWidget extends RelativeLayout
{
  public DrivingErrorWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void setOrientationOfWidget(Configuration paramConfiguration)
  {
    View localView = findViewById(2131558917);
    TextView localTextView = (TextView)findViewById(2131558919);
    if (localView != null)
    {
      if (!MidasSettings.isNightMode())
        break label57;
      if (paramConfiguration.orientation != 2)
        break label48;
      localView.setBackgroundResource(2130837879);
      localTextView.setTextColor(10526880);
    }
    while (true)
    {
      return;
      label48: localView.setBackgroundResource(2130837878);
      continue;
      label57: if (paramConfiguration.orientation == 2)
      {
        localView.setBackgroundResource(2130837627);
        continue;
      }
      localView.setBackgroundResource(2130837626);
    }
  }

  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    setOrientationOfWidget(paramConfiguration);
    super.onConfigurationChanged(paramConfiguration);
  }

  protected void onFinishInflate()
  {
    setOrientationOfWidget(getResources().getConfiguration());
    super.onFinishInflate();
  }

  public int setNightMode(boolean paramBoolean)
  {
    return 0;
  }

  public void setText(String paramString)
  {
    ((TextView)findViewById(2131558919)).setText(paramString);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingErrorWidget
 * JD-Core Version:    0.6.0
 */