package com.vlingo.sdk.internal;

import com.vlingo.sdk.internal.recognizer.results.TaggedResults;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class NBestManager
{
  private static final int MAX_RESULTS = 5;
  private static final String WORD_SEPARATORS = ". ,;:!?\n()[]*&@{}/<>_+=|\"";
  private ArrayList<TaggedResults> previousResults = new ArrayList();

  private String getCurrentWord(char[] paramArrayOfChar, int paramInt)
  {
    String str;
    if (isWordSeparator(paramArrayOfChar[paramInt]))
    {
      str = new String(paramArrayOfChar, paramInt, 1);
      return str;
    }
    int i = 0;
    int j = paramArrayOfChar.length;
    int k = paramInt;
    label32: if (k < paramArrayOfChar.length)
    {
      if (!isWordSeparator(paramArrayOfChar[k]))
        break label96;
      j = k;
    }
    for (int m = paramInt; ; m--)
    {
      if (m >= 0)
      {
        if (!isWordSeparator(paramArrayOfChar[m]))
          continue;
        i = m + 1;
      }
      str = new String(paramArrayOfChar, i, j - i);
      break;
      label96: k++;
      break label32;
    }
  }

  private int getWordsAfter(String[] paramArrayOfString, char[] paramArrayOfChar, int paramInt)
  {
    int i = 0;
    int j = paramInt;
    int k = paramArrayOfChar.length;
    while ((j < k) && (!isWordSeparator(paramArrayOfChar[j])))
      j++;
    while (true)
    {
      int n = j;
      int m;
      if (m < n)
      {
        int i1 = i + 1;
        paramArrayOfString[i] = new String(paramArrayOfChar, m, n - m);
        i = i1;
      }
      if (i < paramArrayOfString.length)
      {
        while ((j < k) && (isWordSeparator(paramArrayOfChar[j])))
          j++;
        m = j;
        if (m < k);
      }
      else
      {
        return i;
      }
      while ((j < k) && (!isWordSeparator(paramArrayOfChar[j])))
        j++;
    }
  }

  private int getWordsBefore(String[] paramArrayOfString, char[] paramArrayOfChar, int paramInt)
  {
    int i = 0;
    for (int j = paramInt; (j >= 0) && (!isWordSeparator(paramArrayOfChar[j])); j--);
    while (true)
    {
      int m = j + 1;
      int k;
      if (m < k)
      {
        int n = i + 1;
        paramArrayOfString[i] = new String(paramArrayOfChar, m, k - m);
        i = n;
      }
      if (i < paramArrayOfString.length)
      {
        while ((j >= 0) && (isWordSeparator(paramArrayOfChar[j])))
          j--;
        k = j + 1;
        if (k > 0);
      }
      else
      {
        return i;
      }
      while ((j >= 0) && (!isWordSeparator(paramArrayOfChar[j])))
        j--;
    }
  }

  private static boolean isWordSeparator(char paramChar)
  {
    return ". ,;:!?\n()[]*&@{}/<>_+=|\"".contains(String.valueOf(paramChar));
  }

  private int moveCursorIfInSpace(char[] paramArrayOfChar, int paramInt)
  {
    int i = -1;
    if ((paramInt < 0) || (paramInt >= paramArrayOfChar.length));
    while (true)
    {
      return i;
      if (Character.isSpace(paramArrayOfChar[paramInt]))
      {
        if ((paramInt > 0) && (!Character.isSpace(paramArrayOfChar[(paramInt - 1)])))
        {
          i = paramInt - 1;
          continue;
        }
        paramInt++;
      }
      if ((paramInt >= paramArrayOfChar.length) || (Character.isSpace(paramArrayOfChar[paramInt])))
        continue;
      i = paramInt;
    }
  }

  public List<String> getNBestForWord(String paramString, int paramInt)
  {
    char[] arrayOfChar = paramString.toCharArray();
    int i = moveCursorIfInSpace(arrayOfChar, paramInt);
    LinkedList localLinkedList;
    if (i < 0)
      localLinkedList = null;
    while (true)
    {
      return localLinkedList;
      String str = getCurrentWord(arrayOfChar, i);
      if (str == null)
      {
        localLinkedList = null;
        continue;
      }
      String[] arrayOfString1 = new String[3];
      String[] arrayOfString2 = new String[3];
      int j = getWordsBefore(arrayOfString1, arrayOfChar, i);
      int k = getWordsAfter(arrayOfString2, arrayOfChar, i);
      int m = this.previousResults.size();
      int n = 0;
      localLinkedList = new LinkedList();
      for (int i1 = 0; i1 < m; i1++)
        n = ((TaggedResults)this.previousResults.get(i1)).getNBestForWordIfBetterMatch(localLinkedList, str, arrayOfString1, j, arrayOfString2, k, n);
      if (!localLinkedList.isEmpty())
        continue;
      localLinkedList = null;
    }
  }

  public Vector<String> getNBestForWord(String paramString)
  {
    int i = this.previousResults.size();
    int j = 0;
    Vector localVector;
    if (j < i)
    {
      localVector = ((TaggedResults)this.previousResults.get(j)).getNBestForWord(paramString);
      if (localVector == null);
    }
    while (true)
    {
      return localVector;
      j++;
      break;
      localVector = null;
    }
  }

  public void registerResults(TaggedResults paramTaggedResults)
  {
    this.previousResults.add(0, paramTaggedResults);
    while (this.previousResults.size() > 5)
      this.previousResults.remove(-1 + this.previousResults.size());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.NBestManager
 * JD-Core Version:    0.6.0
 */