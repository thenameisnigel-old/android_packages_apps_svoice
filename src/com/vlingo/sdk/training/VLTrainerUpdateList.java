package com.vlingo.sdk.training;

import com.vlingo.sdk.internal.VLTrainerUpdateListImpl;

public abstract class VLTrainerUpdateList
{
  public static VLTrainerUpdateList createList()
  {
    return new VLTrainerUpdateListImpl();
  }

  public abstract void addContact(int paramInt, String paramString1, String paramString2, String paramString3);

  public abstract void addPlaylist(int paramInt, String paramString);

  public abstract void addSong(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt2, String paramString6);

  public abstract void removeContact(int paramInt);

  public abstract void removePlayList(int paramInt);

  public abstract void removeSong(int paramInt);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.training.VLTrainerUpdateList
 * JD-Core Version:    0.6.0
 */