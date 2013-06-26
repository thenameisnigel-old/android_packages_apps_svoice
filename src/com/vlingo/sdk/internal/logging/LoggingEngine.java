package com.vlingo.sdk.internal.logging;

import java.util.Date;
import java.util.Vector;

public class LoggingEngine
{
  public static final String LEVEL_DEBUG = "DEBUG";
  public static final String LEVEL_ERROR = "ERROR";
  public static final String LEVEL_INFO = "INFO ";
  protected static volatile LoggingEngine instance = null;
  private final Vector<Object> classFilter = new Vector();
  private boolean filterClasses = false;
  private boolean filterThreads = false;
  private String logMsgPrefix = "";
  protected final Vector<Object> logTargets = new Vector();
  private boolean loggingEnabled = true;
  private final Vector<Object> threadFilter = new Vector();

  public static LoggingEngine getInstance()
  {
    monitorenter;
    try
    {
      if (instance == null)
        instance = new LoggingEngine();
      LoggingEngine localLoggingEngine = instance;
      monitorexit;
      return localLoggingEngine;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static boolean isInitialized()
  {
    monitorenter;
    try
    {
      LoggingEngine localLoggingEngine = instance;
      if (localLoggingEngine != null)
      {
        i = 1;
        return i;
      }
      int i = 0;
    }
    finally
    {
      monitorexit;
    }
  }

  public void addClassToFilter(String paramString)
  {
    monitorenter;
    try
    {
      this.classFilter.addElement(paramString);
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

  public void addTarget(LogTarget paramLogTarget)
  {
    monitorenter;
    try
    {
      this.logTargets.addElement(paramLogTarget);
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

  public void addThreadToFilter(String paramString)
  {
    monitorenter;
    try
    {
      this.threadFilter.addElement(paramString);
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

  public void closeLoggers()
  {
    monitorenter;
    try
    {
      int i = this.logTargets.size();
      for (int j = 0; j < i; j++)
        ((LogTarget)this.logTargets.elementAt(j)).close();
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

  public void enableClassFilter(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      this.filterClasses = paramBoolean;
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

  public void enableThreadFilter(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      this.filterThreads = paramBoolean;
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

  protected String formatLogMessage(String paramString1, String paramString2, String paramString3)
  {
    long l1 = System.currentTimeMillis();
    long l2 = l1 % 1000L;
    Date localDate = new Date(l1);
    StringBuffer localStringBuffer = new StringBuffer(80);
    localStringBuffer.append(this.logMsgPrefix);
    localStringBuffer.append(localDate.toString());
    localStringBuffer.append(" +" + l2 + "ms");
    localStringBuffer.append(" - ");
    localStringBuffer.append(paramString2);
    localStringBuffer.append(" [");
    localStringBuffer.append(Thread.currentThread().getName());
    localStringBuffer.append("] ");
    if (paramString1 != null)
    {
      localStringBuffer.append(paramString1);
      localStringBuffer.append(": ");
    }
    localStringBuffer.append(paramString3);
    return localStringBuffer.toString();
  }

  public String getLogMessagePrefix()
  {
    monitorenter;
    try
    {
      String str = this.logMsgPrefix;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public Vector<Object> getTargets()
  {
    monitorenter;
    try
    {
      Vector localVector = this.logTargets;
      monitorexit;
      return localVector;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  protected void log(String paramString1, String paramString2, String paramString3)
  {
    if ((!this.loggingEnabled) || ((this.filterClasses) && (this.classFilter.contains(paramString1))) || ((this.filterThreads) && (this.threadFilter.contains(Thread.currentThread().getName()))));
    while (true)
    {
      return;
      int i = this.logTargets.size();
      String str = formatLogMessage(paramString1, paramString2, paramString3);
      for (int j = 0; j < i; j++)
        ((LogTarget)this.logTargets.elementAt(j)).log(str, paramString2.equals("ERROR"));
    }
  }

  public void removeTarget(LogTarget paramLogTarget)
  {
    monitorenter;
    try
    {
      this.logTargets.removeElement(paramLogTarget);
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

  public void setEnabled(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      this.loggingEnabled = paramBoolean;
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

  public void setLogMessagePrefix(String paramString)
  {
    monitorenter;
    try
    {
      this.logMsgPrefix = paramString;
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
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.logging.LoggingEngine
 * JD-Core Version:    0.6.0
 */