package com.vlingo.midas.tos;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.res.Resources;
import android.view.KeyEvent;
import android.widget.Toast;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.midas.settings.MidasSettings;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TermsOfServiceManager
{
  private static AlertDialog tempDialog = null;

  public static void dismissTempDlg()
  {
    if (tempDialog != null)
    {
      tempDialog.dismiss();
      tempDialog = null;
    }
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

  public static AlertDialog getTOSDialog(Context paramContext, DialogInterface.OnClickListener paramOnClickListener1, DialogInterface.OnClickListener paramOnClickListener2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    dismissTempDlg();
    MidasSettings.updateCurrentLocale();
    AlertDialog localAlertDialog;
    if (!MidasSettings.isSamsungDisclaimerAccepted())
    {
      DeclineListener localDeclineListener1 = new DeclineListener(paramContext, paramOnClickListener2, paramOnCancelListener);
      AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(paramContext).setIcon(0);
      String str1;
      AlertDialog.Builder localBuilder2;
      if (getBRLocale().equals("pt_BR"))
      {
        str1 = paramContext.getResources().getString(2131362642);
        localBuilder2 = localBuilder1.setTitle(str1).setView(new TermsOfServiceView(paramContext, TermsOfServiceView.Text.TextSamsung));
        if (!getBRLocale().equals("pt_BR"))
          break label174;
      }
      label174: for (String str2 = paramContext.getResources().getString(2131362646); ; str2 = paramContext.getResources().getString(2131362229))
      {
        tempDialog = localBuilder2.setPositiveButton(str2, new AcceptListener(paramContext, paramOnClickListener1, paramOnClickListener2, paramOnCancelListener)).setOnCancelListener(localDeclineListener1).setOnKeyListener(new DialogInterface.OnKeyListener()
        {
          public boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
          {
            if ((paramInt == 84) || (paramInt == 82));
            for (int i = 1; ; i = 0)
              return i;
          }
        }).create();
        UserLoggingEngine.getInstance().helpPageViewed("tos-accept");
        localAlertDialog = tempDialog;
        return localAlertDialog;
        str1 = paramContext.getResources().getString(2131362481);
        break;
      }
    }
    DeclineListener localDeclineListener2 = new DeclineListener(paramContext, paramOnClickListener2, paramOnCancelListener);
    AlertDialog.Builder localBuilder3 = new AlertDialog.Builder(paramContext).setIcon(0);
    String str3;
    label236: String str4;
    label281: AlertDialog.Builder localBuilder5;
    if (getBRLocale().equals("pt_BR"))
    {
      str3 = paramContext.getResources().getString(2131362644);
      AlertDialog.Builder localBuilder4 = localBuilder3.setTitle(str3).setView(new TermsOfServiceView(paramContext, TermsOfServiceView.Text.TextTerms));
      if (!getBRLocale().equals("pt_BR"))
        break label383;
      str4 = paramContext.getResources().getString(2131362647);
      localBuilder5 = localBuilder4.setPositiveButton(str4, new AcceptListener(paramContext, paramOnClickListener1, paramOnClickListener2, paramOnCancelListener));
      if (!getBRLocale().equals("pt_BR"))
        break label397;
    }
    label397: for (String str5 = paramContext.getResources().getString(2131362648); ; str5 = paramContext.getResources().getString(2131362473))
    {
      tempDialog = localBuilder5.setNegativeButton(str5, localDeclineListener2).setOnCancelListener(localDeclineListener2).setOnKeyListener(new DialogInterface.OnKeyListener()
      {
        public boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
        {
          if ((paramInt == 84) || (paramInt == 82));
          for (int i = 1; ; i = 0)
            return i;
        }
      }).create();
      UserLoggingEngine.getInstance().helpPageViewed("tos-accept");
      localAlertDialog = tempDialog;
      break;
      str3 = paramContext.getResources().getString(2131362480);
      break label236;
      label383: str4 = paramContext.getResources().getString(2131362472);
      break label281;
    }
  }

  public static boolean isTOSRequired()
  {
    if ((!MidasSettings.isTOSAccepted()) || (!MidasSettings.isSamsungDisclaimerAccepted()));
    for (int i = 1; ; i = 0)
      return i;
  }

  static class AcceptListener
    implements DialogInterface.OnClickListener
  {
    private final DialogInterface.OnClickListener acceptListener;
    private final DialogInterface.OnCancelListener cancelListener;
    private final Context ctx;
    private final DialogInterface.OnClickListener declineListener;

    public AcceptListener(Context paramContext, DialogInterface.OnClickListener paramOnClickListener1, DialogInterface.OnClickListener paramOnClickListener2, DialogInterface.OnCancelListener paramOnCancelListener)
    {
      this.acceptListener = paramOnClickListener1;
      this.declineListener = paramOnClickListener2;
      this.cancelListener = paramOnCancelListener;
      this.ctx = paramContext;
    }

    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      if (!MidasSettings.isSamsungDisclaimerAccepted())
      {
        MidasSettings.setSamsungDisclaimerAccepted(true);
        TermsOfServiceManager.getTOSDialog(this.ctx, this.acceptListener, this.declineListener, this.cancelListener).show();
      }
      while (true)
      {
        return;
        if (!MidasSettings.isTOSAccepted());
        MidasSettings.setTOSAccepted(true);
        this.acceptListener.onClick(paramDialogInterface, paramInt);
      }
    }
  }

  static class DeclineListener
    implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener
  {
    Context ctx;
    DialogInterface.OnCancelListener onCancelListener;
    DialogInterface.OnClickListener onDeclineListener;

    public DeclineListener(Context paramContext, DialogInterface.OnClickListener paramOnClickListener, DialogInterface.OnCancelListener paramOnCancelListener)
    {
      this.onDeclineListener = paramOnClickListener;
      this.onCancelListener = paramOnCancelListener;
      this.ctx = paramContext;
    }

    void decline()
    {
      MidasSettings.setSamsungDisclaimerAccepted(false);
      Toast.makeText(this.ctx, this.ctx.getResources().getString(2131362477), 0).show();
    }

    public void onCancel(DialogInterface paramDialogInterface)
    {
      this.onCancelListener.onCancel(paramDialogInterface);
      decline();
    }

    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      this.onDeclineListener.onClick(paramDialogInterface, paramInt);
      decline();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.tos.TermsOfServiceManager
 * JD-Core Version:    0.6.0
 */