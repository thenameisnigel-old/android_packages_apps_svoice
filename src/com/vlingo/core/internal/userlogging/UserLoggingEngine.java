package com.vlingo.core.internal.userlogging;

import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.sdk.services.userlogging.VLErrorRecord.Builder;
import com.vlingo.sdk.services.userlogging.VLHelpPageRecord.Builder;
import com.vlingo.sdk.services.userlogging.VLLandingPageRecord.Builder;
import com.vlingo.sdk.services.userlogging.VLLandingPageRecord.TextFieldUsageCounts;
import com.vlingo.sdk.services.userlogging.VLUserLoggerLogRecord;
import com.vlingo.sdk.services.userlogging.VLUserLoggerLogRecord.Builder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class UserLoggingEngine
{
  public static final int ACTION_ALT_PHRASE_SELECTED = 101;
  public static final int ACTION_CONTACT_CHANGE = 102;
  public static final int ACTION_NONE = 0;
  public static final int ACTION_NOTE_CHANGE = 104;
  public static final int ACTION_NUMBER_CHANGE = 103;
  public static final int ACTION_UNDO = 105;
  public static final boolean ERROR_LOGGING_ENABLED = true;
  public static final boolean FIELD_LOGGING_ENABLED = true;
  public static final boolean HELP_LOGGING_ENABLED = true;
  public static final boolean LANDING_PAGE_LOGGING_ENABLED = true;
  private static final int RECORD_TRANSMIT_THRESHOLD = 50;
  public static final boolean SETTINGS_LOGGING_ENABLED = true;
  public static final boolean TIMING_LOGGING_ENABLED = true;
  public static final boolean USERLOGGING_ENGINE_ENABLED = true;
  private static UserLoggingEngine smInstance;
  private HashMap<String, VLErrorRecord.Builder> mErrorRecordBuilders = new HashMap();
  private HashMap<String, VLHelpPageRecord.Builder> mHelpPageRecordBuilders = new HashMap();
  private HashMap<String, VLLandingPageRecord.Builder> mLandingPageRecordBuilders = new HashMap();
  private VLUserLoggerLogRecord.Builder mLogRecordBuilder = new VLUserLoggerLogRecord.Builder();
  private long mStartTime;

  private VLErrorRecord.Builder getErrorRecordBuilder(String paramString)
  {
    VLErrorRecord.Builder localBuilder = (VLErrorRecord.Builder)this.mErrorRecordBuilders.get(paramString);
    if (localBuilder == null)
    {
      localBuilder = new VLErrorRecord.Builder(paramString);
      this.mErrorRecordBuilders.put(paramString, localBuilder);
    }
    return localBuilder;
  }

  private VLHelpPageRecord.Builder getHelpPageRecordBuilder(String paramString)
  {
    VLHelpPageRecord.Builder localBuilder = (VLHelpPageRecord.Builder)this.mHelpPageRecordBuilders.get(paramString);
    if (localBuilder == null)
    {
      localBuilder = new VLHelpPageRecord.Builder(paramString);
      this.mHelpPageRecordBuilders.put(paramString, localBuilder);
    }
    return localBuilder;
  }

  public static UserLoggingEngine getInstance()
  {
    if (smInstance == null)
      smInstance = new UserLoggingEngine();
    return smInstance;
  }

  private VLLandingPageRecord.Builder getLandingPageRecordBuilder(String paramString)
  {
    VLLandingPageRecord.Builder localBuilder = (VLLandingPageRecord.Builder)this.mLandingPageRecordBuilders.get(paramString);
    if (localBuilder == null)
    {
      localBuilder = new VLLandingPageRecord.Builder(paramString, true);
      this.mLandingPageRecordBuilders.put(paramString, localBuilder);
    }
    return localBuilder;
  }

  private int getRecordSize()
  {
    int i = this.mHelpPageRecordBuilders.size() + this.mErrorRecordBuilders.size() + (int)(4.5D * this.mLandingPageRecordBuilders.size());
    Iterator localIterator = this.mLandingPageRecordBuilders.values().iterator();
    while (localIterator.hasNext())
    {
      VLLandingPageRecord.Builder localBuilder = (VLLandingPageRecord.Builder)localIterator.next();
      i = (int)(i + 3.5D * localBuilder.getFieldCount());
    }
    return i;
  }

  private void resetBuilders()
  {
    this.mLogRecordBuilder = new VLUserLoggerLogRecord.Builder();
    this.mLandingPageRecordBuilders.clear();
    this.mHelpPageRecordBuilders.clear();
    this.mErrorRecordBuilders.clear();
  }

  private void transmitIfNecessary()
  {
    monitorenter;
    try
    {
      Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
      Intent localIntent;
      if (localContext != null)
      {
        localIntent = new Intent(localContext, UALService.class);
        if (getRecordSize() <= 50)
          break label50;
        localIntent.putExtra("com.vlingo.client.userlogging.skipInitialDelay", true);
        localContext.startService(localIntent);
      }
      while (true)
      {
        return;
        label50: localContext.startService(localIntent);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  // ERROR //
  public void errorDisplayed(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 164	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 165	java/lang/StringBuilder:<init>	()V
    //   9: aload_1
    //   10: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   13: ldc 171
    //   15: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: aload_2
    //   19: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: invokevirtual 175	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: astore 4
    //   27: ldc 177
    //   29: aload_1
    //   30: invokevirtual 183	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   33: ifne +16 -> 49
    //   36: ldc 185
    //   38: aload_1
    //   39: invokevirtual 183	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   42: istore 10
    //   44: iload 10
    //   46: ifeq +179 -> 225
    //   49: new 164	java/lang/StringBuilder
    //   52: dup
    //   53: invokespecial 165	java/lang/StringBuilder:<init>	()V
    //   56: aload 4
    //   58: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: ldc 187
    //   63: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: invokestatic 192	com/vlingo/sdk/internal/net/ConnectionManager:getInstance	()Lcom/vlingo/sdk/internal/net/ConnectionManager;
    //   69: invokevirtual 195	com/vlingo/sdk/internal/net/ConnectionManager:getGsmSignal	()I
    //   72: invokevirtual 198	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   75: invokevirtual 175	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   78: astore 4
    //   80: new 164	java/lang/StringBuilder
    //   83: dup
    //   84: invokespecial 165	java/lang/StringBuilder:<init>	()V
    //   87: aload 4
    //   89: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: ldc 200
    //   94: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: invokestatic 192	com/vlingo/sdk/internal/net/ConnectionManager:getInstance	()Lcom/vlingo/sdk/internal/net/ConnectionManager;
    //   100: invokevirtual 203	com/vlingo/sdk/internal/net/ConnectionManager:getCurrentConnectionType	()Ljava/lang/String;
    //   103: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: invokevirtual 175	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   109: astore 4
    //   111: new 164	java/lang/StringBuilder
    //   114: dup
    //   115: invokespecial 165	java/lang/StringBuilder:<init>	()V
    //   118: aload 4
    //   120: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: ldc 205
    //   125: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   128: astore 7
    //   130: invokestatic 192	com/vlingo/sdk/internal/net/ConnectionManager:getInstance	()Lcom/vlingo/sdk/internal/net/ConnectionManager;
    //   133: invokevirtual 209	com/vlingo/sdk/internal/net/ConnectionManager:getNetworkInfo	()Landroid/net/NetworkInfo;
    //   136: invokevirtual 214	android/net/NetworkInfo:getType	()I
    //   139: iconst_1
    //   140: if_icmpne +102 -> 242
    //   143: ldc 216
    //   145: astore 8
    //   147: aload 7
    //   149: aload 8
    //   151: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: invokevirtual 175	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   157: astore 4
    //   159: new 164	java/lang/StringBuilder
    //   162: dup
    //   163: invokespecial 165	java/lang/StringBuilder:<init>	()V
    //   166: aload 4
    //   168: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: ldc 218
    //   173: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: invokestatic 192	com/vlingo/sdk/internal/net/ConnectionManager:getInstance	()Lcom/vlingo/sdk/internal/net/ConnectionManager;
    //   179: invokevirtual 221	com/vlingo/sdk/internal/net/ConnectionManager:getNetworkTypeName	()Ljava/lang/String;
    //   182: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: invokevirtual 175	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   188: astore 4
    //   190: new 164	java/lang/StringBuilder
    //   193: dup
    //   194: invokespecial 165	java/lang/StringBuilder:<init>	()V
    //   197: aload 4
    //   199: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: ldc 223
    //   204: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: invokestatic 192	com/vlingo/sdk/internal/net/ConnectionManager:getInstance	()Lcom/vlingo/sdk/internal/net/ConnectionManager;
    //   210: invokevirtual 203	com/vlingo/sdk/internal/net/ConnectionManager:getCurrentConnectionType	()Ljava/lang/String;
    //   213: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: invokevirtual 175	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   219: astore 9
    //   221: aload 9
    //   223: astore 4
    //   225: aload_0
    //   226: aload 4
    //   228: invokespecial 225	com/vlingo/core/internal/userlogging/UserLoggingEngine:getErrorRecordBuilder	(Ljava/lang/String;)Lcom/vlingo/sdk/services/userlogging/VLErrorRecord$Builder;
    //   231: invokevirtual 228	com/vlingo/sdk/services/userlogging/VLErrorRecord$Builder:errorDisplayed	()Lcom/vlingo/sdk/services/userlogging/VLErrorRecord$Builder;
    //   234: pop
    //   235: aload_0
    //   236: invokespecial 230	com/vlingo/core/internal/userlogging/UserLoggingEngine:transmitIfNecessary	()V
    //   239: aload_0
    //   240: monitorexit
    //   241: return
    //   242: ldc 232
    //   244: astore 8
    //   246: goto -99 -> 147
    //   249: astore_3
    //   250: aload_0
    //   251: monitorexit
    //   252: aload_3
    //   253: athrow
    //   254: astore 5
    //   256: goto -31 -> 225
    //
    // Exception table:
    //   from	to	target	type
    //   2	44	249	finally
    //   49	221	249	finally
    //   225	239	249	finally
    //   242	246	249	finally
    //   49	221	254	java/lang/Exception
    //   242	246	254	java/lang/Exception
  }

  VLUserLoggerLogRecord flushUserLogRecord()
  {
    monitorenter;
    try
    {
      this.mLogRecordBuilder.setupStarted(false);
      this.mLogRecordBuilder.setupFinished(false);
      Iterator localIterator1 = this.mErrorRecordBuilders.values().iterator();
      while (localIterator1.hasNext())
      {
        VLErrorRecord.Builder localBuilder2 = (VLErrorRecord.Builder)localIterator1.next();
        this.mLogRecordBuilder.addErrorRecord(localBuilder2.build());
      }
    }
    finally
    {
      monitorexit;
    }
    Iterator localIterator2 = this.mHelpPageRecordBuilders.values().iterator();
    while (localIterator2.hasNext())
    {
      VLHelpPageRecord.Builder localBuilder1 = (VLHelpPageRecord.Builder)localIterator2.next();
      this.mLogRecordBuilder.addHelpPageRecord(localBuilder1.build());
    }
    Iterator localIterator3 = this.mLandingPageRecordBuilders.values().iterator();
    while (localIterator3.hasNext())
    {
      VLLandingPageRecord.Builder localBuilder = (VLLandingPageRecord.Builder)localIterator3.next();
      this.mLogRecordBuilder.addLandingPageRecord(localBuilder.build());
    }
    VLUserLoggerLogRecord localVLUserLoggerLogRecord = this.mLogRecordBuilder.build();
    resetBuilders();
    monitorexit;
    return localVLUserLoggerLogRecord;
  }

  public void helpPageViewed(String paramString)
  {
    monitorenter;
    try
    {
      if (paramString.length() > 128)
        paramString = paramString.substring(0, 128);
      getHelpPageRecordBuilder(paramString).pageViewed();
      transmitIfNecessary();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void landingPageAction(String paramString, List<VLLandingPageRecord.TextFieldUsageCounts> paramList, boolean paramBoolean)
  {
    monitorenter;
    if (paramBoolean);
    try
    {
      paramString = "AutoAction:" + paramString;
      getLandingPageRecordBuilder(paramString).actionClicked(paramList);
      transmitIfNecessary();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void landingPageActionEvent(String paramString, int paramInt)
  {
    monitorenter;
    while (true)
    {
      VLLandingPageRecord.Builder localBuilder;
      try
      {
        localBuilder = getLandingPageRecordBuilder(paramString);
        switch (paramInt)
        {
        default:
          transmitIfNecessary();
          return;
        case 101:
          localBuilder.alterPhrasePicked();
          continue;
        case 102:
        case 103:
        case 104:
        case 105:
        }
      }
      finally
      {
        monitorexit;
      }
      localBuilder.incrContactChange();
      continue;
      localBuilder.incrPhoneChange();
      continue;
      localBuilder.incrNoteChanged();
      continue;
      localBuilder.incrUndoCount();
    }
  }

  public void landingPageCanceled(String paramString, List<VLLandingPageRecord.TextFieldUsageCounts> paramList)
  {
    monitorenter;
    try
    {
      getLandingPageRecordBuilder(paramString).backClicked(paramList);
      transmitIfNecessary();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void landingPageViewed(String paramString)
  {
    monitorenter;
    try
    {
      VLLandingPageRecord.Builder localBuilder = getLandingPageRecordBuilder(paramString);
      localBuilder.pageViewed();
      localBuilder.addLaunchTime(System.currentTimeMillis() - this.mStartTime);
      transmitIfNecessary();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void settingsChanged()
  {
    monitorenter;
    try
    {
      this.mLogRecordBuilder.settings("");
      transmitIfNecessary();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void timeApplicationStart()
  {
    monitorenter;
    try
    {
      this.mStartTime = System.currentTimeMillis();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.userlogging.UserLoggingEngine
 * JD-Core Version:    0.6.0
 */