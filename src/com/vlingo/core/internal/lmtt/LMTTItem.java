package com.vlingo.core.internal.lmtt;

import android.database.sqlite.SQLiteDatabase;

public abstract class LMTTItem
{
  public static final String MUSIC = "music";
  public static final String PIM = "pim";
  ChangeType changeType;
  LmttItemType type = LmttItemType.TYPE_UNKNOWN;
  int uid;

  LMTTItem(LmttItemType paramLmttItemType, int paramInt, ChangeType paramChangeType)
  {
    this.type = paramLmttItemType;
    this.uid = paramInt;
    this.changeType = paramChangeType;
  }

  void setChangeType(ChangeType paramChangeType)
  {
    this.changeType = paramChangeType;
  }

  static enum ChangeType
  {
    private final int delta;
    private final DBUpdateType upType;

    static
    {
      INSERT = new ChangeType("INSERT", 1, 1, DBUpdateType.INS);
      DELETE = new ChangeType("DELETE", 2, -1, DBUpdateType.DEL);
      NOCHANGE = new ChangeType("NOCHANGE", 3, 0, DBUpdateType.NOOP);
      ChangeType[] arrayOfChangeType = new ChangeType[4];
      arrayOfChangeType[0] = UPDATE;
      arrayOfChangeType[1] = INSERT;
      arrayOfChangeType[2] = DELETE;
      arrayOfChangeType[3] = NOCHANGE;
      $VALUES = arrayOfChangeType;
    }

    private ChangeType(int paramInt, DBUpdateType paramDBUpdateType)
    {
      this.delta = paramInt;
      this.upType = paramDBUpdateType;
    }

    void dbUpdate(LMTTItem paramLMTTItem, SQLiteDatabase paramSQLiteDatabase)
    {
      this.upType.dbUpdate(paramLMTTItem, paramSQLiteDatabase);
    }

    int runningSum(int paramInt)
    {
      return paramInt + this.delta;
    }

    static abstract enum DBUpdateType
    {
      static
      {
        DEL = new LMTTItem.ChangeType.DBUpdateType.2("DEL", 1);
        UP = new LMTTItem.ChangeType.DBUpdateType.3("UP", 2);
        NOOP = new LMTTItem.ChangeType.DBUpdateType.4("NOOP", 3);
        DBUpdateType[] arrayOfDBUpdateType = new DBUpdateType[4];
        arrayOfDBUpdateType[0] = INS;
        arrayOfDBUpdateType[1] = DEL;
        arrayOfDBUpdateType[2] = UP;
        arrayOfDBUpdateType[3] = NOOP;
        $VALUES = arrayOfDBUpdateType;
      }

      abstract void dbUpdate(LMTTItem paramLMTTItem, SQLiteDatabase paramSQLiteDatabase);
    }
  }

  public static enum LmttItemType
  {
    private String category;
    private String settingName;

    static
    {
      TYPE_PLAYLIST = new LmttItemType("TYPE_PLAYLIST", 2, "playlist", "music");
      TYPE_UNKNOWN = new LmttItemType("TYPE_UNKNOWN", 3, "unknown", "unknown");
      LmttItemType[] arrayOfLmttItemType = new LmttItemType[4];
      arrayOfLmttItemType[0] = TYPE_CONTACT;
      arrayOfLmttItemType[1] = TYPE_SONG;
      arrayOfLmttItemType[2] = TYPE_PLAYLIST;
      arrayOfLmttItemType[3] = TYPE_UNKNOWN;
      $VALUES = arrayOfLmttItemType;
    }

    private LmttItemType(String paramString1, String paramString2)
    {
      this.settingName = paramString1;
      this.category = paramString2;
    }

    public String getCategory()
    {
      return this.category;
    }

    public String getSettingName()
    {
      return this.settingName;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.lmtt.LMTTItem
 * JD-Core Version:    0.6.0
 */