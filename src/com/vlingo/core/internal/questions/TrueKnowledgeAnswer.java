package com.vlingo.core.internal.questions;

public class TrueKnowledgeAnswer extends AnswerAdaptation
{
  public TrueKnowledgeAnswer(Answer paramAnswer)
  {
    super(paramAnswer);
  }

  public String getSimpleResponse()
  {
    return stripText(super.getSimpleResponse());
  }

  public boolean is(Type paramType)
  {
    int i = 0;
    switch (1.$SwitchMap$com$vlingo$core$internal$questions$TrueKnowledgeAnswer$Type[paramType.ordinal()])
    {
    default:
      return i;
    case 1:
      if ((getSections() == null) || (getSections().length == 0));
      for (i = 1; ; i = 0)
        break;
    case 2:
      if (getSections() != null);
      for (i = 1; ; i = 0)
        break;
    case 3:
    }
    if (getSections() != null);
    for (i = 1; ; i = 0)
      break;
  }

  public static enum Type
  {
    static
    {
      FACT_ANSWER = new Type("FACT_ANSWER", 2);
      LIST_ANSWER = new Type("LIST_ANSWER", 3);
      Type[] arrayOfType = new Type[4];
      arrayOfType[0] = SIMPLE_ANSWER;
      arrayOfType[1] = YES_NO_ANSWER;
      arrayOfType[2] = FACT_ANSWER;
      arrayOfType[3] = LIST_ANSWER;
      $VALUES = arrayOfType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.questions.TrueKnowledgeAnswer
 * JD-Core Version:    0.6.0
 */