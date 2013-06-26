package com.vlingo.core.internal.contacts;

import java.util.EnumSet;

public enum ContactType
{
  final String action;
  boolean keepUnrelatedContacts;
  int subType;
  final EnumSet<ContactLookupType> types;

  static
  {
    EMAIL = new ContactType("EMAIL", 2, "email", EnumSet.of(ContactLookupType.EMAIL_ADDRESS));
    ADDRESS = new ContactType("ADDRESS", 3, "address", EnumSet.of(ContactLookupType.ADDRESS));
    BIRTHDAY = new ContactType("BIRTHDAY", 4, "birthday", EnumSet.of(ContactLookupType.BIRTHDAY));
    FACEBOOK = new ContactType("FACEBOOK", 5, "facebook", EnumSet.of(ContactLookupType.SOCIAL_NETWORK));
    MESSAGE = new ContactType("MESSAGE", 6, "message", EnumSet.allOf(ContactLookupType.class));
    UNDEFINED = new ContactType("UNDEFINED", 7, "", EnumSet.noneOf(ContactLookupType.class));
    ContactType[] arrayOfContactType = new ContactType[8];
    arrayOfContactType[0] = CALL;
    arrayOfContactType[1] = SMS;
    arrayOfContactType[2] = EMAIL;
    arrayOfContactType[3] = ADDRESS;
    arrayOfContactType[4] = BIRTHDAY;
    arrayOfContactType[5] = FACEBOOK;
    arrayOfContactType[6] = MESSAGE;
    arrayOfContactType[7] = UNDEFINED;
    $VALUES = arrayOfContactType;
  }

  private ContactType(String paramString, EnumSet<ContactLookupType> paramEnumSet)
  {
    this.action = paramString;
    this.types = paramEnumSet;
  }

  private ContactType(String paramString, EnumSet<ContactLookupType> paramEnumSet, int paramInt)
  {
    this.action = paramString;
    this.types = paramEnumSet;
    this.subType = paramInt;
  }

  private ContactType(String paramString, EnumSet<ContactLookupType> paramEnumSet, boolean paramBoolean)
  {
    this.action = paramString;
    this.types = paramEnumSet;
    this.keepUnrelatedContacts = paramBoolean;
  }

  public static ContactType of(String paramString)
  {
    ContactType[] arrayOfContactType = values();
    int i = arrayOfContactType.length;
    int j = 0;
    ContactType localContactType;
    if (j < i)
    {
      localContactType = arrayOfContactType[j];
      if (!localContactType.toString().equals(paramString));
    }
    while (true)
    {
      return localContactType;
      j++;
      break;
      localContactType = UNDEFINED;
    }
  }

  public EnumSet<ContactLookupType> getLookupTypes()
  {
    return this.types;
  }

  public int getPreferredTarget()
  {
    return this.subType;
  }

  public boolean keepUnrelatedContacts()
  {
    return this.keepUnrelatedContacts;
  }

  public String toString()
  {
    return this.action;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.contacts.ContactType
 * JD-Core Version:    0.6.0
 */