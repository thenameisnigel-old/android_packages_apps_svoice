package com.vlingo.sdk.internal.crypto;

public class CryptoUtils
{
  public static final int HASH_TYPE_DEFAULT = 2;
  public static final int HASH_TYPE_MD5 = 1;
  public static final int HASH_TYPE_NONE = 0;
  public static final int HASH_TYPE_SHA256 = 2;

  public static String getHash(String paramString, int paramInt)
  {
    Object localObject = null;
    if (paramInt == 1)
    {
      localObject = new MD5();
      if (localObject != null)
        break label37;
    }
    while (true)
    {
      return paramString;
      if (paramInt != 2)
        break;
      localObject = new SHA256();
      break;
      label37: paramString = ((HashFunction)localObject).hash(paramString);
    }
  }

  public static String getMD5(String paramString)
  {
    return getHash(paramString, 1);
  }

  public static String getSHA256(String paramString)
  {
    return getHash(paramString, 2);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.crypto.CryptoUtils
 * JD-Core Version:    0.6.0
 */