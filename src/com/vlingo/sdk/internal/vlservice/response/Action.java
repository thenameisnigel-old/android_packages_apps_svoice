package com.vlingo.sdk.internal.vlservice.response;

import com.vlingo.sdk.internal.recognizer.results.RecResults;
import com.vlingo.sdk.internal.recognizer.results.TaggedResults;
import com.vlingo.sdk.internal.recognizer.results.TaggedResults.ParseGroup;
import com.vlingo.sdk.internal.recognizer.results.TaggedResults.Tag;
import java.util.Enumeration;
import java.util.Hashtable;

public class Action
  implements Cloneable
{
  private static final String c_VPathPrefix = "${";
  public String elseStatement;
  public String ifCondition;
  public String name;
  protected Hashtable parameters;

  public Action()
  {
    this.parameters = new Hashtable();
  }

  protected Action(Action paramAction)
  {
    this.name = paramAction.name;
    this.parameters = paramAction.parameters;
  }

  public Action(String paramString)
  {
    this();
    this.name = paramString;
  }

  private String expandVariableSection(String paramString, TaggedResults paramTaggedResults)
  {
    int i = paramString.toLowerCase().indexOf("${alternates");
    int j;
    String str2;
    String str3;
    String str1;
    if (i >= 0)
    {
      j = paramString.indexOf("}", i);
      if (j >= 0)
      {
        str2 = "";
        str3 = paramString.substring(i + 2, j);
        if ((str3.equalsIgnoreCase("alternates.wl")) || (str3.equalsIgnoreCase("alternates.ul")))
        {
          str2 = paramTaggedResults.getUttResults().getString();
          if (str2 != null)
            break label233;
          str1 = null;
        }
      }
    }
    while (true)
    {
      return str1;
      int k = str3.indexOf("tag(");
      if (k < 0)
        break;
      int m = k + 4;
      int n = str3.indexOf(")", m);
      if (n < 0)
        break;
      String str4 = str3.substring(m, n);
      TaggedResults.ParseGroup localParseGroup = paramTaggedResults.getParseGroup();
      RecResults localRecResults = null;
      if (localParseGroup != null)
      {
        TaggedResults.Tag localTag = localParseGroup.lookupTagByName(str4);
        if (localTag != null)
          localRecResults = localTag.getRecResults();
      }
      String str5 = str3.substring(n + 1);
      if ((localRecResults != null) && (str5.length() > 0) && (localRecResults.uttListCannonical != null) && (localRecResults.uttListCannonical.length > 0))
      {
        str2 = localRecResults.uttListCannonical[0];
        break;
      }
      if (localRecResults == null)
        break;
      str2 = localRecResults.getString();
      break;
      label233: if (i > 0);
      for (str1 = paramString.substring(0, i) + str2; ; str1 = str2)
      {
        if (j >= -1 + paramString.length())
          break label311;
        str1 = str1 + paramString.substring(j + 1);
        break;
      }
      label311: continue;
      str1 = null;
    }
  }

  private Object parseVPath(String paramString, TaggedResults paramTaggedResults)
  {
    Object localObject = paramString;
    for (String str = expandVariableSection((String)localObject, paramTaggedResults); str != null; str = expandVariableSection((String)localObject, paramTaggedResults))
      localObject = str;
    return localObject;
  }

  public void addParameter(String paramString1, String paramString2, TaggedResults paramTaggedResults)
  {
    Object localObject = paramString2;
    if ((paramTaggedResults != null) && (isVPath(paramString2)))
      localObject = parseVPath(paramString2, paramTaggedResults);
    if (localObject == null)
      localObject = "";
    setParameterValue(paramString1, localObject);
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    Action localAction = (Action)super.clone();
    localAction.parameters = ((Hashtable)this.parameters.clone());
    return localAction;
  }

  public String getName()
  {
    return this.name;
  }

  public Object getParamValue(String paramString)
  {
    if (paramString == null);
    for (Object localObject = null; ; localObject = this.parameters.get(paramString))
      return localObject;
  }

  public Hashtable getParameters()
  {
    return this.parameters;
  }

  public Enumeration<String> getParams()
  {
    return this.parameters.keys();
  }

  public String getStringParamValue(String paramString)
  {
    Object localObject = getParamValue(paramString);
    String str;
    if (localObject == null)
      str = "";
    while (true)
    {
      return str;
      if ((localObject instanceof RecResults))
      {
        str = ((RecResults)localObject).getString();
        continue;
      }
      str = (String)localObject;
    }
  }

  public boolean isConditional()
  {
    if ((this.ifCondition != null) && (this.ifCondition.length() > 0));
    for (int i = 1; ; i = 0)
      return i;
  }

  boolean isVPath(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 3) && (paramString.indexOf("${") >= 0));
    for (int i = 1; ; i = 0)
      return i;
  }

  public Enumeration keys()
  {
    if (this.parameters != null);
    for (Enumeration localEnumeration = this.parameters.keys(); ; localEnumeration = null)
      return localEnumeration;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setParameterValue(String paramString, Object paramObject)
  {
    this.parameters.put(paramString, paramObject);
  }

  public String toHtmlString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("Action: ");
    localStringBuffer.append(this.name);
    localStringBuffer.append("<br/>");
    localStringBuffer.append("    If: ");
    localStringBuffer.append(this.ifCondition);
    localStringBuffer.append("<br/>");
    localStringBuffer.append("  Else: ");
    localStringBuffer.append(this.elseStatement);
    localStringBuffer.append("<br/>");
    Enumeration localEnumeration = this.parameters.keys();
    while (localEnumeration.hasMoreElements())
    {
      String str = (String)localEnumeration.nextElement();
      Object localObject = this.parameters.get(str);
      localStringBuffer.append("   ");
      localStringBuffer.append(str);
      localStringBuffer.append(": ");
      localStringBuffer.append(localObject.toString());
      localStringBuffer.append("<br/>");
    }
    return localStringBuffer.toString();
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("Action: ");
    localStringBuffer.append(this.name);
    localStringBuffer.append("\n");
    localStringBuffer.append("    If: ");
    localStringBuffer.append(this.ifCondition);
    localStringBuffer.append("\n");
    localStringBuffer.append("  Else: ");
    localStringBuffer.append(this.elseStatement);
    localStringBuffer.append("\n");
    Enumeration localEnumeration = this.parameters.keys();
    while (localEnumeration.hasMoreElements())
    {
      String str = (String)localEnumeration.nextElement();
      Object localObject = this.parameters.get(str);
      localStringBuffer.append("   ");
      localStringBuffer.append(str);
      localStringBuffer.append(": ");
      localStringBuffer.append(localObject.toString());
      localStringBuffer.append("\n");
    }
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.vlservice.response.Action
 * JD-Core Version:    0.6.0
 */