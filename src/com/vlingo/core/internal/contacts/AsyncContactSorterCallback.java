package com.vlingo.core.internal.contacts;

import java.util.List;

abstract interface AsyncContactSorterCallback
{
  public abstract void onAsyncSortingFailed();

  public abstract void onAsyncSortingFinished(List<ContactMatch> paramList);

  public abstract void onAsyncSortingUpdated(List<ContactMatch> paramList);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.contacts.AsyncContactSorterCallback
 * JD-Core Version:    0.6.0
 */