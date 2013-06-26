package com.vlingo.sdk.internal.util;

import java.io.ByteArrayOutputStream;

public class NoCopyByteArrayOutputStream extends ByteArrayOutputStream
{
  public NoCopyByteArrayOutputStream()
  {
  }

  public NoCopyByteArrayOutputStream(int paramInt)
  {
    super(paramInt);
  }

  public NoCopyByteArrayOutputStream(byte[] paramArrayOfByte, int paramInt)
  {
    this.buf = paramArrayOfByte;
    this.count = paramInt;
  }

  public byte[] getByteArray()
  {
    return this.buf;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.util.NoCopyByteArrayOutputStream
 * JD-Core Version:    0.6.0
 */