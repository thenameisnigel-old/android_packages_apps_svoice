package com.vlingo.core.internal.dialogmanager.vvs;

import android.content.Context;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.DialogEvent;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.DialogTurn;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.ActionInterface;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;

public abstract interface VVSActionHandlerListener
{
  public abstract void asyncHandlerDone();

  public abstract void asyncHandlerStarted();

  public abstract void endpointReco();

  public abstract void execute(ActionInterface paramActionInterface);

  public abstract void finishDialog();

  public abstract void finishTurn();

  public abstract Context getActivityContext();

  public abstract Object getState(DialogDataType paramDialogDataType);

  public abstract void interruptTurn();

  public abstract void queueEvent(DialogEvent paramDialogEvent, DialogTurn paramDialogTurn);

  public abstract void sendEvent(DialogEvent paramDialogEvent, DialogTurn paramDialogTurn);

  public abstract void setFieldId(DialogFieldID paramDialogFieldID);

  public abstract void showUserText(String paramString);

  public abstract void showUserText(String paramString, DialogTurn paramDialogTurn);

  public abstract void showVlingoText(String paramString);

  public abstract void showVlingoTextAndTTS(String paramString1, String paramString2);

  public abstract <T> void showWidget(WidgetUtil.WidgetKey paramWidgetKey, WidgetDecorator paramWidgetDecorator, T paramT, WidgetActionListener paramWidgetActionListener);

  public abstract boolean startReco();

  public abstract void storeState(DialogDataType paramDialogDataType, Object paramObject);

  public abstract void tts(String paramString);

  public abstract void ttsAnyway(String paramString);

  public abstract void userCancel();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener
 * JD-Core Version:    0.6.0
 */