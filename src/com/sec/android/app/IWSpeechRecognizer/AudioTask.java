package com.sec.android.app.IWSpeechRecognizer;

import android.media.AudioRecord;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

class AudioTask
  implements Runnable
{
  static final int DEFAULT_BLOCK_SIZE = 160;
  public static final int cmdAlarm = 3;
  public static final int cmdCall = 2;
  public static final int cmdCamera = 7;
  public static final int cmdGallery = 8;
  public static final int cmdMusic = 4;
  public static final int cmdRadio = 5;
  public static final int cmdVedio = 6;
  public static final int cmdVoiceTalk_all = 0;
  public static final int cmdVoiceTalk_schedule = 1;
  private int AUDIO_RECORD_FOR_BARGE_IN;
  public short[] BargeinAct;
  public String TAG;
  public double THscore;
  private MMUIRecognizer aMMUIRecognizer = null;
  public int block_size = 0;
  public byte[] buf;
  public float[] cmResult;
  public boolean done = false;
  public File f;
  public Handler handler;
  public boolean isMakePCM;
  public String loadNameList;
  public String loadPath;
  public int mCommandType;
  public FileOutputStream mFileOutputStream;
  public int mLanguage;
  private IWSpeechRecognizerListener m_listener;
  public String modelPath;
  public int numRecogResult;
  public LinkedBlockingQueue<short[]> q = null;
  public AudioRecord rec = null;
  public short[] speech;
  public String[] strResult;
  private int totalReadCount;
  public String[] utfResult;
  public String wordListPath;

  AudioTask(IWSpeechRecognizerListener paramIWSpeechRecognizerListener, String paramString, int paramInt1, int paramInt2)
  {
    float[] arrayOfFloat = new float[1];
    arrayOfFloat[0] = 0.0F;
    this.cmResult = arrayOfFloat;
    this.strResult = new String[3];
    this.utfResult = new String[1];
    short[] arrayOfShort = new short[1];
    arrayOfShort[0] = -1;
    this.BargeinAct = arrayOfShort;
    this.numRecogResult = 0;
    this.speech = null;
    this.TAG = "AudioTask";
    this.isMakePCM = false;
    this.m_listener = null;
    this.loadPath = null;
    this.mCommandType = 0;
    this.loadNameList = "nameList_voicetalk_all.txt";
    this.mLanguage = 1;
    this.modelPath = "/sasr/eng/16k/param";
    this.wordListPath = "/sasr/eng/16k/";
    this.AUDIO_RECORD_FOR_BARGE_IN = 11;
    this.totalReadCount = 0;
    this.f = null;
    this.mFileOutputStream = null;
    this.THscore = -1.5D;
    this.handler = new AudioTask.1(this);
    init(new LinkedBlockingQueue(), 160, paramIWSpeechRecognizerListener, paramString, paramInt1, paramInt2);
  }

  public static short twoBytesToShort(byte paramByte1, byte paramByte2)
  {
    return (short)(paramByte1 & 0xFF | paramByte2 << 8);
  }

  public void SendHandlerMessage(String[] paramArrayOfString)
  {
    Message localMessage = this.handler.obtainMessage();
    Bundle localBundle = new Bundle();
    localBundle.putStringArray("recognition_result", paramArrayOfString);
    localMessage.setData(localBundle);
    this.handler.sendMessage(localMessage);
  }

  public int getBlockSize()
  {
    return this.block_size;
  }

  public LinkedBlockingQueue<short[]> getQueue()
  {
    return this.q;
  }

  void init(LinkedBlockingQueue<short[]> paramLinkedBlockingQueue, int paramInt1, IWSpeechRecognizerListener paramIWSpeechRecognizerListener, String paramString, int paramInt2, int paramInt3)
  {
    Log.e(this.TAG, "init()");
    Log.e(this.TAG, "command : " + paramInt2);
    Log.e(this.TAG, "Language : " + paramInt3);
    this.done = false;
    this.q = paramLinkedBlockingQueue;
    this.block_size = paramInt1;
    this.mCommandType = paramInt2;
    this.rec = new AudioRecord(this.AUDIO_RECORD_FOR_BARGE_IN, 16000, 16, 2, 8192);
    this.m_listener = paramIWSpeechRecognizerListener;
    this.loadPath = paramString;
    this.mLanguage = paramInt3;
    this.BargeinAct[0] = -1;
    this.totalReadCount = 0;
    if (this.isMakePCM)
      this.f = new File("/data/log", "testPCM.pcm");
    try
    {
      this.mFileOutputStream = new FileOutputStream(this.f, true);
      this.aMMUIRecognizer = IWSpeechRecognizerWrapper.getInstance();
      this.speech = new short[' '];
      this.buf = new byte[2 * this.block_size];
      this.aMMUIRecognizer.SetSRLanguage(paramInt3);
      setFilePath(paramInt3);
      Log.e(this.TAG, "Load Model");
      int i = this.aMMUIRecognizer.SASRLoadModel(this.loadPath + this.modelPath);
      Log.e(this.TAG, "Load Model result : " + i);
      switch (this.mCommandType)
      {
      default:
        Log.e(this.TAG, "Load Wordlist");
        int j = this.aMMUIRecognizer.SASRInit(this.loadPath + this.wordListPath + this.loadNameList);
        Log.e(this.TAG, "Load Wordlist result : " + j);
        this.aMMUIRecognizer.SASRReset();
        return;
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      while (true)
      {
        localFileNotFoundException.printStackTrace();
        continue;
        this.loadNameList = "nameList_voicetalk_all.txt";
        continue;
        this.loadNameList = "nameList_voicetalk_schedule.txt";
        continue;
        this.loadNameList = "nameList_call.txt";
        continue;
        this.loadNameList = "nameList_alarm.txt";
        continue;
        this.loadNameList = "nameList_music.txt";
        continue;
        this.loadNameList = "nameList_radio.txt";
        continue;
        this.loadNameList = "nameList_video.txt";
        continue;
        this.loadNameList = "nameList_camera.txt";
        continue;
        this.loadNameList = "nameList_gallery.txt";
      }
    }
  }

  int readByteBlock()
  {
    int i = this.rec.read(this.buf, 0, this.buf.length);
    if (this.totalReadCount % 20 == 0)
      Log.e(this.TAG, "nshorts = " + i);
    this.totalReadCount = (1 + this.totalReadCount);
    for (int j = 0; j < 320; j += 2)
      this.speech[(j / 2)] = twoBytesToShort(this.buf[j], this.buf[(j + 1)]);
    if ((i <= 0) || (this.isMakePCM));
    try
    {
      this.mFileOutputStream.write(this.buf);
      int k = 0;
      if ((this.aMMUIRecognizer != null) && (this.totalReadCount > 50))
        k = this.aMMUIRecognizer.RECThread(this.speech);
      if ((k == -2) && (this.aMMUIRecognizer != null))
      {
        Log.e(this.TAG, "Barge-in : Too long input so Reset");
        this.aMMUIRecognizer.ResetFx();
        this.aMMUIRecognizer.SASRReset();
      }
      if ((k == 2) && (this.aMMUIRecognizer != null))
      {
        this.aMMUIRecognizer.ResetFx();
        this.numRecogResult = this.aMMUIRecognizer.SASRDoRecognition(this.cmResult, this.strResult, this.loadPath + "/sasr/input.txt", this.BargeinAct, this.utfResult);
        this.strResult[0] = this.strResult[0].replace('_', ' ');
        if ((this.mLanguage == 0) || (this.mLanguage == 2))
        {
          this.utfResult[0] = this.utfResult[0].replace('_', ' ');
          this.strResult[0] = this.utfResult[0];
        }
        Log.e(this.TAG, "numResult[0] : " + this.cmResult[0]);
        Log.e(this.TAG, "strResult[0] : " + this.strResult[0]);
        Log.e(this.TAG, "BargeinAct[0] : " + this.BargeinAct[0]);
        if ((this.mCommandType == 3) && (this.BargeinAct[0] == 2))
        {
          this.THscore = -1.8D;
          Log.e(this.TAG, "THscore : " + this.THscore);
          if (this.cmResult[0] <= this.THscore)
            break label560;
          SendHandlerMessage(this.strResult);
          this.aMMUIRecognizer.SASRReset();
        }
      }
      else
      {
        return i;
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        localIOException.printStackTrace();
        continue;
        if (this.mCommandType == 7)
        {
          this.THscore = -1.0D;
          continue;
        }
        this.THscore = -1.5D;
        continue;
        label560: this.strResult[0] = "TH-Reject";
        this.BargeinAct[0] = -1;
        SendHandlerMessage(this.strResult);
      }
    }
  }

  public void run()
  {
    Log.e(this.TAG, "Call rec.startRecording start");
    this.rec.startRecording();
    Log.e(this.TAG, "Call startRecording end");
    while ((!this.done) && (readByteBlock() > 0));
    Log.e(this.TAG, "Call rec.stop start");
    this.rec.stop();
    Log.e(this.TAG, "Call rec.stop end");
    Log.e(this.TAG, "Call rec.release start");
    this.rec.release();
    Log.e(this.TAG, "Call rec.release end");
    if (this.aMMUIRecognizer != null)
    {
      Log.e(this.TAG, "SASRClose start");
      this.aMMUIRecognizer.SASRClose();
      Log.e(this.TAG, "SASRClose end");
    }
    this.aMMUIRecognizer = null;
    this.m_listener = null;
  }

  public void setBlockSize(int paramInt)
  {
    this.block_size = paramInt;
  }

  public void setFilePath(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    }
    while (true)
    {
      return;
      this.modelPath = "/sasr/kor/16k/param";
      this.wordListPath = "/sasr/kor/16k/";
      continue;
      this.modelPath = "/sasr/eng/16k/param";
      this.wordListPath = "/sasr/eng/16k/";
      continue;
      this.modelPath = "/sasr/chi/16k/param";
      this.wordListPath = "/sasr/chi/16k/";
      continue;
      this.modelPath = "/sasr/spa/16k/param";
      this.wordListPath = "/sasr/spa/16k/";
      continue;
      this.modelPath = "/sasr/fra/16k/param";
      this.wordListPath = "/sasr/fra/16k/";
      continue;
      this.modelPath = "/sasr/ger/16k/param";
      this.wordListPath = "/sasr/ger/16k/";
      continue;
      this.modelPath = "/sasr/ita/16k/param";
      this.wordListPath = "/sasr/ita/16k/";
      continue;
      this.modelPath = "/sasr/jap/16k/param";
      this.wordListPath = "/sasr/jap/16k/";
      continue;
      this.modelPath = "/sasr/rus/16k/param";
      this.wordListPath = "/sasr/rus/16k/";
    }
  }

  public void stop()
  {
    Log.e(this.TAG, "stop()");
    this.done = true;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.app.IWSpeechRecognizer.AudioTask
 * JD-Core Version:    0.6.0
 */