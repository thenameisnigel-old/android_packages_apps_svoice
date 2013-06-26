package com.vlingo.core.internal.contacts;

import android.content.res.Resources;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredPostal;
import com.vlingo.core.internal.contacts.mru.MRU;
import com.vlingo.core.internal.util.StringUtils;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ContactData
  implements Comparable<ContactData>, Serializable, Cloneable
{
  private static final long serialVersionUID = 3940560288604424232L;
  public final String address;
  public final ContactMatch contact;
  public final int isDefault;
  public final Kind kind;
  public final String label;
  private float scoreBoost;
  private float scoreMRU;
  public final int type;

  public ContactData(ContactMatch paramContactMatch, Kind paramKind, String paramString, int paramInt1, int paramInt2)
  {
    this.contact = paramContactMatch;
    this.kind = paramKind;
    if (paramString == null)
      paramString = "";
    this.address = paramString;
    this.type = paramInt1;
    this.isDefault = paramInt2;
    this.label = "";
    this.scoreMRU = 0.0F;
    this.scoreBoost = 0.0F;
  }

  public ContactData(ContactMatch paramContactMatch, Kind paramKind, String paramString1, String paramString2, int paramInt)
  {
    this.contact = paramContactMatch;
    this.kind = paramKind;
    if (paramString1 == null)
      paramString1 = "";
    this.address = paramString1;
    this.type = 0;
    this.isDefault = paramInt;
    if (paramString2 == null)
      paramString2 = "";
    this.label = paramString2;
    this.scoreMRU = 0.0F;
    this.scoreBoost = 0.0F;
  }

  public static HashSet<ContactData> clone(HashSet<ContactData> paramHashSet)
  {
    HashSet localHashSet;
    try
    {
      localHashSet = (HashSet)paramHashSet.getClass().newInstance();
      Iterator localIterator = paramHashSet.iterator();
      while (localIterator.hasNext())
        localHashSet.add(((ContactData)localIterator.next()).clone());
    }
    catch (Exception localException)
    {
      throw new RuntimeException("List cloning unsupported", localException);
    }
    return localHashSet;
  }

  public static List<ContactData> clone(List<ContactData> paramList)
  {
    List localList;
    try
    {
      localList = (List)paramList.getClass().newInstance();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
        localList.add(((ContactData)localIterator.next()).clone());
    }
    catch (Exception localException)
    {
      throw new RuntimeException("List cloning unsupported", localException);
    }
    return localList;
  }

  public static Map<String, ContactData> clone(Map<String, ContactData> paramMap)
  {
    Map localMap;
    try
    {
      localMap = (Map)paramMap.getClass().newInstance();
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localMap.put(localEntry.getKey(), localEntry.getValue());
      }
    }
    catch (Exception localException)
    {
      throw new RuntimeException("List cloning unsupported", localException);
    }
    return localMap;
  }

  public ContactData clone()
  {
    try
    {
      localContactData = (ContactData)super.clone();
      return localContactData;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      while (true)
      {
        localCloneNotSupportedException.printStackTrace();
        ContactData localContactData = null;
      }
    }
  }

  public int compareTo(ContactData paramContactData)
  {
    int i;
    if (getScore() > paramContactData.getScore())
      i = 1;
    while (true)
    {
      return i;
      if (getScore() < paramContactData.getScore())
      {
        i = -1;
        continue;
      }
      i = 0;
    }
  }

  void computeMRUScore(MRU paramMRU, ContactType paramContactType, int paramInt)
  {
    this.scoreMRU = paramMRU.getNormalizedCount(paramContactType, paramInt, this.address);
  }

  boolean computeScore(int paramInt, int[] paramArrayOfInt)
  {
    int i = 1;
    float f = 0.0F;
    if (this.type == paramInt)
      f = 0.0F + 0.9F;
    int i2;
    if ((this.kind == Kind.Email) && (paramArrayOfInt != null))
    {
      int i1 = paramArrayOfInt.length;
      i2 = 0;
      if (i2 < i1)
      {
        if (ContactDBUtil.convertEmailType(paramArrayOfInt[i2]) != this.type)
          break label191;
        f += 0.99F;
      }
    }
    int n;
    if ((this.kind == Kind.Address) && (paramArrayOfInt != null))
    {
      int m = paramArrayOfInt.length;
      n = 0;
      label89: if (n < m)
      {
        if (paramArrayOfInt[n] != this.type)
          break label197;
        f += 0.99F;
      }
    }
    int k;
    if (this.kind == Kind.Phone)
    {
      if (paramArrayOfInt != null)
      {
        int j = paramArrayOfInt.length;
        k = 0;
        label135: if (k < j)
        {
          if (paramArrayOfInt[k] != this.type)
            break label203;
          f += 0.99F;
        }
      }
      if (this.type != 2)
        break label209;
      f += 0.006F;
    }
    else
    {
      label175: this.scoreBoost = f;
      if (f < 0.99F)
        break label255;
    }
    while (true)
    {
      return i;
      label191: i2++;
      break;
      label197: n++;
      break label89;
      label203: k++;
      break label135;
      label209: if (this.type == 3)
      {
        f += 0.005F;
        break label175;
      }
      if (this.type == i)
      {
        f += 0.004F;
        break label175;
      }
      f += 0.003F;
      break label175;
      label255: i = 0;
    }
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (this == paramObject)
      bool = true;
    while (true)
    {
      return bool;
      if (!(paramObject instanceof ContactData))
        continue;
      ContactData localContactData = (ContactData)paramObject;
      if ((localContactData.type != this.type) || (localContactData.kind != this.kind) || (!this.label.equals(localContactData.label)))
        continue;
      bool = getNormalizedAddress().equals(localContactData.getNormalizedAddress());
    }
  }

  public String getAcceptedTextTagStringForMessaging()
  {
    return this.kind.getAcceptedText();
  }

  public String getFormattedType(Resources paramResources)
  {
    String str;
    switch (1.$SwitchMap$com$vlingo$core$internal$contacts$ContactData$Kind[this.kind.ordinal()])
    {
    default:
      str = "";
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return str;
      str = ContactsContract.CommonDataKinds.Phone.getTypeLabel(paramResources, this.type, "").toString();
      continue;
      str = ContactsContract.CommonDataKinds.StructuredPostal.getTypeLabel(paramResources, this.type, "").toString();
      continue;
      str = ContactsContract.CommonDataKinds.Phone.getTypeLabel(paramResources, this.type, "").toString();
    }
  }

  public float getMRUScore()
  {
    return this.scoreMRU;
  }

  public String getNormalizedAddress()
  {
    String str1 = this.address;
    if (isPhoneNumber())
    {
      str2 = StringUtils.cleanPhoneNumber(str1);
      if (str2.startsWith("+"))
        str2 = str2.substring(1);
      if (!str2.startsWith("1"));
    }
    for (String str2 = str2.substring(1); ; str2 = str1.trim().toLowerCase())
      return str2;
  }

  public float getScore()
  {
    return this.scoreMRU + this.scoreBoost;
  }

  public int getType()
  {
    return this.type;
  }

  public int hashCode()
  {
    return this.kind.hashCode() ^ getNormalizedAddress().hashCode() ^ 547 * this.type ^ this.label.hashCode();
  }

  public boolean isAddress()
  {
    if (this.kind == Kind.Address);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isBirthday()
  {
    if (this.kind == Kind.Birthday);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isEmail()
  {
    if (this.kind == Kind.Email);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isFacebook()
  {
    if (this.kind == Kind.Facebook);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isPhoneNumber()
  {
    if (this.kind == Kind.Phone);
    for (int i = 1; ; i = 0)
      return i;
  }

  void setBoostScore(float paramFloat)
  {
    this.scoreBoost = paramFloat;
  }

  void setScore(float paramFloat)
  {
    this.scoreBoost = paramFloat;
    this.scoreMRU = paramFloat;
  }

  public String toString()
  {
    return "[ContactData] type=" + this.kind.name() + " address=" + this.address + " type=" + this.type + " score=" + getScore();
  }

  public static enum Kind
  {
    private final String acceptedText;

    static
    {
      Email = new Kind("Email", 1, "email:def");
      Address = new Kind("Address", 2, "address:def");
      Birthday = new Kind("Birthday", 3, "birthday:def");
      Facebook = new Kind("Facebook", 4, "facebook:msg");
      Unspecified = new Kind("Unspecified", 5, null);
      Kind[] arrayOfKind = new Kind[6];
      arrayOfKind[0] = Phone;
      arrayOfKind[1] = Email;
      arrayOfKind[2] = Address;
      arrayOfKind[3] = Birthday;
      arrayOfKind[4] = Facebook;
      arrayOfKind[5] = Unspecified;
      $VALUES = arrayOfKind;
    }

    private Kind(String paramString)
    {
      this.acceptedText = paramString;
    }

    public String getAcceptedText()
    {
      return this.acceptedText;
    }
  }

  public static class ReverseComparator
    implements Comparator<ContactData>
  {
    public int compare(ContactData paramContactData1, ContactData paramContactData2)
    {
      return paramContactData2.compareTo(paramContactData1);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.contacts.ContactData
 * JD-Core Version:    0.6.0
 */