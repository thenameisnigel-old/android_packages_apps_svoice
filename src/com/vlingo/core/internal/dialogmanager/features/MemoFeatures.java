package com.vlingo.core.internal.dialogmanager.features;

public abstract interface MemoFeatures
{
  public static abstract interface Action
  {
    public static final String FIND_MOST_RECENT = "memo.findmostrecent";
    public static final String SAVE = "memo.save";
  }

  public static abstract interface Error
  {
    public static final String ERROR = "memo.error";
    public static final String MISSING = "memo.missing";
  }

  public static abstract interface Field
  {
    public static final String BODY = "memo.body";
    public static final String ID = "memo.id";
    public static final String TITLE = "memo.title";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.features.MemoFeatures
 * JD-Core Version:    0.6.0
 */