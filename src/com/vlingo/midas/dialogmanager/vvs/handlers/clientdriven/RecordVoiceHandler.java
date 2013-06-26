package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.internal.util.PackageUtil;
import com.vlingo.sdk.recognition.VLAction;

public class RecordVoiceHandler extends VVSActionHandler
{
  private static final String PARAM_TITLE = "Title";

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    if (PackageUtil.isAppInstalled("com.sec.android.app.voicerecorder", 0))
    {
      Intent localIntent = new Intent();
      String str1 = VLActionUtil.getParamString(paramVLAction, "Title", false);
      if ((str1 != null) && (!StringUtils.isNullOrWhiteSpace(str1)))
        localIntent.putExtra("title", str1);
      localIntent.putExtra("android.intent.action.RUN", true);
      localIntent.setFlags(270532608);
      localIntent.setComponent(new ComponentName("com.sec.android.app.voicerecorder", "com.sec.android.app.voicerecorder.VoiceRecorderMainActivity"));
      paramVVSActionHandlerListener.getActivityContext().startActivity(localIntent);
    }
    while (true)
    {
      return false;
      String str2 = paramVVSActionHandlerListener.getActivityContext().getString(2131361818);
      paramVVSActionHandlerListener.showVlingoTextAndTTS(str2, str2);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.RecordVoiceHandler
 * JD-Core Version:    0.6.0
 */