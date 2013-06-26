package com.vlingo.core.internal.contacts.mru;

public abstract interface MRUStore
{
  public abstract void init(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt);

  public abstract MRUTable[] loadMRUTables();

  public abstract void removeEntry(String paramString, int paramInt);

  public abstract boolean rescaleAllCounts(String paramString, float paramFloat);

  public abstract void setEntryCount(String paramString1, int paramInt, String paramString2, float paramFloat);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.contacts.mru.MRUStore
 * JD-Core Version:    0.6.0
 */