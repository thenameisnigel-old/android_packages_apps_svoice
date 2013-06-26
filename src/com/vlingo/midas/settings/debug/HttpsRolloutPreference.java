package com.vlingo.midas.settings.debug;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class HttpsRolloutPreference extends DialogPreference
  implements SeekBar.OnSeekBarChangeListener
{
  private static final String androidns = "http://schemas.android.com/apk/res/android";
  private int mDefaultValue;
  private SeekBar mSeekBar;
  private TextView mTextView;

  public HttpsRolloutPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mDefaultValue = paramAttributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "defaultValue", 0);
    setDialogLayoutResource(2130903080);
  }

  protected void onBindDialogView(View paramView)
  {
    this.mSeekBar = ((SeekBar)paramView.findViewById(2131558671));
    this.mSeekBar.setOnSeekBarChangeListener(this);
    this.mTextView = ((TextView)paramView.findViewById(2131558672));
    int i = getSharedPreferences().getInt("https.rollout_percentage", this.mDefaultValue);
    this.mSeekBar.setProgress(i);
    this.mTextView.setText(String.valueOf(i));
    super.onBindDialogView(paramView);
  }

  protected void onDialogClosed(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      SharedPreferences.Editor localEditor = getEditor();
      localEditor.putInt(getKey(), this.mSeekBar.getProgress());
      localEditor.commit();
    }
    super.onDialogClosed(paramBoolean);
  }

  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
    this.mTextView.setText(String.valueOf(paramInt));
  }

  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
  }

  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.HttpsRolloutPreference
 * JD-Core Version:    0.6.0
 */