package com.naver.api.security;

import com.naver.api.util.Base64;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacUtil
{
  private static final String ALGORITHM = "HmacSHA1";
  private static final String AMPERCENT = "&";
  private static final int MAX_MESSAGESIZE = 255;
  private static final String MD = "&md=";
  private static final String MSGPAD = "msgpad=";
  private static final String QUESTION = "?";
  private static final String UTF8 = "utf-8";

  public static Mac getMac(String paramString)
    throws NoSuchAlgorithmException, InvalidKeyException
  {
    SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramString.getBytes(), "HmacSHA1");
    Mac localMac = Mac.getInstance("HmacSHA1");
    localMac.init(localSecretKeySpec);
    return localMac;
  }

  public static String getMessage(String paramString1, String paramString2)
  {
    String str = paramString1.substring(0, Math.min(255, paramString1.length()));
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.setLength(0);
    localStringBuilder.append(str).append(paramString2);
    return localStringBuilder.toString();
  }

  public static String getMessageDigest(String paramString1, String paramString2)
    throws Exception
  {
    return getMessageDigest(getMac(paramString1), paramString2);
  }

  public static String getMessageDigest(Mac paramMac, String paramString)
  {
    monitorenter;
    try
    {
      byte[] arrayOfByte = paramMac.doFinal(paramString.getBytes());
      return Base64.encodeBase64(arrayOfByte);
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static String makeEncryptUrl(Mac paramMac, String paramString)
    throws Exception
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str1 = String.valueOf(Calendar.getInstance().getTimeInMillis());
    String str2 = URLEncoder.encode(getMessageDigest(paramMac, getMessage(paramString, str1)), "utf-8");
    localStringBuilder.setLength(0);
    localStringBuilder.append(paramString);
    if (paramString.contains("?"))
      localStringBuilder.append("&");
    while (true)
    {
      localStringBuilder.append("msgpad=").append(str1).append("&md=").append(str2);
      return localStringBuilder.toString();
      localStringBuilder.append("?");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.naver.api.security.HmacUtil
 * JD-Core Version:    0.6.0
 */