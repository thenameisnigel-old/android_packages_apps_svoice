package com.vlingo.sdk.internal.recognizer.results;

public class RecNBest
{
  public boolean capitalized = false;
  public int choiceIndex = 0;
  public RecChoice[] iChoices;
  public int iN;
  public String iSelectedWord;
  public int iWidth;
  public int iXPosition;
  public int iYPosition;
  public int id = -1;
  public boolean noSpace = false;
  public boolean noSpaceNumber = false;
  public int numAdded = 1;
  public int numReplaced = 1;
  public RecNBest[] replacedNBests = null;

  public RecNBest()
  {
    this.iChoices = null;
    this.iN = 0;
    this.iXPosition = -1;
    this.iYPosition = -1;
    this.iWidth = -1;
  }

  public RecNBest(int paramInt)
  {
    this.iChoices = new RecChoice[paramInt];
    this.iSelectedWord = "";
    this.iN = 0;
    this.iXPosition = -1;
    this.iYPosition = -1;
    this.iWidth = -1;
  }

  public RecNBest(String paramString)
  {
    this.iChoices = null;
    this.iSelectedWord = paramString;
    this.iN = 0;
    this.iXPosition = -1;
    this.iYPosition = -1;
    this.iWidth = -1;
  }

  private boolean isNumber(String paramString)
  {
    int i = 0;
    if (i < paramString.length())
    {
      char c = paramString.charAt(i);
      if ((Character.isDigit(c)) || (c == ','));
    }
    for (int j = 0; ; j = 1)
    {
      return j;
      i++;
      break;
    }
  }

  public RecChoice addResultsFromString(String paramString, int paramInt)
  {
    RecChoice localRecChoice;
    if (this.iN < this.iChoices.length)
    {
      localRecChoice = new RecChoice(paramString, paramInt);
      this.iChoices[this.iN] = localRecChoice;
      if (this.iN == 0)
        setSelectedWord(paramString);
      this.iN = (1 + this.iN);
    }
    while (true)
    {
      return localRecChoice;
      localRecChoice = null;
    }
  }

  public boolean adjustChoiceIndex()
  {
    int i = 1;
    int j = 0;
    if (j < this.iChoices.length)
      if ((this.iSelectedWord.equals(this.iChoices[j].words[0])) && (this.iChoices[j].words.length == i))
        this.choiceIndex = j;
    while (true)
    {
      return i;
      j++;
      break;
      for (int k = 0; ; k++)
      {
        if (k >= this.iChoices.length)
          break label107;
        if (!this.iSelectedWord.equals(this.iChoices[k].words[0]))
          continue;
        this.choiceIndex = k;
        break;
      }
      label107: i = 0;
    }
  }

  public void clearPosition()
  {
    this.iXPosition = -1;
    this.iYPosition = -1;
    this.iWidth = -1;
  }

  public RecNBest copy()
  {
    RecNBest localRecNBest = new RecNBest();
    localRecNBest.id = this.id;
    localRecNBest.iN = this.iN;
    localRecNBest.iSelectedWord = this.iSelectedWord;
    localRecNBest.iChoices = this.iChoices;
    localRecNBest.choiceIndex = this.choiceIndex;
    localRecNBest.clearPosition();
    localRecNBest.noSpace = this.noSpace;
    localRecNBest.noSpaceNumber = this.noSpaceNumber;
    localRecNBest.capitalized = this.capitalized;
    return localRecNBest;
  }

  public String getSelectedWord()
  {
    return this.iSelectedWord;
  }

  public boolean isPunctuation(char paramChar)
  {
    int i = 1;
    if (paramChar == '.');
    while (true)
    {
      return i;
      if ((paramChar == ',') || (paramChar == '!') || (paramChar == '?') || (paramChar == ':') || (paramChar == ';') || (paramChar == ')') || (paramChar == ']') || (paramChar == '}') || (paramChar == '-'))
        continue;
      i = 0;
    }
  }

  public int length()
  {
    if (this.iSelectedWord != null);
    for (int i = this.iSelectedWord.length(); ; i = 0)
      return i;
  }

  public boolean matches(String paramString, boolean paramBoolean)
  {
    if (paramBoolean);
    for (boolean bool = this.iSelectedWord.equalsIgnoreCase(paramString); ; bool = this.iSelectedWord.equals(paramString))
      return bool;
  }

  public boolean needsSpace(RecNBest paramRecNBest)
  {
    int i = 0;
    if (paramRecNBest == null);
    while (true)
    {
      return i;
      String str = paramRecNBest.iSelectedWord;
      if ((this.iSelectedWord.length() == 0) || (str.length() == 0))
      {
        i = 1;
        continue;
      }
      if (this.iChoices != null)
      {
        RecChoice localRecChoice = this.iChoices[this.choiceIndex];
        if ((localRecChoice.noSpace) || ((localRecChoice.noSpaceNumber) && (isNumber(str))))
          continue;
      }
      do
      {
        int j = str.charAt(-1 + str.length());
        char c = this.iSelectedWord.charAt(0);
        if ((j == 10) || (c == '\n') || (isPunctuation(c)))
          break;
        i = 1;
        break;
        if (this.noSpace)
          break;
      }
      while ((!this.noSpaceNumber) || (!isNumber(str)));
    }
  }

  public void setSelectedWord(String paramString)
  {
    this.iSelectedWord = paramString;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("<").append(this.iSelectedWord).append("|");
    if (this.iChoices != null);
    for (int i = this.iChoices.length; ; i = 0)
      return i + ">";
  }

  public int wordLength()
  {
    return this.iSelectedWord.length();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.results.RecNBest
 * JD-Core Version:    0.6.0
 */