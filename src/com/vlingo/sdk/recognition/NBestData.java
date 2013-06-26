package com.vlingo.sdk.recognition;

import com.vlingo.sdk.internal.recognizer.results.RecChoice;
import com.vlingo.sdk.internal.recognizer.results.RecNBest;
import com.vlingo.sdk.internal.recognizer.results.RecResults;
import java.util.ArrayList;
import java.util.List;

public class NBestData
{
  private static final String WORD_SEPARATORS = ". ,;:!?\n()[]*&@{}/<>_+=|\"";
  private RecResults mUttResults;

  public NBestData(RecResults paramRecResults)
  {
    this.mUttResults = paramRecResults;
  }

  public List<String> getWordChoices(int paramInt)
  {
    RecNBest localRecNBest = this.mUttResults.getWord(paramInt);
    ArrayList localArrayList = new ArrayList();
    RecChoice[] arrayOfRecChoice = localRecNBest.iChoices;
    if (arrayOfRecChoice.length > 0)
      for (int i = 0; i < arrayOfRecChoice.length; i++)
        localArrayList.add(arrayOfRecChoice[i].originalChoice);
    return localArrayList;
  }

  public List<String> getWordChoices(String paramString, int paramInt1, int paramInt2)
  {
    String str = paramString.substring(paramInt1, paramInt2);
    return this.mUttResults.getNBestForWord(str);
  }

  public List<String> getWords()
  {
    throw new UnsupportedOperationException();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.NBestData
 * JD-Core Version:    0.6.0
 */