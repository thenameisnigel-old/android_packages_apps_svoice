package com.vlingo.sdk.internal;

import com.vlingo.sdk.internal.util.StringUtils;
import com.vlingo.sdk.internal.vlservice.response.Action;
import com.vlingo.sdk.recognition.VLAction;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

public class VLActionImpl
  implements VLAction
{
  private String elseStatement;
  private String ifCondition;
  private String mName;
  private HashMap<String, String> mParameters;

  VLActionImpl(Action paramAction)
  {
    this.mName = paramAction.getName();
    this.mParameters = new HashMap();
    Enumeration localEnumeration = paramAction.getParams();
    while (localEnumeration.hasMoreElements())
    {
      String str1 = (String)localEnumeration.nextElement();
      String str2 = paramAction.getStringParamValue(str1);
      this.mParameters.put(str1, str2);
    }
    this.ifCondition = paramAction.ifCondition;
    this.elseStatement = paramAction.elseStatement;
  }

  public String getElseStatement()
  {
    return this.elseStatement;
  }

  public String getIfCondition()
  {
    return this.ifCondition;
  }

  public String getName()
  {
    return this.mName;
  }

  public String getParamValue(String paramString)
  {
    if (this.mParameters != null);
    for (String str = (String)this.mParameters.get(paramString); ; str = null)
      return str;
  }

  public Set<String> getParameterNames()
  {
    return this.mParameters.keySet();
  }

  public boolean isConditional()
  {
    if (!StringUtils.isNullOrWhiteSpace(this.ifCondition));
    for (int i = 1; ; i = 0)
      return i;
  }

  public String toString()
  {
    return "Action: " + this.mName + this.mParameters.toString();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.VLActionImpl
 * JD-Core Version:    0.6.0
 */