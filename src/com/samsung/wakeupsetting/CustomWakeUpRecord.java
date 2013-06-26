package com.samsung.wakeupsetting;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CustomWakeUpRecord extends RelativeLayout
{
  private final int MAX_SUCCESS_COUNT = 6;
  private Context context;
  private ImageView[] images;

  public CustomWakeUpRecord(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
  }

  public void onFinishInflate()
  {
    this.images = new ImageView[6];
    this.images[0] = ((ImageView)findViewById(2131558572));
    this.images[1] = ((ImageView)findViewById(2131558573));
    this.images[2] = ((ImageView)findViewById(2131558574));
    this.images[3] = ((ImageView)findViewById(2131558575));
    this.images[4] = ((ImageView)findViewById(2131558576));
    this.images[5] = ((ImageView)findViewById(2131558577));
  }

  public void setImageVisible(boolean paramBoolean)
  {
    if (paramBoolean)
      for (int i = 0; i < 6; i++)
        this.images[i].setVisibility(0);
    this.images[4].setVisibility(8);
    this.images[5].setVisibility(8);
  }

  public void setSuccessCount(int paramInt)
  {
    int i = 0;
    if (i < 6)
    {
      if (i < paramInt)
        this.images[i].setBackgroundDrawable(this.context.getResources().getDrawable(2130838179));
      while (true)
      {
        i++;
        break;
        this.images[i].setBackgroundDrawable(this.context.getResources().getDrawable(2130838178));
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wakeupsetting.CustomWakeUpRecord
 * JD-Core Version:    0.6.0
 */