package com.vlingo.core.internal.settings.util;

public abstract class Setting
{
  public static final String SETTING_RESOURCE_DOMAIN = "vlingoVoiceSettingRD";
  public static final int TYPE_BOOLEAN = 0;
  public static final int TYPE_DATA = 6;
  public static final int TYPE_ENUM = 3;
  public static final int TYPE_INT = 2;
  public static final int TYPE_LONG = 4;
  public static final int TYPE_SERIALIZABLE = 5;
  public static final int TYPE_STRING = 1;
  private final SettingChangeHandler changeHandler;
  public Object defaultValue = null;
  public String description = null;
  protected boolean isDirty = false;
  public String key = null;
  protected boolean mirrorInResourceManager = false;
  public int type = -1;
  public Object value = null;

  protected Setting(String paramString1, int paramInt, Object paramObject, String paramString2)
  {
    this.key = paramString1.toLowerCase();
    this.type = paramInt;
    this.value = paramObject;
    this.defaultValue = paramObject;
    this.description = paramString2;
    this.changeHandler = SettingChangeHandler.getInstance();
  }

  public void clearDirty()
  {
    monitorenter;
    try
    {
      this.isDirty = false;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int getType()
  {
    return this.type;
  }

  public boolean isDirty()
  {
    monitorenter;
    try
    {
      boolean bool = this.isDirty;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean isSetToDefault()
  {
    return this.defaultValue.equals(this.value);
  }

  public void migrate(Setting paramSetting)
  {
    monitorenter;
    try
    {
      if (!this.description.equals(paramSetting.description))
      {
        this.description = paramSetting.description;
        setDirty();
      }
      if (!this.defaultValue.equals(paramSetting.defaultValue))
      {
        this.defaultValue = paramSetting.defaultValue;
        setDirty();
      }
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void onSavedValue(Object paramObject)
  {
    monitorenter;
    try
    {
      this.value = paramObject;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void reset()
  {
    monitorenter;
    try
    {
      setValueInternal(this.defaultValue);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setDefault()
  {
    setValueInternal(this.defaultValue);
  }

  protected void setDefaultValue(Object paramObject)
  {
    this.value = paramObject;
    this.defaultValue = paramObject;
  }

  public void setDirty()
  {
    monitorenter;
    try
    {
      this.isDirty = true;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  protected void setValueInternal(Object paramObject)
  {
    monitorenter;
    if (paramObject == null)
      try
      {
        throw new RuntimeException("cannot assign null value to a Setting");
      }
      finally
      {
        monitorexit;
      }
    if (!this.value.equals(paramObject))
    {
      this.value = paramObject;
      setDirty();
    }
    monitorexit;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.settings.util.Setting
 * JD-Core Version:    0.6.0
 */