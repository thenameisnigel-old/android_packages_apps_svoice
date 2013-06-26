package com.vlingo.core.internal.contacts.mru;

import java.util.Enumeration;
import java.util.Hashtable;

public class MRUTable
{
  private int m_countSinceLastNormalization;
  private String m_name;
  private Hashtable<Object, Object> m_table;
  private float m_tableSum;

  public MRUTable(String paramString, int paramInt)
  {
    this.m_table = new Hashtable(paramInt * 2);
    this.m_tableSum = 0.0F;
    this.m_countSinceLastNormalization = 0;
  }

  public int getCountSinceLastUdate()
  {
    return this.m_countSinceLastNormalization;
  }

  public float getNormalizedCount(int paramInt)
  {
    float f = 0.0F;
    Integer localInteger = new Integer(paramInt);
    MRUForContact localMRUForContact = (MRUForContact)this.m_table.get(localInteger);
    if (localMRUForContact == null);
    while (true)
    {
      return f;
      if (this.m_tableSum == 0.0F)
        continue;
      f = localMRUForContact.getMaxCount() / this.m_tableSum;
    }
  }

  public float getNormalizedCount(int paramInt, String paramString)
  {
    float f = 0.0F;
    Integer localInteger = new Integer(paramInt);
    MRUForContact localMRUForContact = (MRUForContact)this.m_table.get(localInteger);
    if (localMRUForContact == null);
    while (true)
    {
      return f;
      if (this.m_tableSum == 0.0F)
        continue;
      f = localMRUForContact.getCount(paramString) / this.m_tableSum;
    }
  }

  public int getNumItems()
  {
    return this.m_table.size();
  }

  public float incrementCount(int paramInt, String paramString)
  {
    Integer localInteger = new Integer(paramInt);
    MRUForContact localMRUForContact = (MRUForContact)this.m_table.get(localInteger);
    if (localMRUForContact == null)
    {
      localMRUForContact = new MRUForContact();
      this.m_table.put(localInteger, localMRUForContact);
    }
    this.m_tableSum = (1.0F + this.m_tableSum);
    this.m_countSinceLastNormalization = (1 + this.m_countSinceLastNormalization);
    return localMRUForContact.incrementCount(paramString);
  }

  public Integer removeLowestScoringContact()
  {
    Object localObject = null;
    float f1 = this.m_tableSum;
    Enumeration localEnumeration = this.m_table.keys();
    while (localEnumeration.hasMoreElements())
    {
      Integer localInteger = (Integer)localEnumeration.nextElement();
      MRUForContact localMRUForContact2 = (MRUForContact)this.m_table.get(localInteger);
      if (localMRUForContact2 == null)
        continue;
      float f2 = localMRUForContact2.getMaxCount();
      if ((localObject != null) && (f2 >= f1))
        continue;
      f1 = f2;
      localObject = localInteger;
    }
    if (localObject != null)
    {
      MRUForContact localMRUForContact1 = (MRUForContact)this.m_table.get(localObject);
      if (localMRUForContact1 != null)
        this.m_tableSum -= localMRUForContact1.getSum();
      this.m_table.remove(localObject);
    }
    return localObject;
  }

  public void scaleValues(float paramFloat)
  {
    this.m_countSinceLastNormalization = 0;
    this.m_tableSum = (paramFloat * this.m_tableSum);
    Enumeration localEnumeration = this.m_table.elements();
    while (localEnumeration.hasMoreElements())
      ((MRUForContact)localEnumeration.nextElement()).scaleValues(paramFloat);
  }

  public void setCount(int paramInt, String paramString, float paramFloat)
  {
    Integer localInteger = new Integer(paramInt);
    MRUForContact localMRUForContact = (MRUForContact)this.m_table.get(localInteger);
    if (localMRUForContact == null)
    {
      localMRUForContact = new MRUForContact();
      this.m_table.put(localInteger, localMRUForContact);
    }
    localMRUForContact.setCount(paramString, paramFloat);
    this.m_tableSum = (paramFloat + this.m_tableSum);
  }

  public String toString()
  {
    String str = "";
    Enumeration localEnumeration = this.m_table.keys();
    while (localEnumeration.hasMoreElements())
    {
      Integer localInteger = (Integer)localEnumeration.nextElement();
      MRUForContact localMRUForContact = (MRUForContact)this.m_table.get(localInteger);
      if (localMRUForContact == null)
        continue;
      str = str + " Contact " + localInteger + " (" + localMRUForContact.toString() + ")";
    }
    return str;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.contacts.mru.MRUTable
 * JD-Core Version:    0.6.0
 */