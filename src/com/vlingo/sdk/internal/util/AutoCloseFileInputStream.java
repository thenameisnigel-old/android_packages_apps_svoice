package com.vlingo.sdk.internal.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AutoCloseFileInputStream extends FileInputStream
{
  private boolean isClosed = false;

  public AutoCloseFileInputStream(String paramString)
    throws FileNotFoundException
  {
    super(paramString);
  }

  public void close()
    throws IOException
  {
    if (this.isClosed);
    while (true)
    {
      return;
      super.close();
      this.isClosed = true;
    }
  }

  public int read()
    throws IOException
  {
    int i = super.read();
    if (i == -1)
      close();
    return i;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = super.read(paramArrayOfByte, paramInt1, paramInt2);
    if (i == -1)
      close();
    return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.util.AutoCloseFileInputStream
 * JD-Core Version:    0.6.0
 */