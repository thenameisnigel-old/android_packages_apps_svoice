package com.vlingo.core.internal.lmtt;

public class LMTTContactItem extends LMTTItem
{
  String companyName;
  String firstName;
  String lastName;

  LMTTContactItem(int paramInt, LMTTItem.ChangeType paramChangeType)
  {
    this(null, null, paramInt, paramChangeType);
  }

  LMTTContactItem(String paramString1, String paramString2, int paramInt, LMTTItem.ChangeType paramChangeType)
  {
    super(LMTTItem.LmttItemType.TYPE_CONTACT, paramInt, paramChangeType);
    if (paramString1 == null)
      paramString1 = "";
    if (paramString2 == null)
      paramString2 = "";
    this.firstName = paramString1;
    this.lastName = paramString2;
    this.companyName = "";
  }

  public int hashCode()
  {
    int i = 0;
    if (this.firstName != null)
      i = 0 + this.firstName.hashCode();
    if (this.lastName != null)
      i += this.lastName.hashCode();
    if (this.companyName != null)
      i += this.companyName.hashCode();
    return i;
  }

  public String toString()
  {
    return "LMTTContact: " + this.firstName + " " + this.lastName + " | " + this.companyName + " | uid: " + this.uid + " changeType: " + this.changeType;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.lmtt.LMTTContactItem
 * JD-Core Version:    0.6.0
 */