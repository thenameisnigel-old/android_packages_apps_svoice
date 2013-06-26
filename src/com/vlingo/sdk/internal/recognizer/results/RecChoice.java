package com.vlingo.sdk.internal.recognizer.results;

public class RecChoice
{
  public boolean capitalized = false;
  public boolean noSpace = false;
  public boolean noSpaceNumber = false;
  public int numAlign;
  public String originalChoice;
  public String[] words;

  public RecChoice(String paramString, int paramInt)
  {
    String[] arrayOfString = new String[10];
    this.originalChoice = paramString;
    int i = 0;
    int j = 0;
    int k = paramString.length();
    char[] arrayOfChar = paramString.toCharArray();
    int m = 0;
    if (j < k)
    {
      int i1 = j;
      label62: if (i1 < k)
      {
        if (arrayOfChar[i1] == ' ')
          m = i1;
      }
      else
      {
        if (i1 == k)
          m = k;
        if (m > j)
        {
          arrayOfString[i] = new String(arrayOfChar, j, m - j);
          i++;
        }
      }
      for (int i2 = m; ; i2++)
      {
        if ((i2 < k) && (arrayOfChar[i2] == ' '))
          continue;
        j = i2;
        break;
        i1++;
        break label62;
      }
    }
    this.words = new String[i];
    for (int n = 0; n < i; n++)
      this.words[n] = arrayOfString[n];
    this.numAlign = paramInt;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.results.RecChoice
 * JD-Core Version:    0.6.0
 */