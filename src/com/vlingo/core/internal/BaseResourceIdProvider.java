package com.vlingo.core.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.vlingo.core.internal.util.ApplicationAdapter;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseResourceIdProvider
  implements ResourceIdProvider
{
  private static final int NOT_FOUND_ID = -1;
  private Map<ResourceIdProvider.array, Integer> arrayMap = new HashMap(ResourceIdProvider.array.values().length);
  private Map<ResourceIdProvider.drawable, Integer> drawableMap = new HashMap(ResourceIdProvider.drawable.values().length);
  private Map<ResourceIdProvider.id, Integer> idMap = new HashMap(ResourceIdProvider.id.values().length);
  private Map<ResourceIdProvider.layout, Integer> layoutMap = new HashMap(ResourceIdProvider.layout.values().length);
  private Map<ResourceIdProvider.raw, Integer> rawMap = new HashMap(ResourceIdProvider.raw.values().length);
  private Map<ResourceIdProvider.string, Integer> stringMap = new HashMap(ResourceIdProvider.string.values().length);

  protected BaseResourceIdProvider()
  {
    initDrawableMap();
    initIdMap();
    initLayoutMap();
    initStringMap();
    initArrayMap();
    initRawMap();
  }

  private int getIntValue(Integer paramInteger)
  {
    if (paramInteger == null);
    for (int i = -1; ; i = paramInteger.intValue())
      return i;
  }

  public Drawable getDrawable(ResourceIdProvider.drawable paramdrawable)
  {
    int i = getResourceId(paramdrawable);
    if (i != -1);
    for (Drawable localDrawable = ApplicationAdapter.getInstance().getApplicationContext().getResources().getDrawable(i); ; localDrawable = null)
      return localDrawable;
  }

  public final int getResourceId(ResourceIdProvider.array paramarray)
  {
    return getIntValue((Integer)this.arrayMap.get(paramarray));
  }

  public final int getResourceId(ResourceIdProvider.drawable paramdrawable)
  {
    return getIntValue((Integer)this.drawableMap.get(paramdrawable));
  }

  public final int getResourceId(ResourceIdProvider.id paramid)
  {
    return getIntValue((Integer)this.idMap.get(paramid));
  }

  public final int getResourceId(ResourceIdProvider.layout paramlayout)
  {
    return getIntValue((Integer)this.layoutMap.get(paramlayout));
  }

  public final int getResourceId(ResourceIdProvider.raw paramraw)
  {
    return getIntValue((Integer)this.rawMap.get(paramraw));
  }

  public final int getResourceId(ResourceIdProvider.string paramstring)
  {
    return getIntValue((Integer)this.stringMap.get(paramstring));
  }

  public final String getString(ResourceIdProvider.string paramstring)
  {
    int i = getResourceId(paramstring);
    if (i != -1);
    for (String str = ApplicationAdapter.getInstance().getApplicationContext().getString(i); ; str = null)
      return str;
  }

  public String getString(ResourceIdProvider.string paramstring, Object[] paramArrayOfObject)
  {
    int i = getResourceId(paramstring);
    if (i != -1);
    for (String str = ApplicationAdapter.getInstance().getApplicationContext().getString(i, paramArrayOfObject); ; str = null)
      return str;
  }

  public String[] getStringArray(ResourceIdProvider.array paramarray)
  {
    int i = getResourceId(paramarray);
    if (i != -1);
    for (String[] arrayOfString = ApplicationAdapter.getInstance().getApplicationContext().getResources().getStringArray(i); ; arrayOfString = null)
      return arrayOfString;
  }

  protected abstract void initArrayMap();

  protected abstract void initDrawableMap();

  protected abstract void initIdMap();

  protected abstract void initLayoutMap();

  protected abstract void initRawMap();

  protected abstract void initStringMap();

  protected final void putArray(ResourceIdProvider.array paramarray, Integer paramInteger)
  {
    this.arrayMap.put(paramarray, paramInteger);
  }

  protected final void putDrawable(ResourceIdProvider.drawable paramdrawable, Integer paramInteger)
  {
    this.drawableMap.put(paramdrawable, paramInteger);
  }

  protected final void putId(ResourceIdProvider.id paramid, Integer paramInteger)
  {
    this.idMap.put(paramid, paramInteger);
  }

  protected final void putLayout(ResourceIdProvider.layout paramlayout, Integer paramInteger)
  {
    this.layoutMap.put(paramlayout, paramInteger);
  }

  protected final void putRaw(ResourceIdProvider.raw paramraw, Integer paramInteger)
  {
    this.rawMap.put(paramraw, paramInteger);
  }

  protected final void putString(ResourceIdProvider.string paramstring, Integer paramInteger)
  {
    this.stringMap.put(paramstring, paramInteger);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.BaseResourceIdProvider
 * JD-Core Version:    0.6.0
 */