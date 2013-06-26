package com.vlingo.core.internal.util;

import android.content.Context;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.DialogEvent;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.DialogTurn;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.ActionInterface;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.VVSDispatcher;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.recognition.VLAction;
import com.vlingo.sdk.services.VLServices;
import com.vlingo.sdk.services.VLServicesErrors;
import com.vlingo.sdk.services.VLServicesListener;
import java.util.List;

public class SayHello
{
  private static boolean isSayHelloDone = false;

  public static void sendHello()
  {
    String str = Settings.getLanguageApplication();
    VLServices localVLServices = VLSdk.getInstance().getVLServices();
    1 local1 = new VLServicesListener()
    {
      public void onError(VLServicesErrors paramVLServicesErrors, String paramString)
      {
      }

      public void onSuccess(List<VLAction> paramList)
      {
        VVSDispatcher.processActionList(paramList, new SayHello.VVSListener(null), null);
      }
    };
    Settings.setBoolean("hello_request_complete", true);
    localVLServices.sendHello(str, local1);
  }

  private static class VVSListener
    implements VVSActionHandlerListener
  {
    public void asyncHandlerDone()
    {
    }

    public void asyncHandlerStarted()
    {
    }

    public void endpointReco()
    {
    }

    public void execute(ActionInterface paramActionInterface)
    {
    }

    public void finishDialog()
    {
    }

    public void finishTurn()
    {
    }

    public Context getActivityContext()
    {
      return null;
    }

    public Object getState(DialogDataType paramDialogDataType)
    {
      return null;
    }

    public void interruptTurn()
    {
    }

    public void queueEvent(DialogEvent paramDialogEvent, DialogTurn paramDialogTurn)
    {
    }

    public void sendEvent(DialogEvent paramDialogEvent, DialogTurn paramDialogTurn)
    {
    }

    public void setFieldId(DialogFieldID paramDialogFieldID)
    {
    }

    public void showUserText(String paramString)
    {
    }

    public void showUserText(String paramString, DialogTurn paramDialogTurn)
    {
    }

    public void showVlingoText(String paramString)
    {
    }

    public void showVlingoTextAndTTS(String paramString1, String paramString2)
    {
    }

    public <T> void showWidget(WidgetUtil.WidgetKey paramWidgetKey, WidgetDecorator paramWidgetDecorator, T paramT, WidgetActionListener paramWidgetActionListener)
    {
    }

    public boolean startReco()
    {
      return false;
    }

    public void storeState(DialogDataType paramDialogDataType, Object paramObject)
    {
    }

    public void tts(String paramString)
    {
    }

    public void ttsAnyway(String paramString)
    {
    }

    public void userCancel()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.SayHello
 * JD-Core Version:    0.6.0
 */