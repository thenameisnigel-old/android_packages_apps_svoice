package com.vlingo.sdk.recognition.dialog;

import android.util.Pair;
import com.vlingo.sdk.internal.http.HttpUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VLDialogEvent
{
  ArrayList<VLDialogEventFieldGroup> fieldGroupList;
  ArrayList<Pair<String, String>> fieldList;
  String name;

  VLDialogEvent(Builder paramBuilder)
  {
    this.name = paramBuilder.name;
    this.fieldList = new ArrayList(paramBuilder.fieldList);
    this.fieldGroupList = new ArrayList(paramBuilder.fieldGroupList);
  }

  void generateXML(String paramString, StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append("<").append(paramString).append(" ");
    paramStringBuilder.append(HttpUtil.genAtr("n", this.name));
    paramStringBuilder.append(">");
    Iterator localIterator1 = this.fieldList.iterator();
    while (localIterator1.hasNext())
    {
      Pair localPair = (Pair)localIterator1.next();
      paramStringBuilder.append("<Field ");
      paramStringBuilder.append(HttpUtil.genAtr("n", (String)localPair.first));
      paramStringBuilder.append(HttpUtil.genAtr("v", (String)localPair.second));
      paramStringBuilder.append("/>");
    }
    Iterator localIterator2 = this.fieldGroupList.iterator();
    while (localIterator2.hasNext())
      ((VLDialogEventFieldGroup)localIterator2.next()).generateXML("FieldGroup", paramStringBuilder);
    paramStringBuilder.append("</").append(paramString).append(">");
  }

  public List<VLDialogEventFieldGroup> getFieldGroups()
  {
    return new ArrayList(this.fieldGroupList);
  }

  public List<Pair<String, String>> getFields()
  {
    return new ArrayList(this.fieldList);
  }

  public String getName()
  {
    return this.name;
  }

  public String getXML()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    generateXML("Event", localStringBuilder);
    return localStringBuilder.toString();
  }

  public static class Builder
  {
    private ArrayList<VLDialogEventFieldGroup> fieldGroupList;
    private ArrayList<Pair<String, String>> fieldList;
    private String name;

    public Builder(String paramString)
    {
      this.name = paramString;
      this.fieldList = new ArrayList();
      this.fieldGroupList = new ArrayList();
    }

    public VLDialogEvent build()
    {
      return new VLDialogEvent(this);
    }

    public Builder eventField(String paramString1, String paramString2)
    {
      this.fieldList.add(new Pair(paramString1, paramString2));
      return this;
    }

    public Builder eventFieldGroup(VLDialogEventFieldGroup paramVLDialogEventFieldGroup)
    {
      this.fieldGroupList.add(paramVLDialogEventFieldGroup);
      return this;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.dialog.VLDialogEvent
 * JD-Core Version:    0.6.0
 */