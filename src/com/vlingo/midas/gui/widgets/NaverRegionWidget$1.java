package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.Toast;
import com.naver.api.security.client.MACManager;
import com.vlingo.midas.naver.NaverRegionItem;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

class NaverRegionWidget$1
  implements Runnable
{
  public void run()
  {
    String str1 = NaverRegionWidget.access$000(this.this$0).getMapImageUrl();
    if (str1 == null);
    while (true)
    {
      return;
      String str2 = str1.replace("defaul&", "default&").concat("&w=290&h=200");
      Object localObject = str2;
      Bitmap localBitmap;
      try
      {
        MACManager.initialize();
        String str4 = MACManager.getEncryptUrl(str2);
        localObject = str4;
        try
        {
          URL localURL = new URL((String)localObject);
          localBitmap = BitmapFactory.decodeStream(localURL.openStream());
          if (localBitmap != null)
            break label149;
          localBufferedReader = new BufferedReader(new InputStreamReader(localURL.openStream()));
          while (true)
          {
            String str3 = localBufferedReader.readLine();
            if (str3 == null)
              break;
            Toast.makeText(NaverRegionWidget.access$100(this.this$0), str3, 0).show();
          }
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
        }
      }
      catch (Exception localException1)
      {
        BufferedReader localBufferedReader;
        while (true)
          localException1.printStackTrace();
        localBufferedReader.close();
      }
      continue;
      label149: new Handler(this.this$0.getContext().getMainLooper()).postDelayed(new NaverRegionWidget.1.1(this, localBitmap), 500L);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.NaverRegionWidget.1
 * JD-Core Version:    0.6.0
 */