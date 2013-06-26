package com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven;

import android.content.Intent;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.actions.OpenAppAction;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.OpenAppUtil;
import com.vlingo.core.internal.util.OpenAppUtil.AppInfo;
import com.vlingo.core.internal.util.OrdinalUtil;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.List;

public class ShowOpenAppWidgetHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private VVSActionHandlerListener listener;

  private void launchApp(String paramString1, String paramString2, VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    OpenAppUtil localOpenAppUtil = new OpenAppUtil();
    if (StringUtils.isNullOrWhiteSpace(paramString2))
    {
      this.listener = null;
      localOpenAppUtil.buildMatchingAppList(paramString1);
      if (localOpenAppUtil.getAppInfoList().size() == 1)
      {
        ResourceIdProvider.string localstring4 = ResourceIdProvider.string.core_opening_app;
        Object[] arrayOfObject4 = new Object[1];
        arrayOfObject4[0] = ((OpenAppUtil.AppInfo)localOpenAppUtil.getAppInfoList().get(0)).toString();
        String str3 = getString(localstring4, arrayOfObject4);
        getListener().showVlingoTextAndTTS(str3, str3);
        launchAppAction((OpenAppUtil.AppInfo)localOpenAppUtil.getAppInfoList().get(0));
      }
    }
    while (true)
    {
      return;
      if (localOpenAppUtil.getAppInfoList().size() > 1)
      {
        OrdinalUtil.storeOrdinalData(paramVVSActionHandlerListener, localOpenAppUtil.getAppInfoList());
        this.listener = paramVVSActionHandlerListener;
        VVSActionBase.UnifiedPrompter localUnifiedPrompter = unified();
        ResourceIdProvider.string localstring3 = ResourceIdProvider.string.core_multiple_applications;
        Object[] arrayOfObject3 = new Object[2];
        arrayOfObject3[0] = Integer.toString(localOpenAppUtil.getAppInfoList().size());
        arrayOfObject3[1] = paramString1;
        localUnifiedPrompter.showSystemTurn(getString(localstring3, arrayOfObject3));
        paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.OpenApp, null, localOpenAppUtil.getAppInfoList(), this);
        promptForApp(localOpenAppUtil.getAppInfoList());
        continue;
      }
      ResourceIdProvider localResourceIdProvider;
      ResourceIdProvider.string localstring2;
      Object[] arrayOfObject2;
      if (!StringUtils.isNullOrWhiteSpace(paramString1))
      {
        localResourceIdProvider = VlingoAndroidCore.getResourceProvider();
        localstring2 = ResourceIdProvider.string.core_car_tts_NO_SPOKEN_APPMATCH_DEMAND;
        arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = paramString1;
      }
      for (String str2 = localResourceIdProvider.getString(localstring2, arrayOfObject2); ; str2 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_tts_NO_APPMATCH_DEMAND))
      {
        unified().showSystemTurn(str2);
        launchAppAction(null);
        break;
      }
      if ((!StringUtils.isNullOrWhiteSpace(paramString2)) && (OrdinalUtil.getElement(paramVVSActionHandlerListener, paramString2) == null))
      {
        promptForApp(localOpenAppUtil.getAppInfoList());
        continue;
      }
      OpenAppUtil.AppInfo localAppInfo = (OpenAppUtil.AppInfo)OrdinalUtil.getElement(paramVVSActionHandlerListener, paramString2);
      OrdinalUtil.clearOrdinalData(paramVVSActionHandlerListener);
      ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_opening_app;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = localAppInfo.toString();
      String str1 = getString(localstring1, arrayOfObject1);
      getListener().showVlingoTextAndTTS(str1, str1);
      launchAppAction(localAppInfo);
    }
  }

  private void promptForApp(List<OpenAppUtil.AppInfo> paramList)
  {
    unified().showSystemTurn(ResourceIdProvider.string.core_car_tts_LAUNCHAPP_PROMPT_DEMAND, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_MAIN_LAUNCHAPP));
    UserLoggingEngine.getInstance().landingPageViewed("car-sms-message");
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    String str = VLActionUtil.getParamString(paramVLAction, "Which", false);
    launchApp(VLActionUtil.getParamString(paramVLAction, "Name", StringUtils.isNullOrWhiteSpace(str)), str, paramVLAction, paramVVSActionHandlerListener);
    return false;
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    Integer localInteger;
    if ((this.listener != null) && ("com.vlingo.core.internal.dialogmanager.DataTransfered".equals(paramIntent.getAction())))
    {
      VVSActionHandlerListener localVVSActionHandlerListener = this.listener;
      if ((paramObject instanceof Integer))
      {
        localInteger = Integer.valueOf(((Integer)paramObject).intValue());
        OrdinalUtil.storeOrdinalData(localVVSActionHandlerListener, localInteger.intValue());
      }
    }
    while (true)
    {
      return;
      localInteger = null;
      break;
      getListener().interruptTurn();
      launchAppAction((OpenAppUtil.AppInfo)paramObject);
    }
  }

  public void launchAppAction(OpenAppUtil.AppInfo paramAppInfo)
  {
    String str = "launch";
    if (paramAppInfo != null)
      str = str + " " + paramAppInfo;
    UserLoggingEngine.getInstance().landingPageViewed(str);
    ((OpenAppAction)getAction(DMActionType.OPEN_APP, OpenAppAction.class)).appInfo(paramAppInfo).queue();
  }

  public void reset()
  {
    getListener().finishDialog();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.ShowOpenAppWidgetHandler
 * JD-Core Version:    0.6.0
 */