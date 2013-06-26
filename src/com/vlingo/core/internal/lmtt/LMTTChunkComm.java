package com.vlingo.core.internal.lmtt;

import android.content.Context;
import android.os.Handler;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.training.VLTrainer;
import com.vlingo.sdk.training.VLTrainer.TrainerItemType;
import com.vlingo.sdk.training.VLTrainerErrors;
import com.vlingo.sdk.training.VLTrainerListener;
import com.vlingo.sdk.training.VLTrainerUpdateList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class LMTTChunkComm
  implements VLTrainerListener
{
  private static final int DEFAULT_CHUNK_DELAY_MS = 15000;
  private static final int DEFAULT_CHUNK_RETRY_DELAY = 120000;
  private static final int DEFAULT_CHUNK_SIZE = 2500;
  private static final int DEFAULT_MAX_RETRIES = 10;
  private LMTTChunk mActiveChunk;
  private Stack<LMTTChunk> mChunkStack;
  private boolean mFailedChunks;
  private Handler mHandler;
  private VLTrainerListener mListener;
  private LMTTManager.LmttUpdateType mLmttUpdateType;
  private HashMap<VLTrainer.TrainerItemType, Integer> mServerCounts;

  private LMTTChunkComm(LMTTManager.LmttUpdateType paramLmttUpdateType, ArrayList<LMTTItem> paramArrayList, boolean paramBoolean, VLTrainerListener paramVLTrainerListener)
  {
    this.mLmttUpdateType = paramLmttUpdateType;
    this.mListener = paramVLTrainerListener;
    this.mFailedChunks = false;
    this.mChunkStack = createChunkStack(paramArrayList, paramBoolean);
    this.mServerCounts = new HashMap();
    this.mHandler = new Handler(ApplicationAdapter.getInstance().getApplicationContext().getMainLooper());
  }

  private Stack<LMTTChunk> createChunkStack(ArrayList<LMTTItem> paramArrayList, boolean paramBoolean)
  {
    boolean bool1 = true;
    Stack localStack = new Stack();
    ArrayList localArrayList = new ArrayList();
    Object localObject = null;
    int i = 1;
    int j = 0;
    int k = Settings.getInt("lmtt.chunk_size", 2500);
    if (this.mLmttUpdateType == LMTTManager.LmttUpdateType.LMTT_LANGUAGE_UPDATE)
    {
      LMTTChunk localLMTTChunk1 = new LMTTChunk(ChunkState.NODELAY_START_STATE);
      localLMTTChunk1.setItemList(localArrayList);
      localStack.add(localLMTTChunk1);
    }
    label147: label170: label216: label222: 
    do
    {
      if (localObject != null)
        localStack.add(localObject);
      return localStack;
      Iterator localIterator = paramArrayList.iterator();
      while (localIterator.hasNext())
      {
        localArrayList.add((LMTTItem)localIterator.next());
        if (localArrayList.size() < k)
          continue;
        j++;
        ChunkState localChunkState2;
        LMTTChunk localLMTTChunk3;
        boolean bool2;
        if (i != 0)
        {
          localChunkState2 = ChunkState.NODELAY_START_STATE;
          localLMTTChunk3 = new LMTTChunk(localChunkState2);
          if ((i == 0) || (!paramBoolean))
            break label216;
          bool2 = bool1;
          localLMTTChunk3.setIsWhole(bool2);
          localLMTTChunk3.setItemList(localArrayList);
          if (i == 0)
            break label222;
          localObject = localLMTTChunk3;
        }
        while (true)
        {
          i = 0;
          localArrayList = new ArrayList();
          break;
          localChunkState2 = ChunkState.DELAYED_START_STATE;
          break label147;
          bool2 = false;
          break label170;
          localStack.add(localLMTTChunk3);
        }
      }
    }
    while (localArrayList.size() <= 0);
    (j + 1);
    ChunkState localChunkState1;
    label256: LMTTChunk localLMTTChunk2;
    if (i != 0)
    {
      localChunkState1 = ChunkState.NODELAY_START_STATE;
      localLMTTChunk2 = new LMTTChunk(localChunkState1);
      if ((i == 0) || (!paramBoolean))
        break label308;
    }
    while (true)
    {
      localLMTTChunk2.setIsWhole(bool1);
      localLMTTChunk2.setItemList(localArrayList);
      localStack.add(localLMTTChunk2);
      break;
      localChunkState1 = ChunkState.DELAYED_START_STATE;
      break label256;
      label308: bool1 = false;
    }
  }

  private VLTrainerUpdateList generateTrainerUpdateList(ArrayList<LMTTItem> paramArrayList)
  {
    VLTrainerUpdateList localVLTrainerUpdateList = VLTrainerUpdateList.createList();
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
    {
      LMTTItem localLMTTItem = (LMTTItem)localIterator.next();
      switch (5.$SwitchMap$com$vlingo$core$internal$lmtt$LMTTItem$ChangeType[localLMTTItem.changeType.ordinal()])
      {
      default:
        break;
      case 1:
        switch (5.$SwitchMap$com$vlingo$core$internal$lmtt$LMTTItem$LmttItemType[localLMTTItem.type.ordinal()])
        {
        default:
          break;
        case 1:
          localVLTrainerUpdateList.removeContact(localLMTTItem.uid);
          break;
        case 2:
          localVLTrainerUpdateList.removeSong(localLMTTItem.uid);
          break;
        case 3:
          localVLTrainerUpdateList.removePlayList(localLMTTItem.uid);
        }
        break;
      case 2:
      case 3:
        switch (5.$SwitchMap$com$vlingo$core$internal$lmtt$LMTTItem$LmttItemType[localLMTTItem.type.ordinal()])
        {
        default:
          break;
        case 1:
          LMTTContactItem localLMTTContactItem = (LMTTContactItem)localLMTTItem;
          localVLTrainerUpdateList.addContact(localLMTTItem.uid, localLMTTContactItem.firstName, localLMTTContactItem.lastName, localLMTTContactItem.companyName);
          break;
        case 2:
          LMTTSongItem localLMTTSongItem = (LMTTSongItem)localLMTTItem;
          localVLTrainerUpdateList.addSong(localLMTTItem.uid, localLMTTSongItem.title, localLMTTSongItem.artist, localLMTTSongItem.album, localLMTTSongItem.composer, localLMTTSongItem.genre, localLMTTSongItem.year, localLMTTSongItem.folder);
          break;
        case 3:
          LMTTPlaylistItem localLMTTPlaylistItem = (LMTTPlaylistItem)localLMTTItem;
          localVLTrainerUpdateList.addPlaylist(localLMTTItem.uid, localLMTTPlaylistItem.title);
        }
      }
    }
    return localVLTrainerUpdateList;
  }

  public static LMTTChunkComm getInstance(LMTTManager.LmttUpdateType paramLmttUpdateType, ArrayList<LMTTItem> paramArrayList, boolean paramBoolean, VLTrainerListener paramVLTrainerListener)
  {
    return new LMTTChunkComm(paramLmttUpdateType, paramArrayList, paramBoolean, paramVLTrainerListener);
  }

  private void handleNextChunk()
  {
    if (!this.mChunkStack.empty())
    {
      this.mActiveChunk = ((LMTTChunk)this.mChunkStack.pop());
      if (this.mLmttUpdateType == LMTTManager.LmttUpdateType.LMTT_LANGUAGE_UPDATE)
        this.mHandler.postDelayed(new Runnable()
        {
          public void run()
          {
            VLSdk.getInstance().getTrainer().updateTrainerModelLanguage(Settings.getLanguageApplication(), LMTTChunkComm.this);
          }
        }
        , this.mActiveChunk.getState().getDelay());
    }
    while (true)
    {
      return;
      VLTrainerUpdateList localVLTrainerUpdateList = generateTrainerUpdateList(this.mActiveChunk.getItemList());
      if (this.mActiveChunk.getIsWhole())
      {
        this.mHandler.postDelayed(new Runnable(localVLTrainerUpdateList)
        {
          public void run()
          {
            VLSdk.getInstance().getTrainer().sendFullUpdate(this.val$updateList, Settings.getLanguageApplication(), LMTTChunkComm.this);
          }
        }
        , this.mActiveChunk.getState().getDelay());
        continue;
      }
      this.mHandler.postDelayed(new Runnable(localVLTrainerUpdateList)
      {
        public void run()
        {
          VLSdk.getInstance().getTrainer().sendPartialUpdate(this.val$updateList, Settings.getLanguageApplication(), LMTTChunkComm.this);
        }
      }
      , this.mActiveChunk.getState().getDelay());
      continue;
      if (this.mFailedChunks)
      {
        this.mListener.onError(VLTrainerErrors.ERROR_NETWORK, "chunk error");
        continue;
      }
      this.mListener.onUpdateReceived(this.mServerCounts);
    }
  }

  public void onError(VLTrainerErrors paramVLTrainerErrors, String paramString)
  {
    ChunkState localChunkState = this.mActiveChunk.getState().getNextState(false);
    if (localChunkState != ChunkState.ERRORED_OUT_STATE)
    {
      this.mActiveChunk.setState(localChunkState);
      this.mChunkStack.push(this.mActiveChunk);
    }
    while (true)
    {
      handleNextChunk();
      return;
      this.mFailedChunks = true;
    }
  }

  public void onUpdateReceived(HashMap<VLTrainer.TrainerItemType, Integer> paramHashMap)
  {
    this.mServerCounts.putAll(paramHashMap);
    boolean bool = false;
    if ((paramHashMap != null) && (paramHashMap.size() > 0))
      bool = true;
    new Thread("LMTTChunkThread", this.mActiveChunk.getState().getNextState(bool))
    {
      public void run()
      {
        if ((this.val$nextState != LMTTChunkComm.ChunkState.FINISHED_STATE) && (this.val$nextState != LMTTChunkComm.ChunkState.ERRORED_OUT_STATE))
        {
          LMTTChunkComm.this.mActiveChunk.setState(this.val$nextState);
          LMTTChunkComm.this.mChunkStack.push(LMTTChunkComm.this.mActiveChunk);
        }
        while (true)
        {
          LMTTChunkComm.this.handleNextChunk();
          return;
          if (this.val$nextState == LMTTChunkComm.ChunkState.FINISHED_STATE)
          {
            LMTTDBUtil.updateSynchedItems(LMTTChunkComm.this.mActiveChunk.getItemList());
            continue;
          }
          if (this.val$nextState != LMTTChunkComm.ChunkState.ERRORED_OUT_STATE)
            continue;
          LMTTChunkComm.access$202(LMTTChunkComm.this, true);
        }
      }
    }
    .start();
  }

  public void transmitChunks()
  {
    handleNextChunk();
  }

  public static enum ChunkState
  {
    private long delay;
    private ChunkState failNextState;
    private int retryCount;
    private ChunkState successNextState;

    static
    {
      DELAYED_START_STATE = new ChunkState("DELAYED_START_STATE", 3, Settings.getInt("lmtt.chunk_delay_ms", 15000), STATE_RETRY, FINISHED_STATE);
      NODELAY_START_STATE = new ChunkState("NODELAY_START_STATE", 4, 0L, STATE_RETRY, FINISHED_STATE);
      ChunkState[] arrayOfChunkState = new ChunkState[5];
      arrayOfChunkState[0] = ERRORED_OUT_STATE;
      arrayOfChunkState[1] = FINISHED_STATE;
      arrayOfChunkState[2] = STATE_RETRY;
      arrayOfChunkState[3] = DELAYED_START_STATE;
      arrayOfChunkState[4] = NODELAY_START_STATE;
      $VALUES = arrayOfChunkState;
    }

    private ChunkState()
    {
    }

    private ChunkState(long paramLong, ChunkState paramChunkState1, ChunkState paramChunkState2)
    {
      this.delay = paramLong;
      this.failNextState = paramChunkState1;
      this.successNextState = paramChunkState2;
    }

    public long getDelay()
    {
      return this.delay;
    }

    public ChunkState getNextState(boolean paramBoolean)
    {
      if ((this == FINISHED_STATE) || (this == ERRORED_OUT_STATE));
      while (true)
      {
        return this;
        if (!paramBoolean)
        {
          if (this == STATE_RETRY)
          {
            int i = Settings.getInt("lmtt.chunk_retries", 10);
            this.retryCount = (1 + this.retryCount);
            if ((this.retryCount >= i) && (i >= 0))
              continue;
            this.delay = (2L * this.delay);
            continue;
          }
          this = this.failNextState;
          continue;
        }
        this.delay = 120000L;
        this = this.successNextState;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.lmtt.LMTTChunkComm
 * JD-Core Version:    0.6.0
 */