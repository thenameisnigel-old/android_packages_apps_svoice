package com.naver.api.security.client;

import com.naver.api.security.HmacUtil;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import javax.crypto.Mac;

public class ServiceMACManager
{
  private static final String EXTSERVICE = "__extservice__";
  private static final String KEY_FILENAME = "/NHNAPIGatewayKey.properties";
  private static Mac mac;
  private static String serviceId;

  public static String getEncryptUrl(String paramString)
    throws Exception
  {
    if (mac == null)
      initialize();
    if (!paramString.contains("__extservice__"))
      throw new Exception("wrong url : '__extservice__' not exists");
    String str = paramString.replaceAll("__extservice__", serviceId);
    return HmacUtil.makeEncryptUrl(mac, str);
  }

  public static void initialize()
    throws Exception
  {
    Properties localProperties = new Properties();
    InputStream localInputStream = null;
    String str;
    try
    {
      localInputStream = ServiceMACManager.class.getResourceAsStream("/NHNAPIGatewayKey.properties");
      localProperties.load(localInputStream);
      serviceId = (String)localProperties.keySet().iterator().next();
      str = localProperties.getProperty(serviceId);
      if (str == null)
        throw new Exception("HMAC key Not Exists");
    }
    finally
    {
      if (localInputStream != null)
        localInputStream.close();
    }
    mac = HmacUtil.getMac(str);
    if (localInputStream != null)
      localInputStream.close();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.naver.api.security.client.ServiceMACManager
 * JD-Core Version:    0.6.0
 */