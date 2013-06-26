package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;

public class SetTurnParamsHandler extends VVSActionHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    String str1 = VLActionUtil.getParamString(paramVLAction, "fieldid", false);
    if (!StringUtils.isNullOrWhiteSpace(str1))
      paramVVSActionHandlerListener.setFieldId(DialogFieldID.buildFromString(str1));
    String str2 = VLActionUtil.getParamString(paramVLAction, "ActionName", false);
    if (!StringUtils.isNullOrWhiteSpace(str2))
      paramVVSActionHandlerListener.storeState(DialogDataType.CURRENT_ACTION, str2);
    String str3 = VLActionUtil.getParamString(paramVLAction, "ParseType", false);
    if (!StringUtils.isNullOrWhiteSpace(str3))
      paramVVSActionHandlerListener.storeState(DialogDataType.PARSE_TYPE, str3);
    return false;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.SetTurnParamsHandler
 * JD-Core Version:    0.6.0
 */