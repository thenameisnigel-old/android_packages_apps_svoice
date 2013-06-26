package com.weibo.sdk.android.util;

import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public final class BitmapHelper
{
  public static int getSampleSizeAutoFitToScreen(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramInt2 == 0) || (paramInt1 == 0));
    for (int i = 1; ; i = Math.min(Math.max(paramInt3 / paramInt1, paramInt4 / paramInt2), Math.max(paramInt4 / paramInt1, paramInt3 / paramInt2)))
      return i;
  }

  public static int getSampleSizeOfNotTooLarge(Rect paramRect)
  {
    double d = 2.0D * (paramRect.width() * paramRect.height()) / 5242880.0D;
    if (d >= 1.0D);
    for (int i = (int)d; ; i = 1)
      return i;
  }

  public static boolean makesureSizeNotTooLarge(Rect paramRect)
  {
    if (2 * (paramRect.width() * paramRect.height()) > 5242880);
    for (int i = 0; ; i = 1)
      return i;
  }

  public static boolean verifyBitmap(InputStream paramInputStream)
  {
    int i;
    if (paramInputStream == null)
      i = 0;
    while (true)
    {
      return i;
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = true;
      if ((paramInputStream instanceof BufferedInputStream))
        BitmapFactory.decodeStream(paramInputStream, null, localOptions);
      try
      {
        paramInputStream.close();
        if ((localOptions.outHeight > 0) && (localOptions.outWidth > 0))
        {
          i = 1;
          continue;
          paramInputStream = new BufferedInputStream(paramInputStream);
        }
      }
      catch (IOException localIOException)
      {
        while (true)
          localIOException.printStackTrace();
        i = 0;
      }
    }
  }

  public static boolean verifyBitmap(String paramString)
  {
    try
    {
      boolean bool2 = verifyBitmap(new FileInputStream(paramString));
      bool1 = bool2;
      return bool1;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      while (true)
      {
        localFileNotFoundException.printStackTrace();
        boolean bool1 = false;
      }
    }
  }

  public static boolean verifyBitmap(byte[] paramArrayOfByte)
  {
    return verifyBitmap(new ByteArrayInputStream(paramArrayOfByte));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.util.BitmapHelper
 * JD-Core Version:    0.6.0
 */