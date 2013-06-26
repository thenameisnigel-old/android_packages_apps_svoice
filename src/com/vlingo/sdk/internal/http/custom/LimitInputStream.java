package com.vlingo.sdk.internal.http.custom;

import java.io.IOException;
import java.io.InputStream;

public class LimitInputStream extends InputStream
{
  private int ivMax;
  private InputStream ivOrg;
  private int ivRead;

  public LimitInputStream(InputStream paramInputStream, int paramInt)
  {
    this.ivMax = paramInt;
    this.ivOrg = paramInputStream;
  }

  public void close()
    throws IOException
  {
    this.ivOrg.close();
  }

  public int read()
    throws IOException
  {
    if (this.ivMax == this.ivRead);
    for (int i = -1; ; i = this.ivOrg.read())
    {
      return i;
      this.ivRead = (1 + this.ivRead);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.custom.LimitInputStream
 * JD-Core Version:    0.6.0
 */