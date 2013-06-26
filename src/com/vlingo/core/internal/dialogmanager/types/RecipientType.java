package com.vlingo.core.internal.dialogmanager.types;

public class RecipientType
{
  private String address;
  private String displayName;

  public RecipientType(String paramString1, String paramString2)
  {
    this.displayName = paramString1;
    this.address = paramString2;
  }

  public boolean equals(Object paramObject)
  {
    int i = 0;
    if ((paramObject == null) || (getClass() != paramObject.getClass()));
    while (true)
    {
      return i;
      RecipientType localRecipientType = (RecipientType)paramObject;
      if (this.address == null)
      {
        if (localRecipientType.address != null)
          continue;
        label38: if (this.displayName != null)
          break label74;
        if (localRecipientType.displayName != null)
          continue;
      }
      label74: 
      do
      {
        i = 1;
        break;
        if (this.address.equals(localRecipientType.address))
          break label38;
        break;
      }
      while (this.displayName.equals(localRecipientType.displayName));
    }
  }

  public String getAddress()
  {
    return this.address;
  }

  public String getDisplayName()
  {
    return this.displayName;
  }

  public void setAddress(String paramString)
  {
    this.address = paramString;
  }

  public void setDisplayName(String paramString)
  {
    this.displayName = paramString;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.types.RecipientType
 * JD-Core Version:    0.6.0
 */