package com.vlingo.core.internal.logging;

import java.util.Hashtable;

public class Logger
{
  private static Hashtable<LogMatcher, LogListener> listeners = new Hashtable();
  private String m_ClassName;
  private boolean m_isEnabled = true;
  private String m_prefix;

  private Logger(String paramString1, String paramString2, boolean paramBoolean)
  {
    this.m_ClassName = paramString1.substring(1 + paramString1.lastIndexOf('.'));
    this.m_prefix = paramString2;
    this.m_isEnabled = paramBoolean;
  }

  public static void clearLogListeners()
  {
    listeners.clear();
  }

  public static Logger getLogger(Class<?> paramClass)
  {
    return getLogger(paramClass, null);
  }

  public static Logger getLogger(Class<?> paramClass, String paramString)
  {
    return getLogger(paramClass, paramString, true);
  }

  public static Logger getLogger(Class<?> paramClass, String paramString, boolean paramBoolean)
  {
    if (paramClass == null)
      throw new NullPointerException();
    if (paramString == null)
      paramString = "VAC_";
    return new Logger(paramClass.getName(), paramString, paramBoolean);
  }

  public static Logger getLogger(Class<?> paramClass, boolean paramBoolean)
  {
    return getLogger(paramClass, null, true);
  }

  private String getThread()
  {
    return Thread.currentThread().getName();
  }

  public static void setLogListener(LogMatcher paramLogMatcher, LogListener paramLogListener)
  {
    listeners.put(paramLogMatcher, paramLogListener);
  }

  public void debug(String paramString)
  {
  }

  public void error(String paramString)
  {
  }

  @Deprecated
  public void error(String paramString1, String paramString2)
  {
  }

  public void error(String paramString1, String paramString2, String paramString3)
  {
  }

  public void info(String paramString)
  {
  }

  public void warn(String paramString)
  {
  }

  public static abstract interface LogListener
  {
    public abstract void onMatch(String paramString);
  }

  public static class LogMatcher
  {
    private String mPrefix;
    private String mTag;

    public LogMatcher(String paramString)
    {
      this.mTag = paramString;
    }

    public LogMatcher(String paramString1, String paramString2)
    {
      this(paramString1);
      this.mPrefix = paramString2;
    }

    public boolean match(String paramString1, String paramString2)
    {
      if ((this.mTag.equalsIgnoreCase(paramString1)) && (paramString2.startsWith(this.mPrefix)));
      for (int i = 1; ; i = 0)
        return i;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.logging.Logger
 * JD-Core Version:    0.6.0
 */