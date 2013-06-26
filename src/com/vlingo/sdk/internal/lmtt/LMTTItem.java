package com.vlingo.sdk.internal.lmtt;

public abstract class LMTTItem
{
  public ChangeType changeType;
  public LmttItemType type = LmttItemType.TYPE_UNKNOWN;
  public int uid;

  public LMTTItem(LmttItemType paramLmttItemType, int paramInt, ChangeType paramChangeType)
  {
    this.type = paramLmttItemType;
    this.uid = paramInt;
    this.changeType = paramChangeType;
  }

  public abstract void getDelXML(StringBuilder paramStringBuilder);

  public abstract void getInsXML(StringBuilder paramStringBuilder);

  public abstract void getUpXML(StringBuilder paramStringBuilder);

  public String getXML()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    switch (1.$SwitchMap$com$vlingo$sdk$internal$lmtt$LMTTItem$ChangeType[this.changeType.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return localStringBuilder.toString();
      getUpXML(localStringBuilder);
      continue;
      getInsXML(localStringBuilder);
      continue;
      getDelXML(localStringBuilder);
    }
  }

  public void setChangeType(ChangeType paramChangeType)
  {
    this.changeType = paramChangeType;
  }

  public static enum ChangeType
  {
    static
    {
      INSERT = new ChangeType("INSERT", 1);
      DELETE = new ChangeType("DELETE", 2);
      NOCHANGE = new ChangeType("NOCHANGE", 3);
      ChangeType[] arrayOfChangeType = new ChangeType[4];
      arrayOfChangeType[0] = UPDATE;
      arrayOfChangeType[1] = INSERT;
      arrayOfChangeType[2] = DELETE;
      arrayOfChangeType[3] = NOCHANGE;
      $VALUES = arrayOfChangeType;
    }
  }

  public static enum LmttItemType
  {
    static
    {
      TYPE_PLAYLIST = new LmttItemType("TYPE_PLAYLIST", 2);
      TYPE_UNKNOWN = new LmttItemType("TYPE_UNKNOWN", 3);
      LmttItemType[] arrayOfLmttItemType = new LmttItemType[4];
      arrayOfLmttItemType[0] = TYPE_CONTACT;
      arrayOfLmttItemType[1] = TYPE_SONG;
      arrayOfLmttItemType[2] = TYPE_PLAYLIST;
      arrayOfLmttItemType[3] = TYPE_UNKNOWN;
      $VALUES = arrayOfLmttItemType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.lmtt.LMTTItem
 * JD-Core Version:    0.6.0
 */