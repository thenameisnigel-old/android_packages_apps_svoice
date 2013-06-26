package com.vlingo.core.internal.localsearch;

import android.util.Pair;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.vcs.WidgetResponseReceivedListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

public class LocalSearchAdaptor
  implements LocalSearchRequestListener
{
  private String currentSearchQuery = "";
  private String currentSpokenLocation = "";
  private String currentSpokenSearch;
  private String failReason = null;
  private boolean isRequestComplete = false;
  private boolean isRequestFailed = false;
  private Vector<LocalSearchListing> localSearchDetailsListing;
  private Vector<LocalSearchListing> localSearchListings = new Vector();
  private LocalSearchServiceManager lsManager = new LocalSearchServiceManager();
  private int numberOfRetries = 0;
  private HashSet<String> providers;
  private WidgetResponseReceivedListener widgetListener = null;

  private void executeSearch(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    this.isRequestComplete = false;
    if (paramString1 == null)
      paramString1 = "";
    if (this.currentSearchQuery == null)
      this.currentSearchQuery = "";
    if (this.currentSearchQuery.equals(paramString1))
      this.numberOfRetries = (1 + this.numberOfRetries);
    if ((paramString1.length() > 0) && ((!this.currentSearchQuery.equals(paramString1)) || (paramBoolean)))
    {
      this.currentSearchQuery = paramString1;
      this.currentSpokenSearch = paramString1;
      this.currentSpokenLocation = paramString2;
      if (!VlingoAndroidCore.isChineseBuild())
        break label126;
      if (!Settings.getString("language", "zh-CN").equals("zh-CN"))
        break label117;
      this.lsManager.sendChineseSearchRequest(paramString1, paramString2, paramString3, this);
    }
    while (true)
    {
      return;
      label117: onRequestFailed("Not match conditon");
      continue;
      label126: this.lsManager.sendSearchRequest(paramString1, paramString2, this);
    }
  }

  private void setLocalSearchListings(Vector<LocalSearchListing> paramVector)
  {
    synchronized (this.localSearchListings)
    {
      this.localSearchListings = paramVector;
      setProviders(paramVector);
      return;
    }
  }

  private void setProviders(Vector<LocalSearchListing> paramVector)
  {
    this.providers = new HashSet();
    Iterator localIterator = paramVector.iterator();
    while (localIterator.hasNext())
    {
      LocalSearchListing localLocalSearchListing = (LocalSearchListing)localIterator.next();
      if (localLocalSearchListing.getProvider() == null)
        continue;
      String str = localLocalSearchListing.getProvider();
      this.providers.add(str);
    }
  }

  public void executeDetailRequest(LocalSearchListing paramLocalSearchListing, WidgetResponseReceivedListener paramWidgetResponseReceivedListener)
  {
    if (VlingoAndroidCore.isChineseBuild())
      this.lsManager.sendChineseMoreDetailsRequest(paramLocalSearchListing, new DetailsLocalSearchRequestListener(paramWidgetResponseReceivedListener));
    while (true)
    {
      return;
      this.lsManager.sendMoreDetailsRequest(paramLocalSearchListing, new DetailsLocalSearchRequestListener(paramWidgetResponseReceivedListener));
    }
  }

  public void executeForceSearch(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    executeSearch(paramString1, paramString2, paramString3, paramBoolean);
  }

  public void executeSearch(String paramString1, String paramString2, String paramString3)
  {
    executeSearch(paramString1, paramString2, paramString3, false);
  }

  public int getCount()
  {
    synchronized (this.localSearchListings)
    {
      int i = this.localSearchListings.size();
      return i;
    }
  }

  public String getCurrentSearchQuery()
  {
    return this.currentSearchQuery;
  }

  public String getCurrentSpokenLocation()
  {
    return this.currentSpokenLocation;
  }

  public String getCurrentSpokenSearch()
  {
    return this.currentSpokenSearch;
  }

  public String getFailReason()
  {
    return this.failReason;
  }

  public LocalSearchListing getItem(int paramInt)
  {
    LocalSearchListing localLocalSearchListing;
    synchronized (this.localSearchListings)
    {
      if ((this.localSearchListings.size() > 0) && (this.localSearchListings.size() > paramInt))
        localLocalSearchListing = (LocalSearchListing)this.localSearchListings.get(paramInt);
      else
        localLocalSearchListing = null;
    }
    return localLocalSearchListing;
  }

  public Vector<LocalSearchListing> getLocalSearchDetailsListing()
  {
    synchronized (this.localSearchDetailsListing)
    {
      Vector localVector2 = this.localSearchDetailsListing;
      return localVector2;
    }
  }

  public int getNumberOfRetries()
  {
    return this.numberOfRetries;
  }

  public WidgetResponseReceivedListener getWidgetListener()
  {
    return this.widgetListener;
  }

  public boolean isRequestComplete()
  {
    return this.isRequestComplete;
  }

  public boolean isRequestFailed()
  {
    return this.isRequestFailed;
  }

  public void onRequestComplete(boolean paramBoolean, Object paramObject)
  {
    this.isRequestComplete = true;
    if ((paramBoolean) && ((paramObject instanceof Pair)) && ((((Pair)paramObject).second instanceof Vector)))
    {
      Pair localPair = (Pair)paramObject;
      setLocalSearchListings((Vector)localPair.second);
      if (!StringUtils.isNullOrWhiteSpace(((LocalSearchRequestInfo)localPair.first).getQuery()))
        this.currentSearchQuery = ((LocalSearchRequestInfo)localPair.first).getQuery();
      if (!StringUtils.isNullOrWhiteSpace(((LocalSearchRequestInfo)localPair.first).getLocalizedCityState()))
        this.currentSpokenLocation = ((LocalSearchRequestInfo)localPair.first).getLocalizedCityState();
    }
    if (this.widgetListener != null)
      this.widgetListener.onResponseReceived();
  }

  public void onRequestFailed(String paramString)
  {
    this.isRequestComplete = true;
    this.isRequestFailed = true;
    this.failReason = paramString;
    if (this.widgetListener != null)
      this.widgetListener.onRequestFailed();
  }

  public void onRequestScheduled()
  {
    this.isRequestComplete = false;
    this.isRequestFailed = false;
    if (this.widgetListener != null)
      this.widgetListener.onRequestScheduled();
  }

  public void resetNumberOfRetries()
  {
    this.numberOfRetries = 0;
  }

  public void resetSearch()
  {
    synchronized (this.localSearchListings)
    {
      this.localSearchListings.clear();
      return;
    }
  }

  public void setLocalSearchDetailsListing(Vector<LocalSearchListing> paramVector)
  {
    monitorenter;
    try
    {
      this.localSearchDetailsListing = paramVector;
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

  public void setWidgetListener(WidgetResponseReceivedListener paramWidgetResponseReceivedListener)
  {
    this.widgetListener = paramWidgetResponseReceivedListener;
  }

  private class DetailsLocalSearchRequestListener
    implements LocalSearchRequestListener
  {
    private WidgetResponseReceivedListener widgetDetailResponseListener;

    public DetailsLocalSearchRequestListener(WidgetResponseReceivedListener arg2)
    {
      Object localObject;
      this.widgetDetailResponseListener = localObject;
    }

    public void onRequestComplete(boolean paramBoolean, Object paramObject)
    {
      if (((paramObject instanceof Pair)) && ((((Pair)paramObject).second instanceof Vector)))
        LocalSearchAdaptor.this.setLocalSearchDetailsListing((Vector)((Pair)paramObject).second);
      this.widgetDetailResponseListener.onResponseReceived();
    }

    public void onRequestFailed(String paramString)
    {
      this.widgetDetailResponseListener.onRequestFailed();
    }

    public void onRequestScheduled()
    {
      this.widgetDetailResponseListener.onRequestScheduled();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.localsearch.LocalSearchAdaptor
 * JD-Core Version:    0.6.0
 */