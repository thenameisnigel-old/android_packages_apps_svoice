package com.vlingo.sdk.internal.recognizer.results;

import com.vlingo.sdk.internal.vlservice.response.VLResponseParser;
import com.vlingo.sdk.internal.vlservice.response.VLResponseSectionParser;
import com.vlingo.sdk.internal.xml.XmlAttributes;

public class TaggedResultsParser extends VLResponseSectionParser
{
  private final int ATTRIBUTE_C;
  private final int ATTRIBUTE_CA;
  private final int ATTRIBUTE_CF;
  private final int ATTRIBUTE_GUTTID;
  private final int ATTRIBUTE_ID;
  private final int ATTRIBUTE_N;
  private final int ATTRIBUTE_NAME;
  private final int ATTRIBUTE_NS;
  private final int ATTRIBUTE_NSD;
  private final int ATTRIBUTE_R;
  private final int ATTRIBUTE_TYPE;
  private final int RESULT_ALT;
  private final int RESULT_CHOICE;
  private final int RESULT_PARSE_GRP;
  private final int RESULT_RECOGNITION;
  private final int RESULT_TAG;
  private final int RESULT_TAG_CHOICE;
  private final int RESULT_TAG_LIST;
  private final int RESULT_UTT_LIST;
  private final int RESULT_WORD;
  private final int RESULT_WORD_LIST;
  private int choiceCount = 0;
  private RecNBest curParsedWord;
  private String guttid = null;
  private RecResults recResults = null;
  SRResponseParser srResultsParser;
  private TaggedResults tagResults = null;
  private int wordCount = 0;

  public TaggedResultsParser(SRResponseParser paramSRResponseParser)
  {
    super(paramSRResponseParser.parser);
    this.srResultsParser = paramSRResponseParser;
    this.RESULT_RECOGNITION = paramSRResponseParser.registerElement("Recognition");
    this.RESULT_ALT = paramSRResponseParser.registerElement("Alternates");
    this.RESULT_UTT_LIST = paramSRResponseParser.registerElement("UL");
    this.RESULT_WORD_LIST = paramSRResponseParser.registerElement("WL");
    this.RESULT_TAG_LIST = paramSRResponseParser.registerElement("T");
    this.RESULT_WORD = paramSRResponseParser.registerElement("w");
    this.RESULT_CHOICE = paramSRResponseParser.registerElement("c");
    this.RESULT_PARSE_GRP = paramSRResponseParser.registerElement("pg");
    this.RESULT_TAG = paramSRResponseParser.registerElement("tag");
    this.RESULT_TAG_CHOICE = paramSRResponseParser.registerElement("tl");
    this.ATTRIBUTE_GUTTID = paramSRResponseParser.registerAttribute("guttid");
    this.ATTRIBUTE_N = paramSRResponseParser.registerAttribute("n");
    this.ATTRIBUTE_R = paramSRResponseParser.registerAttribute("r");
    this.ATTRIBUTE_ID = paramSRResponseParser.registerAttribute("id");
    this.ATTRIBUTE_NS = paramSRResponseParser.registerAttribute("ns");
    this.ATTRIBUTE_NSD = paramSRResponseParser.registerAttribute("nsd");
    this.ATTRIBUTE_C = paramSRResponseParser.registerAttribute("c");
    this.ATTRIBUTE_CF = paramSRResponseParser.registerAttribute("cf");
    this.ATTRIBUTE_TYPE = paramSRResponseParser.registerAttribute("t");
    this.ATTRIBUTE_NAME = paramSRResponseParser.registerAttribute("nm");
    this.ATTRIBUTE_CA = paramSRResponseParser.registerAttribute("ca");
  }

  private void handleChoiceInUL(XmlAttributes paramXmlAttributes, char[] paramArrayOfChar)
  {
    if (paramXmlAttributes != null)
    {
      float f = parseFloat(paramXmlAttributes.lookup(this.ATTRIBUTE_C), -1.0F);
      String str = paramXmlAttributes.lookup(this.ATTRIBUTE_CF);
      int i = parseInt(paramXmlAttributes.lookup(this.ATTRIBUTE_N), 0);
      if (i > 0)
      {
        this.recResults.uttList[this.choiceCount] = new RecNBest[i];
        this.recResults.uttListConf[this.choiceCount] = f;
        String[] arrayOfString = this.recResults.uttListCannonical;
        if ((arrayOfString != null) && (str != null) && (arrayOfString[this.choiceCount] == null))
          arrayOfString[this.choiceCount] = str;
      }
      this.wordCount = 0;
    }
  }

  private void handleChoiceInWL(XmlAttributes paramXmlAttributes, char[] paramArrayOfChar)
  {
    String str1;
    int i;
    boolean bool1;
    boolean bool3;
    int j;
    label32: int k;
    if (paramArrayOfChar != null)
    {
      str1 = new String(paramArrayOfChar);
      i = 1;
      bool1 = false;
      bool2 = false;
      bool3 = false;
      if (paramXmlAttributes == null)
        break label213;
      j = 0;
      if (j >= paramXmlAttributes.getLength())
        break label213;
      k = paramXmlAttributes.getType(j);
      if (this.ATTRIBUTE_CA != k)
        break label102;
      String str4 = paramXmlAttributes.getValue(j);
      if ((str4 == null) || (!str4.equals("t")))
        break label96;
      bool3 = true;
    }
    label96: label102: label125: 
    do
    {
      while (true)
      {
        j++;
        break label32;
        str1 = "(null)";
        break;
        bool3 = false;
        continue;
        if (this.ATTRIBUTE_R != k)
          break label125;
        i = Integer.parseInt(paramXmlAttributes.getValue(j));
      }
      if (this.ATTRIBUTE_NS != k)
        continue;
      String str3 = paramXmlAttributes.getValue(j);
      if ((str3 != null) && (str3.equals("t")));
      for (bool1 = true; ; bool1 = false)
        break;
    }
    while (this.ATTRIBUTE_NSD != k);
    String str2 = paramXmlAttributes.getValue(j);
    if ((str2 != null) && (str2.equals("t")));
    for (boolean bool2 = true; ; bool2 = false)
      break;
    label213: RecChoice localRecChoice = this.curParsedWord.addResultsFromString(str1, i);
    if (localRecChoice != null)
    {
      localRecChoice.noSpace = bool1;
      localRecChoice.noSpaceNumber = bool2;
      localRecChoice.capitalized = bool3;
    }
  }

  private void handleWordInUL(XmlAttributes paramXmlAttributes, char[] paramArrayOfChar)
  {
    String str1;
    int i;
    boolean bool1;
    boolean bool3;
    int k;
    label33: int m;
    if (paramArrayOfChar != null)
    {
      str1 = new String(paramArrayOfChar);
      i = -1;
      bool1 = false;
      bool2 = false;
      bool3 = false;
      if (paramXmlAttributes == null)
        break label214;
      k = 0;
      if (k >= paramXmlAttributes.getLength())
        break label214;
      m = paramXmlAttributes.getType(k);
      if (this.ATTRIBUTE_CA != m)
        break label103;
      String str4 = paramXmlAttributes.getValue(k);
      if ((str4 == null) || (!str4.equals("t")))
        break label97;
      bool3 = true;
    }
    label97: label103: label126: 
    do
    {
      while (true)
      {
        k++;
        break label33;
        str1 = "(null)";
        break;
        bool3 = false;
        continue;
        if (this.ATTRIBUTE_ID != m)
          break label126;
        i = Integer.parseInt(paramXmlAttributes.getValue(k));
      }
      if (this.ATTRIBUTE_NS != m)
        continue;
      String str3 = paramXmlAttributes.getValue(k);
      if ((str3 != null) && (str3.equals("t")));
      for (bool1 = true; ; bool1 = false)
        break;
    }
    while (this.ATTRIBUTE_NSD != m);
    String str2 = paramXmlAttributes.getValue(k);
    if ((str2 != null) && (str2.equals("t")));
    for (boolean bool2 = true; ; bool2 = false)
      break;
    label214: RecNBest localRecNBest1 = new RecNBest(str1);
    localRecNBest1.noSpace = bool1;
    localRecNBest1.noSpaceNumber = bool2;
    localRecNBest1.capitalized = bool3;
    if ((i >= 0) && (i <= this.recResults.wordList.length))
    {
      localRecNBest1.id = i;
      RecNBest localRecNBest2 = this.recResults.wordList[i];
      if (localRecNBest2 != null)
      {
        localRecNBest1.iChoices = localRecNBest2.iChoices;
        localRecNBest1.iN = localRecNBest2.iN;
        localRecNBest1.adjustChoiceIndex();
      }
    }
    if (this.choiceCount == 0)
      this.recResults.addNBest(this.wordCount, localRecNBest1.copy());
    RecNBest[][] arrayOfRecNBest = this.recResults.uttList;
    if (this.wordCount < arrayOfRecNBest[this.choiceCount].length)
    {
      RecNBest[] arrayOfRecNBest1 = arrayOfRecNBest[this.choiceCount];
      int j = this.wordCount;
      this.wordCount = (j + 1);
      arrayOfRecNBest1[j] = localRecNBest1;
    }
  }

  private void handleWordInWL(XmlAttributes paramXmlAttributes, char[] paramArrayOfChar)
  {
    if (paramXmlAttributes != null)
    {
      int i = 0;
      int j = -1;
      int k = 0;
      if (k < paramXmlAttributes.getLength())
      {
        int m = paramXmlAttributes.getType(k);
        if (this.ATTRIBUTE_N == m)
          i = Integer.parseInt(paramXmlAttributes.getValue(k));
        while (true)
        {
          k++;
          break;
          if (this.ATTRIBUTE_ID != m)
            continue;
          j = Integer.parseInt(paramXmlAttributes.getValue(k));
        }
      }
      this.curParsedWord = new RecNBest(i);
      if ((j >= 0) && (j <= this.recResults.wordList.length))
      {
        this.curParsedWord.id = j;
        this.recResults.wordList[j] = this.curParsedWord;
      }
    }
  }

  private void newRecResults()
  {
    this.curParsedWord = null;
    this.choiceCount = 0;
    this.wordCount = 0;
    if (this.recResults != null)
    {
      RecNBest[] arrayOfRecNBest = this.recResults.wordList;
      this.recResults = new RecResults(10, this.guttid);
      this.recResults.wordList = arrayOfRecNBest;
    }
  }

  private static float parseFloat(String paramString, float paramFloat)
  {
    float f = paramFloat;
    if (paramString != null)
      f = Float.parseFloat(paramString);
    return f;
  }

  private static int parseInt(String paramString, int paramInt)
  {
    int i = paramInt;
    if (paramString != null)
      i = Integer.parseInt(paramString);
    return i;
  }

  public void beginElement(int paramInt1, XmlAttributes paramXmlAttributes, char[] paramArrayOfChar, int paramInt2)
  {
    if (paramInt1 == this.RESULT_RECOGNITION)
    {
      this.recResults = null;
      this.choiceCount = 0;
      this.wordCount = 0;
      this.curParsedWord = null;
      this.guttid = paramXmlAttributes.lookup(this.ATTRIBUTE_GUTTID);
      this.srResultsParser.getResponse().setGUttId(this.guttid);
    }
    while (true)
    {
      return;
      if (paramInt1 == this.RESULT_ALT)
      {
        this.recResults = null;
        this.tagResults = new TaggedResults(this.guttid);
        continue;
      }
      if (paramInt1 == this.RESULT_WORD_LIST)
      {
        if (paramXmlAttributes == null)
          continue;
        if (this.recResults == null)
          this.recResults = new RecResults(10, this.guttid);
        int i2 = parseInt(paramXmlAttributes.lookup(this.ATTRIBUTE_N), 0);
        if (i2 <= 0)
          continue;
        RecNBest[] arrayOfRecNBest = new RecNBest[i2];
        this.recResults.wordList = arrayOfRecNBest;
        for (int i3 = 0; i3 < i2; i3++)
          arrayOfRecNBest[i3] = null;
        continue;
      }
      if (paramInt1 == this.RESULT_UTT_LIST)
      {
        if (this.recResults == null)
          this.recResults = new RecResults(10, this.guttid);
        int n = parseInt(paramXmlAttributes.lookup(this.ATTRIBUTE_N), 0);
        if (n > 0)
        {
          RecNBest[][] arrayOfRecNBest;2 = new RecNBest[n][];
          float[] arrayOfFloat2 = new float[n];
          this.recResults.uttList = arrayOfRecNBest;2;
          this.recResults.uttListConf = arrayOfFloat2;
          for (int i1 = 0; i1 < n; i1++)
            arrayOfRecNBest;2[i1] = null;
        }
        this.recResults.choiceIndex = 0;
        this.curParsedWord = null;
        this.choiceCount = 0;
        continue;
      }
      if (paramInt1 == this.RESULT_TAG_LIST)
      {
        int m = parseInt(paramXmlAttributes.lookup(this.ATTRIBUTE_N), 0);
        this.tagResults.onTagList(m);
        continue;
      }
      if (paramInt1 == this.RESULT_PARSE_GRP)
      {
        float f = parseFloat(paramXmlAttributes.lookup(this.ATTRIBUTE_C), -1.0F);
        String str3 = paramXmlAttributes.lookup(this.ATTRIBUTE_TYPE);
        int k = parseInt(paramXmlAttributes.lookup(this.ATTRIBUTE_N), 0);
        this.tagResults.onParseGroup(f, str3, k);
        continue;
      }
      if (paramInt1 == this.RESULT_TAG)
      {
        newRecResults();
        String str1 = paramXmlAttributes.lookup(this.ATTRIBUTE_NAME);
        String str2 = paramXmlAttributes.lookup(this.ATTRIBUTE_CF);
        int i = parseInt(paramXmlAttributes.lookup(this.ATTRIBUTE_N), 0);
        if (i > 0)
        {
          RecNBest[][] arrayOfRecNBest;1 = new RecNBest[i][];
          float[] arrayOfFloat1 = new float[i];
          String[] arrayOfString = new String[i];
          this.recResults.uttList = arrayOfRecNBest;1;
          this.recResults.uttListConf = arrayOfFloat1;
          this.recResults.uttListCannonical = arrayOfString;
          for (int j = 0; j < i; j++)
            arrayOfRecNBest;1[j] = null;
          if (str2 != null)
            this.recResults.uttListCannonical[0] = str2;
        }
        this.tagResults.onTag(str1);
        continue;
      }
      if (paramInt1 == this.RESULT_WORD)
      {
        this.curParsedWord = null;
        if (this.recResults == null)
          continue;
        if (this.recResults.uttList != null)
        {
          handleWordInUL(paramXmlAttributes, paramArrayOfChar);
          continue;
        }
        handleWordInWL(paramXmlAttributes, paramArrayOfChar);
        continue;
      }
      if (((paramInt1 != this.RESULT_TAG_CHOICE) && (paramInt1 != this.RESULT_CHOICE)) || (this.recResults == null))
        continue;
      if (this.recResults.uttList != null)
      {
        handleChoiceInUL(paramXmlAttributes, paramArrayOfChar);
        continue;
      }
      if (this.curParsedWord == null)
        continue;
      handleChoiceInWL(paramXmlAttributes, paramArrayOfChar);
    }
  }

  public void endElement(int paramInt1, int paramInt2)
  {
    if (this.RESULT_ALT == paramInt1)
    {
      this.srResultsParser.getResponse().setTaggedResults(this.tagResults);
      this.responseParser.onSectionComplete();
    }
    while (true)
    {
      return;
      if (this.RESULT_UTT_LIST == paramInt1)
      {
        this.tagResults.onUttResults(this.recResults);
        continue;
      }
      if (this.RESULT_TAG == paramInt1)
      {
        this.tagResults.onTagResults(this.recResults);
        continue;
      }
      if (((this.RESULT_CHOICE != paramInt1) && (this.RESULT_TAG_CHOICE != paramInt1)) || (this.recResults.uttList == null))
        continue;
      this.choiceCount = (1 + this.choiceCount);
    }
  }

  public boolean handlesElement(int paramInt)
  {
    if (this.RESULT_RECOGNITION == paramInt);
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.results.TaggedResultsParser
 * JD-Core Version:    0.6.0
 */