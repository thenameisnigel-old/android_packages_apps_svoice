package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.logging.Logger;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.vcs.WidgetResponseReceivedListener;
import com.vlingo.midas.naver.NaverAdaptor;
import com.vlingo.sdk.recognition.VLAction;

public class NaverContentHandler extends VVSActionHandler
  implements WidgetActionListener, WidgetResponseReceivedListener
{
  private NaverAdaptor adaptor = null;
  Handler handler = null;
  private Logger log = Logger.getLogger(NaverContentHandler.class, "VLG_NaverContentHandler");

  public NaverContentHandler()
  {
    this.adaptor.setWidgetListener(this);
    this.handler = new Handler(Looper.getMainLooper());
  }

  private void showFailure()
  {
    this.handler.post(new NaverContentHandler.2(this));
  }

  private void showSuccess()
  {
    this.handler.post(new NaverContentHandler.1(this));
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("search");
    String str = VLActionUtil.getParamString(paramVLAction, "Request", false);
    this.adaptor.setVVSActionHandlerListener(paramVVSActionHandlerListener);
    this.adaptor.sendNaverRequest(str, true);
    return true;
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
  }

  public void onRequestFailed()
  {
    try
    {
      showFailure();
      return;
    }
    finally
    {
      getListener().asyncHandlerDone();
    }
    throw localObject;
  }

  public void onRequestScheduled()
  {
  }

  public void onResponseReceived()
  {
    try
    {
      if (StringUtils.isNullOrWhiteSpace(this.adaptor.getContent()))
        showFailure();
      while (true)
      {
        return;
        showSuccess();
      }
    }
    finally
    {
      getListener().asyncHandlerDone();
    }
    throw localObject;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.NaverContentHandler
 * JD-Core Version:    0.6.0
 */