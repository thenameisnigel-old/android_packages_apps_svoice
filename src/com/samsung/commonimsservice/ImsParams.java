package com.samsung.commonimsservice;

import android.os.Build;
import android.os.Parcel;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class ImsParams
{
  private static final boolean ENG = false;
  static final String TAG = "IMS-UI-Java";
  private HashMap<String, String> mMap = new HashMap();

  public void clearAll()
  {
    this.mMap.clear();
  }

  public void dump()
  {
    Log.e("IMS-UI-Java", "dump: size=" + this.mMap.size());
    Iterator localIterator = this.mMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Log.e("IMS-UI-Java", "dump: " + str + "=" + (String)this.mMap.get(str));
    }
  }

  public String flatten()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Log.d("VoIPAPP", " in flatten");
    Parcel.obtain();
    Iterator localIterator = this.mMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localStringBuilder.append(str);
      localStringBuilder.append("=");
      localStringBuilder.append((String)this.mMap.get(str));
      localStringBuilder.append(";");
    }
    localStringBuilder.deleteCharAt(-1 + localStringBuilder.length());
    if (ENG)
      Log.d("########VoIPAPP ", " in flatten - String is=" + localStringBuilder.toString());
    return localStringBuilder.toString();
  }

  public String get(String paramString)
  {
    return (String)this.mMap.get(paramString);
  }

  public int getInt(String paramString)
  {
    return Integer.parseInt((String)this.mMap.get(paramString));
  }

  public void set(String paramString, int paramInt)
  {
    this.mMap.put(paramString, Integer.toString(paramInt));
  }

  public void set(String paramString1, String paramString2)
  {
    if ((paramString1.indexOf('=') != -1) || (paramString1.indexOf(';') != -1))
      Log.e("IMS-UI-Java", "Key \"" + paramString1 + "\" contains invalid character (= or ;)");
    while (true)
    {
      return;
      if ((paramString2.indexOf('=') != -1) || (paramString2.indexOf(';') != -1))
      {
        Log.e("IMS-UI-Java", "Value \"" + paramString2 + "\" contains invalid character (= or ;)");
        continue;
      }
      this.mMap.put(paramString1, paramString2);
    }
  }

  public void unflatten(String paramString)
  {
    this.mMap.clear();
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ";");
    while (localStringTokenizer.hasMoreElements())
    {
      String str1 = localStringTokenizer.nextToken();
      int i = str1.indexOf('=');
      if (i == -1)
        continue;
      String str2 = str1.substring(0, i);
      String str3 = str1.substring(i + 1);
      this.mMap.put(str2, str3);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.commonimsservice.ImsParams
 * JD-Core Version:    0.6.0
 */