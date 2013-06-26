package com.vlingo.core.internal.contacts;

import android.content.Context;
import android.os.Handler;
import com.vlingo.core.internal.logging.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.List<Lcom.vlingo.core.internal.contacts.ContactMatch;>;

public class ContactMatcher
  implements AsyncContactSorterCallback
{
  public static final String ACTION_ADDRESS = "address";
  public static final String ACTION_BIRTHDAY = "birthday";
  public static final String ACTION_CALL = "call";
  public static final String ACTION_EMAIL = "email";
  public static final String ACTION_FACEBOOK = "facebook";
  public static final String ACTION_MESSAGE = "message";
  public static final String ACTION_SMS = "sms";
  public static final int TYPE_AUTO_ACTION_ALWAYS = 2;
  public static final int TYPE_AUTO_ACTION_IF_CONFIDENT = 1;
  public static final int TYPE_AUTO_ACTION_NEVER;
  private static Logger log = Logger.getLogger(ContactMatcher.class);
  private static int sInstanceCounter = 0;
  private boolean isAddressBookEmpty;
  private final ContactType mActionType;
  private final int[] mAddressTypes;
  private int mAutoActionType;
  private boolean mCheckAutoAction;
  private String mContactRequest;
  private Context mContext;
  private final int[] mEmailTypes;
  private final Handler mHandler;
  final ContactMatchListener mListener;
  private final int[] mPhoneTypes;
  private final float mRecognizerConfidenceScore;
  private final ContactSortRequest mRequest;
  private Thread mRequestThread;
  private final int[] mSocialTypes;
  private List<ContactMatch> mSuperList;

  // ERROR //
  public ContactMatcher(Context paramContext, ContactMatchListener paramContactMatchListener, ContactType paramContactType, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4, String paramString, float paramFloat, List<ContactMatch> paramList)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 88	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: iconst_0
    //   6: putfield 90	com/vlingo/core/internal/contacts/ContactMatcher:mAutoActionType	I
    //   9: aload_0
    //   10: iconst_0
    //   11: putfield 92	com/vlingo/core/internal/contacts/ContactMatcher:isAddressBookEmpty	Z
    //   14: aload_0
    //   15: aconst_null
    //   16: putfield 94	com/vlingo/core/internal/contacts/ContactMatcher:mSuperList	Ljava/util/List;
    //   19: aload_0
    //   20: aconst_null
    //   21: putfield 96	com/vlingo/core/internal/contacts/ContactMatcher:mContactRequest	Ljava/lang/String;
    //   24: aload_0
    //   25: aconst_null
    //   26: putfield 98	com/vlingo/core/internal/contacts/ContactMatcher:mRequestThread	Ljava/lang/Thread;
    //   29: aload_0
    //   30: aconst_null
    //   31: putfield 100	com/vlingo/core/internal/contacts/ContactMatcher:mContext	Landroid/content/Context;
    //   34: aload 8
    //   36: ifnull +14 -> 50
    //   39: aload 8
    //   41: invokevirtual 106	java/lang/String:trim	()Ljava/lang/String;
    //   44: invokevirtual 110	java/lang/String:length	()I
    //   47: ifne +13 -> 60
    //   50: new 112	java/lang/IllegalArgumentException
    //   53: dup
    //   54: ldc 114
    //   56: invokespecial 117	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   59: athrow
    //   60: aload_0
    //   61: aload_2
    //   62: putfield 119	com/vlingo/core/internal/contacts/ContactMatcher:mListener	Lcom/vlingo/core/internal/contacts/ContactMatchListener;
    //   65: aload_0
    //   66: new 121	android/os/Handler
    //   69: dup
    //   70: invokespecial 122	android/os/Handler:<init>	()V
    //   73: putfield 124	com/vlingo/core/internal/contacts/ContactMatcher:mHandler	Landroid/os/Handler;
    //   76: aload_0
    //   77: aload_3
    //   78: putfield 126	com/vlingo/core/internal/contacts/ContactMatcher:mActionType	Lcom/vlingo/core/internal/contacts/ContactType;
    //   81: aload_0
    //   82: aload 4
    //   84: putfield 128	com/vlingo/core/internal/contacts/ContactMatcher:mPhoneTypes	[I
    //   87: aload_0
    //   88: aload 5
    //   90: putfield 130	com/vlingo/core/internal/contacts/ContactMatcher:mEmailTypes	[I
    //   93: aload_0
    //   94: aload 7
    //   96: putfield 132	com/vlingo/core/internal/contacts/ContactMatcher:mAddressTypes	[I
    //   99: aload_0
    //   100: aload 6
    //   102: putfield 134	com/vlingo/core/internal/contacts/ContactMatcher:mSocialTypes	[I
    //   105: aload_0
    //   106: aload 10
    //   108: putfield 94	com/vlingo/core/internal/contacts/ContactMatcher:mSuperList	Ljava/util/List;
    //   111: aload 10
    //   113: ifnonnull +118 -> 231
    //   116: iconst_1
    //   117: istore 11
    //   119: aload_0
    //   120: iload 11
    //   122: putfield 136	com/vlingo/core/internal/contacts/ContactMatcher:mCheckAutoAction	Z
    //   125: aload_0
    //   126: aload 8
    //   128: putfield 96	com/vlingo/core/internal/contacts/ContactMatcher:mContactRequest	Ljava/lang/String;
    //   131: aload_0
    //   132: aload_1
    //   133: putfield 100	com/vlingo/core/internal/contacts/ContactMatcher:mContext	Landroid/content/Context;
    //   136: ldc 2
    //   138: monitorenter
    //   139: iconst_1
    //   140: getstatic 82	com/vlingo/core/internal/contacts/ContactMatcher:sInstanceCounter	I
    //   143: iadd
    //   144: putstatic 82	com/vlingo/core/internal/contacts/ContactMatcher:sInstanceCounter	I
    //   147: ldc 2
    //   149: monitorexit
    //   150: aload_0
    //   151: fload 9
    //   153: putfield 138	com/vlingo/core/internal/contacts/ContactMatcher:mRecognizerConfidenceScore	F
    //   156: aload_0
    //   157: new 140	com/vlingo/core/internal/contacts/ContactFetchAndSortRequest
    //   160: dup
    //   161: aload_1
    //   162: aload_0
    //   163: getfield 126	com/vlingo/core/internal/contacts/ContactMatcher:mActionType	Lcom/vlingo/core/internal/contacts/ContactType;
    //   166: aload 8
    //   168: aload_0
    //   169: invokespecial 144	com/vlingo/core/internal/contacts/ContactMatcher:getRequestedTypes	()[I
    //   172: aload_0
    //   173: invokespecial 147	com/vlingo/core/internal/contacts/ContactFetchAndSortRequest:<init>	(Landroid/content/Context;Lcom/vlingo/core/internal/contacts/ContactType;Ljava/lang/String;[ILcom/vlingo/core/internal/contacts/AsyncContactSorterCallback;)V
    //   176: putfield 149	com/vlingo/core/internal/contacts/ContactMatcher:mRequest	Lcom/vlingo/core/internal/contacts/ContactSortRequest;
    //   179: aload_0
    //   180: new 151	java/lang/Thread
    //   183: dup
    //   184: aload_0
    //   185: getfield 149	com/vlingo/core/internal/contacts/ContactMatcher:mRequest	Lcom/vlingo/core/internal/contacts/ContactSortRequest;
    //   188: new 153	java/lang/StringBuilder
    //   191: dup
    //   192: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   195: ldc 156
    //   197: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: getstatic 82	com/vlingo/core/internal/contacts/ContactMatcher:sInstanceCounter	I
    //   203: invokevirtual 163	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   206: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   209: invokespecial 169	java/lang/Thread:<init>	(Ljava/lang/Runnable;Ljava/lang/String;)V
    //   212: putfield 98	com/vlingo/core/internal/contacts/ContactMatcher:mRequestThread	Ljava/lang/Thread;
    //   215: aload_0
    //   216: getfield 98	com/vlingo/core/internal/contacts/ContactMatcher:mRequestThread	Ljava/lang/Thread;
    //   219: iconst_1
    //   220: invokevirtual 173	java/lang/Thread:setDaemon	(Z)V
    //   223: aload_0
    //   224: getfield 98	com/vlingo/core/internal/contacts/ContactMatcher:mRequestThread	Ljava/lang/Thread;
    //   227: invokevirtual 176	java/lang/Thread:start	()V
    //   230: return
    //   231: iconst_0
    //   232: istore 11
    //   234: goto -115 -> 119
    //   237: astore 12
    //   239: ldc 2
    //   241: monitorexit
    //   242: aload 12
    //   244: athrow
    //   245: astore 13
    //   247: getstatic 80	com/vlingo/core/internal/contacts/ContactMatcher:log	Lcom/vlingo/core/internal/logging/Logger;
    //   250: new 153	java/lang/StringBuilder
    //   253: dup
    //   254: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   257: ldc 178
    //   259: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: aload 13
    //   264: invokevirtual 181	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   267: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   270: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   273: invokevirtual 184	com/vlingo/core/internal/logging/Logger:error	(Ljava/lang/String;)V
    //   276: aload_2
    //   277: invokeinterface 189 1 0
    //   282: aload 13
    //   284: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   139	150	237	finally
    //   239	242	237	finally
    //   150	230	245	java/lang/RuntimeException
  }

  private void attemptAutoAction(List<ContactMatch> paramList)
  {
    if ((!this.mCheckAutoAction) || (paramList == null) || (paramList.isEmpty()));
    label83: label215: 
    while (true)
    {
      return;
      ContactMatch localContactMatch;
      int i;
      if ((paramList.size() != 1) || (!isRunning()))
      {
        this.mCheckAutoAction = false;
        localContactMatch = (ContactMatch)paramList.get(0);
        i = 0;
        if (this.mAutoActionType != 2)
          break label83;
        i = 1;
      }
      while (true)
      {
        if (i == 0)
          break label215;
        this.mListener.onAutoAction(localContactMatch);
        break;
        break;
        if ((this.mAutoActionType != 1) || (this.mRecognizerConfidenceScore < 0.1F) || ((paramList.size() != 1) && (localContactMatch.getScore(true) <= 1.0F + ((ContactMatch)paramList.get(1)).getScore(true))))
          continue;
        switch (localContactMatch.getAllData().size())
        {
        case 0:
        default:
          if (((ContactData)localContactMatch.getAllData().get(0)).getScore() - ((ContactData)localContactMatch.getAllData().get(1)).getScore() <= 0.05F)
            continue;
          i = 1;
          break;
        case 1:
          i = 1;
        }
      }
    }
  }

  private List<ContactMatch> attemptFindContact(List<ContactMatch> paramList)
  {
    Iterator localIterator = paramList.iterator();
    Object localObject;
    while (localIterator.hasNext())
    {
      ContactMatch localContactMatch = (ContactMatch)localIterator.next();
      String str = localContactMatch.primaryDisplayName;
      ArrayList localArrayList1 = new ArrayList();
      String[] arrayOfString1 = str.split(" ");
      int i = arrayOfString1.length;
      for (int j = 0; j < i; j++)
        localArrayList1.add(arrayOfString1[j].toLowerCase());
      ArrayList localArrayList2 = new ArrayList();
      String[] arrayOfString2 = this.mContactRequest.split(" ");
      int k = arrayOfString2.length;
      for (int m = 0; m < k; m++)
        localArrayList2.add(arrayOfString2[m].toLowerCase());
      if (!localArrayList1.equals(localArrayList2))
        continue;
      localObject = new ArrayList();
      ((List)localObject).add(localContactMatch);
    }
    while (true)
    {
      return localObject;
      localObject = paramList;
    }
  }

  private int[] getRequestedTypes()
  {
    int[] arrayOfInt = null;
    switch (3.$SwitchMap$com$vlingo$core$internal$contacts$ContactType[this.mActionType.ordinal()])
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
      return arrayOfInt;
      arrayOfInt = this.mPhoneTypes;
      continue;
      arrayOfInt = this.mEmailTypes;
      continue;
      arrayOfInt = this.mAddressTypes;
      continue;
      arrayOfInt = this.mSocialTypes;
    }
  }

  private List<ContactMatch> getSortedSublist(List<ContactMatch> paramList)
  {
    if ((this.mSuperList == null) || (this.mSuperList.size() == 0));
    while (true)
    {
      return paramList;
      Object localObject = new ArrayList();
      Iterator localIterator1 = paramList.iterator();
      while (localIterator1.hasNext())
      {
        ContactMatch localContactMatch = (ContactMatch)localIterator1.next();
        Iterator localIterator2 = this.mSuperList.iterator();
        while (localIterator2.hasNext())
        {
          if (((ContactMatch)localIterator2.next()).primaryContactID != localContactMatch.primaryContactID)
            continue;
          ((List)localObject).add(localContactMatch);
        }
      }
      if (((List)localObject).size() == 0)
        continue;
      if ((((List)localObject).size() == paramList.size()) && (((List)localObject).size() == this.mSuperList.size()) && (((List)localObject).size() > 1))
        localObject = attemptFindContact((List)localObject);
      paramList = (List<ContactMatch>)localObject;
    }
  }

  private boolean isRunning()
  {
    int i = 0;
    monitorenter;
    try
    {
      if ((this.mRequest != null) && (this.mRequest.hasStarted()))
      {
        boolean bool = this.mRequest.isDone();
        if (!bool)
          i = 1;
      }
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean determinePerformAction(List<ContactMatch> paramList)
  {
    ContactMatch localContactMatch = (ContactMatch)paramList.get(0);
    int i = 0;
    if (this.mAutoActionType == 2)
      i = 1;
    while (true)
    {
      return i;
      if ((this.mAutoActionType != 1) || (this.mRecognizerConfidenceScore < 0.1F) || ((paramList.size() != 1) && (localContactMatch.getScore(true) <= 1.0F + ((ContactMatch)paramList.get(1)).getScore(true))))
        continue;
      switch (paramList.size())
      {
      default:
        i = 0;
        break;
      case 1:
        i = 1;
      }
    }
  }

  ContactMatch getBestContact()
  {
    monitorenter;
    try
    {
      if (this.mRequest != null)
      {
        ContactMatch localContactMatch2 = this.mRequest.getBestContact();
        localContactMatch1 = localContactMatch2;
        return localContactMatch1;
      }
      ContactMatch localContactMatch1 = null;
    }
    finally
    {
      monitorexit;
    }
  }

  public int getNumContacts()
  {
    List localList = getSortedContacts();
    if (localList != null);
    for (int i = localList.size(); ; i = 0)
      return i;
  }

  public List<ContactMatch> getSortedContacts()
  {
    monitorenter;
    try
    {
      if (this.mRequest != null)
      {
        List localList2 = this.mRequest.getSortedContacts();
        localList1 = localList2;
        return localList1;
      }
      List localList1 = null;
    }
    finally
    {
      monitorexit;
    }
  }

  public boolean isAddressBookEmpty()
  {
    return this.isAddressBookEmpty;
  }

  public void onAsyncSortingFailed()
  {
    this.mContext = null;
    this.mListener.onContactMatchingFailed();
  }

  public void onAsyncSortingFinished(List<ContactMatch> paramList)
  {
    if (paramList.isEmpty())
      this.isAddressBookEmpty = ContactDBUtil.isAddressBookEmpty(this.mContext);
    this.mContext = null;
    this.mHandler.post(new Runnable(paramList)
    {
      public void run()
      {
        ContactMatcher.this.attemptAutoAction(this.val$sortedContacts);
        ContactMatcher.this.mListener.onContactMatchingFinished(ContactMatcher.this.getSortedSublist(this.val$sortedContacts));
      }
    });
  }

  public void onAsyncSortingUpdated(List<ContactMatch> paramList)
  {
    this.mHandler.post(new Runnable(paramList)
    {
      public void run()
      {
        ContactMatcher.this.mListener.onContactMatchResultsUpdated(this.val$sortedContacts);
      }
    });
  }

  ContactMatch scoreContacts(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      ContactMatch localContactMatch = this.mRequest.scoreContacts(paramBoolean);
      monitorexit;
      return localContactMatch;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setAutoActionType(int paramInt)
  {
    this.mAutoActionType = paramInt;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.contacts.ContactMatcher
 * JD-Core Version:    0.6.0
 */