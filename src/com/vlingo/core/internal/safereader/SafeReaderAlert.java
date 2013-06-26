package com.vlingo.core.internal.safereader;

import java.io.Serializable;

public class SafeReaderAlert
  implements Comparable<SafeReaderAlert>, Serializable
{
  private static final long serialVersionUID = -2564622766468729519L;
  protected String address = "";
  protected long id = -1L;
  protected String senderDisplayName = null;
  protected long timeStamp = 0L;
  protected String type = "SafeReaderAlert";

  public SafeReaderAlert(long paramLong1, String paramString1, String paramString2, long paramLong2, String paramString3)
  {
    this.id = paramLong1;
    this.address = paramString1;
    this.senderDisplayName = paramString2;
    this.timeStamp = paramLong2;
    this.type = paramString3;
  }

  public int compareTo(SafeReaderAlert paramSafeReaderAlert)
  {
    return (int)(getTimeStamp() - paramSafeReaderAlert.getTimeStamp());
  }

  public boolean equals(Object paramObject)
  {
    int i = 0;
    if ((paramObject == null) || (getClass() != paramObject.getClass()));
    while (true)
    {
      return i;
      SafeReaderAlert localSafeReaderAlert = (SafeReaderAlert)paramObject;
      if (this.id != localSafeReaderAlert.id)
        continue;
      if (this.type == null)
        if (localSafeReaderAlert.type != null)
          continue;
      do
      {
        i = 1;
        break;
      }
      while (this.type.equals(localSafeReaderAlert.type));
    }
  }

  public String getAddress()
  {
    return this.address;
  }

  public long getId()
  {
    return this.id;
  }

  public String getSenderDisplayName()
  {
    return this.senderDisplayName;
  }

  public long getTimeStamp()
  {
    return this.timeStamp;
  }

  public String getType()
  {
    return this.type;
  }

  public int hashCode()
  {
    return new Long(getId()).hashCode();
  }

  public void setAddress(String paramString)
  {
    this.address = paramString;
  }

  public void setId(long paramLong)
  {
    this.id = paramLong;
  }

  public void setSenderDisplayName(String paramString)
  {
    this.senderDisplayName = paramString;
  }

  public void setTimeStamp(long paramLong)
  {
    this.timeStamp = paramLong;
  }

  public void setType(String paramString)
  {
    this.type = paramString;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.safereader.SafeReaderAlert
 * JD-Core Version:    0.6.0
 */