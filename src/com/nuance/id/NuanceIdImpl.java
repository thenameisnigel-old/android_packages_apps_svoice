package com.nuance.id;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class NuanceIdImpl
{
  private static final String SEED = "2beebf614f0f4f6096051804940a8d6e";
  private static final int VERSION;

  private String bytesToStr(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramArrayOfByte.length; i++)
      localStringBuilder.append(toHex(0xFF & paramArrayOfByte[i]));
    return localStringBuilder.toString();
  }

  private byte[] strToBytes(String paramString)
  {
    byte[] arrayOfByte = new byte[paramString.length() / 2];
    int i = 0;
    try
    {
      while (i < paramString.length())
      {
        int j = 0xFF & Integer.parseInt(paramString.substring(i, i + 2), 16);
        arrayOfByte[(i / 2)] = (byte)j;
        i += 2;
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      arrayOfByte = null;
    }
    return arrayOfByte;
  }

  private String toHex(int paramInt)
  {
    String str = Integer.toHexString(paramInt);
    if (paramInt < 16)
      str = "0" + str;
    return str;
  }

  private void unitTest()
  {
    if (!"d06a079160f9cf88a3ac0262edd6eef6e539a1b5ddae08ac14375dea6e1bef2e9".equals(generateHash("99000028669272")))
      throw new RuntimeException("Unit Test Failed!!");
  }

  String generateHash(String paramString)
  {
    try
    {
      localMessageDigest = MessageDigest.getInstance("SHA-256");
      if (paramString == null);
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      try
      {
        MessageDigest localMessageDigest;
        localMessageDigest.update(paramString.getBytes("UTF-8"));
        label22: localMessageDigest.update(strToBytes("2beebf614f0f4f6096051804940a8d6e"));
        String str2 = bytesToStr(localMessageDigest.digest());
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(str2, 0, 4).append(Integer.toHexString(0)).append(str2, 4, str2.length());
        String str3 = localStringBuilder.toString();
        for (String str1 = str3; ; str1 = "00000000000000000000000000000000000000000000000000000000000000000")
        {
          return str1;
          localNoSuchAlgorithmException = localNoSuchAlgorithmException;
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        break label22;
      }
    }
  }

  String sha1hash(String paramString)
  {
    try
    {
      localMessageDigest = MessageDigest.getInstance("SHA-1");
      if (paramString == null);
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      try
      {
        MessageDigest localMessageDigest;
        localMessageDigest.update(paramString.getBytes("UTF-8"));
        label22: String str2 = bytesToStr(localMessageDigest.digest());
        for (String str1 = str2; ; str1 = "00000000000000000000000000000000000000000000000000000000000000000")
        {
          return str1;
          localNoSuchAlgorithmException = localNoSuchAlgorithmException;
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        break label22;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.nuance.id.NuanceIdImpl
 * JD-Core Version:    0.6.0
 */