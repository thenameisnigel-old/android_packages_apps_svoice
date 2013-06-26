package com.vlingo.core.internal.dialogmanager;

import com.vlingo.core.internal.audio.EndpointTimeWithSpeech;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class StateController extends Controller
{
  protected VLAction action;
  private boolean allSlotsFilled = false;

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    if (paramVLAction != null)
    {
      extractFieldsFromAction(paramVLAction);
      if (!paramVLAction.getName().equals("LPAction"))
        break label78;
      String str = VLActionUtil.getParamString(paramVLAction, "Action", false);
      if ((StringUtils.isNullOrWhiteSpace(str)) || (!str.equals("cancel")))
        break label72;
      getListener().interruptTurn();
      reset();
    }
    while (true)
    {
      return false;
      label72: handleLPAction(paramVLAction);
      label78: this.allSlotsFilled = executeNextRule();
    }
  }

  protected void executeConfirm()
  {
    if (getRules() == null);
    for (Rule localRule = null; ; localRule = (Rule)getRules().get("Confirm"))
    {
      if (localRule != null)
        showSystemTurn(localRule.getPrompt(), localRule.getPrompt(), DialogFieldID.buildFromString(localRule.getField_id(), EndpointTimeWithSpeech.SHORT));
      return;
    }
  }

  protected boolean executeNextRule()
  {
    int i = 1;
    if (getRules() == null)
      break label26;
    while (true)
    {
      return i;
      Iterator localIterator = getRules().values().iterator();
      label26: if (!localIterator.hasNext())
        continue;
      Rule localRule = (Rule)localIterator.next();
      if ((!StringUtils.isNullOrWhiteSpace(localRule.getValue())) || (!localRule.isRequired()))
        break;
      showSystemTurn(localRule.getPrompt(), localRule.getPrompt(), DialogFieldID.buildFromString(localRule.getField_id()));
      i = 0;
    }
  }

  protected void extractFieldsFromAction(VLAction paramVLAction)
  {
    Map localMap1 = getRules();
    if ((localMap1 == null) || (paramVLAction == null));
    while (true)
    {
      return;
      Iterator localIterator1 = localMap1.keySet().iterator();
      while (localIterator1.hasNext())
      {
        String str4 = (String)localIterator1.next();
        String str5 = VLActionUtil.getParamString(paramVLAction, str4, false);
        if (StringUtils.isNullOrWhiteSpace(str5))
          continue;
        Rule localRule2 = (Rule)localMap1.get(str4);
        if (localRule2 == null)
          continue;
        localRule2.setValue(str5);
      }
      Map localMap2 = getRuleMappings();
      if (localMap2 == null)
        continue;
      Iterator localIterator2 = paramVLAction.getParameterNames().iterator();
      while (localIterator2.hasNext())
      {
        String str1 = (String)localIterator2.next();
        String str2 = (String)localMap2.get(str1);
        if (str2 == null)
          continue;
        String str3 = VLActionUtil.getParamString(paramVLAction, str1, false);
        if (StringUtils.isNullOrWhiteSpace(str3))
          continue;
        Rule localRule1 = (Rule)localMap1.get(str2);
        if (localRule1 == null)
          continue;
        localRule1.setValue(str3);
      }
    }
  }

  public abstract Map<String, String> getRuleMappings();

  public abstract Map<String, Rule> getRules();

  public abstract boolean handleLPAction(VLAction paramVLAction);

  protected boolean hasConfirm()
  {
    if ((getRules() != null) && (getRules().get("Confirm") != null));
    for (int i = 1; ; i = 0)
      return i;
  }

  protected boolean isAllSlotsFilled()
  {
    return this.allSlotsFilled;
  }

  protected void setAllSlotsFilled(boolean paramBoolean)
  {
    this.allSlotsFilled = paramBoolean;
  }

  public static class Rule
  {
    private final String field_id;
    private final String prompt;
    private final boolean required;
    private String value;

    public Rule(String paramString1, String paramString2, boolean paramBoolean)
    {
      this.field_id = paramString1;
      this.prompt = paramString2;
      this.required = paramBoolean;
      this.value = null;
    }

    String getField_id()
    {
      return this.field_id;
    }

    String getPrompt()
    {
      return this.prompt;
    }

    public String getValue()
    {
      return this.value;
    }

    boolean isRequired()
    {
      return this.required;
    }

    void setValue(String paramString)
    {
      this.value = paramString;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.StateController
 * JD-Core Version:    0.6.0
 */