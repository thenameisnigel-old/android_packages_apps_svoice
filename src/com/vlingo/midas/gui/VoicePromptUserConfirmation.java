package com.vlingo.midas.gui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.res.Resources;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.vlingo.core.internal.settings.VoicePrompt;
import com.vlingo.core.internal.settings.VoicePrompt.ConfirmationDelegate;

public class VoicePromptUserConfirmation
  implements VoicePrompt.ConfirmationDelegate
{
  private static boolean alertDialogIsShowing = false;
  private int NO_ICON = 0;
  private Context context;

  public VoicePromptUserConfirmation(Context paramContext)
  {
    this.context = paramContext;
  }

  private Context context()
  {
    return this.context;
  }

  private void getConfirmationDialog(VoicePrompt paramVoicePrompt, Runnable paramRunnable)
  {
    Resources localResources = context().getResources();
    int i;
    VoicePromptUserConfirmation.ConfirmationView.Switch localSwitch;
    if (paramVoicePrompt.isOn())
    {
      i = 2131362483;
      String str1 = localResources.getString(i);
      String str2 = localResources.getString(2131362487);
      String str3 = localResources.getString(2131362482);
      Context localContext = context();
      if (!paramVoicePrompt.isOn())
        break label228;
      localSwitch = VoicePromptUserConfirmation.ConfirmationView.Switch.OFF;
      label61: ConfirmationView localConfirmationView = new ConfirmationView(localContext, localSwitch);
      1 local1 = new DialogInterface.OnClickListener(localConfirmationView, paramVoicePrompt, paramRunnable)
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          VoicePromptUserConfirmation.access$002(false);
          if (this.val$view.DontAsk().isChecked())
            this.val$_voicePrompt.shouldAsk(false);
          if (this.val$_onOK != null)
            this.val$_onOK.run();
        }
      };
      2 local2 = new DialogInterface.OnClickListener(localConfirmationView, paramVoicePrompt)
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          VoicePromptUserConfirmation.access$002(false);
          if (this.val$view.DontAsk().isChecked())
            this.val$_voicePrompt.shouldAsk(true);
        }
      };
      3 local3 = new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramDialogInterface)
        {
          VoicePromptUserConfirmation.access$002(false);
        }
      };
      4 local4 = new DialogInterface.OnKeyListener()
      {
        public boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
        {
          int i = 0;
          VoicePromptUserConfirmation.access$002(false);
          if ((paramInt == 84) || (paramInt == 82))
            i = 1;
          return i;
        }
      };
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(context());
      localBuilder.setIcon(this.NO_ICON);
      localBuilder.setTitle(str1);
      localBuilder.setView(localConfirmationView);
      localBuilder.setPositiveButton(str2, local1);
      localBuilder.setNegativeButton(str3, local2);
      localBuilder.setOnCancelListener(local3);
      localBuilder.setOnKeyListener(local4);
      AlertDialog localAlertDialog = localBuilder.create();
      localAlertDialog.show();
      if (!localAlertDialog.isShowing())
        break label236;
      alertDialogIsShowing = true;
    }
    while (true)
    {
      return;
      i = 2131362484;
      break;
      label228: localSwitch = VoicePromptUserConfirmation.ConfirmationView.Switch.ON;
      break label61;
      label236: alertDialogIsShowing = false;
    }
  }

  public void confirm(VoicePrompt paramVoicePrompt, Runnable paramRunnable)
  {
    getConfirmationDialog(paramVoicePrompt, paramRunnable);
  }

  private static class ConfirmationView extends LinearLayout
  {
    private CheckBox dont_ask;

    public ConfirmationView(Context paramContext, Switch paramSwitch)
    {
      super();
      View.inflate(getContext(), 2130903068, this);
      TextView localTextView = (TextView)findViewById(2131558608);
      if (paramSwitch == Switch.OFF);
      for (int i = 2131362485; ; i = 2131362486)
      {
        localTextView.setText(i);
        this.dont_ask = ((CheckBox)findViewById(2131558609));
        this.dont_ask.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
          }
        });
        return;
      }
    }

    public CheckBox DontAsk()
    {
      return this.dont_ask;
    }

    public static enum Switch
    {
      static
      {
        Switch[] arrayOfSwitch = new Switch[2];
        arrayOfSwitch[0] = OFF;
        arrayOfSwitch[1] = ON;
        $VALUES = arrayOfSwitch;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.VoicePromptUserConfirmation
 * JD-Core Version:    0.6.0
 */