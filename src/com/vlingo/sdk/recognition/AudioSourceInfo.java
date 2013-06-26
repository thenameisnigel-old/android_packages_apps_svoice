package com.vlingo.sdk.recognition;

import com.vlingo.sdk.internal.util.StringUtils;
import java.io.InputStream;

public class AudioSourceInfo
{
  private String mFilename = null;
  private SourceFormat mFormat = null;
  private InputStream mInputStream = null;
  private String mText = null;
  private SourceType mType = null;

  public static AudioSourceInfo getDataBufferSource(SourceFormat paramSourceFormat)
  {
    if (paramSourceFormat == null)
      throw new IllegalArgumentException("format parameter cannot be null!");
    AudioSourceInfo localAudioSourceInfo = new AudioSourceInfo();
    localAudioSourceInfo.mType = SourceType.BUFFER;
    localAudioSourceInfo.mFormat = paramSourceFormat;
    return localAudioSourceInfo;
  }

  public static final AudioSourceInfo getFileSource(String paramString, SourceFormat paramSourceFormat)
  {
    if (StringUtils.isNullOrWhiteSpace(paramString))
      throw new IllegalArgumentException("filename cannot be null or empty!");
    if (paramSourceFormat == null)
      throw new IllegalArgumentException("format parameter cannot be null!");
    AudioSourceInfo localAudioSourceInfo = new AudioSourceInfo();
    localAudioSourceInfo.mType = SourceType.FILE;
    localAudioSourceInfo.mFormat = paramSourceFormat;
    localAudioSourceInfo.mFilename = paramString;
    return localAudioSourceInfo;
  }

  public static AudioSourceInfo getStreamSource(InputStream paramInputStream, SourceFormat paramSourceFormat)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("inputstream parameter cannot be null");
    if (paramSourceFormat == null)
      throw new IllegalArgumentException("format parameter cannot be null!");
    AudioSourceInfo localAudioSourceInfo = new AudioSourceInfo();
    localAudioSourceInfo.mType = SourceType.STREAM;
    localAudioSourceInfo.mInputStream = paramInputStream;
    localAudioSourceInfo.mFormat = paramSourceFormat;
    return localAudioSourceInfo;
  }

  public static final AudioSourceInfo getStringSource(String paramString)
  {
    if (StringUtils.isNullOrWhiteSpace(paramString))
      throw new IllegalArgumentException("text cannot be null or empty!");
    AudioSourceInfo localAudioSourceInfo = new AudioSourceInfo();
    localAudioSourceInfo.mType = SourceType.STRING;
    localAudioSourceInfo.mFormat = SourceFormat.PLAIN_TEXT;
    localAudioSourceInfo.mText = paramString;
    return localAudioSourceInfo;
  }

  public String getFilename()
  {
    return this.mFilename;
  }

  public SourceFormat getFormat()
  {
    return this.mFormat;
  }

  public InputStream getInputStream()
  {
    return this.mInputStream;
  }

  public String getText()
  {
    return this.mText;
  }

  public SourceType getType()
  {
    return this.mType;
  }

  public boolean isAMR()
  {
    if (this.mFormat == SourceFormat.AMR);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isDataBuffer()
  {
    if (this.mType == SourceType.BUFFER);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isFile()
  {
    if (this.mType == SourceType.FILE);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isStream()
  {
    if (this.mType == SourceType.STREAM);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isString()
  {
    if (this.mType == SourceType.STRING);
    for (int i = 1; ; i = 0)
      return i;
  }

  public static enum SourceFormat
  {
    static
    {
      PCM_16KHZ_16BIT_BE = new SourceFormat("PCM_16KHZ_16BIT_BE", 3);
      PCM_8KHZ_16BIT_BE = new SourceFormat("PCM_8KHZ_16BIT_BE", 4);
      PCM_AUTO = new SourceFormat("PCM_AUTO", 5);
      PLAIN_TEXT = new SourceFormat("PLAIN_TEXT", 6);
      SourceFormat[] arrayOfSourceFormat = new SourceFormat[7];
      arrayOfSourceFormat[0] = AMR;
      arrayOfSourceFormat[1] = PCM_16KHZ_16BIT;
      arrayOfSourceFormat[2] = PCM_8KHZ_16BIT;
      arrayOfSourceFormat[3] = PCM_16KHZ_16BIT_BE;
      arrayOfSourceFormat[4] = PCM_8KHZ_16BIT_BE;
      arrayOfSourceFormat[5] = PCM_AUTO;
      arrayOfSourceFormat[6] = PLAIN_TEXT;
      $VALUES = arrayOfSourceFormat;
    }
  }

  public static enum SourceType
  {
    static
    {
      BUFFER = new SourceType("BUFFER", 2);
      STRING = new SourceType("STRING", 3);
      SourceType[] arrayOfSourceType = new SourceType[4];
      arrayOfSourceType[0] = FILE;
      arrayOfSourceType[1] = STREAM;
      arrayOfSourceType[2] = BUFFER;
      arrayOfSourceType[3] = STRING;
      $VALUES = arrayOfSourceType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.AudioSourceInfo
 * JD-Core Version:    0.6.0
 */