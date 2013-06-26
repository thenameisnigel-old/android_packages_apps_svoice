package com.vlingo.core.internal.phrasespotter;

import com.vlingo.core.internal.util.StringUtils;

public class CoreSpotterParameters
{
  public static final int DEFAULT_DELTA_D = 0;
  public static final int DEFAULT_DELTA_S = 50;
  public static final float DEFAULT_PHRASESPOT_ABSBEAM = 40.0F;
  public static final float DEFAULT_PHRASESPOT_AOFFSET = 0.0F;
  public static final float DEFAULT_PHRASESPOT_BEAM = 20.0F;
  public static final float DEFAULT_PHRASESPOT_DELAY;
  private float absbeam;
  private float aoffset;
  private float beam;
  private String cgFilename;
  private float delay;
  private int deltaD;
  private int deltaS;
  private String grammarSpec;
  private String language;
  private String[] pronunList;
  private String wakeUpExternalStorage;
  private String[] wordList;

  private CoreSpotterParameters()
  {
  }

  private CoreSpotterParameters(Builder paramBuilder)
  {
    this.language = paramBuilder.language;
    this.cgFilename = paramBuilder.cgFilename;
    this.grammarSpec = paramBuilder.grammarSpec;
    this.wordList = paramBuilder.wordList;
    this.pronunList = paramBuilder.pronunList;
    this.beam = paramBuilder.beam;
    this.absbeam = paramBuilder.absbeam;
    this.aoffset = paramBuilder.aoffset;
    this.delay = paramBuilder.delay;
    this.deltaD = paramBuilder.deltaD;
    this.deltaS = paramBuilder.deltaS;
    this.wakeUpExternalStorage = paramBuilder.wakeUpExternalStorage;
  }

  public float getAbsbeam()
  {
    return this.absbeam;
  }

  public float getAoffset()
  {
    return this.aoffset;
  }

  public float getBeam()
  {
    return this.beam;
  }

  public String getCGFilename()
  {
    return this.cgFilename;
  }

  public float getDelay()
  {
    return this.delay;
  }

  public int getDeltaD()
  {
    return this.deltaD;
  }

  public int getDeltaS()
  {
    return this.deltaS;
  }

  public String getGrammarSpec()
  {
    return this.grammarSpec;
  }

  public String getLanguage()
  {
    return this.language;
  }

  public String[] getPronunList()
  {
    return this.pronunList;
  }

  public String getWakeUpExternalStorage()
  {
    return this.wakeUpExternalStorage;
  }

  public String[] getWordList()
  {
    return this.wordList;
  }

  public static class Builder
  {
    private float absbeam = 40.0F;
    private float aoffset = 0.0F;
    private float beam = 20.0F;
    private String cgFilename;
    private float delay = 0.0F;
    private int deltaD = 0;
    private int deltaS = 50;
    private String grammarSpec;
    private String language;
    private String[] pronunList;
    private String wakeUpExternalStorage;
    private String[] wordList;

    private Builder()
    {
    }

    public Builder(String paramString1, String paramString2)
    {
      this();
      if ((StringUtils.isNullOrWhiteSpace(paramString1)) || (StringUtils.isNullOrWhiteSpace(paramString2)))
        throw new IllegalArgumentException("language, cgFilename cannot be null or empty");
      this.language = paramString1;
      this.cgFilename = paramString2;
      this.grammarSpec = null;
      this.wordList = null;
      this.pronunList = null;
    }

    public Builder(String paramString1, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2)
    {
      this();
      if (StringUtils.isNullOrWhiteSpace(paramString1))
        throw new IllegalArgumentException("language cannot be null or empty");
      if (StringUtils.isNullOrWhiteSpace(paramString2))
        throw new IllegalArgumentException("grammarSpec cannot be null or empty");
      if ((paramArrayOfString1 == null) || (paramArrayOfString2 == null) || (paramArrayOfString1.length == 0) || (paramArrayOfString2.length == 0))
        throw new IllegalArgumentException("wordList and pronunlist cannot be null or empty");
      this.language = paramString1;
      this.grammarSpec = paramString2;
      this.wordList = paramArrayOfString1;
      this.pronunList = paramArrayOfString2;
      this.cgFilename = null;
    }

    public CoreSpotterParameters build()
    {
      return new CoreSpotterParameters(this, null);
    }

    public Builder setDeltas(int paramInt1, int paramInt2)
    {
      this.deltaD = paramInt1;
      this.deltaS = paramInt2;
      return this;
    }

    public Builder setSensoryParams(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, String paramString)
    {
      this.beam = paramFloat1;
      this.absbeam = paramFloat2;
      this.aoffset = paramFloat3;
      this.delay = paramFloat4;
      this.wakeUpExternalStorage = paramString;
      return this;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.phrasespotter.CoreSpotterParameters
 * JD-Core Version:    0.6.0
 */