package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.localsearch.LocalSearchAdaptor;
import com.vlingo.core.internal.recognition.acceptedtext.AcceptedText;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.vcs.WidgetResponseReceivedListener;
import com.vlingo.sdk.recognition.VLAction;

public class LocalSearchHandler extends VVSActionHandler
  implements WidgetActionListener, WidgetResponseReceivedListener
{
  private LocalSearchAdaptor adaptor = new LocalSearchAdaptor();
  private Handler handler = new Handler(Looper.getMainLooper());

  public LocalSearchHandler()
  {
    getAdaptor().setWidgetListener(this);
  }

  private void showSuccess()
  {
    getHandler().post(new LocalSearchHandler.1(this));
    UserLoggingEngine.getInstance().landingPageViewed("local search");
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    int i = 0;
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    String str1 = VLActionUtil.getParamString(paramVLAction, "Location", false);
    String str2 = VLActionUtil.getParamString(paramVLAction, "Search", false);
    String str3 = VLActionUtil.getParamString(paramVLAction, "Region", false);
    if ((StringUtils.isNullOrWhiteSpace(str1)) && (StringUtils.isNullOrWhiteSpace(str2)))
    {
      String str4 = getString(ResourceIdProvider.string.core_local_search_blank_request_message, new Object[0]);
      getListener().showVlingoTextAndTTS(str4, str4);
    }
    while (true)
    {
      return i;
      getAdaptor().resetNumberOfRetries();
      getAdaptor().executeSearch(str2, str1, str3);
      i = 1;
    }
  }

  public LocalSearchAdaptor getAdaptor()
  {
    return this.adaptor;
  }

  public Handler getHandler()
  {
    return this.handler;
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    if ("com.vlingo.core.internal.dialogmanager.AcceptedText".equals(paramIntent.getAction()))
      if ((paramObject instanceof AcceptedText))
        sendAcceptedText((AcceptedText)paramObject);
    while (true)
    {
      return;
      throwUnknownActionException(paramIntent.getAction());
    }
  }

  // ERROR //
  public void onRequestFailed()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 166	com/vlingo/core/internal/dialogmanager/vvs/handlers/LocalSearchHandler:showFailure	()V
    //   4: aload_0
    //   5: invokevirtual 54	com/vlingo/core/internal/dialogmanager/vvs/handlers/LocalSearchHandler:getListener	()Lcom/vlingo/core/internal/dialogmanager/vvs/VVSActionHandlerListener;
    //   8: invokeinterface 169 1 0
    //   13: return
    //   14: astore_2
    //   15: aload_0
    //   16: invokevirtual 54	com/vlingo/core/internal/dialogmanager/vvs/handlers/LocalSearchHandler:getListener	()Lcom/vlingo/core/internal/dialogmanager/vvs/VVSActionHandlerListener;
    //   19: invokeinterface 169 1 0
    //   24: goto -11 -> 13
    //   27: astore_1
    //   28: aload_0
    //   29: invokevirtual 54	com/vlingo/core/internal/dialogmanager/vvs/handlers/LocalSearchHandler:getListener	()Lcom/vlingo/core/internal/dialogmanager/vvs/VVSActionHandlerListener;
    //   32: invokeinterface 169 1 0
    //   37: aload_1
    //   38: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   0	4	14	java/lang/Exception
    //   0	4	27	finally
  }

  public void onRequestScheduled()
  {
  }

  public void onResponseReceived()
  {
    try
    {
      if (getAdaptor().getCount() > 0)
        showSuccess();
      while (true)
      {
        return;
        showFailure();
      }
    }
    catch (Exception localException)
    {
      while (true)
        getListener().asyncHandlerDone();
    }
    finally
    {
      getListener().asyncHandlerDone();
    }
    throw localObject;
  }

  protected void setAdaptor(LocalSearchAdaptor paramLocalSearchAdaptor)
  {
    this.adaptor = paramLocalSearchAdaptor;
  }

  protected void setHandler(Handler paramHandler)
  {
    this.handler = paramHandler;
  }

  protected void showFailure()
  {
    getHandler().post(new LocalSearchHandler.2(this));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.LocalSearchHandler
 * JD-Core Version:    0.6.0
 */