package com.vlingo.midas.ui;

import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class SimpleIconListScreen extends SimpleListScreen
{
  protected int m_listItemResource = 2130903123;

  protected void addListItem(String paramString1, String paramString2, int paramInt, Class<?> paramClass)
  {
    addListItem(paramString1, paramString2, paramInt, paramClass, null);
  }

  protected void addListItem(String paramString1, String paramString2, int paramInt, Class<?> paramClass, String paramString3)
  {
    HashMap localHashMap1 = new HashMap();
    localHashMap1.put("line1", paramString1);
    localHashMap1.put("line2", paramString2);
    localHashMap1.put("icon1", Integer.valueOf(paramInt));
    this.m_list.add(localHashMap1);
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("activity", paramClass);
    localHashMap2.put("url", paramString3);
    this.m_targets.add(localHashMap2);
  }

  protected SimpleAdapter initAdapter()
  {
    ArrayList localArrayList = this.m_list;
    int i = this.m_listItemResource;
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "icon1";
    arrayOfString[1] = "line1";
    arrayOfString[2] = "line2";
    int[] arrayOfInt = new int[3];
    arrayOfInt[0] = 16908295;
    arrayOfInt[1] = 16908308;
    arrayOfInt[2] = 16908309;
    return new SimpleAdapter(this, localArrayList, i, arrayOfString, arrayOfInt);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.ui.SimpleIconListScreen
 * JD-Core Version:    0.6.0
 */