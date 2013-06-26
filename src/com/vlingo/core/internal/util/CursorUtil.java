package com.vlingo.core.internal.util;

import android.database.Cursor;
import android.database.CursorWrapper;

public class CursorUtil
{
  public static Cursor guard(Cursor paramCursor)
  {
    return new CursorGuard(paramCursor);
  }

  protected static boolean hasRows(Cursor paramCursor)
  {
    if ((isValid(paramCursor)) && (paramCursor.getCount() > 0));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean isValid(Cursor paramCursor)
  {
    if ((paramCursor != null) && (!paramCursor.isClosed()));
    for (int i = 1; ; i = 0)
      return i;
  }

  protected static boolean moveToFirst(Cursor paramCursor)
  {
    if ((isValid(paramCursor)) && (paramCursor.moveToFirst()));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static class CursorGuard extends CursorWrapper
  {
    private Cursor cursor;

    public CursorGuard(Cursor paramCursor)
    {
      super();
      this.cursor = paramCursor;
    }

    private Cursor cursor()
    {
      return this.cursor;
    }

    public static boolean isValid(Cursor paramCursor)
    {
      return CursorUtil.isValid(paramCursor);
    }

    public int getCount()
    {
      if (isValid());
      for (int i = 0; ; i = cursor().getCount())
        return i;
    }

    public boolean isValid()
    {
      return isValid(cursor());
    }

    public boolean moveToFirst()
    {
      if ((isValid()) && (cursor().moveToFirst()));
      for (int i = 1; ; i = 0)
        return i;
    }

    public boolean moveToLast()
    {
      if ((isValid()) && (cursor().moveToLast()));
      for (int i = 1; ; i = 0)
        return i;
    }

    public boolean moveToNext()
    {
      if ((isValid()) && (cursor().moveToNext()));
      for (int i = 1; ; i = 0)
        return i;
    }

    public boolean moveToPosition(int paramInt)
    {
      if ((isValid()) && (cursor().moveToPosition(paramInt)));
      for (int i = 1; ; i = 0)
        return i;
    }

    public boolean moveToPrevious()
    {
      if ((isValid()) && (cursor().moveToPrevious()));
      for (int i = 1; ; i = 0)
        return i;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.CursorUtil
 * JD-Core Version:    0.6.0
 */