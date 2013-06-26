package com.vlingo.core.internal.contacts;

import java.util.List;

public abstract interface ContactMatchListener
{
  public abstract void onAutoAction(ContactMatch paramContactMatch);

  public abstract void onContactMatchResultsUpdated(List<ContactMatch> paramList);

  public abstract void onContactMatchingFailed();

  public abstract void onContactMatchingFinished(List<ContactMatch> paramList);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.contacts.ContactMatchListener
 * JD-Core Version:    0.6.0
 */