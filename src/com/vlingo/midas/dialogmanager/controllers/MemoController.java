package com.vlingo.midas.dialogmanager.controllers;

import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.StateController;
import com.vlingo.core.internal.dialogmanager.StateController.Rule;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.DeleteMemoInterface;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.memo.IMemoUtil;
import com.vlingo.core.internal.memo.Memo;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.OrdinalUtil;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.gui.widgets.BargeInWidget;
import com.vlingo.midas.memo.MemoManager;
import com.vlingo.sdk.recognition.VLAction;
import java.util.List;
import java.util.Map;

public class MemoController extends StateController
  implements WidgetActionListener
{
  private Memo memoSelection;

  public void actionFail(String paramString)
  {
    unified().showSystemTurn(ApplicationAdapter.getInstance().getApplicationContext().getString(2131362309));
    super.actionFail(paramString);
  }

  public void actionSuccess()
  {
    unified().showSystemTurn(ApplicationAdapter.getInstance().getApplicationContext().getString(2131362308));
    super.actionSuccess();
  }

  public void deleteMemoAction(Memo paramMemo)
  {
    ((DeleteMemoInterface)getAction(DMActionType.DELETE_MEMO, DeleteMemoInterface.class)).memo(paramMemo).queue();
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    if (paramVLAction.getName().equals("LPAction"))
      return false;
    this.memoSelection = null;
    String str1 = VLActionUtil.getParamString(paramVLAction, "Name", false);
    String str2 = (String)paramVVSActionHandlerListener.getState(DialogDataType.CURRENT_ACTION);
    String str3 = VLActionUtil.getParamString(paramVLAction, "Which", false);
    label72: List localList;
    if (StringUtils.isNullOrWhiteSpace(str3))
    {
      OrdinalUtil.clearOrdinalData(paramVVSActionHandlerListener);
      if ((StringUtils.isNullOrWhiteSpace(str3)) || (this.memoSelection != null))
        break label172;
      localList = (List)paramVVSActionHandlerListener.getState(DialogDataType.ORDINAL_DATA);
      label101: if ((localList != null) || (this.memoSelection != null))
        break label222;
      if (str1 == null)
        break label193;
      VVSActionBase.UnifiedPrompter localUnifiedPrompter2 = unified();
      ResourceIdProvider.string localstring3 = ResourceIdProvider.string.core_no_memo_saved_about;
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = str1;
      localUnifiedPrompter2.showSystemTurn(getString(localstring3, arrayOfObject3));
    }
    while (true)
    {
      break;
      this.memoSelection = ((Memo)OrdinalUtil.getElement(paramVVSActionHandlerListener, str3));
      break label72;
      label172: localList = MemoManager.getMemoUtil().searchMemos(paramVVSActionHandlerListener.getActivityContext(), str1);
      break label101;
      label193: unified().showSystemTurn(getString(ResourceIdProvider.string.core_no_memo_saved, new Object[0]));
      getListener().finishDialog();
      continue;
      label222: if ((localList != null) && ((localList.size() == 1) || (this.memoSelection != null)))
      {
        Memo localMemo;
        if (localList.size() == 1)
        {
          localMemo = (Memo)localList.get(0);
          this.memoSelection = localMemo;
        }
        while (true)
        {
          if (!str2.equals("MemoDelete"))
            break label387;
          VVSActionBase.UnifiedPrompter localUnifiedPrompter1 = unified();
          ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_memo_confirm_delete;
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = localMemo.getMemoName(false);
          String str4 = getString(localstring1, arrayOfObject1);
          ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_memo_confirm_delete;
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = localMemo.getMemoName(true);
          localUnifiedPrompter1.showSystemTurn(str4, getString(localstring2, arrayOfObject2), VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_MEMO_DELETEPROMPT));
          paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.Memo, null, localMemo, this);
          break;
          localMemo = this.memoSelection;
        }
        if (!str2.equals("MemoLookup"))
          continue;
        paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.Memo, null, localMemo, this);
        DialogFlow.getInstance().cancelDialog();
        continue;
      }
      label387: if (localList != null)
      {
        OrdinalUtil.storeOrdinalData(paramVVSActionHandlerListener, localList, BargeInWidget.getDisplayCount(localList.size()));
        DialogFieldID localDialogFieldID = VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_MEMO_DELETECHOOSE);
        if (str2.equals("MemoLookup"))
          localDialogFieldID = VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_MEMO_LOOKUPCHOOSE);
        unified().showSystemTurn(ResourceIdProvider.string.core_memo_multiple_found, true, localDialogFieldID);
        paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.MemoList, null, localList, this);
        continue;
      }
      unified().showSystemTurn(getString(ResourceIdProvider.string.core_no_memo_saved, new Object[0]));
    }
  }

  public Map<String, String> getRuleMappings()
  {
    return null;
  }

  public Map<String, StateController.Rule> getRules()
  {
    return null;
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    Memo localMemo = (Memo)paramObject;
    this.memoSelection = localMemo;
    String str1 = (String)getListener().getState(DialogDataType.CURRENT_ACTION);
    if (StringUtils.isNullOrWhiteSpace(str1));
    while (true)
    {
      return;
      if (str1 != null)
      {
        if (str1.equals("MemoDelete"))
        {
          getListener().interruptTurn();
          VVSActionBase.UnifiedPrompter localUnifiedPrompter = unified();
          ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_memo_confirm_delete;
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = localMemo.getMemoName(false);
          String str2 = getString(localstring1, arrayOfObject1);
          ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_memo_confirm_delete;
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = localMemo.getMemoName(true);
          localUnifiedPrompter.showSystemTurn(str2, getString(localstring2, arrayOfObject2), VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_MEMO_DELETEPROMPT));
          getListener().showWidget(WidgetUtil.WidgetKey.Memo, null, localMemo, this);
          continue;
        }
        if (!str1.equals("MemoLookup"))
          break;
        getListener().interruptTurn();
        getListener().showWidget(WidgetUtil.WidgetKey.Memo, null, localMemo, this);
        getListener().finishTurn();
        reset();
        continue;
      }
    }
    throw new IllegalArgumentException();
  }

  public boolean handleLPAction(VLAction paramVLAction)
  {
    int i = 0;
    if ((VLActionUtil.getParamBool(paramVLAction, "execute", false, false)) && (this.memoSelection != null))
    {
      deleteMemoAction(this.memoSelection);
      getListener().finishDialog();
      i = 1;
    }
    return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.controllers.MemoController
 * JD-Core Version:    0.6.0
 */