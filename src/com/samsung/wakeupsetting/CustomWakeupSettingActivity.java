package com.samsung.wakeupsetting;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.media.AudioTrack;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TwoLineListItem;
import com.samsung.voiceshell.VoiceEngine;
import com.samsung.voiceshell.VoiceEngineWrapper;
import com.vlingo.core.internal.CoreAdapterRegistrar;
import com.vlingo.core.internal.CoreAdapterRegistrar.AdapterList;
import com.vlingo.core.internal.util.CorePackageInfoProvider;
import com.vlingo.core.internal.vlservice.VlingoApplicationService;
import com.vlingo.midas.iux.IUXCompoundActivity;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.ui.VLActivity;
import com.vlingo.sdk.internal.util.PackageUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomWakeupSettingActivity extends VLActivity
{
  static final int REQUEST_WAKEUP_COMMAND_RECORD = 1;
  static final int SET_BOOKMARK_REQUEST = 111;
  static final int SET_DIRECT_DIAL_REQUEST = 2;
  static final int SET_DIRECT_MESSAGE_REQUEST = 3;
  static final int SET_NAVIGATION_REQUEST = 4;
  static final int SET_OPEN_APPLICATION_REQUEST = 5;
  public static boolean enrolled;
  private static boolean mFirstInit;
  static int mFunctionPositionSelected;
  private static boolean mNeedToShowFunctionList;
  private static int mTheme;
  public static String[] m_lastEnroll;
  public static String[] m_realLastEnroll;
  static int wakeupCommandPositonSelected = 0;
  private static Boolean wakeupRecordSuccess;
  private boolean customTitleSupported;
  private TextView customWakeupBubbleTextView;
  private int density;
  private AlertDialog.Builder functionAlertDialogBuilder;
  ArrayAdapter functionListAdapter;
  ListView functionListView;
  final List<FunctionItem> functionNameArray = new ArrayList();
  private boolean inIUXMode;
  private boolean isFromHelpApp;
  private boolean isFromMainApp;
  private Boolean isFunctionValueSelected;
  private float mDensity;
  private float mOldLX;
  private float mOldLY;
  private float mOldPX;
  private float mOldPY;
  private float mOldX;
  private float mOldY;
  private VoiceEngine mVoiceEngine;
  private int my_orientation;
  String[] preferenceNameArray;
  private AlertDialog.Builder resetAlertDialogBuilder;
  List<WakeupCommandListItem> wakeupCommandArrayList;
  ListView wakeupCommandListView;
  final String[] wakeupCommandNameArray = new String[5];
  final String[] wakeupDialogAutoFunctionArray = new String[4];
  ArrayAdapter wakeupDialogListAdapter;
  ListView wakeupDialogListView;
  final String[] wakeupDialogVoiceTalkArray = new String[3];
  SharedPreferences[] wakeupPreference = new SharedPreferences[this.wakeupCommandNameArray.length];
  ArrayAdapter wakeupTwoLineListAdapter;

  static
  {
    mFunctionPositionSelected = 0;
    wakeupRecordSuccess = Boolean.valueOf(false);
    enrolled = false;
    mFirstInit = false;
    mNeedToShowFunctionList = false;
    mTheme = 2131296526;
    String[] arrayOfString1 = new String[5];
    arrayOfString1[0] = "/data/data/com.vlingo.midas/lastEnrollUtt_1.pcm";
    arrayOfString1[1] = "/data/data/com.vlingo.midas/lastEnrollUtt_2.pcm";
    arrayOfString1[2] = "/data/data/com.vlingo.midas/lastEnrollUtt_3.pcm";
    arrayOfString1[3] = "/data/data/com.vlingo.midas/lastEnrollUtt_4.pcm";
    arrayOfString1[4] = "/data/data/com.vlingo.midas/lastEnrollUtt_5.pcm";
    m_lastEnroll = arrayOfString1;
    String[] arrayOfString2 = new String[5];
    arrayOfString2[0] = "/data/data/com.vlingo.midas/realLastEnrollUtt_1.pcm";
    arrayOfString2[1] = "/data/data/com.vlingo.midas/realLastEnrollUtt_2.pcm";
    arrayOfString2[2] = "/data/data/com.vlingo.midas/realLastEnrollUtt_3.pcm";
    arrayOfString2[3] = "/data/data/com.vlingo.midas/realLastEnrollUtt_4.pcm";
    arrayOfString2[4] = "/data/data/com.vlingo.midas/realLastEnrollUtt_5.pcm";
    m_realLastEnroll = arrayOfString2;
  }

  public CustomWakeupSettingActivity()
  {
    String[] arrayOfString = new String[5];
    arrayOfString[0] = "WakeupCommandPreference1";
    arrayOfString[1] = "WakeupCommandPreference2";
    arrayOfString[2] = "WakeupCommandPreference3";
    arrayOfString[3] = "WakeupCommandPreference4";
    arrayOfString[4] = "WakeupCommandPreference5";
    this.preferenceNameArray = arrayOfString;
    this.isFunctionValueSelected = Boolean.valueOf(false);
    this.mVoiceEngine = VoiceEngineWrapper.getInstance();
    this.isFromMainApp = false;
    this.isFromHelpApp = false;
    this.mDensity = 0.0F;
    this.mOldX = 0.0F;
    this.mOldY = 0.0F;
    this.mOldPX = 0.0F;
    this.mOldPY = 0.0F;
    this.mOldLX = 0.0F;
    this.mOldLY = 0.0F;
    this.customTitleSupported = false;
    this.inIUXMode = false;
  }

  private void PlayShortAudioFileViaAudioTrack(String paramString)
    throws IOException
  {
    if (paramString == null);
    while (true)
    {
      return;
      File localFile = new File(paramString);
      byte[] arrayOfByte = new byte[(int)localFile.length()];
      try
      {
        localFileInputStream = new FileInputStream(localFile);
      }
      catch (FileNotFoundException localFileNotFoundException2)
      {
        try
        {
          FileInputStream localFileInputStream;
          localFileInputStream.read(arrayOfByte);
          localFileInputStream.close();
          while (true)
          {
            while (true)
            {
              label44: AudioTrack localAudioTrack = new AudioTrack(3, 16000, 2, 2, AudioTrack.getMinBufferSize(16000, 2, 2), 1);
              if (localAudioTrack == null)
                break;
              try
              {
                while (true)
                {
                  localAudioTrack.play();
                  localAudioTrack.write(arrayOfByte, 0, arrayOfByte.length);
                  localAudioTrack.stop();
                  try
                  {
                    localAudioTrack.release();
                  }
                  catch (Exception localException4)
                  {
                    localException4.printStackTrace();
                  }
                }
                break;
              }
              catch (Exception localException2)
              {
                while (true)
                {
                  localException2.printStackTrace();
                  try
                  {
                    localAudioTrack.release();
                  }
                  catch (Exception localException3)
                  {
                    localException3.printStackTrace();
                  }
                }
                break;
              }
              finally
              {
                try
                {
                  localAudioTrack.release();
                  throw localObject;
                }
                catch (Exception localException1)
                {
                  while (true)
                    localException1.printStackTrace();
                }
              }
            }
            localFileNotFoundException2 = localFileNotFoundException2;
          }
        }
        catch (FileNotFoundException localFileNotFoundException1)
        {
          break label44;
        }
      }
    }
  }

  private void addToPreference(String paramString, Boolean paramBoolean)
  {
    if (enrolled)
    {
      wakeupCommandPositonSelected = 0;
      enrolled = false;
    }
    SharedPreferences.Editor localEditor = this.wakeupPreference[wakeupCommandPositonSelected].edit();
    localEditor.putString("wakeupCommand", paramString);
    localEditor.putBoolean("recordSuccess", paramBoolean.booleanValue());
    localEditor.commit();
  }

  private void addToPreference(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    SharedPreferences.Editor localEditor = this.wakeupPreference[wakeupCommandPositonSelected].edit();
    localEditor.putString("wakeupCommand", paramString1);
    localEditor.putString("Function", paramString2);
    localEditor.putString("FunctionDetail", paramString3);
    localEditor.putInt("FunctionPosition", paramInt);
    localEditor.commit();
  }

  private void checkPreferenceValue()
  {
    SharedPreferences localSharedPreferences = getSharedPreferences(this.preferenceNameArray[wakeupCommandPositonSelected], 0);
    String str1 = localSharedPreferences.getString("wakeupCommand", "");
    if (wakeupCommandPositonSelected == 0)
      wakeupRecordSuccess = Boolean.valueOf(localSharedPreferences.getBoolean("recordSuccess", false));
    while (true)
    {
      return;
      String str2 = localSharedPreferences.getString("Function", "");
      if ((str1.equals("")) || (str2.equals("")))
      {
        this.isFunctionValueSelected = Boolean.valueOf(false);
        continue;
      }
      this.isFunctionValueSelected = Boolean.valueOf(true);
    }
  }

  private String getFuncitonName(int paramInt)
  {
    int i = 0;
    if (i < this.functionNameArray.size())
      if (((FunctionItem)this.functionNameArray.get(i)).getIndex() != paramInt);
    for (String str = ((FunctionItem)this.functionNameArray.get(i)).getTitle(); ; str = "")
    {
      return str;
      i++;
      break;
    }
  }

  private String getFuncitonSubTitle(int paramInt)
  {
    int i = 0;
    if (i < this.functionNameArray.size())
      if (((FunctionItem)this.functionNameArray.get(i)).getIndex() != paramInt);
    for (String str = ((FunctionItem)this.functionNameArray.get(i)).getSubTitle(); ; str = "")
    {
      return str;
      i++;
      break;
    }
  }

  private int getPreferenceIntValue(int paramInt)
  {
    return getSharedPreferences(this.preferenceNameArray[paramInt], 0).getInt("FunctionPosition", -1);
  }

  private Intent getRadioIntent()
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localIntent.setComponent(new ComponentName("com.sec.android.app.fm", "com.sec.android.app.fm.MainActivity"));
    return localIntent;
  }

  private void initStrings()
  {
    this.functionNameArray.add(new FunctionItem(getString(2131362572), getString(2131362572), 7));
    if (CorePackageInfoProvider.hasDialing())
    {
      this.functionNameArray.add(new FunctionItem(getString(2131362533), getString(2131362533), 0));
      this.functionNameArray.add(new FunctionItem(getString(2131362534), getString(2131362534), 1));
    }
    this.functionNameArray.add(new FunctionItem(getString(2131362535), getString(2131362535), 2));
    this.functionNameArray.add(new FunctionItem(getString(2131362560), getString(2131362560), 3));
    this.functionNameArray.add(new FunctionItem(getString(2131362536), getString(2131362536), 4));
    if (isIntentLaunchable(getRadioIntent()))
      this.functionNameArray.add(new FunctionItem(getString(2131362537), getString(2131362537), 5));
    if (PackageUtil.isAppInstalled("com.sec.android.app.voicerecorder", 0))
      this.functionNameArray.add(new FunctionItem(getString(2131362538), getString(2131362538), 6));
    this.functionNameArray.add(new FunctionItem(getString(2131362311), getString(2131362311), 8));
    this.wakeupCommandNameArray[0] = getString(2131362539);
    this.wakeupCommandNameArray[1] = getString(2131362540);
    this.wakeupCommandNameArray[2] = getString(2131362541);
    this.wakeupCommandNameArray[3] = getString(2131362542);
    this.wakeupCommandNameArray[4] = getString(2131362543);
    this.wakeupDialogAutoFunctionArray[0] = getString(2131362546);
    this.wakeupDialogAutoFunctionArray[1] = getString(2131362544);
    this.wakeupDialogAutoFunctionArray[2] = getString(2131362545);
    this.wakeupDialogAutoFunctionArray[3] = getString(2131362356);
    this.wakeupDialogVoiceTalkArray[0] = getString(2131362546);
    this.wakeupDialogVoiceTalkArray[1] = getString(2131362544);
    this.wakeupDialogVoiceTalkArray[2] = getString(2131362356);
  }

  private void initializeAutoFunctionAdapter()
  {
    this.wakeupDialogListAdapter = new ArrayAdapter(this, 17367043, this.wakeupDialogAutoFunctionArray)
    {
      public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
      {
        TextView localTextView;
        if (paramView == null)
        {
          localTextView = (TextView)((LayoutInflater)CustomWakeupSettingActivity.this.getApplicationContext().getSystemService("layout_inflater")).inflate(17367043, null);
          localTextView.setTextColor(Color.parseColor("#ffffff"));
          localTextView.setText(CustomWakeupSettingActivity.this.wakeupDialogAutoFunctionArray[paramInt]);
          localTextView.setTextSize(2, 20.0F);
          localTextView.setPadding(17 * CustomWakeupSettingActivity.this.density, 0, 17 * CustomWakeupSettingActivity.this.density, 0);
          if ((paramInt != 0) && (paramInt != 3) && (paramInt != 2))
            break label150;
          CustomWakeupSettingActivity.this.checkPreferenceValue();
          if (CustomWakeupSettingActivity.this.isFunctionValueSelected.booleanValue())
            break label141;
          localTextView.setEnabled(false);
        }
        while (true)
        {
          return localTextView;
          localTextView = (TextView)paramView;
          break;
          label141: localTextView.setEnabled(true);
          continue;
          label150: localTextView.setEnabled(true);
        }
      }

      public boolean isEnabled(int paramInt)
      {
        int i = 1;
        if ((paramInt == 0) || (paramInt == 2) || (paramInt == 3))
        {
          CustomWakeupSettingActivity.this.checkPreferenceValue();
          if (!CustomWakeupSettingActivity.this.isFunctionValueSelected.booleanValue())
            i = 0;
        }
        return i;
      }
    };
  }

  private void initializeVoiceTalkAdapter()
  {
    this.wakeupDialogListAdapter = new ArrayAdapter(this, 17367043, this.wakeupDialogVoiceTalkArray)
    {
      public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
      {
        TextView localTextView;
        if (paramView == null)
        {
          localTextView = (TextView)((LayoutInflater)CustomWakeupSettingActivity.this.getApplicationContext().getSystemService("layout_inflater")).inflate(17367043, null);
          localTextView.setTextColor(Color.parseColor("#ffffff"));
          localTextView.setText(CustomWakeupSettingActivity.this.wakeupDialogVoiceTalkArray[paramInt]);
          localTextView.setTextSize(2, 20.0F);
          localTextView.setPadding(17 * CustomWakeupSettingActivity.this.density, 0, 17 * CustomWakeupSettingActivity.this.density, 0);
          if ((paramInt != 2) && (paramInt != 0))
            break label141;
          CustomWakeupSettingActivity.this.checkPreferenceValue();
          if (CustomWakeupSettingActivity.wakeupRecordSuccess.booleanValue())
            break label132;
          localTextView.setEnabled(false);
        }
        while (true)
        {
          return localTextView;
          localTextView = (TextView)paramView;
          break;
          label132: localTextView.setEnabled(true);
          continue;
          label141: localTextView.setEnabled(true);
        }
      }

      public boolean isEnabled(int paramInt)
      {
        int i = 1;
        if ((paramInt == 0) || (paramInt == 2))
        {
          CustomWakeupSettingActivity.this.checkPreferenceValue();
          if (!CustomWakeupSettingActivity.wakeupRecordSuccess.booleanValue())
            i = 0;
        }
        return i;
      }
    };
  }

  private boolean isDialogMode()
  {
    if (mTheme != 2131296526);
    for (int i = 1; ; i = 0)
      return i;
  }

  private boolean isFunctionAvailable(int paramInt)
  {
    int i = 0;
    int j = 1;
    if (j < this.wakeupCommandNameArray.length)
    {
      if ((paramInt == getPreferenceIntValue(j)) && (j != wakeupCommandPositonSelected))
        i = 1;
    }
    else
      if (i != 0)
        break label47;
    label47: for (int k = 1; ; k = 0)
    {
      return k;
      j++;
      break;
    }
  }

  private boolean isIntentLaunchable(Intent paramIntent)
  {
    int i = 0;
    if (getPackageManager().queryIntentActivities(paramIntent, 0).size() > 0)
      i = 1;
    return i;
  }

  private void launchFunctionAlertDialog()
  {
    if (this.functionAlertDialogBuilder == null)
    {
      this.functionAlertDialogBuilder = new AlertDialog.Builder(this);
      this.functionAlertDialogBuilder.setTitle(2131362547);
      this.functionAlertDialogBuilder.setMessage(2131362548);
      this.functionAlertDialogBuilder.setNegativeButton(17039370, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
        }
      });
    }
    this.functionAlertDialogBuilder.show();
  }

  private void launchResetAlertDialog()
  {
    String str1 = getResources().getString(2131362526);
    String str2 = getResources().getString(2131362527);
    String str3 = getResources().getString(2131362528);
    this.resetAlertDialogBuilder = new AlertDialog.Builder(this);
    this.resetAlertDialogBuilder.setTitle(getString(2131362356));
    this.resetAlertDialogBuilder.setMessage(str1);
    this.resetAlertDialogBuilder.setPositiveButton(str2, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        CustomWakeupSettingActivity.this.resetPreferenceValue();
        if (CustomWakeupSettingActivity.wakeupCommandPositonSelected == 0)
        {
          MidasSettings.setBoolean("samsung_wakeup_engine_enable", false);
          if (!MidasSettings.getBoolean("samsung_multi_engine_enable", false))
            CoreAdapterRegistrar.unregisterHandler(CoreAdapterRegistrar.AdapterList.PhraseSpotter);
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/kwd_1.bin");
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/modelStatus_1.bin");
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/enSTR_1.bin");
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/enSTR4_1.bin");
          CustomWakeupSettingActivity.this.showBubble();
          return;
        }
        CustomWakeupSettingActivity.this.readFromPreferenceAndUpdateList();
        int[] arrayOfInt1 = new int[4];
        int[] arrayOfInt2 = new int[4];
        if (CustomWakeupSettingActivity.this.mVoiceEngine.functionAssignment("/data/data/com.vlingo.midas/typeDefine.bin", arrayOfInt1, 0) == -1)
        {
          arrayOfInt1[0] = -1;
          arrayOfInt1[1] = -1;
          arrayOfInt1[2] = -1;
          arrayOfInt1[3] = -1;
        }
        switch (CustomWakeupSettingActivity.wakeupCommandPositonSelected)
        {
        case 0:
        default:
        case 1:
        case 2:
        case 3:
        case 4:
        }
        while (true)
        {
          arrayOfInt2[0] = MidasSettings.getInt("kew_wake_up_and_auto_function1", -1);
          arrayOfInt2[1] = MidasSettings.getInt("kew_wake_up_and_auto_function2", -1);
          arrayOfInt2[2] = MidasSettings.getInt("kew_wake_up_and_auto_function3", -1);
          arrayOfInt2[3] = MidasSettings.getInt("kew_wake_up_and_auto_function4", -1);
          if (((arrayOfInt2[0] == -1) || (arrayOfInt2[1] == -1) || (arrayOfInt2[2] == -1) || (arrayOfInt2[3] == -1)) && (MidasSettings.getBoolean("samsung_multi_engine_enable", false)))
          {
            CoreAdapterRegistrar.unregisterHandler(CoreAdapterRegistrar.AdapterList.PhraseSpotter);
            MidasSettings.setBoolean("samsung_multi_engine_enable", false);
          }
          CustomWakeupSettingActivity.this.mVoiceEngine.functionAssignment("/data/data/com.vlingo.midas/typeDefine.bin", arrayOfInt1, 1);
          break;
          MidasSettings.setInt("kew_wake_up_and_auto_function1", -1);
          arrayOfInt1[0] = -1;
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/kwd_2.bin");
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/modelStatus_2.bin");
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/enSTR_2.bin");
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/enSTR4_2.bin");
          continue;
          MidasSettings.setInt("kew_wake_up_and_auto_function2", -1);
          arrayOfInt1[1] = -1;
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/kwd_3.bin");
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/modelStatus_3.bin");
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/enSTR_3.bin");
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/enSTR4_3.bin");
          continue;
          MidasSettings.setInt("kew_wake_up_and_auto_function3", -1);
          arrayOfInt1[2] = -1;
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/kwd_4.bin");
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/modelStatus_4.bin");
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/enSTR_4.bin");
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/enSTR4_4.bin");
          continue;
          MidasSettings.setInt("kew_wake_up_and_auto_function4", -1);
          arrayOfInt1[3] = -1;
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/kwd_5.bin");
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/modelStatus_5.bin");
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/enSTR_5.bin");
          CustomWakeupSettingActivity.this.deleteData("/data/data/com.vlingo.midas/enSTR4_5.bin");
        }
      }
    });
    this.resetAlertDialogBuilder.setNegativeButton(str3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        CustomWakeupSettingActivity.this.showBubble();
      }
    });
    this.resetAlertDialogBuilder.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramDialogInterface)
      {
        CustomWakeupSettingActivity.this.showBubble();
      }
    });
    this.resetAlertDialogBuilder.show();
  }

  private void launchWakeupDialog()
  {
    7 local7 = new Dialog(this)
    {
      public void onBackPressed()
      {
        CustomWakeupSettingActivity.this.showBubble();
        super.onBackPressed();
      }
    };
    local7.setContentView(2130903128);
    local7.setTitle(this.wakeupCommandNameArray[wakeupCommandPositonSelected]);
    this.wakeupDialogListView = ((ListView)local7.findViewById(2131558842));
    if (wakeupCommandPositonSelected == 0)
      initializeVoiceTalkAdapter();
    while (true)
    {
      this.wakeupDialogListView.setAdapter(this.wakeupDialogListAdapter);
      this.wakeupDialogListView.invalidate();
      local7.show();
      this.wakeupDialogListView.setOnItemClickListener(new AdapterView.OnItemClickListener(local7)
      {
        public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
        {
          CustomWakeupSettingActivity.access$1002(false);
          switch (paramInt)
          {
          default:
          case 0:
          case 1:
          case 2:
          case 3:
          }
          while (true)
          {
            this.val$dialog.dismiss();
            return;
            try
            {
              CustomWakeupSettingActivity.this.PlayShortAudioFileViaAudioTrack(CustomWakeupSettingActivity.m_realLastEnroll[CustomWakeupSettingActivity.wakeupCommandPositonSelected]);
              CustomWakeupSettingActivity.this.showBubble();
            }
            catch (IOException localIOException)
            {
            }
            continue;
            CustomWakeupSettingActivity.this.startCustomCommandRecordingActivity();
            continue;
            if (CustomWakeupSettingActivity.wakeupCommandPositonSelected == 0)
            {
              CustomWakeupSettingActivity.this.checkPreferenceValue();
              if (!CustomWakeupSettingActivity.wakeupRecordSuccess.booleanValue())
                continue;
              CustomWakeupSettingActivity.this.launchResetAlertDialog();
              continue;
            }
            CustomWakeupSettingActivity.this.functionListView.setVisibility(0);
            CustomWakeupSettingActivity.this.setTitle(CustomWakeupSettingActivity.this.getString(2131362545));
            CustomWakeupSettingActivity.this.functionListAdapter.notifyDataSetChanged();
            CustomWakeupSettingActivity.this.wakeupCommandListView.setVisibility(8);
            CustomWakeupSettingActivity.this.showBubble();
            continue;
            CustomWakeupSettingActivity.this.checkPreferenceValue();
            if (!CustomWakeupSettingActivity.this.isFunctionValueSelected.booleanValue())
              continue;
            CustomWakeupSettingActivity.this.launchResetAlertDialog();
          }
        }
      });
      return;
      initializeAutoFunctionAdapter();
    }
  }

  private void readFromPreferenceAndUpdateList()
  {
    SharedPreferences localSharedPreferences = getSharedPreferences(this.preferenceNameArray[wakeupCommandPositonSelected], 0);
    String str1 = localSharedPreferences.getString("wakeupCommand", "");
    int i = localSharedPreferences.getInt("FunctionPosition", -1);
    String str2;
    String str3;
    if (i != -1)
    {
      str2 = getFuncitonName(i);
      str3 = localSharedPreferences.getString("FunctionDetail", "");
      if (str1 == "")
        break label120;
      this.wakeupCommandArrayList.set(wakeupCommandPositonSelected, new WakeupCommandListItem(str1, str2, str3));
    }
    while (true)
    {
      this.wakeupTwoLineListAdapter.notifyDataSetChanged();
      return;
      str2 = "";
      str3 = "";
      break;
      label120: this.wakeupCommandArrayList.set(wakeupCommandPositonSelected, new WakeupCommandListItem(this.wakeupCommandNameArray[wakeupCommandPositonSelected], str2, str3));
    }
  }

  private void resetPreferenceValue()
  {
    SharedPreferences.Editor localEditor = this.wakeupPreference[wakeupCommandPositonSelected].edit();
    if (wakeupCommandPositonSelected == 0)
    {
      localEditor.putString("wakeupCommand", this.wakeupCommandNameArray[wakeupCommandPositonSelected]);
      localEditor.putBoolean("recordSuccess", false);
    }
    while (true)
    {
      this.wakeupTwoLineListAdapter.notifyDataSetChanged();
      this.functionListAdapter.notifyDataSetChanged();
      localEditor.commit();
      return;
      localEditor.putString("wakeupCommand", this.wakeupCommandNameArray[wakeupCommandPositonSelected]);
      localEditor.putString("Function", "");
      localEditor.putString("FunctionDetail", "");
      localEditor.putInt("FunctionPosition", -1);
    }
  }

  private void startCustomCommandRecordingActivity()
  {
    Intent localIntent = new Intent(getApplicationContext(), CustomCommandRecordingActivity.class);
    localIntent.putExtra("trainType", wakeupCommandPositonSelected);
    localIntent.putExtra("svoice", this.isFromMainApp);
    startActivityForResult(localIntent, 1);
  }

  private void updateWakeupSettingUI()
  {
    String str1 = ((FunctionItem)this.functionNameArray.get(mFunctionPositionSelected)).getTitle();
    String str2 = ((FunctionItem)this.functionNameArray.get(mFunctionPositionSelected)).getSubTitle();
    addToPreference(this.wakeupCommandNameArray[wakeupCommandPositonSelected], str1, str2, ((FunctionItem)this.functionNameArray.get(mFunctionPositionSelected)).getIndex());
    this.wakeupCommandListView.setVisibility(0);
    this.functionListView.setVisibility(8);
    showBubble();
    readFromPreferenceAndUpdateList();
    this.wakeupTwoLineListAdapter.notifyDataSetChanged();
  }

  @SuppressLint({"WorldReadableFiles"})
  private void writeWakeupDataFile(String paramString1, String paramString2)
  {
    try
    {
      FileOutputStream localFileOutputStream = getApplicationContext().openFileOutput(paramString1, 1);
      localFileOutputStream.write(paramString2.getBytes());
      localFileOutputStream.close();
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      while (true)
        localFileNotFoundException.printStackTrace();
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public void deleteData(String paramString)
  {
    File localFile = new File(paramString);
    if (localFile.exists())
      localFile.delete();
  }

  public void hideBubble()
  {
    this.customWakeupBubbleTextView.setVisibility(8);
    this.customWakeupBubbleTextView.invalidate();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    this.wakeupCommandListView.setVisibility(0);
    this.functionListView.setVisibility(8);
    showBubble();
    setTitle(getString(2131362530));
    if (paramInt2 == -1)
      switch (paramInt1)
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      }
    while (true)
    {
      mFirstInit = false;
      while (true)
      {
        return;
        if (wakeupCommandPositonSelected == 0)
        {
          this.functionListView.setVisibility(8);
          setTitle(getString(2131362530));
          this.wakeupTwoLineListAdapter.notifyDataSetChanged();
          this.wakeupCommandListView.setVisibility(0);
          addToPreference(this.wakeupCommandNameArray[wakeupCommandPositonSelected], Boolean.valueOf(true));
          showBubble();
          break;
        }
        SharedPreferences localSharedPreferences = getSharedPreferences(this.preferenceNameArray[wakeupCommandPositonSelected], 0);
        localSharedPreferences.getString("wakeupCommand", "");
        String str11 = localSharedPreferences.getString("Function", "");
        int i = localSharedPreferences.getInt("FunctionPosition", -1);
        String str12;
        String str13;
        if (mFirstInit)
        {
          str12 = ((FunctionItem)this.functionNameArray.get(mFunctionPositionSelected)).getTitle();
          i = ((FunctionItem)this.functionNameArray.get(mFunctionPositionSelected)).getIndex();
          str13 = ((FunctionItem)this.functionNameArray.get(mFunctionPositionSelected)).getSubTitle();
        }
        while (true)
        {
          addToPreference(this.wakeupCommandNameArray[wakeupCommandPositonSelected], str12, str13, i);
          readFromPreferenceAndUpdateList();
          if (!mNeedToShowFunctionList)
            break;
          this.functionListAdapter.notifyDataSetChanged();
          this.functionListView.setVisibility(0);
          setTitle(getString(2131362545));
          this.wakeupCommandListView.setVisibility(8);
          showBubble();
          break;
          str12 = str11;
          if (((FunctionItem)this.functionNameArray.get(mFunctionPositionSelected)).getIndex() < 9)
          {
            str13 = ((FunctionItem)this.functionNameArray.get(mFunctionPositionSelected)).getSubTitle();
            continue;
          }
          str13 = localSharedPreferences.getString("FunctionDetail", str11);
        }
        if (paramIntent.getData().equals(null))
        {
          addToPreference(this.wakeupCommandNameArray[wakeupCommandPositonSelected], "", "", -1);
          readFromPreferenceAndUpdateList();
          continue;
        }
        Uri localUri2 = Uri.parse(paramIntent.getDataString());
        Cursor localCursor2 = getContentResolver().query(localUri2, null, null, null, null);
        localCursor2.moveToFirst();
        String str9 = localCursor2.getString(localCursor2.getColumnIndex("data1"));
        String str10 = localCursor2.getString(localCursor2.getColumnIndex("display_name"));
        if (localCursor2 != null)
          localCursor2.close();
        writeWakeupDataFile("wakeupdata_DirectDial.txt", str9);
        ((FunctionItem)this.functionNameArray.get(mFunctionPositionSelected)).setSubTitle(getString(2131362628) + " " + str10);
        if (mFirstInit)
        {
          startCustomCommandRecordingActivity();
          continue;
        }
        updateWakeupSettingUI();
        continue;
        if (paramIntent.getData().equals(null))
        {
          addToPreference(this.wakeupCommandNameArray[wakeupCommandPositonSelected], "", "", -1);
          readFromPreferenceAndUpdateList();
          continue;
        }
        Uri localUri1 = Uri.parse(paramIntent.getDataString());
        Cursor localCursor1 = getContentResolver().query(localUri1, null, null, null, null);
        localCursor1.moveToFirst();
        String str7 = localCursor1.getString(localCursor1.getColumnIndex("data1"));
        String str8 = localCursor1.getString(localCursor1.getColumnIndex("display_name"));
        if (localCursor1 != null)
          localCursor1.close();
        writeWakeupDataFile("wakeupdata_DirectMessage.txt", str7);
        ((FunctionItem)this.functionNameArray.get(mFunctionPositionSelected)).setSubTitle(getString(2131362629) + " " + str8);
        if (mFirstInit)
        {
          startCustomCommandRecordingActivity();
          continue;
        }
        updateWakeupSettingUI();
        continue;
        if (paramIntent == null)
        {
          addToPreference(this.wakeupCommandNameArray[wakeupCommandPositonSelected], "", "", -1);
          readFromPreferenceAndUpdateList();
          continue;
        }
        String str5 = paramIntent.getStringExtra("address");
        String str6 = paramIntent.getStringExtra("name");
        writeWakeupDataFile("wakeupdata_NavigationShortcut.txt", str5);
        ((FunctionItem)this.functionNameArray.get(mFunctionPositionSelected)).setSubTitle(str6);
        if (mFirstInit)
        {
          startCustomCommandRecordingActivity();
          continue;
        }
        updateWakeupSettingUI();
        continue;
        if (paramIntent == null)
        {
          addToPreference(this.wakeupCommandNameArray[wakeupCommandPositonSelected], "", "", -1);
          readFromPreferenceAndUpdateList();
          continue;
        }
        String str3 = paramIntent.getStringExtra("selected_package");
        String str4 = paramIntent.getStringExtra("selected_activity");
        Object localObject = null;
        try
        {
          CharSequence localCharSequence = getPackageManager().getApplicationLabel(getPackageManager().getApplicationInfo(str3, 0));
          localObject = localCharSequence;
          writeWakeupDataFile("wakeupdata_OpenApplication.txt", str3 + "|" + str4);
          ((FunctionItem)this.functionNameArray.get(mFunctionPositionSelected)).setSubTitle(localObject.toString());
          if (mFirstInit)
            startCustomCommandRecordingActivity();
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          while (true)
            localNameNotFoundException.printStackTrace();
          updateWakeupSettingUI();
        }
        continue;
        if (paramInt2 != 111)
          break label1169;
        if (paramIntent == null)
        {
          addToPreference(this.wakeupCommandNameArray[wakeupCommandPositonSelected], "", "", -1);
          readFromPreferenceAndUpdateList();
          continue;
        }
        String str1 = paramIntent.getStringExtra("URL");
        String str2 = paramIntent.getStringExtra("TITLE");
        writeWakeupDataFile("wakeupdata_BookmarkShortcut.txt", str1);
        ((FunctionItem)this.functionNameArray.get(mFunctionPositionSelected)).setSubTitle(str2);
        if (mFirstInit)
        {
          startCustomCommandRecordingActivity();
          continue;
        }
        updateWakeupSettingUI();
      }
      label1169: if (!mFirstInit)
        continue;
      addToPreference(this.wakeupCommandNameArray[wakeupCommandPositonSelected], "", "", -1);
      readFromPreferenceAndUpdateList();
    }
  }

  public void onBackPressed()
  {
    if (this.functionListView.isShown())
    {
      this.wakeupTwoLineListAdapter.notifyDataSetChanged();
      this.wakeupCommandListView.setVisibility(0);
      setTitle(getString(2131362530));
      this.functionListView.setVisibility(8);
      showBubble();
    }
    while (true)
    {
      return;
      if (this.inIUXMode)
      {
        finish();
        startActivity(new Intent(this, IUXCompoundActivity.class));
        continue;
      }
      super.onBackPressed();
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (paramConfiguration != null)
      super.onConfigurationChanged(paramConfiguration);
    WindowManager.LayoutParams localLayoutParams;
    if (isDialogMode())
    {
      this.my_orientation = getResources().getConfiguration().orientation;
      if (paramConfiguration != null)
      {
        localLayoutParams = getWindow().getAttributes();
        Point localPoint = new Point();
        getWindowManager().getDefaultDisplay().getSize(localPoint);
        if (this.my_orientation != 2)
          break label101;
        localLayoutParams.x = (int)this.mOldLX;
      }
    }
    for (localLayoutParams.y = (int)this.mOldLY; ; localLayoutParams.y = (int)this.mOldPY)
    {
      getWindow().setAttributes(localLayoutParams);
      System.gc();
      return;
      label101: localLayoutParams.x = (int)this.mOldPX;
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    DisplayMetrics localDisplayMetrics1 = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics1);
    this.mDensity = localDisplayMetrics1.density;
    DisplayMetrics localDisplayMetrics2;
    Point localPoint;
    float f1;
    float f2;
    if (((localDisplayMetrics1.density == 1.0D) && (localDisplayMetrics1.heightPixels + localDisplayMetrics1.widthPixels == 2032) && (localDisplayMetrics1.xdpi == 149.82489F) && (localDisplayMetrics1.ydpi == 150.51852F)) || ((localDisplayMetrics1.density == 1.0D) && (localDisplayMetrics1.heightPixels + localDisplayMetrics1.widthPixels == 2080) && (localDisplayMetrics1.xdpi == 149.82401F) && (localDisplayMetrics1.ydpi == 150.51801F)))
    {
      mTheme = 2131296529;
      setTheme(mTheme);
      super.onCreate(paramBundle);
      this.density = (getResources().getDisplayMetrics().densityDpi / 160);
      if (!isDialogMode())
        break label720;
      getWindow().clearFlags(2);
      getWindow().addFlags(17170976);
      setContentView(2130903127);
      getWindow().setBackgroundDrawableResource(2130837952);
      ActionBar localActionBar2 = getActionBar();
      localActionBar2.setCustomView(getLayoutInflater().inflate(2130903048, null), new ActionBar.LayoutParams(-1, 40));
      localActionBar2.setDisplayUseLogoEnabled(false);
      localActionBar2.setDisplayOptions(0x10 ^ localActionBar2.getDisplayOptions(), 16);
      localActionBar2.setDisplayShowHomeEnabled(false);
      View localView = localActionBar2.getCustomView();
      1 local1 = new View.OnTouchListener()
      {
        public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
        {
          int i = 1;
          WindowManager.LayoutParams localLayoutParams = CustomWakeupSettingActivity.this.getWindow().getAttributes();
          if (paramMotionEvent.getAction() == 0)
          {
            CustomWakeupSettingActivity.access$002(CustomWakeupSettingActivity.this, paramMotionEvent.getRawX());
            CustomWakeupSettingActivity.access$102(CustomWakeupSettingActivity.this, paramMotionEvent.getRawY());
          }
          while (true)
          {
            return i;
            if (paramMotionEvent.getAction() == 2)
            {
              float f1 = paramMotionEvent.getRawX() - CustomWakeupSettingActivity.this.mOldX;
              float f2 = paramMotionEvent.getRawY() - CustomWakeupSettingActivity.this.mOldY;
              if ((Math.abs(f1) <= 3.0F) && (Math.abs(f2) <= 3.0F))
                continue;
              localLayoutParams.x -= (int)f1;
              localLayoutParams.y -= (int)f2;
              if (CustomWakeupSettingActivity.this.my_orientation == 2)
              {
                CustomWakeupSettingActivity.access$302(CustomWakeupSettingActivity.this, localLayoutParams.x);
                CustomWakeupSettingActivity.access$402(CustomWakeupSettingActivity.this, localLayoutParams.y);
              }
              while (true)
              {
                CustomWakeupSettingActivity.access$002(CustomWakeupSettingActivity.this, paramMotionEvent.getRawX());
                CustomWakeupSettingActivity.access$102(CustomWakeupSettingActivity.this, paramMotionEvent.getRawY());
                CustomWakeupSettingActivity.this.getWindow().setAttributes(localLayoutParams);
                break;
                CustomWakeupSettingActivity.access$502(CustomWakeupSettingActivity.this, localLayoutParams.x);
                CustomWakeupSettingActivity.access$602(CustomWakeupSettingActivity.this, localLayoutParams.y);
              }
            }
            i = 0;
          }
        }
      };
      localView.setOnTouchListener(local1);
      ImageButton localImageButton = (ImageButton)findViewById(2131558546);
      if (localImageButton != null)
      {
        2 local2 = new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            CustomWakeupSettingActivity.this.finish();
          }
        };
        localImageButton.setOnClickListener(local2);
      }
      WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
      localLayoutParams.gravity = 85;
      localLayoutParams.type = 2006;
      localDisplayMetrics2 = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics2);
      localPoint = new Point();
      getWindowManager().getDefaultDisplay().getSize(localPoint);
      if ((getWindowManager().getDefaultDisplay().getRotation() != 1) && (getWindowManager().getDefaultDisplay().getRotation() != 3))
        break label665;
      f1 = 476.0F * localDisplayMetrics2.density;
      f2 = 704.0F * localDisplayMetrics2.density;
      this.mOldLX = ((localPoint.y - f1) / 2.0F);
      this.mOldPX = ((localPoint.x - f1) / 2.0F);
      label473: localLayoutParams.height = (int)f2;
      localLayoutParams.width = (int)f1;
      localLayoutParams.x = ((localPoint.x - localLayoutParams.width) / 2);
      localLayoutParams.y = 0;
      getWindow().setAttributes(localLayoutParams);
      findViewById(2131558839).setBackgroundColor(-16777216);
      findViewById(2131558840).setBackgroundColor(-16777216);
    }
    while (true)
    {
      if ((getWindowManager().getDefaultDisplay().getRotation() == 1) || (getWindowManager().getDefaultDisplay().getRotation() == 3))
        onConfigurationChanged(null);
      initStrings();
      this.isFromMainApp = getIntent().getBooleanExtra("svoice", false);
      this.isFromHelpApp = getIntent().getBooleanExtra("helpapp", false);
      for (int i = 0; i < this.wakeupCommandNameArray.length; i++)
        this.wakeupPreference[i] = getSharedPreferences(this.preferenceNameArray[i], 0);
      mTheme = 2131296526;
      break;
      label665: f1 = 476.0F * localDisplayMetrics2.density;
      f2 = 704.0F * localDisplayMetrics2.density;
      this.mOldPX = ((localPoint.y - f1) / 2.0F);
      this.mOldLX = ((localPoint.x - f1) / 2.0F);
      break label473;
      label720: setContentView(2130903127);
      ActionBar localActionBar1 = getActionBar();
      localActionBar1.setDisplayOptions(14);
      localActionBar1.setTitle(getString(2131362530));
    }
    this.functionListView = ((ListView)findViewById(2131558839));
    this.wakeupCommandListView = ((ListView)findViewById(2131558840));
    this.customWakeupBubbleTextView = ((TextView)findViewById(2131558841));
    this.wakeupCommandArrayList = new ArrayList();
    int j = 0;
    if (j < this.wakeupCommandNameArray.length)
    {
      int k = getPreferenceIntValue(j);
      if (k != -1)
      {
        List localList2 = this.wakeupCommandArrayList;
        WakeupCommandListItem localWakeupCommandListItem2 = new WakeupCommandListItem(this.wakeupCommandNameArray[j], getFuncitonName(k), getFuncitonSubTitle(k));
        localList2.add(localWakeupCommandListItem2);
      }
      while (true)
      {
        j++;
        break;
        List localList1 = this.wakeupCommandArrayList;
        WakeupCommandListItem localWakeupCommandListItem1 = new WakeupCommandListItem(this.wakeupCommandNameArray[j], " ", " ");
        localList1.add(localWakeupCommandListItem1);
      }
    }
    if (enrolled)
      addToPreference(this.wakeupCommandNameArray[0], Boolean.valueOf(true));
    3 local3 = new ArrayAdapter(this, 2130903126, this.wakeupCommandArrayList)
    {
      public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
      {
        TwoLineListItem localTwoLineListItem;
        if (paramView == null)
        {
          localTwoLineListItem = (TwoLineListItem)((LayoutInflater)CustomWakeupSettingActivity.this.getApplicationContext().getSystemService("layout_inflater")).inflate(2130903126, null);
          CustomWakeupSettingActivity.WakeupCommandListItem localWakeupCommandListItem = (CustomWakeupSettingActivity.WakeupCommandListItem)CustomWakeupSettingActivity.this.wakeupCommandArrayList.get(paramInt);
          localTwoLineListItem.getText1().setText(localWakeupCommandListItem.getCommand());
          localTwoLineListItem.getText1().setSelected(true);
          if (paramInt != 0)
            break label139;
          CustomWakeupSettingActivity.this.checkPreferenceValue();
          if (CustomWakeupSettingActivity.wakeupRecordSuccess.booleanValue())
            break label119;
          localTwoLineListItem.getText2().setText(CustomWakeupSettingActivity.this.getString(2131362604));
        }
        while (true)
        {
          return localTwoLineListItem;
          localTwoLineListItem = (TwoLineListItem)paramView;
          break;
          label119: localTwoLineListItem.getText2().setText(CustomWakeupSettingActivity.this.getString(2131362603));
          continue;
          label139: SharedPreferences localSharedPreferences = CustomWakeupSettingActivity.this.getSharedPreferences(CustomWakeupSettingActivity.this.preferenceNameArray[paramInt], 0);
          localTwoLineListItem.getText2().setText(localSharedPreferences.getString("FunctionDetail", localSharedPreferences.getString("Function", "")));
        }
      }
    };
    this.wakeupTwoLineListAdapter = local3;
    this.wakeupCommandListView.setAdapter(this.wakeupTwoLineListAdapter);
    ListView localListView1 = this.wakeupCommandListView;
    4 local4 = new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        CustomWakeupSettingActivity.wakeupCommandPositonSelected = paramInt;
        CustomWakeupSettingActivity.this.checkPreferenceValue();
        if ((CustomWakeupSettingActivity.wakeupCommandPositonSelected > 0) && (!CustomWakeupSettingActivity.this.isFunctionValueSelected.booleanValue()))
        {
          CustomWakeupSettingActivity.this.functionListView.setVisibility(0);
          CustomWakeupSettingActivity.this.setTitle(CustomWakeupSettingActivity.this.getString(2131362545));
          CustomWakeupSettingActivity.this.functionListAdapter.notifyDataSetChanged();
          CustomWakeupSettingActivity.this.wakeupCommandListView.setVisibility(8);
          CustomWakeupSettingActivity.access$1002(true);
          CustomWakeupSettingActivity.this.showBubble();
        }
        while (true)
        {
          return;
          if ((CustomWakeupSettingActivity.wakeupCommandPositonSelected == 0) && (!CustomWakeupSettingActivity.wakeupRecordSuccess.booleanValue()))
          {
            CustomWakeupSettingActivity.this.startCustomCommandRecordingActivity();
            continue;
          }
          CustomWakeupSettingActivity.this.launchWakeupDialog();
        }
      }
    };
    localListView1.setOnItemClickListener(local4);
    5 local5 = new ArrayAdapter(this, 2130903074, this.functionNameArray)
    {
      public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
      {
        if (paramView == null);
        for (TextView localTextView = (TextView)((LayoutInflater)CustomWakeupSettingActivity.this.getBaseContext().getSystemService("layout_inflater")).inflate(2130903074, null); ; localTextView = (TextView)paramView)
        {
          localTextView.setText(((CustomWakeupSettingActivity.FunctionItem)CustomWakeupSettingActivity.this.functionNameArray.get(paramInt)).getTitle());
          localTextView.setContentDescription(((CustomWakeupSettingActivity.FunctionItem)CustomWakeupSettingActivity.this.functionNameArray.get(paramInt)).getDescription());
          CustomWakeupSettingActivity.this.getSharedPreferences(CustomWakeupSettingActivity.this.preferenceNameArray[CustomWakeupSettingActivity.wakeupCommandPositonSelected], 0).getString("Function", "");
          return localTextView;
        }
      }
    };
    this.functionListAdapter = local5;
    this.functionListView.setItemsCanFocus(false);
    this.functionListView.setChoiceMode(1);
    this.functionListView.setAdapter(this.functionListAdapter);
    ListView localListView2 = this.functionListView;
    6 local6 = new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        CustomWakeupSettingActivity.mFunctionPositionSelected = paramInt;
        if (!CustomWakeupSettingActivity.this.isFunctionAvailable(((CustomWakeupSettingActivity.FunctionItem)CustomWakeupSettingActivity.this.functionNameArray.get(paramInt)).getIndex()))
          CustomWakeupSettingActivity.this.launchFunctionAlertDialog();
        while (true)
        {
          label40: return;
          if ((!CustomWakeupSettingActivity.mFirstInit) && (((CustomWakeupSettingActivity.FunctionItem)CustomWakeupSettingActivity.this.functionNameArray.get(paramInt)).getIndex() < 9))
            CustomWakeupSettingActivity.this.updateWakeupSettingUI();
          int[] arrayOfInt = new int[4];
          int i = ((CustomWakeupSettingActivity.FunctionItem)CustomWakeupSettingActivity.this.functionNameArray.get(paramInt)).getIndex();
          if (CustomWakeupSettingActivity.this.mVoiceEngine.functionAssignment("/data/data/com.vlingo.midas/typeDefine.bin", arrayOfInt, 0) == -1)
          {
            arrayOfInt[0] = -1;
            arrayOfInt[1] = -1;
            arrayOfInt[2] = -1;
            arrayOfInt[3] = -1;
          }
          switch (CustomWakeupSettingActivity.wakeupCommandPositonSelected)
          {
          default:
            CustomWakeupSettingActivity.this.showBubble();
          case 1:
          case 2:
          case 3:
          case 4:
          }
          while (true)
          {
            CustomWakeupSettingActivity.this.mVoiceEngine.functionAssignment("/data/data/com.vlingo.midas/typeDefine.bin", arrayOfInt, 1);
            switch (((CustomWakeupSettingActivity.FunctionItem)CustomWakeupSettingActivity.this.functionNameArray.get(paramInt)).getIndex())
            {
            default:
              if (!CustomWakeupSettingActivity.mFirstInit)
                break label40;
              CustomWakeupSettingActivity.this.startCustomCommandRecordingActivity();
              break label40;
              MidasSettings.setInt("kew_wake_up_and_auto_function1", i);
              arrayOfInt[0] = i;
              continue;
              MidasSettings.setInt("kew_wake_up_and_auto_function2", i);
              arrayOfInt[1] = i;
              continue;
              MidasSettings.setInt("kew_wake_up_and_auto_function3", i);
              arrayOfInt[2] = i;
              continue;
              MidasSettings.setInt("kew_wake_up_and_auto_function4", i);
              arrayOfInt[3] = i;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            }
          }
          Intent localIntent5 = new Intent("android.intent.action.PICK", Uri.parse("content://contacts"));
          localIntent5.setType("vnd.android.cursor.dir/phone_v2");
          CustomWakeupSettingActivity.this.startActivityForResult(localIntent5, 2);
          continue;
          Intent localIntent4 = new Intent("android.intent.action.PICK", Uri.parse("content://contacts"));
          localIntent4.setType("vnd.android.cursor.dir/phone_v2");
          CustomWakeupSettingActivity.this.startActivityForResult(localIntent4, 3);
          continue;
          Intent localIntent3 = new Intent();
          localIntent3.setClassName("com.sec.android.app.sbrowser", "com.sec.android.app.sbrowser.BookmarkShowActivity");
          localIntent3.putExtra("actionbar_title", CustomWakeupSettingActivity.this.getString(2131362711));
          CustomWakeupSettingActivity.this.startActivityForResult(localIntent3, 111);
          continue;
          Intent localIntent2 = new Intent(CustomWakeupSettingActivity.this.getApplicationContext(), NavigationSetting.class);
          localIntent2.setAction("android.intent.action.CREATE_SHORTCUT");
          CustomWakeupSettingActivity.this.startActivityForResult(localIntent2, 4);
          continue;
          Intent localIntent1 = new Intent();
          localIntent1.setClassName("com.android.settings", "com.android.settings.lockscreenshortcut.AppList");
          localIntent1.putExtra("clicked_view_index", 0);
          localIntent1.putExtra("shortcut_app_list", "");
          localIntent1.putExtra("num_of_shortcut", 0);
          localIntent1.putExtra("max_num_of_shortcut", 0);
          CustomWakeupSettingActivity.this.startActivityForResult(localIntent1, 5);
        }
      }
    };
    localListView2.setOnItemClickListener(local6);
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
    case 16908332:
    }
    while (true)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      if (this.functionListView.isShown())
      {
        this.wakeupTwoLineListAdapter.notifyDataSetChanged();
        this.wakeupCommandListView.setVisibility(0);
        setTitle(getString(2131362530));
        this.functionListView.setVisibility(8);
        showBubble();
        continue;
      }
      if (this.inIUXMode)
      {
        finish();
        startActivity(new Intent(this, IUXCompoundActivity.class));
        continue;
      }
      finish();
    }
  }

  protected void onPause()
  {
    super.onPause();
    if (this.inIUXMode)
      finish();
  }

  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
    if (Boolean.valueOf(paramBundle.getBoolean("isFuncListVisible", false)).booleanValue())
    {
      this.functionListView.setVisibility(0);
      setTitle(getString(2131362545));
      if (!Boolean.valueOf(paramBundle.getBoolean("isWakeupListVisible", false)).booleanValue())
        break label108;
      this.wakeupCommandListView.setVisibility(0);
      setTitle(getString(2131362530));
    }
    while (true)
    {
      showBubble();
      this.wakeupTwoLineListAdapter.notifyDataSetChanged();
      this.functionListAdapter.notifyDataSetChanged();
      return;
      this.functionListView.setVisibility(8);
      break;
      label108: this.wakeupCommandListView.setVisibility(8);
    }
  }

  protected void onResume()
  {
    super.onResume();
    MidasSettings.updateCurrentLocale();
    this.inIUXMode = getIntent().getBooleanExtra("wycs.show.done", false);
    Intent localIntent = new Intent(this, VlingoApplicationService.class);
    localIntent.setAction("com.vlingo.client.app.action.ACTIVITY_STATE_CHANGED");
    localIntent.putExtra("com.vlingo.client.app.extra.STATE", 0);
    startService(localIntent);
    showBubble();
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putBoolean("isFuncListVisible", this.functionListView.isShown());
    paramBundle.putBoolean("isWakeupListVisible", this.wakeupCommandListView.isShown());
  }

  public void showBubble()
  {
    if ((this.wakeupCommandListView == null) || (this.functionListView == null));
    while (true)
    {
      return;
      if ((this.customWakeupBubbleTextView != null) && (this.isFromHelpApp))
      {
        this.customWakeupBubbleTextView.setVisibility(0);
        if (this.functionListView.isShown())
        {
          this.customWakeupBubbleTextView.setText(getString(2131362795));
          this.customWakeupBubbleTextView.invalidate();
          continue;
        }
        this.customWakeupBubbleTextView.setText(getString(2131362794));
        this.customWakeupBubbleTextView.invalidate();
        continue;
      }
    }
  }

  private class FunctionItem
  {
    private String mDescription;
    private int mIndex;
    private String mSubTitle = null;
    private String mTitle;

    public FunctionItem(String paramInt, int arg3)
    {
      this.mTitle = paramInt;
      int i;
      this.mIndex = i;
    }

    public FunctionItem(String paramString1, String paramInt, int arg4)
    {
      this.mTitle = paramString1;
      int i;
      this.mIndex = i;
      this.mDescription = paramInt;
    }

    public String getDescription()
    {
      return this.mDescription;
    }

    public int getIndex()
    {
      return this.mIndex;
    }

    public String getSubTitle()
    {
      return this.mSubTitle;
    }

    public String getTitle()
    {
      return this.mTitle;
    }

    public void setDescription(String paramString)
    {
      this.mDescription = paramString;
    }

    public void setIndex(int paramInt)
    {
      this.mIndex = paramInt;
    }

    public void setSubTitle(String paramString)
    {
      this.mSubTitle = paramString;
    }

    public void setTitle(String paramString)
    {
      this.mTitle = paramString;
    }
  }

  public class WakeupCommandListItem
  {
    private String detail;
    private String function;
    private String wakeupcommand;

    public WakeupCommandListItem(String paramString1, String paramString2, String arg4)
    {
      this.wakeupcommand = paramString1;
      this.function = paramString2;
      Object localObject;
      this.detail = localObject;
    }

    public String getCommand()
    {
      return this.wakeupcommand;
    }

    public String getFunction()
    {
      return this.function;
    }

    public String getFunctionDetail()
    {
      return this.detail;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wakeupsetting.CustomWakeupSettingActivity
 * JD-Core Version:    0.6.0
 */