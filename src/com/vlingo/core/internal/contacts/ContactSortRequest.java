package com.vlingo.core.internal.contacts;

import android.util.Log;
import com.vlingo.core.internal.contacts.mru.MRU;
import com.vlingo.core.internal.logging.Logger;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class ContactSortRequest
  implements Runnable
{
  private static final Logger log = Logger.getLogger(ContactSortRequest.class);
  private ContactMatch bestContact;
  protected final AsyncContactSorterCallback callback;
  protected volatile boolean doneRunning = false;
  private volatile boolean hasStarted = false;
  private String[] matchString;
  protected List<ContactMatch> matches;
  protected final MRU mru;
  protected final float pruningThreshold;
  protected final String query;
  protected final int[] requestedTypes;
  private final List<ContactMatch> sortedContacts;
  protected final ContactType type;

  protected ContactSortRequest(String paramString, ContactType paramContactType, int[] paramArrayOfInt, AsyncContactSorterCallback paramAsyncContactSorterCallback)
  {
    this.query = paramString;
    this.mru = MRU.getMRU();
    this.type = paramContactType;
    this.requestedTypes = paramArrayOfInt;
    this.pruningThreshold = 10.0F;
    this.sortedContacts = new ArrayList();
    this.callback = paramAsyncContactSorterCallback;
    this.matchString = paramString.toLowerCase().split(" ");
  }

  private String breakAllWords(String paramString)
  {
    String str = new String();
    int i = 0;
    if (i < paramString.length())
    {
      if (StringUtils.chineseUnicodeBlocks.contains(Character.UnicodeBlock.of(paramString.charAt(0))));
      for (str = str + paramString.substring(i, i + 1) + " "; ; str = str + paramString.substring(i, i + 1))
      {
        i++;
        break;
      }
    }
    return str;
  }

  private String breakAtPosition(String paramString, int paramInt)
  {
    String str = new String();
    int i = 0;
    if (i < paramString.length())
    {
      if (i == paramInt);
      for (str = str + paramString.substring(i, i + 1) + " "; ; str = str + paramString.substring(i, i + 1))
      {
        i++;
        break;
      }
    }
    return str;
  }

  private float computeScore(ContactMatch paramContactMatch)
  {
    String str = paramContactMatch.primaryDisplayName;
    float f1 = computeScore(paramContactMatch, str);
    if ((StringUtils.chineseUnicodeBlocks.contains(Character.UnicodeBlock.of(str.charAt(0)))) && (f1 < 20.0F))
    {
      float f2 = computeScore(paramContactMatch, breakAllWords(str));
      if (f2 > f1)
        f1 = f2;
      if (f1 < 20.0F)
      {
        float f3 = computeScore(paramContactMatch, str.replace(" ", ""));
        if (f3 > f1)
          f1 = f3;
        if (f1 < 20.0F)
        {
          float f4 = computeScore(paramContactMatch, breakAtPosition(str, 1));
          if (f4 > f1)
            f1 = f4;
          if (f1 < 20.0F)
          {
            float f5 = computeScore(paramContactMatch, breakAtPosition(str, 0));
            if (f5 > f1)
              f1 = f5;
          }
        }
      }
    }
    return f1;
  }

  private float computeScore(ContactMatch paramContactMatch, String paramString)
  {
    float f1 = computeScore(split(paramString.toLowerCase(), ", &/+."));
    if ((Settings.getBoolean("contacts.use_other_names", true)) && (paramContactMatch.hasExtraNames()))
    {
      Iterator localIterator = paramContactMatch.getExtraNames().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        float f2 = -2.0F + computeScore(split(str.toLowerCase(), ", &/+."));
        if (f1 >= f2)
          continue;
        f1 = f2;
        paramContactMatch.extraNameUsed = str;
      }
    }
    return f1;
  }

  private float computeScore(String[] paramArrayOfString)
  {
    float f = 0.0F;
    boolean[] arrayOfBoolean = new boolean[this.matchString.length];
    for (int i = 0; i < this.matchString.length; i++)
      arrayOfBoolean[i] = false;
    int j = 0;
    if (j < paramArrayOfString.length)
    {
      int m = 0;
      int n = -1;
      String str1 = paramArrayOfString[j];
      if (str1.length() == 0);
      while (true)
      {
        j++;
        break;
        int i1 = 0;
        if (i1 < this.matchString.length)
        {
          int i2;
          int i3;
          String str2;
          if (arrayOfBoolean[i1] == 0)
          {
            i2 = 0;
            if (((i1 == 0) && (j == 0)) || ((i1 != 0) && (j != 0)))
              i2 = 1;
            i3 = 0;
            str2 = this.matchString[i1];
            if (str2.length() != 0)
              break label143;
          }
          label143: label214: 
          while (true)
          {
            i1++;
            break;
            if (str1.equals(str2))
              if (i2 != 0)
                i3 = 20;
            while (true)
            {
              if (i3 <= m)
                break label214;
              m = i3;
              n = i1;
              break;
              i3 = 18;
              continue;
              if (!str1.startsWith(str2))
                continue;
              if (i2 != 0)
              {
                i3 = 10;
                continue;
              }
              i3 = 8;
            }
          }
        }
        if (n >= 0)
        {
          f += m;
          arrayOfBoolean[n] = true;
          continue;
        }
        f -= 1.0F;
      }
    }
    for (int k = 0; k < this.matchString.length; k++)
    {
      if (arrayOfBoolean[k] != 0)
        continue;
      f -= 1.0F;
    }
    return f;
  }

  private void matchExtraSortedContacts()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.sortedContacts.iterator();
    while (localIterator.hasNext())
    {
      ContactMatch localContactMatch = (ContactMatch)localIterator.next();
      if (!localContactMatch.hasMatchedType())
        continue;
      localArrayList.add(localContactMatch);
    }
    if (!localArrayList.isEmpty())
    {
      this.sortedContacts.clear();
      this.sortedContacts.addAll(localArrayList);
    }
  }

  private static String[] split(String paramString1, String paramString2)
  {
    int i = 0;
    int j = paramString1.length();
    int k = 1;
    int m = 0;
    if (m < j)
    {
      if (paramString2.indexOf(paramString1.charAt(m)) >= 0);
      for (k = 1; ; k = 0)
      {
        m++;
        break;
        if (k == 0)
          continue;
        i++;
      }
    }
    String[] arrayOfString = new String[i];
    int n = -1;
    int i1 = 0;
    int i2 = 0;
    int i3;
    if (i1 <= j)
      if ((i1 == j) || (paramString2.indexOf(paramString1.charAt(i1)) >= 0))
      {
        if (n < 0)
          break label160;
        i3 = i2 + 1;
        arrayOfString[i2] = paramString1.substring(n, i1);
      }
    while (true)
    {
      n = -1;
      while (true)
      {
        i1++;
        i2 = i3;
        break;
        if (n < 0)
        {
          n = i1;
          i3 = i2;
          continue;
          return arrayOfString;
        }
        i3 = i2;
      }
      label160: i3 = i2;
    }
  }

  public ContactMatch getBestContact()
  {
    return this.bestContact;
  }

  public String getQuery()
  {
    return this.query;
  }

  public List<ContactMatch> getSortedContacts()
  {
    return this.sortedContacts;
  }

  public boolean hasStarted()
  {
    return this.hasStarted;
  }

  public boolean isDone()
  {
    return this.doneRunning;
  }

  public void run()
  {
    sortContacts();
  }

  public ContactMatch scoreContacts(boolean paramBoolean)
  {
    if ((this.matches != null) && (this.matches.size() > 0))
    {
      int i = 0;
      Iterator localIterator1 = this.matches.iterator();
      while (localIterator1.hasNext())
      {
        ContactMatch localContactMatch2 = (ContactMatch)localIterator1.next();
        localContactMatch2.score = computeScore(localContactMatch2);
        localContactMatch2.computeScores(this.type.getPreferredTarget(), this.requestedTypes);
        if ((paramBoolean) && (localContactMatch2.getPhoneData() == null))
          localContactMatch2.score = (0.1F * localContactMatch2.score);
        if ((this.bestContact != null) && (i != 0) && (localContactMatch2.score < this.bestContact.score))
          continue;
        localContactMatch2.computeMRUScore(this.mru, this.type);
        localContactMatch2.computeMRUScoresForData(this.mru, this.type);
        if ((i != 0) && (localContactMatch2.compareTo(this.bestContact) != 1))
          continue;
        this.bestContact = localContactMatch2;
        this.bestContact.sortDetails();
        i = 1;
      }
      Iterator localIterator2 = this.matches.iterator();
      while (localIterator2.hasNext())
      {
        ContactMatch localContactMatch1 = (ContactMatch)localIterator2.next();
        if (localContactMatch1.score <= this.bestContact.score - this.pruningThreshold)
          continue;
        this.sortedContacts.add(localContactMatch1);
      }
      matchExtraSortedContacts();
    }
    return this.bestContact;
  }

  public void sortContacts()
  {
    this.hasStarted = true;
    try
    {
      Iterator localIterator = this.matches.iterator();
      while (localIterator.hasNext())
      {
        ContactMatch localContactMatch = (ContactMatch)localIterator.next();
        localContactMatch.computeScores(this.type.getPreferredTarget(), this.requestedTypes);
        localContactMatch.computeMRUScore(this.mru, this.type);
        localContactMatch.computeMRUScoresForData(this.mru, this.type);
        localContactMatch.sortDetails();
      }
    }
    catch (RuntimeException localRuntimeException)
    {
      log.debug("Caught RuntimeException" + localRuntimeException.getMessage() + " stack trace: " + Log.getStackTraceString(localRuntimeException));
      this.callback.onAsyncSortingFailed();
    }
    while (true)
    {
      return;
      Collections.sort(this.sortedContacts, new ContactMatch.ReverseComparator());
      this.doneRunning = true;
      if (this.callback == null)
        continue;
      this.callback.onAsyncSortingUpdated(this.sortedContacts);
      this.callback.onAsyncSortingFinished(this.sortedContacts);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.contacts.ContactSortRequest
 * JD-Core Version:    0.6.0
 */