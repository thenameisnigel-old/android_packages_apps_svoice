package com.vlingo.sdk.internal.http.custom;

import com.vlingo.sdk.internal.util.CompressUtils;
import com.vlingo.sdk.internal.util.StringUtils;
import java.io.DataOutputStream;
import java.io.IOException;

public class MPOutputStream
{
  private static final String ivEndLine = "\r\n";
  private String ivBoundary;
  private DataOutputStream ivDout;

  public MPOutputStream(DataOutputStream paramDataOutputStream, String paramString)
    throws IOException
  {
    if (paramDataOutputStream == null)
      throw new RuntimeException("Output stream is null");
    this.ivDout = paramDataOutputStream;
    this.ivBoundary = paramString;
  }

  private void write(String paramString)
    throws IOException
  {
    this.ivDout.write(StringUtils.convertStringToBytes(paramString));
  }

  public void close()
    throws IOException
  {
    this.ivDout.close();
  }

  public void flush()
    throws IOException
  {
    this.ivDout.flush();
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.ivDout.write(paramArrayOfByte, paramInt1, paramInt2);
  }

  public void writeBoundary()
    throws IOException
  {
    write(this.ivBoundary + "\r\n");
  }

  public void writeDataField(String paramString1, String paramString2, byte[] paramArrayOfByte)
    throws IOException
  {
    writeDataField(paramString1, paramString2, paramArrayOfByte, false);
  }

  public void writeDataField(String paramString1, String paramString2, byte[] paramArrayOfByte, boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean)
    {
      writeFieldHeader(paramString1, paramString2, "deflate");
      byte[] arrayOfByte = CompressUtils.deflate(paramArrayOfByte);
      write(arrayOfByte, 0, arrayOfByte.length);
    }
    while (true)
    {
      writeEndFieldValue();
      writeBoundary();
      return;
      writeFieldHeader(paramString1, paramString2, null);
      write(paramArrayOfByte, 0, paramArrayOfByte.length);
    }
  }

  public void writeEndFieldValue()
    throws IOException
  {
    write("\r\n");
  }

  public void writeEndHeader()
    throws IOException
  {
    write("\r\n");
  }

  public void writeField(String paramString1, String paramString2, String paramString3)
    throws IOException
  {
    writeFieldHeader(paramString1, paramString2, null);
    write(paramString3);
    writeEndFieldValue();
    writeBoundary();
  }

  public void writeFieldHeader(String paramString1, String paramString2, String paramString3)
    throws IOException
  {
    writeHeader("Content-Disposition", "form-data; name=\"" + paramString1 + "\"");
    writeHeader("Content-Type", paramString2);
    if (paramString3 != null)
      writeHeader("Content-Encoding", paramString3);
    writeEndHeader();
  }

  public void writeFileFieldHeader(String paramString1, String paramString2)
    throws IOException
  {
    writeHeader("Content-Disposition", "form-data; name=\"" + paramString1 + "\"; filename=\"" + paramString1 + "\"");
    writeHeader("Content-Type", paramString2);
    writeHeader("Content-Transfer-Encoding", "binary");
    writeEndHeader();
  }

  public void writeHeader(String paramString1, String paramString2)
    throws IOException
  {
    write(paramString1 + ":" + paramString2 + "\r\n");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.custom.MPOutputStream
 * JD-Core Version:    0.6.0
 */