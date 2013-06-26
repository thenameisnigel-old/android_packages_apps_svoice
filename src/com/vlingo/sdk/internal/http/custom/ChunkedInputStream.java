package com.vlingo.sdk.internal.http.custom;

import java.io.IOException;
import java.io.InputStream;

public class ChunkedInputStream extends InputStream
{
  private static final int READ_BYTE = 0;
  private static final int READ_CR = 1;
  private static final int READ_EOF = -1;
  private static final int READ_LF = 2;
  private int currChunkRead;
  private int currChunkSize = -1;
  private InputStream ivIn;

  public ChunkedInputStream(InputStream paramInputStream)
  {
    this.ivIn = paramInputStream;
  }

  private boolean ensureChunkAvailable()
    throws IOException
  {
    int i = 1;
    if ((this.currChunkSize == -1) || (this.currChunkRead >= this.currChunkSize))
    {
      String str = readLine();
      if (str.length() == 0)
        throw new IOException("Missing chunk header");
      this.currChunkSize = Integer.valueOf(str, 16).intValue();
      this.currChunkRead = 0;
      if (this.currChunkSize == 0)
        readLine();
      if (this.currChunkSize <= 0)
        break label83;
    }
    while (true)
    {
      return i;
      label83: i = 0;
    }
  }

  private String readLine()
    throws IOException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    do
    {
      int j = this.ivIn.read();
      switch (j)
      {
      default:
        i = 0;
        localStringBuffer.append(j);
      case 13:
      case 10:
      case -1:
      }
    }
    while ((i != -1) && (i != 2));
    return localStringBuffer.toString();
    if (i == 0)
    {
      i = 1;
      label82: if (i != 1)
        break label98;
    }
    label98: 
    while (true)
    {
      i = -1;
      break;
      i = 0;
      break label82;
    }
  }

  public void close()
    throws IOException
  {
    this.ivIn.close();
  }

  public int read()
    throws IOException
  {
    int i;
    if (!ensureChunkAvailable())
      i = -1;
    while (true)
    {
      return i;
      i = this.ivIn.read();
      this.currChunkRead = (1 + this.currChunkRead);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.custom.ChunkedInputStream
 * JD-Core Version:    0.6.0
 */