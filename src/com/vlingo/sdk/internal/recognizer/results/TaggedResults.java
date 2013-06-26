package com.vlingo.sdk.internal.recognizer.results;

import java.util.List;
import java.util.Vector;

public class TaggedResults
{
  private static final boolean SHOW_DEBUG = true;
  private ParseGroup curParseGroup = null;
  private Tag curTag = null;
  private Vector<Object> groups = null;
  private String guttid;
  private RecResults uttResults = null;

  public TaggedResults(String paramString)
  {
    this.guttid = paramString;
  }

  private static final void collapseWords(StringBuffer paramStringBuffer, String[] paramArrayOfString)
  {
    if (paramArrayOfString.length > 0)
    {
      paramStringBuffer.append(paramArrayOfString[0].trim());
      for (int i = 1; i < paramArrayOfString.length; i++)
      {
        paramStringBuffer.append(" ");
        paramStringBuffer.append(paramArrayOfString[i].trim());
      }
    }
  }

  private static final void serializeParseGroups(StringBuffer paramStringBuffer, Vector<Object> paramVector)
  {
    if ((paramVector != null) && (!paramVector.isEmpty()))
    {
      int i = paramVector.size();
      paramStringBuffer.append("<T n=\"" + i + "\">");
      for (int j = 0; j < i; j++)
      {
        ParseGroup localParseGroup = (ParseGroup)paramVector.elementAt(j);
        paramStringBuffer.append("<pg t=\"" + localParseGroup.parseType + "\" c=\"" + localParseGroup.confidence + "\" n=\"" + localParseGroup.tags.size() + "\">");
        int k = localParseGroup.tags.size();
        for (int m = 0; m < k; m++)
        {
          Tag localTag = (Tag)localParseGroup.tags.elementAt(m);
          RecNBest[][] arrayOfRecNBest = localTag.tagResults.uttList;
          String[] arrayOfString = localTag.tagResults.uttListCannonical;
          paramStringBuffer.append("<tag n=\"" + arrayOfRecNBest.length + "\" ");
          paramStringBuffer.append("nm=\"" + localTag.name + "\">");
          for (int n = 0; n < arrayOfRecNBest.length; n++)
          {
            paramStringBuffer.append("<tl ");
            if ((arrayOfString != null) && (arrayOfString.length > n) && (arrayOfString[n] != null))
              paramStringBuffer.append("cf=\"" + arrayOfString[n] + "\" ");
            paramStringBuffer.append("n=\"" + arrayOfRecNBest[n].length + "\">");
            int i1 = 0;
            if (i1 < arrayOfRecNBest[n].length)
            {
              int i2 = arrayOfRecNBest[n][i1].id;
              if (i2 != -1)
                paramStringBuffer.append("<w id=\"" + i2 + "\"");
              while (true)
              {
                if (arrayOfRecNBest[n][i1].noSpace)
                  paramStringBuffer.append(" ns=\"t\"");
                if (arrayOfRecNBest[n][i1].noSpaceNumber)
                  paramStringBuffer.append(" nsd=\"t\"");
                paramStringBuffer.append(">");
                paramStringBuffer.append(arrayOfRecNBest[n][i1].iSelectedWord);
                paramStringBuffer.append("</w>");
                i1++;
                break;
                paramStringBuffer.append("<w");
              }
            }
            paramStringBuffer.append("</tl>");
          }
          paramStringBuffer.append("</tag>");
        }
        paramStringBuffer.append("</pg>");
      }
      paramStringBuffer.append("</T>");
    }
  }

  private static void serializeUttList(StringBuffer paramStringBuffer, RecResults paramRecResults)
  {
    RecNBest[][] arrayOfRecNBest = paramRecResults.uttList;
    float[] arrayOfFloat = paramRecResults.uttListConf;
    paramStringBuffer.append("<UL n=\"" + arrayOfRecNBest.length + "\">");
    for (int i = 0; i < arrayOfRecNBest.length; i++)
    {
      paramStringBuffer.append("<c ");
      if ((arrayOfFloat != null) && (i < arrayOfFloat.length))
        paramStringBuffer.append("c=\"" + arrayOfFloat[i] + "\" ");
      paramStringBuffer.append("n=\"" + arrayOfRecNBest[i].length + "\">");
      int j = 0;
      if (j < arrayOfRecNBest[i].length)
      {
        int k = arrayOfRecNBest[i][j].id;
        if (k != -1)
          paramStringBuffer.append("<w id=\"" + k + "\"");
        while (true)
        {
          if (arrayOfRecNBest[i][j].noSpace)
            paramStringBuffer.append(" ns=\"t\"");
          if (arrayOfRecNBest[i][j].noSpaceNumber)
            paramStringBuffer.append(" nsd=\"t\"");
          paramStringBuffer.append(">");
          paramStringBuffer.append(arrayOfRecNBest[i][j].iSelectedWord);
          paramStringBuffer.append("</w>");
          j++;
          break;
          paramStringBuffer.append("<w");
        }
      }
      paramStringBuffer.append("</c>");
    }
    paramStringBuffer.append("</UL>");
  }

  private static void serializeWordList(StringBuffer paramStringBuffer, RecResults paramRecResults)
  {
    RecNBest[] arrayOfRecNBest = paramRecResults.wordList;
    paramStringBuffer.append("<WL n=\"" + arrayOfRecNBest.length + "\">");
    for (int i = 0; i < arrayOfRecNBest.length; i++)
    {
      RecChoice[] arrayOfRecChoice = arrayOfRecNBest[i].iChoices;
      paramStringBuffer.append("<w id=\"" + i + "\" n=\"" + arrayOfRecChoice.length + "\">");
      for (int j = 0; j < arrayOfRecChoice.length; j++)
      {
        RecChoice localRecChoice = arrayOfRecChoice[j];
        paramStringBuffer.append("<c r=\"" + localRecChoice.numAlign + "\"");
        if (localRecChoice.noSpace)
          paramStringBuffer.append(" ns=\"t\"");
        if (localRecChoice.noSpaceNumber)
          paramStringBuffer.append(" nsd=\"t\"");
        paramStringBuffer.append(">");
        collapseWords(paramStringBuffer, localRecChoice.words);
        paramStringBuffer.append("</c>");
      }
      paramStringBuffer.append("</w>");
    }
    paramStringBuffer.append("</WL>");
  }

  public String getGUttID()
  {
    return this.guttid;
  }

  public Vector<String> getNBestForWord(String paramString)
  {
    return this.uttResults.getNBestForWord(paramString);
  }

  public int getNBestForWordIfBetterMatch(List<String> paramList, String paramString, String[] paramArrayOfString1, int paramInt1, String[] paramArrayOfString2, int paramInt2, int paramInt3)
  {
    return this.uttResults.getNBestForWordIfBetterMatch(paramList, paramString, paramArrayOfString1, paramInt1, paramArrayOfString2, paramInt2, paramInt3);
  }

  public int getNumParseGroups()
  {
    if (this.groups != null);
    for (int i = this.groups.size(); ; i = 0)
      return i;
  }

  public ParseGroup getParseGroup()
  {
    monitorenter;
    try
    {
      if (this.groups != null)
      {
        boolean bool = this.groups.isEmpty();
        if (!bool)
          break label27;
      }
      label27: for (ParseGroup localParseGroup = null; ; localParseGroup = (ParseGroup)this.groups.elementAt(0))
        return localParseGroup;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public Vector<Object> getParseGroups()
  {
    return this.groups;
  }

  public String getParseType()
  {
    monitorenter;
    try
    {
      String str2;
      if (getParseGroup() != null)
        str2 = getParseGroup().getParseType();
      for (String str1 = str2; ; str1 = "")
        return str1;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public RecResults getUttResults()
  {
    return this.uttResults;
  }

  public boolean isEmpty()
  {
    if ((this.uttResults == null) || (this.uttResults.size() == 0));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isParseGroupsEmpty()
  {
    if ((this.groups == null) || (this.groups.size() == 0));
    for (int i = 1; ; i = 0)
      return i;
  }

  void onParseGroup(float paramFloat, String paramString, int paramInt)
  {
    this.curParseGroup = new ParseGroup(paramFloat, paramString, paramInt, null);
    this.groups.addElement(this.curParseGroup);
  }

  void onTag(String paramString)
  {
    this.curTag = new Tag(paramString, null);
    this.curParseGroup.tags.addElement(this.curTag);
  }

  void onTagList(int paramInt)
  {
    this.groups = new Vector(paramInt);
  }

  void onTagResults(RecResults paramRecResults)
  {
    Tag.access$302(this.curTag, paramRecResults);
  }

  void onUttResults(RecResults paramRecResults)
  {
    this.uttResults = paramRecResults;
  }

  public String serialize()
  {
    if (this.uttResults == null);
    StringBuffer localStringBuffer;
    for (String str = ""; ; str = localStringBuffer.toString())
    {
      return str;
      localStringBuffer = new StringBuffer();
      localStringBuffer.append("<Alternates guttid=\"" + this.uttResults.guttid + "\">");
      serializeWordList(localStringBuffer, this.uttResults);
      serializeUttList(localStringBuffer, this.uttResults);
      serializeParseGroups(localStringBuffer, this.groups);
      localStringBuffer.append("</Alternates>");
    }
  }

  public String toString()
  {
    String str1 = "   TaggedResults: " + this.guttid;
    String str2 = str1 + "\n      UttResults: " + this.uttResults;
    String str3 = str2 + "\n         UttPhrase:";
    if (this.uttResults != null)
    {
      String[] arrayOfString = this.uttResults.getPhraseLevel();
      if (arrayOfString != null)
        for (int k = 0; k < arrayOfString.length; k++)
          str3 = str3 + "\n            " + arrayOfString[k];
    }
    else
    {
      str3 = str3 + " null";
    }
    if (this.groups != null)
    {
      str4 = str3 + "\n      ParseGroups " + this.groups.size();
      int i = this.groups.size();
      for (int j = 0; j < i; j++)
      {
        ParseGroup localParseGroup = (ParseGroup)this.groups.elementAt(j);
        str4 = str4 + localParseGroup.toString();
      }
    }
    String str4 = str3 + " null";
    return str4;
  }

  public static class ParseGroup
  {
    private final float confidence;
    private final String parseType;
    private final Vector<Object> tags;

    private ParseGroup(float paramFloat, String paramString, int paramInt)
    {
      this.confidence = paramFloat;
      this.parseType = paramString;
      this.tags = new Vector(paramInt);
    }

    public float getConfidence()
    {
      return this.confidence;
    }

    public int getNumTags()
    {
      if (this.tags != null);
      for (int i = this.tags.size(); ; i = 0)
        return i;
    }

    public String getParseType()
    {
      return this.parseType;
    }

    public Vector<Object> getTags()
    {
      return this.tags;
    }

    public boolean isTagsEmpty()
    {
      if ((this.tags == null) || (this.tags.size() == 0));
      for (int i = 1; ; i = 0)
        return i;
    }

    public TaggedResults.Tag lookupTagByName(String paramString)
    {
      int j;
      TaggedResults.Tag localTag;
      if ((this.tags != null) && (this.tags.size() > 0))
      {
        int i = this.tags.size();
        j = 0;
        if (j < i)
        {
          localTag = (TaggedResults.Tag)this.tags.elementAt(j);
          if (!localTag.getName().equalsIgnoreCase(paramString));
        }
      }
      while (true)
      {
        return localTag;
        j++;
        break;
        localTag = null;
      }
    }

    public String toString()
    {
      String str1 = "\n         ParseGroup: " + this.confidence + " " + this.parseType;
      if (this.tags != null)
      {
        int i = this.tags.size();
        str2 = str1 + "\n            Tags: " + i;
        for (int j = 0; j < i; j++)
        {
          TaggedResults.Tag localTag = (TaggedResults.Tag)this.tags.elementAt(j);
          str2 = str2 + localTag.toString();
        }
      }
      String str2 = str1 + " null";
      return str2;
    }
  }

  public static class Tag
  {
    private String name;
    private RecResults tagResults = null;

    private Tag(String paramString)
    {
      this.name = paramString;
    }

    public String getName()
    {
      return this.name;
    }

    public RecResults getRecResults()
    {
      return this.tagResults;
    }

    public String toString()
    {
      String str1 = "\n            Tag " + this.name;
      String str2 = str1 + "\n               UttLevel: " + this.tagResults;
      String str3 = str2 + "               PhraseLevel: ";
      String[] arrayOfString = this.tagResults.getPhraseLevel();
      if (arrayOfString != null)
        for (int i = 0; i < arrayOfString.length; i++)
          str3 = str3 + "               " + arrayOfString[i];
      return str3;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.results.TaggedResults
 * JD-Core Version:    0.6.0
 */