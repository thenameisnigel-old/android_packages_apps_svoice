package com.vlingo.sdk.internal.recognizer.sr3;

import com.vlingo.sdk.internal.http.HttpUtil;
import com.vlingo.sdk.internal.http.URL;
import com.vlingo.sdk.internal.http.custom.HttpInteraction;
import com.vlingo.sdk.internal.http.custom.HttpRequest;
import com.vlingo.sdk.internal.http.custom.HttpResponse;
import com.vlingo.sdk.internal.http.custom.MPOutputStream;
import com.vlingo.sdk.internal.http.custom.MultiplexHttpConnection;
import com.vlingo.sdk.internal.http.custom.VHttpConnection;
import com.vlingo.sdk.internal.net.ConnectionProvider;
import com.vlingo.sdk.internal.recognizer.ClientMeta;
import com.vlingo.sdk.internal.recognizer.SRContext;
import com.vlingo.sdk.internal.recognizer.SoftwareMeta;
import com.vlingo.sdk.internal.settings.Settings;
import com.vlingo.sdk.internal.vlservice.VLServiceUtil;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;

public class ChunkingHttpConnection extends HttpConnectionAdapter
{
  private VHttpConnection ivCon;
  private InputStream ivDin;
  private DataOutputStream ivDout;
  private HttpInteraction ivInteraction;
  private MPOutputStream ivMPOut;

  ChunkingHttpConnection(VHttpConnection paramVHttpConnection, HttpInteraction paramHttpInteraction, int paramInt)
    throws IOException
  {
    super(paramInt);
    this.ivCon = paramVHttpConnection;
    this.ivInteraction = paramHttpInteraction;
  }

  private static ChunkingHttpConnection initConnection(VHttpConnection paramVHttpConnection, String paramString1, String paramString2, ClientMeta paramClientMeta, SoftwareMeta paramSoftwareMeta, Hashtable<String, String> paramHashtable, int paramInt, SRContext paramSRContext)
    throws IOException
  {
    HttpInteraction localHttpInteraction = paramVHttpConnection.openInteraction(paramString2);
    HttpRequest localHttpRequest = localHttpInteraction.getRequest();
    localHttpRequest.setMethod(paramString1);
    if (!Settings.isAsrKeepAliveEnabled())
      localHttpRequest.setHeader("Connection", "Close");
    Hashtable localHashtable1 = new Hashtable();
    VLServiceUtil.addStandardVlingoHttpHeaders(localHashtable1, paramClientMeta, paramSoftwareMeta, paramSRContext);
    Hashtable localHashtable2 = VLServiceUtil.addVLServiceCookies(paramHashtable, paramVHttpConnection.getHost(), localHttpRequest.getPath());
    Enumeration localEnumeration = localHashtable1.keys();
    while (localEnumeration.hasMoreElements())
    {
      String str2 = (String)localEnumeration.nextElement();
      localHttpRequest.setHeader(str2, (String)localHashtable1.get(str2));
    }
    String str1 = HttpUtil.getCookies(localHashtable2);
    if (str1.length() > 0)
      localHttpRequest.setHeader("Cookie", str1);
    return new ChunkingHttpConnection(paramVHttpConnection, localHttpInteraction, paramInt);
  }

  public static ChunkingHttpConnection newConnection(VHttpConnection paramVHttpConnection, String paramString1, String paramString2, ClientMeta paramClientMeta, SoftwareMeta paramSoftwareMeta, Hashtable<String, String> paramHashtable, int paramInt, SRContext paramSRContext)
    throws IOException
  {
    return initConnection(paramVHttpConnection, paramString1, paramString2, paramClientMeta, paramSoftwareMeta, paramHashtable, paramInt, paramSRContext);
  }

  public static ChunkingHttpConnection newConnection(ConnectionProvider paramConnectionProvider, String paramString, URL paramURL, ClientMeta paramClientMeta, SoftwareMeta paramSoftwareMeta, Hashtable<String, String> paramHashtable, int paramInt, SRContext paramSRContext)
    throws IOException
  {
    VHttpConnection localVHttpConnection = new VHttpConnection(paramURL);
    localVHttpConnection.open();
    return initConnection(localVHttpConnection, paramString, paramURL.path, paramClientMeta, paramSoftwareMeta, paramHashtable, paramInt, paramSRContext);
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
            label56: return;
          }
          catch (Exception localException1)
          {
            break label56;
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
    this.ivInteraction.getRequest().finish();
  }

  public void finishResponse()
    throws IOException
  {
    this.ivInteraction.getResponse().finish();
    if (!(this.ivCon instanceof MultiplexHttpConnection))
      close();
  }

  public VHttpConnection getConnection()
  {
    return this.ivCon;
  }

  public InputStream getIn()
    throws IOException
  {
    if (this.ivDin == null)
      this.ivDin = this.ivInteraction.getResponse().getInputStream();
    return this.ivDin;
  }

  public MPOutputStream getOut()
    throws IOException
  {
    if (this.ivMPOut == null)
    {
      this.ivDout = this.ivInteraction.getRequest().getOutputStream();
      this.ivMPOut = new MPOutputStream(this.ivDout, ivBoundary);
    }
    return this.ivMPOut;
  }

  public String getResponseHeaderField(int paramInt)
    throws IOException
  {
    String str1 = getResponseHeaderFieldKey(paramInt);
    if (str1 == null);
    for (String str2 = null; ; str2 = this.ivInteraction.getResponse().getHeaderValue(str1))
      return str2;
  }

  public String getResponseHeaderFieldKey(int paramInt)
    throws IOException
  {
    Enumeration localEnumeration = this.ivInteraction.getResponse().getHeaderNames();
    String str = null;
    for (int i = 0; ; i++)
    {
      if (i <= paramInt)
      {
        if (!localEnumeration.hasMoreElements())
          str = null;
      }
      else
        return str;
      str = (String)localEnumeration.nextElement();
    }
  }

  public void setRequestHeader(String paramString1, String paramString2)
    throws IOException
  {
    this.ivInteraction.getRequest().setHeader(paramString1, paramString2);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.sr3.ChunkingHttpConnection
 * JD-Core Version:    0.6.0
 */