package com.vlingo.core.internal.questions.parser;

import com.vlingo.core.internal.questions.Answer;
import com.vlingo.core.internal.questions.Answer.Section;
import com.vlingo.core.internal.questions.Answer.Subsection;
import com.vlingo.core.internal.util.StringUtils;
import java.util.ArrayList;
import java.util.Map;

public class ProviderResponse extends ResponseElement
  implements Answer
{
  private static int DEBUG = 0;
  private ServerResponse mAnswer;
  private ArrayList<SectionElement> mSections = new ArrayList();

  public ProviderResponse(String paramString)
  {
    super(paramString);
  }

  public void add(SectionElement paramSectionElement)
  {
    this.mSections.add(paramSectionElement);
  }

  public String getAnswer()
  {
    if (this.mAnswer == null);
    for (String str = null; ; str = this.mAnswer.getAnswer())
      return str;
  }

  public Answer.Section[] getInformationalSections()
  {
    return getSections();
  }

  public String getProvider()
  {
    return (String)this.attributes.get(PROVIDER);
  }

  public String getQuestion()
  {
    if (this.mAnswer == null);
    for (String str = null; ; str = this.mAnswer.getQuestion())
      return str;
  }

  public String getRawResponse()
  {
    return (String)this.attributes.get(RAW_RESPONSE);
  }

  public Answer.Section getSection(String paramString)
  {
    Answer.Section[] arrayOfSection = (Answer.Section[])this.mSections.toArray(new Answer.Section[0]);
    int i = arrayOfSection.length;
    int j = 0;
    Answer.Section localSection;
    if (j < i)
    {
      localSection = arrayOfSection[j];
      if (!localSection.getName().equals(paramString));
    }
    while (true)
    {
      return localSection;
      j++;
      break;
      localSection = null;
    }
  }

  public Answer.Section[] getSections()
  {
    return (Answer.Section[])this.mSections.toArray(new Answer.Section[0]);
  }

  public String getSimpleResponse()
  {
    return (String)this.attributes.get(SIMPLE_RESPONSE);
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
    if (getSection(paramString) != null);
    for (int i = 1; ; i = 0)
      return i;
  }

  protected String paran(String paramString)
  {
    return "(" + paramString + ")";
  }

  public void setAnswer(ServerResponse paramServerResponse)
  {
    this.mAnswer = paramServerResponse;
  }

  public String toString()
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    StringBuilder localStringBuilder2 = localStringBuilder1.append(getClass().getSimpleName()).append("\n").append("  Question").append(paran(quoted(getQuestion()))).append("\n").append("  Answer").append(paran(quoted(getAnswer()))).append("\n").append("  Provider").append(paran(quoted(getProvider()))).append("\n").append("  Response").append(paran(quoted(getSimpleResponse()))).append("\n").append("  RawResponse");
    String str;
    Answer.Section[] arrayOfSection;
    int i;
    if (DEBUG > 0)
    {
      str = getRawResponse();
      localStringBuilder2.append(paran(quoted(str)));
      arrayOfSection = getSections();
      i = arrayOfSection.length;
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
        break label381;
      Answer.Section localSection = arrayOfSection[j];
      localStringBuilder1.append("\n").append("  Section").append(paran(quoted(localSection.getName())));
      Answer.Subsection[] arrayOfSubsection = localSection.getSubsections();
      int k = arrayOfSubsection.length;
      int m = 0;
      while (true)
        if (m < k)
        {
          Answer.Subsection localSubsection = arrayOfSubsection[m];
          localStringBuilder1.append("\n").append("    Subsection").append("\n").append("    (").append("\n").append("      text").append(paran(quoted(localSubsection.getText()))).append("\n").append("      url").append(paran(quoted(localSubsection.getImageUrl()))).append("\n").append("      height").append(paran(quoted(Integer.toString(localSubsection.getHeight())))).append("\n").append("    )");
          m++;
          continue;
          str = "...";
          break;
        }
    }
    label381: return localStringBuilder1.toString();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.questions.parser.ProviderResponse
 * JD-Core Version:    0.6.0
 */