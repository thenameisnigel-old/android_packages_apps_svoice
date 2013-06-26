package com.vlingo.core.internal.contacts.mru;

import com.vlingo.core.internal.contacts.ContactType;

public class MRU
{
  private static final String[] APPLICATION_NAMES;
  private static final String[] COLUMNS;
  private static final int COUNT_INCREMENT_FOR_RESCALE = 5;
  private static Class<?> ImplClass;
  private static final int MRU_MAX_SIZE = 50;
  private static final float RESCALE_FACTOR = 0.98F;
  private static MRU m_instance;
  private MRUStore m_store = createMRUStore();
  MRUTable[] m_tables = this.m_store.loadMRUTables();

  static
  {
    String[] arrayOfString1 = new String[5];
    arrayOfString1[0] = "sms";
    arrayOfString1[1] = "call";
    arrayOfString1[2] = "email";
    arrayOfString1[3] = "message";
    arrayOfString1[4] = "facebook";
    APPLICATION_NAMES = arrayOfString1;
    String[] arrayOfString2 = new String[3];
    arrayOfString2[0] = "contactID";
    arrayOfString2[1] = "address";
    arrayOfString2[2] = "count";
    COLUMNS = arrayOfString2;
    m_instance = null;
    ImplClass = null;
  }

  private MRUStore createMRUStore()
  {
    if (ImplClass == null)
      throw new RuntimeException("MRUStore implementation class is not set");
    try
    {
      MRUStore localMRUStore = (MRUStore)ImplClass.newInstance();
      localMRUStore.init(APPLICATION_NAMES, COLUMNS, 50);
      return localMRUStore;
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new RuntimeException("MRUStore InstantiationException: " + localInstantiationException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    throw new RuntimeException("MRUStore IllegalAccessException: " + localIllegalAccessException);
  }

  public static MRU getMRU()
  {
    monitorenter;
    try
    {
      if (m_instance == null)
        m_instance = new MRU();
      MRU localMRU = m_instance;
      monitorexit;
      return localMRU;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private MRUTable getMRUTable(ContactType paramContactType)
  {
    int i = 0;
    if (i < APPLICATION_NAMES.length)
      if (!APPLICATION_NAMES[i].equals(paramContactType.toString()));
    for (MRUTable localMRUTable = this.m_tables[i]; ; localMRUTable = null)
    {
      return localMRUTable;
      i++;
      break;
    }
  }

  private MRUTable getMRUTable(String paramString)
  {
    int i = 0;
    if (i < APPLICATION_NAMES.length)
      if (!APPLICATION_NAMES[i].equals(paramString));
    for (MRUTable localMRUTable = this.m_tables[i]; ; localMRUTable = null)
    {
      return localMRUTable;
      i++;
      break;
    }
  }

  private void pruneTable(String paramString, MRUTable paramMRUTable)
  {
    while (paramMRUTable.getNumItems() > 50)
    {
      Integer localInteger = paramMRUTable.removeLowestScoringContact();
      if (localInteger == null)
        continue;
      this.m_store.removeEntry(paramString, localInteger.intValue());
    }
  }

  private void rescaleCounts(String paramString, MRUTable paramMRUTable)
  {
    if (this.m_store.rescaleAllCounts(paramString, 0.98F))
      paramMRUTable.scaleValues(0.98F);
  }

  public static void setMRUStoreImpl(Class<?> paramClass)
  {
    monitorenter;
    if (paramClass == null)
      try
      {
        throw new RuntimeException("MRUStore class null");
      }
      finally
      {
        monitorexit;
      }
    if (!MRUStore.class.isAssignableFrom(paramClass))
      throw new RuntimeException("MRUStore invalid impl: " + paramClass);
    ImplClass = paramClass;
    monitorexit;
  }

  public float getNormalizedCount(ContactType paramContactType, int paramInt)
  {
    MRUTable localMRUTable = getMRUTable(paramContactType);
    float f;
    if (localMRUTable == null)
      f = 0.0F;
    while (true)
    {
      return f;
      f = localMRUTable.getNormalizedCount(paramInt);
    }
  }

  public float getNormalizedCount(ContactType paramContactType, int paramInt, String paramString)
  {
    MRUTable localMRUTable = getMRUTable(paramContactType);
    float f;
    if (localMRUTable == null)
      f = 0.0F;
    while (true)
    {
      return f;
      f = localMRUTable.getNormalizedCount(paramInt, paramString);
    }
  }

  public float incrementCount(String paramString1, int paramInt, String paramString2)
  {
    MRUTable localMRUTable = getMRUTable(paramString1);
    float f;
    if (localMRUTable == null)
      f = 0.0F;
    while (true)
    {
      return f;
      f = localMRUTable.incrementCount(paramInt, paramString2);
      this.m_store.setEntryCount(paramString1, paramInt, paramString2, f);
      if (localMRUTable.getCountSinceLastUdate() > 5)
        rescaleCounts(paramString1, localMRUTable);
      if (localMRUTable.getNumItems() <= 50)
        continue;
      pruneTable(paramString1, localMRUTable);
    }
  }

  public String toString(String paramString)
  {
    MRUTable localMRUTable = getMRUTable(paramString);
    if (localMRUTable == null);
    for (String str = "No MRU Table for " + paramString; ; str = "MRU for " + paramString + ":" + localMRUTable.toString())
      return str;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.contacts.mru.MRU
 * JD-Core Version:    0.6.0
 */