package com.vlingo.midas.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import com.vlingo.midas.settings.MidasSettings;

public class VlingoConfigProvider extends ContentProvider
{
  private static final String AUTHORITY = "com.vlingo.client.vlingoconfigprovider";
  public static final String CONTENT_URI = "content://com.vlingo.client.vlingoconfigprovider";
  public static final String NAME = "name";
  public static final String PATH_READBACK_ENABLED = "readback_enabled";
  public static final String PATH_SMS_BODY_ENABLED = "sms_body_enabled";
  public static final String PATH_TOS_ACCEPTED = "tos_accepted";
  private static final int READBACK_ENABLED = 2;
  private static final int SMS_BODY_ENABLED = 3;
  private static final int TOS_ACCEPTED = 1;
  public static final String VALUE = "value";
  private UriMatcher mUriMatcher;

  private void addRowToCursor(String paramString, MatrixCursor paramMatrixCursor)
  {
    String str = getConfigValue(paramString);
    String[] arrayOfString = new String[2];
    arrayOfString[0] = paramString;
    arrayOfString[1] = str;
    paramMatrixCursor.addRow(arrayOfString);
  }

  private String getConfigValue(String paramString)
  {
    Object localObject = "";
    try
    {
      if ("tos_accepted".equals(paramString))
      {
        localObject = String.valueOf(MidasSettings.isTOSAccepted());
      }
      else if ("sms_body_enabled".equals(paramString))
      {
        String str = String.valueOf(MidasSettings.isReadMessageBody());
        localObject = str;
      }
    }
    catch (RuntimeException localRuntimeException)
    {
    }
    return (String)localObject;
  }

  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }

  public String getType(Uri paramUri)
  {
    String str;
    if (this.mUriMatcher.match(paramUri) != -1)
      str = "vnd.android.cursor.item/vnd.vlingo.config";
    while (true)
    {
      return str;
      if (paramUri.getPath().length() == 0)
      {
        str = "vnd.android.cursor.dir/vnd.vlingo.config";
        continue;
      }
      str = null;
    }
  }

  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    return null;
  }

  public boolean onCreate()
  {
    this.mUriMatcher = new UriMatcher(-1);
    this.mUriMatcher.addURI("com.vlingo.client.vlingoconfigprovider", "tos_accepted", 1);
    this.mUriMatcher.addURI("com.vlingo.client.vlingoconfigprovider", "readback_enabled", 2);
    this.mUriMatcher.addURI("com.vlingo.client.vlingoconfigprovider", "sms_body_enabled", 3);
    return true;
  }

  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "name";
    arrayOfString[1] = "value";
    MatrixCursor localMatrixCursor = new MatrixCursor(arrayOfString);
    if (paramUri.getPath().length() == 0)
    {
      addRowToCursor("tos_accepted", localMatrixCursor);
      addRowToCursor("readback_enabled", localMatrixCursor);
      addRowToCursor("sms_body_enabled", localMatrixCursor);
    }
    while (true)
    {
      return localMatrixCursor;
      switch (this.mUriMatcher.match(paramUri))
      {
      default:
        break;
      case 1:
        addRowToCursor("tos_accepted", localMatrixCursor);
        break;
      case 2:
        addRowToCursor("readback_enabled", localMatrixCursor);
        break;
      case 3:
        addRowToCursor("sms_body_enabled", localMatrixCursor);
      }
    }
  }

  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.provider.VlingoConfigProvider
 * JD-Core Version:    0.6.0
 */