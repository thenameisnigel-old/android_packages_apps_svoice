package com.vlingo.midas.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class SimpleListScreen extends ListActivity
{
  public static final String EXTRA_LIST_PARAM = "listitemparam";
  protected SimpleAdapter m_adapter;
  private boolean m_forwardToNextActivity = false;
  protected ArrayList<HashMap<String, Object>> m_list = new ArrayList();
  protected ArrayList<HashMap<String, Object>> m_targets = new ArrayList();

  private void addListItem(String paramString1, String paramString2, Class<?> paramClass, String paramString3)
  {
    HashMap localHashMap1 = new HashMap();
    localHashMap1.put("line1", paramString1);
    localHashMap1.put("line2", paramString2);
    this.m_list.add(localHashMap1);
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("activity", paramClass);
    localHashMap2.put("url", paramString3);
    this.m_targets.add(localHashMap2);
  }

  protected void addListItem(String paramString1, String paramString2, Class<?> paramClass)
  {
    addListItem(paramString1, paramString2, paramClass, null);
  }

  protected void addListItem(String paramString1, String paramString2, String paramString3)
  {
    addListItem(paramString1, paramString2, null, paramString3);
  }

  protected SimpleAdapter initAdapter()
  {
    ArrayList localArrayList = this.m_list;
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "line1";
    arrayOfString[1] = "line2";
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = 16908308;
    arrayOfInt[1] = 16908309;
    return new SimpleAdapter(this, localArrayList, 17367044, arrayOfString, arrayOfInt);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.m_adapter = initAdapter();
    onInitListItems();
    setListAdapter(this.m_adapter);
    getListView().setOnItemClickListener(new SimpleListScreen.1(this));
  }

  protected abstract void onInitListItems();

  protected void setForwardToNextActivity(boolean paramBoolean)
  {
    this.m_forwardToNextActivity = paramBoolean;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.ui.SimpleListScreen
 * JD-Core Version:    0.6.0
 */