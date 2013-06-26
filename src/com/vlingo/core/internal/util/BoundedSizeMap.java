package com.vlingo.core.internal.util;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class BoundedSizeMap<K, V> extends LinkedHashMap<K, V>
{
  private static final long serialVersionUID = 631194002295387887L;
  private int maxEntries;

  public BoundedSizeMap(int paramInt)
  {
    super(16, 0.75F, true);
    this.maxEntries = paramInt;
  }

  protected boolean removeEldestEntry(Map.Entry<K, V> paramEntry)
  {
    if (size() > this.maxEntries);
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.BoundedSizeMap
 * JD-Core Version:    0.6.0
 */