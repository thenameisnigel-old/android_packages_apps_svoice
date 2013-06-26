package com.facebook.android;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public final class Util
{
  public static void clearCookies(Context paramContext)
  {
    CookieSyncManager.createInstance(paramContext);
    CookieManager.getInstance().removeAllCookie();
  }

  public static Bundle decodeUrl(String paramString)
  {
    Bundle localBundle = new Bundle();
    if (paramString != null)
    {
      String[] arrayOfString1 = paramString.split("&");
      int i = arrayOfString1.length;
      for (int j = 0; j < i; j++)
      {
        String[] arrayOfString2 = arrayOfString1[j].split("=");
        if (arrayOfString2.length <= 1)
          continue;
        localBundle.putString(URLDecoder.decode(arrayOfString2[0]), URLDecoder.decode(arrayOfString2[1]));
      }
    }
    return localBundle;
  }

  public static String encodePostBody(Bundle paramBundle, String paramString)
  {
    if (paramBundle == null);
    StringBuilder localStringBuilder;
    for (String str1 = ""; ; str1 = localStringBuilder.toString())
    {
      return str1;
      localStringBuilder = new StringBuilder();
      Iterator localIterator = paramBundle.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str2 = (String)localIterator.next();
        if (paramBundle.getByteArray(str2) != null)
          continue;
        localStringBuilder.append("Content-Disposition: form-data; name=\"" + str2 + "\"\r\n\r\n" + paramBundle.getString(str2));
        localStringBuilder.append("\r\n--" + paramString + "\r\n");
      }
    }
  }

  public static String encodeUrl(Bundle paramBundle)
  {
    if (paramBundle == null);
    StringBuilder localStringBuilder;
    for (String str1 = ""; ; str1 = localStringBuilder.toString())
    {
      return str1;
      localStringBuilder = new StringBuilder();
      int i = 1;
      Iterator localIterator = paramBundle.keySet().iterator();
      if (!localIterator.hasNext())
        continue;
      String str2 = (String)localIterator.next();
      if (i != 0)
        i = 0;
      while (true)
      {
        localStringBuilder.append(URLEncoder.encode(str2) + "=" + URLEncoder.encode(paramBundle.getString(str2)));
        break;
        localStringBuilder.append("&");
      }
    }
  }

  public static String openUrl(String paramString1, String paramString2, Bundle paramBundle)
    throws MalformedURLException, IOException
  {
    if (paramString2.equals("GET"))
      paramString1 = paramString1 + "?" + encodeUrl(paramBundle);
    HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString1).openConnection();
    localHttpURLConnection.setRequestProperty("User-Agent", System.getProperties().getProperty("http.agent") + " FacebookAndroidSDK");
    if (!paramString2.equals("GET"))
    {
      Bundle localBundle = new Bundle();
      Iterator localIterator1 = paramBundle.keySet().iterator();
      while (localIterator1.hasNext())
      {
        String str4 = (String)localIterator1.next();
        if (paramBundle.getByteArray(str4) == null)
          continue;
        localBundle.putByteArray(str4, paramBundle.getByteArray(str4));
      }
      if (!paramBundle.containsKey("method"))
        paramBundle.putString("method", paramString2);
      if (paramBundle.containsKey("access_token"))
        paramBundle.putString("access_token", URLDecoder.decode(paramBundle.getString("access_token")));
      localHttpURLConnection.setRequestMethod("POST");
      localHttpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
      localHttpURLConnection.setDoOutput(true);
      localHttpURLConnection.setDoInput(true);
      localHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
      localHttpURLConnection.connect();
      BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(localHttpURLConnection.getOutputStream());
      localBufferedOutputStream.write(("--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
      localBufferedOutputStream.write(encodePostBody(paramBundle, "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f").getBytes());
      localBufferedOutputStream.write(("\r\n" + "--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
      if (!localBundle.isEmpty())
      {
        Iterator localIterator2 = localBundle.keySet().iterator();
        while (localIterator2.hasNext())
        {
          String str3 = (String)localIterator2.next();
          localBufferedOutputStream.write(("Content-Disposition: form-data; filename=\"" + str3 + "\"" + "\r\n").getBytes());
          localBufferedOutputStream.write(("Content-Type: content/unknown" + "\r\n" + "\r\n").getBytes());
          localBufferedOutputStream.write(localBundle.getByteArray(str3));
          localBufferedOutputStream.write(("\r\n" + "--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
        }
      }
      localBufferedOutputStream.flush();
    }
    try
    {
      String str2 = read(localHttpURLConnection.getInputStream());
      str1 = str2;
      return str1;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      while (true)
        String str1 = read(localHttpURLConnection.getErrorStream());
    }
  }

  public static JSONObject parseJson(String paramString)
    throws JSONException, FacebookError
  {
    if (paramString.equals("false"))
      throw new FacebookError("request failed");
    if (paramString.equals("true"))
      paramString = "{value : true}";
    JSONObject localJSONObject1 = new JSONObject(paramString);
    if (localJSONObject1.has("error"))
    {
      JSONObject localJSONObject2 = localJSONObject1.getJSONObject("error");
      throw new FacebookError(localJSONObject2.getString("message"), localJSONObject2.getString("type"), 0);
    }
    if ((localJSONObject1.has("error_code")) && (localJSONObject1.has("error_msg")))
      throw new FacebookError(localJSONObject1.getString("error_msg"), "", Integer.parseInt(localJSONObject1.getString("error_code")));
    if (localJSONObject1.has("error_code"))
      throw new FacebookError("request failed", "", Integer.parseInt(localJSONObject1.getString("error_code")));
    if (localJSONObject1.has("error_msg"))
      throw new FacebookError(localJSONObject1.getString("error_msg"));
    if (localJSONObject1.has("error_reason"))
      throw new FacebookError(localJSONObject1.getString("error_reason"));
    return localJSONObject1;
  }

  public static Bundle parseUrl(String paramString)
  {
    String str = paramString.replace("fbconnect", "http");
    try
    {
      URL localURL = new URL(str);
      localBundle = decodeUrl(localURL.getQuery());
      localBundle.putAll(decodeUrl(localURL.getRef()));
      return localBundle;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      while (true)
        Bundle localBundle = new Bundle();
    }
  }

  private static String read(InputStream paramInputStream)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream), 1000);
    for (String str = localBufferedReader.readLine(); str != null; str = localBufferedReader.readLine())
      localStringBuilder.append(str);
    paramInputStream.close();
    return localStringBuilder.toString();
  }

  public static void showAlert(Context paramContext, String paramString1, String paramString2)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(paramString1);
    localBuilder.setMessage(paramString2);
    localBuilder.create().show();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.facebook.android.Util
 * JD-Core Version:    0.6.0
 */