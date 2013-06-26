package com.vlingo.core.internal.localsearch;

import android.util.Pair;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.sdk.internal.http.HttpResponse;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocalSearchChineseResponseParser
  implements LocalSearchParser
{
  private static final String ARRAY_NAME_BUSINESS = "businesses";
  private static final String ARRAY_NAME_REVIEWS = "reviews";

  private void addJSONToBusinessItem(JSONObject paramJSONObject, ChineseLocalSearchListing paramChineseLocalSearchListing)
    throws JSONException
  {
    paramChineseLocalSearchListing.putValue("city", paramJSONObject.getString("city"));
    paramChineseLocalSearchListing.putValue("address", paramJSONObject.getString("address"));
    paramChineseLocalSearchListing.putValue("business_url", paramJSONObject.getString("business_url"));
    paramChineseLocalSearchListing.putValue("distance", paramJSONObject.getString("distance"));
    paramChineseLocalSearchListing.putValue("business_id", paramJSONObject.getString("business_id"));
    paramChineseLocalSearchListing.putValue("name", paramJSONObject.getString("name"));
    paramChineseLocalSearchListing.putValue("telephone", paramJSONObject.getString("telephone"));
    paramChineseLocalSearchListing.putValue("avg_rating", paramJSONObject.getString("avg_rating"));
    paramChineseLocalSearchListing.putValue("review_count", paramJSONObject.getString("review_count"));
    paramChineseLocalSearchListing.putValue("Provider", VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_localsearch_provider_dianping));
  }

  private LocalSearchListing.Review parseReviews(JSONObject paramJSONObject, ChineseLocalSearchListing paramChineseLocalSearchListing)
    throws JSONException
  {
    LocalSearchListing.Review localReview = new LocalSearchListing.Review();
    localReview.author = paramJSONObject.getString("user_nickname");
    localReview.body = paramJSONObject.getString("text_excerpt");
    localReview.date = paramJSONObject.getString("created_time");
    localReview.id = paramJSONObject.getString("review_id");
    localReview.rating = paramJSONObject.getString("review_rating");
    return localReview;
  }

  public Pair<LocalSearchRequestInfo, Vector<LocalSearchListing>> parseLocalSearchResponse(HttpResponse paramHttpResponse, LocalSearchListing paramLocalSearchListing)
  {
    Vector localVector = new Vector();
    LocalSearchRequestInfo localLocalSearchRequestInfo = new LocalSearchRequestInfo();
    try
    {
      LocalSearchListingCache localLocalSearchListingCache = ApplicationAdapter.getInstance().getBusinessItemCache();
      JSONObject localJSONObject1 = new JSONObject(paramHttpResponse.getDataAsString());
      if (localJSONObject1.getString("status").equals("OK"))
      {
        int i = localJSONObject1.getInt("count");
        if (localJSONObject1.has("businesses"))
        {
          JSONArray localJSONArray = localJSONObject1.getJSONArray("businesses");
          for (int k = 0; k < i; k++)
          {
            JSONObject localJSONObject2 = localJSONArray.getJSONObject(k);
            String str = localJSONObject2.getString("business_id");
            ChineseLocalSearchListing localChineseLocalSearchListing2 = null;
            if (0 == 0)
              localChineseLocalSearchListing2 = new ChineseLocalSearchListing(str);
            addJSONToBusinessItem(localJSONObject2, localChineseLocalSearchListing2);
            localVector.add(localChineseLocalSearchListing2);
            localLocalSearchListingCache.add(localChineseLocalSearchListing2);
          }
        }
        if ((localJSONObject1.has("reviews")) && (paramLocalSearchListing != null))
        {
          ChineseLocalSearchListing localChineseLocalSearchListing1 = (ChineseLocalSearchListing)paramLocalSearchListing;
          for (int j = 0; j < i; j++)
            localChineseLocalSearchListing1.addReview(parseReviews(localJSONObject1.getJSONArray("reviews").getJSONObject(j), localChineseLocalSearchListing1));
          localVector.add(localChineseLocalSearchListing1);
          localLocalSearchListingCache.add(localChineseLocalSearchListing1);
        }
      }
      localPair = new Pair(localLocalSearchRequestInfo, localVector);
      return localPair;
    }
    catch (Exception localException)
    {
      while (true)
        Pair localPair = null;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.localsearch.LocalSearchChineseResponseParser
 * JD-Core Version:    0.6.0
 */