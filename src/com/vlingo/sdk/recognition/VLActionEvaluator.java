package com.vlingo.sdk.recognition;

import com.vlingo.sdk.internal.core.ApplicationAdapter;
import com.vlingo.sdk.internal.util.PackageUtil;
import java.util.HashMap;

public class VLActionEvaluator
{
  protected HashMap<String, String> variables = new HashMap();

  private boolean getBooleanVariable(String paramString)
  {
    return "true".equalsIgnoreCase(getVariable(paramString));
  }

  private String getVariable(String paramString)
  {
    String str = (String)this.variables.get(paramString);
    if (str == null)
      str = "";
    return str;
  }

  protected static boolean isBoolean(String paramString)
  {
    if (("true".equalsIgnoreCase(paramString)) || ("false".equalsIgnoreCase(paramString)));
    for (int i = 1; ; i = 0)
      return i;
  }

  private void setVariable(String paramString1, String paramString2)
  {
    this.variables.put(paramString1, paramString2);
  }

  public boolean evaluateAction(VLAction paramVLAction)
  {
    int i = 1;
    if (!paramVLAction.isConditional())
      return i;
    Operator localOperator = Operator.OR;
    String[] arrayOfString;
    label48: int k;
    boolean bool;
    if (paramVLAction.getIfCondition().contains("&&"))
    {
      arrayOfString = paramVLAction.getIfCondition().split("&&");
      localOperator = Operator.AND;
      j = 1;
      if (localOperator == Operator.OR)
        j = 0;
      k = 0;
      if (k < arrayOfString.length)
      {
        bool = evaluateExpression(arrayOfString[k]);
        if (localOperator != Operator.AND)
          break label206;
        if ((j == 0) || (!bool))
          break label200;
      }
    }
    label200: for (int j = i; ; j = 0)
    {
      if (j != 0)
        break label226;
      if ((j == 0) && (paramVLAction.getElseStatement() != null) && (paramVLAction.getElseStatement().length() > 0))
        evaluateExpression(paramVLAction.getElseStatement());
      i = j;
      break;
      if (paramVLAction.getIfCondition().contains("||"))
      {
        arrayOfString = paramVLAction.getIfCondition().split("||");
        break label48;
      }
      arrayOfString = new String[i];
      arrayOfString[0] = paramVLAction.getIfCondition();
      break label48;
    }
    label206: if (localOperator == Operator.OR)
      if ((j == 0) && (!bool))
        break label232;
    label226: label232: for (j = i; ; j = 0)
    {
      k++;
      break;
    }
  }

  protected boolean evaluateExpression(String paramString)
  {
    int i = paramString.indexOf('(');
    int j = -1 + paramString.length();
    String str1 = paramString.substring(i + 1, j);
    String str2 = paramString.substring(0, i);
    String[] arrayOfString;
    if (str1.contains(","))
    {
      arrayOfString = str1.split(",");
      int k = 0;
      if (str2.startsWith("!"))
      {
        str2 = str2.substring(1);
        k = 1;
      }
      bool = evaluateExpression(str2, arrayOfString);
      if (k != 0)
        if (bool)
          break label117;
    }
    label117: for (boolean bool = true; ; bool = false)
    {
      return bool;
      arrayOfString = new String[1];
      arrayOfString[0] = str1;
      break;
    }
  }

  protected boolean evaluateExpression(String paramString, String[] paramArrayOfString)
  {
    int i = 1;
    String str5;
    int k;
    if ("is-installed".equalsIgnoreCase(paramString))
    {
      str5 = paramArrayOfString[0];
      k = 0;
      if (paramArrayOfString.length <= i);
    }
    while (true)
    {
      int j;
      try
      {
        int m = Integer.parseInt(paramArrayOfString[1]);
        k = m;
        j = PackageUtil.isAppInstalled(str5, k);
        return j;
      }
      catch (Exception localException)
      {
        j = 0;
        continue;
      }
      boolean bool;
      if ("can-handle".equalsIgnoreCase(paramString))
      {
        String str2 = "";
        String str3 = "";
        String str4 = "";
        if (paramArrayOfString.length > j)
        {
          str2 = paramArrayOfString[j];
          if (paramArrayOfString.length > 2)
            str3 = paramArrayOfString[2];
          if (paramArrayOfString.length > 3)
            str4 = paramArrayOfString[3];
        }
        bool = PackageUtil.canHandleIntent(ApplicationAdapter.getInstance().getApplicationContext(), paramArrayOfString[0], str2, str3, str4);
        continue;
      }
      if ("is-true".equalsIgnoreCase(paramString))
      {
        bool = getBooleanVariable(paramArrayOfString[0]);
        continue;
      }
      if ("is-false".equalsIgnoreCase(paramString))
      {
        if (!getBooleanVariable(paramArrayOfString[0]))
          continue;
        bool = false;
        continue;
      }
      if ("equals".equalsIgnoreCase(paramString))
      {
        String str1 = paramArrayOfString[0];
        bool = paramArrayOfString[bool].equalsIgnoreCase(getVariable(str1));
        continue;
      }
      if (!"set".equalsIgnoreCase(paramString))
        continue;
      setVariable(paramArrayOfString[0], paramArrayOfString[bool]);
    }
  }

  private static enum Operator
  {
    static
    {
      AND = new Operator("AND", 1);
      Operator[] arrayOfOperator = new Operator[2];
      arrayOfOperator[0] = OR;
      arrayOfOperator[1] = AND;
      $VALUES = arrayOfOperator;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.VLActionEvaluator
 * JD-Core Version:    0.6.0
 */