package com.vlingo.core.internal.localsearch;

import android.telephony.PhoneNumberUtils;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChineseLocalSearchListing extends LocalSearchListing
{
  public static final String FIELD_ADDR_CITY = "city";
  public static final String FIELD_ADDR_STREET_NAME = "address";
  public static final String FIELD_CLICK_URL = "business_url";
  public static final String FIELD_DISTANCE = "distance";
  public static final String FIELD_LISTING_ID = "business_id";
  public static final String FIELD_NAME = "name";
  public static final String FIELD_PHONE_NUMBER = "telephone";
  public static final String FIELD_PROVIDER = "Provider";
  public static final String FIELD_RATING = "avg_rating";
  public static final String FIELD_REVIEW_COUNT = "review_count";
  public static final String FIELD_REVIEW_ID = "review_id";
  public static final String FIELD_REVIEW_RATING = "review_rating";
  public static final String FIELD_REVIEW_REVIEW_DATE = "created_time";
  public static final String FIELD_REVIEW_TEXT = "text_excerpt";
  public static final String FIELD_REVIEW_USER_NAME = "user_nickname";

  public ChineseLocalSearchListing(String paramString)
  {
    super("business_id", paramString);
  }

  private void getAddressLine1(StringBuilder paramStringBuilder)
  {
    if (hasValue("address"))
      paramStringBuilder.append(getString("address"));
  }

  private void getAddressLine2(StringBuilder paramStringBuilder)
  {
    if (hasValue("city"))
    {
      if (paramStringBuilder.length() > 0)
        paramStringBuilder.append(", ");
      paramStringBuilder.append(getString("city"));
    }
  }

  public String getAddressLine1()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    getAddressLine1(localStringBuilder);
    return localStringBuilder.toString();
  }

  public String getAddressLine2()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    getAddressLine2(localStringBuilder);
    return localStringBuilder.toString();
  }

  public String getCaption()
  {
    return null;
  }

  public String getCityState()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (hasValue("city"))
      localStringBuilder.append(getString("city"));
    return localStringBuilder.toString();
  }

  public String getClickUrl()
  {
    return getString("business_url");
  }

  public String getDistanceString()
  {
    double d;
    DecimalFormat localDecimalFormat;
    if (hasValue("distance"))
    {
      d = getDouble("distance", -1.0D);
      if (d != -1.0D)
        localDecimalFormat = new DecimalFormat("######.##");
    }
    for (String str = localDecimalFormat.format(d) + " m"; ; str = "")
      return str;
  }

  public String getFullAddress()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (hasValue("address"))
    {
      localStringBuilder.append(getString("address"));
      localStringBuilder.append(" ");
    }
    if (hasValue("city"))
    {
      if (localStringBuilder.length() > 0)
        localStringBuilder.append("\n");
      localStringBuilder.append(getString("city"));
    }
    return localStringBuilder.toString();
  }

  public String getListingID()
  {
    return getString("business_id");
  }

  public String getName()
  {
    return getString("name");
  }

  public String getPhoneNumber()
  {
    return getString("telephone");
  }

  public String getPhoneNumberFormatted()
  {
    return PhoneNumberUtils.formatNumber(getPhoneNumber());
  }

  public String getProvider()
  {
    return getString("Provider");
  }

  public double getRating()
  {
    return getDouble("avg_rating", 0.0D);
  }

  public String getReserveUrl()
  {
    return null;
  }

  public int getReviewCount()
  {
    return getInteger("review_count", 0);
  }

  public String getSynopsis()
  {
    return null;
  }

  public String getUrl()
  {
    return null;
  }

  public boolean isOrganic()
  {
    return false;
  }

  public boolean isSponsored()
  {
    return false;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(" LocalSearchChineseListing [");
    localStringBuilder.append("\n Name :").append(getName());
    localStringBuilder.append("\n AddressLine1 :").append(getAddressLine1());
    localStringBuilder.append("\n AddressLine2 :").append(getAddressLine2());
    localStringBuilder.append("\n CityState :").append(getCityState());
    localStringBuilder.append("\n FullAddress :").append(getFullAddress());
    localStringBuilder.append("\n DistanceString :").append(getDistanceString());
    localStringBuilder.append("\n ListingID :").append(getListingID());
    localStringBuilder.append("\n PhoneNumber :").append(getPhoneNumber());
    localStringBuilder.append("\n Rating :").append(getRating());
    localStringBuilder.append("\n ReviewCount :").append(getReviewCount());
    localStringBuilder.append("\n Reviews : {").append(getReviews().toString()).append("}");
    localStringBuilder.append("\n hasReviews :").append(hasReviews());
    localStringBuilder.append("\n isOrganic :").append(isOrganic());
    localStringBuilder.append("\n isSponsored :").append(isSponsored());
    localStringBuilder.append("\n areMoreDetailsAvailable :").append(areMoreDetailsAvailable());
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.localsearch.ChineseLocalSearchListing
 * JD-Core Version:    0.6.0
 */