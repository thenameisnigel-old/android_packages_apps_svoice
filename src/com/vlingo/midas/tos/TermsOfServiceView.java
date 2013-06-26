package com.vlingo.midas.tos;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class TermsOfServiceView extends LinearLayout
{
  TextView m_tosLink;

  public TermsOfServiceView(Context paramContext, Text paramText)
  {
    super(paramContext);
    View.inflate(getContext(), 2130903124, this);
    init(paramText);
  }

  private static String getBRLocale()
  {
    String str1 = "";
    try
    {
      Process localProcess1 = Runtime.getRuntime().exec("getprop persist.sys.language");
      Process localProcess2 = Runtime.getRuntime().exec("getprop persist.sys.country");
      BufferedReader localBufferedReader1 = new BufferedReader(new InputStreamReader(localProcess1.getInputStream()));
      BufferedReader localBufferedReader2 = new BufferedReader(new InputStreamReader(localProcess2.getInputStream()));
      String str2 = localBufferedReader1.readLine();
      String str3 = localBufferedReader2.readLine();
      str1 = str2 + "_" + str3;
      localBufferedReader1.close();
      localBufferedReader2.close();
      label111: return str1;
    }
    catch (IOException localIOException)
    {
      break label111;
    }
  }

  private void init(Text paramText)
  {
    if (paramText == Text.TextSamsung)
    {
      i = 2131362230;
      if (getBRLocale().equals("pt_BR"))
        if (paramText != Text.TextSamsung)
          break label140;
    }
    label140: for (int i = 2131362643; ; i = 2131362645)
    {
      this.m_tosLink = ((TextView)findViewById(2131558837));
      this.m_tosLink.setText(getResources().getString(i));
      this.m_tosLink.setTextColor(-1);
      this.m_tosLink.setVisibility(0);
      this.m_tosLink.setMovementMethod(LinkMovementMethod.getInstance());
      this.m_tosLink.setText(Html.fromHtml(this.m_tosLink.getText().toString()));
      if ((i == 2131362476) || (i == 2131362645))
        this.m_tosLink.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
          }
        });
      return;
      i = 2131362476;
      break;
    }
  }

  public static enum Text
  {
    static
    {
      TextSamsung = new Text("TextSamsung", 1);
      Text[] arrayOfText = new Text[2];
      arrayOfText[0] = TextTerms;
      arrayOfText[1] = TextSamsung;
      $VALUES = arrayOfText;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.tos.TermsOfServiceView
 * JD-Core Version:    0.6.0
 */