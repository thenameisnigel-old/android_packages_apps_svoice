package com.vlingo.midas.dialogmanager.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.Controller;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.MemoInterface;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.memo.IMemoUtil;
import com.vlingo.core.internal.memo.Memo;
import com.vlingo.core.internal.memo.MemoUtilException;
import com.vlingo.core.internal.recognition.acceptedtext.AcceptedText.TextType;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.gui.ConversationActivity;
import com.vlingo.midas.gui.ConversationActivity.DrivingMode;
import com.vlingo.midas.memo.MemoManager;
import com.vlingo.midas.memo.SMemo2Util;
import com.vlingo.sdk.recognition.VLAction;

public class AddMemoController extends Controller
  implements WidgetActionListener
{
  private String memoText;

  private int findCreatedMemoId(Context paramContext, String paramString)
  {
    IMemoUtil localIMemoUtil = MemoManager.getMemoUtil();
    try
    {
      Memo localMemo = localIMemoUtil.getMostRecentlyCreatedMemo(paramContext, paramString);
      if (localMemo != null)
      {
        int j = localMemo.getId();
        i = j;
        return i;
      }
    }
    catch (MemoUtilException localMemoUtilException)
    {
      while (true)
        int i = -2;
    }
  }

  private void save(DMAction.Listener paramListener)
  {
    ((MemoInterface)getAction(DMActionType.SAVE_MEMO, MemoInterface.class, paramListener)).memo(this.memoText).queue();
    sendAcceptedText(this.memoText, AcceptedText.TextType.MEMO);
  }

  private void viewMemo(DMAction.Listener paramListener)
  {
    int i = findCreatedMemoId(ApplicationAdapter.getInstance().getApplicationContext(), this.memoText);
    Intent localIntent = new Intent(MemoManager.getMemoUtil().getViewMemoAction());
    if (SMemo2Util.isInstalled())
      localIntent.putExtra("id", i);
    while (true)
    {
      getListener().getActivityContext().startActivity(localIntent);
      return;
      localIntent.putExtra("memoID", i);
    }
  }

  public void actionFail(String paramString)
  {
    super.actionFail(paramString);
    unified().showSystemTurn(getString(ResourceIdProvider.string.core_memo_not_saved, new Object[0]), false, (DialogFieldID)null);
  }

  public void actionSuccess()
  {
    super.actionSuccess();
    unified().showSystemTurn(ApplicationAdapter.getInstance().getApplicationContext().getString(2131361949), false, (DialogFieldID)null);
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    int i = 0;
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    if (paramVLAction.getName().equals("PopulateTextbox"))
    {
      String str1 = VLActionUtil.getParamString(paramVLAction, "Action", false);
      if ((!StringUtils.isNullOrWhiteSpace(str1)) && ("append".equals(str1)))
      {
        String str2 = VLActionUtil.getParamString(paramVLAction, "Value", false);
        if (!StringUtils.isNullOrWhiteSpace(str2))
          this.memoText = (this.memoText + " " + str2);
        i = 1;
        label100: Context localContext = paramVVSActionHandlerListener.getActivityContext();
        if ((!(localContext instanceof ConversationActivity)) || (((ConversationActivity)localContext).isDrivingMode() != ConversationActivity.DrivingMode.Driving))
          break label292;
      }
    }
    label292: for (int j = 1; ; j = 0)
    {
      if ((j != 0) || (this.memoText != null))
      {
        Memo localMemo = new Memo();
        localMemo.setText(this.memoText);
        if (VLActionUtil.getParamBool(paramVLAction, "doit", false, false))
        {
          save(this);
          if (localMemo.getId() == -1)
            localMemo.setId(-2);
        }
        paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.Memo, null, localMemo, this);
        if (i != 0)
          unified().showSystemTurn(this.memoText + paramVVSActionHandlerListener.getActivityContext().getResources().getString(2131362226), true, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_MEMO_MSG));
      }
      return false;
      this.memoText = VLActionUtil.getParamString(paramVLAction, "Value", true);
      break;
      this.memoText = VLActionUtil.getParamString(paramVLAction, "memo", false);
      break label100;
    }
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    if ("memo.save".equals(paramIntent.getAction()))
    {
      getListener().interruptTurn();
      save(this);
    }
    while (true)
    {
      return;
      if ("com.vlingo.core.internal.dialogmanager.Cancel".equals(paramIntent.getAction()))
      {
        getListener().interruptTurn();
        unified().showSystemTurn(getString(ResourceIdProvider.string.core_car_tts_TASK_CANCELLED, new Object[0]));
        reset();
        continue;
      }
      if (!"memo.findmostrecent".equals(paramIntent.getAction()))
        continue;
      getListener().interruptTurn();
      viewMemo(this);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.controllers.AddMemoController
 * JD-Core Version:    0.6.0
 */