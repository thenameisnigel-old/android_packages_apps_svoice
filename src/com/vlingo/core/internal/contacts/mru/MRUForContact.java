package com.vlingo.core.internal.contacts.mru;

import java.util.Enumeration;
import java.util.Hashtable;

public class MRUForContact
{
  Hashtable<String, Object> m_addressTable = new Hashtable(10);
  private float m_maxCount = 0.0F;

  public float getCount(String paramString)
  {
    Float localFloat = (Float)this.m_addressTable.get(paramString);
    float f;
    if (localFloat == null)
      f = 0.0F;
    while (true)
    {
      return f;
      f = localFloat.floatValue();
    }
  }

  public float getMaxCount()
  {
    return this.m_maxCount;
  }

  public float getNumItems()
  {
    return this.m_addressTable.size();
  }

  public float getSum()
  {
    float f = 0.0F;
    Enumeration localEnumeration = this.m_addressTable.elements();
    while (localEnumeration.hasMoreElements())
    {
      Float localFloat = (Float)localEnumeration.nextElement();
      if (localFloat == null)
        continue;
      f += localFloat.floatValue();
    }
    return f;
  }

  public float incrementCount(String paramString)
  {
    Float localFloat = (Float)this.m_addressTable.get(paramString);
    float f = 1.0F;
    if (localFloat == null)
      this.m_addressTable.put(paramString, new Float(f));
    while (true)
    {
      if (f > this.m_maxCount)
        this.m_maxCount = f;
      return f;
      f += localFloat.floatValue();
      this.m_addressTable.put(paramString, new Float(f));
    }
  }

  public void scaleValues(float paramFloat)
  {
    Enumeration localEnumeration = this.m_addressTable.keys();
    while (localEnumeration.hasMoreElements())
    {
      String str = (String)localEnumeration.nextElement();
      Float localFloat = (Float)this.m_addressTable.get(str);
      if (localFloat == null)
        continue;
      this.m_addressTable.put(str, new Float(paramFloat * localFloat.floatValue()));
    }
    this.m_maxCount = (paramFloat * this.m_maxCount);
  }

  public void setCount(String paramString, float paramFloat)
  {
    this.m_addressTable.put(paramString, new Float(paramFloat));
    if (paramFloat > this.m_maxCount)
      this.m_maxCount = paramFloat;
  }

  public String toString()
  {
    String str1 = "";
    Enumeration localEnumeration = this.m_addressTable.keys();
    while (localEnumeration.hasMoreElements())
    {
      String str2 = (String)localEnumeration.nextElement();
      Float localFloat = (Float)this.m_addressTable.get(str2);
      str1 = str1 + str2 + " " + localFloat.toString() + " ";
    }
    return str1;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.contacts.mru.MRUForContact
 * JD-Core Version:    0.6.0
 */