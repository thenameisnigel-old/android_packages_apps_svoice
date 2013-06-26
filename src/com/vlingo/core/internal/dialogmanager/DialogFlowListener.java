package com.vlingo.core.internal.dialogmanager;

import com.vlingo.core.internal.logging.EventLog;
import com.vlingo.sdk.recognition.NBestData;
import com.vlingo.sdk.recognition.VLRecognitionErrors;
import com.vlingo.sdk.recognition.VLRecognitionStates;
import com.vlingo.sdk.recognition.VLRecognitionWarnings;

public abstract interface DialogFlowListener
{
  public abstract boolean onInterceptStartReco();

  public abstract void onRecoCancelled();

  public abstract long onRecoToneStarting();

  public abstract void onResultsNoAction();

  public abstract void showDialogFlowStateChange(DialogFlow.DialogFlowState paramDialogFlowState);

  public abstract void showError(VLRecognitionErrors paramVLRecognitionErrors, String paramString);

  public abstract void showRMSChange(int paramInt);

  public abstract void showReceivedResults(EventLog paramEventLog);

  public abstract void showRecoStateChange(VLRecognitionStates paramVLRecognitionStates);

  public abstract void showUserText(String paramString, NBestData paramNBestData);

  public abstract void showVlingoText(String paramString);

  public abstract void showWarning(VLRecognitionWarnings paramVLRecognitionWarnings, String paramString);

  public abstract void userCancel();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.DialogFlowListener
 * JD-Core Version:    0.6.0
 */