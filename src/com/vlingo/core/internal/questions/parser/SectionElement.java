package com.vlingo.core.internal.questions.parser;

import com.vlingo.core.internal.questions.Answer.Section;
import com.vlingo.core.internal.questions.Answer.Subsection;
import java.util.ArrayList;

public class SectionElement extends ResponseElement
  implements Answer.Section
{
  private ArrayList<SubsectionElement> mSubsections = new ArrayList();

  public SectionElement(String paramString)
  {
    super(paramString);
  }

  public void add(SubsectionElement paramSubsectionElement)
  {
    this.mSubsections.add(paramSubsectionElement);
  }

  public String getName()
  {
    return getAttribute("Name");
  }

  public Answer.Subsection[] getSubsections()
  {
    return (Answer.Subsection[])this.mSubsections.toArray(new Answer.Subsection[0]);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.questions.parser.SectionElement
 * JD-Core Version:    0.6.0
 */