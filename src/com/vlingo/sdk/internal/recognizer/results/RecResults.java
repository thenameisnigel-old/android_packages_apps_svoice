package com.vlingo.sdk.internal.recognizer.results;

import java.util.List;
import java.util.Vector;

public class RecResults
{
  static final char END_DELIM = '}';
  static final char START_DELIM = '{';
  public int choiceIndex;
  public String guttid;
  public RecNBest iMostRecentRecEnd = null;
  public RecNBest iMostRecentRecStart = null;
  public int iNumWords = 0;
  private RecNBest[] iResults;
  private String mostRecentString = null;
  private int totalStringHash = 0;
  public int upDownArrowWordIndex = -1;
  public RecNBest[][] uttList = (RecNBest[][])null;
  public String[] uttListCannonical = null;
  public float[] uttListConf = null;
  public RecNBest[] wordList = null;

  public RecResults(int paramInt)
  {
    this(paramInt, null);
  }

  public RecResults(int paramInt, String paramString)
  {
    this.iResults = new RecNBest[paramInt];
    this.guttid = paramString;
  }

  private int countMatchingContextWords(int paramInt1, String[] paramArrayOfString1, int paramInt2, String[] paramArrayOfString2, int paramInt3)
  {
    int i = 0;
    for (int j = 0; j < paramInt2; j++)
    {
      if (!hasMatchingWord(paramArrayOfString1[j], paramInt1 - (paramInt2 + 2), paramInt1))
        continue;
      i++;
    }
    for (int k = 0; k < paramInt3; k++)
    {
      if (!hasMatchingWord(paramArrayOfString2[k], paramInt1 + 1, paramInt1 + (paramInt3 + 2)))
        continue;
      i++;
    }
    return i;
  }

  private void growResults(int paramInt)
  {
    RecNBest[] arrayOfRecNBest = new RecNBest[paramInt];
    for (int i = 0; i < this.iNumWords; i++)
      arrayOfRecNBest[i] = this.iResults[i];
    this.iResults = null;
    this.iResults = arrayOfRecNBest;
  }

  private boolean hasMatchingWord(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
      paramInt1 = 0;
    if (paramInt2 > this.iNumWords)
      paramInt2 = this.iNumWords;
    int i = paramInt1;
    int k;
    if (i < paramInt2)
    {
      RecChoice[] arrayOfRecChoice = this.iResults[i].iChoices;
      k = 0;
      label43: if (k < arrayOfRecChoice.length)
        if (!paramString.equalsIgnoreCase(arrayOfRecChoice[k].originalChoice));
    }
    for (int j = 1; ; j = 0)
    {
      return j;
      k++;
      break label43;
      i++;
      break;
    }
  }

  private void moveWords(int paramInt1, int paramInt2)
  {
    int i = paramInt2 + this.iNumWords;
    if (i > this.iResults.length)
      growResults(i * 2);
    for (int j = i - 1; j >= paramInt1 + paramInt2; j--)
      this.iResults[j] = this.iResults[(j - paramInt2)];
    this.iNumWords = (paramInt2 + this.iNumWords);
  }

  public void addNBest(int paramInt, RecNBest paramRecNBest)
  {
    if (1 + this.iNumWords >= this.iResults.length)
      growResults(2 * this.iNumWords);
    moveWords(paramInt, 1);
    this.iResults[paramInt] = paramRecNBest;
  }

  public void addWord(int paramInt, String paramString)
  {
    if (1 + this.iNumWords >= this.iResults.length)
      growResults(2 * this.iNumWords);
    moveWords(paramInt, 1);
    this.iResults[paramInt] = new RecNBest(paramString);
  }

  public void append(RecNBest paramRecNBest)
  {
    if (this.iNumWords >= -1 + this.iResults.length)
      growResults(2 * this.iNumWords);
    RecNBest[] arrayOfRecNBest = this.iResults;
    int i = this.iNumWords;
    this.iNumWords = (i + 1);
    arrayOfRecNBest[i] = paramRecNBest;
  }

  public void append(String paramString)
  {
    if (this.iNumWords >= -1 + this.iResults.length)
      growResults(2 * this.iNumWords);
    RecNBest[] arrayOfRecNBest = this.iResults;
    int i = this.iNumWords;
    this.iNumWords = (i + 1);
    arrayOfRecNBest[i] = new RecNBest(paramString);
  }

  public void clear()
  {
    for (int i = 0; i < this.iNumWords; i++)
      this.iResults[i] = null;
    this.iNumWords = 0;
  }

  public void clearMostRecentRecognition()
  {
    this.iMostRecentRecStart = null;
    this.iMostRecentRecEnd = null;
  }

  public void clearPositions(int paramInt)
  {
    for (int i = paramInt; i < this.iNumWords; i++)
    {
      if (this.iResults[i] == null)
        continue;
      this.iResults[i].clearPosition();
    }
  }

  public void deleteWord(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this.iNumWords))
    {
      for (int i = paramInt; i < -1 + this.iNumWords; i++)
        this.iResults[i] = this.iResults[(i + 1)];
      this.iNumWords = (-1 + this.iNumWords);
    }
  }

  public int findNumChoice(int paramInt)
  {
    return this.iResults[paramInt].iN;
  }

  public int getChars(char[] paramArrayOfChar)
  {
    int i = 0;
    for (int j = 0; j < this.iNumWords; j++)
    {
      RecNBest localRecNBest = this.iResults[j];
      String str = localRecNBest.iSelectedWord;
      int k = str.length();
      str.getChars(0, k, paramArrayOfChar, i);
      i += k;
      if ((j >= -1 + this.iNumWords) || (!this.iResults[(j + 1)].needsSpace(localRecNBest)))
        continue;
      int m = i + 1;
      paramArrayOfChar[i] = ' ';
      i = m;
    }
    return i;
  }

  public Vector<String> getNBestForWord(String paramString)
  {
    Vector localVector = new Vector();
    for (int i = 0; i < this.iNumWords; i++)
    {
      RecChoice[] arrayOfRecChoice = this.iResults[i].iChoices;
      if (arrayOfRecChoice.length <= 0)
        continue;
      for (int j = 0; j < arrayOfRecChoice.length; j++)
      {
        if (!paramString.equalsIgnoreCase(arrayOfRecChoice[j].originalChoice))
          continue;
        for (int k = 0; k < arrayOfRecChoice.length; k++)
          localVector.add(arrayOfRecChoice[k].originalChoice);
      }
    }
    return localVector;
  }

  public int getNBestForWordIfBetterMatch(List<String> paramList, String paramString, String[] paramArrayOfString1, int paramInt1, String[] paramArrayOfString2, int paramInt2, int paramInt3)
  {
    int i = -1;
    int j = paramInt3;
    int k = paramList.size();
    for (int m = 0; m < this.iNumWords; m++)
    {
      RecChoice[] arrayOfRecChoice2 = this.iResults[m].iChoices;
      for (int i1 = 0; i1 < arrayOfRecChoice2.length; i1++)
      {
        if (!paramString.equalsIgnoreCase(arrayOfRecChoice2[i1].originalChoice))
          continue;
        int i2 = 1 + countMatchingContextWords(m, paramArrayOfString1, paramInt1, paramArrayOfString2, paramInt2);
        if (((i2 <= j) && ((k >= 2) || (arrayOfRecChoice2.length <= 1))) || ((arrayOfRecChoice2.length <= 1) && (j != 0)))
          continue;
        j = i2;
        i = m;
        k = arrayOfRecChoice2.length;
      }
    }
    if (i >= 0)
    {
      paramList.clear();
      RecChoice[] arrayOfRecChoice1 = this.iResults[i].iChoices;
      for (int n = 0; n < arrayOfRecChoice1.length; n++)
        paramList.add(arrayOfRecChoice1[n].originalChoice);
    }
    return j;
  }

  public String[] getPhraseLevel()
  {
    String[] arrayOfString1;
    StringBuffer localStringBuffer;
    int i;
    RecNBest[] arrayOfRecNBest1;
    if ((this.uttList != null) && (this.uttList.length > 0))
    {
      RecNBest[][] arrayOfRecNBest = this.uttList;
      arrayOfString1 = new String[arrayOfRecNBest.length];
      localStringBuffer = new StringBuffer();
      i = 0;
      if (i >= arrayOfRecNBest.length)
        break label151;
      localStringBuffer.setLength(0);
      arrayOfRecNBest1 = arrayOfRecNBest[i];
      if (arrayOfRecNBest1 != null);
    }
    label151: for (String[] arrayOfString2 = null; ; arrayOfString2 = arrayOfString1)
    {
      return arrayOfString2;
      if (arrayOfRecNBest1.length > 0)
      {
        localStringBuffer.append(arrayOfRecNBest1[0].getSelectedWord());
        for (int j = 1; j < arrayOfRecNBest1.length; j++)
        {
          localStringBuffer.append(" ");
          localStringBuffer.append(arrayOfRecNBest1[j].getSelectedWord());
        }
      }
      arrayOfString1[i] = localStringBuffer.toString();
      i++;
      break;
      arrayOfString1 = new String[1];
      arrayOfString1[0] = getString();
    }
  }

  public String getString()
  {
    int i = 0;
    String str;
    if (this.iNumWords == 0)
      str = "";
    while (true)
    {
      return str;
      for (int j = 0; j < this.iNumWords; j++)
        i += this.iResults[j].iSelectedWord.hashCode();
      if ((this.mostRecentString != null) && (i == this.totalStringHash))
      {
        str = this.mostRecentString;
        continue;
      }
      char[] arrayOfChar = new char[size()];
      this.mostRecentString = new String(arrayOfChar, 0, getChars(arrayOfChar));
      this.totalStringHash = i;
      str = this.mostRecentString;
    }
  }

  public RecNBest getWord(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this.iNumWords));
    for (RecNBest localRecNBest = this.iResults[paramInt]; ; localRecNBest = null)
      return localRecNBest;
  }

  public int getWordLength(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this.iNumWords));
    for (int i = this.iResults[paramInt].length(); ; i = 0)
      return i;
  }

  public int indexOf(RecNBest paramRecNBest)
  {
    int i = 0;
    if (i < this.iNumWords)
      if (this.iResults[i] != paramRecNBest);
    while (true)
    {
      return i;
      i++;
      break;
      i = -1;
    }
  }

  public int insert(RecResults paramRecResults, int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0);
    int j;
    for (int i = paramInt1; ; i = paramInt1 + 1)
    {
      if (i > this.iNumWords)
        i = this.iNumWords;
      j = paramRecResults.iNumWords;
      if (j + this.iNumWords >= this.iResults.length)
        growResults(j + 2 * this.iNumWords);
      moveWords(i, j);
      for (int k = 0; k < j; k++)
        this.iResults[(k + i)] = paramRecResults.iResults[k];
    }
    return j;
  }

  public int removeRangeOfWords(RecNBest paramRecNBest1, RecNBest paramRecNBest2, int paramInt)
  {
    int i = -1;
    int j = -1;
    int k = 0;
    while (true)
    {
      int n;
      if (k < this.iNumWords)
      {
        if (this.iResults[k] == paramRecNBest1)
          i = k;
        if (this.iResults[k] == paramRecNBest2)
          j = k;
      }
      else
      {
        if ((i < 0) || (j < 0))
          break;
        n = j + 1 - i;
        this.iNumWords -= n;
        for (int i1 = i; i1 < this.iNumWords; i1++)
          this.iResults[i1] = this.iResults[(i1 + n)];
      }
      k++;
      continue;
      for (int i2 = this.iNumWords; i2 < n + this.iNumWords; i2++)
        this.iResults[i2] = null;
    }
    for (int m = i; ; m = -1)
      return m;
  }

  public int size()
  {
    int i = 0;
    for (int j = 0; j < this.iNumWords; j++)
    {
      RecNBest localRecNBest = this.iResults[j];
      i += localRecNBest.iSelectedWord.length();
      if ((j >= -1 + this.iNumWords) || (!this.iResults[(j + 1)].needsSpace(localRecNBest)))
        continue;
      i++;
    }
    return i;
  }

  public int size(int paramInt1, int paramInt2)
  {
    int i = 0;
    for (int j = paramInt1; (j < paramInt2) && (j < this.iNumWords); j++)
    {
      RecNBest localRecNBest = this.iResults[j];
      i += localRecNBest.iSelectedWord.length();
      if ((j >= -1 + this.iNumWords) || (j >= paramInt2 - 1) || (!this.iResults[(j + 1)].needsSpace(localRecNBest)))
        continue;
      i++;
    }
    return i;
  }

  public String toString()
  {
    return getString();
  }

  public void updateSentenceCap()
  {
    int i = 0;
    int j = 0;
    if (j < this.iNumWords)
    {
      RecNBest localRecNBest = getWord(j);
      if (localRecNBest.iSelectedWord.trim().length() == 0);
      while (true)
      {
        j++;
        break;
        if (localRecNBest.iSelectedWord.equals("."))
        {
          i = 1;
          continue;
        }
        if (i == 0)
          continue;
        if ((!localRecNBest.capitalized) && (Character.isLowerCase(localRecNBest.iSelectedWord.charAt(0))))
        {
          String str = localRecNBest.iSelectedWord.substring(0, 1).toUpperCase();
          if (localRecNBest.iSelectedWord.length() > 1)
            str = str + localRecNBest.iSelectedWord.substring(1);
          localRecNBest.iSelectedWord = str;
        }
        i = 0;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.results.RecResults
 * JD-Core Version:    0.6.0
 */