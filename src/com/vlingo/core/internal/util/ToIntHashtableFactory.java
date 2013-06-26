package com.vlingo.core.internal.util;

public class ToIntHashtableFactory
{
  public static ToIntHashtable createNewHashtable()
  {
    return new AndroidToIntHashtable();
  }

  public static ToIntHashtable createNewHashtable(int paramInt)
  {
    return new AndroidToIntHashtable(paramInt);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.ToIntHashtableFactory
 * JD-Core Version:    0.6.0
 */