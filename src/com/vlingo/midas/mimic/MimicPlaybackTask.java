package com.vlingo.midas.mimic;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.AudioTrack;
import com.samsung.chatbot.ChatbotFilter;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.sdk.recognition.VLRecognitionListener;
import com.vlingo.sdk.recognition.VLRecognitionStates;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class MimicPlaybackTask extends MimicBaseTask
{
  public MimicPlaybackTask(Context paramContext)
  {
    super(paramContext);
  }

  public void run()
  {
    getVlRecognitionListener().onStateChanged(VLRecognitionStates.THINKING);
    new WriterThread(this).start();
  }

  private static class WriterThread extends Thread
  {
    final int bufferSize = 320;
    private MimicPlaybackTask mimicTask;

    public WriterThread(MimicPlaybackTask paramMimicPlaybackTask)
    {
      this.mimicTask = paramMimicPlaybackTask;
    }

    public void run()
    {
      try
      {
        localAudioTrack = new AudioTrack(3, 16000, 4, 2, 320, 1);
        localFileInputStream = new FileInputStream(this.mimicTask.filePlay);
        String str = this.mimicTask.context.getPackageManager().getApplicationInfo(this.mimicTask.context.getPackageName(), 0).dataDir + "/lib";
        int i = Integer.parseInt(Settings.getString("mimic_mode", "-1"));
        if (i == -1)
          i = 1 + Math.abs(new Random().nextInt()) % 5;
        ChatbotFilter localChatbotFilter = new ChatbotFilter();
        localChatbotFilter.initialize(str);
        short[] arrayOfShort = new short[' '];
        for (int j = 0; j < 160; j++)
          arrayOfShort[j] = 0;
        byte[] arrayOfByte = new byte[320];
        localAudioTrack.play();
        while ((!Thread.currentThread().isInterrupted()) && (localFileInputStream.available() > 0) && (!this.mimicTask.isCancelled()))
        {
          int k = localFileInputStream.read(arrayOfByte, 0, 320);
          for (int m = 0; m < k / 2; m++)
            arrayOfShort[m] = (short)((arrayOfByte[(1 + m * 2)] << 8) + arrayOfByte[(m * 2)]);
          localChatbotFilter.run(arrayOfShort, k / 2, i);
          localAudioTrack.write(arrayOfShort, 0, k / 2);
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        AudioTrack localAudioTrack;
        FileInputStream localFileInputStream;
        localNameNotFoundException.printStackTrace();
        while (true)
        {
          this.mimicTask.notifyFinished();
          return;
          localFileInputStream.close();
          localAudioTrack.stop();
          localAudioTrack.release();
        }
      }
      catch (IOException localIOException)
      {
        while (true)
          localIOException.printStackTrace();
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.mimic.MimicPlaybackTask
 * JD-Core Version:    0.6.0
 */