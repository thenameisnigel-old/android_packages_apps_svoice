package com.vlingo.sdk.internal.recognizer.sr3;

import com.vlingo.sdk.internal.http.HttpUtil;
import com.vlingo.sdk.internal.http.custom.MPOutputStream;
import com.vlingo.sdk.internal.http.custom.VHttpConnection;
import com.vlingo.sdk.internal.net.ConnectionProvider;
import com.vlingo.sdk.internal.net.HttpConnection;
import com.vlingo.sdk.internal.recognizer.ClientMeta;
import com.vlingo.sdk.internal.recognizer.SRContext;
import com.vlingo.sdk.internal.recognizer.SoftwareMeta;
import com.vlingo.sdk.internal.settings.Settings;
import com.vlingo.sdk.internal.vlservice.VLServiceUtil;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

public class StandardHttpConnection extends HttpConnectionAdapter
{
  private HttpConnection ivCon;
  private InputStream ivDin;
  private DataOutputStream ivDout;
  private MPOutputStream ivMPOut;

  StandardHttpConnection(HttpConnection paramHttpConnection, int paramInt)
    throws IOException
  {
    super(paramInt);
    this.ivCon = paramHttpConnection;
  }

  public static HttpConnectionAdapter newConnection(ConnectionProvider paramConnectionProvider, String paramString1, String paramString2, ClientMeta paramClientMeta, SoftwareMeta paramSoftwareMeta, Hashtable<String, String> paramHashtable, int paramInt, SRContext paramSRContext)
    throws IOException
  {
    Hashtable localHashtable1 = new Hashtable();
    VLServiceUtil.addStandardVlingoHttpHeaders(localHashtable1, paramClientMeta, paramSoftwareMeta, paramSRContext);
    Hashtable localHashtable2 = VLServiceUtil.addVLServiceCookies(paramHashtable, HttpUtil.getDomain(paramString2), HttpUtil.getPath(paramString2));
    return new StandardHttpConnection(HttpUtil.newHttpConnection(paramConnectionProvider, paramString1, paramString2, Settings.isAsrKeepAliveEnabled(), localHashtable2, localHashtable1, paramClientMeta, paramSoftwareMeta), paramInt);
  }

  public void close()
    throws IOException
  {
    if (this.ivDin != null);
    try
    {
      this.ivDin.close();
      label14: if (this.ivMPOut != null);
      try
      {
        this.ivMPOut.close();
        label28: if (this.ivDout != null);
        try
        {
          this.ivDout.close();
          label42: if (this.ivCon != null);
          try
          {
            this.ivCon.close();
            label58: return;
          }
          catch (Exception localException1)
          {
            break label58;
          }
        }
        catch (Exception localException2)
        {
          break label42;
        }
      }
      catch (Exception localException3)
      {
        break label28;
      }
    }
    catch (Exception localException4)
    {
      break label14;
    }
  }

  public void finishRequest()
    throws IOException
  {
  }

  public void finishResponse()
    throws IOException
  {
    close();
  }

  public VHttpConnection getConnection()
  {
    return null;
  }

  public InputStream getIn()
    throws IOException
  {
    if (this.ivDin == null)
      this.ivDin = this.ivCon.openDataInputStream();
    return this.ivDin;
  }

  public MPOutputStream getOut()
    throws IOException
  {
    if (this.ivMPOut == null)
    {
      this.ivDout = this.ivCon.openDataOutputStream();
      this.ivMPOut = new MPOutputStream(this.ivDout, ivBoundary);
    }
    return this.ivMPOut;
  }

  public int getRequestID()
  {
    return this.ivRequestID;
  }

  public String getResponseHeaderField(int paramInt)
    throws IOException
  {
    return this.ivCon.getHeaderField(paramInt);
  }

  public String getResponseHeaderFieldKey(int paramInt)
    throws IOException
  {
    return this.ivCon.getHeaderFieldKey(paramInt);
  }

  public void setRequestHeader(String paramString1, String paramString2)
    throws IOException
  {
    this.ivCon.setRequestProperty(paramString1, paramString2);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.sr3.StandardHttpConnection
 * JD-Core Version:    0.6.0
 */