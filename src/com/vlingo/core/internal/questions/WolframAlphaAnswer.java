package com.vlingo.core.internal.questions;

import com.vlingo.core.internal.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class WolframAlphaAnswer extends AnswerAdaptation
{
  public WolframAlphaAnswer(Answer paramAnswer)
  {
    super(paramAnswer);
  }

  private String getResultText()
  {
    String str1 = "";
    for (Answer.Section localSection : getSections())
    {
      if (!localSection.getName().equals("Result"))
        continue;
      Answer.Subsection[] arrayOfSubsection = localSection.getSubsections();
      int k = arrayOfSubsection.length;
      for (int m = 0; m < k; m++)
      {
        String str2 = arrayOfSubsection[m].getText();
        if (str2 == null)
          continue;
        str1 = str1 + str2;
      }
    }
    if (shouldNix(str1))
      str1 = "";
    return stripText(str1);
  }

  private boolean shouldNix(String paramString)
  {
    int i = 1;
    if (paramString == null);
    while (true)
    {
      return i;
      if (paramString.matches("^\\(noun\\)"))
        continue;
      i = 0;
    }
  }

  public Answer.Section[] getInformationalSections()
  {
    ArrayList localArrayList = new ArrayList();
    boolean bool = hasSection("Result");
    Answer.Section[] arrayOfSection = getSections();
    int i = arrayOfSection.length;
    int j = 0;
    if (j < i)
    {
      Answer.Section localSection = arrayOfSection[j];
      if ("Result".equals(localSection.getName()));
      while (true)
      {
        j++;
        break;
        if (("Input interpretation".equals(localSection.getName())) && (bool))
          continue;
        localArrayList.add(localSection);
      }
    }
    if ((bool) && (localArrayList.isEmpty()) && (StringUtils.isNullOrWhiteSpace(getResultText())))
      localArrayList.add(getSection("Result"));
    return (Answer.Section[])localArrayList.toArray(new Answer.Section[0]);
  }

  public String getSimpleResponse()
  {
    if (hasSection("Result"));
    for (String str = getResultText(); ; str = super.getSimpleResponse())
      return str;
  }

  public boolean hasAnswer()
  {
    int i = 0;
    if (StringUtils.isNullOrWhiteSpace(getSimpleResponse()))
      if ((getSections() != null) && (getSections().length != 0))
        break label41;
    while (true)
    {
      return i;
      if ("data not available".equals(getSimpleResponse()))
        continue;
      label41: i = 1;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.questions.WolframAlphaAnswer
 * JD-Core Version:    0.6.0
 */