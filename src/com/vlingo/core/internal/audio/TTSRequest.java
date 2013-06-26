package com.vlingo.core.internal.audio;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.vlingo.core.internal.settings.VoicePrompt;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.StringUtils;

public class TTSRequest extends AudioRequest
  implements Parcelable
{
  public static final Parcelable.Creator<TTSRequest> CREATOR = new Parcelable.Creator()
  {
    public TTSRequest createFromParcel(Parcel paramParcel)
    {
      return new TTSRequest(paramParcel);
    }

    public TTSRequest[] newArray(int paramInt)
    {
      return new TTSRequest[paramInt];
    }
  };
  public boolean allowLocalTTS = true;
  public boolean allowNetworkTTS = true;
  public String body;
  public int flags = 0;
  public boolean isCacheable = true;
  public boolean isPersistentCache = false;
  public MessageReadbackType messageReadbackType;
  public String senderAddress;
  public String senderDisplayName;
  public String subject;
  private boolean ttsAnyway = false;
  private String ttsFilePath;
  public Type type = Type.Prompt;
  public int wordLimit = 0;

  protected TTSRequest()
  {
  }

  public TTSRequest(Parcel paramParcel)
  {
    super(paramParcel);
  }

  public static TTSRequest getMessageReadback(String paramString)
  {
    TTSRequest localTTSRequest = new TTSRequest();
    localTTSRequest.body = StringUtils.cleanTts(paramString);
    localTTSRequest.type = Type.MessageReadback;
    return localTTSRequest;
  }

  public static TTSRequest getPrompt(String paramString)
  {
    TTSRequest localTTSRequest = new TTSRequest();
    localTTSRequest.body = StringUtils.cleanTts(paramString);
    localTTSRequest.type = Type.Prompt;
    localTTSRequest.setFlag(4);
    return localTTSRequest;
  }

  public static TTSRequest getPrompt(String paramString, boolean paramBoolean)
  {
    TTSRequest localTTSRequest = new TTSRequest();
    localTTSRequest.body = StringUtils.cleanTts(paramString);
    localTTSRequest.type = Type.Prompt;
    localTTSRequest.setFlag(4);
    localTTSRequest.ttsAnyway = paramBoolean;
    return localTTSRequest;
  }

  public static TTSRequest getResult(String paramString)
  {
    TTSRequest localTTSRequest = new TTSRequest();
    localTTSRequest.body = StringUtils.cleanTts(paramString);
    localTTSRequest.type = Type.Result;
    return localTTSRequest;
  }

  protected void clearCachedFile()
  {
    if (!this.isCacheable);
  }

  String getCacheKey()
  {
    return Long.toHexString(hashCode());
  }

  protected String getTextToSpeak()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(this.body);
    return localStringBuffer.toString();
  }

  public int hashCode()
  {
    return toString().hashCode();
  }

  public void onRequestDidPlay(AudioRequest paramAudioRequest)
  {
    super.onRequestDidPlay(paramAudioRequest);
    clearCachedFile();
  }

  public void onSetDataSourceFailed()
  {
    clearCachedFile();
  }

  public boolean prepareForPlayback(Context paramContext, AudioPlayer paramAudioPlayer)
  {
    return prepareForPlayback(paramContext, paramAudioPlayer.getTTSEngine());
  }

  public boolean prepareForPlayback(Context paramContext, TTSEngine paramTTSEngine)
  {
    int i = 0;
    if ((!ClientSuppliedValues.isDrivingModeEnabled()) && (!VoicePrompt.isEnabled()) && (this.type == Type.Prompt) && (!this.ttsAnyway));
    while (true)
    {
      return i;
      if (this.ttsFilePath != null)
      {
        i = 1;
        continue;
      }
      this.ttsFilePath = paramTTSEngine.getFilePathForTTSRequest(paramContext, this);
      if (this.ttsFilePath == null)
        continue;
      i = 1;
    }
  }

  public void readFromParcel(Parcel paramParcel)
  {
    super.readFromParcel(paramParcel);
    this.senderAddress = paramParcel.readString();
    this.senderDisplayName = paramParcel.readString();
    this.subject = paramParcel.readString();
    this.body = paramParcel.readString();
    boolean[] arrayOfBoolean = new boolean[3];
    paramParcel.readBooleanArray(arrayOfBoolean);
    this.isCacheable = arrayOfBoolean[0];
    this.allowLocalTTS = arrayOfBoolean[1];
    this.allowNetworkTTS = arrayOfBoolean[2];
  }

  // ERROR //
  public void setDataSource(Context paramContext, android.media.MediaPlayer paramMediaPlayer, AudioPlayer paramAudioPlayer)
    throws java.lang.Exception
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 149	com/vlingo/core/internal/audio/TTSRequest:ttsFilePath	Ljava/lang/String;
    //   4: ifnull +38 -> 42
    //   7: aconst_null
    //   8: astore 4
    //   10: new 179	java/io/FileInputStream
    //   13: dup
    //   14: aload_0
    //   15: getfield 149	com/vlingo/core/internal/audio/TTSRequest:ttsFilePath	Ljava/lang/String;
    //   18: invokespecial 182	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   21: astore 5
    //   23: aload_2
    //   24: aload 5
    //   26: invokevirtual 186	java/io/FileInputStream:getFD	()Ljava/io/FileDescriptor;
    //   29: invokevirtual 191	android/media/MediaPlayer:setDataSource	(Ljava/io/FileDescriptor;)V
    //   32: aload 5
    //   34: ifnull +8 -> 42
    //   37: aload 5
    //   39: invokevirtual 194	java/io/FileInputStream:close	()V
    //   42: return
    //   43: astore 11
    //   45: aload 4
    //   47: ifnull -5 -> 42
    //   50: aload 4
    //   52: invokevirtual 194	java/io/FileInputStream:close	()V
    //   55: goto -13 -> 42
    //   58: astore 7
    //   60: goto -18 -> 42
    //   63: astore 8
    //   65: aload 4
    //   67: ifnull +8 -> 75
    //   70: aload 4
    //   72: invokevirtual 194	java/io/FileInputStream:close	()V
    //   75: aload 8
    //   77: athrow
    //   78: astore 10
    //   80: goto -38 -> 42
    //   83: astore 9
    //   85: goto -10 -> 75
    //   88: astore 8
    //   90: aload 5
    //   92: astore 4
    //   94: goto -29 -> 65
    //   97: astore 6
    //   99: aload 5
    //   101: astore 4
    //   103: goto -58 -> 45
    //
    // Exception table:
    //   from	to	target	type
    //   10	23	43	java/lang/Exception
    //   50	55	58	java/lang/Exception
    //   10	23	63	finally
    //   37	42	78	java/lang/Exception
    //   70	75	83	java/lang/Exception
    //   23	32	88	finally
    //   23	32	97	java/lang/Exception
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(this.type).append(this.messageReadbackType);
    if (!StringUtils.isNullOrWhiteSpace(this.senderAddress))
      localStringBuffer.append(this.senderAddress);
    if (!StringUtils.isNullOrWhiteSpace(this.senderDisplayName))
      localStringBuffer.append(this.senderDisplayName);
    if (!StringUtils.isNullOrWhiteSpace(this.subject))
      localStringBuffer.append(this.subject);
    if (!StringUtils.isNullOrWhiteSpace(this.body))
      localStringBuffer.append(this.body);
    localStringBuffer.append(this.isCacheable).append(this.isPersistentCache).append(this.allowLocalTTS).append(this.allowNetworkTTS);
    localStringBuffer.append(this.flags).append(this.wordLimit);
    if (!StringUtils.isNullOrWhiteSpace(this.ttsFilePath))
      localStringBuffer.append(this.ttsFilePath);
    return localStringBuffer.toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(this.senderAddress);
    paramParcel.writeString(this.senderDisplayName);
    paramParcel.writeString(this.subject);
    paramParcel.writeString(this.body);
    boolean[] arrayOfBoolean = new boolean[3];
    arrayOfBoolean[0] = this.isCacheable;
    arrayOfBoolean[1] = this.allowLocalTTS;
    arrayOfBoolean[2] = this.allowNetworkTTS;
    paramParcel.writeBooleanArray(arrayOfBoolean);
  }

  public static enum MessageReadbackType
  {
    static
    {
      Email = new MessageReadbackType("Email", 1);
      MessageReadbackType[] arrayOfMessageReadbackType = new MessageReadbackType[2];
      arrayOfMessageReadbackType[0] = SMS;
      arrayOfMessageReadbackType[1] = Email;
      $VALUES = arrayOfMessageReadbackType;
    }
  }

  public static enum Type
  {
    static
    {
      MessageReadback = new Type("MessageReadback", 1);
      Result = new Type("Result", 2);
      Type[] arrayOfType = new Type[3];
      arrayOfType[0] = Prompt;
      arrayOfType[1] = MessageReadback;
      arrayOfType[2] = Result;
      $VALUES = arrayOfType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.TTSRequest
 * JD-Core Version:    0.6.0
 */