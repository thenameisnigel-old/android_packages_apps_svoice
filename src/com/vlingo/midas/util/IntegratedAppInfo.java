package com.vlingo.midas.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.vlingo.core.internal.dialogmanager.util.IntegratedAppInfoInterface;
import com.vlingo.core.internal.dialogmanager.util.IntegratedAppInfoInterface.IntegrateAppType;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ApplicationUtil;
import com.vlingo.midas.memo.SMemo2Util;
import com.vlingo.sdk.internal.util.PackageUtil;

public class IntegratedAppInfo
  implements IntegratedAppInfoInterface
{
  private static String AUTHORITY_MEMO;
  private static String AUTHORITY_SMEMO = "com.sec.android.widgetapp.q1_penmemo";
  private static final String AUTHORITY_SMEMO2 = "com.infraware.provider.SNoteProvider";
  private static final String AUTHORITY_SNOTE = "com.infraware.provider.SNoteProvider";
  private static final Uri CONTENT_URI_MEMO;
  private static final Uri CONTENT_URI_SMEMO = Uri.parse("content://" + AUTHORITY_SMEMO + "/" + "PenMemo");
  private static final Uri CONTENT_URI_SMEMO2;
  private static final Uri CONTENT_URI_SNOTE;
  private static final String EXEC_NAME_MEMO = "com.sec.android.app.memo.Memo";
  private static final String EXEC_NAME_SMEMO = "com.sec.android.widgetapp.q1_penmemo.MemoListActivity";
  private static final String EXEC_PACKAGE_MEMO = "com.sec.android.app.memo";
  private static final String EXEC_PACKAGE_SMEMO = "com.sec.android.widgetapp.diotek.smemo";
  private static final String EXEC_PACKAGE_SMEMO2 = "com.sec.android.app.snotebook";
  private static final String EXEC_PACKAGE_SNOTE = "com.sec.android.app.snotebook";
  private static final String INTENT_NAME_CREATE_MEMO = "com.sec.android.app.memo.MAKE_NEW_MEMO";
  private static final String INTENT_NAME_CREATE_SMEMO = "com.sec.android.widgetapp.diotek.smemo.MAKE_NEW_MEMO";
  private static final String INTENT_NAME_CREATE_SMEMO2 = "com.sec.android.memo.CREATE_TMEMO";
  private static final String INTENT_NAME_CREATE_SNOTE = "com.sec.android.app.snotebook.MAKE_NEW_NOTE";
  private static final String INTENT_NAME_DELETE_SMEMO2 = "com.sec.android.memo.DELETE_ID";
  private static final String INTENT_NAME_START_FM_RADIO = "com.sec.android.app.fm.widget.on";
  private static final String TABLE_MEMO = "memo/id";
  private static final String TABLE_SMEMO = "PenMemo";
  private static final String TABLE_SMEMO2 = "fileMgr";
  private static final String TABLE_SNOTE = "fileMgr";
  private static final Uri UPDATE_CONTENT_URI_MEMO;
  private static final Uri UPDATE_CONTENT_URI_SMEMO = CONTENT_URI_SMEMO;
  private static final Uri UPDATE_CONTENT_URI_SMEMO2;
  private static final Uri UPDATE_CONTENT_URI_SNOTE;
  private IntegratedAppInfoInterface.IntegrateAppType appType;
  private Uri contentProviderUri;
  private String execName;
  private String execPackage;
  private String intentNameCreate;
  private String intentNameDelete;
  private String intentNameStart;
  private boolean isBroadcast;
  private Uri updateContentProviderUri;

  static
  {
    CONTENT_URI_SNOTE = Uri.parse("content://com.infraware.provider.SNoteProvider/fileMgr");
    UPDATE_CONTENT_URI_SNOTE = CONTENT_URI_SNOTE;
    CONTENT_URI_SMEMO2 = Uri.parse("content://com.infraware.provider.SNoteProvider/fileMgr");
    UPDATE_CONTENT_URI_SMEMO2 = CONTENT_URI_SMEMO2;
    AUTHORITY_MEMO = "com.samsung.sec.android";
    CONTENT_URI_MEMO = Uri.parse("content://" + AUTHORITY_MEMO + "/" + "memo/id");
    UPDATE_CONTENT_URI_MEMO = Uri.parse("content://" + AUTHORITY_MEMO + "/memo");
  }

  public IntegratedAppInfo(IntegratedAppInfoInterface.IntegrateAppType paramIntegrateAppType)
  {
    this.appType = paramIntegrateAppType;
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    this.isBroadcast = true;
    switch (1.$SwitchMap$com$vlingo$core$internal$dialogmanager$util$IntegratedAppInfoInterface$IntegrateAppType[paramIntegrateAppType.ordinal()])
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      return;
      if (ApplicationUtil.checkApplicationExists(localContext, "com.sec.android.app.snotebook"))
      {
        this.intentNameCreate = "com.sec.android.app.snotebook.MAKE_NEW_NOTE";
        this.contentProviderUri = CONTENT_URI_SNOTE;
        this.updateContentProviderUri = UPDATE_CONTENT_URI_SNOTE;
        continue;
      }
      if (SMemo2Util.isInstalled())
      {
        this.intentNameCreate = "com.sec.android.memo.CREATE_TMEMO";
        this.contentProviderUri = CONTENT_URI_SMEMO2;
        this.updateContentProviderUri = UPDATE_CONTENT_URI_SMEMO2;
        this.intentNameDelete = "com.sec.android.memo.DELETE_ID";
        this.isBroadcast = false;
        continue;
      }
      if ((!isIntentAvailable(localContext, "com.sec.android.widgetapp.diotek.smemo.MAKE_NEW_MEMO")) && (!ApplicationUtil.checkApplicationExists(localContext, "com.sec.android.widgetapp.diotek.smemo")))
      {
        this.execName = "com.sec.android.app.memo.Memo";
        this.execPackage = "com.sec.android.app.memo";
        this.intentNameCreate = "com.sec.android.app.memo.MAKE_NEW_MEMO";
        this.contentProviderUri = CONTENT_URI_MEMO;
        this.updateContentProviderUri = UPDATE_CONTENT_URI_MEMO;
        continue;
      }
      this.execName = "com.sec.android.widgetapp.q1_penmemo.MemoListActivity";
      this.execPackage = "com.sec.android.widgetapp.diotek.smemo";
      this.intentNameCreate = "com.sec.android.widgetapp.diotek.smemo.MAKE_NEW_MEMO";
      this.contentProviderUri = CONTENT_URI_SMEMO;
      this.updateContentProviderUri = UPDATE_CONTENT_URI_SMEMO;
      continue;
      this.intentNameStart = "com.sec.android.app.fm.widget.on";
    }
  }

  private boolean isIntentAvailable(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramString);
    if (this.isBroadcast);
    for (boolean bool = PackageUtil.canHandleBroadcastIntent(paramContext, localIntent); ; bool = PackageUtil.canHandleIntent(paramContext, localIntent))
      return bool;
  }

  public IntegratedAppInfoInterface.IntegrateAppType getAppType()
  {
    return this.appType;
  }

  public Uri getContentProviderUri()
  {
    return this.contentProviderUri;
  }

  public String getExecName()
  {
    return this.execName;
  }

  public String getExecPackage()
  {
    return this.execPackage;
  }

  public String getIntentNameCreate()
  {
    return this.intentNameCreate;
  }

  public String getIntentNameDelete()
  {
    return this.intentNameDelete;
  }

  public String getIntentNameStart()
  {
    return this.intentNameStart;
  }

  public Uri getUpdateContentProviderUri()
  {
    return this.updateContentProviderUri;
  }

  public boolean isBroadcast()
  {
    return this.isBroadcast;
  }

  public boolean isSNote()
  {
    return isIntentAvailable(ApplicationAdapter.getInstance().getApplicationContext(), "com.sec.android.app.snotebook.MAKE_NEW_NOTE");
  }

  public void setAppType(IntegratedAppInfoInterface.IntegrateAppType paramIntegrateAppType)
  {
    this.appType = paramIntegrateAppType;
  }

  public void setBroadcast(boolean paramBoolean)
  {
    this.isBroadcast = paramBoolean;
  }

  public void setContentProviderUri(Uri paramUri)
  {
    this.contentProviderUri = paramUri;
  }

  public void setExecName(String paramString)
  {
    this.execName = paramString;
  }

  public void setExecPackage(String paramString)
  {
    this.execPackage = paramString;
  }

  public void setIntentNameCreate(String paramString)
  {
    this.intentNameCreate = paramString;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.IntegratedAppInfo
 * JD-Core Version:    0.6.0
 */