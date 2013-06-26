package com.vlingo.core.internal.util;

import java.util.Enumeration;

public abstract interface ToIntHashtable
{
  public abstract void clear();

  public abstract boolean contains(int paramInt);

  public abstract boolean containsKey(Object paramObject);

  public abstract int get(Object paramObject);

  public abstract boolean isEmpty();

  public abstract Enumeration<?> keys();

  public abstract int put(Object paramObject, int paramInt);

  public abstract int remove(Object paramObject);

  public abstract int size();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.ToIntHashtable
 * JD-Core Version:    0.6.0
 */