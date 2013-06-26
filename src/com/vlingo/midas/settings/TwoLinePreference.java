package com.vlingo.midas.settings;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class TwoLinePreference extends Preference
{
  public TwoLinePreference(Context paramContext)
  {
    super(paramContext);
  }

  public TwoLinePreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public TwoLinePreference(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
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
 * Qualified Name:     com.vlingo.midas.settings.TwoLinePreference
 * JD-Core Version:    0.6.0
 */