package com.vlingo.sdk.recognition.spotter;

import com.vlingo.sdk.internal.util.StringUtils;
import com.vlingo.sdk.recognition.AudioSourceInfo;
import com.vlingo.sdk.recognition.AudioSourceInfo.SourceFormat;
import com.vlingo.sdk.recognition.AudioSourceInfo.SourceType;
import java.util.ArrayList;

public final class VLSpotterContext
{
  public static final String DEFAULT_LANGUAGE = "en-US";
  public static final float DEFAULT_PHRASESPOT_ABSBEAM = 40.0F;
  public static final float DEFAULT_PHRASESPOT_AOFFSET = 0.0F;
  public static final float DEFAULT_PHRASESPOT_BEAM = 20.0F;
  public static final float DEFAULT_PHRASESPOT_DELAY = 100.0F;
  public static final int DEFAULT_PHRASESPOT_PARAMA = 0;
  public static final int DEFAULT_PHRASESPOT_PARAMB = 320;
  public static final int DEFAULT_PHRASESPOT_PARAMC = 500;
  float absbeam;
  float aoffset;
  private AudioSourceInfo audioSourceInfo;
  float beam;
  float delay;
  GrammarSource grammarSource;
  private String language;

  private VLSpotterContext(Builder paramBuilder)
  {
    this.audioSourceInfo = paramBuilder.audioSourceInfo;
    this.language = paramBuilder.language;
    this.grammarSource = paramBuilder.grammarSource;
    this.beam = paramBuilder.beam;
    this.absbeam = paramBuilder.absbeam;
    this.aoffset = paramBuilder.aoffset;
    this.delay = paramBuilder.delay;
  }

  public float getAbsBeam()
  {
    return this.absbeam;
  }

  public float getAoffset()
  {
    return this.aoffset;
  }

  public AudioSourceInfo getAudioSourceInfo()
  {
    return this.audioSourceInfo;
  }

  public float getBeam()
  {
    return this.beam;
  }

  public float getDelay()
  {
    return this.delay;
  }

  public GrammarSource getGrammarSource()
  {
    return this.grammarSource;
  }

  public String getLanguage()
  {
    return this.language;
  }

  public static class Builder
  {
    private float absbeam = 40.0F;
    private float aoffset = 0.0F;
    private AudioSourceInfo audioSourceInfo = AudioSourceInfo.getDataBufferSource(AudioSourceInfo.SourceFormat.PCM_16KHZ_16BIT);
    private float beam = 20.0F;
    private float delay = 100.0F;
    private VLSpotterContext.GrammarSource grammarSource;
    private String language = "en-US";

    public Builder(VLSpotterContext.GrammarSource paramGrammarSource)
    {
      validateDefined(paramGrammarSource);
      this.grammarSource = paramGrammarSource;
    }

    private void validateDefined(Object paramObject)
    {
      if (paramObject == null)
        throw new IllegalArgumentException("Value cannot be null");
    }

    private void validateStringNotEmpty(String paramString)
    {
      if (StringUtils.isNullOrWhiteSpace(paramString))
        throw new IllegalArgumentException("Value cannot be null or empty");
    }

    public Builder audioSourceInfo(AudioSourceInfo paramAudioSourceInfo)
    {
      validateDefined(paramAudioSourceInfo);
      if (paramAudioSourceInfo.getType() != AudioSourceInfo.SourceType.BUFFER)
        throw new IllegalArgumentException("Unsupported audio source, supported formats are: SourceType.BUFFER");
      if (paramAudioSourceInfo.getFormat() != AudioSourceInfo.SourceFormat.PCM_16KHZ_16BIT)
        throw new IllegalArgumentException("Unsupported audio format format, supported formats are: SourceFormat.PCM_16KHZ_16BIT");
      this.audioSourceInfo = paramAudioSourceInfo;
      return this;
    }

    public VLSpotterContext build()
    {
      return new VLSpotterContext(this, null);
    }

    public Builder language(String paramString)
    {
      validateStringNotEmpty(paramString);
      this.language = paramString;
      return this;
    }

    public Builder spotterParams(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, String paramString)
    {
      this.beam = paramFloat1;
      this.absbeam = paramFloat2;
      this.aoffset = paramFloat3;
      this.delay = paramFloat4;
      this.language = paramString;
      return this;
    }
  }

  public static final class GrammarSource
  {
    private String cgFile;
    private String grammarSpec;
    private String[] pronunciationList;
    private String[] wordList;

    public static GrammarSource getCompiledFileSource(String paramString)
    {
      if (StringUtils.isNullOrWhiteSpace(paramString))
        throw new IllegalArgumentException("cgFile cannot be null or empty");
      GrammarSource localGrammarSource = new GrammarSource();
      localGrammarSource.cgFile = paramString;
      return localGrammarSource;
    }

    private static String getGrammarSpec(String[] paramArrayOfString)
    {
      StringBuilder localStringBuilder = new StringBuilder("g=(");
      int i = paramArrayOfString.length;
      for (int j = 0; j < i; j++)
      {
        localStringBuilder.append(paramArrayOfString[j]);
        localStringBuilder.append("|");
      }
      localStringBuilder.setLength(-1 + localStringBuilder.length());
      localStringBuilder.append(");\nparamA:$g 0;\nparamB:$g 320;\nparamC:$g 500;\n");
      return localStringBuilder.toString();
    }

    public static GrammarSource getGrammarSpecSource(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
    {
      if (StringUtils.isNullOrWhiteSpace(paramString))
        throw new IllegalArgumentException("grammarSpec cannot be null or empty");
      if ((paramArrayOfString1 == null) || (paramArrayOfString1.length == 0))
        throw new IllegalArgumentException("wordList cannot be null and must have at least 1 element");
      if ((paramArrayOfString2 != null) && (paramArrayOfString2.length != paramArrayOfString1.length))
        throw new IllegalArgumentException("when not null, pronunciationList must contain the same number of elements as wordList");
      GrammarSource localGrammarSource = new GrammarSource();
      localGrammarSource.grammarSpec = paramString;
      localGrammarSource.wordList = paramArrayOfString1;
      localGrammarSource.pronunciationList = paramArrayOfString2;
      return localGrammarSource;
    }

    public static GrammarSource getGrammarSpecSource(String[] paramArrayOfString)
    {
      if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
        throw new IllegalArgumentException("phraseList cannot be null and must have at least 1 element");
      GrammarSource localGrammarSource = new GrammarSource();
      localGrammarSource.grammarSpec = getGrammarSpec(paramArrayOfString);
      localGrammarSource.wordList = getWordList(paramArrayOfString);
      return localGrammarSource;
    }

    private static String[] getWordList(String[] paramArrayOfString)
    {
      ArrayList localArrayList = new ArrayList();
      int i = paramArrayOfString.length;
      for (int j = 0; j < i; j++)
      {
        String str1 = paramArrayOfString[j].trim();
        if (str1.length() <= 0)
          continue;
        for (String str2 : StringUtils.split(str1, ' '))
        {
          if (localArrayList.contains(str1))
            continue;
          localArrayList.add(str2);
        }
      }
      String[] arrayOfString1 = new String[localArrayList.size()];
      localArrayList.toArray(arrayOfString1);
      return arrayOfString1;
    }

    public String getCompiledFilepath()
    {
      return this.cgFile;
    }

    public String getGrammarSpec()
    {
      return this.grammarSpec;
    }

    public String[] getPronunciationList()
    {
      return this.pronunciationList;
    }

    public String[] getWordList()
    {
      return this.wordList;
    }

    public boolean isCompiledFileSource()
    {
      if (this.cgFile != null);
      for (int i = 1; ; i = 0)
        return i;
    }

    public boolean isGrammarSpecSource()
    {
      if (this.grammarSpec != null);
      for (int i = 1; ; i = 0)
        return i;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.spotter.VLSpotterContext
 * JD-Core Version:    0.6.0
 */