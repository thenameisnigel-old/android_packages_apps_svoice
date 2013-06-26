package com.vlingo.midas.iux;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;

public class UsingVlingoScreen extends IUXBaseActivity
{
  public static final String EXTRA_IS_IUX = "wycs.is.iux";

  void applyHtml(int paramInt)
  {
    TextView localTextView = (TextView)findViewById(paramInt);
    localTextView.setText(Html.fromHtml(localTextView.getText().toString()));
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    UserLoggingEngine.getInstance().helpPageViewed("setup-usingvlingo");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.iux.UsingVlingoScreen
 * JD-Core Version:    0.6.0
 */