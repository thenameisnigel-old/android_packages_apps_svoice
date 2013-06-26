package com.android.providers.settings;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.FileUtils;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;

public class SettingsBackupAgent extends BackupAgentHelper
{
  private static final int COLUMN_NAME = 1;
  private static final int COLUMN_VALUE = 2;
  private static final boolean DEBUG = false;
  private static final byte[] EMPTY_DATA;
  private static final String FILE_WIFI_SUPPLICANT = "/data/misc/wifi/wpa_supplicant.conf";
  private static final String FILE_WIFI_SUPPLICANT_TEMPLATE = "/system/etc/wifi/wpa_supplicant.conf";
  private static final String KEY_LOCALE = "locale";
  private static final String KEY_SECURE = "secure";
  private static final String KEY_SYSTEM = "system";
  private static final String KEY_WIFI_SUPPLICANT = "￭WIFI";
  private static final String[] PROJECTION;
  private static final int STATE_LOCALE = 2;
  private static final int STATE_SECURE = 1;
  private static final int STATE_SIZE = 4;
  private static final int STATE_SYSTEM = 0;
  private static final int STATE_VERSION = 1;
  private static final int STATE_WIFI = 3;
  private static final String TAG = "SettingsBackupAgent";
  private static String[] sortedSecureKeys;
  private static String[] sortedSystemKeys = null;
  private SettingsHelper mSettingsHelper;

  static
  {
    sortedSecureKeys = null;
    EMPTY_DATA = new byte[0];
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "_id";
    arrayOfString[1] = "name";
    arrayOfString[2] = "value";
    PROJECTION = arrayOfString;
  }

  private String[] copyAndSort(String[] paramArrayOfString)
  {
    String[] arrayOfString = new String[paramArrayOfString.length];
    System.arraycopy(paramArrayOfString, 0, arrayOfString, 0, paramArrayOfString.length);
    Arrays.sort(arrayOfString);
    return arrayOfString;
  }

  private void copyWifiSupplicantTemplate()
  {
    BufferedReader localBufferedReader;
    BufferedWriter localBufferedWriter;
    try
    {
      localBufferedReader = new BufferedReader(new FileReader("/system/etc/wifi/wpa_supplicant.conf"));
      localBufferedWriter = new BufferedWriter(new FileWriter("/data/misc/wifi/wpa_supplicant.conf"));
      char[] arrayOfChar = new char[1024];
      while (true)
      {
        int i = localBufferedReader.read(arrayOfChar);
        if (i <= 0)
          break;
        localBufferedWriter.write(arrayOfChar, 0, i);
      }
    }
    catch (IOException localIOException)
    {
      Log.w("SettingsBackupAgent", "Couldn't copy wpa_supplicant file");
    }
    while (true)
    {
      return;
      localBufferedWriter.close();
      localBufferedReader.close();
    }
  }

  private int enableWifi(boolean paramBoolean)
  {
    WifiManager localWifiManager = (WifiManager)getSystemService("wifi");
    int i;
    if (localWifiManager != null)
    {
      i = localWifiManager.getWifiState();
      localWifiManager.setWifiEnabled(paramBoolean);
    }
    while (true)
    {
      return i;
      i = 4;
    }
  }

  private byte[] getSecureSettings()
  {
    Cursor localCursor = getContentResolver().query(Settings.Secure.CONTENT_URI, PROJECTION, null, null, "name");
    if (sortedSecureKeys == null)
      sortedSecureKeys = copyAndSort(Settings.Secure.SETTINGS_TO_BACKUP);
    byte[] arrayOfByte = extractRelevantValues(localCursor, sortedSecureKeys);
    localCursor.close();
    return arrayOfByte;
  }

  private byte[] getSystemSettings()
  {
    Cursor localCursor = getContentResolver().query(Settings.System.CONTENT_URI, PROJECTION, null, null, "name");
    if (sortedSystemKeys == null)
      sortedSystemKeys = copyAndSort(Settings.System.SETTINGS_TO_BACKUP);
    byte[] arrayOfByte = extractRelevantValues(localCursor, sortedSystemKeys);
    localCursor.close();
    return arrayOfByte;
  }

  private byte[] getWifiSupplicant(String paramString)
  {
    StringBuffer localStringBuffer;
    byte[] arrayOfByte;
    try
    {
      File localFile = new File(paramString);
      if (localFile.exists())
      {
        BufferedReader localBufferedReader = new BufferedReader(new FileReader(localFile));
        localStringBuffer = new StringBuffer();
        int i = 0;
        while (true)
        {
          String str = localBufferedReader.readLine();
          if (str == null)
            break;
          if ((i == 0) && (str.startsWith("network")))
            i = 1;
          if (i == 0)
            continue;
          localStringBuffer.append(str).append("\n");
        }
      }
    }
    catch (IOException localIOException)
    {
      Log.w("SettingsBackupAgent", "Couldn't backup " + paramString);
      arrayOfByte = EMPTY_DATA;
    }
    while (true)
    {
      return arrayOfByte;
      if (localStringBuffer.length() > 0)
      {
        arrayOfByte = localStringBuffer.toString().getBytes();
        continue;
      }
      arrayOfByte = EMPTY_DATA;
      continue;
      arrayOfByte = EMPTY_DATA;
    }
  }

  private boolean invalidSavedSetting(String[] paramArrayOfString, String paramString)
  {
    int i = 0;
    if (paramArrayOfString == null);
    while (true)
    {
      return i;
      int j = paramArrayOfString.length;
      for (int k = 0; ; k++)
      {
        if (k >= j)
          break label39;
        if (paramArrayOfString[k].equals(paramString))
          break;
      }
      label39: i = 1;
    }
  }

  private int readInt(byte[] paramArrayOfByte, int paramInt)
  {
    return (0xFF & paramArrayOfByte[paramInt]) << 24 | (0xFF & paramArrayOfByte[(paramInt + 1)]) << 16 | (0xFF & paramArrayOfByte[(paramInt + 2)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 3)]) << 0;
  }

  private long[] readOldChecksums(ParcelFileDescriptor paramParcelFileDescriptor)
    throws IOException
  {
    long[] arrayOfLong = new long[4];
    DataInputStream localDataInputStream = new DataInputStream(new FileInputStream(paramParcelFileDescriptor.getFileDescriptor()));
    try
    {
      if (localDataInputStream.readInt() == 1)
        for (int i = 0; i < 4; i++)
          arrayOfLong[i] = localDataInputStream.readLong();
    }
    catch (EOFException localEOFException)
    {
      localDataInputStream.close();
    }
    return arrayOfLong;
  }

  private void restoreSettings(BackupDataInput paramBackupDataInput, Uri paramUri)
  {
    String[] arrayOfString = null;
    ContentValues localContentValues;
    byte[] arrayOfByte;
    if (paramUri.equals(Settings.Secure.CONTENT_URI))
    {
      arrayOfString = Settings.Secure.SETTINGS_TO_BACKUP;
      localContentValues = new ContentValues(2);
      arrayOfByte = new byte[paramBackupDataInput.getDataSize()];
    }
    while (true)
    {
      try
      {
        paramBackupDataInput.readEntityData(arrayOfByte, 0, arrayOfByte.length);
        int i = 0;
        if (i < arrayOfByte.length)
        {
          int j = readInt(arrayOfByte, i);
          int k = i + 4;
          if (j <= 0)
            break label248;
          str1 = new String(arrayOfByte, k, j);
          int m = k + j;
          int n = readInt(arrayOfByte, m);
          int i1 = m + 4;
          if (n <= 0)
            break label254;
          str2 = new String(arrayOfByte, i1, n);
          i = i1 + n;
          if ((TextUtils.isEmpty(str1)) || (TextUtils.isEmpty(str2)) || (invalidSavedSetting(arrayOfString, str1)) || (!this.mSettingsHelper.restoreValue(str1, str2)))
            continue;
          localContentValues.clear();
          localContentValues.put("name", str1);
          localContentValues.put("value", str2);
          getContentResolver().insert(paramUri, localContentValues);
          continue;
          if (!paramUri.equals(Settings.System.CONTENT_URI))
            break;
          arrayOfString = Settings.System.SETTINGS_TO_BACKUP;
        }
      }
      catch (IOException localIOException)
      {
        Log.e("SettingsBackupAgent", "Couldn't read entity data");
      }
      return;
      label248: String str1 = null;
      continue;
      label254: String str2 = null;
    }
  }

  private void restoreWifiSupplicant(String paramString, BackupDataInput paramBackupDataInput)
  {
    byte[] arrayOfByte = new byte[paramBackupDataInput.getDataSize()];
    if (arrayOfByte.length <= 0);
    while (true)
    {
      return;
      try
      {
        paramBackupDataInput.readEntityData(arrayOfByte, 0, arrayOfByte.length);
        File localFile = new File("/data/misc/wifi/wpa_supplicant.conf");
        if (localFile.exists())
          localFile.delete();
        copyWifiSupplicantTemplate();
        FileOutputStream localFileOutputStream = new FileOutputStream(paramString, true);
        localFileOutputStream.write("\n".getBytes());
        localFileOutputStream.write(arrayOfByte);
      }
      catch (IOException localIOException)
      {
        Log.w("SettingsBackupAgent", "Couldn't restore " + paramString);
      }
    }
  }

  private int writeBytes(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2)
  {
    System.arraycopy(paramArrayOfByte2, 0, paramArrayOfByte1, paramInt, paramArrayOfByte2.length);
    return paramInt + paramArrayOfByte2.length;
  }

  private long writeIfChanged(long paramLong, String paramString, byte[] paramArrayOfByte, BackupDataOutput paramBackupDataOutput)
  {
    CRC32 localCRC32 = new CRC32();
    localCRC32.update(paramArrayOfByte);
    long l = localCRC32.getValue();
    if (paramLong == l);
    while (true)
    {
      return paramLong;
      try
      {
        paramBackupDataOutput.writeEntityHeader(paramString, paramArrayOfByte.length);
        paramBackupDataOutput.writeEntityData(paramArrayOfByte, paramArrayOfByte.length);
        label53: paramLong = l;
      }
      catch (IOException localIOException)
      {
        break label53;
      }
    }
  }

  private int writeInt(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    paramArrayOfByte[(paramInt1 + 0)] = (byte)(0xFF & paramInt2 >> 24);
    paramArrayOfByte[(paramInt1 + 1)] = (byte)(0xFF & paramInt2 >> 16);
    paramArrayOfByte[(paramInt1 + 2)] = (byte)(0xFF & paramInt2 >> 8);
    paramArrayOfByte[(paramInt1 + 3)] = (byte)(0xFF & paramInt2 >> 0);
    return paramInt1 + 4;
  }

  private void writeNewChecksums(long[] paramArrayOfLong, ParcelFileDescriptor paramParcelFileDescriptor)
    throws IOException
  {
    DataOutputStream localDataOutputStream = new DataOutputStream(new FileOutputStream(paramParcelFileDescriptor.getFileDescriptor()));
    localDataOutputStream.writeInt(1);
    for (int i = 0; i < 4; i++)
      localDataOutputStream.writeLong(paramArrayOfLong[i]);
    localDataOutputStream.close();
  }

  byte[] extractRelevantValues(Cursor paramCursor, String[] paramArrayOfString)
  {
    byte[][] arrayOfByte = new byte[2 * paramArrayOfString.length][];
    byte[] arrayOfByte1;
    if (!paramCursor.moveToFirst())
    {
      Log.e("SettingsBackupAgent", "Couldn't read from the cursor");
      arrayOfByte1 = new byte[0];
      return arrayOfByte1;
    }
    int i = 0;
    int j = 0;
    label40: String str2;
    int n;
    byte[] arrayOfByte3;
    if (!paramCursor.isAfterLast())
    {
      String str1 = paramCursor.getString(1);
      do
      {
        if (paramArrayOfString[i].compareTo(str1.toString()) >= 0)
          break;
        i++;
      }
      while (i != paramArrayOfString.length);
      if ((i < paramArrayOfString.length) && (str1.equals(paramArrayOfString[i])))
      {
        str2 = paramCursor.getString(2);
        byte[] arrayOfByte2 = str1.toString().getBytes();
        n = j + (4 + arrayOfByte2.length);
        arrayOfByte[(i * 2)] = arrayOfByte2;
        if (!TextUtils.isEmpty(str2))
          break label244;
        arrayOfByte3 = null;
      }
    }
    for (j = n + 4; ; j = n + (4 + arrayOfByte3.length))
    {
      arrayOfByte[(1 + i * 2)] = arrayOfByte3;
      i++;
      if ((i != paramArrayOfString.length) && (paramCursor.moveToNext()))
        break label40;
      arrayOfByte1 = new byte[j];
      int k = 0;
      for (int m = 0; m < 2 * paramArrayOfString.length; m++)
      {
        if (arrayOfByte[m] == null)
          continue;
        k = writeBytes(arrayOfByte1, writeInt(arrayOfByte1, k, arrayOfByte[m].length), arrayOfByte[m]);
      }
      break;
      label244: arrayOfByte3 = str2.toString().getBytes();
    }
  }

  public void onBackup(ParcelFileDescriptor paramParcelFileDescriptor1, BackupDataOutput paramBackupDataOutput, ParcelFileDescriptor paramParcelFileDescriptor2)
    throws IOException
  {
    byte[] arrayOfByte1 = getSystemSettings();
    byte[] arrayOfByte2 = getSecureSettings();
    byte[] arrayOfByte3 = this.mSettingsHelper.getLocaleData();
    byte[] arrayOfByte4 = getWifiSupplicant("/data/misc/wifi/wpa_supplicant.conf");
    long[] arrayOfLong = readOldChecksums(paramParcelFileDescriptor1);
    arrayOfLong[0] = writeIfChanged(arrayOfLong[0], "system", arrayOfByte1, paramBackupDataOutput);
    arrayOfLong[1] = writeIfChanged(arrayOfLong[1], "secure", arrayOfByte2, paramBackupDataOutput);
    arrayOfLong[2] = writeIfChanged(arrayOfLong[2], "locale", arrayOfByte3, paramBackupDataOutput);
    arrayOfLong[3] = writeIfChanged(arrayOfLong[3], "￭WIFI", arrayOfByte4, paramBackupDataOutput);
    writeNewChecksums(arrayOfLong, paramParcelFileDescriptor2);
  }

  public void onCreate()
  {
    this.mSettingsHelper = new SettingsHelper(this);
    super.onCreate();
  }

  public void onRestore(BackupDataInput paramBackupDataInput, int paramInt, ParcelFileDescriptor paramParcelFileDescriptor)
    throws IOException
  {
    while (paramBackupDataInput.readNextHeader())
    {
      String str = paramBackupDataInput.getKey();
      int i = paramBackupDataInput.getDataSize();
      if ("system".equals(str))
      {
        restoreSettings(paramBackupDataInput, Settings.System.CONTENT_URI);
        this.mSettingsHelper.applyAudioSettings();
        continue;
      }
      if ("secure".equals(str))
      {
        restoreSettings(paramBackupDataInput, Settings.Secure.CONTENT_URI);
        continue;
      }
      if ("￭WIFI".equals(str))
      {
        int j = enableWifi(false);
        restoreWifiSupplicant("/data/misc/wifi/wpa_supplicant.conf", paramBackupDataInput);
        FileUtils.setPermissions("/data/misc/wifi/wpa_supplicant.conf", 432, Process.myUid(), 1010);
        if ((j == 3) || (j == 2));
        for (boolean bool = true; ; bool = false)
        {
          enableWifi(bool);
          break;
        }
      }
      if ("locale".equals(str))
      {
        byte[] arrayOfByte = new byte[i];
        paramBackupDataInput.readEntityData(arrayOfByte, 0, i);
        this.mSettingsHelper.setLocaleData(arrayOfByte);
        continue;
      }
      paramBackupDataInput.skipEntityData();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.providers.settings.SettingsBackupAgent
 * JD-Core Version:    0.6.0
 */