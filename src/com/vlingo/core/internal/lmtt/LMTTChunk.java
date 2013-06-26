package com.vlingo.core.internal.lmtt;

import java.util.ArrayList;

public class LMTTChunk
{
  private ArrayList<LMTTItem> chunkItems;
  private LMTTChunkComm.ChunkState chunkUpdateState;
  private boolean isWhole;

  private LMTTChunk()
  {
  }

  LMTTChunk(LMTTChunkComm.ChunkState paramChunkState)
  {
    this.chunkUpdateState = paramChunkState;
  }

  public boolean getIsWhole()
  {
    return this.isWhole;
  }

  public ArrayList<LMTTItem> getItemList()
  {
    return this.chunkItems;
  }

  public int getSize()
  {
    if (this.chunkItems != null);
    for (int i = this.chunkItems.size(); ; i = 0)
      return i;
  }

  public LMTTChunkComm.ChunkState getState()
  {
    return this.chunkUpdateState;
  }

  public void setIsWhole(boolean paramBoolean)
  {
    this.isWhole = paramBoolean;
  }

  public void setItemList(ArrayList<LMTTItem> paramArrayList)
  {
    this.chunkItems = paramArrayList;
  }

  public void setState(LMTTChunkComm.ChunkState paramChunkState)
  {
    this.chunkUpdateState = paramChunkState;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.lmtt.LMTTChunk
 * JD-Core Version:    0.6.0
 */