package com.vlingo.sdk.recognition;

import java.util.Set;

public abstract interface VLAction
{
  public abstract String getElseStatement();

  public abstract String getIfCondition();

  public abstract String getName();

  public abstract String getParamValue(String paramString);

  public abstract Set<String> getParameterNames();

  public abstract boolean isConditional();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.VLAction
 * JD-Core Version:    0.6.0
 */