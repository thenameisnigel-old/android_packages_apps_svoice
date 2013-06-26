package com.vlingo.midas.settings;

import android.content.Context;
import android.graphics.Bitmap;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImagePreference extends Preference
{
  private Bitmap bitmap = null;
  private ImageView imageView;
  private int visibility = 0;

  public ImagePreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setWidgetLayoutResource(2130903116);
  }

  private void updateUI()
  {
    if (this.imageView != null)
    {
      this.imageView.setVisibility(this.visibility);
      this.imageView.setImageBitmap(this.bitmap);
    }
  }

  @Deprecated
  public ImageView getImageView()
  {
    return this.imageView;
  }

  protected View onCreateView(ViewGroup paramViewGroup)
  {
    View localView = super.onCreateView(paramViewGroup);
    this.imageView = ((ImageView)localView.findViewById(2131558821));
    updateUI();
    return localView;
  }

  public void setImageViewBitmap(Bitmap paramBitmap)
  {
    this.bitmap = paramBitmap;
    updateUI();
  }

  public void setImageViewVisibility(int paramInt)
  {
    this.visibility = paramInt;
    updateUI();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.ImagePreference
 * JD-Core Version:    0.6.0
 */