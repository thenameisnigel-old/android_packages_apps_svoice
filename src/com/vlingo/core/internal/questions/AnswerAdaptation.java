package com.vlingo.core.internal.questions;

import com.vlingo.core.internal.util.StringUtils;

public class AnswerAdaptation
  implements Answer
{
  static final String[] duplicated_unit_table;
  private Answer mAnswer;

  static
  {
    String[] arrayOfString = new String[10];
    arrayOfString[0] = "lb  (pounds)";
    arrayOfString[1] = "kg  (kilograms)";
    arrayOfString[2] = "mg  (milligrams)";
    arrayOfString[3] = "gr  (grams)";
    arrayOfString[4] = "t  (metric tons)";
    arrayOfString[5] = "km  (kilometers)";
    arrayOfString[6] = "m  (meters)";
    arrayOfString[7] = "cm  (centimeters)";
    arrayOfString[8] = "mm  (millimeters)";
    arrayOfString[9] = "dm  (decimeters)";
    duplicated_unit_table = arrayOfString;
  }

  public AnswerAdaptation(Answer paramAnswer)
  {
    this.mAnswer = paramAnswer;
  }

  public Answer.Section[] getInformationalSections()
  {
    return this.mAnswer.getInformationalSections();
  }

  public Answer.Section getSection(String paramString)
  {
    return this.mAnswer.getSection(paramString);
  }

  public Answer.Section[] getSections()
  {
    return this.mAnswer.getSections();
  }

  public String getSimpleResponse()
  {
    return this.mAnswer.getSimpleResponse();
  }

  public boolean hasAnswer()
  {
    if ((StringUtils.isNullOrWhiteSpace(getSimpleResponse())) && ((getSections() == null) || (getSections().length == 0)));
    for (int i = 0; ; i = 1)
      return i;
  }

  public boolean hasMoreInformation()
  {
    if ((getInformationalSections() != null) && (getInformationalSections().length > 0));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean hasSection(String paramString)
  {
    return this.mAnswer.hasSection(paramString);
  }

  protected String removeDuplicatedUnit(String paramString)
  {
    int i = 0;
    if (i < duplicated_unit_table.length)
      if (paramString.indexOf(duplicated_unit_table[i]) == -1);
    for (String str = StringUtils.replace(paramString, duplicated_unit_table[i], duplicated_unit_table[i].split(" ")[0]); ; str = paramString)
    {
      return str;
      i++;
      break;
    }
  }

  protected String stripText(String paramString)
  {
    String str = removeDuplicatedUnit(paramString);
    StringBuilder localStringBuilder = new StringBuilder();
    if (str.contains("("))
    {
      int i = 0;
      int j = 1;
      int k = 0;
      if (k < str.length())
      {
        if ((str.charAt(k) == '(') && (j == 0))
          i = 1;
        while (true)
        {
          if (str.charAt(k) == ')')
          {
            i = 0;
            j = 0;
          }
          k++;
          break;
          if ((i != 0) || (str.charAt(k) == ')') || (str.charAt(k) == '('))
            continue;
          localStringBuilder.append(str.charAt(k));
        }
      }
    }
    else
    {
      localStringBuilder.append(str);
    }
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.questions.AnswerAdaptation
 * JD-Core Version:    0.6.0
 */