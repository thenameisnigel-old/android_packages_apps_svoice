package com.vlingo.midas.settings;

import android.content.Context;
import android.preference.CheckBoxPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class TwoLineCheckBoxPreference extends CheckBoxPreference
{
  public TwoLineCheckBoxPreference(Context paramContext)
  {
    super(paramContext);
  }

  public TwoLineCheckBoxPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public TwoLineCheckBoxPreference(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void onBindView(View paramView)
  {
    super.onBindView(paramView);
    View localView = paramView.findViewById(16908310);
    if ((localView != null) && ((localView instanceof TextView)))
    {
      ((TextView)localView).setSingleLine(false);
      ((TextView)localView).setMaxLines(2);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.TwoLineCheckBoxPreference
 * JD-Core Version:    0.6.0
 */