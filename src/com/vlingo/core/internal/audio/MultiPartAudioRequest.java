package com.vlingo.core.internal.audio;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;

public class MultiPartAudioRequest extends AudioRequest
  implements Parcelable
{
  private int currentPart = 0;
  private final ArrayList<AudioRequest> parts = new ArrayList();

  public MultiPartAudioRequest()
  {
    this(null);
  }

  public MultiPartAudioRequest(AudioRequest[] paramArrayOfAudioRequest)
  {
    if (paramArrayOfAudioRequest != null)
    {
      int i = paramArrayOfAudioRequest.length;
      for (int j = 0; j < i; j++)
        addRequest(paramArrayOfAudioRequest[j]);
    }
  }

  public static MultiPartAudioRequest getMultipartRequest(AudioRequest[] paramArrayOfAudioRequest)
  {
    MultiPartAudioRequest localMultiPartAudioRequest = new MultiPartAudioRequest();
    int i = paramArrayOfAudioRequest.length;
    for (int j = 0; j < i; j++)
      localMultiPartAudioRequest.addRequest(paramArrayOfAudioRequest[j]);
    return localMultiPartAudioRequest;
  }

  public void addRequest(AudioRequest paramAudioRequest)
  {
    this.parts.add(paramAudioRequest);
  }

  boolean areMorePartsWaiting()
  {
    if (this.currentPart < this.parts.size());
    for (int i = 1; ; i = 0)
      return i;
  }

  public AudioRequest getLastRequest()
  {
    return getRequest(-1 + this.parts.size());
  }

  public AudioRequest getRequest(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this.parts.size()));
    for (AudioRequest localAudioRequest = (AudioRequest)this.parts.get(paramInt); ; localAudioRequest = null)
      return localAudioRequest;
  }

  boolean isOnFirstPart()
  {
    if (this.currentPart == 0);
    for (int i = 1; ; i = 0)
      return i;
  }

  boolean isOnLastPart()
  {
    if (this.currentPart >= this.parts.size());
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean prepareForPlayback(Context paramContext, AudioPlayer paramAudioPlayer)
  {
    AudioRequest localAudioRequest1 = getLastRequest();
    Iterator localIterator = this.parts.iterator();
    AudioRequest localAudioRequest2;
    do
    {
      if (!localIterator.hasNext())
        break;
      localAudioRequest2 = (AudioRequest)localIterator.next();
      if (localAudioRequest2 == localAudioRequest1)
        continue;
      localAudioRequest2.setFlag(4);
    }
    while (localAudioRequest2.prepareForPlayback(paramContext, paramAudioPlayer));
    for (int i = 0; ; i = 1)
      return i;
  }

  public void setDataSource(Context paramContext, MediaPlayer paramMediaPlayer, AudioPlayer paramAudioPlayer)
    throws Exception
  {
    if (areMorePartsWaiting())
    {
      AudioRequest localAudioRequest = (AudioRequest)this.parts.get(this.currentPart);
      this.currentPart = (1 + this.currentPart);
      localAudioRequest.setDataSource(paramContext, paramMediaPlayer, paramAudioPlayer);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.MultiPartAudioRequest
 * JD-Core Version:    0.6.0
 */