package com.vlingo.core.internal.dialogmanager;

import com.vlingo.core.internal.logging.EventLog;
import com.vlingo.sdk.recognition.NBestData;
import com.vlingo.sdk.recognition.VLRecognitionErrors;
import com.vlingo.sdk.recognition.VLRecognitionStates;
import com.vlingo.sdk.recognition.VLRecognitionWarnings;

public class DefaultDialogFlowListener
  implements DialogFlowListener
{
  public boolean onInterceptStartReco()
  {
    return false;
  }

  public void onRecoCancelled()
  {
  }

  public long onRecoToneStarting()
  {
    return 0L;
  }

  public void onResultsNoAction()
  {
  }

  public void showDialogFlowStateChange(DialogFlow.DialogFlowState paramDialogFlowState)
  {
  }

  public void showError(VLRecognitionErrors paramVLRecognitionErrors, String paramString)
  {
  }

  public void showRMSChange(int paramInt)
  {
  }

  public void showReceivedResults(EventLog paramEventLog)
  {
  }

  public void showRecoStateChange(VLRecognitionStates paramVLRecognitionStates)
  {
  }

  public void showUserText(String paramString, NBestData paramNBestData)
  {
  }

  public void showVlingoText(String paramString)
  {
  }

  public void showWarning(VLRecognitionWarnings paramVLRecognitionWarnings, String paramString)
  {
  }

  public void userCancel()
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.DefaultDialogFlowListener
 * JD-Core Version:    0.6.0
 */