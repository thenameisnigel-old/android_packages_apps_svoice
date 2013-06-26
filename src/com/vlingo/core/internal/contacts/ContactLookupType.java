package com.vlingo.core.internal.contacts;

public enum ContactLookupType
{
  static
  {
    EMAIL_ADDRESS = new ContactLookupType("EMAIL_ADDRESS", 1);
    ADDRESS = new ContactLookupType("ADDRESS", 2);
    SOCIAL_NETWORK = new ContactLookupType("SOCIAL_NETWORK", 3);
    BIRTHDAY = new ContactLookupType("BIRTHDAY", 4);
    ContactLookupType[] arrayOfContactLookupType = new ContactLookupType[5];
    arrayOfContactLookupType[0] = PHONE_NUMBER;
    arrayOfContactLookupType[1] = EMAIL_ADDRESS;
    arrayOfContactLookupType[2] = ADDRESS;
    arrayOfContactLookupType[3] = SOCIAL_NETWORK;
    arrayOfContactLookupType[4] = BIRTHDAY;
    $VALUES = arrayOfContactLookupType;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.contacts.ContactLookupType
 * JD-Core Version:    0.6.0
 */