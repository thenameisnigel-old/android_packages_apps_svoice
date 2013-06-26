package com.vlingo.core.internal.lmtt;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Data;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Audio.Playlists;
import android.util.Log;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.training.VLTrainer.TrainerItemType;
import com.vlingo.sdk.training.VLTrainerErrors;
import com.vlingo.sdk.training.VLTrainerListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

class LMTTManager
{
  private static final long WAIT_TIME_MS = 15000L;
  private ContentResolver mContentResolver;
  private HashMap<LMTTItem.LmttItemType, Integer> mDeviceItemCounts;
  private UpdateTask mRunningTask;
  private List<UpdateTask> readyList;
  private Timer timer;
  private List<UpdateTask> waitingList;

  LMTTManager(ContentResolver paramContentResolver)
  {
    this.mContentResolver = paramContentResolver;
    this.waitingList = new ArrayList();
    this.readyList = new ArrayList();
    this.timer = new Timer();
  }

  private static String getFolderName(String paramString)
  {
    if (paramString == null);
    for (String str = null; ; str = paramString.substring(0, paramString.lastIndexOf('/')))
      return str;
  }

  private UpdateTask getTask(List<UpdateTask> paramList, LmttUpdateType paramLmttUpdateType)
  {
    Iterator localIterator = paramList.iterator();
    UpdateTask localUpdateTask;
    do
    {
      if (!localIterator.hasNext())
        break;
      localUpdateTask = (UpdateTask)localIterator.next();
    }
    while (localUpdateTask.lmttUpdateType != paramLmttUpdateType);
    while (true)
    {
      return localUpdateTask;
      localUpdateTask = null;
    }
  }

  private int getUpdateChangedItemsList(LMTTItem.LmttItemType paramLmttItemType, HashMap<Integer, Integer> paramHashMap, ArrayList<LMTTItem> paramArrayList)
  {
    int i = 0;
    int j = 0;
    Object localObject1 = null;
    String str1 = null;
    try
    {
      Uri localUri;
      String[] arrayOfString;
      if (paramLmttItemType == LMTTItem.LmttItemType.TYPE_SONG)
      {
        localUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        arrayOfString = new String[7];
        arrayOfString[0] = "_id";
        arrayOfString[1] = "title";
        arrayOfString[2] = "artist";
        arrayOfString[3] = "composer";
        arrayOfString[4] = "album";
        arrayOfString[5] = "year";
        arrayOfString[6] = "_data";
        str1 = "is_music!=0";
      }
      while (true)
      {
        Cursor localCursor = this.mContentResolver.query(localUri, arrayOfString, str1, null, null);
        localObject1 = localCursor;
        if (localObject1 == null)
        {
          k = 0;
          if (localObject1 != null);
          try
          {
            localObject1.close();
            while (true)
            {
              return k;
              if (paramLmttItemType == LMTTItem.LmttItemType.TYPE_PLAYLIST)
              {
                localUri = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;
                arrayOfString = new String[2];
                arrayOfString[0] = "_id";
                arrayOfString[1] = "name";
                break;
              }
              if (paramLmttItemType == LMTTItem.LmttItemType.TYPE_CONTACT)
              {
                localUri = ContactsContract.Data.CONTENT_URI;
                arrayOfString = new String[3];
                arrayOfString[0] = "_id";
                arrayOfString[1] = "data2";
                arrayOfString[2] = "data3";
                str1 = "mimetype='vnd.android.cursor.item/name' AND in_visible_group=1";
                break;
              }
              k = -1;
              if (0 == 0)
                continue;
              try
              {
                null.close();
              }
              catch (Exception localException4)
              {
                Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException4));
              }
            }
          }
          catch (Exception localException8)
          {
            while (true)
              Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException8));
          }
        }
      }
      if (!localObject1.moveToFirst())
      {
        j = 1;
        break label871;
        do
        {
          n = localObject1.getInt(0);
          if (paramLmttItemType != LMTTItem.LmttItemType.TYPE_SONG)
            break label631;
          str5 = localObject1.getString(1);
          if (!StringUtils.isNullOrWhiteSpace(str5))
            break;
        }
        while (localObject1.moveToNext());
        Iterator localIterator = paramHashMap.keySet().iterator();
        while (localIterator.hasNext())
        {
          i2 = ((Integer)localIterator.next()).intValue();
          localObject4 = null;
          switch (2.$SwitchMap$com$vlingo$core$internal$lmtt$LMTTItem$LmttItemType[paramLmttItemType.ordinal()])
          {
          default:
            paramArrayList.add(localObject4);
            m++;
            int i3 = m % 10;
            if (i3 != 0)
              continue;
            try
            {
              Thread.sleep(10L);
            }
            catch (Exception localException7)
            {
              Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException7));
            }
          case 1:
          case 2:
          case 3:
          }
        }
      }
    }
    catch (Exception localException2)
    {
      while (true)
      {
        String str5;
        Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException2));
        k = -1;
        if (localObject1 == null)
          continue;
        try
        {
          localObject1.close();
        }
        catch (Exception localException3)
        {
          Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException3));
        }
        continue;
        localObject3 = new LMTTSongItem(str5, localObject1.getString(2), localObject1.getString(3), localObject1.getString(4), "", localObject1.getInt(5), getFolderName(localObject1.getString(6)), n, LMTTItem.ChangeType.NOCHANGE);
        i++;
        localInteger = (Integer)paramHashMap.remove(Integer.valueOf(n));
        if (localInteger != null)
          break;
        ((LMTTItem)localObject3).setChangeType(LMTTItem.ChangeType.INSERT);
        paramArrayList.add(localObject3);
        m++;
        int i1 = m % 10;
        if (i1 != 0)
          continue;
        try
        {
          Thread.sleep(10L);
        }
        catch (Exception localException5)
        {
          Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException5));
        }
      }
    }
    finally
    {
      while (true)
      {
        int k;
        int n;
        int i2;
        Object localObject4;
        Object localObject3;
        Integer localInteger;
        if (localObject1 != null);
        try
        {
          localObject1.close();
          throw localObject2;
          label631: if (paramLmttItemType == LMTTItem.LmttItemType.TYPE_PLAYLIST)
          {
            String str4 = localObject1.getString(1);
            if (StringUtils.isNullOrWhiteSpace(str4))
              continue;
            localObject3 = new LMTTPlaylistItem(str4, n, LMTTItem.ChangeType.NOCHANGE);
            i++;
            continue;
          }
          String str2 = localObject1.getString(1);
          String str3 = localObject1.getString(2);
          if ((StringUtils.isNullOrWhiteSpace(str2)) && (StringUtils.isNullOrWhiteSpace(str3)))
            continue;
          localObject3 = new LMTTContactItem(str2, str3, n, LMTTItem.ChangeType.NOCHANGE);
          i++;
          continue;
          if (localInteger.intValue() == localObject3.hashCode())
            continue;
          ((LMTTItem)localObject3).setChangeType(LMTTItem.ChangeType.UPDATE);
          paramArrayList.add(localObject3);
          continue;
          localObject4 = new LMTTContactItem(i2, LMTTItem.ChangeType.DELETE);
          continue;
          localObject4 = new LMTTSongItem(i2, LMTTItem.ChangeType.DELETE);
          continue;
          localObject4 = new LMTTPlaylistItem(i2, LMTTItem.ChangeType.DELETE);
          continue;
          if (localObject1 != null);
          try
          {
            localObject1.close();
            k = i;
          }
          catch (Exception localException6)
          {
            while (true)
              Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException6));
          }
        }
        catch (Exception localException1)
        {
          while (true)
            Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException1));
        }
        label871: int m = 0;
        if (j != 0)
          continue;
      }
    }
  }

  private static boolean isUriValid(Uri paramUri, ContentResolver paramContentResolver)
  {
    Cursor localCursor = paramContentResolver.query(paramUri, null, null, null, null);
    if (localCursor == null);
    for (int i = 0; ; i = 1)
    {
      return i;
      if (localCursor == null)
        continue;
      localCursor.close();
    }
  }

  private void makeReady(UpdateTask paramUpdateTask)
  {
    monitorenter;
    try
    {
      this.waitingList.remove(paramUpdateTask);
      this.readyList.add(paramUpdateTask);
      runNextTask();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private void runNextTask()
  {
    monitorenter;
    try
    {
      if ((this.mRunningTask == null) && (this.readyList.size() > 0))
      {
        this.mRunningTask = ((UpdateTask)this.readyList.remove(0));
        new Thread(new Runnable()
        {
          public void run()
          {
            LMTTManager.UpdateTask.access$400(LMTTManager.this.mRunningTask);
          }
        }).start();
      }
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  void fireUpdate(LmttUpdateType paramLmttUpdateType, boolean paramBoolean1, boolean paramBoolean2)
  {
    monitorenter;
    while (true)
    {
      UpdateTask localUpdateTask1;
      try
      {
        localUpdateTask1 = new UpdateTask(paramLmttUpdateType, paramBoolean2, null);
        UpdateTask localUpdateTask2 = getTask(this.readyList, localUpdateTask1.lmttUpdateType);
        if (localUpdateTask2 == null)
          continue;
        if (!localUpdateTask1.clearLMTT)
          continue;
        UpdateTask.access$202(localUpdateTask2, true);
        return;
        UpdateTask localUpdateTask3 = getTask(this.waitingList, localUpdateTask1.lmttUpdateType);
        if (localUpdateTask3 == null)
          continue;
        localUpdateTask3.cancel();
        this.waitingList.remove(localUpdateTask3);
        if (!localUpdateTask3.clearLMTT)
          continue;
        UpdateTask.access$202(localUpdateTask1, true);
        if (paramBoolean1)
        {
          this.readyList.add(localUpdateTask1);
          runNextTask();
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      this.waitingList.add(localUpdateTask1);
      this.timer.schedule(localUpdateTask1, 15000L);
    }
  }

  public static enum LmttUpdateType
  {
    static
    {
      LMTT_CONTACT_UPDATE = new LmttUpdateType("LMTT_CONTACT_UPDATE", 1);
      LMTT_LANGUAGE_UPDATE = new LmttUpdateType("LMTT_LANGUAGE_UPDATE", 2);
      LmttUpdateType[] arrayOfLmttUpdateType = new LmttUpdateType[3];
      arrayOfLmttUpdateType[0] = LMTT_MUSIC_UPDATE;
      arrayOfLmttUpdateType[1] = LMTT_CONTACT_UPDATE;
      arrayOfLmttUpdateType[2] = LMTT_LANGUAGE_UPDATE;
      $VALUES = arrayOfLmttUpdateType;
    }
  }

  private class UpdateTask extends TimerTask
    implements VLTrainerListener
  {
    private ArrayList<LMTTItem> changedItems;
    private boolean clearLMTT;
    private boolean isCancelled;
    private LMTTManager.LmttUpdateType lmttUpdateType;
    private int retryCount;

    private UpdateTask(LMTTManager.LmttUpdateType paramBoolean, boolean arg3)
    {
      boolean bool;
      this.clearLMTT = bool;
      this.lmttUpdateType = paramBoolean;
      this.retryCount = 0;
    }

    private UpdateTask(LMTTManager.LmttUpdateType paramBoolean, boolean paramInt, int arg4)
    {
      this.clearLMTT = paramInt;
      this.lmttUpdateType = paramBoolean;
      int i;
      this.retryCount = i;
    }

    private void end()
    {
      LMTTManager.access$302(LMTTManager.this, null);
      LMTTManager.this.runNextTask();
    }

    private void executeTask()
    {
      LMTTManager.access$502(LMTTManager.this, new HashMap());
      this.changedItems = new ArrayList();
      boolean bool = true;
      LMTTItem.LmttItemType[] arrayOfLmttItemType1;
      int j;
      LMTTItem.LmttItemType localLmttItemType;
      HashMap localHashMap;
      if (this.lmttUpdateType == LMTTManager.LmttUpdateType.LMTT_MUSIC_UPDATE)
      {
        arrayOfLmttItemType1 = new LMTTItem.LmttItemType[2];
        arrayOfLmttItemType1[0] = LMTTItem.LmttItemType.TYPE_PLAYLIST;
        arrayOfLmttItemType1[1] = LMTTItem.LmttItemType.TYPE_SONG;
        LMTTItem.LmttItemType[] arrayOfLmttItemType2 = arrayOfLmttItemType1;
        int i = arrayOfLmttItemType2.length;
        j = 0;
        if (j >= i)
          break label201;
        localLmttItemType = arrayOfLmttItemType2[j];
        if (this.clearLMTT)
          LMTTDBUtil.clearLMTTTable(localLmttItemType);
        localHashMap = LMTTDBUtil.getSynchedItems(localLmttItemType);
        if (localHashMap != null)
          break label141;
        retry();
      }
      while (true)
      {
        return;
        if (this.lmttUpdateType == LMTTManager.LmttUpdateType.LMTT_CONTACT_UPDATE)
        {
          arrayOfLmttItemType1 = new LMTTItem.LmttItemType[1];
          arrayOfLmttItemType1[0] = LMTTItem.LmttItemType.TYPE_CONTACT;
          break;
        }
        arrayOfLmttItemType1 = new LMTTItem.LmttItemType[0];
        break;
        label141: if ((bool) && (localHashMap.isEmpty()));
        for (bool = true; ; bool = false)
        {
          int k = LMTTManager.this.getUpdateChangedItemsList(localLmttItemType, localHashMap, this.changedItems);
          LMTTManager.this.mDeviceItemCounts.put(localLmttItemType, Integer.valueOf(k));
          j++;
          break;
        }
        label201: if (((this.changedItems != null) && (this.changedItems.size() > 0)) || (this.lmttUpdateType == LMTTManager.LmttUpdateType.LMTT_LANGUAGE_UPDATE))
        {
          LMTTChunkComm.getInstance(this.lmttUpdateType, this.changedItems, bool, this).transmitChunks();
          if (this.lmttUpdateType == LMTTManager.LmttUpdateType.LMTT_MUSIC_UPDATE)
          {
            if (bool);
            for (str = "lmtt-scheduled-music-full"; ; str = "lmtt-scheduled-music-partial")
            {
              UserLoggingEngine.getInstance().helpPageViewed(str);
              break;
            }
          }
          if (bool);
          for (String str = "lmtt-scheduled-contact-full"; ; str = "lmtt-scheduled-contact-partial")
            break;
        }
        end();
      }
    }

    private void retry()
    {
      end();
      LMTTManager.this.fireUpdate(this.lmttUpdateType, false, this.clearLMTT);
    }

    public boolean cancel()
    {
      this.isCancelled = true;
      return super.cancel();
    }

    public void onError(VLTrainerErrors paramVLTrainerErrors, String paramString)
    {
      if (this.lmttUpdateType == LMTTManager.LmttUpdateType.LMTT_MUSIC_UPDATE)
        UserLoggingEngine.getInstance().helpPageViewed("lmtt-comm-error-music-update");
      while (true)
      {
        end();
        return;
        if (this.lmttUpdateType == LMTTManager.LmttUpdateType.LMTT_CONTACT_UPDATE)
        {
          UserLoggingEngine.getInstance().helpPageViewed("lmtt-comm-error-contact-update");
          continue;
        }
        UserLoggingEngine.getInstance().helpPageViewed("lmtt-comm-error-language-update");
      }
    }

    public void onUpdateReceived(HashMap<VLTrainer.TrainerItemType, Integer> paramHashMap)
    {
      int i = 1;
      int j = 0;
      if ((paramHashMap == null) || (paramHashMap.isEmpty()))
      {
        j = 1;
        if (j == 0)
          break label317;
        if (this.lmttUpdateType != LMTTManager.LmttUpdateType.LMTT_MUSIC_UPDATE)
          break label306;
        UserLoggingEngine.getInstance().helpPageViewed("lmtt-reschedule-full-music-update");
        label39: retry();
      }
      while (true)
      {
        return;
        if (LMTTManager.this.mDeviceItemCounts == null)
          LMTTManager.access$502(LMTTManager.this, new HashMap());
        Iterator localIterator = LMTTManager.this.mDeviceItemCounts.keySet().iterator();
        label304: 
        while (localIterator.hasNext())
        {
          LMTTItem.LmttItemType localLmttItemType = (LMTTItem.LmttItemType)localIterator.next();
          int m = 0;
          int n = 0;
          if (localLmttItemType == LMTTItem.LmttItemType.TYPE_SONG)
            if (paramHashMap.get(VLTrainer.TrainerItemType.SONG) == null)
              i = 0;
          while (true)
          {
            if (m == n)
              break label304;
            i = 0;
            break;
            m = ((Integer)LMTTManager.this.mDeviceItemCounts.get(localLmttItemType)).intValue();
            n = ((Integer)paramHashMap.get(VLTrainer.TrainerItemType.SONG)).intValue();
            continue;
            if (localLmttItemType == LMTTItem.LmttItemType.TYPE_PLAYLIST)
            {
              if (paramHashMap.get(VLTrainer.TrainerItemType.PLAYLIST) == null)
              {
                i = 0;
                continue;
              }
              m = ((Integer)LMTTManager.this.mDeviceItemCounts.get(localLmttItemType)).intValue();
              n = ((Integer)paramHashMap.get(VLTrainer.TrainerItemType.PLAYLIST)).intValue();
              continue;
            }
            if (localLmttItemType != LMTTItem.LmttItemType.TYPE_CONTACT)
              continue;
            if (paramHashMap.get(VLTrainer.TrainerItemType.CONTACT) == null)
            {
              i = 0;
              continue;
            }
            m = ((Integer)LMTTManager.this.mDeviceItemCounts.get(localLmttItemType)).intValue();
            n = ((Integer)paramHashMap.get(VLTrainer.TrainerItemType.CONTACT)).intValue();
          }
        }
        label306: UserLoggingEngine.getInstance().helpPageViewed("lmtt-reschedule-full-contact-update");
        break label39;
        label317: if (i == 0)
        {
          this.clearLMTT = true;
          LMTTManager localLMTTManager = LMTTManager.this;
          LMTTManager.LmttUpdateType localLmttUpdateType = this.lmttUpdateType;
          boolean bool = this.clearLMTT;
          int k = this.retryCount;
          this.retryCount = (k + 1);
          new UpdateTask(localLMTTManager, localLmttUpdateType, bool, k);
          if (this.lmttUpdateType == LMTTManager.LmttUpdateType.LMTT_MUSIC_UPDATE)
            UserLoggingEngine.getInstance().helpPageViewed("lmtt-reschedule-full-music-update");
          while (true)
          {
            retry();
            break;
            UserLoggingEngine.getInstance().helpPageViewed("lmtt-reschedule-full-contact-update");
          }
        }
        end();
      }
    }

    public void run()
    {
      synchronized (LMTTManager.this)
      {
        if (!this.isCancelled)
          LMTTManager.this.makeReady(this);
        return;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.lmtt.LMTTManager
 * JD-Core Version:    0.6.0
 */