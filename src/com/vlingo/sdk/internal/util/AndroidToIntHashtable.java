package com.vlingo.sdk.internal.util;

import java.util.Enumeration;
import java.util.Hashtable;

public class AndroidToIntHashtable
  implements ToIntHashtable
{
  private final Hashtable<Object, Integer> hash;

  public AndroidToIntHashtable()
  {
    this.hash = new Hashtable();
  }

  public AndroidToIntHashtable(int paramInt)
  {
    this.hash = new Hashtable(paramInt);
  }

  public void clear()
  {
    this.hash.clear();
  }

  public boolean contains(int paramInt)
  {
    return this.hash.contains(new Integer(paramInt));
  }

  public boolean containsKey(Object paramObject)
  {
    return this.hash.containsKey(paramObject);
  }

  public int get(Object paramObject)
  {
    Integer localInteger = (Integer)this.hash.get(paramObject);
    if (localInteger != null);
    for (int i = localInteger.intValue(); ; i = -1)
      return i;
  }

  public boolean isEmpty()
  {
    return this.hash.isEmpty();
  }

  public Enumeration<?> keys()
  {
    return this.hash.keys();
  }

  public int put(Object paramObject, int paramInt)
  {
    Integer localInteger = (Integer)this.hash.put(paramObject, new Integer(paramInt));
    if (localInteger != null);
    for (int i = localInteger.intValue(); ; i = -1)
      return i;
  }

  public int remove(Object paramObject)
  {
    Integer localInteger = (Integer)this.hash.remove(paramObject);
    if (localInteger != null);
    for (int i = localInteger.intValue(); ; i = -1)
      return i;
  }

  public int size()
  {
    return this.hash.size();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.util.AndroidToIntHashtable
 * JD-Core Version:    0.6.0
 */