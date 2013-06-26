package com.vlingo.sdk.internal.vlservice.response;

import com.vlingo.sdk.internal.util.StringUtils;
import com.vlingo.sdk.internal.util.UrlUtils;
import java.util.Enumeration;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActionList
  implements Cloneable
{
  Vector list = new Vector();

  public static ActionList createActionListFromJSONArray(String paramString)
  {
    Object localObject = new ActionList();
    try
    {
      ActionList localActionList = createActionListFromJSONArray(new JSONArray(paramString));
      localObject = localActionList;
      label22: return localObject;
    }
    catch (JSONException localJSONException)
    {
      break label22;
    }
  }

  public static ActionList createActionListFromJSONArray(JSONArray paramJSONArray)
  {
    ActionList localActionList = new ActionList();
    int i = 0;
    try
    {
      while (i < paramJSONArray.length())
      {
        JSONObject localJSONObject1 = paramJSONArray.getJSONObject(i);
        Action localAction = new Action(localJSONObject1.getString("Name"));
        if (localJSONObject1.has("If"))
          localAction.ifCondition = localJSONObject1.getString("If");
        if (localJSONObject1.has("Else"))
          localAction.elseStatement = localJSONObject1.getString("Else");
        if (localJSONObject1.has("Params"))
        {
          JSONArray localJSONArray = localJSONObject1.getJSONArray("Params");
          for (int j = 0; j < localJSONArray.length(); j++)
          {
            JSONObject localJSONObject2 = localJSONArray.getJSONObject(j);
            localAction.addParameter(localJSONObject2.getString("Name"), localJSONObject2.getString("Value"), null);
          }
        }
        localActionList.addElement(localAction);
        i++;
      }
    }
    catch (JSONException localJSONException)
    {
    }
    return localActionList;
  }

  public static ActionList createActionListFromURL(String paramString)
  {
    ActionList localActionList = new ActionList();
    if ((paramString.startsWith("action:")) || (paramString.startsWith("vvaction:")))
    {
      String str1 = "";
      String[] arrayOfString1 = StringUtils.split(paramString.substring(1 + paramString.indexOf(':')), ',');
      for (int i = 0; i < arrayOfString1.length; i++)
      {
        String str2 = arrayOfString1[i];
        int j = str2.indexOf("?");
        String str3;
        if (j > -1)
        {
          str3 = str2.substring(0, j);
          str1 = str2.substring(j + 1);
        }
        Action localAction;
        while (true)
        {
          localAction = new Action(str3);
          if (str1.length() <= 0)
            break;
          String[] arrayOfString2 = StringUtils.split(str1, '&');
          for (int k = 0; k < arrayOfString2.length; k++)
          {
            String[] arrayOfString3 = StringUtils.split(arrayOfString2[k], '=');
            localAction.addParameter(arrayOfString3[0], UrlUtils.urlDecode(arrayOfString3[1]), null);
          }
          str3 = str2;
        }
        localActionList.addElement(localAction);
      }
    }
    return localActionList;
  }

  public static ActionList createActionListFromXML(String paramString)
  {
    return VLServiceResponse.createFromXml(paramString).getActionList();
  }

  public void addElement(Action paramAction)
  {
    monitorenter;
    try
    {
      this.list.addElement(paramAction);
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

  public Object clone()
    throws CloneNotSupportedException
  {
    ActionList localActionList = (ActionList)super.clone();
    localActionList.list = ((Vector)this.list.clone());
    return localActionList;
  }

  public boolean containsActionWithName(String paramString)
  {
    int i = 0;
    if (i < size())
      if (!paramString.equalsIgnoreCase(elementAt(i).name));
    for (int j = 1; ; j = 0)
    {
      return j;
      i++;
      break;
    }
  }

  public boolean containsErrorMessage()
  {
    int i = 0;
    if (i < size())
    {
      Action localAction = elementAt(i);
      if ((!localAction.getName().equals("ShowMessage")) || (!localAction.getStringParamValue("Type").equals("error")));
    }
    for (int j = 1; ; j = 0)
    {
      return j;
      i++;
      break;
    }
  }

  public Action elementAt(int paramInt)
  {
    return (Action)this.list.elementAt(paramInt);
  }

  public Enumeration elements()
  {
    monitorenter;
    try
    {
      Enumeration localEnumeration = this.list.elements();
      monitorexit;
      return localEnumeration;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public Action getActionByName(String paramString)
  {
    int i = 0;
    Action localAction;
    if (i < size())
    {
      localAction = elementAt(i);
      if (!paramString.equalsIgnoreCase(localAction.name));
    }
    while (true)
    {
      return localAction;
      i++;
      break;
      localAction = null;
    }
  }

  public boolean isEmpty()
  {
    return this.list.isEmpty();
  }

  public void removeActionByName(String paramString)
  {
    for (int i = 0; ; i++)
    {
      if (i < size())
      {
        Action localAction = elementAt(i);
        if (!paramString.equalsIgnoreCase(localAction.name))
          continue;
        this.list.removeElement(localAction);
      }
      return;
    }
  }

  public void removeAllElements()
  {
    monitorenter;
    try
    {
      this.list.removeAllElements();
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

  public int size()
  {
    return this.list.size();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.vlservice.response.ActionList
 * JD-Core Version:    0.6.0
 */