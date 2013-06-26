package com.vlingo.core.internal.questions.parser;

public class ServerResponse
{
  private String mAnswer;
  private String mQuestion;
  private String mXML;

  private String paran(String paramString)
  {
    return "(" + paramString + ")";
  }

  private String quoted(String paramString)
  {
    return "\"" + paramString + "\"";
  }

  public String getAnswer()
  {
    return this.mAnswer;
  }

  public String getQuestion()
  {
    return this.mQuestion;
  }

  public String getXML()
  {
    return this.mXML;
  }

  public void setAnswer(String paramString)
  {
    this.mAnswer = paramString;
  }

  public void setQuestion(String paramString)
  {
    this.mQuestion = paramString;
  }

  public void setXML(String paramString)
  {
    this.mXML = paramString;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("\n");
    localStringBuilder.append("Question");
    localStringBuilder.append(paran(quoted(this.mQuestion)));
    localStringBuilder.append("\n");
    localStringBuilder.append("Answer");
    localStringBuilder.append(paran(quoted(this.mAnswer)));
    localStringBuilder.append("\n");
    localStringBuilder.append("XML");
    localStringBuilder.append(paran(quoted(this.mXML)));
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.questions.parser.ServerResponse
 * JD-Core Version:    0.6.0
 */