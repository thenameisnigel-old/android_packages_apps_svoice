package com.google.gdata.util.common.base;

public abstract interface Escaper
{
  public abstract Appendable escape(Appendable paramAppendable);

  public abstract String escape(String paramString);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.google.gdata.util.common.base.Escaper
 * JD-Core Version:    0.6.0
 */