package com.vlingo.sdk.internal.logging;

public class Logger
{
  private String m_ClassName;
  private boolean m_isEnabled = true;
  private String m_prefix;

  private Logger(String paramString1, String paramString2, boolean paramBoolean)
  {
    this.m_ClassName = paramString1.substring(1 + paramString1.lastIndexOf('.'));
    this.m_prefix = paramString2;
    this.m_isEnabled = paramBoolean;
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
      paramString = "VLGSDK_";
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

  public void debug(String paramString)
  {
  }

  public void error(String paramString)
  {
  }

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
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.logging.Logger
 * JD-Core Version:    0.6.0
 */