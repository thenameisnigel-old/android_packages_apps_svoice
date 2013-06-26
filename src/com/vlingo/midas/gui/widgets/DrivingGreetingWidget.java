package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.vlingo.midas.util.DrivingWidgetInterface;

public class DrivingGreetingWidget extends RelativeLayout
  implements DrivingWidgetInterface
{
  private TextView iv1;
  private TextView iv1_h;
  private ImageView iv2;
  private ImageView iv2_h;
  private final Context mContext;

  public DrivingGreetingWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  public int getDecreasedHeight()
  {
    return 0;
  }

  public int getProperHeight()
  {
    return 0;
  }

  public boolean isDecreasedSize()
  {
    return false;
  }

  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    switchGreetingScreen(paramConfiguration.orientation);
    super.onConfigurationChanged(paramConfiguration);
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.iv1 = ((TextView)findViewById(2131558920));
    this.iv2 = ((ImageView)findViewById(2131558921));
    this.iv1_h = ((TextView)findViewById(2131558923));
    this.iv2_h = ((ImageView)findViewById(2131558922));
    switchGreetingScreen(this.mContext.getResources().getConfiguration().orientation);
  }

  public void setAlpha(int paramInt)
  {
    if ((this.iv2 != null) && (this.iv2_h != null))
    {
      this.iv2.getDrawable().setAlpha(paramInt);
      this.iv2_h.getDrawable().setAlpha(paramInt);
    }
  }

  public int setNightMode(boolean paramBoolean)
  {
    return 0;
  }

  public void setText(String paramString)
  {
    if (paramString != null)
    {
      this.iv1.setText(paramString);
      this.iv1_h.setText(paramString);
    }
  }

  public int setWidgetToDecreasedSize(boolean paramBoolean)
  {
    return 0;
  }

  public void startAnimationTranslate(View paramView)
  {
  }

  public void switchGreetingScreen(int paramInt)
  {
    if (!isInEditMode())
    {
      if (paramInt != 1)
        break label47;
      this.iv1.setVisibility(0);
      this.iv2.setVisibility(0);
      this.iv1_h.setVisibility(8);
      this.iv2_h.setVisibility(8);
    }
    while (true)
    {
      return;
      label47: this.iv1_h.setVisibility(0);
      this.iv2_h.setVisibility(0);
      this.iv1.setVisibility(8);
      this.iv2.setVisibility(8);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingGreetingWidget
 * JD-Core Version:    0.6.0
 */