package com.vlingo.core.internal.localsearch;

import android.util.Pair;
import com.vlingo.sdk.internal.http.HttpResponse;
import java.util.Vector;

public abstract interface LocalSearchParser
{
  public abstract Pair<LocalSearchRequestInfo, Vector<LocalSearchListing>> parseLocalSearchResponse(HttpResponse paramHttpResponse, LocalSearchListing paramLocalSearchListing);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.localsearch.LocalSearchParser
 * JD-Core Version:    0.6.0
 */