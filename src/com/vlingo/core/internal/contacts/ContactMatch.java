package com.vlingo.core.internal.contacts;

import com.vlingo.core.internal.contacts.mru.MRU;
import com.vlingo.core.internal.util.StringUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.List<Lcom.vlingo.core.internal.contacts.ContactData;>;

public class ContactMatch
  implements Comparable<ContactMatch>, Serializable, Cloneable
{
  private static final long serialVersionUID = 6343331258433862439L;
  private List<ContactData> addressData;
  private List<ContactData> allData;
  private boolean allDataSorted = false;
  private float bestDetailScore = -1.0F;
  private List<ContactData> birthdayData;
  private HashSet<ContactData> dataSet = new HashSet();
  private boolean detailsAreSorted = false;
  private List<ContactData> emailData;
  public volatile String extraNameUsed;
  private List<String> extraNames;
  public final String lookupKey;
  private List<ContactData> phoneData;
  public final long primaryContactID;
  public final String primaryDisplayName;
  float score = 0.0F;
  float scoreBestMRU = 0.0F;
  private List<ContactData> socialData;
  public final boolean starred;
  private int timesContacted = 0;
  private boolean typeMatches = false;

  public ContactMatch(String paramString1, long paramLong, String paramString2, boolean paramBoolean)
  {
    this.primaryDisplayName = paramString1;
    this.primaryContactID = paramLong;
    this.lookupKey = paramString2;
    this.starred = paramBoolean;
  }

  public static List<ContactMatch> clone(List<ContactMatch> paramList)
  {
    List localList;
    try
    {
      localList = (List)paramList.getClass().newInstance();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
        localList.add(((ContactMatch)localIterator.next()).clone());
    }
    catch (Exception localException)
    {
      throw new RuntimeException("List cloning unsupported", localException);
    }
    return localList;
  }

  private float getBestDetailScore()
  {
    List localList;
    if (this.bestDetailScore == -1.0F)
    {
      localList = getAllData(false);
      if (!localList.isEmpty())
        break label35;
    }
    label35: for (this.bestDetailScore = 0.0F; ; this.bestDetailScore = ((ContactData)localList.get(0)).getScore())
      return this.bestDetailScore;
  }

  void addAddress(ContactData paramContactData)
  {
    if (!this.dataSet.contains(paramContactData))
    {
      if (this.addressData == null)
        this.addressData = new ArrayList();
      this.addressData.add(paramContactData);
      this.dataSet.add(paramContactData);
      this.allData = null;
    }
  }

  void addBirthday(ContactData paramContactData)
  {
    if (!this.dataSet.contains(paramContactData))
    {
      if (this.birthdayData == null)
        this.birthdayData = new ArrayList();
      this.birthdayData.add(paramContactData);
      this.dataSet.add(paramContactData);
      this.allData = null;
    }
  }

  void addEmail(ContactData paramContactData)
  {
    if (!this.dataSet.contains(paramContactData))
    {
      if (this.emailData == null)
        this.emailData = new ArrayList();
      this.emailData.add(paramContactData);
      this.dataSet.add(paramContactData);
      this.allData = null;
    }
  }

  void addExtraName(String paramString)
  {
    if (StringUtils.isEqual(this.primaryDisplayName, paramString));
    while (true)
    {
      return;
      if (!StringUtils.isNullOrWhiteSpace(paramString))
      {
        if (this.extraNames == null)
        {
          this.extraNames = new ArrayList();
          this.extraNames.add(paramString);
          continue;
        }
        int i = 0;
        Iterator localIterator = this.extraNames.iterator();
        while (localIterator.hasNext())
        {
          if (!StringUtils.isEqual(paramString, (String)localIterator.next()))
            continue;
          i = 1;
        }
        if (i != 0)
          continue;
        this.extraNames.add(paramString);
        continue;
      }
    }
  }

  void addPhone(ContactData paramContactData)
  {
    if (!this.dataSet.contains(paramContactData))
    {
      if (this.phoneData == null)
        this.phoneData = new ArrayList();
      this.dataSet.add(paramContactData);
      this.phoneData.add(paramContactData);
      this.allData = null;
    }
  }

  void addSocial(ContactData paramContactData)
  {
    if (!this.dataSet.contains(paramContactData))
    {
      if (this.socialData == null)
        this.socialData = new ArrayList();
      this.dataSet.add(paramContactData);
      this.socialData.add(paramContactData);
      this.allData = null;
    }
  }

  public ContactMatch clone()
  {
    ContactMatch localContactMatch;
    try
    {
      localContactMatch = (ContactMatch)super.clone();
      HashSet localHashSet;
      List localList1;
      label32: List localList2;
      label48: List localList3;
      label64: List localList4;
      label80: List localList5;
      label96: List localList6;
      if (this.dataSet == null)
      {
        localHashSet = null;
        localContactMatch.dataSet = localHashSet;
        if (this.emailData != null)
          break label156;
        localList1 = null;
        localContactMatch.emailData = localList1;
        if (this.phoneData != null)
          break label168;
        localList2 = null;
        localContactMatch.phoneData = localList2;
        if (this.addressData != null)
          break label180;
        localList3 = null;
        localContactMatch.addressData = localList3;
        if (this.birthdayData != null)
          break label192;
        localList4 = null;
        localContactMatch.birthdayData = localList4;
        if (this.socialData != null)
          break label204;
        localList5 = null;
        localContactMatch.socialData = localList5;
        if (this.allData != null)
          break label216;
        localList6 = null;
        label112: localContactMatch.allData = localList6;
        if (this.extraNames != null)
          break label228;
      }
      label156: label168: label180: label192: label204: label216: label228: for (ArrayList localArrayList = null; ; localArrayList = new ArrayList(this.extraNames))
      {
        localContactMatch.extraNames = localArrayList;
        localContactMatch.timesContacted = this.timesContacted;
        break label251;
        localHashSet = ContactData.clone(this.dataSet);
        break;
        localList1 = ContactData.clone(this.emailData);
        break label32;
        localList2 = ContactData.clone(this.phoneData);
        break label48;
        localList3 = ContactData.clone(this.addressData);
        break label64;
        localList4 = ContactData.clone(this.birthdayData);
        break label80;
        localList5 = ContactData.clone(this.socialData);
        break label96;
        localList6 = ContactData.clone(this.allData);
        break label112;
      }
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      localCloneNotSupportedException.printStackTrace();
      localContactMatch = null;
    }
    label251: return localContactMatch;
  }

  public int compareTo(ContactMatch paramContactMatch)
  {
    boolean bool = true;
    if (getScore(bool) > paramContactMatch.getScore(bool));
    while (true)
    {
      return bool;
      if (getScore(bool) < paramContactMatch.getScore(bool))
      {
        i = -1;
        continue;
      }
      if (getBestDetailScore() > paramContactMatch.getBestDetailScore())
        continue;
      if (getBestDetailScore() < paramContactMatch.getBestDetailScore())
      {
        i = -1;
        continue;
      }
      if ((this.starred) && (!paramContactMatch.starred))
        continue;
      if ((!this.starred) && (paramContactMatch.starred))
      {
        i = -1;
        continue;
      }
      if (getTimesContacted() > paramContactMatch.getTimesContacted())
        continue;
      if (getTimesContacted() < paramContactMatch.getTimesContacted())
      {
        i = -1;
        continue;
      }
      if ((this.primaryDisplayName != null) && (paramContactMatch.primaryDisplayName == null))
        continue;
      if ((this.primaryDisplayName == null) && (paramContactMatch.primaryDisplayName != null))
      {
        i = -1;
        continue;
      }
      if ((this.primaryDisplayName == null) && (paramContactMatch.primaryDisplayName == null))
      {
        i = 0;
        continue;
      }
      int i = -this.primaryDisplayName.compareTo(paramContactMatch.primaryDisplayName);
    }
  }

  void computeMRUScore(MRU paramMRU, ContactType paramContactType)
  {
    this.scoreBestMRU = paramMRU.getNormalizedCount(paramContactType, (int)this.primaryContactID);
  }

  void computeMRUScoresForData(MRU paramMRU, ContactType paramContactType)
  {
    Iterator localIterator = getAllData().iterator();
    while (localIterator.hasNext())
      ((ContactData)localIterator.next()).computeMRUScore(paramMRU, paramContactType, (int)this.primaryContactID);
  }

  void computeScores(int paramInt, int[] paramArrayOfInt)
  {
    if (this.phoneData != null)
    {
      Iterator localIterator4 = this.phoneData.iterator();
      while (localIterator4.hasNext())
      {
        if (!((ContactData)localIterator4.next()).computeScore(paramInt, paramArrayOfInt))
          continue;
        this.typeMatches = true;
      }
    }
    if (this.emailData != null)
    {
      Iterator localIterator3 = this.emailData.iterator();
      while (localIterator3.hasNext())
      {
        if (!((ContactData)localIterator3.next()).computeScore(paramInt, paramArrayOfInt))
          continue;
        this.typeMatches = true;
      }
    }
    if (this.addressData != null)
    {
      Iterator localIterator2 = this.addressData.iterator();
      while (localIterator2.hasNext())
      {
        if (!((ContactData)localIterator2.next()).computeScore(paramInt, paramArrayOfInt))
          continue;
        this.typeMatches = true;
      }
    }
    if (this.socialData != null)
    {
      Iterator localIterator1 = this.socialData.iterator();
      while (localIterator1.hasNext())
      {
        if (!((ContactData)localIterator1.next()).computeScore(paramInt, paramArrayOfInt))
          continue;
        this.typeMatches = true;
      }
    }
  }

  boolean containsData()
  {
    if (this.dataSet.size() > 0);
    for (int i = 1; ; i = 0)
      return i;
  }

  public List<ContactData> getAddressData()
  {
    return this.addressData;
  }

  public List<ContactData> getAllData()
  {
    return getAllData(true);
  }

  public List<ContactData> getAllData(boolean paramBoolean)
  {
    Object localObject = this.allData;
    if (localObject == null)
    {
      localObject = new ArrayList();
      if (this.emailData != null)
        ((List)localObject).addAll(this.emailData);
      if (this.phoneData != null)
        ((List)localObject).addAll(this.phoneData);
      if (this.addressData != null)
        ((List)localObject).addAll(this.addressData);
      if (this.birthdayData != null)
        ((List)localObject).addAll(this.birthdayData);
      if (this.socialData != null)
        ((List)localObject).addAll(this.socialData);
      if (paramBoolean)
        this.allData = ((List)localObject);
    }
    if ((this.detailsAreSorted) && (!this.allDataSorted))
    {
      Collections.sort((List)localObject, new ContactData.ReverseComparator());
      if (this.allData != null)
        this.allDataSorted = true;
    }
    return (List<ContactData>)localObject;
  }

  public List<ContactData> getBirthdayData()
  {
    return this.birthdayData;
  }

  public List<ContactData> getData(ContactType paramContactType)
  {
    List localList;
    switch (1.$SwitchMap$com$vlingo$core$internal$contacts$ContactType[paramContactType.ordinal()])
    {
    default:
      localList = getAllData();
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    }
    while (true)
    {
      return localList;
      localList = getPhoneData();
      continue;
      localList = getEmailData();
      continue;
      localList = getAddressData();
      continue;
      localList = getBirthdayData();
      continue;
      localList = getAddressData();
      continue;
      localList = getSocialData();
    }
  }

  public List<ContactData> getEmailData()
  {
    return this.emailData;
  }

  public List<String> getExtraNames()
  {
    return this.extraNames;
  }

  public String getLookupKey()
  {
    return this.lookupKey;
  }

  public List<ContactData> getPhoneData()
  {
    return this.phoneData;
  }

  public float getScore(boolean paramBoolean)
  {
    float f;
    if (paramBoolean)
      f = this.score + this.scoreBestMRU;
    while (true)
    {
      return f;
      f = this.score;
    }
  }

  public List<ContactData> getSocialData()
  {
    return this.socialData;
  }

  public int getTimesContacted()
  {
    return this.timesContacted;
  }

  public boolean hasExtraNames()
  {
    if (this.extraNames != null);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean hasMatchedType()
  {
    return this.typeMatches;
  }

  public void setTimesContacted(int paramInt)
  {
    this.timesContacted = paramInt;
  }

  void sortDetails()
  {
    if (!this.detailsAreSorted)
    {
      ContactData.ReverseComparator localReverseComparator = new ContactData.ReverseComparator();
      if (this.phoneData != null)
        Collections.sort(this.phoneData, localReverseComparator);
      if (this.emailData != null)
        Collections.sort(this.emailData, localReverseComparator);
      if (this.addressData != null)
        Collections.sort(this.addressData, localReverseComparator);
      if (this.birthdayData != null)
        Collections.sort(this.birthdayData, localReverseComparator);
      if (this.socialData != null)
        Collections.sort(this.socialData, localReverseComparator);
      if (this.allData != null)
        Collections.sort(this.allData, localReverseComparator);
      this.detailsAreSorted = true;
    }
  }

  String toDescriptiveString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(toString());
    Iterator localIterator = getAllData(false).iterator();
    while (localIterator.hasNext())
    {
      ContactData localContactData = (ContactData)localIterator.next();
      localStringBuffer.append("  " + localContactData.toString());
    }
    return localStringBuffer.toString();
  }

  public String toString()
  {
    return "[ContactMatch] name=" + this.primaryDisplayName + " contactID=" + this.primaryContactID + " starred=" + this.starred + " lookupKey=" + this.lookupKey + " score=" + getScore(true) + " timesContacted=" + this.timesContacted;
  }

  static class ReverseComparator
    implements Comparator<ContactMatch>
  {
    public int compare(ContactMatch paramContactMatch1, ContactMatch paramContactMatch2)
    {
      return paramContactMatch2.compareTo(paramContactMatch1);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.contacts.ContactMatch
 * JD-Core Version:    0.6.0
 */