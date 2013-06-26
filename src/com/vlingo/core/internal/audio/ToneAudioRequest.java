package com.vlingo.core.internal.audio;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ToneAudioRequest extends AudioRequest
  implements Parcelable
{
  public static final Parcelable.Creator<ToneAudioRequest> CREATOR = new ToneAudioRequest.1();
  protected int toneResourceId;

  protected ToneAudioRequest()
  {
  }

  protected ToneAudioRequest(int paramInt)
  {
    this.toneResourceId = paramInt;
  }

  public ToneAudioRequest(Parcel paramParcel)
  {
    super(paramParcel);
  }

  public static ToneAudioRequest getRequest(int paramInt)
  {
    ToneAudioRequest localToneAudioRequest = new ToneAudioRequest(paramInt);
    localToneAudioRequest.requestAudioFocus = true;
    localToneAudioRequest.setFlag(8);
    localToneAudioRequest.setFlag(2);
    localToneAudioRequest.setFlag(1);
    return localToneAudioRequest;
  }

  public boolean prepareForPlayback(Context paramContext, AudioPlayer paramAudioPlayer)
  {
    return true;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    super.readFromParcel(paramParcel);
    this.toneResourceId = paramParcel.readInt();
  }

  // ERROR //
  public void setDataSource(Context paramContext, android.media.MediaPlayer paramMediaPlayer, AudioPlayer paramAudioPlayer)
    throws java.lang.Exception
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_1
    //   4: invokevirtual 59	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   7: aload_0
    //   8: getfield 24	com/vlingo/core/internal/audio/ToneAudioRequest:toneResourceId	I
    //   11: invokevirtual 65	android/content/res/Resources:openRawResourceFd	(I)Landroid/content/res/AssetFileDescriptor;
    //   14: astore 4
    //   16: aload_2
    //   17: aload 4
    //   19: invokevirtual 71	android/content/res/AssetFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   22: aload 4
    //   24: invokevirtual 75	android/content/res/AssetFileDescriptor:getStartOffset	()J
    //   27: aload 4
    //   29: invokevirtual 78	android/content/res/AssetFileDescriptor:getDeclaredLength	()J
    //   32: invokevirtual 83	android/media/MediaPlayer:setDataSource	(Ljava/io/FileDescriptor;JJ)V
    //   35: aload 4
    //   37: ifnull +8 -> 45
    //   40: aload 4
    //   42: invokevirtual 86	android/content/res/AssetFileDescriptor:close	()V
    //   45: return
    //   46: astore 5
    //   48: aload 4
    //   50: ifnull +8 -> 58
    //   53: aload 4
    //   55: invokevirtual 86	android/content/res/AssetFileDescriptor:close	()V
    //   58: aload 5
    //   60: athrow
    //   61: astore 7
    //   63: goto -18 -> 45
    //   66: astore 6
    //   68: goto -10 -> 58
    //
    // Exception table:
    //   from	to	target	type
    //   3	35	46	finally
    //   40	45	61	java/lang/Exception
    //   53	58	66	java/lang/Exception
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.toneResourceId);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.ToneAudioRequest
 * JD-Core Version:    0.6.0
 */