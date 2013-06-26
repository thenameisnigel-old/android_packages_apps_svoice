package com.vlingo.sdk.internal;

import com.vlingo.sdk.internal.lmtt.LMTTContactItem;
import com.vlingo.sdk.internal.lmtt.LMTTItem;
import com.vlingo.sdk.internal.lmtt.LMTTItem.ChangeType;
import com.vlingo.sdk.internal.lmtt.LMTTItem.LmttItemType;
import com.vlingo.sdk.internal.lmtt.LMTTPlaylistItem;
import com.vlingo.sdk.internal.lmtt.LMTTSongItem;
import com.vlingo.sdk.training.VLTrainer.TrainerItemType;
import com.vlingo.sdk.training.VLTrainerUpdateList;
import java.util.ArrayList;
import java.util.List;

public class VLTrainerUpdateListImpl extends VLTrainerUpdateList
{
  List<LMTTItem> mUpdateList = new ArrayList();

  private int removeExistingItemFromList(LMTTItem.LmttItemType paramLmttItemType, int paramInt1, int paramInt2)
  {
    int i = 0;
    for (int j = 0; j < paramInt2; j++)
    {
      LMTTItem localLMTTItem = (LMTTItem)this.mUpdateList.get(j);
      if ((localLMTTItem.type != paramLmttItemType) || (localLMTTItem.uid != paramInt1))
        continue;
      this.mUpdateList.remove(j);
      j--;
      paramInt2--;
      i++;
    }
    return i;
  }

  public void addContact(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    LMTTContactItem localLMTTContactItem = new LMTTContactItem(paramString1, paramString2, paramString3, paramInt, LMTTItem.ChangeType.INSERT);
    this.mUpdateList.add(localLMTTContactItem);
  }

  public void addNOOPItem(VLTrainer.TrainerItemType paramTrainerItemType)
  {
    switch (1.$SwitchMap$com$vlingo$sdk$training$VLTrainer$TrainerItemType[paramTrainerItemType.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return;
      this.mUpdateList.add(new LMTTContactItem(-1, LMTTItem.ChangeType.NOCHANGE));
      continue;
      this.mUpdateList.add(new LMTTSongItem(-1, LMTTItem.ChangeType.NOCHANGE));
      continue;
      this.mUpdateList.add(new LMTTPlaylistItem(-1, LMTTItem.ChangeType.NOCHANGE));
    }
  }

  public void addPlaylist(int paramInt, String paramString)
  {
    LMTTPlaylistItem localLMTTPlaylistItem = new LMTTPlaylistItem(paramString, paramInt, LMTTItem.ChangeType.INSERT);
    this.mUpdateList.add(localLMTTPlaylistItem);
  }

  public void addSong(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt2, String paramString6)
  {
    LMTTSongItem localLMTTSongItem = new LMTTSongItem(paramString1, paramString2, paramString4, paramString3, paramString5, paramInt2, paramString6, paramInt1, LMTTItem.ChangeType.INSERT);
    this.mUpdateList.add(localLMTTSongItem);
  }

  public List<LMTTItem> compact()
  {
    LMTTItem localLMTTItem;
    for (int i = -1 + this.mUpdateList.size(); i > 0; i = -1 + (i - removeExistingItemFromList(localLMTTItem.type, localLMTTItem.uid, i)))
      localLMTTItem = (LMTTItem)this.mUpdateList.get(i);
    return this.mUpdateList;
  }

  public boolean isEmpty()
  {
    return this.mUpdateList.isEmpty();
  }

  public void removeContact(int paramInt)
  {
    LMTTContactItem localLMTTContactItem = new LMTTContactItem(paramInt, LMTTItem.ChangeType.DELETE);
    this.mUpdateList.add(localLMTTContactItem);
  }

  public void removePlayList(int paramInt)
  {
    LMTTPlaylistItem localLMTTPlaylistItem = new LMTTPlaylistItem(paramInt, LMTTItem.ChangeType.DELETE);
    this.mUpdateList.add(localLMTTPlaylistItem);
  }

  public void removeSong(int paramInt)
  {
    LMTTSongItem localLMTTSongItem = new LMTTSongItem(paramInt, LMTTItem.ChangeType.DELETE);
    this.mUpdateList.add(localLMTTSongItem);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.VLTrainerUpdateListImpl
 * JD-Core Version:    0.6.0
 */