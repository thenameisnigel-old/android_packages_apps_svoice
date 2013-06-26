package com.vlingo.core.internal.localsearch;

import com.vlingo.core.internal.util.BoundedSizeMap;

public class LocalSearchListingCache extends BoundedSizeMap<String, LocalSearchListing>
{
  static final int CACHE_SIZE = 30;
  private static final long serialVersionUID = -7098052622138782393L;

  public LocalSearchListingCache()
  {
    super(30);
  }

  public void add(LocalSearchListing paramLocalSearchListing)
  {
    put(paramLocalSearchListing.getListingID(), paramLocalSearchListing);
  }

  public LocalSearchListing get(Object paramObject)
  {
    LocalSearchListing localLocalSearchListing = (LocalSearchListing)super.get(paramObject);
    if (localLocalSearchListing != null)
    {
      remove(paramObject);
      put((String)paramObject, localLocalSearchListing);
    }
    return localLocalSearchListing;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.localsearch.LocalSearchListingCache
 * JD-Core Version:    0.6.0
 */