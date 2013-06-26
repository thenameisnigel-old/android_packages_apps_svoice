package com.naver.api.security.client;

import com.naver.api.security.HmacUtil;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import javax.crypto.Mac;

public class MACManager
{
  private static final String KEY_FILENAME = "/NHNAPIGatewayKey.properties";
  private static Mac mac;

  public static String getEncryptUrl(String paramString)
    throws Exception
  {
    if (mac == null)
      initialize();
    return HmacUtil.makeEncryptUrl(mac, paramString);
  }

  public static void initialize()
    throws Exception
  {
    Properties localProperties = new Properties();
    InputStream localInputStream = null;
    try
    {
      localInputStream = MACManager.class.getResourceAsStream("/NHNAPIGatewayKey.properties");
      localProperties.load(localInputStream);
      mac = HmacUtil.getMac((String)localProperties.elements().nextElement());
      return;
    }
    finally
    {
      if (localInputStream != null)
        localInputStream.close();
    }
    throw localObject;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.naver.api.security.client.MACManager
 * JD-Core Version:    0.6.0
 */