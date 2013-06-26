package com.vlingo.midas.settings.debug;

import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.widget.EditText;

public class EditTextPreferenceBuilder extends PreferenceBuilder<EditTextPreference>
{
  private static PreferenceUpdateListener showAsSummaryAction = new EditTextPreferenceBuilder.1();

  public EditTextPreferenceBuilder()
  {
    setShowAsSummary(showAsSummaryAction);
  }

  public EditTextPreferenceBuilder(String paramString)
  {
    super(paramString);
    setShowAsSummary(showAsSummaryAction);
  }

  public EditTextPreference register(PreferenceActivity paramPreferenceActivity)
  {
    EditTextPreference localEditTextPreference = (EditTextPreference)attach(paramPreferenceActivity);
    if (hasValue())
      localEditTextPreference.setText((String)getValue());
    if (localEditTextPreference.getText() == null);
    for (String str = (String)getDefault(); ; str = localEditTextPreference.getText())
    {
      localEditTextPreference.getEditText().setText(str);
      if (isShowAsSummary())
        localEditTextPreference.setSummary(str);
      return localEditTextPreference;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.EditTextPreferenceBuilder
 * JD-Core Version:    0.6.0
 */